/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.util;

import br.com.stefanini.control.dao.ParametroDAO;
import br.com.stefanini.model.entity.ProgressoAtividade;
import br.com.stefanini.model.enuns.TipoAtividade;
import br.com.stefanini.model.enuns.TipoParametro;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author rkkitagawa
 */
public class GeradorPlanilha {

    public void teste(List<ProgressoAtividade> lev,List<ProgressoAtividade> dev,List<ProgressoAtividade> tst ) {
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

            worksheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));

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
        private static Double valorContrato = 0.0;
        private static Double valorRepasse = 0.0;
    
//    public static void main(String[] args) {
//		gerarDetalhamento(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>(), new Date(), new Totais());
//
//	}

    private static String buildLabelDetalhamento(Date date,String prefix,String sufix) {
            if (date == null) {
                    return null;
            }
            StringBuilder sb = new StringBuilder(prefix);
            sb.append(new SimpleDateFormat("MM_YYYY").format(date));
            sb.append(sufix);
            return sb.toString();
    }
    
    private static String buildLabelMMMMDetalhamento(Date date,String prefix,String sufix) {
            if (date == null) {
                    return null;
            }
            StringBuilder sb = new StringBuilder(prefix);
            sb.append(new SimpleDateFormat("MMMM/YYYY").format(date));
            sb.append(sufix);
            return sb.toString();
    }
    
    private static String buildLabelMMMMDetalhamentoPosterior(Date date,String prefix,String sufix) {
            if (date == null) {
                    return null;
            }
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.MONTH, +1);
            Date dataAnterior = c.getTime();
            StringBuilder sb = new StringBuilder(prefix);
            sb.append(new SimpleDateFormat("MMMM/YYYY").format(dataAnterior));
            sb.append(sufix);
            return sb.toString();
    }

    public static String gerarDetalhamento(PlanilhaDetalhes planilhaDetalhes) {
            try {
                    valorContrato = new ParametroDAO().buscaParametroRecente(TipoParametro.CONTRATO).getValor();
                    valorRepasse = new ParametroDAO().buscaParametroRecente(TipoParametro.REPASSE).getValor();
                    String fileName = "C:\\planilhas\\" + buildLabelDetalhamento(planilhaDetalhes.getData(),(planilhaDetalhes.isRepasse()? "STEFANINI":"BDMG"),".xls");
                    FileOutputStream fileOut = new FileOutputStream(fileName);
                    HSSFWorkbook workbook = new HSSFWorkbook();
                    CreationHelper ch = workbook.getCreationHelper();
                    
                    HSSFFont zehn = workbook.createFont();
                    zehn.setFontHeightInPoints((short) 10);
                    CellStyle cellZehnM = workbook.createCellStyle();
                    cellZehnM.setFont(zehn);
                    cellZehnM.setBorderTop(BorderStyle.THIN);
                    cellZehnM.setTopBorderColor(HSSFColor.BLACK.index);
                    cellZehnM.setBorderBottom(BorderStyle.THIN);
                    cellZehnM.setBottomBorderColor(HSSFColor.BLACK.index);
                    cellZehnM.setBorderLeft(BorderStyle.THIN);
                    cellZehnM.setLeftBorderColor(HSSFColor.BLACK.index);
                    cellZehnM.setBorderRight(BorderStyle.THIN);
                    cellZehnM.setRightBorderColor(HSSFColor.BLACK.index);
                    cellZehnM.setDataFormat(ch.createDataFormat().getFormat("R$#,##0.00"));
                    
                    CellStyle cellZehnN = workbook.createCellStyle();
                    cellZehnN.setFont(zehn);
                    cellZehnN.setBorderTop(BorderStyle.THIN);
                    cellZehnN.setTopBorderColor(HSSFColor.BLACK.index);
                    cellZehnN.setBorderBottom(BorderStyle.THIN);
                    cellZehnN.setBottomBorderColor(HSSFColor.BLACK.index);
                    cellZehnN.setBorderLeft(BorderStyle.THIN);
                    cellZehnN.setLeftBorderColor(HSSFColor.BLACK.index);
                    cellZehnN.setBorderRight(BorderStyle.THIN);
                    cellZehnN.setRightBorderColor(HSSFColor.BLACK.index);
                    cellZehnN.setDataFormat(ch.createDataFormat().getFormat("0"));

                    CellStyle cellZehnD = workbook.createCellStyle();
                    cellZehnD.setFont(zehn);
                    cellZehnD.setBorderTop(BorderStyle.THIN);
                    cellZehnD.setTopBorderColor(HSSFColor.BLACK.index);
                    cellZehnD.setBorderBottom(BorderStyle.THIN);
                    cellZehnD.setBottomBorderColor(HSSFColor.BLACK.index);
                    cellZehnD.setBorderLeft(BorderStyle.THIN);
                    cellZehnD.setLeftBorderColor(HSSFColor.BLACK.index);
                    cellZehnD.setBorderRight(BorderStyle.THIN);
                    cellZehnD.setRightBorderColor(HSSFColor.BLACK.index);
                    cellZehnD.setDataFormat(ch.createDataFormat().getFormat("#,##0.00"));
                    
                    HSSFFont boldZehn = workbook.createFont();
                    boldZehn.setBold(true);
                    boldZehn.setFontHeightInPoints((short) 10);
                    CellStyle boldCellZehn = workbook.createCellStyle();
                    boldCellZehn.setFont(boldZehn);
                    boldCellZehn.setFont(boldZehn);
                    boldCellZehn.setBorderTop(BorderStyle.THIN);
                    boldCellZehn.setTopBorderColor(HSSFColor.BLACK.index);
                    boldCellZehn.setBorderBottom(BorderStyle.THIN);
                    boldCellZehn.setBottomBorderColor(HSSFColor.BLACK.index);
                    boldCellZehn.setBorderLeft(BorderStyle.THIN);
                    boldCellZehn.setLeftBorderColor(HSSFColor.BLACK.index);
                    boldCellZehn.setBorderRight(BorderStyle.THIN);
                    boldCellZehn.setRightBorderColor(HSSFColor.BLACK.index);

                    HSSFFont boldZwolf = workbook.createFont();
                    boldZwolf.setBold(true);
                    boldZwolf.setFontHeightInPoints((short) 12);
                    CellStyle boldCellZwolf = workbook.createCellStyle();
                    boldCellZwolf.setVerticalAlignment(VerticalAlignment.CENTER);
                    boldCellZwolf.setAlignment(HorizontalAlignment.CENTER);
                    boldCellZwolf.setFont(boldZwolf);
                    boldCellZwolf.setBorderTop(BorderStyle.MEDIUM);
                    boldCellZwolf.setTopBorderColor(HSSFColor.BLACK.index);
                    boldCellZwolf.setBorderBottom(BorderStyle.MEDIUM);
                    boldCellZwolf.setBottomBorderColor(HSSFColor.BLACK.index);
                    boldCellZwolf.setBorderLeft(BorderStyle.MEDIUM);
                    boldCellZwolf.setLeftBorderColor(HSSFColor.BLACK.index);
                    boldCellZwolf.setBorderRight(BorderStyle.MEDIUM);
                    boldCellZwolf.setRightBorderColor(HSSFColor.BLACK.index);
                    boldCellZwolf.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    boldCellZwolf.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                    

                    HSSFSheet sheetDetalhamento = workbook.createSheet(buildLabelDetalhamento(planilhaDetalhes.getData(),"Detalhamento de ",""));
                    montarDetalhamento(workbook, planilhaDetalhes, sheetDetalhamento, planilhaDetalhes.isRepasse());
                    
                    HSSFSheet sheetLevantamento = workbook.createSheet("Levantamento - 35%");
                    montarProgressoAtividade(sheetLevantamento, planilhaDetalhes.getLev(), boldCellZwolf, cellZehnM, cellZehnD, cellZehnN, planilhaDetalhes.isRepasse(), TipoAtividade.LE);

                    HSSFSheet sheetDesenvolvimento = workbook.createSheet("Desenvolvimento - 40%");
                    montarProgressoAtividade(sheetDesenvolvimento, planilhaDetalhes.getDev(), boldCellZwolf, cellZehnM, cellZehnD, cellZehnN, planilhaDetalhes.isRepasse(), TipoAtividade.DE);
                    HSSFSheet sheetHomologacao = workbook.createSheet("Teste e Homologação - 25%");
                    montarProgressoAtividade(sheetHomologacao, planilhaDetalhes.getTst(), boldCellZwolf, cellZehnM, cellZehnD, cellZehnN, planilhaDetalhes.isRepasse(), TipoAtividade.TE);

                    workbook.write(fileOut);
                    fileOut.flush();
                    fileOut.close();
                    return fileName;
            } catch (FileNotFoundException e) {
                    e.printStackTrace();
            } catch (IOException e) {
                    e.printStackTrace();
            }
        return null;
    }

    private static void montarDetalhamento(HSSFWorkbook workbook, PlanilhaDetalhes planilhaDetalhes, HSSFSheet sheet, boolean repasse){        
        HSSFFont boldZehnBlau = workbook.createFont();
        CreationHelper ch = workbook.getCreationHelper();
        
        boldZehnBlau.setBold(true);
        boldZehnBlau.setFontHeightInPoints((short) 10);
        boldZehnBlau.setColor(HSSFColor.BLUE.index);
        CellStyle boldCellZehnBlauLeft = workbook.createCellStyle();
        boldCellZehnBlauLeft.setFont(boldZehnBlau);
        boldCellZehnBlauLeft.setAlignment(HorizontalAlignment.LEFT);
        
        HSSFFont boldZehn = workbook.createFont();
        boldZehn.setBold(true);
        boldZehn.setFontHeightInPoints((short) 10);
        
        HSSFFont zehn = workbook.createFont();
        zehn.setFontHeightInPoints((short) 10);
        
        CellStyle boldCellZehnNbCenter = workbook.createCellStyle();
        boldCellZehnNbCenter.setFont(boldZehn);
        boldCellZehnNbCenter.setAlignment(HorizontalAlignment.CENTER);
        
        CellStyle boldCellZehnNbRight = workbook.createCellStyle();
        boldCellZehnNbRight.setFont(boldZehn);
        boldCellZehnNbRight.setAlignment(HorizontalAlignment.RIGHT);
        
        CellStyle boldCellZehnNbLeft = workbook.createCellStyle();
        boldCellZehnNbLeft.setFont(boldZehn);
        boldCellZehnNbLeft.setAlignment(HorizontalAlignment.LEFT);
        
        CellStyle cellZehnNbRight = workbook.createCellStyle();
        cellZehnNbRight.setFont(zehn);
        cellZehnNbRight.setAlignment(HorizontalAlignment.RIGHT);  
        
        CellStyle cellZehnNbM = workbook.createCellStyle();
        cellZehnNbM.setFont(zehn);
        cellZehnNbM.setDataFormat(ch.createDataFormat().getFormat("R$#,##0.00"));
        
        CellStyle cellZehnNbD = workbook.createCellStyle();
        cellZehnNbD.setFont(zehn);
        cellZehnNbD.setDataFormat(ch.createDataFormat().getFormat("#,##0.00"));
        
        CellStyle cellZehnNbN = workbook.createCellStyle();
        cellZehnNbN.setFont(zehn);
        cellZehnNbN.setDataFormat(ch.createDataFormat().getFormat("0"));
        
        
        CellStyle boldCellZehnMedium = workbook.createCellStyle();
        boldCellZehnMedium.setFont(boldZehn);
        boldCellZehnMedium.setBorderTop(BorderStyle.MEDIUM);
        boldCellZehnMedium.setTopBorderColor(HSSFColor.BLACK.index);
        boldCellZehnMedium.setBorderBottom(BorderStyle.MEDIUM);
        boldCellZehnMedium.setBottomBorderColor(HSSFColor.BLACK.index);
        boldCellZehnMedium.setBorderLeft(BorderStyle.MEDIUM);
        boldCellZehnMedium.setLeftBorderColor(HSSFColor.BLACK.index);
        boldCellZehnMedium.setBorderRight(BorderStyle.MEDIUM);
        boldCellZehnMedium.setRightBorderColor(HSSFColor.BLACK.index);
        boldCellZehnMedium.setAlignment(HorizontalAlignment.LEFT);
        
        CellStyle cellZehnMediumM = workbook.createCellStyle();
        cellZehnMediumM.setFont(zehn);
        cellZehnMediumM.setBorderTop(BorderStyle.MEDIUM);
        cellZehnMediumM.setTopBorderColor(HSSFColor.BLACK.index);
        cellZehnMediumM.setBorderBottom(BorderStyle.MEDIUM);
        cellZehnMediumM.setBottomBorderColor(HSSFColor.BLACK.index);
        cellZehnMediumM.setBorderLeft(BorderStyle.MEDIUM);
        cellZehnMediumM.setLeftBorderColor(HSSFColor.BLACK.index);
        cellZehnMediumM.setBorderRight(BorderStyle.MEDIUM);
        cellZehnMediumM.setRightBorderColor(HSSFColor.BLACK.index);
        cellZehnMediumM.setAlignment(HorizontalAlignment.LEFT);
        cellZehnMediumM.setDataFormat(ch.createDataFormat().getFormat("R$#,##0.00"));
               
        CellStyle cellZehnMediumN = workbook.createCellStyle();
        cellZehnMediumN.setFont(zehn);
        cellZehnMediumN.setBorderTop(BorderStyle.MEDIUM);
        cellZehnMediumN.setTopBorderColor(HSSFColor.BLACK.index);
        cellZehnMediumN.setBorderBottom(BorderStyle.MEDIUM);
        cellZehnMediumN.setBottomBorderColor(HSSFColor.BLACK.index);
        cellZehnMediumN.setBorderLeft(BorderStyle.MEDIUM);
        cellZehnMediumN.setLeftBorderColor(HSSFColor.BLACK.index);
        cellZehnMediumN.setBorderRight(BorderStyle.MEDIUM);
        cellZehnMediumN.setRightBorderColor(HSSFColor.BLACK.index);
        cellZehnMediumN.setAlignment(HorizontalAlignment.LEFT);
        cellZehnMediumN.setDataFormat(ch.createDataFormat().getFormat("0"));
	
        CellStyle boldCellZehnCenterGrey = workbook.createCellStyle();
        boldCellZehnCenterGrey.setFont(boldZehn);
        boldCellZehnCenterGrey.setAlignment(HorizontalAlignment.CENTER);
        boldCellZehnCenterGrey.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        boldCellZehnCenterGrey.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell = row.createCell((short) 0);
        
        cell.setCellStyle(boldCellZehnNbCenter);
        cell.setCellValue("ESPELHO DE FATURAMENTO");
        
        row = sheet.createRow((short) 2);
        cell = row.createCell((short) 0);
        cell.setCellStyle(boldCellZehnNbRight);
        cell.setCellValue("Faturamento do mês de:");        
        cell = row.createCell((short) 1);
        cell.setCellStyle(boldCellZehnBlauLeft);
        cell.setCellValue(buildLabelMMMMDetalhamentoPosterior(planilhaDetalhes.getData(), "", ""));      
        cell = row.createCell((short) 2);
        cell.setCellStyle(boldCellZehnNbRight);
        cell.setCellValue("Faturamento referênte ao mês de:");        
        cell = row.createCell((short) 3);
        cell.setCellStyle(boldCellZehnBlauLeft);
        cell.setCellValue(buildLabelMMMMDetalhamento(planilhaDetalhes.getData(), "", "")); 
        
        
        row = sheet.createRow((short) 4);
        cell = row.createCell((short) 0);
        cell.setCellStyle(boldCellZehnMedium);
        cell.setCellValue("Quantidade Total de Atividades");         
        cell = row.createCell((short) 1);
        cell.setCellStyle(cellZehnMediumN);
        cell.setCellValue(planilhaDetalhes.getDev().size() + planilhaDetalhes.getLev().size() + planilhaDetalhes.getTst().size());
        
        row = sheet.createRow((short) 6);
        cell = row.createCell((short) 0);
        cell.setCellStyle(boldCellZehnMedium);
        cell.setCellValue("Valor Total Estimado (Contrato):"); 
        cell = row.createCell((short) 1);
        cell.setCellStyle(cellZehnMediumM);
        cell.setCellType(CellType.NUMERIC);        
        cell.setCellValue(planilhaDetalhes.getTotalEstimadaContrato());
        cell = row.createCell((short) 2);
        cell.setCellStyle(boldCellZehnMedium);
        cell.setCellValue("Valor Total Detalhado (Contrato):"); 
        cell = row.createCell((short) 3);
        cell.setCellStyle(cellZehnMediumM);
        cell.setCellValue(planilhaDetalhes.getTotalDetalhadaContrato());

        if(repasse){
            row = sheet.createRow((short) 8);
            cell = row.createCell((short) 0);
            cell.setCellStyle(boldCellZehnMedium);
            cell.setCellValue("Valor Total Estimado (Repasse):"); 
            cell = row.createCell((short) 1);
            cell.setCellStyle(cellZehnMediumM);
            cell.setCellValue(planilhaDetalhes.getTotalEstimadaRepasse());
            cell = row.createCell((short) 2);
            cell.setCellStyle(boldCellZehnMedium);
            cell.setCellValue("Valor Total Detalhado (Repasse):"); 
            cell = row.createCell((short) 3);
            cell.setCellStyle(cellZehnMediumM);
            cell.setCellValue(planilhaDetalhes.getTotalDetalhadaRepasse());        
        }
      
        montarSubDetalhamento(sheet, ch, planilhaDetalhes.getLev(), boldCellZehnCenterGrey, boldCellZehnNbLeft, cellZehnNbM, cellZehnNbD, cellZehnNbN, repasse, TipoAtividade.LE, (repasse?10:8));
        montarSubDetalhamento(sheet, ch, planilhaDetalhes.getDev(), boldCellZehnCenterGrey, boldCellZehnNbLeft, cellZehnNbM, cellZehnNbD, cellZehnNbN, repasse, TipoAtividade.DE, (repasse?16:13));
        montarAliAie(sheet, planilhaDetalhes.getTst(), boldCellZehnCenterGrey, boldCellZehnNbLeft, cellZehnNbM, cellZehnNbD, cellZehnNbN, repasse, (repasse?22:18));
        montarSubDetalhamento(sheet, ch, planilhaDetalhes.getTst(), boldCellZehnCenterGrey, boldCellZehnNbLeft, cellZehnNbM, cellZehnNbD, cellZehnNbN, repasse, TipoAtividade.TE, (repasse?27:22));  
//        montarSubDetalhamento(sheet, ch, planilhaDetalhes.getTst(), boldCellZehnCenterGrey, boldCellZehnNbLeft, cellZehnNbM, cellZehnNbD, cellZehnNbN, repasse, TipoAtividade.TE, (repasse?22:18));
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
    }
    
    private static void montarAliAie(HSSFSheet sheet, List<ProgressoAtividade> lista, CellStyle boldCellZehnCenterGrey,CellStyle boldCellZehnNbLeft,CellStyle cellZehnNbM, CellStyle cellZehnNbD, CellStyle cellZehnNbN, boolean repasse, int initRow){
        sheet.addMergedRegion(new CellRangeAddress(initRow, initRow, 0, 3));
        HSSFRow row = sheet.createRow((short) (initRow));
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellStyle(boldCellZehnCenterGrey);
        cell.setCellValue("ALI/AIE");
        
        Double pfE =0.0, pfD =0.0;
        for (ProgressoAtividade progressoAtividade : lista) {
            pfE += progressoAtividade.getId().getAtividade().getAliEstimada();
            pfD += progressoAtividade.getId().getAtividade().getAliDetalhada();
        }
        
        row = sheet.createRow((short) (initRow+1));
        cell = row.createCell((short) 0);
        cell.setCellStyle(boldCellZehnNbLeft);
        cell.setCellValue("Valor de Pontos de Função Estimado");         
        cell = row.createCell((short) 1);
        cell.setCellStyle(cellZehnNbM);
        cell.setCellValue(pfE*valorContrato*getModificador(TipoAtividade.DE)); 
        cell = row.createCell((short) 2);
        cell.setCellStyle(boldCellZehnNbLeft);
        cell.setCellValue("Quantidade de Pontos de Função Estimado");         
        cell = row.createCell((short) 3);
        cell.setCellStyle(cellZehnNbD);
        cell.setCellValue(pfE);
        
        row = sheet.createRow((short) (initRow+2));
        cell = row.createCell((short) 0);
        cell.setCellStyle(boldCellZehnNbLeft);
        cell.setCellValue("Valor de Pontos de Função Detalhado");         
        cell = row.createCell((short) 1);
        cell.setCellStyle(cellZehnNbM);
        cell.setCellValue(pfD*valorContrato*getModificador(TipoAtividade.DE));         
        cell = row.createCell((short) 2);        
        cell.setCellStyle(boldCellZehnNbLeft);
        cell.setCellValue("Quantidade de Pontos de Função Detalhado");         
        cell = row.createCell((short) 3);
        cell.setCellStyle(cellZehnNbD);
        cell.setCellValue(pfD);
        
        if(repasse){
            row = sheet.createRow((short) (initRow+3));
            cell = row.createCell((short) 0);
            cell.setCellStyle(boldCellZehnNbLeft);
            cell.setCellValue("Valor Total Detalhado de Repasse");         
            cell = row.createCell((short) 1);        
            cell.setCellStyle(cellZehnNbM);
            cell.setCellValue(pfD*valorRepasse*getModificador(TipoAtividade.DE)); 
        }
        
    }
    
    private static void montarSubDetalhamento(HSSFSheet sheet,CreationHelper ch, List<ProgressoAtividade> lista, CellStyle boldCellZehnCenterGrey,CellStyle boldCellZehnNbLeft,CellStyle cellZehnNbM, CellStyle cellZehnNbD, CellStyle cellZehnNbN, boolean repasse, TipoAtividade tipoAtividade, int initRow){
        sheet.addMergedRegion(new CellRangeAddress(initRow, initRow, 0, 3));
        HSSFRow row = sheet.createRow((short) (initRow));
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellStyle(boldCellZehnCenterGrey);
        cell.setCellValue(tipoAtividade.toString()); 
        
        Double pfE =0.0, pfD =0.0;
        for (ProgressoAtividade progressoAtividade : lista) {
            pfE += progressoAtividade.getId().getAtividade().getContagemEstimada();
            pfD += progressoAtividade.getId().getAtividade().getContagemDetalhada();
        }
        
        row = sheet.createRow((short) (initRow+1));
        cell = row.createCell((short) 0);
        cell.setCellStyle(boldCellZehnNbLeft);
        cell.setCellValue("Valor de Pontos de Função Estimado");         
        cell = row.createCell((short) 1);
        cell.setCellStyle(cellZehnNbM);
        cell.setCellValue(pfE*valorContrato*getModificador(tipoAtividade)); 
        cell = row.createCell((short) 2);
        cell.setCellStyle(boldCellZehnNbLeft);
        cell.setCellValue("Quantidade de Pontos de Função Estimado");         
        cell = row.createCell((short) 3);
        cell.setCellStyle(cellZehnNbD);
        cell.setCellValue(pfE);
        
        row = sheet.createRow((short) (initRow+2));
        cell = row.createCell((short) 0);
        cell.setCellStyle(boldCellZehnNbLeft);
        cell.setCellValue("Valor de Pontos de Função Detalhado");         
        cell = row.createCell((short) 1);
        cell.setCellStyle(cellZehnNbM);
        cell.setCellValue(pfD*valorContrato*getModificador(tipoAtividade));         
        cell = row.createCell((short) 2);        
        cell.setCellStyle(boldCellZehnNbLeft);
        cell.setCellValue("Quantidade de Pontos de Função Detalhado");         
        cell = row.createCell((short) 3);
        cell.setCellStyle(cellZehnNbD);
        cell.setCellValue(pfD);
        
        row = sheet.createRow((short) (initRow+3));
        cell = row.createCell((short) 0);
        cell.setCellStyle(boldCellZehnNbLeft);
        cell.setCellValue("Valor Total Detalhado de Contrato");         
        cell = row.createCell((short) 1);        
        cell.setCellStyle(cellZehnNbM);
        cell.setCellValue(pfD*valorContrato*getModificador(tipoAtividade)); 
        cell = row.createCell((short) 2);
        cell.setCellStyle(boldCellZehnNbLeft);
        cell.setCellValue("Quantidade de Atividades");         
        cell = row.createCell((short) 3);
        cell.setCellStyle(cellZehnNbN);
        cell.setCellValue(lista.size());
        
        if(repasse){
            row = sheet.createRow((short) (initRow+4));
            cell = row.createCell((short) 0);
            cell.setCellStyle(boldCellZehnNbLeft);
            cell.setCellValue("Valor Total Detalhado de Repasse");         
            cell = row.createCell((short) 1);
            cell.setCellStyle(cellZehnNbM);
            cell.setCellValue(pfD*valorRepasse*getModificador(tipoAtividade)); 
            
        }
        
    }
        
    
    private static void montarProgressoAtividade(HSSFSheet sheet,List<ProgressoAtividade> lista, CellStyle boldCellZwolf, CellStyle cellZehnM, CellStyle cellZehnD, CellStyle cellZehnN , boolean repasse, TipoAtividade tipoAtividade) {
            HSSFRow row = sheet.createRow((short) 0);
            HSSFCell cell = row.createCell((short) 0);
            cell.setCellValue("ID");
            cell.setCellStyle(boldCellZwolf);
            cell = row.createCell((short) 1);
            cell.setCellValue("Ordem de Serviço");
            cell.setCellStyle(boldCellZwolf);
            cell = row.createCell((short) 2);
            cell.setCellValue("Módulo");
            cell.setCellStyle(boldCellZwolf);
            cell = row.createCell((short) 3);
            cell.setCellValue("Projeto");
            cell.setCellStyle(boldCellZwolf);
            cell = row.createCell((short) 4);
            cell.setCellValue("Atividade");
            cell.setCellStyle(boldCellZwolf);
            cell = row.createCell((short) 5);
            cell.setCellValue("Estimada (PF)");
            cell.setCellStyle(boldCellZwolf);
            cell = row.createCell((short) 6);
            cell.setCellValue("Detalhada (PF)");
            cell.setCellStyle(boldCellZwolf);
            cell = row.createCell((short) 7);
            cell.setCellValue("Estimada (Contrato)");
            cell.setCellStyle(boldCellZwolf);
            cell = row.createCell((short) 8);
            cell.setCellValue("Detalhada (Contrato)");
            cell.setCellStyle(boldCellZwolf);
            if(repasse){
                cell = row.createCell((short) 9);
                cell.setCellValue("Estimada (Repasse)");
                cell.setCellStyle(boldCellZwolf);            
                cell = row.createCell((short) 10);
                cell.setCellValue("Detalhada (Repasse)");
                cell.setCellStyle(boldCellZwolf);
            }

            Integer contador = 1;
            for (ProgressoAtividade progressoAtividade : lista) {
                    row = sheet.createRow(contador);
                    cell = row.createCell((short) 0);
                    cell.setCellValue(contador);
                    cell.setCellStyle(cellZehnN);
                    cell = row.createCell((short) 1);
                    cell.setCellValue(progressoAtividade.getId().getAtividade().getOrdemServico().toString());
                    cell.setCellStyle(cellZehnN);
                    cell = row.createCell((short) 2);
                    cell.setCellValue(progressoAtividade.getId().getAtividade().getPacote().getModulo().toString());
                    cell.setCellStyle(cellZehnN);
                    cell = row.createCell((short) 3);
                    cell.setCellValue(progressoAtividade.getId().getAtividade().getPacote().getModulo().getProjeto().toString());
                    cell.setCellStyle(cellZehnN);
                    cell = row.createCell((short) 4);
                    cell.setCellValue(progressoAtividade.getId().getAtividade().toString());
                    cell.setCellStyle(cellZehnN);
                    cell = row.createCell((short) 5);
                    cell.setCellValue(progressoAtividade.getId().getAtividade().getContagemEstimada());                  
                    cell.setCellStyle(cellZehnD);
                    cell = row.createCell((short) 6);
                    cell.setCellValue(progressoAtividade.getId().getAtividade().getContagemDetalhada());
                    cell.setCellStyle(cellZehnD);
                    cell = row.createCell((short) 7);
                    cell.setCellValue(progressoAtividade.getId().getAtividade().getContagemEstimada() * getModificador(tipoAtividade) * valorContrato);
                    cell.setCellStyle(cellZehnM);
                    cell = row.createCell((short) 8);
                    cell.setCellValue(progressoAtividade.getId().getAtividade().getContagemDetalhada() * getModificador(tipoAtividade) * valorContrato);
                    cell.setCellStyle(cellZehnM);
                    if(repasse){
                        cell = row.createCell((short) 9);                        
                        cell.setCellValue(progressoAtividade.getId().getAtividade().getContagemEstimada() * getModificador(tipoAtividade) * valorRepasse);
                        cell.setCellStyle(cellZehnM);                    
                        cell = row.createCell((short) 10);
                        cell.setCellValue(progressoAtividade.getId().getAtividade().getContagemDetalhada() * getModificador(tipoAtividade) * valorRepasse);
                        cell.setCellStyle(cellZehnM);                        
                    }
                    contador++;
            }


            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8); 
            if(repasse){                           
                sheet.autoSizeColumn(9);
                sheet.autoSizeColumn(10);
            }
    }
    
    private static double getModificador(TipoAtividade tipoAtividade){
        switch(tipoAtividade){
            case LE:
                return 0.35;
            case DE:
                return 0.4;
            case TE:
                return 0.25;
            default:
                return 0.0;
        }
    }
}
