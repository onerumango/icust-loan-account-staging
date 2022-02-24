package com.rumango.serviceImpl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustLoanInterestDetails;
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
	public ResponseEntity<?> upsertDetails(IcustLoanInterestModel loanInterestModel) {
		try {
			if (loanInterestModel.getLoanAccountId() == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanId is Mandatory");
			else {
				Optional<IcustLoanInterestDetails> interestObj = loanInterestRepo
						.findByLoanAccountId(loanInterestModel.getLoanAccountId());
				IcustLoanInterestDetails interestData = new Gson().fromJson(new Gson().toJson(loanInterestModel),
						IcustLoanInterestDetails.class);
				if (interestObj.isPresent()) {
					validateEntityDetails(interestObj.get(), interestData);
					return ResponseEntity.status(HttpStatus.OK).body(loanInterestRepo.save(interestObj.get()));
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(loanInterestRepo.save(interestData));
				}
			}
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
			Optional<IcustLoanInterestDetails> icState = loanInterestRepo.findById(loanId);
			IcustLoanInterestModel loanInterestModel = new IcustLoanInterestModel();
			if (icState.isPresent()) {
				mapper.map(icState.get(), loanInterestModel);

				return ResponseEntity.status(HttpStatus.OK).body(loanInterestModel);
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