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

import com.rumango.model.IcustAdmissionDetailsModel;
import com.rumango.model.IcustAssetDetailsModel;
import com.rumango.service.IcustAdmissionService;

@RestController
@RequestMapping("/admission-api")
public class IcustAdmissionController {
	private static final Logger logger = LogManager.getLogger(IcustAdmissionController.class);

	@Autowired
	IcustAdmissionService icustAdmissionService;


	@PostMapping(value="/upsertAdmissionDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertAdmissionDetails(@RequestBody IcustAdmissionDetailsModel icustAdmissionDetailsModel){
		logger.info(MessageFormat.format("Execution Started for upsertAdmissionDetails icustAdmissionDetailsModel:{0}", icustAdmissionDetailsModel));
		try {
			return icustAdmissionService.upsertAdmissionDetails(icustAdmissionDetailsModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertAdmissionDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertAdmissionDetails");
		}
	}
	
	@GetMapping(value = "/fetchAdmissionDetails")
	public ResponseEntity<?> fetchAdmissionDetails(@RequestParam(value="loanId" , required=false) Long loanId){
		logger.info(MessageFormat.format("Execution Started for fetchAdmissionDetails loanId:{0}", loanId));
		try {
			return icustAdmissionService.fetchAdmissionDetails(loanId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchAdmissionDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchAdmissionDetails");
		}
	}
	
	@GetMapping(value = "/fetchAdmissionInfo")
	public ResponseEntity<?> fetchAssetInfo(){
		logger.info("Execution Started for fetchAdmissionInfo");
		try {
			return icustAdmissionService.fetchAdmissionInfo();
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchAdmissionInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchAdmissionInfo");
		}
	}
}
