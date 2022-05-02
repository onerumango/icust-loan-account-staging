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

import com.rumango.model.IcustCardApprovalModel;
import com.rumango.service.IcustCardApprovalDetailService;

@RestController
@RequestMapping("/card-apprstage-api")
public class IcustCardApprovalDetailController {

private static final Logger logger = Logger.getLogger(IcustSupervisorApprovalStageController.class);
	
	@Autowired
	IcustCardApprovalDetailService cardApprovalService;
	
	@PostMapping(value="/upsertApprovalDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertApprovalDetails(@RequestBody IcustCardApprovalModel icustApprovalModel){
		logger.info(MessageFormat.format("Execution Started for upsertApprovalDetails icustCardInfoModel:{0}", icustApprovalModel));
		try {
			return cardApprovalService.upsertCardApprovalDetails(icustApprovalModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertApprovalDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertApprovalDetails");
		}
	}
	
	@GetMapping(value = "/fetchApprovalDetails")
	public ResponseEntity<?> fetchApprovalDetails(@RequestParam(value="cardId" , required=false) Long cardId){
		logger.info(MessageFormat.format("Execution Started for fetchApprovalDetails cardId:{0}", cardId));
		try {
			return cardApprovalService.fetchApprovalDetails(cardId);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Execption occoured while executing fetchApprovalDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchApprovalDetails");
		}
	}
}
