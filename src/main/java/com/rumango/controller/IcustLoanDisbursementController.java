package com.rumango.controller;

import java.text.MessageFormat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

import com.rumango.model.IcustLoanDisbursementModel;
import com.rumango.service.IcustLoanDisbursementService;

@RestController
@RequestMapping(value="/disbursement-api")
public class IcustLoanDisbursementController {

	private static final Logger logger = LogManager.getLogger(IcustLoanDisbursementController.class);
	
	@Autowired
	IcustLoanDisbursementService service;
	
	@PostMapping(value="/upsertLoanDisbursementDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertLoanDisbursementDetails(@RequestBody IcustLoanDisbursementModel icustLoanDisbursementModel){
		logger.info(MessageFormat.format("Execution Started for upsertLoanDisbursementDetails icustLoanDisbursementModel:{0}", icustLoanDisbursementModel));
		try {
			return service.upsertLoanDisbursementDetails(icustLoanDisbursementModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertLoanDisbursementDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertLoanDisbursementDetails");
		}
	}
	@GetMapping(value = "/fetchLoanDisbursementDetails")
	public ResponseEntity<?> fetchLoanDisbursementDetails(){
		logger.info("Execution Started for fetchLoanDisbursementDetails");
		try {
			return service.fetchLoanDisbursementDetails();
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanDisbursementDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchLoanDisbursementDetails");
		}
	}

	@GetMapping(value = "/fetchLoanDisbursementById")
	public ResponseEntity<?> fetchLoanDisbursementById(@RequestParam(value="loanId" , required=false) Long loanId){
		logger.info(MessageFormat.format("Execution Started for fetchLoanDisbursementById loanId:{0}", loanId));
		try {
			return service.fetchLoanDisbursementById(loanId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanDisbursementById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchLoanDisbursementById");
		}
	}

}
