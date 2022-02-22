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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ICUST_ADMISSION_DETAILS")
@Data
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class IcustAdmissionDetails implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ADMISSION_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admission_id_Sequence")
	@SequenceGenerator(name = "admission_id_Sequence", sequenceName = "ADMISSION_ID_SEQ")
	private Long admissionId;
	private Long loanId;
	private String loanRequestedFor;
	private String admissionStatus;
	private String modeOfStudy;
	private String proposedCourseOfStudy;
	private String institution;
	private String university;
	private String country;
	private String institutionRanking;
	private String courseDuration;
	private Date courseCommencementDate;
	private String specialization;
	private String projectedEarning;
	private String employmentPotential;
	private Boolean scholarshipOrBusinessEligible;
	private Double costOfCourse;
	private Double source;
}
