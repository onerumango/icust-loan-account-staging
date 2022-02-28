package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustOfferIssueModel;

public interface IcustOfferIssueService {

	ResponseEntity<?> upsertOfferIssue(IcustOfferIssueModel icustOfferIssueModel);

	ResponseEntity<?> fetchOfferIssueByLoanAccountId(Long loanAccountId);

	ResponseEntity<?> fetchOfferIssueById(Long id);

}
