package com.company.netsdk.module;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;

import com.company.NetSDK.CB_pfAudioDataCallBack;
import com.company.NetSDK.EM_USEDEV_MODE;
import com.company.NetSDK.FinalVar;
import com.company.NetSDK.INetSDK;
import com.company.NetSDK.NET_SPEAK_PARAM;
import com.company.NetSDK.NET_TALK_TRANSFER_PARAM;
import com.company.NetSDK.SDKDEV_TALKFORMAT_LIST;
import com.company.NetSDK.SDK_TALK_CODING_TYPE;
import com.company.PlaySDK.IPlaySDK;
import com.company.PlaySDK.IPlaySDKCallBack;
import com.company.netsdk.R;
import com.company.netsdk.activity.NetSDKApplication;
import com.company.netsdk.activity.TalkActivity;
import com.company.netsdk.common.ToolKits;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 29779 on 2017/4/8.
 */
public class TalkModule {
    SDKDEV_TALKFORMAT_LIST mTalkFormatList = new SDKDEV_TALKFORMAT_LIST();
    public long mTalkHandle = 0;
    String errMsg = "";
    boolean mOpenAudioRecord = false;
    boolean bTransfer = false;
    CB_pfAudioDataCallBack cbAudioDataCallBack = null;
    IPlaySDKCallBack.pCallFunction cbAudioRecord = null;
    ArrayList<String> channelList = new ArrayList<String>();
    NetSDKApplication app;
    Context mContext;
    Resources res;
    int nPort = 99;

    public TalkModule(Context context) {
        this.mContext = context;
        res = mContext.getResources();
        app = ((NetSDKApplication) ((AppCompatActivity) mContext).getApplication());
        cbAudioDataCallBack = new AudioDataCallBack();
        cbAudioRecord = new AudioRecordCallBack();
    }


    public boolean startTalk() {
        ///Set talk encode type
        ///设置对讲编码类型
        mTalkFormatList.type[0].encodeType = SDK_TALK_CODING_TYPE.SDK_TALK_PCM;
        mTalkFormatList.type[0].dwSampleRate = 8000;
        mTalkFormatList.type[0].nAudioBit = 16;
        mTalkFormatList.type[0].nPacketPeriod = 25;
        if (!INetSDK.SetDeviceMode(app.getLoginHandle(), EM_USEDEV_MODE.SDK_TALK_ENCODE_TYPE, mTalkFormatList.type[0])) {
            ToolKits.writeErrorLog("Set Talk Encode Mode Failed!");
            errMsg = res.getString(R.string.set_talk_encode_mode) + res.getString(R.string.info_failed);
            return false;
        }

        ///Set talk transfer mode
        ///设置对讲转发模式
        bTransfer = ((TalkActivity) mContext).mSelectTransferMode.getSelectedItemPosition() == 1;
        NET_TALK_TRANSFER_PARAM mTalkTransfer = new NET_TALK_TRANSFER_PARAM();
        mTalkTransfer.bTransfer = bTransfer;
        if (!INetSDK.SetDeviceMode(app.getLoginHandle(), EM_USEDEV_MODE.SDK_TALK_TRANSFER_MODE, mTalkTransfer)) {
            ToolKits.writeErrorLog("Set Transfer Mode Failed!");
            errMsg = res.getString(R.string.set_transfer_mode) + res.getString(R.string.info_failed);
            return false;
        }

        if (bTransfer) {
            int chn = ((TalkActivity) mContext).mSelectTransferChn.getSelectedItemPosition();
            if (!INetSDK.SetDeviceMode(app.getLoginHandle(), EM_USEDEV_MODE.SDK_TALK_TALK_CHANNEL, chn)) {
                ToolKits.writeErrorLog("Set Transfer Channel Failed!");
                errMsg = res.getString(R.string.set_transfer_channel) + res.getString(R.string.info_failed);
                return false;
            }
        }

        nPort = 99;
        return talk();
    }

    public boolean startClientTalk() {
        ///Set talk mode
        ///设置对讲模式
        mTalkFormatList.type[0].encodeType = SDK_TALK_CODING_TYPE.SDK_TALK_PCM;
        mTalkFormatList.type[0].dwSampleRate = 8000;
        mTalkFormatList.type[0].nAudioBit = 16;
        mTalkFormatList.type[0].nPacketPeriod = 25;
        if( ! INetSDK.SetDeviceMode(app.getLoginHandle(), EM_USEDEV_MODE.SDK_TALK_ENCODE_TYPE, mTalkFormatList.type[0]) ) {
            ToolKits.writeErrorLog("Set Talk Encode Mode Failed!");
            errMsg = res.getString(R.string.set_talk_encode_mode) + res.getString(R.string.info_failed);
            return false;
        }

        if( ! INetSDK.SetDeviceMode(app.getLoginHandle(), EM_USEDEV_MODE.SDK_TALK_CLIENT_MODE, null)) {
            ToolKits.writeErrorLog("Set Talk Client Mode Failed!");
            errMsg = res.getString(R.string.set_talk_client_mode) + res.getString(R.string.info_failed);
            return false;
        }

        NET_SPEAK_PARAM stParam = new NET_SPEAK_PARAM();
        stParam.nMode = 0;
        stParam.nEnableWait = 0;
        if( ! INetSDK.SetDeviceMode(app.getLoginHandle(), EM_USEDEV_MODE.SDK_TALK_SPEAK_PARAM, stParam)) {
            ToolKits.writeErrorLog("Set Talk Speak Param Failed!");
            errMsg = res.getString(R.string.set_talk_speak_param) + res.getString(R.string.info_failed);
            return false;
        }

        nPort = 0;
        return talk();
    }

    public boolean isTalking() {
        return mTalkHandle != 0;
    }

    private boolean talk() {
        ///Start talk
        ///开始对讲
        mTalkHandle = INetSDK.StartTalkEx(app.getLoginHandle(), cbAudioDataCallBack);
        if (0 != mTalkHandle) {
            ///Start audio record
            ///开始音频录音
            boolean bSuccess = startAudioRecord();
            if (!bSuccess) {
                ToolKits.writeLog("Start Audio Record Failed!");
                INetSDK.StopTalkEx(mTalkHandle);
                errMsg = res.getString(R.string.start_audio_record) + res.getString(R.string.info_failed);
                return false;
            } else {
                mOpenAudioRecord = true;
                errMsg = res.getString(R.string.start_talk);
            }
        } else {
            ToolKits.writeErrorLog("Start Talk Failed!");
            errMsg = res.getString(R.string.talk) + res.getString(R.string.info_failed);
            return false;
        }
        return true;
    }

    public boolean stopTalk() {
        if(mOpenAudioRecord) {
            stopAudioRecord();
        }

        if(0 != mTalkHandle) {
            ///Stop audio talk to the device
            ///停止设备的音频对讲
            if(INetSDK.StopTalkEx(mTalkHandle)) {
                mTalkHandle = 0;
                errMsg = res.getString(R.string.stop_talk);
            } else {
                return false;
            }
        }
        return true;
    }

    ///Talk callback
    ///对讲回调函数
    public class AudioDataCallBack implements CB_pfAudioDataCallBack
    {
        public void invoke(long lTalkHandle, byte pDataBuf[], byte byAudioFlag)
        {
//            ToolKits.writeLog("AudioDataCallBack received " + byAudioFlag);
            if(mTalkHandle == lTalkHandle)
            {
                ///byAudioFlag Audio data home sign, 0:means audio data collected by local audio recording list; 1:means received audio data sent by devie
                ///byAudioFlag 音频数据归属标志, 0:表示是本地录音库采集的音频数据; 1:表示收到的设备发过来的音频数据
                if(1 == byAudioFlag)
                {

                    ///You can use PLAY SDK to decode to get PCM and then encode to other formats if you get a uniform formats.
                    ///通过PLAY SDK解码获取PCM，并且如果你获取统一格式，请对其他格式进行编码
                    IPlaySDK.PLAYInputData(nPort, pDataBuf, pDataBuf.length);
                }
            }
        }
    }

    private boolean startAudioRecord()	{

        boolean bRet = false;

        ///Then specify frame length
        ///指定的帧长度
        int nFrameLength = 1024;

        ///Then call PLAYSDK library to begin recording audio
        ///调用PLAYSDK库，来开始录制音频
        boolean bOpenRet = IPlaySDK.PLAYOpenStream(nPort,null,0,1024*1024) == 0? false : true;
        if(bOpenRet) {
            boolean bPlayRet = IPlaySDK.PLAYPlay(nPort, (Surface)null) == 0? false : true;
            if(bPlayRet) {
                IPlaySDK.PLAYPlaySoundShare(nPort);
                boolean bSuccess = IPlaySDK.PLAYOpenAudioRecord(cbAudioRecord,mTalkFormatList.type[0].nAudioBit,
                        mTalkFormatList.type[0].dwSampleRate, nFrameLength, 0) == 0? false : true;
                if(bSuccess) {
                    bRet = true;
                    ToolKits.writeLog("nAudioBit = " + mTalkFormatList.type[0].nAudioBit + "\n" + "dwSampleRate = "
                                      + mTalkFormatList.type[0].dwSampleRate + "\n" + "nFrameLength = " + nFrameLength + "\n");
                } else {
                    IPlaySDK.PLAYStopSoundShare(nPort);
                    IPlaySDK.PLAYStop(nPort);
                    IPlaySDK.PLAYCloseStream(nPort);
                }
            } else {
                IPlaySDK.PLAYCloseStream(nPort);
            }
        }

        return bRet;
    }

    private void stopAudioRecord()	{
        mOpenAudioRecord = false;
        IPlaySDK.PLAYCloseAudioRecord();
        IPlaySDK.PLAYStop(nPort);
        IPlaySDK.PLAYStopSoundShare(nPort);
        IPlaySDK.PLAYCloseStream(nPort);
    }

    public class AudioRecordCallBack implements IPlaySDKCallBack.pCallFunction {
        public void invoke(byte[] pDataBuffer,int nBufferLen, long pUserData) {
            try
            {
                ///encode
                ///编码
//                ToolKits.writeLog("AudioRecord send " + nBufferLen);
                byte encode[] = AudioRecord(pDataBuffer);

                ///send user's audio data to device.
                ///发送语音数据到设备.
                long lSendLen = INetSDK.TalkSendData(mTalkHandle, encode);
                if(lSendLen != (long)encode.length) {
                    ///Error occurred when sending the user audio data to the device.
                    ///发送音频数据给设备失败.
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    byte[] AudioRecord(byte[] pDataBuffer) {
        int DataLength = pDataBuffer.length;
        byte pCbData[] = null;
        pCbData = new byte[8+DataLength];

        pCbData[0] = (byte) 0x00;
        pCbData[1] = (byte) 0x00;
        pCbData[2] = (byte) 0x01;
        pCbData[3] = (byte) 0xF0;
        pCbData[4] = (byte) 0x0C;

        pCbData[5] = 0x02; // 8k
        pCbData[6]=(byte)(DataLength & 0x00FF);
        pCbData[7]=(byte)(DataLength >> 8);
        System.arraycopy(pDataBuffer, 0, pCbData, 8, DataLength);
        return pCbData;
    }

    ///Get talk format list，this demo only use PCM.
    ///获取语音对讲格式列表, 本demo只用到了PCM.
    public void getCodeType() {
        if(!INetSDK.QueryDevState(app.getLoginHandle(), FinalVar.SDK_DEVSTATE_TALK_ECTYPE, mTalkFormatList, 4000)) {
            ToolKits.writeLog("QueryDevState TalkList Failed!");
            return;
        }
    }

    /// Get Transfer Mode
    /// 获取转发模式
    public List getTransferModeList() {
        ArrayList<String> outputList = new ArrayList<String>();
        String[] transferModeNames = res.getStringArray(R.array.transfer_mode_array);
        for (int i = 0; i < transferModeNames.length; i++) {
            outputList.add(transferModeNames[i]);
        }
        return outputList;
    }

    ///Is Transfer Mode
    ///是否转发模式
    public boolean isTransfer(){
        return bTransfer;
    }

    ///Get Channel Num
    ///获取通道数量
    private int getChannel(){
        if (app == null)
            return 0;
        return app.getDeviceInfo().nChanNum;
    }

    ///Get Error Msg
    ///获取错误信息
    public String getErrMsg() {
        return errMsg;
    }

    /// Get Channel List
    ///获取要显示的通道号
    public List getChannelList(){
        if (!channelList.isEmpty()) {
            return channelList;
        }
        for (int i=0;i<getChannel();i++){
            channelList.add(res.getString(R.string.channel)+(i));
        }
        return channelList;
    }
}
