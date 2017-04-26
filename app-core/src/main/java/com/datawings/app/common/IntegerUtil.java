package com.datawings.app.common;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Locale;


public class IntegerUtil {

	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public static Integer convertInteger(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return 0;
	}
	
	public static BigInteger convertBigInteger(String str) {
		try {
			return new BigInteger(str.getBytes());
		}catch (Exception e) {
		}
		return BigInteger.valueOf(0);
	}
	
	public static String fromBigInteger(BigInteger number)
	{
	    return new String(number.toByteArray());
	}
	
	public static String formatWithLang(Integer dMontant, Locale locale) {
		NumberFormat nf = NumberFormat.getInstance(locale);
		 return nf.format(dMontant);
	}
}
