package com.rumango.model;

import lombok.Data;

@Data
public class IcustCollateralDetailsModel {
	private Long collateralDetailsId;
	private Long collateralId;
	private Long noOfCollateral;
	private Double totalCollateralValue;
	private String utilizedPreviously;
	private String coverAvailable;
	private Boolean secondaryChargeAllowed;
}
