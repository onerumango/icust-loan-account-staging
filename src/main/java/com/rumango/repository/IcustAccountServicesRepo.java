package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustAccountServices;

@Repository
public interface IcustAccountServicesRepo extends JpaRepository<IcustAccountServices, Long>{

	Optional<IcustAccountServices> findByLoanAccountId(Long loanAccountId);

}
