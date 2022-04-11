package com.rumango.model;

import lombok.Data;

@Data
public class IcustLoanInterestModel {
	private Long loanInterestId;
	private Long loanAccountId;
	private String intrestType;
	private Long intrestRateApplicable;
	private Long marginIn;
	private Long effectiveRate;
}