package com.rumango.serviceImpl;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustFinancialDetails;
import com.rumango.model.IcustFinancialDetailsModel;
import com.rumango.repository.IcustFinancialDetailsRepo;
import com.rumango.service.IcustFinancialDetailsService;

@Service
public class IcustFinancialDetailsServiceImpl implements IcustFinancialDetailsService{
	private static final Logger logger = LogManager.getLogger(IcustFinancialDetailsServiceImpl.class);

	
	@Autowired
	IcustFinancialDetailsRepo icustFinancialDetailsRepo;
	
	@Override
	public ResponseEntity<?> upsertFinancialDetails(IcustFinancialDetailsModel icustFinancialDetailsModel) {
		try {
			if (icustFinancialDetailsModel.getLoanAccountId()==null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			else {
				Optional<IcustFinancialDetails> financialObj = icustFinancialDetailsRepo
						.findByLoanAccountId(icustFinancialDetailsModel.getLoanAccountId());
				IcustFinancialDetails financialData = new Gson().fromJson(new Gson().toJson(icustFinancialDetailsModel),
						IcustFinancialDetails.class);
				if (financialObj.isPresent()) {
					validateFinancialDetails(financialObj.get(),financialData);
					return ResponseEntity.status(HttpStatus.OK).body(icustFinancialDetailsRepo.save(financialObj.get()));
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(icustFinancialDetailsRepo.save(financialData));
				}
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertFinancialDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private void validateFinancialDetails(IcustFinancialDetails oldFinancialDetails,
			IcustFinancialDetails newFinancialDetails) {
		if(newFinancialDetails.getLoanAccountId()!=null)
			oldFinancialDetails.setLoanAccountId(newFinancialDetails.getLoanAccountId());
		if(!Strings.isNullOrEmpty(newFinancialDetails.getApplicantName()))
			oldFinancialDetails.setApplicantName(newFinancialDetails.getApplicantName());
		if(newFinancialDetails.getApplicantTotalExpense()!=null)
			oldFinancialDetails.setApplicantTotalExpense(newFinancialDetails.getApplicantTotalExpense());
		if(newFinancialDetails.getApplicantTotalIncome()!=null)
			oldFinancialDetails.setApplicantTotalIncome(newFinancialDetails.getApplicantTotalIncome());
		if(newFinancialDetails.getLastUpdatedon()!=null)
			oldFinancialDetails.setLastUpdatedon(newFinancialDetails.getLastUpdatedon());
		if(!Strings.isNullOrEmpty(newFinancialDetails.getEmploymentType()))
			oldFinancialDetails.setEmploymentType(newFinancialDetails.getEmploymentType());
		if(!Strings.isNullOrEmpty(newFinancialDetails.getEmploymentCategory()))
			oldFinancialDetails.setEmploymentCategory(newFinancialDetails.getEmploymentCategory());
		if(!Strings.isNullOrEmpty(newFinancialDetails.getEmployeeNumber()))
			oldFinancialDetails.setEmployeeNumber(newFinancialDetails.getEmployeeNumber());
		if(!Strings.isNullOrEmpty(newFinancialDetails.getOfficeName()))
			oldFinancialDetails.setOfficeName(newFinancialDetails.getOfficeName());
		if(!Strings.isNullOrEmpty(newFinancialDetails.getDesignation()))
			oldFinancialDetails.setDesignation(newFinancialDetails.getDesignation());
		if(newFinancialDetails.getEmploymentEndDate()!=null)
			oldFinancialDetails.setEmploymentStartDate(newFinancialDetails.getEmploymentStartDate());
		if(newFinancialDetails.getEmploymentEndDate()!=null)
			oldFinancialDetails.setEmploymentEndDate(newFinancialDetails.getEmploymentEndDate());
		if(newFinancialDetails.getSalary()!=null)
			oldFinancialDetails.setSalary(newFinancialDetails.getSalary());
		if(newFinancialDetails.getBusiness()!=null)
			oldFinancialDetails.setBusiness(newFinancialDetails.getBusiness());
		if(newFinancialDetails.getInterestIncome()!=null)
			oldFinancialDetails.setInterestIncome(newFinancialDetails.getInterestIncome());
		if(newFinancialDetails.getPension()!=null)
			oldFinancialDetails.setPension(newFinancialDetails.getPension());
		if(newFinancialDetails.getBonus()!=null)
			oldFinancialDetails.setBonus(newFinancialDetails.getBonus());
		if(newFinancialDetails.getMonthlyIncomeRentals()!=null)
			oldFinancialDetails.setMonthlyIncomeRentals(newFinancialDetails.getMonthlyIncomeRentals());
		if(newFinancialDetails.getCashGifts()!=null)
			oldFinancialDetails.setCashGifts(newFinancialDetails.getCashGifts());
		if(newFinancialDetails.getMonthlyIncomeOthers()!=null)
			oldFinancialDetails.setMonthlyIncomeOthers(newFinancialDetails.getMonthlyIncomeOthers());
		if(newFinancialDetails.getMonthlyIncomeTotal()!=null)
			oldFinancialDetails.setMonthlyIncomeTotal(newFinancialDetails.getMonthlyIncomeTotal());
		if(newFinancialDetails.getHousehold()!=null)
			oldFinancialDetails.setHousehold(newFinancialDetails.getHousehold());
		if(newFinancialDetails.getMedical()!=null)
			oldFinancialDetails.setMedical(newFinancialDetails.getMedical());
		if(newFinancialDetails.getEducation()!=null)
			oldFinancialDetails.setEducation(newFinancialDetails.getEducation());
		if(newFinancialDetails.getTravel()!=null)
			oldFinancialDetails.setTravel(newFinancialDetails.getTravel());
		if(newFinancialDetails.getVehicleMaintenance()!=null)
			oldFinancialDetails.setVehicleMaintenance(newFinancialDetails.getVehicleMaintenance());
		if(newFinancialDetails.getMonthlyExpenseRentals()!=null)
			oldFinancialDetails.setMonthlyExpenseRentals(newFinancialDetails.getMonthlyExpenseRentals());
		if(newFinancialDetails.getMonthlyExpenseOthers()!=null)
			oldFinancialDetails.setMonthlyExpenseOthers(newFinancialDetails.getMonthlyExpenseOthers());
		if(newFinancialDetails.getMonthlyExpenseTotal()!=null)
			oldFinancialDetails.setMonthlyExpenseTotal(newFinancialDetails.getMonthlyExpenseTotal());
		if(newFinancialDetails.getPropertyLoan()!=null)
			oldFinancialDetails.setPropertyLoan(newFinancialDetails.getPropertyLoan());
		if(newFinancialDetails.getVehicleLoan()!=null)
			oldFinancialDetails.setVehicleLoan(newFinancialDetails.getVehicleLoan());
		if(newFinancialDetails.getPersonalLoans()!=null)
			oldFinancialDetails.setPersonalLoans(newFinancialDetails.getPersonalLoans());
		if(newFinancialDetails.getCardOutstandings()!=null)
			oldFinancialDetails.setCardOutstandings(newFinancialDetails.getCardOutstandings());
		if(newFinancialDetails.getOverdraft()!=null)
			oldFinancialDetails.setOverdraft(newFinancialDetails.getOverdraft());
		if(newFinancialDetails.getLiabilityOthers()!=null)
			oldFinancialDetails.setLiabilityOthers(newFinancialDetails.getLiabilityOthers());
		if(newFinancialDetails.getLiabilityTotal()!=null)
			oldFinancialDetails.setLiabilityTotal(newFinancialDetails.getLiabilityTotal());
		if(newFinancialDetails.getSavingDeposits()!=null)
			oldFinancialDetails.setSavingDeposits(newFinancialDetails.getSavingDeposits());
		if(newFinancialDetails.getStocks()!=null)
			oldFinancialDetails.setStocks(newFinancialDetails.getStocks());
		if(newFinancialDetails.getProperties()!=null)
			oldFinancialDetails.setProperties(newFinancialDetails.getProperties());
		if(newFinancialDetails.getAutomobiles()!=null)
			oldFinancialDetails.setAutomobiles(newFinancialDetails.getAutomobiles());
		if(newFinancialDetails.getFixedDeposits()!=null)
			oldFinancialDetails.setFixedDeposits(newFinancialDetails.getFixedDeposits());
		if(newFinancialDetails.getLands()!=null)
			oldFinancialDetails.setLands(newFinancialDetails.getLands());
		if(newFinancialDetails.getAssetOthers()!=null)
			oldFinancialDetails.setAssetOthers(newFinancialDetails.getAssetOthers());
		if(newFinancialDetails.getAssetTotal()!=null)
			oldFinancialDetails.setAssetTotal(newFinancialDetails.getAssetTotal());
		if(newFinancialDetails.getTotalIncome()!=null)
			oldFinancialDetails.setTotalIncome(newFinancialDetails.getTotalIncome());
		if(!Strings.isNullOrEmpty(newFinancialDetails.getAccountId()))
			oldFinancialDetails.setAccountId(newFinancialDetails.getAccountId());
		oldFinancialDetails.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
	}

	@Override
	public ResponseEntity<?> fetchFinancialDetails(Long loanAccountId) {
		try {
			if (loanAccountId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanAccountId is Mandatory");
			
			Optional<IcustFinancialDetails> financialObj = icustFinancialDetailsRepo.findByLoanAccountId(loanAccountId);
			if (financialObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(financialObj.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchAssetDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
