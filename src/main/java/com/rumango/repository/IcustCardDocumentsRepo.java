package com.rumango.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustCardDocuments;

@Repository
public interface IcustCardDocumentsRepo extends JpaRepository<IcustCardDocuments, Long>{

	Optional<IcustCardDocuments> findByCardIdAndScreenType(Long cardId, String screenType);

	List<IcustCardDocuments> findByCardIdAndScreenType(Long cardId, Integer screenType);

	List<IcustCardDocuments> findByCardId(Long cardId);
}
