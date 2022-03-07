package com.rumango.model;

import lombok.Data;

@Data
public class IcustApprovalDetailsModel {
	private Long approvalId;
	private Long loanAccountId;
	private String applicantName;
	private String accountType;
	private String accountBranch;
	private String productCode;
	private String productName;
	private Long existingValues;
	private Double approvedLoanAccount;
	private String loanTenure;
	private String installmentType;
	private Long rateOfInterest;
	private Long margin;
	private Long effectiveRate;
	private Double componentConsidered;
	private String userRecommendation;

}
