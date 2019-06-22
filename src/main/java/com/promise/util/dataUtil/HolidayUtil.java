package com.promise.util.dataUtil;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leiwei on 2019/6/21 17:20
 */
public class HolidayUtil {

    // 法律规定的放假日期
    private List<String> lawHolidays = Arrays.asList("2019-01-01","2019-01-01","2019-02-04","2019-02-05","2019-10-07");
    // 由于放假需要额外工作的周末
    private List<String> extraWorkdays =Arrays.asList("2018-09-29","2018-10-12");


    public boolean isLawHoliday(Date date) throws Exception {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.isMatchDateFormat(df.format(date));
        if (lawHolidays.contains(df.format(date))) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是周末
     *
     * @param calendar
     * @return
     * @throws
     */
    public boolean isWeekends(String calendar) throws Exception {
        this.isMatchDateFormat(calendar);
        // 先将字符串类型的日期转换为Calendar类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(calendar);
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);

       /* System.out.println(ca.get(Calendar.DAY_OF_MONTH));
        System.out.println(ca.get(Calendar.WEEK_OF_MONTH));
        System.out.println(ca.get(Calendar.WEEK_OF_YEAR));
        System.out.println(ca.get(Calendar.DAY_OF_WEEK));
        System.out.println(ca.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println(ca.get(Calendar.DAY_OF_YEAR));
        System.out.println(ca.get(Calendar.MONTH));*/
        if (ca.get(Calendar.DAY_OF_WEEK) == 1
                || ca.get(Calendar.DAY_OF_WEEK) == 7) {
            return true;
        }
        return false;
    }


    /**
     * 判断是否是需要额外补班的周末
     *
     * @param calendar
     * @return
     * @throws Exception
     */
    public boolean isExtraWorkday(String calendar) throws Exception {
        this.isMatchDateFormat(calendar);
        if (extraWorkdays.contains(calendar)) {
            return true;
        }
        return false;
    }


    /**
     * 判断是否是休息日（包含法定节假日和不需要补班的周末）
     *
     * @param
     * @return
     * @throws Exception
     */
    public boolean isHoliday(Date date) throws Exception {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String calendar = df.format(date);
        this.isMatchDateFormat(calendar);
        // 首先法定节假日必定是休息日
        if (this.isLawHoliday(date)) {
            return true;
        }
        // 排除法定节假日外的非周末必定是工作日
        if (!this.isWeekends(calendar)) {
            return false;
        }
        // 所有周末中只有非补班的才是休息日
        if (this.isExtraWorkday(calendar)) {
            return false;
        }
        return true;
    }


    /**
     * 使用正则表达式匹配日期格式
     *
     * @throws Exception
     */
    private void isMatchDateFormat(String calendar) throws Exception {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(calendar);
        boolean flag = matcher.matches();
        if (!flag) {
            throw new Exception("输入日期格式不正确，应该为2017-12-19");
        }
    }



}
