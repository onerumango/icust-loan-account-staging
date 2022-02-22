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
@Table(name = "ICUST_ASSET_DETAILS")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustAssetDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name = "ASSET_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_id_Sequence")
	@SequenceGenerator(name = "asset_id_Sequence", sequenceName = "ASSET_ID_SEQ")
	private Long assetId;
	private Long loanId;
	private String mortgagedBranch;
	private String homeType;
	private String dimensions;
	private Double marketValue;
	private String assetStatus;
	private String building;
	private String street;
	private String locality;
	private String city;
	private String state;
	private String country;
	private String zipCode;
}
