package com.rumango.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustLoanDocuments;

@Repository
public interface IcustLoanDocumentsRepo extends JpaRepository<IcustLoanDocuments, Long>{

	Optional<IcustLoanDocuments> findByLoanAccountIdAndScreenType(Long loanAccountId, String screenType);

	List<IcustLoanDocuments> findByLoanAccountIdAndScreenType(Long loanAccountId, Integer screenType);

}
