package com.rumango.service;

import org.springframework.http.ResponseEntity;

public interface IcustLoanTaskSummaryService {

	ResponseEntity<?> fetchTaskSummaryInfo(Long loanAccountId);

	ResponseEntity<?> fetchTaskSummaryDetails(String status, Integer page, Integer size, String customerId,
			Long loanAccountId, String customerName);

}
