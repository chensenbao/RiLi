package com.example.rili.wedget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

/**
 * @作者 chen
 * @时间 2020/4/10 14:34
 * @用途 日期选择器派生的月份选择器
 **/
public class MonthPicker extends DatePicker {

    public MonthPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取年月日的下拉列表项
        ViewGroup viewGroup=((ViewGroup)((ViewGroup)getChildAt(0)).getChildAt(0));
        if(viewGroup.getChildCount()==3){
            //隐藏第三个控件，下同
            viewGroup.getChildAt(2).setVisibility(View.GONE);
        } else if (viewGroup.getChildCount()==5) {
            viewGroup.getChildAt(3).setVisibility(View.GONE);
            viewGroup.getChildAt(4).setVisibility(View.GONE);

        }
    }
}
