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

import com.rumango.model.IcustAssetDetailsModel;
import com.rumango.service.IcustAssetService;

@RestController
@RequestMapping("/asset-api")
public class IcustAssetController {
	private static final Logger logger = LogManager.getLogger(IcustAssetController.class);

	@Autowired
	IcustAssetService icustAssetService;
	
	@PostMapping(value="/upsertAssetDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertAssetDetails(@RequestBody IcustAssetDetailsModel icustAssetDetailsModel){
		logger.info(MessageFormat.format("Execution Started for upsertAssetDetails icustAssetDetailsModel:{0}", icustAssetDetailsModel));
		try {
			return icustAssetService.upsertAssetDetails(icustAssetDetailsModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertAssetDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertAssetDetails");
		}
	}
	
	@GetMapping(value = "/fetchAssetDetails")
	public ResponseEntity<?> fetchAssetDetails(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchAssetDetails loanAccountId:{0}", loanAccountId));
		try {
			return icustAssetService.fetchAssetDetails(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchAssetDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchAssetDetails");
		}
	}
	
	@GetMapping(value = "/fetchAssetInfo")
	public ResponseEntity<?> fetchAssetInfo(){
		logger.info("Execution Started for fetchAssetInfo");
		try {
			return icustAssetService.fetchAssetInfo();
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchAssetInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchAssetInfo");
		}
	}
}
