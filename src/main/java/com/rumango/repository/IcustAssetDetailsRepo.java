package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustAssetDetails;

@Repository
public interface IcustAssetDetailsRepo extends JpaRepository<IcustAssetDetails, Long>{

	Optional<IcustAssetDetails> findByLoanId(Long loanId);

}
