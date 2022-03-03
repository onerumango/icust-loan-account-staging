package com.rumango.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rumango.entity.IcustLoanLegalOpinionEntity;
import com.rumango.model.IcustLoanLegalOpinionModel;
import com.rumango.repository.IcustLoanLegalOpinionRepo;
import com.rumango.service.IcustLoanLegalOpinionService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IcustLoanLegalOpinionServiceImpl implements IcustLoanLegalOpinionService {

	@Autowired
	private IcustLoanLegalOpinionRepo legalOpinionRepo;

	@Override
	public ResponseEntity<?> saveOrUpdateLegalOpinion(IcustLoanLegalOpinionModel legalOpinionModel) {
		Optional<IcustLoanLegalOpinionEntity> isLegalOpinionEntityPresent = null;
		IcustLoanLegalOpinionEntity legalOpinionObj = null;
		try {
			if (legalOpinionModel.getLoanAccountId() != null)
				isLegalOpinionEntityPresent = legalOpinionRepo
						.findByLoanAccountId(legalOpinionModel.getLoanAccountId());
			else
				throw new RuntimeException("Loan Id is Required");

			if (isLegalOpinionEntityPresent != null && isLegalOpinionEntityPresent.isPresent()) {
				IcustLoanLegalOpinionEntity updateLegalOpinionObj = new Gson()
						.fromJson(new Gson().toJson(legalOpinionModel), IcustLoanLegalOpinionEntity.class);
				validateLegalOpinionDetails(isLegalOpinionEntityPresent.get(), updateLegalOpinionObj);
				legalOpinionObj = legalOpinionRepo.save(isLegalOpinionEntityPresent.get());
			} else {
				legalOpinionObj = new Gson().fromJson(new Gson().toJson(legalOpinionModel),
						IcustLoanLegalOpinionEntity.class);
				legalOpinionObj = legalOpinionRepo.save(legalOpinionObj);
			}
			return ResponseEntity.status(HttpStatus.OK).body(legalOpinionObj);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	private void validateLegalOpinionDetails(IcustLoanLegalOpinionEntity icustLoanLegalOpinionEntity,
			IcustLoanLegalOpinionEntity updateLegalOpinionObj) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResponseEntity<?> getLegalOpinionById(Long id) {
		Optional<IcustLoanLegalOpinionEntity> isLegalOpinionEntityPresent = null;
		IcustLoanLegalOpinionModel legalOpinionObj = null;
		try {
			if (id != null)
				isLegalOpinionEntityPresent = legalOpinionRepo.findById(id);
			else
				throw new RuntimeException("Id is Required");

			if (isLegalOpinionEntityPresent != null && isLegalOpinionEntityPresent.isPresent()) {
				legalOpinionObj = new Gson().fromJson(new Gson().toJson(isLegalOpinionEntityPresent),
						IcustLoanLegalOpinionModel.class);
				log.info("legalOpinionObj :: " + legalOpinionObj);
				return ResponseEntity.status(HttpStatus.OK).body(legalOpinionObj);
			} else {
				log.error("No record exists for Id :: " + id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No record exists for Id :: " + id);
			}
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> getLegalOpinionByLoanAccountId(Long loanAccId) {
		Optional<IcustLoanLegalOpinionEntity> isLegalOpinionEntityPresent = null;
		IcustLoanLegalOpinionModel legalOpinionObj = null;
		try {
			if (loanAccId != null)
				isLegalOpinionEntityPresent = legalOpinionRepo.findByLoanAccountId(loanAccId);
			else
				throw new RuntimeException("Loan Id is Required");

			if (isLegalOpinionEntityPresent != null && isLegalOpinionEntityPresent.isPresent()) {
				legalOpinionObj = new Gson().fromJson(new Gson().toJson(isLegalOpinionEntityPresent.get()),
						IcustLoanLegalOpinionModel.class);
				log.info("legalOpinionObj :: " + legalOpinionObj);
				return ResponseEntity.status(HttpStatus.OK).body(legalOpinionObj);
			} else {
				log.error("No record exists for Loan Id :: " + loanAccId);
				return ResponseEntity.status(HttpStatus.NO_CONTENT)
						.body("No record exists for Loan Id :: " + loanAccId);
			}
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
