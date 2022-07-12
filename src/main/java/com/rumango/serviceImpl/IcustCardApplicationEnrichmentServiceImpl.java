package com.rumango.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rumango.entity.IcustCardChargeDetails;
import com.rumango.entity.IcustCardInterestDetails;
import com.rumango.model.IcustApplicationEnrichmentCardSummaryModel;
import com.rumango.model.IcustCardChargeModel;
import com.rumango.model.IcustCardInterestModel;
import com.rumango.repository.IcustCardChargeRepo;
import com.rumango.repository.IcustCardInterestRepo;
import com.rumango.service.IcustCardApplicationEnrichmentService;

@Service
public class IcustCardApplicationEnrichmentServiceImpl implements IcustCardApplicationEnrichmentService {
	
	private static final Logger logger = Logger.getLogger(IcustCardApplicationEnrichmentServiceImpl.class);

	@Autowired
	IcustCardInterestRepo cardInterestRepo;
	
	@Autowired
	IcustCardChargeRepo icustCardChargeRepo;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public ResponseEntity<?> fetchCardSummaryInfo(Long cardAccountId) {
		IcustApplicationEnrichmentCardSummaryModel summaryModel = new IcustApplicationEnrichmentCardSummaryModel();
		try {
			if (cardAccountId != null) {
				List<IcustCardInterestDetails> cardInterestList = cardInterestRepo.findByCardId(cardAccountId);
				if (!CollectionUtils.isEmpty(cardInterestList)) {
					List<IcustCardInterestModel> interestInfo = mapper.map(cardInterestList,
							new TypeToken<List<IcustCardInterestModel>>() {
							}.getType());
					summaryModel.setIntererstInfo(interestInfo);
				}
				List<IcustCardChargeDetails> chargeList = icustCardChargeRepo.findByCardId(cardAccountId);
				if (!CollectionUtils.isEmpty(chargeList)) {
					List<IcustCardChargeModel> interestInfo = mapper.map(chargeList,
							new TypeToken<List<IcustCardChargeModel>>() {
							}.getType());
					summaryModel.setChargeInfo(interestInfo);

				}
				return ResponseEntity.status(HttpStatus.OK).body(summaryModel);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("CardAccountId is mandatory");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchSummaryInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
