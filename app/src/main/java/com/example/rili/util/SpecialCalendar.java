package com.example.rili.util;

import android.provider.CalendarContract;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * @作者 chen
 * @时间 2020/4/11 7:44
 * @用途 判断是否闰年
 **/
public class SpecialCalendar {
    private static final String TAG = "SpecialCalendar";

    //判断是否为闰年
    public static boolean isLeapYear(int year) {
        if (year % 100 == 0 && year / 400 == 0) {
            return true;
        } else if (year % 100 != 0 && year / 4 == 0) {
            return true;
        }
        return false;
    }

    //得到某月的天数
    public static int getDaysOfMonth(boolean isLeapYear, int month) {
        int daysOfMonth = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysOfMonth = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysOfMonth = 30;
                break;
            case 2:
                if (isLeapYear) {
                    daysOfMonth = 29;
                } else {
                    daysOfMonth = 28;
                }
        }
        return daysOfMonth;
    }

    //某月第一天是周几
    public static int getWeekDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();//获取实例
        calendar.set(year, month - 1, 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        Log.d(TAG, "某月第一天周几" + dayOfWeek);
        if (dayOfWeek == 0) {
            dayOfWeek = 7;//每周第一天是周日
        }
        return dayOfWeek;
    }

    //返回一年几周
    public static int getToDAyWeek() {
        int week = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        week = calendar.get(Calendar.WEEK_OF_YEAR);
        Log.d(TAG, "一年几周" + week);
        return week;
    }

}
