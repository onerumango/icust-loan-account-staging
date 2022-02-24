package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustCollateralMasterModel;

public interface IcustCollateralService {

	ResponseEntity<?> upsertCollateralDetails(IcustCollateralMasterModel icustCollateralMasterModel);

	ResponseEntity<?> fetchCollateralDetails(Long loanAccountId);

}
