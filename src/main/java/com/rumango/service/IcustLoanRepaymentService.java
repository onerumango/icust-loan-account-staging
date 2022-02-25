package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustLoanRepaymentModel;

public interface IcustLoanRepaymentService {

	ResponseEntity<?> upsertLoanRepaymentDeatils(IcustLoanRepaymentModel icustLoanRepaymentModel);

	ResponseEntity<?> fetchLoanRepaymentDetails();

	ResponseEntity<?> fetchLoanRepaymentDetailById(Long loanAccountId);

}
