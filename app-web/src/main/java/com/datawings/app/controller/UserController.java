package com.datawings.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.datawings.app.filter.UserFilter;
import com.datawings.app.filter.ValidationError;
import com.datawings.app.model.Branches;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.IBranchesService;
import com.datawings.app.service.ISysUserService;
import com.datawings.app.validator.UserValidator;

@Controller
@SessionAttributes({ "userFilter" })
@PreAuthorize(value = "hasRole('ADMIN')")
@RequestMapping("/secure/")
public class UserController {
	
	@Autowired
	private ISysUserService sysUserService;
	
	@Autowired
	private IBranchesService branchesService;
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserValidator userValidator;
	
	@ModelAttribute("userFilter")
	public UserFilter userFilter() {
		UserFilter filter = new UserFilter();
		return filter;
	}

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String getUser(@ModelAttribute("userFilter") UserFilter filter, Model model) throws IOException {
		List<Branches> branches = branchesService.getBranches();
		model.addAttribute("branches", branches);
		
		return "user";
	}

	@RequestMapping(value = "user/loadpage", method = RequestMethod.GET)
	public String loadPageAjax(@ModelAttribute("userFilter") UserFilter filter, Model model) {
		List<SysUser> users = sysUserService.getUser(filter);
		model.addAttribute("users", users);
		
		return "userLoadPage";
	}

	@RequestMapping(value = "user", method = RequestMethod.POST)
	public String postUser(@ModelAttribute("userFilter") UserFilter filter, Model model, HttpServletRequest request) {
		String action = ServletRequestUtils.getStringParameter(request, "action", "");
		SysUser userLogin = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (StringUtils.equals(action, "CREATE")){
			SysUser sysUser = new SysUser();
			sysUser.setUsername(filter.getUsernameCreate());
			sysUser.setPassword(filter.getPasswordCreate());
			sysUser.setName(filter.getNameCreate());
			sysUser.setTelephone(filter.getTelephoneCreate());
			sysUser.setEmail(filter.getEmailCreate());
			sysUser.setRole(filter.getRoleCreate());
			sysUser.setBranch(filter.getBranchCreate());
			sysUser.setIsEnable(true);
			sysUser.setCreatedBy(userLogin.getUsername());
			sysUser.setCreatedDate(new Date());
			sysUser.setModifiedBy(userLogin.getUsername());
			sysUser.setModifiedDate(new Date());
			
			sysUserService.merge(sysUser);
			
			filter.initCreate();
			
		}else if (StringUtils.equals(action, "DELETE")){
			String id = ServletRequestUtils.getStringParameter(request, "id", "");
			sysUserService.deleteById(id);
		}else if (StringUtils.equals(action, "MODIFY")){
			SysUser sysUser = sysUserService.find(filter.getUsernameModify());
			sysUser.setPassword(filter.getPasswordModify());
			sysUser.setName(filter.getNameModify());
			sysUser.setTelephone(filter.getTelephoneModify());
			sysUser.setEmail(filter.getEmailModify());
			sysUser.setRole(filter.getRoleModify());
			sysUser.setBranch(filter.getBranchModify());
			sysUser.setModifiedBy(userLogin.getUsername());
			sysUser.setModifiedDate(new Date());
			
			sysUserService.merge(sysUser);
		}
		
		return "redirect:/secure/user";
	}

	@RequestMapping(value = "user/errorCreate.json", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doValidatorCreate(@ModelAttribute("userFilter") UserFilter filter, BindingResult result, Locale locale) {
		HashMap<String, Object> rs = new HashMap<String, Object>();
		int errCode = 0;
		errCode = userValidator.doValidatorCreate(filter, result);
		
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
	
	@RequestMapping(value = "user/model/{id}", method = RequestMethod.GET)
	public String getUserModify(@PathVariable String id, Model model) {
		SysUser sysUser = sysUserService.find(id);
		List<Branches> branches = branchesService.getBranches();
		model.addAttribute("branches", branches);
		model.addAttribute("sysUser", sysUser);
		return "userModel";
	}

	@RequestMapping(value = "user/errorEdit.json", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doValidatorEdit(@ModelAttribute("userFilter") UserFilter filter, BindingResult result, Locale locale) {
		HashMap<String, Object> rs = new HashMap<String, Object>();
		int errCode = 0;
		errCode = userValidator.doValidatorEdit(filter, result);
		
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