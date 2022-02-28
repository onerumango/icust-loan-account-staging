package com.rumango.serviceImpl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustLoanRepaymentDetails;
import com.rumango.entity.IcustPostOfferAmendmentDetails;
import com.rumango.model.IcustPostOfferAmendmentModel;
import com.rumango.repository.IcustPostOfferAmendmentRepo;
import com.rumango.service.IcustPostOfferAmendmentService;

@Service
public class IcustPostOfferAmendmentServiceImpl implements IcustPostOfferAmendmentService{

	private static final Logger logger = Logger.getLogger(IcustPostOfferAmendmentServiceImpl.class);
	
	@Autowired
	IcustPostOfferAmendmentRepo icustPostOfferAmendmentRepo;

	@Override
	public ResponseEntity<?> upsertPostOfferAmendment(IcustPostOfferAmendmentModel icustPostOfferAmendmentModel) {
		// TODO Auto-generated method stub
		try {
			if (icustPostOfferAmendmentModel.getLoanAccountId()==null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			else {
				Optional<IcustPostOfferAmendmentDetails> obj = icustPostOfferAmendmentRepo
						.findByLoanAccountId(icustPostOfferAmendmentModel.getLoanAccountId());
				IcustPostOfferAmendmentDetails data = new Gson().fromJson(new Gson().toJson(icustPostOfferAmendmentModel),
						IcustPostOfferAmendmentDetails.class);
				if (obj.isPresent()) {
					validatePostOfferAmendmentDetails(obj.get(),data);
					return ResponseEntity.status(HttpStatus.OK).body(icustPostOfferAmendmentRepo.save(obj.get()));
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(icustPostOfferAmendmentRepo.save(data));
				}
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertPostOfferAmendment", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
private void validatePostOfferAmendmentDetails(IcustPostOfferAmendmentDetails oldDetails, IcustPostOfferAmendmentDetails newDetails) {
		
		if(!Strings.isNullOrEmpty(newDetails.getApplicantName()))
			oldDetails.setApplicantName(newDetails.getApplicantName());
		if(newDetails.getOfferIssueDate()!=null)
			oldDetails.setOfferIssueDate(newDetails.getOfferIssueDate());
		if(newDetails.getOfferExpiryDate()!=null)
			oldDetails.setOfferExpiryDate(newDetails.getOfferExpiryDate());
		if(newDetails.getOfferAmendDate()!=null)
			oldDetails.setOfferAmendDate(newDetails.getOfferAmendDate());
		if(newDetails.getApprovedLoanAmount()!=null)
			oldDetails.setApprovedLoanAmount(newDetails.getApprovedLoanAmount());
		if(newDetails.getLoanAmmountRecommended()!=null)
			oldDetails.setLoanAmmountRecommended(newDetails.getLoanAmmountRecommended());
		if(!Strings.isNullOrEmpty(newDetails.getLoanTenure()))
			oldDetails.setLoanTenure(newDetails.getLoanTenure());
		if(!Strings.isNullOrEmpty(newDetails.getInstallmentType()))
			oldDetails.setInstallmentType(newDetails.getInstallmentType());
		if(newDetails.getRateOfInterest()!=null)
			oldDetails.setRateOfInterest(newDetails.getRateOfInterest());
		if(newDetails.getMargin()!=null)
			oldDetails.setMargin(newDetails.getMargin());
		if(newDetails.getEffectiveRate()!=null)
			oldDetails.setEffectiveRate(newDetails.getEffectiveRate());
		
	}

@Override
public ResponseEntity<?> fetchPostOfferAmendmentDetails() {
	// TODO Auto-generated method stub
	try {
		List<IcustPostOfferAmendmentDetails> list= icustPostOfferAmendmentRepo.findAll();
		if(!CollectionUtils.isEmpty(list)) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}else {
			logger.info("no details found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(list);
		}
	}catch (Exception e) {
		// TODO: handle exception
		logger.error("Execption occure while fetchPostOfferAmendmentDetails");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
}

@Override
public ResponseEntity<?> fetchPostOfferAmendmentDetailsById(Long loanAccountId) {
	// TODO Auto-generated method stub
	try {
		if(loanAccountId==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("loanAccountId is mandatory");
		}else {
			Optional<IcustPostOfferAmendmentDetails> obj=icustPostOfferAmendmentRepo.findByLoanAccountId(loanAccountId);
			if(obj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(obj);
			}else {
				logger.error("No record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No record exist for given id");
			}
		}
		
	}catch (Exception e) {
		// TODO: handle exception
		logger.error("Execption occure while fetchPostOfferAmendmentDetailsById");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
}


}
