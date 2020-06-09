package com.picosoft.picosoft.module;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name="relation_app_politique")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
/**
 * 
 * @author X270
 *
 */
public class RelationAppPolitique implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**
	 * @id id
	 * @see RelationAppPolitique#getId()
	 * @see RelationAppPolitique#setId(Long)
	 */
	private Long id;
	/**
	 * @see ApplicationHoraire
	 */
	@ManyToOne
	private ApplicationHoraire appHoraire;
	/**
	 * @see Politique 
	 */
	@ManyToOne
	private Politique politique;
	
}
