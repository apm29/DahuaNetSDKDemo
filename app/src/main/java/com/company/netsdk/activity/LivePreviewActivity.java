package com.company.netsdk.activity;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.company.NetSDK.PTZ_OPT_ATTR;
import com.company.netsdk.R;
import com.company.netsdk.common.DialogProgress;
import com.company.netsdk.common.PTZControl;
import com.company.netsdk.common.ToolKits;
import com.company.netsdk.module.CapturePictureModule;
import com.company.netsdk.module.EncodeModule;
import com.company.netsdk.module.LivePreviewModule;
import com.company.netsdk.module.OSDModule;

import java.util.ArrayList;

import static com.company.netsdk.module.OSDModule.*;

public class LivePreviewActivity extends AppCompatActivity implements
        SurfaceHolder.Callback,
        View.OnClickListener{
    private final String TAG = LivePreviewActivity.class.getSimpleName();
    private Spinner mSelectStream;
    private Spinner mSelectChannel;
    private TextView mEncodeMode;
    private TextView mEncodeResolution;
    private TextView mEncodeFrameRate;
    private TextView mEncodeBitRate;
    private Button mEncodeSetBtn;
    private SurfaceView mRealView;
    private EditText mEditText;
    private Button btnStartReplay;
    private View mPtzControlLayoutView;
    private View mEncodeLayoutView;
    private LivePreviewModule mLiveModule;
    private PTZControl ptzControl;
    private NetSDKApplication app;
    private AlertDialog.Builder builder;
    private Button btnChannelTitle;
    private Button btnTimeTitle;

    private OSDModule osdModule;
    private EncodeModule encodeModule;

    private ArrayList<String> mEncodeModeMsg = null;
    private ArrayList<String> mEncodeResolutionMsg = null;
    private ArrayList<String> mEncodeFrameRateMsg = null;
    private ArrayList<String> mEncodeBitRateMsg = null;
    private Integer[] mSecelt = null;
    private boolean isRecord = false;
    private int count = 0;
    ///touch time.
    ///触摸时间.
    long mTouchStartTime = 0;
    long mTouchMoveTime = 0;

    ///single touch.
    ///单点触摸.
    float mSingleTouchStart_x = 0;
    float mSingleTouchStart_y = 0;
    float mSingleTouchEnd_x = 0;
    float mSingleTouchEnd_y = 0;

    ///double touch.
    ///两点触摸.
    float mDoubleTouchStart_x1 = 0;
    float mDoubleTouchStart_y1 = 0;
    float mDoubleTouchStart_x2 = 0;
    float mDoubleTouchStart_y2 = 0;
    float mDoubleTouchEnd_x1 = 0;
    float mDoubleTouchEnd_y1 = 0;
    float mDoubleTouchEnd_x2 = 0;
    float mDoubleTouchEnd_y2 = 0;
    Button  mEncodeBtn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_preview);
        mLiveModule = new LivePreviewModule(this);
        osdModule = new OSDModule(this);
        encodeModule = new EncodeModule(this);
        ptzControl = new PTZControl();
        app = (NetSDKApplication)getApplication();
        setTitle(R.string.activity_function_list_live_preview);

        builder = new AlertDialog.Builder(this);
        String msg = getString(R.string.ptz_control_fragment_info) + "\n" + getString(R.string.only_implement_osd_function_of_main_stream);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

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
        mSelectStream = (Spinner)findViewById(R.id.select_stream_type);
        mSelectChannel = (Spinner)findViewById(R.id.select_channel);
        mRealView = (SurfaceView)findViewById(R.id.real_view);
        mRealView.getHolder().addCallback(this);
        initializeSpinner(mSelectChannel,(ArrayList)mLiveModule.getChannelList()).setSelection(0);
        initializeSpinner(mSelectStream,(ArrayList)mLiveModule.getStreamTypeList(mSelectChannel.getSelectedItemPosition())).setSelection(0);

        ((Button)findViewById(R.id.preview_ptz_control)).setOnClickListener(this);
        mPtzControlLayoutView = (View)findViewById(R.id.ptz_control);
        mEditText = (EditText)mPtzControlLayoutView.findViewById(R.id.edittext_preset);

        ///Only limit to use number
        ///只允许输入数字
        KeyListener keyListener = new DigitsKeyListener(false, false);
        mEditText.setKeyListener(keyListener);

        ///Limit preset range : 0~999
        ///限制预置点的范围 : 0~999
        ToolKits.limitEditTextNumberRange(mEditText, 0, 999);

        ((Button) mPtzControlLayoutView.findViewById(R.id.preview_focus_add)).setOnClickListener(this);
        ((Button) mPtzControlLayoutView.findViewById(R.id.preview_focus_dec)).setOnClickListener(this);
        ((Button) mPtzControlLayoutView.findViewById(R.id.preview_aperture_add)).setOnClickListener(this);
        ((Button) mPtzControlLayoutView.findViewById(R.id.preview_aperture_dec)).setOnClickListener(this);
        ((Button) mPtzControlLayoutView.findViewById(R.id.preview_setpreset)).setOnClickListener(this);
        ((Button) mPtzControlLayoutView.findViewById(R.id.preview_clearpreset)).setOnClickListener(this);
        ((Button) mPtzControlLayoutView.findViewById(R.id.preview_gotopreset)).setOnClickListener(this);

        btnStartReplay = (Button)findViewById(R.id.buttonStartReplay);
        btnStartReplay.setOnClickListener(this);
        ((Button)findViewById(R.id.buttonLocalCapture)).setOnClickListener(this);
        mEncodeBtn = ((Button)findViewById(R.id.preview_encode));
        mEncodeBtn.setOnClickListener(this);
        ((Button)findViewById(R.id.buttonStartRecord)).setOnClickListener(this);

        btnChannelTitle = (Button)findViewById(R.id.preview_osd_channel);
        btnChannelTitle.setOnClickListener(this);

        btnTimeTitle = (Button)findViewById(R.id.preview_osd_time);
        btnTimeTitle.setOnClickListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mLiveModule.initSurfaceView(mRealView);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onDestroy(){
        osdModule.destroy();
        osdModule = null;
        mLiveModule.stopRealPlay();
        mLiveModule = null;
        encodeModule = null;
        mRealView = null;
        super.onDestroy();
    }

    private void onChannelChanged(int pos){
        if (mLiveModule == null)
            return;
        mLiveModule.stopRealPlay();
        mLiveModule.startPlay(pos,mSelectStream.getSelectedItemPosition(),mRealView);
    }

    private void onStreamTypeChanged(int position){
        if (mLiveModule == null)
            return;
        mLiveModule.stopRealPlay();
        mLiveModule.startPlay(mSelectChannel.getSelectedItemPosition(),position,mRealView);
    }

    @Override
    public void onClick(View v) {
        String text = mEditText.getText().toString();
        switch (v.getId()){
            case R.id.preview_ptz_control:    // 云台控制
                if(mPtzControlLayoutView.isShown()) {
                    mPtzControlLayoutView.setVisibility(View.GONE);
                } else  {
                    mPtzControlLayoutView.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.buttonStartReplay:    // 开始实时预览
                if (mLiveModule.getHandle() == 0) {
                    mLiveModule.startPlay(mSelectChannel.getSelectedItemPosition(),mSelectStream.getSelectedItemPosition(),mRealView);
                    if (!mLiveModule.isRealPlaying()) {
                        ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.live_preview_failed));
                    }else {
                        mSelectChannel.setEnabled(false);
                        mSelectStream.setEnabled(false);
                        ((Button)v).setText(R.string.stop_replay);
                    }
                } else {
                    mLiveModule.stopRealPlay();
                    mSelectChannel.setEnabled(true);
                    mSelectStream.setEnabled(true);
                    ((Button)v).setText(R.string.start_replay);
                }
                break;
            case R.id.buttonLocalCapture:    // 远程抓图
                String filename = mLiveModule.createInnerAppFile("jpg");
                ToolKits.writeLog("FileName:"+filename);
                if (mLiveModule.getHandle() != 0) {
                    if(CapturePictureModule.localCapturePicture(mLiveModule.getPlayPort(), filename)) {
                        ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.info_success));
                    } else {
                        ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.info_failed));
                    }
                }else {
                    ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.live_preview_not_open));
                }

                break;
            case R.id.preview_encode:      // 编码配置
                mEncodeBtn.setEnabled(false);
                onEncode();
                break;
            case R.id.buttonStartRecord:    // 开始录像
                isRecord = !isRecord;
               onRecord(v, isRecord);
                break;
            case R.id.preview_setpreset:    // 设置预置点
                if(text != null && !text.equals("")) {
                    if(ptzControl.ptzControlPresetAdd(app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte)Integer.parseInt(text))) {
                        ToolKits.showMessage(LivePreviewActivity.this , getString(R.string.info_success));
                    } else {
                        ToolKits.showMessage(LivePreviewActivity.this , getString(R.string.info_failed));
                    }
                }else {
                    ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.input_number));
                }
                break;
            case R.id.preview_clearpreset:  // 清除预置点
                if(text != null && !text.equals("")) {
                    if(ptzControl.ptzControlPresetDec(app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte)Integer.parseInt(text))) {
                        ToolKits.showMessage(LivePreviewActivity.this , getString(R.string.info_success));
                    } else {
                        ToolKits.showMessage(LivePreviewActivity.this , getString(R.string.info_failed));
                    }
                }else {
                    ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.input_number));
                }
                break;
            case R.id.preview_gotopreset:     // 跳转预置点
                if(text != null && !text.equals("")) {
                    if(ptzControl.ptzControlPresetGoto(app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte)Integer.parseInt(text))) {
                        ToolKits.showMessage(LivePreviewActivity.this , getString(R.string.info_success));
                    } else {
                        ToolKits.showMessage(LivePreviewActivity.this , getString(R.string.info_failed));
                    }
                } else {
                    ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.input_number));
                }
                break;
            case R.id.preview_focus_add:   // 变焦+
                ptzControl.ptzControlFocusAddStart(app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte) 8);

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ptzControl.ptzControlFocusAddStop(app.getLoginHandle(), mSelectChannel.getSelectedItemPosition());

                break;
            case R.id.preview_focus_dec:  // 变焦-
                ptzControl.ptzControlFocusDecStart(app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte) 8);

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ptzControl.ptzControlFocusDecStop(app.getLoginHandle(), mSelectChannel.getSelectedItemPosition());

                break;
            case R.id.preview_aperture_add: // 光圈+
                ptzControl.ptzControlApertureAddStart(app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte) 8);

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ptzControl.ptzControlApertureAddStop(app.getLoginHandle(), mSelectChannel.getSelectedItemPosition());

                break;
            case R.id.preview_aperture_dec:  // 光圈-
                ptzControl.ptzControlApertureDecStart(app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte) 8);

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ptzControl.ptzControlApertureDecStop(app.getLoginHandle(), mSelectChannel.getSelectedItemPosition());

                break;
            case R.id.preview_osd_channel: // osd 通道标题
            {
                // 本Demo只做主码流的叠加
                final int channel1 = mSelectChannel.getSelectedItemPosition();

                osdModule.getChannelInfo(channel1);

                osdModule.addGetChannelInfoListener(new GetChannelInfoListener() {
                    @Override
                    public void onChannelInfoListener(boolean bRet1, boolean bRet2, boolean bEncodeBlend, String channelName) {
                        if (!bRet1 && !bRet2) {
                            ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.get_channel_title_and_name_failed));
                            return;
                        }

                        if (!bRet1 && bRet2) {
                            ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.get_channel_title_failed));
                        }

                        if (bRet1 && !bRet2) {
                            ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.get_channel_name_failed));
                        }

                        if (bRet1 && bRet2) {
                            ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.get_channel_title_and_name_succeed));
                        }

                        View channelView = getLayoutInflater().inflate(R.layout.osd_channel, null);

                        // 通道标题使能
                        RelativeLayout channelTitleEnableLayout = (RelativeLayout) channelView.findViewById(R.id.channel_title_enable_layout);
                        final ImageView channelTitleEnableImageView = (ImageView) channelView.findViewById(R.id.channel_title_enable_btn);

                        // 通道标题名称
                        final EditText channelTitleNameEd = (EditText) channelView.findViewById(R.id.channel_title_name_text);

                        // 通道标题按钮
                        Button setChannelTitleBtn = (Button) channelView.findViewById(R.id.set_channel_title_btn);

                        if (bRet1) {
                            channelTitleEnableImageView.setSelected(bEncodeBlend);
                        }

                        if (bRet2) {
                            channelTitleNameEd.setText(channelName);
                        }

                        final AlertDialog channelDialog = new AlertDialog.Builder(LivePreviewActivity.this, R.style.DialogFullScreen)
                                .setView(channelView)
                                .show();

                        Window window = channelDialog.getWindow();
                        WindowManager.LayoutParams layoutParams = window.getAttributes();
                        layoutParams.width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
                        layoutParams.height =  getWindow().getWindowManager().getDefaultDisplay().getHeight() / 3;
                        layoutParams.gravity = Gravity.BOTTOM;
                        window.setAttributes(layoutParams);

                        channelTitleEnableLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (channelTitleEnableImageView.isSelected()) {
                                    channelTitleEnableImageView.setSelected(false);
                                } else {
                                    channelTitleEnableImageView.setSelected(true);
                                }
                            }
                        });

                        // 设置通道标题按钮事件
                        setChannelTitleBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(channelTitleNameEd.getText().toString().getBytes().length > 255) {
                                    ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.channel_name_maxlen_255));
                                    return;
                                }

                                osdModule.setChannelInfo(channel1, channelTitleEnableImageView.isSelected(), channelTitleNameEd.getText().toString());

                                osdModule.addSetChannelInfoListener(new SetChannelInfoListener() {
                                    @Override
                                    public void onChannelInfoListener(boolean bRet1, boolean bRet2) {
                                        if (!bRet1 && !bRet2) {
                                            ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.set_channel_title_and_name_failed));
                                            return;
                                        }

                                        if (!bRet1 && bRet2) {
                                            ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.set_channel_title_failed));
                                        }

                                        if (bRet1 && !bRet2) {
                                            ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.set_channel_name_failed));
                                        }

                                        if (bRet1 && bRet2) {
                                            channelDialog.dismiss();
                                            ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.set_channel_title_and_name_succeed));
                                        }
                                    }
                                });
                            }
                        });
                    }
                });

                break;
            }
            case R.id.preview_osd_time: // osd 时间标题
            {
                // 本Demo只做主码流的叠加
                final int channel = mSelectChannel.getSelectedItemPosition();

                osdModule.getTimeTitlelInfo(channel);
                osdModule.addGetTimeTitleInfoListener(new GetTimeTitleInfoListener() {
                    @Override
                    public void onTimeTitleInfoListener(boolean bRet, boolean bBlend, boolean bShowWeek) {
                        if (!bRet) {
                            ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.get_time_title_failed));
                            return;
                        }

                        if (bRet) {
                            ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.get_time_title_succeed));
                        }

                        View timeView = getLayoutInflater().inflate(R.layout.osd_time, null);

                        // 时间标题使能
                        RelativeLayout timeTitleEnableLayout = (RelativeLayout) timeView.findViewById(R.id.time_title_enable_layout);
                        final ImageView timeTitleEnableImageView = (ImageView) timeView.findViewById(R.id.time_title_enable_btn);

                        // 显示星期使能
                        RelativeLayout weekeEnableLayout = (RelativeLayout) timeView.findViewById(R.id.show_week_enable_layout);
                        final ImageView weekEnableImageView = (ImageView) timeView.findViewById(R.id.show_week_enable_btn);

                        // 时间标题按钮
                        Button setTimeTitleBtn = (Button) timeView.findViewById(R.id.set_time_title_btn);

                        timeTitleEnableImageView.setSelected(bBlend);
                        weekEnableImageView.setSelected(bShowWeek);

                        final AlertDialog timeDialog = new AlertDialog.Builder(LivePreviewActivity.this, R.style.DialogFullScreen)
                                .setView(timeView)
                                .show();

                        Window window = timeDialog.getWindow();
                        WindowManager.LayoutParams layoutParams = window.getAttributes();
                        layoutParams.width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
                        layoutParams.height =  getWindow().getWindowManager().getDefaultDisplay().getHeight() / 3;
                        layoutParams.gravity = Gravity.BOTTOM;
                        window.setAttributes(layoutParams);

                        timeTitleEnableLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (timeTitleEnableImageView.isSelected()) {
                                    timeTitleEnableImageView.setSelected(false);
                                } else {
                                    timeTitleEnableImageView.setSelected(true);
                                }
                            }
                        });

                        weekeEnableLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (weekEnableImageView.isSelected()) {
                                    weekEnableImageView.setSelected(false);
                                } else {
                                    weekEnableImageView.setSelected(true);
                                }
                            }
                        });

                        // 设置时间标题按钮事件
                        setTimeTitleBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                osdModule.setTimeTitleInfo(channel, timeTitleEnableImageView.isSelected(), weekEnableImageView.isSelected());
                                osdModule.addSetTimeTitleInfoListener(new SetTimeTitleInfoListener() {
                                    @Override
                                    public void onTimeTitleInfoListener(boolean bRet) {
                                        if (!bRet) {
                                            ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.set_time_title_failed));
                                        } else {
                                            timeDialog.dismiss();
                                            ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.set_time_title_succeed));
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
                break;
            }
            case R.id.encode_set_layout:          // 压缩格式
            {

                final String[] msg = new String[mEncodeModeMsg.size()];
                for(int i = 0; i < mEncodeModeMsg.size(); i++) {
                    msg[i] = mEncodeModeMsg.get(i);
                }

                android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this)
                        .setTitle(getString(R.string.compress_format))
                        .setSingleChoiceItems(msg, mSecelt[0], new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onUpdateMode(msg[which], isMainStream());
                                mEncodeMode.setText(msg[which]);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), null).show();

                break;
            }
            case R.id.resolution_set_layout:    // 分辨率
            {
                final String[] msg = new String[mEncodeResolutionMsg.size()];
                for(int i = 0; i < mEncodeResolutionMsg.size(); i++) {
                    msg[i] = mEncodeResolutionMsg.get(i);
                }

                android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this)
                        .setTitle(getString(R.string.resolve_ratio))
                        .setSingleChoiceItems(msg, mSecelt[1], new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onUpdateResolve(msg[which], isMainStream());
                                mEncodeResolution.setText(msg[which]);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), null).show();

                break;
            }
            case R.id.frame_rate_set_layout:    // 帧率
            {
                final String[] msg = new String[mEncodeFrameRateMsg.size()];
                for(int i = 0; i < mEncodeFrameRateMsg.size(); i++) {
                    msg[i] = mEncodeFrameRateMsg.get(i);
                }
                android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this)
                        .setTitle(getString(R.string.frame_rate))
                        .setSingleChoiceItems(msg, mSecelt[2], new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onUpdateFps(which, isMainStream());
                                mEncodeFrameRate.setText(msg[which]);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), null).show();

                break;
            }
            case R.id.bit_rate_set_layout:    // 码流
            {
                final String[] msg = new String[mEncodeBitRateMsg.size()];
                for(int i = 0; i < mEncodeBitRateMsg.size(); i++) {
                    msg[i] = mEncodeBitRateMsg.get(i);
                }

                android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this)
                        .setTitle(getString(R.string.bit_stream))
                        .setSingleChoiceItems(msg, mSecelt[3], new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onUpdateBitRate(msg[which], isMainStream());
                                mEncodeBitRate.setText(msg[which]);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), null).show();

                break;
            }
            default:
                break;
        }
    }

    /*
     * 云台控制
     */
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        final int mAction = event.getAction();
//        int mPointorCount = event.getPointerCount();
//        boolean bPtzMove = false;
//        switch(mAction) {
//            case MotionEvent.ACTION_DOWN :
//                if(mPtzControlLayoutView.isShown()) {
//                    mPtzControlLayoutView.setVisibility(View.GONE);
//                }
//
//                ///If the input method has already been shown on the window, it is hidden.
//                ///如果输入法在窗口上已显示，则隐藏
//                InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//                inputManager.hideSoftInputFromWindow(mRealView.getWindowToken(), 0);
//
//                mTouchStartTime = System.currentTimeMillis();
//                mSingleTouchStart_x = event.getX();
//                mSingleTouchStart_y = event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE :
//                mTouchMoveTime = System.currentTimeMillis() - mTouchStartTime;
//                int mHistorySize = event.getHistorySize();
//                if(mHistorySize == 0) {
//                    return true;
//                }
//
//                if((mPointorCount == 1) && (mTouchMoveTime > 300)){
//                    mSingleTouchEnd_x = event.getX();
//                    mSingleTouchEnd_y = event.getY();
//
//                    float mSingleTouchValue_x = mSingleTouchEnd_x - mSingleTouchStart_x;
//                    float mSingleTouchValue_y = mSingleTouchEnd_y - mSingleTouchStart_y;
//
//                    float mDeviation = Math.abs(mSingleTouchValue_y/mSingleTouchValue_x);
//
//                    if((mSingleTouchValue_x > 0) && (mDeviation < 0.87)) {
//                        bPtzMove = ptzControl.ptzControlRight(event, app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte) 0, (byte) 8);
//                    } else if((mSingleTouchValue_x < 0) && (mDeviation < 0.87)) {
//                        bPtzMove = ptzControl.ptzControlLeft(event, app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte) 0, (byte) 8);
//                    } else if((mSingleTouchValue_y > 0) && (mDeviation > 11.43)) {
//                        bPtzMove = ptzControl.ptzControlDown(event, app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte) 0, (byte) 8);
//                    } else if((mSingleTouchValue_y < 0) && (mDeviation > 11.43)) {
//                        bPtzMove = ptzControl.ptzControlUp(event, app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte) 0, (byte) 8);
//                    } else if((mSingleTouchValue_x < 0) && (mSingleTouchValue_y < 0) && (mDeviation <= 11.43) && (mDeviation >= 0.87)) {
//                        bPtzMove = ptzControl.ptzControlLeftUp(event, app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte) 8, (byte) 8);
//                    } else if((mSingleTouchValue_x < 0) && (mSingleTouchValue_y > 0) && (mDeviation <= 11.43) && (mDeviation >= 0.87)) {
//                        bPtzMove = ptzControl.ptzControlLeftDown(event, app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte) 8, (byte) 8);
//                    } else if((mSingleTouchValue_x > 0) && (mSingleTouchValue_y < 0) && (mDeviation <= 11.43) && (mDeviation >= 0.87)) {
//                        bPtzMove = ptzControl.ptzControlRightUp(event, app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte) 8, (byte) 8);
//                    } else if((mSingleTouchValue_x > 0) && (mSingleTouchValue_y > 0) && (mDeviation <= 11.43) && (mDeviation >= 0.87)) {
//                        bPtzMove = ptzControl.ptzControlRightDown(event, app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte) 8, (byte) 8);
//                    }
//
//                } else if((mPointorCount == 2) && (mTouchMoveTime > 300)){
//                    mDoubleTouchStart_x1 = event.getHistoricalX(0, mHistorySize - 1);
//                    mDoubleTouchStart_y1 = event.getHistoricalY(0, mHistorySize - 1);
//                    mDoubleTouchStart_x2 = event.getHistoricalX(1, mHistorySize - 1);
//                    mDoubleTouchStart_y2 = event.getHistoricalY(1, mHistorySize - 1);
//
//                    mDoubleTouchEnd_x1 = event.getX(0);
//                    mDoubleTouchEnd_y1 = event.getY(0);
//                    mDoubleTouchEnd_x2 = event.getX(1);
//                    mDoubleTouchEnd_y2 = event.getY(1);
//
//                    float mStartDistance_x = mDoubleTouchStart_x2 - mDoubleTouchStart_x1;
//                    float mStartDistance_y = mDoubleTouchStart_y2 - mDoubleTouchStart_y1;
//                    float mEndDistance_x = mDoubleTouchEnd_x2 - mDoubleTouchEnd_x1;
//                    float mEndDistance_y = mDoubleTouchEnd_y2 - mDoubleTouchEnd_y1;
//
//                    float mStartTouchDistance = (float)Math.sqrt(mStartDistance_x * mStartDistance_x + mStartDistance_y * mStartDistance_y);
//                    float mEndTouchDistance = (float)Math.sqrt(mEndDistance_x * mEndDistance_x + mEndDistance_y * mEndDistance_y);
//
//                    if(mEndTouchDistance > mStartTouchDistance) {
//                        bPtzMove = ptzControl.ptzControlZoomAdd(event, app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte) 8);
//                    } else if(mEndTouchDistance < mStartTouchDistance) {
//                        bPtzMove = ptzControl.ptzControlZoomDec(event, app.getLoginHandle(), mSelectChannel.getSelectedItemPosition(), (byte) 8);
//                    } else {
//                        return false;
//                    }
//                }
//                break;
//            case MotionEvent.ACTION_UP :
//                break;
//            default :
//                break;
//        }
//        return bPtzMove;
//    }

    private void onEncode(){
        final Dialog dialog = new Dialog(this, R.style.DialogFullScreen);

        // 编码配置
        mEncodeLayoutView = getLayoutInflater().inflate(R.layout.encode_config_dialog, null, true);

        mEncodeSetBtn = (Button)mEncodeLayoutView.findViewById(R.id.encode_set_btn);
        ((RelativeLayout)mEncodeLayoutView.findViewById(R.id.encode_set_layout)).setOnClickListener(this);
        ((RelativeLayout)mEncodeLayoutView.findViewById(R.id.resolution_set_layout)).setOnClickListener(this);
        ((RelativeLayout)mEncodeLayoutView.findViewById(R.id.frame_rate_set_layout)).setOnClickListener(this);
        ((RelativeLayout)mEncodeLayoutView.findViewById(R.id.bit_rate_set_layout)).setOnClickListener(this);

        mEncodeMode = (TextView)mEncodeLayoutView.findViewById(R.id.encode_set_text);
        mEncodeResolution  = (TextView)mEncodeLayoutView.findViewById(R.id.resolution_set_text);
        mEncodeFrameRate  = (TextView)mEncodeLayoutView.findViewById(R.id.frame_rate_set_text);
        mEncodeBitRate  = (TextView)mEncodeLayoutView.findViewById(R.id.bit_rate_set_text);
        dialog.setCancelable(true);
        dialog.setContentView(mEncodeLayoutView);

        encodeModule.setSpinnerDataCallBack(new EncodeModule.SpinnerDataCallback() {
            @Override
            public void onSetSpinner(Bundle data, DialogProgress dialogprogress) {
                if (dialogprogress != null && dialogprogress.isShowing())
                    dialogprogress.dismiss();

                if (data == null) {
                    ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.get_encode_failed));
                    return;
                }

                dialog.show();

                Window window = dialog.getWindow();
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.width = getWindowManager().getDefaultDisplay().getWidth();
                layoutParams.height =  WindowManager.LayoutParams.WRAP_CONTENT;
                layoutParams.gravity = Gravity.BOTTOM;
                window.setAttributes(layoutParams);

                mEncodeModeMsg = data.getStringArrayList(encodeModule.MODE);
                mEncodeResolutionMsg = data.getStringArrayList(encodeModule.RESOLUTION);
                mEncodeFrameRateMsg = data.getStringArrayList(encodeModule.FPS);
                mEncodeBitRateMsg = data.getStringArrayList(encodeModule.BITRATE);

                mSecelt = new Integer[5];
                mSecelt[0] = data.getInt(encodeModule.MODE_POS);
                mSecelt[1] = data.getInt(encodeModule.RESOLUTION_POS);
                mSecelt[2] = data.getInt(encodeModule.FPS_POS);
                mSecelt[3] = data.getInt(encodeModule.BITRATE_POS);
                if ( mSecelt[3] == -1)
                {
                    mSecelt[4] = data.getInt(encodeModule.BITRATE_POS_CUSTOMIZED);
                }

                mEncodeMode.setText(mEncodeModeMsg.get(data.getInt(encodeModule.MODE_POS)));
                mEncodeResolution.setText(mEncodeResolutionMsg.get(data.getInt(encodeModule.RESOLUTION_POS)));
                mEncodeFrameRate.setText(mEncodeFrameRateMsg.get(data.getInt(encodeModule.FPS_POS)));
                if (mSecelt[3] == -1)
                {
                    mEncodeBitRate.setText(Integer.toString(mSecelt[4]));
                }
                else
                {
                    mEncodeBitRate.setText(mEncodeBitRateMsg.get(data.getInt(encodeModule.BITRATE_POS)));
                }
            }
        });

        encodeModule.SetSpinnerDataUpdateCallback(new EncodeModule.SpinnerDataUpdateCallback() {
            @Override
            public void onUpdateSetSpinner(Bundle data, DialogProgress dialogprogress) {
                if (dialogprogress != null && dialogprogress.isShowing())
                    dialogprogress.dismiss();

                if (data == null) {
                    ToolKits.showMessage(LivePreviewActivity.this, getString(R.string.get_encode_failed));
                    return;
                }

                mEncodeModeMsg = data.getStringArrayList(encodeModule.MODE);
                mEncodeResolutionMsg = data.getStringArrayList(encodeModule.RESOLUTION);
                mEncodeFrameRateMsg = data.getStringArrayList(encodeModule.FPS);
                mEncodeBitRateMsg = data.getStringArrayList(encodeModule.BITRATE);

                mSecelt = new Integer[4];
                mSecelt[0] = data.getInt(encodeModule.MODE_POS);
                mSecelt[1] = data.getInt(encodeModule.RESOLUTION_POS);
                mSecelt[2] = data.getInt(encodeModule.FPS_POS);
                mSecelt[3] = data.getInt(encodeModule.BITRATE_POS);
                mEncodeMode.setText(mEncodeModeMsg.get(data.getInt(encodeModule.MODE_POS)));
                mEncodeResolution.setText(mEncodeResolutionMsg.get(data.getInt(encodeModule.RESOLUTION_POS)));
                mEncodeFrameRate.setText(mEncodeFrameRateMsg.get(data.getInt(encodeModule.FPS_POS)));
                mEncodeBitRate.setText(mEncodeBitRateMsg.get(data.getInt(encodeModule.BITRATE_POS)));
            }
        });

        // get encode of device
        encodeModule.getEncodeData(mSelectChannel.getSelectedItemPosition(), isMainStream());
        mEncodeSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if(encodeModule.setEncodeConfig(mSelectChannel.getSelectedItemPosition())){
                    ToolKits.showMessage(LivePreviewActivity.this,getString(R.string.encode_set_success));
                }else {
                    ToolKits.showMessage(LivePreviewActivity.this,getString(R.string.encode_set_failed));
                }
            }
        });
        mEncodeBtn.setEnabled(true);
    }

    /**
     * 更新压缩格式
     * @param text           压缩格式值
     * @param isMainStream  是否主码流
     */
    private void onUpdateMode(String text,boolean isMainStream){
        encodeModule.updateMode(mSelectChannel.getSelectedItemPosition(),text,isMainStream);
    }

    /**
     * 更新分辨率
     * @param text              分辨率值
     * @param isMainStream     是否主码流
     */
    private void onUpdateResolve(String text,boolean isMainStream){
        encodeModule.updateResolve(mSelectChannel.getSelectedItemPosition(),text,isMainStream);
    }

    /**
     * 更新帧率
     * @param pos            帧率信息数组下标
     * @param isMainStream  是否主码流
     */
    private void onUpdateFps(int pos,boolean isMainStream){
        encodeModule.updateFps(mSelectChannel.getSelectedItemPosition(),pos,isMainStream);
    }

    /**
     * 更新码流
     * @param value          码流值
     * @param isMainStream  是否主码流
     */
    private void onUpdateBitRate(String value,boolean isMainStream){
        encodeModule.updateBitRate(value,isMainStream);
    }

    private Spinner initializeSpinner(final Spinner spinner, ArrayList array){
        spinner.setSelection(0,true);
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,array));
        return spinner;
    }

    private void onRecord(View v, boolean recordFlag){
        if( mLiveModule.record(recordFlag)){
            if(recordFlag){
                btnStartReplay.setEnabled(false);
                ((Button)v).setText(R.string.stop_record);
            }else {
                btnStartReplay.setEnabled(true);
                ((Button)v).setText(R.string.start_record);
            }
        }
    }

    private boolean isMainStream(){
       return mSelectStream.getSelectedItemPosition() == 0 ? true : false;
    }

}
