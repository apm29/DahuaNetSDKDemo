package com.company.netsdk.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.company.NetSDK.CB_fSearchDevicesCB;
import com.company.NetSDK.DEVICE_NET_INFO_EX;
import com.company.netsdk.R;
import com.company.netsdk.common.ClearEditText;
import com.company.netsdk.common.DialogProgress;
import com.company.netsdk.common.EyeImageButton;
import com.company.netsdk.common.ToolKits;
import com.company.netsdk.module.DeviceSearchModule;
import com.company.netsdk.module.WIFIConfigurationModule;
import com.company.netsdk.zxing.activity.CaptureActivity;

public class WIFIConfigurationActivity extends AppCompatActivity implements View.OnClickListener{
    private ClearEditText mSnEditText;
    private ClearEditText mSsidEditText;
    private ClearEditText mPwdEditText;
    private EyeImageButton mEyeImageButton;
    private ImageButton mScanImageButton;
    private TextView mDeviceInfoTextView;

    private WIFIConfigurationModule mConfigModule;
    private DeviceSearchModule mDeviceSearchModule;
    private DialogProgress mDialogProgress;
    Resources res;

    private int WIFICONFIG_OR_AUDIOPAIR = 0;
    private final int WIFI_CONFIG = 0x11;
    private final int AUDIOPAIR_CONFIG = 0x12;
    private String snStr;
    private String ssidStr;
    private String pwdStr;

    SmartConfigTask smartconfigTask;
    AudioPairTask audioPairTask;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AUDIOPAIR_CONFIG:   // 声波
                    mDeviceInfoTextView.setText((String)msg.obj);

                    if(!audioPairTask.isCancelled()) {
                        audioPairTask.cancel(false);
                    }

                    break;
                case WIFI_CONFIG:  // wifi
                    mDeviceInfoTextView.setText((String)msg.obj);

                    if(!smartconfigTask.isCancelled()) {
                        smartconfigTask.cancel(false);
                    }

                    break;
                default:
                    break;
            }
        }
    };

    private CB_fSearchDevicesCB callback = new  CB_fSearchDevicesCB(){

        @Override
        public void invoke(DEVICE_NET_INFO_EX device_net_info_ex) {
            String temp = res.getString(R.string.config_succeed) + "\n" +
                          res.getString(R.string.activity_iplogin_device_ip) + " : "+ new String(device_net_info_ex.szIP).trim();

            ///声波配对判断
            if(new String(device_net_info_ex.szSerialNo).trim().equals(snStr)
                    && (WIFICONFIG_OR_AUDIOPAIR == 2)
                    && (device_net_info_ex.iIPVersion == 4)) {

                Message msg = mHandler.obtainMessage(AUDIOPAIR_CONFIG);
                msg.obj = temp;
                mHandler.sendMessage(msg);
            }

            ///Wifi配置判断
            if(new String(device_net_info_ex.szSerialNo).trim().equals(snStr)
                    && (WIFICONFIG_OR_AUDIOPAIR == 3)
                    && (device_net_info_ex.iIPVersion == 4)) {

                Message msg = mHandler.obtainMessage(WIFI_CONFIG);
                msg.obj = temp;
                mHandler.sendMessage(msg);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wificonfiguration);
        setTitle(R.string.activity_main_wifi_config);
        mConfigModule = new WIFIConfigurationModule(this);
        mDeviceSearchModule = new DeviceSearchModule(this);
        mDialogProgress = new DialogProgress(this);
        res = this.getResources();

        // 添加返回键
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setSystemVolumeMax();
        setupView();
        getWIFIInfo();
        ToolKits.verifyCameraPermissions(WIFIConfigurationActivity.this);
    }

    private void setupView(){
        mSnEditText = (ClearEditText)findViewById(R.id.sn_et);
        mSsidEditText = (ClearEditText)findViewById(R.id.ssid_et);
        mPwdEditText = (ClearEditText)findViewById(R.id.pwd_et);

        ((Button)findViewById(R.id.config_start)).setOnClickListener(this);

        mScanImageButton = ((ImageButton)findViewById(R.id.scan_button));
        mScanImageButton.setOnClickListener(this);
        ((Button)findViewById(R.id.audio_pair)).setOnClickListener(this);

        mEyeImageButton = (EyeImageButton)findViewById(R.id.eye_button_passwd);
        mEyeImageButton.setEditText(mPwdEditText);

        mDeviceInfoTextView = (TextView)findViewById(R.id.config_device_info);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {    ///Wifi配置与声波配对的成功与否，是通过设备搜索判断的
        int id = v.getId();
        if (id == R.id.config_start){    ///wifi配置
            WIFICONFIG_OR_AUDIOPAIR = 3;
            if(checkConfigEditText()) {
                //if(getWIFIInfo().equals(ssidStr)) {
                    smartconfigTask = new SmartConfigTask();
                    smartconfigTask.execute();
//                } else {
//                    ToolKits.showMessage(WIFIConfigurationActivity.this, res.getString(R.string.please_check_wlan_ssid));
//                }
            }
        } else if(id == R.id.audio_pair){   ///声波配对
            WIFICONFIG_OR_AUDIOPAIR = 2;
            if(checkConfigEditText()) {
//                if(getWIFIInfo().equals(ssidStr)) {
                    audioPairTask = new AudioPairTask();
                    audioPairTask.execute();
//                } else {
//                    ToolKits.showMessage(WIFIConfigurationActivity.this, res.getString(R.string.please_check_wlan_ssid));
//                }
            }
        }  else if(id == R.id.scan_button) {
            startActivityForResult(new Intent(WIFIConfigurationActivity.this, CaptureActivity.class), 0);
        }
    }

    private class SmartConfigTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mDialogProgress.setMessage(res.getString(R.string.configing));
            mDialogProgress.setSpinnerType(DialogProgress.FADED_ROUND_SPINNER);
            mDialogProgress.show();
            mDeviceInfoTextView.setText("");
        }
        @Override
        protected Boolean doInBackground(String... params) {
            mDeviceSearchModule.stopSearchDevices();
            mDeviceSearchModule.startSearchDevices(callback);
            mConfigModule.startSearchIPCWifi(snStr, ssidStr, pwdStr);

            for(int i = 0; i < 400; i++) {
                try {
                    if(smartconfigTask.isCancelled()) {
                        break;
                    }
                    if(!mDialogProgress.isShowing()) {
                        smartconfigTask.cancel(false);
                        break;
                    }

                    Thread.sleep(250);

                    // 5秒发一次搜索命令
                    if(i % 20 == 0) {
                        mDeviceSearchModule.stopSearchDevices();
                        mDeviceSearchModule.startSearchDevices(callback);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(mDeviceSearchModule.lDevSearchHandle != 0) {
                mConfigModule.stopSearchIPCWifi();
                mDeviceSearchModule.stopSearchDevices();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result){
            mDialogProgress.dismiss();
            if(!result) {
                ToolKits.showMessage(WIFIConfigurationActivity.this, res.getString(R.string.config_failed));
            }
        }

        @Override
        protected void onCancelled() {
            if(mDialogProgress.isShowing()) {
                mDialogProgress.dismiss();
            }
        }
    }

    private class AudioPairTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mDialogProgress.setMessage(res.getString(R.string.configing));
            mDialogProgress.setSpinnerType(DialogProgress.FADED_ROUND_SPINNER);
            mDialogProgress.show();
            mDeviceInfoTextView.setText("");
        }
        @Override
        protected Boolean doInBackground(String... params) {
            mDeviceSearchModule.stopSearchDevices();
            mDeviceSearchModule.startSearchDevices(callback);
            mConfigModule.startAudioPair(snStr, ssidStr, pwdStr, WIFIConfigurationActivity.this);

            for(int i = 0; i < 400; i++) {
                try {
                    if(audioPairTask.isCancelled()) {
                       break;
                    }
                    if(!mDialogProgress.isShowing()) {
                        audioPairTask.cancel(false);
                        break;
                    }

                    Thread.sleep(250);

                    // 5秒发一次搜索命令
                    if(i % 20 == 0) {
                        mDeviceSearchModule.stopSearchDevices();
                        mDeviceSearchModule.startSearchDevices(callback);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(mDeviceSearchModule.lDevSearchHandle != 0) {
                mConfigModule.stopAudioData();
                mDeviceSearchModule.stopSearchDevices();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean result){
            mDialogProgress.dismiss();
            if(!result) {
                ToolKits.showMessage(WIFIConfigurationActivity.this, res.getString(R.string.config_failed));
            }
        }

        @Override
        protected void onCancelled() {
            if(mDialogProgress.isShowing()) {
                mDialogProgress.dismiss();
            }
        }
    }

    ///获取连接的wlan的名称，并设置到界面
    private String getWIFIInfo() {
        ///判断wifi状态
        ConnectivityManager conMan = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(networkInfo == null || networkInfo.getState() != NetworkInfo.State.CONNECTED) {
            ///如果没连接wifi，设为空
            mSsidEditText.setText("");
            return "";
        }

        ///如果连接了wifi，获取当前wifi名称
        WifiManager mWifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
        String nowSSID = "";
        if (wifiInfo != null) {
            nowSSID = wifiInfo.getSSID().replace("\"", "");
        }
        mSsidEditText.setText(nowSSID);
        return nowSSID;
    }

    ///修改系统音量
    private void setSystemVolumeMax() {
        try {
            AudioManager  mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
            int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            int mCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, max, 0);
        } catch (Exception e) {
            Log.e("lc Exception", "init audio error", e);
        }
    }

    ///检查序列号与wlan名称是否输入
    public boolean checkConfigEditText() {
        snStr = mSnEditText.getText().toString();
        ssidStr = mSsidEditText.getText().toString();
        pwdStr = mPwdEditText.getText().toString();

        if(snStr.length() == 0) {
            ToolKits.showMessage(WIFIConfigurationActivity.this, res.getString(R.string.please_input_device_sn));
            return false;
        }

        if(ssidStr.length() == 0) {
            ToolKits.showMessage(WIFIConfigurationActivity.this, res.getString(R.string.please_input_wlan_ssid));
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = "";
            Bundle bundle = data.getExtras();
            if (bundle != null){
                result = bundle.getString("result");
                if(result.contains(",")) {
                    result =  result.split(",")[0].split(":")[1];
                }
            }
            mSnEditText.setText(result);
        }
    }

    @Override
    protected void onDestroy(){
        if(smartconfigTask != null && smartconfigTask.getStatus() == AsyncTask.Status.RUNNING) {
            smartconfigTask.cancel(false);
        }

        if(audioPairTask != null && audioPairTask.getStatus() == AsyncTask.Status.RUNNING) {
            audioPairTask.cancel(false);
        }

        mConfigModule.stopAudioData();
        mConfigModule.stopSearchIPCWifi();
        mDeviceSearchModule.stopSearchDevices();
        mConfigModule = null;
        mDeviceSearchModule = null;

        super.onDestroy();
    }
}
