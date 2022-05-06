package com.rumango.model;

import lombok.Data;

@Data
public class IcustCardChargeModel {
	private Long chargeDetailsId;
	private Long cardId;
	private String intrestType;
	private Double amount;
	private Boolean waiver;
}