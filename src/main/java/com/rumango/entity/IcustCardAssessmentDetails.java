package com.rumango.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ICUST_CARD_ASSESSMENT_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IcustCardAssessmentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Card_Assessment_Sequence")
	@SequenceGenerator(name = "Card_Assessment_Sequence", sequenceName = "CARD_ASSESSMENT_SEQUENCE_ID_SEQ")
	private Long assessmentId;
	private String cardId;
	private Double requestedCardLimit;
	private String systemRecommendation;
	private String userRecommendation;
	private Double recommendedCardLimit;
	private Double approvedCardLimit;
}
