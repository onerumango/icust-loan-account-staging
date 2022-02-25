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
import com.rumango.enums.BankValuationEnum;
import com.rumango.enums.UnitMeasurementEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "IC_LOAN_VAL_OF_ASSET")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IcustLoanValuationOfAssetEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "valuationOfAssetId_seq")
	@SequenceGenerator(name = "valuationOfAssetId_seq", sequenceName = "valuationOfAssetId_seq", allocationSize = 1)
	private Long valuationId;

	@Column(name = "LOAN_ACC_ID")
	private Long loanAccountId;

	@Column(name = "BANK_VALUATION")
	@Enumerated(EnumType.STRING)
	private BankValuationEnum bankValuation;

	@Column(name = "ASSET_TYPE")
	private String assetType;

	@Column(name = "PROP_AREA_IN_UNITS")
	@Enumerated(EnumType.STRING)
	private UnitMeasurementEnum propertyAreaInUnits;

	@Column(name = "PROP_AREA_SIZE")
	private Double propertyAreaSize;

	@Column(name = "BORROWER_MKT_VAL_OF_ASSET")
	private Double borrowersMktValOfAsset;

	@Column(name = "ASSET_VALUE")
	private String assetValue;

	@Column(name = "VALUATION_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date valuationDate;

	@Column(name = "ACTUAL_PROP_AREA_IN_UNITS")
	@Enumerated(EnumType.STRING)
	private UnitMeasurementEnum actualPropertyAreaInUnits;

	@Column(name = "ACTUAL_PROP_AREA_SIZE")
	private Double actualPropertyAreaSize;

	@Column(name = "FACE_VAL_OF_ASSET")
	private String faceValOfAsset;

	@Column(name = "MKT_VAL_OF_ASSET")
	private String mktValOfAsset;

	@Column(name = "FORCED_SALE_VALUE")
	private Double forcedSaleValue;

}
