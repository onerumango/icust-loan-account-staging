package com.rumango.model;

import java.util.List;

import lombok.Data;

@Data
public class IcustCollateralMasterModel {
	private Long collateralId;
	private Long loanAccountId;
	private String collateralType;
	private String collateralCurrency;
	private Double collateralValue;
	private String attributes;
	private String dimensions;
	private Boolean thirdPartyCollateral;	
	private String building;
	private String street;
	private String locality;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private List<IcustCollateralDetailsModel> collateralDetails;
}
