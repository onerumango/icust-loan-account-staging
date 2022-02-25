package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustLoanAssessmentDetailsModel;

public interface IcustLoanAssessmentService {

	public ResponseEntity<?> upsertLoanAssessmentDetails(IcustLoanAssessmentDetailsModel loanAssessmentDetailsModel);

	public ResponseEntity<?> fetchLoanAssessmentDetails();

	public ResponseEntity<?> fetchLoanAssessmentById(Long loanAccountId);
	
}
