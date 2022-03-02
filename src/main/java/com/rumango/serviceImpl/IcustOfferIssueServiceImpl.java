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
import com.rumango.entity.IcustAssetDetails;
import com.rumango.entity.IcustOfferIssue;
import com.rumango.model.IcustOfferIssueModel;
import com.rumango.repository.IcustOfferIssueRepo;
import com.rumango.service.IcustOfferIssueService;

@Service
public class IcustOfferIssueServiceImpl implements IcustOfferIssueService{
	private static final Logger logger = Logger.getLogger(IcustOfferIssueServiceImpl.class);

	@Autowired
	IcustOfferIssueRepo icustOfferIssueRepo;
	@Override
	public ResponseEntity<?> upsertOfferIssue(IcustOfferIssueModel icustOfferIssueModel) {
		try {
			if (icustOfferIssueModel.getLoanAccountId()==null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanId is Mandatory");
			else {
				Optional<IcustOfferIssue> issueObj = icustOfferIssueRepo
						.findByLoanAccountId(icustOfferIssueModel.getLoanAccountId());
				IcustOfferIssue assetData = new Gson().fromJson(new Gson().toJson(icustOfferIssueModel),
						IcustOfferIssue.class);
				if (issueObj.isPresent()) {
					validateOfferIssueDetails(issueObj.get(),assetData);
					return ResponseEntity.status(HttpStatus.OK).body(icustOfferIssueRepo.save(issueObj.get()));
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(icustOfferIssueRepo.save(assetData));
				}
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertOfferIssue", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private void validateOfferIssueDetails(IcustOfferIssue oldOfferIssue, IcustOfferIssue newOfferIssue) {
		
		if(!Strings.isNullOrEmpty(newOfferIssue.getApplicantName()))
			oldOfferIssue.setApplicantName(newOfferIssue.getApplicantName());
		if(newOfferIssue.getApprovedLoanAmount()!=null)
			oldOfferIssue.setApprovedLoanAmount(newOfferIssue.getApprovedLoanAmount());
		if(!Strings.isNullOrEmpty(newOfferIssue.getLoanTenure()))
			oldOfferIssue.setLoanTenure(newOfferIssue.getLoanTenure());
		if(!Strings.isNullOrEmpty(newOfferIssue.getInstallmentType()))
			oldOfferIssue.setInstallmentType(newOfferIssue.getInstallmentType());
		if(!Strings.isNullOrEmpty(newOfferIssue.getInstallmentFrequency()))
			oldOfferIssue.setInstallmentFrequency(newOfferIssue.getInstallmentFrequency());
		if(newOfferIssue.getRateOfInterest()!=null)
			oldOfferIssue.setRateOfInterest(newOfferIssue.getRateOfInterest());
		if(newOfferIssue.getPrincipal()!=null)
			oldOfferIssue.setPrincipal(newOfferIssue.getPrincipal());
		if(newOfferIssue.getInterest()!=null)
			oldOfferIssue.setInterest(newOfferIssue.getInterest());
		if(newOfferIssue.getInstallmentAmount()!=null)
			oldOfferIssue.setInstallmentAmount(newOfferIssue.getInstallmentAmount());
		if(newOfferIssue.getCharges()!=null)
			oldOfferIssue.setCharges(newOfferIssue.getCharges());
		if(newOfferIssue.getOfferIssueDate()!=null)
			oldOfferIssue.setOfferIssueDate(newOfferIssue.getOfferIssueDate());
		if(!Strings.isNullOrEmpty(newOfferIssue.getGenerateOffer()))
			oldOfferIssue.setGenerateOffer(newOfferIssue.getGenerateOffer());
		if(newOfferIssue.getCustomerResponse()!=null)
			oldOfferIssue.setCustomerResponse(newOfferIssue.getCustomerResponse());
		if(newOfferIssue.getOfferAcceptRejectDate()!=null)
			oldOfferIssue.setOfferAcceptRejectDate(newOfferIssue.getOfferAcceptRejectDate());
		if(newOfferIssue.getOfferExpiryDate()!=null)
			oldOfferIssue.setOfferExpiryDate(newOfferIssue.getOfferExpiryDate());
	}

	@Override
	public ResponseEntity<?> fetchOfferIssueByLoanAccountId(Long loanAccountId) {
		try {
			if (loanAccountId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			
			Optional<IcustOfferIssue> issueObj = icustOfferIssueRepo.findByLoanAccountId(loanAccountId);
			if (issueObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(issueObj.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchOfferIssueByLoanAccountId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchOfferIssueById(Long id) {
		try {
			if (id == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is Mandatory");
			
			Optional<IcustOfferIssue> issueObj = icustOfferIssueRepo.findById(id);
			if (issueObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(issueObj.get());
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
