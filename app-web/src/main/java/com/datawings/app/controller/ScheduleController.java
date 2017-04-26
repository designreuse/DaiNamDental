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
import com.datawings.app.filter.RecordsFilter;
import com.datawings.app.model.Branches;
import com.datawings.app.model.Dentist;
import com.datawings.app.model.Records;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.IBranchesService;
import com.datawings.app.service.IDentistService;
import com.datawings.app.service.IRecordsService;

@Controller
@SessionAttributes({ "scheduleFilter" })
public class ScheduleController {

	@Autowired
	private IRecordsService recordsService;
	
	@Autowired
	private IDentistService dentistService;
	
	@Autowired
	private IBranchesService branchesService;
	
	@ModelAttribute("scheduleFilter")
	public RecordsFilter userFilter() {
		RecordsFilter filter = new RecordsFilter();
		filter.setDateNextFrom(DateUtil.date2String(new Date(), "dd/MM/yyyy"));
		return filter;
	}
	
	@RequestMapping(value = "/secure/schedule", method = RequestMethod.GET)
	public String getDentist(@ModelAttribute("scheduleFilter") RecordsFilter filter, Model model, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		request.getSession().setAttribute("FROM_PAGE", "SCHEDULE");
		
		Integer rowCount = recordsService.getScheduleRowCount(filter, sysUser);
		filter.setRowCount(rowCount);
		List<Dentist> dentists =  dentistService.findSysUser(sysUser);
		List<Branches> branches = branchesService.getBranches();
		
		model.addAttribute("branches", branches);
		model.addAttribute("dentists", dentists);
		return "schedule";
	}
	
	@RequestMapping(value = "/secure/schedule/loadpage", method = RequestMethod.GET)
	public String getDentistLoadPage(@ModelAttribute("scheduleFilter") RecordsFilter filter, Integer pageNo, Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		filter.setPage(pageNo);
		List<Records> records = recordsService.getSchedule(filter, sysUser);
		model.addAttribute("records", records);
		model.addAttribute("row", (filter.getPage()) * filter.getPageSize());
		return "scheduleLoadPage";
	}
	
	@RequestMapping(value = "/secure/schedule", method = RequestMethod.POST)
	public String postRdv(@ModelAttribute("scheduleFilter") RecordsFilter filter, Model model, HttpServletRequest request) {
		
		String action = ServletRequestUtils.getStringParameter(request, "action", "");
		
		if(StringUtils.equals(action, "GO")){
			filter.setPage(0);
		}else if (StringUtils.equals(action, "RESET")) {
			filter.init();
		}
		return "redirect:/secure/schedule";
	}
	
}
