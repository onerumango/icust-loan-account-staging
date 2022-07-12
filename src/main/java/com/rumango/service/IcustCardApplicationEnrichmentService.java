package com.rumango.service;

import org.springframework.http.ResponseEntity;

public interface IcustCardApplicationEnrichmentService {

	public ResponseEntity<?> fetchCardSummaryInfo(Long cardAccountId);
}
