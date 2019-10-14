package com.company.netsdk.module;

import com.company.NetSDK.CtrlType;
import com.company.NetSDK.EM_FILE_QUERY_TYPE;
import com.company.NetSDK.EM_MARKFILE_MODE;
import com.company.NetSDK.EM_MARKFILE_NAMEMADE_TYPE;
import com.company.NetSDK.EM_QUERY_RECORD_TYPE;
import com.company.NetSDK.EM_RECORD_SNAP_FLAG_TYPE;
import com.company.NetSDK.INetSDK;
import com.company.NetSDK.NET_IN_MEDIA_QUERY_FILE;
import com.company.NetSDK.NET_IN_SET_MARK_FILE;
import com.company.NetSDK.NET_IN_SET_MARK_FILE_BY_TIME;
import com.company.NetSDK.NET_OUT_MEDIA_QUERY_FILE;
import com.company.NetSDK.NET_OUT_SET_MARK_FILE;
import com.company.NetSDK.NET_OUT_SET_MARK_FILE_BY_TIME;
import com.company.NetSDK.NET_RECORDFILE_INFO;
import com.company.NetSDK.NET_TIME;
import com.company.NetSDK.NET_TIME_EX;
import com.company.netsdk.common.ToolKits;


/**
 * Created by 41299 on 2018/10/13.
 */
public class MarkRecordModule {

    public static final  int MAX_QUERY_NUM = 24;

    private static int nTotalCount = 0;

    private static boolean bTotalSuccess = false;

    public static int getMarkFileCount() {
        return nTotalCount;
    }
    public static boolean ifFindTotalSuccess() {
        return bTotalSuccess;
    }

    public static boolean markFileByTime(long lLoginID, int chn, NET_TIME_EX startTime, NET_TIME_EX endTime) {
        NET_IN_SET_MARK_FILE_BY_TIME stuIn = new NET_IN_SET_MARK_FILE_BY_TIME();
        NET_OUT_SET_MARK_FILE_BY_TIME stuOut = new NET_OUT_SET_MARK_FILE_BY_TIME();

        stuIn.nChannel = chn;
        stuIn.stuStartTime = startTime;
        stuIn.stuEndTime = endTime;
        stuIn.bFlag = true;

        boolean bRet = INetSDK.SetMarkFileByTime(lLoginID, stuIn, stuOut, 10000);
        if (!bRet) {
            ToolKits.writeErrorLog("Mark file by time failed!");
        }
        return bRet;
    }

    public static NET_RECORDFILE_INFO[] queryMarkFile(long lLoginID, int chn, NET_TIME_EX startTime, NET_TIME_EX endTime) {

        NET_RECORDFILE_INFO[] stFileInfo =  new NET_RECORDFILE_INFO[MAX_QUERY_NUM]; //数组大小是查询的录像等的最大值
        for(int i=0; i<stFileInfo.length; i++) {
            stFileInfo[i] = new NET_RECORDFILE_INFO();
        }

        NET_TIME stuStartTime = new NET_TIME();
        NET_TIME stuEndTime = new NET_TIME();

        copyTime(stuStartTime, startTime);
        copyTime(stuEndTime, endTime);

        Integer nFileCount = new Integer(0);
        nTotalCount = 0;
        bTotalSuccess = true;
        boolean cRet = INetSDK.QueryRecordFile(lLoginID, chn, EM_QUERY_RECORD_TYPE.EM_RECORD_TYPE_IMPORTANT,
                stuStartTime, stuEndTime, null, stFileInfo, nFileCount, 5000, false);
        if(!cRet) {
            ToolKits.writeErrorLog("QueryRecordFile Failed!");
            return null;
        }

        if (nFileCount == MAX_QUERY_NUM) {
            //findMarkFileTotalCount(lLoginID, chn, startTime, endTime);
        }

        if (!bTotalSuccess) {
            nTotalCount = MAX_QUERY_NUM;
        }

        return stFileInfo;
    }

    public static NET_OUT_MEDIA_QUERY_FILE[] findMarkFile(long lLoginID, int chn, NET_TIME_EX startTime, NET_TIME_EX endTime) {
        nTotalCount = 0;
        // 获取标记录像信息
        NET_IN_MEDIA_QUERY_FILE infile = new NET_IN_MEDIA_QUERY_FILE();

        copyTime(infile.stuStartTime, startTime);
        copyTime(infile.stuEndTime, endTime);

        infile.nMediaType = 2;
        infile.nChannelID = chn;
        infile.nFalgCount = 1;
        infile.emFalgLists[0] = EM_RECORD_SNAP_FLAG_TYPE.FLAG_TYPE_MARKED;
        long lFindHandle = INetSDK.FindFileEx(lLoginID, EM_FILE_QUERY_TYPE.SDK_FILE_QUERY_FILE, infile, 3000);
        if(lFindHandle != 0){
            ToolKits.writeLog("FindFileEx Succeed!");
        } else {
            ToolKits.writeErrorLog("FindFileEx Failed!" );
            return null;
        }

        NET_OUT_MEDIA_QUERY_FILE[] outfile = new NET_OUT_MEDIA_QUERY_FILE[MAX_QUERY_NUM];
        for (int i = 0; i < outfile.length; i++) {
            outfile[i] = new NET_OUT_MEDIA_QUERY_FILE();
        }

        int nRetCount = INetSDK.FindNextFileEx(lFindHandle, EM_FILE_QUERY_TYPE.SDK_FILE_QUERY_FILE, outfile, 10000);
        if(nRetCount < 0) {
            ToolKits.writeErrorLog("FindNextFileEx Failed!" );
            INetSDK.FindCloseEx(lFindHandle);
            return null;
        }

        bTotalSuccess = true;
        nTotalCount = nRetCount;
        if (nTotalCount == MAX_QUERY_NUM) {
            int nMaxQueryCount = 100;
            NET_OUT_MEDIA_QUERY_FILE[] tmpfile = new NET_OUT_MEDIA_QUERY_FILE[nMaxQueryCount];
            for (int i = 0; i < tmpfile.length; i++) {
                tmpfile[i] = new NET_OUT_MEDIA_QUERY_FILE();
            }

            nRetCount = 0;
            while (true) {
                nRetCount = INetSDK.FindNextFileEx(lFindHandle, EM_FILE_QUERY_TYPE.SDK_FILE_QUERY_FILE, tmpfile, 10000);
                if(nRetCount < 0) {
                    ToolKits.writeErrorLog("FindNextFileEx Failed!" );
                    bTotalSuccess = false;
                    break;
                }
                nTotalCount += nRetCount;
                if(nRetCount < nMaxQueryCount) {
                    break;
                }
            }
        }

        INetSDK.FindCloseEx(lFindHandle);

        return outfile;
    }

    private static void copyTime(NET_TIME dst, NET_TIME_EX src) {
        dst.dwYear = src.dwYear;
        dst.dwMonth = src.dwMonth;
        dst.dwDay = src.dwDay;
        dst.dwHour = src.dwHour;
        dst.dwMinute = src.dwMinute;
        dst.dwSecond = src.dwSecond;
    }

    public static boolean unlock(long lLoginID, NET_OUT_MEDIA_QUERY_FILE[] stFileInfo, boolean[] selectArray) {

        int nCount = nTotalCount > MAX_QUERY_NUM ? MAX_QUERY_NUM: nTotalCount;

        for (int i = 0; i < nCount; ++i) {
            if (selectArray[i]) {
                if (!setMarkFile(lLoginID, stFileInfo[i])) {
                    ToolKits.writeErrorLog(" unlock file Failed!");
                    return false;
                }else {
                    ToolKits.writeLog( i + " unlocked file " +  new String(stFileInfo[i].szFilePath).trim());
                }
            }
        }
        return true;
    }

    private static boolean setMarkFile(long lLoginID, NET_OUT_MEDIA_QUERY_FILE stFileInfo) {
        NET_IN_SET_MARK_FILE inMarkFile = new NET_IN_SET_MARK_FILE();
        inMarkFile.emLockMode = EM_MARKFILE_MODE.EM_MARK_FILE_BY_NAME_MODE;  // 通过文件名方式对录像加锁
        inMarkFile.emFileNameMadeType = EM_MARKFILE_NAMEMADE_TYPE.EM_MARKFILE_NAMEMADE_JOINT;
        inMarkFile.nDriveNo = stFileInfo.nDriveNo;
        inMarkFile.nStartCluster = stFileInfo.nCluster;
        inMarkFile.byImportantRecID = 0; // 0:false清除;   1:true 标记
        // arrayCharToByte(inMarkFile.szFilename, stFileInfo.szFilePath);
        NET_OUT_SET_MARK_FILE outMarkFile = new NET_OUT_SET_MARK_FILE();
        boolean bRet = INetSDK.SetMarkFile(lLoginID, inMarkFile, outMarkFile, 3000);
        if(bRet){
            ToolKits.writeLog("SetMarkFile Succeed!");
        } else {
            ToolKits.writeErrorLog("SetMarkFile Failed!" );
        }
        return bRet;
    }

    private static void arrayCharToByte(byte[] dst,char [] src) {
        for (int i = 0; i < src.length; ++i) {
            dst[i] = (byte)src[i];
        }
    }
}