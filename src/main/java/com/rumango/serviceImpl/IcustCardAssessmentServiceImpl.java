package com.rumango.serviceImpl;

import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.rumango.entity.IcustCardAssessmentDetails;
import com.rumango.model.IcustCardAssessmentModel;
import com.rumango.repository.IcustCardAssessmentDetailsRepo;
import com.rumango.service.IcustCardAssessmentService;

@Service
public class IcustCardAssessmentServiceImpl implements IcustCardAssessmentService {

private static final Logger logger = LogManager.getLogger(IcustCardAssessmentServiceImpl.class);
	
	@Autowired
	private IcustCardAssessmentDetailsRepo assessmentDetailsRepo;

	@Override
	public ResponseEntity<?> upsertCardAssessmentDetails(IcustCardAssessmentModel cardAssessmentModel) {
		try {
			if(cardAssessmentModel.getCardId() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Card ID is mandatory");
			}
			else {
				Optional<IcustCardAssessmentDetails> assessmentDetails = assessmentDetailsRepo
						.findByCardId(cardAssessmentModel.getCardId());
				IcustCardAssessmentDetails cardAssessmentDetails = new Gson().fromJson(new Gson().toJson(cardAssessmentModel), 
						IcustCardAssessmentDetails.class);
				if(assessmentDetails.isPresent()) {
					validateCardAssessmentDetails(assessmentDetails.get(), cardAssessmentDetails);
					return ResponseEntity.status(HttpStatus.OK).body(assessmentDetailsRepo.save(assessmentDetails.get()));
				}
				else {
					return ResponseEntity.status(HttpStatus.OK).body(assessmentDetailsRepo.save(cardAssessmentDetails));
				}
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertCardAssessmentDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	private void validateCardAssessmentDetails(IcustCardAssessmentDetails oldAssessmentDetails, IcustCardAssessmentDetails newAssessmentDetails) {
		if(newAssessmentDetails.getCardId()!=null)
			oldAssessmentDetails.setCardId(newAssessmentDetails.getCardId());
		if(newAssessmentDetails.getRequestedCardLimit()!=null)
			oldAssessmentDetails.setRequestedCardLimit(newAssessmentDetails.getRequestedCardLimit());
		if(newAssessmentDetails.getSystemRecommendation()!=null)
			oldAssessmentDetails.setSystemRecommendation(newAssessmentDetails.getSystemRecommendation());
		if(newAssessmentDetails.getUserRecommendation()!=null)
			oldAssessmentDetails.setUserRecommendation(newAssessmentDetails.getUserRecommendation());
		if(newAssessmentDetails.getRecommendedCardLimit()!=null)
			oldAssessmentDetails.setRecommendedCardLimit(newAssessmentDetails.getRecommendedCardLimit());
		if(newAssessmentDetails.getApprovedCardLimit()!=null)
			oldAssessmentDetails.setApprovedCardLimit(newAssessmentDetails.getApprovedCardLimit());
	}

	@Override
	public ResponseEntity<?> fetchCardAssessmentDetails() {
		try {
			List<IcustCardAssessmentDetails> cardAssessmentDetails = assessmentDetailsRepo.findAll();
			if (cardAssessmentDetails != null) {
				return ResponseEntity.status(HttpStatus.OK).body(cardAssessmentDetails);
			} else {
				logger.error("No Data Found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Data Found");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchCardAssessmentDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchCardAssessmentById(Long cardId) {
		try {
			if (cardId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CardId is Mandatory");
			
			Optional<IcustCardAssessmentDetails> cardAssessmentData = assessmentDetailsRepo.findByCardId(cardId+"");
			if (cardAssessmentData.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(cardAssessmentData.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchCardAssessmentById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
