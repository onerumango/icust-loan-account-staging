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

import com.rumango.model.IcustVehicleDetailsModel;
import com.rumango.service.IcustVehicleService;

@RestController
@RequestMapping("/vehicle-api")
public class IcustVehicleController {
	private static final Logger logger = LogManager.getLogger(IcustVehicleController.class);

	@Autowired
	IcustVehicleService icustVehicleService;
	
	@PostMapping(value="/upsertVehicleDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertVehicleDetails(@RequestBody IcustVehicleDetailsModel icustVehicleDetailsModel){
		logger.info(MessageFormat.format("Execution Started for upsertVehicleDetails icustVehicleDetailsModel:{0}", icustVehicleDetailsModel));
		try {
			return icustVehicleService.upsertVehicleDetails(icustVehicleDetailsModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertVehicleDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertVehicleDetails");
		}
	}
	
	@GetMapping(value = "/fetchVehicleDetails")
	public ResponseEntity<?> fetchAssetDetails(@RequestParam(value="loanId" , required=false) Long loanId){
		logger.info(MessageFormat.format("Execution Started for fetchVehicleDetails loanId:{0}", loanId));
		try {
			return icustVehicleService.fetchVehicleDetails(loanId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchVehicleDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchVehicleDetails");
		}
	}
	
	@GetMapping(value = "/fetchVehicleInfo")
	public ResponseEntity<?> fetchVehicleInfo(){
		logger.info("Execution Started for fetchVehicleInfo");
		try {
			return icustVehicleService.fetchVehicleInfo();
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchVehicleInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchVehicleInfo");
		}
	}
}
