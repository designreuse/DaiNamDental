package com.datawings.app.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils{
	private static Properties props = new Properties();

	public PropertiesUtils() {}

	public PropertiesUtils(boolean isResources, String... srcPaths) {
		System.out.println("PATH ***************************** ");
		if (isResources) {
			loadResources(srcPaths);
		} else{
			loadFile(srcPaths);
		}
		System.out.println(" ***************************** PATH");
	}

	public void loadResources(String... srcPaths){
		try {
			for(String srcPath : srcPaths){
				System.out.println(srcPath);
				props.load(getClass().getResourceAsStream(srcPath));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadFile(String... srcPaths){
		try {
			for(String srcPath : srcPaths){
				System.out.println(srcPath);
				props.load(new FileInputStream(srcPath));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key){
		return props.getProperty(key);
	}

	public Properties getProps() {
		return props;
	}
}