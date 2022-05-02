package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustCardApprovalModel;

public interface IcustCardApprovalDetailService {

	ResponseEntity<?> upsertCardApprovalDetails(IcustCardApprovalModel icustCardApprovalModel);

	ResponseEntity<?> fetchApprovalDetails(Long cardId);
}
