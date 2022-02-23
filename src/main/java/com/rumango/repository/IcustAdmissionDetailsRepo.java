package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustAdmissionDetails;

@Repository
public interface IcustAdmissionDetailsRepo extends JpaRepository<IcustAdmissionDetails, Long>{

	Optional<IcustAdmissionDetails> findByLoanId(Long loanId);

}
