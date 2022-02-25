package com.rumango.serviceImpl;

import java.text.MessageFormat;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustApprovalDetails;
import com.rumango.entity.IcustVehicleDetails;
import com.rumango.model.IcustApprovalDetailsModel;
import com.rumango.repository.IcustApprovalDetailsRepo;
import com.rumango.service.IcustApprovalDetailsService;

@Service
public class IcustApprovalDetailsServiceImpl implements IcustApprovalDetailsService{
	private static final Logger logger = Logger.getLogger(IcustApprovalDetailsServiceImpl.class);

	@Autowired
	IcustApprovalDetailsRepo approvalDetailsRepo;
	
	@Override
	public ResponseEntity<?> upsertApprovalDetails(IcustApprovalDetailsModel icustApprovalDetailsModel) {
		try {
			if (icustApprovalDetailsModel.getLoanAccountId()==null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			else {
				Optional<IcustApprovalDetails> approvalObj = approvalDetailsRepo
						.findByLoanAccountId(icustApprovalDetailsModel.getLoanAccountId());
				IcustApprovalDetails approvalData = new Gson().fromJson(new Gson().toJson(icustApprovalDetailsModel),
						IcustApprovalDetails.class);
				if (approvalObj.isPresent()) {
					validateApprovalDetails(approvalObj.get(),approvalData);
					return ResponseEntity.status(HttpStatus.OK).body(approvalDetailsRepo.save(approvalObj.get()));
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(approvalDetailsRepo.save(approvalData));
				}
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertApprovalDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private void validateApprovalDetails(IcustApprovalDetails oldApproval, IcustApprovalDetails newApproval) {
		
		if(!Strings.isNullOrEmpty(newApproval.getApplicantName()))
			oldApproval.setApplicantName(newApproval.getApplicantName());
		if(!Strings.isNullOrEmpty(newApproval.getAccountType()))
			oldApproval.setAccountType(newApproval.getAccountType());
		if(!Strings.isNullOrEmpty(newApproval.getAccountBranch()))
			oldApproval.setAccountBranch(newApproval.getAccountBranch());
		if(!Strings.isNullOrEmpty(newApproval.getProductCode()))
			oldApproval.setProductCode(newApproval.getProductCode());
		if(!Strings.isNullOrEmpty(newApproval.getProductName()))
			oldApproval.setProductName(newApproval.getProductName());
		if(newApproval.getExistingValues()!=null)
			oldApproval.setExistingValues(newApproval.getExistingValues());
		if(newApproval.getApprovedLoanAccount()!=null)
			oldApproval.setApprovedLoanAccount(newApproval.getApprovedLoanAccount());
		if(!Strings.isNullOrEmpty(newApproval.getLoanTenure()))
			oldApproval.setLoanTenure(newApproval.getLoanTenure());
		if(!Strings.isNullOrEmpty(newApproval.getInstallmentType()))
			oldApproval.setInstallmentType(newApproval.getInstallmentType());
		if(newApproval.getRateOfInterest()!=null)
			oldApproval.setRateOfInterest(newApproval.getRateOfInterest());		
		if(newApproval.getMargin()!=null)
			oldApproval.setMargin(newApproval.getMargin());
		if(newApproval.getEffectiveRate()!=null)
			oldApproval.setEffectiveRate(newApproval.getEffectiveRate());
		if(newApproval.getComponentConsidered()!=null)
			oldApproval.setComponentConsidered(newApproval.getComponentConsidered());
		if(!Strings.isNullOrEmpty(newApproval.getUserRecommendation()))
			oldApproval.setUserRecommendation(newApproval.getUserRecommendation());
	}

	@Override
	public ResponseEntity<?> fetchApprovalDetails(Long loanAccountId) {
		try {
			if (loanAccountId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanId is Mandatory");
			Optional<IcustApprovalDetails> approvalObj = approvalDetailsRepo.findByLoanAccountId(loanAccountId);
			
			if (approvalObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(approvalObj.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchApprovalDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}