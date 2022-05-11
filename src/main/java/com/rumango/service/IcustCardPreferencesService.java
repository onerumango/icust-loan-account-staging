package com.rumango.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustCardPreferencesModel;

public interface IcustCardPreferencesService {

	ResponseEntity<?> fetchCardPreferenceByCardId(Long cardId);

	ResponseEntity<?> upsertCardPreferenceDetails(List<IcustCardPreferencesModel> cardPreferencesModel);

	ResponseEntity<?> deleteCardPreference(Long id);

}
