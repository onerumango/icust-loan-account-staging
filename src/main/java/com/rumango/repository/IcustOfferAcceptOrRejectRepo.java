package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustLoanOfferAcceptOrRejectDetails;

@Repository
public interface IcustOfferAcceptOrRejectRepo extends JpaRepository<IcustLoanOfferAcceptOrRejectDetails, Long>{

	Optional<IcustLoanOfferAcceptOrRejectDetails> findByLoanAccountId(Long loanAccountId);

	
}
