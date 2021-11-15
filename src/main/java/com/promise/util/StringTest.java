package com.promise.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leiwei on 2020/6/23 17:06
 */
public class StringTest {

    private static final Pattern PATTERN = Pattern.compile("^[a-z]+(_[a-z0-9]+){0,}");

    public static void main(String[] args) {
        String directory = "sdkjfjAAsd_asjd_jsad";
       /* org.springframework.util.StringUtils 切分时会去空格以及过滤掉空的
        String[] strings = StringUtils.tokenizeToStringArray(directory, "/,;", true, true);
        for (String string : strings) {
            System.out.println(string);
        }*/

        //org.apache.commons.lang3.StringUtils 切割不会去重空格和空字符串
        Matcher matcher = PATTERN.matcher(directory);


        if(matcher.matches()){
            System.out.println(directory);
        }


    }
}



