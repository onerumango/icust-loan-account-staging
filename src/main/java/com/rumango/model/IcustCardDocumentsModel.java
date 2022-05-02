package com.rumango.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class IcustCardDocumentsModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String documentName;
	private int documentType;
	private String fileType;
	private String fileName;
	private Long cardId;
	private String fileUrl;
	private String screenType;
	
}
