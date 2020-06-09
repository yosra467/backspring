package com.picosoft.picosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picosoft.picosoft.module.Departement;
/**
 * 
 * @author X270
 * @see JpaRepository<Departement, Long>
 */
@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long>{

}
