package com.rumango.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class IcustCardApprovalModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long approvalId;
	private Long cardId;
	private String accountType;
	private String accountBranch;
	private String productCode;
	private String productName;
	private String accountCurrency;
	private Long existingValues;
	private String userRecommendation;
	private String remarks;
}
