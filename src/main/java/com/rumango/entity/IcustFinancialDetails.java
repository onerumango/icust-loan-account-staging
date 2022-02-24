package com.rumango.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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
@Table(name = "FINANCIAL_DETAILS")
@Data
@XmlRootElement
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class IcustFinancialDetails implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FinancialDetails_Sequence")
	@SequenceGenerator(name = "FinancialDetails_Sequence", sequenceName = "FINANCIALDETAILS_SEQUENCE_ID_SEQ")
	private Long id;
	private String applicantName;
	private Double applicantTotalIncome;
	private Double applicantTotalExpense;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "IST")
	private Date lastUpdatedon;
	private String employmentType;
	private String employmentCategory;
	private String employeeNumber;
	private String officeName;
	private String designation;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd")
	private Date employmentStartDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd")
	private Date employmentEndDate;
	private Double salary;
	private Double business;
	private Double interestIncome;
	private Double pension;
	private Double bonus;
	private Double monthlyIncomeRentals;
	private Double cashGifts;
	private Double monthlyIncomeOthers;
	private Double monthlyIncomeTotal;
	private Double household;
	private Double medical;
	private Double education;
	private Double travel;
	private Double vehicleMaintenance;
	private Double monthlyExpenseRentals;
	private Double monthlyExpenseOthers;
	private Double monthlyExpenseTotal;
	private Double propertyLoan;
	private Double vehicleLoan;
	private Double personalLoans;
	private Double cardOutstandings;
	private Double overdraft;
	private Double liabilityOthers;
	private Double liabilityTotal;
	private Double savingDeposits;
	private Double stocks;
	private Double properties;
	private Double automobiles;
	private Double fixedDeposits;
	private Double lands;
	private Double assetOthers;
	private Double assetTotal;
	private Double totalIncome;
	private String accountId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss")
	private Timestamp createdDate;
	private Long createdBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss")
	private Timestamp lastModifiedDate;
	private Long lastModifiedBy;
	private Long loanAccountId;
}
