package com.rumango.controller;

import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rumango.model.IcustCardPreferencesModel;
import com.rumango.service.IcustCardPreferencesService;

@RestController
@RequestMapping("/cardPreference-api")
public class IcustCardPreferencesController {
	private static final Logger logger = Logger.getLogger(IcustCardPreferencesController.class);
	
	@Autowired
	IcustCardPreferencesService cardPreferencesService;

	
	@PostMapping(value="/upsertCardPreferenceDetails", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertCardPreferenceDetails(@RequestBody List<IcustCardPreferencesModel> cardPreferencesModel){
		logger.info(MessageFormat.format("Execution Started for upsertCardPreferenceDetails cardPreferencesModel:{0}", cardPreferencesModel));
		try {
			return cardPreferencesService.upsertCardPreferenceDetails(cardPreferencesModel);
		}catch (Exception e) {
			logger.error("Execption occoured while executing upsertCardPreferenceDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for upsertCardPreferenceDetails");
		}
	}
	
	@GetMapping(value = "/fetchCardPreferenceByCardId" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchCardPreferenceByCardId(@RequestParam(value="cardId" , required=false) Long cardId){
		logger.info(MessageFormat.format("Execution Started for fetchCardPreferenceByCardAccId cardId:{0}", cardId));
		try {
			return cardPreferencesService.fetchCardPreferenceByCardId(cardId);
		}catch (Exception e) {
			logger.error("Execption occoured while executing fetchCardPreferenceByCardAccId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchCardPreferenceByCardAccId");
		}
	}
	
	@DeleteMapping(value="/deleteCardPreference", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteCardPreference (@RequestParam(value = "id") Long id){
		try {
			return cardPreferencesService.deleteCardPreference(id);
		}catch(Exception e){
			logger.error("Execption occoured while executing deleteCardPreference", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}finally {
			logger.info("Execution completed for deleteCardPreference");
		}
		
	}
}
