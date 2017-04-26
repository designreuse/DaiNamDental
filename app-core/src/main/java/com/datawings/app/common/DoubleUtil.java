package com.datawings.app.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.lang.StringUtils;

public class DoubleUtil {
	public static boolean isDouble(String amountStr, Locale locale) {
		DoubleConverter doubleConverter = new DoubleConverter();
		doubleConverter.setLocale(locale);
		try {
			doubleConverter.convert(double.class, amountStr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String roundAndFormat(double dMontant) {
		DecimalFormat fm = new DecimalFormat("###0.00");
		String dResult = fm.format(dMontant).replace(".", ",");
		String tmp = "";
		Integer cnt = 0;
		for (int i = dResult.split(",")[0].length() - 1; i >= 0; i--) {

			if (cnt % 3 == 0 && cnt != 0) {
				tmp = (dResult.split(",")[0]).charAt(i) + " " + tmp;
			} else {
				tmp = (dResult.split(",")[0]).charAt(i) + tmp;
			}
			cnt++;
		}
		return tmp + "," + dResult.split(",")[1];

	}
	
	public static Double roundExcel(double dMontant) {
		DecimalFormat fm = new DecimalFormat("###0.00");
		String dResult = fm.format(dMontant);
		Double tmp = 0.0;
		tmp = DoubleUtil.convertDouble(dResult);
		return tmp ;

	}
	

	public static double round2(double amt) {
		BigDecimal bd = new BigDecimal(amt);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
	
	public static String roundAndFormat1(double dMontant, int precision) {
		dMontant = round(dMontant, precision);
		DecimalFormat fm = new DecimalFormat("###0.0#");
		String dResult = fm.format(dMontant).replace(".", ",");
		String tmp = "";
		Integer cnt = 0;
		for (int i = dResult.split(",")[0].length() - 1; i >= 0; i--) {

			if (cnt % 3 == 0 && cnt != 0) {
				tmp = (dResult.split(",")[0]).charAt(i) + " " + tmp;
			} else {
				tmp = (dResult.split(",")[0]).charAt(i) + tmp;
			}
			cnt++;
		}
		return tmp;
	}

	public static Double round(Double dMontant, int precision) {
		BigDecimal bigDec = new BigDecimal(dMontant);

		Double dResult = new Double(bigDec.setScale(precision,
				BigDecimal.ROUND_HALF_UP).doubleValue());

		return dResult;
	}	
	
	/**
	 * Return Double from amount with format as eee,dd (ex: 119,85)
	 * 
	 * @param amount
	 * @return
	 */
	public static Double convertAmount(String amount) {
		String s = StringUtils.replace(amount, ",", ".");
		if (DoubleUtil.isAmount(s)) {
			Double result = DoubleUtil.convertDouble(StringUtils.replace(
					amount, ",", "."));
			return result;
		}
		// logger.error("ERROR * format amount=[" + amount + "]");
		return 0.0;
	}

	/**
	 * Return true if String as amount is Double
	 * 
	 * @param amount
	 * @return
	 */
	public static boolean isAmount(String amount) {
		try {
			Double.parseDouble(amount);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	

	public static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);			
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * Cette m�thode convertit un String en Double
	 * 
	 * @param sAmount
	 * @return
	 */
	public static Double convertDouble(String sAmount) {		
		try {
			return Double.parseDouble(sAmount);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return 0.0;
	}
	
	public static Double convertBsr(Double amount, String orig, String dest) {		
		return amount;
	}

	
	public static String formatWithLang(Double dMontant, Locale locale) {
		DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(locale);
		// unusualSymbols.setDecimalSeparator(',');
		 unusualSymbols.setGroupingSeparator('?');

		 String strange = "#,##0.00";
		 DecimalFormat weirdFormatter = new DecimalFormat(strange, unusualSymbols);
		 weirdFormatter.setGroupingSize(3);

		 String bizarre = weirdFormatter.format(dMontant);
		 return StringUtils.replace(bizarre, "?", "");
	}	
	
	public static String localizedFormat(double d, Locale locale) {
		NumberFormat nf = NumberFormat.getInstance(locale);
		return nf.format(d);
	}
	
	 public static Double convertDouble(String sAmount, int precision) {
	     Double div = 1.0;
	     if (precision == 1) 
	    	 div = 10.0;
	     if (precision == 2) 
	    	 div = 100.0;
	     if (precision == 3) 
	    	 div = 1000.0;
	     if (precision == 4) 
	    	 div = 10000.0;
	     Double amount = Double.parseDouble(sAmount);
	     amount = amount / div;
	     return round(amount);	
	 }
	 
	 public static double round (double dMontant) {
		BigDecimal bigDec = new BigDecimal(Double.toString(dMontant));
		Double dResult = new Double( bigDec.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() );
		return dResult.doubleValue(); 
	}
}
