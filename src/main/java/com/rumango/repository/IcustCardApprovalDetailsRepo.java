package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustCardApprovalDetails;

@Repository
public interface IcustCardApprovalDetailsRepo extends JpaRepository<IcustCardApprovalDetails, Long>{

	Optional<IcustCardApprovalDetails> findByCardId(Long cardId);
}
