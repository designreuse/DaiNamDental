package com.datawings.app.common;


public class SystemUtil {
	
	public static final String OS_NAME = getSystemProperty("os.name");

	public static boolean isLinuxOS(){
		if(OS_NAME==null){
			return false;
		}
		return OS_NAME.startsWith("Linux")||OS_NAME.startsWith("LINUX");
	}
	
	public static boolean isWindowsOS(){
		if(OS_NAME==null){
			return false;
		}
		return OS_NAME.startsWith("Windows");
	}
	
	private static String getSystemProperty(String property){
        try {
            return System.getProperty(property);
        }catch (SecurityException ex){
            return null;
        }
    }

	public static String getShortNameOS(){
		if(OS_NAME.startsWith("Linux")||OS_NAME.startsWith("LINUX"))
			return "Linux";
		else if(OS_NAME.startsWith("Windows"))
			return "Windows";
		return "";
	}
		
}
