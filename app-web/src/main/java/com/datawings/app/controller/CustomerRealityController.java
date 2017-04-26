package com.datawings.app.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.datawings.app.common.DateUtil;
import com.datawings.app.filter.CustomerFiler;
import com.datawings.app.model.Branches;
import com.datawings.app.model.Dentist;
import com.datawings.app.model.Records;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.IBranchesService;
import com.datawings.app.service.IDentistService;
import com.datawings.app.service.IRecordsService;

@Controller
@SessionAttributes({ "customerRealityFilter" })
public class CustomerRealityController {
	@Autowired
	private IBranchesService branchesService;
	
	@Autowired
	private IDentistService dentistService;
	
	@Autowired
	private IRecordsService recordsService;
	
	@ModelAttribute("customerRealityFilter")
	public CustomerFiler customerFilter() {
		CustomerFiler filter = new CustomerFiler();
		String tmp = DateUtil.date2String(new Date(), "MM/yyyy");
		Date start = DateUtil.string2Date(tmp, "MM/yyyy");
		Date end  = DateUtil.endMonth(start);
		filter.setDateStartFrom(DateUtil.date2String(start, "dd/MM/yyyy"));
		filter.setDateStartTo(DateUtil.date2String(end, "dd/MM/yyyy"));
		
		return filter;
	}
	
	@RequestMapping(value = "/secure/customer/reality", method = RequestMethod.GET)
	public String getDentist(@ModelAttribute("customerRealityFilter") CustomerFiler filter, Model model, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		Integer rowCount = recordsService.getCountCustomerReality(filter, sysUser);
		filter.setRowCount(rowCount);
		
		List<Dentist> dentists = dentistService.findSysUser(sysUser);
		List<Branches> branches = branchesService.getBranches();
		model.addAttribute("dentists", dentists);
		model.addAttribute("branches", branches);
		return "customerReality";
	}
	
	@RequestMapping(value = "secure/customer/reality/loadpage", method = RequestMethod.GET)
	public String getDentistLoadPage(@ModelAttribute("customerRealityFilter") CustomerFiler filter, Integer pageNo, Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		filter.setPage(pageNo);
		List<Records> records = recordsService.getCustomerReality(filter, sysUser);
		Integer totalPayment = recordsService.getTotalCustomerReality(filter, sysUser);
		
		model.addAttribute("row", (filter.getPage()) * filter.getPageSize());
		model.addAttribute("records", records);
		model.addAttribute("totalPayment", totalPayment);
		return "customerRealityLoadPage";
	}
	
	@RequestMapping(value = "/secure/customer/reality", method = RequestMethod.POST)
	public String postRdv(@ModelAttribute("customerRealityFilter") CustomerFiler filter, Model model, HttpServletRequest request) {
		String action = ServletRequestUtils.getStringParameter(request, "action", "");
		
		if(StringUtils.equals(action, "GO")){
			filter.setPage(0);
		}else if(StringUtils.equals(action, "RESET")){
			filter.init();
		}else if(StringUtils.equals(action, "VIEW")){
			request.getSession().setAttribute("FROM_PAGE", "REALITY");
			String id = ServletRequestUtils.getStringParameter(request, "id", "");
			String branch = ServletRequestUtils.getStringParameter(request, "agency", "");
			return "redirect:/secure/records?id=" + id + "&agency=" + branch;
		}
		return "redirect:/secure/customer/reality";
	}
}
