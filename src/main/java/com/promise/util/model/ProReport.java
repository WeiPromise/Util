package com.promise.util.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by leiwei on 2019/6/12 11:22
 */
@Data
public class ProReport {

    //项目
    private String  project;

    //月总计
    private Double  mtimes;

    //周总计
    private Double  wtimes;

    private Map<String,Double> buMap;

}
