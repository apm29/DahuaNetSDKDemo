package com.company.netsdk.module;

import com.company.NetSDK.EM_LOGIN_SPAC_CAP_TYPE;
import com.company.NetSDK.INetSDK;
import com.company.NetSDK.NET_DEVICEINFO_Ex;
import com.company.netsdk.common.P2pClient;
import com.company.netsdk.common.ToolKits;

/**
 * Created by 29779 on 2017/4/8.
 */
public class P2PLoginModule{
    private static final String TAG = "P2PLoginModule";
    private P2pClient mP2pClient;
    private long mLoginHandle = 0;
    private NET_DEVICEINFO_Ex mDeviceInfo;
    private int mErrorCode = 0;
    private boolean mServiceStarted = false;

    public P2PLoginModule() {
        mP2pClient = new P2pClient();
    }

    public boolean isServiceStarted() {
        return mServiceStarted;
    }
    public boolean startP2pService( String svrAddress, String svrPort, String username, String svrKey,
                                   String deviceSn, String devicePort) {
       if(mP2pClient.startService(svrAddress, svrPort, username, svrKey, deviceSn, devicePort)) {
           mServiceStarted = true;
       } else {
           mServiceStarted = false;
           return false;
       }
        return true;
    }

    public boolean stopP2pService() {
        mLoginHandle = 0;
        mServiceStarted = false;
        mDeviceInfo = null;
        mErrorCode = 0;
        return mP2pClient.stopService();
    }

    public boolean login(String username, String password) {
        Integer err = new Integer(0);
        mDeviceInfo = new NET_DEVICEINFO_Ex();

        /// default device login ip.
        final String deviceIP = "127.0.0.1";
        mLoginHandle = INetSDK.LoginEx2(deviceIP, mP2pClient.getP2pPort(), username, password,
                EM_LOGIN_SPAC_CAP_TYPE.EM_LOGIN_SPEC_CAP_P2P, "", mDeviceInfo, err);
        if (0 == mLoginHandle) {
            mErrorCode = INetSDK.GetLastError();
            ToolKits.writeErrorLog("Failed to Login Device [ username: " + username + " password: " + password +" ].");
            return false;
        }
        return true;
    }

    public boolean logout() {
        if (0 == mLoginHandle) {
            return  false;
        }

        boolean retLogout = INetSDK.Logout(mLoginHandle);
        if (retLogout) {
            mLoginHandle = 0;
        }

        return  retLogout;
    }

    public int errorCode() {
        return mErrorCode;
    }

    public long getLoginHandle() {
        return mLoginHandle;
    }

    public NET_DEVICEINFO_Ex getDeviceInfo() {
        return mDeviceInfo;
    }
}
