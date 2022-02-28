package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustAssetDetailsModel;

public interface IcustAssetService {

	ResponseEntity<?> upsertAssetDetails(IcustAssetDetailsModel icustAssetDetailsModel);

	ResponseEntity<?> fetchAssetDetailsByLoanAccId(Long loanAccountId);

	ResponseEntity<?> fetchAssetInfoById(Long id);

}
