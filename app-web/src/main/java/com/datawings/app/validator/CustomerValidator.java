package com.datawings.app.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.datawings.app.common.DateUtil;
import com.datawings.app.filter.CustomerFiler;
import com.datawings.app.model.Customer;
import com.datawings.app.model.CustomerId;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.ICustomerService;

@Component
public class CustomerValidator {

	@Autowired
	private ICustomerService customerService;
	
	public int checkCustomerAdd(Object target, SysUser sysUser, Errors errors) {
		CustomerFiler filter = (CustomerFiler) target;
		Integer erroCode = 0;
		
		if (StringUtils.isBlank(filter.getSerialCreate())) {
			errors.rejectValue("serialCreate", "message.empty", "!");
			erroCode = 1;
		}else {
			CustomerId customerId = new CustomerId();
			customerId.setSerial(filter.getSerialCreate());
			customerId.setBranch(sysUser.getBranch());
			Customer customer = customerService.find(customerId);
			if(customer != null){
				errors.rejectValue("serialCreate", "message.exist", "!");
				erroCode = 1;
			}
		}
		
		if (StringUtils.isBlank(filter.getNameCreate())) {
			errors.rejectValue("nameCreate", "message.empty", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getDateBirthCreate())) {
			errors.rejectValue("dateBirthCreate", "message.empty", "!");
			erroCode = 1;
		}else if (!DateUtil.checkDateAsString(filter.getDateBirthCreate(), "dd/MM/yyyy")) {
			errors.rejectValue("dateBirthCreate", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getDateStartCreate())) {
			errors.rejectValue("dateStartCreate", "message.empty", "!");
			erroCode = 1;
		}else if (!DateUtil.checkDateAsString(filter.getDateStartCreate(), "dd/MM/yyyy")) {
			errors.rejectValue("dateStartCreate", "message.valid", "!");
			erroCode = 1;
		}
		
		return erroCode;
	}

	public int checkCustomerEdit(Object target, Errors errors) {
		CustomerFiler filter = (CustomerFiler) target;
		Integer erroCode = 0;
		
		if (StringUtils.isBlank(filter.getNameCreate())) {
			errors.rejectValue("nameCreate", "message.empty", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getDateBirthCreate())) {
			errors.rejectValue("dateBirthCreate", "message.empty", "!");
			erroCode = 1;
		}else if (!DateUtil.checkDateAsString(filter.getDateBirthCreate(), "dd/MM/yyyy")) {
			errors.rejectValue("dateBirthCreate", "message.valid", "!");
			erroCode = 1;
		}
		
		if (StringUtils.isBlank(filter.getDateStartCreate())) {
			errors.rejectValue("dateStartCreate", "message.empty", "!");
			erroCode = 1;
		}else if (!DateUtil.checkDateAsString(filter.getDateStartCreate(), "dd/MM/yyyy")) {
			errors.rejectValue("dateStartCreate", "message.valid", "!");
			erroCode = 1;
		}
		
		return erroCode;
	}

}
