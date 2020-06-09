package com.picosoft.picosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picosoft.picosoft.module.HoraireJour;
/**
 * 
 * @author X270
 * @see JpaRepository<HoraireJour, Long>
 */
@Repository
public interface HoraireJourRepository extends JpaRepository<HoraireJour, Long>{

}
