package mapping.model;

import java.util.List;

/**
 * Created by leiwei on 2020/8/6 15:38
 */
public class OriginAttr {

    private Integer originAttrId;

    private Integer originTableId;

    private String attrNameEn;

    private String attrNameCn;

    private String columnType;

    private List<String> samples;


    public OriginAttr() {
    }

    public OriginAttr(Integer originAttrId, Integer originTableId, String attrNameEn,String attrNameCn, String columnType) {
        this.originAttrId = originAttrId;
        this.originTableId = originTableId;
        this.attrNameEn = attrNameEn;
        this.attrNameCn = attrNameCn;
        this.columnType = columnType;

    }

    public Integer getOriginAttrId() {
        return originAttrId;
    }

    public void setOriginAttrId(Integer originAttrId) {
        this.originAttrId = originAttrId;
    }

    public Integer getOriginTableId() {
        return originTableId;
    }

    public void setOriginTableId(Integer originTableId) {
        this.originTableId = originTableId;
    }

    public String getAttrNameEn() {
        return attrNameEn;
    }

    public void setAttrNameEn(String attrNameEn) {
        this.attrNameEn = attrNameEn;
    }

    public String getAttrNameCn() {
        return attrNameCn;
    }

    public void setAttrNameCn(String attrNameCn) {
        this.attrNameCn = attrNameCn;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public List<String> getSamples() {
        return samples;
    }

    public void setSamples(List<String> samples) {
        this.samples = samples;
    }

    @Override
    public String toString() {
        return "OriginAttr{" +
                "originAttrId=" + originAttrId +
                ", originTableId=" + originTableId +
                ", attrNameEn='" + attrNameEn + '\'' +
                ", attrNameCn='" + attrNameCn + '\'' +
                ", columnType='" + columnType + '\'' +
                ", samples=" + samples +
                '}';
    }
}
