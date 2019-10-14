package com.company.netsdk.activity;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.company.netsdk.R;
import com.company.netsdk.common.DialogProgress;
import com.company.netsdk.common.ToolKits;
import com.company.netsdk.module.TalkModule;

import java.util.ArrayList;

public class TalkActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private DialogProgress mDialogProgress;
    private TalkModule mTalkModule;
    public Spinner mSelectTransferMode;
    public Spinner mSelectTransferChn;
    Button mStartBtn;
    boolean mTalkFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        setTitle(R.string.activity_function_list_talk);

        mDialogProgress = new DialogProgress(this);
        mTalkModule = new TalkModule(this);

        // 添加返回键
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setupView();

        ToolKits.verifyRecordPermissions(TalkActivity.this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void setupView(){
        mSelectTransferChn = (Spinner)findViewById(R.id.spinner_transfer_channel);
        mSelectTransferMode = (Spinner)findViewById(R.id.spinner_transfer_mode);
        mSelectTransferMode.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mTalkModule.getTransferModeList()));
        mSelectTransferMode.setSelection(0);
        mSelectTransferMode.setOnItemSelectedListener(this);

        mStartBtn = (Button)findViewById(R.id.buttonStartTalk);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TalkTask talkTask = new TalkTask();
                talkTask.execute();
            }
        });
    }

    /// TalkTask
    private class TalkTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialogProgress.setMessage(getString(R.string.waiting));
            mDialogProgress.setSpinnerType(DialogProgress.FADED_ROUND_SPINNER);
            mDialogProgress.setCancelable(false);
            mDialogProgress.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            if (!mTalkFlag) {
                return mTalkModule.startTalk();
            } else {
                return mTalkModule.stopTalk();
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            mDialogProgress.dismiss();
            if (result) {
                if (!mTalkFlag) {
                    mSelectTransferMode.setEnabled(false);
                    mSelectTransferChn.setEnabled(false);
                    mTalkFlag = true;
                    mStartBtn.setText(R.string.stop_talk);
                } else {
                    if (mTalkModule.isTransfer()) {
                        mSelectTransferChn.setEnabled(true);
                    }
                    mSelectTransferMode.setEnabled(true);
                    mTalkFlag = false;
                    mStartBtn.setText(R.string.start_talk);
                }
            }
            ToolKits.showMessage(TalkActivity.this, mTalkModule.getErrMsg());
        }
    }

    @Override
    protected void onDestroy() {
        mTalkModule.stopTalk();
        mTalkModule.mTalkHandle = 0;
        mTalkModule = null;
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        // while onResume we should logout the device.
        super.onResume();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.spinner_transfer_mode) {
            if (position == 1) {
                mSelectTransferChn.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mTalkModule.getChannelList()));
                mSelectTransferChn.setSelection(0,true);
                mSelectTransferChn.setEnabled(true);
            }else {
                mSelectTransferChn.setAdapter((new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, new ArrayList<String>())));
                mSelectTransferChn.setEnabled(false);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
