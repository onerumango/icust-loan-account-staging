package com.rumango.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IcustPostOfferAmendmentModel {

	private Long postOfferAmendmentId;
	private Long loanAccountId;
	private String applicantName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date offerIssueDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date offerExpiryDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date offerAmendDate;
	private Double approvedLoanAmount;
	private Double loanAmmountRecommended;
	private String loanTenure;
	private String installmentType;
	private Double rateOfInterest;
	private Long margin;
	private Double effectiveRate;
}
