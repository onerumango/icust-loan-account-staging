package com.rumango.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustFinancialDetails;

@Repository
public interface IcustFinancialDetailsRepo extends JpaRepository<IcustFinancialDetails, Long>{

	List<IcustFinancialDetails> findByLoanAccountId(Long loanAccountId);

	List<IcustFinancialDetails> findByCardId(Long cardId);


}
