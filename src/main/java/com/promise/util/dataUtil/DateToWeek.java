package com.promise.util.dataUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author leiwei
 * @Title: DateTools
 * @Description: TODO
 * @Date 2018/6/13 14:14
 */
public class DateToWeek {

    private static final String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
    /**
     * @Description:  日期转星期
     * @param datetime:
     * @return java.lang.String
     */
    public static String dateToWeek(String datetime) {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance(); // 获得一个日历

        try {
            if (datetime == null) {
                cal.setTime(new Date());
            } else {
                cal.setTime(f.parse(datetime));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 指示一个星期中的某天。
        return weekDays[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }


}
