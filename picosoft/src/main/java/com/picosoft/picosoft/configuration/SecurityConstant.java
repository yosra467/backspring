package com.picosoft.picosoft.configuration;
/**
 * 
 * @author X270
 *
 */
public class SecurityConstant {
	public static final String SECRET="picosoft";
	/**
	 * le token n'est valable que pour 10 jours 
	 */
	public static final long EXPIRATION_TIME= 864_000_000 ;
	/**
	 * au cours de l'authentification le token a pour prefix Bearer
	 */
	public static final String TOKEN_PREFIX="Bearer";
	/**
	 * c'est celui qui va recevoir le token 
	 */
	public static final  String HEADER_STRING="Authorization"; 

}
