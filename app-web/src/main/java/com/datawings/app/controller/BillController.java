package com.datawings.app.controller;

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

import com.datawings.app.filter.InvoiceFilter;
import com.datawings.app.model.Branches;
import com.datawings.app.model.InvoiceDetail;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.IBranchesService;
import com.datawings.app.service.IInvoiceDetailService;

@Controller
@SessionAttributes({ "billFilter" })
public class BillController {
	@Autowired
	private IInvoiceDetailService invoiceDetailService;
	
	@Autowired
	private IBranchesService branchesService;
	
	@ModelAttribute("billFilter")
	public InvoiceFilter init() {
		InvoiceFilter filter = new InvoiceFilter();
		return filter;
	}
	
	@RequestMapping(value = "/secure/bill", method = RequestMethod.GET)
	public String getDentist(@ModelAttribute("billFilter") InvoiceFilter filter, Model model, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		request.getSession().setAttribute("FROM_PAGE", "CUSTOMER");
		
		Integer rowCount = invoiceDetailService.getBillRowCount(filter, sysUser);
		filter.setRowCount(rowCount);
		List<Branches> branches = branchesService.getBranches();
		model.addAttribute("branches", branches);
		return "bill";
	}
	
	@RequestMapping(value = "secure/bill/loadpage", method = RequestMethod.GET)
	public String getDentistLoadPage(@ModelAttribute("billFilter") InvoiceFilter filter, Integer pageNo, Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		filter.setPage(pageNo);
		List<InvoiceDetail> bills = invoiceDetailService.getBills(filter, sysUser);
		Integer totalBill = invoiceDetailService.getTotalBills(filter, sysUser);
		
		model.addAttribute("bills", bills);
		model.addAttribute("totalBill", totalBill);
		model.addAttribute("row", (filter.getPage()) * filter.getPageSize());
		return "billLoadPage";
	}
	
	@RequestMapping(value = "/secure/bill", method = RequestMethod.POST)
	public String postRdv(@ModelAttribute("billFilter") InvoiceFilter filter, Model model, HttpServletRequest request) {
		String action = ServletRequestUtils.getStringParameter(request, "action", "");
		
		if(StringUtils.equals(action, "GO")){
			filter.setPage(0);
		}else if(StringUtils.equals(action, "RESET")){
			filter.init();
		}
		return "redirect:/secure/bill";
	}
	
}
