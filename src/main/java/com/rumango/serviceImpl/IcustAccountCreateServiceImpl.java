package com.rumango.serviceImpl;

import java.sql.Date;
import java.text.MessageFormat;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustAccountCreate;
import com.rumango.entity.IcustOfferIssue;
import com.rumango.model.IcustAccountCreateModel;
import com.rumango.repository.IcustAccountCreateRepo;
import com.rumango.service.IcustAccountCreateService;

@Service
public class IcustAccountCreateServiceImpl implements IcustAccountCreateService{
	private static final Logger logger = Logger.getLogger(IcustAccountCreateServiceImpl.class);

	@Autowired
	IcustAccountCreateRepo accountCreateRepo;

	@Override
	public ResponseEntity<?> upsertAccountCreate(IcustAccountCreateModel icustAccountCreateModel) {
		try {
			if (icustAccountCreateModel.getLoanAccountId()==null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			else {
				Optional<IcustAccountCreate> accountObj = accountCreateRepo
						.findByLoanAccountId(icustAccountCreateModel.getLoanAccountId());
				IcustAccountCreate accountData = new Gson().fromJson(new Gson().toJson(icustAccountCreateModel),
						IcustAccountCreate.class);
				if (accountObj.isPresent()) {
					validateAccountCreateDetails(accountObj.get(),accountData);
					return ResponseEntity.status(HttpStatus.OK).body(accountCreateRepo.save(accountObj.get()));
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(accountCreateRepo.save(accountData));
				}
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertAccountCreate", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private void validateAccountCreateDetails(IcustAccountCreate oldAccountCreate, IcustAccountCreate newAccountCreate) {
		if(!Strings.isNullOrEmpty(newAccountCreate.getApplicantName()))
			oldAccountCreate.setApplicantName(newAccountCreate.getApplicantName());
		if(newAccountCreate.getApprovedLoanAmount()!=null)
			oldAccountCreate.setApprovedLoanAmount(newAccountCreate.getApprovedLoanAmount());
		if(!Strings.isNullOrEmpty(newAccountCreate.getLoanTenure()))
			oldAccountCreate.setLoanTenure(newAccountCreate.getLoanTenure());
		if(!Strings.isNullOrEmpty(newAccountCreate.getInstallmentType()))
			oldAccountCreate.setInstallmentType(newAccountCreate.getInstallmentType());
		if(!Strings.isNullOrEmpty(newAccountCreate.getInstallmentFrequency()))
			oldAccountCreate.setInstallmentFrequency(newAccountCreate.getInstallmentFrequency());
		if(newAccountCreate.getRateOfInterest()!=null)
			oldAccountCreate.setRateOfInterest(newAccountCreate.getRateOfInterest());
		if(newAccountCreate.getPrincipal()!=null)
			oldAccountCreate.setPrincipal(newAccountCreate.getPrincipal());
		if(newAccountCreate.getInterest()!=null)
			oldAccountCreate.setInterest(newAccountCreate.getInterest());
		if(newAccountCreate.getInstallmentAmount()!=null)
			oldAccountCreate.setInstallmentAmount(newAccountCreate.getInstallmentAmount());
		if(newAccountCreate.getCharges()!=null)
			oldAccountCreate.setCharges(newAccountCreate.getCharges());
		if(newAccountCreate.getOfferIssueDate()!=null)
			oldAccountCreate.setOfferIssueDate(newAccountCreate.getOfferIssueDate());
		if(newAccountCreate.getOfferAcceptedDate()!=null)
			oldAccountCreate.setOfferAcceptedDate(newAccountCreate.getOfferAcceptedDate());
		if(newAccountCreate.getDisbursementAmount()!=null)
			oldAccountCreate.setDisbursementAmount(newAccountCreate.getDisbursementAmount());
		if(newAccountCreate.getRepaymentAmount()!=null)
			oldAccountCreate.setRepaymentAmount(newAccountCreate.getRepaymentAmount());
	}

	@Override
	public ResponseEntity<?> fetchAccountCreateByLoanAccountId(Long loanAccountId) {
		try {
			if (loanAccountId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			
			Optional<IcustAccountCreate> accountObj =accountCreateRepo.findByLoanAccountId(loanAccountId);
			if (accountObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(accountObj.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchAccountCreateByLoanAccountId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchAccountCreateById(Long id) {
		try {
			if (id == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is Mandatory");
			
			Optional<IcustAccountCreate> accountObj = accountCreateRepo.findById(id);
			if (accountObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(accountObj.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchOfferIssueByLoanAccountId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
