package com.promise.util.dataUtil;


import org.apache.commons.lang3.RandomUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by leiwei on 2019/10/19 14:26
 */
public class RandomDateUtil {

    /**
     * 取范围日期的随机日期时间,不含边界
     * @param startDay
     * @param endDay
     * @return
     */
    public static LocalDateTime randomLocalDateTime(int startDay,int endDay){

        int plusMinus = 1;
        if(startDay < 0 && endDay > 0){
            plusMinus = Math.random()>0.5?1:-1;
            if(plusMinus>0){
                startDay = 0;
            }else{
                endDay = Math.abs(startDay);
                startDay = 0;
            }
        }else if(startDay < 0 && endDay < 0){
            plusMinus = -1;

            //两个数交换
            startDay = startDay + endDay;
            endDay  = startDay - endDay;
            startDay = startDay -endDay;

            //取绝对值
            startDay = Math.abs(startDay);
            endDay = Math.abs(endDay);

        }

        LocalDate day = LocalDate.now().plusDays(plusMinus * RandomUtils.nextInt(startDay,endDay));
        int hour = RandomUtils.nextInt(1,24);
        int minute = RandomUtils.nextInt(0,60);
        int second = RandomUtils.nextInt(0,60);
        LocalTime time = LocalTime.of(hour, minute, second);
        return LocalDateTime.of(day, time);
    }

    /**
     * 取范围日期的随机日期时间,不含边界
     * @param startDay
     * @param endDay
     * @return
     */
    public Date randomDateTime(int startDay, int endDay){
        LocalDateTime ldt = randomLocalDateTime(startDay,endDay);
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            System.out.println(randomLocalDateTime(-1000,1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        }
    }


}
