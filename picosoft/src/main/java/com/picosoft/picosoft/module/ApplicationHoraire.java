package com.picosoft.picosoft.module;

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
@Entity(name="application_horaire")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ApplicationHoraire {
	/**
	 * @id id
	 * @see user#getId()
	 * @see user#setId(long) 
	 * @see user#getDate()
	 * @see user#setDate(string)

	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String date;
	/**
	 * @see Horaire
	 */
	@ManyToOne
	private Horaire horaire;
	
	@OneToMany(mappedBy = "appHoraire" , cascade = CascadeType.ALL)
	@JsonIgnore
	private List<RelationAppPolitique> relationApp;
	

}
