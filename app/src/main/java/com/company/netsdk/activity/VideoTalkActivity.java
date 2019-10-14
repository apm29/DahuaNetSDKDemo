package com.company.netsdk.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.company.netsdk.R;
import com.company.netsdk.common.DialogProgress;
import com.company.netsdk.common.ToolKits;
import com.company.netsdk.module.DeviceControlModule;
import com.company.netsdk.module.LivePreviewModule;
import com.company.netsdk.module.TalkModule;

import java.util.ArrayList;

public class VideoTalkActivity extends AppCompatActivity implements
        SurfaceHolder.Callback, View.OnClickListener{

    private final String TAG = LivePreviewActivity.class.getSimpleName();

    DialogProgress mDialogProgress;
    Spinner mSelectStream;
    Spinner mSelectChannel;
    SurfaceView mRealView;
    Button btnVideoTalk;

    private boolean isVideoTalking = false;
    private LivePreviewModule mLiveModule;
    private TalkModule mTalkModule;
    private DeviceControlModule mDeviceControlModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.activity_function_list_video_talk);
        setContentView(R.layout.activity_video_talk);
        mLiveModule = new LivePreviewModule(this);
        mTalkModule = new TalkModule(this);
        mDeviceControlModule = new DeviceControlModule(this);
        mDialogProgress = new DialogProgress(this);

        // 添加返回键
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setupView();

        ToolKits.verifyRecordPermissions(VideoTalkActivity.this);
    }

    private void setupView(){
        mSelectStream = (Spinner)findViewById(R.id.select_stream_type);
        mSelectChannel = (Spinner)findViewById(R.id.select_channel);
        mRealView = (SurfaceView)findViewById(R.id.real_view);
        mRealView.getHolder().addCallback(this);
        mLiveModule.setOpenSound(false);
        initializeSpinner(mSelectChannel,(ArrayList)mLiveModule.getChannelList()).setSelection(0);
        initializeSpinner(mSelectStream,(ArrayList)mLiveModule.getStreamTypeList(mSelectChannel.getSelectedItemPosition())).setSelection(0);

        btnVideoTalk = ((Button)findViewById(R.id.buttonVideoTalk));
        btnVideoTalk.setOnClickListener(this);
        ((Button)findViewById(R.id.buttonAccessOpen)).setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonVideoTalk:
                VideoTalkTask task = new VideoTalkTask();
                task.execute();
                break;
            case R.id.buttonAccessOpen:
                accessOpen();
                break;
            default:
                break;
        }
    }

    private void accessOpen() {
        if (mDeviceControlModule.accessOpen(mSelectChannel.getSelectedItemPosition())) {
            ToolKits.showMessage(VideoTalkActivity.this, getString(R.string.access_open) + getString(R.string.info_success));
        }else {
            ToolKits.showMessage(VideoTalkActivity.this, getString(R.string.access_open) + getString(R.string.info_failed));
        }
    }

    /// VideoTalkTask
    private class VideoTalkTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialogProgress.setMessage(getString(R.string.waiting));
            mDialogProgress.setSpinnerType(DialogProgress.FADED_ROUND_SPINNER);
            mDialogProgress.setCancelable(false);
            mDialogProgress.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            return videoTalk();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            mDialogProgress.dismiss();
            if (!result) {
                ToolKits.showMessage(VideoTalkActivity.this, getString(R.string.video_talk) + getString(R.string.info_failed));
                return;
            }
            if (!isVideoTalking) {
                mSelectChannel.setEnabled(false);
                mSelectStream.setEnabled(false);
                isVideoTalking = true;
                if (!mLiveModule.isRealPlaying()) {
                    ToolKits.showMessage(VideoTalkActivity.this, getString(R.string.live_preview_failed));
                }else if (!mTalkModule.isTalking()) {
                    ToolKits.showMessage(VideoTalkActivity.this, getString(R.string.talk) + getString(R.string.info_failed));
                }
                btnVideoTalk.setText(R.string.stop_video_talk);
            } else {
                isVideoTalking = false;
                btnVideoTalk.setText(R.string.start_video_talk);
                mSelectChannel.setEnabled(true);
                mSelectStream.setEnabled(true);
            }

            ToolKits.showMessage(VideoTalkActivity.this, getString(R.string.info_success));
        }
    }

    private boolean videoTalk() {
        boolean bRet = true;
        if (!isVideoTalking) {
            mTalkModule.startClientTalk();
            mLiveModule.startPlay(mSelectChannel.getSelectedItemPosition(),mSelectStream.getSelectedItemPosition(),mRealView);
            if (!mTalkModule.isTalking() && !mLiveModule.isRealPlaying()) {
                bRet = false;
            }
        }else {
            if ( mLiveModule.getHandle() != 0) {
                mLiveModule.stopRealPlay();
            }
            mTalkModule.stopTalk();
        }

        return bRet;
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mLiveModule.initSurfaceView(mRealView);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onDestroy(){
        if ( mLiveModule.getHandle() != 0) {
            mLiveModule.stopRealPlay();
        }
        mTalkModule.stopTalk();
        mLiveModule = null;
        mRealView = null;
        super.onDestroy();
    }

    private Spinner initializeSpinner(final Spinner spinner, ArrayList array){
        spinner.setSelection(0,true);
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,array));
        return spinner;
    }
}
