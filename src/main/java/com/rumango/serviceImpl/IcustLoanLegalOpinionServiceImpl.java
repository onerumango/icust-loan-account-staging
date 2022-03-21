package com.rumango.serviceImpl;

import java.sql.Date;
import java.util.Optional;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rumango.entity.IcustLoanLegalOpinionEntity;
import com.rumango.enums.UnitMeasurementEnum;
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
		System.err.println("legalOpinionModel::"+legalOpinionModel);
		try {
			if (legalOpinionModel.getLoanAccountId() != null)
				isLegalOpinionEntityPresent = legalOpinionRepo
						.findByLoanAccountId(legalOpinionModel.getLoanAccountId());
			else
				throw new RuntimeException("Loan Id is Required");

			if (isLegalOpinionEntityPresent != null && isLegalOpinionEntityPresent.isPresent()) {
				IcustLoanLegalOpinionEntity updateLegalOpinionObj = new Gson()
						.fromJson(new Gson().toJson(legalOpinionModel), IcustLoanLegalOpinionEntity.class);
				System.err.println("updateLegalOpinionObj::"+updateLegalOpinionObj);
				validateLegalOpinionDetails(isLegalOpinionEntityPresent.get(), updateLegalOpinionObj);
				legalOpinionObj = legalOpinionRepo.save(isLegalOpinionEntityPresent.get());
			} else {
				legalOpinionObj = new Gson().fromJson(new Gson().toJson(legalOpinionModel),
						IcustLoanLegalOpinionEntity.class);
				System.err.println("legalOpinionObj else::"+legalOpinionObj);
				legalOpinionObj = legalOpinionRepo.save(legalOpinionObj);
			}
			System.err.println("legalOpinionObj return::"+legalOpinionObj);
			return ResponseEntity.status(HttpStatus.OK).body(legalOpinionObj);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	private void validateLegalOpinionDetails(IcustLoanLegalOpinionEntity oldLegalOpinion,
			IcustLoanLegalOpinionEntity newLegalOpinion) {
		if(newLegalOpinion.getAssetAreaInUnits()!=null)
			oldLegalOpinion.setAssetAreaInUnits(newLegalOpinion.getAssetAreaInUnits());
		if(newLegalOpinion.getAssetAreaSize()!=null)
			oldLegalOpinion.setAssetAreaSize(newLegalOpinion.getAssetAreaSize());
		if(!Strings.isNullOrEmpty(newLegalOpinion.getDescription()))
			oldLegalOpinion.setDescription(newLegalOpinion.getDescription());
		if(!Strings.isNullOrEmpty(newLegalOpinion.getIsDecisionFavorable()))
			oldLegalOpinion.setIsDecisionFavorable(newLegalOpinion.getIsDecisionFavorable());
		if(!Strings.isNullOrEmpty(newLegalOpinion.getMktValOfAsset()))
			oldLegalOpinion.setMktValOfAsset(newLegalOpinion.getMktValOfAsset());
		if(!Strings.isNullOrEmpty(newLegalOpinion.getLawyerName()))
			oldLegalOpinion.setLawyerName(newLegalOpinion.getLawyerName());
		if(!Strings.isNullOrEmpty(newLegalOpinion.getOpinion()))
			oldLegalOpinion.setOpinion(newLegalOpinion.getOpinion());
		if(newLegalOpinion.getOpinionDate()!=null)
			oldLegalOpinion.setOpinionDate(newLegalOpinion.getOpinionDate());
		if(newLegalOpinion.getValuationDate()!=null)
			oldLegalOpinion.setValuationDate(newLegalOpinion.getValuationDate());

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
