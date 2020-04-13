package com.example.rili.bean;

import com.example.rili.fragment.CalendarFragment;

/**
 * @作者 chen
 * @时间 2020/4/10 22:40
 * @用途 beans
 **/
public class CalendarTransfer {
    public String day_name;
    public int solar_year;//solar阳历
    public int solar_month;
    public int solar_day;
    public int lunar_year;//lunar阴历
    public int lunar_month;
    public int lunar_day;

    public CalendarTransfer() {
        day_name = "";
        solar_year = 0;
        solar_month = 0;
        solar_day = 0;
        lunar_year = 0;
        lunar_month = 0;
        lunar_day = 0;
    }
}
