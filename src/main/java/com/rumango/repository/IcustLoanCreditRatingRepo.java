package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rumango.entity.IcustLoanCreditRatingDetails;

public interface IcustLoanCreditRatingRepo extends JpaRepository<IcustLoanCreditRatingDetails, Long>{

	Optional<IcustLoanCreditRatingDetails> findByLoanAccountId(Long loanAccId);

}
