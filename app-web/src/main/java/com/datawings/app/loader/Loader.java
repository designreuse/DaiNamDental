package com.datawings.app.loader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Loader {
	public static void main(String[] args) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		CustomerLoader loader = new CustomerLoader();
		loader.updateCustomerDetail();
		
		//loader.updateInvoice();
		
		System.out.println("------ finish");
	}
}
