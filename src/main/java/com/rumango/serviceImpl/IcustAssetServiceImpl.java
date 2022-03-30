package com.rumango.serviceImpl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustAssetDetails;
import com.rumango.model.IcustAssetDetailsModel;
import com.rumango.model.IcustLoanInfoModel;
import com.rumango.repository.IcustAssetDetailsRepo;
import com.rumango.service.IcustAssetService;

@Service
public class IcustAssetServiceImpl implements IcustAssetService{
	private static final Logger logger = LogManager.getLogger(IcustAssetServiceImpl.class);

	@Autowired
	IcustAssetDetailsRepo icustAssetDetailsRepo;

	@Override
	public ResponseEntity<?> upsertAssetDetails(IcustAssetDetailsModel icustAssetDetailsModel) {
		try {
			if (icustAssetDetailsModel.getLoanAccountId()==null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanId is Mandatory");
			else {
				Optional<IcustAssetDetails> assetObj = icustAssetDetailsRepo
						.findByLoanAccountId(icustAssetDetailsModel.getLoanAccountId());
				IcustAssetDetails assetData = new Gson().fromJson(new Gson().toJson(icustAssetDetailsModel),
						IcustAssetDetails.class);
				if (assetObj.isPresent()) {
					validateAssetDetails(assetObj.get(),assetData);
					return ResponseEntity.status(HttpStatus.OK).body(icustAssetDetailsRepo.save(assetObj.get()));
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(icustAssetDetailsRepo.save(assetData));
				}
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertAssetDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}


	private void validateAssetDetails(IcustAssetDetails oldAssetDetails, IcustAssetDetails newAssetDetails) {
		if(newAssetDetails.getLoanAccountId()!=null)
			oldAssetDetails.setLoanAccountId(newAssetDetails.getLoanAccountId());
		if(!Strings.isNullOrEmpty(newAssetDetails.getMortgagedBranch()))
			oldAssetDetails.setMortgagedBranch(newAssetDetails.getMortgagedBranch());
		if(!Strings.isNullOrEmpty(newAssetDetails.getHomeType()))
			oldAssetDetails.setHomeType(newAssetDetails.getHomeType());
		if(!Strings.isNullOrEmpty(newAssetDetails.getDimensions()))
			oldAssetDetails.setDimensions(newAssetDetails.getDimensions());
		if(newAssetDetails.getMarketValue()!=null)
			oldAssetDetails.setMarketValue(newAssetDetails.getMarketValue());
		if(!Strings.isNullOrEmpty(newAssetDetails.getAssetStatus()))
			oldAssetDetails.setAssetStatus(newAssetDetails.getAssetStatus());
		if(!Strings.isNullOrEmpty(newAssetDetails.getBuilding()))
			oldAssetDetails.setBuilding(newAssetDetails.getBuilding());
		if(!Strings.isNullOrEmpty(newAssetDetails.getStreet()))
			oldAssetDetails.setStreet(newAssetDetails.getStreet());
		if(!Strings.isNullOrEmpty(newAssetDetails.getLocality()))
			oldAssetDetails.setLocality(newAssetDetails.getLocality());
		if(!Strings.isNullOrEmpty(newAssetDetails.getCity()))
			oldAssetDetails.setCity(newAssetDetails.getCity());
		if(!Strings.isNullOrEmpty(newAssetDetails.getState()))
			oldAssetDetails.setState(newAssetDetails.getState());
		if(!Strings.isNullOrEmpty(newAssetDetails.getCountry()))
			oldAssetDetails.setCountry(newAssetDetails.getCountry());
		if(!Strings.isNullOrEmpty(newAssetDetails.getZipCode()))
			oldAssetDetails.setZipCode(newAssetDetails.getZipCode());
	}


	@Override
	public ResponseEntity<?> fetchAssetDetailsByLoanAccId(Long loanAccountId) {
		try {
			if (loanAccountId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanId is Mandatory");
			
			Optional<IcustAssetDetails> assetObj = icustAssetDetailsRepo.findByLoanAccountId(loanAccountId);
			if (assetObj.isPresent()) {
				IcustAssetDetailsModel assetInfo = new Gson().fromJson(new Gson().toJson(assetObj.get()),
						IcustAssetDetailsModel.class);
				return ResponseEntity.status(HttpStatus.OK).body(assetInfo);
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchAssetDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}


	@Override
	public ResponseEntity<?> fetchAssetInfoById(Long id) {
		try {
			List<IcustAssetDetails> assetList = icustAssetDetailsRepo.findAll();
			if (!CollectionUtils.isEmpty(assetList)) {
				return ResponseEntity.status(HttpStatus.OK).body(assetList);
			} else {
				logger.error("No details found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No details found");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchAssetInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
