package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustVehicleDetails;

@Repository
public interface IcustVehicleDetailsRepo extends JpaRepository<IcustVehicleDetails, Long>{

	Optional<IcustVehicleDetails> findByLoanId(Long loanId);

}
