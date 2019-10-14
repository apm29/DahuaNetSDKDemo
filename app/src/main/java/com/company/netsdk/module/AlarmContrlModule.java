package com.company.netsdk.module;

import android.content.Context;
import android.content.res.Resources;

import com.company.NetSDK.ALARM_CONTROL;
import com.company.NetSDK.INetSDK;
import com.company.NetSDK.SDK_IOTYPE;
import com.company.NetSDK.TRIGGER_MODE_CONTROL;
import com.company.netsdk.R;
import com.company.netsdk.common.ToolKits;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 29779 on 2017/4/8.
 */
public class AlarmContrlModule {
    Context mContext;
    Resources res;
    Integer nIOCount = new Integer(0);
    public int nChnIn = 0;
    public int nChnOut = 0;
    public int nChnTri = 0;

    public AlarmContrlModule(Context context) {
        this.mContext = context;
        res = mContext.getResources();
    }

    ///Get the count of alarm channel
    ///获取报警通道数量
    public int getChannel(long loginHandle, int mAlarmIOType){
        if (INetSDK.QueryIOControlState(loginHandle, mAlarmIOType, null, nIOCount, 3000)) {
            ToolKits.writeLog("channelNum : " + nIOCount.intValue());
        } else {
            return -1;
        }
        return nIOCount.intValue();
    }

    ///Depending on the count of alarm input channel, add the number of alarm input channel on the widget.
    ///根据获取的报警输入通道数量，在控件上来添加报警输入通道号.
    public List getInputChnList(long loginHandle){
        ArrayList<String> inputChnList = new ArrayList<String>();
        nChnIn = getChannel(loginHandle, SDK_IOTYPE.SDK_ALARMINPUT);
        for (int i = 0; i < nChnIn; i++){
            inputChnList.add(res.getString(R.string.channel) + i);
        }
        return inputChnList;
    }

    ///Depending on the count of alarm output channel, add the number of alarm output channel on the widget.
    ///根据获取的报警输出通道数量，在控件上来添加报警输出通道号.
    public List getOutputChnList(long loginHandle){
        ArrayList<String> outputChnlList = new ArrayList<String>();
        nChnOut = getChannel(loginHandle, SDK_IOTYPE.SDK_ALARMOUTPUT);
        for (int i = 0; i < nChnOut; i++){
            outputChnlList.add(res.getString(R.string.channel) + i);
        }
        return outputChnlList;
    }

    ///Depending on the count of alarm trigger channel, add the number of alarm trigger channel on the widget.
    ///根据获取的报警触发通道数量，在控件上来添加报警触发通道号.
    public List getTriggerChnList(long loginHandle){
        ArrayList<String> triggerChnlList = new ArrayList<String>();
        nChnTri = getChannel(loginHandle, SDK_IOTYPE.SDK_ALARM_TRIGGER_MODE);
        for (int i = 0; i < nChnTri; i++){
            triggerChnlList.add(res.getString(R.string.channel) + i);
        }
        return triggerChnlList;
    }

    ///Depending on the count of alarm input channel, add the state of alarm input channel on the widget.
    ///根据获取的报警输入通道数量，在控件上来添加报警输入通道状态.
    public List getInputStateList(){
        String[] inputStateNames;
        ArrayList<String> inputStateList = new ArrayList<String>();
        if(nChnIn > 0) {
            inputStateNames = res.getStringArray(R.array.alarm_state_array);
            for (int i = 0; i < inputStateNames.length; i++) {
                inputStateList.add(inputStateNames[i]);
            }
        }
        return inputStateList;
    }

    ///Depending on the count of alarm output channel, add the state of alarm output channel on the widget.
    ///根据获取的报警输出通道数量，在控件上来添加报警输出通道状态.
    public List getOutputStateList(){
        String[] outputStateNames;
        ArrayList<String> outputStateList = new ArrayList<String>();
        if(nChnOut > 0) {
            outputStateNames = res.getStringArray(R.array.alarm_state_array);
            for (int i = 0; i < outputStateNames.length; i++) {
                outputStateList.add(outputStateNames[i]);
            }
        }
        return outputStateList;
    }

    ///Depending on the count of alarm trigger channel, add the state of alarm trigger channel on the widget.
    ///根据获取的报警通道数量，在控件上来添加报警通道状态.
    public List getTriggerStateList(){
        String[] triggerStateNames;
        ArrayList<String> triggerStateList = new ArrayList<String>();
       if(nChnTri > 0){
           triggerStateNames = res.getStringArray(R.array.alarm_trigger_mode_array);
            for (int i = 0; i < triggerStateNames.length; i++){
                triggerStateList.add(triggerStateNames[i]);
            }
        }
        return triggerStateList;
    }

    ///Get the state of alarm input channel
    ///获取报警输入通道状态
    public int getAlarmInputChnState(long loginHandle, int channel) {
        int alarmInputChnState = 0;
        ALARM_CONTROL[] alarmInputGet = new ALARM_CONTROL[nChnIn];
        for(int i = 0; i < nChnIn; i++) {
            alarmInputGet[i] = new ALARM_CONTROL();
        }
        if (INetSDK.QueryIOControlState(loginHandle, SDK_IOTYPE.SDK_ALARMINPUT, alarmInputGet, nIOCount, 3000)) {
            alarmInputChnState = alarmInputGet[channel].state;
        } else {
            return -1;
        }
        return alarmInputChnState;
    }

    ///Get the state of alarm output channel
    ///获取报警输出通道状态
    public int getAlarmOutputChnState(long loginHandle, int channel) {
        int alarmOutputChnState = 0;
        ALARM_CONTROL[] alarmOutputGet = new ALARM_CONTROL[nChnOut];
        for(int j=0; j<nChnOut; j++) {
            alarmOutputGet[j] = new ALARM_CONTROL();
        }
        if (INetSDK.QueryIOControlState(loginHandle, SDK_IOTYPE.SDK_ALARMOUTPUT, alarmOutputGet, nIOCount, 3000)) {
            alarmOutputChnState = alarmOutputGet[channel].state;
        } else {
            return -1;
        }
        return alarmOutputChnState;
    }

    ///Get the state of alarm trigger channel
    ///获取报警触发通道状态
    public int getAlarmTriggerChnState(long loginHandle, int channel) {
        int alarmTriggerChnState = 0;
        TRIGGER_MODE_CONTROL[] alarmTriggerGet = new TRIGGER_MODE_CONTROL[nChnTri];
        for(int k = 0; k < nChnTri; k++) {
            alarmTriggerGet[k] = new TRIGGER_MODE_CONTROL();
        }
        if (INetSDK.QueryIOControlState(loginHandle, SDK_IOTYPE.SDK_ALARM_TRIGGER_MODE, alarmTriggerGet, nIOCount, 3000)) {
            alarmTriggerChnState =  alarmTriggerGet[channel].mode;
        } else {
            return  -1;
        }
        return alarmTriggerChnState;
    }

    ///Set the state of alarm input channel
    ///设置报警输入通道状态
    public boolean setAlarmInputChnState(long loginHandle, int channel, int state) {
        ALARM_CONTROL[] alarmInputSet = new ALARM_CONTROL[1];
        alarmInputSet[0] = new ALARM_CONTROL();
        alarmInputSet[0].index = (short)channel;
        alarmInputSet[0].state = (short)state;

        return INetSDK.IOControl(loginHandle, SDK_IOTYPE.SDK_ALARMINPUT, alarmInputSet);
    }

    ///Set the state of alarm output channel
    ///设置报警输出通道状态
    public boolean setAlarmOutputChnState(long loginHandle, int channel, int state) {
        ALARM_CONTROL[] alarmOutputSet = new ALARM_CONTROL[1];
        alarmOutputSet[0] = new ALARM_CONTROL();
        alarmOutputSet[0].index = (short)channel;
        alarmOutputSet[0].state = (short)state;

        return INetSDK.IOControl(loginHandle, SDK_IOTYPE.SDK_ALARMOUTPUT, alarmOutputSet);
    }

    ///Set the state of alarm trigger channel
    ///设置报警触发通道状态
    public boolean setAlarmTriggerChnState(long loginHandle, int channel, int state) {
        TRIGGER_MODE_CONTROL[] alarmTriggerSet = new TRIGGER_MODE_CONTROL[1];
        alarmTriggerSet[0] = new TRIGGER_MODE_CONTROL();
        alarmTriggerSet[0].index = (short)channel;
        alarmTriggerSet[0].mode = (short)state;

        return INetSDK.IOControl(loginHandle, SDK_IOTYPE.SDK_ALARM_TRIGGER_MODE, alarmTriggerSet);
    }
}
