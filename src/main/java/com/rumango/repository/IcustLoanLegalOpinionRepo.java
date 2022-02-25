package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rumango.entity.IcustLoanLegalOpinionEntity;

public interface IcustLoanLegalOpinionRepo extends JpaRepository<IcustLoanLegalOpinionEntity, Long>{

	Optional<IcustLoanLegalOpinionEntity> findByLoanAccountId(Long loanAccountId);

}
