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

import com.rumango.model.IcustLoanInfoModel;
import com.rumango.model.IcustOfferIssueModel;
import com.rumango.service.IcustOfferIssueService;

@RestController
@RequestMapping("/offer-issueStage-api")
public class IcustOfferIssueStageController {
	private static final Logger logger = Logger.getLogger(IcustOfferIssueStageController.class);

	@Autowired
	IcustOfferIssueService icustOfferIssueService;
	
	@PostMapping(value="/upsertOfferIssue", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertOfferIssue(@RequestBody IcustOfferIssueModel icustOfferIssueModel){
		logger.info(MessageFormat.format("Execution Started for upsertOfferIssue icustLoanInfoModel:{0}", icustOfferIssueModel));
		try {
			return icustOfferIssueService.upsertOfferIssue(icustOfferIssueModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertOfferIssue", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertOfferIssue");
		}
	}
	
	@GetMapping(value = "/fetchOfferIssueByLoanAccountId")
	public ResponseEntity<?> fetchOfferIssueByLoanAccountId(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchOfferIssueByLoanAccountId loanAccountId:{0}", loanAccountId));
		try {
			return icustOfferIssueService.fetchOfferIssueByLoanAccountId(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchOfferIssueByLoanAccountId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchOfferIssueByLoanAccountId");
		}
	}
	
	@GetMapping(value = "/fetchOfferIssueById")
	public ResponseEntity<?> fetchOfferIssueById(@RequestParam(value="id" , required=false) Long id){
		logger.info(MessageFormat.format("Execution Started for fetchOfferIssueById id:{0}", id));
		try {
			return icustOfferIssueService.fetchOfferIssueById(id);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchOfferIssueById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchOfferIssueById");
		}
	}
}
