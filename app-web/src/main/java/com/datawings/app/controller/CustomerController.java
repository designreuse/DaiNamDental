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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.datawings.app.common.DateUtil;
import com.datawings.app.filter.CustomerFiler;
import com.datawings.app.filter.ValidationError;
import com.datawings.app.model.Branches;
import com.datawings.app.model.Customer;
import com.datawings.app.model.CustomerId;
import com.datawings.app.model.Dentist;
import com.datawings.app.model.SysUser;
import com.datawings.app.service.IBranchesService;
import com.datawings.app.service.ICustomerService;
import com.datawings.app.service.IDentistService;
import com.datawings.app.validator.CustomerValidator;

@Controller
@SessionAttributes({ "customerFilter", "customerDetailFilter" })
public class CustomerController {

	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IDentistService dentistService;
	
	@Autowired
	private CustomerValidator customerValidator;
	
	@Autowired 
	private MessageSource messageSource;
	
	@Autowired
	private IBranchesService branchesService;
	
	@ModelAttribute("customerFilter")
	public CustomerFiler customerFilter() {
		CustomerFiler filter = new CustomerFiler();
		filter.setDateStartCreate(DateUtil.date2String(new Date(), "dd/MM/yyyy"));
		return filter;
	}
	
	@ModelAttribute("customerDetailFilter")
	public CustomerFiler customerDetailFilter() {
		CustomerFiler filter = new CustomerFiler();
		return filter;
	}
	
	@RequestMapping(value = "/secure/customer", method = RequestMethod.GET)
	public String getDentist(@ModelAttribute("customerFilter") CustomerFiler filter, Model model, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		request.getSession().setAttribute("FROM_PAGE", "CUSTOMER");
		
		Integer rowCount = customerService.getCustomerRowCount(filter, sysUser);
		filter.setRowCount(rowCount);
		
		List<Dentist> dentists = dentistService.findSysUser(sysUser);
		List<Branches> branches = branchesService.getBranches();
		model.addAttribute("dentists", dentists);
		model.addAttribute("branches", branches);
		return "customer";
	}
	
	@RequestMapping(value = "secure/customer/loadpage", method = RequestMethod.GET)
	public String getDentistLoadPage(@ModelAttribute("customerFilter") CustomerFiler filter, Integer pageNo, Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		filter.setPage(pageNo);
		List<Customer> customers = customerService.getCustomers(filter, sysUser);
		model.addAttribute("customers", customers);
		model.addAttribute("row", (filter.getPage()) * filter.getPageSize());
		return "customerLoadPage";
	}
	
	@RequestMapping(value = "/secure/customer", method = RequestMethod.POST)
	public String postRdv(@ModelAttribute("customerFilter") CustomerFiler filter, Model model, HttpServletRequest request) {
		
		String action = ServletRequestUtils.getStringParameter(request, "action", "");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		if(StringUtils.equals(action, "CREATE")){
			Customer customer = new Customer();
			customer.getId().setSerial(filter.getSerialCreate());
			customer.setName(filter.getNameCreate().trim().toUpperCase());
			customer.setSex(filter.getSexCreate());
			customer.setType(filter.getTypeCreate());
			customer.setDateBirth(DateUtil.string2Date(filter.getDateBirthCreate(), "dd/MM/yyyy"));
			customer.setTelephone(filter.getTelephoneCreate());
			customer.setEmail(filter.getEmailCreate());
			customer.setAddress(filter.getAddressCreate().toUpperCase());
			customer.setDateStart(DateUtil.string2Date(filter.getDateStartCreate(), "dd/MM/yyyy"));
			customer.setCause(filter.getCauseCreate().toUpperCase());
			customer.setDentist(filter.getDentistCreate());
			customer.setStatus(filter.getStatusCreate());
			customer.getId().setBranch(sysUser.getBranch());
			customer.setNote(filter.getNoteCreate().trim());
			customer.setCreatedBy(sysUser.getUsername());
			customer.setCreatedDate(new Date());
			
			CustomerId customerId = new CustomerId();
			customerId.setSerial(filter.getSerialCreate());
			customerId.setBranch(sysUser.getBranch());
			Customer customerCheck = customerService.find(customerId);
			
			if(customerCheck == null){
				customerService.save(customer);
			}
			
			filter.init();
			return "redirect:/secure/records?id=" + customer.getId().getSerial() + "&agency=" + customer.getId().getBranch();
			
		}else if(StringUtils.equals(action, "DELETE")){
			String id = ServletRequestUtils.getStringParameter(request, "id", "");
			String branch = ServletRequestUtils.getStringParameter(request, "agency", "");
			CustomerId customerId = new CustomerId();
			customerId.setSerial(id);
			customerId.setBranch(branch);
			
			customerService.deleteById(customerId);
			
		}else if(StringUtils.equals(action, "GO")){
			filter.setPage(0);
		}else if(StringUtils.equals(action, "RESET")){
			filter.init();
		}
		return "redirect:/secure/customer";
	}
	
	@RequestMapping(value = "/secure/customer/detail", method = RequestMethod.GET)
	public String getCustomerDetail(@ModelAttribute("customerDetailFilter") CustomerFiler filter, Model model, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		String id = ServletRequestUtils.getStringParameter(request, "id", "");
		String branch = ServletRequestUtils.getStringParameter(request, "agency", "");
		
		CustomerId customerId = new CustomerId();
		customerId.setSerial(id);
		customerId.setBranch(branch);
		Customer customer = customerService.find(customerId);
		if(customer == null){
			return "redirect:/secure/customer";
		}
			
		List<Dentist> dentists = dentistService.findSysUser(sysUser);
		List<Branches> branches = branchesService.getBranches();
		
		model.addAttribute("branches", branches);
		model.addAttribute("customer", customer);
		model.addAttribute("dentists", dentists);
		return "customerDetail";
	}
	
	@RequestMapping(value = "/secure/customer/detail", method = RequestMethod.POST)
	public String postCustomerDetail(@ModelAttribute("customerDetailFilter") CustomerFiler filter, Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		String action = ServletRequestUtils.getStringParameter(request, "action", "");
		String id = ServletRequestUtils.getStringParameter(request, "id", "");
		String branch = ServletRequestUtils.getStringParameter(request, "agency", "");
		
		CustomerId customerId = new CustomerId();
		customerId.setSerial(id);
		customerId.setBranch(branch);
		
		if(StringUtils.equals(action, "GO")){
			Customer customer = customerService.find(customerId);
			customer.setName(filter.getNameCreate().trim().toUpperCase());
			customer.setSex(filter.getSexCreate());
			customer.setType(filter.getTypeCreate());
			customer.setDateBirth(DateUtil.string2Date(filter.getDateBirthCreate(), "dd/MM/yyyy"));
			customer.setTelephone(filter.getTelephoneCreate());
			customer.setEmail(filter.getEmailCreate());
			customer.setAddress(filter.getAddressCreate().toUpperCase());
			customer.setDateStart(DateUtil.string2Date(filter.getDateStartCreate(), "dd/MM/yyyy"));
			customer.setCause(filter.getCauseCreate().toUpperCase());
			customer.setDentist(filter.getDentistCreate());
			customer.setStatus(filter.getStatusCreate());
			customer.setNote(filter.getNoteCreate().trim());
			customer.setModifiedBy(sysUser.getUsername());
			customer.setModifiedDate(new Date());
			
			customerService.merge(customer);
			filter.init();
		} else if(StringUtils.equals(action, "BACK")){
			String fromPage = (String) request.getSession().getAttribute("FROM_PAGE");
			
			if(StringUtils.equals(fromPage, "CUSTOMER")){
				return "redirect:/secure/customer";
			}else if(StringUtils.equals(fromPage, "SCHEDULE")){
				return "redirect:/secure/schedule";
			}else if(StringUtils.equals(fromPage, "REALITY")){
				return "redirect:/secure/customer/reality";
			}else if(StringUtils.equals(fromPage, "DASHBOARD")){
				return "redirect:/secure/dashboard";
			}
		}
		
		return "redirect:/secure/customer/detail?id=" + id + "&agency=" + branch;
	}
	
	@RequestMapping(value = "/secure/customer/errorCreate.json", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doValidatorAgent(@ModelAttribute("customerFilter") CustomerFiler filter, BindingResult result, Model model, Locale locale){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		HashMap<String, Object> rs = new HashMap<String, Object>();
		int errCode = 0;
		errCode = customerValidator.checkCustomerAdd(filter, sysUser, result);
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
	
	@RequestMapping(value = "/secure/customer/errorEdit.json", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doValidatorEdit(@ModelAttribute("customerDetailFilter") CustomerFiler filter, BindingResult result, Model model, Locale locale){
		HashMap<String, Object> rs = new HashMap<String, Object>();
		int errCode = 0;
		errCode = customerValidator.checkCustomerEdit(filter, result);
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
