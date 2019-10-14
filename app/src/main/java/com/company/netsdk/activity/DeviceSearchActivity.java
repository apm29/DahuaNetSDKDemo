package com.company.netsdk.activity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.company.NetSDK.CB_fSearchDevicesCB;
import com.company.NetSDK.DEVICE_NET_INFO_EX;
import com.company.netsdk.R;
import com.company.netsdk.module.DeviceSearchModule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 32940 on 2018/6/29.
 */
public class DeviceSearchActivity extends AppCompatActivity {
    private  boolean mSearchFlag = false;
    final Set<String> inforSet = new HashSet<String>();
    Resources res;
    private final int UPDATE_DEVICE_SEARCH_INFOR = 0x10;

    private ListView mInforLv;
    private Button mSearchDeviceBtn;
    private DeviceSearchModule mDeviceSearchModule;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_DEVICE_SEARCH_INFOR:   ///设备搜索结果展示在列表里
                    Log.i("SearchDevMessage","infor:"+(String)msg.obj);
                    ((InforAdapter)mInforLv.getAdapter()).insertInfor((String)msg.obj);
                    break;
                default:
                    break;
            }
        }
    };

    private CB_fSearchDevicesCB callback = new  CB_fSearchDevicesCB(){

        @Override
        public void invoke(DEVICE_NET_INFO_EX device_net_info_ex) {
            String temp = res.getString(R.string.activity_iplogin_device_ip) + " : "+ new String(device_net_info_ex.szIP).trim() + "\n" +
                    res.getString(R.string.activity_p2plogin_device_id) + " : " + new String(device_net_info_ex.szSerialNo).trim();

            ///Search Device，Filter repeated
            ///设备搜索功能，过滤重复的
            if(!inforSet.contains(temp)
                    && !new String(device_net_info_ex.szIP).trim().contains("/")){
                inforSet.add(temp);
                Message msg1 = mHandler.obtainMessage(UPDATE_DEVICE_SEARCH_INFOR);
                msg1.obj = temp;
                mHandler.sendMessage(msg1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicesearch);
        setTitle(R.string.activity_main_search_device);

        res = this.getResources();
        mDeviceSearchModule = new DeviceSearchModule(this);

        // 添加返回键
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setupView();
    }

    private void setupView(){
        mInforLv = (ListView)findViewById(R.id.device_search_list);
        mInforLv.setAdapter(new InforAdapter());

        mSearchDeviceBtn = (Button)findViewById(R.id.device_search_button);
        mSearchDeviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mSearchFlag) {
                    ((InforAdapter)mInforLv.getAdapter()).clean();
                    inforSet.clear();
                    mDeviceSearchModule.startSearchDevices(callback);
                    mSearchDeviceBtn.setText(res.getString(R.string.config_stopsearchdevice));
                    mSearchFlag = true;
                } else {
                    mDeviceSearchModule.stopSearchDevices();
                    mSearchDeviceBtn.setText(res.getString(R.string.config_startsearchdevice));
                    mSearchFlag = false;
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private class InforAdapter extends BaseAdapter {
        private ArrayList<String> list = new ArrayList<String>();
        public InforAdapter(){
            if (list == null)
                list = new ArrayList<String>();
        }
        public void insertInfor(String  i){
            this.list.add(i);
            this.notifyDataSetChanged();
            mInforLv.setSelection(this.list.size()-1);
        }
        public void clean(){
            this.list.clear();
            this.notifyDataSetChanged();
        }
        @Override
        public int getCount() {
            return this.list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView temp = null;
            if(temp == null) {
                temp = new TextView(DeviceSearchActivity.this);
                AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                temp.setLayoutParams(params);
                temp.setTextColor(Color.BLACK);
            }
            temp.setText(list.get(position));
            return temp;
        }
    };

    @Override
    protected void onDestroy(){
        ((InforAdapter)mInforLv.getAdapter()).clean();
        inforSet.clear();
        mDeviceSearchModule.stopSearchDevices();
        mDeviceSearchModule = null;

        super.onDestroy();
    }
}
