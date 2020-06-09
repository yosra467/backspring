package com.picosoft.picosoft.module;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name="user")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
/**
 * 
 * @author X270
 *
 */
public class User {
	
	/**

	
	 * @id idUser
	 * @see user#getId()
	 * @see user#setId(long) 
	 * @see user#getnom()
	 * @see user#setnom(string)
	 * @see user#getgender()
	 * @see user#setgender(string)
	 * @see user#getemail()
	 * @see user#setemail(string)
	 * @see user#getpassword()
	 * @see user#setpassword(string)
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUser;
	private String prenom;
	private String nom;
	private String gender;
	
	@javax.validation.constraints.Email	
	private String email;
	
	private String password;
	/**
	 * un utilisateur peut etre inscrit dans une seule politique
	 * @see Politique
	 */
	
	@ManyToOne
	private Politique politique;
	/**
	 * un utilisateur est inscrit dans un seul département 
	 * @see Departement
	 */
	@ManyToOne
	private Departement departement;
	/**
	 * un utilisateur peut avoir un seul role 
	 * @see Role
	 */
	@ManyToOne
	private Role role;
	/**
	 * un utilisateur peut etre le superieur d'autres utilisateurs
	 *
	 */
	@ManyToOne
	private User superior;
	/**
	 * un utilisateur peut etre le superieur d'autres utilisateurs
	 */
	@OneToMany(mappedBy="superior", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<User> equipe;
	/**
	 * un utilisateur peut avoir plusieurs pointages
	 */
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Pointage> pointage;
}