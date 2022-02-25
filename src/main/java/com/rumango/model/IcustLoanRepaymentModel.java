package com.rumango.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IcustLoanRepaymentModel {

	private Long loanRepaymentId;
	private Long loanAccountId;
	private String typeOfRepayment;
	private String repaymentFrequency;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd", timezone = "IST")
	private Date firstRepaymentDate;
	private String loanTenure;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd", timezone = "IST")
	private Date maturityDate;
	private String repaymentMode;
	private Long moraturioumPeriod;
	private String customerAccount;
	private String branchCode;
	
}
