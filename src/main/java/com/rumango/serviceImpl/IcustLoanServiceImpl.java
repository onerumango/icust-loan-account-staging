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
import com.rumango.entity.IcustLoanInfo;
import com.rumango.model.IcustLoanInfoModel;
import com.rumango.repository.IcustLoanInfoRepo;
import com.rumango.service.IcustLoanService;

@Service
public class IcustLoanServiceImpl implements IcustLoanService {
	private static final Logger logger = LogManager.getLogger(IcustLoanServiceImpl.class);

	@Autowired
	IcustLoanInfoRepo icustLoanInfoRepo;

	@Override
	public ResponseEntity<?> upsertLoanDetails(IcustLoanInfoModel icustLoanInfoModel) {
		try {
			Optional<IcustLoanInfo> loanObj = icustLoanInfoRepo
					.findById(icustLoanInfoModel.getLoanAccountId());
			IcustLoanInfo loanData = new Gson().fromJson(new Gson().toJson(icustLoanInfoModel),
					IcustLoanInfo.class);
			if (loanObj.isPresent()) {
				validateLoanDetails(loanObj.get(),loanData);
				return ResponseEntity.status(HttpStatus.OK).body(icustLoanInfoRepo.save(loanObj.get()));
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(icustLoanInfoRepo.save(loanData));
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertFinancialDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private void validateLoanDetails(IcustLoanInfo oldLoanInfo, IcustLoanInfo newLoanInfo) {
		if(!Strings.isNullOrEmpty(newLoanInfo.getBusinessProductName()))
			oldLoanInfo.setBusinessProductName(newLoanInfo.getBusinessProductName());
		if(!Strings.isNullOrEmpty(newLoanInfo.getAccountBranch()))
			oldLoanInfo.setAccountBranch(newLoanInfo.getAccountBranch());
		if(newLoanInfo.getApplicationDate()!=null)
			oldLoanInfo.setApplicationDate(newLoanInfo.getApplicationDate());
		if(!Strings.isNullOrEmpty(newLoanInfo.getAccountType()))
			oldLoanInfo.setAccountType(newLoanInfo.getAccountType());
		if(newLoanInfo.getEstimatedCost()!=null)
			oldLoanInfo.setEstimatedCost(newLoanInfo.getEstimatedCost());
		if(!Strings.isNullOrEmpty(newLoanInfo.getCustomerContribution()))
			oldLoanInfo.setCustomerContribution(newLoanInfo.getCustomerContribution());
		if(newLoanInfo.getLoanAmount()!=null)
			oldLoanInfo.setLoanAmount(newLoanInfo.getLoanAmount());
		if(!Strings.isNullOrEmpty(newLoanInfo.getLoanTenure()))
			oldLoanInfo.setLoanTenure(newLoanInfo.getLoanTenure());
		if(!Strings.isNullOrEmpty(newLoanInfo.getPurposeOfLoan()))
			oldLoanInfo.setPurposeOfLoan(newLoanInfo.getPurposeOfLoan());
		
	}

	@Override
	public ResponseEntity<?> fetchLoanDetails(Long loanId) {
		try {
			Optional<IcustLoanInfo> loanObj = icustLoanInfoRepo.findById(loanId);
			if (loanObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(loanObj.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchLoanInfo() {
		try {
			List<IcustLoanInfo> loanList = icustLoanInfoRepo.findAll();
			if (!CollectionUtils.isEmpty(loanList)) {
				return ResponseEntity.status(HttpStatus.OK).body(loanList);
			} else {
				logger.error("No details found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No details found");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
