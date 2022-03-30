package com.rumango.model;

import java.util.List;

import lombok.Data;

@Data
public class IcustApplicationEnrichmentSummaryModel {
	private List<IcustLoanInterestModel> intererstInfo;
	private IcustLoanDisbursementModel disbursementInfo;
	private IcustLoanRepaymentModel repaymentInfo;
	private List<IcustLoanChargeModel> chargeInfo;
	private IcustAccountServicesModel accServiceInfo;
}
