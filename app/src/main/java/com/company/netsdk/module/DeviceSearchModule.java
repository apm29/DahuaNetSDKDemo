package com.company.netsdk.module;

import android.content.Context;

import com.company.NetSDK.CB_fSearchDevicesCB;
import com.company.NetSDK.INetSDK;

/**
 * Created by 32940 on 2018/6/29.
 */
public class DeviceSearchModule {
    public long lDevSearchHandle = 0;
    Context mContext;

    public DeviceSearchModule(Context context) {
        this.mContext = context;
    }

    ///Search device
    ///设备搜索
    public long startSearchDevices(CB_fSearchDevicesCB callback) {
        if (callback == null)
            throw new NullPointerException("callback parameter is null");
        lDevSearchHandle = INetSDK.StartSearchDevices(callback);

        return lDevSearchHandle;
    }

    ///Stop search device
    ///停止设备搜索
    public void stopSearchDevices() {
        if(lDevSearchHandle != 0) {
            INetSDK.StopSearchDevices(lDevSearchHandle);
            lDevSearchHandle = 0;
        }
    }
}
