package com.rumango.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rumango.entity.IcustLoanValuationOfAssetEntity;
import com.rumango.enums.BankValuationEnum;
import com.rumango.enums.UnitMeasurementEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IcustLoanValuationOfAssetModel {
	private Long valuationId;

	private Long loanAccountId;

	private String bankValuation;

	private String assetType;

	private String propertyAreaInUnits;

	private Double propertyAreaSize;

	private Double borrowersMktValOfAsset;

	private String assetValue;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date valuationDate;

	private String actualPropertyAreaInUnits;

	private Double actualPropertyAreaSize;

	private String faceValOfAsset;

	private String mktValOfAsset;

	private Double forcedSaleValue;
}
