package com.rumango.model;

import lombok.Data;

@Data
public class IcustAssetInfoModel {
	private Long assetId;
	private Long loanId;
	private String mortgagedBranch;
	private String homeType;
	private String dimensions;
	private Double marketValue;
	private String assetStatus;
	private String building;
	private String street;
	private String locality;
	private String city;
	private String state;
	private String country;
	private String zipCode;
}
