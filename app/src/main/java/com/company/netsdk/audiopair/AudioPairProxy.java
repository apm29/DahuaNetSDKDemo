package com.company.netsdk.audiopair;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

public class AudioPairProxy {
	
	
	// 消息类型定义
	public final static int AUDIOPAIR_RECEIVE_SUCCESS_MSG = 12345;
	public final static int AUDIOPAIR_PLAYAUDIO_ERROR_MSG = 12346;
	
	private Boolean mIsRun = true;
	private AudioPair mAudioPair = null; 
	
	public AudioPairProxy()
	{
		mAudioPair = new AudioPair();
	}
	
	// 播放配对声音数据
	public void playAudioData(final String ssid, final String password, final String security, final String deviceCode,final Context mContext )
	{
		mIsRun = true;
		
		new Thread(new Runnable() {

			@Override
			public void run() {
//				// TODO Auto-generated method stub
//				Log.d("jane", "1");
//				if(handle == null){
//					Log.d("jane", "2");
//					return ;
//				}
				if (!mAudioPair.init(mContext) )
				{
					Log.d("jane", "3");
//					handle.sendEmptyMessage(AUDIOPAIR_PLAYAUDIO_ERROR_MSG);
					mAudioPair.uninit();
					return;
				}
				//int playTime=0;
				while(mIsRun)
				{
					Log.d("jane", "4");
				//	Log.v("playAudioData", "play+ "+(++playTime)+" Times!");
					mAudioPair.playAudioData(ssid, password, security, deviceCode);
					
					// 线程休息一下
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//Log.v("playAudioData", "Total playTime= "+playTime+" Times!");
				mAudioPair.uninit();
			}
		}).start();
	}
	
	// 停止播放声音数据
	public void stopAudioData()
	{
		Log.d("audioPair", "stopAudioData");
		mIsRun = false;
	}

	// 接收到设备发回的数据信号
	public void hasReceiveSign(final Handler handle)
	{
		/*
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(mIsRun)
				{
					if (mAudioPair.hasReceiveSign())
					{
						handle.sendEmptyMessage(AUDIOPAIR_RECEIVE_SUCCESS_MSG);
						return;
					}
				}
			}
		}).start();
		*/
	}
}
