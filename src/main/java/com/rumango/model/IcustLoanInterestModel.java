package com.rumango.model;

import lombok.Data;

@Data
public class IcustLoanInterestModel {
	private Long loanInterestId;
	private Long loanId;
	private String interestType;
	private Long interestRateApplicable;
	private Long margin;
	private Long effectiveRate;
}