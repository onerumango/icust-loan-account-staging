package com.rumango.controller;

import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rumango.model.IcustAdmissionDetailsModel;
import com.rumango.model.IcustAssetDetailsModel;
import com.rumango.model.IcustCollateralMasterModel;
import com.rumango.model.IcustFinancialDetailsModel;
import com.rumango.model.IcustGuarantorDetailsModel;
import com.rumango.model.IcustLoanAssessmentDetailsModel;
import com.rumango.model.IcustLoanInfoModel;
import com.rumango.model.IcustMandateMasterModel;
import com.rumango.model.IcustVehicleDetailsModel;
import com.rumango.service.IcustAdmissionService;
import com.rumango.service.IcustApplicationEntryStageService;
import com.rumango.service.IcustAssetService;
import com.rumango.service.IcustCollateralService;
import com.rumango.service.IcustFinancialDetailsService;
import com.rumango.service.IcustGuarantorService;
import com.rumango.service.IcustLoanService;
import com.rumango.service.IcustMandateService;
import com.rumango.service.IcustVehicleService;

@RestController
@RequestMapping("/application-entrystage-api")
public class IcustApplicationEntryStageController {
	private static final Logger logger = Logger.getLogger(IcustApplicationEntryStageController.class);
	
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
	@Autowired
	IcustApplicationEntryStageService entryStageService;
	
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
	
	@GetMapping(value = "/fetchLoanDetailsByLoanAccId")
	public ResponseEntity<?> fetchLoanDetailsByLoanAccId(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchLoanDetails loanAccountId:{0}", loanAccountId));
		try {
			return icustLoanService.fetchLoanDetailsByLoanAccId(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanDetailsByLoanAccId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchLoanDetailsByLoanAccId");
		}
	}
	
	@GetMapping(value = "/fetchLoanDetailsById")
	public ResponseEntity<?> fetchLoanInfo(@RequestParam(value="id" , required=false) Long id){
		logger.info("Execution Started for fetchLoanDetailsById");
		try {
			return icustLoanService.fetchLoanDetailsById(id);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanDetailsById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchLoanDetailsById");
		}
	}
	
	@PutMapping("/updateStatus")
	public ResponseEntity<?> updateStatusApproveOrReject(@RequestBody IcustLoanInfoModel icustLoanInfoModel) {
		logger.info("Exectution started for updateStatusApproveOrReject: " + icustLoanInfoModel.getLoanAccountId());
		try {
			return icustLoanService.updateStatusApproveOrReject(icustLoanInfoModel);
		} catch (Exception e) {
			logger.error("Execption occoured while executing updateStatusApproveOrReject", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for updateStatusApproveOrReject");
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
	
	@GetMapping(value = "/fetchAssetDetailsByLoanAccId")
	public ResponseEntity<?> fetchAssetDetailsByLoanAccId(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchAssetDetailsByLoanAccId loanAccountId:{0}", loanAccountId));
		try {
			return icustAssetService.fetchAssetDetailsByLoanAccId(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchAssetDetailsByLoanAccId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchAssetDetailsByLoanAccId");
		}
	}
	
	@GetMapping(value = "/fetchAssetInfoById")
	public ResponseEntity<?> fetchAssetInfo(@RequestParam(value="id" , required=false) Long id){
		logger.info("Execution Started for fetchAssetInfoById");
		try {
			return icustAssetService.fetchAssetInfoById(id);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchAssetInfoById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchAssetInfoById");
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
	
	@GetMapping(value = "/fetchVehicleDetailsByLoanAccId")
	public ResponseEntity<?> fetchVehicleDetails(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchVehicleDetailsByLoanAccId loanAccountId:{0}", loanAccountId));
		try {
			return icustVehicleService.fetchVehicleDetailsByLoanAccId(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchVehicleDetailsByLoanAccId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchVehicleDetailsByLoanAccId");
		}
	}
	
	@GetMapping(value = "/fetchVehicleInfoById")
	public ResponseEntity<?> fetchVehicleInfo(@RequestParam(value="id" , required=false) Long id){
		logger.info("Execution Started for fetchVehicleInfoById");
		try {
			return icustVehicleService.fetchVehicleInfoById(id);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchVehicleInfoById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchVehicleInfoById");
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
	
	@GetMapping(value = "/fetchAdmissionDetailsByLoanAccId")
	public ResponseEntity<?> fetchAdmissionDetails(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchAdmissionDetailsByLoanAccId loanId:{0}", loanAccountId));
		try {
			return icustAdmissionService.fetchAdmissionDetailsByLoanAccId(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchAdmissionDetailsByLoanAccId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchAdmissionDetailsByLoanAccId");
		}
	}
	
	@GetMapping(value = "/fetchAdmissionInfoById")
	public ResponseEntity<?> fetchAdmissionInfo(@RequestParam(value="id" , required=false) Long id){
		logger.info("Execution Started for fetchAdmissionInfoById");
		try {
			return icustAdmissionService.fetchAdmissionInfoById(id);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchAdmissionInfoById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchAdmissionInfoById");
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
	
	@GetMapping("/fetchMandateDetailsByLoanAccId")
	public ResponseEntity<?> fetchMandateDetailsByLoanAccId(@RequestParam(value = "loanAccountId", required = true) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchMandateDetailsByLoanAccId loanAccountId:{0}", loanAccountId));
		try {
			return icustMandateService.fetchMandateDetailsByLoanAccId(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchMandateDetailsByLoanAccId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchMandateDetailsByLoanAccId");
		}
	}
	
	@GetMapping(value = "/fetchMandateInfoById")
	public ResponseEntity<?> fetchMandateInfo(@RequestParam(value = "id", required = true) Long id){
		logger.info("Execution Started for fetchMandateInfoById");
		try {
			return icustMandateService.fetchMandateInfoById(id);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchMandateInfoById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchMandateInfoById");
		}
	}
	
	@PostMapping(value="/upsertfinancialDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertfinancialDetails(@RequestBody List<IcustFinancialDetailsModel> icustFinancialDetailsModel){
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
	
	@GetMapping(value = "/fetchFinancialDetailsByLoanAccId")
	public ResponseEntity<?> fetchFinancialDetails(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchFinancialDetailsByLoanAccId loanAccountId:{0}", loanAccountId));
		try {
			return financialDetailsService.fetchFinancialDetailsByLoanAccId(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchFinancialDetailsByLoanAccId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchFinancialDetailsByLoanAccId");
		}
	}
	
	@GetMapping(value = "/fetchFinancialInfoById")
	public ResponseEntity<?> fetchFinancialInfoById(@RequestParam(value="id" , required=false) Long id){
		logger.info(MessageFormat.format("Execution Started for fetchFinancialInfoById id:{0}", id));
		try {
			return financialDetailsService.fetchFinancialInfoById(id);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchFinancialInfoById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchFinancialInfoById");
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
	
	@GetMapping(value = "/fetchCollateralByLoanAccountId")
	public ResponseEntity<?> fetchCollateralByLoanAccountId(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchCollateralByLoanAccountId loanAccountId:{0}", loanAccountId));
		try {
			return icustCollateralService.fetchCollateralByLoanAccountId(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchCollateralByLoanAccountId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchCollateralByLoanAccountId");
		}
	}
	
	@GetMapping(value = "/fetchCollateralInfoById")
	public ResponseEntity<?> fetchCollateralInfoById(@RequestParam(value="id" , required=false) Long id){
		logger.info(MessageFormat.format("Execution Started for fetchCollateralByLoanAccountId id:{0}", id));
		try {
			return icustCollateralService.fetchCollateralInfoById(id);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchCollateralInfoById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchCollateralInfoById");
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
	
	@GetMapping(value = "/fetchGuarantorByLoanAccId")
	public ResponseEntity<?> fetchGuarantorByLoanAccId(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchGuarantorByLoanAccId loanAccountId:{0}", loanAccountId));
		try {
			return guarantorService.fetchGuarantorByLoanAccId(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchGuarantorByLoanAccId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchGuarantorByLoanAccId");
		}
	}
	
	@GetMapping(value = "/fetchAssessmentInfoByLoanAccId")
	public ResponseEntity<?> fetchAssessmentInfoByLoanAccId(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchAssessmentInfoByLoanAccId loanAccountId:{0}", loanAccountId));
		try {
			return icustLoanService.fetchAssessmentInfoByLoanAccId(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchAssessmentInfoByLoanAccId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchAssessmentInfoByLoanAccId");
		}
	}
	
	@PutMapping(value = "updateApprovedLoanAmount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateApprovedLoanAmount( @RequestBody IcustLoanAssessmentDetailsModel assessmentModel ) {
		logger.info("Exectution started for updateApprovedLoanAmount: " + assessmentModel);
		try {
			logger.info(MessageFormat.format("Update approved loan amount for object: {0}", assessmentModel));
			return icustLoanService.updateApprovedLoanAmount(assessmentModel);
		} catch (Exception e) {
			logger.error("Execption occoured while executing updateApprovedLoanAmount", e);
			throw e;
		} finally {
			logger.info("Execution completed for updateApprovedLoanAmount");
		}
	}
	
	@GetMapping(value = "/fetchOfferIssueInfoByLoanAccId")
	public ResponseEntity<?> fetchOfferIssueInfoByLoanAccId(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchOfferIssueInfoByLoanAccId loanAccountId:{0}", loanAccountId));
		try {
			return icustLoanService.fetchOfferIssueInfoByLoanAccId(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchOfferIssueInfoByLoanAccId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchOfferIssueInfoByLoanAccId");
		}
	}
	
	@GetMapping(value = "/fetchSummaryInfo")
	public ResponseEntity<?> fetchSummaryInfo(@RequestParam(value="loanAccountId" , required=false) Long loanAccountId){
		logger.info(MessageFormat.format("Execution Started for fetchSummaryInfo loanAccountId:{0}", loanAccountId));
		try {
			return entryStageService.fetchSummaryInfo(loanAccountId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchSummaryInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchSummaryInfo");
		}
	}
}
