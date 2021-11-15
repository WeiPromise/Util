package com.promise.util;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.File;
import java.io.IOException;

;

/**
 * Created by leiwei on 2019/10/17 17:53
 */
public class HbaseTest {

    private static Configuration conf;

    private static Connection conn=null;

    static {
        conf= HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","ws02:2181,ws03:2181,ws04:2181");
        try {
            conn= ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //创建File对象
        File file = new File("D:\\Downloads");
        //获取该目录下的所有文件
       /* String[] files = file.list();

        for (String f : files){
            System.out.println(f);
        }*/

        //listFiles是获取该目录下所有文件和目录的绝对路径
        File[] fs = file.listFiles();
        for (File f : fs){
            System.out.println(f);
        }
    }



    public static Boolean check(Object obj) {
        String phoneNo = obj.toString();
        //1. 去除特殊字符
        phoneNo = phoneNo.replaceAll("[\\+\\-\\(\\)（）]", "");
        //2. 去除空白字符
        phoneNo = phoneNo.replaceAll("\\s+", "");
        //3. 去除多个前缀0
        phoneNo = phoneNo.replaceAll("^0+", "");
        //4. 是否是数字
        System.out.println(phoneNo);
        return phoneNo.matches("^\\d+$");
    }




}
