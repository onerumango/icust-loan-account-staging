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
	private Double rateOfInterest;
	private Double principal;
	private Double interest;
	private Double installmentAmount;
	private Double charges;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date offerIssueDate;
	private String generateOffer;
}
