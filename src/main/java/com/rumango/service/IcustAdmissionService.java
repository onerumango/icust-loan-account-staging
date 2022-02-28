package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustAdmissionDetailsModel;

public interface IcustAdmissionService {

	ResponseEntity<?> upsertAdmissionDetails(IcustAdmissionDetailsModel icustAdmissionDetailsModel);

	ResponseEntity<?> fetchAdmissionDetailsByLoanAccId(Long loanAccountId);

	ResponseEntity<?> fetchAdmissionInfoById(Long id);

}
