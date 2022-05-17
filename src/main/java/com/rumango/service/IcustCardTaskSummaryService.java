package com.rumango.service;

import org.springframework.http.ResponseEntity;

public interface IcustCardTaskSummaryService {

	ResponseEntity<?> fetchTaskSummaryInfo(Long cardId);

	ResponseEntity<?> fetchTaskSummaryDetails(String status, Integer page, Integer size, String customerId,
			Long loanAccountId, String customerName);

}
