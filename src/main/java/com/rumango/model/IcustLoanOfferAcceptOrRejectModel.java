package com.rumango.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class IcustLoanOfferAcceptOrRejectModel {
	
	private Long offerAcceptOrRejectId;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date generateOffer;
	private String accept;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date dateOfOfferAcceptOrReject;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date offerExpiryDate;

}
