package com.example.rili.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.rili.R;
import com.example.rili.adapter.CalendarGridAdapter;


public class CalendarFragment extends Fragment {
    private static final String TAG = "CalendarFragment";
    protected View mView;
    protected Context mContext;
    protected int mYear, mMonth;
    protected GridView gv_calendar;//网格视图对象

    //获取碎片的一个实例
    public static CalendarFragment newInstance(int year, int month) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("year", year);
        bundle.putInt("month", month);
        fragment.setArguments(bundle);//包裹加载到碎片
        return fragment;//返回碎片实例
    }

    //创建碎片视图
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();//获取活动页的上下文
        if (getArguments()!=null) {
            mYear=getArguments().getInt("year",1);
            mMonth=getArguments().getInt("month",2000);
        }
        //根据布局生成视图对象
        mView=inflater.inflate(R.layout.fragment_calendar,container,false);
        //获取xml的gv_calendar网格视图
        gv_calendar=mView.findViewById(R.id.gv_calendar);
        //返回碎片的视图对象
        return mView;
        }

        @Override
    public void onResume(){
        super.onResume();
        //构建月历的网格适配器
            CalendarGridAdapter adapter=new CalendarGridAdapter(mContext, mYear,mMonth,1);
            gv_calendar.setAdapter(adapter);

        }
}
