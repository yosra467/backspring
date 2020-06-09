package com.picosoft.picosoft.module;
import java.security.Timestamp;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name="pointage") 
@Data @NoArgsConstructor @AllArgsConstructor @ToString
/**
 * 
 * @author X270
 *
 */
public class Pointage {
	/**
	 * @see Pointage#getId()
	 * @see Pointage#setId(long)
	 * @see Pointage#getcheckTime()
	 * @see Pointage#setcheckTime(Time)
	 * @see Pointage#getcheckDate()
	 * @see Pointage#setcheckDate(String)
	 * @see Pointage#getverifyCode()
	 * @see Pointage#setverifyCode(int)
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Time checkTime;
	private String checkDate;
	private String checkType;

	private int verifyCode;
	/**
	 * @see User
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
}