package mapping.model;

import mapping.annotations.MethodMeta;

/**
 * Created by leiwei on 2020/8/5 18:53
 */
public class TargetTableEntity extends BaseModel {


    private String nameEn;
    private String nameCn;
    private String primaryCn;
    private String primaryEn;
    private String SampleOne;
    private String regular;

    public TargetTableEntity() {
    }

    public TargetTableEntity(String nameEn, String nameCn, String primaryCn, String primaryEn, String sampleOne, String regular) {
        this.nameEn = nameEn;
        this.nameCn = nameCn;
        this.primaryCn = primaryCn;
        this.primaryEn = primaryEn;
        SampleOne = sampleOne;
        this.regular = regular;
    }

    public String getNameEn() {
        return nameEn;
    }

    @MethodMeta(value = "实体英文名",index =0 )
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameCn() {
        return nameCn;
    }

    @MethodMeta(value = "实体中文名 ",index = 1)
    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getPrimaryCn() {
        return primaryCn;
    }

    @MethodMeta(value = "主属性中文名",index =2 )
    public void setPrimaryCn(String primaryCn) {
        this.primaryCn = primaryCn;
    }

    public String getPrimaryEn() {
        return primaryEn;
    }

    @MethodMeta(value = "主属性英文名",index =3 )
    public void setPrimaryEn(String primaryEn) {
        this.primaryEn = primaryEn;
    }

    public String getSampleOne() {
        return SampleOne;
    }

    @MethodMeta(value = "主属性样例数据1",index = 4)
    public void setSampleOne(String sampleOne) {
        SampleOne = sampleOne;
    }

    public String getRegular() {
        return regular;
    }

    @MethodMeta(value = "正则",index = 6)
    public void setRegular(String regular) {
        this.regular = regular;
    }

    @Override
    public String toString() {
        return "TargetTableEntity{" +
                "nameEn='" + nameEn + '\'' +
                ", nameCn='" + nameCn + '\'' +
                ", primaryCn='" + primaryCn + '\'' +
                ", primaryEn='" + primaryEn + '\'' +
                ", SampleOne='" + SampleOne + '\'' +
                ", regular='" + regular + '\'' +
                '}';
    }
}
