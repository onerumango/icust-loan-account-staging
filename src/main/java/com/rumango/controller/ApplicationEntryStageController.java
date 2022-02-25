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

import com.rumango.model.IcustAdmissionDetailsModel;
import com.rumango.model.IcustAssetDetailsModel;
import com.rumango.model.IcustCollateralMasterModel;
import com.rumango.model.IcustFinancialDetailsModel;
import com.rumango.model.IcustGuarantorDetailsModel;
import com.rumango.model.IcustLoanInfoModel;
import com.rumango.model.IcustMandateMasterModel;
import com.rumango.model.IcustVehicleDetailsModel;
import com.rumango.service.IcustAdmissionService;
import com.rumango.service.IcustAssetService;
import com.rumango.service.IcustCollateralService;
import com.rumango.service.IcustFinancialDetailsService;
import com.rumango.service.IcustGuarantorService;
import com.rumango.service.IcustLoanService;
import com.rumango.service.IcustMandateService;
import com.rumango.service.IcustVehicleService;

@RestController
@RequestMapping("application-entrystage-api")
public class ApplicationEntryStageController {
	private static final Logger logger = Logger.getLogger(ApplicationEntryStageController.class);
	
	@Autowired
	private IcustLoanService icustLoanService;
	@Autowired
	IcustAssetService icustAssetService;
	@Autowired
	IcustVehicleService icustVehicleService;
	@Autowired
	IcustAdmissionService icustAdmissionService;
	@Autowired
	IcustMandateService icustMandateService;
	@Autowired
	IcustFinancialDetailsService financialDetailsService;
	@Autowired
	IcustCollateralService icustCollateralService;
	@Autowired
	IcustGuarantorService guarantorService;
	
	@PostMapping(value="/upsertLoanDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertLoanDetails(@RequestBody IcustLoanInfoModel icustLoanInfoModel){
		logger.info(MessageFormat.format("Execution Started for upsertLoanDetails icustLoanInfoModel:{0}", icustLoanInfoModel));
		try {
			return icustLoanService.upsertLoanDetails(icustLoanInfoModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertLoanDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertLoanDetails");
		}
	}
	
	@GetMapping(value = "/fetchLoanDetails")
	public ResponseEntity<?> fetchLoanDetails(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchLoanDetails loanAccountId:{0}", loanAccountId));
		try {
			return icustLoanService.fetchLoanDetails(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchLoanDetails");
		}
	}
	
	@GetMapping(value = "/fetchLoanInfo")
	public ResponseEntity<?> fetchLoanInfo(){
		logger.info("Execution Started for fetchLoanInfo");
		try {
			return icustLoanService.fetchLoanInfo();
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchLoanInfo");
		}
	}
	
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
	public ResponseEntity<?> fetchVehicleDetails(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchVehicleDetails loanAccountId:{0}", loanAccountId));
		try {
			return icustVehicleService.fetchVehicleDetails(loanAccountId);
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
	public ResponseEntity<?> fetchAdmissionDetails(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchAdmissionDetails loanId:{0}", loanAccountId));
		try {
			return icustAdmissionService.fetchAdmissionDetails(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchAdmissionDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchAdmissionDetails");
		}
	}
	
	@GetMapping(value = "/fetchAdmissionInfo")
	public ResponseEntity<?> fetchAdmissionInfo(){
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
	public ResponseEntity<?> fetchMandateInfo(){
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
	
	@PostMapping(value="/upsertfinancialDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertfinancialDetails(@RequestBody IcustFinancialDetailsModel icustFinancialDetailsModel){
		logger.info(MessageFormat.format("Execution Started for upsertfinancialDetails icustFinancialDetailsModel:{0}", icustFinancialDetailsModel));
		try {
			return financialDetailsService.upsertFinancialDetails(icustFinancialDetailsModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertfinancialDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertfinancialDetails");
		}
	}
	
	@GetMapping(value = "/fetchFinancialDetails")
	public ResponseEntity<?> fetchFinancialDetails(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchFinancialDetails loanAccountId:{0}", loanAccountId));
		try {
			return financialDetailsService.fetchFinancialDetails(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchFinancialDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchFinancialDetails");
		}
	}
	
	@PostMapping(value="/upsertCollateralDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertCollateralDetails(@RequestBody IcustCollateralMasterModel icustCollateralMasterModel){
		logger.info(MessageFormat.format("Execution Started for upsertCollateralDetails icustCollateralMasterModel:{0}", icustCollateralMasterModel));
		try {
			return icustCollateralService.upsertCollateralDetails(icustCollateralMasterModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertCollateralDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertCollateralDetails");
		}
	}
	
	@GetMapping(value = "/fetchCollateralDetails")
	public ResponseEntity<?> fetchCollateralDetails(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchCollateralDetails loanAccountId:{0}", loanAccountId));
		try {
			return icustCollateralService.fetchCollateralDetails(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchCollateralDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchCollateralDetails");
		}
	}
	
	@PostMapping(value="/upsertGuarantorDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertGuarantorDetails(@RequestBody IcustGuarantorDetailsModel icustGuarantorDetailsModel){
		logger.info(MessageFormat.format("Execution Started for upsertGuarantorDetails icustGuarantorDetailsModel:{0}", icustGuarantorDetailsModel));
		try {
			return guarantorService.upsertGuarantorDetails(icustGuarantorDetailsModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertGuarantorDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertGuarantorDetails");
		}
	}
	
	@GetMapping(value = "/fetchGuarantorDetails")
	public ResponseEntity<?> fetchGuarantorDetails(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchGuarantorDetails loanAccountId:{0}", loanAccountId));
		try {
			return guarantorService.fetchGuarantorDetails(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchGuarantorDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchGuarantorDetails");
		}
	}
}
