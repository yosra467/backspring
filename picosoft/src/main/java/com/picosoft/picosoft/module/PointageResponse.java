package com.picosoft.picosoft.module;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * 
 * @author X270
 *
 */
@Data @AllArgsConstructor
public class PointageResponse {
	/**
	 * @see PointageResponse#getName()
	 * @see PointageResponse#setName(String)
	 * @see PointageResponse#setValue()
	 * @see PointageResponse#getValue(Double)
	 */
	private String name;
	private Double value;
}
