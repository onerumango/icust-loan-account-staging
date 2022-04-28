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
import com.rumango.entity.IcustCardInterestDetails;
import com.rumango.model.IcustCardInterestModel;
import com.rumango.repository.IcustCardInterestRepo;
import com.rumango.service.IcustCardInterestService;

@Service
public class IcustCardInterestServiceImpl implements IcustCardInterestService {

	private static final Logger logger = LogManager.getLogger(IcustCardInterestServiceImpl.class);

	@Autowired
	IcustCardInterestRepo cardInterestRepo;

	ModelMapper mapper = new ModelMapper();

	@Override
	public ResponseEntity<?> upsertDetails(List<IcustCardInterestModel> cardInterestModel) {
		try {
			List<IcustCardInterestDetails> interestDetails = new LinkedList<>();
			Iterator<IcustCardInterestModel> iterator = cardInterestModel.iterator();
			Optional<IcustCardInterestDetails> updateInterestDetails = null;
			while (iterator.hasNext()) {
				IcustCardInterestModel icCardInterestModel = (IcustCardInterestModel) iterator.next();
				if (icCardInterestModel.getCardId() == null)
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CardId is Mandatory");
				if (Strings.isNullOrEmpty(icCardInterestModel.getIntrestType()))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Interest Type is Mandatory");
				if (icCardInterestModel.getIntrestRateApplicable() == null)
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Interest rate applicable is Mandatory");
				if (icCardInterestModel.getEffectiveRate() == null)
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Effective rate is Mandatory");
				if (icCardInterestModel.getCardInterestId() != null) {
					updateInterestDetails = cardInterestRepo.findById(icCardInterestModel.getCardInterestId());
				}
				IcustCardInterestDetails interestData = new Gson().fromJson(new Gson().toJson(icCardInterestModel),
						IcustCardInterestDetails.class);
				if (updateInterestDetails != null && updateInterestDetails.isPresent()) {
					validateEntityDetails(updateInterestDetails.get(), interestData);
					interestDetails.add(updateInterestDetails.get());
				} else {
					interestDetails.add(mapper.map(icCardInterestModel, IcustCardInterestDetails.class));
				}
			}
			logger.info("Saving cardInterest details " + interestDetails);
			return ResponseEntity.status(HttpStatus.OK).body(cardInterestRepo.saveAll(interestDetails));
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private void validateEntityDetails(IcustCardInterestDetails oldInterestDetail,
			IcustCardInterestDetails newInterestDetail) {
		if (newInterestDetail.getCardId() != null)
			oldInterestDetail.setCardId(newInterestDetail.getCardId());
		if (!Strings.isNullOrEmpty(newInterestDetail.getIntrestType()))
			oldInterestDetail.setIntrestType(newInterestDetail.getIntrestType());
		if (newInterestDetail.getIntrestRateApplicable() != null)
			oldInterestDetail.setIntrestRateApplicable(newInterestDetail.getIntrestRateApplicable());
		if (newInterestDetail.getMarginIn() != null)
			oldInterestDetail.setMarginIn(newInterestDetail.getMarginIn());
		if (newInterestDetail.getEffectiveRate() != null)
			oldInterestDetail.setEffectiveRate(newInterestDetail.getEffectiveRate());
	}

	@Override
	public ResponseEntity<?> fetchCardInterestDetails() {
		try {
			List<IcustCardInterestDetails> list = cardInterestRepo.findAll();
			if (list != null) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("No base denomination details found");
			}

		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while fetchCardInterestDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchCardInterestById(Long cardId) {
		try {
			List<IcustCardInterestDetails> cardInterestList = cardInterestRepo.findByCardId(cardId);
			if (!CollectionUtils.isEmpty(cardInterestList)) {

				return ResponseEntity.status(HttpStatus.OK).body(cardInterestList);
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchCardInterestById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
}
