package com.rumango.serviceImpl;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
//import org.apache.logging.log4j.util.Strings;
//import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustLaonChargeDetails;
import com.rumango.entity.IcustLoanInterestDetails;
import com.rumango.model.IcustLoanChargeListModel;
import com.rumango.model.IcustLoanChargeModel;
import com.rumango.model.IcustLoanInterestModel;
import com.rumango.repository.IcustLoanChargeRepo;
import com.rumango.service.IcustLoanChargeService;

@Service
public class IcustLoanChargeServiceImpl implements IcustLoanChargeService{

	private static final Logger logger= LogManager.getLogger(IcustLoanChargeServiceImpl.class);
	@Autowired
	IcustLoanChargeRepo icustLoanChargeRepo;
	
	ModelMapper mapper = new ModelMapper();
	
	@Override
	public ResponseEntity<?> upsertLoanChargeDetails(IcustLoanChargeListModel icustLoanChargeListModel) {
		try {
			List<IcustLoanChargeModel> chargeModel = icustLoanChargeListModel.getChargeInfo();
			List<IcustLaonChargeDetails> chargeDetails = new LinkedList<>();
			Iterator<IcustLoanChargeModel> iterator = chargeModel.iterator();
			Optional<IcustLaonChargeDetails> updateChargeDetails = null;
			while (iterator.hasNext()) {
				IcustLoanChargeModel loanChargeModel = (IcustLoanChargeModel) iterator.next();
				if (loanChargeModel.getLoanAccountId() == null)
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
				else {
					if (loanChargeModel.getChargeDetailsId() != null) {
						updateChargeDetails = icustLoanChargeRepo.findById(loanChargeModel.getChargeDetailsId());
					}
					IcustLaonChargeDetails chargeData = new Gson().fromJson(new Gson().toJson(loanChargeModel),
							IcustLaonChargeDetails.class);
					if (updateChargeDetails != null && updateChargeDetails.isPresent()) {
						validatorChargeDetails(updateChargeDetails.get(), chargeData);
						chargeDetails.add(updateChargeDetails.get());
					} else {
						chargeDetails.add(mapper.map(loanChargeModel, IcustLaonChargeDetails.class));
					}
				}
			}
			logger.info("Saving charge details " + chargeDetails);
			return ResponseEntity.status(HttpStatus.OK).body(icustLoanChargeRepo.saveAll(chargeDetails));
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertLoanChargeDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	private void validatorChargeDetails(IcustLaonChargeDetails oldDetails, IcustLaonChargeDetails newDetails) {
		if(newDetails.getLoanAccountId()!=null) {
			oldDetails.setLoanAccountId(newDetails.getLoanAccountId());
		}
		if(!Strings.isNullOrEmpty(newDetails.getInterestType())) {
			oldDetails.setInterestType(newDetails.getInterestType());
		}
		if(newDetails.getAmout()!=null) {
			oldDetails.setAmout(newDetails.getAmout());
		}
		if(newDetails.getWaiver()!=null) {
			oldDetails.setWaiver(newDetails.getWaiver());
		}
	}

	@Override
	public ResponseEntity<?> fetchLoanChargeDetails() {
		try {
			List<IcustLaonChargeDetails> list=icustLoanChargeRepo.findAll();
			if(!CollectionUtils.isEmpty(list)) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			}else {
				logger.error("No Details found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Details Found");
			}
		}catch (Exception e) {
			
			logger.error("Execption occure while fetchLoanChargeDetails",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchLoanChargeDetailsById(Long loanAccoutId) {
		try {
			List<IcustLaonChargeDetails> chargeList = icustLoanChargeRepo.findByLoanAccountId(loanAccoutId);
			if (!CollectionUtils.isEmpty(chargeList)) {

				return ResponseEntity.status(HttpStatus.OK).body(chargeList);
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanChargeDetailsById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
