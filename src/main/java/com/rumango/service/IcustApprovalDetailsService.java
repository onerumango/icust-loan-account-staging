package com.rumango.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rumango.model.IcustApprovalDetailsModel;

@Service
public interface IcustApprovalDetailsService {

	ResponseEntity<?> upsertApprovalDetails(IcustApprovalDetailsModel icustApprovalDetailsModel);

	ResponseEntity<?> fetchApprovalDetails(Long loanAccountId);

}
