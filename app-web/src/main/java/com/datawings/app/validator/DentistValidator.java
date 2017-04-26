package com.datawings.app.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.datawings.app.filter.DentistFilter;
import com.datawings.app.model.Dentist;
import com.datawings.app.service.IDentistService;

@Component
public class DentistValidator {

	@Autowired
	private IDentistService dentistService;
	
	public int checkDentistAdd(Object target, Errors errors) {
		DentistFilter filter = (DentistFilter) target;

		Integer erro_code = 0;
		
		if (StringUtils.isBlank(filter.getNameCreate())) {
			errors.rejectValue("nameCreate", "message.empty", "!");
			erro_code = 1;
		}else {
			Dentist dentist = dentistService.findByName(filter);
			if (dentist != null) {
				errors.rejectValue("nameCreate", "message.exist", "!");
				erro_code = 1;
			}
		}
		return erro_code;
	}

	public int checkDentistEdit(Object target, Errors errors) {
		DentistFilter filter = (DentistFilter) target;

		Integer erro_code = 0;
		
		if (StringUtils.isBlank(filter.getNameModify())) {
			errors.rejectValue("nameModify", "message.empty", "!");
			erro_code = 1;
		}
		return erro_code;
	}
	
}
