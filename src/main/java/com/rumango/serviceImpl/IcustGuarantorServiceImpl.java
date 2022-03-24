package com.rumango.serviceImpl;

import java.text.MessageFormat;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustCustomerInfo;
import com.rumango.entity.IcustGuarantorDetails;
import com.rumango.model.IcustCustomerCreateModel;
import com.rumango.model.IcustGuarantorDetailsModel;
import com.rumango.repository.IcustCustomerInfoRepo;
import com.rumango.repository.IcustGuarantorDetailsRepo;
import com.rumango.service.IcustGuarantorService;

@Service
public class IcustGuarantorServiceImpl implements IcustGuarantorService {
	private static final Logger logger = Logger.getLogger(IcustGuarantorServiceImpl.class);

	@Autowired
	IcustGuarantorDetailsRepo guarantorDetailsRepo;
	@Autowired
	IcustCustomerInfoRepo icustCustomerRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<?> upsertGuarantorDetails(IcustGuarantorDetailsModel icustGuarantorDetailsModel) {
		try {
			IcustGuarantorDetails gurantorDetails = null;
			if (icustGuarantorDetailsModel.getLoanAccountId() == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			else {
				Optional<IcustGuarantorDetails> guarantorObj = guarantorDetailsRepo
						.findByLoanAccountId(icustGuarantorDetailsModel.getLoanAccountId());
				IcustGuarantorDetails guarantorData = new Gson().fromJson(new Gson().toJson(icustGuarantorDetailsModel),
						IcustGuarantorDetails.class);
				if (guarantorObj.isPresent()) {
					validateGuarantorDetails(guarantorObj.get(), guarantorData);
					gurantorDetails = guarantorDetailsRepo.save(guarantorObj.get());
				} else {
					IcustCustomerInfo custInfo = new Gson().fromJson(
							new Gson().toJson(icustGuarantorDetailsModel.getCustomerInfo()), IcustCustomerInfo.class);
					IcustCustomerInfo saveObj = icustCustomerRepo.save(custInfo);
					guarantorData.setCustomerId(saveObj.getCustomerId());
					gurantorDetails = guarantorDetailsRepo.save(guarantorData);
				}
				IcustGuarantorDetailsModel guarantorModel =new Gson().fromJson(new Gson().toJson(gurantorDetails),
						IcustGuarantorDetailsModel.class);
				Optional<IcustCustomerInfo> custInfo = icustCustomerRepo.findByCustomerId(guarantorObj.get().getCustomerId());
				if(custInfo.isPresent()) {
					IcustCustomerCreateModel custModel =modelMapper.map(custInfo.get(), IcustCustomerCreateModel.class);
					guarantorModel.setCustomerInfo(custModel);
				}
				return ResponseEntity.status(HttpStatus.OK).body(guarantorModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Exception occoured while upsertGuarantorDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private void validateGuarantorDetails(IcustGuarantorDetails oldGuarantor, IcustGuarantorDetails newGuarantor) {
		if (!Strings.isNullOrEmpty(newGuarantor.getRelationWithCustomer()))
			oldGuarantor.setRelationWithCustomer(newGuarantor.getRelationWithCustomer());
		if(!Strings.isNullOrEmpty(newGuarantor.getBuilding()))
			oldGuarantor.setBuilding(newGuarantor.getBuilding());
		if(!Strings.isNullOrEmpty(newGuarantor.getStreet()))
			oldGuarantor.setStreet(newGuarantor.getStreet());
		if(!Strings.isNullOrEmpty(newGuarantor.getLocality()))
			oldGuarantor.setLocality(newGuarantor.getLocality());
		if(!Strings.isNullOrEmpty(newGuarantor.getCity()))
			oldGuarantor.setCity(newGuarantor.getCity());
		if(!Strings.isNullOrEmpty(newGuarantor.getState()))
			oldGuarantor.setState(newGuarantor.getState());
		if(!Strings.isNullOrEmpty(newGuarantor.getCountry()))
			oldGuarantor.setCountry(newGuarantor.getCountry());
		if(!Strings.isNullOrEmpty(newGuarantor.getZipCode()))
			oldGuarantor.setZipCode(newGuarantor.getZipCode());
		if(!Strings.isNullOrEmpty(newGuarantor.getMobileNumber()))
			oldGuarantor.setMobileNumber(newGuarantor.getMobileNumber());
		if(!Strings.isNullOrEmpty(newGuarantor.getPhoneNumber()))
			oldGuarantor.setPhoneNumber(newGuarantor.getPhoneNumber());
		if(!Strings.isNullOrEmpty(newGuarantor.getEmailAddress()))
			oldGuarantor.setEmailAddress(newGuarantor.getEmailAddress());
	}

	@Override
	public ResponseEntity<?> fetchGuarantorByLoanAccId(Long loanAccountId) {
		try {
			if (loanAccountId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");

			Optional<IcustGuarantorDetails> guarantorObj = guarantorDetailsRepo.findByLoanAccountId(loanAccountId);
			if (guarantorObj.isPresent()) {
				IcustGuarantorDetailsModel guarantorModel =new Gson().fromJson(new Gson().toJson(guarantorObj.get()),
						IcustGuarantorDetailsModel.class);
				Optional<IcustCustomerInfo> custInfo = icustCustomerRepo.findByCustomerId(guarantorObj.get().getCustomerId());
				if(custInfo.isPresent()) {
					System.err.println("customerinfo::"+custInfo.get());
					IcustCustomerCreateModel custModel =modelMapper.map(custInfo.get(), IcustCustomerCreateModel.class);
					guarantorModel.setCustomerInfo(custModel);
				}
				return ResponseEntity.status(HttpStatus.OK).body(guarantorModel);
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Execption occoured while executing fetchGuarantorByLoanAccId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}
}
