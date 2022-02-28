package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustLoanChargeModel;

public interface IcustLoanChargeService {

	ResponseEntity<?> upsertLoanChargeDetails(IcustLoanChargeModel icustLoanChargeModel);

	ResponseEntity<?> fetchLoanChargeDetails();

	ResponseEntity<?> fetchLoanChargeDetailsById(Long loanAccoutId);

}
