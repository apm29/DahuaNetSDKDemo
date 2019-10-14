package com.company.netsdk.module;


import com.company.NetSDK.CB_fSearchDevicesCB;
import com.company.NetSDK.DEVICE_NET_INFO_EX;
import com.company.NetSDK.INetSDK;
import com.company.NetSDK.NET_IN_INIT_DEVICE_ACCOUNT;
import com.company.NetSDK.NET_OUT_INIT_DEVICE_ACCOUNT;
import com.company.netsdk.common.ToolKits;

/**
 * Created by 41299 on 2018/4/21.
 */
public class InitDevAccountModule {
    public long lDevSearchHandle = 0;
    ///Search device
    ///设备搜索
    public long startSearchDevices(CB_fSearchDevicesCB callback) {

        lDevSearchHandle = INetSDK.StartSearchDevices(callback);
        if(lDevSearchHandle == 0) {
            ToolKits.writeErrorLog("StartSearchDevices Failed!" + INetSDK.GetLastError());
        }

        return lDevSearchHandle;
    }

    ///Stop search device
    ///停止设备搜索
    public void stopSearchDevices() {
        if(lDevSearchHandle != 0) {
            if (INetSDK.StopSearchDevices(lDevSearchHandle)) {
                lDevSearchHandle = 0;
                ToolKits.writeLog("StopSearchDevices Succeed!");
            } else {
                ToolKits.writeErrorLog("StopSearchDevices Failed!" + INetSDK.GetLastError());
            }
        }
    }

    /**
     * 根据设备ip初始化账户(组播初始化),初始化之前，要通过设备搜索判断设备是否可以初始化
     */
    public boolean initDevAccount(DEVICE_NET_INFO_EX mDeviceInfo, String username, String password, String mInitPhoneOrMail) {
        boolean bRet = false;

        NET_IN_INIT_DEVICE_ACCOUNT inInit = new NET_IN_INIT_DEVICE_ACCOUNT();
        // mac地址
        System.arraycopy(mDeviceInfo.szMac, 0, inInit.szMac, 0, mDeviceInfo.szMac.length);

        // 用户名
        System.arraycopy(username.getBytes(), 0, inInit.szUserName, 0, username.getBytes().length);

        // 密码，必须字母与数字结合，8位以上，否则设备不识别
        System.arraycopy(password.getBytes(), 0, inInit.szPwd, 0, password.getBytes().length);

        if((mDeviceInfo.byPwdResetWay >> 1 & 0x01) == 0) {    // 手机号
            System.arraycopy(mInitPhoneOrMail.getBytes(), 0, inInit.szCellPhone, 0, mInitPhoneOrMail.getBytes().length);
        } else if((mDeviceInfo.byPwdResetWay >> 1 & 0x01) == 1) {  // 邮箱
            System.arraycopy(mInitPhoneOrMail.getBytes(), 0, inInit.szMail, 0, mInitPhoneOrMail.getBytes().length);
        }

        //  密码重置方式, 设备搜索返回的
        inInit.byPwdResetWay = mDeviceInfo.byPwdResetWay;

        NET_OUT_INIT_DEVICE_ACCOUNT outInit = new NET_OUT_INIT_DEVICE_ACCOUNT();

        bRet = INetSDK.InitDevAccount(inInit, outInit, 5000, null);
        if(bRet) {
            ToolKits.writeLog("InitDevAccount Succeed!");
        } else {
            ToolKits.writeErrorLog("InitDevAccount Failed!" + INetSDK.GetLastError());
        }
        return bRet;
    }

    /**
     * 根据设备ip初始化账户(单播初始化)，初始化之前，要通过设备搜索判断设备是否可以初始化
     */
    public boolean initDevAccountByIP(DEVICE_NET_INFO_EX mDeviceInfo, String username, String password, String mInitPhoneOrMail) {
        boolean bRet = false;
        NET_IN_INIT_DEVICE_ACCOUNT inInit = new NET_IN_INIT_DEVICE_ACCOUNT();

        // mac地址
        System.arraycopy(mDeviceInfo.szMac, 0, inInit.szMac, 0, mDeviceInfo.szMac.length);

        // 用户名
        System.arraycopy(username.getBytes(), 0, inInit.szUserName, 0, username.getBytes().length);

        // 密码, 必须字母与数字结合，8位以上，否则设备不识别
        System.arraycopy(password.getBytes(), 0, inInit.szPwd, 0, password.getBytes().length);

        if((mDeviceInfo.byPwdResetWay >> 1 & 0x01) == 0) {    // 手机号
            System.arraycopy(mInitPhoneOrMail.getBytes(), 0, inInit.szCellPhone, 0, mInitPhoneOrMail.getBytes().length);
        } else if((mDeviceInfo.byPwdResetWay >> 1 & 0x01) == 1) {  // 邮箱
            System.arraycopy(mInitPhoneOrMail.getBytes(), 0, inInit.szMail, 0, mInitPhoneOrMail.getBytes().length);
        }

        //  密码重置方式, 设备搜索返回的
        inInit.byPwdResetWay = mDeviceInfo.byPwdResetWay;

        NET_OUT_INIT_DEVICE_ACCOUNT outInit = new NET_OUT_INIT_DEVICE_ACCOUNT();

        String szDeviceIP = new String(mDeviceInfo.szIP).trim(); // 搜索到的设备ip，跟mac地址对应

        bRet = INetSDK.InitDevAccountByIP(inInit, outInit, 5000, null, szDeviceIP);
        if(bRet) {
            ToolKits.writeLog("InitDevAccountByIP Succeed!");
        } else {
            ToolKits.writeErrorLog("InitDevAccountByIP Failed!" );
        }

        return bRet;
    }
}
