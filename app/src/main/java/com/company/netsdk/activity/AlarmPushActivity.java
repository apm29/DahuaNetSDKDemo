package com.company.netsdk.activity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.company.netsdk.R;
import com.company.netsdk.common.DialogProgress;
import com.company.netsdk.common.ToolKits;
import com.company.netsdk.module.AlarmPushModule;

public class AlarmPushActivity extends AppCompatActivity {
    private static final String TAG = "AlarmPushActivity";
    private boolean isPush = false;
    private SharedPreferences mSharedPrefs;
    private AlarmPushModule mAlarmPushModule;
    private Button btnSubAlarm;
    private Button btnUnSubAlarm;
    private DialogProgress mDialogProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_push);
        setTitle(R.string.activity_function_list_alarm_push);
        mAlarmPushModule = new AlarmPushModule(this);

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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void setupView() {
        mDialogProgress = new DialogProgress(this);

        btnSubAlarm = (Button)findViewById(R.id.buttonSubAlarm);
        btnUnSubAlarm = (Button)findViewById(R.id.buttonUnSubAlarm);

        btnSubAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPush = true;
                new AlarmPushTask().execute();
            }
        });

        btnUnSubAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPush = false;
                new AlarmPushTask().execute();
            }
        });
    }

    private class AlarmPushTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mDialogProgress.setMessage(getString(R.string.waiting));
            mDialogProgress.setSpinnerType(DialogProgress.FADED_ROUND_SPINNER);
            mDialogProgress.setCancelable(false);
            mDialogProgress.show();
        }
        @Override
        protected Boolean doInBackground(String... params) {
            if (isPush) {
                return mAlarmPushModule.subscribe();
            }else {
                return mAlarmPushModule.unsubscribe();
            }
        }

        @Override
        protected void onPostExecute(Boolean result){
            mDialogProgress.dismiss();
            ToolKits.showMessage(AlarmPushActivity.this, getString(mAlarmPushModule.getResId()));
        }
    }
}
