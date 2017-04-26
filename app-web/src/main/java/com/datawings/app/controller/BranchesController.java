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

import com.datawings.app.common.BeanUtil;
import com.datawings.app.filter.ValidationError;
import com.datawings.app.model.Branches;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.IBranchesService;
import com.datawings.app.validator.BranchesValidator;

@Controller
@SessionAttributes({ "branchesFilter" })
@PreAuthorize(value = "hasRole('ADMIN')")
public class BranchesController {
	@Autowired
	private BranchesValidator branchesValidator;

	@Autowired 
	private MessageSource messageSource;
	
	@Autowired
	private IBranchesService branchesService;
	
	@ModelAttribute("branchesFilter")
	public Branches userFilter() {
		Branches filter = new Branches();
		return filter;
	}
	
	@RequestMapping(value = "/secure/branches", method = RequestMethod.GET)
	public String getBranches(@ModelAttribute("branchesFilter") Branches filter, Model model, HttpServletRequest request, HttpServletResponse response) {
		List<Branches> branches = branchesService.getBranches();
		model.addAttribute("branches", branches);
		return "branches";
	}
	
	@RequestMapping(value = "/secure/branches", method = RequestMethod.POST)
	public String postRdv(@ModelAttribute("branchesFilter") Branches filter, Model model, HttpServletRequest request) {
		
		String action = ServletRequestUtils.getStringParameter(request, "action", "");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		if(StringUtils.equals(action, "CREATE")){
			Branches branch = new Branches();
			
			BeanUtil.copyProperties(branch, filter);
			branch.setCreatedBy(sysUser.getUsername());
			branch.setCreatedDate(new Date());
			branchesService.merge(branch);
			
			filter.init();
		}else if(StringUtils.equals(action, "DELETE")){
			String id = ServletRequestUtils.getStringParameter(request, "id_branch", "");
			branchesService.deleteById(id);
		}else if(StringUtils.equals(action, "MODIFY")){
			Branches branch = branchesService.find(filter.getId());
			branch.setName(filter.getName());
			branch.setFullName(filter.getFullName());
			branch.setManager(filter.getManager());
			branch.setAddress(filter.getAddress());
			branch.setTelephone(filter.getTelephone());
			branch.setModifiedBy(sysUser.getUsername());
			branch.setModifiedDate(new Date());
			
			branchesService.merge(branch);
			filter.init();
		}
		
		return "redirect:/secure/branches";	
	}
	
	@RequestMapping(value = {"/secure/branches/edit/{id}"}, method = RequestMethod.GET)
	public String getAircraft(@PathVariable String id, Model model) {	
		Branches branches = branchesService.find(id);
		model.addAttribute("branches", branches);
		return "branchesEdit";
	}
	
	@RequestMapping(value = "/secure/branches/error.json", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doValidatorAgent(@ModelAttribute("branchesFilter") Branches filter, BindingResult result, Model model, Locale locale){
		HashMap<String, Object> rs = new HashMap<String, Object>();
		int errCode = 0;
		errCode = branchesValidator.checkBranches(filter, result);
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
	
	@RequestMapping(value = "/secure/branches/edit/error.json", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doValidatorBranchesEdit(@ModelAttribute("branchesFilter") Branches filter, BindingResult result, Model model, Locale locale){
		HashMap<String, Object> rs = new HashMap<String, Object>();
		int errCode = 0;
		errCode = branchesValidator.checkBranchesEdit(filter, result);
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
