package com.company.netsdk.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.company.netsdk.R;
import com.company.netsdk.module.LivePreviewDoubleChannelModule;

import java.util.ArrayList;

public class LivePreviewDoubleChannelActivity extends AppCompatActivity implements View.OnClickListener{
    LivePreviewDoubleChannelModule mLiveDoubleChannelModule;
    boolean bFirstPlaying = false;
    boolean bSecondPlaying = false;
    SurfaceView mView1;
    SurfaceView mView2;
    Button btnStartReplay2;
    Spinner mSelectStream1;
    Spinner mSelectChannel1;
    Spinner mSelectStream2;
    Spinner mSelectChannel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLiveDoubleChannelModule = new LivePreviewDoubleChannelModule(this);

        setTitle(R.string.activity_function_list_double_channel);
        setContentView(R.layout.activity_double_channel_preview);

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

    private void setupView(){
        mView1 = (SurfaceView)findViewById(R.id.multiply_view_1);
        mView2 = (SurfaceView)findViewById(R.id.multiply_view_2);

        mSelectStream1 = (Spinner)findViewById(R.id.select_stream_type_1);
        mSelectChannel1 = (Spinner)findViewById(R.id.select_channel_1);

        mSelectStream2 = (Spinner)findViewById(R.id.select_stream_type_2);
        mSelectChannel2 = (Spinner)findViewById(R.id.select_channel_2);

        initializeSpinner(mSelectChannel1,(ArrayList)mLiveDoubleChannelModule.getChannelList()).setSelection(0);
        initializeSpinner(mSelectStream1,(ArrayList)mLiveDoubleChannelModule.getStreamTypeList(mSelectChannel1.getSelectedItemPosition())).setSelection(0);

        initializeSpinner(mSelectChannel2,(ArrayList)mLiveDoubleChannelModule.getChannelList()).setSelection(0);
        initializeSpinner(mSelectStream2,(ArrayList)mLiveDoubleChannelModule.getStreamTypeList(mSelectChannel2.getSelectedItemPosition())).setSelection(1);

        ((Button)findViewById(R.id.buttonStartReplay1)).setOnClickListener(this);
        btnStartReplay2 = (Button)findViewById(R.id.buttonStartReplay2);
        btnStartReplay2.setOnClickListener(this);
    }

    private Spinner initializeSpinner(final Spinner spinner, ArrayList array){
        spinner.setSelection(0,true);
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,array));
        return spinner;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mLiveDoubleChannelModule.release();
        mView1 = null;
        mView2 = null;
        mLiveDoubleChannelModule = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonStartReplay1:
                if (!bFirstPlaying) {
                    if (mLiveDoubleChannelModule.multiPlay_channel1(mSelectChannel1.getSelectedItemPosition(), mSelectStream1.getSelectedItemPosition(), mView1)) {
                        bFirstPlaying = true;
                        mSelectChannel1.setEnabled(false);
                        mSelectStream1.setEnabled(false);
                        ((Button) v).setText(R.string.stop_replay);
                    }
                } else {
                    mLiveDoubleChannelModule.stopMultiPlay(true);
                    bFirstPlaying = false;
                    mSelectChannel1.setEnabled(true);
                    mSelectStream1.setEnabled(true);
                    ((Button) v).setText(R.string.start_replay);
                }
                break;
            case R.id.buttonStartReplay2:
                if (!bSecondPlaying) {
                    if (mLiveDoubleChannelModule.multiPlay_channel2(mSelectChannel2.getSelectedItemPosition(), mSelectStream2.getSelectedItemPosition(), mView2)) {
                        bSecondPlaying = true;
                        mSelectChannel2.setEnabled(false);
                        mSelectStream2.setEnabled(false);
                        ((Button) v).setText(R.string.stop_replay);
                    }
                } else {
                    mLiveDoubleChannelModule.stopMultiPlay(false);
                    bSecondPlaying = false;
                    mSelectChannel2.setEnabled(true);
                    mSelectStream2.setEnabled(true);
                    ((Button) v).setText(R.string.start_replay);
                }
        }
    }
}
