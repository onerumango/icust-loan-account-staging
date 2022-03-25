package com.rumango.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.rumango.model.IcustLoanDocumentsModel;

public interface LoanDocumentUploadService {

	ResponseEntity<?> uploadLoanDocs(MultipartFile file, IcustLoanDocumentsModel model);

	ResponseEntity<?> fetchLoanDocuments(Long loanAccountId, Integer screenType);

	Resource loadAsResource(String fileName);

}
