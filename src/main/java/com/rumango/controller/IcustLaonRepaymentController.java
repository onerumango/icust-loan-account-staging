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

import com.rumango.model.IcustLoanRepaymentModel;
import com.rumango.service.IcustLoanRepaymentService;

@RestController
@RequestMapping(value ="/repayment-api")
public class IcustLaonRepaymentController {
	
	private static final Logger logger= LogManager.getLogger(IcustLaonRepaymentController.class);

	@Autowired
	IcustLoanRepaymentService service;
	
	@PostMapping(value="/upsertLoanRepaymentDeatils", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertLoanRepaymentDeatils(@RequestBody IcustLoanRepaymentModel icustLoanRepaymentModel){
		logger.info(MessageFormat.format("Execution Started for upsertLoanDisbursementDetails icustLoanRepaymentModel:{0}",icustLoanRepaymentModel));
		
		try {
			return service.upsertLoanRepaymentDeatils(icustLoanRepaymentModel);
		}catch(Exception e){
			logger.error("Execption occoured while executing upsertingLoanRepaymentDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}finally{
			logger.info("Execution complete for upsertingLoanRepaymentDetails");
		}
		
	}
	
	@GetMapping(value="/fetchLoanRepaymentDetails")
	public ResponseEntity<?> fetchLoanRepaymentDetails(){
		logger.info("Exectution starts for fetchLoanRepaymentDetails");
		try {
			return service.fetchLoanRepaymentDetails();
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception occure while fetchLoanRepaymentDetails",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchLoanRepaymentDetails");
		}
	}
	
	@GetMapping(value="/fetchLoanRepaymentDetailById")
	public ResponseEntity<?> fetchLoanRepaymentDetailById(@RequestParam(value="loanAccountId", required=false) Long loanAccountId){
		logger.info("Execution starts for fetchLoanRepaymentDetailById");
		try {
			return service.fetchLoanRepaymentDetailById(loanAccountId);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Execption occure while fetchLoanRepaymentDetailById",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}finally {
			logger.info("Exectuiion complete for fetchLoanRepaymentDetailById");
		}
	}
}
