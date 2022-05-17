package com.rumango.model;

import java.util.List;

import lombok.Data;

@Data
public class IcustLoanAccountSummaryModel {
	private IcustLoanInfoModel loanDetailsInfo;
	private IcustAssetDetailsModel assessInfo;
	private IcustVehicleDetailsModel vehicleInfo;
	private IcustAdmissionDetailsModel admissionInfo;

	private IcustMandateDetailsModel mandateInfo;
	private List<IcustFinancialDetailsModel> financialInfo;
	private IcustCollateralMasterModel collateralInfo;
	private IcustGuarantorDetailsModel guarantorInfo;
	
	private List<IcustLoanInterestModel> interestInfo;
	private IcustLoanDisbursementModel disbursementInfo;
	private IcustLoanRepaymentModel repaymentInfo;
	private List<IcustLoanChargeModel> chargeInfo;
	private IcustAccountServicesModel accountServiceInfo;
	
	private IcustLoanCreditRatingDetailsModel creditRatingInfo;
	private IcustLoanValuationOfAssetModel valAssetInfo;
	private IcustLoanLegalOpinionModel legalOpionInfo;
	
	private IcustLoanAssessmentDetailsModel assessmentInfo;
	private IcustApprovalDetailsModel approvalInfo;
	
	private IcustOfferIssueModel offerIssueInfo;
	
	private IcustLoanOfferAcceptOrRejectModel offerAcceptRejectInfo;
	
}
