package com.promise.util.dataUtil;

import com.promise.util.model.ProReport;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by leiwei on 2019/6/12 16:10
 */
public class ExcelUtil {




    public static void makeExcel(List<ProReport> proReports, List<String> title, Map<String,Double> total, String name) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet( name);
        //人名样式
        HSSFCellStyle nameStyle = createCellStyle(workbook, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, HSSFColor.GREEN.index, true, (short) 12);
        //列标题样式
        HSSFCellStyle colStyle = createCellStyle(workbook, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, HSSFColor.DARK_RED.index, true, (short) 12);
        //内容样式
        HSSFCellStyle Style01 = createCellStyle(workbook, HorizontalAlignment.CENTER, VerticalAlignment.TOP, HSSFColor.AUTOMATIC.index, false, (short) 12);
        //剩余工时列样式
        HSSFCellStyle Style02 = createCellStyle(workbook, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, HSSFColor.RED.index, false, (short) 12);
        //2.创建工作表
        //第三步，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //设置行高
        row.setHeight((short) (40 * 20));
        //第四步，创建列标题;并且设置列标题
        List<String> titles = new ArrayList<>();
        titles.add("项目");
        titles.add("月统计(人/时)");
        titles.add("周统计(人/时)");
        titles.addAll(title);
        for (int i = 0; i < titles.size(); i++) {
            HSSFCell cell = row.createCell(i);
            //加载单元格样式
            cell.setCellStyle(colStyle);
            cell.setCellValue(titles.get(i));
        }
        //设置列宽
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {
                sheet.setColumnWidth(i, 256 * 50 + 184);
            } else {
                sheet.setColumnWidth(i, 256 * 15 + 184);
            }
            //sheet.autoSizeColumn(i);

        }
        //第五步，写入实体数据，实际应用中这些数据从数据库得到,对象封装数据，集合包对象。对象的属性值对应表的每行的值
        if (proReports != null && proReports.size() >= 1) {
            for (int i = 0; i < proReports.size() + 1; i++) {
                HSSFRow row1 = sheet.createRow(i + 1);
                if (i == 0) {
                    row1.setHeight((short) (30 * 20));
                    //创建单元格设值
                    HSSFCell cell0 = row1.createCell(0);
                    cell0.setCellStyle(Style02);
                    cell0.setCellValue("总计");
                    for (int j = 1; j < titles.size(); j++) {
                        HSSFCell cell = row1.createCell(j);
                        cell.setCellStyle(Style02);
                        String cellTitle = row.getCell(j).getStringCellValue();
                        if (total.containsKey(cellTitle.replace("(周-人/时)", ""))) {
                            cell.setCellValue(total.get(cellTitle.replace("(周-人/时)", "")));
                        } else if (total.containsKey(cellTitle.replace("(人/时)", ""))) {
                            cell.setCellValue(total.get(cellTitle.replace("(人/时)", "")));
                        }
                    }
                } else {
                    //row1.setHeight((short)(50*20));
                    ProReport proReport = proReports.get(i - 1);
                    //创建单元格设值
                    HSSFCell cell0 = row1.createCell(0);
                    cell0.setCellStyle(nameStyle);
                    cell0.setCellValue(proReport.getProject());
                    HSSFCell cell1 = row1.createCell(1);
                    cell1.setCellStyle(Style01);
                    if (proReport.getMtimes() != null) {
                        cell1.setCellValue(proReport.getMtimes());
                    }
                    HSSFCell cell2 = row1.createCell(2);
                    cell2.setCellStyle(Style01);
                    if (proReport.getWtimes() != null) {
                        cell2.setCellValue(proReport.getWtimes());
                    }

                    Map<String, Double> buMap = proReport.getBuMap();
                    for (int j = 3; j < titles.size(); j++) {
                        HSSFCell cell = row1.createCell(j);
                        cell.setCellStyle(Style01);
                        if (buMap != null) {
                            String cellTitle = row.getCell(j).getStringCellValue();
                            if (buMap.containsKey(cellTitle.replace("(周-人/时)", ""))) {
                                cell.setCellValue(buMap.get(cellTitle.replace("(周-人/时)", "")));
                            }
                        }
                    }
                }
            }
        }

        //将文件保存到指定的位置
        try {
            String str ="/d....";
            String dir = str.replace("//", "/");
            String location = dir + name + ".xls";
            FileOutputStream fos = new FileOutputStream(location);
            workbook.write(fos);
            System.out.println("写入成功");
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, HorizontalAlignment align0, VerticalAlignment align1, short color, boolean bold, short fontsize) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(align0);//水平居中
        style.setVerticalAlignment(align1);//垂直居中
        style.setWrapText(true);//自动换行
        //设置边框
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        //创建字体
        HSSFFont font = workbook.createFont();
        font.setFontName("等线");
        font.setColor(color);
        //font.setBoldweight(boldweight);
        font.setBold(bold);
        font.setFontHeightInPoints(fontsize);
        //加载字体
        style.setFont(font);
        return style;
    }
}
