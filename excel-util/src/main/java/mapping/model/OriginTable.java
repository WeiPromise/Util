package mapping.model;

/**
 * Created by leiwei on 2020/8/6 14:11
 */
public class OriginTable {

    private Integer originTableId;

    private Integer dataSourceInfoId;

    private String tbNameEn;

    private String tbNameCn;

    public OriginTable() {
    }

    public OriginTable(Integer originTableId, Integer dataSourceInfoId, String tbNameEn, String tbNameCn) {
        this.originTableId = originTableId;
        this.dataSourceInfoId = dataSourceInfoId;
        this.tbNameEn = tbNameEn;
        this.tbNameCn = tbNameCn;
    }

    public Integer getOriginTableId() {
        return originTableId;
    }

    public void setOriginTableId(Integer originTableId) {
        this.originTableId = originTableId;
    }

    public Integer getDataSourceInfoId() {
        return dataSourceInfoId;
    }

    public void setDataSourceInfoId(Integer dataSourceInfoId) {
        this.dataSourceInfoId = dataSourceInfoId;
    }

    public String getTbNameEn() {
        return tbNameEn;
    }

    public void setTbNameEn(String tbNameEn) {
        this.tbNameEn = tbNameEn;
    }

    public String getTbNameCn() {
        return tbNameCn;
    }

    public void setTbNameCn(String tbNameCn) {
        this.tbNameCn = tbNameCn;
    }

    @Override
    public String toString() {
        return "OriginTable{" +
                "originTableId=" + originTableId +
                ", dataSourceInfoId=" + dataSourceInfoId +
                ", tbNameEn='" + tbNameEn + '\'' +
                ", tbNameCn='" + tbNameCn + '\'' +
                '}';
    }
}
