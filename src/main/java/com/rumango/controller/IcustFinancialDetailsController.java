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

import com.rumango.model.IcustFinancialDetailsModel;
import com.rumango.service.IcustFinancialDetailsService;

@RestController
@RequestMapping("/financialDetails-api")
public class IcustFinancialDetailsController {
	private static final Logger logger = LogManager.getLogger(IcustFinancialDetailsController.class);

	@Autowired
	IcustFinancialDetailsService financialDetailsService;
	
	@PostMapping(value="/upsertfinancialDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertfinancialDetails(@RequestBody IcustFinancialDetailsModel icustFinancialDetailsModel){
		logger.info(MessageFormat.format("Execution Started for upsertfinancialDetails icustFinancialDetailsModel:{0}", icustFinancialDetailsModel));
		try {
			return financialDetailsService.upsertFinancialDetails(icustFinancialDetailsModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertfinancialDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertfinancialDetails");
		}
	}
	
	@GetMapping(value = "/fetchFinancialDetails")
	public ResponseEntity<?> fetchFinancialDetails(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchFinancialDetails loanAccountId:{0}", loanAccountId));
		try {
			return financialDetailsService.fetchFinancialDetails(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchFinancialDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchFinancialDetails");
		}
	}
	
	
}
