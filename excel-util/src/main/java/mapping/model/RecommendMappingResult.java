package mapping.model;


import mapping.annotations.FieldMeta;
import mapping.constant.ElementType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by leiwei on 2020/8/6 16:07
 */
public class RecommendMappingResult {

    //@FieldMeta(fieldName = "目标表类型")
    private ElementType type;

    //@FieldMeta(fieldName = "目标表英文名")
    private String TargetTableNameEn;

    @FieldMeta(fieldName = "数据源ID",order = 1)
    private Integer dataSourceInfoId;

    @FieldMeta(fieldName = "原始表ID",order = 2)
    private Integer originTableId;

    @FieldMeta(fieldName = "原始表英文名",order = 3)
    private String originTableNameEn;

    @FieldMeta(fieldName = "原始表中文名",order = 4)
    private String originTableNameCn;

    @FieldMeta(fieldName = "目标表主键字段英文名",order = 5)
    private String primaryEn;

    @FieldMeta(fieldName = "目标表主键字段中文名",order = 6)
    private String primaryCn;

    @FieldMeta(fieldName = "原始表字段ID",order = 7)
    private Integer originTableMappingAttrId;

    @FieldMeta(fieldName = "原始表映射字段英文名",order = 8)
    private String originTableMappingAttrNameEn;

    @FieldMeta(fieldName = "原始表映射字段中文名",order = 9)
    private String originTableMappingAttrNameCn;

    @FieldMeta(fieldName = "原始表映射字段字段类型",order = 10)
    private String originTableMappingAttrColumnType;

    public RecommendMappingResult() {
    }

    public RecommendMappingResult(ElementType type, String targetTableNameEn, Integer dataSourceInfoId, Integer originTableId, String originTableNameEn, String originTableNameCn, String primaryEn, String primaryCn, Integer originTableMappingAttrId, String originTableMappingAttrNameEn, String originTableMappingAttrNameCn, String originTableMappingAttrColumnType) {
        this.type = type;
        TargetTableNameEn = targetTableNameEn;
        this.dataSourceInfoId = dataSourceInfoId;
        this.originTableId = originTableId;
        this.originTableNameEn = originTableNameEn;
        this.originTableNameCn = originTableNameCn;
        this.primaryEn = primaryEn;
        this.primaryCn = primaryCn;
        this.originTableMappingAttrId = originTableMappingAttrId;
        this.originTableMappingAttrNameEn = originTableMappingAttrNameEn;
        this.originTableMappingAttrNameCn = originTableMappingAttrNameCn;
        this.originTableMappingAttrColumnType = originTableMappingAttrColumnType;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    public String getTargetTableNameEn() {
        return TargetTableNameEn;
    }

    public void setTargetTableNameEn(String targetTableNameEn) {
        TargetTableNameEn = targetTableNameEn;
    }

    public Integer getDataSourceInfoId() {
        return dataSourceInfoId;
    }

    public void setDataSourceInfoId(Integer dataSourceInfoId) {
        this.dataSourceInfoId = dataSourceInfoId;
    }

    public Integer getOriginTableId() {
        return originTableId;
    }

    public void setOriginTableId(Integer originTableId) {
        this.originTableId = originTableId;
    }

    public String getOriginTableNameEn() {
        return originTableNameEn;
    }

    public void setOriginTableNameEn(String originTableNameEn) {
        this.originTableNameEn = originTableNameEn;
    }

    public String getOriginTableNameCn() {
        return originTableNameCn;
    }

    public void setOriginTableNameCn(String originTableNameCn) {
        this.originTableNameCn = originTableNameCn;
    }

    public String getPrimaryEn() {
        return primaryEn;
    }

    public void setPrimaryEn(String primaryEn) {
        this.primaryEn = primaryEn;
    }

    public String getPrimaryCn() {
        return primaryCn;
    }

    public void setPrimaryCn(String primaryCn) {
        this.primaryCn = primaryCn;
    }

    public Integer getOriginTableMappingAttrId() {
        return originTableMappingAttrId;
    }

    public void setOriginTableMappingAttrId(Integer originTableMappingAttrId) {
        this.originTableMappingAttrId = originTableMappingAttrId;
    }

    public String getOriginTableMappingAttrNameEn() {
        return originTableMappingAttrNameEn;
    }

    public void setOriginTableMappingAttrNameEn(String originTableMappingAttrNameEn) {
        this.originTableMappingAttrNameEn = originTableMappingAttrNameEn;
    }

    public String getOriginTableMappingAttrNameCn() {
        return originTableMappingAttrNameCn;
    }

    public void setOriginTableMappingAttrNameCn(String originTableMappingAttrNameCn) {
        this.originTableMappingAttrNameCn = originTableMappingAttrNameCn;
    }

    public String getOriginTableMappingAttrColumnType() {
        return originTableMappingAttrColumnType;
    }

    public void setOriginTableMappingAttrColumnType(String originTableMappingAttrColumnType) {
        this.originTableMappingAttrColumnType = originTableMappingAttrColumnType;
    }

    public static List<String> getAttrTitle(){
        List<String> title=new ArrayList<>();
        RecommendMappingResult result=new RecommendMappingResult();
        Field[] fields = result.getClass().getDeclaredFields();
        Arrays.sort(fields,new FieldCompare());
        for (Field field : fields) {
            if(field.isAnnotationPresent(FieldMeta.class)){
                title.add(field.getAnnotation(FieldMeta.class).fieldName());
            }
        }

        return title;
    }

    private static class FieldCompare implements Comparator<Field> {
        @Override
        public int compare(Field o1, Field o2) {
            int order1 = o1.isAnnotationPresent(FieldMeta.class) ? o1.getAnnotation(FieldMeta.class).order() : Integer.MAX_VALUE;
            int order2 = o2.isAnnotationPresent(FieldMeta.class) ? o2.getAnnotation(FieldMeta.class).order() : Integer.MAX_VALUE;
            return Integer.compare(order1, order2);
        }
    }





    @Override
    public String toString() {
        return "RecommendMappingResult{" +
                "type=" + type +
                ", TargetTableNameEn='" + TargetTableNameEn + '\'' +
                ", dataSourceInfoId=" + dataSourceInfoId +
                ", originTableId=" + originTableId +
                ", originTableNameEn='" + originTableNameEn + '\'' +
                ", originTableNameCn='" + originTableNameCn + '\'' +
                ", primaryEn='" + primaryEn + '\'' +
                ", primaryCn='" + primaryCn + '\'' +
                ", originTableMappingAttrId='" + originTableMappingAttrId + '\'' +
                ", originTableMappingAttrNameEn='" + originTableMappingAttrNameEn + '\'' +
                ", originTableMappingAttrNameCn='" + originTableMappingAttrNameCn + '\'' +
                ", originTableMappingAttrColumnType='" + originTableMappingAttrColumnType + '\'' +
                '}';
    }
}
