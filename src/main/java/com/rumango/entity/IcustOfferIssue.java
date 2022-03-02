package com.rumango.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ICUST_OFFER_ISSUE")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustOfferIssue implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name = "OFFER_ISSUE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offer_issue_id_Sequence")
	@SequenceGenerator(name = "offer_issue_id_Sequence", sequenceName = "OFFER_ISSUE_ID_SEQ")
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
	private String customerResponse;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date offerAcceptRejectDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date offerExpiryDate;

}
