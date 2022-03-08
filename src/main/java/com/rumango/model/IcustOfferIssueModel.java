package com.rumango.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IcustOfferIssueModel {
	private Long offerIssueId;
	private Long loanAccountId;
	private String applicantName;
	private Double approvedLoanAmount;
	private String loanTenure;
	private String installmentType;
	private String installmentFrequency;
	private Long rateOfInterest;
	private Double principal;
	private Long interest;
	private Double installmentAmount;
	private Double charges;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date offerIssueDate;
	private String generateOffer;
	private String customerResponse;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date offerAcceptRejectDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date offerExpiryDate;
}
