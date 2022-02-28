package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustFinancialDetailsModel;

public interface IcustFinancialDetailsService {

	ResponseEntity<?> upsertFinancialDetails(IcustFinancialDetailsModel icustFinancialDetailsModel);

	ResponseEntity<?> fetchFinancialDetailsByLoanAccId(Long loanAccountId);

	ResponseEntity<?> fetchFinancialInfoById(Long id);

}
