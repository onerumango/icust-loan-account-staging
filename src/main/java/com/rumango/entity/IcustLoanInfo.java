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
@Table(name = "ICUST_LOAN_INFO")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustLoanInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "LOAN_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_id_Sequence")
	@SequenceGenerator(name = "loan_id_Sequence", sequenceName = "LOAN_ID_SEQ")
	private Long loanId;
	private Long customerId;
	private String businessProductName;
	private String accountBranch;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date applicationDate;
	private String accountType;
	private Double estimatedCost;
	private String customerContribution;
	private Double loanAmount;
	private String loanTenure;
	private String purposeOfLoan;
}
