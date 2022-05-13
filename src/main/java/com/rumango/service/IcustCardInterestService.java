package com.rumango.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rumango.model.IcustCardInterestListModel;

@Service
public interface IcustCardInterestService {

	ResponseEntity<?> upsertDetails(IcustCardInterestListModel cardInterestModel);

	ResponseEntity<?> fetchCardInterestDetails();

	ResponseEntity<?> fetchCardInterestById(Long cardAccountId);
}
