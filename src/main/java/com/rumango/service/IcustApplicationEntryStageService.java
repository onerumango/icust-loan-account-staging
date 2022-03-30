package com.rumango.service;

import org.springframework.http.ResponseEntity;

public interface IcustApplicationEntryStageService {

	ResponseEntity<?> fetchSummaryInfo(Long loanAccountId);

}
