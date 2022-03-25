package com.rumango.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ICUST_LOAN_DOCUMENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IcustLoanDocuments implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_doc_id_Sequence")
	@SequenceGenerator(name = "loan_doc_id_Sequence", sequenceName = "LOAN_DOCUMENT_ID_SEQ")
	private long id;
	private String documentName;
	private int documentType;
	private String fileType;
	private String fileName;
	private Long loanAccountId;
	private String fileUrl;
	private int documentSide;
	private String screenType;
	
}
