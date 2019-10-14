package com.company.netsdk.module;

import android.content.Context;
import android.content.res.Resources;

import com.company.NetSDK.*;
import com.company.netsdk.R;
import com.company.netsdk.common.ToolKits;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 32940 on 2017/6/29.
 */
public class DownLoadRecordFileModule {
    public long lDownloadHandle = 0;
    Context mContext;
    Resources res;

    private int nErrorCode = 0;

    public DownLoadRecordFileModule(Context context) {
        this.mContext = context;
        res = mContext.getResources();
    }

    public boolean startDownLocadRecord(long loginHandle, int nChn, int stream, NET_TIME startTime, NET_TIME endTime, CB_fTimeDownLoadPosCallBack cb_fTimeDownLoadPosCallBack) {

        if(INetSDK.SetDeviceMode(loginHandle, EM_USEDEV_MODE.SDK_RECORD_STREAM_TYPE, stream)) {
            ToolKits.writeLog("Set Stream Succeed!" + stream);
        } else {
            ToolKits.writeErrorLog("Set Stream Failed!");
            return false;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date());
        String recFileName = mContext.getExternalFilesDir(null).getAbsolutePath()+"/" + time.replace(":","_") + " download.dav";
        lDownloadHandle = INetSDK.DownloadByTimeEx(loginHandle, nChn, EM_QUERY_RECORD_TYPE.EM_RECORD_TYPE_ALL,
                startTime, endTime, recFileName, cb_fTimeDownLoadPosCallBack, null, null);

        if(lDownloadHandle == 0) {
            ToolKits.writeErrorLog("DownLoad Failed.");
            nErrorCode = INetSDK.GetLastError();
        }
        return lDownloadHandle ==0 ? false : true;
    }

    public boolean stopDownLoadRecord() {
        if(lDownloadHandle == 0) {
            return false;
        }
        nErrorCode = 0;
        boolean bRet = INetSDK.StopDownload(lDownloadHandle);
        if(bRet){
            lDownloadHandle = 0;
        }

        return bRet;
    }

    ///要显示的码流类型
    public List getStreamTypeList(){
        ArrayList<String> list = new ArrayList<String>();
        String[] streamNames = res.getStringArray(R.array.stream_array);
        for (int i = 0; i < 3; i++){
            list.add(streamNames[i]);
        }
        return list;
    }

    /// is no record
    /// 是否无记录
    public boolean isNoRecord(){
        return nErrorCode == FinalVar.NET_NO_RECORD_FOUND;
    }

}
