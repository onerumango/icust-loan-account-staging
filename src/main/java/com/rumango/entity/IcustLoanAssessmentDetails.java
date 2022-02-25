package com.rumango.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ICUST_LOAN_ASSESSMENT_DETAILS")
@Data
@NoArgsConstructor
public class IcustLoanAssessmentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LoanAssessment_Sequence")
	@SequenceGenerator(name = "LoanAssessment_Sequence", sequenceName = "LOANASSESSMENT_SEQUENCE_ID_SEQ")
	private Long assessmentId;
	private String loanAccountId;
	private Double requestedLoanAmount;
	private Integer loanTenure;
	private Integer rateOfInterest;
	private String systemRecommendation;
	private String userRecommendation;
	private Double loanAmountRecommendation;
	private Double approvedLoanAmount;
	private Integer finalLoanTenure;
	private Integer finalRate;
	
}
