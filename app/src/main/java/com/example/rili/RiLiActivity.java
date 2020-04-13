package com.example.rili;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rili.adapter.CalendarPagerAdapter;
import com.example.rili.util.DateUtil;
import com.example.rili.wedget.MonthPicker;


public class RiLiActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RiLiActivity";
    private LinearLayout ll_calendar_main;//主体线性视图
    private LinearLayout ll_month_select;//月份视图
    private MonthPicker mp_month;//月份选择器
    private ViewPager vp_calendar;//翻页视图
    private TextView tv_calendar;//日历头，可选择年份
    private boolean isShowSelect = false;//是否显示月份选择器
    private int mSelectedYear = 2000;//默认年份

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ri_li);
        ll_calendar_main = findViewById(R.id.ll_calendar_main);
        ll_month_select = findViewById(R.id.ll_month_select);
        mp_month = findViewById(R.id.mp_month);
        //翻页标题栏
        findViewById(R.id.btn_0k).setOnClickListener(this);
        PagerTabStrip pts_calendar = findViewById(R.id.pts_calendar);
        pts_calendar.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        pts_calendar.setTextColor(Color.BLACK);
        //翻页视图
        vp_calendar = findViewById(R.id.vp_calendar);
        tv_calendar = findViewById(R.id.tv_calendar);
        tv_calendar.setOnClickListener(this);
        //默认显示当前年月
        showCalendar(DateUtil.getNowYear(), DateUtil.getNowMonth());
    }

    private void showCalendar(int year, int month) {

        //重构年历
        if (year != mSelectedYear) {
            tv_calendar.setText(year + "年");
            //指定年份的年历翻页适配器
            CalendarPagerAdapter adapter = new CalendarPagerAdapter(getSupportFragmentManager(), year);
            vp_calendar.setAdapter(adapter);//绑定适配器
            mSelectedYear = year;
        }
        vp_calendar.setCurrentItem(month - 1);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.tv_calendar) {
            resetPage();
        } else if (view.getId()==R.id.btn_0k) {
            showCalendar(mp_month.getYear(),mp_month.getMonth());
            resetPage();
        }

    }

    //刷新页面
    private void resetPage() {
        isShowSelect=!isShowSelect;
        ll_calendar_main.setVisibility(isShowSelect? View.GONE: View.VISIBLE);
        ll_month_select.setVisibility(isShowSelect? View.VISIBLE: View.GONE);
    }

}



















