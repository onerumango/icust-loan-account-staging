package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustLoanInterestDetails;

@Repository
public interface IcustLoanInterestRepo extends JpaRepository<IcustLoanInterestDetails, Long>{

	Optional<IcustLoanInterestDetails> findByLoanAccountId(Long loanAccountId);


}
