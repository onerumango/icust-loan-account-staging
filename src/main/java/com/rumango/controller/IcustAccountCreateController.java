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

import com.rumango.model.IcustAccountCreateModel;
import com.rumango.model.IcustOfferIssueModel;
import com.rumango.service.IcustAccountCreateService;

@RestController
@RequestMapping("/accountCreate-api")
public class IcustAccountCreateController {

	private static final Logger logger = Logger.getLogger(IcustAccountCreateController.class);
	
	@Autowired
	IcustAccountCreateService accountCreateService;
	
	@PostMapping(value="/upsertAccountCreate", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertAccountCreate(@RequestBody IcustAccountCreateModel icustAccountCreateModel){
		logger.info(MessageFormat.format("Execution Started for upsertAccountCreate icustAccountCreateModel:{0}", icustAccountCreateModel));
		try {
			return accountCreateService.upsertAccountCreate(icustAccountCreateModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertAccountCreate", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertAccountCreate");
		}
	}
	
	@GetMapping(value = "/fetchAccountCreateByLoanAccountId")
	public ResponseEntity<?> fetchAccountCreateByLoanAccountId(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchAccountCreateByLoanAccountId loanAccountId:{0}", loanAccountId));
		try {
			return accountCreateService.fetchAccountCreateByLoanAccountId(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchAccountCreateByLoanAccountId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchAccountCreateByLoanAccountId");
		}
	}
	
	@GetMapping(value = "/fetchAccountCreateById")
	public ResponseEntity<?> fetchAccountCreateById(@RequestParam(value="id" , required=false) Long id){
		logger.info(MessageFormat.format("Execution Started for fetchAccountCreateById id:{0}", id));
		try {
			return accountCreateService.fetchAccountCreateById(id);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchAccountCreateById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchAccountCreateById");
		}
	}
}
