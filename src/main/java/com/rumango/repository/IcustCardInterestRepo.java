package com.rumango.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustCardInterestDetails;

@Repository
public interface IcustCardInterestRepo extends JpaRepository<IcustCardInterestDetails, Long> {

	List<IcustCardInterestDetails> findByCardId(Long cardAccountId);

	IcustCardInterestDetails findByCardIdAndIntrestType(Long cardAccountId, String intrestType);
}
