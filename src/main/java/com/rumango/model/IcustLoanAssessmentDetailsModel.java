package com.rumango.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class IcustLoanAssessmentDetailsModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long assessmentId;
	private String loanAccountId;
	private Double requestedLoanAmount;
	private String loanTenure;
	private Integer rateOfInterest;
	private String systemRecommendation;
	private String userRecommendation;
	private Double loanAmountRecommendation;
	private Double approvedLoanAmount;
	private Integer finalLoanTenure;
	private Integer finalRate;
}
