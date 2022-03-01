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
@Table(name = "ICUST_ACCOUNT_CREATE")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustAccountCreate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ACCOUNT_CREATE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_create_id_Sequence")
	@SequenceGenerator(name = "account_create_id_Sequence", sequenceName = "ACCOUNT_CREATE_ID_SEQ")
	private Long accountCreateId;
	private Long loanAccountId;
	private String applicantName;
	private Double approvedLoanAmount;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date offerIssueDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date offerAcceptedDate;
	private String loanTenure;
	private String installmentType;
	private String installmentFrequency;
	private Double rateOfInterest;
	private Double principal;
	private Double interest;
	private Double installmentAmount;
	private Double charges;
	private Double disbursementAmount;
	private Double repaymentAmount;
	

}
