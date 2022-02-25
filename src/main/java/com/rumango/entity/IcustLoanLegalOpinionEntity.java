package com.rumango.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rumango.enums.UnitMeasurementEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "IC_LOAN_LEGAL_OPINION")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IcustLoanLegalOpinionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "legalOpinionId_seq")
	@SequenceGenerator(name = "legalOpinionId_seq", sequenceName = "legalOpinionId_seq", allocationSize = 1)
	private Long legalOpinionId;
	
	@Column(name = "LOAN_ACC_ID")
	private Long loanAccountId;

	@Column(name = "ASSET_AREA_IN_UNITS")
	@Enumerated(EnumType.STRING)
	private UnitMeasurementEnum assetAreaInUnits;

	@Column(name = "ASSET_AREA_SIZE")
	private Double assetAreaSize;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "IS_DECISION_FAVORABLE")
	private String isDecisionFavorable;

	@Column(name = "MKT_VAL_OF_ASSET")
	private String mktValOfAsset;

	@Column(name = "LAWYER_NAME")
	private String lawyerName;

	@Column(name = "OPPINION")
	private String opinion;

	@Column(name = "OPPINION_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date opinionDate;

	@Column(name = "VALUATION_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date valuationDate;

}
