package com.rumango.model;

import lombok.Data;

@Data
public class IcustCardInterestModel {
	private Long cardInterestId;
	private Long cardId;
	private String intrestType;
	private Long intrestRateApplicable;
	private Long marginIn;
	private Long effectiveRate;
}
