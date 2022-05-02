package com.rumango.controller;

import java.util.List;

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

import com.rumango.model.IcustCardChargeModel;
import com.rumango.model.IcustLoanChargeListModel;
import com.rumango.service.IcustCardChargeService;
import com.rumango.service.IcustLoanChargeService;


@RestController
@RequestMapping(value="charge_api")
public class IcustLoanChargeController {
	
	private static final Logger logger= LogManager.getLogger(IcustLoanChargeController.class);
	
	@Autowired
	IcustLoanChargeService service;
	
	@Autowired
	IcustCardChargeService cardService;
	
	@PostMapping(value="/upsertLoanChargeDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertLoanChargeDetails(@RequestBody IcustLoanChargeListModel icustLoanChargeListModel){
		try {
			logger.info("Execution starts for upsertLoanChargeDetails");
			return service.upsertLoanChargeDetails(icustLoanChargeListModel);
		}catch (Exception e) {
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
			logger.error("Execption occure while fetchLoanChargeDetailsById",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}finally {
			logger.info("Execution complete for fetchLoanChargeDetailsById");
		}
		
	}
	
	@PostMapping(value="/upsertCardChargeDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertCardChargeDetails(@RequestBody List<IcustCardChargeModel> icustCardChargeModel){
		try {
			logger.info("Execution starts for upsertCardChargeDetails");
			return cardService.upsertCardChargeDetails(icustCardChargeModel);
		}catch (Exception e) {
			logger.error("Execption occure while upsertCardChargeDetails",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}finally {
			logger.info("Execution complete for upsertCardChargeDetails");
		}
	}

	@GetMapping(value= "/fetchCardChargeDetails")
	public ResponseEntity<?> fetchCardChargeDetails(){
		try {
			return cardService.fetchCardChargeDetails();
		}catch (Exception e) {
			logger.error("Execption occure while fetchCardChargeDetails",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}finally {
			logger.info("Execution complete for fetchCardChargeDetails");
			
		}
	}
	
	@GetMapping(value = "/fetchCardChargeDetailsById")
	public ResponseEntity<?> fetchCardChargeDetailsById(@RequestParam (value="cardId", required = false) Long cardId){
		
		try {
			return cardService.fetchCardChargeDetailsById(cardId);
		}catch (Exception e) {
			logger.error("Execption occure while fetchCardChargeDetailsById",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}finally {
			logger.info("Execution complete for fetchCardChargeDetailsById");
		}
		
	}
	
}

