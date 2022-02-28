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

import com.rumango.model.IcustOfferIssueModel;
import com.rumango.model.IcustPostOfferAmendmentModel;
import com.rumango.service.IcustPostOfferAmendmentService;

@RestController
@RequestMapping(value = "/post-offer-amendment-api")
public class IcustPostOfferAmendmentController {
	
	private static final Logger logger = Logger.getLogger(IcustPostOfferAmendmentController.class);

	@Autowired
	IcustPostOfferAmendmentService service;

	@PostMapping(value="/upsertPostOfferAmendment", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertPostOfferAmendment(@RequestBody IcustPostOfferAmendmentModel icustPostOfferAmendmentModel){
		logger.info(MessageFormat.format("Execution Started for upsertOfferIssue icustLoanInfoModel:{0}", icustPostOfferAmendmentModel));
		try {
			return service.upsertPostOfferAmendment(icustPostOfferAmendmentModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertOfferIssue", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertOfferIssue");
		}
	}
	
	@GetMapping(value="/fetchPostOfferAmendmentDetails")
	public ResponseEntity<?> fetchPostOfferAmendmentDetails(){
		logger.info("Exectution starts for fetchPostOfferAmendmentDetails");
		try {
			return service.fetchPostOfferAmendmentDetails();
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception occure while fetchPostOfferAmendmentDetails",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchPostOfferAmendmentDetails");
		}
	}
	
	@GetMapping(value="/fetchPostOfferAmendmentDetailsById")
	public ResponseEntity<?> fetchPostOfferAmendmentDetailsById(@RequestParam(value="loanAccountId", required=false) Long loanAccountId){
		logger.info("Execution starts for fetchLoanRepaymentDetailById");
		try {
			return service.fetchPostOfferAmendmentDetailsById(loanAccountId);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Execption occure while fetchPostOfferAmendmentDetailsById",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}finally {
			logger.info("Exectuiion complete for fetchPostOfferAmendmentDetailsById");
		}
	}
}
