package com.company.netsdk.activity.MotionDetectConfig;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.company.NetSDK.CFG_MOTION_INFO;
import com.company.netsdk.R;

import java.util.Calendar;

/**
 * Created by 32940 on 2018/8/24.
 */
public class MotionDetectTimeSetActivity extends AppCompatActivity {


    private RelativeLayout                 detectDateLayout;       // 动检日期b布局选择
    private TextView                       detectDateTextView;    // 动检日期

    private RecyclerView                   recyclerView;           // 时间段列表

    private CFG_MOTION_INFO                motion_info;            // 动检配置信息

    private int                            mIndex;                 // 当前日期索引

    private TimePicker                     startTimePicker;       // 开始时间控件
    private TimePicker                     endTimePicker;         // 结束时间控件

    private int                            startHour;             // 开始时间：时
    private int                            startMinute;           // 开始时间：分
    private int                            startSecond;           // 开始时间：秒

    private int                            endHour;               // 结束时间：时
    private int                            endMinute;             // 结束时间：分
    private int                            endSecond;             // 结束时间：秒

    private Resources                       res;

    // 接口里的周几，数组下标0~6，对应 周日~周六
    private String[]                        dates = null;

    private MotionDetectDateAdapter         adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motion_detect_time_set);

        res = this.getResources();

        dates = res.getStringArray(R.array.week);

        Toolbar toolbar = (Toolbar) findViewById(R.id.motion_detect_time_toolbar);
        toolbar.setTitle(res.getString(R.string.time_section));  // 在setSupportActionBar之前调用，否则无效；或者在onResume里调用
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initUIView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.save_btn) {   // 将设置的布撤防时间段信息返回
            Intent intent = new Intent();

            intent.putExtra("motion_timesection", motion_info);
            setResult(RESULT_OK, intent);
            MotionDetectTimeSetActivity.this.finish();

            return true;
        }else if(id == android.R.id.home){ // 返回
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化控件
     */
    private void initUIView() {
        // 获取配置信息
        motion_info = (CFG_MOTION_INFO)getIntent().getSerializableExtra("motion_timesection");

        // 获取当前周几
        Calendar calendar = Calendar.getInstance();
        int mWeek = calendar.get(Calendar.DAY_OF_WEEK);  // mWeek值为 1~7   周日-周六
        mIndex = mWeek - 1;

        detectDateLayout = (RelativeLayout)findViewById(R.id.motion_detect_date_layout);
        detectDateTextView = (TextView)findViewById(R.id.motion_detect_date_text);
        detectDateTextView.setText(dates[mIndex]);

        recyclerView = (RecyclerView)findViewById(R.id.motion_detect_date_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MotionDetectDateAdapter(mIndex);
        recyclerView.setAdapter(adapter);

        detectDateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(MotionDetectTimeSetActivity.this)
                        .setTitle(res.getString(R.string.select_date))
                        .setSingleChoiceItems(dates, mIndex, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                detectDateTextView.setText(dates[which]);
                                mIndex = which;
                                adapter.setIndex(which);
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(res.getString(R.string.cancel), null).show();
            }
        });
    }

    private class MotionDetectDateHolder extends RecyclerView.ViewHolder {
        private ImageView   mEnableImageView;
        private TextView    mIndexTextView;
        private TextView    mDetectTimeTextView;

        public MotionDetectDateHolder(View view) {
            super(view);

            mEnableImageView = (ImageView)view.findViewById(R.id.detect_time_enable);
            mIndexTextView = (TextView)view.findViewById(R.id.detect_time_index);
            mDetectTimeTextView = (TextView)view.findViewById(R.id.detect_time_text_set);
        }
    }

    private class MotionDetectDateAdapter extends RecyclerView.Adapter<MotionDetectDateHolder> {
        private int index;

        public MotionDetectDateAdapter(int index) {
            this.index = index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public MotionDetectDateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.motion_detect_time_text, parent, false);

            return new MotionDetectDateHolder(view);
        }

        @Override
        public void onBindViewHolder(final MotionDetectDateHolder holder, final int position) {
            // 布撤防时间段是二维数组，  第一维代表周几，  第二维 对应每一天的时间段，最多可以设置6个。
            // 周几数组（0~6  对应 周日 -  周六）

            // index 索引代表周几
            // position 代表时间数组下标
            if(motion_info.stuTimeSection[index][position].dwRecordMask == 1) {
                holder.mEnableImageView.setSelected(true);
            } else {
                holder.mEnableImageView.setSelected(false);
            }

            holder.mIndexTextView.setText(String.valueOf(position));

            String time = getTime(motion_info.stuTimeSection[index][position].nBeginHour) + ":" +
                          getTime(motion_info.stuTimeSection[index][position].nBeginMin) + ":" +
                          getTime(motion_info.stuTimeSection[index][position].nBeginSec) + "-" +
                          getTime(motion_info.stuTimeSection[index][position].nEndHour) + ":" +
                          getTime(motion_info.stuTimeSection[index][position].nEndMin) + ":" +
                          getTime(motion_info.stuTimeSection[index][position].nEndSec);

            holder.mDetectTimeTextView.setText(time);

            holder.mEnableImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // dwRecordMask 代表时间使能， 0-false; 1-true
                    if(holder.mEnableImageView.isSelected()) {
                        holder.mEnableImageView.setSelected(false);
                        motion_info.stuTimeSection[index][position].dwRecordMask = 0;
                    } else {
                        holder.mEnableImageView.setSelected(true);
                        motion_info.stuTimeSection[index][position].dwRecordMask = 1;
                    }
                }
            });

            holder.mDetectTimeTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.motion_detect_time_picker, null);

                    startTimePicker = (TimePicker)view.findViewById(R.id.motion_start_time_picker);
                    endTimePicker = (TimePicker)view.findViewById(R.id.motion_end_time_picker);

                    startTimePicker.clearFocus();
                    endTimePicker.clearFocus();

                    startTimePicker.invalidate();
                    endTimePicker.invalidate();

                    startTimePicker.setIs24HourView(true);
                    endTimePicker.setIs24HourView(true);

                    // 初始化开始时间
                    startHour = motion_info.stuTimeSection[index][position].nBeginHour;
                    startMinute = motion_info.stuTimeSection[index][position].nBeginMin;
                    startSecond = motion_info.stuTimeSection[index][position].nBeginSec;
                    if(startHour == 24) {
                        startHour = 23;
                        startMinute = 59;
                        startSecond = 59;
                    }
                    initTimePicker(startTimePicker,startHour, startMinute);

                    // 初始化结束时间
                    endHour = motion_info.stuTimeSection[index][position].nEndHour;
                    endMinute = motion_info.stuTimeSection[index][position].nEndMin;
                    endSecond = motion_info.stuTimeSection[index][position].nEndSec;
                    if(endHour == 24) {
                        endHour = 23;
                        endMinute = 59;
                        endSecond = 59;
                    }
                    initTimePicker(endTimePicker, endHour, endMinute);

                    AlertDialog dialog = new AlertDialog.Builder(MotionDetectTimeSetActivity.this)
                            .setTitle(res.getString(R.string.time_section))
                            .setView(view)
                            .setPositiveButton(res.getString(R.string.set), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    motion_info.stuTimeSection[index][position].nBeginHour = startHour;
                                    motion_info.stuTimeSection[index][position].nBeginMin = startMinute;
                                    motion_info.stuTimeSection[index][position].nBeginSec = startSecond;

                                    motion_info.stuTimeSection[index][position].nEndHour = endHour;
                                    motion_info.stuTimeSection[index][position].nEndMin = endMinute;
                                    motion_info.stuTimeSection[index][position].nEndSec = endSecond;

                                    String time = getTime(startHour) + ":" +
                                                  getTime(startMinute) + ":" +
                                                  getTime(startSecond) + "-" +
                                                  getTime(endHour) + ":" +
                                                  getTime(endMinute) + ":" +
                                                  getTime(endSecond);

                                    holder.mDetectTimeTextView.setText(time);

                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton(res.getString(R.string.cancel), null).create();
                    dialog.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return 6;
        }
    }

    ///时分初始化监听
    private void initTimePicker(TimePicker timePicker, int dwhour, int dwminute) {
        // 初始化时间控件
        timePicker.setCurrentHour(dwhour);
        timePicker.setCurrentMinute(dwminute);

        // 时间控件监听
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(view == startTimePicker) {
                    startHour = hourOfDay;
                    startMinute = minute;
                } else {
                    endHour = hourOfDay;
                    endMinute = minute;
                }
            }
        });
    }

    private String getTime(int time) {
        String times = String.valueOf(time);
        if(time >= 0 && time < 10) {
            times = "0" + String.valueOf(time);
        }

        return times;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
