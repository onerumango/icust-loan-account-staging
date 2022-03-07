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

import com.rumango.model.IcustApprovalDetailsModel;
import com.rumango.service.IcustApprovalDetailsService;

@RestController
@RequestMapping("/supervisor-apprstage-api")
public class IcustSupervisorApprovalStageController {

	private static final Logger logger = Logger.getLogger(IcustSupervisorApprovalStageController.class);
	
	@Autowired
	IcustApprovalDetailsService approvalDetailsService;
	
	@PostMapping(value="/upsertApprovalDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertApprovalDetails(@RequestBody IcustApprovalDetailsModel icustApprovalDetailsModel){
		logger.info(MessageFormat.format("Execution Started for upsertApprovalDetails icustLoanInfoModel:{0}", icustApprovalDetailsModel));
		try {
			return approvalDetailsService.upsertApprovalDetails(icustApprovalDetailsModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertApprovalDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertApprovalDetails");
		}
	}
	
	@GetMapping(value = "/fetchApprovalDetails")
	public ResponseEntity<?> fetchApprovalDetails(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchApprovalDetails loanAccountId:{0}", loanAccountId));
		try {
			return approvalDetailsService.fetchApprovalDetails(loanAccountId);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Execption occoured while executing fetchApprovalDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchApprovalDetails");
		}
	}
}
