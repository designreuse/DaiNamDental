package com.datawings.app.report;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.hssf.util.Region;


public abstract class AExcelPageEvent {
	
	protected void createStyles(HSSFWorkbook workbook, Map<String, HSSFCellStyle> styles){
		
		//Styles Title
		HSSFCellStyle titleLeft = titleLeft(workbook);
		styles.put("titleLeft", titleLeft);
		
		HSSFCellStyle titleCenter = titleCenter(workbook);
		styles.put("titleCenter", titleCenter);
		
		HSSFCellStyle titleRight = titleRight(workbook);
		styles.put("titleRight", titleRight);
		
		//Styles detail color		
		HSSFCellStyle detailColorLeft = detailColorLeft(workbook);
		styles.put("detailColorLeft", detailColorLeft);
		
		HSSFCellStyle detailColorRight = detailColorRight(workbook);
		styles.put("detailColorRight", detailColorRight);
		
		//Styles detail
		HSSFCellStyle detailLeft = workbook.createCellStyle();
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor colorBorder = palette.findSimilarColor(186, 186, 184);
		detailLeft.setFillForegroundColor(HSSFColor.WHITE.index);
		detailLeft.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		detailLeft.setFont(arial(workbook, (short)8, HSSFFont.BOLDWEIGHT_NORMAL));
		detailLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		detailLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		detailLeft.setWrapText(true);
		
		detailLeft.setBorderTop((byte) 1);
		detailLeft.setTopBorderColor(colorBorder.getIndex());
		detailLeft.setBorderBottom((byte) 1);
		detailLeft.setBottomBorderColor(colorBorder.getIndex());
		detailLeft.setBorderLeft((byte) 1);
		detailLeft.setLeftBorderColor(colorBorder.getIndex());
		detailLeft.setBorderRight((byte) 1);
		detailLeft.setRightBorderColor(colorBorder.getIndex());
		styles.put("detailLeft", detailLeft);
		
		HSSFCellStyle detailCenter = workbook.createCellStyle();
		detailCenter.setFillForegroundColor(HSSFColor.WHITE.index);
		detailCenter.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		detailCenter.setFont(arial(workbook, (short)8, HSSFFont.BOLDWEIGHT_NORMAL));
		detailCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		detailCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		detailCenter.setWrapText(true);
		
		detailCenter.setBorderTop((byte) 1);
		detailCenter.setTopBorderColor(colorBorder.getIndex());
		detailCenter.setBorderBottom((byte) 1);
		detailCenter.setBottomBorderColor(colorBorder.getIndex());
		detailCenter.setBorderLeft((byte) 1);
		detailCenter.setLeftBorderColor(colorBorder.getIndex());
		detailCenter.setBorderRight((byte) 1);
		detailCenter.setRightBorderColor(colorBorder.getIndex());
		styles.put("detailCenter", detailCenter);
		
		HSSFCellStyle detailRight = workbook.createCellStyle();
		detailRight.setFillForegroundColor(HSSFColor.WHITE.index);
		detailRight.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		detailRight.setFont(arial(workbook, (short)8, HSSFFont.BOLDWEIGHT_NORMAL));
		detailRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		detailRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		detailRight.setWrapText(true);
		
		detailRight.setBorderTop((byte) 1);
		detailRight.setTopBorderColor(colorBorder.getIndex());
		detailRight.setBorderBottom((byte) 1);
		detailRight.setBottomBorderColor(colorBorder.getIndex());
		detailRight.setBorderLeft((byte) 1);
		detailRight.setLeftBorderColor(colorBorder.getIndex());
		detailRight.setBorderRight((byte) 1);
		detailRight.setRightBorderColor(colorBorder.getIndex());
		styles.put("detailRight", detailRight);
		
		
		HSSFCellStyle filterStyle = workbook.createCellStyle();
		filterStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		filterStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		filterStyle.setFont(arial(workbook, (short)9, HSSFFont.BOLDWEIGHT_BOLD));
		filterStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		filterStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		filterStyle.setWrapText(true);
		
		filterStyle.setBorderTop((byte) 1);
		filterStyle.setTopBorderColor(colorBorder.getIndex());
		filterStyle.setBorderBottom((byte) 1);
		filterStyle.setBottomBorderColor(colorBorder.getIndex());
		filterStyle.setBorderLeft((byte) 1);
		filterStyle.setLeftBorderColor(colorBorder.getIndex());
		filterStyle.setBorderRight((byte) 1);
		filterStyle.setRightBorderColor(colorBorder.getIndex());
		styles.put("filterStyle", filterStyle);
		
		HSSFCellStyle filterStyleCenter = workbook.createCellStyle();
		filterStyleCenter.setFillForegroundColor(HSSFColor.WHITE.index);
		filterStyleCenter.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		filterStyleCenter.setFont(arial(workbook, (short)9, HSSFFont.BOLDWEIGHT_BOLD));
		filterStyleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		filterStyleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		filterStyleCenter.setWrapText(true);
		
		filterStyleCenter.setBorderTop((byte) 1);
		filterStyleCenter.setTopBorderColor(colorBorder.getIndex());
		filterStyleCenter.setBorderBottom((byte) 1);
		filterStyleCenter.setBottomBorderColor(colorBorder.getIndex());
		filterStyleCenter.setBorderLeft((byte) 1);
		filterStyleCenter.setLeftBorderColor(colorBorder.getIndex());
		filterStyleCenter.setBorderRight((byte) 1);
		filterStyleCenter.setRightBorderColor(colorBorder.getIndex());
		styles.put("filterStyleCenter", filterStyleCenter);
	}
	
	
	private HSSFCellStyle detailColorLeft(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(arial(workbook, (short)8, HSSFFont.BOLDWEIGHT_NORMAL));	
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor color = palette.findSimilarColor(221, 226, 226);
		HSSFColor colorBorder = palette.findSimilarColor(186, 186, 184);
		
		style.setFillForegroundColor(color.getIndex());
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
		
		style.setBorderTop((byte) 1);
		style.setTopBorderColor(colorBorder.getIndex());
		style.setBorderBottom((byte) 1);
		style.setBottomBorderColor(colorBorder.getIndex());
		style.setBorderLeft((byte) 1);
		style.setLeftBorderColor(colorBorder.getIndex());
		style.setBorderRight((byte) 1);
		style.setRightBorderColor(colorBorder.getIndex());
		
		return style;
	}
	
	private HSSFCellStyle detailColorRight(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(arial(workbook, (short)8, HSSFFont.BOLDWEIGHT_NORMAL));	
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor color = palette.findSimilarColor(221, 226, 226);
		HSSFColor colorBorder = palette.findSimilarColor(186, 186, 184);
		
		style.setFillForegroundColor(color.getIndex());
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
		
		style.setBorderTop((byte) 1);
		style.setTopBorderColor(colorBorder.getIndex());
		style.setBorderBottom((byte) 1);
		style.setBottomBorderColor(colorBorder.getIndex());
		style.setBorderLeft((byte) 1);
		style.setLeftBorderColor(colorBorder.getIndex());
		style.setBorderRight((byte) 1);
		style.setRightBorderColor(colorBorder.getIndex());
		return style;
	}
	
	@SuppressWarnings("deprecation")
	protected HSSFCell getCell(HSSFSheet sheet, int row, int col) {
		HSSFRow sheetRow = sheet.getRow(row);
		if (sheetRow == null) {
			sheetRow = sheet.createRow(row);
		}
		HSSFCell cell = sheetRow.getCell((short) col);
		if (cell == null) {
			cell = sheetRow.createCell((short) col);
		}
		return cell;
	}
	
	protected void writeCell(HSSFSheet sheet, int row, int col, String value, HSSFCellStyle style){
		HSSFCell cell = getCell(sheet, row, col);
		cell.setCellValue(new HSSFRichTextString(value));
		if (style!=null)
			cell.setCellStyle(style);
	}
	
	protected void writeCell(HSSFSheet sheet, int row, int col, Integer value, HSSFCellStyle style){
		HSSFCell cell = getCell(sheet, row, col);
		cell.setCellValue(value);
		if (style!=null)
			cell.setCellStyle(style);
	}
	
	protected void writeCell(HSSFSheet sheet, int row, int col, Double value, HSSFCellStyle style){
		HSSFCell cell = getCell(sheet, row, col);
		cell.setCellValue(value);
		if (style!=null)
			cell.setCellStyle(style);
	}
	
	@SuppressWarnings("deprecation")
	protected void writeCell(HSSFSheet sheet, int row, int col, String value, HSSFCellStyle style, int colspan){
		HSSFCell cell = getCell(sheet, row, col);
		cell.setCellValue(new HSSFRichTextString(value));
		Region region = new Region(row, (short)col, row, (short)(col + colspan));
		sheet.addMergedRegion(region);
		if (style!=null)
			cell.setCellStyle(style);
	}
	
	
	@SuppressWarnings("deprecation")
	protected void writeCell(HSSFSheet sheet, int row, int col, String value, HSSFCellStyle style, int colspan, HSSFWorkbook wb){
		HSSFCell cell = getCell(sheet, row, col);
		cell.setCellValue(new HSSFRichTextString(value));
		HSSFPalette palette = wb.getCustomPalette();
		HSSFColor colorBorder = palette.findSimilarColor(186, 186, 184);
		Region region = new Region(row, (short)col, row, (short)(col + colspan));
		sheet.addMergedRegion(region);
		
		HSSFRegionUtil.setBorderTop((byte)1, region, sheet, wb);
		HSSFRegionUtil.setTopBorderColor(colorBorder.getIndex(), region, sheet, wb);
		
		HSSFRegionUtil.setBorderLeft((byte)1, region, sheet, wb);
		HSSFRegionUtil.setLeftBorderColor(colorBorder.getIndex(), region, sheet, wb);
		
		HSSFRegionUtil.setBorderBottom((byte)1, region, sheet, wb);
		HSSFRegionUtil.setBottomBorderColor(colorBorder.getIndex(), region, sheet, wb);
		
		HSSFRegionUtil.setBorderRight((byte)1, region, sheet, wb);
		HSSFRegionUtil.setRightBorderColor(colorBorder.getIndex(), region, sheet, wb);
		if (style!=null)
			cell.setCellStyle(style);
	}
	
	
	private HSSFCellStyle titleLeft(HSSFWorkbook workbook){
		HSSFCellStyle style=workbook.createCellStyle();
		HSSFFont font=workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short)9);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor color = palette.findSimilarColor(12, 6, 166);
		HSSFColor colorBorder = palette.findSimilarColor(186, 186, 184);
		
		style.setWrapText(true);
		
		style.setFillForegroundColor(color.getIndex());
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setBorderTop((byte) 1);
		style.setTopBorderColor(colorBorder.getIndex());
		style.setBorderBottom((byte) 1);
		style.setBottomBorderColor(colorBorder.getIndex());
		style.setBorderLeft((byte) 1);
		style.setLeftBorderColor(HSSFColor.WHITE.index);
		style.setBorderRight((byte) 1);
		style.setRightBorderColor(HSSFColor.WHITE.index);
		return style;
	}
	
	private HSSFCellStyle titleCenter(HSSFWorkbook workbook){
		HSSFCellStyle style=workbook.createCellStyle();
		HSSFFont font=workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		font.setFontHeightInPoints((short)9);
		style.setFont(font);
		
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor color = palette.findSimilarColor(12, 6, 166);
		HSSFColor colorBorder = palette.findSimilarColor(186, 186, 184);
		
		style.setWrapText(true);
		
		style.setFillForegroundColor(color.getIndex());
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setBorderTop((byte) 1);
		style.setTopBorderColor(colorBorder.getIndex());
		style.setBorderBottom((byte) 1);
		style.setBottomBorderColor(colorBorder.getIndex());
		style.setBorderLeft((byte) 1);
		style.setLeftBorderColor(HSSFColor.WHITE.index);
		style.setBorderRight((byte) 1);
		style.setRightBorderColor(HSSFColor.WHITE.index);
		return style;
	}
	
	private HSSFCellStyle titleRight(HSSFWorkbook workbook){
		HSSFCellStyle style=workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)9);
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor color = palette.findSimilarColor(12, 6, 166);
		HSSFColor colorBorder = palette.findSimilarColor(186, 186, 184);
		
		style.setWrapText(true);
		
		style.setFillForegroundColor(color.getIndex());
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setBorderTop((byte) 1);
		style.setTopBorderColor(colorBorder.getIndex());
		style.setBorderBottom((byte) 1);
		style.setBottomBorderColor(colorBorder.getIndex());
		style.setBorderLeft((byte) 1);
		style.setLeftBorderColor(HSSFColor.WHITE.index);
		style.setBorderRight((byte) 1);
		style.setRightBorderColor(HSSFColor.WHITE.index);
		return style;
	}
	
	private HSSFFont arial(HSSFWorkbook workbook, short height, short boldweight){
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints( height);
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setBoldweight( boldweight);
		return font;
	}	
	
	public static HSSFCellStyle copy(HSSFCellStyle style, HSSFWorkbook wb) {
		HSSFCellStyle style2 = wb.createCellStyle();
		style2.setFont(wb.getFontAt(style.getFontIndex()));
		style2.setVerticalAlignment(style.getVerticalAlignment());
		style2.setAlignment(style.getAlignment());
		style2.setWrapText(style.getWrapText());
		style2.setBorderBottom(style.getBorderBottom());
		style2.setBorderTop(style.getBorderTop());
		style2.setBorderRight(style.getBorderRight());
		style2.setBorderLeft(style.getBorderLeft());

		style2.setBottomBorderColor(style.getBottomBorderColor());
		style2.setTopBorderColor(style.getTopBorderColor());
		style2.setRightBorderColor(style.getRightBorderColor());
		style2.setLeftBorderColor(style.getLeftBorderColor());

		style2.setFillForegroundColor(style.getFillForegroundColor());
		style2.setFillPattern(style.getFillPattern());
		return style2;
	}
	
	public static void cellStyle(HSSFCell cell, HSSFWorkbook wb) {
		HSSFCellStyle style = copy(cell.getCellStyle(), wb);		
		cell.setCellStyle(style);
	}

	@SuppressWarnings("deprecation")
	public static void leftRegion(Region region, HSSFSheet sheet, HSSFWorkbook wb) {
		int rowStart = region.getRowFrom();
		int rowEnd = region.getRowTo();
		short column = region.getColumnFrom();
		for (int i = rowStart; i <= rowEnd; i++) {
			if (sheet.getRow(i) == null) {
				sheet.createRow(i);
				if (sheet.getRow(i).getCell(column) == null) {
					cellStyle(sheet.getRow(i).createCell((short) column), wb);
				} else {
					cellStyle(sheet.getRow(i).getCell(column), wb);
				}
			} else {
				if (sheet.getRow(i).getCell(column) == null) {
					cellStyle(sheet.getRow(i).createCell((short) column), wb);
				} else {
					cellStyle(sheet.getRow(i).getCell(column), wb);
				}
			}

		}
	}

	@SuppressWarnings("deprecation")
	public static void rightRegion(Region region, HSSFSheet sheet, HSSFWorkbook wb) {
		int rowStart = region.getRowFrom();
		int rowEnd = region.getRowTo();
		short columnEnd = region.getColumnTo();
		for (int i = rowStart; i <= rowEnd; i++) {
			if (sheet.getRow(i) == null) {
				sheet.createRow(i);
				if (sheet.getRow(i).getCell(columnEnd) == null) {
					cellStyle(sheet.getRow(i).createCell((short) columnEnd), wb);
				} else {
					cellStyle(sheet.getRow(i).getCell(columnEnd), wb);
				}
			} else {
				if (sheet.getRow(i).getCell(columnEnd) == null) {
					cellStyle(sheet.getRow(i).createCell((short) columnEnd), wb);
				} else {
					cellStyle(sheet.getRow(i).getCell(columnEnd), wb);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void bottomRegion(Region region, HSSFSheet sheet, HSSFWorkbook wb) {
		int rowEnd = region.getRowTo();
		int column = region.getColumnFrom();
		int columnEnd = region.getColumnTo();
		for (int i = column; i <= columnEnd; i++) {
			if (sheet.getRow(rowEnd) == null) {
				sheet.createRow(rowEnd);
				if (sheet.getRow(rowEnd).getCell((short) i) == null) {
					cellStyle(sheet.getRow(rowEnd).createCell((short) i), wb);
				} else {
					cellStyle(sheet.getRow(rowEnd).getCell((short) i), wb);
				}
			} else {
				if (sheet.getRow(rowEnd).getCell((short) i) == null) {
					cellStyle(sheet.getRow(rowEnd).createCell((short) i), wb);
				} else {
					cellStyle(sheet.getRow(rowEnd).getCell((short) i), wb);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void topRegion(Region region, HSSFSheet sheet, HSSFWorkbook wb) {
		int rowStart = region.getRowFrom();
		int column = region.getColumnFrom();
		int columnEnd = region.getColumnTo();
		for (int i = column; i <= columnEnd; i++) {
			if (sheet.getRow(rowStart) == null) {
				sheet.createRow(rowStart);
				if (sheet.getRow(rowStart).getCell((short) i) == null) {
					cellStyle(sheet.getRow(rowStart).createCell((short) i), wb);
				} else {
					cellStyle(sheet.getRow(rowStart).getCell((short) i), wb);
				}
			} else {
				if (sheet.getRow(rowStart).getCell((short) i) == null) {
					cellStyle(sheet.getRow(rowStart).createCell((short) i), wb);
				} else {
					cellStyle(sheet.getRow(rowStart).getCell((short) i), wb);
				}
			}
		}
	}
	
	public static void createRegion(Region region, HSSFSheet sheet, HSSFWorkbook wb) {
		leftRegion(region, sheet, wb);
		rightRegion(region, sheet, wb);
		bottomRegion(region, sheet, wb);
		topRegion(region, sheet, wb);
	}
	
	@SuppressWarnings("deprecation")
	protected void writeCellRegion(HSSFWorkbook wb, HSSFSheet sheet, int row,
			int col, Region region, String value, HSSFCellStyle style) {
		createRegion(region, sheet, wb);
		HSSFCell cell = getCell(sheet, row, col);
		cell.setCellValue(new HSSFRichTextString(value));
		sheet.addMergedRegion(region);
		if (style != null) {
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			cell.setCellStyle(style);
		}
	}
	
	
}
