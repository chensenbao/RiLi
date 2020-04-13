package com.example.rili.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.rili.fragment.CalendarFragment;
import com.example.rili.util.Constant;

/**
 * @作者 chen
 * @时间 2020/4/10 15:18
 * @用途 年份月份适配器
 **/
public class CalendarPagerAdapter extends FragmentStatePagerAdapter {
    private int mYear;//当前日历所处年份

    //碎片页适配器的构造函数，传入碎片管理器与年份
    public CalendarPagerAdapter(FragmentManager fm, int mYear) {
        super(fm);
        this.mYear = mYear;
    }
    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public CalendarFragment getItem(int position) {
        return CalendarFragment.newInstance(mYear,position+1);
    }

    public CharSequence getPageTitle(int position) {
        return new String(Constant.xuhaoArray[position+1]+"月");
    }
}
