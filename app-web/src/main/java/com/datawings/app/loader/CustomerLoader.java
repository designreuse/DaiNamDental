package com.datawings.app.loader;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.datawings.app.model.Customer;
import com.datawings.app.model.Invoice;
import com.datawings.app.service.ICustomerService;
import com.datawings.app.service.IInvoiceDetailService;
import com.datawings.app.service.IInvoiceService;
import com.datawings.app.service.IRecordsService;

public class CustomerLoader {

	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IRecordsService recordsService;
	
	@Autowired
	private IInvoiceService invoiceService;
	
	@Autowired
	private IInvoiceDetailService invoiceDetailService;
	
	public CustomerLoader() throws IOException {
		super();
		loadApplicationContext(new String[] { "/app-web-context.xml" });
	}

	@SuppressWarnings("resource")
	public void loadApplicationContext(String[] context) {
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(context);
		applicationContext.registerShutdownHook();
		applicationContext.getBeanFactory().autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
	}

	public void updateCustomerDetail() {
		String branch = "DN10";
		List<Customer> loaderCustomer = customerService.getCustomer(branch);
		
		int count = 0;
		for (Customer elm : loaderCustomer) {
			recordsService.updateName(elm);
			
			count++;
			System.out.println("--------" + count + "---------- " + elm.getId().getSerial());
		}
	}

	public void updateInvoice() {
		List<Invoice> list=	invoiceService.getAll();
		
		for (Invoice elm : list) {
			invoiceDetailService.updateInf(elm);
			System.out.println(elm.getId().getSerial() + "....." + elm.getId().getBranch());
		}
	}

}
