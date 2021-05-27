package it.exolab.tesina.mybank.util;

import java.util.Date;

public class Utils {
	
	public static boolean isNullOrEmpty(String value) {
		
		return value==null || value.equals("");
	}
	public static boolean isNullOrEmpty(Integer value) {
		
		return value==null;
	}
	public static boolean isNullOrEmpty(Long value) {
		
		return value==null;
	}
	public static boolean isNullOrEmpty(Date value) {
		
		return value==null;
	}
	
	



}
