package com.rumango.controller;

import java.text.MessageFormat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rumango.model.IcustLoanInfoModel;
import com.rumango.service.IcustLoanService;

@RestController
@RequestMapping("/loan-account")
public class IcustLoanController {
	private static final Logger logger = LogManager.getLogger(IcustLoanController.class);

	@Autowired
	private IcustLoanService icustLoanService;
	
	@PostMapping(value="/upsertLoanDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertLoanDetails(@RequestBody IcustLoanInfoModel icustLoanInfoModel){
		logger.info(MessageFormat.format("Execution Started for upsertLoanDetails icustLoanInfoModel:{0}", icustLoanInfoModel));
		try {
			return icustLoanService.upsertLoanDetails(icustLoanInfoModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertLoanDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertLoanDetails");
		}
	}
	
	@GetMapping(value = "/fetchLoanDetails")
	public ResponseEntity<?> fetchLoanDetails(@RequestParam(value="loanId" , required=false) Long loanId){
		logger.info(MessageFormat.format("Execution Started for fetchLoanDetails loanId:{0}", loanId));
		try {
			return icustLoanService.fetchLoanDetails(loanId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchLoanDetails");
		}
	}
	
	@GetMapping(value = "/fetchLoanInfo")
	public ResponseEntity<?> fetchLoanInfo(){
		logger.info("Execution Started for fetchLoanInfo");
		try {
			return icustLoanService.fetchLoanInfo();
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchLoanInfo");
		}
	}
}

