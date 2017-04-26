package com.datawings.app.report;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.datawings.app.common.DateUtil;
import com.datawings.app.common.IntegerUtil;
import com.datawings.app.common.PropertiesUtils;
import com.datawings.app.model.Branches;
import com.datawings.app.model.InvoiceDetail;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;

public class InvoicePdf extends APdfPageEvent{

	PropertiesUtils prop;
	Locale local;
	float[] widths = new float[] {0.7f, 1.3f, 1f, 1f, 0.2f, 0.7f, 1.3f, 1f, 1f};
	
	public InvoicePdf() {
		super();
		this.prop = new PropertiesUtils(true, "/messages_vi.properties");
		this.local = new Locale("vi");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Document makePdf(Document document, HttpServletRequest request, InvoiceDetail invoice, Branches branch, String pathImage) throws DocumentException, IOException {
		
		String pathFontNormal = request.getSession().getServletContext().getRealPath("/static/font/times.ttf");
		String pathFontBold = request.getSession().getServletContext().getRealPath("/static/font/timesbd.ttf");
		String pathFontItalic = request.getSession().getServletContext().getRealPath("/static/font/timesi.ttf");
		String pathFontBoldItalic = request.getSession().getServletContext().getRealPath("/static/font/timesbi.ttf");
		
		File fileFontNormal = new File(pathFontNormal);
		BaseFont fontNormal = BaseFont.createFont(fileFontNormal.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		
		File fileFontBold = new File(pathFontBold);
		BaseFont fontBold = BaseFont.createFont(fileFontBold.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		
		File fileFontItalic = new File(pathFontItalic);
		BaseFont fontItalic = BaseFont.createFont(fileFontItalic.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		
		File fileFontBoldItalic = new File(pathFontBoldItalic);
		BaseFont fontBoldItalic = BaseFont.createFont(fileFontBoldItalic.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		
		HashMap map = new HashMap();
		map.put("fontNormal", fontNormal);
		map.put("fontBold", fontBold);
		map.put("fontItalic", fontItalic);
		map.put("fontBoldItalic", fontBoldItalic);
	        
		PdfPTable header = addHeader(document, branch, map, pathImage);
		document.add(header);
		
		PdfPTable title = addTitle(map);
		document.add(title);
		
		PdfPTable infCustomer = addInfCustomer(map, invoice);
		document.add(infCustomer);
		
		PdfPTable customer = addCustomer(map, invoice);
		document.add(customer);
		
		PdfPTable bill = addBill(map, invoice);
		document.add(bill);
		
		PdfPTable footer = addFooter(map);
		document.add(footer);
		
		return document;
	}

	@SuppressWarnings({ "rawtypes" })
	private PdfPTable addFooter(HashMap map) throws DocumentException {
		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);
		table.setWidths(widths);
		
		BaseFont fontNormal = (BaseFont) map.get("fontNormal");
		BaseFont fontItalic = (BaseFont) map.get("fontItalic");
		
		Font font = new Font(fontItalic, 12f);
		
		addCellLeft(table, font, " ", 4);
		addCellLeft(table, new Font(fontNormal, 11f), " |");
		addCellLeft(table, font, " ", 4);
		
		addCellLeft(table, font, prop.getProperty("invoice.note1"), 4);
		addCellLeft(table, new Font(fontNormal, 11f), "");
		addCellLeft(table, font, prop.getProperty("invoice.note1"), 4);
		
		addCellLeft(table, font, " ", 4);
		addCellLeft(table, new Font(fontNormal, 11f), " |");
		addCellLeft(table, font, " ", 4);
		
		font = new Font(fontNormal, 12f);
		addCellLeft(table, font, prop.getProperty("invoice.payer"), 2);
		addCellLeft(table, font, prop.getProperty("invoice.biller"), 2);
		addCellLeft(table, new Font(fontNormal, 11f), "");
		addCellLeft(table, font, prop.getProperty("invoice.payer"), 2);
		addCellLeft(table, font, prop.getProperty("invoice.biller"), 2);
		
		addCellLeft(table, font, prop.getProperty("invoice.note2"), 2);
		addCellLeft(table, font, prop.getProperty("invoice.note2"), 2);
		addCellLeft(table, new Font(fontNormal, 11f), " |");
		addCellLeft(table, font, prop.getProperty("invoice.note2"), 2);
		addCellLeft(table, font, prop.getProperty("invoice.note2"), 2);
		
		addCellLeft(table, font, " ", 9);
		
		addCellLeft(table, font, " ", 4);
		addCellLeft(table, new Font(fontNormal, 11f), " |");
		addCellLeft(table, font, " ", 4);
		
		addCellLeft(table, font, " ", 9);
		
		addCellLeft(table, font, " ", 4);
		addCellLeft(table, new Font(fontNormal, 11f), " |");
		addCellLeft(table, font, " ", 4);
		return table;
	}

	@SuppressWarnings({ "rawtypes" })
	private PdfPTable addBill(HashMap map, InvoiceDetail invoice) throws DocumentException {
		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);
		table.setWidths(widths);
		
		BaseFont fontNormal = (BaseFont) map.get("fontNormal");
		BaseFont fontBold = (BaseFont) map.get("fontBold");
		
		Font font = new Font(fontBold, 14f);
		
		addCellLeft(table, font, " ", 4);
		addCellLeft(table, new Font(fontNormal, 11f), " |");
		addCellLeft(table, font, " ", 4);
		
		addCellLeftBorderBottom(table, font, prop.getProperty("invoice.info"), 4);
		addCellLeft(table, new Font(fontNormal, 11f), "");
		addCellLeftBorderBottom(table, font, prop.getProperty("invoice.info"), 4);
		
		font = new Font(fontBold, 12f);
		addCellCenterBorder(table, font, prop.getProperty("invoice.date.excute"));
		addCellCenterBorder(table, font, prop.getProperty("invoice.cause"));
		addCellRightBorder(table, font, prop.getProperty("invoice.payment.new"));
		addCellRightBorder(table, font, prop.getProperty("invoice.rest"));
		addCellLeft(table, new Font(fontNormal, 11f), " |");
		addCellCenterBorder(table, font, prop.getProperty("invoice.date.excute"));
		addCellCenterBorder(table, font, prop.getProperty("invoice.cause"));
		addCellRightBorder(table, font, prop.getProperty("invoice.payment.new"));
		addCellRightBorder(table, font, prop.getProperty("invoice.rest"));
		
		font = new Font(fontNormal, 12f);
		addCellCenterBorder(table, font, DateUtil.date2String(invoice.getDateExcute(), "dd/MM/yyyy"));
		addCellLeftBorder(table, font, invoice.getCause().toUpperCase());
		addCellRightBorder(table, font, IntegerUtil.formatWithLang(invoice.getAmount(), local) + " " + prop.getProperty("invoice.money"));
		addCellRightBorder(table, font, IntegerUtil.formatWithLang(invoice.getGross() - invoice.getPayment() - invoice.getAmount(), local) + " " + prop.getProperty("invoice.money"));
		addCellLeft(table, new Font(fontNormal, 11f), "");
		addCellCenterBorder(table, font, DateUtil.date2String(invoice.getDateExcute(), "dd/MM/yyyy"));
		addCellLeftBorder(table, font, invoice.getCause().toUpperCase());
		addCellRightBorder(table, font, IntegerUtil.formatWithLang(invoice.getAmount(), local) + " " + prop.getProperty("invoice.money"));
		addCellRightBorder(table, font, IntegerUtil.formatWithLang(invoice.getGross() - invoice.getPayment() - invoice.getAmount(), local) + " " + prop.getProperty("invoice.money"));
		
		return table;
	}

	@SuppressWarnings({ "rawtypes" })
	private PdfPTable addCustomer(HashMap map, InvoiceDetail invoice) throws DocumentException {
		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);
		table.setWidths(widths);
		
		BaseFont fontNormal = (BaseFont) map.get("fontNormal");
		BaseFont fontBold = (BaseFont) map.get("fontBold");
		
		Font font = new Font(fontBold, 12f);
		
		addCellCenterBorder(table, font, prop.getProperty("invoice.content"), 2);
		addCellRightBorder(table, font, prop.getProperty("invoice.gross"));
		addCellRightBorder(table, font, prop.getProperty("invoice.payment"));
		addCellLeft(table, new Font(fontNormal, 11f), " |");
		addCellCenterBorder(table, font, prop.getProperty("invoice.content"), 2);
		addCellRightBorder(table, font, prop.getProperty("invoice.gross"));
		addCellRightBorder(table, font, prop.getProperty("invoice.payment"));
		
		font = new Font(fontNormal, 11f);
		
		if(invoice.getContent().length() > 22){
			addCellLeftBorder(table, font, StringUtils.substring(invoice.getContent().toUpperCase(), 0, 25) + "..." , 2);
		}else{
			addCellLeftBorder(table, font, invoice.getContent().toUpperCase(), 2);
		}
		
		addCellRightBorder(table, font, IntegerUtil.formatWithLang(invoice.getGross(), local) + " " + prop.getProperty("invoice.money"));
		addCellRightBorder(table, font, IntegerUtil.formatWithLang(invoice.getPayment(), local) + " " + prop.getProperty("invoice.money"));
		addCellLeft(table, new Font(fontNormal, 11f), "");
		if(invoice.getContent().length() > 22){
			addCellLeftBorder(table, font, StringUtils.substring(invoice.getContent().toUpperCase(), 0, 25) + "..." , 2);
		}else{
			addCellLeftBorder(table, font, invoice.getContent().toUpperCase(), 2);
		}
		addCellRightBorder(table, font, IntegerUtil.formatWithLang(invoice.getGross(), local) + " " + prop.getProperty("invoice.money"));
		addCellRightBorder(table, font, IntegerUtil.formatWithLang(invoice.getPayment(), local) + " " + prop.getProperty("invoice.money"));
		
		return table;
	}

	@SuppressWarnings({ "rawtypes" })
	private PdfPTable addInfCustomer(HashMap map, InvoiceDetail invoice) throws DocumentException {
		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);
		table.setWidths(widths);
		
		BaseFont fontNormal = (BaseFont) map.get("fontNormal");
		BaseFont fontBold = (BaseFont) map.get("fontBold");
		
		Font font = new Font(fontBold, 14f);
		
		addCellLeft(table, font, " ", 4);
		addCellLeft(table, new Font(fontNormal, 11f), " |");
		addCellLeft(table, font, " ", 4);
		
		addCellLeftBorderBottom(table, font, prop.getProperty("invoice.customer"), 4);
		addCellLeft(table, new Font(fontNormal, 11f), "");
		addCellLeftBorderBottom(table, font, prop.getProperty("invoice.customer"), 4);
		
		font = new Font(fontNormal, 12f);
		addCellLeft(table, font, prop.getProperty("invoice.serial") +": " + invoice.getSerial(), 4);
		addCellLeft(table, new Font(fontNormal, 11f), " |");
		addCellLeft(table, font, prop.getProperty("invoice.serial") +": " + invoice.getSerial(), 4);
		
		addCellLeft(table, font, prop.getProperty("invoice.name") +": " + invoice.getInvoice().getNameCustomer().toUpperCase(), 4);
		addCellLeft(table, new Font(fontNormal, 11f), "");
		addCellLeft(table, font, prop.getProperty("invoice.name") +": " + invoice.getInvoice().getNameCustomer().toUpperCase(), 4);
		
		addCellLeft(table, font, prop.getProperty("invoice.telephone") +": " + invoice.getInvoice().getTelephone(), 4);
		addCellLeft(table, new Font(fontNormal, 11f), " |");
		addCellLeft(table, font, prop.getProperty("invoice.telephone") +": " + invoice.getInvoice().getTelephone(), 4);
		
		addCellLeft(table, font, prop.getProperty("invoice.address") +": " + invoice.getInvoice().getAddress().toUpperCase(), 4);
		addCellLeft(table, new Font(fontNormal, 11f), "");
		addCellLeft(table, font, prop.getProperty("invoice.address") +": " + invoice.getInvoice().getAddress().toUpperCase(), 4);
		
		return table;
	}

	@SuppressWarnings({ "rawtypes" })
	private PdfPTable addTitle(HashMap map) throws DocumentException {
		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);
		table.setWidths(widths);
		
		BaseFont fontNormal = (BaseFont) map.get("fontNormal");
		BaseFont fontBold = (BaseFont) map.get("fontBold");
		
		Font font = new Font(fontBold, 20f);
		
		addCellCenter(table, font, " ", 4);
		addCellLeft(table, new Font(fontNormal, 11f), " |");
		addCellCenter(table, font, " ", 4);
		
		addCellCenter(table, font, prop.getProperty("branches.bill").toUpperCase(), 4);
		addCellLeft(table, new Font(fontNormal, 11f), " |");
		addCellCenter(table, font, prop.getProperty("branches.bill").toUpperCase(), 4);
		
		font = new Font(fontBold, 13f);
		addCellCenter(table, font, prop.getProperty("invoice.save"), 4);
		addCellLeft(table, font, " |");
		addCellCenter(table, font, prop.getProperty("invoice.send"), 4);
		
		font = new Font(fontNormal, 12f);
		addCellCenter(table, font, prop.getProperty("invoice.date")+ " " + DateUtil.date2String(new Date(), "dd") 
				+ " " + prop.getProperty("invoice.month") + " " + DateUtil.date2String(new Date(), "MM") 
				+ " " + prop.getProperty("invoice.year") + " " + DateUtil.date2String(new Date(), "yyyy"), 4);
		addCellLeft(table, font, "");
		addCellCenter(table, font, prop.getProperty("invoice.date") + " " + DateUtil.date2String(new Date(), "dd") 
				+ " " + prop.getProperty("invoice.month") + " " + DateUtil.date2String(new Date(), "MM") 
				+ " " + prop.getProperty("invoice.year") + " " + DateUtil.date2String(new Date(), "yyyy"), 4);
		
		return table;
	}

	@SuppressWarnings("rawtypes")
	private PdfPTable addHeader(Document document, Branches branch, HashMap map, String pathImage) throws DocumentException, MalformedURLException, IOException {
		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);
		table.setWidths(widths);
		
		BaseFont fontNormal = (BaseFont) map.get("fontNormal");
		BaseFont fontBold = (BaseFont) map.get("fontBold");
		
		Font font = new Font(fontBold, 15f);
		
		addCellCenter(table, font, branch.getFullName(), 4);
		addCellLeft(table, new Font(fontNormal, 11f), " |");
		addCellCenter(table, font, branch.getFullName(), 4);
		
		font = new Font(fontNormal, 11f);
		
		addCellLeft(table, font, " ", 4);
		addCellLeft(table, font, " |");
		addCellLeft(table, font, " ", 4);
		
		addCellLeft(table, font, prop.getProperty("branches.address") + ": " + branch.getAddress(), 4);
		addCellLeft(table, font, "");
		addCellLeft(table, font, prop.getProperty("branches.address") + ": " + branch.getAddress(), 4);

		addCellLeft(table, font, prop.getProperty("branches.telephone") + ": " + branch.getTelephone(), 2);
		addCellLeft(table, font, prop.getProperty("branches.hotline"), 2);
		addCellLeft(table, font, " |");
		addCellLeft(table, font, prop.getProperty("branches.telephone") + ": " + branch.getTelephone(), 2);
		addCellLeft(table, font, prop.getProperty("branches.hotline"), 2);
		
		addCellLeft(table, font, prop.getProperty("branches.website"), 2);
		addCellLeft(table, font, prop.getProperty("branches.email"), 2);
		addCellLeft(table, font, "");
		addCellLeft(table, font, prop.getProperty("branches.website"), 2);
		addCellLeft(table, font, prop.getProperty("branches.email"), 2);
		
		return table;
	}

}
