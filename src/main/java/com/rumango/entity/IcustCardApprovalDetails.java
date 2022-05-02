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

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ICUST_CARD_APPROVAL_DETAILS")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustCardApprovalDetails {

	@Id
	@Column(name = "APPROVAL_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_approval_id_Sequence")
	@SequenceGenerator(name = "card_approval_id_Sequence", sequenceName = "CARD_APPROVAL_ID_SEQ")
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
