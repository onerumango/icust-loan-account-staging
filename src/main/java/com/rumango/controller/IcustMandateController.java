package com.rumango.controller;

import java.text.MessageFormat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rumango.model.IcustMandateMasterModel;
import com.rumango.service.IcustMandateService;

@RestController
@RequestMapping("/madate-api")
public class IcustMandateController {
	private static final Logger logger = LogManager.getLogger(IcustMandateController.class);

	@Autowired
	IcustMandateService icustMandateService;

	@PostMapping("/upsertMandateDetails")
	public ResponseEntity<?> upsertMandateDetails(@RequestBody IcustMandateMasterModel icustMandateMasterModel) {
		logger.info(MessageFormat.format("Execution Started for upsertMandateDetails icustMandateMasterModel:{0}",
				icustMandateMasterModel));
		try {
			return icustMandateService.upsertMandateDetails(icustMandateMasterModel);
		} catch (Exception e) {
			logger.error("Execption occoured while executing upsertAssetDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertAssetDetails");
		}
	}
	
	@GetMapping("/fetchMandateDetails")
	public ResponseEntity<?> fetchMandateDetails(@RequestParam(value = "loanAccountId", required = true) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchMandateDetails loanAccountId:{0}", loanAccountId));
		try {
			return icustMandateService.fetchMandateDetails(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchMandateDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchMandateDetails");
		}
	}
	
	@GetMapping(value = "/fetchMandateInfo")
	public ResponseEntity<?> fetchAssetInfo(){
		logger.info("Execution Started for fetchMandateInfo");
		try {
			return icustMandateService.fetchMandateInfo();
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchMandateInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchMandateInfo");
		}
	}
}
