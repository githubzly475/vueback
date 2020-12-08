package com.example.demo.utils;

import java.util.UUID;

/**
 * @author
 */
public class UUIDUtils {

	/**
	* 获得一个UUID
	* @return String UUID
	*/
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
