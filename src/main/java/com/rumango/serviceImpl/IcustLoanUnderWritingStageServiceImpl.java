package com.rumango.serviceImpl;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rumango.entity.IcustLoanCreditRatingDetails;
import com.rumango.entity.IcustLoanLegalOpinionEntity;
import com.rumango.entity.IcustLoanValuationOfAssetEntity;
import com.rumango.model.IcustLoanCreditRatingDetailsModel;
import com.rumango.model.IcustLoanLegalOpinionModel;
import com.rumango.model.IcustLoanUnderWritingStageSummaryModel;
import com.rumango.model.IcustLoanValuationOfAssetModel;
import com.rumango.repository.IcustLoanCreditRatingRepo;
import com.rumango.repository.IcustLoanLegalOpinionRepo;
import com.rumango.repository.IcustLoanValuationOfAssetRepo;
import com.rumango.service.IcustLoanUnderWritingStageService;

@Service
public class IcustLoanUnderWritingStageServiceImpl implements IcustLoanUnderWritingStageService {
	private static final Logger logger = Logger.getLogger(IcustLoanUnderWritingStageServiceImpl.class);

	@Autowired
	private IcustLoanCreditRatingRepo creditRatingRepo;
	@Autowired
	private IcustLoanValuationOfAssetRepo loanValuationOfAssetRepo;
	@Autowired
	private IcustLoanLegalOpinionRepo legalOpinionRepo;

	@Override
	public ResponseEntity<?> fetchSummaryInfo(Long loanAccountId) {
		IcustLoanUnderWritingStageSummaryModel summaryModel = new IcustLoanUnderWritingStageSummaryModel();
		try {
			if (loanAccountId != null) {
				Optional<IcustLoanCreditRatingDetails> isCreditREntityPresent = creditRatingRepo
						.findByLoanAccountId(loanAccountId);
				if (isCreditREntityPresent.isPresent()) {
					IcustLoanCreditRatingDetailsModel creditRatingInfo = new Gson().fromJson(
							new Gson().toJson(isCreditREntityPresent.get()), IcustLoanCreditRatingDetailsModel.class);
					summaryModel.setCreditRatingInfo(creditRatingInfo);
				}
				Optional<IcustLoanValuationOfAssetEntity> valuationOfAssetObj = loanValuationOfAssetRepo
						.findByLoanAccountId(loanAccountId);
				if(valuationOfAssetObj.isPresent()) {
					IcustLoanValuationOfAssetModel valuationOfAssetInfo = new Gson().fromJson(
							new Gson().toJson(valuationOfAssetObj.get()), IcustLoanValuationOfAssetModel.class);
					summaryModel.setValuationOfAssetInfo(valuationOfAssetInfo);
				}
				Optional<IcustLoanLegalOpinionEntity> legalOpinionObj = legalOpinionRepo.findByLoanAccountId(loanAccountId);
				if(legalOpinionObj.isPresent()) {
					IcustLoanLegalOpinionModel legalOpinionInfo = new Gson().fromJson(
							new Gson().toJson(legalOpinionObj.get()), IcustLoanLegalOpinionModel.class);
					summaryModel.setLegalOpinionInfo(legalOpinionInfo);
				}
				return ResponseEntity.status(HttpStatus.OK).body(summaryModel);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("LoanAccountId is mandatory");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchSummaryInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

}
