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
@Entity(name="departement")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Departement {
	/**
	 * @id id
	 * @see user#getId()
	 * @see user#setId(long) 
	 * @see user#getDeptName()
	 * @see user#setDeptName(string)
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String deptName;
	
	@OneToMany(mappedBy="departement", cascade =CascadeType.ALL)
	@JsonIgnore
	private List<User> user;
	

}