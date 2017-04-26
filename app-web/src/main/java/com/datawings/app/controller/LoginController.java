package com.datawings.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.datawings.app.model.SysUser;
import com.datawings.app.service.ISysUserService;

@Controller
public class LoginController {			
	
	@Autowired
	private ISysUserService sysUserService;
	
	@RequestMapping(value = "/secure/login/success", method = RequestMethod.GET)
	public String loginSuccess(Model model, HttpServletRequest request ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		request.getSession().setAttribute("sysUser", sysUser);
		
		if(sysUser.getRole() != null){
			return "redirect:/secure/dashboard";
		}else{
			return "login";
		}		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
	public String loginFailed(Model model) {		
		model.addAttribute("loginFailed", "Login Failed");
		return "login";
	}

	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String loginAccessDenied(Model model) {
		return "accessdenied";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		return "login";
	}
}