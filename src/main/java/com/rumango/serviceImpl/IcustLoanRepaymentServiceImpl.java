package com.rumango.serviceImpl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.rumango.entity.IcustLoanInfo;
import com.rumango.entity.IcustLoanInterestDetails;
import com.rumango.entity.IcustLoanRepaymentDetails;
import com.rumango.model.IcustLoanRepaymentModel;
import com.rumango.model.IcustRepaymentScheduleInfoModel;
import com.rumango.repository.IcustLoanInfoRepo;
import com.rumango.repository.IcustLoanInterestRepo;
import com.rumango.repository.IcustLoanRepaymentRepo;
import com.rumango.service.IcustLoanRepaymentService;
import com.rumango.service.IcustPaymentService;


@Service
public class IcustLoanRepaymentServiceImpl implements IcustLoanRepaymentService{

	private static final Logger logger= LogManager.getLogger(IcustLoanRepaymentServiceImpl.class);
	
	@Autowired
	IcustLoanRepaymentRepo icustLoanRepaymentRepo;
	@Autowired
	IcustLoanInfoRepo icustLoanInfoRepo;
	@Autowired
	IcustLoanInterestRepo loanInterestRepo;
	@Autowired
	IcustPaymentService icustPaymentService;
	
	@Override
	public ResponseEntity<?> upsertLoanRepaymentDeatils(IcustLoanRepaymentModel icustLoanRepaymentModel) {
		
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
		try {
			List<IcustLoanRepaymentDetails> list= icustLoanRepaymentRepo.findAll();
			if(!CollectionUtils.isEmpty(list)) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			}else {
				logger.info("no details found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(list);
			}
		}catch (Exception e) {
			logger.error("Execption occure while fetchLoanRepaymentDetails");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchLoanRepaymentDetailById(Long loanAccountId) {
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
			logger.error("Execption occure while fetchLoanRepaymentDetailById");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchRepaymentScheduleInfo(IcustLoanRepaymentModel icustLoanRepaymentModel) {
		try {
			  List<IcustRepaymentScheduleInfoModel> repaymentList = null;
			if(icustLoanRepaymentModel.getLoanAccountId()==null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("loanAccountId is Mandotory");
			}else {
				Optional<IcustLoanInfo> loanObj = icustLoanInfoRepo.findById(icustLoanRepaymentModel.getLoanAccountId());
				IcustLoanInterestDetails loanInterestInfo = loanInterestRepo.findByLoanAccountIdAndInterestType(icustLoanRepaymentModel.getLoanAccountId(),"Fixed Rate");
				if(loanObj.isPresent()) {
					repaymentList  = calculatePaymentList(icustLoanRepaymentModel.getFirstRepaymentDate(), loanObj.get().getLoanAmount(),
			        		icustLoanRepaymentModel.getLoanTenure(), 0, (loanInterestInfo!=null?loanInterestInfo.getInterestRateApplicable():0), 0);

				}
				return ResponseEntity.status(HttpStatus.OK).body(repaymentList);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();;
			logger.error(MessageFormat.format("Execption occcure while fetchRepaymentScheduleInfo", e.getMessage()),e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private List<IcustRepaymentScheduleInfoModel> calculatePaymentList(Date firstRepaymentDate, Double loanAmount,
			String loanTenure, int paymentType, Double interestRate, int k) {
		  List<IcustRepaymentScheduleInfoModel> paymentList = new ArrayList<IcustRepaymentScheduleInfoModel>();
	        Date loopDate = firstRepaymentDate;
	        Double balance = loanAmount;
	        Double accumulatedInterest = 0.0;
	        for (int paymentNumber = 1; paymentNumber <= 12; paymentNumber++)
	        {
	            if (paymentType == 0)
	            {
	                loopDate = addOneMonth(loopDate);
	            }
	            Double principalPaid = loanAmount/12;
	            Double interest = loanAmount*(interestRate /100);
	            Double interestPaid =interest/12;
//	            double monthlyPayment = loanAmount * monthlyInterestRate /
//	                    (1 - 1 / Math.pow(1 + monthlyInterestRate, 12));
//	            System.err.println("monthlyPayment::"+monthlyPayment);
	            //Double principalPaid = icustPaymentService.principalPayment(icustPaymentService.getMonthlyInterestRate(interestRate), paymentNumber, 12, loanAmount, 0, 0);
	            //Double interestPaid = icustPaymentService.interestPayment(icustPaymentService.getMonthlyInterestRate(interestRate), paymentNumber, 12, loanAmount, 0, 0);
	          //  balance = balance + principalPaid;
	           // accumulatedInterest += interestPaid;

	            IcustRepaymentScheduleInfoModel schedule = new IcustRepaymentScheduleInfoModel();
	            schedule.setPaymentNumber(paymentNumber);
	            schedule.setPaymentDate(loopDate);
	            schedule.setPrincipalPaid(principalPaid);
	            schedule.setInterestPaid(interestPaid);
	            paymentList.add(schedule);

	            if (paymentType == 1)
	            {
	                loopDate = addOneMonth(loopDate);
	            }
	        }
	        return paymentList;
	}
	
	 private Date addOneMonth(Date date)
	    {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.MONTH, 1);
	        return cal.getTime();
		 
		
	    }

	
}
