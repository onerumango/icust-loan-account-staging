package com.rumango.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ICUST_CARD_PREFERENCES")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustCardPreferences implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "CARD_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_id_Sequence")
	@SequenceGenerator(name = "card_id_Sequence", sequenceName = "CARD_ID_SEQ")
	private Long cardId;
	private Long customerId;
	private Boolean existingCustomer;
	private String cardType;
}
