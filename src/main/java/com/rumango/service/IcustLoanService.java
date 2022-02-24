package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustLoanInfoModel;

public interface IcustLoanService {

	ResponseEntity<?> upsertLoanDetails(IcustLoanInfoModel icustLoanInfoModel);

	ResponseEntity<?> fetchLoanDetails(Long loanAccountId);

	ResponseEntity<?> fetchLoanInfo();

}
