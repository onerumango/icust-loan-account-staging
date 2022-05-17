package com.rumango.serviceImpl;

import static org.springframework.data.jpa.domain.Specification.where;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
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
import com.rumango.constants.DataConstants;
import com.rumango.entity.IcustCardInitiation;
import com.rumango.entity.IcustCustomerDocuments;
import com.rumango.entity.IcustCustomerInfo;
import com.rumango.enums.CardInitiationStatus;
import com.rumango.model.CardTaskSummaryDataModel;
import com.rumango.model.IcustCardApprovalModel;
import com.rumango.model.IcustCardAssessmentModel;
import com.rumango.model.IcustCardInitiationModel;
import com.rumango.model.IcustCardOriginSummaryModel;
import com.rumango.model.IcustCardPreferencesModel;
import com.rumango.model.IcustFinancialDetailsModel;
import com.rumango.model.IcustLoanChargeModel;
import com.rumango.model.IcustLoanCreditRatingDetailsModel;
import com.rumango.model.IcustLoanInterestModel;
import com.rumango.model.CardTaskSummaryModel;
import com.rumango.repository.CustomerDocumentsRepo;
import com.rumango.repository.IcustCardInitiationRepo;
import com.rumango.repository.IcustCustomerInfoRepo;
import com.rumango.service.IcustCardApprovalDetailService;
import com.rumango.service.IcustCardAssessmentService;
import com.rumango.service.IcustCardChargeService;
import com.rumango.service.IcustCardDocumentsService;
import com.rumango.service.IcustCardInitiationService;
import com.rumango.service.IcustCardInterestService;
import com.rumango.service.IcustCardPreferencesService;
import com.rumango.service.IcustCardTaskSummaryService;
import com.rumango.service.IcustFinancialDetailsService;
import com.rumango.service.IcustLoanCreditRatingService;

@Service
public class IcustCardTaskSummaryServiceImpl implements IcustCardTaskSummaryService{
	private static final Logger logger = Logger.getLogger(IcustCardTaskSummaryServiceImpl.class);


	@Autowired
	ModelMapper mapper;
	@Autowired
	IcustCardInitiationService cardInitiationService;
	@Autowired
	IcustFinancialDetailsService financialDetailsService;
	@Autowired
	IcustCardInterestService cardService;
	@Autowired
	IcustCardPreferencesService cardPreferencesService;
	@Autowired
	IcustCardDocumentsService uploadService;
	@Autowired
	IcustCardChargeService cardChargeService;
	@Autowired
	private IcustLoanCreditRatingService creditRatingService;
	@Autowired
	private IcustCardAssessmentService assessmentService;
	@Autowired
	IcustCardApprovalDetailService cardApprovalService;
	@Autowired
	IcustCardInitiationRepo initiationRepo;
	@Autowired
	IcustCustomerInfoRepo customerRepo;
	@Autowired
	CustomerDocumentsRepo documentRepo;
	@Override
	public ResponseEntity<?> fetchTaskSummaryInfo(Long cardId) {
		IcustCardOriginSummaryModel summaryInfo = new IcustCardOriginSummaryModel();
		try {
			if (cardId != null) {
				summaryInfo.setInitiationInfo(mapper.map(cardInitiationService.fetchCardInitiationByCardId(cardId).getBody(), IcustCardInitiationModel.class));
				summaryInfo.setFinancialInfo((List<IcustFinancialDetailsModel>) financialDetailsService.fetchFinancialDetails(null,cardId).getBody());
				summaryInfo.setPreferenceInfo((List<IcustCardPreferencesModel>) cardPreferencesService.fetchCardPreferenceByCardId(cardId).getBody());
				//summaryInfo.setDocumentInfo((List<IcustCardDocumentsModel>) uploadService.fetchCardDocuments(cardId,"cardOrigin").getBody());
				summaryInfo.setInterestInfo((List<IcustLoanInterestModel>) cardService.fetchCardInterestById(cardId).getBody());
				summaryInfo.setChargeInfo((List<IcustLoanChargeModel>) cardChargeService.fetchCardChargeDetailsById(cardId).getBody());
				summaryInfo.setCreditRatingInfo(mapper.map(creditRatingService.getCreditRatingsByLoanAccountId(null,cardId).getBody(), IcustLoanCreditRatingDetailsModel.class));
				summaryInfo.setAssessmentInfo(mapper.map(assessmentService.fetchCardAssessmentById(cardId).getBody(), IcustCardAssessmentModel.class));
				summaryInfo.setApprovalInfo((IcustCardApprovalModel) cardApprovalService.fetchApprovalDetails(cardId).getBody());
				
				return ResponseEntity.status(HttpStatus.OK).body(summaryInfo);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("CardId is mandatory");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Execption occoured while executing fetchTaskSummaryInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> fetchTaskSummaryDetails(String status, Integer page, Integer size, String customerId,
			Long cardId, String customerName) {
		Page<IcustCardInitiation> initiationInfo = null;
		CardTaskSummaryModel taskSum = new CardTaskSummaryModel();
		CardTaskSummaryDataModel tDModel = null;
		Page<IcustCustomerInfo> customerInfoObject =null;
		try {

			if (customerName == null) {
				initiationInfo = initiationRepo.findAll(where(getCardInfoSpecification(status, customerId, cardId)),
						PageRequest.of(page, size,
								Sort.by("createdTime").descending()));
				if (!CollectionUtils.isEmpty(initiationInfo.getContent())) {
					for (IcustCardInitiation cardInitiation : initiationInfo) {

						tDModel = new CardTaskSummaryDataModel();

						if (cardInitiation.getCustomerId()!=null) {
							Optional<IcustCustomerInfo> customerInfo = customerRepo
									.findById(cardInitiation.getCustomerId());
							if (customerInfo.isPresent()) {
								Optional<IcustCustomerDocuments> custDoc = documentRepo.findByCustomerIdAndDocumentType(
										cardInitiation.getCustomerId(), DataConstants.PROFILEIMAGE);
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
						tDModel.setCardId(cardInitiation.getCardId());
						tDModel.setCustomerId(cardInitiation.getCustomerId());
						tDModel.setCardType(cardInitiation.getCardType());
						tDModel.setStatus(cardInitiation.getStatus().toString());
						taskSum.getCardList().add(tDModel);
					}

					taskSum.setNoOfElements(initiationInfo.getNumberOfElements());
					taskSum.setTotalNoOfElements(initiationInfo.getTotalElements());
					taskSum.setTotalPages(initiationInfo.getTotalPages());

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
						List<IcustCardInitiation> initationList=initiationRepo.findByCustomerId(customerInfo.getCustomerId());
						tDModel = new CardTaskSummaryDataModel();
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
						if(initationList !=null && !initationList.isEmpty()) {
							CardTaskSummaryDataModel tasModel = null;
							for (IcustCardInitiation accInfo : initationList) {
								tasModel = mapper.map(accInfo, CardTaskSummaryDataModel.class);
							}
							taskSum.getCardList().add(tasModel);
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
	@SuppressWarnings("serial")
	private Specification<IcustCustomerInfo> getCustomerInfoSpecification(String customerName, String condition) {
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
	public static Specification<IcustCardInitiation> getCardInfoSpecification(String status, String customerId, Long cardId) {
		return new Specification<IcustCardInitiation>() {
			@Override
			public Predicate toPredicate(Root<IcustCardInitiation> cardInfo, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();

				if (cardId != null) {
					predicateList.add(cb.equal(cardInfo.get("cardId"), cardId));
				}
				if (!Strings.isNullOrEmpty(customerId)) {
					predicateList.add(cb.equal(cardInfo.get("customerId"), customerId));
				}
				if (!Strings.isNullOrEmpty(status)) {
					predicateList.add(cb.equal(cardInfo.get("status"),
							CardInitiationStatus.valueOf(status.toUpperCase(Locale.getDefault()))));
				}

				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
	
	@SuppressWarnings("serial")
	private Specification getCustomerInfoSpecificationForChatBot(Long customerId, String kycReference,
			String condition) {
		return new Specification<IcustCustomerInfo>() {
			@Override
			public Predicate toPredicate(Root<IcustCustomerInfo> customerInfo, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();

				if (customerId != null) {
					predicateList.add(cb.equal(customerInfo.get("customerId"), customerId));
				}
				if (!Strings.isNullOrEmpty(kycReference)) {
					predicateList.add(cb.equal(customerInfo.get("kycReference"), kycReference));
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


}
