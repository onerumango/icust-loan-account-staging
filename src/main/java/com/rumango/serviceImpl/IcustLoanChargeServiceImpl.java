package com.rumango.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.apache.logging.log4j.util.Strings;
//import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustLaonChargeDetails;
import com.rumango.model.IcustLoanChargeModel;
import com.rumango.repository.IcustLoanChargeRepo;
import com.rumango.service.IcustLoanChargeService;

@Service
public class IcustLoanChargeServiceImpl implements IcustLoanChargeService{

	private static final Logger logger= LogManager.getLogger(IcustLoanChargeServiceImpl.class);
	@Autowired
	IcustLoanChargeRepo icustLoanChargeRepo;
	
	@Override
	public ResponseEntity<?> upsertLoanChargeDetails(IcustLoanChargeModel icustLoanChargeModel) {
		// TODO Auto-generated method stub
		try {
			if(icustLoanChargeModel.getLoanAccountId()==null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("LoanAccountId is Mandotory");
			}else {
				Optional<IcustLaonChargeDetails> chargeObj=
						icustLoanChargeRepo.findByLoanAccountId(icustLoanChargeModel.getLoanAccountId());
				IcustLaonChargeDetails chargeData=
						new Gson().fromJson(new Gson().toJson(icustLoanChargeModel), IcustLaonChargeDetails.class);
				if(chargeObj.isPresent()) {
					validatorChargeDetails(chargeObj.get(), chargeData);
					return ResponseEntity.status(HttpStatus.OK).body(icustLoanChargeRepo.save(chargeObj.get()));
				}else {
					return ResponseEntity.status(HttpStatus.OK).body(icustLoanChargeRepo.save(chargeData));
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Execption occure while upsertLoanChargeDetails",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	private void validatorChargeDetails(IcustLaonChargeDetails oldDetails, IcustLaonChargeDetails newDetails) {
		if(newDetails.getLoanAccountId()!=null) {
			oldDetails.setLoanAccountId(newDetails.getLoanAccountId());
		}
		if(!Strings.isNullOrEmpty(newDetails.getInterestType())) {
			oldDetails.setInterestType(newDetails.getInterestType());
		}
		if(newDetails.getAmout()!=null) {
			oldDetails.setAmout(newDetails.getAmout());
		}
		if(newDetails.getWaiver()!=null) {
			oldDetails.setWaiver(newDetails.getWaiver());
		}
	}

	@Override
	public ResponseEntity<?> fetchLoanChargeDetails() {
		// TODO Auto-generated method stub
		try {
			List<IcustLaonChargeDetails> list=icustLoanChargeRepo.findAll();
			if(!CollectionUtils.isEmpty(list)) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			}else {
				logger.error("No Details found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Details Found");
			}
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Execption occure while fetchLoanChargeDetails",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchLoanChargeDetailsById(Long loanAccoutId) {
		// TODO Auto-generated method stub
		try {
			if(loanAccoutId == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Loan Account id is Mandatory");
			}else {
				Optional<IcustLaonChargeDetails> chargeObj= icustLoanChargeRepo.findByLoanAccountId(loanAccoutId);
				if(chargeObj.isPresent()) {
					return ResponseEntity.status(HttpStatus.OK).body(chargeObj.get());
				}else {
					logger.info("NO record exist for this given loan Account Id");
					return ResponseEntity.status(HttpStatus.NO_CONTENT).body("NO record exist for this given loan Account Id");
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Execption occure while fetchLoanChargeById",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
