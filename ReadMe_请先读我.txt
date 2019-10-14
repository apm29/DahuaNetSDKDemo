NetSDK Android 开发包说明

1). 依赖库说明
  - 其中P2P登录功能需要依赖于 P2P 库.
  - 涉及播放（预览和回放）功能依赖于 PlaySDK 库.
  - Wifi配置，设备连接Wifi，把设备配置到网络上需要依赖于 SmartConfig 库.

  - jar包以及库的路径： NetSDK_Chn_Android\DemoSource\AndroidDemo\app\libs
  - NetSDK的jar包：     INetSDK.jar      
  - NetSDK库：	        libconfigsdk.so  libjninetsdk.so  libnetsdk.so

  - PlaySDK的jar包：    IPlaySDK.jar 
  - PlaySDK库：         libplay.so   libjniplay.so   libgnustl_shared.so   libhwdec.so

  - P2P的jar包：        libToUProxy.jar  
  - P2P库：             libToUProxy.so
			 
  - SmartConfig的jar包：SmartConfig.jar
  - SmartConfig库：     libjnismartconfig.so

  - 声波配对的jar包     audiopairsdk.jar
  - 声波配对库          libAudioPairSDK.so
			 
			 
    
2). 开发包目录说明
  - Demo目录 
    包含可安装的apk文件及demo功能快照
    
  - DemoSource目录
    包含AndroidDemo的示例源码
    
  - ProgrammingManual目录
    NetSDK库的接口说明文档
    
3). 注意事项
  - 需要额外配置模块的build.gradle文件，用于加载so文件。（gradle 版本大于0.5）
    
    在app的build.gradle的android节点下设置
    android {
        ...
        sourceSets {
            main {
                jniLibs.srcDirs = ['libs']
            }
        }
    } 

	
///////////////////////////////////////////////////////////////////  
【设备搜索】
1.  功能概要：
	主要功能有：组播和广播搜索(同一个网络搜索，不能跨网段搜索)

2.  Demo中涉及到的接口如下：
    1） 开始搜索接口：INetSDK.StartSearchDevices
			回调函数 CB_fSearchDevicesCB
	2） 停止搜索接口：INetSDK.StopSearchDevices			

3.  注意事项:
	无

4.  相关接口：
	无
	
	
///////////////////////////////////////////////////////////////////  
【设备初始化】
1.  功能概要：
	主要功能有：组播初始化和单播初始化			

2.  Demo中涉及到的接口如下：
    1） 开始搜索接口：INetSDK.StartSearchDevices
			回调函数 CB_fSearchDevicesCB
	    停止搜索接口：INetSDK.StopSearchDevices		
    2） 组播初始化接口：INetSDK.InitDevAccount
	3） 单播初始化接口：INetSDK.InitDevAccountByIP		

3.  注意事项:
    [1]初始化之前，要先确定设备已经连接网络
    [2]在设备初始化之前，要先根据设备搜索判断设备是否支持初始化。	[3]有些设备组播初始化成功率高，有些设备单播初始化成功率高，所以一般情况下，俩个接口一起调用，有一个接口成功，则成功。

4.  相关接口：
	无
	

///////////////////////////////////////////////////////////////////  
【智能配置】
1.  功能概要：
	主要功能有：WIFI配对和声波配对，主要是将设备添加到路由的网络上	

2.  Demo中涉及到的接口如下：
    1） 开始搜索接口：INetSDK.StartSearchDevices
			回调函数 CB_fSearchDevicesCB
	    停止搜索接口：INetSDK.StopSearchDevices		
    2） 开始WIFI配置接口：ISmartConfig.StartSearchIPCWifi
		停止WIFI配置接口：ISmartConfig.StopSearchIPCWifi
	3） 开始声波配对接口：playAudioData
        停止声波配对接口：stopAudioData

3.  注意事项:
    [1]在配置之前，手机压连接路由的WIFI(需要将设备添加到哪个WIFI，就连哪个)
	[2]WIFI配对和声波配对，调用开始接口后，会不停的发送信息进行配置，具体有没有配置成功，是通过设备搜索判断的。

4.  相关接口：
	无
	
	
///////////////////////////////////////////////////////////////////  
【AP配置】
1.  功能概要：
	主要功能有：跟智能配置的目的一样，将设备添加到路由的网络上，只是实现方式不一样。

	功能步骤：1）将设备的热点打开，连接设备的热点
			  2）通过搜索判断设备有没有初始化。 
			     如果已初始化，直接登录设备；
			     如果没有初始化，先对设备初始化，初始化成功后，登录设备。
			  3）在2的基础上登录设备（第四点和第五点需要登录设备）
			  4）查询WLAN列表
			  5）选择需要配置的WLAN，进行AP配置。
			  6）如果判断设备有没有添加成功，可以切换要配置的WLAN，通过设备搜索检测。

2.  Demo中涉及到的接口如下：
    1） 开始搜索接口：INetSDK.StartSearchDevices
			回调函数 CB_fSearchDevicesCB
	    停止搜索接口：INetSDK.StopSearchDevices		
    2） 组播初始化接口：INetSDK.InitDevAccount
	3） 单播初始化接口：INetSDK.InitDevAccountByIP
    4） 登录接口：INetSDK.LoginEx2
        登出接口：INetSDK.Logout
    5） 获取WLAN列表接口：INetSDK.GetDevConfig   对应命令：FinalVar.SDK_DEV_WLAN_DEVICE_CFG_EX
	6） AP获取接口：INetSDK.GetNewDevConfig   INetSDK.ParseData   对应命令：FinalVar.CFG_CMD_WLAN 
	    AP配置接口：INetSDK.PacketData  INetSDK.SetNewDevConfig   对应命令：FinalVar.CFG_CMD_WLAN 

3.  注意事项:
	[1]软AP配置连接的无线是设备的热点
	[2]软AP的功能，是需要登录设备后，配置的
    [3]获取WLAN列表，可以得到认证模式和加密模式，这两个参数 根据 Encryption.java 映射得到加密模式，用于AP配置
	[4]AP配置完成，不管配置接口有没有返回，并不能表明AP配置成功，需要连接配置的无线，通过设备搜索判断。
4.  相关接口：
	无
	
	
///////////////////////////////////////////////////////////////////  
【动检配置】
1.  功能概要：
	主要功能有：动检配置，主要用于配置动检时间和动检区域等。

2.  Demo中涉及到的接口如下：
	1） 获取动检接口：INetSDK.GetNewDevConfig   INetSDK.ParseData   对应命令：FinalVar.CFG_CMD_MOTIONDETECT 
	    设置动检接口：INetSDK.PacketData  INetSDK.SetNewDevConfig   对应命令：FinalVar.CFG_CMD_MOTIONDETECT 

3.  注意事项:
	[1]动检区域是个数组，每个数组下标对应一个区域，WEB上可以配置四个互不相关的区域，本Demo只做了下标为0的动检区域配置。其他几个的配置方法类型。
4.  相关接口：
	无


///////////////////////////////////////////////////////////////////  
【实时预览】
1.  功能概要：
	主要功能有：实时预览

2.  Demo中涉及到的接口如下：
	1） 开始预览接口：
				    IPlaySDK.InitSurface
					INetSDK.RealPlayEx
					IPlaySDK.PLAYOpenStream
					IPlaySDK.PLAYPlay
					PLAYPlaySoundShare
					INetSDK.SetRealDataCallBackEx
					IPlaySDK.PLAYInputData
					
	2） 停止预览接口：
					IPlaySDK.PLAYStop
					IPlaySDK.PLAYStopSoundShare
					IPlaySDK.PLAYCloseStream
					INetSDK.StopRealPlayEx
					IPlaySDK.PLAYRefreshPlay


3.  注意事项:
	实时预览分主码流和辅码流，并且跟通道相关。
4.  相关接口：
	无
	
///////////////////////////////////////////////////////////////////  
【云台控制】
1.  功能概要：
	主要功能有：八个方向的云台控制
				变倍+/-
				变焦+/-
				光圈+/-

2.  Demo中涉及到的接口如下：
	1） 八个方向：
				[1]向上：INetSDK.SDKPTZControl   
						 对应命令 SDK_PTZ_ControlType.SDK_PTZ_UP_CONTROL       
						 接口里的最后一个参数表示运行或停止
				[2]向下：INetSDK.SDKPTZControl   
						 对应命令 SDK_PTZ_ControlType.SDK_PTZ_DOWN_CONTROL     
						 接口里的最后一个参数表示运行或停止
				[3]向左：INetSDK.SDKPTZControl   
						 对应命令 SDK_PTZ_ControlType.SDK_PTZ_LEFT_CONTROL     
						 接口里的最后一个参数表示运行或停止
				[4]向右：INetSDK.SDKPTZControl   
						 对应命令 SDK_PTZ_ControlType.SDK_PTZ_RIGHT_CONTROL    
						 接口里的最后一个参数表示运行或停止
				[5]左上：INetSDK.SDKPTZControl   
						 对应命令 SDK_EXTPTZ_ControlType.SDK_EXTPTZ_LEFTTOP    
						 接口里的最后一个参数表示运行或停止
				[6]左下：INetSDK.SDKPTZControl   
						 对应命令 SDK_EXTPTZ_ControlType.SDK_EXTPTZ_LEFTDOWN   
						 接口里的最后一个参数表示运行或停止
				[7]右上：INetSDK.SDKPTZControl   
						 对应命令 SDK_EXTPTZ_ControlType.SDK_EXTPTZ_RIGHTTOP   
						 接口里的最后一个参数表示运行或停止
				[8]右下：INetSDK.SDKPTZControl   
						 对应命令 SDK_EXTPTZ_ControlType.SDK_EXTPTZ_RIGHTDOWN  
						 接口里的最后一个参数表示运行或停止
				
	2） 变倍接口：
				[1]变倍+：INetSDK.SDKPTZControl   
						  对应命令 SDK_PTZ_ControlType.SDK_PTZ_ZOOM_ADD_CONTROL 
						  接口里的最后一个参数表示运行或停止
			    [2]变倍-：INetSDK.SDKPTZControl   
						  对应命令 SDK_PTZ_ControlType.SDK_PTZ_ZOOM_DEC_CONTROL 
						  接口里的最后一个参数表示运行或停止
	2） 变焦接口：
				[1]变焦+：INetSDK.SDKPTZControl   
						  对应命令 SDK_PTZ_ControlType.SDK_PTZ_FOCUS_DEC_CONTROL 
						  接口里的最后一个参数表示运行或停止
			    [2]变焦-：INetSDK.SDKPTZControl   
						  对应命令 SDK_PTZ_ControlType.SDK_PTZ_FOCUS_ADD_CONTROL 
						  接口里的最后一个参数表示运行或停止
	2） 光圈接口：
				[1]光圈+：INetSDK.SDKPTZControl   
						  对应命令 SDK_PTZ_ControlType.SDK_PTZ_APERTURE_ADD_CONTROL 
						  接口里的最后一个参数表示运行或停止
			    [2]光圈-：INetSDK.SDKPTZControl   
						  对应命令 SDK_PTZ_ControlType.SDK_PTZ_APERTURE_DEC_CONTROL 
						  接口里的最后一个参数表示运行或停止

3.  注意事项:
	变焦里的命令，SDK_PTZ_FOCUS_DEC_CONTROL 对应变焦+
				  SDK_PTZ_FOCUS_ADD_CONTROL 对应变焦-
4.  相关接口：
	无
	
	
///////////////////////////////////////////////////////////////////  
【OSD配置】
1.  功能概要：
	主要功能有：时间标题(时间和星期是否叠加显示)和通道标题(通道名称是否叠加显示以及通道名称修改)

2.  Demo中涉及到的接口如下：
	1） 时间标题获取接口：INetSDK.GetConfig   对应命令：NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_TIMETITLE
	    时间标题设置接口：INetSDK.SetConfig   对应命令：NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_TIMETITLE
	2） 通道标题获取接口：INetSDK.GetConfig   对应命令：NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_CHANNELTITLE
	    通道标题设置接口：INetSDK.SetConfig   对应命令：NET_EM_CFG_OPERATE_TYPE.NET_EM_CFG_CHANNELTITLE 
	3） 通道名称获取接口：INetSDK.GetNewDevConfig   INetSDK.ParseData   对应命令：FinalVar.CFG_CMD_CHANNELTITLE 
	    通道名称设置接口：INetSDK.PacketData  INetSDK.SetNewDevConfig   对应命令：FinalVar.CFG_CMD_CHANNELTITLE 

3.  注意事项:
	无
4.  相关接口：
	无
	
	
///////////////////////////////////////////////////////////////////
【录像回放、下载录像、重要录像加解锁功能】
1.  功能概要：
	主要功能有：按时间回放和下载录像、回放控制、回放码流本地抓图、重要录像加解锁		   

2.  Demo中涉及到的接口如下：
	1） 录像回放：
			1） 获取空闲通道号：IPlaySDK.PLAYGetFreePort
			2） 设置设备模式：INetSDK.SetDeviceMode
					设置录像码流类型对应命令：EM_USEDEV_MODE.SDK_RECORD_STREAM_TYPE
					设置录像文件类型对应命令：EM_USEDEV_MODE.SDK_RECORD_TYPE
			3） 回放初始化：调用PlaySDK接口
					1） 打开流播放：IPlaySDK.PLAYOpenStream
					2） 设置流播放模式：IPlaySDK.PLAYSetStreamOpenMode
					3） 开始播放：IPlaySDK.PLAYPlay
					4） 以共享方式播放声音：IPlaySDK.PLAYPlaySoundShare
			4） 对设备发来的码流数据解码：IPlaySDK.PLAYInputData
			5） 获取编码中时间信息：IPlaySDK.PLAYQueryInfo 枚举值Constants.PLAY_CMD_GetTime	
			6） 按时间进行录像回放：INetSDK.PlayBackByTimeEx
					实现INetSDK.CB_fDataCallBack处理回放码流，调用IPlaySDK.PLAYInputData解码播放
					实现INetSDK.CB_fDownLoadPosCallBack处理回放进度，调用IPlaySDK.PLAYQueryInfo更新时间
			7） 设置文件当前播放时间：IPlaySDK.PLAYSetPlayedTimeEx
			8） 清空指定缓冲区的剩余数据：IPlaySDK.PLAYResetBuffer
					清空视频源缓冲和解码后视频数据缓冲
			9） 停止回放：
					1） 关闭播放：IPlaySDK.PLAYStop
					2） 关闭流播放：IPlaySDK.PLAYCloseStream	
					3） 关闭声音(共享方式)：IPlaySDK.PLAYStopSoundShare
					4） 停止回放：INetSDK.StopPlayBack
	2） 回放控制：
			1） 播放/暂停：IPlaySDK.PLAYPause + INetSDK.PausePlayBack
			2） 快放：IPlaySDK.PLAYFast + INetSDK.FastPlayBack
			3） 慢放：IPlaySDK.PLAYSlow + INetSDK.SlowPlayBack
			4） 常速：IPlaySDK.PLAYPlay + INetSDK.NormalPlayBack
	3） 回放码流本地抓图：IPlaySDK.PLAYCatchPicEx	
	4） 下载录像：
			1） 设置录像码流类型：INetSDK.SetDeviceMode，对应命令：EM_USEDEV_MODE.SDK_RECORD_STREAM_TYPE
			2） 按时间下载录像：INetSDK.DownloadByTimeEx
					实现INetSDK.CB_fDownLoadPosCallBack处理下载进度
			3） 停止录像下载：INetSDK.StopDownload
	5） 重要录像加解锁：
			1） 查询重要录像： 对应类型EM_RECORD_SNAP_FLAG_TYPE.FLAG_TYPE_MARKED
					1） 按查询条件查询文件：INetSDK.FindFileEx
					2） 查找文件：INetSDK.FindNextFileEx
					3） 结束录像文件查找：INetSDK.FindCloseEx
			2） 重要录像按时间段加锁：INetSDK.SetMarkFileByTime
			3） 重要录像按文件解锁：INetSDK.SetMarkFile
 
3. 注意事项：
   1）下载录像格式为dav、保存图片格式为JPEG

4. 相关接口：
   无


///////////////////////////////////////////////////////////////////   
【语音对讲功能】
1.  功能概要：
	主要功能有：直连对讲、转发模式对讲

2.  Demo中涉及到的接口如下：
	1） 设置设备对讲模式：INetSDK.SetDeviceMode
			设置语音对讲编码格式对应命令：EM_USEDEV_MODE.SDK_TALK_ENCODE_TYPE
			设置对讲是否为转发模式对应命令：EM_USEDEV_MODE.SDK_TALK_TRANSFER_MODE
			转发模式时设置转发通道对应命令：EM_USEDEV_MODE.SDK_TALK_TALK_CHANNEL
	2） 向设备发送用户的音频数据：INetSDK.TalkSendData
	3） 对设备发来的音频数据解码：IPlaySDK.PLAYInputData	
	4） 向设备发起语音对讲请求：INetSDK.StartTalkEx
			对讲回调实现INetSDK.CB_pfAudioDataCallBack接口，对设备发来的音频数据解码
    5） 录音：调用PlaySDK接口
			1） 打开流播放：IPlaySDK.PLAYOpenStream
			2） 开始播放：IPlaySDK.PLAYPlay
			3） 以共享方式播放声音：IPlaySDK.PLAYPlaySoundShare
			4） 打开音频采集功能：IPlaySDK.PLAYOpenAudioRecord
					回调实现IPlaySDKCallBack.pCallFunction接口，发送音频到设备
    6） 结束录音：调用PlaySDK接口
			1） 关闭音频采集功能：IPlaySDK.PLAYCloseAudioRecord
			2） 关闭播放：IPlaySDK.PLAYStop
			3） 关闭声音(共享方式)：IPlaySDK.PLAYStopSoundShare
			4） 关闭流播放：IPlaySDK.PLAYCloseStream
    7） 停止语音对讲：INetSDK.StopTalkEx

3.  注意事项:
	录音端口使用99，发送音频到设备需要对音频数据加音频头后再发送
	
4.  相关接口：
	无

	
///////////////////////////////////////////////////////////////////  
【抓图功能】
1.  功能概要：
	主要功能有：远程抓图和定时抓图

2.  Demo中涉及到的接口如下：
    1） 设置抓图回调：INetSDK.SetSnapRevCallBack
			回调实现INetSDK.CB_fSnapRev接口
	2） 远程抓图、定时抓图、停止定时抓图：INetSDK.SnapPictureEx
			抓图模式：-1:表示停止抓图, 0：表示请求一帧（远程抓图）, 1：表示定时发送请求

3.  注意事项:
	无

4.  相关接口：
	无

	
///////////////////////////////////////////////////////////////////  
【可视对讲功能】
1.  功能概要：
	主要功能有：可视对讲和门禁开门

2.  Demo中涉及到的接口如下：
    1） 可视对讲：
			1） 实时预览：参考【实时预览功能】
			2） 语音对讲：
					1） 设置设备对讲模式：INetSDK.SetDeviceMode
							设置语音对讲编码格式对应命令：EM_USEDEV_MODE.SDK_TALK_ENCODE_TYPE
							设置客户端方式对讲对应命令：EM_USEDEV_MODE.SDK_TALK_CLIENT_MODE
							设置语音参数对应命令：EM_USEDEV_MODE.SDK_TALK_SPEAK_PARAM
					2） 其他参考【语音对讲功能】
	2） 门禁开门：INetSDK.ControlDevice 枚举值CtrlType.SDK_CTRL_ACCESS_OPEN
3.  注意事项:
	无

4.  相关接口：
	无	