package com.rumango.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IcustCustomerCreateModel {
	private Long customerId;
	private String customerTypeId;
	private String prefix;
	private String firstName;
	private String lastName;
	private String middleName;
	private String primaryEmailAdress;
	private Long phoneNumber;
	private Long cifNumber;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "IST")
	private Date dateOfBirth;
	private String gender;
	private String materialStatus;
	private String nationality;
	private Set<IcustCustomerAddressModel> userAddress;
	private String secondaryEmailAdress;
	private String landlineNumber;
	private boolean active;
	private String createdBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss")
	private Timestamp createdTime;
	private String modifiedBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss")
	private Timestamp modifiedTime;
	private Boolean existingCustomer;
	private Boolean primaryCustomer;
	private String birthCountry;
}
