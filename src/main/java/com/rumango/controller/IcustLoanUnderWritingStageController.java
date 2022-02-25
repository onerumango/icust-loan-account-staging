package com.rumango.controller;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rumango.model.IcustLoanCreditRatingDetailsModel;
import com.rumango.model.IcustLoanLegalOpinionModel;
import com.rumango.model.IcustLoanValuationOfAssetModel;
import com.rumango.service.IcustLoanCreditRatingService;
import com.rumango.service.IcustLoanLegalOpinionService;
import com.rumango.service.IcustLoanValuationOfAssetService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("underWritingStage")
@Slf4j
public class IcustLoanUnderWritingStageController {
	@Autowired
	private IcustLoanCreditRatingService creditRatingService;

	@Autowired
	private IcustLoanValuationOfAssetService loanValuationOfAssetService;

	@Autowired
	private IcustLoanLegalOpinionService legalOpinionService;

	@PostMapping(value = "/saveOrUpdateCreditRating", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveOrUpdateCreditRating(
			@RequestBody IcustLoanCreditRatingDetailsModel creditRatingModel) {
		log.info(MessageFormat.format("Exectution started for saveOrUpdateCreditRating ", creditRatingModel));
		try {
			return creditRatingService.saveOrUpdateCreditRating(creditRatingModel);
		} catch (Exception e) {
			log.error("Execption occoured while executing saveOrUpdateCreditRating", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			log.info("Execution completed for saveOrUpdateCreditRating");
		}
	}

	@GetMapping(value = "/getCreditRatingByLoanId/{loanAccId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCreditRatingsByLoanAccountId(@PathVariable("loanAccId") Long loanAccId) {
		log.info(MessageFormat.format("Exectution started for getCreditRatingsByLoanAccountId {1}", loanAccId));
		try {
			return creditRatingService.getCreditRatingsByLoanAccountId(loanAccId);
		} catch (Exception e) {
			log.error("Execption occoured while executing getCreditRatingsByLoanAccountId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			log.info("Execution completed for getCreditRatingsByLoanAccountId");
		}
	}

	@GetMapping(value = "/getCreditRatingByCreditRatingId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCreditRatingsByCreditRatingId(@PathVariable("id") Long id) {
		log.info(MessageFormat.format("Exectution started for getCreditRatingsByCreditRatingId {1}", id));
		try {
			return creditRatingService.getCreditRatingsById(id);
		} catch (Exception e) {
			log.error("Execption occoured while executing getCreditRatingsByCreditRatingId :: ", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			log.info("Execution completed for getCreditRatingsByCreditRatingId");
		}
	}

	@PostMapping(value = "/saveOrValuationOfAsset", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveOrValuationOfAsset(@RequestBody IcustLoanValuationOfAssetModel valuationOfAsset) {
		log.info(MessageFormat.format("Exectution started for saveOrValuationOfAsset {1}", valuationOfAsset));
		try {
			return loanValuationOfAssetService.saveOrValuationOfAsset(valuationOfAsset);
		} catch (Exception e) {
			log.error("Execption occoured while executing saveOrValuationOfAsset", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			log.info("Execution completed for saveOrValuationOfAsset");
		}
	}

	@GetMapping(value = "/getValOfAsstByLoanId/{loanAccId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getValuationOfAssetByLoanAccountId(@PathVariable("loanAccId") Long loanAccId) {
		log.info(MessageFormat.format("Exectution started for getValuationOfAssetByLoanAccountId {1}", loanAccId));
		try {
			return loanValuationOfAssetService.getValuationOfAssetByLoanAccountId(loanAccId);
		} catch (Exception e) {
			log.error("Execption occoured while executing getValuationOfAssetByLoanAccountId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			log.info("Execution completed for getValuationOfAssetByLoanAccountId");
		}
	}

	@GetMapping(value = "/getValuationOfAssetById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getValuationOfAssetById(@PathVariable("id") Long id) {
		log.info(MessageFormat.format("Exectution started for getValuationOfAssetById {1}", id));
		try {
			return loanValuationOfAssetService.getValuationOfAssetById(id);
		} catch (Exception e) {
			log.error("Execption occoured while executing getValuationOfAssetById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			log.info("Execution completed for getValuationOfAssetById");
		}
	}

	@PostMapping(value = "/saveOrUpdateLegalOpinion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveOrUpdateLegalOpinion(@RequestBody IcustLoanLegalOpinionModel legalOpinionModel) {
		log.info(MessageFormat.format("Exectution started for saveOrUpdateLegalOpinion {1}", legalOpinionModel));
		try {
			return legalOpinionService.saveOrUpdateLegalOpinion(legalOpinionModel);
		} catch (Exception e) {
			log.error("Execption occoured while executing saveOrUpdateLegalOpinion", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			log.info("Execution completed for saveOrUpdateLegalOpinion");
		}
	}

	@GetMapping(value = "/getLegalOpinionByLoanId/{loanAccId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getLegalOpinionByLoanAccountId(@PathVariable("loanAccId") Long loanAccId) {
		log.info(MessageFormat.format("Exectution started for getLegalOpinionByLoanAccountId {1}", loanAccId));
		try {
			return legalOpinionService.getLegalOpinionByLoanAccountId(loanAccId);
		} catch (Exception e) {
			log.error("Execption occoured while executing getLegalOpinionByLoanAccountId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			log.info("Execution completed for getLegalOpinionByLoanAccountId");
		}
	}

	@GetMapping(value = "/getLegalOpinionById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getLegalOpinionById(@PathVariable("id") Long id) {
		log.info(MessageFormat.format("Exectution started for getLegalOpinionById {1}", id));
		try {
			return legalOpinionService.getLegalOpinionById(id);
		} catch (Exception e) {
			log.error("Execption occoured while executing getLegalOpinionById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			log.info("Execution completed for getLegalOpinionById");
		}
	}

}
