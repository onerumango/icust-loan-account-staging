package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustVehicleDetailsModel;

public interface IcustVehicleService {

	ResponseEntity<?> upsertVehicleDetails(IcustVehicleDetailsModel icustVehicleDetailsModel);

	ResponseEntity<?> fetchVehicleDetails(Long loanAccountId);

	ResponseEntity<?> fetchVehicleInfo();

}
