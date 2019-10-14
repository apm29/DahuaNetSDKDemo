package com.company.netsdk.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.company.PlaySDK.IPlaySDK;
import com.company.PlaySDK.IPlaySDKCallBack;
import com.company.netsdk.R;
import com.company.netsdk.common.ToolKits;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by 36141 on 2017/5/22.
 */
public class FileBrowserActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = FileBrowserActivity.class.getSimpleName();
    private final int FLAG_RECORD_FILE_END = 2;
    ArrayList<String> mFilesPath = new ArrayList<String>();
    ListView mFileList;
    int port =IPlaySDK.PLAYGetFreePort() ;
    boolean bPlay = false;

    ///enumeration file tpes
    ///文件类型的枚举
    enum TYPE{
        PICTURE,
        VIDEO,
        UNKNOWN;
    }

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_file_browser);
        setTitle(R.string.function_list_files_browser);

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
        mFileList = (ListView)findViewById(R.id.file_list);
        mFileList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.function_list_row,
                R.id.line1,
                getFiles()
        ));
        mFileList.setOnItemClickListener(this);
    }

    private ArrayList<String> getFiles(){
        File[]  files1 = (new File(this.getExternalFilesDir(null).getAbsolutePath()+"/")).listFiles();
        ArrayList<String>  list = new ArrayList<String>();
        if(files1 != null) {
            for (int i = 0; i < files1.length; i++) {
                ToolKits.writeLog(files1[i].getAbsoluteFile().getAbsolutePath());
                if(!files1[i].isDirectory()) {
                    list.add(files1[i].getName());
                    mFilesPath.add(files1[i].getAbsoluteFile().getAbsolutePath());
                }
            }
        }
        Log.d(TAG,"list.size == "+list.size());
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tv = (TextView)view.findViewById(R.id.line1);
        String filename = tv.getText().toString();
        showFile(position,getFileType(filename));
    }


    ///determine the file tpe
    ///判断文件类型
    private TYPE getFileType(String name){
        if (name == null || name.equals(""))
            return TYPE.UNKNOWN;
        String suffix = name.substring(name.indexOf(".")+1,name.length());
        if (suffix.equals("dav")){
            return TYPE.VIDEO;
        }else if (suffix.equals("jpg") || suffix.equals("mpeg4")){
            return TYPE.PICTURE;
        }
        return TYPE.UNKNOWN;
    }

    private void  showFile(int position,TYPE fileType){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (fileType == TYPE.PICTURE) {
            Bitmap bmp = BitmapFactory.decodeFile(mFilesPath.get(position));
            ImageView iv = new ImageView(this);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            iv.setLayoutParams(layoutParams);
            iv.setImageBitmap(bmp);
            iv.setScaleType(ImageView.ScaleType.CENTER);
            iv.setAdjustViewBounds(true);
            builder.setView(iv);
            Dialog dialog = builder.create();
            Window window = dialog.getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.dimAmount = 0f;
            window.setAttributes(params);
            dialog.show();
        }else if (fileType == TYPE.VIDEO){
            final View view = LayoutInflater.from(this).inflate(R.layout.browser_video_dialog,null);
            final SurfaceView sv = (SurfaceView)view.findViewById(R.id.browser_video);
            builder.setView(view);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    stopPlayback();
                }
            });
            Dialog dialog = builder.create();
            Window window = dialog.getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.dimAmount = 0f;
            window.setAttributes(params);
            dialog.show();
            sv.getHolder().addCallback(new SurfaceHolder.Callback() {
                public void surfaceCreated(SurfaceHolder holder){
                    if(bPlay) {
                        IPlaySDK.PLAYSetDisplayRegion(port, 0, null, holder.getSurface(), 1);
                    }
                }

                public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                           int height)	{
                }

                public void surfaceDestroyed(SurfaceHolder holder){
                    if(bPlay) {
                        IPlaySDK.PLAYSetDisplayRegion(port, 0, null, holder.getSurface(), 0);
                    }
                }
            });
            if (!bPlay) {
                bPlay = true;
                playBackEx(sv, mFilesPath.get(position));
            }
        }

    }

    private void playBackEx(SurfaceView sv,final String file){
        long userData = 0;
        if(IPlaySDK.PLAYSetFileEndCallBack(port,new IPlaySDKCallBack.fpFileEndCBFun(){
            @Override
            public void invoke(int i, long l) {
                Message msg = mHandler.obtainMessage(2);
                msg.what = FLAG_RECORD_FILE_END;
                mHandler.sendMessage(msg);
            }
        },userData) == 0){
            Log.i(TAG,"PLAYSetFileEndCallBack failed"+IPlaySDK.PLAYGetLastError(port));
            return;
        }
        if (IPlaySDK.PLAYOpenFile(port,file) == 0) {
            Log.i(TAG,"PLAYOpenFile failed"+IPlaySDK.PLAYGetLastError(port));
            return;
        }
        if (IPlaySDK.PLAYSetDecodeThreadNum(port,4) == 0) {
            Log.i(TAG,"PLAYSetDecodeThreadNum failed"+IPlaySDK.PLAYGetLastError(port));
            return;
        }
        if (IPlaySDK.PLAYPlay(port,sv.getHolder().getSurface()) == 0) {
            Log.i(TAG,"PLAYPlay failed"+IPlaySDK.PLAYGetLastError(port));
            return;
        }
        if (IPlaySDK.PLAYPlaySound(port) == 0) {
            Log.i(TAG,"PLAYPlaySound failed"+IPlaySDK.PLAYGetLastError(port));
            return;
        }
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case FLAG_RECORD_FILE_END:
                    stopPlayback();
                    break;
                default:
                    break;
            }
        }
    };

    private void stopPlayback(){
        IPlaySDK.PLAYRigisterDrawFun(port,0,null,0);
        IPlaySDK.PLAYStopSound();
        IPlaySDK.PLAYCleanScreen(port,0,0,0,1,0);
        IPlaySDK.PLAYStop(port);
        IPlaySDK.PLAYCloseFile(port);
        bPlay = false;
    }


}
