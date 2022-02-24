package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustMandateMasterModel;

public interface IcustMandateService {

	ResponseEntity<?> upsertMandateDetails(IcustMandateMasterModel icustMandateMasterModel);

	ResponseEntity<?> fetchMandateDetails(Long loanAccountId);

	ResponseEntity<?> fetchMandateInfo();

}
