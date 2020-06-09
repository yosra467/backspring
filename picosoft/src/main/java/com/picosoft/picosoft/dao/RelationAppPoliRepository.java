package com.picosoft.picosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picosoft.picosoft.module.ApplicationHoraire;
import com.picosoft.picosoft.module.RelationAppPolitique;
/**
 * 
 * @author X270
 *@see JpaRepository<RelationAppPolitique, Long>
 */
@Repository
public interface RelationAppPoliRepository extends JpaRepository<RelationAppPolitique,Long>{

}
