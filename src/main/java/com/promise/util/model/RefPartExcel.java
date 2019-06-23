package com.promise.util.model;

import com.promise.util.annotation.ExcelColumn;
import lombok.Data;

/**
 * @author leiwei
 * @Title: RefPartExcel
 * @Description: TODO
 * @Date 2019/6/23 12:15
 */
@Data
public class RefPartExcel {

    @ExcelColumn("原零件号")
    private String partNo;

    @ExcelColumn("原零件名称")
    private String partName;

    @ExcelColumn("参考零件号")
    private String refPartNo;

    @ExcelColumn("参考零件名称")
    private String refPartName;;

    @ExcelColumn("长")
    private String length;

    @ExcelColumn("宽")
    private String width;

}
