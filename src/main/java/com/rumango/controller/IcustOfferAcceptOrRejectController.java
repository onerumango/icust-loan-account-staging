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

import com.rumango.model.IcustLoanOfferAcceptOrRejectModel;
import com.rumango.service.IcustOfferAcceptOrRejectService;

@RestController
@RequestMapping(value = "offer-acceptOrReject-api")
public class IcustOfferAcceptOrRejectController {

	private static final Logger logger= LogManager.getLogger(IcustOfferAcceptOrRejectController.class);
	
	@Autowired
	IcustOfferAcceptOrRejectService service;
	
	@PostMapping(value = "upsertOfferAcceptOrReject", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertOfferAcceptOrReject(@RequestBody IcustLoanOfferAcceptOrRejectModel icustLoanOfferAcceptOrRejectModel){
		logger.info(MessageFormat.format("Execution Started for upsertOfferIssue icustLoanOfferAcceptOrRejectModel:{0}", icustLoanOfferAcceptOrRejectModel));
		try {
			return service.upsertOfferAcceptOrReject(icustLoanOfferAcceptOrRejectModel);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Execption occoured while executing upsertOfferAcceptOrReject", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}finally {
			logger.info("Execution completed for upsertOfferIssue");

		}
		
	}
	
	@GetMapping(value = "fetchOfferAcceptOrRejectDetails")
	public ResponseEntity<?> fetchOfferAcceptOrRejectDetails(){
		logger.info("Exectution starts for fetchOfferAcceptOrRejectDetails");
		try {
			return service.fetchOfferAcceptOrRejectDetails();
		}catch (Exception e) {
			// TODO: handle exception
			logger.info("Exeception occure while fetchOfferAcceptOrRejectDetails");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}finally {
			logger.info("Execution completed for fetchOfferAcceptOrRejectDetails");

		}
	}
	
	@GetMapping(value = "fetchOfferAcceptOrRejectDetailsById")
	public ResponseEntity<?> fetchOfferAcceptOrRejectDetailsById
	(@RequestParam (value="loanAccountId", required=false) Long loanAccountId){
		logger.info("Execution starts for fetchOfferAcceptOrRejectDetailsById");
		try {
			return service.fetchOfferAcceptOrRejectDetailsById(loanAccountId);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Execption occure while fetchOfferAcceptOrRejectDetailsById",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}finally {
			logger.info("Exectuiion complete for fetchOfferAcceptOrRejectDetailsById");
		}
	}
}
