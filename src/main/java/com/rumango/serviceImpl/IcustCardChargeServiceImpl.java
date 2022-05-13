package com.rumango.serviceImpl;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustCardChargeDetails;
import com.rumango.model.IcustCardChargeListModel;
import com.rumango.model.IcustCardChargeModel;
import com.rumango.repository.IcustCardChargeRepo;
import com.rumango.service.IcustCardChargeService;

@Service
public class IcustCardChargeServiceImpl implements IcustCardChargeService {

	private static final Logger logger= LogManager.getLogger(IcustCardChargeServiceImpl.class);
	
	@Autowired
	IcustCardChargeRepo icustCardChargeRepo;
	
	ModelMapper mapper = new ModelMapper();
	
	@Override
	public ResponseEntity<?> upsertCardChargeDetails(IcustCardChargeListModel icustCardChargeModel) {
		try {
			List<IcustCardChargeDetails> chargeDetails = new LinkedList<>();
			Iterator<IcustCardChargeModel> iterator = icustCardChargeModel.getChargeInfo().iterator();
			Optional<IcustCardChargeDetails> updateChargeDetails = null;
			while (iterator.hasNext()) {
				IcustCardChargeModel cardChargeModel = (IcustCardChargeModel) iterator.next();
				if (cardChargeModel.getCardId() == null)
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CardId is Mandatory");
				else {
					if (cardChargeModel.getChargeDetailsId() != null) {
						updateChargeDetails = icustCardChargeRepo.findById(cardChargeModel.getChargeDetailsId());
					}
					IcustCardChargeDetails chargeData = new Gson().fromJson(new Gson().toJson(cardChargeModel),
							IcustCardChargeDetails.class);
					if (updateChargeDetails != null && updateChargeDetails.isPresent()) {
						logger.info(updateChargeDetails.get());
						validatorChargeDetails(updateChargeDetails.get(), chargeData);
						chargeDetails.add(updateChargeDetails.get());
					} else {
						chargeDetails.add(mapper.map(cardChargeModel, IcustCardChargeDetails.class));
					}
				}
			}
			logger.info("Saving charge details " + chargeDetails);
			return ResponseEntity.status(HttpStatus.OK).body(icustCardChargeRepo.saveAll(chargeDetails));
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertCardChargeDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	private void validatorChargeDetails(IcustCardChargeDetails oldDetails, IcustCardChargeDetails newDetails) {
		if(newDetails.getCardId()!=null) {
			oldDetails.setCardId(newDetails.getCardId());
		}
		if(!Strings.isNullOrEmpty(newDetails.getIntrestType())) {
			oldDetails.setIntrestType(newDetails.getIntrestType());
		}
		if(newDetails.getAmount()!=0.0) {
			logger.info(newDetails.getAmount());
			oldDetails.setAmount(newDetails.getAmount());
			logger.info(oldDetails.getAmount());
		}
		if(newDetails.getWaiver()!=null) {
			oldDetails.setWaiver(newDetails.getWaiver());
		}
	}

	@Override
	public ResponseEntity<?> fetchCardChargeDetails() {
		try {
			List<IcustCardChargeDetails> list=icustCardChargeRepo.findAll();
			if(!CollectionUtils.isEmpty(list)) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			}else {
				logger.error("No Details found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Details Found");
			}
		}catch (Exception e) {
			
			logger.error("Execption occure while fetchCardChargeDetails",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchCardChargeDetailsById(Long cardId) {
		try {
			List<IcustCardChargeDetails> chargeList = icustCardChargeRepo.findByCardId(cardId);
			if (!CollectionUtils.isEmpty(chargeList)) {

				return ResponseEntity.status(HttpStatus.OK).body(chargeList);
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchCardChargeDetailsById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
