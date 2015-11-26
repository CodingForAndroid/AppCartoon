package com.jorge.appcartoon.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author：Jorge on 2015/10/12 13:16
 */
public class TimeUtils {
    /**
     * convert time str
     *
     * @param time
     * @return
     */
    public static String convertTime(int time) {

        time /= 1000;
        int minute = time / 60;
        int second = time % 60;
        minute %= 60;
        return String.format("%02d:%02d", minute, second);
    }


    /**
     * 获取当前时间 格式 4:00:19  时分秒
     * @return
     */
    public static String currentTime(){
        Date now = new Date();
        DateFormat dateFormat = DateFormat.getTimeInstance();

      return    dateFormat.format(now);
    }

    /**
     * 返回当前时间 格式 16:55
     * @return
     */
    public static String currentTime12(){

        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);//获取年份
        int month=ca.get(Calendar.MONTH);//获取月份
        int day=ca.get(Calendar.DATE);//获取日
        int minute=ca.get(Calendar.MINUTE);//分
        int hour=ca.get(Calendar.HOUR);//小时
        int second=ca.get(Calendar.SECOND);//秒
        int WeekOfYear = ca.get(Calendar.DAY_OF_WEEK);
        return  hour+":"+minute;
    }

    /**
     * 时分  4:10
     * @return
     */
    public  static  String currentTime24(){
        long time=System.currentTimeMillis();
        final Calendar mCalendar=Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        int mHour=mCalendar.get(Calendar.HOUR);
        int mMinuts=mCalendar.get(Calendar.MINUTE);

        LogUtils.e(mHour+":"+mMinuts);
        return  mHour+":"+mMinuts;
    }

}
