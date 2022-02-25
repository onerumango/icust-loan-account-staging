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
@Table(name = "ICUST_GUARANTOR_DETAILS")
@Data
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class IcustGuarantorDetails implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "GUARANTOR_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guarantor_id_Sequence")
	@SequenceGenerator(name = "guarantor_id_Sequence", sequenceName = "GUARANTOR_ID_SEQ")
	private Long guarantorId;
	private Long loanAccountId;
	private String relationWithCustomer;
	private Long customerId;

}
