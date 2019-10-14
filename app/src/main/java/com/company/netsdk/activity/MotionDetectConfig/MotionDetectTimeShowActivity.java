package com.company.netsdk.activity.MotionDetectConfig;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

import com.company.NetSDK.CFG_MOTION_INFO;
import com.company.netsdk.R;
import com.company.netsdk.activity.MotionDetectConfig.view.MotionTimeShowView;
import com.company.netsdk.activity.MotionDetectConfig.view.SetTime;
import com.company.netsdk.activity.MotionDetectConfig.view.Time;
import com.company.netsdk.activity.MotionDetectConfig.view.TimeSection;
import com.company.netsdk.common.ToolKits;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by 32940 on 2018/8/29.
 */
public class MotionDetectTimeShowActivity extends AppCompatActivity{
    private static final int                         TIMESECTION_SET = 105;

    private MotionTimeShowView                          motionTimeShowView;    // 布撤防时间段显示面板

    private CFG_MOTION_INFO                             motion_info;            // 动检配置信息

    private ArrayList<String>                           mTimeList;
    private LinkedHashMap<String, ArrayList<SetTime>>   mTimeMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motion_detect_time_show);

        Toolbar toolbar = (Toolbar) findViewById(R.id.motion_detect_time_show_toolbar);
        toolbar.setTitle(this.getResources().getString(R.string.time_section));  // 在setSupportActionBar之前调用，否则无效；或者在onResume里调用
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initUIView();
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.set_save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.set_btn) {    // 设置相关布撤防时间
            Intent intent = new Intent(this, MotionDetectTimeSetActivity.class);

            intent.putExtra("motion_timesection", motion_info);
            startActivityForResult(intent, TIMESECTION_SET);

            return true;
        } else if(id == R.id.save_btn) {   // 将设置的布撤防时间段信息返回
            Intent intent = new Intent();

            intent.putExtra("motion_timesection", motion_info);
            setResult(RESULT_OK, intent);
            MotionDetectTimeShowActivity.this.finish();

            return true;
        }else if(id == android.R.id.home){ // 返回
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TIMESECTION_SET && resultCode == RESULT_OK)  // 布撤防时间段返回, 并刷新当前显示
        {
            motion_info = (CFG_MOTION_INFO)data.getSerializableExtra("motion_timesection");
            initData();
        }
    }

    /**
     * 初始化控件
     */
    private void initUIView() {
        // 初始化控件
        motionTimeShowView = (MotionTimeShowView)findViewById(R.id.motion_detect_time_show_view);

        // 获取动检配置信息
        motion_info = (CFG_MOTION_INFO)getIntent().getSerializableExtra("motion_timesection");
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mTimeList = new ArrayList<String>();
        TimeSection stuTimeSect[][] = new TimeSection[TimeSection.DAYS_OF_WEEK][TimeSection.MAX_COUNT_OF_TS];

        for(int i =0; i< TimeSection.DAYS_OF_WEEK; i++)
        {
            for(int j =0; j< TimeSection.MAX_COUNT_OF_TS;j++)
            {
                stuTimeSect[i][j] = new TimeSection();
                stuTimeSect[i][j].nEnable = motion_info.stuTimeSection[i][j].dwRecordMask;
                stuTimeSect[i][j].nBeginHour = motion_info.stuTimeSection[i][j].nBeginHour;
                stuTimeSect[i][j].nBeginMinute = motion_info.stuTimeSection[i][j].nBeginMin;
                stuTimeSect[i][j].nBeginSecond = motion_info.stuTimeSection[i][j].nBeginSec;
                stuTimeSect[i][j].nEndHour = motion_info.stuTimeSection[i][j].nEndHour;
                stuTimeSect[i][j].nEndMinute = motion_info.stuTimeSection[i][j].nEndMin;
                stuTimeSect[i][j].nEndSecond = motion_info.stuTimeSection[i][j].nEndSec;

                mTimeList.add(stuTimeSect[i][j].toString());
            }
        }

        if(mTimeList != null) {
            String[] weeks = this.getResources().getStringArray(R.array.week_short);
            mTimeMap = new LinkedHashMap<String, ArrayList<SetTime>>();
            for(int i = 0; i < TimeSection.DAYS_OF_WEEK; i++)
            {
                ArrayList<SetTime> timeList = new ArrayList<SetTime>();
                for(int j = 0; j < TimeSection.MAX_COUNT_OF_TS;j++)
                {
                    String s =  mTimeList.get(i * TimeSection.MAX_COUNT_OF_TS + j);
                    SetTime time = getTimeByStr(s);
                    if(time != null)
                    {
                        timeList.add(time);
                    }
                }
                mTimeMap.put(weeks[i], timeList);
            }

            motionTimeShowView.setTimes(mTimeMap);
        }
    }

    private SetTime getTimeByStr(String timeStr)
    {
        String[] tmp = timeStr.split(" ");
        if(tmp[0].equals("1"))
        {
            String[] time = tmp[1].split("-");
            String[] s = time[0].split(":");
            String[] e = time[1].split(":");
            Time sTime = null;
            Time eTime = null;
            sTime = new Time(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
            eTime = new Time(Integer.parseInt(e[0]), Integer.parseInt(e[1]), Integer.parseInt(e[2]));
            SetTime setTime = new SetTime(sTime, eTime);
            return setTime;
        }
        else
        {
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
