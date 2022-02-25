package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustLaonChargeDetails;

@Repository
public interface IcustLoanChargeRepo extends JpaRepository<IcustLaonChargeDetails, Long>{

	Optional<IcustLaonChargeDetails> findByLoanAccountId(Long loanAccountId);

}
