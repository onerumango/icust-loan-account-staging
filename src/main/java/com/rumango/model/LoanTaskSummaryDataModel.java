package com.rumango.model;

import java.util.Date;

import lombok.Data;

@Data
public class LoanTaskSummaryDataModel {

	private Date applicationDate;
	private Long loanAccountId;
	private Long customerId;
	private String accountType;
	private String createdBy;
	private String businessProductName;
	private String accountBranch;
	private Long cifNumber;
	private String firstName;
	private String middleName;
	private String lastName;
	private String status;
	private String profileImage;
	private String prefix;
	
}
