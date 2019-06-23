package com.promise.util.annotation;

import java.lang.annotation.*;

/**
 * @author leiwei
 * @Title: ExcelColumn
 * @Description: TODO
 * @Date 2019/6/23 12:06
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {

    public String value() default "";
}
