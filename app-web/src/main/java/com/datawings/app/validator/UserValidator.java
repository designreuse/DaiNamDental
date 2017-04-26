package com.datawings.app.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.datawings.app.filter.UserFilter;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.ISysUserService;

@Component
public class UserValidator {

	@Autowired
	private ISysUserService sysUserService;
	
	public int doValidatorCreate(Object target, Errors errors) {
		UserFilter filter = (UserFilter) target;

		Integer errCode = 0;
		
		if(StringUtils.isBlank(filter.getUsernameCreate())){
			errors.rejectValue("usernameCreate", "message.empty", "!");
			errCode = 1;
		}else {
			SysUser sysUser = sysUserService.find(filter.getUsernameCreate());
			if(sysUser != null){
				errors.rejectValue("usernameCreate", "message.exist", "!");
				errCode = 1;
			}
		}
		
		if(StringUtils.isBlank(filter.getPasswordCreate())){
			errors.rejectValue("passwordCreate", "message.empty", "!");
			errCode = 1;
		} else if(StringUtils.isBlank(filter.getRetypePasswordCreate())){
			errors.rejectValue("retypePasswordCreate", "message.empty", "!");
			errCode = 1;
		} else if(!StringUtils.equals(filter.getPasswordCreate(), filter.getRetypePasswordCreate())){
			errors.rejectValue("retypePasswordCreate", "message.retype", "!");
			errCode = 1;
		}
		
		return errCode;
	}

	public int doValidatorEdit(Object target, Errors errors) {
		UserFilter filter = (UserFilter) target;
		
		Integer errCode = 0;
		
		if(StringUtils.isBlank(filter.getPasswordModify())){
			errors.rejectValue("passwordModify", "message.empty", "!");
			errCode = 1;
		} else if(StringUtils.isBlank(filter.getRetypePasswordModify())){
			errors.rejectValue("retypePasswordModify", "message.empty", "!");
			errCode = 1;
		} else if(!StringUtils.equals(filter.getPasswordModify(), filter.getRetypePasswordModify())){
			errors.rejectValue("retypePasswordModify", "message.retype", "!");
			errCode = 1;
		}
		
		return errCode;
	}
}
