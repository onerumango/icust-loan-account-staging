package com.rumango.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ICUST_CUSTOMER_ADDRESS_INFO",indexes = {@Index(name = "customer_address",  columnList="CUSTOMER_ID", unique = false)})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IcustCustomerAddress implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_Sequence")
	@SequenceGenerator(name = "address_id_Sequence", sequenceName = "CUSTOMER_ADDRESS_ID_SEQ")
	private long id;
	
	@Column(name = "ADDRESS_TYPE")
	private String addressType;
	
	@Column(name = "ADDRESS1")
	private String address1;
	
	@Column(name = "ADDRESS2")
	private String address2;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "COUNTRY")
	private String country;
	private Long cityId;
	
	@Column(name = "RESIDENCE_TYPE")
	private String residenceType;
	
	@Column(name = "ZIP_CODE")
	private Long zipCode;
	
	@Column(name="CUSTOMER_ID")
	private Long customerId;
	
	private String customerType;
	
	@Column(name="CORPORATE_ID")
	private Long corporateId;
	
	private String primaryEmail;
	private String secondaryEmail;
	private Long mobileNumber;
	private String landLine;
	private String isMailingAddressSame;
	
}
