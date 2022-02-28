package com.rumango.service;

import org.springframework.http.ResponseEntity;

import com.rumango.model.IcustPostOfferAmendmentModel;

public interface IcustPostOfferAmendmentService {

	ResponseEntity<?> upsertPostOfferAmendment(IcustPostOfferAmendmentModel icustPostOfferAmendmentModel);

	ResponseEntity<?> fetchPostOfferAmendmentDetails();

	ResponseEntity<?> fetchPostOfferAmendmentDetailsById(Long loanAccountId);

}
