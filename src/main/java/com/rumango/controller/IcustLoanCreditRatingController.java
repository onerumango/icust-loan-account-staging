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
import com.rumango.service.IcustLoanCreditRatingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("icLoanCreditRating")
@Slf4j
public class IcustLoanCreditRatingController {

	@Autowired
	private IcustLoanCreditRatingService creditRatingService;

	@PostMapping(value = "/saveOrUpdateCreditRating", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveOrUpdateCreditRating(@RequestBody IcustLoanCreditRatingDetailsModel creditRatingModel) {
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

	@GetMapping(value = "/getAllCreditRating", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllCreditRatings() {
		log.info("Exectution started for getAllCreditRatings ");
		try {
			return creditRatingService.getAllCreditRatings();
		} catch (Exception e) {
			log.error("Execption occoured while executing getAllCreditRatings", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			log.info("Execution completed for getAllCreditRatings");
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

}
