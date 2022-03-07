package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustLoanChargeListModel;

public interface IcustLoanChargeService {

	ResponseEntity<?> upsertLoanChargeDetails(IcustLoanChargeListModel icustLoanChargeListModel);

	ResponseEntity<?> fetchLoanChargeDetails();

	ResponseEntity<?> fetchLoanChargeDetailsById(Long loanAccoutId);

}
