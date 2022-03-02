package com.rumango.controller;

import java.text.MessageFormat;

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

import com.rumango.model.IcustAccountServicesModel;
import com.rumango.service.IcustAccountServicesService;

@RestController
@RequestMapping("/account-services-api")
public class IcustAccountServicesController {
	private static final Logger logger = Logger.getLogger(IcustAccountServicesController.class);

	@Autowired
	IcustAccountServicesService accountServicesService;
	
	@PostMapping(value="/upsertAccountServices", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertAccountServices(@RequestBody IcustAccountServicesModel icustAccountServicesModel){
		logger.info(MessageFormat.format("Execution Started for upsertAccountServices icustLoanInfoModel:{0}", icustAccountServicesModel));
		try {
			return accountServicesService.upsertAccountServices(icustAccountServicesModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertAccountServices", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertAccountServices");
		}
	}
	
	@GetMapping(value = "/fetchAccountServicesByLoanAccountId")
	public ResponseEntity<?> fetchAccountServicesByLoanAccountId(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchAccountServicesByLoanAccountId loanAccountId:{0}", loanAccountId));
		try {
			return accountServicesService.fetchAccountServicesByLoanAccountId(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchAccountServicesByLoanAccountId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchAccountServicesByLoanAccountId");
		}
	}
	
	@GetMapping(value = "/fetchAccountServicesById")
	public ResponseEntity<?> fetchAccountServicesById(@RequestParam(value="id" , required=false) Long id){
		logger.info(MessageFormat.format("Execution Started for fetchAccountServicesById id:{0}", id));
		try {
			return accountServicesService.fetchAccountServicesById(id);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchAccountServicesById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchAccountServicesById");
		}
	}
}
