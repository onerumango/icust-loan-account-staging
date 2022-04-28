package com.rumango.controller;

import java.text.MessageFormat;
import java.util.List;

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

import com.rumango.model.IcustCardInterestModel;
import com.rumango.model.IcustLoanInterestListModel;
import com.rumango.service.IcustCardInterestService;
import com.rumango.service.IcustLoanInterestService;


@RestController
@RequestMapping(value="interest-api")
public class IcustLoanInterestController {

	private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(IcustLoanInterestController.class);
	
	@Autowired
	IcustLoanInterestService service;
	
	@Autowired
	IcustCardInterestService cardService;
	
	@PostMapping(value = "/upsertDetails",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertDetails(
			@RequestBody IcustLoanInterestListModel icustLoanInterestListModel) throws Exception {
		logger.info(MessageFormat.format("Exectution started for upsertLoanInterestDetails: {0}",
				icustLoanInterestListModel));
		try {
			return service.upsertDetails(icustLoanInterestListModel);
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
	public ResponseEntity<?> fetchLoanInterestById (@RequestParam(value = "loanAccountId") Long loanAccountId){
		logger.info("Exectution started for fetchLoanInterestById");
		try {
			return service.fetchLoanInterestById(loanAccountId);
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanInterestById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchLoanInterestById");
		}
	}
	
	@PostMapping(value = "/upsertDetails",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertDetails(
			@RequestBody List<IcustCardInterestModel> icustCardInterestListModel) throws Exception {
		logger.info(MessageFormat.format("Exectution started for upsertCardInterestDetails: {0}",
				icustCardInterestListModel));
		try {
			return cardService.upsertDetails(icustCardInterestListModel);
		} catch (Exception e) {
			logger.error("Execption occoured while executing upsertCardInterestDetails", e);
			throw e;
		} finally {
			logger.info("Execution completed for upsertCardInterestDetails");
		}
	}
	
	@GetMapping(value = "/fetchCardInterestDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchCardInterestDetails() {
		logger.info("Exectution started for fetchCardInterestDetails");
		try {
			return cardService.fetchCardInterestDetails();
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchCardInterestDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchCardInterestDetails");
		}
	}
	
	@GetMapping(value = "/fetchCardInterestById", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchCardInterestById (@RequestParam(value = "cardAccountId") Long cardAccountId){
		logger.info("Exectution started for fetchCardInterestById");
		try {
			return cardService.fetchCardInterestById(cardAccountId);
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchCardInterestById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchCardInterestById");
		}
	}
	
}