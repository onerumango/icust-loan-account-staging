package com.rumango.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustCardApprovalDetails;

@Repository
public interface IcustCardApprovalDetailsRepo extends JpaRepository<IcustCardApprovalDetails, Long>{

	@Query("from IcustCardApprovalDetails where cardId=:cardId")
	public Optional<IcustCardApprovalDetails> findByCardId(@Param("cardId") Long cardId);
}
