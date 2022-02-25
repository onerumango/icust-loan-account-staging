package com.rumango.serviceImpl;

import java.text.MessageFormat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.rumango.entity.IcustLoanAssessmentDetails;
import com.rumango.model.IcustLoanAssessmentDetailsModel;
import com.rumango.repository.IcustLoanAssessmentDetailsRepo;
import com.rumango.service.IcustLoanAssessmentService;

import java.util.List;

@Service
public class IcustLoanAssessmentServiceImpl implements IcustLoanAssessmentService {

	private static final Logger logger = LogManager.getLogger(IcustLoanAssessmentServiceImpl.class);
	
	@Autowired
	private IcustLoanAssessmentDetailsRepo assessmentDetailsRepo;

	@Override
	public ResponseEntity<?> upsertLoanAssessmentDetails(IcustLoanAssessmentDetailsModel loanAssessmentDetailsModel) {
		try {
			if(loanAssessmentDetailsModel.getLoanAccountId() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Loan Account ID is mandatory");
			}
			else {
				Optional<IcustLoanAssessmentDetails> assessmentDetails = assessmentDetailsRepo
						.findByLoanAccountId(loanAssessmentDetailsModel.getLoanAccountId());
				IcustLoanAssessmentDetails loanAssessmentDetails = new Gson().fromJson(new Gson().toJson(loanAssessmentDetailsModel), 
						IcustLoanAssessmentDetails.class);
				if(assessmentDetails.isPresent()) {
					validateLoanAssessmentDetails(assessmentDetails.get(), loanAssessmentDetails);
					return ResponseEntity.status(HttpStatus.OK).body(assessmentDetailsRepo.save(assessmentDetails.get()));
				}
				else {
					return ResponseEntity.status(HttpStatus.OK).body(assessmentDetailsRepo.save(loanAssessmentDetails));
				}
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertLoanAssessmentDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	private void validateLoanAssessmentDetails(IcustLoanAssessmentDetails oldAssessmentDetails, IcustLoanAssessmentDetails newAssessmentDetails) {
		if(newAssessmentDetails.getLoanAccountId()!=null)
			oldAssessmentDetails.setLoanAccountId(newAssessmentDetails.getLoanAccountId());
		if(newAssessmentDetails.getRequestedLoanAmount()!=null)
			oldAssessmentDetails.setRequestedLoanAmount(newAssessmentDetails.getRequestedLoanAmount());
		if(newAssessmentDetails.getLoanTenure()!=null)
			oldAssessmentDetails.setLoanTenure(newAssessmentDetails.getLoanTenure());
		if(newAssessmentDetails.getRateOfInterest()!=null)
			oldAssessmentDetails.setRateOfInterest(newAssessmentDetails.getRateOfInterest());
		if(newAssessmentDetails.getSystemRecommendation()!=null)
			oldAssessmentDetails.setSystemRecommendation(newAssessmentDetails.getSystemRecommendation());
		if(newAssessmentDetails.getUserRecommendation()!=null)
			oldAssessmentDetails.setUserRecommendation(newAssessmentDetails.getUserRecommendation());
		if(newAssessmentDetails.getLoanAmountRecommendation()!=null)
			oldAssessmentDetails.setLoanAmountRecommendation(newAssessmentDetails.getLoanAmountRecommendation());
		if(newAssessmentDetails.getApprovedLoanAmount()!=null)
			oldAssessmentDetails.setApprovedLoanAmount(newAssessmentDetails.getApprovedLoanAmount());
		if(newAssessmentDetails.getFinalLoanTenure()!=null)
			oldAssessmentDetails.setFinalLoanTenure(newAssessmentDetails.getFinalLoanTenure());
		if(newAssessmentDetails.getFinalRate()!=null)
			oldAssessmentDetails.setFinalRate(newAssessmentDetails.getFinalRate());
	}

	@Override
	public ResponseEntity<?> fetchLoanAssessmentDetails() {
		try {
			List<IcustLoanAssessmentDetails> loanAssessmentDetails = assessmentDetailsRepo.findAll();
			if (loanAssessmentDetails != null) {
				return ResponseEntity.status(HttpStatus.OK).body(loanAssessmentDetails);
			} else {
				logger.error("No Data Found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Data Found");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanAssessmentDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchLoanAssessmentById(Long loanAccountId) {
		try {
			if (loanAccountId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			
			Optional<IcustLoanAssessmentDetails> loanAssessmentData = assessmentDetailsRepo.findByLoanAccountId(loanAccountId+"");
			if (loanAssessmentData.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(loanAssessmentData.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanAssessmentById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
}
