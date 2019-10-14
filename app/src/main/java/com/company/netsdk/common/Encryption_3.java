package com.company.netsdk.common;

import com.company.NetSDK.FinalVar;

/**
 *  此映射表查询用的三代接口 {@link com.company.NetSDK.INetSDK#QueryDevInfo}
 *  加密模式
 *  三代byAuthMode  , byEncrAlgr  与三代映射关系
 *  Authentication认证方式          DataEncryption数据加密方式     Encryption加密模式
 *  WPA-NONE 6                      NONE      0                     WPA-NONE       0
 *  OPEN     0                      NONE      0                     On			   1
 *  OPEN     0                      WEP       1                     WEP-OPEN	   2
 *  SHARED   1                      WEP       1                     WEP-SHARED     3
 *  WPA      2                      TKIP      2                     WPA-TKIP       4
 *  WPA-PSK  3                      TKIP      2                     WPA-PSK-TKIP   5
 *  WPA2     4                      TKIP      2                     WPA2-TKIP      6
 *  WPA2-PSK 5                      TKIP      2                     WPA2-PSK-TKIP  7
 *  WPA      2                      AES(CCMP) 3                     WPA-AES        8
 *  WPA-PSK  3                      AES(CCMP) 3                     WPA-PSK-AES    9
 *  WPA2     4                      AES(CCMP) 3                     WPA2-AES       10
 *  WPA2-PSK 5                      AES(CCMP) 3                     WPA2-PSK-AES   11
 *  WPA      2                      TKIP+AES( mix Mode) 4           WPA-TKIP或者WPA-AES  4或8
 *  WPA-PSK  3                      TKIP+AES( mix Mode) 4           WPA-PSK-TKIP或者WPA-PSK-AES 5或9
 *  WPA2     4                      TKIP+AES( mix Mode) 4           WPA2-TKIP或者WPA2-AES   6或10
 *  WPA2-PSK 5                      TKIP+AES( mix Mode) 4           WPA2-PSK-TKIP或者WPA2-PSK-AES 7或11
 *
 * 以下是混合模式
 * WPA-PSK|WPA2-PSK 7
 * WPA|WPA2         8
 * WPA|WPA-PSK      9
 * WPA2|WPA2-PSK    10
 * WPA|WPA-PSK|WPA2|WPA2-PSK 11
 */
public class Encryption_3 {
    /**
     * 三代映射的加密方式
     * @param byAuthMode  认证方式,  通过接口{@link com.company.NetSDK.INetSDK#QueryDevInfo},对应命令{@link FinalVar#NET_QUERY_WLAN_ACCESSPOINT}查询得到
     * @param byEncrAlgr  数据加密方式,   通过接口{@link com.company.NetSDK.INetSDK#QueryDevInfo},对应命令{@link FinalVar#NET_QUERY_WLAN_ACCESSPOINT}查询得到
     * @return nEncryption   用于{@link FinalVar#CFG_CMD_WLAN}配置WLAN   加密模式, 0: off, 1: on, 2: WEP-OPEN, 3: WEP-SHARED, 4: WPA-TKIP, 5: WPA-PSK-TKIP, 6: WPA2-TKIP, 7: WPA2-PSK-TKIP, 8: WPA-AES, 9: WPA-PSK-AES, 10: WPA2-AES, 11: WPA2-PSK-AES, 12: AUTO
     */
    public static int getValue(int byAuthMode, int byEncrAlgr) {
        int nEncryption = 0;

        if(byAuthMode == 6 && byEncrAlgr == 0)
        {
            nEncryption = 0;
        }
        else if(byAuthMode == 0 && byEncrAlgr == 0)
        {
            nEncryption = 1;
        }
        else  if(byAuthMode == 0 && byEncrAlgr == 1)
        {
            nEncryption = 2;
        }
        else  if(byAuthMode == 1 && byEncrAlgr == 1)
        {
            nEncryption = 3;
        }
        else  if(byAuthMode == 2 && byEncrAlgr == 2)
        {
            nEncryption = 4;
        }
        else  if(byAuthMode == 3 && byEncrAlgr == 2)
        {
            nEncryption = 5;
        }
        else  if(byAuthMode == 4 && byEncrAlgr == 2)
        {
            nEncryption = 6;
        }
        else  if(byAuthMode == 5 && byEncrAlgr == 2)
        {
            nEncryption = 7;
        }
        else  if(byAuthMode == 2 && byEncrAlgr == 3)
        {
            nEncryption = 8;
        }
        else  if(byAuthMode == 3 && byEncrAlgr == 3)
        {
            nEncryption = 9;
        }
        else  if(byAuthMode == 4 && byEncrAlgr == 3)
        {
            nEncryption = 10;
        }
        else  if(byAuthMode == 5 && byEncrAlgr == 3)
        {
            nEncryption = 11;
        }
        else  if(byAuthMode == 2 && byEncrAlgr == 4)
        {
            nEncryption = 8;  // 4或者8
        }
        else  if(byAuthMode == 3 && byEncrAlgr == 4)
        {
            nEncryption = 9;  // 5或9
        }
        else  if(byAuthMode == 4 && byEncrAlgr == 4)
        {
            nEncryption = 10;  // 6或10
        }
        else  if(byAuthMode == 5 && byEncrAlgr == 4)
        {
            nEncryption = 11;  // 7或11
        }
        else if(byAuthMode == 7)  // 混合模式WPA-PSK|WPA2-PSK   3或5
        {
            if(byEncrAlgr == 2) {
                nEncryption = 7;  // 5或7
            }
            else if(byEncrAlgr == 3)
            {
                nEncryption = 11;  // 9或11
            }
            else if(byEncrAlgr == 4)
            {
                nEncryption = 11;  // 5或7或9或11
            }
            else
            {
                nEncryption = 12;
            }
        }
        else if(byAuthMode == 8)  // 混合模式WPA|WPA2    2或4
        {
            if(byEncrAlgr == 2) {
                nEncryption = 6;  // 4或6
            }
            else if(byEncrAlgr == 3)
            {
                nEncryption = 10;  // 8或10
            }
            else if(byEncrAlgr == 4)
            {
                nEncryption = 10;  // 4或6或8或10
            }
            else
            {
                nEncryption = 12;
            }
        }
        else if(byAuthMode == 9)  // 混合模式WPA|WPA-PSK  2或3
        {
            if(byEncrAlgr == 2) {
                nEncryption = 5;  // 4或5
            }
            else if(byEncrAlgr == 3)
            {
                nEncryption = 9;  // 8或9
            }
            else if(byEncrAlgr == 4)
            {
                nEncryption = 9;  // 4或5或8或9
            }
            else
            {
                nEncryption = 12;
            }
        }
        else if(byAuthMode == 10)  // 混合模式WPA2|WPA2-PSK  4或5
        {
            if(byEncrAlgr == 2) {
                nEncryption = 7;  // 6或7
            }
            else if(byEncrAlgr == 3)
            {
                nEncryption = 11;  // 10或11
            }
            else if(byEncrAlgr == 4)
            {
                nEncryption = 11;  // 6或7或10或11
            }
            else
            {
                nEncryption = 12;
            }
        }
        else if(byAuthMode == 11)  // 混合模式WPA|WPA-PSK|WPA2|WPA2-PSK  2或3或4或5
        {
            if(byEncrAlgr == 2) {
                nEncryption = 7;  // 4或5或6或7
            }
            else if(byEncrAlgr == 3)
            {
                nEncryption = 11;  // 8或9或10或11
            }
            else if(byEncrAlgr == 4)
            {
                nEncryption = 11;  // 4或5或6或7或8或9或10或11
            }
            else
            {
                nEncryption = 12;
            }
        } else {
            nEncryption = 12;
        }
        return nEncryption;
    }
}
