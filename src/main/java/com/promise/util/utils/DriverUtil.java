package com.promise.util.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by leiwei on 2020/3/16 14:24
 */
public class DriverUtil {

    private static Connection conn;
    public static Connection getConn(String url,String user,String pass) throws MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        if(conn==null){
            URL[]urls={new URL("file:C:\\Users\\Promise\\Desktop\\mysql-connector-java-5.1.32.jar")};
            URLClassLoader myClassLoader=new URLClassLoader(urls);
            Driver driver=(Driver) myClassLoader.loadClass("com.mysql.jdbc.Driver").newInstance();
            Properties pros=new Properties();
            pros.setProperty("user", user);
            pros.setProperty("password", pass);
            conn=driver.connect(url, pros);
        }
        return conn;
    }
  /*  public static method1 getConn() throws MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

        URL[]urls={new URL("file:com.em")};
        URLClassLoader myClassLoader=new URLClassLoader(urls);
        method1 driver=(method1) myClassLoader.loadClass("com.em.method1").newInstance();

        return driver;
    }*/

    public static void main(String[] args) throws MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        System.out.println(getConn("jdbc:mysql://ws03.mlamp.cn:3306/cona3_lw?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true", "root", "123456"));
        //System.out.println(getConn());
    }

}
