package com.datawings.app.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.datawings.app.common.DateUtil;
import com.datawings.app.common.IntegerUtil;
import com.datawings.app.common.StringUtilz;
import com.datawings.app.filter.InvoiceFilter;
import com.datawings.app.filter.ValidationError;
import com.datawings.app.model.Branches;
import com.datawings.app.model.Invoice;
import com.datawings.app.model.InvoiceDetail;
import com.datawings.app.model.InvoiceId;
import com.datawings.app.model.SysUser;
import com.datawings.app.report.InvoicePdf;
import com.datawings.app.service.IBranchesService;
import com.datawings.app.service.IInvoiceDetailService;
import com.datawings.app.service.IInvoiceService;
import com.datawings.app.validator.InvoiceValidator;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@SessionAttributes({ "invoiceFilter" })
public class InvoiceController {

	@Autowired
	private IInvoiceService invoiceService;
	
	@Autowired
	private IInvoiceDetailService invoiceDetailService;
	
	@Autowired
	private InvoiceValidator invoiceValidator;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private IBranchesService branchesService;
	
	@ModelAttribute("invoiceFilter")
	public InvoiceFilter invoiceFilterFilter() {
		InvoiceFilter filter = new InvoiceFilter();
		return filter;
	}
	
	@RequestMapping(value = "/secure/invoice", method = RequestMethod.GET)
	public String getInvoice(@ModelAttribute("invoiceFilter") InvoiceFilter filter, Model model, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		Integer rowCount = invoiceService.getInvoiceRowCount(filter, sysUser);
		filter.setRowCount(rowCount);
		List<Branches> branches = branchesService.getBranches();
		model.addAttribute("branches", branches);
		return "invoice";
	}
	
	@RequestMapping(value = "secure/invoice/loadpage", method = RequestMethod.GET)
	public String getInvoiceLoadPage(@ModelAttribute("invoiceFilter") InvoiceFilter filter, Integer pageNo, Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		filter.setPage(pageNo);
		List<Invoice> invoices = invoiceService.getListInvoice(filter, sysUser);
		
		model.addAttribute("invoices", invoices);
		model.addAttribute("row", (filter.getPage()) * filter.getPageSize());
		return "invoiceLoadPage";
	}
	
	@RequestMapping(value = "/secure/invoice/errorCreate.json", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doValidatorInvoice(@ModelAttribute("invoiceFilter") InvoiceFilter filter, BindingResult result, Model model, Locale locale){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		HashMap<String, Object> rs = new HashMap<String, Object>();
		int errCode = 0;
		errCode = invoiceValidator.checkInvoiceAdd(filter, sysUser, result);
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
	
	@RequestMapping(value = "/secure/invoice", method = RequestMethod.POST)
	public String postInvoice(@ModelAttribute("invoiceFilter") InvoiceFilter filter, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws IOException, com.lowagie.text.DocumentException {
		
		String action = ServletRequestUtils.getStringParameter(request, "action", "");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		if(StringUtils.equals(action, "CREATE")){
			processCreateInvoice(filter, sysUser);
			
		}else if(StringUtils.equals(action, "CREATE_PRINT")){
			Integer id = processCreateInvoice(filter, sysUser);
			return "redirect:/secure/invoice/pdf?bill=" + id;
			
		}else if(StringUtils.equals(action, "RESET")){
			filter.init();
		}else if(StringUtils.equals(action, "GO")){
			filter.setPage(0);
		}else if(StringUtils.equals(action, "DELETE")){
			String id = ServletRequestUtils.getStringParameter(request, "id", "");
			String branch = ServletRequestUtils.getStringParameter(request, "agency", "");
			InvoiceId invoiceId = new InvoiceId();
			invoiceId.setSerial(id);
			invoiceId.setBranch(branch);
			
			invoiceService.deleteById(invoiceId);
		}
		
		return "redirect:/secure/invoice";
	}
	
	@RequestMapping(value = "secure/invoice/pdf", method = RequestMethod.GET)
	public String getInvoicePdf(Model model, HttpServletRequest request){
		int id = ServletRequestUtils.getIntParameter(request, "bill", 0);
		InvoiceDetail invoice = invoiceDetailService.find(id);
		Branches branch = branchesService.find(invoice.getBranch());
		
		model.addAttribute("branch", branch);
		model.addAttribute("invoice", invoice);
		return "invoicePdf";
	}
	
	
	@RequestMapping(value = "secure/invoice/print", method = RequestMethod.GET)
	public ModelAndView getInvoicePdfview(Model model, HttpServletRequest request) throws DocumentException, IOException{
		int id = ServletRequestUtils.getIntParameter(request, "id", 0);
		InvoiceDetail invoice = invoiceDetailService.find(id);
		Branches branch = branchesService.find(invoice.getBranch());
		
		String pathImage = request.getSession().getServletContext().getRealPath("/static/images/logopdf.png"); 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4.rotate());
		
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		writer.setViewerPreferences(PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage);
		
		InvoicePdf invoicePdf = new InvoicePdf();
		document.open();
		document = invoicePdf.makePdf(document, request, invoice, branch, pathImage);
		document.close();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("document", document);
		map.put("baos", baos);

		return new ModelAndView("fileView", "map", map);
	}
	
	@RequestMapping(value = "/secure/invoice/detail", method = RequestMethod.GET)
	public String getInvoiceDetail(@ModelAttribute("invoiceFilter") InvoiceFilter filter, Model model, HttpServletRequest request, HttpServletResponse response) {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//SysUser sysUser = (SysUser) auth.getPrincipal();
		String id = ServletRequestUtils.getStringParameter(request, "id", "");
		String branch = ServletRequestUtils.getStringParameter(request, "agency", "");
		
		InvoiceId invoiceId = new InvoiceId();
		invoiceId.setSerial(id);
		invoiceId.setBranch(branch);
		
		Invoice invoice = invoiceService.find(invoiceId);
		if(invoice == null){
			return "redirect:/secure/invoice";
		}
		model.addAttribute("invoice", invoice);
		return "invoiceDetail";
	}
	
	@RequestMapping(value = "/secure/invoice/detail", method = RequestMethod.POST)
	public String postInvoiceDetail(@ModelAttribute("invoiceFilter") InvoiceFilter filter, Model model, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SysUser sysUser = (SysUser) auth.getPrincipal();
		
		String action = ServletRequestUtils.getStringParameter(request, "action", "");
		String id = ServletRequestUtils.getStringParameter(request, "id", "");
		String branch = ServletRequestUtils.getStringParameter(request, "agency", "");
		
		InvoiceId invoiceId = new InvoiceId();
		invoiceId.setSerial(id);
		invoiceId.setBranch(branch);
		
		if(StringUtils.equals(action, "CREATE")){
			Invoice invoice = invoiceService.find(invoiceId);
			processCreateInvoiceDetail(invoice, filter, sysUser);
			
		}else if(StringUtils.equals(action, "CREATE_PRINT")){
			Invoice invoice = invoiceService.find(invoiceId);
			Integer invoiceDetailId = processCreateInvoiceDetail(invoice, filter, sysUser);
			return "redirect:/secure/invoice/pdf?bill=" + invoiceDetailId;
			
		}else if(StringUtils.equals(action, "DELETE")){
			Invoice invoice = invoiceService.find(invoiceId);
			Integer idInvoiceDetail = ServletRequestUtils.getIntParameter(request, "idInvoiceDetail", 0);
			InvoiceDetail invoiceDetail = invoiceDetailService.find(idInvoiceDetail);
			
			invoice.setAmount(invoice.getAmount() - invoiceDetail.getAmount());
			invoiceDetailService.deleteById(idInvoiceDetail);
			invoiceService.merge(invoice);
		}else if(StringUtils.equals(action, "MODIFY")){
			
			InvoiceId invoiceIdx = new InvoiceId();
			invoiceIdx.setSerial(filter.getSerialEdit());
			invoiceIdx.setBranch(filter.getBranchEdit());
			
			Invoice invoice = invoiceService.find(invoiceIdx);
			InvoiceDetail invoiceDetail = invoiceDetailService.find(filter.getIdInvoice());
			
			invoice.setAmount(invoice.getAmount() + IntegerUtil.convertInteger(StringUtilz.replaceMoney(filter.getAmountEdit())) - invoiceDetail.getAmount());
			invoiceService.merge(invoice);
			
			invoiceDetail.setDateExcute(DateUtil.string2Date(filter.getDateExcuteEdit(), "dd/MM/yyyy"));
			invoiceDetail.setCause(filter.getCauseEdit().toUpperCase());
			invoiceDetail.setAmount(IntegerUtil.convertInteger(StringUtilz.replaceMoney(filter.getAmountEdit())));
			
			invoiceDetail.setContent(filter.getContentEdit().toUpperCase());
			invoiceDetail.setGross(IntegerUtil.convertInteger(StringUtilz.replaceMoney(filter.getGrossEdit())));
			invoiceDetail.setPayment(IntegerUtil.convertInteger(StringUtilz.replaceMoney(filter.getPaymentEdit())));
			invoiceDetail.setNote(filter.getNote().toUpperCase());
			invoiceDetail.setModifiedBy(sysUser.getUsername());
			invoiceDetail.setModifiedDate(new Date());
			
			invoiceDetailService.merge(invoiceDetail);
			
			return "redirect:/secure/invoice/detail?id=" + filter.getSerialEdit() + "&agency=" + filter.getBranchEdit();
		}
		
		return "redirect:/secure/invoice/detail?id=" + id + "&agency=" + branch;
	}
	
	@RequestMapping(value = {"/secure/invoice/edit/{id}"}, method = RequestMethod.GET)
	public String getInvoice(@PathVariable Integer id, Model model) {
		InvoiceDetail invoiceDetail = invoiceDetailService.find(id);
		
		model.addAttribute("invoiceDetail", invoiceDetail);
		return "invoiceEdit";
	}
	
	@RequestMapping(value = "/secure/invoice/detail/errorCreate.json", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doValidatorInvoiceDetail(@ModelAttribute("invoiceFilter") InvoiceFilter filter, BindingResult result, Model model, Locale locale){
		HashMap<String, Object> rs = new HashMap<String, Object>();
		int errCode = 0;
		errCode = invoiceValidator.checkInvoiceDetailAdd(filter, result);
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
	
	@RequestMapping(value = "/secure/invoice/errorEdit.json", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doValidatorInvoiceDetailEdit(@ModelAttribute("invoiceFilter") InvoiceFilter filter, BindingResult result, Model model, Locale locale){
		HashMap<String, Object> rs = new HashMap<String, Object>();
		int errCode = 0;
		errCode = invoiceValidator.checkInvoiceDetailEdit(filter, result);
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
	
	private Integer processCreateInvoice(InvoiceFilter filter, SysUser sysUser) {
		Invoice invoice = new Invoice();
		invoice.getId().setSerial(filter.getSerialCreate());
		invoice.setNameCustomer(filter.getNameCustomerCreate().toUpperCase());
		invoice.setTelephone(filter.getTelephoneCreate());
		invoice.setAddress(filter.getAddressCreate().toUpperCase());
		invoice.getId().setBranch(sysUser.getBranch());
		invoice.setAmount(IntegerUtil.convertInteger( StringUtilz.replaceMoney(filter.getAmountCreate())));
		invoice.setCreatedBy(sysUser.getUsername());
		invoice.setCreatedDate(new Date());
		
		invoiceService.save(invoice);
		
		InvoiceDetail invoiceDetail = new InvoiceDetail();
		invoiceDetail.setSerial(invoice.getId().getSerial());
		invoiceDetail.setBranch(invoice.getId().getBranch());
		invoiceDetail.setDateExcute(DateUtil.string2Date(filter.getDateExcuteCreate(), "dd/MM/yyyy"));
		invoiceDetail.setCause(filter.getCauseCreate().toUpperCase());
		invoiceDetail.setAmount(IntegerUtil.convertInteger(StringUtilz.replaceMoney(filter.getAmountCreate())));
		
		invoiceDetail.setContent(filter.getContentCreate().toUpperCase());
		invoiceDetail.setGross(IntegerUtil.convertInteger(StringUtilz.replaceMoney(filter.getGrossCreate())));
		invoiceDetail.setPayment(IntegerUtil.convertInteger(StringUtilz.replaceMoney(filter.getPaymentCreate())));
		
		invoiceDetail.setCreatedBy(sysUser.getUsername());
		invoiceDetail.setCreatedDate(new Date());
		
		invoiceDetailService.save(invoiceDetail);
		
		filter.initCreate();
		return invoiceDetail.getId();
	}
	
	private Integer processCreateInvoiceDetail(Invoice invoice, InvoiceFilter filter, SysUser sysUser) {
		InvoiceDetail invoiceDetail = new InvoiceDetail();
		invoiceDetail.setSerial(invoice.getId().getSerial());
		invoiceDetail.setBranch(invoice.getId().getBranch());
		
		invoiceDetail.setDateExcute(DateUtil.string2Date(filter.getDateExcuteCreate(), "dd/MM/yyyy"));
		invoiceDetail.setCause(filter.getCauseCreate().toUpperCase());
		invoiceDetail.setAmount(IntegerUtil.convertInteger(StringUtilz.replaceMoney(filter.getAmountCreate())));
		
		invoiceDetail.setContent(filter.getContentCreate().toUpperCase());
		invoiceDetail.setGross(IntegerUtil.convertInteger(StringUtilz.replaceMoney(filter.getGrossCreate())));
		invoiceDetail.setPayment(IntegerUtil.convertInteger(StringUtilz.replaceMoney(filter.getPaymentCreate())));
		
		invoiceDetail.setCreatedBy(sysUser.getUsername());
		invoiceDetail.setCreatedDate(new Date());
		
		invoice.setAmount(invoice.getAmount() + invoiceDetail.getAmount());
		invoiceService.merge(invoice);
		invoiceDetailService.save(invoiceDetail);
		filter.initCreate();
		
		return invoiceDetail.getId();
	}
}
