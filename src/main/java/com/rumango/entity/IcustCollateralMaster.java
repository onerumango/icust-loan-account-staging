package com.rumango.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ICUST_COLLATERAL_MASTER")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustCollateralMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "COLLATERAL_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collateral_id_Sequence")
	@SequenceGenerator(name = "collateral_id_Sequence", sequenceName = "COLLATERAL_ID_SEQ")
	private Long collateralId;
	private Long loanAccountId;
	private String collateralType;
	private String collateralCurrency;
	private Double collateralValue;
	private String attributes;
	private String dimensions;
	private Boolean thirdPartyCollateral;	
	private String building;
	private String street;
	private String locality;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "collateralId")
	private List<IcustCollateralDetails> collateralDetails;
}
