package com.rumango.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rumango.model.IcustCardChargeListModel;

@Service
public interface IcustCardChargeService {

	ResponseEntity<?> upsertCardChargeDetails(IcustCardChargeListModel icustCardChargeModel);

	ResponseEntity<?> fetchCardChargeDetails();

	ResponseEntity<?> fetchCardChargeDetailsById(Long cardAccoutId);

}
