package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustLoanDisbursementDetails;

@Repository
public interface IcustLoanDisbursementRepo extends JpaRepository<IcustLoanDisbursementDetails, Long>{

	Optional<IcustLoanDisbursementDetails> findByLoanDisbursementId(Long loanDisbursementId);

	Optional<IcustLoanDisbursementDetails> findByLoanAccountId(Long loanAccountId);

}
