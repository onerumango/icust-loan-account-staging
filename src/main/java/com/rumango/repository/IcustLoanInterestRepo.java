package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rumango.entity.IcustLoanInterestDetails;

public interface IcustLoanInterestRepo extends JpaRepository<IcustLoanInterestDetails, Long>{

	Optional<IcustLoanInterestDetails> findByLoanId(Long loanId);

}
