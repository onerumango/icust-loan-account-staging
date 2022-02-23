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

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustAssetDetails;
import com.rumango.entity.IcustLoanDisbursementDetails;
import com.rumango.entity.IcustLoanInfo;
import com.rumango.model.IcustLoanDisbursementModel;
import com.rumango.repository.IcustLoanDisbursementRepo;
import com.rumango.service.IcustLoanDisbursementService;

@Service
public class IcustLoanDisbursementServiceImpl implements IcustLoanDisbursementService{
	
	private static final Logger logger = LogManager.getLogger(IcustLoanDisbursementServiceImpl.class);

	@Autowired
	IcustLoanDisbursementRepo icustLoanDisbursementRepo;

	@Override
	public ResponseEntity<?> upsertLoanDisbursementDetails(IcustLoanDisbursementModel icustLoanDisbursementModel) {
		// TODO Auto-generated method stub
		try {
			if (icustLoanDisbursementModel.getLoanId()==null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanId is Mandatory");
			else {
			Optional<IcustLoanDisbursementDetails> obj = icustLoanDisbursementRepo
					.findById(icustLoanDisbursementModel.getLoanId());
			IcustLoanDisbursementDetails data = new Gson().fromJson(new Gson().toJson(icustLoanDisbursementModel),
					IcustLoanDisbursementDetails.class);
			if (obj.isPresent()) {
				validateLoanDetails(obj.get(),data);
				return ResponseEntity.status(HttpStatus.OK).body(icustLoanDisbursementRepo.save(obj.get()));
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(icustLoanDisbursementRepo.save(data));
			}
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertFinancialDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	private void validateLoanDetails(IcustLoanDisbursementDetails oldLoanDisb, IcustLoanDisbursementDetails newLoanDisb) {
		if(newLoanDisb.getMutipleDisbursementRequired()!= null )
			oldLoanDisb.setMutipleDisbursementRequired(newLoanDisb.getMutipleDisbursementRequired());
		if(newLoanDisb.getLoanId()!= null)
			oldLoanDisb.setLoanId(newLoanDisb.getLoanId());
		if(newLoanDisb.getLoanAmount()!=null)
			oldLoanDisb.setLoanAmount(newLoanDisb.getLoanAmount());
		if(newLoanDisb.getNumberOfDisbursement()!=null)
			oldLoanDisb.setNumberOfDisbursement(newLoanDisb.getNumberOfDisbursement());
		if(newLoanDisb.getFirstDisbursementDate()!=null)
			oldLoanDisb.setFirstDisbursementDate(newLoanDisb.getFirstDisbursementDate());
		if(newLoanDisb.getTotalDisbursement()!=null)
			oldLoanDisb.setTotalDisbursement(newLoanDisb.getTotalDisbursement());
		if(!Strings.isNullOrEmpty(newLoanDisb.getDisbursementMode()))
			oldLoanDisb.setDisbursementMode(newLoanDisb.getDisbursementMode());
		if(newLoanDisb.getCustomerAccount()!=null)
			oldLoanDisb.setCustomerAccount(newLoanDisb.getCustomerAccount());
		if(!Strings.isNullOrEmpty(newLoanDisb.getBranchCode()))
			oldLoanDisb.setBranchCode(newLoanDisb.getBranchCode());
		
	}

	@Override
	public ResponseEntity<?> fetchLoanDisbursementDetails() {
		// TODO Auto-generated method stub
		try {
			List<IcustLoanDisbursementDetails> list = icustLoanDisbursementRepo.findAll();
			if (!CollectionUtils.isEmpty(list)) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			} else {
				logger.error("No details found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No details found");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanDisbursementDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchLoanDisbursementById(Long loanId) {
		// TODO Auto-generated method stub
		try {
			if (loanId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("loanId is Mandatory");
			
			Optional<IcustLoanDisbursementDetails> loanDisbObj = icustLoanDisbursementRepo.findByLoanId(loanId);
			if (loanDisbObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(loanDisbObj.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanDisbursementById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}


}
