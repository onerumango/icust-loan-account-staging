package com.rumango.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustMandateMaster;

@Repository
@Transactional
public interface IcustMandateMasterRepo extends JpaRepository<IcustMandateMaster, Long>{

	Optional<IcustMandateMaster> findByLoanAccountId(Long loanAccountId);


}
