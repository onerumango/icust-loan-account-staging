package com.rumango.entity;

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
@Table(name = "ICUST_CARD_DOCUMENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IcustCardDocuments {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_doc_id_Sequence")
	@SequenceGenerator(name = "card_doc_id_Sequence", sequenceName = "CARD_DOCUMENT_ID_SEQ")
	private long id;
	private String documentName;
	private int documentType;
	private String fileType;
	private String fileName;
	private Long cardId;
	private String fileUrl;
	private int documentSide;
	private String screenType;
}
