package com.picosoft.picosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picosoft.picosoft.module.JourFerie;
/**
 * 
 * @author X270
 * @see  JpaRepository<JourFerie, Long>
 */
@Repository
public interface JourFerieRepository extends JpaRepository<JourFerie, Long>{
	

}
