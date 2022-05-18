package com.rumango.serviceImpl;

import static org.springframework.data.jpa.domain.Specification.where;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import javax.persistence.criteria.Root;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.constants.DataConstants;
import com.rumango.entity.IcustCardInitiation;
import com.rumango.entity.IcustCustomerDocuments;
import com.rumango.entity.IcustCustomerInfo;
import com.rumango.entity.IcustFinancialDetails;
import com.rumango.entity.IcustLaonChargeDetails;
import com.rumango.entity.IcustLoanInfo;
import com.rumango.entity.IcustLoanInterestDetails;
import com.rumango.entity.IcustLoanValuationOfAssetEntity;
import com.rumango.enums.CardInitiationStatus;
import com.rumango.enums.LoanAccStatusEnum;
import com.rumango.model.CardTaskSummaryDataModel;
import com.rumango.model.IcustAccountServicesModel;
import com.rumango.model.IcustAdmissionDetailsModel;
import com.rumango.model.IcustApprovalDetailsModel;
import com.rumango.model.IcustAssetDetailsModel;
import com.rumango.model.IcustCollateralMasterModel;
import com.rumango.model.IcustFinancialDetailsModel;
import com.rumango.model.IcustGuarantorDetailsModel;
import com.rumango.model.IcustLoanAccountSummaryModel;
import com.rumango.model.IcustLoanAssessmentDetailsModel;
import com.rumango.model.IcustLoanChargeModel;
import com.rumango.model.IcustLoanCreditRatingDetailsModel;
import com.rumango.model.IcustLoanDisbursementModel;
import com.rumango.model.IcustLoanInfoModel;
import com.rumango.model.IcustLoanInterestModel;
import com.rumango.model.IcustLoanLegalOpinionModel;
import com.rumango.model.IcustLoanRepaymentModel;
import com.rumango.model.IcustLoanValuationOfAssetModel;
import com.rumango.model.IcustMandateDetailsModel;
import com.rumango.model.IcustOfferIssueModel;
import com.rumango.model.IcustVehicleDetailsModel;
import com.rumango.model.LoanTaskSummaryDataModel;
import com.rumango.model.LoanTaskSummaryModel;
import com.rumango.model.CardTaskSummaryModel;
import com.rumango.repository.CustomerDocumentsRepo;
import com.rumango.repository.IcustCustomerInfoRepo;
import com.rumango.repository.IcustFinancialDetailsRepo;
import com.rumango.repository.IcustLoanChargeRepo;
import com.rumango.repository.IcustLoanInfoRepo;
import com.rumango.repository.IcustLoanInterestRepo;
import com.rumango.repository.IcustLoanValuationOfAssetRepo;
import com.rumango.service.IcustAccountServicesService;
import com.rumango.service.IcustAdmissionService;
import com.rumango.service.IcustApprovalDetailsService;
import com.rumango.service.IcustAssetService;
import com.rumango.service.IcustCollateralService;
import com.rumango.service.IcustFinancialDetailsService;
import com.rumango.service.IcustGuarantorService;
import com.rumango.service.IcustLoanAssessmentService;
import com.rumango.service.IcustLoanChargeService;
import com.rumango.service.IcustLoanCreditRatingService;
import com.rumango.service.IcustLoanDisbursementService;
import com.rumango.service.IcustLoanInterestService;
import com.rumango.service.IcustLoanLegalOpinionService;
import com.rumango.service.IcustLoanRepaymentService;
import com.rumango.service.IcustLoanService;
import com.rumango.service.IcustLoanTaskSummaryService;
import com.rumango.service.IcustLoanValuationOfAssetService;
import com.rumango.service.IcustMandateService;
import com.rumango.service.IcustOfferAcceptOrRejectService;
import com.rumango.service.IcustOfferIssueService;
import com.rumango.service.IcustVehicleService;

@Service
public class IcustLoanTaskSummaryServiceImpl implements IcustLoanTaskSummaryService {
	private static final Logger logger = Logger.getLogger(IcustLoanTaskSummaryServiceImpl.class);

	@Autowired
	private IcustLoanService icustLoanService;
	@Autowired
	IcustAssetService icustAssetService;
	@Autowired
	IcustVehicleService icustVehicleService;
	@Autowired
	IcustAdmissionService icustAdmissionService;
	@Autowired
	IcustMandateService icustMandateService;
	@Autowired
	IcustFinancialDetailsService financialDetailsService;
	@Autowired
	IcustCollateralService icustCollateralService;
	@Autowired
	IcustGuarantorService guarantorService;
	@Autowired
	IcustLoanInterestService service;
	@Autowired
	IcustLoanDisbursementService disbursementService;
	@Autowired
	IcustLoanRepaymentService repaymentService;
	@Autowired
	IcustLoanInterestService interestService;
	@Autowired
	IcustLoanChargeService chargeService;
	@Autowired
	IcustAccountServicesService accountServicesService;
	@Autowired
	ModelMapper mapper;
	@Autowired
	private IcustLoanCreditRatingService creditRatingService;
	@Autowired
	private IcustLoanValuationOfAssetService loanValuationOfAssetService;
	@Autowired
	private IcustLoanLegalOpinionService legalOpinionService;
	@Autowired
	private IcustLoanAssessmentService assessmentService;
	@Autowired
	IcustApprovalDetailsService approvalDetailsService;
	@Autowired
	IcustOfferIssueService icustOfferIssueService;
	@Autowired
	IcustOfferAcceptOrRejectService OfferAcceptOrRejectService;
	@Autowired
	IcustLoanInfoRepo loanRepo;
	@Autowired
	IcustCustomerInfoRepo customerRepo;
	@Autowired
	CustomerDocumentsRepo documentRepo;
	@Autowired
	IcustFinancialDetailsRepo icustFinancialDetailsRepo;
	@Autowired
	IcustLoanInterestRepo loanInterestRepo;
	@Autowired
	IcustLoanChargeRepo icustLoanChargeRepo;
	@Autowired
	private IcustLoanValuationOfAssetRepo loanValuationOfAssetRepo;

	
	@Override
	public ResponseEntity<?> fetchTaskSummaryInfo(Long loanAccountId) {
		IcustLoanAccountSummaryModel summaryInfo = new IcustLoanAccountSummaryModel();
		try {
			if (loanAccountId != null) {
				summaryInfo.setLoanDetailsInfo(mapper.map(icustLoanService.fetchLoanDetailsByLoanAccId(loanAccountId).getBody(), IcustLoanInfoModel.class));
				summaryInfo.setAssessInfo(mapper.map(icustAssetService.fetchAssetDetailsByLoanAccId(loanAccountId).getBody(), IcustAssetDetailsModel.class));
				summaryInfo.setVehicleInfo(mapper.map(icustVehicleService.fetchVehicleDetailsByLoanAccId(loanAccountId).getBody(), IcustVehicleDetailsModel.class));
				summaryInfo.setAdmissionInfo(mapper.map(icustAdmissionService.fetchAdmissionDetailsByLoanAccId(loanAccountId).getBody(), IcustAdmissionDetailsModel.class));
				summaryInfo.setMandateInfo(mapper.map(icustMandateService.fetchMandateDetailsByLoanAccId(loanAccountId).getBody(), IcustMandateDetailsModel.class));
				
				List<IcustFinancialDetails> financialList = icustFinancialDetailsRepo.findByLoanAccountId(loanAccountId);
				if (!CollectionUtils.isEmpty(financialList)) {
					List<IcustFinancialDetailsModel> financialInfo = mapper.map(financialList, new TypeToken<List<IcustFinancialDetailsModel>>() {}.getType());
					summaryInfo.setFinancialInfo(financialInfo);
				}
					
				summaryInfo.setCollateralInfo(mapper.map(icustCollateralService.fetchCollateralByLoanAccountId(loanAccountId).getBody(), IcustCollateralMasterModel.class));
				summaryInfo.setGuarantorInfo(mapper.map(guarantorService.fetchGuarantorByLoanAccId(loanAccountId).getBody(), IcustGuarantorDetailsModel.class));
				
				List<IcustLoanInterestDetails> loanInterestList = loanInterestRepo.findByLoanAccountId(loanAccountId);
				if (!CollectionUtils.isEmpty(loanInterestList)) {
					List<IcustLoanInterestModel> interestInfo = mapper.map(loanInterestList, new TypeToken<List<IcustLoanInterestModel>>() {}.getType());
					summaryInfo.setInterestInfo(interestInfo);
				}
				summaryInfo.setDisbursementInfo(mapper.map(disbursementService.fetchLoanDisbursementById(loanAccountId).getBody(), IcustLoanDisbursementModel.class));
				summaryInfo.setRepaymentInfo(mapper.map(repaymentService.fetchLoanRepaymentDetailById(loanAccountId).getBody(), IcustLoanRepaymentModel.class));
				List<IcustLaonChargeDetails> chargeList = icustLoanChargeRepo.findByLoanAccountId(loanAccountId);
				if (!CollectionUtils.isEmpty(chargeList)) {
					List<IcustLoanChargeModel> chargeInfo = mapper.map(chargeList, new TypeToken<List<IcustLoanChargeModel>>() {}.getType());
					summaryInfo.setChargeInfo(chargeInfo);
				}
				summaryInfo.setAccountServiceInfo(mapper.map(accountServicesService.fetchAccountServicesByLoanAccountId(loanAccountId).getBody(), IcustAccountServicesModel.class));
				
				summaryInfo.setCreditRatingInfo(mapper.map(creditRatingService.getCreditRatingsByLoanAccountId(loanAccountId,null).getBody(), IcustLoanCreditRatingDetailsModel.class));
				Optional<IcustLoanValuationOfAssetEntity> isValuationOfAssetEntityPresent  = loanValuationOfAssetRepo.findByLoanAccountId(loanAccountId);

					if (isValuationOfAssetEntityPresent.isPresent()) {
						IcustLoanValuationOfAssetModel valuationOfAssetObj = new Gson().fromJson(new Gson().toJson(isValuationOfAssetEntityPresent.get()),
								IcustLoanValuationOfAssetModel.class);
						summaryInfo.setValAssetInfo(valuationOfAssetObj);
					}
						
				summaryInfo.setLegalOpionInfo((IcustLoanLegalOpinionModel) legalOpinionService.getLegalOpinionByLoanAccountId(loanAccountId).getBody());
				summaryInfo.setAssessmentInfo((IcustLoanAssessmentDetailsModel) icustLoanService.fetchAssessmentInfoByLoanAccId(loanAccountId).getBody());
				summaryInfo.setApprovalInfo((IcustApprovalDetailsModel) approvalDetailsService.fetchApprovalDetails(loanAccountId).getBody());
				summaryInfo.setOfferIssueInfo(mapper.map(icustOfferIssueService.fetchOfferIssueByLoanAccountId(loanAccountId).getBody(), IcustOfferIssueModel.class));
				
				return ResponseEntity.status(HttpStatus.OK).body(summaryInfo);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("LoanAccountId is mandatory");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Execption occoured while executing fetchTaskSummaryInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchTaskSummaryDetails(String status, Integer page, Integer size, String customerId,
			Long loanAccountId, String customerName) {
		Page<IcustLoanInfo> loanInfo = null;
		LoanTaskSummaryModel taskSum = new LoanTaskSummaryModel();
		LoanTaskSummaryDataModel tDModel = null;
		Page<IcustCustomerInfo> customerInfoObject =null;
		try {

			if (customerName == null) {
				loanInfo = loanRepo.findAll(where(getLoanInfoSpecification(status, customerId, loanAccountId)),
						PageRequest.of(page, size,
								Sort.by("createdTime").descending()));
				if (!CollectionUtils.isEmpty(loanInfo.getContent())) {
					for (IcustLoanInfo loan : loanInfo) {

						tDModel = new LoanTaskSummaryDataModel();

						if (loan.getCustomerId()!=null) {
							Optional<IcustCustomerInfo> customerInfo = customerRepo
									.findById(loan.getCustomerId());
							if (customerInfo.isPresent()) {
								Optional<IcustCustomerDocuments> custDoc = documentRepo.findByCustomerIdAndDocumentType(
										loan.getCustomerId(), DataConstants.PROFILEIMAGE);
								if (custDoc.isPresent()) {
									tDModel.setProfileImage(custDoc.get().getFileUrl() != null ? custDoc.get().getFileUrl()
											: "not_available");
								} else {
									tDModel.setProfileImage("not_available");
								}
								tDModel.setPrefix(customerInfo.get().getPrefix());
								tDModel.setCifNumber(customerInfo.get().getCifNumber());
								tDModel.setFirstName(customerInfo.get().getFirstName());
								tDModel.setMiddleName(customerInfo.get().getMiddleName());
								tDModel.setLastName(customerInfo.get().getLastName());
							}
						}
						tDModel.setLoanAccountId(loan.getLoanAccountId());
						tDModel.setCustomerId(loan.getCustomerId());
						tDModel.setAccountType(loan.getAccountType());
						tDModel.setApplicationDate(loan.getApplicationDate());
						tDModel.setAccountBranch(loan.getAccountBranch());
						tDModel.setBusinessProductName(loan.getBusinessProductName());
						tDModel.setStatus(loan.getStatus().toString());
						taskSum.getLoanList().add(tDModel);
					}

					taskSum.setNoOfElements(loanInfo.getNumberOfElements());
					taskSum.setTotalNoOfElements(loanInfo.getTotalElements());
					taskSum.setTotalPages(loanInfo.getTotalPages());

					return ResponseEntity.status(HttpStatus.OK).body(taskSum);
				}else {
					logger.error("No data found in the table");
					return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Data found");
				}
			} else {
				customerInfoObject = customerRepo.findAll(
						where(getCustomerInfoSpecification(customerName, "and")),
						PageRequest.of(page, size, Sort.by("createdTime").descending()));

				if(customerInfoObject !=null && !customerInfoObject.isEmpty()) {
					
					for (IcustCustomerInfo customerInfo : customerInfoObject) {
						List<IcustLoanInfo> loanList=loanRepo.findByCustomerId(customerInfo.getCustomerId());
						tDModel = new LoanTaskSummaryDataModel();
						Optional<IcustCustomerDocuments> custDoc = documentRepo.findByCustomerIdAndDocumentType(
								Long.parseLong(customerInfo.getCustomerId().toString()), DataConstants.PROFILEIMAGE);
						if (custDoc.isPresent()) {
							tDModel.setProfileImage(custDoc.get().getFileUrl() != null ? custDoc.get().getFileUrl()
									: "not_available");
						} else {
							tDModel.setProfileImage("not_available");
						}
						tDModel.setPrefix(customerInfo.getPrefix());
						tDModel.setCifNumber(customerInfo.getCifNumber());
						tDModel.setFirstName(customerInfo.getFirstName());
						tDModel.setMiddleName(customerInfo.getMiddleName());
						tDModel.setLastName(customerInfo.getLastName());
						if(loanList !=null && !loanList.isEmpty()) {
							LoanTaskSummaryDataModel tasModel = null;
							for (IcustLoanInfo loansInfo : loanList) {
								tasModel = mapper.map(loansInfo, LoanTaskSummaryDataModel.class);
							}
							taskSum.getLoanList().add(tasModel);
						}else {
							logger.error("No card data found in the table");
						}
					}
					taskSum.setNoOfElements(customerInfoObject.getNumberOfElements());
					taskSum.setTotalNoOfElements(customerInfoObject.getTotalElements());
					taskSum.setTotalPages(customerInfoObject.getTotalPages());

					return ResponseEntity.status(HttpStatus.OK).body(taskSum);
				}else {
					logger.error("No data found in the table");
					return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Data found");
				}
			}

			

		} catch (Exception e) {
			logger.error("Execption occoured while executing getCustAccountSummaryByAccountId", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	private Specification getCustomerInfoSpecification(String customerName, String condition) {
		return new Specification<IcustCustomerInfo>() {
			@Override
			public Predicate toPredicate(Root<IcustCustomerInfo> customerInfo, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();

				if (!Strings.isNullOrEmpty(customerName)) {
					String[] split = customerName.split(" ");
					int idx = customerName.lastIndexOf(' ');
					if (idx == -1) {
						predicateList.add(cb.like(cb.lower(customerInfo.get("firstName")),
								customerName.toLowerCase(Locale.getDefault()) + "%"));
					} else if (split.length == 3) {
						predicateList.add(cb.like(cb.lower(customerInfo.get("firstName")),
								split[0].toLowerCase(Locale.getDefault()) + "%"));
						predicateList.add(cb.like(cb.lower(customerInfo.get("middleName")),
								split[1].toLowerCase(Locale.getDefault()) + "%"));
						predicateList.add(cb.like(cb.lower(customerInfo.get("lastName")),
								customerName.substring(idx + 1).toLowerCase(Locale.getDefault()) + "%"));
					} else {
						predicateList.add(cb.like(cb.lower(customerInfo.get("firstName")),
								customerName.substring(0, idx).toLowerCase(Locale.getDefault()) + "%"));
						predicateList.add(cb.like(cb.lower(customerInfo.get("lastName")),
								customerName.substring(idx + 1).toLowerCase(Locale.getDefault()) + "%"));
					}
				}

				if (!Strings.isNullOrEmpty(condition) && "and".equals(condition))
					return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
				else if (!Strings.isNullOrEmpty(condition) && "or".equals(condition))
					return cb.or(predicateList.toArray(new Predicate[predicateList.size()]));
				else
					return null;
			}
		};
	}
	
	@SuppressWarnings("serial")
	private Specification<IcustLoanInfo> getLoanInfoSpecification(String status, String customerId, Long loanAccountId) {
		return new Specification<IcustLoanInfo>() {
			@Override
			public Predicate toPredicate(Root<IcustLoanInfo> loanInfo, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();

				if (loanAccountId != null) {
					predicateList.add(cb.equal(loanInfo.get("loanAccountId"), loanAccountId));
				}
				if (!Strings.isNullOrEmpty(customerId)) {
					predicateList.add(cb.equal(loanInfo.get("customerId"), customerId));
				}
				if (!Strings.isNullOrEmpty(status)) {
					predicateList.add(cb.equal(loanInfo.get("status"),
							LoanAccStatusEnum.valueOf(status.toUpperCase(Locale.getDefault()))));
				}

				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
}
