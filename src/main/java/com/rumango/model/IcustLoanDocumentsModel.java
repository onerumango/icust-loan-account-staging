package com.rumango.model;

import lombok.Data;

@Data
public class IcustLoanDocumentsModel {
	private long id;
	private String documentName;
	private int documentType;
	private String fileType;
	private String fileName;
	private Long loanAccountId;
	private String fileUrl;
	private String screenType;
}
