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
@Table(name = "ICUST_MANDATE_DETAILS")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustMandateDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "MANDATE_DETAILS_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mandate_details_id_Sequence")
	@SequenceGenerator(name = "mandate_details_id_Sequence", sequenceName = "MANDATE_DETAILS_ID_SEQ")
	private Long mandateDetailsId;
	private Long mandateId;
	private String applicantName;
	private Double collateralShare;
	private Double repaymentShare;
	
}
