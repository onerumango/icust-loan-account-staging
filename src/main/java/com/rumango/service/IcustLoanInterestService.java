package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustLoanInterestModel;

public interface IcustLoanInterestService {

	ResponseEntity<?> upsertDetails(IcustLoanInterestModel loanInterestModel);

	ResponseEntity<?> fetchLoanInterestDetails();

	ResponseEntity<?> fetchLoanInterestById(Long loanAccountId);

}
