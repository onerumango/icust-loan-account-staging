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
@Table(name = "ICUST_POST_OFFER_AMENDMENT")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustPostOfferAmendmentDetails implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "POST_OFFER_AMENDMENT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_offer_amendment_id_Sequence")
	@SequenceGenerator(name = "post_offer_amendment_id_Sequence", sequenceName = "POST_OFFER_AMENDMENT_ID_SEQ")
	
	private Long postOfferAmendmentId;
	private Long loanAccountId;
	private String applicantName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date offerIssueDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date offerExpiryDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss", timezone = "IST")
	private Date offerAmendDate;
	private Double approvedLoanAmount;
	private Double loanAmmountRecommended;
	private String loanTenure;
	private String installmentType;
	private Double rateOfInterest;
	private Long margin;
	private Double effectiveRate;

}
