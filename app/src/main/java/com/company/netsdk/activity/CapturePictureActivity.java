package com.company.netsdk.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ImageView;

import com.company.NetSDK.CB_fSnapRev;
import com.company.netsdk.R;
import com.company.netsdk.common.ToolKits;
import com.company.netsdk.module.CapturePictureModule;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CapturePictureActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mRemoteCaptureBtn;
    private Button mTimerCaptureBtn;
    private Spinner mSelectChannel;
    private ImageView mPictureView;
    private CB_fSnapRev mfCapturePictureRev;
    private CapturePictureModule mCapturePictureModule;
    private CapturePictureType currentSelectType = CapturePictureType.CAPTURE_UNKNOWN;
    private Handler mHandler;
    private boolean bDestroyed = false; // Not use lock, so not 100% stop Handler.

    private enum CapturePictureType {
        CAPTURE_UNKNOWN,
        CAPTURE_REMOTE,
        CAPTURE_TIMER_START,
        CAPTURE_TIMER_STOP,
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_picture);
        setTitle(R.string.activity_function_list_capture_picture);

        mCapturePictureModule = new CapturePictureModule(this);

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

    private void setupView() {

        mPictureView = (ImageView)findViewById(R.id.picture_view);
        mRemoteCaptureBtn = (Button)findViewById(R.id.buttonRemoteCapture);
        mRemoteCaptureBtn.setOnClickListener(this);
        mTimerCaptureBtn = (Button)findViewById(R.id.buttonTimerCapture);
        mTimerCaptureBtn.setOnClickListener(this);
        mSelectChannel = (Spinner)findViewById(R.id.select_channel);
        mSelectChannel.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,mCapturePictureModule.getChannelList()));
        mSelectChannel.setSelection(0,true);
        mfCapturePictureRev = new fCapturePictureRev();
        CapturePictureModule.setSnapRevCallBack(mfCapturePictureRev);

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if (bDestroyed) {
                    return;
                }
                switch (CapturePictureType.values()[msg.what]){
                    case CAPTURE_REMOTE: {
                        byte[] data = (byte[]) msg.obj;
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        if (bitmap != null) {
                            Bitmap newBmp = zoomBitmap(bitmap, mPictureView.getWidth(), mPictureView.getHeight());
                            mPictureView.setImageBitmap(newBmp);
                            savePicture(data, data.length, msg.arg1);
                        }
                        break;
                    }
                    case CAPTURE_TIMER_START: {
                        byte[] data = (byte[]) msg.obj;
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        if (bitmap != null) {
                            Bitmap newBmp = zoomBitmap(bitmap, mPictureView.getWidth(), mPictureView.getHeight());
                            mPictureView.setImageBitmap(newBmp);
                        }
                        break;
                    }
                    default:
                        break;
                }
            }
        };
    }

    @Override
    public void onClick(View v) {  // 不做异步
        switch (v.getId()) {
            case R.id.buttonRemoteCapture:
                currentSelectType = CapturePictureType.CAPTURE_REMOTE;
                mCapturePictureModule.remoteCapturePicture(mSelectChannel.getSelectedItemPosition());
                break;
            case R.id.buttonTimerCapture:
                if (currentSelectType != CapturePictureType.CAPTURE_TIMER_START) {
                    CapturePictureType oldSelectType = currentSelectType;
                    currentSelectType = CapturePictureType.CAPTURE_TIMER_START;
                    if (mCapturePictureModule.timerCapturePicture(mSelectChannel.getSelectedItemPosition())) {
                        mSelectChannel.setEnabled(false);
                        mRemoteCaptureBtn.setEnabled(false);
                        mTimerCaptureBtn.setText(getString(R.string.capture_picture_timer_stop));
                    }else {
                        currentSelectType = oldSelectType;
                    }
                }else {
                    if (mCapturePictureModule.stopCapturePicture(mSelectChannel.getSelectedItemPosition())){
                        currentSelectType = CapturePictureType.CAPTURE_TIMER_STOP;
                        mRemoteCaptureBtn.setEnabled(true);
                        mSelectChannel.setEnabled(true);
                        mTimerCaptureBtn.setText(getString(R.string.capture_picture_timer_start));
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    ///抓图回调
    public class fCapturePictureRev implements CB_fSnapRev {
        private fCapturePictureRev() {}
        @Override
        public void invoke(long lLoginID, byte pBuf[], int RevLen, int EncodeType, int CmdSerial) {

            if (bDestroyed) {
                return;
            }
            byte []msgBuf = new byte[RevLen];
            System.arraycopy(pBuf, 0, msgBuf, 0, RevLen);
            Message msg = mHandler.obtainMessage(currentSelectType.ordinal());
            msg.obj = msgBuf;
            msg.arg1 = EncodeType;
            mHandler.sendMessage(msg);
        }
    }

    private void savePicture(byte pBuf[], int RevLen, int EncodeType) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date());
        String strFileName = getExternalFilesDir(null).getAbsolutePath()+"/"+ time.replace(":","-").replace(" ", "_") + ".jpg";
        ToolKits.writeLog("FileName:"+strFileName);
        if (strFileName.equals(""))
            return;

        FileOutputStream fileStream = null;
        try {
            fileStream = new FileOutputStream(strFileName, true);

            fileStream.write(pBuf, 0, RevLen);
            fileStream.flush();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != fileStream) {
                    fileStream.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();

        float scaleWidth = ((float)w)/width;
        float scaleHeight = ((float)h)/height;
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);

        return newBmp;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if  (currentSelectType == CapturePictureType.CAPTURE_TIMER_START) {
            mCapturePictureModule.stopCapturePicture(mSelectChannel.getSelectedItemPosition());
        }
        bDestroyed = true;
    }
}