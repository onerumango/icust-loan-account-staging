package com.rumango.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rumango.entity.IcustLoanValuationOfAssetEntity;
import com.rumango.model.IcustLoanValuationOfAssetModel;
import com.rumango.repository.IcustLoanValuationOfAssetRepo;
import com.rumango.service.IcustLoanValuationOfAssetService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IcustLoanValuationOfAssetServiceImpl implements IcustLoanValuationOfAssetService {

	@Autowired
	private IcustLoanValuationOfAssetRepo loanValuationOfAssetRepo;

	@Override
	public ResponseEntity<?> saveOrValuationOfAsset(IcustLoanValuationOfAssetModel valuationOfAssetModel) {
		Optional<IcustLoanValuationOfAssetEntity> isValuationOfAssetREntityPresent = null;
		IcustLoanValuationOfAssetEntity valuationOfAssetObj = null;
		try {
			if (valuationOfAssetModel.getLoanAccountId() != null)
				isValuationOfAssetREntityPresent = loanValuationOfAssetRepo
						.findByLoanAccountId(valuationOfAssetModel.getLoanAccountId());
			else
				throw new RuntimeException("Loan Id is Required");

			if (isValuationOfAssetREntityPresent != null && isValuationOfAssetREntityPresent.isPresent()) {
				IcustLoanValuationOfAssetEntity updateValuationOfAssetObj = new Gson()
						.fromJson(new Gson().toJson(valuationOfAssetModel), IcustLoanValuationOfAssetEntity.class);
				validateLoanValuationOfAssetDetails(isValuationOfAssetREntityPresent.get(), updateValuationOfAssetObj);
				valuationOfAssetObj = loanValuationOfAssetRepo.save(isValuationOfAssetREntityPresent.get());
			} else {
				valuationOfAssetObj = new Gson().fromJson(new Gson().toJson(valuationOfAssetModel),
						IcustLoanValuationOfAssetEntity.class);
				valuationOfAssetObj = loanValuationOfAssetRepo.save(valuationOfAssetObj);
			}
			return ResponseEntity.status(HttpStatus.OK).body(valuationOfAssetObj);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	private void validateLoanValuationOfAssetDetails(IcustLoanValuationOfAssetEntity oldValuationOfAsset,
			IcustLoanValuationOfAssetEntity newValuationOfAsset) {
		if(newValuationOfAsset.getBankValuation()!=null)
			oldValuationOfAsset.setBankValuation(newValuationOfAsset.getBankValuation());
		if(!Strings.isNullOrEmpty(newValuationOfAsset.getAssetType()))
			oldValuationOfAsset.setAssetType(newValuationOfAsset.getAssetType());
		if(newValuationOfAsset.getPropertyAreaInUnits()!=null)
			oldValuationOfAsset.setPropertyAreaInUnits(newValuationOfAsset.getPropertyAreaInUnits());
		if(newValuationOfAsset.getPropertyAreaSize()!=null)
			oldValuationOfAsset.setActualPropertyAreaSize(newValuationOfAsset.getPropertyAreaSize());
		if(newValuationOfAsset.getBorrowersMktValOfAsset()!=null)
			oldValuationOfAsset.setBorrowersMktValOfAsset(newValuationOfAsset.getBorrowersMktValOfAsset());
		if(!Strings.isNullOrEmpty(newValuationOfAsset.getAssetValue()))
			oldValuationOfAsset.setAssetValue(newValuationOfAsset.getAssetValue());
		if(newValuationOfAsset.getValuationDate()!=null)
			oldValuationOfAsset.setValuationDate(newValuationOfAsset.getValuationDate());
		if(newValuationOfAsset.getActualPropertyAreaInUnits()!=null)
			oldValuationOfAsset.setActualPropertyAreaInUnits(newValuationOfAsset.getActualPropertyAreaInUnits());
		if(newValuationOfAsset.getActualPropertyAreaSize()!=null)
			oldValuationOfAsset.setActualPropertyAreaSize(newValuationOfAsset.getActualPropertyAreaSize());
		if(!Strings.isNullOrEmpty(newValuationOfAsset.getFaceValOfAsset()))
			oldValuationOfAsset.setFaceValOfAsset(newValuationOfAsset.getFaceValOfAsset());
		if(!Strings.isNullOrEmpty(newValuationOfAsset.getMktValOfAsset()))
			oldValuationOfAsset.setMktValOfAsset(newValuationOfAsset.getMktValOfAsset());
		if(newValuationOfAsset.getForcedSaleValue()!=null)
			oldValuationOfAsset.setForcedSaleValue(newValuationOfAsset.getForcedSaleValue());
		
	}

	@Override
	public ResponseEntity<?> getValuationOfAssetById(Long id) {
		Optional<IcustLoanValuationOfAssetEntity> isValuationOfAssetEntityPresent = null;
		IcustLoanValuationOfAssetModel valuationOfAssetObj = null;
		try {
			if (id != null)
				isValuationOfAssetEntityPresent = loanValuationOfAssetRepo.findById(id);
			else
				throw new RuntimeException("Id is Required");

			if (isValuationOfAssetEntityPresent != null && isValuationOfAssetEntityPresent.isPresent()) {
				valuationOfAssetObj = new Gson().fromJson(new Gson().toJson(isValuationOfAssetEntityPresent),
						IcustLoanValuationOfAssetModel.class);
				log.info("valuationOfAssetObj :: " + valuationOfAssetObj);
				return ResponseEntity.status(HttpStatus.OK).body(valuationOfAssetObj);
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
	public ResponseEntity<?> getValuationOfAssetByLoanAccountId(Long loanAccId) {
		Optional<IcustLoanValuationOfAssetEntity> isValuationOfAssetEntityPresent = null;
		IcustLoanValuationOfAssetModel valuationOfAssetObj = null;
		try {
			if (loanAccId != null)
				isValuationOfAssetEntityPresent = loanValuationOfAssetRepo.findByLoanAccountId(loanAccId);
			else
				throw new RuntimeException("Loan Id is Required");

			if (isValuationOfAssetEntityPresent != null && isValuationOfAssetEntityPresent.isPresent()) {
				valuationOfAssetObj = new Gson().fromJson(new Gson().toJson(isValuationOfAssetEntityPresent.get()),
						IcustLoanValuationOfAssetModel.class);
				log.info("valuationOfAssetObj :: " + valuationOfAssetObj);
				return ResponseEntity.status(HttpStatus.OK).body(valuationOfAssetObj);
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
