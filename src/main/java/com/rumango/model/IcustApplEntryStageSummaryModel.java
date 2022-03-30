package com.rumango.model;

import java.util.List;

import lombok.Data;

@Data
public class IcustApplEntryStageSummaryModel {
	private IcustLoanInfoModel loanInfo;
	private IcustAssetDetailsModel assetInfo;
	private IcustVehicleDetailsModel vehicleInfo;
	private IcustAdmissionDetailsModel admissionInfo;
	private IcustMandateMasterModel mandateInfo;
	private List<IcustFinancialDetailsModel> financialInfo;
	private IcustCollateralMasterModel collateralInfo;
	private IcustGuarantorDetailsModel guarantorInfo;
}
