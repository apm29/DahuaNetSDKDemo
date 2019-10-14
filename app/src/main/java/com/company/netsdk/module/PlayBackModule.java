package com.company.netsdk.module;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.company.NetSDK.CB_fDataCallBack;
import com.company.NetSDK.CB_fDownLoadPosCallBack;
import com.company.NetSDK.EM_USEDEV_MODE;
import com.company.NetSDK.FinalVar;
import com.company.NetSDK.INetSDK;
import com.company.NetSDK.NET_RECORD_TYPE;
import com.company.NetSDK.NET_TIME;
import com.company.NetSDK.SDK_RealPlayType;
import com.company.PlaySDK.Constants;
import com.company.PlaySDK.IPlaySDK;
import com.company.netsdk.R;
import com.company.netsdk.activity.NetSDKApplication;
import com.company.netsdk.common.DialogProgress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 36141 on 2017/5/11.
 */
public class PlayBackModule {
    private static final String TAG = PlayBackModule.class.getSimpleName();
    private final int STREAM_BUFFER_SIZE = 1024*1024*2;
    private final int EMPTY_BIT_STREAM_LIBRARY = 1;
    private final int EMPTY_PLAY_QUEUE = 3;

    private int nErrorCode = 0;
    SurfaceHolder.Callback mSurfaceCallback;
    Context mContext;
    long mLoginHandler = 0;              /// 登陆句柄

    volatile long mPlayHandler = 0;     /// 多线程共享，  防止发生线程不同步
    volatile int mPort = 0;              /// 多线程共享，防止发生线程不同步

    CB_fDownLoadPosCallBack mPosCallBack;
    CB_fDataCallBack  mDataCallBack;

    SurfaceView mSurface;

    public PlayBackModule(Context context){
        this.mContext = context;
        mLoginHandler = ((NetSDKApplication)((AppCompatActivity)mContext).getApplication()).getLoginHandle();
        mSurfaceCallback = new SurfaceCallback();
    }

    /// is no record
    /// 是否无记录
    public boolean isNoRecord(){
        return nErrorCode == FinalVar.NET_NO_RECORD_FOUND;
    }

    /// start playback
    /// 开始回放
    public void startPlayBack(int channel, int stream, int recordType, NET_TIME start, NET_TIME end,OnPlayBackTaskDone taskDone){
        final PlayBackTask task = new PlayBackTask(taskDone);
        task.execute(channel, stream, recordType, start, end);
    }

    /// stop playback
    /// 停止回放
    public void stopPlayBack(){
        doStopPlayBack();
        if (mSurface != null) {
            mSurface.getHolder().removeCallback(mSurfaceCallback);
        }
    }

    public void doStopPlayBack(){
        if (mPlayHandler == 0) {
            return;
        }
        IPlaySDK.PLAYStop(mPort);
        IPlaySDK.PLAYCloseStream(mPort);
        IPlaySDK.PLAYStopSoundShare(mPort);
        INetSDK.StopPlayBack(mPlayHandler);
        mPort = 0;
        nErrorCode = 0;
        mPlayHandler = 0;
    }

    /// release
    /// 释放资源
    public void release(){
        stopPlayBack();
        mPosCallBack = null;
        mLoginHandler = 0;
        mContext = null;
    }

    public int getPort(){
        return this.mPort;
    }

    public void setView(final SurfaceView view) {
        mSurface = view;
    }
    /************************************************************************************************************************/
    /*                                          回放实现（playback implement）
    /***********************************************************************************************************************/
    /// playback task
    /// 回放任务
    private class PlayBackTask extends AsyncTask<Object,Object,Boolean>{
        private final DialogProgress dialog = new DialogProgress(mContext);
        private OnPlayBackTaskDone taskDone;

        public PlayBackTask(OnPlayBackTaskDone done){
            this.taskDone = done;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            dialog.setMessage(mContext.getString(R.string.waiting));
            dialog.setSpinnerType(DialogProgress.FADED_ROUND_SPINNER);
            dialog.setCancelable(false);
            dialog.show();
        }
        @Override
        protected Boolean doInBackground(Object[] params) {
            final int channel = (Integer) params[0];
            final int stream = (Integer) params[1];
            final int recordType = (Integer) params[2];
            final NET_TIME start = (NET_TIME) params[3];
            final NET_TIME end = (NET_TIME)params[4];

            doStopPlayBack();
            boolean bRet = initSurfaceView(stream, recordType);
            if (bRet) {
                bRet = doPlayBack(channel, start, end);
            }
            return bRet;
        }
        @Override
        protected void onPostExecute(Boolean result){
            super.onPostExecute(result);

            if (!result) {
                mSurface.getHolder().removeCallback(mSurfaceCallback);
            }
            if (this.taskDone!=null){
                this.taskDone.onTaskDone(result);
            }
            if (dialog!=null && dialog.isShowing()){
                dialog.dismiss();
            }
        }
    }

    private boolean doPlayBack(int channelID, NET_TIME startTime,NET_TIME endTime){
        if (mLoginHandler == 0)
            return false;

        logTime("startTime", startTime);
        logTime("endTime", endTime);
        mDataCallBack = new PlayBackDataCallBack();
        mPlayHandler = INetSDK.PlayBackByTimeEx(mLoginHandler, channelID, startTime, endTime, mPosCallBack, mDataCallBack);

        if(mPlayHandler == 0) {
            IPlaySDK.PLAYStop(mPort);
            IPlaySDK.PLAYCloseStream(mPort);
            IPlaySDK.PLAYStopSoundShare(mPort);
            nErrorCode = INetSDK.GetLastError();
            return false;
        }

        IPlaySDK.PLAYSetPlayedTimeEx(mPort,0);
        IPlaySDK.PLAYResetBuffer(mPort,EMPTY_BIT_STREAM_LIBRARY);
        IPlaySDK.PLAYResetBuffer(mPort,EMPTY_PLAY_QUEUE);

        return true;
    }

    /************************************************************************************************************************/
    /*                                          API初始化（api initialize）
    /***********************************************************************************************************************/
    ///initialize player view
    ///初始化播放控件
    private boolean initSurfaceView(int streamType, int recordType){
        mPort = IPlaySDK.PLAYGetFreePort();
        int record = NET_RECORD_TYPE.NET_RECORD_TYPE_ALL;  ///默认全部类型。  default all type.
        if (recordType == 1) {
            record = NET_RECORD_TYPE.NET_RECORD_TYPE_ALARM; ///报警   alarm.
        }

        if (!setDeviceMode(streamType, record)) {
            Log.d(TAG,"playback setDeviceMode failed.");
            return false;
        }
        if (mSurface == null) {
            Log.d(TAG,"the parameter Surface is null");
            return false;
        }
        mSurface.getHolder().addCallback(mSurfaceCallback);

        return initPlayer();
    }

    /// set device mode
    /// 设置设备模式
    private boolean setDeviceMode(int streamType, int recordType){
        if(!INetSDK.SetDeviceMode(mLoginHandler, EM_USEDEV_MODE.SDK_RECORD_STREAM_TYPE,streamType))
            return false;
        if( !INetSDK.SetDeviceMode(mLoginHandler, EM_USEDEV_MODE.SDK_RECORD_TYPE, recordType))
            return false;
        return true;
    }

    /// initialize player
    /// 回放初始化
    private boolean initPlayer(){
        if (!(IPlaySDK.PLAYOpenStream(mPort,null,0,STREAM_BUFFER_SIZE) == 0 ? false:true))
            return false;
        IPlaySDK.PLAYSetStreamOpenMode(mPort, Constants.STREAME_FILE);
        if(!(IPlaySDK.PLAYPlay(mPort,mSurface) == 0 ? false:true)) {
            IPlaySDK.PLAYCloseStream(mPort);
            return false;
        }
        int result = IPlaySDK.PLAYPlaySoundShare(mPort);
        if (result == 0) {
            IPlaySDK.PLAYStop(mPort);
            IPlaySDK.PLAYCloseStream(mPort);
            return false;
        }
        return true;
    }

    /************************************************************************************************************************/
    /*                                          回放接口或回调（playback interface or callback）
    /***********************************************************************************************************************/
    /// set playback pos callback
    /// 设置回放位置回调
    public void setPosCallBack(CB_fDownLoadPosCallBack callBack){
        this.mPosCallBack = callBack;
    }

    // playback data callback
    /// 回放数据回调
    private class PlayBackDataCallBack implements CB_fDataCallBack {

        @Override
        public int invoke(long l, int i, byte[] buffers, int i1) {
//            Log.d(TAG,"PlayBackDataCallBack ");
            return IPlaySDK.PLAYInputData(mPort, buffers, buffers.length);
        }
    }

    /// playback task done interface
    /// 回放任务完成接口
    public interface  OnPlayBackTaskDone{
        public void onTaskDone(boolean result);
    }

    /************************************************************************************************************************/
    /*                                          回放控制（playback control）
    /***********************************************************************************************************************/
    /// play control, play==false will pause, play==true will continue play
    /// 播放控制，play==false表示暂停；  play==true表示继续播放
    public boolean play(boolean bPlay){
        if (mPlayHandler == 0)
            return false;
        if(!bPlay){
            if(IPlaySDK.PLAYPause(mPort,(short)1) == 0)
                return false;
        }else {
            if(IPlaySDK.PLAYPause(mPort,(short)0) == 0)
                return false;
        }
        return INetSDK.PausePlayBack(mPlayHandler,!bPlay);
    }

    /// fast playback
    /// 快放
    public boolean playFast(){
        if (mPlayHandler == 0)
            return false;
        if (IPlaySDK.PLAYFast(mPort) == 0)
            return false;

        return INetSDK.FastPlayBack(mPlayHandler);
    }

    /// slow playback
    /// 慢放
    public boolean playSlow(){
        if (mPlayHandler == 0)
            return false;
        if (IPlaySDK.PLAYSlow(mPort) == 0)
            return false;

        return INetSDK.SlowPlayBack(mPlayHandler);
    }

    /// normal playback
    /// 常速回放
    public boolean playNormal(){
        if (mPlayHandler == 0)
            return false;
        if (IPlaySDK.PLAYPlay(mPort,mSurface) == 0)
            return false;

        return INetSDK.NormalPlayBack(mPlayHandler);
    }


    private class SurfaceCallback implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            IPlaySDK.InitSurface(mPort,mSurface);
            Log.d(TAG,"playback surfaceCreated");
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Log.d(TAG,"playback surfaceChanged");
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.d(TAG,"playback surfaceDestroyed");
        }
    }

    /************************************************************************************************************************/
    /*                                          其他（others）
    /***********************************************************************************************************************/
    /// get recordfile type
    /// 获取录像文件类型
    public List getRecordFileTypeList() {
        ArrayList<String> outputList = new ArrayList<String>();
        String[] recordFileTypes = mContext.getResources().getStringArray(R.array.record_file_type);
        for (int i = 0; i < recordFileTypes.length; i++) {
            outputList.add(recordFileTypes[i]);
        }
        return outputList;
    }

    ///要显示的码流类型
    public List getStreamTypeList(){
        ArrayList<String> list = new ArrayList<String>();
        String[] streamNames = mContext.getResources().getStringArray(R.array.stream_array);
        for (int i = 0; i < streamNames.length; i++){
            list.add(streamNames[i]);
        }
        return list;
    }

    /// get OSD time
    /// 获取OSD时间
    public NET_TIME getOSDtime(){
        byte[] rr = new byte[24];  // array size must large than 24 , because it is used to store 6 int. if not , QueryInfo will return false.
        Integer gf = new Integer(0);

        if(IPlaySDK.PLAYQueryInfo(mPort, Constants.PLAY_CMD_GetTime, rr, rr.length, gf) != 0) {

            NET_TIME time = new NET_TIME();
            time.dwYear = ((long)(rr[3]  & 0xff) << 24) + ((long)(rr[2]  & 0xff) << 16) + ((long)(rr[1]  & 0xff) << 8) + (long)(rr[0]  & 0xff);;
            time.dwMonth = ((long)(rr[7]  & 0xff) << 24) + ((long)(rr[6]  & 0xff) << 16) + ((long)(rr[5]  & 0xff) << 8) + (long)(rr[4]  & 0xff);
            time.dwDay = ((long)(rr[11] & 0xff) << 24) + ((long)(rr[10] & 0xff) << 16) + ((long)(rr[9]  & 0xff) << 8) + (long)(rr[8]  & 0xff);
            time.dwHour = ((long)(rr[15] & 0xff) << 24) + ((long)(rr[14] & 0xff) << 16) + ((long)(rr[13] & 0xff) << 8) + (long)(rr[12] & 0xff);
            time.dwMinute = ((long)(rr[19] & 0xff) << 24) + ((long)(rr[18] & 0xff) << 16) + ((long)(rr[17] & 0xff) << 8) + (long)(rr[16] & 0xff);
            time.dwSecond = ((long)(rr[23] & 0xff) << 24) + ((long)(rr[22] & 0xff) << 16) + ((long)(rr[21] & 0xff) << 8) + (long)(rr[20] & 0xff);
            return time;
        }
        return null;
    }

    /// print time
    /// 打印时间
    public void logTime(String head , NET_TIME time){
        Log.i(TAG,head+" Year:"+time.dwYear+";Month:"+time.dwMonth+";Day:"+time.dwDay+
                ";Hour:"+time.dwHour+";Minute:"+time.dwMinute+";Second:"+time.dwSecond);
    }
}