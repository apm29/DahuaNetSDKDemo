package com.company.netsdk.activity;

import android.app.Activity;
import android.app.Application;

import android.os.Build;
import android.os.Bundle;

import com.company.NetSDK.NET_DEVICEINFO_Ex;

import java.lang.ref.WeakReference;

/**
 * Created by 29779 on 2017/4/14.
 */
public class NetSDKApplication extends Application{
    private long mloginHandle;
    private NET_DEVICEINFO_Ex mDeviceInfo;
    private static NetSDKApplication instance ;
    
    public static NetSDKApplication getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {
                        ActivityManager.getManager().setCurrentActivity(activity);
                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {

                }
            });
        }
        setLoginHandle(0);
        setDeviceInfo(null);
    }

    public long getLoginHandle() {
        return mloginHandle;
    }

    public void setLoginHandle(long loginHandle) {
        this.mloginHandle = loginHandle;
    }

    public NET_DEVICEINFO_Ex getDeviceInfo() {
        return mDeviceInfo;
    }

    public void setDeviceInfo(NET_DEVICEINFO_Ex mDeviceInfo) {
        this.mDeviceInfo = mDeviceInfo;
    }

    public static class ActivityManager{
        private static ActivityManager manager = new ActivityManager();
        private WeakReference<Activity> current;
        private ActivityManager(){

        }
        public static ActivityManager getManager(){
            return manager;
        }

        public Activity getTopActivity(){
            if (current != null)
                return current.get();
            return null;
        }
        public void setCurrentActivity(Activity obj){
            current = new WeakReference<Activity>(obj);
        }
    }

}
