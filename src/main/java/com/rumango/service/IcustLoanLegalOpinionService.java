package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustLoanLegalOpinionModel;

public interface IcustLoanLegalOpinionService {

	ResponseEntity<?> saveOrUpdateLegalOpinion(IcustLoanLegalOpinionModel legalOpinionModel);

	ResponseEntity<?> getLegalOpinionById(Long id);

	ResponseEntity<?> getLegalOpinionByLoanAccountId(Long loanAccId);
}
