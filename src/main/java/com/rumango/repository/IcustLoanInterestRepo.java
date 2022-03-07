package com.rumango.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustLoanInterestDetails;

@Repository
public interface IcustLoanInterestRepo extends JpaRepository<IcustLoanInterestDetails, Long>{

	List<IcustLoanInterestDetails> findByLoanAccountId(Long loanAccountId);

	IcustLoanInterestDetails findByLoanAccountIdAndInterestType(Long loanAccountId, String interestType);


}
