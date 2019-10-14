package com.company.netsdk.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;

import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.company.NetSDK.*;
import com.company.netsdk.activity.NetSDKApplication;

@SuppressLint("UseValueOf")
public class TestInterfaceActivity extends ListActivity {
	public interface ListFunctionInterface {
		public void listFun();
	};

	private List<ListFunctionInterface> listFunctions = new ArrayList<ListFunctionInterface>();
	private List<String> testList = new ArrayList<String>();
	private long lanalyHandle = 0;
	public static final String DISCONNECTED_BROAST = "disconnected_callback";
	public static final String AUTOCONNECTED_BROAST = "autoconnected_callback";
	
	private long __LoginHandle = NetSDKApplication.getInstance().getLoginHandle();
	private int  _waittime = 3000;

	private void addFunctionToList(String listItemName, ListFunctionInterface interfaceFunction)
	{
		listFunctions.add(interfaceFunction);
		testList.add(listItemName);
	}

	/////////////////////////////////////////////在这里新增列表项/////////////////////////////////////
	private void generFunctionList()
	{
		addFunctionToList("CustomTitleConfig", new ListFunctionInterface(){
			@Override
			public void listFun(){
				CustomTitleConfig();
			}
		});
		addFunctionToList("getRemoteDevice", new ListFunctionInterface(){
			@Override
			public void listFun(){
				getRemoteDevice();
			}
		});
		addFunctionToList("RealLoadPicEx", new ListFunctionInterface(){
			@Override
			public void listFun(){
				RealLoadPicEx();
			}
		});
		addFunctionToList("MatrixGetCameras", new ListFunctionInterface() {
			@Override
			public void listFun() {
				MatrixGetCameras();
			}
		});
		addFunctionToList("AudioAndVideoEncrypt", new ListFunctionInterface() {
			@Override
			public void listFun() {
				AudioAndVideoEncrypt();
			}
		});
		addFunctionToList("CrossRegionRuleConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				CrossRegionRuleConfig();
			}
		});
		addFunctionToList("CrosslineRuleConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				CrosslineRuleConfig();
			}
		});
		addFunctionToList("LetrackRuleConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				LetrackRuleConfig();
			}
		});
		addFunctionToList("CoaxialLightConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				CoaxialLightConfig();
			}
		});
		addFunctionToList("PirAlarmParaConfigm", new ListFunctionInterface() {
			@Override
			public void listFun() {
				PirAlarmParaConfigm();
			}
		});
		addFunctionToList("LightGlobalConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				LightGlobalConfig();
			}
		});
		addFunctionToList("VideoInExposureInfoConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				VideoInExposureInfoConfig();
			}
		});
		addFunctionToList("NASConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				NASConfig();
			}
		});
		addFunctionToList("StoragePointConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				StoragePointConfig();
			}
		});
		addFunctionToList("QueryHDDState", new ListFunctionInterface() {
			@Override
			public void listFun() {
				QueryHDDState();
			}
		});
		addFunctionToList("QueryVideoAnalyseInfo", new ListFunctionInterface() {
			@Override
			public void listFun() {
				QueryVideoAnalyseInfo();
			}
		});
		addFunctionToList("VideoInColorInfoConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				VideoInColorInfoConfig();
			}
		});
		addFunctionToList("GetUserManageCaps", new ListFunctionInterface() {
			@Override
			public void listFun() {
				GetUserManageCaps();
			}
		});
		addFunctionToList("GetWireLessDevSignal", new ListFunctionInterface() {
			@Override
			public void listFun() {
				GetWireLessDevSignal();
			}
		});
		addFunctionToList("SetCoaxialControlIO", new ListFunctionInterface() {
			@Override
			public void listFun() {
				SetCoaxialControlIO();
			}
		});
		addFunctionToList("GetCoaxialControlIOCaps", new ListFunctionInterface() {
			@Override
			public void listFun() {
				GetCoaxialControlIOCaps();
			}
		});
		addFunctionToList("ChannelNameNewConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				ChannelNameNewConfig();
			}
		});
		addFunctionToList("ChannelNameOldConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				ChannelNameOldConfig();
			}
		});
		addFunctionToList("TimeTitleConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				TimeTitleConfig();
			}
		});
		addFunctionToList("ChannelTitleConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				ChannelTitleConfig();
			}
		});
		addFunctionToList("ControlAlarmOut", new ListFunctionInterface() {
			@Override
			public void listFun() {
				ControlAlarmOut();
			}
		});
		addFunctionToList("ExAlarmBoxInfoConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				ExAlarmBoxInfoConfig();
			}
		});
		addFunctionToList("QueryExAlarmBoxCaps", new ListFunctionInterface() {
			@Override
			public void listFun() {
				QueryExAlarmBoxCaps();
			}
		});
		addFunctionToList("QueryExAlarmChannels", new ListFunctionInterface() {
			@Override
			public void listFun() {
				QueryExAlarmChannels();
			}
		});
		addFunctionToList("GetAlarmOutState", new ListFunctionInterface() {
			@Override
			public void listFun() {
				GetAlarmOutState();
			}
		});
		addFunctionToList("AlarmOutConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				AlarmOutConfig();
			}
		});
		addFunctionToList("ExAlarmOutConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				ExAlarmOutConfig();
			}
		});
		addFunctionToList("GetChannelsState", new ListFunctionInterface() {
			@Override
			public void listFun() {
				GetChannelsState();
			}
		});
		addFunctionToList("GetComPortDeviceChannelInfo", new ListFunctionInterface() {
			@Override
			public void listFun() {
				GetComPortDeviceChannelInfo();
			}
		});
		addFunctionToList("FindGroupInfo", new ListFunctionInterface() {
			@Override
			public void listFun() {
				FindGroupInfo();
			}
		});
		addFunctionToList("RainBrushMode", new ListFunctionInterface() {
			@Override
			public void listFun() {
				RainBrushMode();
			}
		});
		addFunctionToList("RemoveRemoteFiles", new ListFunctionInterface() {
			@Override
			public void listFun() {
				RemoveRemoteFiles();
			}
		});
		addFunctionToList("DropBoxTokenConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				DropBoxTokenConfig();
			}
		});

		addFunctionToList("InitDevAccount", new ListFunctionInterface() {
			@Override
			public void listFun() {
				InitDevAccount();
			}
		});
		addFunctionToList("InitDevAccountByIP", new ListFunctionInterface() {
			@Override
			public void listFun() {
				InitDevAccountByIP();
			}
		});

		addFunctionToList("AppLanguageInfoConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				AppLanguageInfoConfig();
			}
		});

		addFunctionToList("SetGPSStatus", new ListFunctionInterface() {
			@Override
			public void listFun() {
				SetGPSStatus();
			}
		});

		addFunctionToList("ArmScheduleInfoConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				ArmScheduleInfoConfig();
			}
		});

		addFunctionToList("NetCfgExConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				NetCfgExConfig();
			}
		});

		addFunctionToList("QueryStorageInfo", new ListFunctionInterface() {
			@Override
			public void listFun() {
				QueryStorageInfo();
			}
		});

		addFunctionToList("LocalExAlarmConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				LocalExAlarmConfig();
			}
		});

		addFunctionToList("NetMonitorAbortConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				NetMonitorAbortConfig();
			}
		});

		addFunctionToList("ExAlarmInputConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				ExAlarmInputConfig();
			}
		});

		addFunctionToList("VideoImageInfoConfig", new ListFunctionInterface() {
			public void listFun() {
				VideoImageInfoConfig();
			}
		});
		addFunctionToList("BlueToothRecord", new ListFunctionInterface() {
			public void listFun() {
				BlueToothRecord();
			}
		});
		addFunctionToList("QrcodeRecord", new ListFunctionInterface() {
			public void listFun() {
				QrcodeRecord();
			}
		});
		addFunctionToList("AttachDeviceDiscovery", new ListFunctionInterface() {
			public void listFun() {
				AttachDeviceDiscovery();
			}
		});
		addFunctionToList("StartDeviceDiscovery", new ListFunctionInterface() {
			public void listFun() {
				StartDeviceDiscovery();
			}
		});

		addFunctionToList("StopDeviceDiscovery", new ListFunctionInterface() {
			public void listFun() {
				StopDeviceDiscovery();
			}
		});

		addFunctionToList("DetachDeviceDiscovery", new ListFunctionInterface() {
			public void listFun() {
				DetachDeviceDiscovery();
			}
		});
	
		addFunctionToList("FindRecordTrafficRedList", new ListFunctionInterface() {
			public void listFun() {
				FindRecordTrafficRedList();
			}
		});
		addFunctionToList("FindRecordTrafficRedListVague", new ListFunctionInterface() {
			public void listFun() {
				FindRecordTrafficRedListVague();
			}
		});
		
		addFunctionToList("GetPwdSpecification", new ListFunctionInterface() {
			public void listFun() {
				GetPwdSpecification();
			}
		});
	
		addFunctionToList("Upgrade", new ListFunctionInterface() {
			public void listFun() {
				Upgrade();
			}
		});
		
		addFunctionToList("VedioInOptionsConfig", new ListFunctionInterface() {
			public void listFun() {
				VedioInOptionsConfig();
			}
		});
		
		addFunctionToList("CarCoacnConfig", new ListFunctionInterface() {
			public void listFun() {
				CarCoacnConfig();
			}
		});
	
		addFunctionToList("GetAuthClassifyList", new ListFunctionInterface() {
			public void listFun() {
				GetAuthClassifyList();		
			}
		});
	
		addFunctionToList("ShootingRuleConfig", new ListFunctionInterface() {
			public void listFun() {
				ShootingRuleConfig();
			}
		});
		
		addFunctionToList("InitDevAccess", new ListFunctionInterface() {
			public void listFun() {
				InitDevAccess();
			}
		});

    	addFunctionToList("QueryUpgradeState", new ListFunctionInterface() {
		public void listFun() {
			QueryUpgradeState();
		}
		});
		
		addFunctionToList("VSPCaysConfig", new ListFunctionInterface() {
			public void listFun() {
				VSPCaysConfig();
			}
		});
		
		addFunctionToList("TestFindFileRecord", new ListFunctionInterface() {
			public void listFun() {
				TestFindFileRecord();
			}
		});
		
		addFunctionToList("TestFindTrafficCar", new ListFunctionInterface() {
			public void listFun() {
				TestFindTrafficCar();
			}
		});
		
		addFunctionToList("TestPtz", new ListFunctionInterface() {
			public void listFun() {
				TestPtz();				
			}			
		});
		

		addFunctionToList("ControlSmartSwitch", new ListFunctionInterface() {
			@Override
			public void listFun() {
				ControlSmartSwitch();
			}
		});
		
		addFunctionToList("ReleaseAlaram", new ListFunctionInterface() {
			@Override
			public void listFun() {
				ReleaseAlaram();
			}
		});
		
		addFunctionToList("MatchTwoFaceImage", new ListFunctionInterface() {
			public void listFun() {
				MatchTwoFaceImage();
			}
		});
		
		addFunctionToList("AttachAIOFileProc", new ListFunctionInterface() {
			public void listFun() {
				AttachAIOFileProc();
			}
		});
		addFunctionToList("TestNetPolicy", new ListFunctionInterface() {
			public void listFun() {
				TestNetPolicy();
			}
		});
		
		addFunctionToList("UploadRemoteFile", new ListFunctionInterface() {
			public void listFun() {
				UploadRemoteFile();
			}
		});
		addFunctionToList("DownloadRemoteFile", new ListFunctionInterface() {
			public void listFun() {
				DownloadRemoteFile();
			}
		});
		addFunctionToList("OperateMonitorWall", new ListFunctionInterface() {
			public void listFun() {
				OperateMonitorWall();
			}
		});
		
		addFunctionToList("MonitorWall", new ListFunctionInterface() {
			@Override
			public void listFun() {
				MonitorWall();
			}
		});
		
		addFunctionToList("QueryBackupDev", new ListFunctionInterface(){
			@Override
			public void listFun(){
				QueryBackupDev();
			}
		});		
		
		addFunctionToList("QueryComposite", new ListFunctionInterface(){
			@Override
			public void listFun(){
				QueryComposite();
			}
		});
		
		// 自由开窗
		addFunctionToList("SplitWindow ", new ListFunctionInterface() {
			@Override
			public void listFun() {
				SplitWindow ();
			}
		});		

		addFunctionToList("MobileSubscribe", new ListFunctionInterface(){
			@Override
			public void listFun(){
				MobileSubscribe();
			}
		});
		
		addFunctionToList("MobileSubscribeCfg", new ListFunctionInterface(){
			@Override
			public void listFun(){
				MobileSubscribeCfg();
			}
		});
		
		addFunctionToList("MobileSubscribeDelete", new ListFunctionInterface(){
			@Override
			public void listFun(){
				MobileSubscribeDelete();
			}
		});
		addFunctionToList("TestOperateSplit", new ListFunctionInterface() {
			public void listFun() {
				TestOperateSplit();
			}
		});
		
		addFunctionToList("NTPConfig", new ListFunctionInterface() {
			public void listFun() {
				NTPConfig();
			}
		});
		
		addFunctionToList("TestCourseRecord", new ListFunctionInterface() {
			public void listFun() {
				TestCourseRecord();
			}
		});
		
		addFunctionToList("ModifyLowRateWPAN", new ListFunctionInterface() {
			public void listFun() {
				ModifyLowRateWPAN();
			}
		});
		
		addFunctionToList("RomoveLowRateWPAN", new ListFunctionInterface() {
			public void listFun() {
				RomoveLowRateWPAN();
			}
		});
		
		addFunctionToList("OpenDoor", new ListFunctionInterface() {
			public void listFun() {
				OpenDoor();
			}
		});
		
		addFunctionToList("AddLowRateWLAN", new ListFunctionInterface() {
			public void listFun() {
				AddLowRateWLAN();
			}
		});
		
		addFunctionToList("QueryWirelessDevState", new ListFunctionInterface() {
			public void listFun() {
				QueryWirelessDevState();
			}
		});
		
		addFunctionToList("QueryCodeIDState", new ListFunctionInterface() {
			public void listFun() {
				QueryCodeIDState();
			}
		});
		
        addFunctionToList("TestMail", new ListFunctionInterface() {
            @Override
            public void listFun() {
				TestMail();
            }
        });
       
		addFunctionToList("CloudUpgrader", new ListFunctionInterface() {
			@Override
			public void listFun() {
				CloudUpgrader();
			}
		});
	
		addFunctionToList("DVRIPConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				DVRIPConfig();
			}
		});
		
		addFunctionToList("SearchAndConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				SearchAndConfig();
			}
		});
		
		addFunctionToList("EmailConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				EmailConfig();
			}
		});
		
		addFunctionToList("SmartEncodeConfig", new ListFunctionInterface(){
			@Override
			public void listFun() {
				SmartEncodeConfig();
			}
		});
		
		addFunctionToList("WlanConfig", new ListFunctionInterface(){
			@Override
			public void listFun(){
				WlanConfig();
			}
		});
	
		addFunctionToList("SnapPictureToFile", new ListFunctionInterface(){
			@Override
			public void listFun(){
				SnapPictureToFile();
			}
		});
		
		addFunctionToList("FindNumberStat", new ListFunctionInterface(){
			@Override
			public void listFun(){
				FindNumberStat();
			}
		});
		
		addFunctionToList("TestChannelName", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestChannelName();
			}
		});
		
		addFunctionToList("TestCommConfig", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestCommConfig();
			}
		});
		
		addFunctionToList("TestFindFile", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestFindFile();
			}
		});
		addFunctionToList("StartListenEx", new ListFunctionInterface(){
			@Override
			public void listFun(){
				StartListenEx();
			}
		});
		
		addFunctionToList("TestTalk", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestTalk();
			}
		});
		
		addFunctionToList("TestUserOperation", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestUserOperation();
			}
		});

		addFunctionToList("TestRecordState", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestRecordState();
			}
		});


		addFunctionToList("TestStatisticFlux", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestStatisticFlux();
			}
		});
		
		addFunctionToList("TestIO", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestIO();
			}
		});
		
		addFunctionToList("TestKeyFrame", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestKeyFrame();
			}
		});

		addFunctionToList("TestComm", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestComm();
			}
		});
		
		addFunctionToList("TestDevConfig", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestDevConfig();
			}
		});
		
		addFunctionToList("TestDevTime", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestDevTime();
			}
		});
		
		addFunctionToList("TestSearchDev", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestSearchDev();
			}
		});
		
		addFunctionToList("TestRecord", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestRecord();
			}
		});
		
		addFunctionToList("TestAlm", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestAlm();
			}
		});
		
		addFunctionToList("TestQueryDev", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestQueryDev();
			}
		});
		
		addFunctionToList("TestPtz", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestPtz();
			}
		});
		
		addFunctionToList("TestFileBurned", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestFileBurned();
			}
		});
		
		addFunctionToList("TestQProdDef", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestQProdDef();
			}
		});
		
		addFunctionToList("TestMatrix", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestMatrix();
			}
		});
		
		addFunctionToList("TestCfgEncode", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestCfgEncode();
			}
		});

		addFunctionToList("TestDevState", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestDevState();
			}
		});
		
		addFunctionToList("TestControlDev", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestControlDev();
			}
		});
		addFunctionToList("IntelBrass", new ListFunctionInterface(){
			@Override
			public void listFun(){
				IntelBrass();
			}
		});		
		
		addFunctionToList("MobilePushNotify", new ListFunctionInterface(){
			@Override
			public void listFun(){
				MobilePushNotify();
			}
		});
		
		addFunctionToList("GetDeviceInfoAll", new ListFunctionInterface(){
			@Override
			public void listFun(){
				GetDeviceInfoAll();
			}
		});

		addFunctionToList("AttachCameraState", new ListFunctionInterface(){
			@Override
			public void listFun(){
				AttachCameraState();
			}
		});


		addFunctionToList("QueryDeviceLog", new ListFunctionInterface(){
			@Override
			public void listFun(){
				QueryDeviceLog();
			}
		});


		addFunctionToList("testGetBypass", new ListFunctionInterface(){
			@Override
			public void listFun(){
				testGetBypass();
			}
		});


		addFunctionToList("TestGetActiveDefenceInfo", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestGetActiveDefenceInfo();
			}
		});
		
		addFunctionToList("TestQueryBurnDevInfo", new ListFunctionInterface(){
			@Override
			public void listFun(){
				TestQueryBurnDevInfo();
			}
		});
		
		addFunctionToList("QueryBurnSessionNum", new ListFunctionInterface() {
			@Override
			public void listFun() {
				QueryBurnSessionNum();
			}
		});
		
		// 测试刻录相关功能
		addFunctionToList("BurnFunction", new ListFunctionInterface() {
			@Override
			public void listFun() {
				BurnFunction();
			}
		});
		
		// 测试庭审主机相关配置
		addFunctionToList("TestCourtHearingConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				TestCourtHearingConfig();
			}
		});
		
		// 测试庭审主机相关接口
		addFunctionToList("TestCourtHearingInterface", new ListFunctionInterface() {
			@Override
			public void listFun() {
				TestCourtHearingInterface();
			}
			});
		
		// 公安智能项目
		addFunctionToList("VideoAnalyseSourceConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				VideoAnalyseSourceConfig();
			}
		});
		
		// 四川移动看店配置
		addFunctionToList("VSP_SCYDKDConfig", new ListFunctionInterface() {
			@Override
			public void listFun() {
				VSP_SCYDKDConfig();
			}
		});
		
		// 录像下载
		addFunctionToList("DownloadByTime", new ListFunctionInterface() {
			@Override
			public void listFun() {
				DownloadByTime();
			}
		});

		//获取录播状态
		addFunctionToList("GetRecordState",new ListFunctionInterface(){
			public void listFun() {
				GetRecordState();
			}
		});
		//录播主机复合操作
		addFunctionToList("RecordedOperateComposite",new ListFunctionInterface(){
			public void listFun() {
				RecordedOperateComposite();
			}
			
		});
		addFunctionToList("SetDetectorWorkMode",new ListFunctionInterface(){
			@Override
			public void listFun() {
				SetDetectorWorkMode();
			}
		});
		//上传文件
		addFunctionToList("StartUploadRemoteFile",new ListFunctionInterface(){
			@Override
			public void listFun() {
				StartUploadRemoteFile();
			}
		});
		//停止上传文件
		addFunctionToList("StopUploadRemoteFile",new ListFunctionInterface(){
			@Override
			public void listFun() {
				StopUploadRemoteFile();
			}
		});
		
		//马来西亚定制
		addFunctionToList("EncodeConfig",new ListFunctionInterface(){
			@Override
			public void listFun() {
				EncodeConfig();
			}
		});
		//DMSS接入智能锁新增接口测试
		addFunctionToList("SetLowRateWPANPower",new ListFunctionInterface(){
			@Override
			public void listFun() {
				SetLowRateWPANPower();
			}
		});
		//DMSS接入智能锁
		addFunctionToList("SetSmartLockUsername",new ListFunctionInterface(){
			@Override
			public void listFun() {
				SetSmartLockUsername();
			}
		});
		addFunctionToList("GetSmartLockRegisterInfo",new ListFunctionInterface(){
			@Override
			public void listFun() {
				GetSmartLockRegisterInfo();
			}
		});
		addFunctionToList("MatrixAddCamerasByDevice",new ListFunctionInterface(){
			@Override
			public void listFun() {
				MatrixAddCamerasByDevice();
			}
		});
		addFunctionToList("AudioInputVolumeConfig",new ListFunctionInterface(){
			@Override
			public void listFun() {
				AudioInputVolumeConfig();
			}
		});
		addFunctionToList("AudioOutputVolumeConfig",new ListFunctionInterface(){
			@Override
			public void listFun() {
				AudioOutputVolumeConfig();
			}
		});
		addFunctionToList("InitAccount",new ListFunctionInterface(){

			@Override
			public void listFun() {
				InitAccount();
			}

		});
		addFunctionToList("ResetWebPwd",new ListFunctionInterface(){

			@Override
			public void listFun() {
				ResetWebPwd();
			}

		});

	}
	
    private BroadcastReceiver dynamicReceiver   = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(DISCONNECTED_BROAST)){ 
              
              ToolKits.writeLog("DISCONNECTED_BROAST lanalyHandle:" + lanalyHandle);
               if(lanalyHandle != 0)
               {
            	   INetSDK.StopLoadPic(lanalyHandle);
            	   lanalyHandle = 0;
               }
            }
            else if(intent.getAction().equals(AUTOCONNECTED_BROAST)) {
            	 ToolKits.writeLog("DISCONNECTED_BROAST AUTOCONNECTED_BROAST:" + lanalyHandle);
            	 DevicePicUpload cbProc = new DevicePicUpload();
	        		lanalyHandle = INetSDK.RealLoadPictureEx(
	        				__LoginHandle, 0, FinalVar.EVENT_IVS_ALL, true, cbProc, 0);
            }
        }};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		generFunctionList();
		ToolKits.verifyStoragePermissions(TestInterfaceActivity.this);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, testList);
		setListAdapter(adapter);
		
		IntentFilter dynamicFilter = new IntentFilter();
		dynamicFilter.addAction(DISCONNECTED_BROAST);      
		dynamicFilter.addAction(AUTOCONNECTED_BROAST);
        registerReceiver(dynamicReceiver, dynamicFilter);  // 注册自定义动态广播消息*/
	}
	
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		unregisterReceiver(dynamicReceiver);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		ListFunctionInterface function = listFunctions.get(position);
		if(function != null)
		{
			function.listFun();
		}
		super.onListItemClick(l, v, position, id);
	}

	void testGetBypass() {
		new ToolKits.SimpleAsyncTask<Integer>() {
			 @Override
			    protected void onPreExecute() {
			            super.onPreExecute();
			    }
			     
			     @Override
			      protected  Integer doInBackground(Void... params)
			     {
			 		NET_DEVSTATE_GET_BYPASS stuBypass = new NET_DEVSTATE_GET_BYPASS();
			 		
			 		// 如果要查询本地通道，就初始化如下字段
					stuBypass.nLocalCount = 5;
					stuBypass.pnLocal = new int[stuBypass.nLocalCount];
					for (int i = 0; i < stuBypass.nLocalCount; i++)
					{
						stuBypass.pnLocal[i] = i;
					}
					stuBypass.pemLocal = new int[stuBypass.nLocalCount];
					
					// 如果要查询扩展通道，就初始化如下字段
					stuBypass.nExtendedCount = 4;
					stuBypass.pnExtended = new int[stuBypass.nExtendedCount];
					for (int i = 0; i < stuBypass.nExtendedCount; i++)
					{
						stuBypass.pnExtended[i] = i;
					}
					stuBypass.pemExtended = new int[stuBypass.nExtendedCount];
					
					try {
						boolean bRet = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_GET_BYPASS, stuBypass, 5000);
						if (bRet)
						{
							for (int i = 0; i < stuBypass.nLocalCount; i++)
							{
								ToolKits.writeErrorLog("channel" + stuBypass.pnLocal[i] + " is " + stuBypass.pemLocal[i]);
							}
							for (int i = 0; i < stuBypass.nExtendedCount; i++)
							{
								ToolKits.writeErrorLog("extchannel" + stuBypass.pnExtended[i] + " is " + stuBypass.pemExtended[i]);
							}
						}
						else
						{
							ToolKits.writeErrorLog("query bypass state err:" );
							return -1;
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
			        return 0;
			     }
			     @Override
			     protected void onPostExecute(Integer result) {
			    	 ToolKits.writeLog("SimpleAsyncTask result: " + result);
			      }  
		}.execute();
	}
	
	void TestGetActiveDefenceInfo()
	{
		new ToolKits.SimpleAsyncTask<Integer>() {
			 @Override
			    protected void onPreExecute() {
			            super.onPreExecute();
			    }
			     
			     @Override
			      protected  Integer doInBackground(Void... params)
			     {
			    	 NET_ACTIVATEDDEFENCEAREA stuActiveDefence = new NET_ACTIVATEDDEFENCEAREA();
			    	 
			    	 // 如果要查询本地通道，就初始化如下字段
			    	 stuActiveDefence.nAlarmInCount = 8;
			    	 stuActiveDefence.nRetAlarmInCount = 0;
			    	 stuActiveDefence.pstuAlarmInDefenceAreaInfo = new NET_ACTIVATEDDEFENCEAREA_INFO[stuActiveDefence.nAlarmInCount];
			    	 for (int i = 0; i < stuActiveDefence.nAlarmInCount; i++)
			    	 {
			    		 stuActiveDefence.pstuAlarmInDefenceAreaInfo[i] = new NET_ACTIVATEDDEFENCEAREA_INFO();
			    	 }
			    	 
			    	 // 如果要查询扩展通道，就初始化如下字段
			    	 stuActiveDefence.nExAlarmInCount = 2;
			    	 stuActiveDefence.nRetExAlarmInCount = 0;
			    	 stuActiveDefence.pstuExAlarmInDefenceAreaInfo = new NET_ACTIVATEDDEFENCEAREA_INFO[stuActiveDefence.nExAlarmInCount];
			    	 for (int i = 0; i < stuActiveDefence.nExAlarmInCount; i++)
			    	 {
			    		 stuActiveDefence.pstuExAlarmInDefenceAreaInfo[i] = new NET_ACTIVATEDDEFENCEAREA_INFO();
			    	 }
			    	 
			    	 try {
						boolean bRet = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_ACTIVATEDDEFENCEAREA, stuActiveDefence, 5000);
						if (bRet)
						{
							for (int i = 0; i < stuActiveDefence.nRetAlarmInCount; i++)
							{
								ToolKits.writeErrorLog("activated defence[" + i + "] is " + stuActiveDefence.pstuAlarmInDefenceAreaInfo[i].nChannel);
								ToolKits.writeLog("activated time: " + stuActiveDefence.pstuAlarmInDefenceAreaInfo[i].stuActivationTime.toString());
							}
							for (int i = 0; i < stuActiveDefence.nRetExAlarmInCount; i++)
							{
								ToolKits.writeErrorLog("activated extdefence[" + i + "] is " + stuActiveDefence.pstuExAlarmInDefenceAreaInfo[i].nChannel);
							}
						}
						else
						{
							ToolKits.writeErrorLog("query active defence state err:" );
							return -1;
						}
			    	 }
			    	 catch (Exception e) {
			    		 e.printStackTrace();
			    	 }

			        return 0;
			     }
			     @Override
			     protected void onPostExecute(Integer result) {
			    	 ToolKits.writeLog("SimpleAsyncTask result: " + result);
			      }  
		}.execute();
	}
	
	void TestCommConfig() {
		CFG_COMMGROUP_INFO m_stCommInfo = new CFG_COMMGROUP_INFO();
		m_stCommInfo.nCommNum = 4;
		int channelID = 0;
		if (ToolKits.GetDevConfig(FinalVar.CFG_CMD_COMM, m_stCommInfo,
				__LoginHandle, channelID
				, 1024)) {
			ToolKits.showMessage(this, "Get Config Success");

		} else {
			ToolKits.showMessage(this, "Get Config Failed");
		}
	}

	void TestFindFile() {
		NET_TIME stBegin = new NET_TIME();
		stBegin.dwYear = 2014;
		stBegin.dwMonth = 12;
		stBegin.dwDay = 12;
		NET_TIME stEnd = new NET_TIME();
		stEnd.dwYear = 2014;
		stEnd.dwMonth = 12;
		stEnd.dwDay = 12;
		stEnd.dwHour = 23;
		stEnd.dwMinute = 59;
		stEnd.dwSecond = 59;
		int channelID = 0;

		MEDIAFILE_FACE_DETECTION_PARAM stQueryPar = new MEDIAFILE_FACE_DETECTION_PARAM();
		stQueryPar.nChannelID = channelID;
		stQueryPar.stuStartTime = stBegin;
		stQueryPar.stuEndTime = stEnd;
		stQueryPar.emPicType = EM_FACEPIC_TYPE.NET_FACEPIC_TYPE_SMALL;
		stQueryPar.bDetailEnable = false;
		long lRetQuery = INetSDK.FindFileEx(__LoginHandle,
				EM_FILE_QUERY_TYPE.SDK_FILE_QUERY_FACE_DETECTION, stQueryPar,
				5000);
		MEDIAFILE_FACE_DETECTION_INFO stQueryFile[] = new MEDIAFILE_FACE_DETECTION_INFO[5];
		for (int i = 0; i < 5; i++) {
			stQueryFile[i] = new MEDIAFILE_FACE_DETECTION_INFO();
		}
		int nFileCount = INetSDK.FindNextFileEx(lRetQuery,
				EM_FILE_QUERY_TYPE.SDK_FILE_QUERY_FACE_DETECTION, stQueryFile,
				5000);
		if(nFileCount == 0) {
			ToolKits.writeErrorLog("FindNextFileEx failed");
		}
		boolean zRet = INetSDK.FindCloseEx(lRetQuery);
		if (!zRet) {
			ToolKits.writeErrorLog("FindCloseEx failed");
		}
	}
	
	void TestFindFileRecord() {
		long hLogin = __LoginHandle;
		int channel = 0;
		NET_TIME stTimeStart = new NET_TIME();
		stTimeStart.dwYear = 2016;
		stTimeStart.dwMonth = 12;
		stTimeStart.dwDay = 23;
		stTimeStart.dwHour = 0;
		
		NET_TIME stTimeEnd = new NET_TIME();
		stTimeEnd.dwYear = 2016;
		stTimeEnd.dwMonth = 12;
		stTimeEnd.dwDay = 23;
		stTimeEnd.dwHour = 12;
		
		boolean bTime = false;
		int nWaitTime = 5000;
		
		long lFind = INetSDK.FindFile(hLogin, channel, 9, null, stTimeStart, stTimeEnd, bTime, nWaitTime);
		if (0 == lFind) {
			ToolKits.writeErrorLog("FindFile");
			return;
		}
		
		NET_RECORDFILE_INFO stuFile = new NET_RECORDFILE_INFO();
		int fileNum = 0;
		int nRet =0;
		do {
			
			nRet = INetSDK.FindNextFile(lFind, stuFile);
			if (nRet < 0) {
				break;
			}
			
			fileNum ++;
			System.out.println(stuFile.ch);
		}while(nRet > 0);
		
		System.out.println("fileNum " + fileNum);
		INetSDK.FindClose(lFind);		
	}
	
	void TestFindTrafficCar() {
		int type = EM_FILE_QUERY_TYPE.SDK_FILE_QUERY_TRAFFICCAR;
		MEDIA_QUERY_TRAFFICCAR_PARAM stuParam = new MEDIA_QUERY_TRAFFICCAR_PARAM(1);
		
		//通道号  -1代表所有通道
		stuParam.nChannelID = -1;
		
		 //文件类型 0:任意类型, 1:jpg图片, 2:dav文件
		stuParam.nMediaType = 1;
		
	    //设置 文件标志, 0xFF-使用nFileFlagEx, 0-表示所有录像, 1-定时文件, 2-手动文件, 3-事件文件, 4-重要文件, 5-合成文件
	    stuParam.byFileFlag = 0;

	    //设置 是否需要在查询过程中随意跳转，0-不需要，1-需要
	    stuParam.byRandomAccess = 1;

		//查询相应车道信息  -1代表所有车道
		stuParam.byLane = -1;

		//查询车开往的方向  -1代表所有方向
		stuParam.nDirection = -1;
		
		// 设置 开始时间		
		stuParam.StartTime.dwYear   = 2016;
	    stuParam.StartTime.dwMonth  = 12;
	    stuParam.StartTime.dwDay    = 1;
	    stuParam.StartTime.dwHour   = 10;
	    stuParam.StartTime.dwMinute = 0;
	    stuParam.StartTime.dwSecond = 0;
	    
	    // 设置 结束时间
	    stuParam.EndTime.dwYear   = 2016;
	    stuParam.EndTime.dwMonth  = 12;
	    stuParam.EndTime.dwDay    = 7;
	    stuParam.EndTime.dwHour   = 10;
	    stuParam.EndTime.dwMinute = 30;
	    stuParam.EndTime.dwSecond = 0;
	    
	    stuParam.nEventTypeNum = 1; // 设置需要查询的违章事件类型个数
	    stuParam.pEventTypes[0] = FinalVar.EVENT_IVS_TRAFFICJUNCTION;
		
	    // 设置查询类型 获取查询句柄
		long hFind = INetSDK.FindFileEx(__LoginHandle, type, stuParam, 3000);
		if (hFind == 0) {
			ToolKits.writeErrorLog("Failed to Find File");
			return;			
		}

		// 每次获取的违章数量
		int nMaxConut = 10;
		
		MEDIAFILE_TRAFFICCAR_INFO[] pMediaFileInfo = new MEDIAFILE_TRAFFICCAR_INFO[nMaxConut];
		for (int i = 0; i < pMediaFileInfo.length; ++i) {
			pMediaFileInfo[i] = new MEDIAFILE_TRAFFICCAR_INFO();
		}
		
		//循环查询
		int nCurCount = 0;
		while(true)
		{
			// 查询, nRetCount为返回的查询个数
			int nRetCount = INetSDK.FindNextFileEx(hFind, nMaxConut, pMediaFileInfo, 3000);
			if (nRetCount < 0)
			{
				ToolKits.writeErrorLog("FindNextFileEx failed!");
                break;
			}			
			
			for (int j = 0; j < nRetCount; j++)
			{
				//更多信息输出，可以查看结构体MEDIAFILE_TRAFFICCAR_INFO_EX中的MEDIAFILE_TRAFFICCAR_INFO
                MEDIAFILE_TRAFFICCAR_INFO info = pMediaFileInfo[j];
                ToolKits.writeLog("-------------------------[ "+ (j + nCurCount * nMaxConut) +" ]---------------------------");
                ToolKits.writeLog("通道号: " + info.ch);
                ToolKits.writeLog("违章类型: " + info.nEvents[0]);
                ToolKits.writeLog("车道号: " + info.byLane);
                String plateNumber = new String(info.szPlateNumber).trim();
                ToolKits.writeLog("车牌号码: " + plateNumber);
                ToolKits.writeLog("车牌颜色: " + new String(info.szPlateColor).trim());
                ToolKits.writeLog("车牌类型: " + new String(info.szPlateType).trim());
                ToolKits.writeLog("车身颜色: " + new String(info.szVehicleColor).trim());
                ToolKits.writeLog("事件组ID: " + info.nGroupID);
                ToolKits.writeLog("该组ID抓怕张数: " + info.byCountInGroup);
                ToolKits.writeLog("该组ID抓怕索引: " + info.byIndexInGroup);
			}

			if(nRetCount < nMaxConut)
			{
				break;
			}
			else
			{
				nCurCount++;
			}
		}
		
		INetSDK.FindCloseEx(hFind);
	}

	//订阅报警事件
	void StartListenEx() {
		boolean zRet;
		
//		NET_ROAD_LIST_INFO stRoadList = new NET_ROAD_LIST_INFO();
//		zRet = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_GET_ROAD_LIST, stRoadList, 5000);
		
		INetSDK.SetDVRMessCallBack(new TestMessageCallBack());
		zRet = INetSDK.StartListenEx(__LoginHandle);    // 订阅报警


		// INetSDK.StopListen(__LoginHandle);    // 取消订阅
	}

	void TestTalk() {
		String strLog = "";

		strLog += "\nTesting SetDeviceMode...";
		boolean zRet = INetSDK.SetDeviceMode(__LoginHandle,
				EM_USEDEV_MODE.SDK_TALK_CLIENT_MODE, null);
		strLog += zRet;

		strLog += "\nTesting StartTalkEx...";
		long lServiceH = INetSDK.StartTalkEx(__LoginHandle,
				new TestpfAudioDataCallBack());
		strLog += lServiceH;

		strLog += "\nTesting TalkSendData...";
		int nRet = INetSDK.TalkSendData(lServiceH, null);
		strLog += nRet;

		strLog += "\nTesting StopTalkEx...";
		zRet = INetSDK.StopTalkEx(lServiceH);
		strLog += zRet;

		ToolKits.showMessage(this, strLog);
	}

	void TestUserOperation() {
		String strLog = "";

		USER_MANAGE_INFO_EX stManaInfo = new USER_MANAGE_INFO_EX();

		strLog += "\nTesting QueryUserInfoEx...";
		boolean zRet = INetSDK.QueryUserInfoEx(
				__LoginHandle, stManaInfo, 5000);
		strLog += zRet;

		strLog += "\nTesting OperateUserInfoEx...";
		zRet = INetSDK.OperateUserInfoEx(__LoginHandle, 4,
				stManaInfo.userList[12], null, 5000);
		strLog += zRet;
		strLog += "\nTesting OperateUserInfoEx...";
		zRet = INetSDK.OperateUserInfoEx(__LoginHandle, 3,
				stManaInfo.userList[12], null, 5000);
		strLog += zRet;

		strLog += "\nTesting StopTalkEx...";
		strLog += zRet;

		USER_MANAGE_INFO stMana = new USER_MANAGE_INFO();

		strLog += "\nTesting QueryUserInfo...";
		zRet = INetSDK.QueryUserInfo(__LoginHandle, stMana,
				5000);
		strLog += zRet;
		strLog += "\nTesting OperateUserInfo...";
		zRet = INetSDK.OperateUserInfo(__LoginHandle, 4,
				stMana.userList[12], null, 5000);
		strLog += zRet;
		strLog += "\nTesting OperateUserInfo...";
		zRet = INetSDK.OperateUserInfo(__LoginHandle, 3,
				stMana.userList[12], null, 5000);
		strLog += zRet;

		USER_MANAGE_INFO_NEW stManaNew = new USER_MANAGE_INFO_NEW();
		strLog += "\nTesting QueryUserInfoNew...";
		zRet = INetSDK.QueryUserInfoNew(__LoginHandle,
				stManaNew, 5000);
		strLog += zRet;
		strLog += "\nTesting OperateUserInfoNew...";
		zRet = INetSDK.OperateUserInfoNew(__LoginHandle, 4,
				stManaNew.userList[12], null, 5000);
		strLog += zRet;
		strLog += "\nTesting OperateUserInfoNew...";
		zRet = INetSDK.OperateUserInfoNew(__LoginHandle, 3,
				stManaNew.userList[12], null, 5000);
		strLog += zRet;

		ToolKits.showMessage(this, strLog);
	}

	void TestQueryLog() {
		String strLog = "";

		SDK_NEWLOG_ITEM stLogs[] = new SDK_NEWLOG_ITEM[3];
		for (int i = 0; i < stLogs.length; i++) {
			stLogs[i] = new SDK_NEWLOG_ITEM();
		}
		Integer stRetCount = new Integer(0);
		Integer stReserved = new Integer(0);

		boolean zRet = INetSDK.QueryLogEx(__LoginHandle,
				SDK_LOG_QUERY_TYPE.SDKLOG_ALL, stLogs, stRetCount, stReserved, 5000);
		if(zRet) {
			ToolKits.writeLog("Count :　" + stRetCount.intValue());
			for(int i=0; i<stRetCount.intValue(); i++) {
				ToolKits.writeLog("Type :　" + stLogs[i].type);
			}
		}
		stReserved = Integer.valueOf(1);
		zRet = INetSDK.QueryLogEx(__LoginHandle,
				SDK_LOG_QUERY_TYPE.SDKLOG_ALL, stLogs, stRetCount, stReserved, 5000);

		ToolKits.showMessage(this, strLog);
	}

	void TestRecordState() {
		// 查询录像状态
		byte buf[] = new byte[32];
		Integer stRet = Integer.valueOf(0);
		boolean zRet = INetSDK.QueryRecordState(__LoginHandle, buf, stRet, 3000);
		if(zRet) {
			ToolKits.writeLog("QueryRecordState Succeed! " + stRet.intValue());
			for(int i = 0; i < stRet.intValue(); i++) {
				if(buf[i] == 0) {
					ToolKits.writeLog("通道" + i + ": 不录像");
				} else if (buf[i] == 1) {
					ToolKits.writeLog("通道" + i + ": 手动录像");
				} else if (buf[i] == 2) {
					ToolKits.writeLog("通道" + i + ": 自动录像");
				}
			}
		} else {
			ToolKits.writeErrorLog("QueryRecordState Failed!" );
		}
		
		// 设置录像状态
		zRet = false;
		byte bufIn[] = new byte[16];
		for (int i = 0; i < 16; i++) {
			bufIn[i] = buf[i];
		}
		zRet = INetSDK.SetupRecordState(__LoginHandle, bufIn);
		if(zRet) { 
			ToolKits.writeLog("SetupRecordState Succeed!");
		} else {
			ToolKits.writeErrorLog("SetupRecordState Failed!" );
		}
		
		
		//////////////////////////// 开始/停止录像//////////////////////////////////////
		// 获取
		AV_CFG_RecordMode[] recordMode = new AV_CFG_RecordMode[20];
		for(int i=0; i<20; i++) {
			recordMode[i] = new AV_CFG_RecordMode();
		}
		
		for(int i=0; i<20; i++) {
			boolean bRecordModeGet = ToolKits. GetDevConfig(FinalVar.CFG_CMD_RECORDMODE,  recordMode[i], 
					__LoginHandle, i, 1024*1024*2);
			if(bRecordModeGet) {	
			ToolKits.writeLog("通道" + i + "的录像模式：" + recordMode[i].nMode + "\n" +
							  "辅码流录像模式：" + recordMode[i].nModeExtra1);	
			} else {
			ToolKits.writeErrorLog("GetRecordMode Failed!" );
			}
			// 设置，开始、关闭录像
//			recordMode[i].nMode = 1;
//			recordMode[i].nModeExtra1 = 2;
			
			boolean bRecordModeSet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_RECORDMODE, recordMode[i], 
									__LoginHandle, i, 1024*1024*2);
			if(bRecordModeSet) {
			ToolKits.writeLog("SetRecordMode Succeed!");
			} else {
			ToolKits.writeErrorLog("SetRecordMode Failed!" );
			}
		}
		

//		CFG_DEVRECORDGROUP_INFO devRecordGroup = new CFG_DEVRECORDGROUP_INFO();
//
//		boolean bdevRecordGroupGet = ToolKits. GetDevConfig(FinalVar.CFG_CMD_DEVRECORDGROUP,  devRecordGroup, 
//									__LoginHandle, -1, 1024*1024*2);
//		if(bdevRecordGroupGet) {	
//				ToolKits.writeLog("通道个数：" + devRecordGroup.nChannelNum);
//				for(int i=0; i<devRecordGroup.nChannelNum; i++) {
//					ToolKits.writeLog("设备名称：" + devRecordGroup.stuDevRecordInfo[i].byStatus);
//				}
//		} else {
//			ToolKits.writeErrorLog("GetDevRecordGroup Failed!" );
//		}
//		// 设置录像状态
////		String devNameString = "666";
////		String chnNameString = "888";
////		System.arraycopy(devNameString.getBytes(), 0, devRecordGroup.stuDevRecordInfo[0].szDevName, 0, devNameString.getBytes().length);
////		System.arraycopy(chnNameString.getBytes(), 0, devRecordGroup.stuDevRecordInfo[0].szChannelName, 0, chnNameString.getBytes().length);
////		
//		boolean bdevRecordGroupSet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_DEVRECORDGROUP, devRecordGroup, 
//										__LoginHandle, -1, 1024*1024*2);
//		if(bdevRecordGroupSet) {
//			ToolKits.writeLog("SetDevRecordGroup Succeed!");
//		} else {
//			ToolKits.writeErrorLog("SetDevRecordGroup Failed!" );
//		}	
	}

	void TestStatisticFlux() {
		String strLog = "";

		strLog += "\nTesting GetStatiscFlux...";
		int nRet = INetSDK.GetStatiscFlux(__LoginHandle, 0);
		strLog += nRet;
		strLog += "\nTesting SetMaxFlux...";
		boolean zRet = INetSDK.SetMaxFlux(__LoginHandle,
				(short) 1024);
		strLog += zRet;

		ToolKits.showMessage(this, strLog);
	}

	void TestIO() {
//		String strLog = "";
//
//		ALARM_CONTROL stInCtr[] = new ALARM_CONTROL[16];
//		for (int i = 0; i < 16; i++) {
//			stInCtr[i] = new ALARM_CONTROL();
//		}
//		Integer stIOCount = new Integer(0);
//		strLog += "\nTesting QueryIOControlState...";
//		boolean zRet = INetSDK.QueryIOControlState(
//				__LoginHandle, SDK_IOTYPE.SDK_ALARMINPUT,
//				stInCtr, stIOCount, 3000);
//		strLog += zRet;
//		strLog += "\nTesting IOControl...";
//		zRet = INetSDK.IOControl(__LoginHandle,
//				SDK_IOTYPE.SDK_ALARMINPUT, stInCtr);
//		strLog += zRet;
//
//		ToolKits.showMessage(this, strLog);
		
		int emType = SDK_IOTYPE.SDK_ALARM_TRIGGER_MODE;
		Integer nIOCount = Integer.valueOf(0);

		boolean bRet = INetSDK.QueryIOControlState(__LoginHandle, emType, null, nIOCount, 3000);

		if(bRet) {
			ToolKits.writeLog("通道个数 ： " + nIOCount.intValue());
		} else {
			ToolKits.writeErrorLog("查询通道个数失败！" );
		}
		
		TRIGGER_MODE_CONTROL[] objArr = new TRIGGER_MODE_CONTROL[nIOCount.intValue()];
		for(int i = 0; i < nIOCount.intValue(); i++) {
			objArr[i] = new TRIGGER_MODE_CONTROL();
		}	

		boolean bRet1 = INetSDK.QueryIOControlState(__LoginHandle, emType, objArr, nIOCount, 3000);
		
		if(bRet1) {
			ToolKits.writeLog("触发方式个数 ：" + nIOCount.intValue());
		} else { 
			ToolKits.writeErrorLog("查询触发方式失败！" );
		}				
	}
	

	void TestKeyFrame() {
		String strLog = "";
		int channelID = 0;
		strLog += "\nTesting MakeKeyFrame...";
		boolean zRet = INetSDK.MakeKeyFrame(__LoginHandle,
				channelID, 0);
		strLog += zRet;

		ToolKits.showMessage(this, strLog);
	}

	void TestComm() {
		String strLog = "";

		SDK_COMM_STATE stParam = new SDK_COMM_STATE();
		strLog += "\nTesting QueryTransComParams...";
		boolean zRet = INetSDK.QueryTransComParams(
				__LoginHandle, 0, stParam, 3000);
		strLog += zRet;

		TestfTransComCallBack stCb = new TestfTransComCallBack();
		strLog += "\nTesting CreateTransComChannel...";
		long lHandle = INetSDK.CreateTransComChannel(
				__LoginHandle, 0, 115200, 4, 1, 1, stCb);
		strLog += lHandle;

		byte data[] = new byte[8];
		data[0] = 'a';
		data[1] = 'b';
		data[2] = 'c';
		data[3] = 'd';
		data[4] = 'e';
		data[5] = 'f';
		data[6] = 'g';
		data[7] = 'h';
		strLog += "\nTesting SendTransComData...";
		zRet = INetSDK.SendTransComData(lHandle, data, 8);
		strLog += zRet;

		strLog += "\nTesting DestroyTransComChannel...";
		zRet = INetSDK.DestroyTransComChannel(lHandle);
		strLog += zRet;

		ToolKits.showMessage(this, strLog);
	}

	void TestDevConfig() {
		boolean zRet;
		int channelID = 0;
		Integer stRet = new Integer(0);
		SDK_DVR_VIDEOOSD_CFG stVideoOsdCfg[] = new SDK_DVR_VIDEOOSD_CFG[1];
		stVideoOsdCfg[0] = new SDK_DVR_VIDEOOSD_CFG();
		zRet = INetSDK.GetDevConfig(__LoginHandle,
				FinalVar.SDK_DEV_VIDEO_OSD_CFG,
				channelID, stVideoOsdCfg, stRet, 3000);
		
		zRet = false;
		byte[] name = new byte[64];
		System.arraycopy(name, 0, stVideoOsdCfg[0].StOSDTitleOpt[0].SzOSD_Name, 0, name.length);
		String strnameString = "dahua";
		System.arraycopy(strnameString.getBytes(), 0, stVideoOsdCfg[0].StOSDTitleOpt[0].SzOSD_Name, 0, strnameString.getBytes().length);
		zRet = INetSDK.SetDevConfig(__LoginHandle,
				FinalVar.SDK_DEV_VIDEO_OSD_CFG, channelID, stVideoOsdCfg, 3000);
		if(zRet) {
			ToolKits.writeLog("Succeed-------------------------");
		} else {
			ToolKits.writeErrorLog("" );
		}
/*		
		Integer stRet = new Integer(0);
		SDKDEV_WLAN_INFO stCfg[] = new SDKDEV_WLAN_INFO[1];
		stCfg[0] = new SDKDEV_WLAN_INFO();
		zRet = INetSDK.GetDevConfig(__LoginHandle,
				FinalVar.SDK_DEV_WLAN_CFG, 0, stCfg, stRet, 3000);
		if(zRet){
		ToolKits.writeLog("Get Wlan Succeed!");
		ToolKits.writeLog("SSID：" + new String(stCfg[0].szSSID).trim());
		} else {
		ToolKits.writeErrorLog("Get Wlan Failed!" );
		}
		
		stCfg[0].nEncryption = 7;
		System.arraycopy("IPC-GYL".getBytes(), 0, stCfg[0].szSSID, 0, "IPC-GYL".getBytes().length);
		System.arraycopy("88668866".getBytes(), 0, stCfg[0].szKeys[0], 0, "88668866".getBytes().length);
		System.arraycopy("88668866".getBytes(), 0, stCfg[0].szWPAKeys, 0, "88668866".getBytes().length);
		zRet = INetSDK.SetDevConfig(__LoginHandle,
				FinalVar.SDK_DEV_WLAN_CFG, channelID, stCfg, 3000);
		if (!zRet) {
			ToolKits.writeErrorLog("error, ");
		}
		
		Integer stRet1 = new Integer(0);
		SDKDEV_WLAN_DEVICE_LIST stCfg1[] = new SDKDEV_WLAN_DEVICE_LIST[1];
		stCfg1[0] = new SDKDEV_WLAN_DEVICE_LIST();
		zRet = INetSDK.GetDevConfig(__LoginHandle,
				FinalVar.SDK_DEV_WLAN_DEVICE_CFG, 0, stCfg1, stRet1, 3000);
		if(zRet){
		ToolKits.writeLog("Get Wlan Succeed!");
		ToolKits.writeLog("SSID：" + new String(stCfg1[0].lstWlanDev[0].szSSID).trim());
		} else {
		ToolKits.writeErrorLog("Get Wlan Failed!" );
		}
		
//		Integer stRet = new Integer(0);
//		SDKDEV_WIRELESS_ROUTING_CFG stVideoOsdCfg[] = new SDKDEV_WIRELESS_ROUTING_CFG[1];
//		stVideoOsdCfg[0] = new SDKDEV_WIRELESS_ROUTING_CFG();
//		zRet = INetSDK.GetDevConfig(__LoginHandle,
//				FinalVar.SDK_DEV_WIRELESS_ROUTING_CFG, -1, stVideoOsdCfg, stRet, 3000);
//		if(zRet){
//			ToolKits.writeLog("Get Routing Succeed!");
//			ToolKits.writeLog("网关：" + new String(stVideoOsdCfg[0].szGateWay).trim());
//			ToolKits.writeLog("子网掩码：" + new String(stVideoOsdCfg[0].szSubMark).trim());
//		} else {
//			ToolKits.writeErrorLog("Get Routing Failed!" );
//		}
//		zRet = false;
//		String gateWay = "172.9.3.0";
//		System.arraycopy(gateWay.getBytes(), 0, stVideoOsdCfg[0].szGateWay, 0, gateWay.getBytes().length);
//		String subMark = "255.255.255.0";
//		System.arraycopy(subMark.getBytes(), 0, stVideoOsdCfg[0].szSubMark, 0, subMark.getBytes().length);
//		zRet = INetSDK.SetDevConfig(__LoginHandle,
//				FinalVar.SDK_DEV_WIRELESS_ROUTING_CFG, -1, stVideoOsdCfg, 3000);
//		if(zRet){
//			ToolKits.writeLog("Set Routing Succeed!");
//		} else {
//			ToolKits.writeErrorLog("Set Routing Failed!" );
//		}   */
	}

	void TestChannelName() {
		byte name[] = new byte[16 * 32];
		Integer stRet = new Integer(0);
		boolean zRet = INetSDK.QueryChannelName(
				__LoginHandle, name, stRet, 1000);
		zRet = INetSDK.SetupChannelName(__LoginHandle, name);

		SDK_CHANNEL_OSDSTRING stOSDStr = new SDK_CHANNEL_OSDSTRING();
		stOSDStr.bEnable = true;
		stOSDStr.dwPosition[0] = 1;
		stOSDStr.dwPosition[1] = 3;
		stOSDStr.szStrings[0][0] = 'a';
		stOSDStr.szStrings[0][1] = 'b';
		stOSDStr.szStrings[0][2] = 'c';
		stOSDStr.szStrings[1][0] = 'd';
		stOSDStr.szStrings[1][1] = 'e';
		stOSDStr.szStrings[1][2] = 'f';
		int channelID = 0;
		zRet = INetSDK.SetupChannelOsdString(__LoginHandle,
				channelID, stOSDStr);
	}

	void TestDevTime() {
		NET_TIME stNetTime = new NET_TIME();
		boolean bRet = INetSDK.QueryDeviceTime(
				__LoginHandle, stNetTime, 2000);
		if(bRet) {
			ToolKits.writeLog(stNetTime.toString());
		}
		stNetTime.dwYear = 2018;
		stNetTime.dwMonth = 1;
		stNetTime.dwDay = 27;
		stNetTime.dwHour = 10;
		stNetTime.dwMinute = 14;
		stNetTime.dwSecond = 50;

		boolean zRet = INetSDK.SetupDeviceTime(__LoginHandle,
				stNetTime);
		if(zRet) {
			ToolKits.writeLog("SetupDeviceTime Succeed!");
		} else {
			ToolKits.writeErrorLog("SetupDeviceTime Failed!" );
		}
		
	}

	void TestSearchDev() {
		ToolKits.writeLog("Start Search Device");
		
		boolean bRet;
		TestfSearchDevicesCB stCb = new TestfSearchDevicesCB();
		long lRet = INetSDK.StartSearchDevices(stCb);
		if (lRet == 0 ) {
			ToolKits.writeErrorLog("Failed to start search device");
		}
		
		int cnt = 0;
		while(true) {
			try {
				Thread.sleep(6000);
				cnt++;
				
				if(cnt > 10) {
					INetSDK.StopSearchDevices(lRet);
					ToolKits.writeLog("Stop Search Devices");
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		bRet = INetSDK.StopSearchDevices(lRet);

		DEVICE_NET_INFO stInfo[] = new DEVICE_NET_INFO[2];
		stInfo[0] = new DEVICE_NET_INFO();
		stInfo[1] = new DEVICE_NET_INFO();
		Integer stNum = new Integer(0);
		bRet = INetSDK.SearchDevices(stInfo, stNum, 6000);
		for (int i = 0; i < stNum; i++) {
			ToolKits.writeLog(new String(stInfo[i].szIP).trim());
		}

		DEVICE_IP_SEARCH_INFO stIpInfo = new DEVICE_IP_SEARCH_INFO();
		stIpInfo.nIpNum = 3;
		System.arraycopy("172.23.1.25".getBytes(), 0, stIpInfo.szIP[0], 0,
				"172.23.2.66".getBytes().length);
		System.arraycopy("172.23.1.26".getBytes(), 0, stIpInfo.szIP[1], 0,
				"172.23.2.67".getBytes().length);
		System.arraycopy("172.23.1.27".getBytes(), 0, stIpInfo.szIP[2], 0,
				"172.23.2.68".getBytes().length);
		bRet = INetSDK.SearchDevicesByIPs(stIpInfo, stCb, 3000);
		
	}

	void TestDecode() {
		String strLog = "";

		TestfMessDataCallBack stCb = new TestfMessDataCallBack();
		strLog += "\nTesting SetOperateCallBack...";
		boolean zRet = INetSDK.SetOperateCallBack(
				__LoginHandle, stCb);
		strLog += zRet;

		TestfDecPlayBackPosCallBack stPlBkCb = new TestfDecPlayBackPosCallBack();
		strLog += "\nTesting SetDecPlaybackPos...";
		zRet = INetSDK.SetDecPlaybackPos(__LoginHandle,
				stPlBkCb);
		strLog += zRet;

		DEV_DECODER_INFO stInfo = new DEV_DECODER_INFO();
		strLog += "\nTesting QueryDecoderInfo...";
		zRet = INetSDK.QueryDecoderInfo(__LoginHandle,
				stInfo, 3000);
		strLog += zRet;

		int nDecoderId = 0;
		DEV_ENCODER_INFO stEncDev = new DEV_ENCODER_INFO();
		String sIp = "172.28.2.99";
		String sUser = "admin";
		System.arraycopy(sIp.toCharArray(), 0, stEncDev.szDevIp, 0,
				sIp.length());
		System.arraycopy(sUser.toCharArray(), 0, stEncDev.szDevUser, 0,
				sUser.length());
		System.arraycopy(sUser.toCharArray(), 0, stEncDev.szDevPwd, 0,
				sUser.length());
		stEncDev.wDevPort = 37777;
		stEncDev.nDevChannel = 0;
		stEncDev.nStreamType = 0;
		stEncDev.bDevChnEnable = 1;
		stEncDev.byConnType = 0;
		stEncDev.byWorkMode = 0;
		strLog += "\nTesting SwitchDecTVEncoder...";
		long lOperateHandle = INetSDK.SwitchDecTVEncoder(
				__LoginHandle, nDecoderId, stEncDev);
		strLog += lOperateHandle;

		DEC_PLAYBACK_FILE_PARAM stFilePara = new DEC_PLAYBACK_FILE_PARAM();
		System.arraycopy(sIp.toCharArray(), 0, stFilePara.szDevIp, 0,
				sIp.length());
		stFilePara.wDevPort = 37777;
		stFilePara.bDevChnEnable = 1;
		stFilePara.byDecoderID = (byte) nDecoderId;
		System.arraycopy(sUser.toCharArray(), 0, stFilePara.szDevUser, 0,
				sUser.length());
		System.arraycopy(sUser.toCharArray(), 0, stFilePara.szDevPwd, 0,
				sUser.length());

		NET_TIME stBegin = new NET_TIME();
		stBegin.dwYear = 2013;
		stBegin.dwMonth = 1;
		stBegin.dwDay = 1;
		NET_TIME stEnd = new NET_TIME();
		stEnd.dwYear = 2013;
		stEnd.dwMonth = 1;
		stEnd.dwDay = 1;
		int channelID = 0;
		long lFindHandle = INetSDK.FindFile(__LoginHandle,
				channelID, 0, null, stBegin, stEnd,
				false, 3000);
		NET_RECORDFILE_INFO stFile = new NET_RECORDFILE_INFO();
		INetSDK.FindNextFile(lFindHandle, stFile);
		zRet = INetSDK.FindClose(lFindHandle);
		stFilePara.stuRecordInfo = stFile;

		strLog += "\nTesting DecTVPlayback...";
		long lPlBkFile = INetSDK.DecTVPlayback(
				__LoginHandle, nDecoderId,
				DEC_PLAYBACK_MODE.Dec_By_Device_File, stFilePara);
		strLog += lPlBkFile;

		strLog += "\nTesting CtrlDecPlayback...";
		zRet = INetSDK
				.CtrlDecPlayback(__LoginHandle, nDecoderId,
						DEC_CTRL_PLAYBACK_TYPE.Dec_Playback_Pause, 10, 1000);
		strLog += zRet;

		int nMonitorId = 1;
		int nSplitNum = 4;
		byte bChn[] = new byte[4];
		bChn[0] = 0;
		bChn[1] = 1;
		bChn[2] = 2;
		bChn[3] = 3;
		strLog += "\nTesting CtrlDecTVScreen...";
		long lRetHandle = INetSDK.CtrlDecTVScreen(
				__LoginHandle, nMonitorId, true, nSplitNum,
				bChn, 4);
		strLog += lRetHandle;

		DEV_DECCHANNEL_STATE stDecChnSta = new DEV_DECCHANNEL_STATE();
		strLog += "\nTesting QueryDecChannelFlux...";
		zRet = INetSDK.QueryDecChannelFlux(__LoginHandle,
				nDecoderId, stDecChnSta, 1000);
		strLog += zRet;

		DEV_DECODER_TV stTVInfo = new DEV_DECODER_TV();
		strLog += "\nTesting QueryDecoderTVInfo...";
		zRet = INetSDK.QueryDecoderTVInfo(__LoginHandle,
				nDecoderId, stTVInfo, 3000);
		strLog += zRet;

		DEV_ENCODER_INFO stEncInfo = new DEV_ENCODER_INFO();
		strLog += "\nTesting QueryDecEncoderInfo...";
		zRet = INetSDK.QueryDecEncoderInfo(__LoginHandle,
				nDecoderId, stEncInfo, 3000);
		strLog += zRet;

		byte bArr[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		strLog += "\nTesting SetDecTVOutEnable...";
		zRet = INetSDK.SetDecTVOutEnable(__LoginHandle,
				bArr, 9, 3000);
		strLog += zRet;

		byte byChns[] = { 4, 5, 6, 7 };
		strLog += "\nTesting AddTourCombin...";
		int iAddTour = INetSDK.AddTourCombin(__LoginHandle,
				nMonitorId, nSplitNum, byChns, 4, 1000);
		strLog += iAddTour;

		strLog += "\nTesting SetTourCombin...";
		zRet = INetSDK.SetTourCombin(__LoginHandle,
				nMonitorId, iAddTour, nSplitNum, byChns, 4, 1000);
		strLog += zRet;

		DEC_COMBIN_INFO stDecComb = new DEC_COMBIN_INFO();
		strLog += "\nTesting QueryTourCombin...";
		zRet = INetSDK.QueryTourCombin(__LoginHandle,
				nMonitorId, iAddTour, stDecComb, 1000);
		strLog += zRet;

		DEC_TOUR_COMBIN stDecTour = new DEC_TOUR_COMBIN();
		strLog += "\nTesting QueryDecoderTour...";
		zRet = INetSDK.QueryDecoderTour(__LoginHandle,
				nMonitorId, stDecTour, 1000);
		strLog += zRet;

		strLog += "\nTesting SetDecoderTour...";
		zRet = INetSDK.SetDecoderTour(__LoginHandle,
				nMonitorId, stDecTour, 1000);
		strLog += zRet;

		strLog += "\nTesting CtrlDecoderTour...";
		zRet = INetSDK.CtrlDecoderTour(__LoginHandle,
				nMonitorId, DEC_CTRL_TOUR_TYPE.Dec_Tour_Start, 1000);
		strLog += zRet;

		strLog += "\nTesting DelTourCombin...";
		zRet = INetSDK.DelTourCombin(__LoginHandle,
				nMonitorId, iAddTour, 1000);
		strLog += zRet;

		ToolKits.showMessage(this, strLog);
	}

	void TestRecord() {
		String strLog = "";

		NET_TIME stTime = new NET_TIME();
		stTime.dwYear = 2013;
		stTime.dwMonth = 12;
		NET_RECORD_STATUS stStatus = new NET_RECORD_STATUS();
		strLog += "\nTesting QueryRecordStatus...";

		int channelID = 0;
		boolean zRet = INetSDK.QueryRecordStatus(
				__LoginHandle,
				channelID, 0, stTime, null, stStatus,
				3000);
		strLog += zRet;

		NET_TIME stTimeB = new NET_TIME();
		stTimeB.dwYear = 2013;
		stTimeB.dwMonth = 12;
		stTimeB.dwDay = 1;
		NET_TIME stTimeE = new NET_TIME();
		stTimeE.dwYear = 2013;
		stTimeE.dwMonth = 12;
		stTimeE.dwDay = 31;
		BOOL_RET bRet = new BOOL_RET();
		strLog += "\nTesting QueryRecordTime...";
		zRet = INetSDK.QueryRecordTime(__LoginHandle,
				channelID, 0, stTimeB, stTimeE, null,
				bRet, 3000);
		strLog += zRet;

		NET_RECORDFILE_INFO stRecFileInfo[] = new NET_RECORDFILE_INFO[16];
		for (int i = 0; i < 16; i++) {
			stRecFileInfo[i] = new NET_RECORDFILE_INFO();
		}
		Integer stCount = new Integer(0);
		strLog += "\nTesting QuickQueryRecordFile...";
		zRet = INetSDK.QuickQueryRecordFile(__LoginHandle,
				channelID, 0, stTimeB, stTimeE, null,
				stRecFileInfo, stCount, 3000, false);
		strLog += zRet;

		NET_FURTHEST_RECORD_TIME stFurTime = new NET_FURTHEST_RECORD_TIME(16);
		strLog += "\nTesting QueryFurthestRecordTime...";
		zRet = INetSDK.QueryFurthestRecordTime(
				__LoginHandle, 0, null, stFurTime, 3000);
		strLog += zRet;

		byte bArrState[] = new byte[16];
		Integer stRetLen = new Integer(0);
		strLog += "\nTesting QueryExtraRecordState...";
		zRet = INetSDK.QueryExtraRecordState(__LoginHandle,
				bArrState, stRetLen, 3000);
		strLog += zRet;

		ToolKits.showMessage(this, strLog);
	}

	void TestAlm() {
		String strLog = "";

		strLog += "\nTesting AlarmReset...";
		int channelID = 0;
		boolean zRet = INetSDK.AlarmReset(__LoginHandle, -1,
				channelID, null, 3000);
		strLog += zRet;

		ToolKits.showMessage(this, strLog);
	}

	void TestQueryDev() {
		String strLog = "";
		SDK_PRODUCTION_DEFNITION stProd = new SDK_PRODUCTION_DEFNITION();
		
		boolean zRetTemp = INetSDK.QueryProductionDefinition(
				__LoginHandle, stProd, 3000);
		if (!zRetTemp)
		{
			strLog += "\nFailed to query definition.....";
			ToolKits.showMessage(this, strLog);
			
			return;
		}
		
		int nVideoInChannel = stProd.nVideoInChannel;
		

		Integer stIntRet = new Integer(0);
		Integer stIntRetLen = new Integer(0);
		strLog += "\nTesting QueryRemotDevState...";
		int channelID = 0;
		for (int i=0; i<nVideoInChannel; i++)
		{
			boolean zRet = INetSDK.QueryRemotDevState(
						__LoginHandle,
						FinalVar.SDK_DEVSTATE_ONLINE,
						channelID, stIntRet,
						stIntRetLen, 3000);
		
			strLog += zRet;
			strLog += "\nThe remote ipc state on channel:";

			if (1 == stIntRet)
			{
				strLog += "Online";
			}
			else
			{
				strLog += "OffLine";
			}
		
			ToolKits.showMessage(this, strLog);
		}

		/*SDK_CARD_QUERY_EN stQuery = new SDK_CARD_QUERY_EN();
		strLog += "\nTesting QuerySystemInfo...";
		zRet = INetSDK.QuerySystemInfo(__LoginHandle,
				SDK_SYS_ABILITY.ABILITY_CARD_QUERY, stQuery, 3000);
		strLog += zRet;

		char cBuf[] = new char[512];
		Integer stErr = new Integer(0);
		strLog += "\nTesting QueryNewSystemInfo...";
		zRet = INetSDK.QueryNewSystemInfo(__LoginHandle,
				"magicBox.getCPUUsage", channelID,
				cBuf, stErr, 3000);
		strLog += zRet;

		ToolKits.showMessage(this, strLog);*/
		
		// 查询门禁组数
    	CFG_CAP_ACCESSCONTROL accessCtr = new CFG_CAP_ACCESSCONTROL();
    	String szCommand = FinalVar.CFG_CAP_CMD_ACCESSCONTROLMANAGER;
    	char szOutBuffer[] = new char[1024];
    	Integer stError = new Integer(0);
    	boolean bQu = INetSDK.QueryNewSystemInfo(__LoginHandle, szCommand, 0, szOutBuffer, stError, 5000);
    	
    	if(bQu) {
    		bQu = INetSDK.ParseData(szCommand, szOutBuffer, accessCtr, null);
    		if(bQu){
    			ToolKits.writeLog("门禁组数：" + accessCtr.nAccessControlGroups);
    		} else {
    			ToolKits.writeErrorLog("ParseData Failed!" );
    		}
    	} else {
    		ToolKits.writeErrorLog("QueryNewSystemInfo Failed!" );
    	}
	}

	void TestPtz() {
		String strLog = "";

		PTZ_OPT_ATTR stArrt = new PTZ_OPT_ATTR();
		strLog += "\nTesting GetPtzOptAttr...";
		boolean zRet = INetSDK.GetPtzOptAttr(__LoginHandle,
				0, stArrt, 3000);
		strLog += zRet;

		ToolKits.showMessage(this, strLog);
	}

	void TestLoadPic() {
		String strLog = "";
		int channelID = 0;
		TestfAnalyzerDataCallBack stCb = new TestfAnalyzerDataCallBack();
		strLog += "\nTesting RealLoadPicture...";
		long lRealLoad = INetSDK.RealLoadPicture(
				__LoginHandle,
				channelID, FinalVar.EVENT_IVS_ALL,
				stCb);
		strLog += lRealLoad;

		strLog += "\nTesting StopLoadPic...";
		boolean zRet = INetSDK.StopLoadPic(lRealLoad);
		strLog += zRet;

		boolean zNeeded = true;
		strLog += "\nTesting RealLoadPictureEx...";
		lRealLoad = INetSDK.RealLoadPictureEx(__LoginHandle,
				channelID, FinalVar.EVENT_IVS_ALL,
				zNeeded, stCb, null);
		strLog += lRealLoad;

		strLog += "\nTesting StopLoadPic...";
		zRet = INetSDK.StopLoadPic(lRealLoad);
		strLog += zRet;

		ToolKits.showMessage(this, strLog);
	}

	void TestFileBurned() {
		String strLog = "";

		boolean zRet;
		/*
		 * SDK_BURNING_DEVINFO stBurnDevInfo = new SDK_BURNING_DEVINFO(); strLog
		 * += "\nTesting QueryDevState..."; zRet = INetSDK.QueryDevState(
		 * __LoginHandle, INetSDK.SDK_DEVSTATE_BURNING_DEV,
		 * stBurnDevInfo, 1000); strLog += zRet;
		 */

		NET_IN_FILEBURNED_START stInStart = new NET_IN_FILEBURNED_START();
		stInStart.szMode = "append";
		stInStart.szDeviceName = "/dev/sda"; // stBurnDevInfo.stDevs[0].dwDriverName.toString();
		stInStart.szFilename = "/mnt/sdcard/NetSDK/collectorsdk.bat";
		stInStart.cbBurnPos = new TestfBurnFileCallBack();
		NET_OUT_FILEBURNED_START stOutStart = new NET_OUT_FILEBURNED_START();
		strLog += "\nTesting StartUploadFileBurned...";
		long lFileBurned = INetSDK.StartUploadFileBurned(
				__LoginHandle, stInStart, stOutStart, 1000);
		strLog += lFileBurned;

		strLog += "\nTesting StartUploadFileBurned...";
		zRet = INetSDK.SendFileBurned(lFileBurned);
		strLog += zRet;

		strLog += "\nTesting StopUploadFileBurned...";
		zRet = INetSDK.StopUploadFileBurned(lFileBurned);
		strLog += zRet;

		ToolKits.showMessage(this, strLog);
	}

	void TestQProdDef() {
		String strLog = "";

		SDK_PRODUCTION_DEFNITION stProd = new SDK_PRODUCTION_DEFNITION();
		strLog += "\nTesting QueryProductionDefinition...";
		boolean zRet = INetSDK.QueryProductionDefinition(
				__LoginHandle, stProd, 1000);
		strLog += zRet;

		ToolKits.showMessage(this, strLog);
	}

	void TestMatrix() {
		String strLog = "";

		SDK_MATRIX_CARD_LIST stCardList = new SDK_MATRIX_CARD_LIST();
		/*		boolean zRet = INetSDK.QueryMatrixCardInfo(
				__LoginHandle, stCardList, 3000);
		
		for (int i = 0; i < stCardList.nCount; i++) {
			ToolKits.writeLog("Inchannel" + stCardList.stuCards[i].nAudioInChn);
		}

		SDK_SPLIT_CAPS stCaps = new SDK_SPLIT_CAPS();
		zRet = INetSDK.GetSplitCaps(__LoginHandle,
				stCardList.stuCards[0].nVideoDecChnMin, stCaps, 1000);   // 通道号必须是最小值
*/
		SDK_SPLIT_MODE_INFO stMode = new SDK_SPLIT_MODE_INFO();
		stMode.emSplitMode = SDK_SPLIT_MODE.SDK_SPLIT_4;
		boolean zRet = INetSDK.SetSplitMode(__LoginHandle,
				stCardList.stuCards[0].nVideoDecChnMin, stMode, 1000);
		if(zRet) {
			ToolKits.writeLog("SetSplitMode Succeed!");
		} else {
			ToolKits.writeErrorLog("SetSplitMode Failed!" );
		}
		boolean bRet = INetSDK.GetSplitMode(__LoginHandle,
				stCardList.stuCards[0].nVideoDecChnMin, stMode, 1000);
		if(bRet) {
			ToolKits.writeLog("GetSplitMode Succeed!" + stMode.emSplitMode);
		} else {
			ToolKits.writeErrorLog("GetSplitMode Failed!" );
		}
		
/*		SDK_SPLIT_SOURCE stSplitSrc[] = new SDK_SPLIT_SOURCE[16];
		for (int i = 0; i < 16; i++) {
			stSplitSrc[i] = new SDK_SPLIT_SOURCE();
		}
		Integer stRetCount = new Integer(0);
		zRet = INetSDK.GetSplitSource(__LoginHandle,
				stCardList.stuCards[0].nVideoDecChnMin, -1, stSplitSrc,
				stRetCount, 1000);

		zRet = INetSDK.SetSplitSource(__LoginHandle,
				stCardList.stuCards[0].nVideoDecChnMin, -1, stSplitSrc,
				stRetCount.intValue(), 1000);

		ToolKits.showMessage(this, strLog);*/
	}

	void TestCfgEncode() {
		SDKDEV_SYSTEM_ATTR_CFG stSystemArr[] = new SDKDEV_SYSTEM_ATTR_CFG[1];
		stSystemArr[0] = new SDKDEV_SYSTEM_ATTR_CFG();
		Integer stRet = new Integer(0);
		boolean zRet = INetSDK.GetDevConfig(__LoginHandle,
				FinalVar.SDK_DEV_DEVICECFG, -1, stSystemArr, stRet, 3000);
		if (zRet) {
			CFG_ENCODE_INFO stEncInfo[] = new CFG_ENCODE_INFO[stSystemArr[0].byVideoCaptureNum];
			CFG_DSPENCODECAP_INFO stDspEncCap[] = new CFG_DSPENCODECAP_INFO[stSystemArr[0].byVideoCaptureNum];
			// CFG_RECORD_INFO stRecord[] = new
			// CFG_RECORD_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_ALARMIN_INFO stAlarmIn[] = new
			// CFG_ALARMIN_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_NETALARMIN_INFO stNetAlarmIn[] = new
			// CFG_NETALARMIN_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_MOTION_INFO stMotion[] = new
			// CFG_MOTION_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_SHELTER_INFO stShelter[] = new
			// CFG_SHELTER_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_VIDEOLOST_INFO stLossDetect[] = new
			// CFG_VIDEOLOST_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_STORAGENOEXIST_INFO[] stStorageNotExist = new
			// CFG_STORAGENOEXIST_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_STORAGEFAILURE_INFO[] stStorageFailure = new
			// CFG_STORAGEFAILURE_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_STORAGELOWSAPCE_INFO[] stStorageLowSpace = new
			// CFG_STORAGELOWSAPCE_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_NETABORT_INFO[] stNetAbort = new
			// CFG_NETABORT_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_IPCONFLICT_INFO[] stIPConflict = new
			// CFG_IPCONFLICT_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_WATERMARK_INFO[] stWaterMark = new
			// CFG_WATERMARK_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_ANALYSERULES_INFO[] stVideoAnalyseRule = new
			// CFG_ANALYSERULES_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_RAINBRUSH_INFO[] stRainBrush = new
			// CFG_RAINBRUSH_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_DEV_DISPOSITION_INFO[] stGeneral = new
			// CFG_DEV_DISPOSITION_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_ATMMOTION_INFO[] stFetchMoneyOverTime = new
			// CFG_ATMMOTION_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_DEVICESTATUS_INFO[] stDeviceStatus = new
			// CFG_DEVICESTATUS_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_IPSERVER_STATUS[] stIpsServer = new
			// CFG_IPSERVER_STATUS[stSystemArr.byVideoCaptureNum];
			// CFG_VIDEOENCODEROI_INFO[] stVideoEncodeROI = new
			// CFG_VIDEOENCODEROI_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_RTSP_INFO_OUT[] stRTSP = new
			// CFG_RTSP_INFO_OUT[stSystemArr.byVideoCaptureNum];
			// CFG_VIDEODIAGNOSIS_GLOBAL[] stVideoDiagnosisGlobal = new
			// CFG_VIDEODIAGNOSIS_GLOBAL[stSystemArr.byVideoCaptureNum];
			// CFG_TRAFFIC_WORKSTATE_INFO[] stWorkState = new
			// CFG_TRAFFIC_WORKSTATE_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_STORAGEGROUP_INFO[] stStorageDevGroup = new
			// CFG_STORAGEGROUP_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_RECORDTOGROUP_INFO[] stRecordToGroup = new
			// CFG_RECORDTOGROUP_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_ANALYSERULES_INFO[] stIVSFramRule = new
			// CFG_ANALYSERULES_INFO[stSystemArr.byVideoCaptureNum];
			// CFG_METADATA_SERVER[] stMetaDataServer = new
			// CFG_METADATA_SERVER[stSystemArr.byVideoCaptureNum];
//			 AV_CFG_RecordMode[] stRecordMode = new
//			 AV_CFG_RecordMode[stSystemArr.byVideoCaptureNum];
			// AV_CFG_VideoOutAttr[] stVideoOut = new
			// AV_CFG_VideoOutAttr[stSystemArr.byVideoCaptureNum];
//			 AV_CFG_RemoteDevice[] stRemoteDevice = new
//			 AV_CFG_RemoteDevice[stSystemArr.byVideoCaptureNum];
			// AV_CFG_RemoteChannel[] stRemoteChannel = new
			// AV_CFG_RemoteChannel[stSystemArr.byVideoCaptureNum];
			// AV_CFG_RemoteChannel[] stRaid = new
			// AV_CFG_RemoteChannel[stSystemArr.byVideoCaptureNum];
			// AV_CFG_RecordSource[] stRecordSource = new
			// AV_CFG_RecordSource[stSystemArr.byVideoCaptureNum];
			// AV_CFG_StorageGroup[] stStorageGroup = new
			// AV_CFG_StorageGroup[stSystemArr.byVideoCaptureNum];
			// AV_CFG_Language[] stLanguage = new
			// AV_CFG_Language[stSystemArr.byVideoCaptureNum];
			// AV_CFG_AccessFilter[] stAccessFilter = new
			// AV_CFG_AccessFilter[stSystemArr.byVideoCaptureNum];
			// AV_CFG_AutoMaintain[] stAutoMaintain = new
			// AV_CFG_AutoMaintain[stSystemArr.byVideoCaptureNum];
			// CFG_NAS_INFO_EX[] stNAS = new
			// CFG_NAS_INFO_EX[stSystemArr.byVideoCaptureNum];
			AV_CFG_ChannelName stChnName[] = new AV_CFG_ChannelName[stSystemArr[0].byVideoCaptureNum];

			for (int i = 0; i < stSystemArr[0].byVideoCaptureNum; i++) {
				stEncInfo[i] = new CFG_ENCODE_INFO();
				stDspEncCap[i] = new CFG_DSPENCODECAP_INFO();
				// stRecord[i] = new CFG_RECORD_INFO();
				// stAlarmIn[i] = new CFG_ALARMIN_INFO();
				// stNetAlarmIn[i] = new CFG_NETALARMIN_INFO();
				// stLossDetect[i] = new CFG_VIDEOLOST_INFO();
				// stStorageNotExist[i] = new CFG_STORAGENOEXIST_INFO();
				// stStorageFailure[i] = new CFG_STORAGEFAILURE_INFO();
				// stStorageLowSpace[i] = new CFG_STORAGELOWSAPCE_INFO();
				// stNetAbort[i] = new CFG_NETABORT_INFO();
				// stIPConflict[i] = new CFG_IPCONFLICT_INFO();
				// stWaterMark[i] = new CFG_WATERMARK_INFO();
				// stVideoAnalyseRule[i] = new CFG_ANALYSERULES_INFO();
				// stRainBrush[i] = new CFG_RAINBRUSH_INFO();
				// stFetchMoneyOverTime[i] = new CFG_ATMMOTION_INFO();
				// stDeviceStatus[i] = new CFG_DEVICESTATUS_INFO();
				// stVideoEncodeROI[i] = new CFG_VIDEOENCODEROI_INFO();
				// stRTSP[i] = new CFG_RTSP_INFO_OUT();
				// stVideoDiagnosisGlobal[i] = new CFG_VIDEODIAGNOSIS_GLOBAL();
				// stWorkState[i] = new CFG_TRAFFIC_WORKSTATE_INFO();
				// stStorageDevGroup[i] = new CFG_STORAGEGROUP_INFO(2048);
				// stRecordToGroup[i] = new CFG_RECORDTOGROUP_INFO();
				// stMetaDataServer[i] = new CFG_METADATA_SERVER();
				// stRecordMode[i] = new AV_CFG_RecordMode();
				// stVideoOut[i] = new AV_CFG_VideoOutAttr();
				// stRemoteDevice[i] = new AV_CFG_RemoteDevice(8);
				// stRemoteChannel[i] = new AV_CFG_RemoteChannel();
				// stRaid[i] = new AV_CFG_RemoteChannel();
				// stRecordSource[i] = new AV_CFG_RecordSource();
				// stStorageGroup[i] = new AV_CFG_StorageGroup();
				// stLanguage[i] = new AV_CFG_Language();
				// stAccessFilter[i] = new AV_CFG_AccessFilter();
				// stAutoMaintain[i] = new AV_CFG_AutoMaintain();
				// stNAS[i] = new CFG_NAS_INFO_EX();
				// stMotion[i] = new CFG_MOTION_INFO();
				// stShelter[i] = new CFG_SHELTER_INFO();
				stChnName[i] = new AV_CFG_ChannelName();
			}

			for (int i = 0; i < stSystemArr[0].byVideoCaptureNum; i++) {
				boolean zRetEnc;

				SDKDEV_DSP_ENCODECAP stDspCap = new SDKDEV_DSP_ENCODECAP();
				zRetEnc = INetSDK.QueryDevState(
						__LoginHandle,
						FinalVar.SDK_DEVSTATE_DSP, stDspCap, 3000);
				if (!zRetEnc) {
					zRetEnc = ToolKits.GetDevConfig(FinalVar.CFG_CMD_ENCODE,
							stEncInfo[i], __LoginHandle, i,
							4096);
					if (!zRetEnc) {
						ToolKits.showErrorMessage(this, "GetDevConfig failed, "
								+ FinalVar.CFG_CMD_ENCODE + ", chn: " + i);
					} else {
						zRetEnc = ToolKits.SetDevConfig(
								FinalVar.CFG_CMD_ENCODE, stEncInfo[i],
								__LoginHandle, i, 4096);
					}
				}
				// NAS
				/*
				 * zRetEnc = ToolKits.GetDevConfig(FinalVar.CFG_CMD_NASEX,
				 * stNAS[i], __LoginHandle, i, 4096); if
				 * (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_NASEX + ", chn: "
				 * + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_NASEX, stNAS[i],
				 * __LoginHandle, i, 4096); }
				 */
				/*
				 * //AutoMaintain zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_AUTOMAINTAIN,
				 * stAutoMaintain[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_AUTOMAINTAIN +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_AUTOMAINTAIN,
				 * stAutoMaintain[i], __LoginHandle, i,
				 * 4096); }
				 */
				/*
				 * //AccessFilter zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_ACCESSFILTER,
				 * stAccessFilter[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_ACCESSFILTER +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_ACCESSFILTER,
				 * stAccessFilter[i], __LoginHandle, i,
				 * 4096); }
				 */
				/*
				 * //Language zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_LANGUAGE,
				 * stLanguage[i], __LoginHandle, i, 4096); if
				 * (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_LANGUAGE +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_LANGUAGE,
				 * stLanguage[i], __LoginHandle, i, 4096); }
				 */
				/*
				 * //StorageGroup zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_STORAGEGROUP,
				 * stStorageGroup[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_STORAGEGROUP +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_STORAGEGROUP,
				 * stStorageGroup[i], __LoginHandle, i,
				 * 4096); }
				 */
				/*
				 * //RecordSource zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_RECORDSOURCE,
				 * stRecordSource[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_RECORDSOURCE +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_RECORDSOURCE,
				 * stRecordSource[i], __LoginHandle, i,
				 * 4096); }
				 */
				/*
				 * //Raid zRetEnc = ToolKits.GetDevConfig(FinalVar.CFG_CMD_RAID,
				 * stRaid[i], __LoginHandle, i, 4096); if
				 * (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_RAID + ", chn: " +
				 * i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_RAID, stRaid[i],
				 * __LoginHandle, i, 4096); }
				 */
				/*
				 * //RemoteChannel zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_REMOTECHANNEL,
				 * stRemoteChannel[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_REMOTECHANNEL +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_REMOTECHANNEL,
				 * stRemoteChannel[i], __LoginHandle, i,
				 * 4096); }
				 */
				/*
				 * //RemoteDevice zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_REMOTEDEVICE,
				 * stRemoteDevice[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_REMOTEDEVICE +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_REMOTEDEVICE,
				 * stRemoteDevice[i], __LoginHandle, i,
				 * 4096); }
				 */
				/*
				 * //VideoOut zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_VIDEOOUT,
				 * stVideoOut[i], __LoginHandle, i, 4096); if
				 * (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_VIDEOOUT +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_VIDEOOUT,
				 * stVideoOut[i], __LoginHandle, i, 4096); }
				 */
				/*
				 * //RecordMode zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_RECORDMODE,
				 * stRecordMode[i], __LoginHandle, i, 4096);
				 * if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_RECORDMODE +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_RECORDMODE,
				 * stRecordMode[i], __LoginHandle, i, 4096);
				 * }
				 */
				/*
				 * //MetaDataServer zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_MD_SERVER,
				 * stMetaDataServer[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_MD_SERVER +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_MD_SERVER,
				 * stMetaDataServer[i], __LoginHandle, i,
				 * 4096); }
				 */
				/*
				 * //IVSFramRule zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_IVSFRAM_RULE,
				 * stIVSFramRule[i], __LoginHandle, i, 4096);
				 * if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_IVSFRAM_RULE +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_IVSFRAM_RULE,
				 * stIVSFramRule[i], __LoginHandle, i, 4096);
				 * }
				 */
				/*
				 * //RecordToGroup zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_RECORDTOGROUP,
				 * stRecordToGroup[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_RECORDTOGROUP +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_RECORDTOGROUP,
				 * stRecordToGroup[i], __LoginHandle, i,
				 * 4096); }
				 */
				/*
				 * zRetEnc = ToolKits.GetDevConfig(FinalVar.CFG_CMD_HDVR_DSP,
				 * stDspEncCap[i], __LoginHandle, i, 4096);
				 * if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_HDVR_DSP +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_HDVR_DSP,
				 * stDspEncCap[i], __LoginHandle, i, 4096); }
				 * 
				 * zRetEnc = ToolKits.GetDevConfig(FinalVar.CFG_CMD_RECORD,
				 * stRecord[i], __LoginHandle, i, 4096); if
				 * (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_RECORD + ", chn: "
				 * + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_RECORD, stRecord[i],
				 * __LoginHandle, i, 4096); }
				 * 
				 * zRetEnc = ToolKits.GetDevConfig(FinalVar.CFG_CMD_ALARMINPUT,
				 * stAlarmIn[i], __LoginHandle, i, 4096); if
				 * (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_ALARMINPUT +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_ALARMINPUT,
				 * stAlarmIn[i], __LoginHandle, i, 4096); }
				 */

				/*
				 * //NetAlarm zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_NETALARMINPUT,
				 * stNetAlarmIn[i], __LoginHandle, i, 4096);
				 * if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_NETALARMINPUT +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_NETALARMINPUT,
				 * stNetAlarmIn[i], __LoginHandle, i, 4096);
				 * }
				 */
				/*
				 * //LossDetect stLossDetect[i].nChannelID = i; zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_VIDEOLOST,
				 * stLossDetect[i], __LoginHandle, i, 4096);
				 * if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_VIDEOLOST +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_VIDEOLOST,
				 * stLossDetect[i], __LoginHandle, i, 4096);
				 * }
				 */
				/*
				 * //StorageNotExist //stStorageNotExist[i].bEnable = true;
				 * zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_STORAGENOEXIST,
				 * stStorageNotExist[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_STORAGENOEXIST +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_STORAGENOEXIST,
				 * stStorageNotExist[i], __LoginHandle, i,
				 * 4096); }
				 */
				/*
				 * //StorageFailure zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_STORAGEFAILURE,
				 * stStorageFailure[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_STORAGEFAILURE +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_STORAGEFAILURE,
				 * stStorageFailure[i], __LoginHandle, i,
				 * 4096); }
				 */
				/*
				 * //StorageLowSpace zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_STORAGELOWSAPCE,
				 * stStorageLowSpace[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_STORAGELOWSAPCE +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_STORAGELOWSAPCE,
				 * stStorageLowSpace[i], __LoginHandle, i,
				 * 4096); }
				 */
				/*
				 * //NetAbort zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_NETABORT,
				 * stNetAbort[i], __LoginHandle, i, 4096); if
				 * (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_NETABORT +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_NETABORT,
				 * stNetAbort[i], __LoginHandle, i, 4096); }
				 */
				/*
				 * //IPConflict zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_IPCONFLICT,
				 * stIPConflict[i], __LoginHandle, i, 4096);
				 * if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_IPCONFLICT +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_IPCONFLICT,
				 * stIPConflict[i], __LoginHandle, i, 4096);
				 * }
				 */
				/*
				 * //WaterMark zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_WATERMARK,
				 * stWaterMark[i], __LoginHandle, i, 4096);
				 * if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_WATERMARK +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_WATERMARK,
				 * stWaterMark[i], __LoginHandle, i, 4096); }
				 */
				/*
				 * //VideoAnalyseRule zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_ANALYSERULE,
				 * stVideoAnalyseRule[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_ANALYSERULE +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_ANALYSERULE,
				 * stVideoAnalyseRule[i], __LoginHandle, i,
				 * 4096); }
				 */
				/*
				 * //RainBrush stRainBrush[i].bEnable = true; zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_RAINBRUSH,
				 * stRainBrush[i], __LoginHandle, i, 4096);
				 * if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_RAINBRUSH +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_RAINBRUSH,
				 * stRainBrush[i], __LoginHandle, i, 4096); }
				 */
				/*
				 * //General zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_DEV_GENERRAL,
				 * stGeneral[i], __LoginHandle, i, 4096); if
				 * (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_DEV_GENERRAL +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_DEV_GENERRAL,
				 * stGeneral[i], __LoginHandle, i, 4096); }
				 */
				/*
				 * //FetchMoneyOverTime zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_ATMMOTION,
				 * stFetchMoneyOverTime[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_ATMMOTION +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_ATMMOTION,
				 * stFetchMoneyOverTime[i], __LoginHandle, i,
				 * 4096); }
				 */
				/*
				 * //DeviceStatus zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_DEVICESTATUS,
				 * stDeviceStatus[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_DEVICESTATUS +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_DEVICESTATUS,
				 * stDeviceStatus[i], __LoginHandle, i,
				 * 4096); }
				 */
				/*
				 * //IpsServer zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_IPSSERVER,
				 * stIpsServer[i], __LoginHandle, i, 4096);
				 * if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_IPSSERVER +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_IPSSERVER,
				 * stIpsServer[i], __LoginHandle, i, 4096); }
				 */
				/*
				 * //VideoEncodeROI zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_VIDEOENCODEROI,
				 * stVideoEncodeROI[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_VIDEOENCODEROI +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_VIDEOENCODEROI,
				 * stVideoEncodeROI[i], __LoginHandle, i,
				 * 4096); }
				 */
				/*
				 * //RTSP zRetEnc = ToolKits.GetDevConfig(FinalVar.CFG_CMD_RTSP,
				 * stRTSP[i], __LoginHandle, i, 4096); if
				 * (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_RTSP + ", chn: " +
				 * i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_RTSP, stRTSP[i],
				 * __LoginHandle, i, 4096); }
				 */
				/*
				 * //VideoDiagnosisGlobal zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_VIDEODIAGNOSIS_GLOBAL,
				 * stVideoDiagnosisGlobal[i], __LoginHandle,
				 * i, 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " +
				 * FinalVar.CFG_CMD_VIDEODIAGNOSIS_GLOBAL + ", chn: " + i); }
				 * else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_VIDEODIAGNOSIS_GLOBAL,
				 * stVideoDiagnosisGlobal[i], __LoginHandle,
				 * i, 4096); }
				 */
				/*
				 * //WorkState zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_TRAFFIC_WORKSTATE,
				 * stWorkState[i], __LoginHandle, i, 4096);
				 * if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_TRAFFIC_WORKSTATE
				 * + ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_TRAFFIC_WORKSTATE,
				 * stWorkState[i], __LoginHandle, i, 4096); }
				 */
				/*
				 * //StorageDevGroup zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_STORAGEDEVGROUP,
				 * stStorageDevGroup[i], __LoginHandle, i,
				 * 4096); if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_STORAGEDEVGROUP +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_STORAGEDEVGROUP,
				 * stStorageDevGroup[i], __LoginHandle, i,
				 * 4096); }
				 */

				/*
				 * zRetEnc = ToolKits.GetDevConfig(FinalVar.CFG_CMD_VIDEOLOST,
				 * stLossDetect[i], __LoginHandle, i, 4096);
				 * if (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_VIDEOLOST +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_VIDEOLOST,
				 * stLossDetect[i], __LoginHandle, i, 4096);
				 * }
				 */

				/*
				 * zRetEnc =
				 * ToolKits.GetDevConfig(FinalVar.CFG_CMD_MOTIONDETECT,
				 * stMotion[i], __LoginHandle, i, 4096); if
				 * (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_MOTIONDETECT +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_MOTIONDETECT,
				 * stMotion[i], __LoginHandle, i, 4096); }
				 * 
				 * zRetEnc = ToolKits.GetDevConfig(FinalVar.CFG_CMD_VIDEOBLIND,
				 * stShelter[i], __LoginHandle, i, 4096); if
				 * (!zRetEnc) { ToolKits.showErrorMessage(this,
				 * "GetDevConfig failed, " + FinalVar.CFG_CMD_VIDEOBLIND +
				 * ", chn: " + i); } else { zRetEnc =
				 * ToolKits.SetDevConfig(FinalVar.CFG_CMD_VIDEOBLIND,
				 * stShelter[i], __LoginHandle, i, 4096); }
				 */

				zRetEnc = ToolKits
						.GetDevConfig(FinalVar.CFG_CMD_CHANNELTITLE,
								stChnName[i], __LoginHandle,
								i, 4096);
				if (!zRetEnc) {
					ToolKits.showErrorMessage(this, "GetDevConfig failed, "
							+ FinalVar.CFG_CMD_CHANNELTITLE + ", chn: " + i);
				} else {
					String strName = new String(stChnName[i].szName);
					zRetEnc = ToolKits.SetDevConfig(
							FinalVar.CFG_CMD_CHANNELTITLE, stChnName[i],
							__LoginHandle, i, 4096);
				}
			}
		}
	}
	
	void TestDevState() {
		
		final int numChannel = NetSDKApplication.getInstance().getDeviceInfo().nChanNum;
	
		
		// 耗时处理建议异步去做，不要在UI线程处理
		new ToolKits.SimpleAsyncTask<Integer>() {
			 @Override
			    protected void onPreExecute() {
			            super.onPreExecute();
			    }
			     
			     @Override
			      protected  Integer doInBackground(Void... params) {
			    		String strLog = "";
			    	 	strLog += "\nTesting the state of the remote IPC...";
			    		
			    		for (int i = 0; i < numChannel; i++)
			    		{
			    		    boolean zRet = false;
			    		    SDKDEV_VIRTUALCAMERA_STATE_INFO stDevVirtualCam = new SDKDEV_VIRTUALCAMERA_STATE_INFO();
			    		    stDevVirtualCam.nChannelID = i;
			    			zRet = INetSDK.QueryDevState(
			    								__LoginHandle,
			    								FinalVar.SDK_DEVSTATE_VIRTUALCAMERA,
			    								stDevVirtualCam, 
			    								3000);
			    			
			    			if ((stDevVirtualCam.szDeviceType[0] != 0) && (true == zRet))
			    			{
			    				strLog += "\nThe state of the remote ipc on channel ";
				    			strLog += i;
				    			strLog += " is ";
				    			
				    			if (CONNECT_STATE.CONNECT_STATE_UNCONNECT == stDevVirtualCam.emConnectState)
				    			{
				    				strLog += "offLine";
				    			}
				    			else if (CONNECT_STATE.CONNECT_STATE_CONNECTING == stDevVirtualCam.emConnectState)
				    			{
				    				strLog += "connceting";
				    			}
				    			else if (CONNECT_STATE.CONNECT_STATE_CONNECTED == stDevVirtualCam.emConnectState)
				    			{
				    				strLog += "onLine";
				    			}
				    			else
				    			{
				    				strLog += "stateError";
				    			} 
						    	ToolKits.writeLog(strLog);
						    	strLog = "";
			    			}
			    		}
			    		return 0;

			     }
			     @Override
			     protected void onPostExecute(Integer result) {
			    	
			      }  
		}.execute();
		

	}

	void TestReboot() {
		String strLog = "";

		strLog += "\nTesting RebootDev...";
		boolean zRet = INetSDK.RebootDev(__LoginHandle);
		strLog += zRet;

		ToolKits.showMessage(this, strLog);
	}

	void TestShutDown() {
		String strLog = "";

		strLog += "\nTesting ShutDownDev...";
		boolean zRet = INetSDK.ShutDownDev(__LoginHandle);
		strLog += zRet;

		ToolKits.showMessage(this, strLog);
	}

	void TestNewKeyBoard(int type) {
		NKB_PARAM stPram = new NKB_PARAM();
		stPram.bAddressCode = (byte) 0xFF;

		stPram.bKeyStatus = 0;
		boolean zRet = INetSDK.ControlDevice(__LoginHandle,
				type, stPram, 3000);
		stPram.bKeyStatus = 1;
		zRet = INetSDK.ControlDevice(__LoginHandle, type,
				stPram, 3000);
	}

	void TestControlDev() {
		boolean zRet;

		// zRet = INetSDK.ControlDevice(__LoginHandle,
		// CtrlType.SDK_CTRL_REBOOT, null, 3000);

		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_ENTER, null, 3000);
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_ESC, null, 3000);
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_UP, null, 3000);
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_DOWN, null, 3000);
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_LEFT, null, 3000);
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_RIGHT, null, 3000);
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_REC, null, 3000);
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_PLAY, null, 3000);
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_STOP, null, 3000);
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_SLOW, null, 3000);
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_FAST, null, 3000);
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_PREW, null, 3000);
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_NEXT, null, 3000);
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_FN1, null, 3000);
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_FN2, null, 3000);

		// new network keyboard
		// keyboard login
		NKB_PARAM stPram = new NKB_PARAM();
		stPram.bAddressCode = (byte) 0xFF;
		stPram.bKeyStatus = 1;
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_LOGIN, stPram, 3000);

		TestNewKeyBoard(CtrlType.SDK_KEYBOARD_PLAY);
		TestNewKeyBoard(CtrlType.SDK_KEYBOARD_ESC);
		TestNewKeyBoard(CtrlType.SDK_KEYBOARD_ENTER);
		TestNewKeyBoard(CtrlType.SDK_KEYBOARD_ONE);
		TestNewKeyBoard(CtrlType.SDK_KEYBOARD_NINE);
		TestNewKeyBoard(CtrlType.SDK_KEYBOARD_REC);

		stPram.bExtern1 = 5;
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_KEYBOARD_CHNNEL, stPram, 3000);
	}

	// 设置布撤防和旁路状态
	void IntelBrass() {
		boolean zRet;
	
		// 设置布撤防 [撤防不会失败;有报警源输入,布防失败;有报警联动，布防失败]
		CTRL_ARM_DISARM_PARAM stParam = new CTRL_ARM_DISARM_PARAM();
		stParam.bState = 0; 
		stParam.szDevPwd = new String("admin");
		stParam.emSceneMode = NET_SCENE_MODE.NET_SCENE_MODE_INDOOR;
		stParam.szDevID = null;
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_CTRL_ARMED, stParam, 3000);
    	if(zRet) {
    		ToolKits.writeLog("Set Disarm Succeed!");
    	} else {
    		ToolKits.writeErrorLog("Set Disarm Failed!" );
    	}

//		NET_CTRL_ALARM_SUBSYSTEM_SETACTIVE stSubsystem = new NET_CTRL_ALARM_SUBSYSTEM_SETACTIVE();
//		stSubsystem.nChannelId = 0;
//		stSubsystem.bActive = true;
//		zRet = INetSDK
//				.ControlDevice(__LoginHandle,
//						CtrlType.SDK_CTRL_ALARM_SUBSYSTEM_ACTIVE_SET,
//						stSubsystem, 3000);

    	// 设置旁路状态
		NET_CTRL_SET_BYPASS stBypass = new NET_CTRL_SET_BYPASS();
		stBypass.szDevPwd = new String("admin");
		stBypass.emMode = NET_BYPASS_MODE.NET_BYPASS_MODE_NORMAL;
		stBypass.pnLocal = new int[] { 0, 1 };
		stBypass.nLocalCount = stBypass.pnLocal.length;
		stBypass.pnExtended = new int[] { 0, 1 };
		stBypass.nExtendedCount = stBypass.pnExtended.length;
		zRet = INetSDK.ControlDevice(__LoginHandle,
				CtrlType.SDK_CTRL_SET_BYPASS, stBypass, 3000);
    	if(zRet) {
    		ToolKits.writeLog("Set Bypass Succeed!");
    	} else {
    		ToolKits.writeErrorLog("Set Bypass Failed!" );
    	}
    	
//		CTRL_ARM_DISARM_PARAM_EX stCtrlArmParamEx = new CTRL_ARM_DISARM_PARAM_EX();
//		stCtrlArmParamEx.stuIn.emState = NET_ALARM_MODE.NET_ALARM_MODE_ARMING;
//		stCtrlArmParamEx.stuIn.szDevPwd = "admin";
//		stCtrlArmParamEx.stuIn.emSceneMode = NET_SCENE_MODE.NET_SCENE_MODE_INDOOR;
//		zRet = INetSDK.ControlDevice(__LoginHandle,
//				CtrlType.SDK_CTRL_ARMED_EX, stCtrlArmParamEx, 5000);
//
//		CFG_ALARMOUT_INFO stCfgAlarmOutInfo = new CFG_ALARMOUT_INFO();
//		zRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_ALARMOUT,
//				stCfgAlarmOutInfo, __LoginHandle,
//				channelID, 10240);
//		zRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_ALARMOUT,
//				stCfgAlarmOutInfo, __LoginHandle,
//				channelID, 10240);
//
//		CFG_DEFENCE_AREA_DELAY_INFO stCfgDefenceAreaDelay = new CFG_DEFENCE_AREA_DELAY_INFO();
//		zRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_DEFENCE_AREA_DELAY,
//				stCfgDefenceAreaDelay, __LoginHandle, -1,
//				10240);
//		zRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_DEFENCE_AREA_DELAY,
//				stCfgDefenceAreaDelay, __LoginHandle, -1,
//				10240);
//
//		CFG_ALARMBELL_INFO stAlarmBell = new CFG_ALARMBELL_INFO();
//		zRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_ALARMBELL, stAlarmBell,
//				__LoginHandle,
//				channelID, 10240);
//		zRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_ALARMBELL, stAlarmBell,
//				__LoginHandle,
//				channelID, 10240);
//
//		ALARMCTRL_PARAM stOut = new ALARMCTRL_PARAM();
//		stOut.nAlarmNo = 0;
//		stOut.nAction = 1;
//		zRet = INetSDK.ControlDevice(__LoginHandle,
//				CtrlType.SDK_TRIGGER_ALARM_OUT, stOut, 3000);
//
//		NET_CTRL_ALARMBELL stBell = new NET_CTRL_ALARMBELL();
//		stBell.nChannelID = 0;
//		zRet = INetSDK.ControlDevice(__LoginHandle,
//				CtrlType.SDK_CTRL_START_ALARMBELL, stBell, 3000);
//		zRet = INetSDK.ControlDevice(__LoginHandle,
//				CtrlType.SDK_CTRL_STOP_ALARMBELL, stBell, 3000);
//
//		INetSDK.SetDVRMessCallBack(new TestMessageCallBack());
//		Integer stProVer = new Integer(0);
//		zRet = INetSDK.QueryDevState(__LoginHandle,
//				FinalVar.SDK_DEVSTATE_PROTOCAL_VER, stProVer, 1000);
//		if (zRet) {
//			if (stProVer.intValue() < 5) {
//				zRet = INetSDK.StartListen(__LoginHandle);
//				if (!zRet) {
//					ToolKits.showErrorMessage(this, "StartListen failed");
//					return;
//				}
//			} else {
//				zRet = INetSDK.StartListenEx(__LoginHandle);
//				if (!zRet) {
//					ToolKits.showErrorMessage(this, "StartListenEx failed");
//					return;
//				}
//			}
//
//			zRet = INetSDK.StopListen(__LoginHandle);
//		}
	}

	void MobileSubscribe() {	
		/* --------------SetMobileSubscribe接口测试----------------------*/
		String strRegisterID = "APA91bFMDi7xJ4zW2uebY8_2EdFEyJ4ew6_BAX2_ck-povlLgf2xs4JwIr7O8b-VAtJQVFHwj-k9DVuTZ3qKtGujeH25RGcP-vXxyd-p4_fQRkocc8h_hv0IzfDB80wQqrsOHJ517Cg-utvc41o1CuR7EmkvqCYMHA"; // mobile id registration on google GCM service
		String strDevId = "8507de90-58d4-44da-8248-3503225f4159"; // you can use this id to discriminate event msg come different device
		String strUser = "AIzaSyDXwrcImAjxWhNC4WYqygjXEDS-Z83hBMo"; // API Key, get from google service
		String strAuthServerAddr = "https://www.google.com/accounts/ClientLogin";
		String strPushServerAddr = "https://android.googleapis.com/gcm/send";
		String strMainAddr = "https://android.googleapis.com/gcm/send";

		NET_MOBILE_PUSH_NOTIFY stNotify = new NET_MOBILE_PUSH_NOTIFY(1);
		stNotify.emServerType = EM_MOBILE_SERVER_TYPE.EM_MOBILE_SERVER_TYPE_ANDROID;
		stNotify.nPeriodOfValidity = 500646880; // subscribing period, unit s
		
		System.arraycopy(strRegisterID.getBytes(), 0, stNotify.szRegisterID, 0, strRegisterID.getBytes().length);
		
		System.arraycopy(strDevId.getBytes(), 0, stNotify.szDevID, 0, strDevId.getBytes().length);
		
		System.arraycopy(strAuthServerAddr.getBytes(), 0, stNotify.szAuthServerAddr, 0, strAuthServerAddr.getBytes().length);
		stNotify.nAuthServerPort = 443;
		
		System.arraycopy(strPushServerAddr.getBytes(), 0, stNotify.szPushServerAddr, 0, strPushServerAddr.getBytes().length);
		stNotify.nPushServerPort = 443;
		
		System.arraycopy(strMainAddr.getBytes(), 0, stNotify.stuPushServerMain.szAddress, 0, strMainAddr.getBytes().length);
		stNotify.stuPushServerMain.nPort = 443;
		
		System.arraycopy(strUser.getBytes(), 0, stNotify.szUser, 0, strUser.getBytes().length);
		
		stNotify.pstuSubscribes[0].nCode = FinalVar.EVENT_ALARM_MOTIONDETECT; // which event need to be subcribe
		stNotify.pstuSubscribes[0].emSubCode = EM_EVENT_SUB_CODE.EM_EVENT_SUB_CODE_UNKNOWN;
		stNotify.pstuSubscribes[0].nChnNum = 1; // number of channels
		stNotify.pstuSubscribes[0].nIndexs[0] = 0; // channel index

		Integer stuErr = new Integer(0);
		Integer stuRes = new Integer(0);
		boolean zSet = INetSDK.SetMobileSubscribe(__LoginHandle, stNotify, stuErr, stuRes, 5000);
		if (!zSet) {
			ToolKits.writeErrorLog("SetMobileSubscribe failed");
		} else {
			ToolKits.writeLog("SetMobileSubscribe Succeed!");
		}
	}

	// 手机订阅推送信息
	void MobileSubscribeCfg() {
		/*-----------------SetMobileSubscribeCfg接口测试-----------------*/
		String strRegisterID = "Demo-APA91bFMDi7xJ4zW2uebY8_2EdFEyJ4ew6_BAX2_ck-povlLgf2xs4JwIr7O8b-VAtJQVFHwj-k9DVuTZ3qKtGujeH25RGcP-vXxyd-p4_fQRkocc8h_hv0IzfDB80wQqrsOHJ517Cg-utvc41o1CuR7EmkvqCYMHA"; // this is a mobile id registration on google GCM service
		String strAppID = "com.company.Demo";
		String strDevId = "8507de90-58d4-44da-8248-3503225f4159"; // you can use this id to discriminate event msg come different device
		String strUser = "AIzaSyDXwrcImAjxWhNC4WYqygjXEDS-Z83hBMo"; // API Key, get from google service
		String strAuthServerAddr = "https://www.google.com/accounts/ClientLogin";
		String strPushServerAddr = "https://android.googleapis.com/gcm/send";
		String strMainAddr = "https://android.googleapis.com/gcm/send";
		String strNumber = "110";

		NET_MOBILE_PUSH_NOTIFY_CFG stNotifyCfg = new NET_MOBILE_PUSH_NOTIFY_CFG(1);
		stNotifyCfg.emServerType = EM_MOBILE_SERVER_TYPE.EM_MOBILE_SERVER_TYPE_ANDROID;
		stNotifyCfg.nPeriodOfValidity = 500646880; // subscribing period, unit s
		
		System.arraycopy(strRegisterID.getBytes(), 0, stNotifyCfg.szRegisterID, 0, strRegisterID.getBytes().length);
		
		System.arraycopy(strAppID.getBytes(), 0, stNotifyCfg.szAppID, 0, strAppID.getBytes().length);
		
		System.arraycopy(strDevId.getBytes(), 0, stNotifyCfg.szDevID, 0, strDevId.getBytes().length);
		
		System.arraycopy(strAuthServerAddr.getBytes(), 0, stNotifyCfg.szAuthServerAddr, 0, strAuthServerAddr.getBytes().length);
		stNotifyCfg.nAuthServerPort = 443;
		
		System.arraycopy(strPushServerAddr.getBytes(), 0, stNotifyCfg.szPushServerAddr, 0, strPushServerAddr.getBytes().length);
		stNotifyCfg.nPushServerPort = 443;
		
		System.arraycopy(strMainAddr.getBytes(), 0, stNotifyCfg.stuPushServerMain.szAddress, 0, strMainAddr.getBytes().length);
		stNotifyCfg.stuPushServerMain.nPort = 443;
		
		System.arraycopy(strUser.getBytes(), 0, stNotifyCfg.szUser, 0, strUser.getBytes().length);
		
		if(stNotifyCfg.nSubScribeMax > 0){
			stNotifyCfg.pstuSubscribes[0].nCode = FinalVar.EVENT_ALARM_MOTIONDETECT; // which event need to be subcribe
			stNotifyCfg.pstuSubscribes[0].emSubCode = EM_EVENT_SUB_CODE.EM_EVENT_SUB_CODE_UNKNOWN;
			stNotifyCfg.pstuSubscribes[0].nChnNum = 1; // number of channels
			stNotifyCfg.pstuSubscribes[0].nIndexs[0] = 1; // channel index
			System.arraycopy(strNumber.getBytes(), 0, stNotifyCfg.pstuSubscribes[0].szNumber, 0, strNumber.getBytes().length);
		}

		Integer stuErr = new Integer(0);
		Integer stuRes = new Integer(0);
		boolean zSet = INetSDK.SetMobileSubscribeCfg(__LoginHandle, stNotifyCfg, stuErr, stuRes, 5000);
		if (!zSet) {
			ToolKits.writeErrorLog("SetMobileSubscribeCfg failed");
		} 
	}

	// 删除手机订阅推送信息
	void MobileSubscribeDelete() {
		/*String strRegisterID = "szRegisterID"; // this is a mobile id registration on google GCM service
		String strAppID = "com.company.Demo";
		String strAuthServerAddr = "https://www.google.com/accounts/ClientLogin";
		String strPushServerAddr = "https://cellphonepush.quickddns.com/gcm/send";
		String strMainAddr = "https://android.googleapis.com/gcm/send";
		String strRedirectAddr = ""; // not used
		String strDevName = "Device01_NVR"; // device name
		String strDevID = "468101c5"; // device id
		String strUser = "AIzaSyDXwrcImAjxWhNC4WYqygjXEDS-Z83hBMo"; // API Key get from google service
		String strPsw = ""; // 
		String strCertificate = ""; //is used just on iOS 
		String strSecretKey = ""; // not used
		
		Integer stuErr = new Integer(0);
		Integer stuRes = new Integer(0);

		NET_MOBILE_PUSH_NOTIFY_CFG stNotifyCfg = new NET_MOBILE_PUSH_NOTIFY_CFG(0);
		System.arraycopy(strRegisterID.getBytes(), 0, stNotifyCfg.szRegisterID, 0, strRegisterID.getBytes().length);
		System.arraycopy(strAppID.getBytes(), 0, stNotifyCfg.szAppID, 0, strAppID.getBytes().length);
		stNotifyCfg.emServerType = EM_MOBILE_SERVER_TYPE.EM_MOBILE_SERVER_TYPE_ANDROID;
		stNotifyCfg.emPushGatewayType = EM_PUSH_GATEWAY_TYPE.EM_PUSH_GATEWAY_TYPE_APPLE_PUSH;
		stNotifyCfg.nPeriodOfValidity = 500646880;
		System.arraycopy(strAuthServerAddr.getBytes(), 0, stNotifyCfg.szAuthServerAddr, 0, strAuthServerAddr.getBytes().length);
		stNotifyCfg.nAuthServerPort = 443;
		System.arraycopy(strPushServerAddr.getBytes(), 0, stNotifyCfg.szPushServerAddr, 0, strPushServerAddr.getBytes().length);
		stNotifyCfg.nPushServerPort = 443;
		System.arraycopy(strMainAddr.getBytes(), 0, stNotifyCfg.stuPushServerMain.szAddress, 0, strMainAddr.getBytes().length);
		stNotifyCfg.stuPushServerMain.nPort = 443;
		System.arraycopy(strRedirectAddr.getBytes(), 0, stNotifyCfg.stuPushRedirectServer.szAddress, 0, strRedirectAddr.getBytes().length);
		stNotifyCfg.stuPushRedirectServer.nPort = 2006;
		System.arraycopy(strDevName.getBytes(), 0, stNotifyCfg.szDevName, 0, strDevName.getBytes().length);
		System.arraycopy(strDevID.getBytes(), 0, stNotifyCfg.szDevID, 0, strDevID.getBytes().length);
		System.arraycopy(strUser.getBytes(), 0, stNotifyCfg.szUser, 0, strUser.getBytes().length);
		System.arraycopy(strPsw.getBytes(), 0, stNotifyCfg.szPassword, 0, strPsw.getBytes().length);
		System.arraycopy(strCertificate.getBytes(), 0, stNotifyCfg.szCertificate, 0, strCertificate.getBytes().length);
		System.arraycopy(strSecretKey.getBytes(), 0, stNotifyCfg.szSecretKey, 0, strSecretKey.getBytes().length);
		
		boolean zSet = INetSDK.SetMobileSubscribeCfg(__LoginHandle, stNotifyCfg, stuErr, stuRes, 5000);
		if (!zSet) {
			ToolKits.writeErrorLog("SetMobileSubscribeCfg failed");
		}*/
		
		/*-----------------DelMobileSubscribe-----------------------*/
		String strRegisterID = "szRegisterID";
		NET_MOBILE_PUSH_NOTIFY_DEL stIn = new NET_MOBILE_PUSH_NOTIFY_DEL();
		System.arraycopy(strRegisterID.getBytes(), 0, stIn.szRegisterID, 0, strRegisterID.getBytes().length);
		NET_OUT_DELETECFG stOut = new NET_OUT_DELETECFG();
		boolean zDel = INetSDK.DelMobileSubscribe(
				__LoginHandle, stIn, stOut, 5000);
		if (!zDel) {
			ToolKits.writeErrorLog("DelMobileSubscribe failed");
		} else {
			ToolKits.writeLog("DelMobilePushNotify Succeed!");
		}
	}
	
	void MobilePushNotify() {
		String strRegisterID = "szRegisterID";
		String strAppID = "szAppID";
		String strAuthServerAddr = "szAuthServerAddr";
		String strPushServerAddr = "szPushServerAddr";
		String strMainAddr = "stuPushServerMain.szAddress";
		String strRedirectAddr = "stuPushRedirectServer.szAddress";
		String strDevName = "szDevName";
		String strDevID = "szDevID";
		String strUser = "admin";
		String strPsw = "admin";
		String strCertificate = "szCertificate";
		String strSecretKey = "szSecretKey";
		String strNumber = "12345678901";
		String strSound = "music.caf";
		Integer stuErr = new Integer(0);
		Integer stuRes = new Integer(0);

		NET_MOBILE_PUSH_NOTIFY stNotify = new NET_MOBILE_PUSH_NOTIFY(2);
		System.arraycopy(strRegisterID.getBytes(), 0, stNotify.szRegisterID, 0,
				strRegisterID.getBytes().length);
		stNotify.emServerType = EM_MOBILE_SERVER_TYPE.EM_MOBILE_SERVER_TYPE_ANDROID;
		stNotify.nPeriodOfValidity = 100;
		System.arraycopy(strAuthServerAddr.getBytes(), 0,
				stNotify.szAuthServerAddr, 0,
				strAuthServerAddr.getBytes().length);
		stNotify.nAuthServerPort = 2000;
		System.arraycopy(strPushServerAddr.getBytes(), 0,
				stNotify.szPushServerAddr, 0,
				strPushServerAddr.getBytes().length);
		stNotify.nPushServerPort = 2002;
		System.arraycopy(strMainAddr.getBytes(), 0,
				stNotify.stuPushServerMain.szAddress, 0,
				strMainAddr.getBytes().length);
		stNotify.stuPushServerMain.nPort = 2004;
		System.arraycopy(strRedirectAddr.getBytes(), 0,
				stNotify.stuPushRedirectServer.szAddress, 0,
				strRedirectAddr.getBytes().length);
		stNotify.stuPushRedirectServer.nPort = 2006;
		System.arraycopy(strDevName.getBytes(), 0, stNotify.szDevName, 0,
				strDevName.getBytes().length);
		System.arraycopy(strDevID.getBytes(), 0, stNotify.szDevID, 0,
				strDevID.getBytes().length);
		System.arraycopy(strUser.getBytes(), 0, stNotify.szUser, 0,
				strUser.getBytes().length);
		System.arraycopy(strPsw.getBytes(), 0, stNotify.szPassword, 0,
				strPsw.getBytes().length);
		System.arraycopy(strCertificate.getBytes(), 0, stNotify.szCertificate,
				0, strCertificate.getBytes().length);
		System.arraycopy(strSecretKey.getBytes(), 0, stNotify.szSecretKey, 0,
				strSecretKey.getBytes().length);
		stNotify.pstuSubscribes[0].nCode = FinalVar.EVENT_ALARM_MOTIONDETECT;
		stNotify.pstuSubscribes[0].emSubCode = EM_EVENT_SUB_CODE.EM_EVENT_SUB_CODE_UNKNOWN;
		stNotify.pstuSubscribes[0].nChnNum = 2;
		stNotify.pstuSubscribes[0].nIndexs[0] = 1;
		stNotify.pstuSubscribes[0].nIndexs[1] = 2;
		stNotify.pstuSubscribes[1].nCode = FinalVar.EVENT_ALARM_MOTIONDETECT;
		stNotify.pstuSubscribes[1].emSubCode = EM_EVENT_SUB_CODE.EM_EVENT_SUB_CODE_UNKNOWN;
		stNotify.pstuSubscribes[1].nChnNum = 1;
		stNotify.pstuSubscribes[1].nIndexs[0] = 3;
		boolean zSet = INetSDK.SetMobilePushNotify(
				__LoginHandle, stNotify, stuErr, stuRes,
				5000);
		if (!zSet) {
			ToolKits.writeErrorLog("SetMobilePushNotify failed");
		}

		NET_MOBILE_PUSH_NOTIFY_CFG_DEL stInCfg = new NET_MOBILE_PUSH_NOTIFY_CFG_DEL();
		System.arraycopy(strRegisterID.getBytes(), 0, stInCfg.szRegisterID, 0,
				strRegisterID.getBytes().length);
		System.arraycopy(strAppID.getBytes(), 0, stInCfg.szAppID, 0,
				strAppID.getBytes().length);
		NET_OUT_DELETECFG stOutCfg = new NET_OUT_DELETECFG();
		boolean zDel = INetSDK.DelMobilePushNotifyCfg(__LoginHandle,
				stInCfg, stOutCfg, 5000);
		if (!zDel) {
			ToolKits.writeErrorLog("DelMobilePushNotifyCfg failed");
		}
	}

	// 查询无线网络接入点信息
	void GetDeviceInfoAll() {
		boolean ret = false;
		NET_IN_WLAN_ACCESSPOINT stInWlan = new NET_IN_WLAN_ACCESSPOINT();
		NET_OUT_WLAN_ACCESSPOINT stOutWlan = new NET_OUT_WLAN_ACCESSPOINT();
		ret = INetSDK.QueryDevInfo(__LoginHandle,FinalVar.NET_QUERY_WLAN_ACCESSPOINT, stInWlan, stOutWlan, null, 5000);
		if(ret) {
			ToolKits.writeLog("---------------  nCount :  " + stOutWlan.nCount);
		} else {
			ToolKits.writeErrorLog("----------------------Failed");
		}
//		boolean zRet;
//
//		SDK_IN_MATRIX_GET_CAMERAS stCamerasIn = new SDK_IN_MATRIX_GET_CAMERAS();
//		SDK_OUT_MATRIX_GET_CAMERAS stCamerasOut = new SDK_OUT_MATRIX_GET_CAMERAS(50);
//		zRet = INetSDK.MatrixGetCameras(__LoginHandle, stCamerasIn, stCamerasOut, 5000);
//		if (!zRet) {
//			ToolKits.writeErrorLog("MatrixGetCameras failed");
//		} else {
//			int nCount = stCamerasOut.nRetCameraCount < stCamerasOut.nMaxCameraCount ? stCamerasOut.nRetCameraCount : stCamerasOut.nMaxCameraCount;
//			for (int i = 0; i < nCount; i++) {
//				ToolKits.writeLog(stCamerasOut.pstuCameras[i].nUniqueChannel
//						+ ": "
//						+ new String(stCamerasOut.pstuCameras[i].szDevID)
//						+ " "
//						+ new String(
//								stCamerasOut.pstuCameras[i].stuRemoteDevice.szDevType)
//						+ " "
//						+ new String(
//								stCamerasOut.pstuCameras[i].stuRemoteDevice.szDevName)
//						+ " "
//						+ new String(
//								stCamerasOut.pstuCameras[i].stuRemoteDevice.szIp)
//						+ " "
//						+ new String(
//								stCamerasOut.pstuCameras[i].stuRemoteDevice.szUser));
//			}
//		}
//
//		NET_IN_GET_CAMERA_STATEINFO getIn = new NET_IN_GET_CAMERA_STATEINFO();
//		getIn.bGetAllFlag = true;
//		NET_OUT_GET_CAMERA_STATEINFO getOut = new NET_OUT_GET_CAMERA_STATEINFO(256);
//		zRet = INetSDK.QueryDevInfo(__LoginHandle,
//				FinalVar.NET_QUERY_GET_CAMERA_STATE, getIn, getOut, null, 5000);
//		if (!zRet) {
//			ToolKits.writeErrorLog("QueryDevInfo, NET_QUERY_GET_CAMERA_STATE failed");
//		}

		
//		NET_IN_GET_DEVICE_INFO_ALL stDevInfoAllIn = new NET_IN_GET_DEVICE_INFO_ALL();
//		NET_OUT_GET_DEVICE_INFO_ALL stDevInfoAllOut = new NET_OUT_GET_DEVICE_INFO_ALL(20);
//		zRet = INetSDK.QueryDevInfo(__LoginHandle,
//				FinalVar.NET_QUERY_DEV_REMOTE_DEVICE_INFO_ALL, stDevInfoAllIn,
//				stDevInfoAllOut, null, 5000);
//		if (!zRet) {
//			ToolKits.writeErrorLog("QueryDevInfo, NET_QUERY_DEV_REMOTE_DEVICE_INFO_ALL failed");
//		} else {
//			for (int i = 0; i < stDevInfoAllOut.pstuInfo.length; i++) {
//				ToolKits.writeLog((i + 1) + ": "
//						+ new String(stDevInfoAllOut.pstuInfo[i].szDevType));
//			}
//		}
 

//		NET_IN_GET_DEVICE_INFO stDevInfoIn = new NET_IN_GET_DEVICE_INFO();
//		System.arraycopy("172.11.9.17".getBytes(), 0,
//				stDevInfoIn.szAttributeIP, 0, "172.11.9.17".getBytes().length);
//		stDevInfoIn.nAttributePort = 37777;
//		System.arraycopy("admin".getBytes(), 0,
//				stDevInfoIn.szAttributeUsername, 0, "admin".getBytes().length);
//		System.arraycopy("admin".getBytes(), 0,
//				stDevInfoIn.szAttributePassword, 0, "admin".getBytes().length);
//		System.arraycopy("Dahua".getBytes(), 0,
//				stDevInfoIn.szAttributeManufacturer, 0,
//				"Dahua".getBytes().length);
//		NET_OUT_GET_DEVICE_INFO stDevInfoOut = new NET_OUT_GET_DEVICE_INFO();
//		zRet = INetSDK.QueryDevInfo(__LoginHandle,
//				FinalVar.NET_QUERY_DEV_REMOTE_DEVICE_INFO, stDevInfoIn,
//				stDevInfoOut, null, 5000);
//		if (!zRet) {
//			ToolKits.writeErrorLog("QueryDevInfo, NET_QUERY_DEV_REMOTE_DEVICE_INFO failed");
//		}
	}

	public class Tets_CB_fCameraStateCallBack implements
			CB_fCameraStateCallBack {
		@Override
		public void invoke(long lLoginID, long lAttachHandle,
				final NET_CB_CAMERASTATE pBuf) {
			ToolKits.writeLog("lLoginID = " + lLoginID + ", lAttachHandle = "
					+ lAttachHandle + ", nChannel = " + pBuf.nChannel
					+ ", emConnectState = " + pBuf.emConnectState);
		}
	}

	// 订阅摄像头状态
	void AttachCameraState() {
		NET_IN_CAMERASTATE stIn = new NET_IN_CAMERASTATE(1);
		stIn.pChannels[0] = -1;
		stIn.cbCamera = new Tets_CB_fCameraStateCallBack();
		NET_OUT_CAMERASTATE stOut = new NET_OUT_CAMERASTATE();
		long lAttach = INetSDK.AttachCameraState(
				__LoginHandle, stIn, stOut, 3000);
		ToolKits.writeLog("AttachCameraState lAttach: " +  lAttach);
		try {
			Thread.sleep(30000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean zRet = INetSDK.DetachCameraState(lAttach);
		ToolKits.writeLog("DetachCameraState zRet: " + zRet);
	}

	// 设置设备地址信息
	public void SetDevicePosition() {
		String strLog = "";

		NET_IN_SET_DEVICE_POSITION stuIn = new NET_IN_SET_DEVICE_POSITION();
		String strPos = "hangzhou-china";
		System.arraycopy(strPos.getBytes(), 0, stuIn.szPosition, 0,
				strPos.length());

		NET_OUT_SET_DEVICE_POSITION stuOut = new NET_OUT_SET_DEVICE_POSITION();

		if (INetSDK.SetDevicePosition(__LoginHandle, stuIn,
				stuOut, 5000)) {
			// set ok
			strLog += "Set device position to ";
			strLog += stuIn.szPosition;
			strLog += " ok";
			ToolKits.writeLog(strLog);
		} else {
			// set failed
			int nErr = INetSDK.GetLastError();
			strLog += "Set device position to ";
			strLog += stuIn.szPosition;
			strLog += " err";
			ToolKits.writeLog(strLog);
		}
	}

	// 获取设备地址信息
	public void GetDevicePosition() {
		NET_IN_GET_DEVICE_POSITION pstuIn = new NET_IN_GET_DEVICE_POSITION();
		NET_OUT_GET_DEVICE_POSITION pstuOut = new NET_OUT_GET_DEVICE_POSITION();

		if (INetSDK.GetDevicePosition(__LoginHandle, pstuIn,
				pstuOut, 5000)) {
			// get ok
		} else {
			// get failed
			int nErr = INetSDK.GetLastError();
		}
	}

	// 按照时间类型抓图
	public void SnapPictureByEvent() {
		NET_IN_SNAP_BY_EVENT pstuIn = new NET_IN_SNAP_BY_EVENT();
		NET_OUT_SNAP_BY_EVENT pstuOut = new NET_OUT_SNAP_BY_EVENT();

		if (INetSDK.SnapPictureByEvent(__LoginHandle,
				pstuIn, pstuOut, 5000)) {
			// snap ok
		} else {
			// snap failed
			int nErr = INetSDK.GetLastError();
		}
	}

	public class DevicePicUpload implements CB_fAnalyzerDataCallBack {
		String EventMsg = new String("");
		@Override
		public void invoke(long lAnalyzerHandle, int dwAlarmType,
				Object alarmInfo, byte pBuffer[], int dwBufSize,
				int nSequence, int reserved) {
			if(dwAlarmType == FinalVar.EVENT_IVS_TRAFFICGATE) //交通卡口事件
			{
				DEV_EVENT_TRAFFICGATE_INFO  trafficInfo = (DEV_EVENT_TRAFFICGATE_INFO)alarmInfo;
				ToolKits.writeLog("DevicePicUpload EVENT_IVS_TRAFFICGATE plate color:： " + trafficInfo.stuObject.rgbaMainColor + "plate type: " + new String(trafficInfo.szVehicleType)
				 );
				EventMsg = "Plate Number " + new String(trafficInfo.stuObject.szText).trim()
						+ "; Auto Logos " + new String(trafficInfo.stuVehicle.szText).trim();

				ToolKits.writeLog("Year: " + trafficInfo.UTC.dwYear + "mon: " + trafficInfo.UTC.dwMonth + "day: " + trafficInfo.UTC.dwDay +
				"hour: " + trafficInfo.UTC.dwHour);

				String strFileName = "";
					//保存名字可以自己修改
					if (ToolKits.createFile("/mnt/sdcard/NetSDK/", "trafficgate.jpg")) {
						strFileName = "/mnt/sdcard/NetSDK/trafficgate.jpg";
					}
				if ("" != strFileName) {
					FileOutputStream fileStream;
		    		try {
		    			fileStream = new FileOutputStream(strFileName, true);
		    			fileStream.write(pBuffer, 0, dwBufSize);
		    			fileStream.close();
		    		} catch (Exception e) {
		    			e.printStackTrace();
		    		}
				}
			}

			if(dwAlarmType == FinalVar.EVENT_IVS_TRAFFICJUNCTION) //交通路口
			{
				DEV_EVENT_TRAFFICJUNCTION_INFO  trafficInfo = (DEV_EVENT_TRAFFICJUNCTION_INFO)alarmInfo;
				ToolKits.writeLog("DevicePicUpload EVENT_IVS_TRAFFICJUNCTION plate color:： " + new String(trafficInfo.stTrafficCar.szPlateColor).trim() + "plate type: " + new String(trafficInfo.stTrafficCar.szPlateType).trim()
				+ " vehicle number: " + new String(trafficInfo.stTrafficCar.szPlateNumber).trim());

				ToolKits.writeLog("Year: " + trafficInfo.UTC.dwYear + "mon: " + trafficInfo.UTC.dwMonth + "day: " + trafficInfo.UTC.dwDay +
				"hour: " + trafficInfo.UTC.dwHour);

				String strFileName = "";
					if (ToolKits.createFile("/mnt/sdcard/NetSDK/", "trafficJunction.jpg")) {
						strFileName = "/mnt/sdcard/NetSDK/trafficJunction.jpg";
					}
				if ("" != strFileName) {
					FileOutputStream fileStream;
		    		try {
		    			fileStream = new FileOutputStream(strFileName, true);
		    			fileStream.write(pBuffer, 0, dwBufSize);
		    			fileStream.close();
		    		} catch (Exception e) {
		    			e.printStackTrace();
		    		}
				}
			}

			if(dwAlarmType == FinalVar.EVENT_IVS_TRAFFIC_MANUALSNAP) //手动抓拍
			{
				DEV_EVENT_TRAFFIC_MANUALSNAP_INFO  trafficInfo = (DEV_EVENT_TRAFFIC_MANUALSNAP_INFO)alarmInfo;
				ToolKits.writeLog("DevicePicUpload DEV_EVENT_TRAFFIC_MANUALSNAP_INFO plate color:： " + new String(trafficInfo.stTrafficCar.szPlateColor) + "plate type: " + new String(trafficInfo.stTrafficCar.szPlateType)
				+ "Vehicle color:" + trafficInfo.stuVehicle.rgbaMainColor + " vehicle number: " + new String(trafficInfo.stTrafficCar.szPlateNumber));

				ToolKits.writeLog("Year: " + trafficInfo.UTC.dwYear + "mon: " + trafficInfo.UTC.dwMonth + "day: " + trafficInfo.UTC.dwDay +
				"hour: " + trafficInfo.UTC.dwHour);

				String strFileName = "";
					if (ToolKits.createFile("/mnt/sdcard/NetSDK/", "trafficManualSnap.jpg")) {
						strFileName = "/mnt/sdcard/NetSDK/trafficManualSnap.jpg";
					}
				if ("" != strFileName) {
					FileOutputStream fileStream;
		    		try {
		    			fileStream = new FileOutputStream(strFileName, true);
		    			fileStream.write(pBuffer, 0, dwBufSize);
		    			fileStream.close();
		    		} catch (Exception e) {
		    			e.printStackTrace();
		    		}
				}
			}

			if(dwAlarmType == FinalVar.EVENT_IVS_TRAFFIC_PEDESTRAINRUNREDLIGHT) //交通-行人闯红灯事件
			{
				DEV_EVENT_TRAFFIC_PEDESTRAINRUNREDLIGHT_INFO trafficInfo = (DEV_EVENT_TRAFFIC_PEDESTRAINRUNREDLIGHT_INFO)alarmInfo;

				ToolKits.writeLog("交通-行人闯红灯事件， nChannelID :" + trafficInfo.nChannelID + "\n szName:" + new String(trafficInfo.szName).trim() + "\n UTC:" + trafficInfo.UTC.toStringTime() +
								"\n nLane:" + trafficInfo.nLane);
				ToolKits.writeLog("nDriversNum :" + trafficInfo.stCommInfo.nDriversNum);
				ToolKits.writeLog("emNTPStatus :" + trafficInfo.stCommInfo.emNTPStatus);
				ToolKits.writeLog("nAttachmentNum :" + trafficInfo.stCommInfo.nAttachmentNum);

				ToolKits.writeLog( "szObjectType 0 :" + new String(trafficInfo.stCommInfo.pstDriversInfo[0].szObjectType).trim() +
							"\n emAttachmentType 0 :" + trafficInfo.stCommInfo.stuAttachment[0].emAttachmentType);
				ToolKits.writeLog( "szObjectType 1 :" + new String(trafficInfo.stCommInfo.pstDriversInfo[1].szObjectType).trim() +
						"\n emAttachmentType 1 :" + trafficInfo.stCommInfo.stuAttachment[1].emAttachmentType);

				String strFileName = "";
				if (ToolKits.createFile("/mnt/sdcard/NetSDK/", "trafficPedestrain.jpg")) {
					strFileName = "/mnt/sdcard/NetSDK/trafficPedestrain.jpg";
				}
				if ("" != strFileName) {
					FileOutputStream fileStream;
					try {
						fileStream = new FileOutputStream(strFileName, true);
						fileStream.write(pBuffer, 0, dwBufSize);
						fileStream.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			if(dwAlarmType == FinalVar.EVENT_IVS_FACERECOGNITION) //人脸识别事件
			{
				DEV_EVENT_FACERECOGNITION_INFO trafficInfo = (DEV_EVENT_FACERECOGNITION_INFO)alarmInfo;
				ToolKits.writeLog("人脸识别事件");
				ToolKits.writeLog(trafficInfo.toString());
				ToolKits.writeLog(trafficInfo.stuObject.toString() + trafficInfo.stuObject.stPicInfo.toString());
				for(int i=0; i<trafficInfo.nCandidateNum; i++) {
					ToolKits.writeLog(trafficInfo.stuCandidates[i].toString()
									  + trafficInfo.stuCandidates[i].stPersonInfo.toString()
									  + trafficInfo.stuCandidates[i].stuSceneImage.toString());
				}
				ToolKits.writeLog(trafficInfo.stuGlobalScenePicInfo.toString() + trafficInfo.stuGlobalScenePicInfo.stuPoint.toString());
				ToolKits.writeLog(trafficInfo.stuIntelliCommInfo.toString());
				ToolKits.writeLog(trafficInfo.stuFaceData.toString());

				String strFileName = "";
				if (ToolKits.createFile("/mnt/sdcard/NetSDK/", "trafficFaceRecognition.jpg")) {
					strFileName = "/mnt/sdcard/NetSDK/trafficFaceRecognition.jpg";
				}
				if ("" != strFileName) {
					FileOutputStream fileStream;
					try {
						fileStream = new FileOutputStream(strFileName, true);
						fileStream.write(pBuffer, 0, dwBufSize);
						fileStream.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			if(dwAlarmType == FinalVar.EVENT_IVS_FACEDETECT) //人脸检测事件
			{
				DEV_EVENT_FACEDETECT_INFO trafficInfo = (DEV_EVENT_FACEDETECT_INFO)alarmInfo;
				ToolKits.writeLog("人脸检测事件");
				ToolKits.writeLog("事件名称：" + new String(trafficInfo.szName).trim());
				ToolKits.writeLog("通道号:" + trafficInfo.nChannelID);
				ToolKits.writeLog("事件发生的时间:" + trafficInfo.UTC.toStringTime());

				// 大图
				String strFileName = "";
				if (ToolKits.createFile("/mnt/sdcard/NetSDK/", "trafficFaceDetect.jpg")) {
					strFileName = "/mnt/sdcard/NetSDK/trafficFaceDetect.jpg";
				}
				if ("" != strFileName) {
					FileOutputStream fileStream;
					try {
						fileStream = new FileOutputStream(strFileName, true);
						fileStream.write(pBuffer, 0, dwBufSize);
						fileStream.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// 小图
				if (ToolKits.createFile("/mnt/sdcard/NetSDK/", "trafficFaceDetectSmall.jpg")) {
					strFileName = "/mnt/sdcard/NetSDK/trafficFaceDetectSmall.jpg";
				}
				if ("" != strFileName) {
					FileOutputStream fileStream;
					try {
						fileStream = new FileOutputStream(strFileName, true);
						fileStream.write(pBuffer, trafficInfo.stuObject.stPicInfo.dwOffSet, trafficInfo.stuObject.stPicInfo.dwFileLenth);
						fileStream.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			if(dwAlarmType == FinalVar.EVENT_IVS_CITIZEN_PICTURE_COMPARE) // 人证比对事件
			{
				DEV_EVENT_CITIZEN_PICTURE_COMPARE_INFO citizenInfo = (DEV_EVENT_CITIZEN_PICTURE_COMPARE_INFO)alarmInfo;
				ToolKits.writeLog("人证比对事件");
				ToolKits.writeLog(citizenInfo.toString());

				// 抓拍图片
				String strFileName = "";
				if (ToolKits.createFile("/mnt/sdcard/NetSDK/", "SnapPic.jpg")) {
					strFileName = "/mnt/sdcard/NetSDK/SnapPic.jpg";
					ToolKits.writeLog("strFileName SnapPic :" + strFileName);
				}
				if ("" != strFileName) {
					FileOutputStream fileStream1;
					try {
						fileStream1 = new FileOutputStream(strFileName, true);
						fileStream1.write(pBuffer, citizenInfo.stuImageInfo[0].dwOffSet, citizenInfo.stuImageInfo[0].dwFileLenth);
						fileStream1.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// 身份证照片
				strFileName = "";
				if (ToolKits.createFile("/mnt/sdcard/NetSDK/", "CitizenPic.jpg")) {
					strFileName = "/mnt/sdcard/NetSDK/CitizenPic.jpg";
					ToolKits.writeLog("strFileName CitizenPic :" + strFileName);
				}
				if ("" != strFileName) {
					FileOutputStream fileStream;
					try {
						fileStream = new FileOutputStream(strFileName, true);
						fileStream.write(pBuffer, citizenInfo.stuImageInfo[1].dwOffSet, citizenInfo.stuImageInfo[1].dwFileLenth);
						fileStream.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			if(dwAlarmType == FinalVar.EVENT_IVS_POS_EXCHANGE) // POS机交易事件
			{
				DEV_EVENT_POS_EXCHANGE_INFO trafficInfo = (DEV_EVENT_POS_EXCHANGE_INFO)alarmInfo;
				ToolKits.writeLog("POS机交易事件");
				ToolKits.writeLog(trafficInfo.toString());

				ToolKits.writeLog("通道号:" + trafficInfo.nChannelID);
				ToolKits.writeLog("(0:脉冲 1:开始 2:停止):" + trafficInfo.nAction);
				ToolKits.writeLog("事件名称:" + new String(trafficInfo.szName).trim());
				ToolKits.writeLog("事件发生的时间:" + trafficInfo.UTC.toStringTime());
				ToolKits.writeLog("交易号:" + new String(trafficInfo.szDealNum).trim());
				ToolKits.writeLog("卡号:" + new String(trafficInfo.szCardID).trim());
				ToolKits.writeLog("商品清单数量:" + trafficInfo.nItemListCount);

				for(int i = 0; i < trafficInfo.nItemListCount; i++) {
					ToolKits.writeLog("商品名称:" + new String(trafficInfo.pstItemList[i].szItemName).trim());
					ToolKits.writeLog("商品单价:" + trafficInfo.pstItemList[i].dbPrice);
					ToolKits.writeLog("数量:" + trafficInfo.pstItemList[i].dbQuantity);
					ToolKits.writeLog("总价:" + trafficInfo.pstItemList[i].dbAmount);
					ToolKits.writeLog("计价单位:" + new String(trafficInfo.pstItemList[i].szUnit).trim());
				}
				ToolKits.writeLog("字体大小:" + trafficInfo.nFontSize);

				String strFileName = "";
				if (ToolKits.createFile("/mnt/sdcard/NetSDK/", "PosExchange.jpg")) {
					strFileName = "/mnt/sdcard/NetSDK/PosExchange.jpg";
				}
				if ("" != strFileName) {
					FileOutputStream fileStream;
					try {
						fileStream = new FileOutputStream(strFileName, true);
						fileStream.write(pBuffer, 0, dwBufSize);
						fileStream.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			return;
		}
	}

	// 智能订阅
	public void RealLoadPicEx() {
		
		new ToolKits.SimpleAsyncTask<Integer>() {
			 @Override
			    protected void onPreExecute() {
			            super.onPreExecute();
			    }
			     
			     @Override
			      protected  Integer doInBackground(Void... params) {


			    	 DevicePicUpload cbProc = new DevicePicUpload();
		        		lanalyHandle = INetSDK.RealLoadPictureEx(
		        				__LoginHandle, 0, FinalVar.EVENT_IVS_ALL, true, cbProc, 0);
		        		if (lanalyHandle != 0) {
		        			// start listen upload pic and info ok
		        				ToolKits.writeLog(" 手动抓拍触发0通道");
//		        			    MANUAL_SNAP_PARAMETER stuSanpParam = new MANUAL_SNAP_PARAMETER();
//		        		  	    stuSanpParam.nChannel = 0;
//		        			    boolean bret  = INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_MANUAL_SNAP, stuSanpParam, 3000);
//		        				if(!bret)
//		        				{
//		        					ToolKits.writeLog("手动抓拍失败");
//		        				}
		        				
		        			try {
		        				Thread.sleep(10);
		        			} catch (Exception e) {
		        				e.printStackTrace();
		        			}
		        			//INetSDK.StopLoadPic(lHandle);
		        		} else {
		        			ToolKits.writeErrorLog("RealLoadPictureEx Failed.");
		        		}
		        		
			        	return 0;
			     }
			     @Override
			     protected void onPostExecute(Integer result) {
			    	 ToolKits.writeLog("SimpleAsyncTask result: " + result);
			      }  
		}.execute();
		
	}

	public class TestfGpsInfoCallBack implements CB_fSubcribeGPSCallBack {
		@Override
		public void invoke(long lLoginID, GPS_Info gpsInfo) {
			ToolKits.writeLog("TestfGpsInfoCallBack");
		}
	}

	// GPS信息订阅
	public void SubcribeGPS() {
		TestfGpsInfoCallBack stCb = new TestfGpsInfoCallBack();
		INetSDK.SetSubcribeGPSCallBack(stCb);

		// 订阅
		INetSDK.SubcribeGPS(__LoginHandle, true, 3000, 3);

		try {
			Thread.sleep(10);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 取消订阅
		INetSDK.SubcribeGPS(__LoginHandle, false, 0, 0);
	}

	// 车载相关配置,北京公交使用
	public void AboutVehicleConfig() {

		int nChannel = 0;
		SDKDEV_ABOUT_VEHICLE_CFG stOut[] = new SDKDEV_ABOUT_VEHICLE_CFG[1];
		stOut[0] = new SDKDEV_ABOUT_VEHICLE_CFG();
		int nRetLen = 0;
		boolean bRet = INetSDK.GetDevConfig(__LoginHandle,
				FinalVar.SDK_DEV_ABOUT_VEHICLE_CFG, nChannel, stOut, nRetLen,
				3000);
		if (!bRet) {
			return;
		}

		bRet = INetSDK.SetDevConfig(__LoginHandle,
				FinalVar.SDK_DEV_ABOUT_VEHICLE_CFG, nChannel, stOut, 5000);
		if (bRet) {
			//
		} else {
			//
		}
	}

	public class TestfBurnFileCallBack implements CB_fBurnFileCallBack {
		@Override
		public void invoke(long lLoginID, long lUploadHandle, int nTotalSize,
				int nSendSize) {
			ToolKits.writeLog("TestfBurnFileCallBack");
		}
	}

	public class TestfAnalyzerDataCallBack implements CB_fAnalyzerDataCallBack {
		@Override
		public void invoke(long lAnalyzerHandle, int dwAlarmType,
				Object pAlarmInfo, byte pBuffer[], int dwBufSize,
				int nSequence, int reserved) {
			ToolKits.writeLog("TestfAnalyzerDataCallBack");
		}
	}

	public class TestfDecPlayBackPosCallBack implements
			CB_fDecPlayBackPosCallBack {
		@Override
		public void invoke(long lLoginID, int nEncoderID, int dwTotalSize,
				int dwPlaySize) {
			ToolKits.writeLog("TestfDecPlayBackPosCallBack");
		}
	}

	public class TestfMessDataCallBack implements CB_fMessDataCallBack {
		@Override
		public void invoke(long lCommand, NET_CALLBACK_DATA lpData) {
			ToolKits.writeLog("TestfMessDataCallBack");
		}
	}

	public class TestfSnapRev implements CB_fSnapRev {
		@Override
		public void invoke(long lLoginID, byte pBuf[], int RevLen,
				int EncodeType, int CmdSerial) {
			ToolKits.writeLog("TestfSnapRev");
		}
	}

	public class TestfSearchDevicesCB implements CB_fSearchDevicesCB {
		@Override
		public void invoke(DEVICE_NET_INFO_EX pDevNetInfo) {
			ToolKits.writeLog("TestfSearchDevicesCB");
			ToolKits.writeLog(new String(pDevNetInfo.szIP).trim());
			ToolKits.writeLog(new String(pDevNetInfo.szSerialNo).trim());
		}
	}

	public class TestfTransComCallBack implements CB_fTransComCallBack {
		@Override
		public void invoke(long lLoginID, long lTransComChannel,
				byte pBuffer[], int dwBufSize) {
			ToolKits.writeLog("TestfTransComCallBack");
		}
	}

	public class TestMessageCallBack implements CB_fMessageCallBack {
		@Override
		public boolean invoke(int lCommand, long lLoginID, Object obj,
				String pchDVRIP, int nDVRPort) {
			ToolKits.writeLog("TestMessageCallBack");
			ToolKits.writeLog("lCommand ： " + lCommand);

			if(FinalVar.SDK_ALARM_NO_DISK == lCommand) {
				ALARM_NO_DISK_INFO stInfo = (ALARM_NO_DISK_INFO)obj;

				ToolKits.writeLog("无硬盘报警!");
				ToolKits.writeLog("Time:" + stInfo.stuTime.toString());
				ToolKits.writeLog("dwAction:" + stInfo.dwAction);   // 0:Start, 1:Stop
			} else if(FinalVar.SDK_DISKFULL_ALARM_EX == lCommand) {  // 硬盘满报警，数据为1个字节，1为有硬盘满报警，0为无报警。 HDD full alarm
				byte[] diskFull = (byte[]) obj;
				ToolKits.writeLog("length1 : " + diskFull.length);
				if (diskFull[0] == 0) {
					ToolKits.writeLog("no alarm");
				} else if (diskFull[0] == 1) {
					ToolKits.writeLog("HDD full alarm");
				}
			} else if(FinalVar.SDK_DISKERROR_ALARM_EX == lCommand) { // 坏硬盘报警，数据为32个字节，每个字节表示一个硬盘的故障报警状态，1为有报警，0为无报警。  HDD malfunction alarm
				byte[] diskFull = (byte[]) obj;
				ToolKits.writeLog("length2 : " + diskFull.length);
				for (int i = 0; i < diskFull.length; i++) {
					if (diskFull[i] == 0) {
						ToolKits.writeLog("no alarm");
					} else if (diskFull[i] == 1) {
						ToolKits.writeLog("alarm");
					}
				}
			} else if(FinalVar.SDK_ALARM_DISK == lCommand) {  // 硬盘报警   alarm of disk
				ALARM_DISK_INFO stInfo = (ALARM_DISK_INFO) obj;

				ToolKits.writeLog("State" + stInfo.nHDDState);  // 0: Unknown, 1: Running, 2: Offline, 3: Warning, 4: Failed
			} else if(FinalVar.SDK_ALARM_DISK_FLUX == lCommand) {  // 硬盘数据异常事件   disk flux abnormal
				ALARM_DISK_FLUX stInfo = (ALARM_DISK_FLUX)obj;

				ToolKits.writeLog("dwAction:" + stInfo.dwAction);
				ToolKits.writeLog("data flux:" + stInfo.dwDataFlux);  // KB
				ToolKits.writeLog("Time:" + stInfo.stuTime.toString());
			} else if(FinalVar.SDK_ALARM_WIFI_SEARCH == lCommand) {  // 获取到周围环境中WIFI设备上报事件
				ALARM_WIFI_SEARCH_INFO stInfo = (ALARM_WIFI_SEARCH_INFO)obj;

				ToolKits.writeLog("获取到周围环境中WIFI设备上报事件");
			} else if(FinalVar.SDK_ALARM_CGIRECORD == lCommand) {
				ALARM_CGIRECORD stInfo = (ALARM_CGIRECORD)obj;
				ToolKits.writeLog("cgi触发手动录像 \n nAction = " + stInfo.nAction);
				ToolKits.writeLog("nChannelID = " + stInfo.nChannelID);
				ToolKits.writeLog("dbPTS = " + stInfo.dbPTS);
				ToolKits.writeLog("nEventID = " + stInfo.nEventID);
				ToolKits.writeLog("stuTime = " + stInfo.stuTime.toStringTime());
				ToolKits.writeLog("stuStartTime = " + stInfo.stuStartTime.toStringTime());
				ToolKits.writeLog("stuStopTime = " + stInfo.stuStopTime.toStringTime());
			} else if(FinalVar.SDK_ALARM_AIO_APP_CONFIG_EVENT == lCommand) {
				ALARM_AIO_APP_CONFIG_EVENT_INFO stInfo = (ALARM_AIO_APP_CONFIG_EVENT_INFO)obj;
				ToolKits.writeLog("szAddress = " + new String(stInfo.szAddress));
			} else if (FinalVar.SDK_ALARM_ARMMODE_CHANGE_EVENT == lCommand) {
				ALARM_ARMMODE_CHANGE_INFO stInfo = (ALARM_ARMMODE_CHANGE_INFO) obj;
				ToolKits.writeLog("布防状态改变事件 : " + "\n" + "emTriggerMode:" + stInfo.emTriggerMode);
			} else if (FinalVar.SDK_ALARM_BYPASSMODE_CHANGE_EVENT == lCommand) {
				ALARM_BYPASSMODE_CHANGE_INFO stInfo = (ALARM_BYPASSMODE_CHANGE_INFO) obj;
				ToolKits.writeLog("旁路状态变化事件 : " + "\n" + "emTriggerMode:" + stInfo.emTriggerMode);
			} else if(FinalVar.SDK_ALARM_ALARM_EX2 == lCommand) {
				ALARM_ALARM_INFO_EX2 stInfo = (ALARM_ALARM_INFO_EX2) obj;
				ToolKits.writeLog("本地报警事件 : " + "\n" + "emSenseType:" + stInfo.emSenseType);
			} else if (FinalVar.SDK_ALARM_ALARMCLEAR == lCommand) {
				ALARM_ALARMCLEAR_INFO stInfo = (ALARM_ALARMCLEAR_INFO) obj;
				ToolKits.writeLog("消警事件 : " + "\n" + "bEventAction:" + stInfo.bEventAction);
			}
			// 上传中盟失败数据个数（对应结构体ALARM_UPLOADPIC_FAILCOUNT_INFO）
			else if (FinalVar.SDK_ALARM_UPLOADPIC_FAILCOUNT == lCommand) {
				ALARM_UPLOADPIC_FAILCOUNT_INFO stInfo = (ALARM_UPLOADPIC_FAILCOUNT_INFO) obj;
				ToolKits.writeLog("TestMessageCallBack SDK_ALARM_UPLOADPIC_FAILCOUNT: "
						 + "failCount: " + stInfo.nFailCount);
			} else if (FinalVar.SDK_ALARM_UPLOAD_PIC_FAILED == lCommand) {
				ALARM_UPLOAD_PIC_FAILED_INFO stInfo = (ALARM_UPLOAD_PIC_FAILED_INFO)obj;
				ToolKits.writeLog("SDK_ALARM_UPLOAD_PIC_FAILED, action = " + stInfo.nAction);
			} else if (FinalVar.SDK_ALARM_HEATIMG_TEMPER == lCommand) { 
				ALARM_HEATIMG_TEMPER_INFO stInfo = (ALARM_HEATIMG_TEMPER_INFO)obj;
				ToolKits.writeLog("测温点温度异常 SDK_ALARM_HEATIMG_TEMPER, action = " + stInfo.nAction);
			} else if (FinalVar.SDK_ALARM_BETWEENRULE_TEMP_DIFF == lCommand) { 
				ALARM_BETWEENRULE_DIFFTEMPER_INFO stInfo = (ALARM_BETWEENRULE_DIFFTEMPER_INFO)obj;
				ToolKits.writeLog("规则间温差异常 SDK_ALARM_BETWEENRULE_TEMP_DIFF, action = " + stInfo.nAction);
			} else if (FinalVar.SDK_ALARM_HOTSPOT_WARNING == lCommand) { 
				ALARM_HOTSPOT_WARNING_INFO stInfo = (ALARM_HOTSPOT_WARNING_INFO)obj;
				ToolKits.writeLog("热点异常 SDK_ALARM_HOTSPOT_WARNING, action = " + stInfo.nAction);
			} else if (FinalVar.SDK_ALARM_COLDSPOT_WARNING == lCommand) { 
				ALARM_COLDSPOT_WARNING_INFO stInfo = (ALARM_COLDSPOT_WARNING_INFO)obj;
				ToolKits.writeLog("冷点异常 SDK_ALARM_COLDSPOT_WARNING, action = " + stInfo.nAction);
			} else if (FinalVar.SDK_ALARM_FIREWARNING_INFO == lCommand) { 
				ALARM_FIREWARNING_INFO_DETAIL stInfo = (ALARM_FIREWARNING_INFO_DETAIL)obj;
				ToolKits.writeLog("火情报警信息 SDK_ALARM_FIREWARNING_INFO, nChannel = " + stInfo.nChannel);
				for (int i = 0; i < stInfo.nWarningInfoCount; i ++) {
					ToolKits.writeLog("Count = " + i +"SDK_ALARM_FIREWARNING_INFO, nPresetId = " + stInfo.stuFireWarningInfo[i].nPresetId);
				}
			} else if (FinalVar.SDK_ALARM_FIREWARNING == lCommand) { 
				ALARM_FIREWARNING_INFO stInfo = (ALARM_FIREWARNING_INFO)obj;
				ToolKits.writeLog("着火点报警事件 SDK_ALARM_FIREWARNING, nChannel = " + stInfo.nChannel);
				ToolKits.writeLog("着火点报警事件  SDK_ALARM_FIREWARNING, nState = " + stInfo.nState);
			} else if (FinalVar.SDK_ALARM_FACE_OVERHEATING == lCommand) { 
				ALARM_FACE_OVERHEATING_INFO stInfo = (ALARM_FACE_OVERHEATING_INFO)obj;
				ToolKits.writeLog("人体发热预 SDK_ALARM_FACE_OVERHEATING, nRelativeId = " + stInfo.nRelativeId);
				ToolKits.writeLog("人体发热预 SDK_ALARM_FACE_OVERHEATING, nTemperatureUnit = " + stInfo.nTemperatureUnit);
				ToolKits.writeLog("人体发热预 SDK_ALARM_FACE_OVERHEATING, fTemperature = " + stInfo.fTemperature);
			}
			//DMSS接入智能锁
			else if(FinalVar.SDK_ALARM_ACCESS_CTL_NOT_CLOSE == lCommand){
				ALARM_ACCESS_CTL_NOT_CLOSE_INFO stInfo = (ALARM_ACCESS_CTL_NOT_CLOSE_INFO)obj;
				ToolKits.writeLog("NotClosed nAction = " + stInfo.nAction);
				ToolKits.writeLog("NotClosed nDoor = " + stInfo.nDoor);
				ToolKits.writeLog("NotClosed szDoorName = " + new String(stInfo.szDoorName));
				ToolKits.writeLog("NotClosed stuTime = " + + stInfo.stuTime.dwHour+":"+stInfo.stuTime.dwMinute+":"+stInfo.stuTime.dwSecond);
			}else if(FinalVar.SDK_ALARM_ACCESS_CTL_MALICIOUS == lCommand){
				ALARM_ACCESS_CTL_MALICIOUS stInfo = (ALARM_ACCESS_CTL_MALICIOUS)obj;
				ToolKits.writeLog("Malicious emMethod = " + stInfo.emMethod);
				ToolKits.writeLog("Malicious szSerialNum = " + new String(stInfo.szSerialNum));
			}else if(FinalVar.SDK_ALARM_ACCESS_CTL_USERID_REGISTER == lCommand){
				ALARM_ACCESS_CTL_USERID_REGISTER stInfo = (ALARM_ACCESS_CTL_USERID_REGISTER)obj;
				ToolKits.writeLog("UserID Register emMethod = " + stInfo.emMethod);
				ToolKits.writeLog("UserID Register szSerialNum = " + new String(stInfo.szSerialNum));
				ToolKits.writeLog("UserID Register nAction = "+stInfo.nAction);
				ToolKits.writeLog("UserID Register stuTime = " + stInfo.stuTime.dwHour+":"+stInfo.stuTime.dwMinute+":"+stInfo.stuTime.dwSecond);
			}else if(FinalVar.SDK_ALARM_ACCESS_CTL_REVERSELOCK == lCommand){
				ALARM_ACCESS_CTL_REVERSELOCK stInfo = (ALARM_ACCESS_CTL_REVERSELOCK)obj;
				ToolKits.writeLog("ReverseLock emMethod = " + stInfo.emMethod);
				ToolKits.writeLog("ReverseLock szSN = " + new String(stInfo.szSerialNum));
				ToolKits.writeLog("ReverseLock nAction = "+stInfo.nAction);
				ToolKits.writeLog("ReverseLock stuTime = " + stInfo.stuTime.dwHour+":"+stInfo.stuTime.dwMinute+":"+stInfo.stuTime.dwSecond);
			}else if(FinalVar.SDK_ALARM_ACCESS_CTL_USERID_DELETE == lCommand){
				ALARM_ACCESS_CTL_USERID_DELETE stInfo = (ALARM_ACCESS_CTL_USERID_DELETE)obj;
				ToolKits.writeLog("UserID Delete emMethod = " + stInfo.emMethod);
				ToolKits.writeLog("UserID Delete szSN = " + new String(stInfo.szSerialNum));
				ToolKits.writeLog("UserID Delete nAction = "+stInfo.nAction);
				ToolKits.writeLog("UserID Delete stuTime = " + stInfo.stuTime.dwHour+":"+stInfo.stuTime.dwMinute+":"+stInfo.stuTime.dwSecond);
			}else if(FinalVar.SDK_ALARM_ACCESS_DOOR_BELL == lCommand){
				ALARM_ACCESS_DOOR_BELL_INFO stInfo = (ALARM_ACCESS_DOOR_BELL_INFO)obj;
				ToolKits.writeLog("Door Bell SN = " + new String(stInfo.szWirelessDevSN));
				ToolKits.writeLog("Door Bell szName = " + new String(stInfo.szName));
				ToolKits.writeLog("Door Bell nAction = "+stInfo.nChannelID);
				ToolKits.writeLog("Door Bell stuTime = " + stInfo.stuTime.dwHour+":"+stInfo.stuTime.dwMinute+":"+stInfo.stuTime.dwSecond);
			}else if(FinalVar.SDK_ALARM_ACCESS_FACTORY_RESET == lCommand){
				ALARM_ACCESS_FACTORY_RESET_INFO stInfo = (ALARM_ACCESS_FACTORY_RESET_INFO)obj;
				ToolKits.writeLog("Factory Reset SN = " + new String(stInfo.szSmartLockSN));
				ToolKits.writeLog("Factory Reset stuTime = " + stInfo.stuTime.dwHour+":"+stInfo.stuTime.dwMinute+":"+stInfo.stuTime.dwSecond);
			}else if(FinalVar.SDK_ALARM_INPUT_SOURCE_SIGNAL == lCommand){  // 报警输入源信号事件
				ALARM_INPUT_SOURCE_SIGNAL_INFO stInfo = (ALARM_INPUT_SOURCE_SIGNAL_INFO)obj;
				ToolKits.writeLog("报警输入源信号事件 ： ");
				ToolKits.writeLog("nChannelID : " + stInfo.nChannelID);
				ToolKits.writeLog("nAction : " + stInfo.nAction);
				ToolKits.writeLog("stuTime : " + stInfo.stuTime.toString());
			} else if (FinalVar.SDK_ALARM_BOX_ALARM == lCommand) {
				ALARM_BOX_ALARM_INFO info = (ALARM_BOX_ALARM_INFO)obj;
				ToolKits.writeLog("报警盒子本地报警: " + info.toString());
			}else if (FinalVar.SDK_ALARM_NASFILE_STATUS == lCommand) {
				ALARM_NASFILE_STATUS_INFO stInfo = (ALARM_NASFILE_STATUS_INFO)obj;
				ToolKits.writeLog("NAS文件状态事件 ： ");
				ToolKits.writeLog("任务类型: " + stInfo.emTaskType);
				ToolKits.writeLog("任务状态: " + stInfo.emTaskState);
			}
			return true;
		}
	}

	public class TestServiceCallBack implements CB_fServiceCallBack {
		@Override
		public int invoke(long lHandle, String pIp, short wPort, int lCommand,
				Object pParam) {
			ToolKits.writeLog("TestServiceCallBack");
			return 0;
		}
	}

	public class TestfAttachVKCallBack implements CB_fAttachVK {
		@Override
		public void invoke(long lLoginID, long lAttachHandle,
				int nChannelID, NET_VKINFO pstVKInfo) {
			ToolKits.writeLog("TestfAttachVKCallBack");

			return;
		}
	}

	public class TestpfAudioDataCallBack implements CB_pfAudioDataCallBack {
		@Override
		public void invoke(long lTalkHandle, byte pDataBuf[], byte byAudioFlag) {
			ToolKits.writeLog("TestpfAudioDataCallBack");
		}
	}

	// 获取操作员用户名
	void GetOperatorName() {
		NET_IN_GET_OPERATOR_NAME  stInGetOperatorName = new NET_IN_GET_OPERATOR_NAME();
		NET_OUT_GET_OPERATOR_NAME stOutGetOperatorName = new NET_OUT_GET_OPERATOR_NAME();
		boolean bSuccess = INetSDK.GetOperatorName(__LoginHandle, stInGetOperatorName, stOutGetOperatorName, 3000);
		try {
			String strName = new String(stOutGetOperatorName.szOpearatorName);
			ToolKits.writeLog("GetOperatorName bSuccess: " + bSuccess + "name: " + strName);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 获取设备刻录信息， 这些接口不要在UI线程中处理，放到子线程处理
	void TestQueryBurnDevInfo()
	{
		new ToolKits.SimpleAsyncTask<Integer>() {
			 @Override
			    protected void onPreExecute() {
			            super.onPreExecute();
			    }
			     @Override
			      protected  Integer doInBackground(Void... params)
			     {
			    	 try {
			    		SDK_BURNING_DEVINFO burnDevInfo = new SDK_BURNING_DEVINFO();
						boolean bRet = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_BURNING_DEV, burnDevInfo, 5000);
						if (bRet)
						{
							ToolKits.writeLog("query burn dev info success");
							ToolKits.writeLog("burn dev devnum: " + burnDevInfo.dwDevNum);
							for(int i = 0; i < burnDevInfo.dwDevNum; i++)
							{
								ToolKits.writeLog("burn info:  busType: " + burnDevInfo.stDevs[i].dwBusType + 
								" driverType: " + burnDevInfo.stDevs[i].dwDriverType + " remainSpace: " + burnDevInfo.stDevs[i].dwRemainSpace + 
								" totalSpace: " + burnDevInfo.stDevs[i].dwTotalSpace + " driverName: " + 
								 new String(burnDevInfo.stDevs[i].dwDriverName));
							}
						}
						else
						{
							ToolKits.writeErrorLog("query burn dev info err:" );
							return -1;
						}
			    	 }
			    	 catch (Exception e) {
			    		 e.printStackTrace();
			    	 }

			        return 0;
			     }
			     @Override
			     protected void onPostExecute(Integer result) {
			    	 ToolKits.writeLog("SimpleAsyncTask result: " + result);
			      }  
		}.execute();
	}
	
	// 取刻录会话总数， 这些接口不要在UI线程中处理，放到子线程处理
	void QueryBurnSessionNum()
	{
		new ToolKits.SimpleAsyncTask<Integer>() {
			 @Override
			    protected void onPreExecute() {
			            super.onPreExecute();
			    }
			     @Override
			      protected  Integer doInBackground(Void... params)
			     {
			    	 try {
			    		Integer nBurnSessionm = new Integer(0);
						boolean bRet = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_BURN_SESSION_NUM, nBurnSessionm, 5000);
						if (bRet)
						{
							ToolKits.writeLog("query burn session num success num: " + nBurnSessionm);
						}
						else
						{
							ToolKits.writeErrorLog("query burn session num err:" );
							return -1;
						}
			    	 }
			    	 catch (Exception e) {
			    		 e.printStackTrace();
			    	 }
			        return 0;
			     }
			     @Override
			     protected void onPostExecute(Integer result) {
			    	 ToolKits.writeLog("SimpleAsyncTask result: " + result);
			      }  
		}.execute();
	}
	
	// 测试刻录相关功能（开始/停止刻录会话  开始/停止 刻录，暂停/恢复刻录), 这些接口不要在UI线程中处理，放到子线程处理
	void BurnFunction()
	{
		new ToolKits.SimpleAsyncTask<Integer>() {
			 @Override
			    protected void onPreExecute() {
			            super.onPreExecute();
			    }
			     @Override
			      protected  Integer doInBackground(Void... params)
			     {
			    	 try {
			    		 
			    		 //////////////////////打开刻录会话/////////////////////////////////////////////
			    		 NET_IN_START_BURN_SESSION stuInStartBurnSession = new NET_IN_START_BURN_SESSION();
			    		 stuInStartBurnSession.nSessionID = 0; // 从0开始
			    		 
			    		 NET_OUT_START_BURN_SESSION stuOutStartBurnSession = new NET_OUT_START_BURN_SESSION();
						 long lBurnSession = INetSDK.StartBurnSession(__LoginHandle, stuInStartBurnSession,
								 stuOutStartBurnSession, 3000);
						 if(lBurnSession != 0) {
							 ToolKits.writeLog("StartBurnSession result: " + lBurnSession);
						 } else {
							 ToolKits.writeErrorLog("StartBurnSession Failed!" );
						 }						 
						 			
						 
						 //  获取刻录状态
						 NET_IN_BURN_GET_STATE stuInBurnGetState = new  NET_IN_BURN_GET_STATE();
						 NET_OUT_BURN_GET_STATE stuOutBurnGetState = new NET_OUT_BURN_GET_STATE();
						 boolean bBurnGetState = INetSDK.BurnGetState(lBurnSession, stuInBurnGetState, stuOutBurnGetState, 3000);
						 if(bBurnGetState)
						 {
							 ToolKits.writeLog("BurnGetState 刻录状态: " + stuOutBurnGetState.emState + " 刻录错误码: " 
									 + stuOutBurnGetState.emErrorCode + " 有无盘刻录: " + stuOutBurnGetState.emExtMode);
						 } else {
							 ToolKits.writeErrorLog("BurnGetState Failed!" );
						 }
						 
						 
						 ///////////////////////////////////监听刻录状态/////////////////////////////////////////////////////
						 NET_IN_ATTACH_STATE stuInAttachState = new NET_IN_ATTACH_STATE();
						 stuInAttachState.lBurnSession  = lBurnSession;
						 stuInAttachState.cbAttachState = new CB_fAttachBurnStateCB() {
							 @Override
							 public void invoke(long lLoginID, long lAttachHandle, final long lBurnSession, NET_OUT_BURN_GET_STATE burnState)
							 {
								 ToolKits.writeLog("call back burn state emState: " + burnState.emState + "emErrorCode: " + burnState.emErrorCode
										  + "lBurnSession: " + lBurnSession);
								 
								 // 当刻录状态为run的时候，可以做打点，暂停/恢复。停止刻录等操作
								 if(NET_BURN_STATE.BURN_STATE_BURNING == burnState.emState)
								 {
									
									 new Thread(new Runnable() {
							            	@Override
							            	public void run() {
							            		Looper.prepare();
							            		
/*							            		 ////////////////////////////打点，重点标记///////////////////////////////////////
							            	     NET_IN_BURN_MARK_TAG stuInBurnMarkTag = new NET_IN_BURN_MARK_TAG();
												 stuInBurnMarkTag.szDescInfo = "mark tag";
												 NET_OUT_BURN_MARK_TAG stuOutBurnMarkTag = new NET_OUT_BURN_MARK_TAG();
												 
												 boolean bMarkTag = INetSDK.BurnMarkTag(lBurnSession, stuInBurnMarkTag, stuOutBurnMarkTag, 3000);
												 ToolKits.writeLog("mark tag result: " + bMarkTag);*/
												 
												 
//												 /////////////////////////////////暂停刻录//////////////////////////////////////
//												 boolean bPauseBurn = INetSDK.PauseBurn(lBurnSession, true);
//												 ToolKits.writeLog("Pause burn result: " + bPauseBurn);
//												 
//												 /////////////////////////////////恢复刻录//////////////////////////////////////
//												 boolean bRestore = INetSDK.PauseBurn(lBurnSession, false);
//												 ToolKits.writeLog("restore burn result: " + bRestore);												 
							            		
							            		Looper.loop();
							            	}
							            }).start();
								 }
							 }
						 };
						 
						 NET_OUT_ATTACH_STATE stuOutAttachState = new NET_OUT_ATTACH_STATE();
						 long lAttachHandle =  INetSDK.AttachBurnState(__LoginHandle, stuInAttachState, stuOutAttachState, 3000);
						 if(lAttachHandle != 0) {
							 ToolKits.writeLog("AttachBurnState Succeed!");
						 } else {
							 ToolKits.writeErrorLog("AttachBurnState Failed!" );
						 }						
						 
						 
						///////////////////////////////////刻录字幕/////////////////////////////////////////
						String strCmdJudicature = FinalVar.CFG_CMD_JUDICATURE;
						CFG_JUDICATURE_INFO judicatureInfo = new CFG_JUDICATURE_INFO();
						// 获取
						boolean bJudicatureGet = ToolKits.GetDevConfig(strCmdJudicature, judicatureInfo, __LoginHandle, -1, 1024*1024*2);
						if(bJudicatureGet) {
							ToolKits.writeLog("GetJudicature Succeed!" + judicatureInfo.nCustomCase + "\n");
							for(int i=0; i<judicatureInfo.nCustomCase; i++) {
								ToolKits.writeLog("使能：" + judicatureInfo.bCustomCase);
								ToolKits.writeLog("案件名称：" + new String(judicatureInfo.stuCustomCases[i].szCaseTitle).trim());
								ToolKits.writeLog("案件内容：" + new String(judicatureInfo.stuCustomCases[i].szCaseContent).trim());
							}
						} else {
							ToolKits.writeErrorLog("GetJudicature Failed!" +INetSDK.GetLastError());
						}
						// 设置		
						judicatureInfo.bCustomCase = true;  // TRUE:自定义司法案件信息////FALSE: 上边szCaseNo等字段有效,用于获取案件编号
						judicatureInfo.nCustomCase = 8; // 在bCustomCase重定义为true时，需要设置
						
						judicatureInfo.stuCustomCases[1].bCaseNoOsdEn = true;
						
						for(int i = 0; i < FinalVar.MAX_OSD_TITLE_LEN; i++) {
							judicatureInfo.stuCustomCases[1].szCaseTitle[i] = 0;
						}
						
 
						String szCaseTitle2 = "ab";
						System.arraycopy(szCaseTitle2.getBytes(), 0, judicatureInfo.stuCustomCases[1].szCaseTitle, 0, szCaseTitle2.getBytes().length); 
						
						for(int j = 0; j < FinalVar.MAX_OSD_SUMMARY_LEN; j++) {
							judicatureInfo.stuCustomCases[1].szCaseContent[j] = 0;
						}
						
						String caseContent2 = "12";
						System.arraycopy(caseContent2.getBytes(), 0, judicatureInfo.stuCustomCases[1].szCaseContent, 0, caseContent2.getBytes().length); 
	
						
						boolean bJudicatureSet = ToolKits.SetDevConfig(strCmdJudicature, judicatureInfo, __LoginHandle, -1, 1024*1024*2);
						if(bJudicatureSet) {
							ToolKits.writeLog("SetJudicature Succeed!");
						} else {
							ToolKits.writeErrorLog("SetJudicature Failed!" );
						}
						
						 
						/////////////////////////////////// 设置字幕叠加（刻录字幕后，设备自动叠加，这个不需要）///////////////////////////////////////////////
						String strCmdvideoWidget = FinalVar.CFG_CMD_VIDEOWIDGET;
						AV_CFG_VideoWidget videoWidget = new AV_CFG_VideoWidget();
						// 获取
						boolean bvideoWidgetGet = ToolKits.GetDevConfig(strCmdvideoWidget, videoWidget, __LoginHandle, 0, 1024*1024*2);
						if(bvideoWidgetGet) {
							ToolKits.writeLog("GetvideoWidget Succeed!" + "\n" + videoWidget.stuTimeTitle.bEncodeBlend + videoWidget.stuTimeTitle.bEncodeBlendExtra1);
						} else {
							ToolKits.writeErrorLog("GetvideoWidget Failed!" +INetSDK.GetLastError());
						}
						// 设置
						
						videoWidget.nCustomTitleNum = 1;
						videoWidget.stuCustomTitle[0].bPreviewBlend = true;
						String string = "123456";
						System.arraycopy(string.getBytes(), 0, videoWidget.stuCustomTitle[0].szText, 0, string.getBytes().length);
						boolean bvideoWidgetSet = ToolKits.SetDevConfig(strCmdvideoWidget, videoWidget, __LoginHandle, 0, 1024*1024*2);
						if(bvideoWidgetSet) {
							ToolKits.writeLog("SetvideoWidget Succeed!");
						} else {
							ToolKits.writeErrorLog("SetvideoWidget Failed!" );
						}
						
						 //////////////////////////////////////开始刻录/////////////////////////////////////
						 NET_IN_START_BURN	 stuInStartBurn = new NET_IN_START_BURN();
						 stuInStartBurn.nDevMask |= (1 << 1); //从0开始，这里刻录设备第2个有效(每位代表是否有效)
						 
						 stuInStartBurn.nChannelCount = 1;
						 stuInStartBurn.szChannels[0] = 1;  //这里测试只第2个通道
						 
						 stuInStartBurn.emMode = NET_BURN_MODE.BURN_MODE_SYNC;
						 stuInStartBurn.emPack = NET_BURN_RECORD_PACK.BURN_PACK_DHAV;
						 stuInStartBurn.emExtMode = NET_BURN_EXTMODE.BURN_EXTMODE_NORMAL;  // 设置有盘/无盘
						 
						 NET_OUT_START_BURN stuOutStartBurn = new NET_OUT_START_BURN();
						 
						 boolean bStartBurn = INetSDK.StartBurn(lBurnSession, stuInStartBurn, stuOutStartBurn, 3000);
						 if(bStartBurn) {
							 ToolKits.writeLog("StartBurn result: " + bStartBurn);
						 } else {
							 ToolKits.writeErrorLog("StartBurn Failed!" );
						 }
						 
						 Thread.currentThread().sleep(20 * 1000, 0);
						 
					    /////////////////////////////////停止刻录//////////////////////////////////////
						boolean bStopBurn = INetSDK.StopBurn(lBurnSession);
						ToolKits.writeLog("StopBurn result: " + bStopBurn);
						 
                         /////////////////////////////////取消监听刻录状态/////////////////////////////////////////////////////
						 boolean bDetachBurnState =  INetSDK.DetachBurnState(lAttachHandle);
						 ToolKits.writeLog("DetachBurnState result: " + bDetachBurnState);
						 
						 ///////////////////////////////关闭刻录会话//////////////////////////////////////
						 boolean bStopBurnSession = INetSDK.StopBurnSession(lBurnSession);
						 ToolKits.writeLog("StopBurnSession result: " + bStopBurnSession);
						  
					    ///////////////////////////////////////////获取主机时间///////////////////////////////////////////
						NET_TIME pDeviceTime = new NET_TIME();
						boolean bQueryDevTime = INetSDK.QueryDeviceTime(__LoginHandle, pDeviceTime, 5000);
						if(bQueryDevTime) {
							ToolKits.writeLog("时间：" + pDeviceTime);
						} else {
							ToolKits.writeErrorLog("QueryDeviceTime Failed!" );
						}								 
			    	 }
			    	 catch (Exception e) {
			    		 e.printStackTrace();
			    	 }
			        return 0;
			     }
			     @Override
			     protected void onPostExecute(Integer result) {
			    	 ToolKits.writeLog("SimpleAsyncTask result: " + result);
			      }  
		}.execute();
	}
	
	//测试庭审主机配置
	void TestCourtHearingConfig()
	{
		
		new ToolKits.SimpleAsyncTask<Integer>() {
			 @Override
			    protected void onPreExecute() {
			            super.onPreExecute();
			    }
			     @Override
			      protected  Integer doInBackground(Void... params)
			     {
			    	 try {
			    		 int nChannelNum = NetSDKApplication.getInstance().getDeviceInfo().nChanNum & 0xff;
			    		 ToolKits.writeLog("TestCourtHearingConfig nChannelNum: " + nChannelNum);
			    		 
			    		 boolean bRet = false;
			    		 
			    			/////////////////////////一键静音的配置/////////////////////////////////
				    		
							CFG_AUDIO_MATRIX_SILENCE stuAudioMatrixSilence = new CFG_AUDIO_MATRIX_SILENCE(5);

				    		 bRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_AUDIO_MATRIX_SILENCE, stuAudioMatrixSilence, 
				    				__LoginHandle, 0,  1024 * 10);
				    		
							if (bRet)
							{
								ToolKits.writeLog("get audio matrix  silence success : " + bRet);
								
								stuAudioMatrixSilence.nMaxInputListCount = 1;
								stuAudioMatrixSilence.nRetInputListCountOut = 1;
								stuAudioMatrixSilence.pstSilenceInputChn[0].nMatrix = 0;
								stuAudioMatrixSilence.pstSilenceInputChn[0].nOutChannel = 0;
								stuAudioMatrixSilence.pstSilenceInputChn[0].nInputChnConut = 2;
								stuAudioMatrixSilence.pstSilenceInputChn[0].snInputChannel[0] = 1;
								stuAudioMatrixSilence.pstSilenceInputChn[0].snInputChannel[1] = 2;
								bRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_AUDIO_MATRIX_SILENCE, stuAudioMatrixSilence, 
					    				__LoginHandle, 0, 1024 * 10);
								ToolKits.writeErrorLog("set audio matrix  silence bRet: "  + bRet);
								
							}
							else
							{
								ToolKits.writeErrorLog("get audio matrix  silence err: " );
							}
				    		
							///////////////////一键静音控制接口////////////////////////////////////
							
							bRet = false;
							NET_IN_AUDIO_MATRIX_SILENCE stuInAudioMatrixSilence = new NET_IN_AUDIO_MATRIX_SILENCE(1);
							stuInAudioMatrixSilence.bEnable = true;
							stuInAudioMatrixSilence.stSlienceChannel[0].nMatrix = 0;
							stuInAudioMatrixSilence.stSlienceChannel[0].nOutChannel = 2;
							stuInAudioMatrixSilence.stSlienceChannel[0].nOutPutChannel[0] = 0;
							stuInAudioMatrixSilence.stSlienceChannel[0].nOutPutChannel[1] = 1;
							
							NET_OUT_AUDIO_MATRIX_SILENCE stuOutAudioMatrixSilence = new NET_OUT_AUDIO_MATRIX_SILENCE();
									
							bRet = INetSDK.ControlDeviceEx(__LoginHandle, CtrlType.SDK_CTRL_AUDIO_MATRIX_SILENCE, stuInAudioMatrixSilence,
									stuOutAudioMatrixSilence, 3000);
							ToolKits.writeErrorLog("ControlDeviceEx audio matrix silence bRet: "  + bRet);
							
			    		 /*	
			    		 /////////////////////////////////////////////语言激励配置////////////////////////////////////////////////////
			    		 CFG_AUDIO_SPIRIT stuAudioSpirit = new CFG_AUDIO_SPIRIT(nChannelNum);
			    		 

			    		boolean bRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_AUDIO_SPIRIT, stuAudioSpirit, 
			    				__LoginHandle, -1, 2048);
			    		
						if (bRet)
						{
							ToolKits.writeLog("get audio spirit config success " + " bEnable: " + stuAudioSpirit.bEnable + 
									" nAudioLimit: " + stuAudioSpirit.nAudioLimit +  " nDelayTime " + 
									stuAudioSpirit.nDelayTime);
							
							
							bRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_AUDIO_SPIRIT, stuAudioSpirit, 
				    				__LoginHandle, -1, 2048);
							ToolKits.writeErrorLog("set audio spirit config result bRet: "  + bRet);
							
						}
						else
						{
							ToolKits.writeErrorLog("get audio spirit config err: " );
						}
						
						
						/////////////////////////////////////合成通道配置//////////////////////////////////////////////////////////////
						CFG_COMPOSE_CHANNEL stuCompseChannel = new CFG_COMPOSE_CHANNEL();

				    		 bRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_COMPOSE_CHANNEL, stuCompseChannel, 
				    				__LoginHandle, 0, 2048);
				    		
							if (bRet)
							{
								ToolKits.writeLog("get commpose channel success ");
								
								bRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_COMPOSE_CHANNEL, stuCompseChannel, 
					    				__LoginHandle, 0, 2048);
								ToolKits.writeErrorLog("set commpose channe bRet: "  + bRet);
								
							}
							else
							{
								ToolKits.writeErrorLog("get commpose channel err: " );
							}
							
						 ///////////////////////////////////////////查询下位矩阵输出通道名称/////////////////////////////////////////////////////////////////
						 //下位矩阵配置
							CFG_LOWER_MATRIX_LIST stuLowerMatrixList = new CFG_LOWER_MATRIX_LIST();

				    		 bRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_LOWER_MATRIX, stuLowerMatrixList, 
				    				__LoginHandle, -1, 2048);
				    		
							if (bRet)
							{
								ToolKits.writeLog("get lower matrix success ");
								
								bRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_LOWER_MATRIX, stuLowerMatrixList, 
					    				__LoginHandle, -1, 2048);
								ToolKits.writeErrorLog("set lower matrix bRet: "  + bRet);
								
							}
							else
							{
								ToolKits.writeErrorLog("get lower matrix err: " );
							}*/
			    		 
						//////////////////////////////////////////////////////////////////////////////////////////////////
						return bRet ? 1 : 0;
						
			    	 }
			    	 catch (Exception e) {
			    		 e.printStackTrace();
			    	 }
			        return 0;
			     }
			     @Override
			     protected void onPostExecute(Integer result) {
			    	 ToolKits.writeLog("SimpleAsyncTask result: " + result);
			      }  
		}.execute();
	}
	
	// 测试庭审主机相关接口
	void TestCourtHearingInterface()
	{
		new ToolKits.SimpleAsyncTask<Integer>() {
			 @Override
			    protected void onPreExecute() {
			            super.onPreExecute();
			    }
			     @Override
			      protected  Integer doInBackground(Void... params)
			     {
			    	 try {
			    		 
			    		 boolean bRet = false;
			    		
			    			//////////////////////////////////////查询一级输出切换能力///////////////////////////////////////////
				    		SDK_SPLIT_CAPS splitCaps = new  SDK_SPLIT_CAPS();
				    		bRet = INetSDK.GetSplitCaps(__LoginHandle, 1, splitCaps, 3000);
				    		if(bRet)
				    		{
				    			for(int i = 0; i < splitCaps.nModeCount; i++)
				    			{
				    				ToolKits.writeLog("GetSplitCaps ["+ i +  "] emSplitMode: " + splitCaps.emSplitMode[i]);
				    			}
				    			for(int i =0 ; i < splitCaps.nInputChannelCount; i++)
				    			{
				    				ToolKits.writeLog("GetSplitCaps i =   "+ i  +   " szInputChannels: " + splitCaps.szInputChannels[i]);
				    			}
				    		}else {
				    			ToolKits.writeErrorLog("GetSplitCaps Failed!" );
				    		}
				    		
				    		bRet = false;
				    		////////////////////////////////////查询分割模式/////////////////////////////////////////////
				    		SDK_SPLIT_MODE_INFO splitModeInfo = new SDK_SPLIT_MODE_INFO();
				    		bRet = INetSDK.GetSplitMode(__LoginHandle, 1, splitModeInfo, 3000);
				    		if(bRet)
				    		{
				    			ToolKits.writeLog("GetSplitMode emSplitMode: " + splitModeInfo.emSplitMode + "\n" + 
				    							  " nGroupID: " + splitModeInfo.nGroupID + "\n" + 
				    							  "dwDisplayType:" +  splitModeInfo.dwDisplayType+ "\n");
				    		} else {
				    			ToolKits.writeErrorLog("GetSplitMode Failed!" );
				    		}
				    		
				    		// SetSplitMode已经封装，但 设备不支持，
				    		
				    		///////////////////////////////////查询显示源/////////////////////////////////////////////////////////
//				    		bRet = false;
//				    		int nChannel = 0; // 输出通道号
//				    		int nWindow = -1; // 输出通道对应的窗口号， -1表示所有窗口
//				    		int nMaxSplitSource = 10;
//				    		Integer nRetCount= Integer.valueOf(0);
//				    		SDK_SPLIT_SOURCE szStuSplitSource[] = new SDK_SPLIT_SOURCE[nMaxSplitSource];
//				    		for(int i = 0; i < nMaxSplitSource; i++)
//				    		{
//				    			szStuSplitSource[i] = new SDK_SPLIT_SOURCE();
//				    		}
//				    		bRet = INetSDK.GetSplitSource(__LoginHandle, nChannel, nWindow, szStuSplitSource, nRetCount, 3000);
//				    		if(bRet) {
//				    			ToolKits.writeLog("GetSplitSource  result: " + bRet + " nRetCount: " + nRetCount.intValue());
//				    		} else {
//				    			ToolKits.writeErrorLog("GetSplitSource Failed!" );
//				    		}

				    		
				    		// 设置显示源 SetSplitSource已经封装，但设备不支持
				    		
				    		////////////////////////////////////设置显示源, 支持同时设置多个窗口///////////////////////////////////////
							NET_IN_SPLIT_SET_MULTI_SOURCE stuInSplitSetMulitSource = new NET_IN_SPLIT_SET_MULTI_SOURCE(2);
							stuInSplitSetMulitSource.emCtrlType = EM_VIDEO_OUT_CTRL_TYPE.EM_VIDEO_OUT_CTRL_CHANNEL;
							stuInSplitSetMulitSource.nChannel   = 1;
							stuInSplitSetMulitSource.bSplitModeEnable = true;
							stuInSplitSetMulitSource.emSplitMode = SDK_SPLIT_MODE.SDK_SPLIT_2;   // 要大于窗口数量nWindowCount
							stuInSplitSetMulitSource.nGroupID    = 0;  // 分割分组号,由 GetSplitMode 获得
							
							stuInSplitSetMulitSource.nWindowCount  = 2; // 窗口数量
							//
							stuInSplitSetMulitSource.szWindows[0] = 0;
							String DeviceID = "Unique";
							System.arraycopy(DeviceID.getBytes(), 0,stuInSplitSetMulitSource.szStuSources[1].szDeviceID, 0,
									DeviceID.getBytes().length);
							
							stuInSplitSetMulitSource.szStuSources[0].bEnable    = true;
							stuInSplitSetMulitSource.szStuSources[0].nInterval  = 0;
							stuInSplitSetMulitSource.szStuSources[0].nVideoChannel = 0;
							stuInSplitSetMulitSource.szStuSources[0].nStreamType  = 0;
							//
							stuInSplitSetMulitSource.szWindows[1] = 0;
							String DeviceID1 = "Unique";
							System.arraycopy(DeviceID1.getBytes(), 0,stuInSplitSetMulitSource.szStuSources[1].szDeviceID, 0,
									DeviceID1.getBytes().length);
							
							stuInSplitSetMulitSource.szStuSources[1].bEnable    = true;
							stuInSplitSetMulitSource.szStuSources[1].nInterval  = 0;
							stuInSplitSetMulitSource.szStuSources[1].nVideoChannel = 0;
							stuInSplitSetMulitSource.szStuSources[1].nStreamType  = 0;
				    		NET_OUT_SPLIT_SET_MULTI_SOURCE stuOutSplitSetMulitSource = new NET_OUT_SPLIT_SET_MULTI_SOURCE();
				    		bRet = INetSDK.SplitSetMultiSource(__LoginHandle, stuInSplitSetMulitSource,
				    				stuOutSplitSetMulitSource, 3000);
				    		if(bRet) {
				    			ToolKits.writeLog("SplitSetMultiSource Succeed!");
				    		} else {
				    			ToolKits.writeErrorLog("SplitSetMultiSource Failed!" );
				    		}
	
				    		
				    		/////////////////////////////配置下位矩阵切换////////////////////////////////
				    		NET_IN_MATRIX_SWITCH stuMatrixSwitchIn = new NET_IN_MATRIX_SWITCH(1, 1);
				    	    int nInChannel = 1;
				    	    int nOutChannel = 1;

				    	    stuMatrixSwitchIn.emSplitMode = SDK_SPLIT_MODE.SDK_SPLIT_6;
				    	    stuMatrixSwitchIn.szOutputChannels[0] = nOutChannel;
				    	    stuMatrixSwitchIn.szInputChannels[0] = nInChannel;
				    	    
				    	    NET_OUT_MATRIX_SWITCH stuMatrixSwitchOut = new NET_OUT_MATRIX_SWITCH();
				    	    bRet = false;
				    	    bRet = INetSDK.MatrixSwitch(__LoginHandle, stuMatrixSwitchIn, stuMatrixSwitchOut, 3000);
				    	    if(bRet) {
				    	    	ToolKits.writeLog("MatrixSwitch bRet:" + bRet);
				    	    }else {
				    			ToolKits.writeErrorLog("MatrixSwitch Failed!" );
				    		}
				    	    
/*				    	    
				    	    //////////// 查询外接设备//////////////////////////////////////////////////////
				    	    NET_EXTERNAL_DEVICE stuExternalDev = new NET_EXTERNAL_DEVICE();
				    	    bRet = INetSDK.QueryDevState(__LoginHandle, 
									FinalVar.SDK_DEVSTATE_EXTERNAL_DEVICE, stuExternalDev, 5000);
				    	    if (bRet)
				    	    {
				    	    	ToolKits.writeLog("query external device info success ");
				    	    	ToolKits.writeLog("device type:" + stuExternalDev.emType + "  device id:" + stuExternalDev.szDevID
				    	    			 + "  device name:" + stuExternalDev.szDevName);
				    	    }
				    		
				    		//////////// 时序器操作//////////////////////////////////////////////////////
				    		
				    	    ////////////////////////////////////////////////红外面板模板配置/////////////////////////////////////////
				    	    
				    	    CFG_INFRARED_BOARD_TEMPLATE_GROUP stuInfraredBoardTemplateGroup = new CFG_INFRARED_BOARD_TEMPLATE_GROUP();

				    		 bRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_INFRARED_BOARD_TEMPLATE, stuInfraredBoardTemplateGroup, 
				    				__LoginHandle, 0, 2048);
				    		
							if (bRet)
							{
								ToolKits.writeLog("get infrared board template group success ");
								
								bRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_INFRARED_BOARD_TEMPLATE, stuInfraredBoardTemplateGroup, 
					    				__LoginHandle, 0, 2048);
								ToolKits.writeErrorLog("set infrared board template group succes bRet: "  + bRet);
								
							}
							else
							{
								ToolKits.writeErrorLog("get infrared board template group  err: " );
							}
							
							 ///////////////////////////////////////////////////////////红外面板配置///////////////////
							
							CFG_INFRARED_BOARD_GROUP stuInfraredBoardTGroup = new CFG_INFRARED_BOARD_GROUP();

				    		 bRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_INFRARED_BOARD, stuInfraredBoardTGroup, 
				    				__LoginHandle, 0, 2048);
				    		
							if (bRet)
							{
								ToolKits.writeLog("get infrared board  group success nBoardNum: " + stuInfraredBoardTGroup.nBoardNum);
								
								bRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_INFRARED_BOARD, stuInfraredBoardTGroup, 
					    				__LoginHandle, 0, 2048);
								ToolKits.writeErrorLog("set infrared board  group succes bRet: "  + bRet);
								
							}
							else
							{
								ToolKits.writeErrorLog("get infrared board  group  err: " );
							}
							
							//////////////////////////////////////红外按键控制/////////////////////////////////////
						    NET_CTRL_INFRARED_KEY_PARAM stCtrlInfraredKeyParam = new NET_CTRL_INFRARED_KEY_PARAM();
						    stCtrlInfraredKeyParam.nChannel = 0;
						    stCtrlInfraredKeyParam.nKey     = 1;
						    boolean bControl = INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_INFRARED_KEY, stCtrlInfraredKeyParam, 3000);
						    ToolKits.writeErrorLog("ControlDevice infrared key  bRet: "  + bRet);
						
						    /////////////////////////////////////上下位矩阵输出关系恢复/////////////////////////////////////////
						    ////////保存上下位矩阵输出关系///////////////
						    NET_CTRL_MATRIX_SAVE_SWITCH stuMatrixSwitchSave = new NET_CTRL_MATRIX_SAVE_SWITCH();
						    stuMatrixSwitchSave.pszName = "up-lower1";
						    bControl = INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_MATRIX_SAVE_SWITCH, stuMatrixSwitchSave, 3000);
						    if (bControl)
						    {
						    	ToolKits.writeLog("ControlDevice matrix  switch save success. Name: " + stuMatrixSwitchSave.pszName);
						    	//////////恢复上下位矩阵输出关系/////////////////
						    	NET_CTRL_MATRIX_RESTORE_SWITCH stuMatrixSwitchRestore = new NET_CTRL_MATRIX_RESTORE_SWITCH();
						    	stuMatrixSwitchRestore.pszName = "up-lower1";
						    	bControl = INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTR_MATRIX_RESTORE_SWITCH, stuMatrixSwitchRestore, 3000);
						    	if (bControl)
						    	{
						    		ToolKits.writeLog("ControlDevice matrix  switch restore success. Name: " + stuMatrixSwitchSave.pszName);
						    	}
						    	else
						    	{
						    		ToolKits.writeErrorLog("ControlDevice matrix  switch restore  err: "  );
						    	}
						    }
						    else
						    {
						    	ToolKits.writeErrorLog("ControlDevice matrix  switch save  err: "  );
						    }*/
							
			    	     
			    		 /*// 查询下位矩阵输入输出通道数 
			    		 // 查询产品定义
			    		 SDK_PRODUCTION_DEFNITION stuProductionDefnition = new SDK_PRODUCTION_DEFNITION();
			    		 bRet = INetSDK.QueryProductionDefinition(__LoginHandle, stuProductionDefnition, 3000);
			    		 ToolKits.writeLog("QueryProductionDefinition result: " + bRet);
			
			    		 if(bRet)
			    		 {
			    			 for(int i = 0; i < FinalVar.SDK_MAX_LOWER_MITRIX_NUM; i++)
			    			 {
			    				 ToolKits.writeLog("QueryProductionDefinition zLowerMatrixInputChannels i =  " + i + " inputChannel: " + 
			    						 stuProductionDefnition.szLowerMatrixInputChannels[i]);
			    			 }
			    			 for(int i = 0; i < FinalVar.SDK_MAX_LOWER_MITRIX_NUM; i++)
			    			 {
			    				 ToolKits.writeLog("QueryProductionDefinition zLowerMatrixInputChannels i =  " + i + " OutputChannel: " + 
			    						 stuProductionDefnition.szLowerMatrixOutputChannels[i]);
			    			 } 
			    		 }
			    		 bRet = false;
			    		///////////////////////////////查询输入通道      获取所有有效显示源////////////////////////////////////////////////
			   
			    	    SDK_IN_MATRIX_GET_CAMERAS stuInMatrixGetCameras = new SDK_IN_MATRIX_GET_CAMERAS();
			    	    SDK_OUT_MATRIX_GET_CAMERAS stuOutMatrixGetCameras = new SDK_OUT_MATRIX_GET_CAMERAS(50, 20);
			    	    bRet = INetSDK.MatrixGetCameras(__LoginHandle, stuInMatrixGetCameras, stuOutMatrixGetCameras, 3000);
			    	    ToolKits.writeLog("MatrixGetCameras result: " + bRet);
			    						    
			    	    if(bRet)
			    	    {
			    	    	 ToolKits.writeLog("MatrixGetCameras nRetCameraCount: " + stuOutMatrixGetCameras.nRetCameraCount  + " nRealChannelCount: " + 
			    	    			 stuOutMatrixGetCameras.nRealChannelCount);	 
			    	    	 
			    	    }*/
		    	    	
						//////////////////////////////////////////////////////////////////////////////////////////////////
						return bRet ? 1 : 0;
						
			    	 }
			    	 catch (Exception e) {
			    		 e.printStackTrace();
			    	 }
			        return 0;
			     }
			     @Override
			     protected void onPostExecute(Integer result) {
			    	 ToolKits.writeLog("SimpleAsyncTask result: " + result);
			      }  
		}.execute();
	}

	// 视频分析资源配置
	void VideoAnalyseSourceConfig() {
		boolean zRet;

		int channelID = 0;
		CFG_ANALYSESOURCE_INFO stSrcInfo = new CFG_ANALYSESOURCE_INFO(32);
		zRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_ANALYSESOURCE, stSrcInfo, __LoginHandle, channelID, 10240);
		if(zRet) {

			// 设置
			String strPath = "EFC/f2895162c1c14825bac8fe69f5ef574d.dav"/*"EFC/d6c089ca7e184c98aaed2628d26bf08c.dav"*/;
			stSrcInfo.bEnable = true;
			stSrcInfo.nChannelID = channelID;
			stSrcInfo.abDeviceInfo = false;
			stSrcInfo.emSourceType = CFG_VIDEO_SOURCE_TYPE.CFG_VIDEO_SOURCE_FILESTREAM;
			stSrcInfo.stuSourceFile.emFileType = CFG_SOURCE_FILE_TYPE.CFG_SOURCE_FILE_RECORD;
			System.arraycopy(strPath.getBytes(), 0, stSrcInfo.stuSourceFile.szFilePath, 0, strPath.length());
			zRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_ANALYSESOURCE, stSrcInfo, __LoginHandle, channelID, 10240);

		}
	}

	// 订阅智能分析进度
	public void AttachVideoAnalyseState() {
		boolean zRet;

		NET_IN_ATTACH_VIDEOANALYSE_STATE stAttachIn = new NET_IN_ATTACH_VIDEOANALYSE_STATE();
		stAttachIn.nChannleId = 0;
		NET_OUT_ATTACH_VIDEOANALYSE_STATE stAttachOut = new NET_OUT_ATTACH_VIDEOANALYSE_STATE();
		zRet = INetSDK.AttachVideoAnalyseState(__LoginHandle, stAttachIn, new Test_CB_fVideoAnalyseState(), stAttachOut, 5000);

		NET_CTRL_START_VIDEO_ANALYSE stStartAnalyse = new NET_CTRL_START_VIDEO_ANALYSE();
		stStartAnalyse.nChannelId = 0;
		zRet = INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_START_VIDEO_ANALYSE, stStartAnalyse, 5000);

		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 停止视频智能分析
		NET_CTRL_STOP_VIDEO_ANALYSE stStopAnalyse = new NET_CTRL_STOP_VIDEO_ANALYSE();
		stStopAnalyse.nChannelId = 0;
		zRet = INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_STOP_VIDEO_ANALYSE, stStopAnalyse, 5000);

		// 取消订阅
		zRet = INetSDK.DetachVideoAnalyseState(stAttachOut.lAttachHandle);;
	}

	// 四川移动看店启迪平台接入配置
	void VSP_SCYDKDConfig() {
		CFG_VSP_SCYDKD_INFO info = new CFG_VSP_SCYDKD_INFO();
		boolean zRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_VSP_SCYDKD, info, __LoginHandle, -1, 10240);
		if (!zRet) {
			ToolKits.showErrorMessage(this, "GetDevConfig failed, " + FinalVar.CFG_CMD_VSP_SCYDKD);
		} else {
			System.arraycopy("admin".getBytes(), 0, info.szUserName, 0, "admin".length());
			zRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_VSP_SCYDKD, info, __LoginHandle, -1, 10240);
			if (!zRet) {
				ToolKits.showErrorMessage(this, "SetDevConfig failed, " + FinalVar.CFG_CMD_VSP_SCYDKD);
			}
		}
	}

	// 合成通道配置  庭审主机使用，证物切换功能
	public void ComposeLinkageConfig() {
		CFG_COMPOSE_CHANNEL info2 = new CFG_COMPOSE_CHANNEL();
		boolean zRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_COMPOSE_LINKAGE, info2, __LoginHandle, 0, 10240);
		if (!zRet) {
			ToolKits.showErrorMessage(this, "GetDevConfig failed, " + FinalVar.CFG_CMD_COMPOSE_LINKAGE);
		} else {
			zRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_COMPOSE_LINKAGE, info2, __LoginHandle, 0, 10240);
			if (!zRet) {
				ToolKits.showErrorMessage(this, "SetDevConfig failed, " + FinalVar.CFG_CMD_COMPOSE_LINKAGE);
			}
		}
	}

	// 查询外接设备信息
	public void QueryExternalDeviceInfo() {
		NET_EXTERNAL_DEVICE device[] = new NET_EXTERNAL_DEVICE[8];
		for (int i = 0; i < device.length; i++) {
			device[i] = new NET_EXTERNAL_DEVICE();
		}
		boolean zRet = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_EXTERNAL_DEVICE, device, 3000);
		if (zRet) {
			for (int i = 0; i < device.length; i++) {
				if (device[i].emType == NET_EXT_DEV_TYPE.EXT_DEV_UNKNOWN) {
					break;
				}

				if (device[i].emType == NET_EXT_DEV_TYPE.EXT_DEV_SEQUENCE_POWER) {
					int n = 0;
					for (int j = 0; j < device[i].szDevID.length; j++) {
						if (device[i].szDevID[j] != 0) {
							n++;
						} else {
							break;
						}
					}

					// 获取电源时序器能力
					NET_IN_CAP_SEQPOWER stIn = new NET_IN_CAP_SEQPOWER();
					stIn.pszDeviceID = new String(device[i].szDevID, 0, n);
					NET_OUT_CAP_SEQPOWER stOut = new NET_OUT_CAP_SEQPOWER();
					zRet = INetSDK.GetDevCaps(__LoginHandle, FinalVar.NET_DEV_CAP_SEQPOWER, stIn, stOut, 3000);
					if (zRet) {
						ToolKits.writeLog("nChannelNum = " + stOut.nChannelNum);
					}
				}
			}
		}
	}
	
	void DownloadByTime() {
		NET_TIME beginTime = new NET_TIME();
		beginTime.dwYear = 2016;
		beginTime.dwMonth = 2;
		beginTime.dwDay = 23;
		
		NET_TIME endTime = new NET_TIME();
		endTime.dwYear = 2016;
		endTime.dwMonth = 2;
		endTime.dwDay = 23;
		endTime.dwHour = 0;
		endTime.dwMinute = 30;
		
		String recFileName = "/sdcard/NetSDK/downloadbytime.dav";
		int channelID = 0;
//		long lDownload = INetSDK.DownloadByTimeEx2(__LoginHandle, channelID, EM_QUERY_RECORD_TYPE.EM_RECORD_TYPE_ALL, 
//				beginTime, endTime, recFileName, new Test_CB_fTimeDownLoadPosCallBack(), new Test_CB_fDataCallBack(), SC_TYPE.SC_NONE, null);
		long lDownload = INetSDK.DownloadByTimeEx(__LoginHandle, channelID, EM_QUERY_RECORD_TYPE.EM_RECORD_TYPE_ALL,
				beginTime, endTime, recFileName, new Test_CB_fTimeDownLoadPosCallBack(), new Test_CB_fDataCallBack(), null);

		// 未下载完，想重新下载，下载前 要  INetSDK.StopDownload(lDownload);
//		if (0 != lDownload) {
//			try {
//				Thread.sleep(60000 * 5);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			INetSDK.StopDownload(lDownload);
//		}
	}
	
	public class Test_CB_fTimeDownLoadPosCallBack implements CB_fTimeDownLoadPosCallBack {
		@Override
		public void invoke(long lPlayHandle, int dwTotalSize, int dwDownLoadSize, int index, NET_RECORDFILE_INFO recordfileinfo) {
			//ToolKits.writeLog("lPlayHandle = " + lPlayHandle + ", dwTotalSize = " + dwTotalSize + ", dwDownLoadSize = " + dwDownLoadSize);
			// -1 == dwDownLoadSize 下载结束
			if(-1 == dwDownLoadSize) {			
				INetSDK.StopDownload(lDownloadHandle);
				ToolKits.writeLog("DownLoad Completed!!!" + "lDownloadHandle = " + lDownloadHandle);	
			}
		}
	}
	
	public class Test_CB_fDataCallBack implements CB_fDataCallBack {
		@Override
		public int invoke(long lRealHandle, int dwDataType, byte buffer[], int dwBufferSize) {
			// 如果需要转码，把这里的buffer和dwBufferSize传给转码库接口
			//ToolKits.writeLog("lRealHandle = " + lRealHandle + ", dwDataType = " + dwDataType + ", dwBufferSize = " + dwBufferSize);
			return 0;
		}
	}
	
	public class Test_CB_fVideoAnalyseState implements CB_fVideoAnalyseState {
		@Override
		public int invoke(long lAttachHandle, NET_VIDEOANALYSE_STATE pAnalyseStateInfos, Object pReserved) {
			ToolKits.writeLog("Test_CB_fVideoAnalyseState, dwProgress = " + pAnalyseStateInfos.dwProgress);
			return 0;
		}
	}
	
	public class Test_CB_fAnalyzerDataCallBack implements CB_fAnalyzerDataCallBack {
		@Override
		public void invoke(long lAnalyzerHandle, int dwAlarmType, Object alarmInfo, byte pBuffer[], int dwBufSize, int nSequence, int reserved) {
			ToolKits.writeLog("Received IVS event ");
			if (FinalVar.EVENT_IVS_FACEDETECT == dwAlarmType) { // 人脸识别
				ToolKits.writeLog("EVENT_IVS_FACEDETECT");
			} else if (FinalVar.EVENT_IVS_TRAFFICJUNCTION == dwAlarmType) { // 车牌识别
				ToolKits.writeLog("EVENT_IVS_TRAFFICJUNCTION");
			}
		}
	}

	// 查询视频统计信息
	void FindNumberStat() {
		NET_IN_FINDNUMBERSTAT startIn = new NET_IN_FINDNUMBERSTAT();
		startIn.nChannelID = 0;
		startIn.nGranularityType = 1;
		startIn.nWaittime = 3000;
		startIn.stStartTime.dwYear = 2015;
		startIn.stStartTime.dwMonth = 12;
		startIn.stStartTime.dwDay = 1;
		startIn.stEndTime.dwYear = 2015;
		startIn.stEndTime.dwMonth = 12;
		startIn.stEndTime.dwDay = 14;
		NET_OUT_FINDNUMBERSTAT startOut = new NET_OUT_FINDNUMBERSTAT();
		long lFindHandle = INetSDK.StartFindNumberStat(__LoginHandle, startIn, startOut);
		if (lFindHandle != 0 && startOut.dwTotalCount > 0) {
			int nCount = startOut.dwTotalCount;
			int nStep = 10; // 根据实际业务设置
			int nLoop = nCount / nStep;
			for (int i = 0; i < nLoop + 1; i++) {
				NET_IN_DOFINDNUMBERSTAT doIn = new NET_IN_DOFINDNUMBERSTAT();
				doIn.nBeginNumber = i * nStep;
				doIn.nCount = (i == nLoop) ? (nCount - doIn.nBeginNumber) : nStep;
				doIn.nWaittime = 3000;
				NET_OUT_DOFINDNUMBERSTAT doOut = new NET_OUT_DOFINDNUMBERSTAT(nStep);
				int nRet = INetSDK.DoFindNumberStat(lFindHandle, doIn, doOut);
				if (nRet < 0) {
					ToolKits.writeErrorLog("DoFindNumberStat failed");
				}
				//doOut.nCount 把 doOut.pstuNumberStat[i].nEnteredSubTotal 和 doOut.pstuNumberStat[i].nExitedSubtotal 输出
			}
			
			boolean bRet = INetSDK.StopFindNumberStat(lFindHandle);
			if (!bRet) {
				ToolKits.writeErrorLog("DoFindNumberStat failed");
			}
		}
	}

	// 抓图同步接口,将图片数据直接返回给用户
	void SnapPictureToFile() {
		String name = "/sdcard/NetSDK/123.jpg";
		
		NET_IN_SNAP_PIC_TO_FILE_PARAM stIn = new NET_IN_SNAP_PIC_TO_FILE_PARAM();
		NET_OUT_SNAP_PIC_TO_FILE_PARAM stOut = new NET_OUT_SNAP_PIC_TO_FILE_PARAM(1024 * 1024);
		
		stIn.stuParam.Channel = NetSDKApplication.getInstance().getDeviceInfo().nChanNum-1;
		stIn.stuParam.Quality = 3;
		stIn.stuParam.ImageSize = 1;
		stIn.stuParam.mode = 0;
		stIn.stuParam.InterSnap = 5;
		stIn.stuParam.CmdSerial = 100;
		System.arraycopy(name.getBytes(), 0, stIn.szFilePath, 0, name.getBytes().length);
		if (!INetSDK.SnapPictureToFile(__LoginHandle, stIn, stOut, 3000)) {
			ToolKits.writeErrorLog("SnapPictureToFile failed");
		}
	}

	// WLAN配置
	void WlanConfig() {
		CFG_NETAPP_WLAN stCfg = new CFG_NETAPP_WLAN();
		boolean zRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_WLAN, stCfg, __LoginHandle, 0, 10240);
		if (zRet) {
			stCfg.stuWlanInfo[0].stuNetwork.szDnsServers[0] = new byte[FinalVar.AV_CFG_IP_Address_Len_EX];
			stCfg.stuWlanInfo[0].stuNetwork.szDnsServers[1] = new byte[FinalVar.AV_CFG_IP_Address_Len_EX];
			System.arraycopy("8.8.8.8".getBytes(), 0, stCfg.stuWlanInfo[0].stuNetwork.szDnsServers[0], 0, "8.8.8.8".getBytes().length);
			System.arraycopy("8.8.4.4".getBytes(), 0, stCfg.stuWlanInfo[0].stuNetwork.szDnsServers[1], 0, "8.8.4.4".getBytes().length);
			zRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_WLAN, stCfg, __LoginHandle, 0, 10240);
		}
	}

	// Smart H264编码方式配置
	void SmartEncodeConfig() {
		ToolKits.writeLog("TestSmartEncodeCfg");
		CFG_SMART_ENCODE_INFO stCfgs = new CFG_SMART_ENCODE_INFO();
		boolean zRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_SMART_ENCODE, stCfgs, __LoginHandle, 0, 10240);
		if (zRet) {		
			ToolKits.writeLog("bSmartH264 : " + stCfgs.bSmartH264);
			stCfgs.bSmartH264 = !stCfgs.bSmartH264;
			zRet = false;
			zRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_SMART_ENCODE, stCfgs, __LoginHandle, 0, 10240);
			if (zRet)
			{
				ToolKits.writeLog("SmartH264 SetDevConfig OK !");
			}			
		}
	}

	// 邮件发送配置
	void EmailConfig() {
		ToolKits.writeLog("TestEmailCfg");
		CFG_EMAIL_INFO stCfgs = new CFG_EMAIL_INFO();
		boolean zRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_EMAIL, stCfgs, __LoginHandle, -1, 10240);
		if (zRet) {		
			ToolKits.writeLog("bEnable : " + stCfgs.bEnable);
			String address = new String(stCfgs.szAddress, 0, 4);
			ToolKits.writeLog("Address : " + address);

			for (int i = 0; i < stCfgs.nRetReciversNum; ++i)
			{
				ToolKits.writeLog("szReceivers : " + stCfgs.szReceivers[i]);				
			}
			
			ToolKits.writeLog("stuHealthReport->nInterval " + stCfgs.stuHealthReport.nInterval);
		
			// test for set dev config
			stCfgs.bEnable = !stCfgs.bEnable;
			stCfgs.stuHealthReport.nInterval = 60;
			
			String UserName = new String(stCfgs.szUserName, 0, stCfgs.szUserName.length);
			ToolKits.writeLog("UserName : " + UserName);
			
			zRet = false;
			zRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_EMAIL, stCfgs, __LoginHandle, -1, 10240);
			if (zRet)
			{
				ToolKits.writeLog("Email SetDevConfig OK !");
			}			
		}
	}

	// 搜索无线设备扩展配置
	void SearchAndConfig() {
		boolean bRet;
		
		// 打开设备软AP，不需要登录，进行搜索设备，回调函数中获取到设备IP
//		TestfSearchDevicesCB stCb = new TestfSearchDevicesCB();
//		long lRet = INetSDK.StartSearchDevices(stCb);
//		try {
//			Thread.sleep(6000);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		bRet = INetSDK.StopSearchDevices(lRet);   // 回调函数里面搜索到设备之后，启线程调用该接口停止搜索
		
		// 利用上面搜索到的设备IP，调用登录接口进行登录（LiveActivity.java），获取设备的SSID列表
		SDKDEV_WLAN_DEVICE_LIST_EX stListEx[] = new SDKDEV_WLAN_DEVICE_LIST_EX[1];
		stListEx[0] = new SDKDEV_WLAN_DEVICE_LIST_EX();
		Integer stIntRet = new Integer(0);
		bRet = INetSDK.GetDevConfig(__LoginHandle, FinalVar.SDK_DEV_WLAN_DEVICE_CFG_EX, -1, stListEx, stIntRet, 5000);
		if (bRet) {
			for (int i = 0; i < stListEx[0].bWlanDevCount; i++) {
				String strSSID = new String(stListEx[0].lstWlanDev[i].szSSID);   // 把szSSID最后面的那些0去掉，可以解决乱码问题
				ToolKits.writeLog(strSSID);
			}
			
//			// 选择上面的一个SSID，配置参数
//			SDKDEV_WLAN_INFO stCfg[] = new SDKDEV_WLAN_INFO[1];
//			stCfg[0] = new SDKDEV_WLAN_INFO();
//			int channelID = 0;
//			bRet = INetSDK.GetDevConfig(__LoginHandle,
//					FinalVar.SDK_DEV_WLAN_CFG, channelID, stCfg, stIntRet, 5000);
//			stCfg[0].nEncryption = 7;
//			stCfg[0].byConnectedFlag = 1;
//			stCfg[0].szSSID = new byte[36];
//			stCfg[0].szKeys[0] = new byte[32];
//			stCfg[0].szKeys[1] = new byte[32];
//			stCfg[0].szKeys[2] = new byte[32];
//			stCfg[0].szKeys[3] = new byte[32];
//			stCfg[0].szWPAKeys = new byte[128];
//			System.arraycopy("IPC-GYL".getBytes(), 0, stCfg[0].szSSID, 0, "IPC-GYL".getBytes().length);
//			System.arraycopy("66886688".getBytes(), 0, stCfg[0].szKeys[0], 0, "66886688".getBytes().length);
//			System.arraycopy("66886688".getBytes(), 0, stCfg[0].szWPAKeys, 0, "66886688".getBytes().length);
//			bRet = INetSDK.SetDevConfig(__LoginHandle,
//					FinalVar.SDK_DEV_WLAN_CFG, channelID, stCfg, 3000);
//			if (!bRet) {
//				ToolKits.writeErrorLog("error, ");
//			}
		}
	}

	// 网络协议配置
	void DVRIPConfig() {
		CFG_DVRIP_INFO stCfgs = new CFG_DVRIP_INFO();
		boolean zRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_DVRIP, stCfgs, __LoginHandle, -1, 10240);
		if (zRet) {		
			ToolKits.writeLog("nUDPPort : " + stCfgs.nUDPPort);
			String address = new String(stCfgs.szMCASTAddress, 0, 13);
			ToolKits.writeLog("Address : " + address);

			for (int i = 0; i < stCfgs.nRegistersNum; ++i)
			{
				ToolKits.writeLog("stuRegisters : " + stCfgs.stuRegisters[i].nServersNum);				
			}
			
			ToolKits.writeLog("emStreamPolicy " + stCfgs.emStreamPolicy);
		
			// test for set dev config
			stCfgs.nUDPPort = stCfgs.nUDPPort;
			
			zRet = false;
			zRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_DVRIP, stCfgs, __LoginHandle, -1, 10240);
			if (zRet)
			{
				ToolKits.writeLog("DVRIP SetDevConfig OK !");
			}			
		}
	}

	// 控制启动设备升级,由设备独立完成升级过程,不需要传输升级文件
	void CloudUpgrader() {
		// query devtype
		CFG_DEV_DISPOSITION_INFO devtype = new CFG_DEV_DISPOSITION_INFO();
		boolean zRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_DEV_GENERRAL, devtype, __LoginHandle, -1, 10240);
		if (zRet) {
			String type = ToolKits.ByteArrayToString(devtype.szMachineName);
			if (null != type) {
				ToolKits.writeLog(type);
				if (!type.equals("LHV2008")) {
					return;
				}
			}
		}

		// check new firmware
		SDKDEV_UPGRADE_STATE_INFO upgrade_info = new SDKDEV_UPGRADE_STATE_INFO();
		boolean retCheck = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_GET_UPGRADE_STATE, upgrade_info, 10 * 1000);
		if (retCheck) {
			int state = upgrade_info.nState;
			switch (state) {
					case 0: //None
						ToolKits.writeLog("No newer firmware");
						break;
					case 1: //Regular
						// upgrader
						boolean retUpgrader = INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_UPGRADE_DEVICE, null, 10 * 1000);
				        if (retUpgrader) {
				        	ToolKits.writeLog("Upgrader success");
				        } else {
				        	ToolKits.writeErrorLog("Upgrader failed");
				        }
				        break;
					default:
						break;
			}
		} else {
			ToolKits.writeErrorLog("QueryDevState failed");
		}
	}

	// 测试邮件
    void TestMail() {
        NET_IN_TEST_MAIL stuIn = new NET_IN_TEST_MAIL();
        NET_OUT_TEST_MAIL stuOut = new NET_OUT_TEST_MAIL();
        if (INetSDK.ControlDeviceEx(__LoginHandle, CtrlType.SDK_CTRL_TEST_MAIL, stuIn, stuOut, 10 * 1000)) {
            ToolKits.writeLog("Test Mail sucess");
        }else {
            ToolKits.writeErrorLog("Test Mail failed");
        }
    }
    
    // 增加无线设备
    void AddLowRateWLAN() {
    	NET_CTRL_LOWRATEWPAN_ADD stuIn = new NET_CTRL_LOWRATEWPAN_ADD();
    	stuIn.stuCodeIDInfo.nWirelessId = 2;
    	stuIn.stuCodeIDInfo.bEnable = true;
    	stuIn.stuCodeIDInfo.emType = NET_WIRELESS_DEVICE_TYPE.NET_WIRELESS_DEVICE_TYPE_MAGNETOMER;
    	stuIn.stuCodeIDInfo.emMode = EM_WIRELESS_DEVICE_MODE.EM_WIRELESS_DEVICE_MODE_NORMAL;
    	stuIn.stuCodeIDInfo.emSenseMethod = EM_CODEID_SENSE_METHOD_TYPE.EM_CODEID_SENSE_METHOD_TYPE_DOOR_MAGNETISM;
    	
    	String customNameString = new String("app.test");
    	String snString = new String("app.test"); 
    	System.arraycopy(customNameString.getBytes(), 0, stuIn.stuCodeIDInfo.szCustomName, 0, customNameString.length());
    	System.arraycopy(snString.getBytes(), 0, stuIn.stuCodeIDInfo.szSerialNumber, 0, snString.length());
     	
        if (INetSDK.ControlDeviceEx(__LoginHandle, CtrlType.SDK_CTRL_LOWRATEWPAN_ADD, stuIn, null, 10 * 1000)) {
            ToolKits.writeLog("Add low rate WLAN sucess");
        }else {
            ToolKits.writeErrorLog("Add low rate WLAN failed");
        }
    }

    /**
     * 查询设备可支持页数能力级
     * @return 最大页数
     */
    public int QueryMaxPage() {
    	/// 最大页数
    	CFG_CAP_LOWRATEWPAN stuCaps = new CFG_CAP_LOWRATEWPAN();
		char szOutBuffer[] = new char[1024];
		Integer stError = new Integer(0);
		boolean bQN = INetSDK.QueryNewSystemInfo(__LoginHandle, FinalVar.CFG_CAP_CMD_LOWRATEWPAN, 0, szOutBuffer, stError, 5000);
		if (bQN) {
			bQN = INetSDK.ParseData(FinalVar.CFG_CAP_CMD_LOWRATEWPAN, szOutBuffer, stuCaps, null);
			if (!bQN) {
				ToolKits.writeErrorLog("INetSDK.ParseData CFG_CAP_CMD_LOWRATEWPAN error");
				return -1;
			}
		} else {
			ToolKits.writeErrorLog("INetSDK.QueryNewSystemInfo CFG_CAP_CMD_LOWRATEWPAN error");
		}
		
		return stuCaps.nMaxPageSize;
    }
    
    /**
     * 查询无线设备列表及外设使能状态
     */
    void QueryCodeIDState() {
    	new Thread(new Runnable() {
			
			public void run() {
				/// 获取总条数
		    	NET_GET_CODEID_COUNT stuCount = new NET_GET_CODEID_COUNT();
		    	if (!INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_GET_CODEID_COUNT, stuCount, 10*1000)) {
		    		ToolKits.writeErrorLog("Get CodeID Count failed");
		    		return;
		    	}
		    
		    	NET_GET_CODEID_LIST stuList = new NET_GET_CODEID_LIST(20/*GetMaxPage()*/);
		    	stuList.nStartIndex = 0;
		    	
		    	/// 按页查找
		    	do {
		    		if (!INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_GET_CODEID_LIST, stuList, 10*1000)) {
		    			ToolKits.writeErrorLog("INetSDK.QueryDevState SDK_DEVSTATE_GET_CODEID_LIST error");
		    		}
		    		
		    		for (int i = 0; i < stuList.nRetCodeIDNum; ++i) {
		    			ToolKits.writeLog("SerialNO " + new String(stuList.pstuCodeIDInfo[i].szSerialNumber).trim());
		    		}
		    		
		    		stuList.nStartIndex += stuList.nQueryNum;
		    	}while(stuList.nStartIndex < 20 /*GetMaxPage()*/);				
			}
		}).start();  	
    }
    
    /**
     * 查询无线设备状态
     */
    void QueryWirelessDevState() {
    	
    	NET_GET_WIRELESS_DEVICE_STATE stuMsg = new NET_GET_WIRELESS_DEVICE_STATE(20/*GetMaxPage()*/);
    	stuMsg.nStartIndex = 0;
    	
    	/// 按页查找
    	do {
    		if (!INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_GET_WIRESSLESS_STATE, stuMsg, 10*1000)) {
    			ToolKits.writeErrorLog("INetSDK.QueryDevState SDK_DEVSTATE_GET_CODEID_LIST error");
    		}
    		
    		for (int i = 0; i < stuMsg.nRetQueryNum; ++i) {
    			ToolKits.writeLog("SerialNO " + new String(stuMsg.pstuDeviceInfo[i].szSerialNumber).trim());
    		}
    		
    		stuMsg.nStartIndex += stuMsg.nQueryNum;
    	}while(stuMsg.nStartIndex < 20 /*GetMaxPage()*/);
    }
    
    /**
     * 门禁系统，开锁
     */
    void OpenDoor() {
    	NET_CTRL_ACCESS_OPEN stuIn = new NET_CTRL_ACCESS_OPEN();
		stuIn.nChannelID = 0; // 通道号
		if (INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_ACCESS_OPEN, stuIn, 10 * 1000)) {
            ToolKits.writeLog("Test Open Door sucess");
        }else {
            ToolKits.writeErrorLog("Test Open Door failed");
        }
    }
    
    /**
     * 报警盒子，删除指定的设备
     */
    void RomoveLowRateWPAN() {
    	NET_CTRL_LOWRATEWPAN_REMOVE stuIn = new NET_CTRL_LOWRATEWPAN_REMOVE();
    	stuIn.nWirelessId = 20160608;
    	System.arraycopy("SN: Test".getBytes(), 0, stuIn.szSerialNumber, 0, "SN: Test".length());
    	if (INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_LOWRATEWPAN_REMOVE, stuIn, 10 * 1000)) {
            ToolKits.writeLog("Test Romove Device sucess");
        }else {
            ToolKits.writeErrorLog("Test Romove Device failed");
        }
    }

	// 修改无线设备信息
    void ModifyLowRateWPAN() {
    	NET_CTRL_LOWRATEWPAN_MODIFY stuIn = new NET_CTRL_LOWRATEWPAN_MODIFY();
    	System.arraycopy("Test".getBytes(), 0, stuIn.stuCodeIDInfo.szName, 0, "Test".length());
    	stuIn.stuCodeIDInfo.bEnable = true;
    	
    	if (INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_LOWRATEWPAN_MODIFY, stuIn, 10 * 1000)) {
            ToolKits.writeLog("Test Modify Device sucess");
        }else {
            ToolKits.writeErrorLog("Test Modify Device failed");
        }
    }
    
    /**
     * 教学录播:
     * 	设置课程录像模式
     * 	获取课程录像模式
     *  获取导播预览通道号
     *  录播操作参考  TestRecordState
     *  获取软件版本
     *  
     *  录播主机通道绑定关系配置
     *  查询录播录像文件
     */
    void TestCourseRecord() {
    	boolean bRet = false;
    	
       	// 获取教室录像信息
    	int nType = EM_COURSERECORD_OPERATE_TYPE.EM_COURSERECORDE_TYPE_GET_INFO;
    	NET_IN_COURSERECORD_GETINFO inGetinfo = new NET_IN_COURSERECORD_GETINFO();
    	inGetinfo.nClassRoomID = 0;
    	NET_OUT_COURSERECORD_GETINFO outGetinfo = new NET_OUT_COURSERECORD_GETINFO();
    	bRet = INetSDK.OperateCourseRecordManager(__LoginHandle, nType, inGetinfo, outGetinfo, 4000);
    	if(bRet) {
    		ToolKits.writeLog("逻辑通道数量 : " + outGetinfo.nChannelNum);
    		for(int i = 0; i < outGetinfo.nChannelNum; i++) {
    			ToolKits.writeLog("" + outGetinfo.nCanRecord[i]);
    		}
    	}else {
    		ToolKits.writeErrorLog("Get Failed!" );
    	}
    	
    	// 设置教室录像信息
    	bRet = false;
    	nType = EM_COURSERECORD_OPERATE_TYPE.EM_COURSERECORDE_TYPE_SET_INFO;
    	NET_IN_COURSERECORD_SETINFO inSetinfo = new NET_IN_COURSERECORD_SETINFO();
    	inSetinfo.nClassRoomID = 0;
    	inSetinfo.nChannelNum = 64;
    	inSetinfo.nCanRecord[0] = 0;
    	NET_OUT_COURSERECORD_SETINFO outSetinfo = new NET_OUT_COURSERECORD_SETINFO();
    	bRet = INetSDK.OperateCourseRecordManager(__LoginHandle, nType, inSetinfo, outSetinfo, 4000);
    	if(bRet) {
    		ToolKits.writeLog("Set Succeed!");
    	} else {
    		ToolKits.writeErrorLog("Set Failed!" );
    	}
    	
    	// 将录像信息更新到time时的信息
    	bRet = false;
    	nType = EM_COURSERECORD_OPERATE_TYPE.EM_COURSERECORDE_TYPE_UPDATE_INFO;
    	NET_IN_COURSERECORD_UPDATE_INFO inUpdate = new NET_IN_COURSERECORD_UPDATE_INFO();
    	inUpdate.nClassRoomID = 0;
    	inUpdate.stuTime.dwYear = 2017;
    	inUpdate.stuTime.dwMonth = 1;
    	inUpdate.stuTime.dwDay = 13;
    	inUpdate.stuTime.dwHour = 11;
    	inUpdate.stuTime.dwMinute = 11;
    	inUpdate.stuTime.dwSecond = 11;
    	NET_OUT_COURSERECORD_UPDATE_INFO outUpdate = new NET_OUT_COURSERECORD_UPDATE_INFO();
    	bRet = INetSDK.OperateCourseRecordManager(__LoginHandle, nType, inUpdate, outUpdate, 4000);
    	if(bRet) {
    		ToolKits.writeLog("Update Succeed!");
    	} else {
    		ToolKits.writeErrorLog("Update Failed!" );
    	}
    	
    	// 获取当前课程教室已录制时间
    	bRet = false;
    	nType = EM_COURSERECORD_OPERATE_TYPE.EM_COURSERECORDE_TYPE_GET_TIME;
    	NET_IN_COURSERECORD_GET_TIME inGettime = new NET_IN_COURSERECORD_GET_TIME();
    	inGettime.nClassRoomID = 0;
    	NET_OUT_COURSERECORD_GET_TIME outGettime = new NET_OUT_COURSERECORD_GET_TIME();
    	bRet = INetSDK.OperateCourseRecordManager(__LoginHandle, nType, inGettime, outGettime, 4000);
    	if(bRet) {
    		ToolKits.writeLog("GetTime Succeed!" + "\n" + "已录制时间 : " + outGettime.nTime);
    	} else {
    		ToolKits.writeErrorLog("GetTime Failed!" );
    	}
    	
    	
//       	// 控制组合通道与逻辑通道
//    	int typelock = EM_COURSECOMPOSITE_OPERATE_TYPE.EM_COURSECOMPOSITE_TYPE_LOCK_CONTROL;
//    	NET_IN_COURSECOMPOSITE_LOCK_CONTROL inLock_CONTROL = new NET_IN_COURSECOMPOSITE_LOCK_CONTROL();
//    	inLock_CONTROL.bLock = false;
//    	inLock_CONTROL.nClassRoomID = 1;
//    	inLock_CONTROL.nLogicChannel = 1;
//    	NET_OUT_COURSECOMPOSITE_LOCK_CONTROL outLock_CONTROL = new NET_OUT_COURSECOMPOSITE_LOCK_CONTROL();
//    	
//    	bRet = INetSDK.OperateCourseCompositeChannel(__LoginHandle, typelock, inLock_CONTROL, outLock_CONTROL, 4000);
//    	if(bRet) {
//    		ToolKits.writeLog("Lock Control Succeed!");
//    	} else {
//    		ToolKits.writeErrorLog("Lock Control Failed!" );
//    	}
//    	
//    	// 获取组合通道与逻辑通道的锁定信息
//    	bRet = false;
//    	int typeget = EM_COURSECOMPOSITE_OPERATE_TYPE.EM_COURSECOMPOSITE_TYPE_GET_LOCKINFO;
//    	NET_IN_COURSECOMPOSITE_GET_LOCKINFO inGet_LOCKINFO = new NET_IN_COURSECOMPOSITE_GET_LOCKINFO();
//    	inGet_LOCKINFO.nClassRoomID = 1;
//    	inGet_LOCKINFO.nLogicChannel = 1;
//    	NET_OUT_COURSECOMPOSITE_GET_LOCKINFO outGet_LOCKINFO = new NET_OUT_COURSECOMPOSITE_GET_LOCKINFO();
//      	bRet = INetSDK.OperateCourseCompositeChannel(__LoginHandle, typeget, inGet_LOCKINFO, outGet_LOCKINFO, 4000);
//      	if(bRet) {
//      		ToolKits.writeLog("Get LockInfo Succeed! bState = " + outGet_LOCKINFO.bState);
//      	} else {
//      		ToolKits.writeErrorLog("Get LockInfo Failed!" );
//      	}
//    	
//    	
	    	// 获取组合通道信息
	      	bRet = false;
	    	int typeget1 = EM_COURSECOMPOSITE_OPERATE_TYPE.EM_COURSECOMPOSITE_TYPE_GET_INFO;
	    	NET_IN_COURSECOMPOSITE_GET_INFO inGet_INFO = new NET_IN_COURSECOMPOSITE_GET_INFO();
	    	inGet_INFO.nClassRoomId = 1;
	    	NET_OUT_COURSECOMPOSITE_GET_INFO outGet_INFO = new NET_OUT_COURSECOMPOSITE_GET_INFO();
	    	
	    	bRet = INetSDK.OperateCourseCompositeChannel(__LoginHandle, typeget1, inGet_INFO, outGet_INFO, 4000);
	    	if(bRet) {
	    		ToolKits.writeLog("Get Info Succeed!" + "\n" + "录制模式：" + outGet_INFO.stuChannelInfo.nCompositeChannelMode+
	    				";nChannelNum:"+outGet_INFO.stuChannelInfo.nChannelNum+";CourseName:"+new String(outGet_INFO.stuChannelInfo.szCourseName));
	    		
	    	} else {
	    		ToolKits.writeErrorLog("Get Info Failed!" );
	    	}
	    	
	    	outGet_INFO.stuChannelInfo.nCompositeChannelMode = -2;
	    	// 设置组合通道信息
	    	bRet = false;
	    	int typeset = EM_COURSECOMPOSITE_OPERATE_TYPE.EM_COURSECOMPOSITE_TYPE_SET_INFO;
	    	NET_IN_COURSECOMPOSITE_SET_INFO inSet_INFO = new NET_IN_COURSECOMPOSITE_SET_INFO();
	      	inSet_INFO.nClassRoomId = 1;
	    	inSet_INFO.stuChannelInfo = outGet_INFO.stuChannelInfo;
	    	NET_OUT_COURSECOMPOSITE_SET_INFO outSet_INFO = new NET_OUT_COURSECOMPOSITE_SET_INFO();
	    	
	    	bRet = INetSDK.OperateCourseCompositeChannel(__LoginHandle, typeset, inSet_INFO, outSet_INFO, 4000);
	    	if(bRet) {
	    		ToolKits.writeLog("Set Info Succeed!");
	    	} else {
	    		ToolKits.writeErrorLog("Set Info Failed!" );
	    	}
	    	
//    	// 将组合通道信息更新到time时的信息
//    	int typeupdate = EM_COURSECOMPOSITE_OPERATE_TYPE.EM_COURSECOMPOSITE_TYPE_UPDATE_INFO;
//    	NET_IN_COURSECOMPOSITE_UPDATE_INFO inUpdate_INFO = new NET_IN_COURSECOMPOSITE_UPDATE_INFO();
//    	inUpdate_INFO.nClassRoomId = 1;
//    	inUpdate_INFO.stuTime.dwYear = 2017;
//    	inUpdate_INFO.stuTime.dwMonth = 1;
//    	inUpdate_INFO.stuTime.dwDay = 6;
//    	inUpdate_INFO.stuTime.dwHour = 0;
//    	inUpdate_INFO.stuTime.dwMinute = 0;
//    	inUpdate_INFO.stuTime.dwSecond = 0;
//    	NET_OUT_COURSECOMPOSITE_UPDATE_INFO outUpdate_INFO = new NET_OUT_COURSECOMPOSITE_UPDATE_INFO();
//    	
//    	bRet = INetSDK.OperateCourseCompositeChannel(__LoginHandle, typeupdate, inUpdate_INFO, outUpdate_INFO, 4000);
//    	if(bRet) {
//    		ToolKits.writeLog("Update Succeed!");
//    	} else {
//    		ToolKits.writeErrorLog("Update Failed!" );
//    	}  	
    	
    	
    	// 设置: 注意如果重复设置相同的录像模式，设备将会返回失败
//    	NET_IN_SET_COURSE_RECORD_MODE pInBuf = new NET_IN_SET_COURSE_RECORD_MODE();
//    	pInBuf.emRecordMode = 1; // 普通模式
//    	NET_OUT_SET_COURSE_RECORD_MODE pOutBuf = new NET_OUT_SET_COURSE_RECORD_MODE();
//    	if(INetSDK.SetCourseRecordMode(__LoginHandle, pInBuf, pOutBuf, 10*1000)) {
//    		ToolKits.writeLog("SetCourseRecordMode Success");
//    	}
//    	else {
//    		ToolKits.writeLog("SetCourseRecordMode Failed");
//    	}
//    	
//    	// 获取
//    	NET_IN_GET_COURSE_RECORD_MODE getModeIn = new NET_IN_GET_COURSE_RECORD_MODE();
//    	NET_OUT_GET_COURSE_RECORD_MODE getModeOut = new NET_OUT_GET_COURSE_RECORD_MODE();
//    	
//    	if(INetSDK.GetCourseRecordMode(__LoginHandle, getModeIn, getModeOut, 10*1000)) {
//    		ToolKits.writeLog("GetCourseRecordMode Success" + "Mode " + getModeOut.emRecordMode);
//    	}
//    	else {
//    		ToolKits.writeLog("GetCourseRecordMode Failed");
//    	}
//    	
//    	// 导播预览通道号
//    	NET_IN_GET_COMPOSITE_PREVIEW_CHANNEL getChannelIn  = new NET_IN_GET_COMPOSITE_PREVIEW_CHANNEL();
//    	NET_OUT_GET_COMPOSITE_PREVIEW_CHANNEL getChannelOut = new NET_OUT_GET_COMPOSITE_PREVIEW_CHANNEL();
//     	
//    	bRet = INetSDK.GetCompositePreviewChannel(__LoginHandle,  getChannelIn,  getChannelOut, 10*1000);
//    	if (bRet) {
//    		ToolKits.writeLog("GetCompositePreviewChannel Success"
//    						+ "ChannelNum " + getChannelOut.nChannelNum);
//    		for(int i = 0; i < getChannelOut.nChannelNum; i ++) {
//    			ToolKits.writeLog("i " + i + ", Channel is " + getChannelOut.nChannel[i]);
//    		}
//    	}
//    	else {
//    		ToolKits.writeLog("GetCompositePreviewChannel Failed");
//    	}
    	
    	// 获取软件版本
//    	SDKDEV_VERSION_INFO info = new SDKDEV_VERSION_INFO();
//    	bRet = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_SOFTWARE, info, 10*1000);
//    	if (bRet) {
//    		ToolKits.writeLog("QueryDevState" + "SV: " + new String(info.szSoftWareVersion).trim());
//    		ToolKits.writeLog("QueryDevState" + "szDevSerialNo: " + new String(info.szDevSerialNo).trim());
//    		int buildData = info.dwSoftwareBuildDate;
//    		
//    		int day = buildData & 0xff;
//    		buildData >>= 8;
//    		int month = buildData & 0xff;
//    		int year = buildData >> 8;
//    		
//    		ToolKits.writeLog("QueryDevState" + "BuildData: " + year + "-" +month + "-" + day);
//    	}
//    	else {
//    		ToolKits.writeLog("QueryDevState  SDK_DEVSTATE_SOFTWARE Failed");
//    	}
    	
//    	// 开启关闭录播
//    	NET_IN_SET_COURSE_RECORD_STATE InMsg = new NET_IN_SET_COURSE_RECORD_STATE();
//    	InMsg.nAction = 0;
//    	InMsg.nChannel = 0;
//    	
//    	NET_OUT_SET_COURSE_RECORD_STATE OutMsg = new NET_OUT_SET_COURSE_RECORD_STATE();
//    	bRet = INetSDK.SetCourseRecordState(__LoginHandle, InMsg, OutMsg, 10*1000);
//    	if (bRet) {
//    		ToolKits.writeLog("SetCoureRecordState Success");
//    	}
//    	else {
//    		ToolKits.writeLog("SetCoureRecordState Failed");
//    	}
//    	
//    	// 录播主机通道绑定关系配置
//    	CFG_COURSE_CHANNELBIND_INFO stCfgs = new CFG_COURSE_CHANNELBIND_INFO();
//		boolean bCfgs = ToolKits.GetDevConfig(FinalVar.CFG_CMD_COURSE_CHANNELBIND, stCfgs, __LoginHandle, -1, 10240);
//		if (bCfgs) {				
//			// 当配置时需要停止录播
//			bCfgs = ToolKits.SetDevConfig(FinalVar.CFG_CMD_COURSE_CHANNELBIND, stCfgs, __LoginHandle, -1, 10240);
//			if (bCfgs)
//			{
//				ToolKits.writeLog("CHANNELBIND SetDevConfig OK ! " + stCfgs);
//			}			
//		}
//		
//		// 查询录播录像文件
//		NET_IN_QUERY_COURSEMEDIA_FILEOPEN  stuInOpen = new NET_IN_QUERY_COURSEMEDIA_FILEOPEN();
//	    stuInOpen.stuStartTime.dwYear = 2016;
//	    stuInOpen.stuStartTime.dwMonth = 12;
//	    stuInOpen.stuStartTime.dwDay = 22;
//	    stuInOpen.stuStartTime.dwHour = 16;
//	    stuInOpen.stuStartTime.dwMinute = 0;
//	    stuInOpen.stuStartTime.dwSecond = 0;
//	    
//	    stuInOpen.stuEndTime.dwYear = 2016;
//	    stuInOpen.stuEndTime.dwMonth = 12;
//	    stuInOpen.stuEndTime.dwDay = 22;
//	    stuInOpen.stuEndTime.dwHour = 18;
//	    stuInOpen.stuEndTime.dwMinute = 0;
//	    stuInOpen.stuEndTime.dwSecond = 0;
//	    NET_OUT_QUERY_COURSEMEDIA_FILEOPEN stuOutOpen = new NET_OUT_QUERY_COURSEMEDIA_FILEOPEN();
//	    
//	    if(INetSDK.OpenQueryCourseMediaFile(__LoginHandle, stuInOpen, stuOutOpen,5000)) {
//	    	ToolKits.writeLog("ntotalNum : " + stuOutOpen.ntotalNum + "\n" + 
//	    				      "nfindID : " + stuOutOpen.nfindID);
//	    } else {
//	    	ToolKits.writeErrorLog("Failed" );
//	    }
//	    
//	    if (stuOutOpen.ntotalNum > 0) {
//		    NET_IN_QUERY_COURSEMEDIA_FILE stuInDo = new NET_IN_QUERY_COURSEMEDIA_FILE();
//		    stuInDo.nfindID = stuOutOpen.nfindID;
//		    stuInDo.nOffset = 0;
//		    stuInDo.nCount = 20;
//		    NET_OUT_QUERY_COURSEMEDIA_FILE stuOutDo = new NET_OUT_QUERY_COURSEMEDIA_FILE();
//
//		    if(INetSDK.DoQueryCourseMediaFile(__LoginHandle, stuInDo, stuOutDo, 20000)) {
//		    	
//		    	ToolKits.writeLog("查询到的个数 : " + stuOutDo.nCountResult);
//		    	
//		    	for(int i=0; i<stuOutDo.nCountResult; i++) {
//			    	ToolKits.writeLog("ID: " + stuOutDo.stuCourseMediaFile[i].nID + 
//			    					  "通道数量：" + stuOutDo.stuCourseMediaFile[i].nChannelNum);
//			    	
//			    	for(int m=0; m<16; m++) {
//			    		for(int n=0; n<16; n++){
//			    			ToolKits.writeLog("Record Info:" + "\n" + stuOutDo.stuCourseMediaFile[i].stuRecordInfo[m][n].stuStartTime + "\n" +
//			    					          stuOutDo.stuCourseMediaFile[i].stuRecordInfo[m][n].stuEndTime);
//			    		}			
//			    	}
//		    	}
//		    }else {
//		    	ToolKits.writeErrorLog("Failed2" );
//		    }
//	    }
//	    NET_IN_QUERY_COURSEMEDIA_FILECLOSE stuInColse = new NET_IN_QUERY_COURSEMEDIA_FILECLOSE();
//	    NET_OUT_QUERY_COURSEMEDIA_FILECLOSE stuOutColse = new NET_OUT_QUERY_COURSEMEDIA_FILECLOSE();
//	    stuInColse.nFindID = stuOutOpen.nfindID;
//	    
//	    INetSDK.CloseQueryCourseMediaFile(__LoginHandle, stuInColse, stuOutColse,2000);	
    }
    
    /**
     * NTP配置
     */
    void NTPConfig() {
    	CFG_NTP_INFO stCfgs = new CFG_NTP_INFO();
		boolean zRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_NTP, stCfgs, __LoginHandle, -1, 10240);
		if (zRet) {		
			ToolKits.writeLog("bEnable : " + stCfgs.bEnable);
			ToolKits.writeLog("Address : " + new String(stCfgs.szAddress).trim());
			
			// 设置
			stCfgs.bEnable = true;
			String timeZone = new String("BeiJing");
			System.arraycopy(timeZone.getBytes(), 0, stCfgs.szTimeZoneDesc, 0, timeZone.getBytes().length);
			
			// 备用服务器
			for(int i = 0; i < stCfgs.nSandbyServerNum; ++i ) {
				ToolKits.writeLog("i " + i + "Port " + stCfgs.stuStandbyServer[i].nPort);
			}
			
			zRet = false;
			zRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_NTP, stCfgs, __LoginHandle, -1, 10240);
			if (zRet)
			{
				ToolKits.writeLog("NTP SetDevConfig OK !");
			}			
		}
    }
    
    /**
     * 视频分割操作类型
     */
    void TestOperateSplit() {
    	long lLoginID = __LoginHandle;
    	int emType = -1;
    	int nWaitTime = 5000;
    	boolean bRet = false;
    	Object inParam;
    	Object outParam;
    	
    	/// 1. 设置背景图片
//    	NET_IN_SPLIT_SET_BACKGROUND inParam = new NET_IN_SPLIT_SET_BACKGROUND();
//    	inParam.bEnable = true;
//    	inParam.nChannel = 1;
//    	inParam.pszFileName = new String("SetBackGround");
//    	
//    	NET_OUT_SPLIT_SET_BACKBROUND outParam = new NET_OUT_SPLIT_SET_BACKBROUND();
//    	
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_SPLIT_OPERATE_SET_BACKGROUND;
    	
    	/// 2. 获取背景图片
//    	NET_IN_SPLIT_GET_BACKGROUND inParam = new NET_IN_SPLIT_GET_BACKGROUND();
//    	NET_OUT_SPLIT_GET_BACKGROUND outParam = new NET_OUT_SPLIT_GET_BACKGROUND();
//    	
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_SPLIT_OPERATE_GET_BACKGROUND;
    	
    	/// 3. 设置预拉流源
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_SPLIT_OPERATE_SET_PREPULLSRC;
//    	NET_IN_SPLIT_SET_PREPULLSRC inParam = new NET_IN_SPLIT_SET_PREPULLSRC(2);
//    	NET_OUT_SPLIT_SET_PREPULLSRC outParam = new NET_OUT_SPLIT_SET_PREPULLSRC(2);
    	
    	/// 4. 设置源边框高亮使能开关
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_SPLIT_OPERATE_SET_HIGHLIGHT;
//    	NET_IN_SPLIT_SET_HIGHLIGHT inParam = new NET_IN_SPLIT_SET_HIGHLIGHT();
//    	NET_OUT_SPLIT_SET_HIGHLIGHT outParam = new NET_OUT_SPLIT_SET_HIGHLIGHT();
    	
    	/// 5. 调整窗口Z序
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_SPLIT_OPERATE_SET_ZORDER;
//    	NET_IN_SPLIT_SET_ZORDER inParam = new NET_IN_SPLIT_SET_ZORDER();
//    	inParam.nChannel = 0;
//    	NET_OUT_SPLIT_SET_ZORDER outParam = new NET_OUT_SPLIT_SET_ZORDER(5);
    	
    	/// 6. 窗口轮巡控制
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_SPLIT_OPERATE_SET_TOUR;
//    	inParam = new NET_IN_SPLIT_SET_TOUR();
//    	outParam = new NET_OUT_SPLIT_SET_TOUR();
//    	bRet = INetSDK.OperateSplit(lLoginID, emType, inParam, outParam, nWaitTime);
//    	if (bRet) {
//    		ToolKits.writeLog("OperateSplit OK !!!");
//    	}
//    	else{
//    		ToolKits.writeErrorLog("Failed to OperateSplit");
//    	}    
    	
    	/// 7. 获取窗口轮巡状态
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_SPLIT_OPERATE_GET_TOUR_STATUS;
//    	inParam = new NET_IN_SPLIT_GET_TOUR_STATUS();
//    	outParam = new NET_OUT_SPLIT_GET_TOUR_STATUS(5);
//    	bRet = INetSDK.OperateSplit(lLoginID, emType, inParam, outParam, nWaitTime);
//    	if (bRet) {
//    		ToolKits.writeLog("OperateSplit OK !!!");
//    	}
//    	else{
//    		ToolKits.writeErrorLog("Failed to OperateSplit");
//    	} 
    	
    	/// 8. 获取屏内窗口信息
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_SPLIT_OPERATE_GET_SCENE;
//    	inParam = new NET_IN_SPLIT_GET_SCENE();
//    	outParam = new NET_OUT_SPLIT_GET_SCENE(5);
//    	bRet = INetSDK.OperateSplit(lLoginID, emType, inParam, outParam, nWaitTime);
//    	if (bRet) {
//    		ToolKits.writeLog("OperateSplit OK !!!");
//    	}
//    	else{
//    		ToolKits.writeErrorLog("Failed to OperateSplit");
//    	} 
    	
    	/// 9. 批量开窗
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_SPLIT_OPERATE_OPEN_WINDOWS;
//    	inParam = new NET_IN_SPLIT_OPEN_WINDOWS(5);
//    	outParam = new NET_OUT_SPLIT_OPEN_WINDOWS(5);
//    	bRet = INetSDK.OperateSplit(lLoginID, emType, inParam, outParam, nWaitTime);
//    	if (bRet) {
//    		ToolKits.writeLog("OperateSplit OK !!!");
//    	}
//    	else{
//    		ToolKits.writeErrorLog("Failed to OperateSplit");
//    	} 
    	
    	/// 10. 设置工作模式
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_SPLIT_OPERATE_SET_WORK_MODE;
//    	inParam = new NET_IN_SPLIT_SET_WORK_MODE();
//    	outParam = new NET_OUT_SPLIT_SET_WORK_MODE();
//    	bRet = INetSDK.OperateSplit(lLoginID, emType, inParam, outParam, nWaitTime);
//    	if (bRet) {
//    		ToolKits.writeLog("OperateSplit OK !!!");
//    	}
//    	else{
//    		ToolKits.writeErrorLog("Failed to OperateSplit");
//    	} 
    	
    	/// 11. 获取播放器实例
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_SPLIT_OPERATE_GET_PLAYER;
//    	inParam = new NET_IN_SPLIT_GET_PLAYER();
//    	outParam = new NET_OUT_SPLIT_GET_PLAYER();
//    	bRet = INetSDK.OperateSplit(lLoginID, emType, inParam, outParam, nWaitTime);
//    	if (bRet) {
//    		ToolKits.writeLog("OperateSplit OK !!!");
//    	}
//    	else{
//    		ToolKits.writeErrorLog("Failed to OperateSplit");
//    	} 
    	
    	/// 12. 设置窗口工作模式
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_WM_OPERATE_SET_WORK_MODE;
//    	inParam = new NET_IN_WM_SET_WORK_MODE();
//    	outParam = new NET_OUT_WM_SET_WORK_MODE();
//    	bRet = INetSDK.OperateSplit(lLoginID, emType, inParam, outParam, nWaitTime);
//    	if (bRet) {
//    		ToolKits.writeLog("OperateSplit OK !!!");
//    	}
//    	else{
//    		ToolKits.writeErrorLog("Failed to OperateSplit");
//    	} 
    	
    	/// 13. 获取窗口工作模式
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_WM_OPERATE_GET_WORK_MODE;
//    	inParam = new NET_IN_WM_GET_WORK_MODE();
//    	outParam = new NET_OUT_WM_GET_WORK_MODE();
//    	bRet = INetSDK.OperateSplit(lLoginID, emType, inParam, outParam, nWaitTime);
//    	if (bRet) {
//    		ToolKits.writeLog("OperateSplit OK !!!");
//    	}
//    	else{
//    		ToolKits.writeErrorLog("Failed to OperateSplit");
//    	} 
    	
    	/// 14. 批量关窗
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_SPLIT_OPERATE_CLOSE_WINDOWS;
//    	inParam = new NET_IN_SPLIT_CLOSE_WINDOWS(5);
//    	outParam = new NET_OUT_SPLIT_CLOSE_WINDOWS(5);
//    	bRet = INetSDK.OperateSplit(lLoginID, emType, inParam, outParam, nWaitTime);
//    	if (bRet) {
//    		ToolKits.writeLog("OperateSplit OK !!!");
//    	}
//    	else{
//    		ToolKits.writeErrorLog("Failed to OperateSplit");
//    	} 
    	
    	/// 15. 设置输出屏的鱼眼矫正规则 
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_WM_OPERATE_SET_FISH_EYE_PARAM;
//    	inParam = new NET_IN_WM_SET_FISH_EYE_PARAM(5);
//    	outParam = new NET_OUT_WM_SET_FISH_EYE_PARAM();
//    	bRet = INetSDK.OperateSplit(lLoginID, emType, inParam, outParam, nWaitTime);
//    	if (bRet) {
//    		ToolKits.writeLog("OperateSplit OK !!!");
//    	}
//    	else{
//    		ToolKits.writeErrorLog("Failed to OperateSplit");
//    	} 
    	
    	/// 16. 设置窗口走廊模式
//    	emType = NET_SPLIT_OPERATE_TYPE.NET_WM_OPERATE_SET_CORRIDOR_MODE;
//    	inParam = new NET_IN_WM_SET_CORRIDOR_MODE();
//    	outParam = new NET_OUT_WM_SET_CORRIDOR_MODE();
//    	bRet = INetSDK.OperateSplit(lLoginID, emType, inParam, outParam, nWaitTime);
//    	if (bRet) {
//    		ToolKits.writeLog("OperateSplit OK !!!");
//    	}
//    	else{
//    		ToolKits.writeErrorLog("Failed to OperateSplit");
//    	} 
    	
    	/// 17. 获取窗口走廊模式
    	emType = NET_SPLIT_OPERATE_TYPE.NET_WM_OPERATE_GET_CORRIDOR_MODE;
    	inParam = new NET_IN_WM_GET_CORRIDOR_MODE();
    	outParam = new NET_OUT_WM_GET_CORRIDOR_MODE();
    	bRet = INetSDK.OperateSplit(lLoginID, emType, inParam, outParam, nWaitTime);
    	if (bRet) {
    		ToolKits.writeLog("OperateSplit OK !!!");
    	}
    	else{
    		ToolKits.writeErrorLog("Failed to OperateSplit");
    	} 
    }
    
    
	public class TestDownLoadPosCallBack implements CB_fDownLoadPosCallBack {
		@Override
		public void invoke(long lPlayHandle, int dwTotalSize, int dwDownLoadSize) {
			ToolKits.writeLog("dwDownLoadSize = " + dwDownLoadSize + "   dwTotalSize = " + dwTotalSize + "\n");	
			int count = 0;
			if(-1 == dwDownLoadSize) {			
				INetSDK.StopDownload(lDownloadHandle);
				ToolKits.writeLog("DownLoad Completed!!!" + "lDownloadHandle = " + lDownloadHandle);	
			}
		}
	}
	private long lDownloadHandle;
    TestDownLoadPosCallBack cbUser = new TestDownLoadPosCallBack();
    int downCount = 0;
    /**
     * 查询备份设备详细信息
     */
 	public void QueryBackupDev()
 	{	
 		// 1.查询设备信息
		SDKDEV_BACKUP_LIST backupDevList = new SDKDEV_BACKUP_LIST(); 	
		boolean aRet = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_BACKUP_DEV, backupDevList, 5000);
		if (aRet)
		{
			ToolKits.writeLog("query backup dev list succeed" + backupDevList.nBackupDevNum + "/" + backupDevList.szBackupDevNames[0]);	 			

			// 2.查询设备详细信息
			SDKDEV_BACKUP_INFO backupDevInfo = new SDKDEV_BACKUP_INFO();
			System.arraycopy(backupDevList.szBackupDevNames[0], 0, backupDevInfo.szName, 0, backupDevList.szBackupDevNames[0].length);
			boolean bRet = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_BACKUP_DEV_INFO, backupDevInfo, 5000);
			if(bRet) {
				ToolKits.writeLog("query backup dev info succeed" + backupDevInfo.szName + backupDevInfo.byType + backupDevInfo.byBus 
						+ backupDevInfo.nCapability + backupDevInfo.nRemain + backupDevInfo.szDirectory);							
			}

			// 查询录像文件信息
			int nChannelId = 0;
			int nRecordFileType = 4;   // 庭审里，按案件查询，为4;  查询录像设为0； 查询图片设为9
			NET_TIME stTimeStart = new NET_TIME();
			stTimeStart.dwYear = 2016;
			stTimeStart.dwMonth = 12;
			stTimeStart.dwDay = 1;
			stTimeStart.dwHour = 12;
			stTimeStart.dwMinute = 0;
			stTimeStart.dwSecond = 0;

			NET_TIME stTimeEnd = new NET_TIME();
			stTimeEnd.dwYear = 2016;
			stTimeEnd.dwMonth = 12;
			stTimeEnd.dwDay = 1;
			stTimeEnd.dwHour = 17;
			stTimeEnd.dwMinute = 0;
			stTimeEnd.dwSecond = 0;

			NET_RECORDFILE_INFO[] stFileInfo =  new NET_RECORDFILE_INFO[20]; //数组大小是查询的录像等的最大值
			for(int i=0; i<stFileInfo.length; i++) {
				stFileInfo[i] = new NET_RECORDFILE_INFO();
			}

			String cardID = null;  // 在庭审项目里，是自定义里的案件内容， 查询其他设为null
			Integer nFileCount = new Integer(0);

			boolean cRet = INetSDK.QueryRecordFile(__LoginHandle, nChannelId, nRecordFileType, stTimeStart, stTimeEnd, cardID, stFileInfo, nFileCount, 5000, false);
			if(cRet) {
				ToolKits.writeLog("QueryRecordFile  Succeed!" + nFileCount.intValue() + "\n");
			}

			// 开始备份
			BACKUP_RECORD backRecord = new BACKUP_RECORD();
			System.arraycopy(backupDevList.szBackupDevNames[0], 0, backRecord.szDeviceName, 0, backRecord.szDeviceName.length);
			backRecord.nRecordNum = 1;
			for(int i=0; i < stFileInfo.length; i++) {
				backRecord.stuRecordInfo[i] = stFileInfo[i];
			}
			backRecord.stuRecordInfo[0].ch = stFileInfo[0].ch;
			System.arraycopy(stFileInfo[0].filename, 0, backRecord.stuRecordInfo[0].filename, 0, backRecord.stuRecordInfo[0].filename.length);
			backRecord.stuRecordInfo[0].size = stFileInfo[0].size;
			backRecord.stuRecordInfo[0].starttime= stFileInfo[0].starttime;
			backRecord.stuRecordInfo[0].endtime = stFileInfo[0].endtime;
			backRecord.stuRecordInfo[0].driveno = stFileInfo[0].driveno;
			backRecord.stuRecordInfo[0].startcluster = stFileInfo[0].startcluster;
			backRecord.stuRecordInfo[0].nRecordFileType = stFileInfo[0].nRecordFileType;
			backRecord.stuRecordInfo[0].bImportantRecID = stFileInfo[0].bImportantRecID;
			backRecord.stuRecordInfo[0].bHint = stFileInfo[0].bHint;
			backRecord.stuRecordInfo[0].bRecType = stFileInfo[0].bRecType;

			boolean dRet = INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_BACKUP_START, backRecord, 10000);
			if(dRet) {
				ToolKits.writeLog("BackupStart Succeed!");
			} else {
				ToolKits.writeErrorLog("BackupStart Failed!" );
			}
			
			// 5.查看备份进度
			SDKDEV_BACKUP_FEEDBACK backupfeedback = new SDKDEV_BACKUP_FEEDBACK();
			System.arraycopy(backupDevList.szBackupDevNames[0], 0, backupfeedback.szName, 0, backupDevList.szBackupDevNames[0].length);
			boolean eRet = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_BACKUP_FEEDBACK, backupfeedback, 5000);
		    if(eRet) {
		    	ToolKits.writeLog("FeedBack Succeed!" + backupfeedback.nCapability + "----" + backupfeedback.nRemain);
		    } else {
		    	ToolKits.writeErrorLog("FeedBack Failed!" );
		    }
		}
 	}

	// 查询录像/下载录像
	public void QueryRecorFile_DownloadRecord() {
		// 司法刻录配置
//		String strCmdJudicature = FinalVar.CFG_CMD_JUDICATURE;
//		CFG_JUDICATURE_INFO judicatureInfo = new CFG_JUDICATURE_INFO();
//		// 获取
//		boolean bJudicatureGet = ToolKits.GetDevConfig(strCmdJudicature, judicatureInfo, __LoginHandle, -1, 1024*1024*2);
//		if(bJudicatureGet) {
//			ToolKits.writeLog("GetJudicature Succeed!" + "\n" + judicatureInfo.nCustomCase + "\n" +
//					"案件名称：" + new String(judicatureInfo.stuCustomCases[1].szCaseTitle).trim() +
//					"案件内容：" + new String(judicatureInfo.stuCustomCases[1].szCaseContent).trim() + "\n");
//		} else {
//			ToolKits.writeErrorLog("GetJudicature Failed!" +INetSDK.GetLastError());
//		}


		// 查询录像文件信息
		int nChannelId = 0;
		int nRecordFileType = 4;   // 庭审里，按案件查询，为4;  查询录像设为0； 查询图片设为9
		NET_TIME stTimeStart = new NET_TIME();
		stTimeStart.dwYear = 2016;
		stTimeStart.dwMonth = 12;
		stTimeStart.dwDay = 1;
		stTimeStart.dwHour = 12;
		stTimeStart.dwMinute = 0;
		stTimeStart.dwSecond = 0;

		NET_TIME stTimeEnd = new NET_TIME();
		stTimeEnd.dwYear = 2016;
		stTimeEnd.dwMonth = 12;
		stTimeEnd.dwDay = 1;
		stTimeEnd.dwHour = 17;
		stTimeEnd.dwMinute = 0;
		stTimeEnd.dwSecond = 0;

		NET_RECORDFILE_INFO[] stFileInfo =  new NET_RECORDFILE_INFO[20]; //数组大小是查询的录像等的最大值
		for(int i=0; i<stFileInfo.length; i++) {
			stFileInfo[i] = new NET_RECORDFILE_INFO();
		}

		String cardID = null;  // 在庭审项目里，是自定义里的案件内容， 查询其他设为null
		Integer nFileCount = new Integer(0);

		boolean cRet = INetSDK.QueryRecordFile(__LoginHandle, nChannelId, nRecordFileType, stTimeStart, stTimeEnd, cardID, stFileInfo, nFileCount, 5000, false);
		if(cRet) {
			ToolKits.writeLog("QueryRecordFile  Succeed!" + nFileCount.intValue() + "\n");
		}


		INetSDK.StopDownload(lDownloadHandle);

		downCount = nFileCount.intValue();
		// 按文件下载(录像一个一个下载)
		for(int j = 0; j < downCount; j++) {
			String filePathName = "/mnt/sdcard/DownNetSDK/" + j + ".dav";
			lDownloadHandle = INetSDK.DownloadByRecordFile(__LoginHandle, stFileInfo[j], filePathName, cbUser);

			if(lDownloadHandle != 0) {
				ToolKits.writeLog("DownloadByRecordFile Succeed!" + "lDownloadHandle = " + lDownloadHandle);
			} else {
				ToolKits.writeErrorLog("DownloadByRecordFile Failed!" );
			}
		}
	}
 	
 	/**
     * 融合屏通道信息
     */
    public void QueryComposite()
	{
        // 1.查询融合屏通道信息
		SDK_COMPOSITE_CHANNEL compositeDevInfo = new SDK_COMPOSITE_CHANNEL();
		boolean bRet = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_COMPOSITE_CHN, compositeDevInfo, 5000);
		if (bRet)
		{
			ToolKits.writeLog("query composite dev info success" + compositeDevInfo);								
		}
		else
		{
			ToolKits.writeErrorLog("query composite dev info err:" );
		}	
		// 2.测试融合屏配置
/*    	String name = "dahua00";
    	String wallName = "dahuaWall00";	
    	String strCmd = FinalVar.CFG_CMD_SPLICESCREEN;
    	AV_CFG_SpliceScreen stParseData = new AV_CFG_SpliceScreen(); 
    	int nBufferLen = 64 * 1024;
    	boolean zRet = ToolKits.GetDevConfig(strCmd, stParseData, __LoginHandle, -1, nBufferLen);
    	if(zRet) {
    		ToolKits.writeLog("QueryConfig Succeed!" + "szName=" + new String(stParseData.szName) + "; szWallName=" + new String(stParseData.szWallName) + 
    				      "; nBlockID=" + String.valueOf(stParseData.nBlockID) +  "; nTop=" +String.valueOf(stParseData.stuRect.nTop) + "; nBottom=" + String.valueOf(stParseData.stuRect.nBottom)
    				       + "; nLeft=" + String.valueOf(stParseData.stuRect.nLeft) + "; nRight=" + String.valueOf(stParseData.stuRect.nRight));
    		
        	System.arraycopy(name.getBytes(), 0, stParseData.szName, 0, name.getBytes().length);
        	System.arraycopy(wallName.getBytes(), 0, stParseData.szWallName, 0, wallName.getBytes().length);
        	stParseData.nBlockID = 1;
        	stParseData.stuRect.nTop = 25;
        	stParseData.stuRect.nBottom = 25;
        	stParseData.stuRect.nLeft = 25;
        	stParseData.stuRect.nRight = 25;
        	boolean bRet = ToolKits.SetDevConfig(strCmd, stParseData, __LoginHandle, 0, nBufferLen);
        	if(bRet) {
        		ToolKits.writeLog("SetConfig Succeed!");
        	} else {
        		ToolKits.writeErrorLog("SetConfig Failed!" ); 
        	}
    	}else{
    		ToolKits.writeErrorLog("QueryConfig Failed!" );
    	}*/
    
    

        // 3.CFG_CMD_DVRIP//  Get //"DVRIP" GetSpliceScreen

/*    	String szID = "55555";
    	String adress = "111.3.16.45";
    	String strCmd = FinalVar.CFG_CMD_DVRIP;
       	CFG_DVRIP_INFO stParseData = new CFG_DVRIP_INFO();
       	int nBufferLen = 64 * 1024;
    	boolean zRet = ToolKits.GetDevConfig(strCmd, stParseData, __LoginHandle, 0, nBufferLen);
    	if(zRet) {
    		ToolKits.writeLog("ConfigGet Succeed!" + String.valueOf(stParseData.nTcpPort) + "; " + String.valueOf(stParseData.nSSLPort) + ";" + String.valueOf(stParseData.nUDPPort) + ";" + String.valueOf(stParseData.nRegistersNum));   
    		
    		stParseData.stuRegisters[0].bEnable = !stParseData.stuRegisters[0].bEnable;
    		System.arraycopy(szID.getBytes(), 0, stParseData.stuRegisters[0].szDeviceID , 0, szID.getBytes().length);
    		stParseData.stuRegisters[0].stuServers[0].nPort = 37006;
    		System.arraycopy(adress.getBytes(), 0, stParseData.stuRegisters[0].stuServers[0].szAddress, 0, adress.getBytes().length);
    		
    		boolean bRet = ToolKits.SetDevConfig(strCmd, stParseData, __LoginHandle, 0, nBufferLen);
    		if(bRet) {
        		ToolKits.writeLog("ConfigSet Succeed!");       
    		}else {
    			ToolKits.writeErrorLog("ConfigSet Failed!" );
    		}
    	} else {
    		ToolKits.writeErrorLog("ConfigGet Failed!" );
    	}*/
    }   
    
    /**
     * 自由开窗
     */
    public void SplitWindow()
 	{
    	// 1.自由开窗
 		SDK_IN_SPLIT_OPEN_WINDOW inOpenWindow = new SDK_IN_SPLIT_OPEN_WINDOW();
     	inOpenWindow.nChannel = 0;
     	inOpenWindow.stuRect.left = 20;
     	inOpenWindow.stuRect.right = 20;
     	inOpenWindow.stuRect.top = 20;
     	inOpenWindow.stuRect.bottom = 20;
     	inOpenWindow.bDirectable = true;
     	SDK_OUT_SPLIT_OPEN_WINDOW outOpenWindow = new SDK_OUT_SPLIT_OPEN_WINDOW();
//     	outOpenWindow.nWindowID = 1;
//     	outOpenWindow.nZOrder = 1;
   	
    	    boolean zRet = INetSDK.OpenSplitWindow(__LoginHandle, inOpenWindow, outOpenWindow, 5000);
			if (zRet)
			{
				ToolKits.writeLog("OpenSplitWindow Succeed!" + inOpenWindow + outOpenWindow);
			
			} else {
				ToolKits.writeErrorLog("OpenSplitWindow Failed!" );
			}
 	
 		// 2.自由关窗
/* 	    SDK_IN_SPLIT_CLOSE_WINDOW inCloseWindow = new SDK_IN_SPLIT_CLOSE_WINDOW();
 	    inCloseWindow.nChannel = 0;
 	    inCloseWindow.nWindowID = 1;
       	SDK_OUT_SPLIT_CLOSE_WINDOW  outCloseWindow = new  SDK_OUT_SPLIT_CLOSE_WINDOW();
 	
 	    boolean zRet = INetSDK.CloseSplitWindow(__LoginHandle, inCloseWindow, outCloseWindow, 5000);
 		if (zRet)
 		{
 				ToolKits.writeLog("CloseSplitWindow Succeed!" + inCloseWindow + outCloseWindow);
 						
 		} else {
 				ToolKits.writeErrorLog("CloseSplitWindow Failed!" );
 			
 		}*/
    
        // 3.设置窗口位置
/*		SDK_IN_SPLIT_SET_RECT inSetRect = new SDK_IN_SPLIT_SET_RECT();
     	inSetRect.nChannel = 0;
     	inSetRect.nWindowID = 1;
     	inSetRect.stuRect.left = 20;
     	inSetRect.stuRect.right = 20;
     	inSetRect.stuRect.top = 20;
     	inSetRect.stuRect.bottom = 20;
     	inSetRect.bDirectable = false;
     	SDK_OUT_SPLIT_SET_RECT outSetRect = new SDK_OUT_SPLIT_SET_RECT();
    	
     	boolean zRet = INetSDK.SetSplitWindowRect(__LoginHandle, inSetRect, outSetRect, 5000);
		if (zRet)
		{
			ToolKits.writeLog("SetSplitWindowRect Succeed!" );
			
		} else {
			ToolKits.writeErrorLog("SetSplitWindowRect Failed!" );
		
		}*/

        // 4.设置窗口次序
/* 		SDK_IN_SPLIT_SET_TOP_WINDOW inSetTop = new SDK_IN_SPLIT_SET_TOP_WINDOW();
     	inSetTop.nChannel = 0;
     	inSetTop.nWindowID = 1;
     	SDK_OUT_SPLIT_SET_TOP_WINDOW outSetTop = new SDK_OUT_SPLIT_SET_TOP_WINDOW(10);
     	outSetTop.pZOders[0].nWindowID = 22222;
     	outSetTop.pZOders[0].nZOrder = 1;
     	outSetTop.nMaxWndCount = 10;
     	outSetTop.nWndCount = 6;
    	
     	boolean zRet = INetSDK.SetSplitTopWindow(__LoginHandle, inSetTop, outSetTop, 5000) ;
		if (zRet)
		{
			ToolKits.writeLog("SetSplitTopWindow Succeed!" );
			
		} else {
			ToolKits.writeErrorLog("SetSplitTopWindow Failed!" );
		}*/

        // 5.获取当前显示的所有窗口信息
/* 	   	SDK_IN_SPLIT_GET_WINDOWS inGetWindow = new SDK_IN_SPLIT_GET_WINDOWS();
     	SDK_OUT_SPLIT_GET_WINDOWS outGetWindow = new SDK_OUT_SPLIT_GET_WINDOWS();
     	boolean zRet = INetSDK.GetSplitWindowsInfo(__LoginHandle, inGetWindow, outGetWindow, 5000); 						if (zRet)
		{
    		   ToolKits.writeLog("GetSplitWindow Succeed!" + "inGetWindow:" + inGetWindow + "outGetWindow:" + outGetWindow);	
		} else {
			ToolKits.writeErrorLog("GetSplitWindow Failed!" );
		}*/
 	}

    /**
     *  矩阵相关接口测试
     *  	1. 电视墙配置		CFG_CMD_MONITORWALL, AV_CFG_MonitorWall
     */
    public void MonitorWall() {
    	long hHandle = __LoginHandle;
    	int nWaitTime = 5000;
    	boolean bRet = false;
    	long hAttach = 0;
    	
//    	/// 1. 电视墙配置
//    	AV_CFG_MonitorWall monitorWall = new AV_CFG_MonitorWall();
//    	String strCmd = FinalVar.CFG_CMD_MONITORWALL;
//    	bRet = ToolKits.GetDevConfig(strCmd, monitorWall, hHandle, -1, 1024*1024*2);
//    	if (!bRet) {
//    		ToolKits.writeErrorLog("Failed to Get CFG_CMD_MONITORWALL");
//    		return;
//    	}
//    	else {
//    		ToolKits.writeLog("Successed to Get CFG_CMD_MONITORWALL");
//		}
    	
//    	AV_CFG_MonitorWall[] mw = new AV_CFG_MonitorWall[18];
//    	for (int i = 0; i < mw.length; i++) {
//			mw[i] = new AV_CFG_MonitorWall();
//		}
//    	 	
//    	boolean result = false;
//    	int nBufferLen = 2*1024*1024;
//    	Integer error = new Integer(0);
//        char szBuffer[] = new char[nBufferLen];
//        int nChn = -1;
//        Integer count = new Integer(0);
//        String strCmd = FinalVar.CFG_CMD_MONITORWALL;
//        if(INetSDK.GetNewDevConfig( hHandle, strCmd , nChn, szBuffer,nBufferLen, error, 10000) )
//        {  
//         	if( INetSDK.ParseData(strCmd ,szBuffer , mw , count ) )
//         	{
//         		result = true;
//         		ToolKits.writeLog("Has Configure " + count);
//         		
//         		result = INetSDK.PacketData(strCmd, mw, szBuffer, nBufferLen);
//         		if (result) {
//         			Integer errorInteger = new Integer(0);
//         			Integer restartInteger = new Integer(0);
//         			INetSDK.SetNewDevConfig(hHandle, strCmd, -1, szBuffer, nBufferLen, errorInteger, restartInteger, 10000);
//         		}		
//         	}
//         } 	
    	
    	/// 1.1 Remote Device
//   	AV_CFG_RemoteDevice remoteDevice = new AV_CFG_RemoteDevice(128);
//    	String strCmd = FinalVar.CFG_CMD_REMOTEDEVICE;
//    	bRet = ToolKits.GetDevConfig(strCmd, remoteDevice, hHandle, -1, 1024*1024*2);
//    	if (!bRet) {
//    		ToolKits.writeErrorLog("Failed to Get CFG_CMD_REMOTEDEVICE, ");
//    		return;
//    	}
//    	else {
//    		ToolKits.writeLog("Successed to Get CFG_CMD_REMOTEDEVICE, ");
//		}
//    	
//    	AV_CFG_RemoteDevice[] mw = new AV_CFG_RemoteDevice[18];
//    	for (int i = 0; i < mw.length; i++) {
//			mw[i] = new AV_CFG_RemoteDevice(128);
//		}
//    	 	
//    	boolean result = false;
//    	int nBufferLen = 2*1024*1024;
//    	Integer error = new Integer(0);
//        char szBuffer[] = new char[nBufferLen];
//        int nChn = -1;
//        Integer count = new Integer(0);
//        String strCmdEx = FinalVar.CFG_CMD_REMOTEDEVICE;
//        if(INetSDK.GetNewDevConfig( hHandle, strCmdEx , nChn, szBuffer, nBufferLen, error, 10000) )
//        {  
//         	if( INetSDK.ParseData(strCmd ,szBuffer , mw , count ) )
//         	{
//         		result = true;
//         		ToolKits.writeLog("Has Configure " + count);
//         		result = INetSDK.PacketData(strCmd, mw, szBuffer, nBufferLen);
//         		if (result) {
//         			Integer errorInteger = new Integer(0);
//         			Integer restartInteger = new Integer(0);
//         			INetSDK.SetNewDevConfig(hHandle, strCmd, -1, szBuffer, nBufferLen, errorInteger, restartInteger, 10000);
//         		}		
//         	}
//         } 		
    	
    	/// 2. 获取窗口位置，针对自由开窗窗口
//    	SDK_IN_SPLIT_GET_RECT pInParam = new SDK_IN_SPLIT_GET_RECT();
//    	SDK_OUT_SPLIT_GET_RECT pOutParam = new SDK_OUT_SPLIT_GET_RECT();
//    	bRet = INetSDK.GetSplitWindowRect(hHandle, pInParam, pOutParam, nWaitTime);
//    	if (!bRet) {
//    		ToolKits.writeErrorLog("Failed to GetSplitWindowRect");
//    	}
    	
    	/// 3. 设置获取显示源 , 参考  TestMatrix
    	/// 4. 获取电视墙预案
//    	SDK_IN_WM_GET_COLLECTIONS inGetCollections = new SDK_IN_WM_GET_COLLECTIONS();
//    	SDK_OUT_WM_GET_COLLECTIONS outGetCollections = new SDK_OUT_WM_GET_COLLECTIONS(10);
//    	bRet = INetSDK.GetMonitorWallCollections(hHandle, inGetCollections, outGetCollections, nWaitTime);
//    	if (!bRet) {
//    		ToolKits.writeErrorLog("Failed to GetMonitorWallCollections");
//    	}
    	
    	/// 5. 加载预案
//    	SDK_IN_WM_LOAD_COLLECTION inLoaCollection = new SDK_IN_WM_LOAD_COLLECTION();
//    	inLoaCollection.pszName = new String("TestCollection");
//    	SDK_OUT_WM_LOAD_COLLECTION outLoaCollection = new SDK_OUT_WM_LOAD_COLLECTION();
//    	bRet = INetSDK.LoadMonitorWallCollection(hHandle, inLoaCollection, outLoaCollection, nWaitTime);
//    	if (!bRet) {
//    		ToolKits.writeErrorLog("Failed to LoadMonitorWallCollection");
//    	}
    	
    	/// 6. 保存预案.
//    	SDK_IN_WM_SAVE_COLLECTION inSaveCollection = new SDK_IN_WM_SAVE_COLLECTION();
//    	inSaveCollection.pszName = new String("TestSaveCollection");
//    	SDK_OUT_WM_SAVE_COLLECTION outSaveCollection = new SDK_OUT_WM_SAVE_COLLECTION();
//    	bRet = INetSDK.SaveMonitorWallCollection(hHandle, inSaveCollection, outSaveCollection, nWaitTime);
//    	if (!bRet) {
//    		ToolKits.writeErrorLog("Failed to SaveMonitorWallCollection");
//    	}
    	
    	/// 7. 预案重命名
//    	SDK_IN_WM_RENAME_COLLECTION inRenameCollection = new SDK_IN_WM_RENAME_COLLECTION();
//    	inRenameCollection.pszOldName = new String("OldName");
//    	inRenameCollection.pszNewName = new String("NewName");
//    	SDK_OUT_WM_RENAME_COLLECTION outRenameCollection =  new SDK_OUT_WM_RENAME_COLLECTION(); 
//    	bRet = INetSDK.RenameMonitorWallCollection(hHandle, inRenameCollection, outRenameCollection, nWaitTime);
//    	if (!bRet) {
//    		ToolKits.writeErrorLog("Failed to SaveMonitorWallCollection");
//    	}
    	
    	/// 8. 获取电视墙场景
//    	SDK_IN_MONITORWALL_GET_SCENE inGetScene = new SDK_IN_MONITORWALL_GET_SCENE();
//    	inGetScene.nMonitorWallID = 0;
//    	SDK_OUT_MONITORWALL_GET_SCENE outGetScene = new SDK_OUT_MONITORWALL_GET_SCENE();
//    	outGetScene.stuScene = new SDK_MONITORWALL_SCENE(20, 20, 5, 5);
//    	bRet = INetSDK.MonitorWallGetScene(hHandle, inGetScene, outGetScene, nWaitTime);
//    	if (!bRet)
//    	{
//    		ToolKits.writeErrorLog("Failed to MonitorWallGetScene");
//    	}
//    	
//    	ToolKits.writeLog("szName" + new String(outGetScene.szName).trim());
    	
    	/// 9. 设置电视墙场景 	
//    	SDK_IN_MONITORWALL_SET_SCENE inSetScene = new SDK_IN_MONITORWALL_SET_SCENE();
//    	inSetScene.stuScene = outGetScene.stuScene;
//    	SDK_OUT_MONITORWALL_SET_SCENE outSetScene = new SDK_OUT_MONITORWALL_SET_SCENE();
//    	bRet = INetSDK.MonitorWallSetScene(hHandle, inSetScene, outSetScene, nWaitTime);
//    	if (!bRet)
//    	{
//    		ToolKits.writeErrorLog("Failed to MonitorWallSetScene");
//    	}
    	
    	/// 10. 设置窗口轮巡显示源
//    	NET_IN_GET_TOUR_SOURCE inTourSource = new NET_IN_GET_TOUR_SOURCE();
//    	NET_OUT_GET_TOUR_SOURCE outTourSource = new NET_OUT_GET_TOUR_SOURCE(20, 5);
//    	bRet = INetSDK.GetTourSource(hHandle, inTourSource, outTourSource, nWaitTime);
//    	if (!bRet)
//    	{
//    		ToolKits.writeErrorLog("Failed to GetTourSource");
//    	}
    	
//    	NET_IN_SET_TOUR_SOURCE inSetTourSource = new NET_IN_SET_TOUR_SOURCE(5);
//    	NET_OUT_SET_TOUR_SOURCE outSetTourSource = new NET_OUT_SET_TOUR_SOURCE();
//    	
//    	bRet = INetSDK.SetTourSource(hHandle, inSetTourSource, outSetTourSource, nWaitTime);
//    	if (!bRet)
//    	{
//    		ToolKits.writeErrorLog("Failed to SetTourSource");
//    	}
    	
    	/// 11. 音频输出模式
//    	SDK_IN_GET_AUDIO_OUTPUT inGetAudio  = new SDK_IN_GET_AUDIO_OUTPUT();
//    	SDK_OUT_GET_AUDIO_OUTPUT outGetAudio = new SDK_OUT_GET_AUDIO_OUTPUT(5);
//    	bRet = INetSDK.GetSplitAudioOuput(hHandle, inGetAudio, outGetAudio, nWaitTime);
//    	if (!bRet)
//    	{
//    		ToolKits.writeErrorLog("Failed to GetSplitAudioOuput");
//    	}
    	
//    	SDK_IN_SET_AUDIO_OUTPUT inSetAudio =  new SDK_IN_SET_AUDIO_OUTPUT();
//    	SDK_OUT_SET_AUDIO_OUTPUT outSetAudio = new SDK_OUT_SET_AUDIO_OUTPUT();
//    	bRet = INetSDK.SetSplitAudioOuput(hHandle, inSetAudio, outSetAudio, nWaitTime);
//    	if (!bRet)
//    	{
//    		ToolKits.writeErrorLog("Failed to SetSplitAudioOuput");
//    	}
    	
    	/// 12. 订阅轮巡状态
//    	NET_IN_ATTACH_SPLIT_TOUR inSplitTour = new NET_IN_ATTACH_SPLIT_TOUR();
//    	inSplitTour.cbStatus = new CB_fTourStatusCallBack() {
//			public void invoke(long lLoginID, long lAttachHandle,
//					NET_SPLIT_TOUR_STATUS_INFO pstStatus) {
//				ToolKits.writeLog("invoke of split tour: " + pstStatus.stuSource.nAudioChannel);
//			}
//    	};
//    	inSplitTour.nChannel = 0;
//    	NET_OUT_ATTACH_SPLIT_TOUR outSplitTour = new NET_OUT_ATTACH_SPLIT_TOUR();
//    	hAttach = INetSDK.AttachSplitTour(hHandle, inSplitTour, outSplitTour, nWaitTime);
//    	if (0 == hAttach){
//    		ToolKits.writeErrorLog("Failed to attach split tour");
//    	}
    	// 取消订阅
    	//INetSDK.DetachSplitTour(hHandle);
    	
    	/// 13. 订阅预案轮巡状态
    	NET_IN_WM_ATTACH_TOUR inWall = new NET_IN_WM_ATTACH_TOUR();
    	inWall.nMonitorWallID = 0;
    	inWall.cbStatus = new CB_fMonitorWallTourStatusCallBack() {

			public void invoke(long lLoginID, long lAttachHandle, NET_WM_TOUR_STATUS_INFO pstStatus) {
				ToolKits.writeLog("Succeed to attach monitorWall tour: " + pstStatus.emStatus);
			}
    	};
    	NET_OUT_WM_ATTACH_TOUR outWall = new NET_OUT_WM_ATTACH_TOUR();
    	long hAttachMonitorWall = INetSDK.MonitorWallAttachTour(hHandle, inWall, outWall, nWaitTime);
    	if(hAttachMonitorWall != 0) {
    		ToolKits.writeLog("MonitorWallAttachTour Succeed!");
    	} else {
    		ToolKits.writeErrorLog("MonitorWallAttachTour Failed" );
    	}
    	
    	// 取消订阅
//    	INetSDK.MonitorWallDetachTour(hAttachMonitorWall);
    	
    	/// 14. 电视墙开关
//    	SDK_IN_WM_POWER_CTRL inCtrlPower = new SDK_IN_WM_POWER_CTRL();
//    	inCtrlPower.bPowerOn = true;
//    	inCtrlPower.nMonitorWallID = 1;
//    	inCtrlPower.nTVID = -1;
//    	inCtrlPower.pszBlockID = null;
//    	SDK_OUT_WM_POWER_CTRL outCtrlPower = new SDK_OUT_WM_POWER_CTRL();
//    	bRet = INetSDK.PowerControl(hHandle, inCtrlPower, outCtrlPower, nWaitTime);
    }

	// 电视墙相关接口
    void OperateMonitorWall()
    {
    	long lLoginID = __LoginHandle;
    	int nWaitTime = 5000;
    	boolean bRet = false;
    	int emType = -1;
    	Object pInParam;
    	Object pOutParam;
    	
    	/// 0. 添加电视墙, 对应 NET_IN_MONITORWALL_ADD 和 NET_OUT_MONITORWALL_ADD
//    	emType = NET_MONITORWALL_OPERATE_TYPE.NET_MONITORWALL_OPERATE_ADD;
//    	pInParam = new NET_IN_MONITORWALL_ADD(5, 5);
//    	pOutParam = new NET_OUT_MONITORWALL_ADD();  	
//    	bRet = INetSDK.OperateMonitorWall(lLoginID, emType, pInParam, pOutParam, nWaitTime);
    	
    	/// 1. 预案轮巡控制, 对应 NET_IN_CTRL_COLLECTIONTOUR 和  NET_OUT_CTRL_COLLECTIONTOUR
//    	emType = NET_MONITORWALL_OPERATE_TYPE.NET_MONITORWALL_OPERATE_CTRL_TOUR;
//    	pInParam = new NET_IN_CTRL_COLLECTIONTOUR();
//    	pOutParam = new NET_OUT_CTRL_COLLECTIONTOUR();	
//    	bRet = INetSDK.OperateMonitorWall(lLoginID, emType, pInParam, pOutParam, nWaitTime);
    	
    	/// 2. 获取矩阵当前状态, 对应 NET_IN_MONITORWALL_GET_STATUS 和  NET_OUT_MONITORWALL_GET_STATUS
//    	emType = NET_MONITORWALL_OPERATE_TYPE.NET_MONITORWALL_OPERATE_GET_STATUS;
//    	pInParam = new NET_IN_MONITORWALL_GET_STATUS();
//    	pOutParam = new NET_OUT_MONITORWALL_GET_STATUS();	
//    	bRet = INetSDK.OperateMonitorWall(lLoginID, emType, pInParam, pOutParam, nWaitTime);
    	
    	/// 3. 设置预案时间表, 对应 NET_IN_MONITORWALL_SET_COLL_SCHD 和 NET_OUT_MONITORWALL_SET_COLL_SCHD
    	emType = NET_MONITORWALL_OPERATE_TYPE.NET_MONITORWALL_OPERATE_SET_COLL_SCHD;
    	pInParam = new NET_IN_MONITORWALL_SET_COLL_SCHD();
    	pOutParam = new NET_OUT_MONITORWALL_SET_COLL_SCHD();	
    	bRet = INetSDK.OperateMonitorWall(lLoginID, emType, pInParam, pOutParam, nWaitTime);
    	
    	/// 4. 获取预案时间表, 对应 NET_IN_MONITORWALL_GET_COLL_SCHD 和 NET_OUT_MONITORWALL_GET_COLL_SCHD
    	emType = NET_MONITORWALL_OPERATE_TYPE.NET_MONITORWALL_OPERATE_GET_COLL_SCHD;
    	pInParam = new NET_IN_MONITORWALL_GET_COLL_SCHD();
    	pOutParam = new NET_OUT_MONITORWALL_GET_COLL_SCHD();	
    	/// 5. 删除电视墙, 对应 NET_IN_MONITORWALL_REMOVE 和 NET_OUT_MONITORWALL_REMOVE
    	emType = NET_MONITORWALL_OPERATE_TYPE.NET_MONITORWALL_OPERATE_REMOVE;
    	pInParam = new NET_IN_MONITORWALL_REMOVE();
    	pOutParam = new NET_OUT_MONITORWALL_REMOVE();	
    	bRet = INetSDK.OperateMonitorWall(lLoginID, emType, pInParam, pOutParam, nWaitTime);
    	
    	/// 6. 设置使能, 对应 NET_IN_MONITORWALL_SET_ENABLE 和 NET_OUT_MONITORWALL_SET_ENABLE
    	emType = NET_MONITORWALL_OPERATE_TYPE.NET_MONITORWALL_OPERATE_SET_ENABLE;
    	pInParam = new NET_IN_MONITORWALL_SET_ENABLE();
    	pOutParam = new NET_OUT_MONITORWALL_SET_ENABLE();	
    	bRet = INetSDK.OperateMonitorWall(lLoginID, emType, pInParam, pOutParam, nWaitTime);
    
    	/// 7. 获取使能, 对应 NET_IN_MONITORWALL_GET_ENABLE 和 NET_OUT_MONITORWALL_GET_ENABLE
    	emType = NET_MONITORWALL_OPERATE_TYPE.NET_MONITORWALL_OPERATE_GET_ENABLE;
    	pInParam = new NET_IN_MONITORWALL_GET_ENABLE();
    	pOutParam = new NET_OUT_MONITORWALL_GET_ENABLE();	
    	bRet = INetSDK.OperateMonitorWall(lLoginID, emType, pInParam, pOutParam, nWaitTime);
    
    	/// 8. 电视墙是否存在, 对应 NET_IN_MONITORWALL_NAME_EXIST 和 NET_OUT_MONITORWALL_NAME_EXIST
    	emType = NET_MONITORWALL_OPERATE_TYPE.NET_MONITORWALL_OPERATE_NAME_EXIST;
    	pInParam = new NET_IN_MONITORWALL_NAME_EXIST();
    	pOutParam = new NET_OUT_MONITORWALL_NAME_EXIST();	
    	bRet = INetSDK.OperateMonitorWall(lLoginID, emType, pInParam, pOutParam, nWaitTime);
    }

    // 热成像相关接口
    void ThermoGraphy() {
    	long lLoginID = __LoginHandle;
    	int nWaitTime = 5000;
    	boolean bRet = false;
    	int nQueryType = -1;

    	/// 1. 查询测温度的参数值
    	nQueryType = FinalVar.NET_QUERY_DEV_RADIOMETRY_POINT_TEMPER;
    	NET_IN_RADIOMETRY_GETPOINTTEMPER inGetpointtemper = new NET_IN_RADIOMETRY_GETPOINTTEMPER();
    	NET_OUT_RADIOMETRY_GETPOINTTEMPER outGetpointtemper = new NET_OUT_RADIOMETRY_GETPOINTTEMPER();
    	bRet = INetSDK.QueryDevInfo(lLoginID, nQueryType, inGetpointtemper, outGetpointtemper, null, nWaitTime);
    	if (!bRet) {
    		ToolKits.writeErrorLog("Query Point Temper Failed!");
    	}
    	else {
    		ToolKits.writeLog("TemperAver: " +outGetpointtemper.stPointTempInfo.fTemperAver);
    	}

    	/// 2. 查询测温项的参数值
    	nQueryType = FinalVar.NET_QUERY_DEV_RADIOMETRY_TEMPER;
    	NET_IN_RADIOMETRY_GETTEMPER inGettemper = new NET_IN_RADIOMETRY_GETTEMPER();
    	NET_OUT_RADIOMETRY_GETTEMPER outGettemper = new NET_OUT_RADIOMETRY_GETTEMPER();

    	bRet = INetSDK.QueryDevInfo(lLoginID, nQueryType, inGettemper, outGettemper, null, nWaitTime);
    	if (!bRet) {
    		ToolKits.writeErrorLog("Query Temper Failed!");
    	}
    	else {
    		ToolKits.writeLog("TemperAver: " +outGettemper.stTempInfo.fTemperAver);
    	}
    }
	
	// 注册升级文件处理回调函数原形
	public class Test_CB_fAttachAIOFileprocCB implements CB_fAttachAIOFileprocCB {
       private String filePath = new String("/mnt/sdcard/NetSDK/");
       private String fileDav;
       private String strFileName;   
       
       @Override
       public void invoke(long lAttachHandle, NET_CB_AIOFILEPROC pBuf,
              byte[] pbFileBuf, int nFileLen) {
           // TODO Auto-generated method stub
           ToolKits.writeLog("emStatus " + pBuf.emStatus);
           ToolKits.writeLog("emType " + pBuf.emType);
           ToolKits.writeLog("dwLength" + pBuf.dwLength);
           ToolKits.writeLog("nFileLen" + nFileLen);
           
           // 开始上传时，建文件夹
	       if(pBuf.emStatus == 1) {
	        	try {
	        	   // 判断输出的文件类型
	 	           if(pBuf.emType == 1) {
	 	              fileDav = new String("callback.zip");
	 	           } else if(pBuf.emType == 2){
	 	              fileDav = new String("callback.apk");
	 	           } 
	 	           
	 	           // 创建文件
					/*
	 	    	   if (createFile(filePath, fileDav)) {
			           strFileName = filePath + fileDav;           
		    	   } 
	 	    	   */
	 	    	   // 创建一个具有指定strFileName文件中写入数据的输出文件流
				   m_Fout = new FileOutputStream(strFileName);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}	
	       }
	       
           if (null != m_Fout) {
                try {
            	   // 输出缓存
                   m_Fout.write(pbFileBuf);
                } catch (Exception e) {
                   e.printStackTrace();
                }
               
                // 传输结束
	            if (pBuf.emStatus == 3) {
	        	   ToolKits.writeLog("upoload end!");
	        	   try {
	        		 // 关闭输出文件流
					 m_Fout.close();
					 m_Fout = null;					
	        	   } catch (IOException e) {
					 e.printStackTrace();
	        	   }
		        } 	           
           }
       }  
	}
	
	private Test_CB_fAttachAIOFileprocCB aioFilecb = new Test_CB_fAttachAIOFileprocCB();
	private static FileOutputStream m_Fout;
	private long lAttachHandle = 0;
	public void AttachAIOFileProc() {
			
		long lLoginID = __LoginHandle;
		int nWaitTime = 5000;
		
    	// 注销升级文件
		if(lAttachHandle != 0) {
			if(INetSDK.detachAIOFileproc(lAttachHandle)) {
				ToolKits.writeLog("detachAIOFileproc succeed!" + "lAttachHandle = " + lAttachHandle);
			}
		}

		// 注册升级文件
		NET_IN_ATTACH_AIO inAio = new NET_IN_ATTACH_AIO();
		inAio.cbNotify = aioFilecb;
		NET_OUT_ATTACH_AIO outAio = new NET_OUT_ATTACH_AIO();
		lAttachHandle = INetSDK.attachAIOFileProc(lLoginID, inAio, outAio, nWaitTime);
    	if(lAttachHandle != 0)	{
    		ToolKits.writeLog("Succeed!" + "lAttachHandle:" + lAttachHandle);
    	}
	}

	// 设置NVR广告
	public void SetAIOAdvert() {
		NET_IN_SET_ADVERT inAdvert = new NET_IN_SET_ADVERT();
		inAdvert.emAction = NET_EM_ADVERT_ACTION.NET_EM_ADVERT_ACTION_START; //start
		//inAdvert.emAction = NET_EM_ADVERT_ACTION.NET_EM_ADVERT_ACTION_STOP; // stop
		inAdvert.nSpeed = 10;
		inAdvert.emPosition = NET_EM_ADVERT_POSITION.NET_EM_ADVERT_POSITION_MIDDLE;
		NET_OUT_SET_ADVERT outAdvert = new NET_OUT_SET_ADVERT();
		long lRet = INetSDK.SetAIOAdvert(__LoginHandle, inAdvert, outAdvert, 3000);
		if(lRet != 0) {
			ToolKits.writeLog("SetAdvert Succeed!");
		} else {
			ToolKits.writeErrorLog("SetAdvert Failed" );
		}
	}

	// NVR截屏
	public void AIOScreen() {
		NET_IN_AIO_SCREEN inScreen  = new NET_IN_AIO_SCREEN();
		NET_OUT_AIO_SCREEN outScreen = new NET_OUT_AIO_SCREEN();
		long lZet = INetSDK.AIOScreen(__LoginHandle, inScreen, outScreen, 3000);
		if(lZet != 0){
			ToolKits.writeLog("AIOScreen Succeed!" + "outScreen.szName = " + outScreen.szName);
		} else {
			ToolKits.writeErrorLog("AIOScreen Failed!" );
		}
	}

	// 渝北智慧天网参数设置
	public void AIOAppConfig() {
		CFG_AIO_APP_CONFIG_INFO aioAppConfigInfo = new CFG_AIO_APP_CONFIG_INFO();
		String strCmd = FinalVar.CFG_CMD_AIO_APP_CONFIG;
		boolean bRet = ToolKits.GetDevConfig(strCmd, aioAppConfigInfo, __LoginHandle, -1, 1024*1024*2);
		if (!bRet) {
			ToolKits.writeErrorLog("Failed to AioAppConfigInfo!" );
		}
		else {
			ToolKits.writeLog("Successed to AioAppConfigInfo! \n" + new String(aioAppConfigInfo.szAddress).trim() + "\n");
		}


		boolean bRet1 = ToolKits.SetDevConfig(strCmd, aioAppConfigInfo, __LoginHandle, -1, 1024*1024*2);
		if(bRet1) {
			ToolKits.writeLog("Successed to AioAppConfigInfo! \n" + new String(aioAppConfigInfo.szAddress).trim());
		} else {
			ToolKits.writeErrorLog("Failed to AioAppConfigInfo!" );
		}
	}

	// 同步文件上传
	void UploadRemoteFile() {
    	boolean bRet = false;
   	
		// 文件上传
    	SDK_IN_UPLOAD_REMOTE_FILE inUploadFile = new SDK_IN_UPLOAD_REMOTE_FILE();
    	inUploadFile.pszFileDst = new String("TestNetSDK.apk");
    	inUploadFile.pszFolderDst = new String("/mnt/lv/ReservedNAS/");
    	inUploadFile.pszFileSrc = new String("/mnt/sdcard/TestNetSDK.apk");
    	inUploadFile.nPacketLen = 512; 
    	SDK_OUT_UPLOAD_REMOTE_FILE outUploadFile = new SDK_OUT_UPLOAD_REMOTE_FILE();
    	
    	bRet = INetSDK.UploadRemoteFile(__LoginHandle, inUploadFile, outUploadFile, 4000);
    	if (bRet) {
    		ToolKits.writeLog("upload Remote ok");
    	}else {
    		ToolKits.writeErrorLog("upload Remote Error");
    		return;
    	}
    }

	// 文件下载, 只适用于小文件
	public void DownloadRemoteFile() {
		SDK_IN_DOWNLOAD_REMOTE_FILE inDFile = new SDK_IN_DOWNLOAD_REMOTE_FILE();
		inDFile.pszFileDst = new String("/mnt/sdcard/screen.jpg");
		inDFile.pszFileName = new String("/var/screen.jpg");
		SDK_OUT_DOWNLOAD_REMOTE_FILE outDFile = new SDK_OUT_DOWNLOAD_REMOTE_FILE();

		boolean bRet = INetSDK.DownloadRemoteFile(__LoginHandle, inDFile, outDFile, 4000);
		if (bRet) {
			ToolKits.writeLog("download remote ok");
		}else {
			ToolKits.writeErrorLog("download Remote Error");
		}
	}
	
	// 人脸对比
	void MatchTwoFaceImage() {
		String oriSDKPath = Environment.getExternalStorageDirectory().toString();  // 获取原始图片在SD卡里的路径
		String compSDKPath = Environment.getExternalStorageDirectory().toString(); // 获取对比图片在SD卡里的路径
				
		String oriImage = oriSDKPath + "/" + "1.jpg"; 
		String compImage = compSDKPath + "/" +  "2.jpg";
		
		Bitmap oribm = BitmapFactory.decodeFile(oriImage);   // 读取原始图片
		Bitmap compbm = BitmapFactory.decodeFile(compImage); // 读取对比图片
		
		NET_MATCH_TWO_FACE_IN matchTwoFaceIn = new NET_MATCH_TWO_FACE_IN();	
		
		// 原始图片的宽高
		matchTwoFaceIn.stuOriginalImage.dwWidth  = oribm.getWidth();
		matchTwoFaceIn.stuOriginalImage.dwHeight = oribm.getHeight();

		// 对比图片的宽高
		matchTwoFaceIn.stuCompareImage.dwWidth = compbm.getWidth();
		matchTwoFaceIn.stuCompareImage.dwHeight = compbm.getHeight();
		
		matchTwoFaceIn.OriginalImageName = oriImage;   // 原始图片在手机里的路径
		matchTwoFaceIn.compareImageName = compImage;   // 对比图片在手机里的路径
		
		NET_MATCH_TWO_FACE_OUT matchTwoFaceOut = new NET_MATCH_TWO_FACE_OUT();
		
		boolean bRet = INetSDK.MatchTwoFaceImage(__LoginHandle, matchTwoFaceIn, matchTwoFaceOut, 5000);
		
		if(bRet) {
			ToolKits.writeLog("Match Two Face Succeed!" + (matchTwoFaceOut.nSimilarity)/100 + "原图宽高：" + oribm.getWidth() + "-" + oribm.getHeight() + "/ 对比图宽高：" + compbm.getWidth() + "-" + compbm.getHeight());   // 输出相似度
		} else {
			ToolKits.writeErrorLog("Match Two Face Failed!" );
		}
	}

	// 打靶相机智能规则
	void ShootingRuleConfig() {
		boolean bRet = false;
		String szCommand = FinalVar.CFG_CMD_ANALYSERULE;
		CFG_ANALYSERULES_INFO analyserules = new CFG_ANALYSERULES_INFO(10);
		
		// 获取
		bRet = ToolKits.GetDevConfig(szCommand, analyserules, __LoginHandle, 0, 2*1024*1024);
		if(bRet) {
			ToolKits.writeLog("GetNewDevConfig Succeed!" + "\n" + 
							  "事件规则个数：" + analyserules.nRuleCount );			
			
			for(int i = 0; i < analyserules.nRuleCount; i++) {
				ToolKits.writeLog("智能事件类型: " + String.format("%x", analyserules.pRuleBuf[i].dwRuleType));
				ToolKits.writeLog("事规则编号：" + analyserules.pRuleBuf[i].stuRuleCommInfo.bRuleId);
				ToolKits.writeLog("规则所属的场景：" + analyserules.pRuleBuf[i].stuRuleCommInfo.emClassType);			
				
				if(analyserules.pRuleBuf[i].dwRuleType == FinalVar.EVENT_IVS_SHOOTINGSCORERECOGNITION) {
		
					CFG_IVS_SHOOTINGSCORERECOGNITION_INFO ivsShooting = (CFG_IVS_SHOOTINGSCORERECOGNITION_INFO)analyserules.pIvsRuleBuf[i];

					ToolKits.writeLog("规则名称：" + new String(ivsShooting.szRuleName).trim() + "\n" + 
								      "规则使能: " + ivsShooting.bRuleEnable + "\n" + 
									  "相应物体类型个数 : " + ivsShooting.nObjectTypeNum + 
									  "弹孔口径 ：" + ivsShooting.nCaliber);	

					for(int j = 0; j < ivsShooting.nObjectTypeNum; j++) {
						ToolKits.writeLog("相应物体类型列表：" + new String(ivsShooting.szObjectTypes[j]).trim());
					}
					
					// 配置
					ivsShooting.bRuleEnable = true;
					if(ivsShooting.nCaliber == 0) {
						ivsShooting.nCaliber = 1;
					} else if(ivsShooting.nCaliber == 1){
						ivsShooting.nCaliber = 2;
					} else {
						ivsShooting.nCaliber = 0;
					}
				}			
			}

			boolean bRet2 = ToolKits.SetDevConfig(szCommand, analyserules, __LoginHandle, 0, 2*1024*1024);
			if(bRet2) {
				ToolKits.writeLog("setNewDevConfig Succeed!");
			} else {
				ToolKits.writeErrorLog("setNewDevConfig Failed!" );
			}
		} else {
			ToolKits.writeErrorLog("GetNewDevConfig Failed!" );
		}
	}

	// 查询门禁状态
	public void QueryDoorState() {
		NET_DOOR_STATUS_INFO doorstatus = new NET_DOOR_STATUS_INFO();
		boolean bRet = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_DOOR_STATE, doorstatus, 3000);
		if(bRet) {
			ToolKits.writeLog("Query DoorStatus Succeed!" + "\n" + doorstatus.nChannel + "\n" + doorstatus.emStateType);
		} else {
			ToolKits.writeErrorLog("Query DoorStatus Failed!" );
		}
	}

	// 查询录像状态
	public void QueryRecordState() {
		int nType = FinalVar.SDK_DEVSTATE_RECORDING;
		byte[] devRecord = new byte[16];
		boolean bRet = INetSDK.QueryDevState(__LoginHandle, nType, devRecord, 5000);
		if(bRet) {
			ToolKits.writeLog("QueryDevice Succeed!");
			for(int i=0; i<16; i++) {
				ToolKits.writeLog(i + " : " + devRecord[i]);
			}
		} else {
			ToolKits.writeErrorLog("QueryDevice Failed!" );
		}
	}

	// 普通配置
	public void GeneralConfig() {
		String strCmd = FinalVar.CFG_CMD_DEV_GENERRAL;
		CFG_DEV_DISPOSITION_INFO devDisposition = new CFG_DEV_DISPOSITION_INFO();
		boolean bRet = ToolKits.GetDevConfig(strCmd, devDisposition, __LoginHandle, 0, 1024*1024*2);
		if (!bRet) {
			ToolKits.writeErrorLog("Failed to GetDevDisposition!" );
		}
		else {
			ToolKits.writeLog("Succeed to GetDevDisposition" + "\n" + devDisposition.nLocalNo + "\n" +
					new String(devDisposition.szMachineName).trim() + "\n" +
					new String(devDisposition.szMachineAddress).trim() + "\n" +
					devDisposition.nLockLoginTimes + "\n");

			// 设置
			bRet = false;
			bRet = ToolKits.SetDevConfig(strCmd, devDisposition, __LoginHandle, 0, 1024*1024*2);
			if(bRet) {
				ToolKits.writeLog("Succeed to SetDevDisposition");
			} else {
				ToolKits.writeErrorLog("Failed to SetDevDisposition" );
			}
		}
	}

	// 物体的检测模块配置
	public void AnalyseModuleConfig() {
		CFG_ANALYSEMODULES_INFO modules = new CFG_ANALYSEMODULES_INFO();
		String strCmdmodule = FinalVar.CFG_CMD_ANALYSEMODULE;
		boolean bRet = ToolKits.GetDevConfig(strCmdmodule, modules, __LoginHandle, 0, 1024*1024*2);
		if (!bRet) {
			ToolKits.writeErrorLog("Failed to GetModules!" );
		}
		else {
			ToolKits.writeLog("Succeed to GetModules" + "\n" +
					"检测模块数 : " + modules.nMoudlesNum + "\n");
			for(int i = 0 ; i < modules.nMoudlesNum; i++) {
				ToolKits.writeLog("检测区域顶点数 : " + modules.stuModuleInfo[i].nDetectRegionPoint + "\n" + "检测区域 : " + "\n");
				for(int j = 0; j < modules.stuModuleInfo[i].nDetectRegionPoint; j++) {
					ToolKits.writeLog(modules.stuModuleInfo[i].stuDetectRegion[j].nX + "\n" +
							modules.stuModuleInfo[i].stuDetectRegion[j].nY + "\n");
				}
			}
		}
	}

	// 智能跟踪规则配置
	public void LetrackRuleConfig() {
		String szCommandrule = FinalVar.CFG_CMD_ANALYSERULE;
		CFG_ANALYSERULES_INFO analyserule = new CFG_ANALYSERULES_INFO(10);

		// 获取
		boolean bRet1 = ToolKits.GetDevConfig(szCommandrule, analyserule, __LoginHandle, 0, 2*1024*1024);
		if(bRet1) {
			ToolKits.writeLog("GetNewDevConfig Succeed!" + "\n" +
					"事件规则个数：" + analyserule.nRuleCount );

			for(int i = 0; i < analyserule.nRuleCount; i++) {
				ToolKits.writeLog("事件类型: " + String.format("%x", analyserule.pRuleBuf[0].dwRuleType));
				ToolKits.writeLog("事规则编号：" + analyserule.pRuleBuf[i].stuRuleCommInfo.bRuleId);
				ToolKits.writeLog("规则所属的场景：" + analyserule.pRuleBuf[i].stuRuleCommInfo.emClassType);

				if(analyserule.pRuleBuf[i].dwRuleType == FinalVar.EVENT_IVS_LETRACK) {
					CFG_IVS_LETRACK_INFO ivsLretack = (CFG_IVS_LETRACK_INFO)analyserule.pIvsRuleBuf[i];
					ToolKits.writeLog("规则名称：" + new String(ivsLretack.szRuleName).trim() + "\n" +
							"规则使能: " + ivsLretack.bRuleEnable);

					// 配置
					if(ivsLretack.bRuleEnable == true) {
						ivsLretack.bRuleEnable = false;
					} else {
						ivsLretack.bRuleEnable = true;
					}
					for(int m=0; m<FinalVar.WEEK_DAY_NUM; m++){
						for(int n=0; n<FinalVar.MAX_REC_TSECT_EX; n++){
							ivsLretack.stuTimeSection[m][n].dwRecordMask = 22; // 录像掩码
							ivsLretack.stuTimeSection[m][n].nBeginHour = 1;
							ivsLretack.stuTimeSection[m][n].nBeginMin = 0;
							ivsLretack.stuTimeSection[m][n].nBeginSec = 0;
							ivsLretack.stuTimeSection[m][n].nEndHour = 11;
							ivsLretack.stuTimeSection[m][n].nEndMin = 1;
							ivsLretack.stuTimeSection[m][n].nEndSec = 1;
						}
					}
				}
			}

			boolean bRet2 = ToolKits.SetDevConfig(szCommandrule, analyserule, __LoginHandle, 0, 2*1024*1024);
			if(bRet2) {
				ToolKits.writeLog("setNewDevConfig Succeed!");
			} else {
				ToolKits.writeErrorLog("setNewDevConfig Failed!" );
			}
		} else {
			ToolKits.writeErrorLog("GetNewDevConfig Failed!" );
		}
	}

	// 物联网红外检测配置
	public void IOT_InfraredDetectConfig() {
		CFG_IOT_INFRARED_DETECT_INFO ioInfraredtDetect = new CFG_IOT_INFRARED_DETECT_INFO();
		String strCmddetect = FinalVar.CFG_CMD_IOT_INFRARED_DETECT;

		// 获取
		boolean bRet = ToolKits.GetDevConfig(strCmddetect, ioInfraredtDetect, __LoginHandle, -1, 1024*1024*2);
		if (!bRet) {
			ToolKits.writeErrorLog("Failed to GetInfraredtDetect!" );
		}
		else {
			ToolKits.writeLog("Successed to GetInfraredtDetect! \n" + ioInfraredtDetect.nDetectRadius + "\n");
			for(int i=0; i<FinalVar.MAX_AREA_COUNT; i++) {
				ToolKits.writeLog("获取的：" + ioInfraredtDetect.bEnable[i] + "\n" );
			}

			// 设置
			bRet = false;
			ioInfraredtDetect.bEnable[0] = true;
			ioInfraredtDetect.nDetectRadius = 15;
			bRet = ToolKits.SetDevConfig(strCmddetect, ioInfraredtDetect, __LoginHandle, -1, 1024*1024*2);
			if(bRet) {
				ToolKits.writeLog("Successed to SetInfraredtDetect! \n" + ioInfraredtDetect.nDetectRadius + "\n" + ioInfraredtDetect.bEnable[0]);
			} else {
				ToolKits.writeErrorLog("Failed to SetInfraredtDetect!" );
			}
		}
	}

	// 物联网录像联动配置
	public void IOT_RecordHandleConfig() {
		CFG_IOT_RECORD_HANDLE_INFO iotRecordHandle = new CFG_IOT_RECORD_HANDLE_INFO();
		String strCmdrecord = FinalVar.CFG_CMD_IOT_RECORD_HANDLE;

		// 获取
		boolean bRet = ToolKits.GetDevConfig(strCmdrecord, iotRecordHandle, __LoginHandle, -1, 1024*1024*2);
		if (!bRet) {
			ToolKits.writeErrorLog("Failed to GetRecordHandle!");
		}
		else {
			ToolKits.writeLog("Successed to GetRecordHandle! \n");
			ToolKits.writeLog("获取的：" + iotRecordHandle.bEnable + "\n" + iotRecordHandle.nRecordTime + "\n");

			// 设置
			bRet = false;
			iotRecordHandle.bEnable = true;
			iotRecordHandle.nRecordTime = 25;
			bRet = ToolKits.SetDevConfig(strCmdrecord, iotRecordHandle, __LoginHandle, -1, 1024*1024*2);
			if(bRet) {
				ToolKits.writeLog("Successed to SetRecordHandle! \n");
				ToolKits.writeLog("设置的：" + iotRecordHandle.bEnable + "\n" + iotRecordHandle.nRecordTime + "\n");
			} else {
				ToolKits.writeErrorLog("Failed to SetRecordHandle!");
			}
		}
	}

	// 物联网抓图联动配置
	public void IOT_SnapHandleConfig() {
		CFG_IOT_SNAP_HANDLE_INFO iotSnapHandle = new CFG_IOT_SNAP_HANDLE_INFO();
		String strCmdsnap = FinalVar.CFG_CMD_IOT_SNAP_HANDLE;

		// 获取
		boolean bRet = ToolKits.GetDevConfig(strCmdsnap, iotSnapHandle, __LoginHandle, -1, 1024*1024*2);
		if (!bRet) {
			ToolKits.writeErrorLog("Failed to GetSnapHandle!");
		}
		else {
			ToolKits.writeLog("Successed to GetSnapHandle! \n");
			ToolKits.writeLog("获取的：" + iotSnapHandle.bEnable + "\n" + iotSnapHandle.nSnapNum + "\n");

			// 设置
			bRet = false;
			iotSnapHandle.bEnable = true;
			iotSnapHandle.nSnapNum = 6;
			bRet = ToolKits.SetDevConfig(strCmdsnap, iotSnapHandle, __LoginHandle, -1, 1024*1024*2);
			if(bRet) {
				ToolKits.writeLog("Successed to SetSnapHandle! \n");
				ToolKits.writeLog("设置的：" + iotSnapHandle.bEnable + "\n" + iotSnapHandle.nSnapNum + "\n");
			} else {
				ToolKits.writeErrorLog("Failed to SetSnapHandle!");
			}
		}
	}

	// 获取门铃列表
	public void GetRingFileList() {
		NET_IN_GET_RINGFILELIST inGetRingfilelist = new NET_IN_GET_RINGFILELIST();
		NET_OUT_GET_RINGFILELIST outGetRingfilelist = new NET_OUT_GET_RINGFILELIST();

		boolean bRet = INetSDK.GetRingFileList(__LoginHandle, inGetRingfilelist, outGetRingfilelist, 3000);
		if(bRet) {
			ToolKits.writeLog("文件个数：" + outGetRingfilelist.nRingFileNum + "\n" );
			for(int i=0; i<outGetRingfilelist.nRingFileNum; i++) {
				ToolKits.writeLog(i + " : " + new String(outGetRingfilelist.szRingFileList[i]).trim());
			}
		} else {
			ToolKits.writeErrorLog("GetRingFileList Failed!");
		}
	}

	// Qsee 门铃音量和铃声配置
	public void DoorBellSoundConfig() {
		CFG_DOOR_BELLSOUND_INFO doorSound = new CFG_DOOR_BELLSOUND_INFO();
		String strCmdbell = FinalVar.CFD_CMD_DOORBELLSOUND;
		// 获取
		boolean bRet = ToolKits.GetDevConfig(strCmdbell, doorSound, __LoginHandle, -1, 1024*1024*2);
		if (!bRet) {
			ToolKits.writeErrorLog("Failed to GetDoorSound!");
			return;
		}
		else {
			ToolKits.writeLog("Successed to GetDoorSound! \n");
			ToolKits.writeLog(doorSound.bSilenceEnable + "\n" + doorSound.nRingVolume + "\n");
			ToolKits.writeLog(new String(doorSound.szRingFile).trim());

			// 设置
			bRet = false;
			doorSound.bSilenceEnable = true;
			doorSound.nRingVolume = 60;
			String Ring = "f_test";
			for (int i = 0; i < doorSound.szRingFile.length; i++) {
				doorSound.szRingFile[i] = 0;
			}
			System.arraycopy(Ring.getBytes(), 0, doorSound.szRingFile, 0, Ring.getBytes().length);
			bRet = ToolKits.SetDevConfig(strCmdbell, doorSound, __LoginHandle, -1, 1024*1024*2);
			if(bRet) {
				ToolKits.writeLog("Successed to SetDoorSound! \n");
				ToolKits.writeLog(doorSound.bSilenceEnable + "\n" + doorSound.nRingVolume + "\n");
				ToolKits.writeLog(new String(doorSound.szRingFile).trim());

			} else {
				ToolKits.writeErrorLog("Failed to SetDoorSound!");
			}
		}
	}
    
    /**
     * 网络自适应
     */
    void TestNetPolicy() {
    	long lLoginHandle = __LoginHandle;
    	long lPlayHandle = 0;
    	int nWaitTime = 3000;
    	boolean bRet = false;
    	
    	/// 1. 码流缓冲策略
    	// lPlayHandle 为 拉流返回的句柄
    	 lPlayHandle = INetSDK.StartRealPlay(lLoginHandle, -1, SDK_RealPlayType.SDK_RType_Realplay, null, nWaitTime);
    	if (0 != lPlayHandle) {
        	NET_IN_BUFFER_POLICY policy = new NET_IN_BUFFER_POLICY();
        	policy.emRealPlayType = SDK_RealPlayType.SDK_RType_Realplay_Test; // 自适应测试码流
        	policy.nPolicy = 1; // 流畅
        	
        	bRet = INetSDK.SetRealplayBufferPolicy(lPlayHandle, policy, nWaitTime);
        	if (!bRet) {
        		ToolKits.writeErrorLog("Failed to Set");
        	}
    	}
    	
    	/// 2. Https 服务配置
    	CFG_HTTPS_INFO cmdObject = new CFG_HTTPS_INFO();
    	String strCmd = FinalVar.CFG_CMD_HTTPS;
    	bRet = ToolKits.GetDevConfig(strCmd, cmdObject, lLoginHandle, -1, 1024*10);
    	if (!bRet) {
    		ToolKits.writeErrorLog("Failed to GetHttps");
    	}else {
    		cmdObject.bEnable = true;
    		System.arraycopy("China".getBytes(), 0, cmdObject.szCountry, 0, "China".length());
    		
    		ToolKits.SetDevConfig(strCmd, cmdObject, lLoginHandle, -1, 1024*10);   		
    	}
    	
    	/// 3. 网络自适应编码配置(接口调用同上2)
    	///    -> strCmd = CFG_CMD_NETAUTOADAPTORENCODE
    	///    -> cmdObject = CFG_NET_AUTO_ADAPT_ENCODE
    	CFG_NET_AUTO_ADAPT_ENCODE cmdObjectEncode = new CFG_NET_AUTO_ADAPT_ENCODE();
    	String strCmdEncode = FinalVar.CFG_CMD_NETAUTOADAPTORENCODE;
    	bRet = ToolKits.GetDevConfig(strCmdEncode, cmdObjectEncode, lLoginHandle, -1, 1024*10);
    	if (!bRet) {
    		ToolKits.writeErrorLog("Failed to Get auto");
    	}else {	
    		bRet = ToolKits.SetDevConfig(strCmdEncode, cmdObjectEncode, lLoginHandle, -1, 1024*10);
    		if (!bRet) {
    			ToolKits.writeErrorLog("Failed to auto");
    		}
    	}
    	
    	/// 4. 获取编码自适应编码能力
    	///    -> szCommand = CFG_CAP_CMD_ADAPTENCODE 
    	///    -> cmdObject =  CFG_CAP_ADAPT_ENCODE_INFO
    	
		Integer stErr = new Integer(0);
		String szCommand = FinalVar.CFG_CAP_CMD_ADAPTENCODE;
		char[] szOutBuffer = new char[1024*10];
		CFG_CAP_ADAPT_ENCODE_INFO stuCaps = new CFG_CAP_ADAPT_ENCODE_INFO();
		bRet = INetSDK.QueryNewSystemInfo(lLoginHandle, szCommand, NetSDKApplication.getInstance().getDeviceInfo().nChanNum, szOutBuffer, stErr, nWaitTime);
		if (bRet) {
			bRet = INetSDK.ParseData(szCommand, szOutBuffer, stuCaps, null);
			if (!bRet) {
				ToolKits.writeErrorLog(" CFG_CAP_CMD_ADAPTENCODE error");
			}
		}
    }
    
    // 报警消除
    void ReleaseAlaram() {
    	long lLoginHandle = __LoginHandle;
    	int nWaitTime = 3000;
    	
    	NET_CTRL_CLEAR_ALARM info = new NET_CTRL_CLEAR_ALARM();
        info.emAlarmType = NET_ALARM_TYPE.NET_ALARM_ALL; // 所有报警
        info.nChannelID = -1; // 所有通道

        boolean bRet = INetSDK.ControlDevice(lLoginHandle, CtrlType.SDK_CTRL_CLEAR_ALARM, info, nWaitTime);
        if (!bRet) {
        	ToolKits.writeErrorLog(" release error");
        }
    }

	// 查询智能插座信息
	public void QuerySmartSwitchInfo() {
		/// 获取电量信息
		NET_IN_SMART_SWITCH_INFO stInSwitch = new NET_IN_SMART_SWITCH_INFO();
		NET_OUT_SMART_SWITCH_INFO stOutSwitch = new NET_OUT_SMART_SWITCH_INFO();

		String sn = "my_test_sn";
		System.arraycopy(sn.getBytes(), 0, stInSwitch.szSerialNumber, 0, sn.length());

		boolean bRet = INetSDK.QueryDevInfo(__LoginHandle, FinalVar.NET_QUERY_SMART_SWITCH_INFO, stInSwitch, stOutSwitch, null, 3000);
		if (!bRet)
		{
			ToolKits.writeErrorLog("Smart Switch");
			return;
		}

		ToolKits.writeLog("State: " +stOutSwitch.bSwitchEable);
		ToolKits.writeLog("Current Power: " + stOutSwitch.dbCurrentPower);
		ToolKits.writeLog("History Power: " + stOutSwitch.dbHistoryPowerUsed);
		ToolKits.writeLog("Today Power: " + stOutSwitch.dbTodayPowerUsed);
		for (int i = 0; i < 31 && (stOutSwitch.dbMonthPowerUsed[i] != 0.0); ++i)
		{
			ToolKits.writeLog("Month ["+ i + 1+"] Power： " +stOutSwitch.dbMonthPowerUsed[i]);
		}
	}

	// 控制智能开关
    void ControlSmartSwitch() {
        NET_IN_CONTROL_SMART_SWITCH stInCtl = new NET_IN_CONTROL_SMART_SWITCH();
        stInCtl.bEnable = true;
        stInCtl.nDelayTime = 45;
        
        String ctrlSN = "Control SN";
        System.arraycopy(ctrlSN.getBytes(), 0, stInCtl.szSerialNumber, 0, ctrlSN.length());
        
        NET_OUT_CONTROL_SMART_SWITCH stOutCtl = new NET_OUT_CONTROL_SMART_SWITCH();
        boolean bRetCtrl = INetSDK.ControlDeviceEx(__LoginHandle, CtrlType.SDK_CTRL_CONTROL_SMART_SWITCH, stInCtl, stOutCtl, 5000);
        if (!bRetCtrl)
        {
            ToolKits.writeErrorLog("Ctrl Smart Switch");
        }
    }

	// 查询SD卡信息(IPC类产品)
	public void QueryDevStateSDCard() {
		SDK_HARDDISK_STATE mDiskState = new SDK_HARDDISK_STATE();
		if(INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_SD_CARD, mDiskState, 5000)) {
			ToolKits.writeLog("dwDiskNum : " + mDiskState.dwDiskNum);
			for(int i=0; i<mDiskState.dwDiskNum; i++) {
				ToolKits.writeLog("dwVolume : " + mDiskState.stDisks[i].dwVolume);
				ToolKits.writeLog("dwFreeSpace : " + mDiskState.stDisks[i].dwFreeSpace);
				ToolKits.writeLog("dwStatus : " + mDiskState.stDisks[i].dwStatus);
				ToolKits.writeLog("bDiskNum : " + mDiskState.stDisks[i].bDiskNum);
				ToolKits.writeLog("bSubareaNum : " + mDiskState.stDisks[i].bSubareaNum);
				ToolKits.writeLog("bSignal : " + mDiskState.stDisks[i].bSignal);
			}
		}

		// 硬盘管理
		DISKCTRL_PARAM param = new DISKCTRL_PARAM();
		param.nIndex = 0;
		param.ctrlType = 0;
		param.stuDisk = mDiskState.stDisks[0];
		if(INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_DISK, param, 4000)) {
			ToolKits.writeLog("Succeed!");
		} else {
			ToolKits.writeErrorLog("Failed!" );
		}
	}

	// 开机向导流程，接入公网、在线升级检测等功能
    void InitDevAccess(){
		boolean bRet = false;
    	//开机向导流程，接入公网、在线升级检测等功能
    	NET_IN_INIT_DEVICE_ACCESS inInitAccess = new NET_IN_INIT_DEVICE_ACCESS();
    	String mac = "3c:ef:8c:ed:d4:1a";
    	System.arraycopy(mac.getBytes(), 0, inInitAccess.szMac, 0, mac.getBytes().length);
    	String name = "admin";
    	System.arraycopy(name.getBytes(), 0, inInitAccess.szUserName, 0, name.getBytes().length);
    	String passwd = "admin";
    	System.arraycopy(passwd.getBytes(), 0, inInitAccess.szPwd, 0, passwd.getBytes().length);

    	inInitAccess.byNetAccess = 0;
    	inInitAccess.byUpgradeCheck = 0;

    	NET_OUT_INIT_DEVICE_ACCESS outInitAccess = new NET_OUT_INIT_DEVICE_ACCESS();

    	bRet = INetSDK.InitDevAccess(inInitAccess, outInitAccess, 10000, null);
    	if(bRet) {
    		ToolKits.writeLog("InitDevAccess Succeed!");
    	} else {
    		ToolKits.writeErrorLog("InitDevAccess Failed!" );
    	}
    }

	// 格式化备份设备
	public void FormatDevice() {
		// 获取存储设备名称
		boolean bRet = false;
		SDK_STORAGE_DEVICE_NAME[] pstuNames = new SDK_STORAGE_DEVICE_NAME[10];
		for(int i=0; i<pstuNames.length; i++) {
			pstuNames[i] = new SDK_STORAGE_DEVICE_NAME();
		}

		Integer pnRetCount = new Integer(0);
		bRet = INetSDK.GetStorageDeviceNames(__LoginHandle, pstuNames, pnRetCount, 3000);

		if(bRet) {
			ToolKits.writeLog("存储设备数量 : " + pnRetCount);
			for(int i=0; i<pnRetCount; i++) {
				ToolKits.writeLog("存储设备名称 = " + new String(pstuNames[i].szName).trim());

				// 格式化备份设备
				String devicesName = new String(pstuNames[i].szName).trim();
				char[] charNme = devicesName.toCharArray();//new char[devicesName.getBytes().length];
				if(INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_BACKUP_FORMAT, charNme, 5000)) {
					ToolKits.writeLog("格式化成功");
				} else {
					ToolKits.writeErrorLog("格式化失败");
				}
			}
		}
	}

	// 格式化分区
	public void FormatPatition() {
		// 获取存储设备名称
		boolean bRet = false;
		SDK_STORAGE_DEVICE_NAME[] pstuNames = new SDK_STORAGE_DEVICE_NAME[10];
		for(int i=0; i<pstuNames.length; i++) {
			pstuNames[i] = new SDK_STORAGE_DEVICE_NAME();
		}

		Integer pnRetCount = new Integer(0);
		bRet = INetSDK.GetStorageDeviceNames(__LoginHandle, pstuNames, pnRetCount, 3000);

		if(bRet) {
			ToolKits.writeLog("存储设备数量 : " + pnRetCount);
			for (int i = 0; i < pnRetCount; i++) {
				ToolKits.writeLog("存储设备名称 = " + new String(pstuNames[i].szName).trim());

				// 格式化备份设备
				String devicesName = new String(pstuNames[i].szName).trim();
				char[] charNme = devicesName.toCharArray();//new char[devicesName.getBytes().length];
				if (INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_BACKUP_FORMAT, charNme, 5000)) {
					ToolKits.writeLog("格式化成功");
				} else {
					ToolKits.writeErrorLog("格式化失败");
				}

				// 获取文件系统格式
				bRet = false;
				String pszDevName = new String(pstuNames[i].szName).trim();

				SDK_STORAGE_DEVICE pDevice = new SDK_STORAGE_DEVICE();
				bRet = INetSDK.GetStorageDeviceInfo(__LoginHandle, pszDevName, pDevice, 5000);
				if (bRet) {
					ToolKits.writeLog("分区数量：" + pDevice.nPartitionNum);
					for (int j = 0; j < pDevice.nPartitionNum; j++) {
						ToolKits.writeLog("文件系统格式：" + new String(pDevice.stuPartitions[j].szFileSystem).trim());

						// 格式化
						bRet = false;
						int type = CtrlType.SDK_CTRL_FORMAT_PATITION;
						SDK_FORMAT_PATITION formatPattion = new SDK_FORMAT_PATITION();
						formatPattion.pszStorageName = new String(pstuNames[i].szName).trim();// 存储设备名称
						formatPattion.pszPatitionName = new String(pstuNames[i].szName).trim(); // 分区名称
						formatPattion.pszFileSystem = new String(pDevice.stuPartitions[j].szFileSystem).trim();
						bRet = INetSDK.ControlDevice(__LoginHandle, type, formatPattion, 5000);
						if (bRet) {
							ToolKits.writeLog("Format Succeed!");
						} else {
							ToolKits.writeErrorLog("Format Failed!" );
						}
					}
				} else {
					ToolKits.writeErrorLog("GetStorageDeviceInfo Failed" );
				}
			}
		}
	}
    
    // 公安一所平台接入配置
    void VSPCaysConfig() {
    	// 获取
    	CFG_VSP_GAYS_INFO cmdObject = new CFG_VSP_GAYS_INFO();
    	if(ToolKits.GetDevConfig(FinalVar.CFG_CMD_VSP_GAYS, cmdObject, __LoginHandle,
    			-1, 1024*1024)) {
			ToolKits.writeLog("设备国际ID: " + new String(cmdObject.szDeviceId).trim());
			ToolKits.writeLog("设备密码: " + new String(cmdObject.szPassword).trim());
			ToolKits.writeLog("服务器ID: " + new String(cmdObject.szSipSvrId).trim());
			ToolKits.writeLog("服务器IP: " + new String(cmdObject.szSipSvrIp).trim());
			ToolKits.writeLog("服务器端口: " + cmdObject.nSipSvrPort);
			ToolKits.writeLog("域名: " + new String(cmdObject.szDomain).trim());

			// 设置
			System.out.println(cmdObject.bEnable);
			cmdObject.bEnable = !cmdObject.bEnable;
			ToolKits.SetDevConfig(FinalVar.CFG_CMD_VSP_GAYS, cmdObject, __LoginHandle,
					-1, 1024*1024);
		}
    }
    
    // 查询升级状态
    void QueryUpgradeState() {
    	NET_IN_UPGRADE_STATE stIn = new NET_IN_UPGRADE_STATE();
    	NET_OUT_UPGRADE_STATE stOut = new NET_OUT_UPGRADE_STATE();

        boolean bRet = INetSDK.QueryDevInfo(__LoginHandle, FinalVar.NET_QUERY_UPGRADE_STATE, stIn, stOut, null, _waittime);
        if (!bRet) {
            ToolKits.writeErrorLog("check upgrade state");
            return;
        } else {
			ToolKits.writeLog("szOldVersion " + new String(stOut.szOldVersion).trim());
			ToolKits.writeLog("szNewVersion " + new String(stOut.szNewVersion).trim());
			ToolKits.writeLog("emState " + stOut.emState);
			ToolKits.writeLog("emType " + stOut.emType);
			ToolKits.writeLog("nProgress " + stOut.nProgress);
		}
    }
    
    // 报警网关语音配置
    void AlarmSoundConfig() {
   	   	NET_ALARM_SOUND_INFO cfgData = new NET_ALARM_SOUND_INFO();
    	int emCfgOpType = NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_ALARM_SOUND;
    	int nChannelID = -1;

		// 获取
    	if(INetSDK.GetConfig(__LoginHandle, emCfgOpType, nChannelID, cfgData, _waittime, null)) {
			ToolKits.writeLog("alarm effect " + cfgData.emSoundEffect);

			// 设置
			cfgData.emSoundEffect = EM_ALARM_SOUND_EFFECT.EM_ALARM_SOUND_EFFECT_MUTE;
			INetSDK.SetConfig(__LoginHandle, emCfgOpType, nChannelID, cfgData, _waittime, null, null);
		}
    }
    
    // 获取权限类型信息列表
	public void GetAuthClassifyList() {
    	NET_IN_GET_AUTHORITY_INFO_LIST stIn = new NET_IN_GET_AUTHORITY_INFO_LIST();
    	NET_OUT_GET_AUTHORITY_INFO_LIST stOut = new NET_OUT_GET_AUTHORITY_INFO_LIST();
    	
    	boolean bRet = INetSDK.GetAuthClassifyList(__LoginHandle, stIn, stOut, 5000);
    	
    	if(bRet){
    		ToolKits.writeLog("GetAuthClassifyList Succeed!" + "\n" + "返回的权限类型信息个数 : " + stOut.nRetAuthInfoCount);
    		for(int i=0; i<stOut.nRetAuthInfoCount; i++){
    			ToolKits.writeLog("主权限类型 : " + stOut.stuAuthInfo[i].emMainAuthority);
    			ToolKits.writeLog("子权限类型 : " + stOut.stuAuthInfo[i].emSubAuthority);
    			ToolKits.writeLog("通道号 : " + stOut.stuAuthInfo[i].nChannel);
    		}
    	} else { 
    		ToolKits.writeErrorLog("GetAuthClassifyList Failed!" );
    	}
    }

	// 获取LowRateWPAN能力
	public void QueryLowRateWPAN() {
		CFG_CAP_LOWRATEWPAN capLowratewpan = new CFG_CAP_LOWRATEWPAN();
		char szOutBuffer[] = new char[1024];
		Integer error = Integer.valueOf(0);
		boolean bRet = INetSDK.QueryNewSystemInfo(__LoginHandle, FinalVar.CFG_CAP_CMD_LOWRATEWPAN, 0, szOutBuffer, error, 3000);
		if(bRet) {
			bRet = false;
			bRet = INetSDK.ParseData(FinalVar.CFG_CAP_CMD_LOWRATEWPAN, szOutBuffer, capLowratewpan, null);
			if(bRet){
				ToolKits.writeLog("最大分页查询的对码条数：" + capLowratewpan.nMaxPageSize);
			} else {
				ToolKits.writeErrorLog("ParseData Failed!" );
			}
		} else {
			ToolKits.writeErrorLog("QueryNewSystemInfo Failed!" );
		}
	}

	// WiFi服务端配置
	public void AccessPointConfig() {
		boolean bRet = false;
		NET_NETAPP_ACCESSPOINT netApp = new NET_NETAPP_ACCESSPOINT(10);

		// 获取
		bRet = INetSDK.GetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_ACCESS_POINT, -1, netApp, 3000, null);
		if(bRet){
			ToolKits.writeLog("GetNetApp  Succeed!" + "\n" + "AP有效个数：" + netApp.nValidAPNum);
			for(int i=0; i<netApp.nValidAPNum; i++) {
				ToolKits.writeLog("网卡配置名 :" + netApp.stuAccessPoints[i].emAccessPointName);
				ToolKits.writeLog("有效工作模式个数 :" + netApp.stuAccessPoints[i].nValidPatternNum);
				for(int j=0; j<netApp.stuAccessPoints[i].nValidPatternNum; j++) {
					ToolKits.writeLog("工作模式 :" + netApp.stuAccessPoints[i].stuWorkPattern[j].emWorkPatternType);
					ToolKits.writeLog("WIFI网卡使能开关 :" + netApp.stuAccessPoints[i].stuWorkPattern[j].bEnable);
					ToolKits.writeLog("是否隐藏SSID :" + netApp.stuAccessPoints[i].stuWorkPattern[j].bHideSSID);
					ToolKits.writeLog("网络名称 :" + new String(netApp.stuAccessPoints[i].stuWorkPattern[j].szSSID).trim());
					ToolKits.writeLog("网络连接模式 :" + netApp.stuAccessPoints[i].stuWorkPattern[j].emLinkMode);
					ToolKits.writeLog("过滤配置-是否开启过滤功能 :" + netApp.stuAccessPoints[i].stuWorkPattern[j].stuFilter.bEnable);
					ToolKits.writeLog("过滤配置-最大Wifi设备连接个数 :" + netApp.stuAccessPoints[i].stuWorkPattern[j].stuFilter.nMaxAccessNum);
					ToolKits.writeLog("过滤配置-返回的地址个数 :" + netApp.stuAccessPoints[i].stuWorkPattern[j].stuFilter.nRetListNum);
				}
			}

			// 设置
			bRet = false;
			netApp.nValidAPNum = 1;
			netApp.stuAccessPoints[0].emAccessPointName = EM_ACCESSPOINT_NAME.EM_ACCESSPOINT_NAME_ETH2;
			netApp.stuAccessPoints[0].nValidPatternNum = 1;
			netApp.stuAccessPoints[0].stuWorkPattern[0].bHideSSID = true;
			String ssid = "TSJ-1234567899";
			System.arraycopy(ssid.getBytes(), 0, netApp.stuAccessPoints[0].stuWorkPattern[0].szSSID, 0, ssid.getBytes().length);
			netApp.stuAccessPoints[0].stuWorkPattern[0].emWorkPatternType = EM_WORKPATTERN.EM_WORKPATTERN_5G; // EM_WORKPATTERN_2_4G    EM_WORKPATTERN_5G
			netApp.stuAccessPoints[0].stuWorkPattern[0].stuFilter.bEnable = true;
			netApp.stuAccessPoints[0].stuWorkPattern[0].stuFilter.nMaxListNum = 10;
			bRet = INetSDK.SetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_ACCESS_POINT, -1, netApp, 3000, null, null);
			if(bRet){
				ToolKits.writeLog("SetNetApp  Succeed!");
			} else {
				ToolKits.writeErrorLog("SetNetApp Failed!" );
			}
		} else {
			ToolKits.writeErrorLog("GetNetApp Failed!" );
		}
	}

	// 铁路记录配置
	public void CarCoacnConfig() {
		NET_DEV_CAR_COACH_INFO devCarCoach = new NET_DEV_CAR_COACH_INFO();
		boolean bRet = INetSDK.SetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_DEV_CAR_COACH, -1, devCarCoach, 3000, null, null);
		if(bRet){
			ToolKits.writeLog("SetDevCar  Succeed!");
		} else {
			ToolKits.writeErrorLog("SetDevCar Failed!" );
		}

		bRet = false;
		bRet = INetSDK.GetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_DEV_CAR_COACH, -1, devCarCoach, 3000, null);
		if(bRet){
			ToolKits.writeLog("GetDevCar  Succeed!");
		} else {
			ToolKits.writeErrorLog("GetDevCar Failed!" );
		}
	}

    // 获取标记录像信息 和 设置标记文件
    void FindMarkFile_SetMarkFile(){
    	boolean bRet = false;
    	// 获取标记录像信息      
    	NET_IN_MEDIA_QUERY_FILE infile = new NET_IN_MEDIA_QUERY_FILE();
    	infile.stuStartTime.dwYear = 2017;
    	infile.stuStartTime.dwMonth = 3;
    	infile.stuStartTime.dwDay = 9;
    	infile.stuStartTime.dwHour = 11;
    	infile.stuStartTime.dwMinute = 00;
    	infile.stuStartTime.dwSecond = 0;
    	
    	infile.stuEndTime.dwYear = 2017;
    	infile.stuEndTime.dwMonth = 3;
    	infile.stuEndTime.dwDay = 9;
    	infile.stuEndTime.dwHour = 12;
    	infile.stuEndTime.dwMinute = 0;
    	infile.stuEndTime.dwSecond = 0;

		infile.szDirs = "";
    	infile.nMediaType = 2;
    	infile.nChannelID = -1;
    	infile.byVideoStream = 1;

    	long lFindHandle = INetSDK.FindFileEx(__LoginHandle, EM_FILE_QUERY_TYPE.SDK_FILE_QUERY_FILE, infile, 30000);
    	if(lFindHandle != 0){
    		ToolKits.writeLog("FindFileEx Succeed!");
    	} else {
    		ToolKits.writeErrorLog("FindFileEx Failed!" );
    	}
    	
    	NET_OUT_MEDIA_QUERY_FILE[] outfile = new NET_OUT_MEDIA_QUERY_FILE[10];
		for (int i = 0; i < 10; i++) {
			outfile[i] = new NET_OUT_MEDIA_QUERY_FILE();
		}

		int nCount = 0;
		int nMaxCount = 10;
		int nRetCount = 0;
		while(true) {
			nRetCount = INetSDK.FindNextFileEx(lFindHandle, nMaxCount, outfile, 10000);
			if(nRetCount <= 0) {
				ToolKits.writeErrorLog("FindNextFileEx Failed!" );
				return;
			}

    		for(int i = 0; i < nRetCount; i++){
				ToolKits.writeLog("---------------[ " + (i + nMaxCount * nCount) +"] -----------------");
				ToolKits.writeLog("文件路径：" + new String(outfile[i].szFilePath).trim());
    			ToolKits.writeLog("文件类型:" + outfile[i].byFileType);
    	
    			ToolKits.writeLog("事件总数：" + outfile[i].nEventCount);	
    			for(int j=0; j<outfile[i].nEventCount; j++){
    				ToolKits.writeLog("事件：" + outfile[i].nEventLists[j]);
    			}
    			
    			ToolKits.writeLog("文件摘要信息数：" + outfile[i].nFileSummaryNum);	
    			for(int j=0; j<outfile[i].nFileSummaryNum; j++){
    				ToolKits.writeLog("摘要内容：" + new String(outfile[i].stFileSummaryInfo[j].szKey).trim());
    			}
    			
    			ToolKits.writeLog("文件标志总数：" + outfile[i].nFalgCount);	
    			for(int j=0; j<outfile[i].nFalgCount; j++) {
					ToolKits.writeLog("标志文件：" + outfile[i].emFalgLists[j]);
				}
    		}

			if(nRetCount <= nMaxCount) {
				break;
			} else {
				nCount++;
			}
    	}

    	INetSDK.FindCloseEx(lFindHandle);


    	// 按文件标记
    	bRet = false;
    	for(int i = 0; i < nRetCount; i++) {
    		NET_IN_SET_MARK_FILE inMarkFile = new NET_IN_SET_MARK_FILE();
	    	inMarkFile.emLockMode = EM_MARKFILE_MODE.EM_MARK_FILE_BY_NAME_MODE;  // 通过文件名方式对录像加锁
	    	inMarkFile.emFileNameMadeType = EM_MARKFILE_NAMEMADE_TYPE.EM_MARKFILE_NAMEMADE_DEFAULT; // 默认方式：需要用户传递录像文件名参数szFilename

	    	inMarkFile.byImportantRecID = 1; // 0:false清除;   1:true 标记

        	String nameString = new String(outfile[i].szFilePath);
        	System.arraycopy(nameString.getBytes(), 0, inMarkFile.szFilename, 0, nameString.getBytes().length);

        	NET_OUT_SET_MARK_FILE outMarkFile = new NET_OUT_SET_MARK_FILE();
        	bRet = INetSDK.SetMarkFile(__LoginHandle, inMarkFile, outMarkFile, 3000);
        	if(bRet){
        		ToolKits.writeLog("SetMarkFile Succeed!");
        	} else {
        		ToolKits.writeErrorLog("SetMarkFile Failed!" );
        	}
    	}
    }

	// 设备属性配置(获取录像打包时间)
	public void GetDeviceSystemInfo() {
		SDKDEV_SYSTEM_ATTR_CFG[] systemAttr = new SDKDEV_SYSTEM_ATTR_CFG[1];
		systemAttr[0] = new SDKDEV_SYSTEM_ATTR_CFG();
		Integer interAttr = Integer.valueOf(0);
		boolean bRet = INetSDK.GetDevConfig(__LoginHandle, FinalVar.SDK_DEV_DEVICECFG, -1, systemAttr, interAttr, 3000);
		if(bRet) {
			ToolKits.writeLog("byRecordLen = " + systemAttr[0].byRecordLen);
			ToolKits.writeLog("byVideoCaptureNum = " + systemAttr[0].byVideoCaptureNum);
		} else {
			ToolKits.writeErrorLog("GetRecordLen Failed!" );
		}
	}

	// 通道名称配置(老接口)
	public void ChannelNameOldConfig() {
		boolean bRet = false;
		AV_CFG_ChannelName[] channelName = new AV_CFG_ChannelName[16]; // 数组长度为视频口数量最大值
		for(int i=0; i < 16; i++) {
			channelName[i] = new AV_CFG_ChannelName();
		}
		for(int i=0; i < 16; i++) {
			bRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_CHANNELTITLE, channelName[i], __LoginHandle, i, 2048);
			if(bRet) {
				ToolKits.writeLog("GetChannelName Succeed!" + "  " + channelName[i].nSerial + " " + new String(channelName[i].szName).trim());
			} else {
				ToolKits.writeErrorLog("GetChannelName Failed!" );
			}
		}

		bRet = false;
		String Name = "666656789012";  // 要12位
		ToolKits.writeLog(Name.getBytes().length + "  /  " + channelName[0].szName.length);
		System.arraycopy(Name.getBytes(), 0, channelName[0].szName, 0, Name.getBytes().length);
		bRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_CHANNELTITLE, channelName[0], __LoginHandle, 0, 2048);
		if(bRet) {

			ToolKits.writeLog("SetChannelName Succeed!");
		} else {
			ToolKits.writeErrorLog("SetChannelName Failed!" );

		}
	}

	// 通道名称配置(新接口)
	public void ChannelNameNewConfig() {
		NET_ENCODE_CHANNELTITLE_INFO stIn = new NET_ENCODE_CHANNELTITLE_INFO();
		int channel = 0;  // 通道号

		// 获取
		if (INetSDK.GetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_ENCODE_CHANNELTITLE, channel, stIn, 3000, null)) {
			ToolKits.writeLog("szChannelName : " + new String(stIn.szChannelName).trim());
		} else {
			ToolKits.writeErrorLog("Get Faile" );
		}

		String channelName = "IPC";
		ToolKits.StringToByteArray(channelName, stIn.szChannelName);     // 通道名称
		if(INetSDK.SetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_ENCODE_CHANNELTITLE, channel, stIn, 3000, null, null)) {
			ToolKits.writeLog("Set Succeed!");
		} else {
			ToolKits.writeErrorLog("Set Faile" );
		}
	}

	// 按时间日志查询
	public void QueryDeviceLog() {
		QUERY_DEVICE_LOG_PARAM deviceLogParam = new QUERY_DEVICE_LOG_PARAM();
		deviceLogParam.emLogType = SDK_LOG_QUERY_TYPE.SDKLOG_ALL;
		deviceLogParam.stuStartTime.dwYear = 2017;

		deviceLogParam.stuStartTime.dwMonth = 2;
		deviceLogParam.stuStartTime.dwDay = 28;
		deviceLogParam.stuStartTime.dwHour = 11;
		deviceLogParam.stuStartTime.dwMinute = 0;
		deviceLogParam.stuStartTime.dwSecond = 0;

		deviceLogParam.stuEndTime.dwYear = 2017;
		deviceLogParam.stuEndTime.dwMonth = 3;
		deviceLogParam.stuEndTime.dwDay = 1;
		deviceLogParam.stuEndTime.dwHour = 15;
		deviceLogParam.stuEndTime.dwMinute = 0;
		deviceLogParam.stuEndTime.dwSecond = 0;

		deviceLogParam.nStartNum = 0;
		deviceLogParam.nEndNum = 100;
		deviceLogParam.nLogStuType = 1; // 0:表示SDK_DEVICE_LOG_ITEM；1:表示 SDK_DEVICE_LOG_ITEM_EX
		deviceLogParam.nChannelID = 0;
		Integer recLogNum = Integer.valueOf(0);
		SDK_DEVICE_LOG_ITEM_EX[] deviceLogItemEx = new SDK_DEVICE_LOG_ITEM_EX[100];
		for(int i = 0; i < 100; i++){
			deviceLogItemEx[i] = new SDK_DEVICE_LOG_ITEM_EX();
		}
		boolean bRet = INetSDK.QueryDeviceLog(__LoginHandle, deviceLogParam, deviceLogItemEx, recLogNum, 5000);
		if(bRet) {
			ToolKits.writeLog("QueryDeviceLog Succeed!" + "\n" + "日志条数：" + recLogNum );
			for(int i=0; i<recLogNum; i++){
				ToolKits.writeLog("日志类型：" + deviceLogItemEx[i].nLogType);
				ToolKits.writeLog("具体的操作内容：" + new String(deviceLogItemEx[i].szOperation).trim());
				ToolKits.writeLog("详细日志信息描述：" + new String(deviceLogItemEx[i].szDetailContext).trim());
				ToolKits.writeLog("日期：20" + deviceLogItemEx[i].stuOperateTime.year + "-" +
						deviceLogItemEx[i].stuOperateTime.month + "-" +
						deviceLogItemEx[i].stuOperateTime.day + "  " +
						deviceLogItemEx[i].stuOperateTime.hour + ":" +
						deviceLogItemEx[i].stuOperateTime.minute + ":" +
						deviceLogItemEx[i].stuOperateTime.second);
			}
		} else {
			ToolKits.writeErrorLog("QueryDeviceLog Failed!" );
		}
	}

	// 人脸检测设置：人脸检测功能的开与关
	public void AnalyseGlobalConfig() {
		// 获取
		CFG_ANALYSEGLOBAL_INFO analyseglobal = new CFG_ANALYSEGLOBAL_INFO(10, 10);
		boolean bRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_ANALYSEGLOBAL, analyseglobal, __LoginHandle, 0, 2*1024*1024);
		if(bRet) {
			ToolKits.writeLog("type = " + new String(analyseglobal.szSceneType).trim());
			ToolKits.writeLog("bFaceDetection = " + analyseglobal.bFaceDetection);

			// 设置
			bRet = false;
			String scenetype = "FaceDetection";   // 设为“FaceRecognition” 人脸检测使能开；   设为其他，则关  ，参考应用场景  EM_SCENE_TYPE
			System.arraycopy(scenetype.getBytes(), 0, analyseglobal.szSceneType, 0, scenetype.getBytes().length);
			if(analyseglobal.bFaceDetection){
				analyseglobal.bFaceDetection = false;
			} else {
				analyseglobal.bFaceDetection = true;
			}
			analyseglobal.stuFaceDetectionScene.dbCameraHeight = 10;
			analyseglobal.stuFaceDetectionScene.emDetectType = EM_FACEDETECTION_TYPE.EM_FACEDETECTION_TYPE_BOTH;
			bRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_ANALYSEGLOBAL, analyseglobal, __LoginHandle, 0, 2*1024*1024);
			if(bRet) {
				ToolKits.writeLog("SetFaceRecognition Succeed!");
			} else {
				ToolKits.writeErrorLog("SetFaceRecognition Failed!" );
			}
		} else {
			ToolKits.writeErrorLog("GetFaceRecognition Failed!" );
		}
	}

	// 动态检测设置：动态检测功能的开与关
	public void MotionDetectConfig() {
		CFG_MOTION_INFO motion = new CFG_MOTION_INFO();
		motion.nChannelID = 0;
		motion.bEnable = true;
		boolean bRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_MOTIONDETECT, motion, __LoginHandle, 0, 2048);
		if(bRet) {
			ToolKits.writeLog("SetMotionDetect Succeed!");
		} else {
			ToolKits.writeErrorLog("SetMotionDetect Failed!" );
		}
	}

	// 恢复出厂设置(恢复设备的默认设置新协议,指定什么配置，恢复什么配置)
	public void RestoreDefault() {
		NET_CTRL_RESTORE_DEFAULT restore = new NET_CTRL_RESTORE_DEFAULT();
		String cfgname = "Network&&IPv6&&VideoInSensor&&Multicast&&Web&&Https&&DVRIP&&VideoStandard&&VSP_GAYS&&DeviceInfo&&Telnet&&WLan";
		restore.szCfgNames = cfgname;
		boolean bRet = INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_RESTOREDEFAULT_EX, restore, 3000);
		if(bRet) {
			ToolKits.writeLog("RestoreDefault Succeed!");
		} else {
			ToolKits.writeErrorLog("RestoreDefault Failed!" );
		}
	}

	// 恢复出厂设置(恢复除指定配置外的其他配置为默认)
	public void RestoreExcept() {
		NET_CTRL_RESTORE_EXCEPT restore = new NET_CTRL_RESTORE_EXCEPT();
		String cfgname = "Network&&IPv6&&VideoInSensor&&Multicast&&Web&&Https&&DVRIP&&VideoStandard&&VSP_GAYS&&DeviceInfo&&Telnet&&WLan";
		System.arraycopy(cfgname.getBytes(), 0, restore.szCfgName, 0, cfgname.getBytes().length);
		boolean bRet = INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_RESTORE_EXCEPT, restore, 3000);
		if(bRet) {
			ToolKits.writeLog("RestoreDefault Succeed!");
		} else {
			ToolKits.writeErrorLog("RestoreDefault Failed!" );
		}
	}

	// 电子防抖：电子防抖功能的开与关
	public void VideoInStableConfig() {
		NET_VIDEOIN_STABLE_INFO stable = new NET_VIDEOIN_STABLE_INFO();
		stable.emCfgType = NET_EM_CONFIG_TYPE.NET_EM_CONFIG_NORMAL;  // 配置类型,获取和设置时都要指定
		stable.emStableType = NET_EM_STABLE_TYPE.NET_EM_STABLE_ELEC; // NET_EM_STABLE_TYPE.NET_EM_STABLE_OFF 关闭
		boolean bRet = INetSDK.SetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_VIDEOIN_STABLE, -1, stable, 3000, null, null);
		if(bRet){
			ToolKits.writeLog("SetStableElec Succeed!");
		} else {
			ToolKits.writeErrorLog("SetStableElec Failed!" );
		}
	}

	// 强光抑制：强光抑制功能的开与关
	public void VideoInBackLightConfig() {
		NET_VIDEOIN_BACKLIGHT_INFO backlight = new NET_VIDEOIN_BACKLIGHT_INFO();
		backlight.emCfgType = NET_EM_CONFIG_TYPE.NET_EM_CONFIG_NORMAL;  // 配置类型,获取和设置时都要指定
		backlight.emBlackMode = NET_EM_BACK_MODE.NET_EM_BACKLIGHT_MODE_GLAREINHIBITION; // NET_EM_BACK_MODE.NET_EM_BACKLIGHT_MODE_OFF 关闭
		backlight.nGlareInhibition = 10;
		boolean bRet = INetSDK.SetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_VIDEOIN_BACKLIGHT, -1, backlight, 3000, null, null);
		if(bRet) {
			ToolKits.writeLog("SetBackLight Succeed!");
		} else {
			ToolKits.writeErrorLog("SetBackLight Failed!" );
		}
	}

	// 版本信息：获取设备的版本信息，包括 设备型号、硬件版本、软件版本、序列号
	public void QueryDeviceSoftWare() {
		SDKDEV_VERSION_INFO version = new SDKDEV_VERSION_INFO();
		boolean bRet = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_SOFTWARE, version, 5000);
		if(bRet) {
			ToolKits.writeLog("QueryVersion Succeed!");
			ToolKits.writeLog("设备型号：" + new String(version.szDevType).trim());
			ToolKits.writeLog("硬件版本：" + new String(version.szHardwareVersion).trim());
			ToolKits.writeLog("软件版本：" + new String(version.szSoftWareVersion).trim());
			ToolKits.writeLog("序列号：" + new String(version.szDevSerialNo).trim());
		} else {
			ToolKits.writeErrorLog("QueryVersion Failed!" );
		}
	}
    
	/**
	 * 录播主机状态获取
	 **/
    void GetRecordState(){
    	boolean bResult = false;
    	NET_IN_GET_RECORD_STATE recStateIn = new NET_IN_GET_RECORD_STATE();
    	recStateIn.nChannel = 51;
		NET_OUT_GET_RECORD_STATE recStateOut = new NET_OUT_GET_RECORD_STATE();
		bResult = INetSDK.GetRecordState(__LoginHandle, recStateIn, recStateOut, 5000);
		if (!bResult) {
			ToolKits.writeErrorLog("GetRecordState failed");
		} else {
			ToolKits.writeLog("INetSDK.GetRecordState return state:" + recStateOut.bState);
		}
    }

	/**
	 * 录播主机复合操作
	 */
    void RecordedOperateComposite(){
    	showCompositeOperationDialog();
    	}
    
    	private void showCompositeOperationDialog(){
	    final String items[] = {"Operate add","Operate delete",
			   				   "Operate get","Operate modify"};
	    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setItems(items, new DialogInterface.OnClickListener() {
		   @Override
		   public void onClick(DialogInterface arg0, int arg1) {
			switch(arg1){
			case 0:
				addMode();
				break;
			case 1:
				deleteMode();
				break;
			case 2:
				getMode();
				break;
			case 3:
				modifyMode();
				break;
			}
		}
	    });
	    builder.setPositiveButton("TestCompoleted", new DialogInterface.OnClickListener() {	
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			arg0.dismiss();
		}
	    });
	    builder.setCancelable(false);
	    builder.create().show();
    }
   
   private void addMode(){
	   NET_IN_COURSECOMPOSITE_CHANNEL_MODE_ADD stuIn = new NET_IN_COURSECOMPOSITE_CHANNEL_MODE_ADD();
	   stuIn.nCount = 2;
	   for(int i=0;i<stuIn.nCount;i++){
			stuIn.stModeInfo[i].nWindowNum = 2;
			System.arraycopy("xxxx".getBytes(),0,stuIn.stModeInfo[i].szName, 0, "xxxx".getBytes().length);
			for(int j=0;j<stuIn.stModeInfo[i].nWindowNum;++j){
				stuIn.stModeInfo[i].stWindows[j].nLogicChannel = (i + 1) * (j * 3);
				stuIn.stModeInfo[i].stWindows[j].nZOrder = 2;
				stuIn.stModeInfo[i].stWindows[j].stRect.left = 50;
				stuIn.stModeInfo[i].stWindows[j].stRect.top = 50;
				stuIn.stModeInfo[i].stWindows[j].stRect.right = 250;
				stuIn.stModeInfo[i].stWindows[j].stRect.bottom = 250;
			}
	   }
		
		NET_OUT_COURSECOMPOSITE_CHANNEL_MODE_ADD stuOut = new NET_OUT_COURSECOMPOSITE_CHANNEL_MODE_ADD();
		boolean result = INetSDK.OperateCourseCompositeChannelMode(__LoginHandle,
				NET_COURSECOMPOSITE_MODE_OPERATE_TYPE.NET_COURSECOMPOSITE_MODE_ADD, stuIn, stuOut, 5000);
		if(result){
			for(int i=0;i<stuOut.nCount;++i){
				ToolKits.writeLog("nMode:"+String.valueOf(stuOut.stResult[i].nMode)+
						"returnCode:"+stuOut.stResult[i].nReturnCode);
			}
		}else{
			ToolKits.writeLog("OperateCouseCompositeChannelMode add failed.error is:"+(INetSDK.GetLastError()&0x7fffffff));
		}
	}
		
	private void deleteMode(){
		NET_IN_COURSECOMPOSITE_CHANNEL_MODE_DELETE stuIn = new NET_IN_COURSECOMPOSITE_CHANNEL_MODE_DELETE();
		stuIn.nModeNum = 2;
		stuIn.nMode[0] = -7;
		stuIn.nMode[1] = -8;
		
		NET_OUT_COURSECOMPOSITE_CHANNEL_MODE_DELETE stuOut = new NET_OUT_COURSECOMPOSITE_CHANNEL_MODE_DELETE();
		boolean result = INetSDK.OperateCourseCompositeChannelMode(__LoginHandle,
				NET_COURSECOMPOSITE_MODE_OPERATE_TYPE.NET_COURSECOMPOSITE_MODE_DELETE, stuIn, stuOut, 5000);
		if(result){
			ToolKits.writeLog("nReturnNum is:"+stuOut.nReturnNum);
			for(int i=0;i<stuOut.nReturnNum;i++){
				ToolKits.writeLog("returnCode:"+stuOut.nReturnCode[i]);
			}
		}else{
			ToolKits.writeLog("OperateCouseCompositeChannelMode delete failed.error is:"+(INetSDK.GetLastError()&0x7fffffff));
		}
	}

	private void modifyMode(){
		NET_IN_COURSECOMPOSITE_CHANNEL_MODE_MODIFY stuIn = new NET_IN_COURSECOMPOSITE_CHANNEL_MODE_MODIFY();
		stuIn.nModeNum = 2;
		for(int i=0;i<stuIn.nModeNum;i++){
			stuIn.nMode[i] = -7 - i;
			System.arraycopy("yyyy".getBytes(),0,stuIn.stModeInfo[i].szName, 0, "yyyy".getBytes().length);
			stuIn.stModeInfo[i].nWindowNum = 2;
			for(int j=0;j<stuIn.stModeInfo[i].nWindowNum;++j){
				stuIn.stModeInfo[i].stWindows[j].nZOrder = 10;
				stuIn.stModeInfo[i].stWindows[j].nLogicChannel = 20;
			}
		}
		NET_OUT_COURSECOMPOSITE_CHANNEL_MODE_MODIFY stuOut = new NET_OUT_COURSECOMPOSITE_CHANNEL_MODE_MODIFY();
		boolean bRet = INetSDK.OperateCourseCompositeChannelMode(__LoginHandle,
				NET_COURSECOMPOSITE_MODE_OPERATE_TYPE.NET_COURSECOMPOSITE_MODE_MODIFY, stuIn, stuOut, 5000);
		if(bRet){
			ToolKits.writeLog("modify number:"+stuOut.nReturnNum);
			for(int i=0;i<stuOut.nReturnNum;i++){
				ToolKits.writeLog("nReturnCode:"+stuOut.nReturnCode[i]);
			}
		}else{
			ToolKits.writeLog("recorded modify failed.error is:"+(INetSDK.GetLastError()&0x7fffffff));
		}
	
	}

	// 录播主机组合通道模式操作(获取模式)
	private void getMode(){
		NET_IN_COURSECOMPOSITE_CHANNEL_MODE_GET stuIn= new NET_IN_COURSECOMPOSITE_CHANNEL_MODE_GET();
		stuIn.nCount = 10;
		
		NET_OUT_COURSECOMPOSITE_CHANNEL_MODE_GET stuOut = new NET_OUT_COURSECOMPOSITE_CHANNEL_MODE_GET();
		boolean bRet = INetSDK.OperateCourseCompositeChannelMode(__LoginHandle,
				NET_COURSECOMPOSITE_MODE_OPERATE_TYPE.NET_COURSECOMPOSITE_MODE_GET, stuIn, stuOut, 5000);
		
		if(bRet){
			ToolKits.writeLog("getmode number is:"+stuOut.nReturnNum);
			for(int i=0;i<stuOut.nReturnNum;i++){
				ToolKits.writeLog("nMode:" + stuOut.nMode[i] + ";szName:"+new String(stuOut.stModeInfo[i].szName));
			}
		}else{
			ToolKits.writeLog("OperateCouseCompositeChannelMode get  failed.error is:"+(INetSDK.GetLastError()&0x7fffffff));
		}
	
	}

	// 视频输入前端选项
	public void VedioInOptionsConfig() {
		int channel = 10;
		CFG_VIDEO_IN_OPTIONS[] stCfgs = new CFG_VIDEO_IN_OPTIONS[channel];
		for ( int i = 0; i <  stCfgs.length; ++i) {
			stCfgs[i] =  new CFG_VIDEO_IN_OPTIONS();
		}

		boolean result = false;
		int nBufferLen = 4*1024*1024;
		Integer error = new Integer(0);
		char szBuffer[] = new char[nBufferLen];
		int nChn = -1;
		Integer count = new Integer(0);
		String strCmd = FinalVar.CFG_CMD_VIDEOINOPTIONS;

		// 获取
		if(INetSDK.GetNewDevConfig(__LoginHandle, strCmd , nChn, szBuffer,nBufferLen, error, 10000) )
		{
			if( INetSDK.ParseData(strCmd ,szBuffer , stCfgs , count ) )
			{
				result = true;
				ToolKits.writeLog("Has Configure " + count
						+"\n byWhiteBalance - "+ stCfgs[0].byWhiteBalance
						);

				// 设置
				result = INetSDK.PacketData(strCmd, stCfgs, szBuffer, nBufferLen);
				if (result) {
					Integer errorInteger = new Integer(0);
					Integer restartInteger = new Integer(0);
					INetSDK.SetNewDevConfig(__LoginHandle, strCmd, -1, szBuffer, nBufferLen, errorInteger, restartInteger, 10000);
				}
			}
		 }
	}

	// 设置探测器工作模式
	private void SetDetectorWorkMode(){
		NET_CTRL_LOWRATEWPAN_SETWORKMODE stuIn = new NET_CTRL_LOWRATEWPAN_SETWORKMODE();
		NET_CTRL_LOWRATEWPAN_REMOVEALL stuInRm = new NET_CTRL_LOWRATEWPAN_REMOVEALL();
		NET_CTRL_LOWRATEWPAN_ADD stuInAdd = new NET_CTRL_LOWRATEWPAN_ADD();
		stuIn.nMode = 1;
	
		boolean bResult = INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_LOWRATEWPAN_SETWORKMODE, stuIn, 5000);
	//	boolean bResult = INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_LOWRATEWPAN_REMOVEALL, stuInRm, 1000);
	//	boolean bResult = INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_LOWRATEWPAN_ADD, stuInAdd, 5000);
		if(bResult){
			ToolKits.showMessage(this, "set the Detector working mode successful..");
		}else{
			ToolKits.writeLog("Set the Detector working mode failed the error code is :"+(INetSDK.GetLastError()&0x7fffffff));
		}
	}

	// 开始异步文件上传
	private void StartUploadRemoteFile(){
		long lLoginHandle = __LoginHandle;
		long bRet = 0;

		// 文件上传
		SDK_IN_UPLOAD_REMOTE_FILE inUploadFile = new SDK_IN_UPLOAD_REMOTE_FILE();
		inUploadFile.pszFileDst = new String("test.bmp");
		inUploadFile.pszFolderDst = new String("/mnt/lv/SystemPrimaryNAS/PhotoNAS/admin");
		inUploadFile.pszFileSrc = new String("/mnt/sdcard/test.bmp");
		inUploadFile.nPacketLen = 1024*8;
		SDK_OUT_UPLOAD_REMOTE_FILE outUploadFile = new SDK_OUT_UPLOAD_REMOTE_FILE();

		bRet = INetSDK.StartUploadRemoteFile(lLoginHandle, inUploadFile, outUploadFile,new CB_fUploadFileCallBack(){

			@Override
			public void invoke(long lUploadFileHandler, int nTotalSize,
					int nSendSize, long dwUser) {
				ToolKits.writeLog("nTotalSize:"+nTotalSize+"; nSendSize:"+nSendSize);
			}

		});
		if (bRet>0) {
			ToolKits.writeLog("upload Remote ok");
		}else {
			ToolKits.writeErrorLog("upload Remote Error"+(INetSDK.GetLastError()&0x7fffffff));
			return;
		}
	}

	// 停止异步文件上传
	private void StopUploadRemoteFile(){
		boolean result = INetSDK.StopUploadRemoteFile(__LoginHandle);
		if(result){
			ToolKits.writeLog("stop successfully");
		}else{
			ToolKits.showErrorMessage(this, "Error");
			ToolKits.writeErrorLog("Stop Upload Remote File Error"+(INetSDK.GetLastError()&0x7fffffff));
		}
	}

	// 图像通道属性配置
	private void EncodeConfig(){
		CFG_ENCODE_INFO stEncInfo = new CFG_ENCODE_INFO();
		boolean zRetEnc = false;
		if (!zRetEnc) {
			zRetEnc = ToolKits.GetDevConfig(FinalVar.CFG_CMD_ENCODE,
					stEncInfo, __LoginHandle, 0,
					1024*1024);
			if (!zRetEnc) {
				ToolKits.showErrorMessage(this, "GetDevConfig failed, "
						+ FinalVar.CFG_CMD_ENCODE + ";Error:"+(INetSDK.GetLastError()&0x7fffffff));
			} else {
				zRetEnc = ToolKits.SetDevConfig(
						FinalVar.CFG_CMD_ENCODE, stEncInfo,
						__LoginHandle, 0, 1024*1024);
				if(!zRetEnc){
					ToolKits.showErrorMessage(this, "CFG_CMD_ENCODE failed");
				}else{
					ToolKits.writeLog("CFG_CMD_ENCODE successfully");
				}
			}
		}
	}

	// 定时录像配置
	public void RecordConfig() {
		CFG_RECORD_INFO stRecord = new CFG_RECORD_INFO();
		boolean zRetEnc = ToolKits.GetDevConfig(FinalVar.CFG_CMD_RECORD,stRecord, __LoginHandle, 0, 1024*1024);
		if(!zRetEnc) {
			ToolKits.showErrorMessage(this,"GetDevConfig failed, " + FinalVar.CFG_CMD_RECORD );
		} else {
			zRetEnc =ToolKits.SetDevConfig(FinalVar.CFG_CMD_RECORD, stRecord,__LoginHandle, 0, 1024*1024);
			if(!zRetEnc){
				ToolKits.showErrorMessage(this,"SetDevConfig failed, " + FinalVar.CFG_CMD_RECORD );
			}else{
				ToolKits.writeLog(FinalVar.CFG_CMD_RECORD +"  successfully");
			}
		}
	}

	// 网络存储服务器配置
	public void NASConfig() {
		CFG_NAS_INFO_EX stNAS = new CFG_NAS_INFO_EX();
		// 获取
		boolean zRetEnc = ToolKits.GetDevConfig(FinalVar.CFG_CMD_NASEX,stNAS, __LoginHandle, 0, 1024*1024);
		if(!zRetEnc) {
			ToolKits.showErrorMessage(this,"GetDevConfig failed, " + FinalVar.CFG_CMD_NASEX + "ErrorCode:"+(INetSDK.GetLastError()&0x7fffffff));
		} else {

			// 设置
			if(stNAS.nVersion == 0) {
				ToolKits.writeLog("nProtocol:" + stNAS.stuNasOld.nProtocol);
				stNAS.stuNasOld.nProtocol = 0; //FTP
			} else {
				if(stNAS.stuNasGroup.nNasNum > 0) {
					ToolKits.writeLog("nnProtocol:" + stNAS.stuNasGroup.stuNasInfo[0].nPortocol);
					stNAS.stuNasGroup.stuNasInfo[0].nPortocol = 0; // FTP ,只设置一个
				}
			}
			zRetEnc =ToolKits.SetDevConfig(FinalVar.CFG_CMD_NASEX, stNAS,__LoginHandle, 0, 1024*1024);
			if(zRetEnc){
				ToolKits.writeLog(FinalVar.CFG_CMD_NASEX +" is successfully");
				Toast.makeText(this, "Set NAS Succeed!", Toast.LENGTH_SHORT).show();;
			}else{
				ToolKits.showErrorMessage(this,"SetDevConfig failed, " + FinalVar.CFG_CMD_NASEX + "ErrorCode:"+(INetSDK.GetLastError()&0x7fffffff));
				Toast.makeText(this, "Set NAS failed!" + "ErrorCode:"+(INetSDK.GetLastError()&0x7fffffff), Toast.LENGTH_SHORT).show();;
			}
		}
	}

	// 录像存储点映射配置
	public void StoragePointConfig() {
		//CFG_CMD_RECORD_STORAGEPOINT OK
		CFG_RECORDTOSTORAGEPOINT_INFO stRecStorage = new CFG_RECORDTOSTORAGEPOINT_INFO();
		// 获取
		boolean zRetEnc = ToolKits.GetDevConfig(FinalVar.CFG_CMD_RECORD_STORAGEPOINT,stRecStorage, __LoginHandle, 0, 1024*1024);
		if(!zRetEnc) {
			ToolKits.showErrorMessage(this,"GetDevConfig failed, " + FinalVar.CFG_CMD_RECORD_STORAGEPOINT + "ErrorCode:"+(INetSDK.GetLastError()&0x7fffffff));
		} else {
			for(int i = 0; i < stRecStorage.nStoragePointNum; i++) {
				ToolKits.writeLog("nLocalDir:" + stRecStorage.stStoragePoints[i].nLocalDir);
				ToolKits.writeLog("szRemoteDir : " + new String(stRecStorage.stStoragePoints[i].szRemoteDir).trim());
			}

			// 设置
			for(int i = 0; i < stRecStorage.nStoragePointNum; i++) {
				if(stRecStorage.stStoragePoints[i].emStoragePointType ==  EM_STORAGEPOINT_TYPE.EM_STORAGE_TIMINGRECORD ||
					stRecStorage.stStoragePoints[i].emStoragePointType == EM_STORAGEPOINT_TYPE.EM_STORAGE_MANUALRECORD ||
					stRecStorage.stStoragePoints[i].emStoragePointType == EM_STORAGEPOINT_TYPE.EM_STORAGE_VIDEODETECTRECORD ||
					stRecStorage.stStoragePoints[i].emStoragePointType == EM_STORAGEPOINT_TYPE.EM_STORAGE_ALARMRECORD ||
					stRecStorage.stStoragePoints[i].emStoragePointType == EM_STORAGEPOINT_TYPE.EM_STORAGE_CARDRECORD ||
					stRecStorage.stStoragePoints[i].emStoragePointType == EM_STORAGEPOINT_TYPE.EM_STORAGE_EVENTRECORD) {

					if(stRecStorage.stStoragePoints[i].nLocalDir == 1) {
						// 关闭 Local 和 NAS
						stRecStorage.stStoragePoints[i].nLocalDir = 0;
						ToolKits.StringToByteArray("", stRecStorage.stStoragePoints[i].szRemoteDir);
					} else {
						// 打开 Local 和 NAS
						stRecStorage.stStoragePoints[i].nLocalDir = 1;
						ToolKits.StringToByteArray("ddd", stRecStorage.stStoragePoints[i].szRemoteDir);
					}
				}
			}
			zRetEnc =ToolKits.SetDevConfig(FinalVar.CFG_CMD_RECORD_STORAGEPOINT, stRecStorage,__LoginHandle, 0, 1024*1024);
			if(zRetEnc){
				ToolKits.writeLog(FinalVar.CFG_CMD_RECORD_STORAGEPOINT +" is successfully");
				Toast.makeText(this, "Set StoragePoint Succeed!", Toast.LENGTH_SHORT).show();;
			}else{
				ToolKits.showErrorMessage(this,"SetDevConfig failed, " + FinalVar.CFG_CMD_RECORD_STORAGEPOINT + "ErrorCode:"+(INetSDK.GetLastError()&0x7fffffff));
				Toast.makeText(this, "Set StoragePoint Failed!" + "ErrorCode:"+(INetSDK.GetLastError()&0x7fffffff), Toast.LENGTH_SHORT).show();;
			}
		}
	}

	// DMSS接入智能锁设置功率接口
	private void SetLowRateWPANPower(){
		NET_IN_SET_LOWRATEWPAN_POWER stuIn = new NET_IN_SET_LOWRATEWPAN_POWER();
		stuIn.nPower = 10;
		System.arraycopy("AXJ07ZNSZ000049 ".getBytes(), 0, stuIn.szSmartLockNum, 0, "AXJ07ZNSZ000049 ".getBytes().length);
		NET_OUT_SET_LOWRATE_WPAN_POWER stuOut = new NET_OUT_SET_LOWRATE_WPAN_POWER();
		ToolKits.writeLog("sn:"+new String(stuIn.szSmartLockNum));
		boolean result = INetSDK.SetLowRateWPANPower(__LoginHandle, stuIn, stuOut, 5000);
		if(result){
			ToolKits.writeLog("INetSDK.SetLowRateWPANPower  Success! ");
		}else{
			int ErrorCode = INetSDK.GetLastError();
			ToolKits.writeErrorLog("INetSDK.SetLowRateWPANPower Failed the ErrorCode is "+(ErrorCode&0x7fffffff));
		}
	}

	// 智能锁修改用户信息
	private void SetSmartLockUsername(){
		NET_IN_SET_SMART_LOCK_USERNAME inParam = new NET_IN_SET_SMART_LOCK_USERNAME();
		System.arraycopy("1234567".getBytes(), 0, inParam.szName, 0, "1234567".getBytes().length);
		System.arraycopy("AXJ07ZNS123456B".getBytes(), 0, inParam.szSerialNumber, 0, "AXJ07ZNS123456B".getBytes().length);
		inParam.emType = 2;
		System.arraycopy("2".getBytes(), 0, inParam.szUserID, 0, "2".getBytes().length);
		NET_OUT_SET_SMART_LOCK_USERNAME outParam = new NET_OUT_SET_SMART_LOCK_USERNAME();
		boolean result = INetSDK.SetSmartLockUsername(__LoginHandle,inParam,outParam,3000);
		if(result){
			ToolKits.writeLog("SetSmartLockUsername ok..");
		}else{
			ToolKits.writeLog("SetSmartLockUsername failed:"+(INetSDK.GetLastError()&0x7fffffff));
		}
	}

	// 获取当前智能锁的注册用户信息
	public void GetSmartLockRegisterInfo() {
		NET_IN_GET_SMART_LOCK_REGISTER_INFO in_param_get = new NET_IN_GET_SMART_LOCK_REGISTER_INFO();
		in_param_get.nOffset = 0;
		System.arraycopy("AXJ07ZNS123456B".getBytes(), 0, in_param_get.szSerialNumber, 0, "AXJ07ZNS123456B".getBytes().length);
		NET_OUT_GET_SMART_LOCK_REGISTER_INFO out_get_param = new NET_OUT_GET_SMART_LOCK_REGISTER_INFO();
		boolean get = INetSDK.GetSmartLockRegisterInfo(__LoginHandle, in_param_get, out_get_param, 5000);
		if(get){
			ToolKits.writeLog("GetSmartLockRegisterInfo returnCount:"+out_get_param.nReturnCount+";totalCount:"+out_get_param.nTotalCount);
			String temp = "";
			for(int i=0;i<out_get_param.stuRegisterInfo.length;i++){
				temp+= new String((out_get_param.stuRegisterInfo[i].szName))+"  ;";
			}
			ToolKits.writeLog(temp);
		}else{
			ToolKits.writeErrorLog("GetSmartLockRegisterInfo failed:"+(INetSDK.GetLastError()&0x7fffffff));
		}
	}

	// 设备本地升级
	long lUpgradeID = 0;
	public void Upgrade() {
		int emType = EM_UPGRADE_TYPE.SDK_UPGRADE_DEVCONSTINFO_TYPE;
		String pchFileName = "";
		// 开始升级设备程序
		lUpgradeID = INetSDK.StartUpgradeEx(__LoginHandle, emType, pchFileName, cbUpgrade);
		if(lUpgradeID == 0) {
			ToolKits.writeErrorLog("StartUpgradeEx Failed!" );
		} else {
			// 发送数据
			if(INetSDK.SendUpgrade(lUpgradeID)) {
				ToolKits.writeLog("SendUpgrade Succeed!");
			} else {
				ToolKits.writeErrorLog("SendUpgrade Failed!" );
			}
		}
	}
	
	TestUpgradeCallBack cbUpgrade = new TestUpgradeCallBack();
	private int arg2;
	private class TestUpgradeCallBack implements CB_fUpgradeCallBack {
		@Override
		public void invoke(long lLoginID, long lUpgradeChannel, int nTotalSize,
				int nSendSize) {
			int pos = nSendSize/nTotalSize *100;
			ToolKits.writeLog("nSendSize : " + nSendSize);
			ToolKits.writeLog("nTotalSize : " + nTotalSize);
			ToolKits.writeLog("pos : " + pos);
			if(nSendSize == -1) {
				INetSDK.StopUpgrade(lUpgradeID);
			}
		}	
	}
	
	// 查询设备通道名称
	public void QueryChannelName() {
		boolean bRet = false;
		byte name[] = new byte[16 * 32];
		Integer stRet = new Integer(0);
		if(INetSDK.QueryChannelName(__LoginHandle, name, stRet, 1000)) {
			for(int i = 0; i < stRet.intValue(); i++) {
				ToolKits.writeLog("name" + new String(name).trim());
			}
		}
	}

	// 获取密码规范，不用登陆设备
	public void GetPwdSpecification() {
		boolean bRet = false;
		NET_IN_PWD_SPECI inPwdSpeci = new NET_IN_PWD_SPECI();
		String mac = "90:02:91:45:99:99";
		System.arraycopy(mac.getBytes(), 0, inPwdSpeci.szMac, 0, mac.getBytes().length);

		NET_OUT_PWD_SPECI outPwdSpeci = new NET_OUT_PWD_SPECI();
		bRet = INetSDK.GetPwdSpecification(inPwdSpeci, outPwdSpeci, 10000, null);
		if(bRet) {
			ToolKits.writeLog("nMaxPwdLen : " + outPwdSpeci.nMaxPwdLen);
			ToolKits.writeLog("nMinPwdLen : " + outPwdSpeci.nMinPwdLen);
			ToolKits.writeLog("nCombine : " + outPwdSpeci.nCombine);
			ToolKits.writeLog("szType : " + new String(outPwdSpeci.szType).trim());
			ToolKits.writeLog("szCharList : " + new String(outPwdSpeci.szCharList).trim());
		} else {
			ToolKits.writeErrorLog("GetPwdSpecification Failed!" );
		}
	}

	// 图像旋转设置能力
	public void ImageControlCaps() {
		boolean bRet = false;
		int ntype = FinalVar.NET_VIDEO_IMAGECONTROL_CAPS;
		NET_IN_VIDEO_IMAGECONTROL_CAPS inImageCaps = new NET_IN_VIDEO_IMAGECONTROL_CAPS();
		inImageCaps.nChannel = 0;
		NET_OUT_VIDEO_IMAGECONTROL_CAPS outImageCaps = new NET_OUT_VIDEO_IMAGECONTROL_CAPS();
		bRet = INetSDK.GetDevCaps(__LoginHandle, ntype, inImageCaps, outImageCaps, 5000);
		if(bRet) {
			ToolKits.writeLog("bSupport : " + outImageCaps.bSupport);
			ToolKits.writeLog("bMirror : " + outImageCaps.bMirror);
			ToolKits.writeLog("bFlip : " + outImageCaps.bFlip);
			ToolKits.writeLog("bRotate90 : " + outImageCaps.bRotate90);
			ToolKits.writeLog("bFreeze : " + outImageCaps.bFreeze);
			ToolKits.writeLog("emStable : " + outImageCaps.emStable);

		} else {
			ToolKits.writeErrorLog("GetDevCaps Failed!" );
		}
	}

	// 用户管理能力
	public void GetUserManageCaps() {
		boolean bRet = false;
		int nType = FinalVar.NET_USER_MNG_CAPS;
		NET_IN_USER_MNG_GETCAPS inUserMng = new NET_IN_USER_MNG_GETCAPS();
		NET_OUT_USER_MNG_GETCAPS outUserMng = new NET_OUT_USER_MNG_GETCAPS();
		bRet = INetSDK.GetDevCaps(__LoginHandle, nType, inUserMng, outUserMng, 5000);
		if(bRet) {
			ToolKits.writeLog("bAccountLimitation : " + outUserMng.bAccountLimitation);
			ToolKits.writeLog("bIndividualAccessFilter : " + outUserMng.bIndividualAccessFilter);
			ToolKits.writeLog("dwMaxPageSize : " + outUserMng.dwMaxPageSize);
			ToolKits.writeLog("nMaxPwdLen : " + outUserMng.nMaxPwdLen);
			ToolKits.writeLog("nMinPwdLen : " + outUserMng.nMinPwdLen);
			ToolKits.writeLog("nCombine : " + outUserMng.nCombine);
			ToolKits.writeLog("szType : " + new String(outUserMng.szType).trim());
			ToolKits.writeLog("szCharList : " + new String(outUserMng.szCharList).trim());
		} else {
			ToolKits.writeErrorLog("GetDevCaps Failed!" );
		}
	}

	// 雷达配置
	public void RadarConfig() {
		////雷达方向，用的是大华雷达里的雷达方向。    大华雷达是雷达的一项配置。
		int type = NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_RADAR;
		DEV_RADAR_CONFIG radarConfig = new DEV_RADAR_CONFIG();

		// 获取
		if(INetSDK.GetConfig(__LoginHandle, type, 0, radarConfig, 5000, null)) {
			ToolKits.writeLog("雷达使能：" + radarConfig.bEnable);
			ToolKits.writeLog("大华雷达使能：" + radarConfig.bDahuaRadarEnable);
			ToolKits.writeLog("雷达方向：" + radarConfig.stuDhRadarConfig.nDetectMode);

			// 设置
			radarConfig.bEnable = true;
			radarConfig.bDahuaRadarEnable = true;
			radarConfig.stuDhRadarConfig.nDetectMode = 1;
			if(INetSDK.SetConfig(__LoginHandle, type, 0, radarConfig, 5000, null, null)) {
				ToolKits.writeLog("SetConfig Succeed!");
			}
		}
	}

	// 智能交通抓拍配置
	public void TrafficSnapshotConfig() {
		//限速、OSD信息获取/配置
		CFG_TRAFFICSNAPSHOT_INFO trafficshot = new CFG_TRAFFICSNAPSHOT_INFO();

		//获取
		if(ToolKits.GetDevConfig(FinalVar.CFG_CMD_INTELLECTIVETRAFFIC, trafficshot, __LoginHandle, 0, 1024*1024)) {
			ToolKits.writeLog("大小车限速是否有效 ： " + trafficshot.arstDetector[0].bSpeedLimitForSize);
			ToolKits.writeLog("小型车限速值 ： " + trafficshot.arstDetector[0].arnSmallCarSpeedLimit[0] + " / " +
					trafficshot.arstDetector[0].arnSmallCarSpeedLimit[1]);  //arstDetector下标是通道
			ToolKits.writeLog("大型车限速值 ： " + trafficshot.arstDetector[0].arnBigCarSpeedLimit[0] + " / " +
					trafficshot.arstDetector[0].arnBigCarSpeedLimit[1]);  //arstDetector下标是通道

			ToolKits.writeLog("OSD方案内容 : " + trafficshot.stOSD.nOSDContentScheme);
			ToolKits.writeLog("OSD叠加内容自定义排序个数 : " + trafficshot.stOSD.nOSDCustomSortNum);
			for(int i=0; i<trafficshot.stOSD.nOSDCustomSortNum; i++) {
				ToolKits.writeLog("具体叠加元素个数 : " + trafficshot.stOSD.stOSDCustomSorts[i].nElementNum);
				for(int j=0; j<trafficshot.stOSD.stOSDCustomSorts[i].nElementNum; j++) {
					ToolKits.writeLog("名称类型 : " + trafficshot.stOSD.stOSDCustomSorts[i].stElements[j].nNameType);
					ToolKits.writeLog("配置项名称 : " + trafficshot.stOSD.stOSDCustomSorts[i].stElements[j].szName);
				}
			}

			// 配置
			///////限速
			trafficshot.arstDetector[0].bSpeedLimitForSize = true;
			trafficshot.arstDetector[0].arnSmallCarSpeedLimit[0] = 0;
			trafficshot.arstDetector[0].arnSmallCarSpeedLimit[1] = 55;

			//////OSD信息叠加
			trafficshot.stOSD.nOSDCustomSortNum = 1;
			trafficshot.stOSD.stOSDCustomSorts[0].nElementNum = 1;

			///当nOSDContentScheme = 2; 且 nNameType = 0时，叠加信息才有效
			trafficshot.stOSD.nOSDContentScheme = 2; //0=未知, 1=Mask , 2=CustomizeSort
			trafficshot.stOSD.stOSDCustomSorts[0].stElements[0].nNameType = 0;
			String name = "%28"; //[时间日期:%年%月%日%时%分%秒]、[违法代码:%18]、[车牌号码:%09]、[道路方向:%64]、[道路代码:%68]、[车速:%05]、[车牌颜色:%58]、[设备编号:%28]、[欠速比:%66]、[车型:%34]、[车道号:%04]、[防伪码:%41]、[限速:%06]、[车身颜色:%12]、[鉴定证书编号:%42]、[通用自定义:%54]、[车道自定义:%55]
			System.arraycopy(name.getBytes(), 0, trafficshot.stOSD.stOSDCustomSorts[0].stElements[0].szName, 0, name.getBytes().length);  //用于设置叠加信息

			if(ToolKits.SetDevConfig(FinalVar.CFG_CMD_INTELLECTIVETRAFFIC, trafficshot, __LoginHandle, 0, 1024*1024)) {
				ToolKits.writeLog("SetDevConfig Succeed!");
			} else {
				ToolKits.writeErrorLog("SetDevConfig Failed!");
			}
		} else {
			ToolKits.writeErrorLog("GetDevConfig Failed!");
		}
	}

	// 智能交通全局配置（黑名单使能）
	public void TrafficGlobalConfig() {
		///////////启用黑名单////////////
		CFG_TRAFFICGLOBAL_INFO trafficglobal = new CFG_TRAFFICGLOBAL_INFO();
		//获取
		if(ToolKits.GetDevConfig(FinalVar.CFG_CMD_TRAFFICGLOBAL, trafficglobal, __LoginHandle, 0, 1024*1024)) {
			ToolKits.writeLog("黑名单使能 : " + trafficglobal.bEnableBlackList);

			//设置
			trafficglobal.abEnableBlackList = true;
			trafficglobal.bEnableBlackList = true;
			if(ToolKits.SetDevConfig(FinalVar.CFG_CMD_TRAFFICGLOBAL, trafficglobal, __LoginHandle, 0, 1024*1024)) {
				ToolKits.writeLog("SetDevConfig Succeed!");
			} else {
				ToolKits.writeErrorLog("SetDevConfig Failed!");
			}
		} else {
			ToolKits.writeErrorLog("GetDevConfig Failed!");
		}
	}

	/**
	 * 交通白名单查询(精确查找)、删除、修改
	 */
	public void FindRecordTrafficRedList() {
		///////////////白名单查找、删除、修改//////////
		//精确查询,只能根据车牌查找一个
		int nNo = 0;
		FIND_RECORD_TRAFFICREDLIST_CONDITION recordcondition = new FIND_RECORD_TRAFFICREDLIST_CONDITION();  //条件
		String plateNumber = "京a";
		ToolKits.StringToByteArray(plateNumber, recordcondition.szPlateNumber);
		
		NET_IN_FIND_RECORD_PARAM infindRecord = new NET_IN_FIND_RECORD_PARAM();
		infindRecord.emType = EM_NET_RECORD_TYPE.NET_RECORD_TRAFFICREDLIST;
		infindRecord.pQueryCondition = recordcondition;
		NET_OUT_FIND_RECORD_PARAM outfindRecord = new NET_OUT_FIND_RECORD_PARAM();
		if(INetSDK.FindRecord(__LoginHandle, infindRecord, outfindRecord, 5000)) {
			int nCount = 1;
			NET_IN_FIND_NEXT_RECORD_PARAM infindNextRecord = new NET_IN_FIND_NEXT_RECORD_PARAM();
			infindNextRecord.lFindeHandle = outfindRecord.lFindeHandle;
			infindNextRecord.nFileCount = nCount;
			infindNextRecord.emType = EM_NET_RECORD_TYPE.NET_RECORD_TRAFFICREDLIST; 
			
			NET_TRAFFIC_LIST_RECORD[] listRecord = new NET_TRAFFIC_LIST_RECORD[1];
			listRecord[0] = new NET_TRAFFIC_LIST_RECORD();
				
			NET_OUT_FIND_NEXT_RECORD_PARAM outfindNextRecord = new NET_OUT_FIND_NEXT_RECORD_PARAM();
			outfindNextRecord.nMaxRecordNum = nCount;
			outfindNextRecord.pRecordList = listRecord;
			
			int retCount = INetSDK.FindNextRecord(infindNextRecord, outfindNextRecord, 5000);
			if(retCount != 0) {
				ToolKits.writeLog("查到的个数：" + outfindNextRecord.nRetRecordNum);
				
				ToolKits.writeLog("查询到的记录号 : " + listRecord[0].nRecordNo);
				ToolKits.writeLog("车主姓名 : " + new String(listRecord[0].szMasterOfCar).trim());
				ToolKits.writeLog("车牌号 : " + new String(listRecord[0].szPlateNumber).trim());
				ToolKits.writeLog("布控类型 : " + listRecord[0].emControlType);
				
				nNo = listRecord[0].nRecordNo;
				
				
				///////////修改,需要通过精确查询查找记录号///////////
				NET_UPDATE_RECORD_INFO updateRecord = new NET_UPDATE_RECORD_INFO();
				updateRecord.pRecordInfo = listRecord[0];
				updateRecord.pRecordInfo.nRecordNo = nNo;  //之前查询到的记录号 
				String sMasterCar = "22"; //车主姓名
				System.arraycopy(sMasterCar.getBytes(), 0, updateRecord.pRecordInfo.szMasterOfCar, 0, sMasterCar.getBytes().length);
				
				NET_IN_OPERATE_TRAFFIC_LIST_RECORD inOperateList1 = new NET_IN_OPERATE_TRAFFIC_LIST_RECORD();
				inOperateList1.emOperateType = EM_RECORD_OPERATE_TYPE.NET_TRAFFIC_LIST_UPDATE;
				inOperateList1.emRecordType = EM_NET_RECORD_TYPE.NET_RECORD_TRAFFICREDLIST;
				inOperateList1.pstOpreateInfo = updateRecord;
				NET_OUT_OPERATE_TRAFFIC_LIST_RECORD outOperateList1 = new NET_OUT_OPERATE_TRAFFIC_LIST_RECORD();
				if(INetSDK.OperateTrafficList(__LoginHandle, inOperateList1, outOperateList1, 5000)) {
					ToolKits.writeLog("UpdateRecord Succeed!");
				} else {
					ToolKits.writeErrorLog("UpdateRecord Failed!" );
				}  
						
			} else {
				ToolKits.writeErrorLog("FindNextRecord Failed!" );
			}	
		} else {
			ToolKits.writeErrorLog("FindRecord Failed!" );
		}
		
		INetSDK.FindRecordClose(outfindRecord.lFindeHandle);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		///////////删除,需要通过精确查询查找记录号//////////
		NET_REMOVE_RECORD_INFO removeRecord = new NET_REMOVE_RECORD_INFO();
		removeRecord.nRecordNo = nNo;  //之前查询到的记录号 
		
		NET_IN_OPERATE_TRAFFIC_LIST_RECORD inOperateList = new NET_IN_OPERATE_TRAFFIC_LIST_RECORD();
		inOperateList.emOperateType = EM_RECORD_OPERATE_TYPE.NET_TRAFFIC_LIST_REMOVE;
		inOperateList.emRecordType = EM_NET_RECORD_TYPE.NET_RECORD_TRAFFICREDLIST;
		inOperateList.pstOpreateInfo = removeRecord;
		NET_OUT_OPERATE_TRAFFIC_LIST_RECORD outOperateList = new NET_OUT_OPERATE_TRAFFIC_LIST_RECORD();
		if(INetSDK.OperateTrafficList(__LoginHandle, inOperateList, outOperateList, 5000)) {
			ToolKits.writeLog("RemoveRecord Succeed!");
		} else {
			ToolKits.writeErrorLog("RemoveRecord Failed!" );
		}

		//模糊查询，可以查询所有信息
		FindRecordTrafficRedListVague();
	}

	// 交通白名单账户记录(模糊查询)
	public void  FindRecordTrafficRedListVague() {
		//模糊查询，可以查询所有信息
		FIND_RECORD_TRAFFICREDLIST_CONDITION recordcondition = new FIND_RECORD_TRAFFICREDLIST_CONDITION();  //条件
		String plateNumber = "";
		ToolKits.StringToByteArray(plateNumber, recordcondition.szPlateNumberVague);

		NET_IN_FIND_RECORD_PARAM infindRecord = new NET_IN_FIND_RECORD_PARAM();
		infindRecord.emType = EM_NET_RECORD_TYPE.NET_RECORD_TRAFFICREDLIST;
		infindRecord.pQueryCondition = recordcondition;
		NET_OUT_FIND_RECORD_PARAM outfindRecord = new NET_OUT_FIND_RECORD_PARAM();
		if(INetSDK.FindRecord(__LoginHandle, infindRecord, outfindRecord, 5000)) {
			int doNextCount = 0;
			int item = 0;
			while(true) {
				int nCount = 10;
				NET_IN_FIND_NEXT_RECORD_PARAM infindNextRecord = new NET_IN_FIND_NEXT_RECORD_PARAM();
				infindNextRecord.lFindeHandle = outfindRecord.lFindeHandle;
				infindNextRecord.nFileCount = nCount;
				infindNextRecord.emType = EM_NET_RECORD_TYPE.NET_RECORD_TRAFFICREDLIST;

				NET_TRAFFIC_LIST_RECORD[] listRecord = new NET_TRAFFIC_LIST_RECORD[200];   //数组大小，代表可以查询的记录信息最大值
				for(int m=0; m<200; m++) {
					listRecord[m] = new NET_TRAFFIC_LIST_RECORD();
				}

				NET_OUT_FIND_NEXT_RECORD_PARAM outfindNextRecord = new NET_OUT_FIND_NEXT_RECORD_PARAM();
				outfindNextRecord.nMaxRecordNum = nCount;
				outfindNextRecord.pRecordList = listRecord;

				int retCount = INetSDK.FindNextRecord(infindNextRecord, outfindNextRecord, 5000);
				if(retCount != 0) {
					ToolKits.writeLog("查到的个数：" + outfindNextRecord.nRetRecordNum);
					for(int i=0; i<outfindNextRecord.nRetRecordNum; i++) {

						item = i + doNextCount * nCount;
						ToolKits.writeLog("[" + item + "] 查询到的记录号 : " + listRecord[i].nRecordNo);
						ToolKits.writeLog("[" + item + "] 车主姓名 : " + new String(listRecord[i].szMasterOfCar).trim());
						ToolKits.writeLog("[" + item + "] 车牌号 : " + new String(listRecord[i].szPlateNumber).trim());
						ToolKits.writeLog("[" + item + "] 布控类型 : " + listRecord[i].emControlType);
					}

					if(outfindNextRecord.nRetRecordNum < nCount) {
						break;
					} else {
						doNextCount++;
					}

				} else {
					ToolKits.writeErrorLog("FindNextRecord Failed!" );
					break;
				}
			}
			ToolKits.writeLog("查询到的总个数：" + (item + 1));
		} else {
			ToolKits.writeErrorLog("FindRecord Failed!" );
		}

		INetSDK.FindRecordClose(outfindRecord.lFindeHandle);
	}

	// 按设备信息添加显示源
	private void MatrixAddCamerasByDevice(){
		SDK_IN_ADD_LOGIC_BYDEVICE_CAMERA   inParam = new SDK_IN_ADD_LOGIC_BYDEVICE_CAMERA(3);
		String temp = "DH12345678910";
		System.arraycopy(temp.getBytes(), 0, inParam.pszDeviceID, 0, temp.getBytes().length);
		for(int i=0;i<inParam.nCameraCount;i++){
			inParam.pCameras[i].nChannel = i+10;
			inParam.pCameras[i].nUniqueChannel = i+13;
		}
		SDK_OUT_ADD_LOGIC_BYDEVICE_CAMERA  outParam = new SDK_OUT_ADD_LOGIC_BYDEVICE_CAMERA(2);
		boolean result = INetSDK.MatrixAddCamerasByDevice(__LoginHandle,
								inParam,outParam, 4000);
		if(result){
			ToolKits.writeLog("out.nRetResultCount:"+outParam.nRetResultCount+";DeviceID:"+new String(outParam.szDeviceID)+"nUniqueChannel:"+
		outParam.pResults[0].nUniqueChannel+";failedCode:"+outParam.pResults[0].nFailedCode+";failedCode:"+outParam.pResults[1].nFailedCode);
			ToolKits.writeLog("successfully");
		}else{
			ToolKits.writeErrorLog("MatrixAddCameras Failed!" + (INetSDK.GetLastError()&0x7fffffff));
		}
	}

	// 音频输入音量
	private void AudioInputVolumeConfig() {
		CFG_AUDIO_INPUT_VOLUME inputVolume = new CFG_AUDIO_INPUT_VOLUME();

		// 获取
		boolean result = ToolKits.GetDevConfig(FinalVar.CFG_CMD_AUDIO_INPUT_VOLUME,
				inputVolume, __LoginHandle, -1, 1024*1024);
		if (result) {
			ToolKits.writeLog("nAudioInputCount:"+inputVolume.nAudioInputCount);
			for(int i=0;i<inputVolume.szAudioInputVolume.length;i++){
				ToolKits.writeLog("inputVolume:"+(inputVolume.szAudioInputVolume[i]&0xFF));
			}

			// 设置
			result = false;
			result = ToolKits.SetDevConfig(FinalVar.CFG_CMD_AUDIO_INPUT_VOLUME,
				inputVolume, __LoginHandle, -1, 1024*1024);
			if(result){
				ToolKits.writeLog("Set inputVolume Config Successfully");
			}else{
				ToolKits.writeErrorLog("Set inputVolume Config failed");
			}
		}else{
			ToolKits.writeErrorLog("Get inputVolume Config failed");
		}

	}

	// 音频输出音量
	public void AudioOutputVolumeConfig() {
		CFG_AUDIO_OUTPUT_VOLUME outputVolume = new CFG_AUDIO_OUTPUT_VOLUME();

		// 获取
		boolean result = ToolKits.GetDevConfig(FinalVar.CFG_CMD_AUDIO_OUTPUT_VOLUME,
				outputVolume, __LoginHandle, -1, 1024*1024);
		if (result) {
			ToolKits.writeLog("nAudioOutputCount:"+outputVolume.nAudioOutputCount);
			for(int i=0;i<outputVolume.szAudioOutputVolume.length;i++){
				ToolKits.writeLog("outputVolume:"+(outputVolume.szAudioOutputVolume[i]&0xFF));
			}

			// 设置
			result = false;
			result = ToolKits.SetDevConfig(FinalVar.CFG_CMD_AUDIO_OUTPUT_VOLUME,
					outputVolume, __LoginHandle, -1, 1024*1024);
			if(result){
				ToolKits.writeLog("Set outputVolume Config Successfully");
			}else{
				ToolKits.writeErrorLog("Set outputVolume Config failed");
			}
		}else{
			ToolKits.writeErrorLog("Get outputVolume Config failed");
		}
	}
	
	public class Test_fAttachDeviceDiscoveryCB implements CB_fAttachDeviceDiscoveryCB {
		@Override
		public void invoke(long lAttachHandle,
				NET_DEVICE_DISCOVERY_INFO pDeviceInfo, int nDeviceNum) {
			for(int i = 0; i < nDeviceNum; i++) {
				ToolKits.writeLog("-----szMachineName :" + new String(pDeviceInfo.szMachineName).trim() + "\n" +
						"szDeviceClass :" + new String(pDeviceInfo.szDeviceClass).trim() + "\n" +
						"szDeviceType :" + new String(pDeviceInfo.szDeviceType).trim() + "\n" +
						"szSerialNo :" + new String(pDeviceInfo.szSerialNo).trim() + "\n" +
						"szVersion :" + new String(pDeviceInfo.szVersion).trim() + "\n" +
						"szMac :" + new String(pDeviceInfo.szMac).trim() + "\n" +
						"szVendor :" + new String(pDeviceInfo.szVendor).trim() + "\n" +
						"szIPv4Address :" + new String(pDeviceInfo.szIPv4Address).trim() + "\n" +
						"szProtocol :" + new String(pDeviceInfo.szProtocol).trim() + "\n");
			}
		}
	}
	Test_fAttachDeviceDiscoveryCB cbNotify = new Test_fAttachDeviceDiscoveryCB();

	long lDeviceDiscoveryHandle = 0;

	// 注册设备搜索（三代搜索协议）
	public void AttachDeviceDiscovery() {
		NET_IN_ATTACH_DEVICE_DISCOVERY pInAttach = new NET_IN_ATTACH_DEVICE_DISCOVERY();
		pInAttach.emSubClassID = EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE.EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE_ONVIF;
		NET_OUT_ATTACH_DEVICE_DISCOVERY pOutAttach = new NET_OUT_ATTACH_DEVICE_DISCOVERY();
		lDeviceDiscoveryHandle = INetSDK.AttachDeviceDiscovery(__LoginHandle, pInAttach, pOutAttach, cbNotify, 5000);
		if(lDeviceDiscoveryHandle != 0) {
			ToolKits.writeLog("-----AttachDeviceDiscovery Succeed!");
		} else {
			ToolKits.writeErrorLog("-----AttachDeviceDiscovery Failed!" );
		}
	}

	// 启动设备搜索
	public void StartDeviceDiscovery() {
		boolean bRet = false;
		NET_IN_START_DEVICE_DISCOVERY pInStart = new NET_IN_START_DEVICE_DISCOVERY();
		pInStart.emSubClassID = EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE.EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE_ONVIF;
		pInStart.nTimeOut = 30;
		NET_OUT_START_DEVICE_DISCOVERY pOutStart = new NET_OUT_START_DEVICE_DISCOVERY();
		bRet = INetSDK.StartDeviceDiscovery(__LoginHandle, pInStart, pOutStart, 5000);
		if(bRet) {
			ToolKits.writeLog("-----StartDeviceDiscovery Succeed!");
		} else {
			ToolKits.writeErrorLog("-----StartDeviceDiscovery Failed!" );
		}
	}

	// 停止设备搜索
	public void StopDeviceDiscovery() {
		boolean bRet = false;
		NET_IN_STOP_DEVICE_DISCOVERY pInStop = new NET_IN_STOP_DEVICE_DISCOVERY();
		pInStop.emSubClassID = EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE.EM_NET_DEVICE_DISCOVERY_SUBCLASSID_TYPE_ONVIF;
		NET_OUT_STOP_DEVICE_DISCOVERY pOutStop = new NET_OUT_STOP_DEVICE_DISCOVERY();
		bRet = INetSDK.StopDeviceDiscovery(__LoginHandle, pInStop, pOutStop, 5000);
		if(bRet) {
			ToolKits.writeLog("-----StopDeviceDiscovery Succeed!");
		} else {
			ToolKits.writeErrorLog("-----StopDeviceDiscovery Failed!" );
		}

	}

	// 注销设备搜索
	public void DetachDeviceDiscovery() {
		if(lDeviceDiscoveryHandle != 0) {
			INetSDK.DetachDeviceDiscovery(lDeviceDiscoveryHandle);
			lDeviceDiscoveryHandle = 0;
		}
	}

	// FTP上传配置
	public void FTPConfig() {
		//获取
		boolean bRet = false;
		SDKDEV_FTP_PROTO_CFG[] ftp = new SDKDEV_FTP_PROTO_CFG[1];
		ftp[0] = new SDKDEV_FTP_PROTO_CFG();
		Integer lpBytes = new Integer(0);

		// 获取
		bRet = INetSDK.GetDevConfig(__LoginHandle, FinalVar.SDK_DEV_FTP_PROTO_CFG, -1, ftp, lpBytes, 5000);
		if(bRet) {
			ToolKits.writeLog("szHostIp : " + new String(ftp[0].szHostIp).trim());
			ToolKits.writeLog("wHostPort : " + ftp[0].wHostPort);
			ToolKits.writeLog("szUserName : " + new String(ftp[0].szUserName).trim());
			ToolKits.writeLog("szPassword : " + new String(ftp[0].szPassword).trim());
			ToolKits.writeLog("FTP目录路径 : " + new String(ftp[0].szDirName).trim());

			// 设置
			bRet = false;
			String pwd = "admin123";
			System.arraycopy(pwd.getBytes(), 0, ftp[0].szPassword, 0, pwd.getBytes().length);
			bRet = INetSDK.SetDevConfig(__LoginHandle, FinalVar.SDK_DEV_FTP_PROTO_CFG, -1, ftp, 5000);
			if(bRet) {
				ToolKits.writeLog("Set FTP Succeed!");
			} else {
				ToolKits.writeErrorLog("Set FTP Failed!" );
			}
		} else {
			ToolKits.writeErrorLog("Get FTP Failed!" );
		}
	}

	// 蓝牙开门记录集（插入、更新、删除、清除）
	public void BlueToothRecord() {
		// 插入记录
		String username = "admin123";
		String pwd = "admin123";
		String mac = "admin123";
		String note = "admin123";
		NET_RECORD_ACCESS_BLUETOOTH_INFO stuInfoInsert = new NET_RECORD_ACCESS_BLUETOOTH_INFO();  // 蓝牙开门记录集信息
		System.arraycopy(username.getBytes(), 0, stuInfoInsert.szUserName, 0, username.getBytes().length);
		System.arraycopy(pwd.getBytes(), 0, stuInfoInsert.szPassword, 0, pwd.getBytes().length);
		System.arraycopy(mac.getBytes(), 0, stuInfoInsert.szMac, 0, mac.getBytes().length);
		System.arraycopy(note.getBytes(), 0, stuInfoInsert.szNote, 0, note.getBytes().length);

		NET_CTRL_RECORDSET_INSERT_PARAM stuParaInsert = new NET_CTRL_RECORDSET_INSERT_PARAM();
		stuParaInsert.stuCtrlRecordSetInfo.nType = EM_NET_RECORD_TYPE.NET_RECORD_ACCESS_BLUETOOTH;
		stuParaInsert.stuCtrlRecordSetInfo.pBuf = stuInfoInsert;
		if(INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_RECORDSET_INSERT, stuParaInsert, 3000)) {
			ToolKits.writeLog("----------- nRecNo : " + stuParaInsert.stuCtrlRecordSetResult.nRecNo);
		} else {
			ToolKits.writeErrorLog("----------- Insert Failed!" );
		}

		// 更新记录
		NET_RECORD_ACCESS_BLUETOOTH_INFO stuInfoUpdate = new NET_RECORD_ACCESS_BLUETOOTH_INFO();  // 蓝牙开门记录集信息
		stuInfoUpdate.nRecNo = stuParaInsert.stuCtrlRecordSetResult.nRecNo; // 需要修改的记录集编号
		System.arraycopy("654321".getBytes(), 0, stuInfoUpdate.szPassword, 0, "654321".getBytes().length);

		NET_CTRL_RECORDSET_PARAM stuUpdate = new NET_CTRL_RECORDSET_PARAM();
		stuUpdate.nType = EM_NET_RECORD_TYPE.NET_RECORD_ACCESS_BLUETOOTH;
		stuUpdate.pBuf = stuInfoUpdate;
		if(INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_RECORDSET_UPDATE, stuUpdate, 3000)) {
			ToolKits.writeLog("----------- Update Succeed!");
		} else {
			ToolKits.writeErrorLog("----------- Update Failed!" );
		}

		// 删除记录，只需要下发记录集编号
		NET_RECORD_ACCESS_BLUETOOTH_INFO stuInfoRemove = new NET_RECORD_ACCESS_BLUETOOTH_INFO();  // 蓝牙开门记录集信息
		stuInfoRemove.nRecNo = stuParaInsert.stuCtrlRecordSetResult.nRecNo; // 需要修改的记录集编号

		NET_CTRL_RECORDSET_PARAM stuRemove = new NET_CTRL_RECORDSET_PARAM();
		stuRemove.nType = EM_NET_RECORD_TYPE.NET_RECORD_ACCESS_BLUETOOTH;;
		stuRemove.pBuf = stuInfoRemove;
		if(INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_RECORDSET_REMOVE, stuRemove, 3000)) {
			ToolKits.writeLog("----------- Remove Succeed!");
		} else {
			ToolKits.writeErrorLog("----------- Remove Failed!" );
		}

		// 清理记录，只需要下发type
		NET_CTRL_RECORDSET_PARAM stuClear = new NET_CTRL_RECORDSET_PARAM();
		stuClear.nType = EM_NET_RECORD_TYPE.NET_RECORD_ACCESS_BLUETOOTH;;
		if(INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_RECORDSET_CLEAR, stuClear, 3000)) {
			ToolKits.writeLog("----------- Remove Succeed!");
		} else {
			ToolKits.writeErrorLog("----------- Remove Failed!" );
		}
	}

	// 二维码开门记录集（插入、更新、删除、清除）
	public void QrcodeRecord() {
		// 插入记录
		NET_RECORD_ACCESSQRCODE_INFO stuInfoInsert = new NET_RECORD_ACCESSQRCODE_INFO();  // 蓝牙开门记录集信息
		System.arraycopy("123456".getBytes(), 0, stuInfoInsert.szQRCode, 0, "123456".getBytes().length);
		stuInfoInsert.nLeftTimes = 10;
		// 有效开始时间
		stuInfoInsert.stuStartTime.dwYear = 2017;
		stuInfoInsert.stuStartTime.dwMonth = 9;
		stuInfoInsert.stuStartTime.dwDay = 12;
		stuInfoInsert.stuStartTime.dwHour = 11;
		stuInfoInsert.stuStartTime.dwMinute = 0;
		stuInfoInsert.stuStartTime.dwSecond = 0;

		// 有效结束时间
		stuInfoInsert.stuEndTime.dwYear = 2017;
		stuInfoInsert.stuEndTime.dwMonth = 9;
		stuInfoInsert.stuEndTime.dwDay = 13;
		stuInfoInsert.stuEndTime.dwHour = 11;
		stuInfoInsert.stuEndTime.dwMinute = 0;
		stuInfoInsert.stuEndTime.dwSecond = 0;

		NET_CTRL_RECORDSET_INSERT_PARAM stuParaInsert = new NET_CTRL_RECORDSET_INSERT_PARAM();
		stuParaInsert.stuCtrlRecordSetInfo.nType = EM_NET_RECORD_TYPE.NET_RECORD_ACCESSQRCODE;
		stuParaInsert.stuCtrlRecordSetInfo.pBuf = stuInfoInsert;
		if(INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_RECORDSET_INSERT, stuParaInsert, 3000)) {
			ToolKits.writeLog("----------- nRecNo : " + stuParaInsert.stuCtrlRecordSetResult.nRecNo);
		} else {
			ToolKits.writeErrorLog("----------- Insert Failed!" );
		}

		// 更新记录
		NET_RECORD_ACCESSQRCODE_INFO stuInfoUpdate = new NET_RECORD_ACCESSQRCODE_INFO();  // 蓝牙开门记录集信息
		stuInfoUpdate.nRecNo = stuParaInsert.stuCtrlRecordSetResult.nRecNo;   // 需要修改的记录集编号
		System.arraycopy("654321".getBytes(), 0, stuInfoUpdate.szQRCode, 0, "654321".getBytes().length);

		NET_CTRL_RECORDSET_PARAM stuUpdate = new NET_CTRL_RECORDSET_PARAM();
		stuUpdate.nType = EM_NET_RECORD_TYPE.NET_RECORD_ACCESSQRCODE;
		stuUpdate.pBuf = stuInfoUpdate;
		if(INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_RECORDSET_UPDATE, stuUpdate, 3000)) {
			ToolKits.writeLog("----------- Update Succeed!");
		} else {
			ToolKits.writeErrorLog("----------- Update Failed!" );
		}

		// 删除记录，只需要下发记录集编号
		NET_RECORD_ACCESSQRCODE_INFO stuInfoRemove = new NET_RECORD_ACCESSQRCODE_INFO();  // 蓝牙开门记录集信息
		stuInfoRemove.nRecNo = stuParaInsert.stuCtrlRecordSetResult.nRecNo; // 需要修改的记录集编号

		NET_CTRL_RECORDSET_PARAM stuRemove = new NET_CTRL_RECORDSET_PARAM();
		stuRemove.nType = EM_NET_RECORD_TYPE.NET_RECORD_ACCESSQRCODE;;
		stuRemove.pBuf = stuInfoRemove;
		if(INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_RECORDSET_REMOVE, stuRemove, 3000)) {
			ToolKits.writeLog("----------- Remove Succeed!");
		} else {
			ToolKits.writeErrorLog("----------- Remove Failed!" );
		}

		// 清理记录，只需要下发type
		NET_CTRL_RECORDSET_PARAM stuClear = new NET_CTRL_RECORDSET_PARAM();
		stuClear.nType = EM_NET_RECORD_TYPE.NET_RECORD_ACCESSQRCODE;;
		if(INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_CTRL_RECORDSET_CLEAR, stuClear, 3000)) {
			ToolKits.writeLog("----------- Remove Succeed!");
		} else {
			ToolKits.writeErrorLog("----------- Remove Failed!" );
		}
	}

	// 图像属性配置
	public void VideoImageInfoConfig() {
		NET_VIDEOIN_IMAGE_INFO wideoImage = new NET_VIDEOIN_IMAGE_INFO();
		wideoImage.emCfgType = NET_EM_CONFIG_TYPE.NET_EM_CONFIG_DAYTIME ; // 配置类型，获取和设置时都要制定(有些设备是不需要设置的)
		int channel = 1;

		// 获取
		if (INetSDK.GetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_VIDEOIN_IMAGE_OPT, channel, wideoImage, 3000, null)) {
			ToolKits.writeLog("配置类型 ：" + wideoImage.emCfgType);
			ToolKits.writeLog("是否开启画面镜像功能 ：" + wideoImage.bMirror);
			ToolKits.writeLog("是否开启画面翻转功能 ：" + wideoImage.bFlip);
			ToolKits.writeLog("nRotate90 ：" + wideoImage.nRotate90);

			// 设置
			wideoImage.bFlip = true;
			if(INetSDK.SetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_VIDEOIN_IMAGE_OPT, channel, wideoImage, 3000, null, null)) {
				ToolKits.writeLog("Set Succeed!");
			} else {
				ToolKits.writeErrorLog("Set Faile" );
			}
		}
	}

	// 网络监视中断事件配置
	public void NetMonitorAbortConfig() {
	    CFG_NET_MONITOR_ABORT_INFO cfg = new CFG_NET_MONITOR_ABORT_INFO();
		// 获取
		if(ToolKits.GetDevConfig(FinalVar.CFG_CMD_NET_MONITOR_ABORT, cfg, __LoginHandle, 1, 10 * 1024)) {
		    ToolKits.writeLog(cfg.toString());

			// 设置
			if(cfg.bEnable == false) {
				cfg.bEnable = true;
			} else {
				cfg.bEnable = false;
			}
			if(ToolKits.SetDevConfig(FinalVar.CFG_CMD_NET_MONITOR_ABORT, cfg, __LoginHandle, 1, 10 * 1024)) {
				ToolKits.writeLog("Set Succeed!");
			}
		}
	}

	// 扩展报警输入配置
	public void ExAlarmInputConfig() {
		CFG_EXALARMINPUT_INFO cfg = new CFG_EXALARMINPUT_INFO();

		// 获取
		if(ToolKits.GetDevConfig(FinalVar.CFG_CMD_EXALARMINPUT, cfg, __LoginHandle, 1, 10 * 1024)) {
			ToolKits.writeLog(cfg.toString());

			// 设置
			if(cfg.stuAlarmIn.bEnable == false) {
				cfg.stuAlarmIn.bEnable = true;
			} else {
				cfg.stuAlarmIn.bEnable = false;
			}
			if(ToolKits.SetDevConfig(FinalVar.CFG_CMD_EXALARMINPUT, cfg, __LoginHandle, 1, 10 * 1024)) {
				ToolKits.writeLog("Set Succeed!");
			}
		}
	}

	// 本地扩展报警配置
	public void LocalExAlarmConfig() {
		CFG_LOCAL_EXT_ALARME_INFO cfg = new CFG_LOCAL_EXT_ALARME_INFO();

		// 获取
		if (ToolKits.GetDevConfig(FinalVar.CFG_CMD_LOCAL_EXT_ALARM, cfg, __LoginHandle, 1, 1024*32)) {
			ToolKits.writeLog(cfg.toString());

			// 设置
			if (!ToolKits.SetDevConfig(FinalVar.CFG_CMD_LOCAL_EXT_ALARM, cfg, __LoginHandle, 1, 1024*32)) {
				ToolKits.writeErrorLog("Failed to Set DevConfig.");
			}
		}
	}

	// 查询扩展报警盒子报警输入和报警输出的关系
	public void QueryExAlarmChannels() {
		int alarmInCount = 64;  // 扩展模块报警输入通道个数,需用户指定查询个数
		int alarmOutCount = 24; // 扩展模块报警输出通道个数,需用户指定查询个数
		NET_EXALARMCHANNELS exalarmchannels = new NET_EXALARMCHANNELS(alarmInCount, alarmOutCount);

		boolean bRet = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_EXALARMCHANNELS, exalarmchannels, 5000);
		if(bRet) {
			ToolKits.writeLog("扩展模块报警输入通道返回个数 : " + exalarmchannels.nRetExAlarmInCount);
			ToolKits.writeLog("扩展模块报警输出通道返回个数 : " + exalarmchannels.nRetExAlarmOutCount);

			int incount = exalarmchannels.nRetExAlarmInCount > alarmInCount? alarmInCount:exalarmchannels.nRetExAlarmInCount;
			int outcount = exalarmchannels.nRetExAlarmOutCount > alarmOutCount? alarmOutCount:exalarmchannels.nRetExAlarmOutCount;

			ToolKits.writeLog("输入 ： " + incount);
			for(int i = 0; i < incount; i++ ) {
				ToolKits.writeLog("nExAlarmBoxNum ： " + exalarmchannels.pstuExAlarmInInfo[i].nExAlarmBoxNum);
				ToolKits.writeLog("nChannelNum ： " + exalarmchannels.pstuExAlarmInInfo[i].nChannelNum);
				ToolKits.writeLog("szChannelName ： " + new String(exalarmchannels.pstuExAlarmInInfo[i].szChannelName).trim());
			}

			ToolKits.writeLog("输出 ： " + outcount);
			for(int i = 0; i < outcount; i++ ) {
				ToolKits.writeLog("nExAlarmBoxNum ： " + exalarmchannels.pstuExAlarmOutInfo[i].nExAlarmBoxNum);
				ToolKits.writeLog("nChannelNum ： " + exalarmchannels.pstuExAlarmOutInfo[i].nChannelNum);
				ToolKits.writeLog("szChannelName ： " +  new String(exalarmchannels.pstuExAlarmOutInfo[i].szChannelName).trim());
			}
		}
	}

	// 网络扩展配置
	public void NetCfgExConfig() {
		boolean bRet = false;
		int dwCommand = FinalVar.SDK_DEV_NETCFG_EX;
		SDKDEV_NET_CFG_EX[] netCfg = new SDKDEV_NET_CFG_EX[1];
		netCfg[0] = new SDKDEV_NET_CFG_EX();

		// 获取
		Integer lpBytesReturned = new Integer(0);
		bRet = INetSDK.GetDevConfig(__LoginHandle, dwCommand, 0, netCfg, lpBytesReturned, 3000);
		if(bRet){
			ToolKits.writeLog("nEtherNetNum : " + netCfg[0].nEtherNetNum);
			for(int i=0; i<netCfg[0].nEtherNetNum; i++) {
				ToolKits.writeLog("sDevIPAddr : " + new String(netCfg[0].stEtherNet[i].sDevIPAddr).trim());
				ToolKits.writeLog("sDevIPMask : " + new String(netCfg[0].stEtherNet[i].sDevIPMask).trim());
				ToolKits.writeLog("sGatewayIP : " + new String(netCfg[0].stEtherNet[i].sGatewayIP).trim());
				ToolKits.writeLog("byMACAddr : " + new String(netCfg[0].stEtherNet[i].byMACAddr).trim());
			}
			ToolKits.writeLog("sHostIPAddr : " + new String(netCfg[0].struDns.sHostIPAddr).trim());
			ToolKits.writeLog("wTcpPort : " + netCfg[0].wTcpPort);
			ToolKits.writeLog("wUdpPort : " + netCfg[0].wUdpPort);
			ToolKits.writeLog("wHttpPort : " + netCfg[0].wHttpPort);
			ToolKits.writeLog("wHttpsPort : " + netCfg[0].wHttpsPort);

			// 设置
			String ipAddr = "172.29.2.137";
			System.arraycopy(ipAddr.getBytes(), 0, netCfg[0].stEtherNet[0].sDevIPAddr, 0, ipAddr.getBytes().length);

			String ipMask = "255.255.0.0";
			System.arraycopy(ipMask.getBytes(), 0, netCfg[0].stEtherNet[0].sDevIPMask, 0, ipMask.getBytes().length);

			String ipGateway = "172.29.0.2";
			System.arraycopy(ipGateway.getBytes(), 0, netCfg[0].stEtherNet[0].sGatewayIP, 0, ipGateway.getBytes().length);

			String dns = "255.255.1.1";
			System.arraycopy(dns.getBytes(), 0, netCfg[0].struDns.sHostIPAddr, 0, dns.getBytes().length);
			netCfg[0].wTcpPort = 37777;
			netCfg[0].wUdpPort = 37777;
			netCfg[0].wHttpPort = 80;
			netCfg[0].wHttpsPort = 443;
			bRet = false;
			bRet = INetSDK.SetDevConfig(__LoginHandle, dwCommand, 0, netCfg, 3000);
			if(bRet) {
				ToolKits.writeLog("SetDevConfig Succeed!");
			} else {
				ToolKits.writeErrorLog("SetDevConfig Failed!" );
			}
		} else {
			ToolKits.writeErrorLog("GetDevConfig Failed!" );
		}
	}

	// 网络配置
	public void NetWorkConfig() {
		int chn = 0; // 通道号
		CFG_NETWORK_INFO network = new CFG_NETWORK_INFO();

		// 获取
		if(ToolKits.GetDevConfig(FinalVar.CFG_CMD_NETWORK, network, __LoginHandle, chn, 2*1024*1024)) {
			ToolKits.writeLog("网卡数量 : " + network.nInterfaceNum);
			for(int i = 0; i < network.nInterfaceNum; i++) {
				ToolKits.writeLog("是否开启DHCP :" + network.stuInterfaces[i].bDhcpEnable);
			}

			// 设置
			if(ToolKits.SetDevConfig(FinalVar.CFG_CMD_NETWORK, network, __LoginHandle, chn, 2*1024*1024)) {
				ToolKits.writeLog("Set Succed!");
			}
		}
	}

	// RTSP的配置
	public void RTSPConfig() {
		int chn = 0; // 通道号
		CFG_RTSP_INFO_OUT rtsp = new CFG_RTSP_INFO_OUT();

		// 获取
		if(ToolKits.GetDevConfig(FinalVar.CFG_CMD_RTSP, rtsp, __LoginHandle, chn, 2*1024*1024)) {
			ToolKits.writeLog("nPort :" + rtsp.nPort);

			// 设置
			rtsp.nPort = 666;
			if(ToolKits.SetDevConfig(FinalVar.CFG_CMD_RTSP, rtsp, __LoginHandle, chn, 2*1024*1024)) {
				ToolKits.writeLog("Set Succed!");
			}
		}
	}
	/**
	 * 修改设备IP
	 */
	public void ModifyDevice() {
		DEVICE_NET_INFO_EX device = new DEVICE_NET_INFO_EX();
		device.iIPVersion = 4;    // ipv4   还是 ipv6 ，不可少
		String mac = "e0:50:8b:e3:55:f7";
		System.arraycopy(mac.getBytes(), 0, device.szMac, 0, mac.getBytes().length); // mac地址，不可少

		String ipAddr = "172.23.3.191";
		System.arraycopy(ipAddr.getBytes(), 0, device.szIP, 0, ipAddr.getBytes().length);

		String ipMask = "255.255.255.0";
		System.arraycopy(ipMask.getBytes(), 0, device.szSubmask, 0, ipMask.getBytes().length);

		String ipGateway = "172.23.1.1";
		System.arraycopy(ipGateway.getBytes(), 0, device.szGateway, 0, ipGateway.getBytes().length);

		if(INetSDK.ModifyDevice(device, 10000)) {
			ToolKits.writeLog("ModifyDevice Succeed!");
		}else {
			ToolKits.writeErrorLog("ModifyDevice Failed!" );
		}
	}

	/**
	 * 自动布撤防计划配置
	 */
	public void ArmScheduleInfoConfig() {
		NET_CFG_ARMSCHEDULE_INFO armscheduleInfo = new NET_CFG_ARMSCHEDULE_INFO();
		int channel = -1;   // 此配置没通道概念，所以通道要设为 -1
		if (INetSDK.GetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_ARMSCHEDULE, channel, armscheduleInfo, 3000, null)) {
			ToolKits.writeLog("bEnable ：" + armscheduleInfo.bEnable);
			for(int i = 0; i < FinalVar.SDK_N_SCHEDULE_TSECT; i++) {
				for(int j = 0; j < FinalVar.SDK_N_REC_TSECT; j++) {
					ToolKits.writeLog("bEnable:" + armscheduleInfo.stuTimeSection[i][j].bEnable);
					ToolKits.writeLog(armscheduleInfo.stuTimeSection[i][j].iBeginHour + ":" +
							armscheduleInfo.stuTimeSection[i][j].iBeginMin + ":" +
							armscheduleInfo.stuTimeSection[i][j].iBeginSec + "-" +
							armscheduleInfo.stuTimeSection[i][j].iEndHour + ":" +
							armscheduleInfo.stuTimeSection[i][j].iEndMin + ":" +
							armscheduleInfo.stuTimeSection[i][j].iEndSec
					);
				}
			}

			// 设置
			armscheduleInfo.stuTimeSection[0][0].iBeginHour = 1;
			armscheduleInfo.stuTimeSection[0][0].iBeginMin = 1;
			armscheduleInfo.stuTimeSection[0][0].iBeginSec = 1;
			armscheduleInfo.stuTimeSection[0][0].iEndHour = 2;
			armscheduleInfo.stuTimeSection[0][0].iEndMin = 2;
			armscheduleInfo.stuTimeSection[0][0].iEndSec = 2;
			armscheduleInfo.bEnable = true;

			if(INetSDK.SetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_ARMSCHEDULE, channel, armscheduleInfo, 3000, null, null)) {
				ToolKits.writeLog("Set Succeed!");
			} else {
				ToolKits.writeErrorLog("Set Faile" );
			}
		} else {
			ToolKits.writeErrorLog("Get Faile" );
		}
	}

	// 设置GPS状态信息
	public void SetGPSStatus() {
		NET_IN_SET_GPS_STATUS pInParam = new NET_IN_SET_GPS_STATUS();
		NET_OUT_SET_GPS_STATUS pOutParam = new NET_OUT_SET_GPS_STATUS();

		pInParam.bEnable = true;
		pInParam.nChannel = 0;
		pInParam.stGPSInfo.emDateSource = EM_DATE_SOURCE.EM_DATE_SOURCE_GPS; // 数据来源

		pInParam.stGPSInfo.dbLongitude = 320.175694; // 经度(单位是度,范围0-360度)
		pInParam.stGPSInfo.dbLatitude = 110.35325;  // 纬度(单位是度,范围0-180度)
		pInParam.stGPSInfo.dbAltitude = 9999.9;   // 高度(单位:米)
		pInParam.stGPSInfo.dbSpeed = 40.00;     // 速度(单位:km/H)
		pInParam.stGPSInfo.dbBearing = 45.3;   // 方向角(单位:度)
		pInParam.stGPSInfo.emAntennasStatus = NET_THREE_STATUS_BOOL.BOOL_STATUS_TRUE;      // 天线状态(0:坏 1:好)
		pInParam.stGPSInfo.emPositioningResult = NET_THREE_STATUS_BOOL.BOOL_STATUS_TRUE;  // 定位状态(0:不定位 1:定位)
		pInParam.stGPSInfo.nSatelliteCount = 1;  // 卫星个数
		pInParam.stGPSInfo.emWorkStatus = NET_GPS_WORK_STATUS.EM_DIFFERENTIAL_POSITIONING; // 工作状态
		pInParam.stGPSInfo.nAlarmCount = 2; // 报警个数
		pInParam.stGPSInfo.nAlarmState[0] = 1; // 发生的报警位置,值可能多个
		pInParam.stGPSInfo.nAlarmState[1] = 21;
		pInParam.stGPSInfo.dbHDOP = 2; // 水平精度因子

		// 时间
		pInParam.stGPSInfo.stuLocalTime.dwYear = 2017;
		pInParam.stGPSInfo.stuLocalTime.dwMonth = 11;
		pInParam.stGPSInfo.stuLocalTime.dwDay = 1;
		pInParam.stGPSInfo.stuLocalTime.dwHour = 10;
		pInParam.stGPSInfo.stuLocalTime.dwMinute = 0;
		pInParam.stGPSInfo.stuLocalTime.dwSecond = 0;

		if(INetSDK.SetGPSStatus(__LoginHandle, pInParam, pOutParam, 5000)) {
			ToolKits.writeLog("SetGPSStatus Succeed!");
		} else {
			ToolKits.writeErrorLog("SetGPSStatus Failed!");
		}
	}

	/**
	 * 手机推送消息的翻译目标语言配置
	 */
	public void AppLanguageInfoConfig() {
		// 通过获取得到设备支持的语言列表
		CFG_APP_EVENT_LANGUAGE_INFO appLanguageInfo = new CFG_APP_EVENT_LANGUAGE_INFO();

		if(ToolKits.GetDevConfig(FinalVar.CFG_CMD_APP_EVENT_LANGUAGE, appLanguageInfo, __LoginHandle, -1, 2*1024*1024)) {
			ToolKits.writeLog("emCurrLanguage :" + appLanguageInfo.emCurrLanguage);
			ToolKits.writeLog("nSupportLanguageCount :" + appLanguageInfo.nSupportLanguageCount);
			for(int i = 0; i < appLanguageInfo.nSupportLanguageCount; i++) {
				ToolKits.writeLog("emLanguage :" + appLanguageInfo.emLanguage[i]);
			}
		}

		// 根据获取的语言列表，选择其中一种语言设置
		if(appLanguageInfo.emCurrLanguage == appLanguageInfo.emLanguage[0]) {
			appLanguageInfo.emCurrLanguage = appLanguageInfo.emLanguage[1];
		} else if(appLanguageInfo.emCurrLanguage == appLanguageInfo.emLanguage[1]) {
			appLanguageInfo.emCurrLanguage = appLanguageInfo.emLanguage[2];
		} else if(appLanguageInfo.emCurrLanguage == appLanguageInfo.emLanguage[2]) {
			appLanguageInfo.emCurrLanguage = appLanguageInfo.emLanguage[0];
		}

		if(ToolKits.SetDevConfig(FinalVar.CFG_CMD_APP_EVENT_LANGUAGE, appLanguageInfo, __LoginHandle, -1, 2*1024*1024)) {
			ToolKits.writeLog("Set Succed!");
		}
	}

	/**
	 * 根据设备ip初始化账户(组播初始化),初始化之前，要通过设备搜索判断设备是否可以初始化
	 */
	public void InitDevAccount() {
		// 初始化账号
		boolean bRet = false;
		NET_IN_INIT_DEVICE_ACCOUNT inInit = new NET_IN_INIT_DEVICE_ACCOUNT();
		String macAdd = "4c:11:bf:9b:ac:d7";
		System.arraycopy(macAdd.getBytes(), 0, inInit.szMac, 0, macAdd.getBytes().length);
		String username = "admin";
		System.arraycopy(username.getBytes(), 0, inInit.szUserName, 0, username.getBytes().length);
		String password = "admin";
		System.arraycopy(password.getBytes(), 0, inInit.szPwd, 0, password.getBytes().length);
		String cellphone = "15967112475";
		System.arraycopy(cellphone.getBytes(), 0, inInit.szCellPhone, 0, cellphone.getBytes().length);

		///具体看结构体注释
		inInit.byPwdResetWay = 1; //设备搜索到的,必须与搜索到的一致

		NET_OUT_INIT_DEVICE_ACCOUNT outInit = new NET_OUT_INIT_DEVICE_ACCOUNT();

		bRet = INetSDK.InitDevAccount(inInit, outInit, 5000, null);
		if(bRet) {
			ToolKits.writeLog("InitDevAccount Succeed!");
		} else {
			ToolKits.writeErrorLog("InitDevAccount Failed!" );
		}
	}

	/**
	 * 根据设备ip初始化账户(单播初始化)，初始化之前，要通过设备搜索判断设备是否可以初始化
	 */
	public void InitDevAccountByIP() {
		boolean bRet = false;
		NET_IN_INIT_DEVICE_ACCOUNT inInit = new NET_IN_INIT_DEVICE_ACCOUNT();
		String macAdd = "e0:50:8b:e3:55:f7";
		System.arraycopy(macAdd.getBytes(), 0, inInit.szMac, 0, macAdd.getBytes().length);

		String username = "admin";
		System.arraycopy(username.getBytes(), 0, inInit.szUserName, 0, username.getBytes().length);

		String password = "admin123";  // 必须字母与数字结合，8位以上，否则设备不识别
		System.arraycopy(password.getBytes(), 0, inInit.szPwd, 0, password.getBytes().length);

		String cellphone = "15967112475";
		System.arraycopy(cellphone.getBytes(), 0, inInit.szCellPhone, 0, cellphone.getBytes().length);

		String mail = "123@qq.com";
		System.arraycopy(mail.getBytes(), 0, inInit.szMail, 0, mail.getBytes().length);

		///具体看结构体注释
		inInit.byPwdResetWay = 1; //设备搜索到的,必须与搜索到的一致

		NET_OUT_INIT_DEVICE_ACCOUNT outInit = new NET_OUT_INIT_DEVICE_ACCOUNT();

		String szDeviceIP = "192.168.3.46"; // 搜索到的设备ip，跟mac地址对应
		bRet = INetSDK.InitDevAccountByIP(inInit, outInit, 5000, null, szDeviceIP);
		if(bRet) {
			ToolKits.writeLog("InitDevAccountByIP Succeed!");
		} else {
			ToolKits.writeErrorLog("InitDevAccountByIP Failed!" );
		}
	}

	// Dropbox Token 配置
	public void DropBoxTokenConfig() {
		CFG_DROPBOXTOKEN_INFO dropBox = new CFG_DROPBOXTOKEN_INFO();

		if(ToolKits.GetDevConfig(FinalVar.CFG_CMD_DROPBOXTOKEN, dropBox, __LoginHandle, -1, 2*1024*1024)) {
			ToolKits.writeLog("szDropBoxToken : " + new String(dropBox.szDropBoxToken).trim());

			// 如果在获取的基础上进行配置，需要将szDropBoxToken置空；
			String str = "ZD5vAilWJawAAAAAAAAAt";
			ToolKits.StringToByteArray(str,dropBox.szDropBoxToken );

			if(ToolKits.SetDevConfig(FinalVar.CFG_CMD_DROPBOXTOKEN, dropBox, __LoginHandle, -1, 2*1024*1024)) {
				ToolKits.writeLog("SetDevConfig Succeed!");
			} else {
				ToolKits.writeErrorLog("SetDevConfig Failed!");
			}
		} else {
			ToolKits.writeErrorLog("GetDevConfig Failed!");
		}
	}

	/**
	 * 获取云台预置点列表
	 */
	public void GetPtzPresetList() {
		boolean bRet = false;
		int nType = FinalVar.SDK_DEVSTATE_PTZ_PRESET_LIST;
		// 先获取返回的预置点个数
		NET_PTZ_PRESET_LIST ptzPreset1 = new NET_PTZ_PRESET_LIST(5);   // 5为初始化预置点列表数组

		bRet = INetSDK.QueryDevState(__LoginHandle, nType, ptzPreset1, 3000);
		if(bRet) {
			ToolKits.writeLog("dwRetPresetNum : " + ptzPreset1.dwRetPresetNum);

			// 在获取返回的预置点个数的基础上，获取预置点列表
			bRet = false;
			NET_PTZ_PRESET_LIST ptzPreset2 = new NET_PTZ_PRESET_LIST(ptzPreset1.dwRetPresetNum);
			bRet = INetSDK.QueryDevState(__LoginHandle, nType, ptzPreset2, 3000);
			if(bRet) {
				for(int i = 0; i < ptzPreset2.dwRetPresetNum; i++) {
					ToolKits.writeLog("nIndex : " + ptzPreset2.pstuPtzPorsetList[i].nIndex);
					ToolKits.writeLog("szName : " + new String(ptzPreset2.pstuPtzPorsetList[i].szName).trim());
				}
			}
		} else {
			ToolKits.writeErrorLog("Get PTZ Preset Failed!" );
		}
	}

	/**
	 *  云台开机动作设置
	 */
	public void PtzPowerUp() {
		boolean bRet = false;
		String strCmd = FinalVar.CFG_CMD_PTZ_POWERUP;
		int chn = 0;
		CFG_PTZ_POWERUP_INFO ptzPowerInfo = new CFG_PTZ_POWERUP_INFO();

		// 获取
		bRet = ToolKits.GetDevConfig(strCmd, ptzPowerInfo, __LoginHandle, chn, 2* 1024 *1024);
		if(bRet) {
			ToolKits.writeLog("bEnable : " + ptzPowerInfo.bEnable);
			ToolKits.writeLog("nFunction : " + ptzPowerInfo.nFunction);
			ToolKits.writeLog("nScanId : " + ptzPowerInfo.nScanId);
			ToolKits.writeLog("nPresetId : " + ptzPowerInfo.nPresetId);
			ToolKits.writeLog("nPatternId : " + ptzPowerInfo.nPatternId);
			ToolKits.writeLog("nTourId : " + ptzPowerInfo.nTourId);
		} else {
			ToolKits.writeErrorLog("Get Failed!" );
		}

		// 设置
		bRet = false;
		ptzPowerInfo.bEnable = true;
		bRet = ToolKits.SetDevConfig(strCmd, ptzPowerInfo, __LoginHandle, chn, 2* 1024 *1024);
		if(bRet) {
			ToolKits.writeLog("Set Succeed!");
		} else {
			ToolKits.writeErrorLog("Set Failed!" );
		}
	}

	// 删除文件或目录
	public void RemoveRemoteFiles() {
		SDK_IN_REMOVE_REMOTE_FILES stIn = new SDK_IN_REMOVE_REMOTE_FILES(2);
		stIn.pszPath[0] = "/sdcard/11.dav";
		stIn.pszPath[1] = "/sdcard/22.dav";
		SDK_OUT_REMOVE_REMOTE_FILES stOut = new SDK_OUT_REMOVE_REMOTE_FILES();
		boolean bRet = INetSDK.RemoveRemoteFiles(__LoginHandle,stIn, stOut, 5000);
		if(bRet) {
			ToolKits.writeLog("RemoveRemoteFiles Succeed!");
		} else {
			ToolKits.writeErrorLog("RemoveRemoteFiles Failed!" );
		}
	}

	// 雨刷模式相关配置
	public void RainBrushMode() {
		CFG_RAINBRUSHMODE_INFO info = new CFG_RAINBRUSHMODE_INFO();
		// 获取
		boolean zRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_RAINBRUSHMODE, info, __LoginHandle, -1, 10240);
		if (!zRet) {
			ToolKits.showErrorMessage(this, "GetDevConfig failed, " );
		} else {
			ToolKits.writeLog("emMode : " + info.emMode);
			ToolKits.writeLog("emEnableMode : " + info.emEnableMode);
			ToolKits.writeLog("nPort : " + info.nPort);
			ToolKits.writeLog("nSensitivity : " + info.nSensitivity);

			// 设置
			info.nSensitivity = 3;
			zRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_RAINBRUSHMODE, info, __LoginHandle, -1, 10240);
			if (!zRet) {
				ToolKits.showErrorMessage(this, "SetDevConfig failed, " );
			}
		}
	}

	// 查询人脸识别人脸库的信息
	public void FindGroupInfo() {
		// 入参
		NET_IN_FIND_GROUP_INFO stIn = new NET_IN_FIND_GROUP_INFO();
//		String groupId = "1"; // 人员ID
//		System.arraycopy(groupId.getBytes(), 0, stIn.szGroupId, 0, groupId.getBytes().length);    // 此参数不填，就是查询所有的信息

		// 出参
		NET_OUT_FIND_GROUP_INFO stOut = new NET_OUT_FIND_GROUP_INFO(10);   // 需要设备人员组最大个数

		if(INetSDK.FindGroupInfo(__LoginHandle, stIn, stOut, 5000)) {
			ToolKits.writeLog("nRetGroupNum : " + stOut.nRetGroupNum);

			for(int i = 0; i < stOut.nRetGroupNum; i++) {
				ToolKits.writeLog("szGroupId : " + new String(stOut.pGroupInfos[i].szGroupId).trim());
				ToolKits.writeLog("szGroupName : " + new String(stOut.pGroupInfos[i].szGroupName).trim());
			}
		} else {
			ToolKits.writeErrorLog("FindGroupInfo Failed!");
		}
	}
	// 获取在线设备（扩展报警盒子）状态
	public void GetComPortDeviceChannelInfo() {
		// 入参
		NET_IN_GET_COM_PORT_DEVICE_CHANNEL_INFO stIn = new NET_IN_GET_COM_PORT_DEVICE_CHANNEL_INFO();
		stIn.emDeviceType = EM_COM_PORT_DEVICE_TYPE.EM_COM_PORT_DEVICE_TYPE_EXALARMBOX;     // 扩展报警盒子
		// 出参
		int maxOnlineDeviceCount = 10; // 在线的设备的最大个数, 自己设置
		NET_OUT_GET_COM_PORT_DEVICE_CHANNEL_INFO stOut = new NET_OUT_GET_COM_PORT_DEVICE_CHANNEL_INFO(maxOnlineDeviceCount);
		if(INetSDK.GetComPortDeviceChannelInfo(__LoginHandle, stIn, stOut, 5000)) {
			ToolKits.writeLog("设备数目(包括不在线和在线的) : " + stOut.nDeviceCount);
			ToolKits.writeLog("设备返回的当前在线的设备数量 : " + stOut.nRetOnlineDeviceCount);

			// 当前在线的设备的通道索引数组
			int nMinOnlineDeviceCount = maxOnlineDeviceCount >= stOut.nRetOnlineDeviceCount ? stOut.nRetOnlineDeviceCount : maxOnlineDeviceCount;
			for(int i = 0; i < nMinOnlineDeviceCount; i++) {
				ToolKits.writeLog("在线设备通道 ： " + stOut.pOnlineChannel[i]);
			}
		} else {
			ToolKits.writeErrorLog("GetComPortDeviceChannelInfo Failed!");
		}
	}

	// 扩展报警输出配置
	public void ExAlarmOutConfig() {
		// 获取
		boolean bRet = false;
		for(int i = 0; i < 24; i++) {
			CFG_EXALARMOUTPUT_INFO exAlarmOutput = new CFG_EXALARMOUTPUT_INFO();
			bRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_EXALARMOUTPUT, exAlarmOutput, __LoginHandle, i, 10240);
			if (!bRet) {
				ToolKits.writeErrorLog("GetDevConfig failed!");
			} else {
				ToolKits.writeLog("报警通道名称 : " + new String(exAlarmOutput.szChnName).trim());
				ToolKits.writeLog("输出模式 : " + exAlarmOutput.nOutputMode);

				// 设置
				bRet = false;
				bRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_EXALARMOUTPUT, exAlarmOutput, __LoginHandle, i, 10240);
				if (!bRet) {
					ToolKits.writeErrorLog("SetDevConfig failed!");
				} else {
					ToolKits.writeLog("SetDevConfig Succeed!");
				}
			}
		}
	}

	// 报警输出通道配置
	public void AlarmOutConfig() {
		for (int i = 0; i < 24; i++) {
			// 获取
			CFG_ALARMOUT_INFO stCfgAlarmOutInfo = new CFG_ALARMOUT_INFO();
			if (ToolKits.GetDevConfig(FinalVar.CFG_CMD_ALARMOUT, stCfgAlarmOutInfo, __LoginHandle, i, 10240)) {

				ToolKits.writeLog("报警通道号 : " + stCfgAlarmOutInfo.nChannelID);
				ToolKits.writeLog("报警通道名称 : " + new String(stCfgAlarmOutInfo.szChnName).trim());
				ToolKits.writeLog("输出类型 : " + new String(stCfgAlarmOutInfo.szOutputType).trim());
				ToolKits.writeLog("输出模式 : " + stCfgAlarmOutInfo.nOutputMode);

				// 设置
				if (ToolKits.SetDevConfig(FinalVar.CFG_CMD_ALARMOUT, stCfgAlarmOutInfo, __LoginHandle, i, 10240)) {
					ToolKits.writeLog("Set AlarmOut Succeed!");
				} else {
					ToolKits.writeErrorLog("Set AlarmOut Failed!");
				}
			}else{
				ToolKits.writeErrorLog("Get AlarmOut Failed!");
			}
		}
	}

	// 设置报警状态
	public void ControlAlarmOut() {
		ALARMCTRL_PARAM stOut = new ALARMCTRL_PARAM();
		stOut.nAlarmNo = 0;
		stOut.nAction = 1;   // 1：触发报警,0：停止报警

		if(INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_TRIGGER_ALARM_OUT, stOut, 3000)) {
			ToolKits.showMessage(this, "设置触发报警成功");
		} else {
			ToolKits.showErrorMessage(this, "设置触发报警失败");
		}

		stOut.nAction = 0;   // 1：触发报警,0：停止报警
		if(INetSDK.ControlDevice(__LoginHandle, CtrlType.SDK_TRIGGER_ALARM_OUT, stOut, 3000)) {
			ToolKits.showMessage(this, "设置停止报警成功");
		} else {
			ToolKits.showErrorMessage(this, "设置停止报警失败");
		}

	}

	// 扩展模块报警输出通道
	public void GetChannelsState() {
		int minCount = 0;
		int alarmInCount = 16; 		// 报警输入个数,由用户指定
		int alarmOutCount = 16;    // 报警输出个数,由用户指定
		int alarmBellCount = 16;   // 警号个数,由用户指定
		int exAlarmInCount = 16;   // 扩展模块报警输入个数,由用户指定
		int exAlarmOutCount = 16;  // 扩展模块报警输出个数,由用户指定

		NET_CLIENT_ALARM_CHANNELS_STATE alarmChannelState = new NET_CLIENT_ALARM_CHANNELS_STATE(alarmInCount, alarmOutCount ,alarmBellCount, exAlarmInCount, exAlarmOutCount);
		alarmChannelState.emType = NET_ALARM_CHANNEL_TYPE.NET_ALARM_CHANNEL_TYPE_EXALARMOUT;  // 查询报警通道的类型

		if(INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_ALL_ALARM_CHANNELS_STATE, alarmChannelState, 3000)) {
			minCount = alarmInCount <= alarmChannelState.nAlarmInRetCount? alarmInCount : alarmChannelState.nAlarmInRetCount;
			for(int i = 0; i < minCount; i++) {
				ToolKits.writeLog("报警输入状态：" + alarmChannelState.pbAlarmInState[i]);
			}

			minCount = alarmOutCount <= alarmChannelState.nAlarmOutRetCount? alarmOutCount : alarmChannelState.nAlarmOutRetCount;
			for(int i = 0; i < minCount; i++) {
				ToolKits.writeLog("报警输出状态：" + alarmChannelState.pbAlarmOutState[i]);
			}

			minCount = alarmBellCount <= alarmChannelState.nAlarmBellRetCount? alarmBellCount : alarmChannelState.nAlarmBellRetCount;
			for(int i = 0; i < minCount; i++) {
				ToolKits.writeLog("警号状态 ：" + alarmChannelState.pbAlarmBellState[i]);
			}

			minCount = exAlarmInCount <= alarmChannelState.nExAlarmInRetCount? exAlarmInCount : alarmChannelState.nExAlarmInRetCount;
			for(int i = 0; i < minCount; i++) {
				ToolKits.writeLog("扩展模块报警输入状态数 ：" + alarmChannelState.pbExAlarmInState[i]);
				ToolKits.writeLog("扩展模块报警输入有效通道的位置 ：" + alarmChannelState.pnExAlarmInDestionation[i]);
			}

			minCount = exAlarmOutCount <= alarmChannelState.nExAlarmOutRetCount? exAlarmOutCount : alarmChannelState.nExAlarmOutRetCount;
			for(int i = 0; i < minCount; i++) {
				ToolKits.writeLog("扩展模块报警输出状态数 ：" + alarmChannelState.pbExAlarmOutState[i]);
				ToolKits.writeLog("扩展模块报警输出有效通道的位置 ：" + alarmChannelState.pnExAlarmOutDestionation[i]);
			}
		} else {
			ToolKits.writeErrorLog("QueryDevState Alarm Channel State Failed!");
		}
	}

	// 获得每个报警输出端口的状态, 由 QueryExAlarmChannels() 获取报警输出通道个数
	public void GetAlarmOutState() {
		//入参
		NET_IN_GET_ALARM_OUT_STATE stIn = new NET_IN_GET_ALARM_OUT_STATE();

		// 出参
		NET_OUT_GET_ALARM_OUT_STATE stOut = new NET_OUT_GET_ALARM_OUT_STATE();

		if(INetSDK.GetAlarmOutState(__LoginHandle, stIn, stOut, 3000)) {
			for(int i = 0; i < 32; i++) {
				ToolKits.writeLog("输出端口状态 : " + stOut.nState[i]);   // 输出端口状态, 0表示没有, 1表示有
			}
		}
	}

	// 获取扩展报警盒能力集, QueryExAlarmChannels()获取通道个数，
	public void QueryExAlarmBoxCaps() {
		int chn = 0;  // 通道
		CFG_CAP_EXALARMBOX_INFO alarmBox = new CFG_CAP_EXALARMBOX_INFO();
		String szCommand = FinalVar.CFG_CAP_CMD_EXALARMBOX;
		char szOutBuffer[] = new char[1024];
		Integer stError = new Integer(0);

		boolean bRet = INetSDK.QueryNewSystemInfo(__LoginHandle, szCommand, chn, szOutBuffer, stError, 5000);
		if(bRet) {
			boolean bQu = INetSDK.ParseData(szCommand, szOutBuffer, alarmBox, null);
			if(bQu){
				ToolKits.writeLog("扩展报警模块输入个数：" + alarmBox.nAlarmInCount);
				ToolKits.writeLog("扩展报警模块输出个数：" + alarmBox.nAlarmOutCount);
			} else {
				ToolKits.writeErrorLog("ParseData Failed!" );
			}
		} else {
			ToolKits.writeErrorLog("QueryNewSystemInfo Failed!" );
		}
	}

	// 扩展模块报警盒配置
	public void ExAlarmBoxInfoConfig() {
		// 获取
		boolean bRet = false;
		for(int i = 0; i < 24; i++) {
			CFG_EXALARMBOX_INFO exAlarmBox = new CFG_EXALARMBOX_INFO();
			bRet = ToolKits.GetDevConfig(FinalVar.CFG_CMD_EXALARMBOX, exAlarmBox, __LoginHandle, i, 10240);
			if (!bRet) {
				ToolKits.writeErrorLog("GetDevConfig failed!");
			} else {
				ToolKits.writeLog("使能开关 : " + exAlarmBox.bEnable);
				ToolKits.writeLog("协议名称 : " + new String(exAlarmBox.szProtocolName).trim());
				ToolKits.writeLog("使用的串口端口号 : " + exAlarmBox.nPort);
				ToolKits.writeLog("设备地址 : " + exAlarmBox.nAddress);
				ToolKits.writeLog("串口属性 : " + exAlarmBox.stuCommAttr.byDataBit + "-" + exAlarmBox.stuCommAttr.byStopBit + "-" + exAlarmBox.stuCommAttr.byParity + "-" + exAlarmBox.stuCommAttr.byBaudRate);

				// 设置
				bRet = false;
				bRet = ToolKits.SetDevConfig(FinalVar.CFG_CMD_EXALARMBOX, exAlarmBox, __LoginHandle, i, 10240);
				if (!bRet) {
					ToolKits.writeErrorLog("SetDevConfig failed!");
				} else {
					ToolKits.writeLog("SetDevConfig Succeed!");
				}
			}
		}
	}

	// 叠加通道标题属性配置
	public void ChannelTitleConfig() {
		NET_OSD_CHANNEL_TITLE stIn = new NET_OSD_CHANNEL_TITLE();
		stIn.emOsdBlendType = NET_EM_OSD_BLEND_TYPE.NET_EM_OSD_BLEND_TYPE_MAIN; // 叠加类型
		int channel = 0;  // 通道号

		// 获取
		if (INetSDK.GetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_CHANNELTITLE, channel, stIn, 3000, null)) {
			ToolKits.writeLog(stIn.toString());
		} else {
			ToolKits.writeErrorLog("Get Faile" );
		}

		// 设置
		stIn.bEncodeBlend = true;  // 是否叠加
		if(INetSDK.SetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_CHANNELTITLE, channel, stIn, 3000, null, null)) {
			ToolKits.writeLog("Set Succeed!");
		} else {
			ToolKits.writeErrorLog("Set Faile" );
		}
	}

	// 叠加时间标题属性配置
	public void TimeTitleConfig() {
		NET_OSD_TIME_TITLE stIn = new NET_OSD_TIME_TITLE();
		stIn.emOsdBlendType = NET_EM_OSD_BLEND_TYPE.NET_EM_OSD_BLEND_TYPE_MAIN; // 叠加类型,不管是获取还是设置都要设置该字段
		int channel = 0;  // 通道号

		// 获取
		if (INetSDK.GetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_TIMETITLE, channel, stIn, 3000, null)) {
			ToolKits.writeLog(stIn.toString());
		} else {
			ToolKits.writeErrorLog("Get Faile" );
		}

		// 设置
		stIn.bEncodeBlend = true;  // 是否叠加
		stIn.bShowWeek = true;     // 是否显示星期
		if(INetSDK.SetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_TIMETITLE, channel, stIn, 3000, null, null)) {
			ToolKits.writeLog("Set Succeed!");
		} else {
			ToolKits.writeErrorLog("Set Faile" );
		}
	}

	// 叠加自定义标题属性配置
	public void CustomTitleConfig() {
		NET_OSD_CUSTOM_TITLE stIn = new NET_OSD_CUSTOM_TITLE();
		stIn.emOsdBlendType = NET_EM_OSD_BLEND_TYPE.NET_EM_OSD_BLEND_TYPE_MAIN; // 叠加类型,不管是获取还是设置都要设置该字段
		int channel = 0;  // 通道号

		// 获取
		if (INetSDK.GetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_CUSTOMTITLE, channel, stIn, 3000, null)) {
			ToolKits.writeLog(stIn.toString());
			for (int i = 0; i < stIn.nCustomTitleNum; i++) {
				ToolKits.writeLog("NET_CUSTOM_TITLE_INFO{num=" + i + ", bEncodeBlend=" + stIn.stuCustomTitle[i].bEncodeBlend + ", szText=" + new String(stIn.stuCustomTitle[i].szText) + '}');
			}
		} else {
			ToolKits.writeErrorLog("Get Faile" );
		}

		stIn.stuCustomTitle[0].bEncodeBlend = true;
		String title = "小舟从此逝，江海寄余生！";
		Arrays.fill(stIn.stuCustomTitle[0].szText, (byte) 0);
		System.arraycopy(title.getBytes(), 0, stIn.stuCustomTitle[0].szText, 0, title.getBytes().length);

		if(INetSDK.SetConfig(__LoginHandle, NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_CUSTOMTITLE, channel, stIn, 3000, null, null)) {
			ToolKits.writeLog("Set Succeed!");
		} else {
			ToolKits.writeErrorLog("Set Faile" );
		}
	}

	// 发送同轴IO控制命令
	public void SetCoaxialControlIO() {
		// 入参
		NET_IN_CONTROL_COAXIAL_CONTROL_IO stIn = new NET_IN_CONTROL_COAXIAL_CONTROL_IO();
		stIn.nChannel = 0; 	// 通道号
		stIn.nInfoCount = 2;  // 同轴IO信息个数

		stIn.stInfo[0].emType = EM_COAXIAL_CONTROL_IO_TYPE.EM_COAXIAL_CONTROL_IO_TYPE_LIGHT;  // 同轴IO控制类型
		stIn.stInfo[0].emSwicth = EM_COAXIAL_CONTROL_IO_SWITCH.EM_COAXIAL_CONTROL_IO_SWITCH_OPEN;  // 同轴IO控制开关
		stIn.stInfo[0].emMode = EM_COAXIAL_CONTROL_IO_TRIGGER_MODE.EM_COAXIAL_CONTROL_IO_TRIGGER_MODE_MANUAL_TRIGGER;  // 同轴IO触发方式

		stIn.stInfo[1].emType = EM_COAXIAL_CONTROL_IO_TYPE.EM_COAXIAL_CONTROL_IO_TYPE_LIGHT;  // 同轴IO控制类型
		stIn.stInfo[1].emSwicth = EM_COAXIAL_CONTROL_IO_SWITCH.EM_COAXIAL_CONTROL_IO_SWITCH_OPEN;  // 同轴IO控制开关
		stIn.stInfo[1].emMode = EM_COAXIAL_CONTROL_IO_TRIGGER_MODE.EM_COAXIAL_CONTROL_IO_TRIGGER_MODE_MANUAL_TRIGGER;  // 同轴IO触发方式

		// 出参
		NET_OUT_CONTROL_COAXIAL_CONTROL_IO stOut = new NET_OUT_CONTROL_COAXIAL_CONTROL_IO();
		if(INetSDK.ControlDeviceEx(__LoginHandle, CtrlType.SDK_CTRL_COAXIAL_CONTROL_IO, stIn, stOut, 3000)) {
			ToolKits.writeLog("ControlDeviceEx Succeed!");
		} else {
			ToolKits.writeErrorLog("ControlDeviceEx Failed!");
		}
	}

	// 获取同轴IO控制能力
	public void GetCoaxialControlIOCaps() {
		int nType = FinalVar.NET_COAXIAL_CONTROL_IO_CAPS;
		// 入参
		NET_IN_GET_COAXIAL_CONTROL_IO_CAPS stIn = new NET_IN_GET_COAXIAL_CONTROL_IO_CAPS();
		stIn.nChannel = 0; // 通道号

		// 出参
		NET_OUT_GET_COAXIAL_CONTROL_IO_CAPS stOut = new NET_OUT_GET_COAXIAL_CONTROL_IO_CAPS();

		if(INetSDK.GetDevCaps(__LoginHandle, nType, stIn, stOut, 5000)) {
			ToolKits.writeLog("bSupportControlLight : " + stOut.bSupportControlLight);
			ToolKits.writeLog("bSupportControlSpeaker : " + stOut.bSupportControlSpeaker);
		} else {
			ToolKits.writeErrorLog("GetDevCaps Failed!" );
		}
	}

	// 获取无线设备信号强度
	public void GetWireLessDevSignal() {
		int type = CtrlType.SDK_CTRL_LOWRATEWPAN_GETWIRELESSDEVSIGNAL;
		NET_IN_CTRL_LOWRATEWPAN_GETWIRELESSDEVSIGNAL inWireSignal = new NET_IN_CTRL_LOWRATEWPAN_GETWIRELESSDEVSIGNAL();
		inWireSignal.nStartIndex = 0; // 开始的索引号，从0开始
		inWireSignal.nDevStateNum = 10;   // 本次获取的设备状态条数

		NET_OUT_CTRL_LOWRATEWPAN_GETWIRELESSDEVSIGNAL outWireSignal = new NET_OUT_CTRL_LOWRATEWPAN_GETWIRELESSDEVSIGNAL();
		if(INetSDK.ControlDeviceEx(__LoginHandle, type, inWireSignal, outWireSignal, 3000)) {
			ToolKits.writeLog("获取无线设备数量:" + outWireSignal.nWirelessDevNum);

			for(int i = 0; i < outWireSignal.nWirelessDevNum; i++) {
				ToolKits.writeLog("设备序列号：" + outWireSignal.stuDevSignal[i].szDevSN);
				ToolKits.writeLog("无线信号强度百分比：" + outWireSignal.stuDevSignal[i].unSignal + "\n");
			}
		} else {
			ToolKits.writeErrorLog("GetWireLessDevSignal Failed!" );
		}
	}

	// 视频输入颜色配置
	public void VideoInColorInfoConfig() {
		// 获取
		NET_VIDEOIN_COLOR_INFO msg = new NET_VIDEOIN_COLOR_INFO();
		msg.emCfgType = NET_EM_CONFIG_TYPE.NET_EM_CONFIG_NIGHT;    // 配置类型，获取和设置时都要制定
		int nType = NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_VIDEOIN_COLOR;
		int nChannelID = -1;

		if(INetSDK.GetConfig(__LoginHandle, nType, nChannelID, msg, _waittime, null)) {
			ToolKits.writeLog("配置类型:" + msg.emCfgType);
			ToolKits.writeLog("亮度:" + msg.nBrightness);
			ToolKits.writeLog("对比度:" + msg.nContrast);
			ToolKits.writeLog("饱和度:" + msg.nSaturation);
			ToolKits.writeLog("伽马值:" + msg.nGamma);

			// 设置
			msg.emCfgType = 2;
			msg.nBrightness = 80;
			if(INetSDK.SetConfig(__LoginHandle, nType, nChannelID, msg, _waittime, null, null)) {
				ToolKits.writeLog("Set VideoIn Color Succeed!");
			} else {
				ToolKits.writeErrorLog("Set VideoIn Color Failed!");
			}
		} else {
			ToolKits.writeErrorLog("Get VideoIn Color Failed!");
		}
	}

	/**
	 * 视频分析能力集：比如 场景名称
	 */
	public void QueryVideoAnalyseInfo() {
		int nChn = 0; // 通道号
		Integer stErr = new Integer(0);
		String szCommand = FinalVar.CFG_CAP_CMD_VIDEOANALYSE;   // 频分析能力集
		char[] szOutBuffer = new char[1024*10];
		CFG_CAP_ANALYSE_INFO msg = new CFG_CAP_ANALYSE_INFO();
		if (INetSDK.QueryNewSystemInfo(__LoginHandle, szCommand, nChn, szOutBuffer, stErr, 4000)) {
			boolean bRet = INetSDK.ParseData(szCommand, szOutBuffer, msg, null);
			if (!bRet) {
				ToolKits.writeErrorLog(" CFG_CAP_CMD_ADAPTENCODE error");
			} else {
				for(int i = 0; i < msg.nSupportedSceneNum; i++) {
					ToolKits.writeLog("场景 : " + new String(msg.szSceneName[i]).trim());
				}
			}
		}
	}

	/**
	 * 查询硬盘状态
	 */
	public void QueryHDDState() {
		boolean bRet = false;
		SDK_HARDDISK_STATE msg = new SDK_HARDDISK_STATE();
		bRet = INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_DISK, msg, 3000);
		if(bRet) {
			for(int i = 0; i < msg.dwDiskNum; i++) {
				// 倒序解析，7~4位，对应bit0~3， 低四位的值表示硬盘的状态，0-休眠,1-活动,2-故障等
				byte[] b = ToolKits.getByteArray(msg.stDisks[i].dwStatus);

				for(int j = 0; j < b.length; j++) {
					ToolKits.writeLog(" " + b[j]);
				}

				if(b[b.length - 1] == 1) {
					ToolKits.writeLog("活动");
				} else if(b[b.length - 2] == 1) {
					ToolKits.writeLog("故障");
				} else if(b[b.length - 1] == 0 && b[b.length - 2] == 0) {
					ToolKits.writeLog("休眠");
				}
			}
		}
	}

	// 获取编码能力：比如音频使能
	public void GetEncodeCaps() {
		// Get encode
		CFG_ENCODE_INFO encodeInfo = new CFG_ENCODE_INFO();
		int channel = 0;
		if(!ToolKits.GetDevConfig(FinalVar.CFG_CMD_ENCODE, encodeInfo, __LoginHandle, channel, 1024 * 1024 * 2)){
			ToolKits.writeErrorLog("GetDevConfig for CFG_CMD_ENCODE falied in initEncodeData..");
		}

		// 将 CFG_ENCODE_INFO 转为 char[]
		char[] cJson = new char[1024 * 1024 * 2];
		if (!INetSDK.PacketData(FinalVar.CFG_CMD_ENCODE, encodeInfo, cJson, 1024 * 1024 * 2)) {
			ToolKits.writeErrorLog("PacketData Failed!");
		}

		// 获取能力
		int ntype = FinalVar.NET_ENCODE_CFG_CAPS;
		// 入参
		NET_IN_ENCODE_CFG_CAPS stIn = new NET_IN_ENCODE_CFG_CAPS();
		stIn.nChannelId = channel;  // 通道号
		stIn.nStreamType = 0;  // 码流类型，0：主码流；1：辅码流1；2：辅码流2；3：辅码流3；4：抓图码流
		stIn.pchEncodeJson = ToolKits.getBytes(cJson);

		// 出参
		NET_OUT_ENCODE_CFG_CAPS stOut = new NET_OUT_ENCODE_CFG_CAPS();
		boolean bRet = INetSDK.GetDevCaps(__LoginHandle, ntype, stIn, stOut, 5000);
		if(bRet) {
			// 入参是主码流，获取到的就是 主码流配置对应能力
			ToolKits.writeLog("");
		} else {
			ToolKits.writeErrorLog("GetDevCaps Failed!" );
		}
	}

	// 曝光配置
	public void VideoInExposureInfoConfig() {
		int nMaxExposureNum = 5;   // 配置最大个数
		String cmdStr = FinalVar.CFG_CMD_VIDEOIN_EXPOSURE;

		CFG_VIDEOIN_EXPOSURE_INFO videoInExposureInfo = new CFG_VIDEOIN_EXPOSURE_INFO(nMaxExposureNum);

		int chn = 0;
		// 获取
		if(ToolKits.GetDevConfig(cmdStr, videoInExposureInfo , __LoginHandle, chn, 1024 * 1024)) {
			ToolKits.writeLog("\n" + "返回的配置个数:" + videoInExposureInfo.dwRetExposureNum);

			int count = videoInExposureInfo.dwMaxExposureNum > videoInExposureInfo.dwRetExposureNum ? videoInExposureInfo.dwRetExposureNum : videoInExposureInfo.dwMaxExposureNum;

			for(int j = 0; j < count; j++) {
				ToolKits.writeLog("曝光模式:" + videoInExposureInfo.pstuVideoInExposure[j].byExposureMode);
				ToolKits.writeLog("曝光补偿:" + videoInExposureInfo.pstuVideoInExposure[j].byCompensation);
				ToolKits.writeLog("慢曝光:" + videoInExposureInfo.pstuVideoInExposure[j].bySlowAutoExposure);
				ToolKits.writeLog("慢曝光等级:" + videoInExposureInfo.pstuVideoInExposure[j].bySlowSpeed);
				ToolKits.writeLog("曝光速度等级:" + videoInExposureInfo.pstuVideoInExposure[j].byExposureSpeed);
				ToolKits.writeLog("自动曝光时间下限:" + videoInExposureInfo.pstuVideoInExposure[j].fValue1);
				ToolKits.writeLog("自动曝光时间上限:" + videoInExposureInfo.pstuVideoInExposure[j].fValue2);
			}


			// 设置  byExposureMode： 0 自动； 4 手动     i: 0白天，1夜晚，2普通
			for(int i = 0; i < count; i++) {
				if(videoInExposureInfo.pstuVideoInExposure[i].byExposureMode == 0) {
					videoInExposureInfo.pstuVideoInExposure[i].byExposureMode = 4;
				} else if(videoInExposureInfo.pstuVideoInExposure[i].byExposureMode == 4){
					videoInExposureInfo.pstuVideoInExposure[i].byExposureMode =0;
				}
			}

			if(ToolKits.SetDevConfig(cmdStr, videoInExposureInfo , __LoginHandle, chn, 1024 * 1024)) {
				ToolKits.writeLog("Set Exposure Succeed!");
			}
		}
	}

	// 指示灯控制配置
	public void LightGlobalConfig() {
		String cmdStr = FinalVar.CFG_CMD_LIGHT_GLOBAL;

		CFG_LIGHT_GLOBAL light = new CFG_LIGHT_GLOBAL();

			int chn = -1;
		// 获取
		if(ToolKits.GetDevConfig(cmdStr, light , __LoginHandle, chn, 1024 * 1024)) {

			for(int j = 0; j < light.nLightGlobalCount; j++) {
				ToolKits.writeLog("bLightEnable:" + light.bLightEnable[j]);
			}


			if(light.bLightEnable[0]) {
				light.bLightEnable[0] = false;
			} else{
				light.bLightEnable[0] = true;
			}

			if(ToolKits.SetDevConfig(cmdStr, light , __LoginHandle, chn, 1024 * 1024)) {
				ToolKits.writeLog("Set LightGlobal Succeed!");
			}
		}
	}

	// 白光灯视频通道配置
	public void CoaxialLightConfig() {
		NET_CFG_COAXIAL_LIGHT_INFO cfgData = new NET_CFG_COAXIAL_LIGHT_INFO();
		int emCfgOpType = NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_COAXIAL_LIGHT;
		int nChannelID = -1;

		if(INetSDK.GetConfig(__LoginHandle, emCfgOpType, nChannelID, cfgData, 3000, null)) {
			ToolKits.writeLog("bEnable " + cfgData.bEnable);

			for(int i = 0; i < FinalVar.SDK_TSCHE_DAY_NUM; i++) {
				for(int j = 0; j < FinalVar.SDK_TSCHE_SEC_NUM; j++) {
					ToolKits.writeLog("stuTimeSection : " + cfgData.stuTimeSection[i][j].toStartString() + "   " +  cfgData.stuTimeSection[i][j].toEndString());
				}
			}

			INetSDK.SetConfig(__LoginHandle, emCfgOpType, nChannelID, cfgData, 3000, null, null);
		} else {
			ToolKits.writeErrorLog("GetConfig Failed!");
		}
	}

	// PIR配置
	public void PirAlarmParaConfigm() {
		//////////// 获取PIR配置 ////////////////
		// 入参
		NET_IN_GET_PIR_ALARM_PARAM inGet = new NET_IN_GET_PIR_ALARM_PARAM();
		inGet.nChannel = 0; // 通道号

		// 出参
		NET_OUT_GET_PIR_ALARM_PARAM outGet = new NET_OUT_GET_PIR_ALARM_PARAM();

		if(INetSDK.GetPirAlarmParam(__LoginHandle, inGet, outGet, 3000)) {
			ToolKits.writeLog("bEnable:" + outGet.stPirAlarmInfo.bEnable);
			for(int i = 0; i < outGet.stPirAlarmInfo.nDetectWindowCount; i++) {
				ToolKits.writeLog("stDetectWindow:" + outGet.stPirAlarmInfo.stDetectWindow[i].toString());
			}
			ToolKits.writeLog("stPirLink:" + outGet.stPirAlarmInfo.stPirLink.toString());


			///////////// 设置PIR配置 ///////////////
			if(outGet.stPirAlarmInfo.bEnable) {
				outGet.stPirAlarmInfo.bEnable = false;
			} else {
				outGet.stPirAlarmInfo.bEnable = true;
			}

			// 入参
			NET_IN_SET_PIR_ALARM_PARAM inSet = new NET_IN_SET_PIR_ALARM_PARAM();
			inSet.nChannel = 0; // 通道号
			inSet.stPirAlarmInfo = outGet.stPirAlarmInfo;

			// 出参
			NET_OUT_SET_PIR_ALARM_PARAM outSet = new NET_OUT_SET_PIR_ALARM_PARAM();

			if(INetSDK.SetPirAlarmParam(__LoginHandle, inSet, outSet, 3000)) {
				ToolKits.writeLog("SetPirAlarmParam Succeed!");
			} else {
				ToolKits.writeErrorLog("SetPirAlarmParam Failed!");
			}


		} else {
			ToolKits.writeErrorLog("GetPirAlarmParam Failed!");
		}
	}

	// 查询设备通道信息，判断 是 cvi 还是 ipc
	public void QueryDevChannelInfo() {
		NET_DEV_CHN_COUNT_INFO channelInfo = new NET_DEV_CHN_COUNT_INFO();
		if(INetSDK.QueryDevState(__LoginHandle, FinalVar.SDK_DEVSTATE_DEV_CHN_COUNT, channelInfo, 3000)) {
			//  如果 nCurLocal = 4，那么CVI通道就是 0~3；    如果 nCurRemote = 4， 那么ipc通道就是 4 ~ 7
			ToolKits.writeLog("CVI通道:" + channelInfo.stuVideoIn.nCurLocal);       // 表示前N个是CVI通道
			ToolKits.writeLog("IPC通道:" + channelInfo.stuVideoIn.nCurRemote);      // 表示N ~ N+X 个通道是IPC通道
		}
	}

	 // 初始化账户(华为专用定制)
	// web初始化，需要调用下面的俩个方法。
	 public void InitAccount() {
		 InitAccountSDK();
		 InitAccountWeb();
	 }

	public void InitAccountSDK() {
		boolean bRet = false;
		NET_IN_INIT_ACCOUNT inInit = new NET_IN_INIT_ACCOUNT();
		String username = "admin";
		System.arraycopy(username.getBytes(), 0, inInit.szUser, 0, username.getBytes().length);

		String password = "admin123";
		System.arraycopy(password.getBytes(), 0, inInit.szPwd, 0, password.getBytes().length);

		String mac = "3c:ef:8c:f8:31:89";
		System.arraycopy(mac.getBytes(), 0, inInit.szMac, 0, mac.getBytes().length);

		inInit.emAccountType = EM_ACCOUNT_TYPE.EM_ACCOUNT_TYPE_SDK;

		NET_OUT_INIT_ACCOUNT outInit = new NET_OUT_INIT_ACCOUNT();

		bRet = INetSDK.InitAccount(inInit, outInit, 5000, null);
		if(bRet) {
			ToolKits.writeLog("Init SDK Account Succeed!");
		} else {
			ToolKits.writeErrorLog("Init SDK Account Failed!");
		}
	}

	public void InitAccountWeb() {
		boolean bRet = false;
		NET_IN_INIT_ACCOUNT inInit = new NET_IN_INIT_ACCOUNT();
		String username = "admin";
		System.arraycopy(username.getBytes(), 0, inInit.szUser, 0, username.getBytes().length);

		String password = "admin123";
		System.arraycopy(password.getBytes(), 0, inInit.szPwd, 0, password.getBytes().length);

		String mac = "3c:ef:8c:f8:31:89";
		System.arraycopy(mac.getBytes(), 0, inInit.szMac, 0, mac.getBytes().length);

		inInit.emAccountType = EM_ACCOUNT_TYPE.EM_ACCOUNT_TYPE_WEB;

		NET_OUT_INIT_ACCOUNT outInit = new NET_OUT_INIT_ACCOUNT();

		bRet = INetSDK.InitAccount(inInit, outInit, 5000, null);
		if(bRet) {
			ToolKits.writeLog("Init WEB Account Succeed!");
		} else {
			ToolKits.writeErrorLog("Init WEB Account Failed!");
		}
	}

	// 重置WEB密码(华为专用定制)
	public void ResetWebPwd() {
		boolean bRet = false;
		NET_IN_REST_WEB_PWD inInit = new NET_IN_REST_WEB_PWD();
		String username = "admin";
		System.arraycopy(username.getBytes(), 0, inInit.szWebUser, 0, username.getBytes().length);
		String password = "admin12";
		System.arraycopy(password.getBytes(), 0, inInit.szWebPwd, 0, password.getBytes().length);

		NET_OUT_REST_WEB_PWD outInit = new NET_OUT_REST_WEB_PWD();

		bRet = INetSDK.ResetWebPwd(__LoginHandle, inInit, outInit, 15000);
		if(bRet) {
			ToolKits.writeLog("ResetWebPwd Succeed!");
		} else {
			ToolKits.writeErrorLog("ResetWebPwd Failed!");
		}
	}

	// 查询设备的存储模块信息列表
	public void QueryStorageInfo() {
		NET_IN_STORAGE_DEV_INFOS inStoregeDevInfos = new NET_IN_STORAGE_DEV_INFOS();
		inStoregeDevInfos.emVolumeType = NET_VOLUME_TYPE.VOLUME_TYPE_ALL;
		NET_OUT_STORAGE_DEV_INFOS mOutStorageDevInfo = new NET_OUT_STORAGE_DEV_INFOS();
		boolean nRet = INetSDK.QueryDevInfo(__LoginHandle, FinalVar.NET_QUERY_DEV_STORAGE_INFOS, inStoregeDevInfos, mOutStorageDevInfo, null, 3000);
		for (int i = 0; i < mOutStorageDevInfo.nDevInfosNum; i++) {

			long total = new Long(mOutStorageDevInfo.stuStoregeDevInfos[i].nTotalSpace);
			ToolKits.writeLog("total space " + mOutStorageDevInfo.stuStoregeDevInfos[i].nTotalSpace + "double " + total/1024.0/1024.0);
			ToolKits.writeLog("free space " + mOutStorageDevInfo.stuStoregeDevInfos[i].nFreeSpace);
		}
	}

	// 警戒线规则配置
	public void CrosslineRuleConfig() {
		boolean bRet = false;
		String szCommand = FinalVar.CFG_CMD_ANALYSERULE;
		CFG_ANALYSERULES_INFO analyserules = new CFG_ANALYSERULES_INFO(10);

		// 获取
		bRet = ToolKits.GetDevConfig(szCommand, analyserules, __LoginHandle, 0, 4 * 1024 * 1024);
		if(bRet) {
			ToolKits.writeLog("GetNewDevConfig Succeed!" + "\n" +
					"事件规则个数：" + analyserules.nRuleCount);

			for (int i = 0; i < analyserules.nRuleCount; i++) {
				ToolKits.writeLog("智能事件类型: " + analyserules.pRuleBuf[i].dwRuleType);
				ToolKits.writeLog("事规则编号：" + analyserules.pRuleBuf[i].stuRuleCommInfo.bRuleId);
				ToolKits.writeLog("规则所属的场景：" + analyserules.pRuleBuf[i].stuRuleCommInfo.emClassType);

				if (analyserules.pRuleBuf[i].dwRuleType == FinalVar.EVENT_IVS_CROSSLINEDETECTION) {

					CFG_CROSSLINE_INFO msg = (CFG_CROSSLINE_INFO) analyserules.pIvsRuleBuf[i];

					ToolKits.writeLog("规则名称：" + new String(msg.szRuleName).trim() + "\n" +
							"规则使能: " + msg.bRuleEnable + "\n");

					ToolKits.writeLog("跟踪使能:" + msg.bTrackEnable);

					if (msg.nDirection == 0) {
						ToolKits.writeLog("检测方向:由左至右");
					} else if (msg.nDirection == 1) {
						ToolKits.writeLog("检测方向:由右至左");
					} else if (msg.nDirection == 2) {
						ToolKits.writeLog("检测方向:由左至右 和 由右至左 都可以");
					}

					ToolKits.writeLog("警戒线坐标(规则框)：");
					for (int j = 0; j < msg.nDetectLinePoint; j++) {
						ToolKits.writeLog("X:" + msg.stuDetectLine[j].nX);
						ToolKits.writeLog("Y:" + msg.stuDetectLine[j].nY + "\n");
					}

					if (msg.bSizeFileter) {
						if (msg.stuSizeFileter.bFilterMinSizeEnable) {
							ToolKits.writeLog("物体最小尺寸(宽/高)：" + (int) msg.stuSizeFileter.stuFilterMinSize.nWidth + "/" +
									(int) msg.stuSizeFileter.stuFilterMinSize.nHeight);
						}

						if (msg.stuSizeFileter.bFilterMaxSizeEnable)
							ToolKits.writeLog("物体最大尺寸(宽/高)：" + (int) msg.stuSizeFileter.stuFilterMaxSize.nWidth + "/" +
									(int) msg.stuSizeFileter.stuFilterMaxSize.nHeight);

						if(msg.stuSizeFileter.abMinAreaSize) {
							ToolKits.writeLog("最小面积矩形框尺寸(宽/高)：" + (int) msg.stuSizeFileter.stuMinAreaSize.nWidth + "/" +
									(int) msg.stuSizeFileter.stuMinAreaSize.nHeight);
						}

						if(msg.stuSizeFileter.abMaxAreaSize) {
							ToolKits.writeLog("最大面积矩形框尺寸(宽/高)：" + (int) msg.stuSizeFileter.stuMaxAreaSize.nWidth + "/" +
									(int) msg.stuSizeFileter.stuMaxAreaSize.nHeight);
						}

						if(msg.stuSizeFileter.abMinRatioSize) {
							ToolKits.writeLog("最小宽高比矩形框尺寸(宽/高)：" + (int) msg.stuSizeFileter.stuMinRatioSize.nWidth + "/" +
									(int) msg.stuSizeFileter.stuMinRatioSize.nHeight);
						}

						if(msg.stuSizeFileter.abMaxRatioSize) {
							ToolKits.writeLog("最大宽高比矩形框尺寸(宽/高)：" + (int) msg.stuSizeFileter.stuMaxRatioSize.nWidth + "/" +
									(int) msg.stuSizeFileter.stuMaxRatioSize.nHeight);
						}
					}

					// 配置在此处

				}
			}

			// 配置
			boolean bRet2 = ToolKits.SetDevConfig(szCommand, analyserules, __LoginHandle, 0, 4 * 1024 * 1024);
			if (bRet2) {
				ToolKits.writeLog("setNewDevConfig Succeed!");
			} else {
				ToolKits.writeErrorLog("setNewDevConfig Failed!" );
			}
		} else {
			ToolKits.writeErrorLog("GetNewDevConfig Failed!" );
		}
	}

	// 警戒区规则配置
	public void CrossRegionRuleConfig() {
		boolean bRet = false;
		String szCommand = FinalVar.CFG_CMD_ANALYSERULE;
		CFG_ANALYSERULES_INFO analyserules = new CFG_ANALYSERULES_INFO(10);

		// 获取
		bRet = ToolKits.GetDevConfig(szCommand, analyserules, __LoginHandle, 0, 4 * 1024 * 1024);
		if(bRet) {
			ToolKits.writeLog("GetNewDevConfig Succeed!" + "\n" +
					"事件规则个数：" + analyserules.nRuleCount);

			for (int i = 0; i < analyserules.nRuleCount; i++) {
				ToolKits.writeLog("智能事件类型: " + analyserules.pRuleBuf[i].dwRuleType);
				ToolKits.writeLog("事规则编号：" + analyserules.pRuleBuf[i].stuRuleCommInfo.bRuleId);
				ToolKits.writeLog("规则所属的场景：" + analyserules.pRuleBuf[i].stuRuleCommInfo.emClassType);

				if (analyserules.pRuleBuf[i].dwRuleType == FinalVar.EVENT_IVS_CROSSREGIONDETECTION) {

					CFG_CROSSREGION_INFO msg = (CFG_CROSSREGION_INFO) analyserules.pIvsRuleBuf[i];

					ToolKits.writeLog("msg :" + msg);
					ToolKits.writeLog("pIvsRuleBuf :" + analyserules.pIvsRuleBuf[i]);

					ToolKits.writeLog("规则名称：" + new String(msg.szRuleName).trim() + "\n" +
							"规则使能: " + msg.bRuleEnable + "\n");

					ToolKits.writeLog("跟踪使能:" + msg.bTrackEnable);

					if (msg.nDirection == 0) {
						ToolKits.writeLog("检测方向:进入");
					} else if (msg.nDirection == 1) {
						ToolKits.writeLog("检测方向:离开");
					} else if (msg.nDirection == 2) {
						ToolKits.writeLog("检测方向:进入 和 离开 都可以");
					}

					for(int j = 0; j < msg.nActionType; j++) {
						if(msg.bActionType[i] == 0) {
							ToolKits.writeLog("检测动作 ： 出现");
						} else if(msg.bActionType[i] == 1) {
							ToolKits.writeLog("检测动作 ： 消失");
						} else if(msg.bActionType[i] == 2) {
							ToolKits.writeLog("检测动作 ： 在区域内");
						} else if(msg.bActionType[i] == 3) {
							ToolKits.writeLog("检测动作 ： 穿越区域");
						}
					}
					ToolKits.writeLog("检测动作列表:" + new String(msg.bActionType).trim());

					ToolKits.writeLog("警戒区坐标(规则框)：");
					for (int j = 0; j < msg.nDetectRegionPoint; j++) {
						ToolKits.writeLog("X:" + msg.stuDetectRegion[j].nX);
						ToolKits.writeLog("Y:" + msg.stuDetectRegion[j].nY + "\n");
					}

					if (msg.bSizeFileter) {
						if (msg.stuSizeFileter.bFilterMinSizeEnable) {
							ToolKits.writeLog("物体最小尺寸(宽/高)：" + (int) msg.stuSizeFileter.stuFilterMinSize.nWidth + "/" +
									(int) msg.stuSizeFileter.stuFilterMinSize.nHeight);
						}

						if (msg.stuSizeFileter.bFilterMaxSizeEnable)
							ToolKits.writeLog("物体最大尺寸(宽/高)：" + (int) msg.stuSizeFileter.stuFilterMaxSize.nWidth + "/" +
									(int) msg.stuSizeFileter.stuFilterMaxSize.nHeight);

						if(msg.stuSizeFileter.abMinAreaSize) {
							ToolKits.writeLog("最小面积矩形框尺寸(宽/高)：" + (int) msg.stuSizeFileter.stuMinAreaSize.nWidth + "/" +
									(int) msg.stuSizeFileter.stuMinAreaSize.nHeight);
						}

						if(msg.stuSizeFileter.abMaxAreaSize) {
							ToolKits.writeLog("最大面积矩形框尺寸(宽/高)：" + (int) msg.stuSizeFileter.stuMaxAreaSize.nWidth + "/" +
									(int) msg.stuSizeFileter.stuMaxAreaSize.nHeight);
						}

						if(msg.stuSizeFileter.abMinRatioSize) {
							ToolKits.writeLog("最小宽高比矩形框尺寸(宽/高)：" + (int) msg.stuSizeFileter.stuMinRatioSize.nWidth + "/" +
									(int) msg.stuSizeFileter.stuMinRatioSize.nHeight);
						}

						if(msg.stuSizeFileter.abMaxRatioSize) {
							ToolKits.writeLog("最大宽高比矩形框尺寸(宽/高)：" + (int) msg.stuSizeFileter.stuMaxRatioSize.nWidth + "/" +
									(int) msg.stuSizeFileter.stuMaxRatioSize.nHeight);
						}
					}

					// 配置在此处

				}
			}

			// 配置
			boolean bRet2 = ToolKits.SetDevConfig(szCommand, analyserules, __LoginHandle, 0, 4 * 1024 * 1024);
			if (bRet2) {
				ToolKits.writeLog("setNewDevConfig Succeed!");
			} else {
				ToolKits.writeErrorLog("setNewDevConfig Failed!" );
			}
		} else {
			ToolKits.writeErrorLog("GetNewDevConfig Failed!" );
		}
	}

	// 音视频加密
	public void AudioAndVideoEncrypt() {
		boolean bRet = false;

		// 订阅VK
		NET_IN_ATTACH_VK stAttachIn = new NET_IN_ATTACH_VK();
		stAttachIn.nChannelID = 0;
		stAttachIn.cbAttachVK = new TestfAttachVKCallBack();

		NET_OUT_ATTACH_VK stAttachOut = new NET_OUT_ATTACH_VK();

		long lAttachHandle = INetSDK.AttachVK(__LoginHandle, stAttachIn, stAttachOut, 5000);
		if (lAttachHandle != 0) {
			ToolKits.writeLog("AttachVK Succeed!");
		} else {
			ToolKits.writeErrorLog("AttachVK Failed!");
		}

		{	// 获取VK
			NET_IN_GET_VKINFO stIn = new NET_IN_GET_VKINFO();
			stIn.nChannelID = 0;

			NET_OUT_GET_VKINFO stOut = new NET_OUT_GET_VKINFO();

			bRet = INetSDK.GetVK(__LoginHandle, stIn, stOut, 5000);
			if (bRet) {
				ToolKits.writeLog("GetVK Succeed!");
			} else {
				ToolKits.writeErrorLog("GetVK Failed!");
			}
		}

		{	// 更新VK
			NET_IN_UPDATE_VKINFO stIn = new NET_IN_UPDATE_VKINFO();
			stIn.nChannelID = 0;
			NET_OUT_UPDATE_VKINFO stOut = new NET_OUT_UPDATE_VKINFO();
			bRet = INetSDK.UpdateVK(__LoginHandle, stIn, stOut, 5000);
			if (bRet) {
				ToolKits.writeLog("UpdateVK Succeed!");
			} else {
				ToolKits.writeErrorLog("UpdateVK Failed!");
			}
		}

		{	// 获取媒体文件加密能力集
			NET_IN_GET_MEDIA_ENCRYPT_CAPS stIn = new NET_IN_GET_MEDIA_ENCRYPT_CAPS();
			stIn.nChannelOffset = 0;
			stIn.nChannelCount = 2;
			NET_OUT_GET_MEDIA_ENCRYPT_CAPS stOut = new NET_OUT_GET_MEDIA_ENCRYPT_CAPS(2);

			bRet = INetSDK.GetMediaEncryptCaps(__LoginHandle, stIn, stOut, 5000);
			if (bRet) {
				ToolKits.writeLog("GetMediaEncryptCaps Succeed!");
				for (int i = 0; i < stOut.nRetCapsInfoCount; ++i)
				{
					ToolKits.writeLog("是否支持码流加密:" + (stOut.pstuCapsInfo[i].bSupport? "支持":"不支持"));
				}
			} else {
				ToolKits.writeErrorLog("GetMediaEncryptCaps Failed!");
			}
		}

		{    // 获取录像加密密码
			NET_IN_GET_RECORD_FILE_PASSWORD stIn = new NET_IN_GET_RECORD_FILE_PASSWORD();
			String fileName = "password.txt";
			System.arraycopy(fileName.getBytes(), 0, stIn.szFileName, 0, fileName.getBytes().length);

			NET_OUT_GET_RECORD_FILE_PASSWORD stOut = new NET_OUT_GET_RECORD_FILE_PASSWORD();

			bRet = INetSDK.GetRecordFilePassword(__LoginHandle, stIn, stOut, 5000);
			if (bRet) {
				ToolKits.writeLog("GetRecordFilePassword Succeed!");
				ToolKits.writeLog("password:" + new String(stOut.szPassword));
			} else {
				ToolKits.writeErrorLog("GetRecordFilePassword Failed!");
			}
		}

		try {  // 取消订阅VK
			if (lAttachHandle != 0) {
				Thread.sleep(10000);
				INetSDK.DetachVK(lAttachHandle, 5000);
				ToolKits.writeLog("DetachVK Devices");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// 获取所有有效显示源
	public void MatrixGetCameras() {
		// 入参
		SDK_IN_MATRIX_GET_CAMERAS stIn = new SDK_IN_MATRIX_GET_CAMERAS();

		// 出参
		SDK_OUT_MATRIX_GET_CAMERAS stOut = new SDK_OUT_MATRIX_GET_CAMERAS(32, 64);

		if(INetSDK.MatrixGetCameras(__LoginHandle, stIn, stOut, 4000)) {
			ToolKits.writeLog("nRetCameraCount:" + stOut.nRetCameraCount);

			for(int i = 0; i < stOut.nRetCameraCount; i++) {
				ToolKits.writeLog("nChannelID:" + stOut.pstuCameras[i].nChannelID);
				ToolKits.writeLog("emChannelType:" + stOut.pstuCameras[i].emChannelType);
				if(stOut.pstuCameras[i].emChannelType == 0) {
					ToolKits.writeLog("该通道没有连接设备");
				} else {
					ToolKits.writeLog("该通道有连接设备");
				}

				ToolKits.writeLog("nRetVideoInputCount:" + stOut.pstuCameras[i].stuRemoteDevice.nRetVideoInputCount);
				for(int j = 0; j < stOut.pstuCameras[i].stuRemoteDevice.nRetVideoInputCount; j++) {
					for(int k = 0; k < stOut.pstuCameras[i].stuRemoteDevice.pstuVideoInputs[j].nOptionalMainUrlCount; k++) {
						ToolKits.writeLog("szOptionalMainUrls:" + new String(stOut.pstuCameras[i].stuRemoteDevice.pstuVideoInputs[j].szOptionalMainUrls[k]).trim());
					}
					for(int k = 0; k < stOut.pstuCameras[i].stuRemoteDevice.pstuVideoInputs[j].nOptionalExtraUrlCount; k++) {
						ToolKits.writeLog("szOptionalExtraUrls:" + new String(stOut.pstuCameras[i].stuRemoteDevice.pstuVideoInputs[j].szOptionalExtraUrls[k]).trim());
					}
				}
			}
		} else {
			ToolKits.writeErrorLog("MatrixGetCameras Failed.");
		}
	}

	public void getRemoteDevice() {
		// nChanNum为通道号
		AV_CFG_RemoteDevice[] msg = new AV_CFG_RemoteDevice[NetSDKApplication.getInstance().getDeviceInfo().nChanNum];
		for(int i = 0; i < NetSDKApplication.getInstance().getDeviceInfo().nChanNum; i++) {
			msg[i] = new AV_CFG_RemoteDevice(64);
		}
		ToolKits.writeLog("nChanNum:" + NetSDKApplication.getInstance().getDeviceInfo().nChanNum);

		for(int i = 0; i < NetSDKApplication.getInstance().getDeviceInfo().nChanNum; i++) {
			int channelID = i;
			if (ToolKits.GetDevConfig(FinalVar.CFG_CMD_REMOTEDEVICE, msg[i],
									  __LoginHandle, channelID, 1024 * 1024)) {
				ToolKits.writeLog("通道" + i + "的信息：");
				ToolKits.writeLog("bEnable:" + msg[i].bEnable);
				ToolKits.writeLog("szIP:" + new String(msg[i].szIP).trim());
				ToolKits.writeLog("szSerial:" + new String(msg[i].szSerial).trim());

			} else {
				ToolKits.showMessage(this, "Get Config Failed");
			}
		}
	}
}
