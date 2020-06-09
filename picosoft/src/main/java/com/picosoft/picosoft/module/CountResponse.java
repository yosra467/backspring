package com.picosoft.picosoft.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author X270
 *
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class CountResponse {
	
	/**
	 * @see user#getValue()
	 * @see user#setValue(long) 
	 * @see user#getName()
	 * @see user#setName(string)
	 */
	private String name;
	private int value;

}
