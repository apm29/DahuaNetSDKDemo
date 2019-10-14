package com.company.netsdk.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.company.NetSDK.CtrlType;
import com.company.NetSDK.INetSDK;
import com.company.NetSDK.NET_CTRL_ACCESS_OPEN;
import com.company.NetSDK.NET_TIME;
import com.company.netsdk.R;
import com.company.netsdk.activity.NetSDKApplication;
import com.company.netsdk.common.ToolKits;

import java.text.SimpleDateFormat;

/**
 * Created by 29779 on 2017/4/8.
 */
public class DeviceControlModule {
    NetSDKApplication app;
    Context mContext;
    private int resId;

    public DeviceControlModule(Context context) {
        this.mContext = context;
        app = ((NetSDKApplication)((AppCompatActivity)mContext).getApplication());
        resId = R.string.NET_ERROR;
    }

    ///Reboot device
    ///重启设备
    public boolean reboot() {
        boolean bRet = false;
        bRet = INetSDK.ControlDevice(app.getLoginHandle(), CtrlType.SDK_CTRL_REBOOT, null, 3000);
        if (bRet) {
            resId = R.string.device_control_restart_succeed;
        }else {
            ToolKits.writeLog("ControlDevice Reboot Failed!");
            resId = R.string.device_control_restart_failed;
        }
        return bRet;
    }

    ///Set device time
    ///时间同步
    public boolean setTime() {
        NET_TIME deviceTime = new NET_TIME();

        ///Get the current system time of the phone
        ///获取当前的手机系统时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new java.util.Date());

        String[] dateTime = date.split(" ");
        String[] mDate1 = dateTime[0].split("-");
        String[] mDate2 = dateTime[1].split(":");
        deviceTime.dwYear = Integer.parseInt(mDate1[0]);
        deviceTime.dwMonth = Integer.parseInt(mDate1[1]);
        deviceTime.dwDay = Integer.parseInt(mDate1[2]);
        deviceTime.dwHour = Integer.parseInt(mDate2[0]);
        deviceTime.dwMinute = Integer.parseInt(mDate2[1]);
        deviceTime.dwSecond = Integer.parseInt(mDate2[2]);

        boolean bRet = false;
        bRet = INetSDK.SetupDeviceTime(app.getLoginHandle(), deviceTime);
        if (bRet) {
            resId = R.string.device_control_settime_succeed;
        }else {
            ToolKits.writeLog("SetupDeviceTime Failed!");
            resId = R.string.device_control_settime_failed;
        }
        return bRet;
    }

    ///Get device time
    ///获取设备时间
    public String getTime() {
        NET_TIME deviceTime = new NET_TIME();

        if (!INetSDK.QueryDeviceTime(app.getLoginHandle(), deviceTime, 3000)) {
            ToolKits.writeLog("QueryDeviceTime Failed!");
            resId = R.string.device_control_gettime_failed;
            return null;
        }
        String date = deviceTime.toString();
        return date;
    }

    ///Access Open
    ///门禁开门
    public boolean accessOpen(int nChannelID) {
        NET_CTRL_ACCESS_OPEN stuAccessOpen = new NET_CTRL_ACCESS_OPEN();
        stuAccessOpen.nChannelID = nChannelID;
        boolean bRet = INetSDK.ControlDevice(app.getLoginHandle(), CtrlType.SDK_CTRL_ACCESS_OPEN, stuAccessOpen, 3000);
        if (!bRet) {
            ToolKits.writeLog("ControlDevice Access Open Failed!");
        }
        return bRet;
    }

    ///Get Res ID
    ///获取资源ID
    public int getResId() {
        return resId;
    }
}
