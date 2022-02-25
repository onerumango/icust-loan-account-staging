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
@Table(name="ICUST_LOAN_CHARGE_DETAILS")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor

public class IcustLaonChargeDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CHARGE_DETAILS_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "charge_details_id_Sequence")
	@SequenceGenerator(name = "charge_details_id_Sequence", sequenceName = "CHARGE_DETAILS_ID_SEQ")
	
	private Long chargeDetailsId;
	private Long loanAccountId;
	private String interestType;
	private Double amout;
	private Boolean waiver;

}
