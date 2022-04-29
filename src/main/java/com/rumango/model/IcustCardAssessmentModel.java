package com.rumango.model;

import lombok.Data;

@Data
public class IcustCardAssessmentModel {

	private Long assessmentId;
	private String cardId;
	private Double requestedCardLimit;
	private String systemRecommendation;
	private String userRecommendation;
	private Double recommendedCardLimit;
	private Double approvedCardLimit;
}
