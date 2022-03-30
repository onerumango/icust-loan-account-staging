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
import com.rumango.entity.IcustAdmissionDetails;
import com.rumango.entity.IcustAssetDetails;
import com.rumango.entity.IcustCollateralMaster;
import com.rumango.entity.IcustFinancialDetails;
import com.rumango.entity.IcustGuarantorDetails;
import com.rumango.entity.IcustLoanInfo;
import com.rumango.entity.IcustMandateMaster;
import com.rumango.entity.IcustVehicleDetails;
import com.rumango.model.IcustAdmissionDetailsModel;
import com.rumango.model.IcustApplEntryStageSummaryModel;
import com.rumango.model.IcustAssetDetailsModel;
import com.rumango.model.IcustCollateralMasterModel;
import com.rumango.model.IcustFinancialDetailsModel;
import com.rumango.model.IcustGuarantorDetailsModel;
import com.rumango.model.IcustLoanInfoModel;
import com.rumango.model.IcustMandateMasterModel;
import com.rumango.model.IcustVehicleDetailsModel;
import com.rumango.repository.IcustAdmissionDetailsRepo;
import com.rumango.repository.IcustAssetDetailsRepo;
import com.rumango.repository.IcustCollateralMasterRepo;
import com.rumango.repository.IcustFinancialDetailsRepo;
import com.rumango.repository.IcustGuarantorDetailsRepo;
import com.rumango.repository.IcustLoanInfoRepo;
import com.rumango.repository.IcustMandateMasterRepo;
import com.rumango.repository.IcustVehicleDetailsRepo;
import com.rumango.service.IcustApplicationEntryStageService;

@Service
public class IcustApplicationEntryStageServiceImpl implements IcustApplicationEntryStageService {
	private static final Logger logger = Logger.getLogger(IcustApplicationEntryStageServiceImpl.class);

	@Autowired
	IcustLoanInfoRepo icustLoanInfoRepo;
	@Autowired
	IcustAssetDetailsRepo icustAssetDetailsRepo;
	@Autowired
	IcustVehicleDetailsRepo icustVehicleDetailsRepo;
	@Autowired
	IcustAdmissionDetailsRepo icustAdmissionDetailsRepo;
	@Autowired
	IcustMandateMasterRepo icustMandateMasterRepo;
	@Autowired
	IcustFinancialDetailsRepo icustFinancialDetailsRepo;
	@Autowired
	ModelMapper mapper;
	@Autowired
	IcustCollateralMasterRepo collateralMasterRepo;
	@Autowired
	IcustGuarantorDetailsRepo guarantorDetailsRepo;
	
	@Override
	public ResponseEntity<?> fetchSummaryInfo(Long loanAccountId) {
		IcustApplEntryStageSummaryModel summaryModel = new IcustApplEntryStageSummaryModel();
		try {
			if (loanAccountId != null) {
				Optional<IcustLoanInfo> loanObj = icustLoanInfoRepo.findById(loanAccountId);
				if (loanObj.isPresent()) {
					IcustLoanInfoModel loanInfo = new Gson().fromJson(new Gson().toJson(loanObj.get()),
							IcustLoanInfoModel.class);
					summaryModel.setLoanInfo(loanInfo);
				}
				Optional<IcustAssetDetails> assetObj = icustAssetDetailsRepo.findByLoanAccountId(loanAccountId);
				if (assetObj.isPresent()) {
					IcustAssetDetailsModel assetInfo = new Gson().fromJson(new Gson().toJson(assetObj.get()),
							IcustAssetDetailsModel.class);
					summaryModel.setAssetInfo(assetInfo);
				}
				Optional<IcustVehicleDetails> vehicleObj = icustVehicleDetailsRepo.findByLoanAccountId(loanAccountId);
				if (vehicleObj.isPresent()) {
					IcustVehicleDetailsModel vehicleInfo = new Gson().fromJson(new Gson().toJson(vehicleObj.get()),
							IcustVehicleDetailsModel.class);
					summaryModel.setVehicleInfo(vehicleInfo);
				}
				Optional<IcustAdmissionDetails> admissionObj = icustAdmissionDetailsRepo.findByLoanAccountId(loanAccountId);
				if (admissionObj.isPresent()) {
					IcustAdmissionDetailsModel admissionInfo = new Gson().fromJson(new Gson().toJson(admissionObj.get()),
							IcustAdmissionDetailsModel.class);
					summaryModel.setAdmissionInfo(admissionInfo);
				}
				Optional<IcustMandateMaster> mandateObj = icustMandateMasterRepo.findByLoanAccountId(loanAccountId);
				if (mandateObj.isPresent()) {
					IcustMandateMasterModel mandateInfo = new Gson().fromJson(new Gson().toJson(mandateObj.get()),
							IcustMandateMasterModel.class);
					summaryModel.setMandateInfo(mandateInfo);
				}
				List<IcustFinancialDetails> financialList = icustFinancialDetailsRepo.findByLoanAccountId(loanAccountId);
				if (!CollectionUtils.isEmpty(financialList)) {
					List<IcustFinancialDetailsModel> financialInfo = mapper.map(financialList, new TypeToken<List<IcustFinancialDetailsModel>>() {}.getType());
					summaryModel.setFinancialInfo(financialInfo);
				}
				Optional<IcustCollateralMaster> collateralObj = collateralMasterRepo.findByLoanAccountId(loanAccountId);
				if (collateralObj.isPresent()) {
					IcustCollateralMasterModel collateralInfo = new Gson().fromJson(new Gson().toJson(collateralObj.get()),
							IcustCollateralMasterModel.class);
					summaryModel.setCollateralInfo(collateralInfo);
				}
				Optional<IcustGuarantorDetails> guarantorObj = guarantorDetailsRepo.findByLoanAccountId(loanAccountId);
				if (guarantorObj.isPresent()) {
					IcustGuarantorDetailsModel guarantorInfo = new Gson().fromJson(new Gson().toJson(guarantorObj.get()),
							IcustGuarantorDetailsModel.class);
					summaryModel.setGuarantorInfo(guarantorInfo);
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
