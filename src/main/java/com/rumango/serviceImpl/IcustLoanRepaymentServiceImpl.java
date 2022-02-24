package com.rumango.serviceImpl;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.rumango.entity.IcustAssetDetails;
import com.rumango.entity.IcustLoanRepaymentDetails;
import com.rumango.model.IcustLoanRepaymentModel;
import com.rumango.repository.IcustLoanRepaymentRepo;
import com.rumango.service.IcustLoanRepaymentService;


@Service
public class IcustLoanRepaymentServiceImpl implements IcustLoanRepaymentService{

	private static final Logger logger= LogManager.getLogger(IcustLoanRepaymentServiceImpl.class);
	
	@Autowired
	IcustLoanRepaymentRepo icustLoanRepaymentRepo;
	
	@Override
	public ResponseEntity<?> upsertLoanRepaymentDeatils(IcustLoanRepaymentModel icustLoanRepaymentModel) {
		// TODO Auto-generated method stub
		
		try {
			if(icustLoanRepaymentModel.getLoanAccountId()==null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("loanAccountId is Mandotory");
			}else {
				Optional<IcustLoanRepaymentDetails> loanRepaymentObj= 
						icustLoanRepaymentRepo.findByLoanAccountId(icustLoanRepaymentModel.getLoanAccountId());
				IcustLoanRepaymentDetails loadRepaymentData= 
						new Gson().fromJson(new Gson().toJson(icustLoanRepaymentModel), IcustLoanRepaymentDetails.class);
				if(loanRepaymentObj.isPresent()) {
					validateLoanRepaymentDetails(loanRepaymentObj.get(), loadRepaymentData);
					return ResponseEntity.status(HttpStatus.OK).body(icustLoanRepaymentRepo.save(loanRepaymentObj.get()));
				}else {
					return ResponseEntity.status(HttpStatus.OK).body(icustLoanRepaymentRepo.save(loadRepaymentData));
				}
			}
			
		}catch(Exception e) {
			logger.error(MessageFormat.format("Execption occcure while upsertLoanRepaymentDetails", e.getMessage()),e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	private void validateLoanRepaymentDetails(IcustLoanRepaymentDetails oldDetails, IcustLoanRepaymentDetails newDetails) {
		
		if(newDetails.getLoanAccountId()!=null) {
			oldDetails.setLoanAccountId(newDetails.getLoanAccountId());
		}
		if(!Strings.isEmpty(newDetails.getTypeOfRepayment())) {
			oldDetails.setTypeOfRepayment(newDetails.getTypeOfRepayment());
		}
		if(!Strings.isEmpty(newDetails.getRepaymentFrequency())) {
			oldDetails.setRepaymentFrequency(newDetails.getRepaymentFrequency());
		}
		if(newDetails.getFirstRepaymentDate()!=null) {
			oldDetails.setFirstRepaymentDate(newDetails.getFirstRepaymentDate());
		}
		if(!Strings.isEmpty(newDetails.getLoanTenure())) {
			oldDetails.setLoanTenure(newDetails.getLoanTenure());
		}
		if(newDetails.getMaturityDate()!=null) {
			oldDetails.setMaturityDate(newDetails.getMaturityDate());
		}
		if(!Strings.isEmpty(newDetails.getRepaymentMode())) {
			oldDetails.setRepaymentMode(newDetails.getRepaymentMode());
		}
		if(newDetails.getMoraturioumPeriod()!=null) {
			oldDetails.setMoraturioumPeriod(newDetails.getMoraturioumPeriod());
		}
		if(!Strings.isEmpty(newDetails.getCustomerAccount())) {
			oldDetails.setCustomerAccount(newDetails.getCustomerAccount());
		}
		if(!Strings.isEmpty(newDetails.getBranchCode())) {
			oldDetails.setBranchCode(newDetails.getBranchCode());
		}
		
	}

	@Override
	public ResponseEntity<?> fetchLoanRepaymentDetails() {
		// TODO Auto-generated method stub
		try {
			List<IcustLoanRepaymentDetails> list= icustLoanRepaymentRepo.findAll();
			if(!CollectionUtils.isEmpty(list)) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			}else {
				logger.info("no details found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(list);
			}
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Execption occure while fetchLoanRepaymentDetails");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchLoanRepaymentDetailById(Long loanAccountId) {
		// TODO Auto-generated method stub
		try {
			if(loanAccountId==null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("loanAccountId is mandatory");
			}else {
				Optional<IcustLoanRepaymentDetails> obj=icustLoanRepaymentRepo.findByLoanAccountId(loanAccountId);
				if(obj.isPresent()) {
					return ResponseEntity.status(HttpStatus.OK).body(obj.get());
				}else {
					logger.error("No record exist for given id");
					return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No record exist for given id");
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Execption occure while fetchLoanRepaymentDetailById");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
