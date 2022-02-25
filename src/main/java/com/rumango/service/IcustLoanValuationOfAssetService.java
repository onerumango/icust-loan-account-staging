package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustLoanValuationOfAssetModel;

public interface IcustLoanValuationOfAssetService {

	ResponseEntity<?> saveOrValuationOfAsset(IcustLoanValuationOfAssetModel valuationOfAsset);

	ResponseEntity<?> getValuationOfAssetById(Long id);

	ResponseEntity<?> getValuationOfAssetByLoanAccountId(Long loanAccId);

}
