package com.rumango.controller;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rumango.service.IcustApplicationEnrichmentService;

@RestController
@RequestMapping("/application-enrichment-api")
public class IcustApplicationEnrichmentController {
	private static final Logger logger = Logger.getLogger(IcustApplicationEnrichmentController.class);

	@Autowired
	IcustApplicationEnrichmentService enrichmentService;
	
	@GetMapping(value = "/fetchSummaryInfo")
	public ResponseEntity<?> fetchSummaryInfo(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchSummaryInfo loanAccountId:{0}", loanAccountId));
		try {
			return enrichmentService.fetchSummaryInfo(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchSummaryInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchSummaryInfo");
		}
	}
}
