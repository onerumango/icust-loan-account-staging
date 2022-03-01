package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustLoanOfferAcceptOrRejectModel;

public interface IcustOfferAcceptOrRejectService {

	ResponseEntity<?> upsertOfferAcceptOrReject(IcustLoanOfferAcceptOrRejectModel icustLoanOfferAcceptOrRejectModel);

	ResponseEntity<?> fetchOfferAcceptOrRejectDetails();

	ResponseEntity<?> fetchOfferAcceptOrRejectDetailsById(Long loanAccountId);


}
