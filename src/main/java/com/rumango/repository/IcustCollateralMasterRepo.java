package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustCollateralMaster;

@Repository
public interface IcustCollateralMasterRepo extends JpaRepository<IcustCollateralMaster, Long>{

	Optional<IcustCollateralMaster> findByLoanAccountId(Long loanAccountId);

}
