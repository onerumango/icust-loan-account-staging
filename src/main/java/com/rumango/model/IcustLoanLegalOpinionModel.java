package com.rumango.model;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IcustLoanLegalOpinionModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long legalOpinionId;
	
	private Long loanAccountId;

	private String assetAreaInUnits;

	private Double assetAreaSize;

	private String description;

	private String isDecisionFavorable;

	private String mktValOfAsset;

	private String lawyerName;

	private String opinion;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date opinionDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date valuationDate;

}
