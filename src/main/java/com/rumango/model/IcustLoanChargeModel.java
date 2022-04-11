package com.rumango.model;

import lombok.Data;

@Data
public class IcustLoanChargeModel {

	private Long chargeDetailsId;
	private Long loanAccountId;
	private String intrestType;
	private Double amout;
	private Boolean waiver;
	
}
