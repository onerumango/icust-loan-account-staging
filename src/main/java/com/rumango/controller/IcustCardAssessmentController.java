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

import com.rumango.model.IcustCardAssessmentModel;
import com.rumango.service.IcustCardAssessmentService;

@RestController
@RequestMapping("cardAssessment")
@CrossOrigin
public class IcustCardAssessmentController {

private static final Logger logger = LogManager.getLogger(IcustCardAssessmentController.class);
	
	@Autowired
	private IcustCardAssessmentService assessmentService;
	
	@PostMapping(value = "/upsertDetails",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertCardAssessment(@RequestBody IcustCardAssessmentModel cardAssessmentModel) {
		logger.info(MessageFormat.format("Exectution started for upsertCardAssessment: {0}",cardAssessmentModel));
		try {
			return assessmentService.upsertCardAssessmentDetails(cardAssessmentModel);			
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertCardAssessment", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertCardAssessment");
		}
	}
	
	@GetMapping(value = "/fetchCardAssessmentDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchCardAssessmentDetails() {
		logger.info("Exectution started for fetchCardAssessmentDetails");
		try {
			return assessmentService.fetchCardAssessmentDetails();
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchCardAssessmentDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchCardAssessmentDetails");
		}
	}
	
	@GetMapping(value = "/fetchCardAssessmentById", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchCardAssessmentById (@RequestParam(value = "cardAccountId") Long cardAccountId){
		logger.info("Exectution started for fetchCardAssessmentById");
		try {
			return assessmentService.fetchCardAssessmentById(cardAccountId);
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchCardAssessmentById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchCardAssessmentById");
		}
	}
	
}
