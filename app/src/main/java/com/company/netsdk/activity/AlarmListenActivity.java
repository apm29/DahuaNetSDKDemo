package com.company.netsdk.activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.company.NetSDK.CB_fMessageCallBack;
import com.company.NetSDK.FinalVar;
import com.company.netsdk.R;
import com.company.netsdk.common.ToolKits;
import com.company.netsdk.module.AlarmListenModule;
import com.company.netsdk.module.AlarmListenModule.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class AlarmListenActivity extends AppCompatActivity implements View.OnClickListener,CB_fMessageCallBack{
    private final String TAG = "AlarmListenActivity";
    private static final int MAX_SHOW_NUMS = 10;
    List<AlarmEventInfo> data = new ArrayList<>();    // 报警事件信息数据
    LinkedList<String> mAlarmList = new LinkedList<>();    // 报警事件显示列表
    private long mIndex = 0;
    ArrayAdapter<String> adapter;
    ListView mAlarmListView;
    AlarmListenModule mAlarmModule;
    boolean isListening = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_listen);
        setTitle(R.string.activity_function_list_alarm_listen);
        setupView();
        mAlarmModule = new AlarmListenModule(this);
        mAlarmModule.setCallback(this);

        // 添加返回键
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void setupView(){
        ((Button) findViewById(R.id.alarm_start_listen_bt)).setOnClickListener(this);
        mAlarmListView = (ListView) findViewById(R.id.alarm_list_view);
        for (int i = 0; i < MAX_SHOW_NUMS; ++i) {   // 填充数据
            mAlarmList.add(" ");
        }
        initData();
        adapter =  new ArrayAdapter<>(this,
                R.layout.alarm_list_row,
                R.id.alarm_info,
                mAlarmList);
        mAlarmListView.setAdapter(adapter);

    }

    private void initData() {
        isListening = false;
        mIndex = 0;
        data.clear();
        mHandler.removeCallbacksAndMessages(null);
        Collections.fill(mAlarmList, " ");
    }

    /// Alarm Message Map
    /// 报警信息对应表
    private  static HashMap<Integer, Integer> alarmMessageMap = new HashMap<Integer, Integer>() {

        private static final long serialVersionUID = 1L;

        {
            put(FinalVar.SDK_ALARM_ALARM_EX, R.string.alarm_listen_external_alarm);
            put(FinalVar.SDK_MOTION_ALARM_EX, R.string.alarm_listen_motion_alarm);
            put(FinalVar.SDK_VIDEOLOST_ALARM_EX, R.string.alarm_listen_video_lost_alarm);
            put(FinalVar.SDK_SHELTER_ALARM_EX, R.string.alarm_listen_shelter_alarm);
            put(FinalVar.SDK_DISKFULL_ALARM_EX, R.string.alarm_listen_disk_full_alarm);
            put(FinalVar.SDK_DISKERROR_ALARM_EX, R.string.alarm_listen_disk_error_alarm);
        }
    };

    /// Alarm Status Map
    /// 报警状态对应表
    private static HashMap<AlarmStatus, Integer> alarmStatusMap = new HashMap<AlarmStatus, Integer>() {

        private static final long serialVersionUID = 1L;

        {
            put(AlarmStatus.ALARM_START, R.string.alarm_listen_alarm_detect);
            put(AlarmStatus.ALARM_STOP, R.string.alarm_listen_alarm_cancel);
        }
    };

    private final SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    ///message identifier for updating motion detection alarm.
    ///更新动检报警信息的消息标识
    private static final int UPDATE_ALARM_INFO = 0x10;
    Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg){
            if (msg.what == UPDATE_ALARM_INFO && msg.obj !=null){
                AlarmEventInfo alarmEventInfo = (AlarmEventInfo)msg.obj;
                String info = (mIndex+1) + ":" + simpleDate.format(alarmEventInfo.date) + " "
                        + getString(R.string.alarm_listen_alarm_channel)  + ":" + alarmEventInfo.chn + " "
                        + getString(alarmMessageMap.get(alarmEventInfo.type)) + " " + getString(alarmStatusMap.get(alarmEventInfo.status));

                // Log.d(TAG,"new info:"+ info);
                if (!isListening) {
                    return;
                }
                ++mIndex;
                mAlarmList.removeLast();
                mAlarmList.addFirst(info);
                adapter.notifyDataSetChanged();
            }
        }
    };

    private void listenAlarm(View v){
          if (!isListening){
              if (mAlarmModule.startListen()) {
                  isListening = true;
                  ((Button) v).setText(R.string.stop_alarm_listen);
              }else {
                  ToolKits.showMessage(AlarmListenActivity.this, getString(R.string.info_failed));
              }
           }else {
              if (mAlarmModule.stopListen()) {
                  initData();
                  adapter.notifyDataSetChanged();
                  ((Button) v).setText(R.string.start_alarm_listen);
              }else {
                  ToolKits.showMessage(AlarmListenActivity.this, getString(R.string.info_failed));
              }
           }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.alarm_start_listen_bt:
                listenAlarm(v);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean invoke(int lCommand, long lLoginID, Object obj, String pchDVRIP, int nDVRPort) {
        switch (lCommand) {
            case FinalVar.SDK_ALARM_ALARM_EX:
            case FinalVar.SDK_MOTION_ALARM_EX:
            case FinalVar.SDK_VIDEOLOST_ALARM_EX:
            case FinalVar.SDK_SHELTER_ALARM_EX:
            case FinalVar.SDK_DISKFULL_ALARM_EX:
            case FinalVar.SDK_DISKERROR_ALARM_EX: {
                byte[] alarm = (byte[])obj;
                for (int i = 0; i < alarm.length; i++) {
                    if (alarm[i] == 1) {    // 开始
                        AlarmEventInfo alarmEventInfo = new AlarmEventInfo(i, lCommand, AlarmStatus.ALARM_START);
                        if (!data.contains(alarmEventInfo)) {
                            data.add(alarmEventInfo);
                            Message msg = mHandler.obtainMessage(UPDATE_ALARM_INFO);
                            msg.obj = alarmEventInfo;
                            mHandler.sendMessage(msg);
                        }
                    }else {             // 结束
                        AlarmEventInfo alarmEventInfo = new AlarmEventInfo(i, lCommand, AlarmStatus.ALARM_STOP);
                        if (data.remove(alarmEventInfo)) {
                            Message msg = mHandler.obtainMessage(UPDATE_ALARM_INFO);
                            msg.obj = alarmEventInfo;
                            mHandler.sendMessage(msg);
                        }
                    }
                }
                break;
            }
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mAlarmModule.setCallback(null);
        if (isListening){
            mAlarmModule.stopListen();
        }
        mHandler.removeCallbacksAndMessages(null);
        mAlarmModule = null;
    }

}
