package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustLoanDisbursementModel;

public interface IcustLoanDisbursementService {

	ResponseEntity<?> upsertLoanDisbursementDetails(IcustLoanDisbursementModel icustLoanDisbursementModel);

	ResponseEntity<?> fetchLoanDisbursementDetails();

	ResponseEntity<?> fetchLoanDisbursementById(Long loanAccountId);

}
