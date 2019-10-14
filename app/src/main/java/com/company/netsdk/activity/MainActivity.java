package com.company.netsdk.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.company.netsdk.R;
import com.company.netsdk.common.NetSDKLib;
import com.company.netsdk.common.ToolKits;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button mButtonIpLogin;
    private Button mButtonP2PLogin;
    private Button mButtonWifiConfig;
    private Button mButtonDeviceSearch;
    private Button mButtonInitDevAccount;
    private Button mButtonApConfig;

    private final View.OnClickListener mButtonHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mButtonIpLogin && mButtonIpLogin != null) {
                startActivity(new Intent(MainActivity.this, IPLoginActivity.class));
            }else if (v == mButtonP2PLogin && mButtonP2PLogin != null) {
                startActivity(new Intent(MainActivity.this, P2PLoginActivity.class));
            }else if (v == mButtonWifiConfig && mButtonWifiConfig != null) {
                startActivity(new Intent(MainActivity.this, WIFIConfigurationActivity.class));
            }else if (v == mButtonDeviceSearch && mButtonDeviceSearch != null) {
                startActivity(new Intent(MainActivity.this, DeviceSearchActivity.class));
            }else if (v == mButtonInitDevAccount && mButtonInitDevAccount != null) {
                Intent intent = new Intent(MainActivity.this, InitDevAccountActivity.class);
                intent.putExtra("className", "InitDevAccountActivity");

                startActivity(intent);
            }else if(v == mButtonApConfig && mButtonApConfig != null) {
                Intent intent = new Intent(MainActivity.this, InitDevAccountActivity.class);
                intent.putExtra("className", "ApConfigActivity");

                startActivity(intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /// Initializing the NetSDKLib is important and necessary to ensure that
        /// all the APIs of INetSDK.jar are effective.
        /// 注意: 必须调用 init 接口初始化 INetSDK.jar 仅需要一次初始化
        NetSDKLib.getInstance().init();

        // Open sdk log    /mnt/sdcard/sdk_log.log
//        final String file = new String(Environment.getExternalStorageDirectory().getPath() + "/sdk_log.log");
//
//        File logfile = new File(file);
//        if (!logfile.exists()) {
//            try {
//                logfile.createNewFile();
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        NetSDKLib.getInstance().openLog(file);

        ToolKits.verifyStoragePermissions(MainActivity.this);
        setupView();
    }

    @Override
    protected void onDestroy() {
        // while exiting the application, please make sure to invoke cleanup.
        /// 退出应用后，调用 cleanup 清理资源
        NetSDKLib.getInstance().cleanup();
        super.onDestroy();
    }

    private void setupView() {
        mButtonIpLogin = (Button) findViewById(R.id.btnIPLogin);
        mButtonIpLogin.setOnClickListener(mButtonHandler);

        mButtonP2PLogin = (Button) findViewById(R.id.btnP2PLogin);
        mButtonP2PLogin.setOnClickListener(mButtonHandler);

        mButtonWifiConfig = (Button) findViewById(R.id.btnWIFIConfig);
        mButtonWifiConfig.setOnClickListener(mButtonHandler);

        mButtonDeviceSearch = (Button) findViewById(R.id.btnDevcieSearch);
        mButtonDeviceSearch.setOnClickListener(mButtonHandler);

        mButtonInitDevAccount = (Button) findViewById(R.id.btnInitDevAccount);
        mButtonInitDevAccount.setOnClickListener(mButtonHandler);

        mButtonApConfig = (Button) findViewById(R.id.btnApConfig);
        mButtonApConfig.setOnClickListener(mButtonHandler);
    }
}


