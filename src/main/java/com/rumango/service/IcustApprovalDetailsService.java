package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustApprovalDetailsModel;

public interface IcustApprovalDetailsService {

	ResponseEntity<?> upsertApprovalDetails(IcustApprovalDetailsModel icustApprovalDetailsModel);

	ResponseEntity<?> fetchApprovalDetails(Long loanAccountId);

}
