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
@Table(name = "ICUST_COLLATERAL_DETAILS")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustCollateralDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "COLLATERAL_DETAILS_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collateral_details_id_Sequence")
	@SequenceGenerator(name = "collateral_details_id_Sequence", sequenceName = "COLLATERAL_DETAILS_ID_SEQ")
	private Long collateralDetailsId;
	private Long collateralId;
	private Long noOfCollateral;
	private Double totalCollateralValue;
	private String utilizedPreviously;
	private String coverAvailable;
	private Boolean secondaryChargeAllowed;
}