package com.company.netsdk.activity.MotionDetectConfig.view;

/**
 * Created by 32940 on 2018/8/23.
 */
public class Time {
    int hour;
    int mintue;
    int second;

    public Time(int hour,int mintue,int second)
    {
        this.hour 	= hour;
        this.mintue = mintue;
        this.second = second;
    }

    public long getSeconds()
    {
        return (long)hour*3600 + (long)mintue * 60 + (long)second;
    }

    @Override
    public String toString() {
        return hour + ":" + mintue + ":"+ second;
    }
}
