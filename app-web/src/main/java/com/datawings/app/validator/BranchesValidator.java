package com.datawings.app.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.datawings.app.model.Branches;
import com.datawings.app.service.IBranchesService;

@Component
public class BranchesValidator {

	@Autowired
	private IBranchesService branchesService;
	
	public Integer checkBranches(Object target, Errors errors) {
		Branches filter = (Branches) target;

		Integer erro_code = 0;
		
		if (StringUtils.isBlank(filter.getId())) {
			errors.rejectValue("id", "message.empty", "!");
			erro_code = 1;
		} else if (StringUtils.isBlank(filter.getName())) {
			errors.rejectValue("name", "message.empty", "!");
			erro_code = 1;
		}else if (StringUtils.isBlank(filter.getFullName())) {
			errors.rejectValue("fullName", "message.empty", "!");
			erro_code = 1;
		}else if (StringUtils.isBlank(filter.getManager())) {
			errors.rejectValue("manager", "message.empty", "!");
			erro_code = 1;
		}else {
			Branches branch = branchesService.find(filter.getId());
			if (branch != null) {
				errors.rejectValue("id", "message.exist", "!");
				erro_code = 1;
			}
		}
		return erro_code;
	}

	public int checkBranchesEdit(Object target, Errors errors) {
		Branches filter = (Branches) target;

		Integer erro_code = 0;
		
		if (StringUtils.isBlank(filter.getName())) {
			errors.rejectValue("name", "message.empty", "!");
			erro_code = 1;
		} else if (StringUtils.isBlank(filter.getManager())) {
			errors.rejectValue("manager", "message.empty", "!");
			erro_code = 1;
		}else if (StringUtils.isBlank(filter.getFullName())) {
			errors.rejectValue("fullName", "message.empty", "!");
			erro_code = 1;
		}
		return erro_code;
	}
}
