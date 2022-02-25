package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rumango.entity.IcustLoanValuationOfAssetEntity;

public interface IcustLoanValuationOfAssetRepo extends JpaRepository<IcustLoanValuationOfAssetEntity, Long>{

	Optional<IcustLoanValuationOfAssetEntity> findByLoanAccountId(Long loanAccountId);

}
