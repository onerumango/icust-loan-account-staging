package com.rumango.serviceImpl;

import java.text.MessageFormat;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustCardInitiation;
import com.rumango.model.IcustCardInitiationModel;
import com.rumango.repository.IcustCardInitiationRepo;
import com.rumango.service.IcustCardInitiationService;

@Service
public class IcustCardInitiationServiceImpl implements IcustCardInitiationService{
	private static final Logger logger = Logger.getLogger(IcustCardInitiationServiceImpl.class);

	@Autowired
	IcustCardInitiationRepo cardInitiationRepo;
	@Override
	public ResponseEntity<?> upsertCardInitiationDetails(IcustCardInitiationModel cardInitiationModel) {
		Optional<IcustCardInitiation> initiationObj = null;
		IcustCardInitiation cardInitiationDetails = null;
		try {
			IcustCardInitiation cardInitiation = new Gson().fromJson(new Gson().toJson(cardInitiationModel), IcustCardInitiation.class);
			if (cardInitiationModel.getCardId() != null) {
				initiationObj = cardInitiationRepo.findById(cardInitiationModel.getCardId());
			}
			if (initiationObj != null && initiationObj.isPresent()) {
				validateCardInitiationDetails(initiationObj.get(), cardInitiation);
				cardInitiationDetails = cardInitiationRepo.save(initiationObj.get());
			} else {
				cardInitiationDetails = cardInitiationRepo.save(cardInitiation);
			}
			return ResponseEntity.status(HttpStatus.OK).body(cardInitiationDetails);
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertCardInitiationDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private void validateCardInitiationDetails(IcustCardInitiation dataFromDb, IcustCardInitiation newObj) {
		
		if(newObj.getCustomerId()!=null)
			dataFromDb.setCustomerId(newObj.getCustomerId());
		if(newObj.getExistingCustomer()!=null)
			dataFromDb.setExistingCustomer(newObj.getExistingCustomer());
		if(!Strings.isNullOrEmpty(newObj.getCardType()))
			dataFromDb.setCardType(newObj.getCardType());
		if(!Strings.isNullOrEmpty(newObj.getCustomerAccount()))
			dataFromDb.setCustomerAccount(newObj.getCustomerAccount());
		if(!Strings.isNullOrEmpty(newObj.getApplicantName()))
			dataFromDb.setApplicantName(newObj.getApplicantName());
		if(!Strings.isNullOrEmpty(newObj.getCurrency()))
			dataFromDb.setCurrency(newObj.getCurrency());
		if(!Strings.isNullOrEmpty(newObj.getEmploymentType()))
			dataFromDb.setEmploymentType(newObj.getEmploymentType());
		if(!Strings.isNullOrEmpty(newObj.getAffinityProgram()))
			dataFromDb.setAffinityProgram(newObj.getAffinityProgram());
		if(!Strings.isNullOrEmpty(newObj.getNameOnCard()))
			dataFromDb.setNameOnCard(newObj.getNameOnCard());
		if(newObj.getCardPreferences()!=null)
			dataFromDb.setCardPreferences(newObj.getCardPreferences());
	}

	@Override
	public ResponseEntity<?> fetchCardInitiationByCardId(Long cardId) {
		try {
			Optional<IcustCardInitiation> initiationOnj = cardInitiationRepo.findById(cardId);
			if (initiationOnj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(initiationOnj.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchCardInitiationByCardId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
