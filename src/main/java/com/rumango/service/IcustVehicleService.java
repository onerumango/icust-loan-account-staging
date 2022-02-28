package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustVehicleDetailsModel;

public interface IcustVehicleService {

	ResponseEntity<?> upsertVehicleDetails(IcustVehicleDetailsModel icustVehicleDetailsModel);

	ResponseEntity<?> fetchVehicleDetailsByLoanAccId(Long loanAccountId);

	ResponseEntity<?> fetchVehicleInfoById(Long id);

}
