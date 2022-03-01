package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustLoanInterestListModel;
import com.rumango.model.IcustLoanInterestModel;

public interface IcustLoanInterestService {

	ResponseEntity<?> upsertDetails(IcustLoanInterestListModel loanInterestModel);

	ResponseEntity<?> fetchLoanInterestDetails();

	ResponseEntity<?> fetchLoanInterestById(Long loanAccountId);

}
