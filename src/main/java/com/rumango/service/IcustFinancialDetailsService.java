package com.rumango.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustFinancialDetailsModel;

public interface IcustFinancialDetailsService {

	ResponseEntity<?> upsertFinancialDetails(List<IcustFinancialDetailsModel> icustFinancialDetailsModel);

	ResponseEntity<?> fetchFinancialInfoById(Long id);

	ResponseEntity<?> fetchFinancialDetails(Long loanAccountId, Long cardId);

}
