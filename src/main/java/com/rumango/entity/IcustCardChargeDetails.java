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
@Table(name="ICUST_CARD_CHARGE_DETAILS")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
@AllArgsConstructor
public class IcustCardChargeDetails {

	@Id
	@Column(name="CHARGE_DETAILS_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_charge_details_id_sequence")
	@SequenceGenerator(name = "card_charge_details_id_sequence", sequenceName = "CARD_CHARGE_DETAILS_ID_SEQ")
	private Long cardChargeId;
	private Long cardId;
	private String intrestType;
	private Double amount;
	private Boolean waiver;
}
