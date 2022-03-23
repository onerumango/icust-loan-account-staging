package com.rumango.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IcustLoanInfoModel {
	private Long loanAccountId;
	private String businessProductName;
	private String accountBranch;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date applicationDate;
	private String accountType;
	private Double estimatedCost;
	private String customerContribution;
	private Double loanAmount;
	private String loanTenure;
	private String purposeOfLoan;
	private String status;
	private String accountCurrency;
}
