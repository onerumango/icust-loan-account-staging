package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustFinancialDetails;

@Repository
public interface IcustFinancialDetailsRepo extends JpaRepository<IcustFinancialDetails, Long>{

	Optional<IcustFinancialDetails> findByLoanAccountId(Long loanAccountId);

}
