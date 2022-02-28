package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustOfferIssue;

@Repository
public interface IcustOfferIssueRepo extends JpaRepository<IcustOfferIssue, Long>{

	Optional<IcustOfferIssue> findByLoanAccountId(Long loanAccountId);

}
