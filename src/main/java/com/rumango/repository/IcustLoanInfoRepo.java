package com.rumango.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustLoanInfo;

@Repository
public interface IcustLoanInfoRepo extends JpaRepository<IcustLoanInfo, Long>,JpaSpecificationExecutor<IcustLoanInfo>{

	List<IcustLoanInfo> findByCustomerId(Long customerId);

}
