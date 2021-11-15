package com.promise.util.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by leiwei on 2019/10/19 11:15
 */
public class HiveUtil {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://ws02:10000";
    private static String user = "hive";
    private static String password = "";

    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    static {
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createDatabase() throws Exception {
        String sql = "create database octopus_lw";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    public static void showDatabases() throws Exception {
        String sql = "show databases";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

    public static void createTable() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS `octopus_lw`.`person` (`xm` string COMMENT '姓名', `nl` string COMMENT '年龄', `hyzk` string COMMENT '婚姻状况', `zjyxq` string COMMENT '证件有效期', `djdpcs` string COMMENT '登记地派出所', `zjhm` string COMMENT '证件号码', `zjlx` string COMMENT '证件类型', `fzqksm` string COMMENT '犯罪情况说明', `fqsfzh` string COMMENT '父亲身份证号',`fqxm` string COMMENT '父亲姓名',`lusj` string COMMENT '录入时间') COMMENT '人' PARTITIONED BY (`version_partition` int COMMENT '数据版本分区')";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    public static void loadData(String filePath) throws Exception {
        String sql = "load data local inpath '" + filePath + "' into table `octopus_lw`.`person` partition  (version_partition=1)";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    public static void main(String[] args) throws Exception {
        //createDatabase();
        //showDatabases();
        createTable();

        HiveUtil.loadData("/home/meiwen/data1.txt");
    }


}
