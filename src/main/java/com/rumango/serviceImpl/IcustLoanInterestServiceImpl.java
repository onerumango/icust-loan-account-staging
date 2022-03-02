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
import com.rumango.entity.IcustLoanInterestDetails;
import com.rumango.model.IcustLoanInterestListModel;
import com.rumango.model.IcustLoanInterestModel;
import com.rumango.repository.IcustLoanInterestRepo;
import com.rumango.service.IcustLoanInterestService;

@Service
public class IcustLoanInterestServiceImpl implements IcustLoanInterestService {

	private static final Logger logger = LogManager.getLogger(IcustLoanInterestServiceImpl.class);

	@Autowired
	IcustLoanInterestRepo loanInterestRepo;

	ModelMapper mapper = new ModelMapper();

	@Override
	public ResponseEntity<?> upsertDetails(IcustLoanInterestListModel loanInterestModel) {
		try {
			List<IcustLoanInterestModel> loanModel = loanInterestModel.getInterestInfo();
			List<IcustLoanInterestDetails> interestDetails = new LinkedList<>();
			Iterator<IcustLoanInterestModel> iterator = loanModel.iterator();
			Optional<IcustLoanInterestDetails> updateInterestDetails = null;
			while (iterator.hasNext()) {
				IcustLoanInterestModel icLoanInterestModel = (IcustLoanInterestModel) iterator.next();
				if (icLoanInterestModel.getLoanAccountId() == null)
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
				else {
					if (icLoanInterestModel.getLoanInterestId() != null) {
						updateInterestDetails = loanInterestRepo.findById(icLoanInterestModel.getLoanInterestId());
					}
					IcustLoanInterestDetails interestData = new Gson().fromJson(new Gson().toJson(icLoanInterestModel),
							IcustLoanInterestDetails.class);
					if (updateInterestDetails != null && updateInterestDetails.isPresent()) {
						validateEntityDetails(updateInterestDetails.get(), interestData);
						interestDetails.add(updateInterestDetails.get());
					} else {
						interestDetails.add(mapper.map(icLoanInterestModel, IcustLoanInterestDetails.class));
					}
				}
			}
			logger.info("Saving loanInterest details " + interestDetails);
			return ResponseEntity.status(HttpStatus.OK).body(loanInterestRepo.saveAll(interestDetails));
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private void validateEntityDetails(IcustLoanInterestDetails oldInterestDetail,
			IcustLoanInterestDetails newInterestDetail) {
		if (newInterestDetail.getLoanAccountId() != null)
			oldInterestDetail.setLoanAccountId(newInterestDetail.getLoanAccountId());
		if (!Strings.isNullOrEmpty(newInterestDetail.getInterestType()))
			oldInterestDetail.setInterestType(newInterestDetail.getInterestType());
		if (newInterestDetail.getInterestRateApplicable() != null)
			oldInterestDetail.setInterestRateApplicable(newInterestDetail.getInterestRateApplicable());
		if (newInterestDetail.getMargin() != null)
			oldInterestDetail.setMargin(newInterestDetail.getMargin());
		if (newInterestDetail.getEffectiveRate() != null)
			oldInterestDetail.setEffectiveRate(newInterestDetail.getEffectiveRate());
	}

	@Override
	public ResponseEntity<?> fetchLoanInterestDetails() {
		try {
			List<IcustLoanInterestDetails> list = loanInterestRepo.findAll();
			if (list != null) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("No base denomination details found");
			}

		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while fetchLoanInterestDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchLoanInterestById(Long loanId) {
		try {
			List<IcustLoanInterestDetails> loanInterestList = loanInterestRepo.findByLoanAccountId(loanId);
			if (!CollectionUtils.isEmpty(loanInterestList)) {

				return ResponseEntity.status(HttpStatus.OK).body(loanInterestList);
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchLoanInterestById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}