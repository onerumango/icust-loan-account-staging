package com.rumango.controller;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rumango.service.IcustLoanTaskSummaryService;

@RestController
@RequestMapping("/loanAccount")
public class IcustLoanTaskSummaryController {
	private static final Logger logger = Logger.getLogger(IcustLoanTaskSummaryController.class);

	
	@Autowired
	IcustLoanTaskSummaryService summaryService;
	
	@GetMapping(value = "/fetchTaskSummaryInfo")
	public ResponseEntity<?> fetchTaskSummaryInfo(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchTaskSummaryInfo loanAccountId:{0}", loanAccountId));
		try {
			return summaryService.fetchTaskSummaryInfo(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchTaskSummaryInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchTaskSummaryInfo");
		}
	}
	
	@GetMapping(value = "/fetchTaskSummaryDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllAccountByAccountId(@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "customerId", required = false) String customerId,
			@RequestParam(value = "loanAccountId", required = false) Long loanAccountId,
			@RequestParam(value = "customerName", required = false) String customerName,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {

		logger.info(MessageFormat.format(
				"Exectution started for fetchTaskSummaryDetails status:: {0} page:: {1} size:: {2} customerName :: {3} ",
				status, page, size, customerName));
		try {
			return summaryService.fetchTaskSummaryDetails(status, page, size, customerId, loanAccountId, customerName);
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchTaskSummaryDetails ", e.getCause());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
		} finally {
			logger.info("Execution completed for fetchTaskSummaryDetails ");
		}
	}
}
