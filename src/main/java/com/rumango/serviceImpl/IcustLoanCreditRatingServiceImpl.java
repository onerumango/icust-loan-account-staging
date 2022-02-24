package com.rumango.serviceImpl;

import static org.springframework.data.jpa.domain.Specification.where;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
			if (creditRatingModel.getLoanAccountId() != null)
				isCreditREntityPresent = creditRatingRepo.findById(creditRatingModel.getLoanAccountId());
			else
				throw new RuntimeException("Loan Id is Required");

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

		}

	}

	@Override
	public ResponseEntity<?> getAllCreditRatings() {
		List<IcustLoanCreditRatingDetails> creditRatingList = null;
		try {
			creditRatingList = creditRatingRepo.findAll();
			if (creditRatingList != null && creditRatingList.isEmpty())
				return ResponseEntity.status(HttpStatus.OK).body(creditRatingList);
			else
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No records");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> getCreditRatingsByLoanAccountId(Long loanAccId) {
		Optional<IcustLoanCreditRatingDetails> isCreditREntityPresent = null;
		IcustLoanCreditRatingDetails creditRatingObj = null;
		try {
			if (loanAccId != null)
				isCreditREntityPresent = creditRatingRepo.findById(loanAccId);
			else
				throw new RuntimeException("Loan Id is Required");

			if (isCreditREntityPresent != null && isCreditREntityPresent.isPresent()) {
				creditRatingObj = new Gson().fromJson(new Gson().toJson(isCreditREntityPresent),
						IcustLoanCreditRatingDetails.class);
				log.info("creditRatingObj :: " + creditRatingObj);
				return ResponseEntity.status(HttpStatus.OK).body(creditRatingObj);
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

}
