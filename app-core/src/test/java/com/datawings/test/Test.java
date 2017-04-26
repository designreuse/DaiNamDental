package com.datawings.test;



public class Test {

	/**0
	 * @param args
	 */
	public static void main(String[] args) {
		String a = "07/04/2014;EMI   ;11371   ;1540166 ;NAVITOUR MILENIS                   ;    280,36;      0,00;EUR;";
		String b = a.replaceAll("0,00", "    ");
		System.out.println(a);
		System.out.println(b);
		
	}

}
