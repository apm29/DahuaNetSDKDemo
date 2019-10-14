package com.company.netsdk.activity.APConfig;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.company.netsdk.R;
import com.company.netsdk.activity.NetSDKApplication;
import com.company.netsdk.common.ClearEditText;
import com.company.netsdk.common.DialogProgress;
import com.company.netsdk.common.EyeImageButton;
import com.company.netsdk.common.ToolKits;
import com.company.netsdk.module.IPLoginModule;

/**
 * Created by 32940 on 2018/12/12.
 */
public class ApIPLoginActivity extends AppCompatActivity {
    private ClearEditText mEditTextUsername;
    private ClearEditText mEditTextPassword;
    private EyeImageButton mEyeImageButton;

    private IPLoginModule mLoginModule;
    private NetSDKApplication app;
    private DialogProgress mDialogProgress;
    private Resources res;

    private String ap_config_ip;
    private String ap_config_username;
    private String ap_config_pwd;

    private LoginTask loginTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ap_login);
        setTitle(R.string.activity_main_ip_domin);

        /// get global data
        app = (NetSDKApplication)getApplication();
        res = getResources();
        mDialogProgress = new DialogProgress(this);
        mLoginModule = new IPLoginModule();

        // 添加返回键
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setupView();
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void setupView() {
        mEditTextUsername = (ClearEditText)findViewById(R.id.editTextUsername);
        mEditTextPassword = (ClearEditText)findViewById(R.id.editTextPassword);

        mEditTextUsername.setText("admin");
        mEditTextPassword.setText("admin123");

        Button ButtonLogin = (Button)findViewById(R.id.buttonLogin);
        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkLoginEditText()) {
                    loginTask = new LoginTask();
                    loginTask.execute();
                }
            }
        });
        mEyeImageButton = (EyeImageButton)findViewById(R.id.eye_button_passwd);
        mEyeImageButton.setEditText(mEditTextPassword);

        ap_config_ip = getIntent().getStringExtra("ap_config_ip");
        ap_config_username = getIntent().getStringExtra("ap_config_username");
        ap_config_pwd = getIntent().getStringExtra("ap_config_pwd");


        if(!ap_config_username.isEmpty()) {
            mEditTextUsername.setText(ap_config_username);
        }

        if(!ap_config_pwd.isEmpty()) {
            mEditTextPassword.setText(ap_config_pwd);
        }
    }

    /// LoginTask
    private class LoginTask extends AsyncTask<String, Integer, Boolean> {
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
            return mLoginModule.login(ap_config_ip, "37777", ap_config_username, ap_config_pwd);
        }
        @Override
        protected void onPostExecute(Boolean result){
            mDialogProgress.dismiss();
            if (result) {
                app.setLoginHandle(mLoginModule.getLoginHandle());
                app.setDeviceInfo(mLoginModule.getDeviceInfo());

                startActivity(new Intent(ApIPLoginActivity.this, ApSearchWLANListActivity.class));
            } else {
                ToolKits.showMessage(ApIPLoginActivity.this, getErrorCode(getResources(), mLoginModule.errorCode()));
            }
        }

        @Override
        protected void onCancelled() {
            if(mDialogProgress.isShowing()) {
                mDialogProgress.dismiss();
            }
        }
    }

    private boolean checkLoginEditText() {
        ap_config_username = mEditTextUsername.getText().toString();
        ap_config_pwd = mEditTextPassword.getText().toString();

        if(ap_config_username.length() == 0) {
            ToolKits.showMessage(ApIPLoginActivity.this, res.getString(R.string.activity_iplogin_username_empty));
            return false;
        }
        if(ap_config_pwd.length() == 0) {
            ToolKits.showMessage(ApIPLoginActivity.this, res.getString(R.string.activity_iplogin_password_empty));
            return false;
        }

        return true;
    }

    public static String getErrorCode(Resources res, int errorCode) {
        switch(errorCode) {
            case IPLoginModule.NET_USER_FLASEPWD_TRYTIME:
                return res.getString(R.string.NET_USER_FLASEPWD_TRYTIME);
            case IPLoginModule.NET_LOGIN_ERROR_PASSWORD:
                return res.getString(R.string.NET_LOGIN_ERROR_PASSWORD);
            case IPLoginModule.NET_LOGIN_ERROR_USER:
                return res.getString(R.string.NET_LOGIN_ERROR_USER);
            case IPLoginModule.NET_LOGIN_ERROR_TIMEOUT:
                return res.getString(R.string.NET_LOGIN_ERROR_TIMEOUT);
            case IPLoginModule.NET_LOGIN_ERROR_RELOGGIN:
                return res.getString(R.string.NET_LOGIN_ERROR_RELOGGIN);
            case IPLoginModule.NET_LOGIN_ERROR_LOCKED:
                return res.getString(R.string.NET_LOGIN_ERROR_LOCKED);
            case IPLoginModule.NET_LOGIN_ERROR_BLACKLIST:
                return res.getString(R.string.NET_LOGIN_ERROR_BLACKLIST);
            case IPLoginModule.NET_LOGIN_ERROR_BUSY:
                return res.getString(R.string.NET_LOGIN_ERROR_BUSY);
            case IPLoginModule.NET_LOGIN_ERROR_CONNECT:
                return res.getString(R.string.NET_LOGIN_ERROR_CONNECT);
            case IPLoginModule.NET_LOGIN_ERROR_NETWORK:
                return res.getString(R.string.NET_LOGIN_ERROR_NETWORK);
            default:
                return res.getString(R.string.NET_ERROR);
        }
    }

    @Override
    protected void onResume() {
        // while onResume we should logout the device.
        mLoginModule.logout();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(loginTask != null && loginTask.getStatus() == AsyncTask.Status.RUNNING) {
            loginTask.cancel(false);
        }

        if(mDialogProgress.isShowing()) {
            mDialogProgress.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(loginTask != null && loginTask.getStatus() == AsyncTask.Status.RUNNING) {
            loginTask.cancel(false);
        }

        if(mDialogProgress.isShowing()) {
            mDialogProgress.dismiss();
        }

        if(null != mLoginModule) {
            mLoginModule.logout();
            mLoginModule = null;
        }
    }
}
