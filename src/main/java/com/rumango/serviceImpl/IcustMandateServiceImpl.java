package com.rumango.serviceImpl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.rumango.entity.IcustMandateMaster;
import com.rumango.model.IcustAdmissionDetailsModel;
import com.rumango.model.IcustMandateMasterModel;
import com.rumango.repository.IcustMandateMasterRepo;
import com.rumango.service.IcustMandateService;

@Service
public class IcustMandateServiceImpl implements IcustMandateService{
	private static final Logger logger = LogManager.getLogger(IcustMandateServiceImpl.class);

	
	@Autowired
	IcustMandateMasterRepo icustMandateMasterRepo;

	@Override
	public ResponseEntity<?> upsertMandateDetails(IcustMandateMasterModel icustMandateMasterModel) {
		try {
			if (icustMandateMasterModel.getLoanAccountId()==null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			else {
				Optional<IcustMandateMaster> mandateObj = icustMandateMasterRepo
						.findByLoanAccountId(icustMandateMasterModel.getLoanAccountId());
				IcustMandateMaster mandateData = new Gson().fromJson(new Gson().toJson(icustMandateMasterModel),
						IcustMandateMaster.class);
				if (mandateObj.isPresent()) {
					validateMandateDetails(mandateObj.get(),mandateData);
					return ResponseEntity.status(HttpStatus.OK).body(icustMandateMasterRepo.save(mandateObj.get()));
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(icustMandateMasterRepo.save(mandateData));
				}
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertAssetDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private void validateMandateDetails(IcustMandateMaster oldMandateMaster, IcustMandateMaster newMandateMaster) {
		
		if(newMandateMaster.getLoanAccountId()!=null)
			oldMandateMaster.setLoanAccountId(newMandateMaster.getLoanAccountId());
		if(newMandateMaster.getNoOfApplicants()!=null)
			oldMandateMaster.setNoOfApplicants(newMandateMaster.getNoOfApplicants());
		if(newMandateMaster.getRegistered()!=null)
			oldMandateMaster.setRegistered(newMandateMaster.getRegistered());
		if(newMandateMaster.getMandateDetails()!=null)
			oldMandateMaster.setMandateDetails(newMandateMaster.getMandateDetails());
	}

	@Override
	public ResponseEntity<?> fetchMandateDetailsByLoanAccId(Long loanAccountId) {
		try {
			if (loanAccountId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanId is Mandatory");
			
			Optional<IcustMandateMaster> mandateObj = icustMandateMasterRepo.findByLoanAccountId(loanAccountId);
			if (mandateObj.isPresent()) {
				IcustMandateMasterModel mandateInfo = new Gson().fromJson(new Gson().toJson(mandateObj.get()),
						IcustMandateMasterModel.class);
				return ResponseEntity.status(HttpStatus.OK).body(mandateInfo);
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchMandateDetailsByLoanAccId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchMandateInfoById(Long id) {
		try {
			if (id == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is Mandatory");
			
			Optional<IcustMandateMaster> mandateObj = icustMandateMasterRepo.findById(id);
			if (mandateObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(mandateObj);
			} else {
				logger.error("No details found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No details found");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchMandateInfoById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
