package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustCustomerInfo;

@Repository
public interface IcustCustomerInfoRepo extends JpaRepository<IcustCustomerInfo, Long>{

	Optional<IcustCustomerInfo> findByCustomerId(Long customerId);

}
