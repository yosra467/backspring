package com.picosoft.picosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picosoft.picosoft.module.ApplicationHoraire;
/**
 * 
 * @author X270
 * @see JpaRepository<ApplicationHoraire, Long>
 */
@Repository
public interface AppHoraireRepository extends JpaRepository<ApplicationHoraire, Long> {

}
