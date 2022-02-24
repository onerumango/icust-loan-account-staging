package com.rumango.model;

import lombok.Data;

@Data
public class IcustCustomerAddressModel {
	private Long id;
	private String addressType;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String residenceType;
	private Long zipCode;
	private Long customerId;
	private String customerType;
	private Long corporateId;
	private String primaryEmail;
	private String secondaryEmail;
	private Long mobileNumber;
	private String landLine;
	private String isMailingAddressSame;
}
