package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rumango.entity.IcustCustomerDocuments;


@Repository
@Transactional
public interface CustomerDocumentsRepo extends JpaRepository<IcustCustomerDocuments, Long>, JpaSpecificationExecutor<IcustCustomerDocuments>{

	Optional<IcustCustomerDocuments> findByCustomerIdAndDocumentType(Long customerId, Integer pROFILEIMAGE);
	
	


}
