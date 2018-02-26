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

    public static String gerarDetalhamento(PlanilhaDetalhes planilhaDetalhes) {
            try {
                    valorContrato = new ParametroDAO().buscaParametroRecente(TipoParametro.CONTRATO).getValor();
                    valorRepasse = new ParametroDAO().buscaParametroRecente(TipoParametro.REPASSE).getValor();
                    String fileName = "C:\\planilhas\\" + buildLabelDetalhamento(planilhaDetalhes.getData(),(planilhaDetalhes.isRepasse()? "STEFANINI":"BDMG"),".xls");
                    FileOutputStream fileOut = new FileOutputStream(fileName);
                    HSSFWorkbook workbook = new HSSFWorkbook();

                    HSSFFont zehn = workbook.createFont();
                    zehn.setFontHeightInPoints((short) 10);
                    CellStyle cellZehn = workbook.createCellStyle();
                    cellZehn.setFont(zehn);
                    cellZehn.setBorderTop(BorderStyle.THIN);
                    cellZehn.setTopBorderColor(HSSFColor.BLACK.index);
                    cellZehn.setBorderBottom(BorderStyle.THIN);
                    cellZehn.setBottomBorderColor(HSSFColor.BLACK.index);
                    cellZehn.setBorderLeft(BorderStyle.THIN);
                    cellZehn.setLeftBorderColor(HSSFColor.BLACK.index);
                    cellZehn.setBorderRight(BorderStyle.THIN);
                    cellZehn.setRightBorderColor(HSSFColor.BLACK.index);

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
                    boldCellZwolf.setBorderTop(BorderStyle.THIN);
                    boldCellZwolf.setTopBorderColor(HSSFColor.BLACK.index);
                    boldCellZwolf.setBorderBottom(BorderStyle.THIN);
                    boldCellZwolf.setBottomBorderColor(HSSFColor.BLACK.index);
                    boldCellZwolf.setBorderLeft(BorderStyle.THIN);
                    boldCellZwolf.setLeftBorderColor(HSSFColor.BLACK.index);
                    boldCellZwolf.setBorderRight(BorderStyle.THIN);
                    boldCellZwolf.setRightBorderColor(HSSFColor.BLACK.index);

                    HSSFSheet sheetDetalhamento = workbook.createSheet(buildLabelDetalhamento(planilhaDetalhes.getData(),"Detalhamento de ",""));
                    HSSFRow row = sheetDetalhamento.createRow((short) 0);
                    HSSFCell cell = row.createCell((short) 0);
                    cell.setCellValue("Valor Total Estimado (Contrato) - R$:");
                    boldCellZehn.setDataFormat((short)0);
                    cell.setCellStyle(boldCellZehn);
                    cell = row.createCell((short) 1);
                    cell.setCellValue(planilhaDetalhes.getTotalEstimadaContrato());
                    cellZehn.setDataFormat((short)4);
                    cell.setCellStyle(cellZehn);
                    cell = row.createCell((short) 2);
                    cell.setCellValue("Valor Total Detalhado (Contrato) - R$:");
                    boldCellZehn.setDataFormat((short)0);
                    cell.setCellStyle(boldCellZehn);
                    cell = row.createCell((short) 3);
                    cell.setCellValue(planilhaDetalhes.getTotalDetalhadaContrato());
                    cellZehn.setDataFormat((short)4);
                    cell.setCellStyle(cellZehn);

                    row = sheetDetalhamento.createRow((short) 1);
                    cell = row.createCell((short) 0);
                    cell.setCellValue("Valor Total Estimado (Repasse) - R$:");
                    boldCellZehn.setDataFormat((short)0);
                    cell.setCellStyle(boldCellZehn);
                    cell = row.createCell((short) 1);
                    cell.setCellValue(planilhaDetalhes.getTotalEstimadaRepasse());
                    cellZehn.setDataFormat((short)4);
                    cell.setCellStyle(cellZehn);
                    cell = row.createCell((short) 2);
                    cell.setCellValue("Valor Total Detalhado (Repasse) - R$:");
                    boldCellZehn.setDataFormat((short)0);
                    cell.setCellStyle(boldCellZehn);
                    cell = row.createCell((short) 3);
                    cell.setCellValue(planilhaDetalhes.getTotalDetalhadaRepasse());
                    cellZehn.setDataFormat((short)4);
                    cell.setCellStyle(cellZehn);

                    row = sheetDetalhamento.createRow((short) 3);
                    cell = row.createCell((short) 0);
                    cell.setCellValue("Levantamento:");
                    boldCellZehn.setDataFormat((short)0);
                    cell.setCellStyle(boldCellZehn);
                    cell = row.createCell((short) 1);
                    cell.setCellValue(planilhaDetalhes.getLev().size());
                    cellZehn.setDataFormat((short)0);
                    cell.setCellStyle(cellZehn);

                    row = sheetDetalhamento.createRow((short) 4);
                    cell = row.createCell((short) 0);
                    cell.setCellValue("Desenvolvimento:");
                    boldCellZehn.setDataFormat((short)0);
                    cell.setCellStyle(boldCellZehn);
                    cell = row.createCell((short) 1);
                    cell.setCellValue(planilhaDetalhes.getDev().size());
                    cellZehn.setDataFormat((short)0);
                    cell.setCellStyle(cellZehn);

                    row = sheetDetalhamento.createRow((short) 5);
                    cell = row.createCell((short) 0);
                    cell.setCellValue("Teste e Homologação:");
                    boldCellZehn.setDataFormat((short)0);                    
                    cell.setCellStyle(boldCellZehn);
                    cell = row.createCell((short) 1);
                    cell.setCellValue(planilhaDetalhes.getTst().size());
                    cellZehn.setDataFormat((short)0);
                    cell.setCellStyle(cellZehn);

                    sheetDetalhamento.autoSizeColumn(0);
                    sheetDetalhamento.autoSizeColumn(1);
                    sheetDetalhamento.autoSizeColumn(2);
                    sheetDetalhamento.autoSizeColumn(3);
                    HSSFSheet sheetLevantamento = workbook.createSheet("Levantamento - 35%");
                    montarProgressoAtividade(sheetLevantamento, planilhaDetalhes.getLev(), boldCellZwolf, cellZehn, planilhaDetalhes.isRepasse(), TipoAtividade.LE);

                    HSSFSheet sheetDesenvolvimento = workbook.createSheet("Desenvolvimento - 40%");
                    montarProgressoAtividade(sheetDesenvolvimento, planilhaDetalhes.getDev(), boldCellZwolf, cellZehn, planilhaDetalhes.isRepasse(), TipoAtividade.DE);
                    HSSFSheet sheetHomologacao = workbook.createSheet("Teste e Homologação - 25%");
                    montarProgressoAtividade(sheetHomologacao, planilhaDetalhes.getTst(), boldCellZwolf, cellZehn, planilhaDetalhes.isRepasse(), TipoAtividade.TE);

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

    private static void montarProgressoAtividade(HSSFSheet sheet,List<ProgressoAtividade> lista, CellStyle boldCellZwolf, CellStyle cellZehn , boolean repasse, TipoAtividade tipoAtividade) {
            HSSFRow row = sheet.createRow((short) 0);
            HSSFCell cell = row.createCell((short) 0);
            cell.setCellValue("ID");
            boldCellZwolf.setDataFormat((short)0);
            cell.setCellStyle(boldCellZwolf);
            cell = row.createCell((short) 1);
            cell.setCellValue("Ordem de Serviço");
            boldCellZwolf.setDataFormat((short)0);
            cell.setCellStyle(boldCellZwolf);
            cell = row.createCell((short) 2);
            cell.setCellValue("Módulo");
            boldCellZwolf.setDataFormat((short)0);
            cell.setCellStyle(boldCellZwolf);
            cell = row.createCell((short) 3);
            cell.setCellValue("Projeto");
            boldCellZwolf.setDataFormat((short)0);
            cell.setCellStyle(boldCellZwolf);
            cell = row.createCell((short) 4);
            cell.setCellValue("Estimada (PF)");
            boldCellZwolf.setDataFormat((short)0);
            cell.setCellStyle(boldCellZwolf);
            cell = row.createCell((short) 5);
            cell.setCellValue("Detalhada (PF)");
            boldCellZwolf.setDataFormat((short)0);
            cell.setCellStyle(boldCellZwolf);
            cell = row.createCell((short) 6);
            cell.setCellValue("Estimada (Contrato)");
            boldCellZwolf.setDataFormat((short)0);
            cell.setCellStyle(boldCellZwolf);
            cell = row.createCell((short) 7);
            cell.setCellValue("Detalhada (Contrato)");
            boldCellZwolf.setDataFormat((short)0);
            cell.setCellStyle(boldCellZwolf);
            if(repasse){
                cell = row.createCell((short) 8);
                cell.setCellValue("Estimada (Repasse)");
                cellZehn.setDataFormat((short)0);
                cell.setCellStyle(boldCellZwolf);            
                cell = row.createCell((short) 9);
                cell.setCellValue("Detalhada (Repasse)");
                cellZehn.setDataFormat((short)0);
                cell.setCellStyle(boldCellZwolf);
            }

            Integer contador = 1;
            for (ProgressoAtividade progressoAtividade : lista) {
                    row = sheet.createRow(contador);
                    cell = row.createCell((short) 0);
                    cell.setCellValue(contador);
                    cellZehn.setDataFormat((short)1);
                    cell.setCellStyle(cellZehn);
                    cell = row.createCell((short) 1);
                    cell.setCellValue(progressoAtividade.getAtividade().getOrdemServico().toString());
                    cellZehn.setDataFormat((short)0);
                    cell.setCellStyle(cellZehn);
                    cell = row.createCell((short) 2);
                    cell.setCellValue(progressoAtividade.getAtividade().getPacote().getModulo().toString());
                    cellZehn.setDataFormat((short)0);
                    cell.setCellStyle(cellZehn);
                    cell = row.createCell((short) 3);
                    cell.setCellValue(progressoAtividade.getAtividade().getPacote().getModulo().getProjeto().toString());
                    cellZehn.setDataFormat((short)0);
                    cell.setCellStyle(cellZehn);
                    cell = row.createCell((short) 4);
                    cell.setCellValue(progressoAtividade.getAtividade().getContagemEstimada());
                    cellZehn.setDataFormat((short)2);
                    cell.setCellStyle(cellZehn);
                    cell = row.createCell((short) 5);
                    cell.setCellValue(progressoAtividade.getAtividade().getContagemDetalhada());
                    cellZehn.setDataFormat((short)2);
                    cell.setCellStyle(cellZehn);
                    cell = row.createCell((short) 6);
                    cell.setCellValue(progressoAtividade.getAtividade().getContagemEstimada() * getModificador(tipoAtividade) * valorContrato);
                    cellZehn.setDataFormat((short)4);
                    cell.setCellStyle(cellZehn);
                    cell = row.createCell((short) 7);
                    cell.setCellValue(progressoAtividade.getAtividade().getContagemDetalhada() * getModificador(tipoAtividade) * valorContrato);
                    cellZehn.setDataFormat((short)4);
                    cell.setCellStyle(cellZehn);
                    if(repasse){
                        cell = row.createCell((short) 8);                        
                        cell.setCellValue(progressoAtividade.getAtividade().getContagemEstimada() * getModificador(tipoAtividade) * valorRepasse);
                        cellZehn.setDataFormat((short)4);
                        cell.setCellStyle(cellZehn);                    
                        cell = row.createCell((short) 9);
                        cell.setCellValue(progressoAtividade.getAtividade().getContagemDetalhada() * getModificador(tipoAtividade) * valorRepasse);
                        cellZehn.setDataFormat((short)4);
                        cell.setCellStyle(cellZehn);                        
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
            if(repasse){
                sheet.autoSizeColumn(8);            
                sheet.autoSizeColumn(9);
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
