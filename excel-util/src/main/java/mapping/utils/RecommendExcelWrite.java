package mapping.utils;

import mapping.annotations.FieldMeta;
import mapping.constant.ElementType;
import mapping.model.RecommendMappingResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by leiwei on 2020/8/7 10:43
 */
public class RecommendExcelWrite {

    private static final Logger LOGGER = Logger.getLogger(RecommendExcelWrite.class);

    private final   static List<Integer> INTEGERS= Arrays.asList(0,1,6);


    public static void writeRecommendMapping(List<RecommendMappingResult> recommendMappingResults, String path) throws Exception {

        XSSFWorkbook workbook= new XSSFWorkbook();

        //列标题
        XSSFCellStyle colStyle = ExcelUtil.createCellStyle(workbook, HorizontalAlignment.CENTER, HSSFColor.LIME.index, VerticalAlignment.CENTER, HSSFColor.BLACK.index,true,(short)12);
        //内容样式
        XSSFCellStyle Style01 = ExcelUtil.createCellStyle(workbook, HorizontalAlignment.CENTER,null, VerticalAlignment.TOP, HSSFColor.AUTOMATIC.index,false,(short)12);

        //获取title
        List<String> attrTitle = RecommendMappingResult.getAttrTitle();

        Map<ElementType, List<RecommendMappingResult>> typeListMap = recommendMappingResults.stream().collect(Collectors.groupingBy(RecommendMappingResult::getType));


        //实体
        List<RecommendMappingResult> recommendMappingResultsEntity = typeListMap.get(ElementType.ENTITY);
        writeContent(workbook, colStyle, Style01, attrTitle, recommendMappingResultsEntity);
        //事件
        List<RecommendMappingResult> recommendMappingResultsEvent= typeListMap.get(ElementType.EVENT);
        writeContent(workbook, colStyle, Style01, attrTitle, recommendMappingResultsEvent);
        //关系
        List<RecommendMappingResult> recommendMappingResultsRelation = typeListMap.get(ElementType.RELATION);
        writeContent(workbook, colStyle, Style01, attrTitle, recommendMappingResultsRelation);

        //将文件保存到指定的位置
        try {

            path=path.replace("//", "/");
            FileOutputStream fos = new FileOutputStream(path);
            workbook.write(fos);
            LOGGER.info(path+"写入完成");
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeContent(XSSFWorkbook workbook, XSSFCellStyle colStyle, XSSFCellStyle style01, List<String> attrTitle, List<RecommendMappingResult> recommendMappingResultsEntity) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        //按目标表分类建sheet
        Map<String, List<RecommendMappingResult>> targetRec = recommendMappingResultsEntity.stream().collect(Collectors.groupingBy(RecommendMappingResult::getTargetTableNameEn));

        for (Map.Entry<String, List<RecommendMappingResult>> entry : targetRec.entrySet()) {
            RecommendMappingResult pre = entry.getValue().get(0);
            //建立sheet
            XSSFSheet sheet = workbook.createSheet(pre.getType().getElementType() + "_" + pre.getTargetTableNameEn());

            //建立title
            XSSFRow titleRow = sheet.createRow(0);

            titleRow.setHeight((short)(30*20));


            for(int i=0;i<attrTitle.size();i++) {
                XSSFCell cell = titleRow.createCell(i);
                //加载单元格样式
                cell.setCellStyle(colStyle);
                cell.setCellValue(attrTitle.get(i));
                sheet.autoSizeColumn(i);
            }

            //写入内容,按原始表分类
            Map<Integer, List<RecommendMappingResult>> recOrigin = entry.getValue().stream().collect(Collectors.groupingBy(RecommendMappingResult::getOriginTableId));
            int indexRow=1;
            for (List<RecommendMappingResult> instance : recOrigin.values()) {
                for (RecommendMappingResult recommendMappingResult : instance) {
                    XSSFRow rowRecord = sheet.createRow(indexRow);
                    for(int i=0;i<attrTitle.size();i++){
                        XSSFCell cell = rowRecord.createCell(i);
                        cell.setCellStyle(style01);
                        //获取值
                        String cellTitle = titleRow.getCell(i).getStringCellValue();
                        Field[] fields = recommendMappingResult.getClass().getDeclaredFields();
                        for (Field field : fields) {
                            if(field.isAnnotationPresent(FieldMeta.class)&& StringUtils.equals(field.getAnnotation(FieldMeta.class).fieldName(),cellTitle)){
                                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), recommendMappingResult.getClass());
                                Method readMethod = pd.getReadMethod();
                                Object result = readMethod.invoke(recommendMappingResult);
                                if(INTEGERS.contains(i)){
                                    cell.setCellValue((int)result);
                                }else {
                                    cell.setCellValue((String) result);
                                }
                                sheet.autoSizeColumn(i);
                                break;
                            }
                        }
                    }
                    indexRow+=1;
                }
            }

        }
    }
}
