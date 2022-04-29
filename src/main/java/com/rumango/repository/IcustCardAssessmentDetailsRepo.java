package com.rumango.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.rumango.entity.IcustCardAssessmentDetails;

@Repository
public interface IcustCardAssessmentDetailsRepo extends JpaRepository<IcustCardAssessmentDetails, Long> {

	public Optional<IcustCardAssessmentDetails> findByCardId(String cardId);
}
