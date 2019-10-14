package com.company.netsdk.activity;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.company.netsdk.R;
import com.company.netsdk.common.ClearEditText;
import com.company.netsdk.common.DialogProgress;
import com.company.netsdk.common.EyeImageButton;
import com.company.netsdk.module.P2PLoginModule;
import com.company.netsdk.common.PrefsConstants;
import com.company.netsdk.common.ToolKits;
import com.company.netsdk.zxing.activity.CaptureActivity;

public class P2PLoginActivity extends AppCompatActivity {
    private static final String TAG = "P2PLoginActivity";
    private ClearEditText mEditTextServerIp;
    private ClearEditText mEditTextServerPort;
    private ClearEditText mEditTextP2pUsername;
    private ClearEditText mEditTextP2pPassword;
    private ClearEditText mEditTextP2pDeviceId;
    private ClearEditText mEditTextP2pDevicePort;
    private ClearEditText mEditTextP2pDeviceUsername;
    private ClearEditText mEditTextP2pDevicePassword;
    private EyeImageButton mP2PEyeImageButton;
    private EyeImageButton mDeviceEyeImageButton;

    private String serverIp;
    private String serverPort;
    private String p2pUsername;
    private String p2pPassword;
    private String p2pDeviceId;
    private String p2pDevicePort;
    private String p2pDeviceUsername;
    private String p2pDevicePassword;

    private CheckBox mCheckBoxP2p;
    private Button mButtonLogin;
    private SharedPreferences mSharedPrefs;
    private ImageButton scanButton;

    private P2PLoginModule mP2pLoginModule;
    private NetSDKApplication app;
    private DialogProgress mDialogProgress;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2plogin);
        setTitle(R.string.activity_main_p2p);

        mP2pLoginModule = new P2PLoginModule();
        mDialogProgress = new DialogProgress(this);
        /// get global data
        app = (NetSDKApplication)getApplication();
        res = getResources();

        // 添加返回键
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setupView();

        ToolKits.verifyCameraPermissions(P2PLoginActivity.this);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        mP2pLoginModule.logout();
        mP2pLoginModule.stopP2pService();
        mP2pLoginModule = null;

        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        // while onResume we should logout the device.
        mP2pLoginModule.logout();
        mP2pLoginModule.stopP2pService();
        super.onResume();
    }

    private void setupView() {
        mEditTextServerIp = (ClearEditText)findViewById(R.id.editTextP2pServerIp);
        mEditTextServerPort = (ClearEditText)findViewById(R.id.editTextP2pServerPort);
        mEditTextP2pUsername = (ClearEditText)findViewById(R.id.editTextP2pUsername);
        mEditTextP2pPassword = (ClearEditText)findViewById(R.id.editTextP2pPassword);
        mEditTextP2pDeviceId = (ClearEditText)findViewById(R.id.editTextP2pDeviceId);
        mEditTextP2pDevicePort = (ClearEditText)findViewById(R.id.editTextP2pDevicePort);
        mEditTextP2pDeviceUsername = (ClearEditText)findViewById(R.id.editTextP2pDeviceUsername);
        mEditTextP2pDevicePassword = (ClearEditText)findViewById(R.id.editTextP2pDeivcePassword);
        mCheckBoxP2p = (CheckBox)findViewById(R.id.checkBoxP2p);

        mButtonLogin = (Button)findViewById(R.id.buttonP2pLogin);
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkLoginEditText()) {
                    P2PLoginTask loginTask = new P2PLoginTask();
                    loginTask.execute();
                }
            }
        });

        scanButton = (ImageButton)findViewById(R.id.scan_button_p2p);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(P2PLoginActivity.this, CaptureActivity.class), 0);
            }
        });

        mP2PEyeImageButton = (EyeImageButton)findViewById(R.id.eye_p2p_pwd_button_p2p);
        mDeviceEyeImageButton = (EyeImageButton)findViewById(R.id.eye_device_pwd_button_p2p);

        mP2PEyeImageButton.setEditText(mEditTextP2pPassword);
        mDeviceEyeImageButton.setEditText(mEditTextP2pDevicePassword);

        getSharePrefs();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
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
            mEditTextP2pDeviceId.setText(result);
        }
    }

    private class P2PLoginTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mDialogProgress.setMessage(res.getString(R.string.logining));
            mDialogProgress.setSpinnerType(DialogProgress.FADED_ROUND_SPINNER);
            mDialogProgress.setCancelable(false);
            mDialogProgress.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result =  false;
            if(mP2pLoginModule.startP2pService(serverIp, serverPort, p2pUsername, p2pPassword,
                    p2pDeviceId, p2pDevicePort)) {
                result = mP2pLoginModule.login(p2pDeviceUsername, p2pDevicePassword);
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result){
            mDialogProgress.dismiss();
            if(!mP2pLoginModule.isServiceStarted()) {
                ToolKits.showMessage(P2PLoginActivity.this, res.getString(R.string.failed_start_server));
            } else {
                if (result) {
                    putSharePrefs();

                    app.setLoginHandle(mP2pLoginModule.getLoginHandle());
                    app.setDeviceInfo(mP2pLoginModule.getDeviceInfo());

                    startActivity(new Intent(P2PLoginActivity.this, FunctionListActivity.class));
                } else {
                    ToolKits.showMessage(P2PLoginActivity.this, IPLoginActivity.getErrorCode(getResources(), mP2pLoginModule.errorCode()));
                }
            }
        }
    }

    private void getSharePrefs() {
        mSharedPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        mEditTextServerIp.setText(mSharedPrefs.getString(PrefsConstants.P2P_SERVER_IP, "www.dahuap2pcloud.com"));
        mEditTextServerPort.setText(mSharedPrefs.getString(PrefsConstants.P2P_SERVER_PORT, "8800"));
        mEditTextP2pUsername.setText(mSharedPrefs.getString(PrefsConstants.P2P_SERVER_USERNAME, "username"));
        mEditTextP2pPassword.setText(mSharedPrefs.getString(PrefsConstants.P2P_SERVER_PASSWORD, "input-password"));
        mEditTextP2pDeviceId.setText(mSharedPrefs.getString(PrefsConstants.P2P_DEVICE_ID, "input-deviceid"));
        mEditTextP2pDevicePort.setText(mSharedPrefs.getString(PrefsConstants.P2P_DEVICE_PORT, "37777"));
        mEditTextP2pDeviceUsername.setText(mSharedPrefs.getString(PrefsConstants.P2P_DEVICE_USERNAME, "admin"));
        mEditTextP2pDevicePassword.setText(mSharedPrefs.getString(PrefsConstants.P2P_DEVICE_PASSWORD, "admin"));
        mCheckBoxP2p.setChecked(mSharedPrefs.getBoolean(PrefsConstants.P2P_CHECK, false));

        editor.apply();
    }

    private void putSharePrefs() {
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        if (mCheckBoxP2p.isChecked()) {
            editor.putString(PrefsConstants.P2P_SERVER_IP, serverIp);
            editor.putString(PrefsConstants.P2P_SERVER_PORT, serverPort);
            editor.putString(PrefsConstants.P2P_SERVER_USERNAME, p2pUsername);
            editor.putString(PrefsConstants.P2P_SERVER_PASSWORD, p2pPassword);
            editor.putString(PrefsConstants.P2P_DEVICE_ID, p2pDeviceId);
            editor.putString(PrefsConstants.P2P_DEVICE_PORT, p2pDevicePort);
            editor.putString(PrefsConstants.P2P_DEVICE_USERNAME, p2pDeviceUsername);
            editor.putString(PrefsConstants.P2P_DEVICE_PASSWORD, p2pDevicePassword);
            editor.putBoolean(PrefsConstants.P2P_CHECK, true);
        }
        editor.apply();
    }

    private boolean checkLoginEditText() {
        serverIp = mEditTextServerIp.getText().toString();
        serverPort = mEditTextServerPort.getText().toString();
        p2pUsername = mEditTextP2pUsername.getText().toString();
        p2pPassword = mEditTextP2pPassword.getText().toString();
        p2pDeviceId = mEditTextP2pDeviceId.getText().toString();
        p2pDevicePort = mEditTextP2pDevicePort.getText().toString();
        p2pDeviceUsername = mEditTextP2pDeviceUsername.getText().toString();
        p2pDevicePassword = mEditTextP2pDevicePassword.getText().toString();

        if(serverIp.length() == 0) {
            ToolKits.showMessage(P2PLoginActivity.this, res.getString(R.string.activity_p2plogin_server_ip_empty));
            return false;
        }
        if(serverPort.length() == 0) {
            ToolKits.showMessage(P2PLoginActivity.this,res.getString(R.string.activity_p2plogin_server_port_empty));
            return false;
        }
        if(p2pPassword.length() == 0) {
            ToolKits.showMessage(P2PLoginActivity.this,res.getString(R.string.activity_p2plogin_password_empty));
            return false;
        }

        if(p2pDeviceId.length() == 0) {
            ToolKits.showMessage(P2PLoginActivity.this,res.getString(R.string.activity_p2plogin_device_sn_empty));
            return false;
        }

        if (p2pDeviceUsername.length() == 0) {
            ToolKits.showMessage(P2PLoginActivity.this,res.getString(R.string.activity_iplogin_username_empty));
            return false;
        }

        if (p2pDevicePassword.length() == 0) {
            ToolKits.showMessage(P2PLoginActivity.this,res.getString(R.string.activity_iplogin_password_empty));
            return false;
        }

        try {
            Integer.parseInt(p2pDevicePort);
        } catch (Exception e) {
            e.printStackTrace();
            ToolKits.showMessage(P2PLoginActivity.this, res.getString(R.string.activity_iplogin_port_err));
            return false;
        }
        return true;
    }
}
