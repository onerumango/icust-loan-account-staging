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
@Table(name = "ICUST_LOAN_DISBURSEMENT_DETAILS")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustLoanDisbursementDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "LOAN_DISBURSEMENT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_disbursement_id_Sequence")
	@SequenceGenerator(name = "loan_disbursement_id_Sequence", sequenceName = "LOAN_DISBURSEMENT_ID_SEQ")
	private Long loanDisbursementId;
	private Long loanId;
	private Boolean mutipleDisbursementRequired;
	private Double loanAmount;
	private Long numberOfDisbursement;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd", timezone = "IST")
	private Date firstDisbursementDate;
	private Long totalDisbursement;
	private String disbursementMode;
	private Long customerAccount;
	private String branchCode;
	

}
