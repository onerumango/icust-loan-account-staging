package com.rumango.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rumango.model.IcustCardAssessmentModel;

@Service
public interface IcustCardAssessmentService {

	public ResponseEntity<?> upsertCardAssessmentDetails(IcustCardAssessmentModel cardAssessmentDetailsModel);

	public ResponseEntity<?> fetchCardAssessmentDetails();

	public ResponseEntity<?> fetchCardAssessmentById(Long cardId);
}
