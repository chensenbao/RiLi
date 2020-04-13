package com.example.rili;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.rili.wedget.MonthPicker;

public class MonthPickerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_month;
    private MonthPicker Mp_month;//月份选择器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_picker);
        Mp_month=findViewById(R.id.mp_month);
        findViewById(R.id.btn_0k).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_0k) {
            String desc=String.format("您选择的月份是%d年%d月",Mp_month.getYear(),Mp_month.getMonth()+1);
            tv_month.setText(desc);
        }
    }
}
