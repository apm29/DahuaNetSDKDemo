package com.company.netsdk.module;

import android.content.Context;
import android.os.AsyncTask;

import com.company.NetSDK.AV_CFG_ChannelName;
import com.company.NetSDK.FinalVar;
import com.company.NetSDK.INetSDK;
import com.company.NetSDK.NET_EM_CFG_OPERATE_TYPE;
import com.company.NetSDK.NET_OSD_CHANNEL_TITLE;
import com.company.NetSDK.NET_OSD_TIME_TITLE;
import com.company.netsdk.R;
import com.company.netsdk.activity.NetSDKApplication;
import com.company.netsdk.common.DialogProgress;
import com.company.netsdk.common.ToolKits;

/**
 * Created by 32940 on 2018/12/13.
 */
public class OSDModule {
    public NET_OSD_TIME_TITLE              timeTitle;
    public NET_OSD_CHANNEL_TITLE           channelTitle;
    private AV_CFG_ChannelName             channelTitleName;

    private Context mContext;
    private DialogProgress dialogProgress;
    private GetChannelTask getChannelTask;
    private SetChannelTask setChannelTask;
    private GetTimeTitleTask getTimeTitleTask;
    private SeTimeTitleTask setTimeTitleTask;

    public OSDModule(Context context) {
        timeTitle = new NET_OSD_TIME_TITLE();
        channelTitle = new NET_OSD_CHANNEL_TITLE();
        channelTitleName = new AV_CFG_ChannelName();
        this.mContext = context;
        dialogProgress = new DialogProgress(context);
    }

    ///////////////////////////////////////////////// 时间标题 ////////////////////////////////////////////////////////
    /**
     * 获取叠加时间标题属性
     * @param channel       通道
     */
    public boolean getTimeTitle(int channel) {
        timeTitle = null;
        timeTitle = new NET_OSD_TIME_TITLE();

        timeTitle.emOsdBlendType = 1; // 主码流

        // 获取
        if(!INetSDK.GetConfig(NetSDKApplication.getInstance().getLoginHandle(), NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_TIMETITLE, channel, timeTitle, 5000, null)) {
            ToolKits.writeErrorLog("Get Faile" );
            return false;
        }

        return true;
    }

    /**
     * 设置叠加时间标题属性
     * @param channel       通道
     * @param bBlend        是否叠加
     * @param bShowWeek     是否显示星期
     */
    public boolean setTimeTitle(int channel,  boolean bBlend, boolean bShowWeek) {
        timeTitle.emOsdBlendType = 1;            // 主码流
        timeTitle.bEncodeBlend = bBlend;         // 是否叠加
        timeTitle.bShowWeek = bShowWeek;         // 是否显示星期

        if(!INetSDK.SetConfig(NetSDKApplication.getInstance().getLoginHandle(), NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_TIMETITLE, channel, timeTitle, 5000, null, null)) {
            return false;
        }

        return true;
    }

    /////////////////////////////////////////////////// 通道标题 /////////////////////////////////////////////////////
    /**
     * 获取叠加通道标题属性
     * @param channel       通道
     */
    public boolean getChannelTitle(int channel) {
        channelTitle = null;
        channelTitle = new NET_OSD_CHANNEL_TITLE();

        channelTitle.emOsdBlendType = 1; // 主码流

        // 获取
        if(!INetSDK.GetConfig(NetSDKApplication.getInstance().getLoginHandle(), NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_CHANNELTITLE, channel, channelTitle, 5000, null)) {
            ToolKits.writeLog("get bEncodeBlend failed");
            return false;
        }

        ToolKits.writeLog("bEncodeBlend:" + channelTitle.bEncodeBlend);
        return true;
    }

    /**
     * 设置叠加通道标题属
     * @param channel       通道
     * @param bBlend        是否叠加
     */
    public boolean setChannelTitle(int channel,  boolean bBlend) {
        channelTitle.emOsdBlendType = 1;              // 主码流
        channelTitle.bEncodeBlend = bBlend;           // 是否叠加

        if(!INetSDK.SetConfig(NetSDKApplication.getInstance().getLoginHandle(), NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_CHANNELTITLE, channel, channelTitle, 5000, null, null)) {
            return false;
        }
        return true;
    }

    ////////////////////////////////////////////////// 通道名称 ////////////////////////////////////////////////////

    /**
     * 获取通道名称
     * @param channel 通道
     */
    public boolean getChannelName(int channel) {
        channelTitleName = null;
        channelTitleName = new AV_CFG_ChannelName();
        if(!ToolKits.GetDevConfig(FinalVar.CFG_CMD_CHANNELTITLE, channelTitleName, NetSDKApplication.getInstance().getLoginHandle(), channel, 2048)) {
            return false;
        }
        ToolKits.writeLog("channelName:" + new String(channelTitleName.szName).trim());
        return true;
    }

    /**
     * 设置通道名称
     * @param channel 通道
     */
    public boolean setChannelName(int channel, String channelName) {
        ToolKits.StringToByteArray(channelName, channelTitleName.szName);     // 通道名称
        if(!ToolKits.SetDevConfig(FinalVar.CFG_CMD_CHANNELTITLE, channelTitleName,  NetSDKApplication.getInstance().getLoginHandle(), channel, 2048)) {
            return false;
        }

        return true;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    // 获取通道标题和名称
    public interface GetChannelInfoListener {
        void onChannelInfoListener(boolean bRet1, boolean bRet2, boolean bEncodeBlend, String channelName);
    }

    public GetChannelInfoListener getChannelInfoListener;

    public void addGetChannelInfoListener(GetChannelInfoListener listener) {
        this.getChannelInfoListener = listener;
    }

    /**
     * 获取通道标题和通道名称
     * @param channel       通道号
     */
    public void getChannelInfo(int channel) {
        getChannelTask = new GetChannelTask(channel);
        getChannelTask.execute();
    }

    class GetChannelTask extends AsyncTask<Integer, Integer, Boolean[]> {
        private int channel;

        public GetChannelTask(int channel) {
            this.channel = channel;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogProgress.setMessage(mContext.getString(R.string.waiting));
            dialogProgress.setSpinnerType(DialogProgress.FADED_ROUND_SPINNER);
            dialogProgress.setCancelable(false);
            dialogProgress.show();
        }

        @Override
        protected Boolean[] doInBackground(Integer... params) {
            boolean bRet1 = getChannelTitle(channel);
            boolean bRet2 = getChannelName(channel);

            return new Boolean[]{bRet1, bRet2};
        }

        @Override
        protected void onPostExecute(Boolean[] aBoolean) {
            dialogProgress.dismiss();

            boolean bEncodeBlend = false;

            if(aBoolean[0]) {
                bEncodeBlend = channelTitle.bEncodeBlend;
            }

            String channelName = "";
            if(aBoolean[1]) {
                channelName = new String(channelTitleName.szName).trim();
            }
            getChannelInfoListener.onChannelInfoListener(aBoolean[0], aBoolean[1], bEncodeBlend, channelName);
        }

        @Override
        protected void onCancelled() {
            if(dialogProgress.isShowing()) {
                dialogProgress.dismiss();
            }
        }
    }

    // 设置通道标题和名称
    public interface SetChannelInfoListener {
        void onChannelInfoListener(boolean bRet1, boolean bRet2);
    }

    public SetChannelInfoListener setChannelInfoListener;

    public void addSetChannelInfoListener(SetChannelInfoListener listener) {
        this.setChannelInfoListener = listener;
    }

    /**
     * 设置通道标题和通道名称
     * @param channel       通道号
     * @param bEncodeBlend 叠加使能
     * @param channelName  通道名称
     */
    public void setChannelInfo(int channel, boolean bEncodeBlend, String channelName) {
        setChannelTask = new SetChannelTask(channel, bEncodeBlend, channelName);
        setChannelTask.execute();
    }

    class SetChannelTask extends AsyncTask<Integer, Integer, Boolean[]> {
        private int          channel;
        private boolean     bEncodeBlend;
        private String       channelName;

        public SetChannelTask(int channel,boolean bEncodeBlend, String channelName) {
            this.channel = channel;
            this.bEncodeBlend = bEncodeBlend;
            this.channelName = channelName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogProgress.setMessage(mContext.getString(R.string.waiting));
            dialogProgress.setSpinnerType(DialogProgress.FADED_ROUND_SPINNER);
            dialogProgress.setCancelable(false);
            dialogProgress.show();
        }

        @Override
        protected Boolean[] doInBackground(Integer... params) {
            boolean bRet1 = setChannelTitle(channel,bEncodeBlend);
            boolean bRet2 = setChannelName(channel, channelName);

            return new Boolean[]{bRet1, bRet2};
        }

        @Override
        protected void onPostExecute(Boolean[] aBoolean) {
            dialogProgress.dismiss();

            setChannelInfoListener.onChannelInfoListener(aBoolean[0], aBoolean[1]);
        }

        @Override
        protected void onCancelled() {
           if(dialogProgress.isShowing()) {
               dialogProgress.dismiss();
           }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    // 获取时间标题
    public interface GetTimeTitleInfoListener {
        void onTimeTitleInfoListener(boolean bRet, boolean bBlend, boolean bShowWeek);
    }

    public GetTimeTitleInfoListener getTimeTitleInfoListener;

    public void addGetTimeTitleInfoListener(GetTimeTitleInfoListener listener) {
        this.getTimeTitleInfoListener = listener;
    }

    /**
     * 获取通道标题和通道名称
     * @param channel       通道号
     */
    public void getTimeTitlelInfo(int channel) {
        getTimeTitleTask = new GetTimeTitleTask(channel);
        getTimeTitleTask.execute();
    }

    class GetTimeTitleTask extends AsyncTask<Integer, Integer, Boolean> {
        private int channel;

        public GetTimeTitleTask(int channel) {
            this.channel = channel;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogProgress.setMessage(mContext.getString(R.string.waiting));
            dialogProgress.setSpinnerType(DialogProgress.FADED_ROUND_SPINNER);
            dialogProgress.setCancelable(false);
            dialogProgress.show();
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            boolean bRet = getTimeTitle(channel);

            return bRet;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            dialogProgress.dismiss();

            boolean bBlend = false;
            boolean bShowWeek = false;

            if(aBoolean) {
                bBlend = timeTitle.bEncodeBlend;
                bShowWeek = timeTitle.bShowWeek;
            }

            getTimeTitleInfoListener.onTimeTitleInfoListener(aBoolean, bBlend, bShowWeek);
        }

        @Override
        protected void onCancelled() {
            if(dialogProgress.isShowing()) {
                dialogProgress.dismiss();
            }
        }
    }

    // 设置时间标题
    public interface SetTimeTitleInfoListener {
        void onTimeTitleInfoListener(boolean bRet);
    }

    public SetTimeTitleInfoListener setTimeTitleInfoListener;

    public void addSetTimeTitleInfoListener(SetTimeTitleInfoListener listener) {
        this.setTimeTitleInfoListener = listener;
    }

    /**
     * 设置通道标题和通道名称
     * @param channel       通道号
     * @param bBlend        叠加使能
     * @param bShowWeek    是否显示星期
     */
    public void setTimeTitleInfo(int channel, boolean bBlend, boolean bShowWeek) {
        setTimeTitleTask = new SeTimeTitleTask(channel, bBlend, bShowWeek);
        setTimeTitleTask.execute();
    }

    class SeTimeTitleTask extends AsyncTask<Integer, Integer, Boolean> {
        private int          channel;
        private boolean     bBlend;
        private boolean     bShowWeek;

        public SeTimeTitleTask(int channel, boolean bBlend, boolean bShowWeek) {
            this.channel = channel;
            this.bBlend = bBlend;
            this.bShowWeek = bShowWeek;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogProgress.setMessage(mContext.getString(R.string.waiting));
            dialogProgress.setSpinnerType(DialogProgress.FADED_ROUND_SPINNER);
            dialogProgress.setCancelable(false);
            dialogProgress.show();
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            boolean bRet = setTimeTitle(channel, bBlend, bShowWeek);

            return bRet;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            dialogProgress.dismiss();

            setTimeTitleInfoListener.onTimeTitleInfoListener(aBoolean);
        }

        @Override
        protected void onCancelled() {
            if(dialogProgress.isShowing()) {
                dialogProgress.dismiss();
            }
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////

    public void destroy() {
        if(getChannelTask != null && getChannelTask.getStatus() == AsyncTask.Status.RUNNING) {
            getChannelTask.cancel(false);
        }


        if(setChannelTask != null && setChannelTask.getStatus() == AsyncTask.Status.RUNNING) {
            setChannelTask.cancel(false);
        }

        if(getTimeTitleTask != null && getTimeTitleTask.getStatus() == AsyncTask.Status.RUNNING) {
            getTimeTitleTask.cancel(false);
        }


        if(setTimeTitleTask != null && setTimeTitleTask.getStatus() == AsyncTask.Status.RUNNING) {
            setTimeTitleTask.cancel(false);
        }
    }
}
