package com.picosoft.picosoft.module;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
/**
 * 
 * @author X270
 *
 */
@Entity(name="jour_ferie")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class JourFerie {
	
		/**
		 * @id id 
		 * @see user#getId()
		 * @see user#setId(long) 
		 * @see user#getnom()
		 * @see user#setnom(string)
		 * @see user#getDate()
		 * @see user#setDate(string)
		 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	private String date;
	
	
	@OneToMany(mappedBy = "jourferie", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<RelationPoli_JF> relation;
}
