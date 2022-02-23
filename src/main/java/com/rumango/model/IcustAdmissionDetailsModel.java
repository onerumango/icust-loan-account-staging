package com.rumango.model;

import java.sql.Date;

import lombok.Data;

@Data
public class IcustAdmissionDetailsModel {
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
