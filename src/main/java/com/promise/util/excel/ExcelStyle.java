package com.promise.util.excel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by leiwei on 2019/6/12 16:10
 */
public class ExcelStyle {

    /**
     * @Description:  单元格样式
     * @param workbook:
     * @param fontsize:
     * @return org.apache.poi.hssf.usermodel.HSSFCellStyle
     */
    static XSSFCellStyle createCellStyle(XSSFWorkbook workbook, HorizontalAlignment align0, Short bg, VerticalAlignment align1, short color, boolean bold, short fontsize) {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(align0);//水平居中
        style.setVerticalAlignment(align1);//垂直居中
        style.setWrapText(true);//自动换行
        //设置边框
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        //创建字体
        XSSFFont font = workbook.createFont();
        font.setFontName("等线");
        font.setColor(color);
        //font.setBoldweight(boldweight);
        font.setBold(bold);
        font.setFontHeightInPoints(fontsize);
        //加载字体
        style.setFont(font);
        if(bg!=null){
            style.setFillForegroundColor(bg);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        return style;
    }
}
