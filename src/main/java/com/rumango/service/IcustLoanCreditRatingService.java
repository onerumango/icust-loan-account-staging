package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustLoanCreditRatingDetailsModel;

public interface IcustLoanCreditRatingService {
	
	 ResponseEntity<?> saveOrUpdateCreditRating(IcustLoanCreditRatingDetailsModel creditRatingModel);
	 
	 ResponseEntity<?> getCreditRatingsById(Long id);
	 
	 ResponseEntity<?> getCreditRatingsByLoanAccountId(Long loanAccId, Long cardId);

}
