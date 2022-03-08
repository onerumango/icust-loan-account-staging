package com.rumango.serviceImpl;

import java.text.MessageFormat;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustCustomerInfo;
import com.rumango.entity.IcustLoanInfo;
import com.rumango.entity.IcustLoanInterestDetails;
import com.rumango.entity.IcustLoanRepaymentDetails;
import com.rumango.model.IcustLoanAssessmentDetailsModel;
import com.rumango.model.IcustLoanInfoModel;
import com.rumango.model.IcustOfferIssueModel;
import com.rumango.repository.IcustCustomerInfoRepo;
import com.rumango.repository.IcustLoanInfoRepo;
import com.rumango.repository.IcustLoanInterestRepo;
import com.rumango.repository.IcustLoanRepaymentRepo;
import com.rumango.service.IcustLoanService;

@Service
public class IcustLoanServiceImpl implements IcustLoanService {
	private static final Logger logger = LogManager.getLogger(IcustLoanServiceImpl.class);

	@Autowired
	IcustLoanInfoRepo icustLoanInfoRepo;
	@Autowired
	IcustLoanInterestRepo loanInterestRepo;
	@Autowired
	IcustCustomerInfoRepo customerRepo;
	@Autowired
	IcustLoanRepaymentRepo repaymentRepo;
	
	@Override
	public ResponseEntity<?> upsertLoanDetails(IcustLoanInfoModel icustLoanInfoModel) {
		try {
			Optional<IcustLoanInfo> loanObj = icustLoanInfoRepo.findById(icustLoanInfoModel.getLoanAccountId());
			IcustLoanInfo loanData = new Gson().fromJson(new Gson().toJson(icustLoanInfoModel), IcustLoanInfo.class);
			if (loanObj.isPresent()) {
				validateLoanDetails(loanObj.get(), loanData);
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
		if (!Strings.isNullOrEmpty(newLoanInfo.getBusinessProductName()))
			oldLoanInfo.setBusinessProductName(newLoanInfo.getBusinessProductName());
		if (!Strings.isNullOrEmpty(newLoanInfo.getAccountBranch()))
			oldLoanInfo.setAccountBranch(newLoanInfo.getAccountBranch());
		if (newLoanInfo.getApplicationDate() != null)
			oldLoanInfo.setApplicationDate(newLoanInfo.getApplicationDate());
		if (!Strings.isNullOrEmpty(newLoanInfo.getAccountType()))
			oldLoanInfo.setAccountType(newLoanInfo.getAccountType());
		if (newLoanInfo.getEstimatedCost() != null)
			oldLoanInfo.setEstimatedCost(newLoanInfo.getEstimatedCost());
		if (!Strings.isNullOrEmpty(newLoanInfo.getCustomerContribution()))
			oldLoanInfo.setCustomerContribution(newLoanInfo.getCustomerContribution());
		if (newLoanInfo.getLoanAmount() != null)
			oldLoanInfo.setLoanAmount(newLoanInfo.getLoanAmount());
		if (!Strings.isNullOrEmpty(newLoanInfo.getLoanTenure()))
			oldLoanInfo.setLoanTenure(newLoanInfo.getLoanTenure());
		if (!Strings.isNullOrEmpty(newLoanInfo.getPurposeOfLoan()))
			oldLoanInfo.setPurposeOfLoan(newLoanInfo.getPurposeOfLoan());

	}

	@Override
	public ResponseEntity<?> fetchLoanDetailsByLoanAccId(Long loanAccountId) {
		try {
			Optional<IcustLoanInfo> loanObj = icustLoanInfoRepo.findById(loanAccountId);
			if (loanObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(loanObj.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanDetailsByLoanAccId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchLoanDetailsById(Long id) {
		try {
			Optional<IcustLoanInfo> loanObj = icustLoanInfoRepo.findById(id);
			if (loanObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(loanObj.get());
			} else {
				logger.error("No details found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No details found");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanDetailsById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> updateStatusApproveOrReject(IcustLoanInfoModel icustLoanInfoModel) {
		Optional<IcustLoanInfo> loanInfo = null;
		try {
			loanInfo = icustLoanInfoRepo.findById(icustLoanInfoModel.getLoanAccountId());
			if(loanInfo.isPresent()) {
				IcustLoanInfo loanObj = new Gson().fromJson(new Gson().toJson(icustLoanInfoModel), IcustLoanInfo.class);
				return ResponseEntity.status(HttpStatus.OK).body(icustLoanInfoRepo.save(loanObj));
			}else {
				logger.error("No record exists for accountId");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No record exists given LoanAccountId");
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while updateStatusApproveOrReject", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchAssessmentInfoByLoanAccId(Long loanAccountId) {
		IcustLoanAssessmentDetailsModel assessmentModel = new IcustLoanAssessmentDetailsModel();
		try {
			Optional<IcustLoanInfo> loanObj = icustLoanInfoRepo.findById(loanAccountId);
			if (loanObj.isPresent()) {
				IcustLoanInterestDetails loanInterestInfo = loanInterestRepo.findByLoanAccountIdAndInterestType(loanAccountId,"Fixed Rate");
				IcustLoanInfo loanInfo = loanObj.get();
				assessmentModel.setRequestedLoanAmount(loanInfo.getLoanAmount());
				assessmentModel.setLoanTenure(Integer.parseInt(loanInfo.getLoanTenure()));
				if(loanInterestInfo!=null)
					assessmentModel.setRateOfInterest(loanInterestInfo.getInterestRateApplicable().intValue());
				
				return ResponseEntity.status(HttpStatus.OK).body(assessmentModel);
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchAssessmentInfoByLoanAccId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> updateApprovedLoanAmount(IcustLoanAssessmentDetailsModel assessmentModel) {
		try {
			IcustLoanInfo loanInfo = null;
			if (assessmentModel.getLoanAccountId()==null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			else {
				Optional<IcustLoanInfo> loanObj = icustLoanInfoRepo.findById(Long.parseLong(assessmentModel.getLoanAccountId()));
				if(loanObj.isPresent()) {
					loanInfo = loanObj.get();
					loanInfo.setApprovedLoanAmount(assessmentModel.getApprovedLoanAmount());
					icustLoanInfoRepo.save(loanInfo);
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(loanInfo);
		}catch (Exception e) {
			logger.error("Execption occoured while executing updateApprovedLoanAmount", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchOfferIssueInfoByLoanAccId(Long loanAccountId) {
		IcustOfferIssueModel offerIssueModel = new IcustOfferIssueModel();
		try {
			if (loanAccountId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanId is Mandatory");
			Optional<IcustLoanInfo> loanObj = icustLoanInfoRepo.findById(loanAccountId);

			if (loanObj.isPresent()) {
				IcustLoanInterestDetails loanInterestInfo = loanInterestRepo
						.findByLoanAccountIdAndInterestType(loanAccountId, "Fixed Rate");
				Optional<IcustCustomerInfo> customerInfo = customerRepo.findById(loanObj.get().getCustomerId());
				Optional<IcustLoanRepaymentDetails> repaymentInfo = repaymentRepo.findByLoanAccountId(loanAccountId);
				
				if(customerInfo.isPresent()) {
					offerIssueModel.setApplicantName(customerInfo.get().getFirstName() + " "
							+ customerInfo.get().getMiddleName() + " " + customerInfo.get().getLastName());
				}
				offerIssueModel.setApprovedLoanAmount(loanObj.get().getApprovedLoanAmount());
				offerIssueModel.setLoanTenure(loanObj.get().getLoanTenure());
				if(repaymentInfo.isPresent()) {
					offerIssueModel.setInstallmentType(repaymentInfo.get().getTypeOfRepayment());
					offerIssueModel.setInstallmentFrequency(repaymentInfo.get().getRepaymentFrequency());
				}
				if(loanInterestInfo!=null) {
					offerIssueModel.setRateOfInterest(loanInterestInfo.getInterestRateApplicable());
				}
				
				return ResponseEntity.status(HttpStatus.OK).body(offerIssueModel);
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			e.printStackTrace();;
			logger.error("Execption occoured while executing fetchApprovalDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
