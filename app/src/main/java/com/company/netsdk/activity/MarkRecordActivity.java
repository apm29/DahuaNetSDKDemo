package com.company.netsdk.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.company.NetSDK.NET_OUT_MEDIA_QUERY_FILE;
import com.company.NetSDK.NET_RECORDFILE_INFO;
import com.company.NetSDK.NET_TIME_EX;
import com.company.netsdk.R;
import com.company.netsdk.common.DialogProgress;
import com.company.netsdk.module.LivePreviewModule;
import com.company.netsdk.module.MarkRecordModule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MarkRecordActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner mChnSpinner;

    ///日期时间控件
    DatePicker mDatePicker;
    TimePicker mTimePicker;
    ///日期时间控件弹窗
    View mLayoutView;
    AlertDialog timeDialog;

    ///日期时间显示文本控件
    TextView mTextViewStartDate;
    TextView mTextViewEndDate;
    TextView mTextViewStartTime;
    TextView mTextViewEndTime;

    NET_TIME_EX selectTime = new NET_TIME_EX();
    NET_TIME_EX markStartTime = new NET_TIME_EX();
    NET_TIME_EX markEndTime = new NET_TIME_EX();

    DialogProgress mDialogProgress;
    NetSDKApplication app;

    TextView mMarkFileCountText;
    ArrayList<String> mFileList = new ArrayList<String>();
    boolean []mSelectBoolArray = new boolean[MarkRecordModule.MAX_QUERY_NUM];
    RecyclerView recyclerView;
    MarkFileAdapter adapter;

    final int MARK_START_DATE_TIME = 0;
    final int MARK_END_DATE_TIME = 1;
    int markId;

    enum MARK_RECORD_TYPE {
        MARK_RECORD_TYPE_UNKNOWN,
        MARK_RECORD_TYPE_LOCK,
        MARK_RECORD_TYPE_QUERY,
        MARK_RECORD_TYPE_UNLOCK,
    }

    MARK_RECORD_TYPE emRecordType = MARK_RECORD_TYPE.MARK_RECORD_TYPE_UNKNOWN;
    NET_OUT_MEDIA_QUERY_FILE[] queryResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (NetSDKApplication) getApplication();
        mDialogProgress = new DialogProgress(this);
        setTitle(R.string.activity_function_list_mark_record);
        setContentView(R.layout.activity_mark_record);

        // 添加返回键
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setupView();
    }

    private void setupView() {
        mChnSpinner = (Spinner) findViewById(R.id.channel_spinner);
        List channelList = new LivePreviewModule(this).getChannelList();
        /*if (channelList.size() > 1) {
            channelList.add(0,getString(R.string.all_channel));
        }*/
        initializeSpinner(mChnSpinner, channelList);

        mTextViewStartDate = (TextView) findViewById(R.id.mark_record_start_date);
        mTextViewStartTime = (TextView) findViewById(R.id.mark_record_start_time);
        mTextViewStartDate.setOnClickListener(this);
        mTextViewStartTime.setOnClickListener(this);

        mTextViewEndDate = (TextView) findViewById(R.id.mark_record_end_date);
        mTextViewEndTime = (TextView) findViewById(R.id.mark_record_end_time);
        mTextViewEndDate.setOnClickListener(this);
        mTextViewEndTime.setOnClickListener(this);

        mLayoutView = getLayoutInflater().inflate(R.layout.date_time_setting, null);
        mDatePicker = (DatePicker) mLayoutView.findViewById(R.id.date_set_picker);
        mTimePicker = (TimePicker) mLayoutView.findViewById(R.id.time_set_picker);
        mTimePicker.setIs24HourView(true);

        ((Button) findViewById(R.id.lock_record_button)).setOnClickListener(this);
        ((Button) findViewById(R.id.query_record_button)).setOnClickListener(this);
        ((Button) findViewById(R.id.unlock_record_button)).setOnClickListener(this);

        mMarkFileCountText = (TextView) findViewById(R.id.mark_file_count);
        recyclerView = (RecyclerView) findViewById(R.id.mark_file_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MarkFileAdapter();
        recyclerView.setAdapter(adapter);
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
//        recyclerView.addItemDecoration(itemDecoration);

        initDate();
        createTimeDialog();
    }

    public void initDate() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date date = new java.util.Date();
        calendar.setTime(date);

        markStartTime.dwYear = calendar.get(Calendar.YEAR);
        markStartTime.dwMonth = calendar.get(Calendar.MONTH) + 1;
        markStartTime.dwDay = calendar.get(Calendar.DAY_OF_MONTH);
        markStartTime.dwHour = calendar.get(Calendar.HOUR_OF_DAY);
        markStartTime.dwMinute = calendar.get(Calendar.MINUTE);
        ;
        markStartTime.dwSecond = 0;

        copyTime(markEndTime, markStartTime);
        mTextViewStartDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
        mTextViewStartTime.setText(new SimpleDateFormat("HH:mm").format(date));

        mTextViewEndDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
        mTextViewEndTime.setText(new SimpleDateFormat("HH:mm").format(date));

        clearQueryResult();
    }

    private void clearQueryResult() {
        mMarkFileCountText.setText("");
        mFileList.clear();
        Arrays.fill(mSelectBoolArray, false);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mark_record_start_date:
            case R.id.mark_record_start_time:
                selectMarkTime(MARK_START_DATE_TIME);
                break;
            case R.id.mark_record_end_date:
            case R.id.mark_record_end_time:
                selectMarkTime(MARK_END_DATE_TIME);
                break;
            case R.id.lock_record_button:
                emRecordType = MARK_RECORD_TYPE.MARK_RECORD_TYPE_LOCK;
                new MarkRecordTask().execute();
                break;
            case R.id.query_record_button:
                emRecordType = MARK_RECORD_TYPE.MARK_RECORD_TYPE_QUERY;
                new MarkRecordTask().execute();
                break;
            case R.id.unlock_record_button:
                if (mFileList.size() == 0) {
                    Toast.makeText(MarkRecordActivity.this, getString(R.string.please_query_first), Toast.LENGTH_SHORT).show();
                }else if (!haveSelectedItem()){
                    Toast.makeText(MarkRecordActivity.this, getString(R.string.please_select_first), Toast.LENGTH_SHORT).show();
                }else {
                    emRecordType = MARK_RECORD_TYPE.MARK_RECORD_TYPE_UNLOCK;
                    new MarkRecordTask().execute();
                }

                break;
            default:
                break;
        }
    }

    private boolean haveSelectedItem() {
        boolean bFlag = false;
        for (boolean bSelected : mSelectBoolArray) {
            if (bSelected) {
                bFlag = true;
                break;
            }
        }
        return bFlag;
    }

    private boolean lockRecord() {
        return MarkRecordModule.markFileByTime(app.getLoginHandle(), mChnSpinner.getSelectedItemPosition(), markStartTime, markEndTime);
    }

    private boolean queryRecord() {
        queryResult = MarkRecordModule.findMarkFile(app.getLoginHandle(), mChnSpinner.getSelectedItemPosition(), markStartTime, markEndTime);
        if (queryResult == null) {
            return false;
        }
        return true;
    }

    private boolean unlockRecord() {
        return MarkRecordModule.unlock(app.getLoginHandle(), queryResult, mSelectBoolArray);
    }

    private void showQueryResult() {
        clearQueryResult();
        int nRetCount = MarkRecordModule.getMarkFileCount();
        if (nRetCount == 0) {
            queryResult = null;
            Toast.makeText(MarkRecordActivity.this,getString(R.string.no_record_found),Toast.LENGTH_SHORT).show();
            return;
        }
        if (queryResult != null) {
            if (nRetCount > queryResult.length)
            {
                nRetCount = queryResult.length;
            }
            for (int i = 0; i < nRetCount; ++i) {
                mFileList.add(convMarkFileInfo(i+1, queryResult[i]));
            }
            boolean bSuccess = MarkRecordModule.ifFindTotalSuccess();
            if (!bSuccess) {
                Toast.makeText(MarkRecordActivity.this,getString(R.string.find_total_count_failed),Toast.LENGTH_SHORT).show();
            }
            mMarkFileCountText.setText(getString(R.string.total_count) + " : " + (bSuccess?MarkRecordModule.getMarkFileCount(): "----") + "  "  + getString(R.string.show_count) + " : " + mFileList.size());
        }
        adapter.notifyDataSetChanged();
    }

    private String convMarkFileInfo(int index, NET_OUT_MEDIA_QUERY_FILE outfile) {
        String markFileInfo = index + ": " + charArrayToString(outfile.szFilePath);
        return markFileInfo;
    }

    private String charArrayToString(char [] src) {
        byte[] dst = new byte[src.length];
        for (int i = 0; i < src.length; ++i) {
            dst[i] = (byte)src[i];
        }
        String str = "";
        try {
            str =  new String(dst, "GBK");
        }catch (Exception e) {
            str = new String(dst);
        }
        return str;
    }

    /// TalkTask
    private class MarkRecordTask extends AsyncTask<String, Integer, Boolean> {
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
            boolean bRet = false;
            switch (emRecordType) {
                case MARK_RECORD_TYPE_LOCK:
                    bRet = lockRecord();
                    break;
                case MARK_RECORD_TYPE_QUERY:
                    bRet = queryRecord();
                    break;
                case MARK_RECORD_TYPE_UNLOCK:
                    bRet = unlockRecord();
                    break;
                default:
                    break;
            }
            return bRet;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            mDialogProgress.dismiss();
            if (result) {
                switch (emRecordType) {
                    case MARK_RECORD_TYPE_LOCK:
                        Toast.makeText(MarkRecordActivity.this, getString(R.string.info_success), Toast.LENGTH_SHORT).show();
                        break;
                    case MARK_RECORD_TYPE_QUERY:
                        showQueryResult();
                        break;
                    case MARK_RECORD_TYPE_UNLOCK:
                        Toast.makeText(MarkRecordActivity.this, getString(R.string.info_success), Toast.LENGTH_SHORT).show();
                        emRecordType = MARK_RECORD_TYPE.MARK_RECORD_TYPE_QUERY;
                        new MarkRecordTask().execute();
                        break;
                    default:
                        break;
                }

            } else {
                Toast.makeText(MarkRecordActivity.this, getString(R.string.info_failed), Toast.LENGTH_SHORT).show();
                switch (emRecordType) {
                    case MARK_RECORD_TYPE_LOCK:
                        break;
                    case MARK_RECORD_TYPE_QUERY:
                        queryResult = null;
                        clearQueryResult();
                        break;
                    case MARK_RECORD_TYPE_UNLOCK:
                        emRecordType = MARK_RECORD_TYPE.MARK_RECORD_TYPE_QUERY;
                        new MarkRecordTask().execute();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void selectMarkTime(int mark) {
        markId = mark;
        if (markId == MARK_START_DATE_TIME) {
            copyTime(selectTime, markStartTime);
        } else {
            copyTime(selectTime, markEndTime);
        }

        ///初始化控件、监听
        initDate((int) selectTime.dwYear, (int) selectTime.dwMonth - 1, (int) selectTime.dwDay);
        initTime((int) selectTime.dwHour, (int) selectTime.dwMinute);
        ///弹窗时间控件
        timeDialog.show();
    }

    ///年月日初始化、监听
    private void initDate(int dwyear, int dwmonth, int dwday) {
        mDatePicker.init(dwyear, dwmonth, dwday, new DatePicker.OnDateChangedListener() {

            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectTime.dwYear = year;
                selectTime.dwMonth = monthOfYear + 1;
                selectTime.dwDay = dayOfMonth;
            }
        });
    }

    ///时分初始化监听
    private void initTime(int dwhour, int dwminute) {
        mTimePicker.setCurrentHour(dwhour);
        mTimePicker.setCurrentMinute(dwminute);

        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                selectTime.dwHour = hourOfDay;
                selectTime.dwMinute = minute;
            }
        });
    }

    private void createTimeDialog() {
        timeDialog = new AlertDialog.Builder(MarkRecordActivity.this)
                .setTitle(getString(R.string.select_time_continer))
                .setView(mLayoutView)
                .setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String monthStr = formatTime(selectTime.dwMonth);
                        String dayStr = formatTime(selectTime.dwDay);
                        String hourStr = formatTime(selectTime.dwHour);
                        String minuteStr = formatTime(selectTime.dwMinute);

                        if (markId == MARK_START_DATE_TIME) {
                            copyTime(markStartTime, selectTime);
                            mTextViewStartDate.setText(String.valueOf(selectTime.dwYear) + "-" + monthStr + "-" + dayStr);
                            mTextViewStartTime.setText(hourStr + ":" + minuteStr);
                        } else {
                            copyTime(markEndTime, selectTime);
                            mTextViewEndDate.setText(String.valueOf(selectTime.dwYear) + "-" + monthStr + "-" + dayStr);
                            mTextViewEndTime.setText(hourStr + ":" + minuteStr);
                        }
                        dialog.cancel();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), null).create();
    }

    private void copyTime(NET_TIME_EX dst, NET_TIME_EX src) {
        dst.dwYear = src.dwYear;
        dst.dwMonth = src.dwMonth;
        dst.dwDay = src.dwDay;
        dst.dwHour = src.dwHour;
        dst.dwMinute = src.dwMinute;
        dst.dwSecond = src.dwSecond;
    }

    private String formatTime(long t) {
        if (t < 10) {
            return "0" + String.valueOf(t);
        }
        return String.valueOf(t);
    }

    private Spinner initializeSpinner(final Spinner spinner, List array) {
        spinner.setSelection(0, true);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array));
        return spinner;
    }


    public class MarkFileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public MarkFileAdapter() {
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mark_record_list_row, viewGroup, false);
            return new ListItemViewHolder(itemView);
        }

        //绑定界面，设置监听
        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {
            //设置条目状态
            ((ListItemViewHolder) holder).info.setText(mFileList.get(i));
            ((ListItemViewHolder) holder).checkBox.setChecked(mSelectBoolArray[i]);

            //checkBox的监听
            ((ListItemViewHolder) holder).checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectBoolArray[i] = !mSelectBoolArray[i];
                }
            });

            //条目view的监听
            ((ListItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectBoolArray[i] = !mSelectBoolArray[i];
                    notifyItemChanged(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mFileList.size();
        }

        public class ListItemViewHolder extends RecyclerView.ViewHolder {
            //ViewHolder
            CheckBox checkBox;
            TextView info;

            ListItemViewHolder(View view) {
                super(view);
                this.info = (TextView) view.findViewById(R.id.mark_file_info);
                this.checkBox = (CheckBox) view.findViewById(R.id.select_checkbox);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
