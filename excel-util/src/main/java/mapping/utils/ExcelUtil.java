package mapping.utils;

import mapping.annotations.MethodMeta;
import mapping.model.BaseModel;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leiwei on 2019/6/12 16:10
 */
public class ExcelUtil {

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


    public static Map<String, Pair<Integer,Integer>> getMergeCell(Sheet sheet){
        //(row_coll,(rowfirst,colfirst))
        Map<String, Pair<Integer,Integer>> map=new HashMap<>();
        int sheetMergeCount = sheet.getNumMergedRegions();
        for(int i = 0 ; i < sheetMergeCount ; i++){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            for (int m=firstRow;m<=lastRow;m++){
                for (int n=firstColumn;n<=lastColumn;n++){
                    map.put(m+"_"+n,Pair.of(firstRow,firstColumn));
                }
            }
        }
        return map;
    }

    public static <T extends BaseModel> List<T> coverDataToBean(Sheet sheetEntity, Class<T> clazz,List<Integer> unIndexs) throws Exception {
        List<T> entityList=new ArrayList<>();
        int entityLastRowNum = sheetEntity.getLastRowNum();
        //获取合并单元格
        Map<String, Pair<Integer, Integer>> mergeCell = ExcelUtil.getMergeCell(sheetEntity);

        if(entityLastRowNum>0){
            able:
            for (int j = 1; j <= entityLastRowNum; j++) {
                T o = clazz.newInstance();
                Row row = sheetEntity.getRow(j);
                if(row==null )continue;
                //处理合并单元和及空值过滤
                for (Cell cell : row) {
                    int rowIndex = cell.getRowIndex();
                    int columnIndex = cell.getColumnIndex();
                    if(unIndexs!=null&&unIndexs.contains(columnIndex))continue ;
                    Pair<Integer, Integer> pair = mergeCell.get(rowIndex + "_" + columnIndex);
                    if(pair!=null){
                        Row row1 = sheetEntity.getRow(pair.getLeft());
                        cell.setCellValue(row1.getCell(pair.getRight()).getStringCellValue());
                    }
                    //有单原值为空去掉整行
                    CellType typeEnum = cell.getCellTypeEnum();
                    if(typeEnum == CellType.BLANK) {
                        continue able;
                    }
                }

                Method[] methods = o.getClass().getDeclaredMethods();
                for (Method method : methods) {
                    if(method.isAnnotationPresent(MethodMeta.class)){
                        Class<?> type = method.getParameterTypes()[0];
                        int colIndex=method.getAnnotation(MethodMeta.class).index();
                        Cell cell = row.getCell(colIndex);
                        Object value;
                        switch (cell.getCellTypeEnum()){
                            case NUMERIC:
                                value = get(type, cell.getNumericCellValue());
                                break ;
                            case STRING:
                                value = get(type, cell.getStringCellValue());
                                break ;
                            default:
                                value="";

                        }
                        method.invoke(o,value);
                    }
                }
                entityList.add(o);
            }
        }
        return entityList;
    }

    private static  <T> T get(Class<T> clz,Object o){
        if(clz.isInstance(o)){
            return clz.cast(o);
        }
        return null;
    }
}
