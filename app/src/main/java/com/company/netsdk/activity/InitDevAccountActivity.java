package com.company.netsdk.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.company.NetSDK.CB_fSearchDevicesCB;
import com.company.NetSDK.DEVICE_NET_INFO_EX;
import com.company.netsdk.R;
import com.company.netsdk.activity.APConfig.ApIPLoginActivity;
import com.company.netsdk.common.ClearEditText;
import com.company.netsdk.common.DialogProgress;
import com.company.netsdk.common.EyeImageButton;
import com.company.netsdk.common.ToolKits;
import com.company.netsdk.module.InitDevAccountModule;
import com.company.netsdk.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 41299 on 2018/4/20.
 */
public class InitDevAccountActivity extends AppCompatActivity implements View.OnClickListener{
    private String className = "";                 // 判断是设备初始化还是软AP配置

    private final int MSG_FIND_DEVICE = 3;       // 查询设备标志位
    private final int FIND_DEVICE_RESULT = 4;   // 查询结束标志位
    private final int DEVICE_UNABLE_INIT = 0;   // 设备不支持初始化
    private final int DEVICE_NOT_INIT = 1;      // 设备未初始化
    private final int DEVICE_INITED = 2;        // 设备已初始化

    private InitDevAccountModule mInitDevAccountModule;
    private DefaultSearchDeviceCallback searchCallback;

    private ClearEditText mSnEditText;
    private ClearEditText mPhoneEditText;
    private ClearEditText mMailEditText;
    private ImageButton mScanImageButton;
    private Button mSearchDeviceBtn;
    private TextView mDeviceInfoText;
    private Button mInitializeDeviceBtn;
    private EditText mInitUsernameEditText;
    private ClearEditText mInitPasswdEditText;
    private DialogProgress mDialogProgress;
    private EyeImageButton mEyeImageButton;

    private View mSearchView;
    private View mInitView;
    private View mPhoneView;
    private View mMailView;

    private Resources res;
    private String snStr;
    private String mUsername;
    private String mInitPasswd;
    private String mInitPhoneOrMail;
    private int passwdResetWay;

    private List<DEVICE_NET_INFO_EX> mDeviceInfoList;
    private DEVICE_NET_INFO_EX mDeviceInfo;

    DeviceSearchTask deviceSearchTask;
    InitDeviceTask initDeviceTask;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what) {
                case MSG_FIND_DEVICE:
                    DEVICE_NET_INFO_EX device_info = (DEVICE_NET_INFO_EX)msg.obj;

                    mDeviceInfoList.add(device_info);
                    if(!deviceSearchTask.isCancelled()) {
                        deviceSearchTask.cancel(false);
                    }

                    break;
                case FIND_DEVICE_RESULT:
                    mDeviceInfo = (DEVICE_NET_INFO_EX)msg.obj;

                    String text = res.getString(R.string.activity_main_ip) + ": " + new String(mDeviceInfo.szIP).trim() + "\n"
                            + res.getString(R.string.smartconfig_sn) + ": " + new String(mDeviceInfo.szSerialNo).trim() + "\n";

                    // 设备初始化状态，按位确定初始化状态
                    // bit0~1：0-老设备，没有初始化功能 1-未初始化账号 2-已初始化账户
                    // bit2~3：0-老设备，保留 1-公网接入未使能 2-公网接入已使能
                    // bit4~5：0-老设备，保留 1-手机直连未使能 2-手机直连使能
                    int initStatus = mDeviceInfo.byInitStatus & 0x03;
                    passwdResetWay = mDeviceInfo.byPwdResetWay >> 1 & 0x01;

                    if (initStatus == DEVICE_UNABLE_INIT) {       // 老设备，不支持初始化
                        ToolKits.showMessage(InitDevAccountActivity.this, res.getString(R.string.device_unable_init));
                    }else if (initStatus == DEVICE_NOT_INIT) {   // 未初始化，可以初始化
                        mSearchView.setVisibility(View.GONE);
                        mInitView.setVisibility(View.VISIBLE);

                        mDeviceInfoText.setText(text);

                        if(passwdResetWay == 0) { // 手机号， 国内设备
                            mPhoneView.setVisibility(View.VISIBLE);
                            mMailView.setVisibility(View.GONE);
                        } else if(passwdResetWay == 1){  // 邮箱，国外设备
                            mMailView.setVisibility(View.VISIBLE);
                            mPhoneView.setVisibility(View.GONE);
                        }
                    }else if (initStatus == DEVICE_INITED) {   // 设备已初始化
                        if(className.equals("InitDevAccountActivity")) {  // 用于设备初始化
                            ToolKits.showMessage(InitDevAccountActivity.this, res.getString(R.string.device_had_init));
                        } else if(className.equals("ApConfigActivity")){  // 用于软AP配置,已初始化，直接登录设备
                            Intent intent = new Intent(InitDevAccountActivity.this, ApIPLoginActivity.class);
                            intent.putExtra("ap_config_ip", new String(mDeviceInfo.szIP).trim());
                            intent.putExtra("ap_config_username", "");
                            intent.putExtra("ap_config_pwd", "");
                            startActivity(intent);
                        }
                    }

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_dev_account);

        className = getIntent().getStringExtra("className");

        if(className.equals("InitDevAccountActivity")) {  // 用于设备初始化
            setTitle(R.string.activity_main_init_dev_account);
        } else if(className.equals("ApConfigActivity")){  // 用于软AP配置
            setTitle(R.string.activity_main_ap_config);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.confirm_connect_device_hotspot);
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }

        res = this.getResources();
        mInitDevAccountModule = new InitDevAccountModule();
        searchCallback = new DefaultSearchDeviceCallback();
        mDialogProgress = new DialogProgress(this);
        mDeviceInfoList = new ArrayList<DEVICE_NET_INFO_EX>();

        // 添加返回键
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setupView();

        ToolKits.verifyCameraPermissions(InitDevAccountActivity.this);
    }

    private void setupView(){
        mSnEditText = (ClearEditText)findViewById(R.id.sn_et);
        mSearchDeviceBtn = (Button)findViewById(R.id.device_search_button);
        mSearchDeviceBtn.setOnClickListener(this);
        mScanImageButton = ((ImageButton)findViewById(R.id.scan_button));
        mScanImageButton.setOnClickListener(this);

        mDeviceInfoText = (TextView) findViewById(R.id.device_info);
        mInitializeDeviceBtn = (Button)findViewById(R.id.device_initialize_button);
        mInitializeDeviceBtn.setOnClickListener(this);

        mInitUsernameEditText = (EditText)findViewById(R.id.init_user_et);

        mInitPasswdEditText = (ClearEditText)findViewById(R.id.init_passwd_edit);

        mPhoneEditText = (ClearEditText)findViewById(R.id.init_phone_et);

        mMailEditText = (ClearEditText)findViewById(R.id.init_mail_et);

        mSearchView = (View)findViewById(R.id.search_device);
        mInitView = (View)findViewById(R.id.init_dev_account);
        mPhoneView = (View)findViewById(R.id.phone_liner);
        mMailView = (View)findViewById(R.id.mail_liner);

        mEyeImageButton = (EyeImageButton)findViewById(R.id.eye_button_passwd);
        mEyeImageButton.setEditText(mInitPasswdEditText);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.device_search_button:
                if(checkSnEditText()) {
                    deviceSearchTask = new DeviceSearchTask();
                    deviceSearchTask.execute();
                }
                break;
            case R.id.scan_button:
                startActivityForResult(new Intent(InitDevAccountActivity.this, CaptureActivity.class), 0);
                break;
            case R.id.device_initialize_button:
                if(checkInitInfo()) {
                    initDeviceTask = new InitDeviceTask();
                    initDeviceTask.execute();
                }
                break;
            default:
                break;
        }
    }

    ///检查序列号是否输入
    public boolean checkSnEditText() {
        snStr = mSnEditText.getText().toString();
        if(snStr.equals("")) {
            ToolKits.showMessage(InitDevAccountActivity.this, res.getString(R.string.please_input_device_sn));
            return false;
        }

        return true;
    }

    // 检查初始化密码，手机号、邮箱
    public boolean checkInitInfo() {
        mUsername = mInitUsernameEditText.getText().toString();

        mInitPasswd = mInitPasswdEditText.getText().toString();
        if(mInitPasswd.equals("")) {
            ToolKits.showMessage(InitDevAccountActivity.this, res.getString(R.string.please_input_init_passwd));
            return false;
        }

        if(passwdResetWay == 0) { // 手机号， 国内设备
            mInitPhoneOrMail = mPhoneEditText.getText().toString();
            if(mInitPhoneOrMail.equals("")) {
                ToolKits.showMessage(InitDevAccountActivity.this, res.getString(R.string.please_input_init_phone));
                return false;
            }
        } else if(passwdResetWay == 1){  // 邮箱，国外设备
            mInitPhoneOrMail = mMailEditText.getText().toString();
            if(mInitPhoneOrMail.equals("")) {
                ToolKits.showMessage(InitDevAccountActivity.this, res.getString(R.string.please_input_init_mail));
                return false;
            }
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

    ///Default Search Device Callback
    ///设备搜索回调
    private class DefaultSearchDeviceCallback implements  CB_fSearchDevicesCB
    {
        public void invoke(DEVICE_NET_INFO_EX device_net_info_ex) {
            ///Search Device by SerialNo
            ///通过序列号搜索设备
            String serialNo = new String(device_net_info_ex.szSerialNo).trim();

            // 根据序列号过滤,
            if(serialNo.equals(snStr)
                    && device_net_info_ex.iIPVersion == 4
                    && device_net_info_ex.szIP.length > 3    // 有可能有 /0  /1   /64
                    && !new String(device_net_info_ex.szIP).trim().equals("")) {

                Message msg = mHandler.obtainMessage(MSG_FIND_DEVICE);
                msg.obj = device_net_info_ex;
                mHandler.sendMessage(msg);
            }
        }
    }

    /// Device  Search Task
    private class DeviceSearchTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mDialogProgress.setMessage(res.getString(R.string.waiting));
            mDialogProgress.setSpinnerType(DialogProgress.FADED_ROUND_SPINNER);
            mDialogProgress.show();
        }
        @Override
        protected Boolean doInBackground(String... params) {
            mDeviceInfoList.clear();
            mDeviceInfo = null;

            mInitDevAccountModule.stopSearchDevices();
            mInitDevAccountModule.startSearchDevices(searchCallback);

            for(int i = 0; i < 400; i++) {
                try {
                    // 判断有没有搜索到设备
                    if(deviceSearchTask.isCancelled()) {
                        break;
                    }
                    // 判断进度框有没有取消
                    if(!mDialogProgress.isShowing()) {
                        deviceSearchTask.cancel(false);
                    }
                    Thread.sleep(250);

                    // 5秒发一次搜索命令
                    if(i % 20 == 0) {
                        mInitDevAccountModule.stopSearchDevices();
                        mInitDevAccountModule.startSearchDevices(searchCallback);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 搜索到设备信息，等待5秒，主要是单播初始化会用到设备IP，由于设备还是DHCP，设备返回的IP可能不同
            if(deviceSearchTask.isCancelled()
                    && mDeviceInfoList.size() > 0
                    && mDialogProgress.isShowing()) {
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 没搜到设备信息以及取消等待框，只有停止搜索的操作
            mInitDevAccountModule.stopSearchDevices();

            // 搜索到设备信息，才有的操作
            if(deviceSearchTask.isCancelled()
                    && mDeviceInfoList.size() > 0
                    && mDialogProgress.isShowing()) {
                ToolKits.writeLog("mDeviceInfoList size:" + mDeviceInfoList.size());
                for(int j = 0; j < mDeviceInfoList.size(); j++) {
                    ToolKits.writeLog("IP:" + new String(mDeviceInfoList.get(j).szIP).trim());
                }

                Message msg = mHandler.obtainMessage(FIND_DEVICE_RESULT);
                msg.obj = mDeviceInfoList.get(mDeviceInfoList.size() - 1);   // 取最新的设备信息
                mHandler.sendMessage(msg);
            }

            return false;
        }
        @Override
        protected void onPostExecute(Boolean result){
            mDialogProgress.dismiss();
            if (!result) {
                ToolKits.showMessage(InitDevAccountActivity.this, res.getString(R.string.device_search_failed));
            }
        }

        @Override
        protected void onCancelled() {
            if(mDialogProgress.isShowing()) {
                mDialogProgress.dismiss();
            }
        }
    }

    /// Init Device Task
    private class InitDeviceTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mDialogProgress.setMessage(res.getString(R.string.waiting));
            mDialogProgress.setSpinnerType(DialogProgress.FADED_ROUND_SPINNER);
            mDialogProgress.setCancelable(false);
            mDialogProgress.show();
        }
        @Override
        protected Boolean doInBackground(String... params) {
            boolean initState;
            boolean bInit = mInitDevAccountModule.initDevAccount(mDeviceInfo, mUsername, mInitPasswd, mInitPhoneOrMail);
            boolean bInitByIp = mInitDevAccountModule.initDevAccountByIP(mDeviceInfo, mUsername, mInitPasswd, mInitPhoneOrMail);

            if(bInit || bInitByIp) {
                initState = true;
            } else {
                initState = false;
            }

            return initState;
        }
        @Override
        protected void onPostExecute(Boolean result){
            mDialogProgress.dismiss();
            if (result) {
                if(className.equals("InitDevAccountActivity")) {  // 设备初始化成功
                    ToolKits.showMessage(InitDevAccountActivity.this, res.getString(R.string.init_device_success));
                    mDeviceInfoText.append(res.getString(R.string.init_device_success));
                } else if(className.equals("ApConfigActivity")){  // 用于软AP配置,初始化成功，去登录设备
                    Intent intent = new Intent(InitDevAccountActivity.this, ApIPLoginActivity.class);
                    intent.putExtra("ap_config_ip", new String(mDeviceInfo.szIP).trim());
                    intent.putExtra("ap_config_username", mUsername);
                    intent.putExtra("ap_config_pwd", mInitPasswd);
                    startActivity(intent);
                }
            } else {
                ToolKits.showMessage(InitDevAccountActivity.this, res.getString(R.string.init_device_failed));
            }
        }
    }

    @Override
    protected void onDestroy(){
        if(deviceSearchTask != null && deviceSearchTask.getStatus() == AsyncTask.Status.RUNNING) {
            deviceSearchTask.cancel(false);
        }

        if(initDeviceTask != null && initDeviceTask.getStatus() == AsyncTask.Status.RUNNING) {
            initDeviceTask.cancel(false);
        }

        mInitDevAccountModule.stopSearchDevices();
        mInitDevAccountModule = null;

        className = "";

        super.onDestroy();
    }
}
