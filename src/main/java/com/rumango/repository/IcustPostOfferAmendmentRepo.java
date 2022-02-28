package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustPostOfferAmendmentDetails;

@Repository
public interface IcustPostOfferAmendmentRepo extends JpaRepository<IcustPostOfferAmendmentDetails, Long>{

	Optional<IcustPostOfferAmendmentDetails> findByLoanAccountId(Long loanAccountId);

}
