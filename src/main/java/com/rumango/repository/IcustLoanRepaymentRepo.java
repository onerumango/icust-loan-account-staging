package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustLoanRepaymentDetails;

@Repository
public interface IcustLoanRepaymentRepo extends JpaRepository<IcustLoanRepaymentDetails, Long>{

	Optional<IcustLoanRepaymentDetails> findByLoanAccountId(Long loanAccountId);

}
