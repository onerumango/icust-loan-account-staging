package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustAccountCreateModel;

public interface IcustAccountCreateService {

	ResponseEntity<?> upsertAccountCreate(IcustAccountCreateModel icustAccountCreateModel);

	ResponseEntity<?> fetchAccountCreateByLoanAccountId(Long loanAccountId);

	ResponseEntity<?> fetchAccountCreateById(Long id);

}
