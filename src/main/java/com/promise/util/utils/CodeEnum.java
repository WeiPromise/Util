package com.promise.util.utils;

/**
 * Created by leiwei on 2019/7/2 15:01
 */
public enum CodeEnum {

    DCQ(0, 110101, "东城区"),
    XCQ(1, 110102, "西城区"),
    CYQ(2, 110105, "朝阳区"),
    FTQ(3, 110101, "丰台区"),
    SJSQ(4, 110107, "石景山区"),
    HDQ(5, 110108, "海淀区"),
    MTGQ(6, 110109, "门头沟区"),
    FSQ(7, 110111, "房山区"),
    TZQ(8, 110112, "通州区"),
    SYQ(9, 110113, "顺义区"),
    CPQ(10, 110114, "昌平区"),
    DXQ(11, 110115, "大兴区"),
    HRQ(12, 110116, "怀柔区"),
    PGQ(13, 110117, "平谷区"),
    MYQ(14, 110118, "密云区"),
    YQQ(15, 110119, "延庆区");

    private Integer key;

    private Integer code;

    private String name;

    CodeEnum(Integer key, Integer code, String name) {
        this.key = key;
        this.code = code;
        this.name = name;
    }



    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CodeEnum get(Integer id){
        for (CodeEnum value : values()) {
            if(value.key==id){
                return value;
            }
        }
        return DCQ;
    }

}







