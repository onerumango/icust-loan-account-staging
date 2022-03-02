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
import com.rumango.entity.IcustAccountServices;
import com.rumango.model.IcustAccountServicesModel;
import com.rumango.repository.IcustAccountServicesRepo;
import com.rumango.service.IcustAccountServicesService;

@Service
public class IcustAccountServicesServiceImpl implements IcustAccountServicesService{
	private static final Logger logger = Logger.getLogger(IcustAccountServicesServiceImpl.class);

	@Autowired
	IcustAccountServicesRepo accountServicesRepo;
	@Override
	public ResponseEntity<?> upsertAccountServices(IcustAccountServicesModel icustAccountServicesModel) {
		try {
			if (icustAccountServicesModel.getLoanAccountId()==null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			else {
				Optional<IcustAccountServices> accServicesObj = accountServicesRepo
						.findByLoanAccountId(icustAccountServicesModel.getLoanAccountId());
				IcustAccountServices servicesData = new Gson().fromJson(new Gson().toJson(icustAccountServicesModel),
						IcustAccountServices.class);
				if (accServicesObj.isPresent()) {
					validateAccountServicesDetails(accServicesObj.get(),servicesData);
					return ResponseEntity.status(HttpStatus.OK).body(accountServicesRepo.save(accServicesObj.get()));
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(accountServicesRepo.save(servicesData));
				}
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertOfferIssue", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	
	private void validateAccountServicesDetails(IcustAccountServices oldAccServices,
			IcustAccountServices newAccServices) {
		
		if(!Strings.isNullOrEmpty(newAccServices.getStatementCycle()))
			oldAccServices.setStatementCycle(newAccServices.getStatementCycle());
		if(newAccServices.getStartDate()!=null)
			oldAccServices.setStartDate(newAccServices.getStartDate());
		if(!Strings.isNullOrEmpty(newAccServices.getStatementType()))
			oldAccServices.setStatementType(newAccServices.getStatementType());
		if(newAccServices.getServiceDetails()!=null)
			oldAccServices.setServiceDetails(newAccServices.getServiceDetails());

	}


	@Override
	public ResponseEntity<?> fetchAccountServicesByLoanAccountId(Long loanAccountId) {
		try {
			if (loanAccountId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			
			Optional<IcustAccountServices> servicesObj = accountServicesRepo.findByLoanAccountId(loanAccountId);
			if (servicesObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(servicesObj.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchAccountServicesByLoanAccountId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchAccountServicesById(Long id) {
		try {
			if (id == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is Mandatory");
			
			Optional<IcustAccountServices> servicesObj = accountServicesRepo.findById(id);
			if (servicesObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(servicesObj.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchAccountServicesById", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	

}
