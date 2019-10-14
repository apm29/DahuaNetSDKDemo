package com.company.netsdk.module;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.company.NetSDK.CB_fRealDataCallBackEx;
import com.company.NetSDK.INetSDK;
import com.company.NetSDK.SDK_RealPlayType;
import com.company.PlaySDK.IPlaySDK;
import com.company.netsdk.R;
import com.company.netsdk.activity.NetSDKApplication;
import com.company.netsdk.common.ToolKits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 29779 on 2017/4/8.
 */
public class LivePreviewDoubleChannelModule {
    HashMap<Long,Integer> handlersMapPorts = new HashMap<Long, Integer>();
    Map<Integer,Integer> streamTypeMap = new HashMap<Integer,Integer>();
    long playHandle_1 = 0;
    long playHandle_2 = 0;
    private final int STREAM_BUFFER_SIZE = 1024*1024*2;
    private CB_fRealDataCallBackEx mRealDataCallBackChannelOne;
    private CB_fRealDataCallBackEx mRealDataCallBackChannelTwo;
    Resources res;
    Context mContext;
    NetSDKApplication app;

    public LivePreviewDoubleChannelModule(Context context){
        mContext = context;
        res = mContext.getResources();
        app = ((NetSDKApplication)((AppCompatActivity)mContext).getApplication());
        initMap();
    }

    ///码流类型的hash
    private void initMap(){
        streamTypeMap.put(0, SDK_RealPlayType.SDK_RType_Realplay_0);
        streamTypeMap.put(1,SDK_RealPlayType.SDK_RType_Realplay_1);
    }

    public boolean multiPlay_channel1(int chn, int streamType,final SurfaceView view){
        final int port = IPlaySDK.PLAYGetFreePort();
        initSurfaceView(port,view);
        if (!openStream(view,port)){
            ToolKits.showMessage(mContext,res.getString(R.string.channel) + chn+1 + ":" + res.getString(R.string.open_stream));
            return false;
        }

        if (IPlaySDK.PLAYSetDelayTime(port, 500/*ms*/, 1000/*ms*/) == 0) {
            Log.d("multiPlay_channel1","SetDelayTime Failed");
        }

        playHandle_1 = INetSDK.RealPlayEx(app.getLoginHandle(),chn, streamTypeMap.get(streamType));
        if (0 == playHandle_1){
            ToolKits.showMessage(mContext, res.getString(R.string.channel) + chn+1 + ":" + res.getString(R.string.live_preview_failed));
            return false;
        }
        handlersMapPorts.put(playHandle_1,port);
        mRealDataCallBackChannelOne = new CB_fRealDataCallBackEx() {
            @Override
            public void invoke(long lRealHandle, int dwDataType, byte[] pBuffer, int dwBufSize, int param) {
                if (0==dwDataType){
                    IPlaySDK.PLAYInputData(port,pBuffer,pBuffer.length);
                }
            }
        };
        INetSDK.SetRealDataCallBackEx(playHandle_1, mRealDataCallBackChannelOne, 1);
        return true;
    }

    public boolean multiPlay_channel2(int chn, int streamType,final SurfaceView view){
        final int port = IPlaySDK.PLAYGetFreePort();
        initSurfaceView(port,view);
        if(!openStream(view,port)){
            ToolKits.showMessage(mContext,res.getString(R.string.channel) + chn+1 + ":" + res.getString(R.string.open_stream));
            return false;
        }

        if (IPlaySDK.PLAYSetDelayTime(port, 500/*ms*/, 1000/*ms*/) == 0) {
            Log.d("multiPlay_channel2","SetDelayTime Failed");
        }

        playHandle_2 = INetSDK.RealPlayEx(app.getLoginHandle(),chn,streamTypeMap.get(streamType));
        if (0 == playHandle_2){
            ToolKits.showMessage(mContext, res.getString(R.string.channel) + chn+1 + ":" + res.getString(R.string.live_preview_failed));
            return false;
        }
        handlersMapPorts.put(playHandle_2,port);
        mRealDataCallBackChannelTwo = new CB_fRealDataCallBackEx() {
            @Override
            public void invoke(long lRealHandle, int dwDataType, byte[] pBuffer, int dwBufSize, int param) {
                if (0==dwDataType){
                    IPlaySDK.PLAYInputData(port,pBuffer,pBuffer.length);
                }
            }
        };
        INetSDK.SetRealDataCallBackEx(playHandle_2, mRealDataCallBackChannelTwo, 1);
        return true;
    }

    private boolean openStream(final SurfaceView view,final int port){
        if (IPlaySDK.PLAYOpenStream(port,null,0,STREAM_BUFFER_SIZE) == 0){
            return false;
        }
        boolean result = IPlaySDK.PLAYPlay(port,view) == 0 ? false:true;
        if (!result){
            IPlaySDK.PLAYCloseStream(port);
            return false;
        }
        return true;
    }

    public void release(){
        stopMultiPlay(true);
        stopMultiPlay(false);

        handlersMapPorts.clear();
        handlersMapPorts = null;
        mContext = null;
        app = null;
    }

    public void stopMultiPlay(boolean first) {
        if (first) {
            if (playHandle_1 !=0) {
                INetSDK.StopRealPlayEx(playHandle_1);
                if (handlersMapPorts.containsKey(playHandle_1)) {
                    int port1 = handlersMapPorts.get(playHandle_1);
                    IPlaySDK.PLAYStop(port1);
                    IPlaySDK.PLAYCloseStream(port1);
                }
                handlersMapPorts.remove(playHandle_1);
                playHandle_1 = 0;
            }
        } else {
            if (playHandle_2 != 0) {
                INetSDK.StopRealPlayEx(playHandle_2);
                if (handlersMapPorts.containsKey(playHandle_2)) {
                    int port2 = handlersMapPorts.get(playHandle_2);
                    IPlaySDK.PLAYStop(port2);
                    IPlaySDK.PLAYCloseStream(port2);
                }
                handlersMapPorts.remove(playHandle_2);
                playHandle_2 = 0;
            }
        }
    }

    public void initSurfaceView(final int port, final SurfaceView view){
        view.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                IPlaySDK.InitSurface(port,view);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    ///获取通道数量
    public int getChannel(){
        if (app == null)
            return 0;
        return app.getDeviceInfo().nChanNum;
    }
    ///获取要显示的通道号
    public List getChannelList(){
        ArrayList<String> channelList = new ArrayList<String>();
        for (int i=0;i<getChannel();i++){
            channelList.add(res.getString(R.string.channel)+(i));
        }
        return channelList;
    }
    ///获取要显示的码流类型
    public List getStreamTypeList(int channel){
        ArrayList<String> list = new ArrayList<String>();
        String[] streamNames = res.getStringArray(R.array.stream_type_array);
        for (int i=0;i<2;i++){
            list.add(streamNames[i]);
        }
        return list;
    }

}
