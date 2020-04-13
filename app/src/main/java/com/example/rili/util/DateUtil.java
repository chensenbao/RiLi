package com.example.rili.util;

import java.util.Calendar;

/**
 * @作者 chen
 * @时间 2020/4/10 15:07
 * @用途 日期
 **/
public class DateUtil {
    public static int getNowYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int getNowMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }
    public static int getNowDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    public static boolean isHoliday(String text) {
        boolean result = true;
        if ((text.length() == 2 && (text.indexOf("月") > 0 || text.contains("初") || text.contains("十")
                || text.contains("廿") || text.contains("卅")))
                || (text.length() == 3 && text.indexOf("月") > 0)) {
            result = false;
        }
        return result;

    }
}
