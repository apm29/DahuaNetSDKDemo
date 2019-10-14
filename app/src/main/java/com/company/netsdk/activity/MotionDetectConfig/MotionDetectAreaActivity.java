package com.company.netsdk.activity.MotionDetectConfig;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.company.NetSDK.CFG_MOTION_INFO;
import com.company.NetSDK.FinalVar;
import com.company.netsdk.R;
import com.company.netsdk.activity.MotionDetectConfig.view.CustomHScrollView;
import com.company.netsdk.activity.MotionDetectConfig.view.CustomScrollView;
import com.company.netsdk.activity.MotionDetectConfig.view.MotionAreaView;

/**
 * Created by 32940 on 2018/8/27.
 */
public class MotionDetectAreaActivity extends AppCompatActivity implements View.OnClickListener{
    private final static int	ROW18	 = 18;			               // P制行数
    private final static int	ROW15	 = 15;			               // N制行数
    private final static int	LINE	 = 22;			               // 列数

    private int					     mRow;						   // 行数
    private long[]				         mRetRegion;				   // 用于返回的区域数组
    private long[]				         mRegion;					   // 在View上显示的区域数组
    private int					     mVideoStandard;			   // 视频制式 0:PAL 1:NTSC
    private int					     mMotionWidth;				   // 区域控件宽
    private int					     mMotionHeight;			   // 区域控件高

    private LinearLayout                mParentLayout;			   // 区域控件的父窗口
    private MotionAreaView              mAreaView;				   // 区域控件
    private ImageView                   mAreaEditImageView;        // 画图按钮
    private ImageView                   mAreaDeleteImageView;      // 擦除按钮
    private CustomScrollView            mVScrollView;				   // 垂直ScrollView
    private CustomHScrollView           mHScrollView;				   // 水平ScrollView

    private RelativeLayout				 mSenseLevelLayout;		  // 灵敏度布局选择
    private TextView                    mSenseLevelTextView;		  // 灵敏度
    private EditText                    mSenseLevelEditText;

    private RelativeLayout				 mThresholdLayout;		  // 阀值布局选择
    private TextView                    mThresholdTextView;		  // 阀值
    private EditText                    mThresholdEditText;

    private CFG_MOTION_INFO             motion_info;               // 动检配置信息

    private Resources                   res;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motion_detect_area);

        res = this.getResources();

        Toolbar toolbar = (Toolbar) findViewById(R.id.motion_detect_area_toolbar);
        toolbar.setTitle(res.getString(R.string.motion_detect_area));  // 在setSupportActionBar之前调用，否则无效；或者在onResume里调用
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initData();
        initUIView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.save_btn) {   // 将设置的信息返回
            mRetRegion = new long[mRegion.length];

            // 获取画板上的信息, 用于返回
            for(int i = 0; i < mRegion.length; i++) {
                if(i < mRow) {
                    mRetRegion[i] = mAreaView.getAreas()[i];
                }
            }

            // 将获取出来的画板的信息传入 动检结构体
            if (motion_info.abDetectRegion) {
                motion_info.stuRegion[0].nMotionRow = mRetRegion.length;
                motion_info.stuRegion[0].nMotionCol = 22;

                for (int i = 0; i < motion_info.stuRegion[0].nMotionRow; i++) {
                    for (int j = 0; j <motion_info.stuRegion[0].nMotionCol; j++) {
                        if ((mRetRegion[i] & (0x01 << j)) != 0) {
                            motion_info.stuRegion[0].byRegion[i][j] = 1;
                        } else {
                            motion_info.stuRegion[0].byRegion[i][j] = 0;
                        }
                    }
                }
            } else {
                motion_info.nMotionRow = mRetRegion.length;
                motion_info.nMotionCol = 22;

                for (int i = 0; i < motion_info.nMotionRow; i++) {
                    for (int j = 0; j < motion_info.nMotionCol; j++) {
                        if ((mRetRegion[i] & (0x01 << j)) != 0) {
                            motion_info.byRegion[i][j] = 1;
                        } else {
                            motion_info.byRegion[i][j] = 0;
                        }
                    }
                }
            }

            Intent intent = new Intent();

            intent.putExtra("motion_area", motion_info);
            setResult(RESULT_OK, intent);
            MotionDetectAreaActivity.this.finish();

            return true;
        }else if(id == android.R.id.home){ // 返回
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 获取配置信息
        motion_info = (CFG_MOTION_INFO)getIntent().getSerializableExtra("motion_area");

        mRegion = new long[FinalVar.MAX_MOTION_ROW];

        if (motion_info.abDetectRegion) {    // 三代动态检测区域
            for (int i = 0; i < FinalVar.MAX_MOTION_ROW; i++) {
                for (int j = 0; j < FinalVar.MAX_MOTION_COL; j++) {
                    if (motion_info.stuRegion[0].byRegion[i][j] != 0) {
                        mRegion[i] = mRegion[i] | (0x01 << j);
                    }
                }
            }
        } else {   // 二代动态检测区域
            for (int i = 0; i < FinalVar.MAX_MOTION_ROW; i++) {
                for (int j = 0; j < FinalVar.MAX_MOTION_COL; j++) {
                    if (motion_info.byRegion[i][j] != 0) {
                        mRegion[i] = mRegion[i] | (0x01 << j);
                    }
                }
            }
        }

        mVideoStandard = 0;
        Display display = getWindowManager().getDefaultDisplay();
        int screenWidth = display.getWidth();
        if (mVideoStandard == 0) {  // P制
            mMotionWidth = screenWidth;
            mMotionHeight = ROW18 * screenWidth / LINE;
            mRow = ROW18;
        } else {  // N制
            mMotionWidth = screenWidth;
            mMotionHeight = ROW15 * screenWidth / LINE;
            mRow = ROW15;
        }
    }

    /**
     * 初始化控件
     */
    private void initUIView() {
        mSenseLevelLayout = (RelativeLayout)findViewById(R.id.motion_sense_level_layout);
        mSenseLevelTextView = (TextView)findViewById(R.id.motion_sense_level_text);
        mSenseLevelLayout.setOnClickListener(this);

        // 灵敏度
        if (motion_info.abDetectRegion) {
            mSenseLevelTextView.setText(String.valueOf(motion_info.stuRegion[0].nSenseLevel));
        } else {
            mSenseLevelTextView.setText(String.valueOf(motion_info.nSenseLevel));
        }

        mThresholdLayout = (RelativeLayout)findViewById(R.id.motion_threshold_layout);
        mThresholdTextView = (TextView)findViewById(R.id.motion_threshold_text);
        mThresholdLayout.setOnClickListener(this);

        // 阀值
        if (motion_info.abDetectRegion) {
            mThresholdTextView.setText(String.valueOf(motion_info.stuRegion[0].nThreshold));
        }

        mVScrollView = (CustomScrollView) findViewById(R.id.motion_vScroll);
        mVScrollView.setLayoutParams(new RelativeLayout.LayoutParams(mMotionWidth, mMotionHeight));

        mHScrollView = (CustomHScrollView) findViewById(R.id.motion_hScroll);
        mHScrollView.setLayoutParams(new FrameLayout.LayoutParams(mMotionWidth, mMotionHeight));

        mParentLayout = (LinearLayout) findViewById(R.id.motion_area_parent);
        mParentLayout.setLayoutParams(new FrameLayout.LayoutParams(mMotionWidth, mMotionHeight));

        mAreaView = (MotionAreaView) findViewById(R.id.motion_areaView);
        mAreaView.setLayoutParams(new LinearLayout.LayoutParams(mMotionWidth, mMotionHeight));
        mAreaView.setScrollView(mVScrollView, mHScrollView);
        mAreaView.setRowAndCol(mRow, LINE);
        mAreaView.setAreas(mRegion);

        mAreaEditImageView = (ImageView)findViewById(R.id.motion_detect_area_edit);
        mAreaDeleteImageView = (ImageView)findViewById(R.id.motion_detect_area_delete);
        mAreaEditImageView.setSelected(true);
        mAreaDeleteImageView.setSelected(false);
        mAreaEditImageView.setOnClickListener(this);
        mAreaDeleteImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.motion_sense_level_layout:  // 灵敏度
            {
                mSenseLevelEditText = new EditText(this);
                mSenseLevelEditText.setText(mSenseLevelTextView.getText().toString());

                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle(res.getString(R.string.sense_level) + "(0~100)")
                        .setView(mSenseLevelEditText)
                        .setPositiveButton(res.getString(R.string.confirm), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 界面显示
                                mSenseLevelTextView.setText(mSenseLevelEditText.getText().toString());

                                // 灵敏度
                                if (motion_info.abDetectRegion) {
                                    motion_info.stuRegion[0].nSenseLevel = Integer.parseInt(mSenseLevelEditText.getText().toString());
                                } else {
                                    motion_info.nSenseLevel = Integer.parseInt(mSenseLevelEditText.getText().toString());
                                }
                            }
                        })
                        .setNegativeButton(res.getString(R.string.cancel), null).show();
                break;
            }
            case R.id.motion_threshold_layout:   // 阀值
            {
                mThresholdEditText = new EditText(this);
                mThresholdEditText.setText(mThresholdTextView.getText().toString());

                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle(res.getString(R.string.threshold) + "(0~100)")
                        .setView(mThresholdEditText)
                        .setPositiveButton(res.getString(R.string.confirm), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 界面显示
                                mThresholdTextView.setText(mThresholdEditText.getText().toString());

                                // 阀值
                                if (motion_info.abDetectRegion) {
                                    motion_info.stuRegion[0].nThreshold = Integer.parseInt(mThresholdEditText.getText().toString());
                                }
                            }
                        })
                        .setNegativeButton(res.getString(R.string.cancel), null).show();
                break;
            }
            case R.id.motion_detect_area_edit:  // 画图
            {
                if(!mAreaEditImageView.isSelected()) {
                    mAreaEditImageView.setSelected(true);
                    mAreaDeleteImageView.setSelected(false);
                    mAreaView.setMode(MotionAreaView.EDIT);
                    mVScrollView.setScrollEnable(false);
                    mHScrollView.setScrollEnable(false);
                }
                break;
            }
            case R.id.motion_detect_area_delete: // 擦除
            {
                if(!mAreaDeleteImageView.isSelected()) {
                    mAreaEditImageView.setSelected(false);
                    mAreaDeleteImageView.setSelected(true);
                    mAreaView.setMode(MotionAreaView.DELETE);
                    mVScrollView.setScrollEnable(false);
                    mHScrollView.setScrollEnable(false);
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
