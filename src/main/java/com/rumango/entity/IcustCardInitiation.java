package com.rumango.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rumango.enums.CardInitiationStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ICUST_CARD_INITIATION")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustCardInitiation implements Serializable{
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
	private String customerAccount;
	private String applicantName;
	private String currency;
	private String employmentType;
	private String affinityProgram;
	private String nameOnCard;
	
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private CardInitiationStatus status;
	
	
}
