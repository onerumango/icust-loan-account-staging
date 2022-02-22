package com.rumango.controller;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rumango.model.IcustLoanInterestModel;
import com.rumango.service.IcustLoanInterestService;


@RestController
@RequestMapping(value="loanInterest")
public class IcustLoanInterestController {

	private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(IcustLoanInterestController.class);
	
	@Autowired
	IcustLoanInterestService service;
	
	@PostMapping(value = "/upsertDetails",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertDetails(
			@RequestBody IcustLoanInterestModel loanInterestModel) throws Exception {
		logger.info(MessageFormat.format("Exectution started for upsertLoanInterestDetails: {0}",
				loanInterestModel));
		try {
			return service.upsertDetails(loanInterestModel);
		} catch (Exception e) {
			logger.error("Execption occoured while executing upsertLoanInterestDetails", e);
			throw e;
		} finally {
			logger.info("Execution completed for upsertLoanInterestDetails");
		}
	}
	
	@GetMapping(value = "/fetchLoanInterestDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchLoanInterestDetails() {
		logger.info("Exectution started for fetchLoanInterestDetails");
		try {
			return service.fetchLoanInterestDetails();
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanInterestDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchLoanInterestDetails");
		}
	}
	
	@GetMapping(value = "/fetchLoanInterestById", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchLoanInterestById (@RequestParam(value = "loanId") Long loanId){
		logger.info("Exectution started for fetchLoanInterestById");
		try {
			return service.fetchLoanInterestById(loanId);
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanInterestById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchLoanInterestById");
		}
	}
}