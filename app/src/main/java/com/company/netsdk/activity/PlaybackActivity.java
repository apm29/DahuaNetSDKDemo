package com.company.netsdk.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.company.NetSDK.CB_fDownLoadPosCallBack;
import com.company.NetSDK.NET_TIME;
import com.company.netsdk.R;
import com.company.netsdk.common.ToolKits;
import com.company.netsdk.module.CapturePictureModule;
import com.company.netsdk.module.LivePreviewModule;
import com.company.netsdk.module.PlayBackModule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PlaybackActivity extends AppCompatActivity
        implements View.OnClickListener,DataTimePicker.OnDateTimeChangeed,AdapterView.OnItemSelectedListener {

    private static final String TAG = PlaybackActivity.class.getSimpleName();

    final int MARK_RECORD = 0x1000;
    final int DOWNLOAD_RECORD = 0x1001;

    private PlayBackModule mPlayBackModule;

    Spinner mSelectChannel;
    Spinner mSelectStreamType;
    SurfaceView mSurface;
    SeekBar mPlayBackSeekbar;
    TextView mCurrentOSDTime;

    Button mPlayButton;
    Button mFastPlayButton;
    Button mSlowPlayButton;
    Button mNormalPlayButton;

    TextView mPlayBackTimeTv;
    DataTimePicker picker;
    Spinner mSelectFileType;

    Button mPlayBackButton;
    Button mLocalCaptureButton;

    private boolean isPlaying = false;
    private final int MIN_SPEED_INDEX = 0;
    private final int NORMAL_SPEED_INDEX = 3;
    private final int MAX_SPEED_INDEX = 6;
    private int CURRENT_SPEED_INDEX = NORMAL_SPEED_INDEX;
    private String[] mPlaySpeed;

    NET_TIME mPlayBackTime = new NET_TIME();
    PlayBackPosCallBack posCallBack = new PlayBackPosCallBack();
    long currentProgress = -1;
    private final int UPDATE_PLAYBACK_PROGRESS = 0x25;

    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            currentProgress = mPlayBackSeekbar.getProgress();
            switch (msg.what) {
                case UPDATE_PLAYBACK_PROGRESS:
                    long offset = (Long) msg.obj;
                    if (currentProgress != offset) {
                        Log.d(TAG,"CurrentProgress: "+ currentProgress);
                        mPlayBackSeekbar.setProgress((int) offset);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.activity_function_list_play_back);
        setContentView(R.layout.activity_playback);

        mPlaySpeed = getResources().getStringArray(R.array.play_back_speed);
        mPlayBackModule = new PlayBackModule(this);
        mPlayBackModule.setPosCallBack(posCallBack);

        // 添加返回键
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setupView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void setupView() {
        /// init flow layout
        mSelectChannel = (Spinner) findViewById(R.id.select_channel);
        mSelectStreamType = (Spinner) findViewById(R.id.select_stream_type);
        initializeSpinner(mSelectChannel, (ArrayList) new LivePreviewModule(this).getChannelList()).setSelection(0);
        initializeSpinner(mSelectStreamType, (ArrayList)mPlayBackModule.getStreamTypeList()).setSelection(0);
        mSelectStreamType.setSelected(false);

        mSurface = (SurfaceView) findViewById(R.id.play_back_view);
        mPlayBackModule.setView(mSurface);

        mPlayBackSeekbar = (SeekBar) findViewById(R.id.play_back_seekbar);
        mPlayBackSeekbar.setProgress(0);
        mPlayBackSeekbar.setOnSeekBarChangeListener(new PlayBackProgress());
        mCurrentOSDTime = (TextView) findViewById(R.id.current_osd_time);

        mPlayButton = (Button) findViewById(R.id.play_start_pause);
        mFastPlayButton = (Button) findViewById(R.id.play_fast);
        mSlowPlayButton = (Button) findViewById(R.id.play_slow);
        mNormalPlayButton = (Button) findViewById(R.id.play_normal);
        mPlayButton.setOnClickListener(this);
        mFastPlayButton.setOnClickListener(this);
        mSlowPlayButton.setOnClickListener(this);
        mNormalPlayButton.setOnClickListener(this);

        mPlayBackTimeTv = ((TextView) findViewById(R.id.playback_date));
        mPlayBackTimeTv.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        mPlayBackTimeTv.setOnClickListener(this);
        picker = new DataTimePicker(this, this);
        mSelectFileType = (Spinner) findViewById(R.id.select_recordfile_type);
        initializeSpinner(mSelectFileType, (ArrayList) mPlayBackModule.getRecordFileTypeList()).setSelection(0);

        mPlayBackButton = (Button)findViewById(R.id.start_playback);
        mPlayBackButton.setOnClickListener(this);
        mLocalCaptureButton = (Button) findViewById(R.id.local_capture_picture);
        mLocalCaptureButton.setOnClickListener(this);
        ((Button) findViewById(R.id.mark_record)).setOnClickListener(this);
        ((Button) findViewById(R.id.download_record)).setOnClickListener(this);

        initPlayBackTime();
        setPlayStatus(false);
    }

    private void initPlayBackTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        mPlayBackTime.dwYear = calendar.get(Calendar.YEAR);
        mPlayBackTime.dwMonth = calendar.get(Calendar.MONTH) + 1;
        mPlayBackTime.dwDay = calendar.get(Calendar.DAY_OF_MONTH);
        mPlayBackTime.dwHour = 0;
        mPlayBackTime.dwMinute = 0;
        mPlayBackTime.dwSecond = 0;
    }

    /************************************************************************************************************************/
    /*                                          事件处理（deal event）
    /***********************************************************************************************************************/
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.play_start_pause:
                play();
                break;
            case R.id.play_fast:
                playFast();
                break;
            case R.id.play_slow:
                playSlow();
                break;
            case R.id.play_normal:
                playNormal();
                break;
            case R.id.playback_date:
                selectPlayBackTime();
                break;
            case R.id.start_playback:
                playBack();
                break;
            case R.id.local_capture_picture:
                localCapturePicture();
                break;
            case R.id.mark_record:
                if (isPlaying){
                    stopPlayBack();
                }
                markRecord();
                break;
            case R.id.download_record:
                if (isPlaying){
                    stopPlayBack();
                }
                downloadRecord();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDateTimeChangeed(int id, int year, int month, int day) {
        switch (id){
            case R.id.playback_date:
                mPlayBackTime.dwYear = year;
                mPlayBackTime.dwMonth = month;
                mPlayBackTime.dwDay = day;
                mPlayBackTimeTv.setText(String.valueOf(year)+"-"+String.valueOf(month)+"-"+
                        String.valueOf(day));
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int parentID = parent.getId();
        switch (parentID) {
            case R.id.select_channel:
            case R.id.select_stream_type:
            case R.id.select_recordfile_type:
                playBackChanged();
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mPlayBackModule.release();
        mPlayBackModule = null;
        picker.release();
        picker = null;
    }

    /************************************************************************************************************************/
    /*                                          功能（functions）
    /***********************************************************************************************************************/
    private void playBack() {
        if (!isPlaying) {
            NET_TIME start = getStartTime();
            if (start == null) {
                ToolKits.showMessage(this, getString(R.string.start_time_error));
            }
            NET_TIME end = getEndTime();
            mPlayBackSeekbar.setProgress(0);
            startPlayBack(start, end);
        } else {
            stopPlayBack();
        }
    }

    private void play(){
        boolean bPlay  = mPlayButton.getText().equals(getString(R.string.play_back_start));
        if(!mPlayBackModule.play(bPlay)) {
            ToolKits.showMessage(this,getString(R.string.operation_failed));
            return;
        }
        if(!bPlay){
            mPlayButton.setText(R.string.play_back_start);
        }else {
            mPlayButton.setText(R.string.play_back_pause);
        }
    }

    private void playFast(){
        CURRENT_SPEED_INDEX++;
        if (CURRENT_SPEED_INDEX>MAX_SPEED_INDEX) {
            CURRENT_SPEED_INDEX = MAX_SPEED_INDEX;
            Toast.makeText(this, getString(R.string.fast_upper)+":"+mPlaySpeed[CURRENT_SPEED_INDEX], Toast.LENGTH_SHORT).show();
            return;
        }
        if(mPlayBackModule.playFast()) {
            Toast.makeText(this, mPlaySpeed[CURRENT_SPEED_INDEX], Toast.LENGTH_SHORT).show();
        } else {
            CURRENT_SPEED_INDEX--;
            Toast.makeText(this, getString(R.string.operation_failed), Toast.LENGTH_SHORT).show();
        }
    }

    private void playSlow(){
        CURRENT_SPEED_INDEX--;
        if (CURRENT_SPEED_INDEX < MIN_SPEED_INDEX) {
            CURRENT_SPEED_INDEX = MIN_SPEED_INDEX;
            Toast.makeText(this, getString(R.string.slow_lower)+":"+mPlaySpeed[CURRENT_SPEED_INDEX], Toast.LENGTH_SHORT).show();
            return;
        }
        if(mPlayBackModule.playSlow()) {
            Toast.makeText(this, mPlaySpeed[CURRENT_SPEED_INDEX], Toast.LENGTH_SHORT).show();
        } else {
            CURRENT_SPEED_INDEX++;
            Toast.makeText(this, getString(R.string.operation_failed), Toast.LENGTH_SHORT).show();
        }
    }

    private void playNormal(){
        if (CURRENT_SPEED_INDEX == NORMAL_SPEED_INDEX)
            return;
        if(mPlayBackModule.playNormal()) {
            CURRENT_SPEED_INDEX = NORMAL_SPEED_INDEX;
            Toast.makeText(this, getResources().getString(R.string.play_back_normal), Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this,getString(R.string.operation_failed), Toast.LENGTH_SHORT).show();
    }

    private void selectPlayBackTime() {
        if (isPlaying) {
            ToolKits.showMessage(this, getString(R.string.repick_date_warn));
            return;
        }
        picker.showAt(R.id.playback_date,findViewById(R.id.parent));
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mPlayBackSeekbar.setProgress(0);
                mCurrentOSDTime.setText("");
            }
        });
    }

    private void playBackChanged(){
        if (isPlaying){
            stopPlayBack();
            Toast.makeText(this,getString(R.string.playback_changed),Toast.LENGTH_SHORT).show();
        }
    }

    private void localCapturePicture(){
        String filename = LivePreviewModule.createInnerAppFile("jpg");
        ToolKits.writeLog("FileName:"+filename);
        if (isPlaying) {
            if(CapturePictureModule.localCapturePicture(mPlayBackModule.getPort(), filename)) {
                ToolKits.showMessage(PlaybackActivity.this, getString(R.string.info_success));
            } else {
                ToolKits.showMessage(PlaybackActivity.this, getString(R.string.info_failed));
            }
        }else {
            ToolKits.showMessage(PlaybackActivity.this, getString(R.string.play_back_not_start));
        }
    }

    private void markRecord(){
        Intent intent = new Intent(this, MarkRecordActivity.class);
        startActivity(intent);
    }

    private void downloadRecord(){
        Intent intent = new Intent(this, DownLoadRecordFileActivity.class);
        startActivity(intent);
    }
    /************************************************************************************************************************/
    /*                                          回放辅助函数（playback ex）
    /***********************************************************************************************************************/
    private void startPlayBack(NET_TIME start, NET_TIME end){
        startPlayBack(-1, start, end);
    }

    private void startPlayBack(int progress, NET_TIME start, NET_TIME end){
        setPlayStatus(false);
        mPlayBackModule.startPlayBack(mSelectChannel.getSelectedItemPosition(), mSelectStreamType.getSelectedItemPosition(), mSelectFileType.getSelectedItemPosition(),
                start, end, new PlayBackTaskDone(progress));
    }

    private class PlayBackTaskDone implements PlayBackModule.OnPlayBackTaskDone {
        private  int progress = 0;

        public PlayBackTaskDone(int progress) {
            this.progress = progress;
        }

        @Override
        public void onTaskDone(boolean result) {
            if (!result){
                if (mPlayBackModule.isNoRecord()) {
                    Toast.makeText(PlaybackActivity.this,getString(R.string.no_record_found),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PlaybackActivity.this,getString(R.string.play_back_failed),Toast.LENGTH_SHORT).show();
                }
                mSurface.setBackgroundColor(Color.BLACK);
                if (progress != -1) {
                    mPlayBackSeekbar.setProgress(progress);
                }
            }else {
                setPlayStatus(true);
            }
        }
    }

    private void stopPlayBack(){
        mPlayBackModule.stopPlayBack();
        setPlayStatus(false);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mPlayBackSeekbar.setProgress(0);
                mCurrentOSDTime.setText("");
            }
        });
    }

    private void setPlayStatus(boolean bPlay){
        isPlaying = bPlay;
        if (isPlaying) {
            mPlayBackButton.setText(R.string.stop_play_back);
            mPlayButton.setText(R.string.play_back_pause);
            mSurface.setBackgroundColor(Color.TRANSPARENT);
        }else {
            mSurface.setBackgroundColor(Color.BLACK);
            CURRENT_SPEED_INDEX = NORMAL_SPEED_INDEX;
            mPlayBackButton.setText(R.string.start_play_back);
            mPlayButton.setText(R.string.play_back_start);
        }

        mPlayButton.setEnabled(bPlay);
        mFastPlayButton.setEnabled(bPlay);
        mSlowPlayButton.setEnabled(bPlay);
        mNormalPlayButton.setEnabled(bPlay);
        mLocalCaptureButton.setEnabled(bPlay);
    }

    // playback pos callback
    private class PlayBackPosCallBack implements CB_fDownLoadPosCallBack {

        @Override
        public void invoke(long playHandler,final int totalSize,final int downloadSize) {
            Message msg;
            NET_TIME time = mPlayBackModule.getOSDtime();
            if(time !=null) {
                msg = mHandler.obtainMessage(UPDATE_PLAYBACK_PROGRESS);//send message to UI Thread  update seekbar.
                long second = time.dwHour * 60 * 60 + time.dwMinute * 60 + time.dwSecond;
                msg.obj = second;
                mHandler.sendMessage(msg);
            }
        }
    }

    private class PlayBackProgress implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.i(TAG,"PlayBackProgress onProgressChanged");

            String hour = "";
            String minute = "";
            String second = "";

            NET_TIME time = calculateTime(progress);

            if (time.dwHour<10){
                hour = "0"+time.dwHour;
            }else {
                hour = String.valueOf(time.dwHour);
            }
            if (time.dwMinute<10){
                minute = "0"+time.dwMinute;
            }else {
                minute = String.valueOf(time.dwMinute);
            }
            if (time.dwSecond<10){
                second = "0"+time.dwSecond;
            }else {
                second = String.valueOf(time.dwSecond);
            }

            String temp = hour+ ":" + minute+ ":" + second;
            mCurrentOSDTime.setText(temp);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.i(TAG,"PlayBackProgress onStartTrackingTouch");
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.i(TAG,"PlayBackProgress onStopTrackingTouch");
            int progress = seekBar.getProgress();
            NET_TIME start = calculateTime(progress);
            NET_TIME end = getEndTime();
            startPlayBack(progress, start, end);
        }
    }

    private NET_TIME calculateTime(int second){
        NET_TIME time = new NET_TIME();

        time.dwYear = mPlayBackTime.dwYear;
        time.dwMonth = mPlayBackTime.dwMonth;
        time.dwDay = mPlayBackTime.dwDay;
        time.dwHour = second/3600;
        time.dwMinute = second%3600/60;
        time.dwSecond = second%60;

        return time;
    }

    private NET_TIME getEndTime(){
        NET_TIME time = new NET_TIME();
        time.dwYear = mPlayBackTime.dwYear;
        time.dwMonth = mPlayBackTime.dwMonth;
        time.dwDay = mPlayBackTime.dwDay;
        time.dwHour = 23;
        time.dwMinute = 59;
        time.dwSecond = 59;
        return time;
    }

    private NET_TIME getStartTime(){
        return mPlayBackTime;
    }

    private Spinner initializeSpinner(final Spinner spinner, ArrayList array){
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,array));
        return spinner;
    }
}
