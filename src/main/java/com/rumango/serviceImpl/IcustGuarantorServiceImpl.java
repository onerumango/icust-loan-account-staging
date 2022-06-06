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
import com.rumango.model.IcustCollateralMasterModel;
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
					return ResponseEntity.status(HttpStatus.OK).body(guarantorDetailsRepo.save(guarantorObj.get()));
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(guarantorDetailsRepo.save(guarantorData));
				}
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
		if(!Strings.isNullOrEmpty(newGuarantor.getAddress1()))
			oldGuarantor.setAddress1(newGuarantor.getAddress1());
		if(!Strings.isNullOrEmpty(newGuarantor.getAddress2()))
			oldGuarantor.setAddress2(newGuarantor.getAddress2());
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
		if(newGuarantor.getCifNumber()!=null)
			oldGuarantor.setCifNumber(newGuarantor.getCifNumber());
	}

	@Override
	public ResponseEntity<?> fetchGuarantorByLoanAccId(Long loanAccountId) {
		try {
			if (loanAccountId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");

			Optional<IcustGuarantorDetails> guarantorObj = guarantorDetailsRepo.findByLoanAccountId(loanAccountId);
			if (guarantorObj.isPresent()) {
				IcustGuarantorDetailsModel guarantorInfo = new Gson().fromJson(new Gson().toJson(guarantorObj.get()),
						IcustGuarantorDetailsModel.class);
				return ResponseEntity.status(HttpStatus.OK).body(guarantorInfo);
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
