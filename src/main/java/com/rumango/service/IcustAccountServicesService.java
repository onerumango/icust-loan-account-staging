package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustAccountServicesModel;

public interface IcustAccountServicesService {

	ResponseEntity<?> fetchAccountServicesByLoanAccountId(Long loanAccountId);

	ResponseEntity<?> fetchAccountServicesById(Long id);

	ResponseEntity<?> upsertAccountServices(IcustAccountServicesModel icustAccountServicesModel);

}
