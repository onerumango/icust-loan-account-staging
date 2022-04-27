package com.rumango.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="ICUST_LOAN_REPAYMENT_DETAILS")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor

public class IcustLoanRepaymentDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name= "LOAN_REPAYMENT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_repayment_id_Sequence")
	@SequenceGenerator(name = "loan_repayment_id_Sequence", sequenceName = "LOAN_REPAYMENT_ID_SEQ")

	private Long loanRepaymentId;
	private Long loanAccountId;
	private String typeOfRepayment;
	private String repaymentFrequency;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd", timezone = "IST")
	private Date firstRepaymentDate;
	private String loanTenure;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd", timezone = "IST")
	private Date maturityDate;
	private String repaymentMode;
	private Long moraturioumPeriod;
	private String customerAccount;
	private String branchCode;
	private String bankName;
	

}
