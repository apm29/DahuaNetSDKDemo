package com.company.netsdk.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.company.NetSDK.EM_EVENT_SUB_CODE;
import com.company.NetSDK.EM_MOBILE_SERVER_TYPE;
import com.company.NetSDK.FinalVar;
import com.company.NetSDK.INetSDK;
import com.company.NetSDK.NET_MOBILE_PUSH_NOTIFY;
import com.company.NetSDK.NET_MOBILE_PUSH_NOTIFY_DEL;
import com.company.NetSDK.NET_OUT_DELETECFG;
import com.company.netsdk.R;
import com.company.netsdk.activity.NetSDKApplication;
import com.company.netsdk.common.PushHelper;
import com.company.netsdk.common.ToolKits;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by 29779 on 2017/4/8.
 */
public class AlarmPushModule {
    private final static String TAG = "AlarmPushModule";
    NetSDKApplication app;
    Context mContext;
    private int resId;

    public AlarmPushModule(Context context) {
        mContext = context;
        app = ((NetSDKApplication)((AppCompatActivity)mContext).getApplication());
        resId = R.string.NET_ERROR;
    }

    /**
     *  Subscribe Alarm Push, support VideoMotion
     */
    public boolean subscribe() {

        /// Get register id form google service
        String registerID = PushHelper.instance().getRegisterID(mContext);
        if (registerID == null) {
            Log.d(TAG, "not support google service.");
            resId = R.string.alarm_push_not_support_google_service;
            return false;
        }

        int nChnCount = app.getDeviceInfo().nChanNum;
        NET_MOBILE_PUSH_NOTIFY stNotify = new NET_MOBILE_PUSH_NOTIFY(nChnCount);

        ///default value
        long period = 500646880;
        String devName = "deviceName";

        //RegisterID
        StringToByteArray(registerID, stNotify.szRegisterID); // for device service to

        //serverType
        stNotify.emServerType = EM_MOBILE_SERVER_TYPE.EM_MOBILE_SERVER_TYPE_ANDROID;

        //PeriodOfValidity
        stNotify.nPeriodOfValidity = (int) period;

        //AuthServer -- invalid since google not supported C2DM any more
        StringToByteArray("https://www.google.com/accounts/ClientLogin", stNotify.szAuthServerAddr); //
        stNotify.nAuthServerPort = 443;

        //PushServer -- proxy server.
        StringToByteArray("https://cellphonepush.quickddns.com/gcm/send", stNotify.szPushServerAddr);
        stNotify.nPushServerPort = 443;

        // PushServer
        String PushServer = "https://android.googleapis.com/gcm/send";
        StringToByteArray(PushServer, stNotify.stuPushServerMain.szAddress);
        stNotify.stuPushServerMain.nPort = 443;

        // DevName
        StringToByteArray(devName, stNotify.szDevName);
        // DevID
        System.arraycopy(app.getDeviceInfo().sSerialNumber, 0, stNotify.szDevID, 0, app.getDeviceInfo().sSerialNumber.length);
        // user
        StringToByteArray(PushHelper.instance().getApiKey(), stNotify.szUser);
        //password
        StringToByteArray("", stNotify.szPassword);

        stNotify.pstuSubscribes[0].nCode = FinalVar.EVENT_ALARM_MOTIONDETECT;
        stNotify.pstuSubscribes[0].emSubCode = EM_EVENT_SUB_CODE.EM_EVENT_SUB_CODE_UNKNOWN;
        stNotify.pstuSubscribes[0].nChnNum = nChnCount;
        for (int i = 0; i < nChnCount; i++) {
            stNotify.pstuSubscribes[0].nIndexs[i] = i;
        }

        stNotify.nSubScribeMax = nChnCount;
        Integer stuErr = new Integer(0);
        Integer stuRes = new Integer(0);
        boolean bRet = INetSDK.SetMobileSubscribe(app.getLoginHandle(), stNotify, stuErr, stuRes, 5000);
        if (!bRet) {
            ToolKits.writeErrorLog("subscribe SetMobilePushNotify failed");
            resId = R.string.alarm_push_sub_failed;
        }else {
            ToolKits.writeLog("subscribe SetMobilePushNotify Succeed!");
            resId = R.string.alarm_push_sub_successed;
        }
        return bRet;
    }

    /**
     * unsubscribe Alarm Push
     */
    public boolean unsubscribe() {

        /// Get register id form google service
        String registerID = PushHelper.instance().getRegisterID(mContext);
        if (registerID == null) {
            Log.d(TAG, "not support google service.");
            resId = R.string.alarm_push_not_support_google_service;
            return false;
        }

        NET_MOBILE_PUSH_NOTIFY_DEL stIn = new NET_MOBILE_PUSH_NOTIFY_DEL();
        StringToByteArray(registerID, stIn.szRegisterID);
        NET_OUT_DELETECFG stOut = new NET_OUT_DELETECFG();
        boolean bRet = INetSDK.DelMobileSubscribe(app.getLoginHandle(), stIn, stOut, 5000);
        if (!bRet) {
            resId = R.string.alarm_push_unsub_failed;
            ToolKits.writeErrorLog("DelMobileSubscribe failed");
        } else {
            resId = R.string.alarm_push_unsub_successed;
            ToolKits.writeLog("DelMobileSubscribe Succeed!");
        }

        NET_MOBILE_PUSH_NOTIFY stNotify = new NET_MOBILE_PUSH_NOTIFY(0);

        ///default value
        long period = 500646880;
        String devName = "deviceName";

        //RegisterID
        StringToByteArray(registerID, stNotify.szRegisterID); // for device service to

        //serverType
        stNotify.emServerType = EM_MOBILE_SERVER_TYPE.EM_MOBILE_SERVER_TYPE_ANDROID;

        //PeriodOfValidity
        stNotify.nPeriodOfValidity = (int) period;

        // DevName
        StringToByteArray(devName, stNotify.szDevName);
        // DevID
        System.arraycopy(app.getDeviceInfo().sSerialNumber, 0, stNotify.szDevID, 0, app.getDeviceInfo().sSerialNumber.length);
        // user
        StringToByteArray(PushHelper.instance().getApiKey(), stNotify.szUser);
        //password
        StringToByteArray("", stNotify.szPassword);
        stNotify.nSubScribeMax = 0;

        Integer stuErr = new Integer(0);
        Integer stuRes = new Integer(0);
        bRet = INetSDK.SetMobileSubscribe(app.getLoginHandle(), stNotify, stuErr, stuRes, 5000);
        if (!bRet) {
            ToolKits.writeErrorLog("unsubscribe SetMobilePushNotify failed");
            resId = R.string.alarm_push_unsub_failed;
        }else {
            ToolKits.writeLog("unsubscribe SetMobilePushNotify Succeed!");
            resId = R.string.alarm_push_unsub_successed;
        }
        return bRet;
    }

    /**
     * Get Res ID
     */
    public int getResId() {
        return resId;
    }

    /**
     * Copy Strings data to Byte Array data
     * @param str
     * @param bytes
     */
    private void StringToByteArray(String str, byte[] bytes) {
        System.arraycopy(str.getBytes(), 0, bytes, 0, str.getBytes().length);
    }
}
