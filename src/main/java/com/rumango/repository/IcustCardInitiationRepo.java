package com.rumango.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustCardInitiation;

@Repository
public interface IcustCardInitiationRepo extends JpaRepository<IcustCardInitiation, Long>,JpaSpecificationExecutor<IcustCardInitiation>{

	List<IcustCardInitiation> findByCustomerId(Long customerId);

}
