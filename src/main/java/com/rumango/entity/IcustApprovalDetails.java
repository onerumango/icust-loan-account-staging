package com.rumango.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ICUST_APPROVAL_DETAILS")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustApprovalDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "APPROVAL_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "approval_id_Sequence")
	@SequenceGenerator(name = "approval_id_Sequence", sequenceName = "APPROVAL_ID_SEQ")
	private Long approvalId;
	private Long loanAccountId;
	private String applicantName;
	private String accountType;
	private String accountBranch;
	private String productCode;
	private String productName;
	private Long existingValues;
	private Double approvedLoanAccount;
	private String loanTenure;
	private String installmentType;
	private Double rateOfInterest;
	private Double margin;
	private Double effectiveRate;
	private Double componentConsidered;
	private String userRecommendation;
}
