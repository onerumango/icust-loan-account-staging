package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustAccountCreate;

@Repository
public interface IcustAccountCreateRepo extends JpaRepository<IcustAccountCreate, Long>{

	Optional<IcustAccountCreate> findByLoanAccountId(Long loanAccountId);

}
