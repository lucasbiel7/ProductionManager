/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.util;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author rkkitagawa
 */
public class GeradorPlanilha {
    
    public void teste(){
		try {
			FileOutputStream fileOut = new FileOutputStream("C:\\planilhas\\poi-test.xls");
                        HSSFWorkbook workbook = new HSSFWorkbook();
                        HSSFSheet worksheet = workbook.createSheet("POI Worksheet");

//                        worksheet.setDefaultColumnWidth(100);
                        worksheet.setColumnWidth(0, 50);
                        worksheet.setColumnWidth(1, 70);
                        worksheet.setColumnWidth(2, 110);
                        worksheet.setColumnWidth(3, 110);
                        
                        
                       
                        HSSFRow row = worksheet.createRow((short) 0);
                        
                         
                        worksheet.addMergedRegion(new CellRangeAddress(0,0,0,3));

                        HSSFCell cellA = row.createCell((short) 0);
			cellA.setCellValue("TITLE");
                          
                        
                        HSSFFont headerFont = workbook.createFont();
                        headerFont.setBold(true);
                        headerFont.setFontHeightInPoints((short) 22);
                        
                        
                        //Set Header Style
                        CellStyle headerStyle = workbook.createCellStyle();
                        headerStyle.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
                        headerStyle.setAlignment(headerStyle.getAlignmentEnum().CENTER);
                        headerStyle.setFont(headerFont);
                        cellA.setCellStyle(headerStyle);
                        
                        
                        HSSFCellStyle curStyle = cellA.getCellStyle();
                        
                        
                        curStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.AQUA.getIndex());
                        cellA.setCellStyle(curStyle);
                        
			// index from 0,0... cell A1 is cell(0,0)
                        HSSFRow row1 = worksheet.createRow((short) 1);
                        
                        
                        HSSFCell cellA1 = row1.createCell((short) 0);
			cellA1.setCellValue("Hello");
                        
                        HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
			
			cellA1.setCellStyle(cellStyle);

			HSSFCell cellB1 = row1.createCell((short) 1);
			cellB1.setCellValue("Goodbye");
			cellStyle = workbook.createCellStyle();
                        
			cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.TAN.getIndex());
			
			cellB1.setCellStyle(cellStyle);

			HSSFCell cellC1 = row1.createCell((short) 2);
			cellC1.setCellValue(true);

			HSSFCell cellD1 = row1.createCell((short) 3);
			cellD1.setCellValue(new Date());
                        
			cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(HSSFDataFormat
					.getBuiltinFormat("m/d/yy h:mm"));
			cellD1.setCellStyle(cellStyle);

//                        worksheet.autoSizeColumn(0);
//                        worksheet.autoSizeColumn(1);
//                        worksheet.autoSizeColumn(2);
//                        worksheet.autoSizeColumn(3);
                        
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
