package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class   UUIDUtils {

	public static void main(String[] args) {
		System.out.println(getUUID());
	}

	/**
	* 获得指定数目的UUID
	* @param number int 需要获得的UUID数量
	* @return String[] UUID数组
	*/
	public static String[] getUUID(int number){
		if(number < 1){
		return null;
		}
		String[] retArray = new String[number];
		for(int i=0;i<number;i++){
		retArray[i] = getUUID();
		}
		return retArray;
	}

	/**
	* 获得一个UUID
	* @return String UUID
	*/
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString();
		//去掉“-”符号
		return uuid.replaceAll("-", "");
	}

	public static String get20UUID(){

		UUID id=UUID.randomUUID();
		String[] idd=id.toString().split("-");
		return idd[0]+idd[1]+idd[2]+idd[3];
	}
	public static String get32UUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		String temp = str.substring(0, 8) + str.substring(9, 13)
				+ str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
		return temp;


	}

	/**
	 * 订单号生成，利用类型区分
	 * @param typestr
	 * @return
	 */
	public static String  createOrderNo(String typestr)
	{
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		int i = (int)(Math.random()*900 + 100);
		 String myStr = Integer.toString(i);
		 try {
		 	return typestr+format.format(new Date())+myStr;
		 }catch (Exception e)
		 {
		 	e.printStackTrace();
		 }
		 return null;
	}

}
