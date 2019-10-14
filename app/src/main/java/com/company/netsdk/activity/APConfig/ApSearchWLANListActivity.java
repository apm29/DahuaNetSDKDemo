package com.company.netsdk.activity.APConfig;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.company.NetSDK.FinalVar;
import com.company.NetSDK.INetSDK;
import com.company.NetSDK.NET_IN_WLAN_ACCESSPOINT;
import com.company.NetSDK.NET_OUT_WLAN_ACCESSPOINT;
import com.company.NetSDK.SDKDEV_WLAN_DEVICE_LIST_EX;
import com.company.netsdk.R;
import com.company.netsdk.activity.NetSDKApplication;
import com.company.netsdk.common.DialogProgress;
import com.company.netsdk.common.ToolKits;

/**
 * Created by 32940 on 2018/12/12.
 */
public class ApSearchWLANListActivity extends AppCompatActivity {
    private Resources                    res;

    private RecyclerView                 recyclerView;
    private ApWlanListDateAdapter        wlanAdapter;

    private DialogProgress               mProgressDialog;						// 等待框

    private GetWlanListTask              mGetWlanListTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ap_search_wlan_list);

        res = this.getResources();

        Toolbar toolbar = (Toolbar) findViewById(R.id.ap_config_toolbar);
        toolbar.setTitle(res.getString(R.string.ap_search_wlan_list));  // 在setSupportActionBar之前调用，否则无效；或者在onResume里调用
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        wlanAdapter = new ApWlanListDateAdapter(null);
        recyclerView = (RecyclerView)findViewById(R.id.ap_wlan_list_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(wlanAdapter);

        mProgressDialog = new DialogProgress(this);

        mGetWlanListTask = new GetWlanListTask();
        mGetWlanListTask.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fresh_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.refresh_btn) {        // 刷新，获取WLAN列表
            mGetWlanListTask = new GetWlanListTask();
            mGetWlanListTask.execute();

            return true;
        } else if(id == android.R.id.home){  // 返回
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private class ApWlanListDateHolder extends RecyclerView.ViewHolder {
        private RelativeLayout  mApWlanLayout;
        private TextView        mWlanNameTextView;

        public ApWlanListDateHolder(View view) {
            super(view);

            mApWlanLayout = (RelativeLayout)view.findViewById(R.id.ap_wlan_layout);
            mWlanNameTextView = (TextView)view.findViewById(R.id.ap_wlan_name_text);
        }
    }

    private class ApWlanListDateAdapter extends RecyclerView.Adapter<ApWlanListDateHolder> {
        private SDKDEV_WLAN_DEVICE_LIST_EX mWlanDeviceList;
        private int nItemCount = 0;

        public ApWlanListDateAdapter(SDKDEV_WLAN_DEVICE_LIST_EX mWlanDeviceList) {
            this.mWlanDeviceList = mWlanDeviceList;
            if(mWlanDeviceList != null) {
                nItemCount = mWlanDeviceList.bWlanDevCount;
            }
        }

        public void setWlanDeviceList(SDKDEV_WLAN_DEVICE_LIST_EX mWlanDeviceList) {
            this.mWlanDeviceList = mWlanDeviceList;
            if(mWlanDeviceList != null) {
                nItemCount = mWlanDeviceList.bWlanDevCount;
            }
        }

        @Override
        public ApWlanListDateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.ap_wlan_text, parent, false);

            return new ApWlanListDateHolder(view);
        }

        @Override
        public void onBindViewHolder(final ApWlanListDateHolder holder, final int position) {
            if(mWlanDeviceList == null) {
                return;
            }

            String ssid = new String(mWlanDeviceList.lstWlanDev[position].szSSID).trim();

            holder.mWlanNameTextView.setText(ssid);

            holder.mApWlanLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.mWlanNameTextView.getText().toString().isEmpty()) {
                        return;
                    }

                    Intent intent = new Intent(ApSearchWLANListActivity.this, ApConfigActivity.class);

                    intent.putExtra("wlan_name", new String(mWlanDeviceList.lstWlanDev[position].szSSID).trim());
                    intent.putExtra("wlan_authMode", (mWlanDeviceList.lstWlanDev[position].byAuthMode & 0xff));
                    intent.putExtra("wlan_encrAlgr", (mWlanDeviceList.lstWlanDev[position].byEncrAlgr & 0xff));

                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return nItemCount;
        }
    }

    /**
     * 三代查询接口，对应关系用 Encryption_3.java
     */
    private void QueryWlanAccessPoint() {
		/*
		 * 入参
 		 */
        NET_IN_WLAN_ACCESSPOINT stIn = new NET_IN_WLAN_ACCESSPOINT();

        // 需要获取信息的无线网络名称,为空时搜索所有网络
        String ssid = "";
        System.arraycopy(ssid.getBytes(), 0, stIn.szSSID, 0, ssid.getBytes().length);

        //  网卡名称, 为空时, 默认为eth2
        String name = "wlan0";
        System.arraycopy(name.getBytes(), 0, stIn.szName, 0, name.getBytes().length);

        // 出参
        NET_OUT_WLAN_ACCESSPOINT stOut = new NET_OUT_WLAN_ACCESSPOINT();

        if(INetSDK.QueryDevInfo(NetSDKApplication.getInstance().getLoginHandle(),FinalVar.NET_QUERY_WLAN_ACCESSPOINT, stIn, stOut, null, 5000)) {
            for(int i = 0; i < stOut.nCount; i++) {
                ToolKits.writeLog("无线网络名称:" + new String(stOut.stuInfo[i].szSSID).trim());
                ToolKits.writeLog("认证模式:" + stOut.stuInfo[i].nAuthMode);
                ToolKits.writeLog("加密模式:" +stOut.stuInfo[i].nEncrAlgr + "\n");
            }
        } else {
            ToolKits.writeErrorLog("查询无线网络接入点信息失败.");
        }
    }

    /// 获取WLAN列表
    private class GetWlanListTask extends AsyncTask<String, Integer, Boolean> {
        SDKDEV_WLAN_DEVICE_LIST_EX[]     stListEx;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mProgressDialog.setMessage(res.getString(R.string.waiting));
            mProgressDialog.setSpinnerType(DialogProgress.FADED_ROUND_SPINNER);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }
        @Override
        protected Boolean doInBackground(String... params) {
            stListEx = new SDKDEV_WLAN_DEVICE_LIST_EX[1];
            stListEx[0] = new SDKDEV_WLAN_DEVICE_LIST_EX();
            Integer stIntRet = new Integer(0);

            /**
             * 二代查询接口，对应关系用 Encryption_2.java
             */
            if(!INetSDK.GetDevConfig(NetSDKApplication.getInstance().getLoginHandle(), FinalVar.SDK_DEV_WLAN_DEVICE_CFG_EX, -1, stListEx, stIntRet, 5000)) {
               return false;
            }

            return true;
        }
        @Override
        protected void onPostExecute(Boolean result){
            mProgressDialog.dismiss();

            if (result) {
                wlanAdapter.setWlanDeviceList(stListEx[0]);
                wlanAdapter.notifyDataSetChanged();

                ToolKits.showMessage(ApSearchWLANListActivity.this, res.getString(R.string.get_wlan_list_succeed));
            } else {
                wlanAdapter.setWlanDeviceList(null);
                wlanAdapter.notifyDataSetChanged();

                ToolKits.showMessage(ApSearchWLANListActivity.this, res.getString(R.string.get_wlan_list_failed));
            }
        }

        @Override
        protected void onCancelled() {
            if(mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mGetWlanListTask != null && mGetWlanListTask.getStatus() == AsyncTask.Status.RUNNING) {
            mGetWlanListTask.cancel(false);
        }

        if(mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        if(mGetWlanListTask != null && mGetWlanListTask.getStatus() == AsyncTask.Status.RUNNING) {
            mGetWlanListTask.cancel(false);
        }

        if(mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        super.onDestroy();
    }
}
