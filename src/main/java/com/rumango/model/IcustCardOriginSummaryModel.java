package com.rumango.model;

import java.util.List;

import lombok.Data;

@Data
public class IcustCardOriginSummaryModel {
	private IcustCardInitiationModel initiationInfo;
	private List<IcustFinancialDetailsModel>  financialInfo;
	private List<IcustCardPreferencesModel> preferenceInfo;
	
	private List<IcustCardDocumentsModel> documentInfo;
	
	private List<IcustCardInterestModel> interestInfo;
	private List<IcustCardChargeModel> chargeInfo;
	
	private IcustLoanCreditRatingDetailsModel creditRatingInfo;
	
	private IcustCardAssessmentModel assessmentInfo;
	
	private IcustCardApprovalModel approvalInfo;

}
