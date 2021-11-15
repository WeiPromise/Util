package mapping.model;


import mapping.annotations.MethodMeta;
import mapping.constant.ElementType;

/**
 * Created by leiwei on 2020/8/5 19:15
 */
public class TargetTable extends BaseModel {

    private ElementType type;

    private String nameEn;
    private String nameCn;

    private String subjet;
    private String object;

    //主体
    private String subjectNameEn;
    private String subjectNameCn;
    //客体
    private String objectNameEn;
    private String objectNameCn;

    //时间
    private String occurrence;

    public TargetTable() {
    }

    public TargetTable(ElementType type, String nameEn, String nameCn, String subjet, String object, String subjectNameEn, String subjectNameCn, String objectNameEn, String objectNameCn, String occurrence) {
        this.type = type;
        this.nameEn = nameEn;
        this.nameCn = nameCn;
        this.subjet = subjet;
        this.object = object;
        this.subjectNameEn = subjectNameEn;
        this.subjectNameCn = subjectNameCn;
        this.objectNameEn = objectNameEn;
        this.objectNameCn = objectNameCn;
        this.occurrence = occurrence;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    public String getNameEn() {
        return nameEn;
    }

    @MethodMeta(value = "事件/关系英文名",index =2 )
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameCn() {
        return nameCn;
    }

    @MethodMeta(value = "事件/关系中文名",index =3 )
    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getSubjet() {
        return subjet;
    }

    @MethodMeta(value = "主体字段名",index =4 )
    public void setSubjet(String subjet) {
        this.subjet = subjet;
    }

    public String getObject() {
        return object;
    }

    @MethodMeta(value = "客体字段名",index =5 )
    public void setObject(String object) {
        this.object = object;
    }

    public String getSubjectNameEn() {
        return subjectNameEn;
    }

    @MethodMeta(value = "主体英文名",index =0 )
    public void setSubjectNameEn(String subjectNameEn) {
        this.subjectNameEn = subjectNameEn;
    }

    public String getSubjectNameCn() {
        return subjectNameCn;
    }

    @MethodMeta(value = "主体中文名 ",index =1 )
    public void setSubjectNameCn(String subjectNameCn) {
        this.subjectNameCn = subjectNameCn;
    }

    public String getObjectNameEn() {
        return objectNameEn;
    }

    @MethodMeta(value = "客体英文名",index =6 )
    public void setObjectNameEn(String objectNameEn) {
        this.objectNameEn = objectNameEn;
    }

    public String getObjectNameCn() {
        return objectNameCn;
    }

    @MethodMeta(value = "客体中文名",index =7 )
    public void setObjectNameCn(String objectNameCn) {
        this.objectNameCn = objectNameCn;
    }

    public String getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    @Override
    public String toString() {
        return "TargetTable{" +
                "type=" + type +
                ", nameEn='" + nameEn + '\'' +
                ", nameCn='" + nameCn + '\'' +
                ", subjet='" + subjet + '\'' +
                ", object='" + object + '\'' +
                ", subjectNameEn='" + subjectNameEn + '\'' +
                ", subjectNameCn='" + subjectNameCn + '\'' +
                ", objectNameEn='" + objectNameEn + '\'' +
                ", objectNameCn='" + objectNameCn + '\'' +
                ", occurrence='" + occurrence + '\'' +
                '}';
    }
}
