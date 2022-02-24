package com.rumango.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IcustLoanDisbursementModel {
	
	private Long loanDisbursementId;
	private Long loanAccountId;
	private Boolean mutipleDisbursementRequired;
	private Double loanAmount;
	private Long numberOfDisbursement;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd", timezone = "IST")
	private Date firstDisbursementDate;
	private Long totalDisbursement;
	private String disbursementMode;
	private Long customerAccount;
	private String branchCode;
	

}
