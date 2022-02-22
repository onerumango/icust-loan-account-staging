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
import com.rumango.entity.IcustVehicleDetails;
import com.rumango.model.IcustVehicleDetailsModel;
import com.rumango.repository.IcustVehicleDetailsRepo;
import com.rumango.service.IcustVehicleService;

@Service
public class IcustVehicleServiceImpl implements IcustVehicleService {
	private static final Logger logger = LogManager.getLogger(IcustVehicleServiceImpl.class);


	@Autowired
	IcustVehicleDetailsRepo icustVehicleDetailsRepo;

	@Override
	public ResponseEntity<?> upsertVehicleDetails(IcustVehicleDetailsModel icustVehicleDetailsModel) {
		try {
			if (icustVehicleDetailsModel.getLoanId()==null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanId is Mandatory");
			else {
				Optional<IcustVehicleDetails> vehicleObj = icustVehicleDetailsRepo
						.findByLoanId(icustVehicleDetailsModel.getLoanId());
				IcustVehicleDetails vehicleData = new Gson().fromJson(new Gson().toJson(icustVehicleDetailsModel),
						IcustVehicleDetails.class);
				if (vehicleObj.isPresent()) {
					validateVehicleDetails(vehicleObj.get(),vehicleData);
					return ResponseEntity.status(HttpStatus.OK).body(icustVehicleDetailsRepo.save(vehicleObj.get()));
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(icustVehicleDetailsRepo.save(vehicleData));
				}
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertVehicleDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private void validateVehicleDetails(IcustVehicleDetails oldVehicleDetails, IcustVehicleDetails newVehicleDetails) {
		if(newVehicleDetails.getLoanId()!=null)
			oldVehicleDetails.setLoanId(newVehicleDetails.getLoanId());
		if(!Strings.isNullOrEmpty(newVehicleDetails.getProductName()))
			oldVehicleDetails.setProductName(newVehicleDetails.getProductName());
		if(!Strings.isNullOrEmpty(newVehicleDetails.getHypothecatedBranch()))
			oldVehicleDetails.setHypothecatedBranch(newVehicleDetails.getHypothecatedBranch());
		if(!Strings.isNullOrEmpty(newVehicleDetails.getVehicleClass()))
			oldVehicleDetails.setVehicleClass(newVehicleDetails.getVehicleClass());
		if(newVehicleDetails.getMarketValue()!=null)
			oldVehicleDetails.setMarketValue(oldVehicleDetails.getMarketValue());
		if(!Strings.isNullOrEmpty(newVehicleDetails.getModel()))
			oldVehicleDetails.setModel(newVehicleDetails.getModel());
		if(newVehicleDetails.getMake()!=null)
			oldVehicleDetails.setMake(newVehicleDetails.getMake());
		if(!Strings.isNullOrEmpty(newVehicleDetails.getChassisNumber()))
			oldVehicleDetails.setChassisNumber(newVehicleDetails.getChassisNumber());
		if(!Strings.isNullOrEmpty(newVehicleDetails.getEngineNumber()))
			oldVehicleDetails.setEngineNumber(newVehicleDetails.getEngineNumber());
		if(!Strings.isNullOrEmpty(newVehicleDetails.getRegistrationNumber()))
			oldVehicleDetails.setRegistrationNumber(newVehicleDetails.getRegistrationNumber());
		if(!Strings.isNullOrEmpty(newVehicleDetails.getRegistrationState()))
			oldVehicleDetails.setRegistrationState(newVehicleDetails.getRegistrationState());
		if(!Strings.isNullOrEmpty(newVehicleDetails.getRegistrationCity()))
			oldVehicleDetails.setRegistrationCity(newVehicleDetails.getRegistrationCity());
		if(newVehicleDetails.getExpectedSellingPrice()!=null)
			oldVehicleDetails.setExpectedSellingPrice(newVehicleDetails.getExpectedSellingPrice());
		if(!Strings.isNullOrEmpty(newVehicleDetails.getDistanceRun()))
			oldVehicleDetails.setDistanceRun(newVehicleDetails.getDistanceRun());
		if(!Strings.isNullOrEmpty(newVehicleDetails.getInsuranceDetails()))
			oldVehicleDetails.setInsuranceDetails(newVehicleDetails.getInsuranceDetails());
		if(!Strings.isNullOrEmpty(newVehicleDetails.getInsuranceCompany()))
			oldVehicleDetails.setInsuranceCompany(newVehicleDetails.getInsuranceCompany());
		if(!Strings.isNullOrEmpty(newVehicleDetails.getPolicyNumber()))
			oldVehicleDetails.setPolicyNumber(newVehicleDetails.getPolicyNumber());
		if(newVehicleDetails.getPolicyCommencementDate()!=null)
			oldVehicleDetails.setPolicyCommencementDate(newVehicleDetails.getPolicyCommencementDate());
		if(newVehicleDetails.getPremiunAmount()!=null)
			oldVehicleDetails.setPremiunAmount(newVehicleDetails.getPremiunAmount());
		if(!Strings.isNullOrEmpty(newVehicleDetails.getPremiunFrequency()))
			oldVehicleDetails.setPremiunFrequency(newVehicleDetails.getPremiunFrequency());
		if(newVehicleDetails.getPolicyRenewalDate()!=null)
			oldVehicleDetails.setPolicyRenewalDate(newVehicleDetails.getPolicyRenewalDate());
	}

	@Override
	public ResponseEntity<?> fetchVehicleDetails(Long loanId) {
		try {
			if (loanId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanId is Mandatory");
			Optional<IcustVehicleDetails> vehicleObj = icustVehicleDetailsRepo.findByLoanId(loanId);
			
			if (vehicleObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(vehicleObj.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchVehicleDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchVehicleInfo() {
		try {
			List<IcustVehicleDetails> vehicleList = icustVehicleDetailsRepo.findAll();
			if (!CollectionUtils.isEmpty(vehicleList)) {
				return ResponseEntity.status(HttpStatus.OK).body(vehicleList);
			} else {
				logger.error("No details found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No details found");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchVehicleInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
