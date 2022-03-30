package com.rumango.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.rumango.entity.IcustAccountServices;
import com.rumango.entity.IcustLaonChargeDetails;
import com.rumango.entity.IcustLoanDisbursementDetails;
import com.rumango.entity.IcustLoanInterestDetails;
import com.rumango.entity.IcustLoanRepaymentDetails;
import com.rumango.model.IcustAccountServicesModel;
import com.rumango.model.IcustApplicationEnrichmentSummaryModel;
import com.rumango.model.IcustAssetDetailsModel;
import com.rumango.model.IcustFinancialDetailsModel;
import com.rumango.model.IcustLoanChargeModel;
import com.rumango.model.IcustLoanDisbursementModel;
import com.rumango.model.IcustLoanInterestModel;
import com.rumango.model.IcustLoanRepaymentModel;
import com.rumango.repository.IcustAccountServicesRepo;
import com.rumango.repository.IcustLoanChargeRepo;
import com.rumango.repository.IcustLoanDisbursementRepo;
import com.rumango.repository.IcustLoanInterestRepo;
import com.rumango.repository.IcustLoanRepaymentRepo;
import com.rumango.service.IcustApplicationEnrichmentService;

@Service
public class IcustApplicationEnrichmentServiceImpl implements IcustApplicationEnrichmentService {
	private static final Logger logger = Logger.getLogger(IcustApplicationEnrichmentServiceImpl.class);

	@Autowired
	IcustLoanInterestRepo loanInterestRepo;
	@Autowired
	ModelMapper mapper;
	@Autowired
	IcustLoanDisbursementRepo icustLoanDisbursementRepo;
	@Autowired
	IcustLoanRepaymentRepo icustLoanRepaymentRepo;
	@Autowired
	IcustLoanChargeRepo icustLoanChargeRepo;
	@Autowired
	IcustAccountServicesRepo accountServicesRepo;

	@Override
	public ResponseEntity<?> fetchSummaryInfo(Long loanAccountId) {
		IcustApplicationEnrichmentSummaryModel summaryModel = new IcustApplicationEnrichmentSummaryModel();
		try {
			if (loanAccountId != null) {
				List<IcustLoanInterestDetails> loanInterestList = loanInterestRepo.findByLoanAccountId(loanAccountId);
				if (!CollectionUtils.isEmpty(loanInterestList)) {
					List<IcustLoanInterestModel> interestInfo = mapper.map(loanInterestList,
							new TypeToken<List<IcustLoanInterestModel>>() {
							}.getType());
					summaryModel.setIntererstInfo(interestInfo);
				}
				Optional<IcustLoanDisbursementDetails> loanDisbObj = icustLoanDisbursementRepo
						.findByLoanAccountId(loanAccountId);
				if (loanDisbObj.isPresent()) {
					IcustLoanDisbursementModel loanDisbInfo = new Gson().fromJson(new Gson().toJson(loanDisbObj.get()),
							IcustLoanDisbursementModel.class);
					summaryModel.setDisbursementInfo(loanDisbInfo);
				}
				Optional<IcustLoanRepaymentDetails> repaymentObj = icustLoanRepaymentRepo
						.findByLoanAccountId(loanAccountId);
				if (repaymentObj.isPresent()) {
					IcustLoanRepaymentModel repaymentInfo = new Gson().fromJson(new Gson().toJson(repaymentObj.get()),
							IcustLoanRepaymentModel.class);
					summaryModel.setRepaymentInfo(repaymentInfo);
				}
				List<IcustLaonChargeDetails> chargeList = icustLoanChargeRepo.findByLoanAccountId(loanAccountId);
				if (!CollectionUtils.isEmpty(chargeList)) {
					List<IcustLoanChargeModel> interestInfo = mapper.map(chargeList,
							new TypeToken<List<IcustLoanChargeModel>>() {
							}.getType());
					summaryModel.setChargeInfo(interestInfo);

				}
				Optional<IcustAccountServices> servicesObj = accountServicesRepo.findByLoanAccountId(loanAccountId);
				if (servicesObj.isPresent()) {
					IcustAccountServicesModel servicesInfo = new Gson().fromJson(new Gson().toJson(servicesObj.get()),
							IcustAccountServicesModel.class);
					summaryModel.setAccServiceInfo(servicesInfo);
				}
				return ResponseEntity.status(HttpStatus.OK).body(summaryModel);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("LoanAccountId is mandatory");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchSummaryInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
