package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustGuarantorDetails;

@Repository
public interface IcustGuarantorDetailsRepo extends JpaRepository<IcustGuarantorDetails, Long>{

	Optional<IcustGuarantorDetails> findByLoanAccountId(Long loanAccountId);

}
