package com.rumango.controller;

import java.text.MessageFormat;

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

import com.rumango.model.IcustCardInitiationModel;
import com.rumango.service.IcustCardInitiationService;

@RestController
@RequestMapping("/cardInitiation-api")
public class IcustCardInitiationController {
	private static final Logger logger = Logger.getLogger(IcustCardInitiationController.class);


	@Autowired
	IcustCardInitiationService cardInitiationService;
	
	@PostMapping(value="/upsertCardInitiationDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertCardInitiationDetails(@RequestBody IcustCardInitiationModel cardInitiationModel){
		logger.info(MessageFormat.format("Execution Started for upsertCardInitiationDetails icustLoanInfoModel:{0}", cardInitiationModel));
		try {
			return cardInitiationService.upsertCardInitiationDetails(cardInitiationModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertCardInitiationDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertCardInitiationDetails");
		}
	}
	
	@GetMapping(value = "/fetchCardInitiationByCardId" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchCardInitiationByCardAccId(@RequestParam(value="cardId" , required=false) Long cardId){
		logger.info(MessageFormat.format("Execution Started for fetchCardInitiationByCardAccId cardId:{0}", cardId));
		try {
			return cardInitiationService.fetchCardInitiationByCardId(cardId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchCardInitiationByCardAccId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchCardInitiationByCardAccId");
		}
	}
	
	@PutMapping("/updateStatus")
	public ResponseEntity<?> updateStatusApproveOrReject(@RequestBody IcustCardInitiationModel cardInitiationModel) {
		logger.info("Exectution started for updateStatusApproveOrReject: " + cardInitiationModel.getCardId());
		try {
			return cardInitiationService.updateStatusApproveOrReject(cardInitiationModel);
		} catch (Exception e) {
			logger.error("Execption occoured while executing updateStatusApproveOrReject", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for updateStatusApproveOrReject");
		}
	}
}
