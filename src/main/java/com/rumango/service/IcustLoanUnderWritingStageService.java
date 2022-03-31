package com.rumango.service;

import org.springframework.http.ResponseEntity;

public interface IcustLoanUnderWritingStageService {

	ResponseEntity<?> fetchSummaryInfo(Long loanAccountId);

}
