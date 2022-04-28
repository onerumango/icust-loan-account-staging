package com.rumango.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumango.entity.IcustCardInitiation;


@Repository
public interface IcustCardPreferencesRepo extends JpaRepository<IcustCardInitiation, Long>{

}