package com.rumango.controller;

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

import com.rumango.model.IcustLoanChargeModel;
import com.rumango.service.IcustLoanChargeService;


@RestController
@RequestMapping(value="charge_api")
public class IcustLoanChargeController {
	
	private static final Logger logger= LogManager.getLogger(IcustLoanChargeController.class);
	
	@Autowired
	IcustLoanChargeService service;
	
	@PostMapping(value="/upsertLoanChargeDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertLoanChargeDetails(@RequestBody IcustLoanChargeModel icustLoanChargeModel){
		try {
			logger.info("Execution starts for upsertLoanChargeDetails");
			return service.upsertLoanChargeDetails(icustLoanChargeModel);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Execption occure while upsertLoanChargeDetails",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}finally {
			logger.info("Execution complete for upsertLoanChargeDetails");
		}
	}

	@GetMapping(value= "/fetchLoanChargeDetails")
	public ResponseEntity<?> fetchLoanChargeDetails(){
		try {
			return service.fetchLoanChargeDetails();
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Execption occure while fetchLoanChargeDetails",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}finally {
			logger.info("Execution complete for fetchLoanChargeDetails");
			
		}
	}
	
	@GetMapping(value = "/fetchLoanChargeDetailsById")
	public ResponseEntity<?> fetchLoanChargeDetailsById(@RequestParam (value="loanAccountId", required = false) Long loanAccoutId){
		
		try {
			return service.fetchLoanChargeDetailsById(loanAccoutId);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Execption occure while fetchLoanChargeDetailsById",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}finally {
			logger.info("Execution complete for fetchLoanChargeDetailsById");
		}
		
	}
	
}

