package com.rumango.serviceImpl;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustCardPreferences;
import com.rumango.model.IcustCardPreferencesModel;
import com.rumango.model.IcustLoanInterestModel;
import com.rumango.model.IcustResponseModel;
import com.rumango.repository.IcustCardPreferencesRepo;
import com.rumango.service.IcustCardPreferencesService;

@Service
public class IcustCardPreferencesServiceImpl implements IcustCardPreferencesService {
	private static final Logger logger = Logger.getLogger(IcustCardPreferencesServiceImpl.class);

	@Autowired
	IcustCardPreferencesRepo cardPreferencesRepo;

	@Autowired
	ModelMapper mapper;

	@Override
	public ResponseEntity<?> upsertCardPreferenceDetails(List<IcustCardPreferencesModel> cardPreferencesModel) {
		try {
			List<IcustCardPreferences> preferenceDetails = new LinkedList<>();
			Iterator<IcustCardPreferencesModel> iterator = cardPreferencesModel.iterator();
			Optional<IcustCardPreferences> updatePreferenceDetails = null;
			while (iterator.hasNext()) {
				IcustCardPreferencesModel preferenceModel = (IcustCardPreferencesModel) iterator.next();
				if (preferenceModel.getCardId() == null)
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CardId is Mandatory");
				if (preferenceModel.getPreferenceId() != null) {
					updatePreferenceDetails = cardPreferencesRepo.findById(preferenceModel.getPreferenceId());
				}
				IcustCardPreferences preferenceData = new Gson().fromJson(new Gson().toJson(preferenceModel),
						IcustCardPreferences.class);
				if (updatePreferenceDetails != null && updatePreferenceDetails.isPresent()) {
					validateCardPreferenceDetails(updatePreferenceDetails.get(), preferenceData);
					preferenceDetails.add(updatePreferenceDetails.get());
				} else {
					preferenceDetails.add(mapper.map(preferenceModel, IcustCardPreferences.class));
				}
			}
			logger.info("Saving loanInterest details " + preferenceDetails);
			return ResponseEntity.status(HttpStatus.OK).body(cardPreferencesRepo.saveAll(preferenceDetails));
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertCardPreferenceDetails", e.getMessage()),
					e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private void validateCardPreferenceDetails(IcustCardPreferences dataFromDb, IcustCardPreferences newObj) {
		if (newObj.getCardId() != null)
			dataFromDb.setCardId(newObj.getCardId());
		if (!Strings.isNullOrEmpty(newObj.getLimitType()))
			dataFromDb.setLimitType(newObj.getLimitType());
		if (newObj.getDailyLimit() != null)
			dataFromDb.setDailyLimit(newObj.getDailyLimit());
		if (newObj.getLimitPerTransaction() != null)
			dataFromDb.setLimitPerTransaction(newObj.getLimitPerTransaction());
	}

	@Override
	public ResponseEntity<?> fetchCardPreferenceByCardId(Long cardId) {
		try {
			List<IcustCardPreferences> cardPreferenceList = cardPreferencesRepo.findByCardId(cardId);
			if (!CollectionUtils.isEmpty(cardPreferenceList)) {
				List<IcustCardPreferencesModel> preferenceInfo = mapper.map(cardPreferenceList, new TypeToken<List<IcustCardPreferencesModel>>() {}.getType());
				return ResponseEntity.status(HttpStatus.OK).body(preferenceInfo);
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchCardPreferenceByCardId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> deleteCardPreference(Long id) {
		IcustResponseModel resp = new IcustResponseModel();
		try {
			Optional<IcustCardPreferences> currencyPairObj = cardPreferencesRepo.findById(id);
			if (currencyPairObj.isPresent()) {
				cardPreferencesRepo.deleteById(id);
				resp.setStatus(200);
				resp.setMessage("Record Deleted successfully");
				return ResponseEntity.status(HttpStatus.OK).body(resp);
			} else {
				resp.setStatus(204);
				resp.setMessage("No record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resp);
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing deleteCardPreference", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
