package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustApprovalDetails;

@Repository
public interface IcustApprovalDetailsRepo extends JpaRepository<IcustApprovalDetails, Long>{

	Optional<IcustApprovalDetails> findByLoanAccountId(Long loanAccountId);

}
