package com.company.netsdk.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.company.netsdk.R;
import com.company.netsdk.activity.MotionDetectConfig.MotionDetectConfigActivity;
import com.company.netsdk.common.TestInterfaceActivity;
import com.company.netsdk.common.ToolKits;

import java.util.ArrayList;
import java.util.List;

public class FunctionListActivity extends AppCompatActivity {

    private AdapterView.OnItemClickListener mListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    startActivity(LivePreviewActivity.class);
                    break;
                case 1:
                    startActivity(LivePreviewDoubleChannelActivity.class);
                    break;
                case 2:
                    startActivity(PlaybackActivity.class);
                    break;
                case 3:
                    startActivity(TalkActivity.class);
                    break;
                case 4:
                    startActivity(CapturePictureActivity.class);
                    break;
                case 5:
                    startActivity(AlarmListenActivity.class);
                    break;
                case 6:
                    startActivity(DeviceControlActivity.class);
                    break;
                case 7:
                    startActivity(VideoTalkActivity.class);
                    break;
                case 8:
                    startActivity(FileBrowserActivity.class);
                    break;
               /*
              case 9:
                    startActivity(MotionDetectConfigActivity.class);
                    break;
              case 10:
                    startActivity(AlarmControlActivity.class);
                    break;
               case 11:
                    startActivity(AlarmPushActivity.class);
                    break;
                case 12:
                    startActivity(TestInterfaceActivity.class);
                    break;*/
                default:
                    ToolKits.writeLog("onListItemClick: " + position);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_list);
        setTitle(R.string.title_function_list);

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
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setupView() {
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.function_list_row,
                R.id.line1,
                getListItems(getResources())
        );

        listView.setOnItemClickListener(mListener);
        listView.setAdapter(adapter);
    }

    private List<String> getListItems(Resources res) {
        List<String> data = new ArrayList<String>();
        data.add(res.getString(R.string.activity_function_list_live_preview));
        data.add(res.getString(R.string.activity_function_list_double_channel));
        data.add(res.getString(R.string.activity_function_list_play_back));
        data.add(res.getString(R.string.activity_function_list_talk));
        data.add(res.getString(R.string.activity_function_list_capture_picture));
        data.add(res.getString(R.string.activity_function_list_alarm_listen));
        data.add(res.getString(R.string.activity_function_list_device_control));
        data.add(res.getString(R.string.activity_function_list_video_talk));
        data.add(res.getString(R.string.function_list_files_browser));

        /*
        data.add(res.getString(R.string.activity_function_list_motion_detect));
        data.add(res.getString(R.string.activity_function_list_alarm_control));
        data.add(res.getString(R.string.activity_function_list_alarm_push));
        data.add(res.getString(R.string.activity_function_lise_interface_test));
        */
        return data;
    }

    private void startActivity(final Class cls){
        startActivity(new Intent(this,cls));
    }

}
