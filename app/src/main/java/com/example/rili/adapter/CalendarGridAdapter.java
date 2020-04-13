package com.example.rili.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rili.R;
import com.example.rili.bean.CalendarTransfer;
import com.example.rili.util.DateUtil;
import com.example.rili.util.LunarCalendar;
import com.example.rili.util.SpecialCalendar;

import java.util.ArrayList;

/**
 * @作者 chen
 * @时间 2020/4/10 22:29
 * @用途 网格视图适配器
 **/
public class CalendarGridAdapter extends BaseAdapter {
    private static final String TAG = "CalendarGridAdapter";
    private Context mContext;
    private boolean isLeapYear = false;
    private int dayOfMonth = 0;//某月的天数
    private int dayOfWeek = 0;//某天是星期几
    private int lastDayOfMonth = 0;//上个月的总天数
    private String[] dayNumber = new String[49];//gridView的数据存放
    private ArrayList<CalendarTransfer> transArray = new ArrayList<CalendarTransfer>();
    private static String[] weekTitle = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    private int currentDay = -1;//标记当天
    private LunarCalendar lc;

    public CalendarGridAdapter(Context context, int year, int month, int day) {
        mContext = context;
        lc = new LunarCalendar();
        Log.d(TAG, "年月日：" + year + month + day);
        isLeapYear = SpecialCalendar.isLeapYear(year);//是否闰年
        //获取某月天数
        dayOfMonth = SpecialCalendar.getDaysOfMonth(isLeapYear, month);
        dayOfWeek = SpecialCalendar.getWeekDayOfMonth(year, month);
        //上个月总天数
        lastDayOfMonth = SpecialCalendar.getDaysOfMonth(isLeapYear, month - 1);
        getWeekDays(year, month);
    }

    @Override
    public int getCount() {
        return dayNumber.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_calendar, null);
            holder.tv_day = view.findViewById(R.id.tv_day);
            view.setTag(holder);
        } else {
            holder= (ViewHolder) view.getTag();
        }
        String day=dayNumber[i].split("\\.")[0];
        String festival=dayNumber[i].split("\\.")[1];
        String itemText=day;
        if (i>=7) {
        itemText=itemText+"\n"+festival;
        }
        holder.tv_day.setText(itemText);
        holder.tv_day.setTextColor(Color.GRAY);
        //设置各种周一到周日标题颜色
        if (i < 7) {
            holder.tv_day.setTextColor(Color.BLACK);
            holder.tv_day.setBackgroundColor(Color.LTGRAY);
        } else if (currentDay == i) {
            holder.tv_day.setBackgroundColor(Color.GREEN);
        }else {
            holder.tv_day.setBackgroundColor(Color.WHITE);
        }
        if (i < dayOfMonth + dayOfWeek + 7 - 1 && i > dayOfWeek + 7 - 1) {
            //当前月信息显示
            if (DateUtil.isHoliday(festival)) {
                holder.tv_day.setTextColor(Color.BLUE);
            } else if ((i + 1) % 7 == 6 || (i + 1) % 7 == 0) {
                holder.tv_day.setTextColor(Color.RED);
            } else {
                holder.tv_day.setTextColor(Color.BLACK);
            }
        }
        return view;
    }

    //存储一个月的天数
    public void getWeekDays(int year, int month) {
        int nextMonthDay=1;
        String lunarDay="";
        for (int i = 0; i < dayNumber.length; i++) {
            CalendarTransfer trans=new CalendarTransfer();
            int weekday=(i-7)%7+1;
            if (i < 7) {
                dayNumber[i]=weekTitle[i]+"."+" ";
            } else if (i < dayOfWeek + 7 - 1) {
                int temp=lastDayOfMonth-dayOfWeek+1-7+1;
                trans=lc.getSubDate(trans,year, month-1,temp+i,weekday,false);
                lunarDay=trans.day_name;
                dayNumber[i]=(temp+i)+"."+lunarDay;
            } else if (i < dayOfMonth + dayOfWeek + 7 - 1) {
                int day = i - dayOfWeek + 1 - 7 + 1;
                trans = lc.getSubDate(trans, year, month, day, weekday, false);
                lunarDay = trans.day_name;
                dayNumber[i] = day + "." + lunarDay;
                // 对于当前月才去标记当前日期
                if (year == DateUtil.getNowYear() && month == DateUtil.getNowMonth() && day == DateUtil.getNowDay()) {
                    currentDay = i;
                }
            } else { // 下一个月
                int next_month = month + 1;
                int next_year = year;
                if (next_month >= 13) {
                    next_month = 1;
                    next_year++;
                }
                trans = lc.getSubDate(trans, next_year, next_month, nextMonthDay, weekday, false);
                lunarDay = trans.day_name;
                dayNumber[i] = nextMonthDay + "." + lunarDay;
                nextMonthDay++;
            }
            transArray.add(trans);
        }
    }
    public CalendarTransfer getCalendarList(int pos) {
        return transArray.get(pos);
    }

    public final class ViewHolder{
        public TextView tv_day;
    }

}
