package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustLoanCreditRatingDetailsModel;

public interface IcustLoanCreditRatingService {
	
	 ResponseEntity<?> saveOrUpdateCreditRating(IcustLoanCreditRatingDetailsModel creditRatingModel);
	 
	 ResponseEntity<?> getAllCreditRatings();
	 
	 ResponseEntity<?> getCreditRatingsByLoanAccountId(Long loanAccId);

}
