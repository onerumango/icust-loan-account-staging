package com.rumango.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	private String prefix;
	private String firstName;
	private String middleName;
	private String lastName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "IST")
	private Date dateOfBirth;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private String phoneNumber;
	private String mobileNumber;
	private String emailAddress;
	private Long cifNumber;
	private String address1;
	private String address2;
}
