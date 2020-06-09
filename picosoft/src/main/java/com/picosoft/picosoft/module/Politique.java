package com.picosoft.picosoft.module;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 
 * @author X270
 *
 */
@Entity(name="politique")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Politique {
	/**
	 
	  
	 * @id id 
	 * @see Politique#getId()
	 * @see Politique#setId(Long)
	 * @see Politique#setNom()
	 * @see Politique#getNom(String)
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nom;
	/**
 	 *  chaque politique peut avoir plusieurs utilisateurs
 	 */
	
	@OneToMany(mappedBy="politique", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<User> user;
	/**
	 * chaque politique peut avoir plusieurs jours fériés 
	 */
	@OneToMany(mappedBy = "politique", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<RelationPoli_JF> jourFerie;
	/**
	 * chaque politique contient plusieurs application horaire.
	 */
	@OneToMany(mappedBy = "politique", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<RelationAppPolitique> appHoraire;
}