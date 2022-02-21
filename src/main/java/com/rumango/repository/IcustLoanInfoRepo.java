package com.rumango.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustLoanInfo;

@Repository
public interface IcustLoanInfoRepo extends JpaRepository<IcustLoanInfo, Long>{

}
