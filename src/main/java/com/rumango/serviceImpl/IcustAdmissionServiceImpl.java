package com.rumango.serviceImpl;

import java.sql.Date;
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
import com.rumango.entity.IcustAdmissionDetails;
import com.rumango.model.IcustAdmissionDetailsModel;
import com.rumango.repository.IcustAdmissionDetailsRepo;
import com.rumango.service.IcustAdmissionService;

@Service
public class IcustAdmissionServiceImpl implements IcustAdmissionService{
	private static final Logger logger = LogManager.getLogger(IcustAdmissionServiceImpl.class);


	@Autowired
	IcustAdmissionDetailsRepo icustAdmissionDetailsRepo;


	@Override
	public ResponseEntity<?> upsertAdmissionDetails(IcustAdmissionDetailsModel icustAdmissionDetailsModel) {
		try {
			if (icustAdmissionDetailsModel.getLoanId()==null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanId is Mandatory");
			else {
				Optional<IcustAdmissionDetails> admissionObj = icustAdmissionDetailsRepo
						.findByLoanId(icustAdmissionDetailsModel.getLoanId());
				IcustAdmissionDetails admissionData = new Gson().fromJson(new Gson().toJson(icustAdmissionDetailsModel),
						IcustAdmissionDetails.class);
				if (admissionObj.isPresent()) {
					validateAdmissionDetails(admissionObj.get(),admissionData);
					return ResponseEntity.status(HttpStatus.OK).body(icustAdmissionDetailsRepo.save(admissionObj.get()));
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(icustAdmissionDetailsRepo.save(admissionData));
				}
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while upsertAdmissionDetails", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	


	private void validateAdmissionDetails(IcustAdmissionDetails oldAdmissionDetails,
			IcustAdmissionDetails newAdmissionDetails) {

		if(newAdmissionDetails.getLoanId()!=null)
			oldAdmissionDetails.setLoanId(newAdmissionDetails.getLoanId());
		if(!Strings.isNullOrEmpty(newAdmissionDetails.getLoanRequestedFor()))
			oldAdmissionDetails.setLoanRequestedFor(newAdmissionDetails.getLoanRequestedFor());
		if(!Strings.isNullOrEmpty(newAdmissionDetails.getAdmissionStatus()))
			oldAdmissionDetails.setAdmissionStatus(newAdmissionDetails.getAdmissionStatus());
		if(!Strings.isNullOrEmpty(newAdmissionDetails.getModeOfStudy()))
			oldAdmissionDetails.setModeOfStudy(newAdmissionDetails.getModeOfStudy());
		if(!Strings.isNullOrEmpty(newAdmissionDetails.getProposedCourseOfStudy()))
			oldAdmissionDetails.setProposedCourseOfStudy(newAdmissionDetails.getProposedCourseOfStudy());
		if(!Strings.isNullOrEmpty(newAdmissionDetails.getInstitution()))
			oldAdmissionDetails.setInstitution(newAdmissionDetails.getInstitution());
		if(!Strings.isNullOrEmpty(newAdmissionDetails.getUniversity()))
			oldAdmissionDetails.setUniversity(newAdmissionDetails.getUniversity());
		if(!Strings.isNullOrEmpty(newAdmissionDetails.getCountry()))
			oldAdmissionDetails.setCountry(newAdmissionDetails.getCountry());
		if(!Strings.isNullOrEmpty(newAdmissionDetails.getInstitutionRanking()))
			oldAdmissionDetails.setInstitutionRanking(newAdmissionDetails.getInstitutionRanking());
		if(!Strings.isNullOrEmpty(newAdmissionDetails.getCourseDuration()))
			oldAdmissionDetails.setCourseDuration(newAdmissionDetails.getCourseDuration());
		if(newAdmissionDetails.getCourseCommencementDate()!=null)
			oldAdmissionDetails.setCourseCommencementDate(newAdmissionDetails.getCourseCommencementDate());
		if(!Strings.isNullOrEmpty(newAdmissionDetails.getSpecialization()))
			oldAdmissionDetails.setSpecialization(newAdmissionDetails.getSpecialization());
		if(!Strings.isNullOrEmpty(newAdmissionDetails.getProjectedEarning()))
			oldAdmissionDetails.setProjectedEarning(newAdmissionDetails.getProjectedEarning());
		if(!Strings.isNullOrEmpty(newAdmissionDetails.getEmploymentPotential()))
			oldAdmissionDetails.setEmploymentPotential(newAdmissionDetails.getEmploymentPotential());
		if(newAdmissionDetails.getCostOfCourse()!=null)
			oldAdmissionDetails.setCostOfCourse(newAdmissionDetails.getCostOfCourse());
		if(newAdmissionDetails.getSource()!=null)
			oldAdmissionDetails.setSource(newAdmissionDetails.getSource());
	}



	@Override
	public ResponseEntity<?> fetchAdmissionDetails(Long loanId) {
		try {
			if (loanId == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LoanId is Mandatory");
			
			Optional<IcustAdmissionDetails> admissionObj = icustAdmissionDetailsRepo.findByLoanId(loanId);
			if (admissionObj.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(admissionObj.get());
			} else {
				logger.error("No  record exist for given id");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No  record exist for given id");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchAdmissionDetails", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}


	@Override
	public ResponseEntity<?> fetchAdmissionInfo() {
		try {
			List<IcustAdmissionDetails> admissionList = icustAdmissionDetailsRepo.findAll();
			if (!CollectionUtils.isEmpty(admissionList)) {
				return ResponseEntity.status(HttpStatus.OK).body(admissionList);
			} else {
				logger.error("No details found");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No details found");
			}
		} catch (Exception e) {
			logger.error("Execption occoured while executing fetchAdmissionInfo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
