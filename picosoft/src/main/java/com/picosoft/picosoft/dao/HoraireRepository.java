package com.picosoft.picosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picosoft.picosoft.module.Horaire;
/**
 * 
 * @author X270
 *@see JpaRepository<Horaire, Long>
 */
@Repository
public interface HoraireRepository extends JpaRepository<Horaire, Long> {

}
