package com.company.netsdk.common;

import android.util.Log;

import com.mm.android.dhproxy.client.DHProxyClient;

/**
 * Created by 29779 on 2017/4/14.
 */
public class P2pClient {
    private final static String TAG = "P2pClient";
    private DHProxyClient mP2pClient;
    private int mLocalPort; /// the port used for p2p service
    private boolean mServiceStopped = false;

    public P2pClient() {
        mP2pClient = new DHProxyClient();
        mLocalPort = 0;
        mServiceStopped = false;
    }

    public int getP2pPort() {
        return this.mLocalPort;
    }

    public synchronized boolean stopService() {
        Log.d(TAG, "stopService");

        if (mServiceStopped) {
            return true;
        }

        if (mLocalPort > 0) {
            if (0 != mP2pClient.delPort(mLocalPort)) {
                Log.d(TAG, "delPort " + mLocalPort);
            }
            mLocalPort = 0;
        }

        if (0 != mP2pClient.exit()) {
            Log.d(TAG, "exit ");
        }

        mServiceStopped = true;
        return true;
    }

    public boolean startService(String svrAddress, String svrPort, String username,String svrKey,
                                  String deviceSn, String devicePort) {
        ToolKits.writeLog("Start Service --> Begin.");
        if (!mP2pClient.initWithName(svrAddress,Integer.parseInt(svrPort), svrKey, username)) {
            Log.e(TAG, "Failed to init P2p Client.");
            return  false;
        }

        boolean bClientOnline = false;
        boolean bDeviceOnline = false;
        int nLoopCount = 3;
        while (nLoopCount > 0) {
            mLocalPort = mP2pClient.addPort(deviceSn, Integer.parseInt(devicePort), 0);
            ToolKits.writeLog("mLocalPort : " + mLocalPort);
            int nTry2 = 0;
            while (nTry2 < 200) {    ///200次，每次100ms，总时间为20s；如果设置的总时间太少(比如20 * 100)，p2p不一定成功
                int nStatus = mP2pClient.portStatus(mLocalPort);
                ToolKits.writeLog("nStatus : " + nStatus);
                if (1 == nStatus) {
                    Log.d(TAG, "Start Service --> End. add port ok . port = " + mLocalPort);
                    return true;
                } else if (2 == nStatus) {
                    if (!bClientOnline) {
                        // status
                        if (mP2pClient.status() == 3) {
                            Log.d(TAG, "client is online.");
                            bClientOnline = true;
                        }
                    }

                    if (!bDeviceOnline) {
                        if (1 == mP2pClient.query(deviceSn)) {
                            Log.d(TAG, "device is online.");
                            bDeviceOnline = true;
                        }
                    }
										
					break;
                }
                nTry2 ++;

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
			mP2pClient.delPort(mLocalPort);
            nLoopCount --;
        }

        Log.d(TAG, "Start Service --> End. failed to start p2p service.");
        return false;
    }
}
