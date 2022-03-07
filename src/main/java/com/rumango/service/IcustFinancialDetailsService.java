package com.rumango.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustFinancialDetailsModel;

public interface IcustFinancialDetailsService {

	ResponseEntity<?> upsertFinancialDetails(List<IcustFinancialDetailsModel> icustFinancialDetailsModel);

	ResponseEntity<?> fetchFinancialDetailsByLoanAccId(Long loanAccountId);

	ResponseEntity<?> fetchFinancialInfoById(Long id);

}
