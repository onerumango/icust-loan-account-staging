package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustLoanAssessmentDetailsModel;
import com.rumango.model.IcustLoanInfoModel;

public interface IcustLoanService {

	ResponseEntity<?> upsertLoanDetails(IcustLoanInfoModel icustLoanInfoModel);

	ResponseEntity<?> fetchLoanDetailsByLoanAccId(Long loanAccountId);

	ResponseEntity<?> fetchLoanDetailsById(Long id);

	ResponseEntity<?> updateStatusApproveOrReject(IcustLoanInfoModel icustLoanInfoModel);

	ResponseEntity<?> fetchAssessmentInfoByLoanAccId(Long loanAccountId);

	ResponseEntity<?> updateApprovedLoanAmount(IcustLoanAssessmentDetailsModel assessmentModel);


}
