package com.rumango.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ICUST_CUSTOMER_DOCUMENTS",indexes = {@Index(name = "customer_documents",  columnList="CUSTOMER_ID", unique = false)})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IcustCustomerDocuments implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_id_Sequence")
	@SequenceGenerator(name = "doc_id_Sequence", sequenceName = "CUSTOMER_DOCUMENT_ID_SEQ")
	private long id;

	@Column(name = "DOCUMENT_NAME")
	private String documentName;

	@Column(name = "DOCUMENT_TYPE")
	private Integer documentType;

	@Column(name = "FILE_NAME")
	private String fileType;

	@Column(name = "FILE_TYPE")
	private String fileName;
	
	@Lob
	@Type(type = "org.hibernate.type.MaterializedClobType")
	@Column(name = "FP_TEMPLATE", length = Integer.MAX_VALUE)
	private String fpTemplateBase64;
	
	@Lob
	@Type(type = "org.hibernate.type.MaterializedClobType")
	@Column(name = "FP_ISOTEMPLATE", length = Integer.MAX_VALUE)
	private String fpIsoTemplateBase64;
	
	@Lob
	@Type(type = "org.hibernate.type.MaterializedClobType")
	@Column(name = "Img_Base64", length = Integer.MAX_VALUE)
	private String imgTemplateBase64;
	
	
	

	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	@Column(name = "VERIFICATION_TYPE")
	private String verificationType;
	
	@Column(name = "DOCUMENT_SIDE")
	private Integer documentSide;
	
	private String fileUrl;
	
	private String idNumber;

}
