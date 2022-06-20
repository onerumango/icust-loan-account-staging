package com.rumango.model;


import lombok.Data;

@Data
public class IcustCardInitiationModel {
	private Long cardId;
	private Long customerId;
	private Boolean existingCustomer;
	private String cardType;
	private String customerAccount;
	private String applicantName;
	private String currency;
	private String employmentType;
	private String affinityProgram;
	private String nameOnCard;
	private String status;
	private String kycStatus;
	private String kycReference;
}
