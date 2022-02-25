package com.rumango.controller;

import java.text.MessageFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rumango.model.IcustLoanAssessmentDetailsModel;
import com.rumango.service.IcustLoanAssessmentService;

@RestController
@RequestMapping("loanAssessment")
@CrossOrigin
public class IcustLoanAssessmentController {

	private static final Logger logger = LogManager.getLogger(IcustLoanAssessmentController.class);
	
	@Autowired
	private IcustLoanAssessmentService assessmentService;
	
	@PostMapping(value = "/upsertDetails",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertLoanAssessment(@RequestBody IcustLoanAssessmentDetailsModel loanAssessmentDetailsModel) {
		logger.info(MessageFormat.format("Exectution started for upsertLoanAssessment: {0}",loanAssessmentDetailsModel));
		try {
			return assessmentService.upsertLoanAssessmentDetails(loanAssessmentDetailsModel);			
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertLoanAssessment", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertLoanAssessment");
		}
	}
	
	@GetMapping(value = "/fetchLoanAssessmentDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchLoanAssessmentDetails() {
		logger.info("Exectution started for fetchLoanAssessmentDetails");
		try {
			return assessmentService.fetchLoanAssessmentDetails();
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanAssessmentDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchLoanAssessmentDetails");
		}
	}
	
	@GetMapping(value = "/fetchLoanAssessmentById", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchLoanAssessmentById (@RequestParam(value = "loanAccountId") Long loanAccountId){
		logger.info("Exectution started for fetchLoanAssessmentById");
		try {
			return assessmentService.fetchLoanAssessmentById(loanAccountId);
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanAssessmentById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchLoanAssessmentById");
		}
	}
}
