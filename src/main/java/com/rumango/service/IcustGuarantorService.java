package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustGuarantorDetailsModel;

public interface IcustGuarantorService {

	ResponseEntity<?> upsertGuarantorDetails(IcustGuarantorDetailsModel icustGuarantorDetailsModel);

	ResponseEntity<?> fetchGuarantorByLoanAccId(Long loanAccountId);

}
