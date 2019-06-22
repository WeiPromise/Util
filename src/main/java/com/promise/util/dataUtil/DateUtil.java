package com.promise.util.dataUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by leiwei on 2019/6/22 18:27
 */
public class DateUtil {


    /**
     * @Description:  一般的根据特定格式把日期转换为String
     * @param date:
     * @return java.lang.String
     */
    public static String dateFormat(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * @Description:  获取一年/月/周的第一天到今天的所有日期
     * @param date:
     * @param type: Calendar.DAY_OF_MONTH/
     * @return java.util.List<java.lang.String>
     */
    public static List<String> getMondayOfWeek(String date,int type) throws ParseException {

        List<String> dateList=new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.setTime(sdf.parse(date));

        int days = ca.get(type);

        for(int i=1;i<=days;i++ ){
            ca.add(type,-(days-i));
            Date time = ca.getTime();
            dateList.add(sdf.format(time));
            ca.setTime(sdf.parse(date));
        }
        return dateList;
    }

    /**
     * @Description: 时间比较
     * @param source:
     * @param target:
     * @return int
     */
    public static int compareTime(String source, String target) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Long start = s.parse(source).getTime();
            Long mid = s.parse(target).getTime();
            return start>mid?-1:start.equals(mid) ?0:1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @Description: 时间比较二
     * @param startTime:
     * @param midTime:
     * @param stopTime:
     * @return boolean
     */
    public static boolean compareTime(String startTime, String midTime, String stopTime) {

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Long start = df.parse(startTime).getTime();
            Long mid = s.parse(midTime).getTime();
            Long stop = s.parse(stopTime).getTime();

            return (start<=mid&&stop>=mid);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean isLastDayOfMonth(String inputdate) {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        Date date= null;

        if(inputdate!=null){
            try {
                date = f.parse(inputdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date==null?new Date():date);
        return calendar.get(Calendar.DAY_OF_MONTH) == calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    public static void main(String[] args) throws ParseException {
        List<String> mondayOfWeek = getMondayOfWeek("2019-01-19",Calendar.DAY_OF_YEAR);

        for (String s : mondayOfWeek) {

            System.out.println(s);
        }
    }
    
}
