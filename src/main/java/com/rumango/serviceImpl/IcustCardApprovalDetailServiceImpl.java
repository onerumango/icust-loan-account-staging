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
import com.rumango.entity.IcustCardApprovalDetails;
import com.rumango.model.IcustCardApprovalModel;
import com.rumango.repository.IcustCardApprovalDetailsRepo;
import com.rumango.service.IcustCardApprovalDetailService;

@Service
public class IcustCardApprovalDetailServiceImpl implements IcustCardApprovalDetailService {

	private static final Logger logger = Logger.getLogger(IcustCardApprovalDetailServiceImpl.class);

	@Autowired
	IcustCardApprovalDetailsRepo approvalDetailsRepo;

	@Override
	public ResponseEntity<?> upsertCardApprovalDetails(IcustCardApprovalModel icustCardApprovalModel) {
		try {
			if (icustCardApprovalModel.getCardId() == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CardId is Mandatory");
			else {
				Optional<IcustCardApprovalDetails> approvalObj = approvalDetailsRepo
						.findByCardId(icustCardApprovalModel.getCardId());
				IcustCardApprovalDetails approvalData = new Gson().fromJson(new Gson().toJson(icustCardApprovalModel),
						IcustCardApprovalDetails.class);
				if (approvalObj.isPresent()) {
					validateApprovalDetails(approvalObj.get(), approvalData);
					return ResponseEntity.status(HttpStatus.OK).body(approvalDetailsRepo.save(approvalObj.get()));
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(approvalDetailsRepo.save(approvalData));
				}
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertCardApprovalDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private void validateApprovalDetails(IcustCardApprovalDetails oldApproval, IcustCardApprovalDetails newApproval) {

		if (!Strings.isNullOrEmpty(newApproval.getAccountType()))
			oldApproval.setAccountType(newApproval.getAccountType());
		if (!Strings.isNullOrEmpty(newApproval.getAccountBranch()))
			oldApproval.setAccountBranch(newApproval.getAccountBranch());
		if (!Strings.isNullOrEmpty(newApproval.getProductCode()))
			oldApproval.setProductCode(newApproval.getProductCode());
		if (!Strings.isNullOrEmpty(newApproval.getProductName()))
			oldApproval.setProductName(newApproval.getProductName());
		if (newApproval.getExistingValues() != null)
			oldApproval.setExistingValues(newApproval.getExistingValues());
		if (!Strings.isNullOrEmpty(newApproval.getAccountCurrency()))
			oldApproval.setAccountCurrency(newApproval.getAccountCurrency());
		if (!Strings.isNullOrEmpty(newApproval.getUserRecommendation()))
			oldApproval.setUserRecommendation(newApproval.getUserRecommendation());
		if (!Strings.isNullOrEmpty(newApproval.getRemarks()))
			oldApproval.setRemarks(newApproval.getRemarks());
	}

	@Override
	public ResponseEntity<?> fetchApprovalDetails(Long cardId) {
		try {
			if (cardId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CardId is Mandatory");
			Optional<IcustCardApprovalDetails> cardObj = approvalDetailsRepo.findByCardId(cardId);

			if (cardObj.isPresent()) {
				IcustCardApprovalModel approvalData = new Gson().fromJson(new Gson().toJson(cardObj.get()),
						IcustCardApprovalModel.class);
				return ResponseEntity.status(HttpStatus.OK).body(approvalData);
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			e.printStackTrace();;
			logger.error("Execption occoured while executing fetchApprovalDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
}
