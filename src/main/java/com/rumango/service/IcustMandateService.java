package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustMandateMasterModel;

public interface IcustMandateService {

	ResponseEntity<?> upsertMandateDetails(IcustMandateMasterModel icustMandateMasterModel);

	ResponseEntity<?> fetchMandateDetailsByLoanAccId(Long loanAccountId);

	ResponseEntity<?> fetchMandateInfoById(Long id);

}
