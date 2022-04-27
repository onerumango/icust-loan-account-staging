package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustCardInitiationModel;

public interface IcustCardInitiationService {

	ResponseEntity<?> upsertCardInitiationDetails(IcustCardInitiationModel cardInitiationModel);

	ResponseEntity<?> fetchCardInitiationByCardId(Long cardId);

}
