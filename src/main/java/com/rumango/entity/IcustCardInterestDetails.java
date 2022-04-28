package com.rumango.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ICUST_CARD_INTEREST_DETAILS")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
@AllArgsConstructor
public class IcustCardInterestDetails {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_interest_details_id_sequence")
	@SequenceGenerator(name = "card_interest_details_id_sequence", sequenceName = "CARD_INTEREST_DETAILS_SEQ")
	private Long cardInterestId;
	private Long cardId;
	private String intrestType;
	private Double intrestRateApplicable;
	private Double marginIn;
	private Double effectiveRate;
}
