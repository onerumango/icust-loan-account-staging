package com.rumango.service;

import org.springframework.http.ResponseEntity;

public interface IcustApplicationEnrichmentService {

	ResponseEntity<?> fetchSummaryInfo(Long loanAccountId);

}
