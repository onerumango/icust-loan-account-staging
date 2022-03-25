package com.rumango.controller;

import java.io.IOException;
import java.text.MessageFormat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.rumango.model.IcustLoanDocumentsModel;
import com.rumango.service.LoanDocumentUploadService;


@RestController
@RequestMapping("/loan/upload")
public class LoanDocumentUploadController {
	private static final Logger logger = LogManager.getLogger(LoanDocumentUploadController.class);
	
	
	@Autowired
	LoanDocumentUploadService uploadService;
	@PostMapping(value = "uploadLoanDocs", produces = MediaType.APPLICATION_JSON_VALUE, consumes = { "multipart/*",
			"application/x-www-form-urlencoded", "application/json" })
	public ResponseEntity<?> uploadLoanDocs(@RequestParam("file") MultipartFile file, @RequestParam("data") String data)
			throws IOException, java.io.FileNotFoundException {
		logger.info(MessageFormat.format("Execution started for uploadLoanDocs for data ", data));
		try {
			IcustLoanDocumentsModel model = new Gson().fromJson(data, IcustLoanDocumentsModel.class);
			return uploadService.uploadLoanDocs(file,model);
		} catch (Exception e) {
			logger.error("Execption occoured while executing uploadLoanDocs", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for uploadLoanDocs");
		}
	}
	
	@GetMapping(value="/fetchLoanDocuments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchLoanDocuments (@RequestParam(value = "loanAccountId", required = true) Long loanAccountId,
			@RequestParam(value = "screenType", required = true) Integer screenType){
		logger.info(MessageFormat.format(
				"Exectution started for fetchLoanDocuments for documentType::", screenType));
		try {
			return uploadService.fetchLoanDocuments(loanAccountId,screenType);
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanDocuments", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchLoanDocuments");
		}
	}
}
