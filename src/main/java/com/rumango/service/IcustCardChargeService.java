package com.rumango.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rumango.model.IcustCardChargeModel;

@Service
public interface IcustCardChargeService {

	ResponseEntity<?> upsertCardChargeDetails(List<IcustCardChargeModel> icustCardChargeModel);

	ResponseEntity<?> fetchCardChargeDetails();

	ResponseEntity<?> fetchCardChargeDetailsById(Long cardAccoutId);

}
