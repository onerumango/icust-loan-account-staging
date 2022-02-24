package com.rumango.model;

import lombok.Data;

@Data
public class IcustGuarantorDetailsModel {
	private Long guarantorId;
	private Long loanAccountId;
	private String relationWithCustomer;
	private IcustCustomerCreateModel customerInfo;
}
