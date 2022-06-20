package com.rumango.model;

import java.sql.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IcustGuarantorDetailsModel {
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
