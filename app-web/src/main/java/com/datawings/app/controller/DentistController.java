package com.datawings.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.datawings.app.filter.DentistFilter;
import com.datawings.app.filter.ValidationError;
import com.datawings.app.model.Branches;
import com.datawings.app.model.Dentist;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.IBranchesService;
import com.datawings.app.service.IDentistService;
import com.datawings.app.validator.DentistValidator;

@Controller
@PreAuthorize(value = "hasRole('ADMIN')")
@SessionAttributes({ "dentistFilter" })
public class DentistController {
	
	@Autowired
	private IDentistService dentistService;
	
	@Autowired
	private IBranchesService branchesService;
	
	@Autowired
	private DentistValidator dentistValidator;
	
	@Autowired 
	private MessageSource messageSource;
	
	@ModelAttribute("dentistFilter")
	public DentistFilter userFilter() {
		DentistFilter filter = new DentistFilter();
		return filter;
	}
	
	@RequestMapping(value = "/secure/dentist", method = RequestMethod.GET)
	public String getDentist(@ModelAttribute("dentistFilter") DentistFilter filter, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		Integer rowCount = dentistService.getDentistRowCount(filter);
		filter.setRowCount(rowCount);
		List<Branches> branches = branchesService.getBranches();
		
		model.addAttribute("branches", branches);
		return "dentist";
	}
	
	@RequestMapping(value = "secure/dentist/loadpage", method = RequestMethod.GET)
	public String getDentistLoadPage(@ModelAttribute("dentistFilter") DentistFilter filter, Integer pageNo, Model model){
		filter.setPage(pageNo);
		List<Dentist> dentists = dentistService.getDentist(filter);
		model.addAttribute("dentists", dentists);
		model.addAttribute("row", (filter.getPage()) * filter.getPageSize());
		return "dentistLoadPage";
	}
	
	@RequestMapping(value = "/secure/dentist", method = RequestMethod.POST)
	public String postRdv(@ModelAttribute("dentistFilter") DentistFilter filter, Model model, HttpServletRequest request) {
		
		String action = ServletRequestUtils.getStringParameter(request, "action", "");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		if(StringUtils.equals(action, "CREATE")){
			Dentist dentist = new Dentist();
			dentist.setName(filter.getNameCreate().trim());
			dentist.setBranch(filter.getBranchCreate());
			dentist.setNote(filter.getNoteCreate().trim());
			dentist.setCreatedBy(sysUser.getUsername());
			dentist.setCreatedDate(new Date());
			dentistService.merge(dentist);
			
			filter.setNameCreate("");
			filter.setBranchCreate("");
			filter.setNoteCreate("");
			
		}else if(StringUtils.equals(action, "DELETE")){
			Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
			dentistService.deleteById(id);
			
		}else if(StringUtils.equals(action, "MODIFY")){
			Dentist dentist = dentistService.find(filter.getIdModify());
			dentist.setName(filter.getNameModify().trim());
			dentist.setBranch(filter.getBranchModify());
			dentist.setNote(filter.getNoteModify().trim());
			
			dentistService.merge(dentist);
			
		}else if(StringUtils.equals(action, "RESET")){
			filter.setPage(0);
			filter.setName("");
			filter.setBranch("");
		}else if(StringUtils.equals(action, "GO")){
			filter.setPage(0);
		}
		return "redirect:/secure/dentist";	
	}
	
	@RequestMapping(value = {"/secure/dentist/edit/{id}"}, method = RequestMethod.GET)
	public String getDentist(@PathVariable Integer id, Model model) {	
		Dentist dentist = dentistService.find(id);
		List<Branches> branches = branchesService.getBranches();
		
		model.addAttribute("branches", branches);
		model.addAttribute("dentist", dentist);
		return "dentistEdit";
	}
	
	@RequestMapping(value = "/secure/dentist/errorCreate.json", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doValidatorAgent(@ModelAttribute("dentistFilter") DentistFilter filter, BindingResult result, Model model, Locale locale){
		HashMap<String, Object> rs = new HashMap<String, Object>();
		int errCode = 0;
		errCode = dentistValidator.checkDentistAdd(filter, result);
		if(errCode > 0){
			rs.put("errCodeCreate", errCode);
			List<ValidationError> lstErr = new ArrayList<ValidationError>();
			for(int i = 0;i <result.getAllErrors().size();i++){
				ValidationError elm = new ValidationError();
				elm.setMessage(messageSource.getMessage(result.getAllErrors().get(i).getCode(), null, 
						result.getAllErrors().get(i).getDefaultMessage(), locale));
				elm.setPropertyName(StringUtils.substringAfterLast(result.getAllErrors().get(i).getCodes()[0], "."));
				lstErr.add(elm);
			}
			rs.put("lstErr", lstErr);
		}
		return rs;
	}
	
	@RequestMapping(value = "/secure/dentist/errorEdit.json", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doValidatorEdit(@ModelAttribute("dentistFilter") DentistFilter filter, BindingResult result, Model model, Locale locale){
		HashMap<String, Object> rs = new HashMap<String, Object>();
		int errCode = 0;
		errCode = dentistValidator.checkDentistEdit(filter, result);
		if(errCode > 0){
			rs.put("errCodeEdit", errCode);
			List<ValidationError> lstErr = new ArrayList<ValidationError>();
			for(int i = 0;i <result.getAllErrors().size();i++){
				ValidationError elm = new ValidationError();
				elm.setMessage(messageSource.getMessage(result.getAllErrors().get(i).getCode(), null, 
						result.getAllErrors().get(i).getDefaultMessage(), locale));
				elm.setPropertyName(StringUtils.substringAfterLast(result.getAllErrors().get(i).getCodes()[0], "."));
				lstErr.add(elm);
			}
			rs.put("lstErr", lstErr);
		}
		return rs;
	}
	
}
