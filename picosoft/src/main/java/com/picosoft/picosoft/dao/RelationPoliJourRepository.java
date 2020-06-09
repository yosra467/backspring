package com.picosoft.picosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picosoft.picosoft.module.RelationPoli_JF;
/**
 * 
 * @author X270
 * @see JpaRepository<RelationPoli_JF,Long>
 */
@Repository
public interface RelationPoliJourRepository extends JpaRepository<RelationPoli_JF,Long>{

}