package com.rumango.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rumango.entity.IcustLoanCreditRatingDetails;
import com.rumango.model.IcustLoanCreditRatingDetailsModel;
import com.rumango.repository.IcustLoanCreditRatingRepo;
import com.rumango.service.IcustLoanCreditRatingService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IcustLoanCreditRatingServiceImpl implements IcustLoanCreditRatingService {

	@Autowired
	private IcustLoanCreditRatingRepo creditRatingRepo;

	@Override
	public ResponseEntity<?> saveOrUpdateCreditRating(IcustLoanCreditRatingDetailsModel creditRatingModel) {
		Optional<IcustLoanCreditRatingDetails> isCreditREntityPresent = null;
		IcustLoanCreditRatingDetails creditRatingObj = null;
		try {
			if(creditRatingModel.getAccountType().equalsIgnoreCase("loan")) {
				if (creditRatingModel.getLoanAccountId() != null)
					isCreditREntityPresent = creditRatingRepo.findByLoanAccountId(creditRatingModel.getLoanAccountId());
				else
					throw new RuntimeException("Loan Id is Required");
			} else if(creditRatingModel.getAccountType().equalsIgnoreCase("cardOrigin")) {
				if (creditRatingModel.getCardId() != null)
					isCreditREntityPresent = creditRatingRepo.findByCardId(creditRatingModel.getCardId());
				else
					throw new RuntimeException("Card Id is Required");
			}

			if (isCreditREntityPresent != null && isCreditREntityPresent.isPresent()) {
				IcustLoanCreditRatingDetails updateCreditRatingObj = new Gson()
						.fromJson(new Gson().toJson(creditRatingModel), IcustLoanCreditRatingDetails.class);
				validateLoanCreditRatingDetails(isCreditREntityPresent.get(), updateCreditRatingObj);
				creditRatingObj = creditRatingRepo.save(isCreditREntityPresent.get());
			} else {
				creditRatingObj = new Gson().fromJson(new Gson().toJson(creditRatingModel),
						IcustLoanCreditRatingDetails.class);
				creditRatingObj = creditRatingRepo.save(creditRatingObj);
			}
			return ResponseEntity.status(HttpStatus.OK).body(creditRatingObj);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	private void validateLoanCreditRatingDetails(IcustLoanCreditRatingDetails fromDb,
			IcustLoanCreditRatingDetails fromResponse) {
		if (fromResponse.getLoanAccountId() != null)
			fromDb.setLoanAccountId(fromResponse.getLoanAccountId());

		if (!Strings.isNullOrEmpty(fromResponse.getCustomerName()))
			fromDb.setCustomerName(fromResponse.getCustomerName());

		if (fromResponse.getAgencyRating() != null && !ObjectUtils.isEmpty(fromResponse.getAgencyRating())) {
			fromDb.setAgencyRating(fromResponse.getAgencyRating());
		}
		if(fromResponse.getCardId()!=null)
			fromDb.setCardId(fromResponse.getCardId());

	}

	@Override
	public ResponseEntity<?> getCreditRatingsByLoanAccountId(Long loanAccId, Long cardId) {
		Optional<IcustLoanCreditRatingDetails> isCreditREntityPresent = null;
		try {
			if (loanAccId != null)
				isCreditREntityPresent = creditRatingRepo.findByLoanAccountId(loanAccId);
			else if (cardId != null)
				isCreditREntityPresent = creditRatingRepo.findByCardId(cardId);
			else
				throw new RuntimeException("Id is Required");

			log.info("isCreditREntityPresent::"+ isCreditREntityPresent);
			if (isCreditREntityPresent != null && isCreditREntityPresent.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(isCreditREntityPresent.get());
			} else {
				log.error("No record exists for Loan Acc Id :: " + loanAccId);
				return ResponseEntity.status(HttpStatus.NO_CONTENT)
						.body("No record exists for Loan Acc Id :: " + loanAccId);
			}
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> getCreditRatingsById(Long id) {
		Optional<IcustLoanCreditRatingDetails> isCreditREntityPresent = null;
		IcustLoanCreditRatingDetails creditRatingObj = null;
		try {
			if (id != null)
				isCreditREntityPresent = creditRatingRepo.findById(id);
			else
				throw new RuntimeException("Id is Required");

			if (isCreditREntityPresent != null && isCreditREntityPresent.isPresent()) {
				creditRatingObj = new Gson().fromJson(new Gson().toJson(isCreditREntityPresent),
						IcustLoanCreditRatingDetails.class);
				log.info("creditRatingObj :: " + creditRatingObj);
				return ResponseEntity.status(HttpStatus.OK).body(creditRatingObj);
			} else {
				log.error("No record exists for Id :: " + id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No record exists for Id :: " + id);
			}
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
