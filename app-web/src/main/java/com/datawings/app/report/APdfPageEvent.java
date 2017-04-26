package com.datawings.app.report;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;


public class APdfPageEvent extends PdfPageEventHelper {	
	protected void addCellLeft(PdfPTable table, Font font, String content){		
		PdfPCell cell = new PdfPCell(new Phrase(content, font));		
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setNoWrap(true);		
		table.addCell(cell);	
	}
	
	protected void addCellLeft(PdfPTable table, Font font, String content, int colspan){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setNoWrap(true);
		cell.setColspan(colspan);
		table.addCell(cell);	
	}
	
	protected void addCellLeftNoWrap(Document document, PdfPTable table, Font font, String content){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setNoWrap(false);
		table.addCell(cell);	
	}
	
	
	
	protected void addCellRight(Document document, PdfPTable table, Font font, String content){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setNoWrap(true);		
		table.addCell(cell);	
	}
	
	protected void addCellRight(Document document, PdfPTable table, Font font, String content, int colspan){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setNoWrap(true);
		cell.setColspan(colspan);
		table.addCell(cell);	
	}
	
	protected void addCellCenter(PdfPTable table, Font font, String content){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setNoWrap(true);		
		table.addCell(cell);	
	}
	
	protected void addCellCenter(PdfPTable table, Font font, String content, int colspan){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setNoWrap(true);
		cell.setColspan(colspan);
		table.addCell(cell);	
	}
	protected void addEmptyLine(Document document, int number) {
		  Paragraph emptyLine = new Paragraph(" ");
		  for (int i = 0; i < number; i++) {
		   emptyLine.add(new Paragraph(" "));
		  }
		  
		  try {
		   document.add(emptyLine);
		  } catch (Exception e) {
		   throw new ExceptionConverter(e);
		  }
		 }
	

	protected void addCellImageLeft(PdfPTable table, Image image) {
		PdfPCell cell = new PdfPCell(image, true);		
		cell.setBorder(0);		
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
	}
	
	protected void addCellImageLeft(PdfPTable table, Image image, int colspan) {
		PdfPCell cell = new PdfPCell(image);		
		cell.setBorder(0);		
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);		
		cell.setColspan(colspan);
		table.addCell(cell);
	}
	
	protected void addCellTableLeft(Document document, PdfPTable table, Font font, PdfPTable content, int colspan){		
		PdfPCell cell = new PdfPCell(content);		
		cell.setBorder(0);		
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);		
		cell.setColspan(colspan);
		table.addCell(cell);
	}	
	
	protected void addCellTableRight(Document document, PdfPTable table, Font font, PdfPTable content, int colspan){		
		PdfPCell cell = new PdfPCell(content);		
		cell.setBorder(0);		
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);		
		cell.setColspan(colspan);
		table.addCell(cell);
	}	
	
	protected void addCellTableCenter(Document document, PdfPTable table, Font font, PdfPTable content, int colspan){		
		PdfPCell cell = new PdfPCell(content);		
		cell.setBorder(0);		
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);		
		cell.setColspan(colspan);
		table.addCell(cell);
	}	
	
	protected void borderLine(PdfPCell cell) {
		cell.setBorderWidthLeft(0.0f);
		cell.setBorderWidthRight(0.0f);		
		cell.setBorderWidthTop(0.0f);
		cell.setBorderWidthBottom(0.1f);
		
	}
	
	protected void border(PdfPCell cell) {
		cell.setBorderWidthLeft(0.1f);
		cell.setBorderWidthRight(0.1f);		
		cell.setBorderWidthTop(0.1f);
		cell.setBorderWidthBottom(0.1f);
		cell.setBorderColor(BaseColor.BLACK);
	}
	
	protected void addCellLeftBorder(Document document, PdfPTable table, Font font, String content, int colspan){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		border(cell);	
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setNoWrap(true);
		cell.setColspan(colspan);
		table.addCell(cell);	
	}
	
	protected void addCellLeftBorderBottom(PdfPTable table, Font font, String content){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBorderWidthLeft(0f);
		cell.setBorderWidthRight(0f);		
		cell.setBorderWidthTop(0f);
		cell.setBorderWidthBottom(0.1f);	
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPaddingBottom(5f);
		cell.setNoWrap(true);
		table.addCell(cell);	
	}
	
	protected void addCellLeftBorderBottom(PdfPTable table, Font font, String content, int colspan){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBorderWidthLeft(0f);
		cell.setBorderWidthRight(0f);		
		cell.setBorderWidthTop(0f);
		cell.setBorderWidthBottom(0.1f);	
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPaddingBottom(5f);
		cell.setColspan(colspan);
		cell.setNoWrap(true);
		table.addCell(cell);	
	}
	
	protected void addCellRightBorder(Document document, PdfPTable table, Font font, String content, int colspan){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		border(cell);	
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setNoWrap(true);
		cell.setColspan(colspan);
		table.addCell(cell);	
	}	
	protected void addCellCenterBorder(PdfPTable table, Font font, String content, int colspan){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		border(cell);	
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setNoWrap(true);
		cell.setColspan(colspan);
		table.addCell(cell);	
	}
	
	protected void addCellCenterBorderLeft(PdfPTable table, Font font, String content, int colspan){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBorderWidthLeft(0.1f);
		cell.setPaddingLeft(1f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setNoWrap(true);
		cell.setColspan(colspan);
		table.addCell(cell);	
	}
	
	protected void addCellLeftBorder(PdfPTable table, Font font, String content){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		border(cell);	
		cell.setBorderColor(BaseColor.BLACK);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setNoWrap(true);
		table.addCell(cell);	
	}	
	protected void addCellRightBorder(PdfPTable table, Font font, String content){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		border(cell);
		cell.setBorderColor(BaseColor.BLACK);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setNoWrap(true);
		table.addCell(cell);	
	}	
	protected void addCellCenterBorder(PdfPTable table, Font font, String content){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		border(cell);
		cell.setBorderColor(BaseColor.BLACK);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setNoWrap(true);
		table.addCell(cell);	
	}	
	
	protected void addCellLeftBorder(PdfPTable table, Font font, String content, int colspan){
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		border(cell);	
		cell.setBorderColor(BaseColor.BLACK);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(colspan);
		cell.setNoWrap(true);		
		table.addCell(cell);	
	}	
	
}
