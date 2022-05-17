package com.rumango.controller;

import java.io.IOException;
import java.text.MessageFormat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.rumango.model.IcustCardDocumentsModel;
import com.rumango.service.IcustCardDocumentsService;

@RestController
@RequestMapping("/cardDoc")
public class IcustCardDocumentsController {

private static final Logger logger = LogManager.getLogger(IcustCardDocumentsController.class);
	
	@Autowired
	IcustCardDocumentsService uploadService;
	
	@PostMapping(value = "/uploadCardDocs", produces = MediaType.APPLICATION_JSON_VALUE, consumes = { "multipart/*",
			"application/x-www-form-urlencoded", "application/json" })
	public ResponseEntity<?> uploadCardDocs(@RequestParam("file") MultipartFile file, @RequestParam("data") String data)
			throws IOException, java.io.FileNotFoundException {
		logger.info(MessageFormat.format("Execution started for uploadCardDocs for data ", data));
		try {
			IcustCardDocumentsModel model = new Gson().fromJson(data, IcustCardDocumentsModel.class);
			return uploadService.uploadCardDocs(file,model);
		} catch (Exception e) {
			logger.error("Execption occoured while executing uploadCardDocs", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for uploadCardDocs");
		}
	}
	
	@GetMapping("/downloadDocs")
	@ResponseBody
	public ResponseEntity<Resource> downloadProfileImage(@RequestParam("fileName") String fileName) {

		Resource resource = null;
		try {
			resource = uploadService.loadAsResource(fileName);
		} catch (com.rumango.exception.FileNotFoundException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping(value="/fetchCardDocuments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchCardDocuments (@RequestParam(value = "cardId", required = true) Long cardId,
			@RequestParam(value = "screenType", required = true) String screenType){
		logger.info(MessageFormat.format(
				"Exectution started for fetchCardDocuments for documentType::", screenType));
		try {
			return uploadService.fetchCardDocuments(cardId,screenType);
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchCardDocuments", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} finally {
			logger.info("Execution completed for fetchCardDocuments");
		}
	}
	
}
