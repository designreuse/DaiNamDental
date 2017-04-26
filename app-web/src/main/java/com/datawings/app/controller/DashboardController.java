package com.datawings.app.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.datawings.app.common.DateUtil;
import com.datawings.app.filter.RecordsFilter;
import com.datawings.app.model.Records;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.IRecordsService;

@Controller
@RequestMapping("/secure/")
@SessionAttributes({ "dashboardFilter" })
public class DashboardController {

	@Autowired
	private IRecordsService recordsService;

	@ModelAttribute("dashboardFilter")
	public RecordsFilter customerFilter() {
		RecordsFilter filter = new RecordsFilter();
		filter.setDateNextFrom(DateUtil.date2String(new Date(), "dd/MM/yyyy"));
		filter.setDateNextTo(DateUtil.date2String(new Date(), "dd/MM/yyyy"));
		return filter;
	}
	
	
	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public String getDashboard(@ModelAttribute("dashboardFilter") RecordsFilter filter, Model model, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		request.getSession().setAttribute("FROM_PAGE", "DASHBOARD");
		
		Integer rowCount = recordsService.getCountScheduleDashboard(filter, sysUser);
		filter.setRowCount(rowCount);
		
		return "dashboard";
	}
	
	@RequestMapping(value = "dashboard/loadpage", method = RequestMethod.GET)
	public String getDentistLoadPage(@ModelAttribute("dashboardFilter") RecordsFilter filter, Integer pageNo, Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		filter.setPage(pageNo);
		List<Records> records = recordsService.getScheduleDashboard(filter, sysUser, pageNo);
		model.addAttribute("records", records);
		model.addAttribute("row", (filter.getPage()) * filter.getPageSize());
		return "dashboardLoadPage";
	}

	@RequestMapping(value = "dashboard", method = RequestMethod.POST)
	public String postDashboard(Model model, HttpServletRequest request, HttpServletResponse response) {

		return "redirect:/secure/dashboard";
	}

}