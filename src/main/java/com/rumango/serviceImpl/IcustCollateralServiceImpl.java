package com.rumango.serviceImpl;

import java.text.MessageFormat;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustCollateralMaster;
import com.rumango.model.IcustCollateralMasterModel;
import com.rumango.repository.IcustCollateralMasterRepo;
import com.rumango.service.IcustCollateralService;

@Service
public class IcustCollateralServiceImpl implements IcustCollateralService{
	private static final Logger logger = Logger.getLogger(IcustCollateralServiceImpl.class);

	@Autowired
	IcustCollateralMasterRepo collateralMasterRepo;
	
	@Override
	public ResponseEntity<?> upsertCollateralDetails(IcustCollateralMasterModel icustCollateralMasterModel) {
		try {
			if (icustCollateralMasterModel.getLoanAccountId()==null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			else {
				Optional<IcustCollateralMaster> collateralObj = collateralMasterRepo
						.findByLoanAccountId(icustCollateralMasterModel.getLoanAccountId());
				IcustCollateralMaster collateralData = new Gson().fromJson(new Gson().toJson(icustCollateralMasterModel),
						IcustCollateralMaster.class);
				if (collateralObj.isPresent()) {
					validateCollateralDetails(collateralObj.get(),collateralData);
					return ResponseEntity.status(HttpStatus.OK).body(collateralMasterRepo.save(collateralObj.get()));
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(collateralMasterRepo.save(collateralData));
				}
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertCollateralDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private void validateCollateralDetails(IcustCollateralMaster oldCollateral,
			IcustCollateralMaster newCollateral) {
		
		if(!Strings.isNullOrEmpty(newCollateral.getCollateralType()))
			oldCollateral.setCollateralType(newCollateral.getCollateralType());
		if(!Strings.isNullOrEmpty(newCollateral.getCollateralCurrency()))
			oldCollateral.setCollateralCurrency(newCollateral.getCollateralCurrency());
		if(newCollateral.getCollateralValue()!=null)
			oldCollateral.setCollateralValue(newCollateral.getCollateralValue());
		if(!Strings.isNullOrEmpty(newCollateral.getAttributes()))
			oldCollateral.setAttributes(newCollateral.getAttributes());
		if(!Strings.isNullOrEmpty(newCollateral.getDimensions()))
			oldCollateral.setDimensions(newCollateral.getDimensions());
		if(newCollateral.getThirdPartyCollateral()!=null)
			oldCollateral.setThirdPartyCollateral(newCollateral.getThirdPartyCollateral());
		if(!Strings.isNullOrEmpty(newCollateral.getBuilding()))
			oldCollateral.setBuilding(newCollateral.getBuilding());
		if(!Strings.isNullOrEmpty(newCollateral.getStreet()))
			oldCollateral.setStreet(newCollateral.getStreet());
		if(!Strings.isNullOrEmpty(newCollateral.getLocality()))
			oldCollateral.setLocality(newCollateral.getLocality());
		if(!Strings.isNullOrEmpty(newCollateral.getCity()))
			oldCollateral.setCity(newCollateral.getCity());
		if(!Strings.isNullOrEmpty(newCollateral.getState()))
			oldCollateral.setState(newCollateral.getState());
		if(!Strings.isNullOrEmpty(newCollateral.getCountry()))
			oldCollateral.setCountry(newCollateral.getCountry());
		if(!Strings.isNullOrEmpty(newCollateral.getZipCode()))
			oldCollateral.setZipCode(newCollateral.getZipCode());
		if(newCollateral.getCollateralDetails()!=null)
			oldCollateral.setCollateralDetails(newCollateral.getCollateralDetails());
	}

	@Override
	public ResponseEntity<?> fetchCollateralByLoanAccountId(Long loanAccountId) {
		try {
			if (loanAccountId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			
			Optional<IcustCollateralMaster> collateralObj = collateralMasterRepo.findByLoanAccountId(loanAccountId);
			if (collateralObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(collateralObj.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchCollateralByLoanAccountId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchCollateralInfoById(Long id) {
		try {
			if (id == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is Mandatory");
			
			Optional<IcustCollateralMaster> collateralObj = collateralMasterRepo.findById(id);
			if (collateralObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(collateralObj.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchCollateralInfoById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
