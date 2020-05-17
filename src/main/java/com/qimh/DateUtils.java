package com.qimh;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author qiminhui
 * 日期工具类
 */
public class DateUtils {
    private static DateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

    /**
     * 获取当前日期格式为：2020-05-17:"yyyy-MM-dd"
     * @return
     */
    public static String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sf.format(date);
        System.out.println("today:" + today);
        return today;
    }


    /**
     * 返回指定日期的前多少天
     * @param days
     * @return
     */
    public static String getTodayBeforeDays(String date,int days){
        Calendar c = Calendar.getInstance();
        Date tmpDate = null;
        try {
            tmpDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(tmpDate);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE,day - days);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        System.out.println("dayBefore:" + dayBefore);
        return dayBefore;

    }

    /**
     * 获取给定时间对应的毫秒数
     * @param  "HH:mm:ss"
     * @return
     */
    public static long getTimeMillis(String time) {
        try {

            Date currentDate = dateFormat.parse(dayFormat.format(new Date()) + " " +time);
            return currentDate.getTime() ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }




    public static void main(String[] args){
//        System.out.println(getCurrentTime());
//        getTodayBeforeDays("2020-05-05",2);
        getTodayBeforeDays(getCurrentTime(),2);

//        System.out.println(getTimeMillis("16:12:00")-getTimeMillis("16:11:00"));
    }

}
