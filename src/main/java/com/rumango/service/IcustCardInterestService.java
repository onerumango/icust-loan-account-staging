package com.rumango.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rumango.model.IcustCardInterestModel;

@Service
public interface IcustCardInterestService {

	ResponseEntity<?> upsertDetails(List<IcustCardInterestModel> cardInterestModel);

	ResponseEntity<?> fetchCardInterestDetails();

	ResponseEntity<?> fetchCardInterestById(Long cardAccountId);
}
