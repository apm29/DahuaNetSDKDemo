package com.company.netsdk.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.PopupWindow;

import com.company.netsdk.R;
import com.company.netsdk.common.ToolKits;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by 36141 on 2017/5/12.
 */
public class DataTimePicker extends PopupWindow{
    DatePicker date;
    OnDateTimeChangeed mListener;
    int mFlag;
    int Year;
    int Month;
    int Day;
    Calendar calendar = Calendar.getInstance(Locale.CHINESE);
    public DataTimePicker(Context context,OnDateTimeChangeed Listener){
        super(((Activity)context).getLayoutInflater().inflate(R.layout.data_time_picker,null),
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        this.mListener = Listener;
        setOutsideTouchable(true);
        date = ((DatePicker)getContentView().findViewById(R.id.data_picker));
        this.setBackgroundDrawable(new ColorDrawable());
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                Year = date.getYear();
                Month = date.getMonth();
                Day = date.getDayOfMonth();
                if (mListener != null)
                    mListener.onDateTimeChangeed(mFlag,Year,Month+1,Day);
            }
        });
        ToolKits.writeLog(String.valueOf(calendar.get(Calendar.MONTH)));
        initDateTime(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initDateTime(int year, int month, int day){

        date.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Year = year;
                    Month = monthOfYear;
                    Day = dayOfMonth;
            }
        });
    }

    public void showAt(int flag,View view){
        if (view == null)
            return;
        this.mFlag = flag;
        showAtLocation(view, Gravity.BOTTOM,0, 0);
    }
    public void release(){
        date = null;
        calendar = null;
        mListener = null;
    }


    public interface OnDateTimeChangeed{
        public void onDateTimeChangeed(int flag,int year,int month,int day);
    }

}
