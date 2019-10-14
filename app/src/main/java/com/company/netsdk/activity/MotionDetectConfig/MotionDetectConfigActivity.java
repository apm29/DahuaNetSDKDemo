package com.company.netsdk.activity.MotionDetectConfig;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.company.NetSDK.CFG_MOTION_INFO;
import com.company.NetSDK.FinalVar;
import com.company.netsdk.R;
import com.company.netsdk.activity.NetSDKApplication;
import com.company.netsdk.common.ToolKits;

/**
 * 动态检测配置.
 */
public class MotionDetectConfigActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int			   TIMESECTION		    =	103;			//布撤防时间段
    private static final int			   REGION				=	104;			//动检区域选择

    private RelativeLayout                mChannelNumLayout;					// 通道选择按钮
    private TextView                      mChannelNumTextView;					// 通道号

    private RelativeLayout 			   mMotionEnableLayout;					// 动检使能布局选择
    private ImageView                     mMotionEnableBtn;						// 动检使能

    private RelativeLayout 			   mTimeSectionLayout;					// 布撤防时间布局选择

    private RelativeLayout				   mDejitterLayout;						// 去抖动布局选择
    private TextView 					   mDejitterTextView;						// 去抖动时间
    private EditText                      mDijitterEditText;

    private RelativeLayout 			   mRregionLayout;						// 区域设置布局选择

    private RelativeLayout				   mRecordChannelLayout;					// 录像通道布局选择
    private ImageView 					   mRecordEnableBtn;						// 录像使能开关

    private RelativeLayout				   mRecordDelayTimeLayout;				// 录像延时布局选择
    private TextView 					   mRecordDelayTimeTextView;			// 录像延时时间
    private EditText                      mRecordDelayTimeEditText;

    private RelativeLayout				   mAlarmOutEnableLayout;				// 报警输出布局选择
    private ImageView 					   mAlarmOutEnableBtn;					// 报警输出使能开关

    private RelativeLayout				   mAlarmOutDelayTimeLayout;				// 报警输出延时布局选择
    private TextView 					   mAlarmOutDelayTimeTextView;			// 报警输出延时时间
    private EditText                      mAlarmOutDelayTimeEditText;

    private RelativeLayout				   mSnapLayout;						    // 抓图布局选择
    private ImageView 					   mSnaptBtn;								// 抓图使能开关

    private ProgressDialog                mProgressDialog;						// 等待框

    private CFG_MOTION_INFO               motion_info;                           // 动检配置信息

    private GetMotionTask                 getMotionTask;                         // 获取动检配置异步线程
    private SetMotionTask                 setMotionTask;                         // 保存动检配置异步线程

    private int                           nChn;                                   // 通道个数
    private String[]                      items;                                  // 通道列表
    private int                           mIndex;                                // 当前通道索引

    private Resources                     res;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_motion_detect_config);

        res = this.getResources();

        Toolbar toolbar = (Toolbar) findViewById(R.id.motion_detect_config_toolbar);
        toolbar.setTitle(res.getString(R.string.activity_function_list_motion_detect));  // 在setSupportActionBar之前调用，否则无效；或者在onResume里调用
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
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TIMESECTION && resultCode == RESULT_OK)  // 布撤防时间段返回, 并刷新当前配置信息
        {
            motion_info = (CFG_MOTION_INFO)data.getSerializableExtra("motion_timesection");
        }
        else if(requestCode == REGION && resultCode == RESULT_OK)  // 动检区域返回，并刷新当前配置信息
        {
            motion_info = (CFG_MOTION_INFO)data.getSerializableExtra("motion_area");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fresh_save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.refresh_btn) {    // 刷新，获取当前通道配置
            String nChns = mChannelNumTextView.getText().toString();
            if(nChns.equals("") || nChns == null || nChn == 0) {
                return false;
            }
            getMotionTask = new GetMotionTask(mIndex);
            getMotionTask.execute();

            return true;
        } else if(id == R.id.save_btn){  // 保存，设置当前通道配置
            String nChns = mChannelNumTextView.getText().toString();
            if(nChns.equals("") || nChns == null || nChn == 0) {
                return false;
            }

            setMotionTask = new SetMotionTask(mIndex);
            setMotionTask.execute();

            return true;
        } else if(id == android.R.id.home){  // 返回
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化控件
     */
    private void initUIView() {
        mChannelNumLayout = (RelativeLayout)findViewById(R.id.motion_channel_layout);
        mChannelNumTextView = (TextView)findViewById(R.id.motion_channel_text);
        mChannelNumLayout.setOnClickListener(this);

        mMotionEnableLayout = (RelativeLayout)findViewById(R.id.motion_enable_layout);
        mMotionEnableBtn = (ImageView)findViewById(R.id.motion_enable_btn);
        mMotionEnableLayout.setOnClickListener(this);

        mTimeSectionLayout = (RelativeLayout)findViewById(R.id.motion_time_layout);
        mTimeSectionLayout.setOnClickListener(this);

        mDejitterLayout = (RelativeLayout)findViewById(R.id.motion_dejitter_layout);
        mDejitterTextView = (TextView)findViewById(R.id.motion_dejitter_text);
        mDejitterLayout.setOnClickListener(this);

        mRregionLayout = (RelativeLayout)findViewById(R.id.motion_region_layout);
        mRregionLayout.setOnClickListener(this);

        mRecordChannelLayout = (RelativeLayout)findViewById(R.id.record_enable_layout);
        mRecordEnableBtn = (ImageView)findViewById(R.id.record_enable_btn);
        mRecordChannelLayout.setOnClickListener(this);

        mRecordDelayTimeLayout = (RelativeLayout)findViewById(R.id.record_delay_layout);
        mRecordDelayTimeTextView = (TextView)findViewById(R.id.record_delay_text);
        mRecordDelayTimeLayout.setOnClickListener(this);

        mAlarmOutEnableLayout = (RelativeLayout)findViewById(R.id.alarmout_enable_layout);
        mAlarmOutEnableBtn = (ImageView)findViewById(R.id.alarmout_enable_btn) ;
        mAlarmOutEnableLayout.setOnClickListener(this);

        mAlarmOutDelayTimeLayout = (RelativeLayout)findViewById(R.id.alarmout_delay_layout);
        mAlarmOutDelayTimeTextView = (TextView)findViewById(R.id.alarmout_delay_text);
        mAlarmOutDelayTimeLayout.setOnClickListener(this);

        mSnapLayout = (RelativeLayout)findViewById(R.id.snap_enable_layout);
        mSnaptBtn = (ImageView)findViewById(R.id.snap_enable_btn);
        mSnapLayout.setOnClickListener(this);

        // 获取所有通道
        nChn = NetSDKApplication.getInstance().getDeviceInfo().nChanNum;
        items = new String[nChn];
        for (int i = 0; i < nChn; i++) {
            items[i] = res.getString(R.string.channel) + String.valueOf(i);
            ToolKits.writeLog(items[i]);
        }

        // 默认获取通道0的动检配置，并显示
        if(nChn > 0) {
            mIndex = 0;
            mChannelNumTextView.setText(res.getString(R.string.channel) + "0");
            getMotionTask = new GetMotionTask(mIndex);
            getMotionTask.execute();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch(id) {
            case R.id.motion_channel_layout: {   // 通道
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle(res.getString(R.string.channel))
                        .setSingleChoiceItems(items, mIndex, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 界面显示
                                mChannelNumTextView.setText(items[which]);
                                mIndex = which;

                                // 获取当前选中通道的动检配置信息
                                getMotionTask = new GetMotionTask(which);
                                getMotionTask.execute();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(res.getString(R.string.cancel), null).show();
                break;
            }
            case R.id.motion_enable_layout:    // 动检使能
            {
                // 界面显示
                if (mMotionEnableBtn.isSelected()) {
                    mMotionEnableBtn.setSelected(false);
                    mTimeSectionLayout.setVisibility(View.GONE);
                    mDejitterLayout.setVisibility(View.GONE);
                    mRregionLayout.setVisibility(View.GONE);
                } else {
                    mMotionEnableBtn.setSelected(true);
                    mTimeSectionLayout.setVisibility(View.VISIBLE);
                    mDejitterLayout.setVisibility(View.VISIBLE);
                    mRregionLayout.setVisibility(View.VISIBLE);
                }

                // 动检使能
                motion_info.bEnable = mMotionEnableBtn.isSelected();

                break;
            }
            case R.id.motion_time_layout:      // 布撤防时间段
            {
                // 跳转到布撤防时间段
                Intent intent = new Intent(this, MotionDetectTimeShowActivity.class);
                intent.putExtra("motion_timesection", motion_info);

                startActivityForResult(intent, TIMESECTION);
                break;
            }
            case R.id.motion_dejitter_layout:   // 去抖动
            {
                mDijitterEditText = new EditText(this);
                mDijitterEditText.setText(mDejitterTextView.getText().toString());

                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle(res.getString(R.string.dejitter) + "(0~100" + res.getString(R.string.second) + ")")
                        .setView(mDijitterEditText)
                        .setPositiveButton(res.getString(R.string.confirm), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 界面显示
                                mDejitterTextView.setText(mDijitterEditText.getText().toString());

                                // 去抖动
                                if(motion_info.stuEventHandler.abEventLatch) {
                                    motion_info.stuEventHandler.nEventLatch = Integer.parseInt(mDejitterTextView.getText().toString());
                                }
                            }
                        })
                        .setNegativeButton(res.getString(R.string.cancel), null).show();
                break;
            }
            case R.id.motion_region_layout:   // 动检区域配置
            {
                // 跳转到检区域配置
                Intent intent = new Intent(this, MotionDetectAreaActivity.class);
                intent.putExtra("motion_area", motion_info);

                startActivityForResult(intent, REGION);

                break;
            }
            case R.id.record_enable_layout:    // 录像使能
            {
                // 界面显示
                if (mRecordEnableBtn.isSelected()) {
                    mRecordEnableBtn.setSelected(false);
                } else {
                    mRecordEnableBtn.setSelected(true);
                }

                // 录像使能
                motion_info.stuEventHandler.bRecordEnable = mRecordEnableBtn.isSelected();

                break;
            }
            case R.id.record_delay_layout:    // 录像延时
            {
                mRecordDelayTimeEditText = new EditText(this);
                mRecordDelayTimeEditText.setText(mRecordDelayTimeTextView.getText().toString().replace(res.getString(R.string.second), ""));

                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle(res.getString(R.string.record_delay) + "(10~300" + res.getString(R.string.second) + ")")
                        .setView(mRecordDelayTimeEditText)
                        .setPositiveButton(res.getString(R.string.confirm), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 界面显示
                                mRecordDelayTimeTextView.setText(mRecordDelayTimeEditText.getText().toString() + res.getString(R.string.second));

                                // 录像延时
                                motion_info.stuEventHandler.nRecordLatch = Integer.parseInt(mRecordDelayTimeTextView.getText().toString().replace(res.getString(R.string.second), ""));
                            }
                        })
                        .setNegativeButton(res.getString(R.string.cancel), null).show();
                break;
            }
            case R.id.alarmout_enable_layout:   // 报警输出使能
            {
                // 界面显示
                if (mAlarmOutEnableBtn.isSelected()) {
                    mAlarmOutEnableBtn.setSelected(false);
                } else {
                    mAlarmOutEnableBtn.setSelected(true);
                }

                // 报警输出使能
                motion_info.stuEventHandler.bAlarmOutEn = mAlarmOutEnableBtn.isSelected();

                break;
            }
            case R.id.alarmout_delay_layout:   // 报警输出延时
            {
                mAlarmOutDelayTimeEditText = new EditText(this);
                mAlarmOutDelayTimeEditText.setText(mAlarmOutDelayTimeTextView.getText().toString().replace(res.getString(R.string.second), ""));

                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle(res.getString(R.string.alarm_out_delay) + "(0~300" + res.getString(R.string.second) + ")")
                        .setView(mAlarmOutDelayTimeEditText)
                        .setPositiveButton(res.getString(R.string.confirm), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 界面显示
                                mAlarmOutDelayTimeTextView.setText(mAlarmOutDelayTimeEditText.getText().toString() + res.getString(R.string.second));

                                // 报警输出延时
                                motion_info.stuEventHandler.nAlarmOutLatch = Integer.parseInt(mAlarmOutDelayTimeTextView.getText().toString().replace(res.getString(R.string.second), ""));
                            }
                        })
                        .setNegativeButton(res.getString(R.string.cancel), null).show();
                break;
            }
            case R.id.snap_enable_layout:   // 抓图使能
            {
                // 界面显示
                if (mSnaptBtn.isSelected()) {
                    mSnaptBtn.setSelected(false);
                } else {
                    mSnaptBtn.setSelected(true);
                }

                // 抓图使能
                motion_info.stuEventHandler.bSnapshotEn = mSnaptBtn.isSelected();

                break;
            }
            default:
                break;
        }

    }

    /**
     * 获取当前通道的动检配置
     */
    private class GetMotionTask extends AsyncTask<String, Integer, CFG_MOTION_INFO> {
        private int nChn;       // 当前通道

        public GetMotionTask(int nChn) {
            this.nChn = nChn;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ToolKits.showProgressDialog(MotionDetectConfigActivity.this, res.getString(R.string.getting_mition_detect_configuration), false, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    getMotionTask.cancel(false);
                }
            });
        }

        @Override
        protected CFG_MOTION_INFO doInBackground(String... params) {
            motion_info = null;
            motion_info = new CFG_MOTION_INFO();

            boolean bGet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_MOTIONDETECT, motion_info, NetSDKApplication.getInstance().getLoginHandle(), nChn, 1024 * 5);

            if(bGet) {
                return motion_info;
            }

            return null;
        }

        @Override
        protected void onPostExecute(CFG_MOTION_INFO motion_info) {
            mProgressDialog.dismiss();
            if(motion_info == null) {
                ToolKits.showErrorMessage(MotionDetectConfigActivity.this, res.getString(R.string.failed_get_motion_detect_configuration));
            } else {
                if(!motion_info.bEnable) {
                    mTimeSectionLayout.setVisibility(View.GONE);
                    mDejitterLayout.setVisibility(View.GONE);
                    mRregionLayout.setVisibility(View.GONE);;
                } else {
                    mTimeSectionLayout.setVisibility(View.VISIBLE);
                    mDejitterLayout.setVisibility(View.VISIBLE);
                    mRregionLayout.setVisibility(View.VISIBLE);
                }

                // 动检使能
                mMotionEnableBtn.setSelected(motion_info.bEnable);

                // 去抖动
                if(motion_info.stuEventHandler.abEventLatch) {
                    mDejitterTextView.setText(String.valueOf(motion_info.stuEventHandler.nEventLatch));
                } else {
                    mDejitterTextView.setText("");
                }

                // 录像使能
                mRecordEnableBtn.setSelected(motion_info.stuEventHandler.bRecordEnable);

                // 录像延时
                mRecordDelayTimeTextView.setText(String.valueOf(motion_info.stuEventHandler.nRecordLatch) + res.getString(R.string.second));

                // 报警输出使能
                mAlarmOutEnableBtn.setSelected(motion_info.stuEventHandler.bAlarmOutEn);

                // 报警输出延时
                mAlarmOutDelayTimeTextView.setText(String.valueOf(motion_info.stuEventHandler.nAlarmOutLatch) + res.getString(R.string.second));

                // 抓图使能
                mSnaptBtn.setSelected(motion_info.stuEventHandler.bSnapshotEn);

                ToolKits.showMessage(MotionDetectConfigActivity.this, res.getString(R.string.succeed_get_motion_detect_configuration));
            }
        }
    }

    /**
     * 保存当前通道的动检配置
     */
    private class SetMotionTask extends AsyncTask<String, Integer, Boolean> {
        private int nChn;    // 当前通道
        public SetMotionTask(int nChn) {
            this.nChn = nChn;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ToolKits.showProgressDialog(MotionDetectConfigActivity.this, res.getString(R.string.setting_mition_detect_configuration), false, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    setMotionTask.cancel(false);
                }
            });
        }

        @Override
        protected Boolean doInBackground(String... params) {
            boolean bGet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_MOTIONDETECT, motion_info, NetSDKApplication.getInstance().getLoginHandle(), nChn, 1024 * 5);

            return bGet;
        }

        @Override
        protected void onPostExecute(Boolean bln) {
            mProgressDialog.dismiss();
            if(!bln) {
                ToolKits.showErrorMessage(MotionDetectConfigActivity.this, res.getString(R.string.failed_set_motion_detect_configuration));
            } else {
                ToolKits.showMessage(MotionDetectConfigActivity.this, res.getString(R.string.succeed_set_motion_detect_configuration));
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(getMotionTask != null && getMotionTask.getStatus() == AsyncTask.Status.RUNNING) {
            getMotionTask.cancel(false);
        }

        if(setMotionTask != null && setMotionTask.getStatus() == AsyncTask.Status.RUNNING) {
            setMotionTask.cancel(false);
        }
    }

    @Override
    protected void onDestroy() {
        if(getMotionTask != null && getMotionTask.getStatus() == AsyncTask.Status.RUNNING) {
            getMotionTask.cancel(false);
        }

        if(setMotionTask != null && setMotionTask.getStatus() == AsyncTask.Status.RUNNING) {
            setMotionTask.cancel(false);
        }

        if(mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        super.onDestroy();
    }

}
