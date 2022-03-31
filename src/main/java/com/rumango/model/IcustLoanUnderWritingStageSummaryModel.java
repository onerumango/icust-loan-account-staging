package com.rumango.model;

import lombok.Data;

@Data
public class IcustLoanUnderWritingStageSummaryModel {
	private IcustLoanCreditRatingDetailsModel creditRatingInfo;
	private IcustLoanValuationOfAssetModel valuationOfAssetInfo;
	private IcustLoanLegalOpinionModel legalOpinionInfo;
	
	

}
