package com.rumango.model;

import lombok.Data;

@Data
public class CardTaskSummaryDataModel {
	private Long cardId;
	private Long customerId;
	private String cardType;
	private String createdBy;
	private Long cifNumber;
	private String firstName;
	private String middleName;
	private String lastName;
	private String status;
	private String profileImage;
	private String prefix;
}

