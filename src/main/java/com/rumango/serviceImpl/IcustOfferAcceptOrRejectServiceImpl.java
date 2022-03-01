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
import com.rumango.entity.IcustLoanOfferAcceptOrRejectDetails;
import com.rumango.entity.IcustOfferIssue;
import com.rumango.entity.IcustPostOfferAmendmentDetails;
import com.rumango.model.IcustLoanOfferAcceptOrRejectModel;
import com.rumango.repository.IcustOfferAcceptOrRejectRepo;
import com.rumango.service.IcustOfferAcceptOrRejectService;

@Service
public class IcustOfferAcceptOrRejectServiceImpl implements IcustOfferAcceptOrRejectService{

	private static final Logger logger= LogManager.getLogger(IcustOfferAcceptOrRejectServiceImpl.class);
	
	@Autowired
	IcustOfferAcceptOrRejectRepo icustOfferAcceptOrRejectRepo;

	
	@Override
	public ResponseEntity<?> upsertOfferAcceptOrReject(
			IcustLoanOfferAcceptOrRejectModel icustLoanOfferAcceptOrRejectModel) {
		// TODO Auto-generated method stub
		try {
			if (icustLoanOfferAcceptOrRejectModel.getLoanAccountId()==null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			}
			else {
				Optional<IcustLoanOfferAcceptOrRejectDetails> obj= icustOfferAcceptOrRejectRepo
						.findByLoanAccountId(icustLoanOfferAcceptOrRejectModel.getLoanAccountId());
				IcustLoanOfferAcceptOrRejectDetails data = new Gson().fromJson(new Gson().toJson(icustLoanOfferAcceptOrRejectModel),
						IcustLoanOfferAcceptOrRejectDetails.class);
				if(obj.isPresent()) {
					validateOfferAcceptOrRejectDetails(obj.get(),data);
					return ResponseEntity.status(HttpStatus.OK).body(icustOfferAcceptOrRejectRepo.save(obj.get()));
				}else {
					return ResponseEntity.status(HttpStatus.OK).body(icustOfferAcceptOrRejectRepo.save(data));
				}
			}
		}catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertOfferAcceptOrReject", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
private void validateOfferAcceptOrRejectDetails(IcustLoanOfferAcceptOrRejectDetails oldOfferIssue, IcustLoanOfferAcceptOrRejectDetails newOfferIssue) {
		
		if(!Strings.isNullOrEmpty(newOfferIssue.getApplicantName()))
			oldOfferIssue.setApplicantName(newOfferIssue.getApplicantName());
		if(newOfferIssue.getApprovedLoanAmount()!=null)
			oldOfferIssue.setApprovedLoanAmount(newOfferIssue.getApprovedLoanAmount());
		if(!Strings.isNullOrEmpty(newOfferIssue.getLoanTenure()))
			oldOfferIssue.setLoanTenure(newOfferIssue.getLoanTenure());
		if(!Strings.isNullOrEmpty(newOfferIssue.getInstallmentType()))
			oldOfferIssue.setInstallmentType(newOfferIssue.getInstallmentType());
		if(!Strings.isNullOrEmpty(newOfferIssue.getInstallmentFrequency()))
			oldOfferIssue.setInstallmentFrequency(newOfferIssue.getInstallmentFrequency());
		if(newOfferIssue.getRateOfInterest()!=null)
			oldOfferIssue.setRateOfInterest(newOfferIssue.getRateOfInterest());
		if(newOfferIssue.getPrincipal()!=null)
			oldOfferIssue.setPrincipal(newOfferIssue.getPrincipal());
		if(newOfferIssue.getInterest()!=null)
			oldOfferIssue.setInterest(newOfferIssue.getInterest());
		if(newOfferIssue.getInstallmentAmount()!=null)
			oldOfferIssue.setInstallmentAmount(newOfferIssue.getInstallmentAmount());
		if(newOfferIssue.getCharges()!=null)
			oldOfferIssue.setCharges(newOfferIssue.getCharges());
		if(newOfferIssue.getOfferIssueDate()!=null)
			oldOfferIssue.setOfferIssueDate(newOfferIssue.getOfferIssueDate());
		if(newOfferIssue.getGenerateOffer()!=null)
			oldOfferIssue.setGenerateOffer(newOfferIssue.getGenerateOffer());
		if(!Strings.isNullOrEmpty(newOfferIssue.getAccept()))
			oldOfferIssue.setAccept(newOfferIssue.getAccept());
		if(newOfferIssue.getGenerateOffer()!=null)
			oldOfferIssue.setGenerateOffer(newOfferIssue.getGenerateOffer());
	}

	@Override
	public ResponseEntity<?> fetchOfferAcceptOrRejectDetails() {
		// TODO Auto-generated method stub
		try {
			List<IcustLoanOfferAcceptOrRejectDetails> list= icustOfferAcceptOrRejectRepo.findAll();
			if(!CollectionUtils.isEmpty(list)) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			}else {
				logger.info("no details found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(list);
			}
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Execption occure while fetchOfferAcceptOrRejectDetails");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchOfferAcceptOrRejectDetailsById(Long loanAccountId) {
		// TODO Auto-generated method stub
		try {
			if(loanAccountId==null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("loanAccountId is mandatory");
			}else {
				Optional<IcustLoanOfferAcceptOrRejectDetails> obj= icustOfferAcceptOrRejectRepo.findByLoanAccountId(loanAccountId);
				if(obj.isPresent()) {
					return ResponseEntity.status(HttpStatus.OK).body(obj);
				}else {
					logger.error("No record exist for given id");
					return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No record exist for given id");
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Execption occure while fetchOfferAcceptOrRejectDetailsById");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}


}
