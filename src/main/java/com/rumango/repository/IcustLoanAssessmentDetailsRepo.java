package com.rumango.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.rumango.entity.IcustLoanAssessmentDetails;

@Repository
public interface IcustLoanAssessmentDetailsRepo extends JpaRepository<IcustLoanAssessmentDetails, Long> {

	@Query("from IcustLoanAssessmentDetails where loanAccountId=:loanAccountId")
	public Optional<IcustLoanAssessmentDetails> findByLoanAccountId(String loanAccountId);

}
