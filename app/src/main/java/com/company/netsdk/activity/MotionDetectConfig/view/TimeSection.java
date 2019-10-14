package com.company.netsdk.activity.MotionDetectConfig.view;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TimeSection implements Serializable{
	 public	static final int	DAYS_OF_WEEK	=		7;
	 public static final int	MAX_COUNT_OF_TS	=		6;
	
	
	 public int				nEnable;						// 时间段时能（在录像计划中表示录像类型的使能）
	 public int				nBeginHour;					// 开始时间：时
	 public int				nBeginMinute;					// 开始时间：分
	 public int				nBeginSecond;					// 开始时间：秒
	 public int				nEndHour;						// 结束时间：时
	 public int				nEndMinute;					// 结束时间：分
	 public int				nEndSecond;					// 结束时间：秒
	
	 //录像计划类型，暂时解析普通、动检、报警三种类型
	 public boolean			normalEnable		=	false;
	 public	boolean			motionEnable		=	false;
	 public	boolean			alarmEnable		=	false;

	 /**
	  * F6解析录像计划类型
	  */
	 public void serializeRecordType() {
		 int type = 0x0f & nEnable ;
//			type 0 		无
//			type 1 		定时(普通)
//			type 2 		动检
//			type 3 		普通 + 动检 
//			type 4 		报警
//			type 5 		普通 + 报警 
//			type 6 		动检 + 报警
//			type 7 		定时 + 动检 + 报警
//			type 其他	其他
		 switch (type) {
		case 0:
			normalEnable = false;
			motionEnable = false;
			alarmEnable  = false;
			break;
		case 1:
			normalEnable = true;
			motionEnable = false;
			alarmEnable  = false;
			break;
		case 2:
			normalEnable = false;
			motionEnable = true;
			alarmEnable  = false;
			break;
		case 3:
			normalEnable = true;
			motionEnable = true;
			alarmEnable  = false;
			break;
		case 4:
			normalEnable = false;
			motionEnable = false;
			alarmEnable  = true;
			break;
		case 5:
			normalEnable = true;
			motionEnable = false;
			alarmEnable  = true;
			break;
		case 6:
			normalEnable = false;
			motionEnable = true;
			alarmEnable  = true;
			break;
		case 7:
			normalEnable = true;
			motionEnable = true;
			alarmEnable  = true;
			break;

		default:
			normalEnable = false;
			motionEnable = false;
			alarmEnable  = false;
			break;
		}
	}
	
	/**
	  * 二代协议解析录像计划类型
	  */
	 public void serializeRecordTypeToBinary() {
		 
//		 Bit0-动态检测录像；
//		 Bit1-报警录像；
//		 Bit2-定时录像

		 byte bit0 = (byte) (nEnable & 0x01);
		 byte bit1 = (byte) (nEnable >> 1 & 0x01);
		 byte bit2 = (byte) (nEnable >> 2 & 0x01);
		 
		 motionEnable 	= (bit0 == 1) ? true : false;
		 alarmEnable 	= (bit1 == 1) ? true : false;
		 normalEnable 	= (bit2 == 1) ? true : false;
	}
	
	 /**
		 * 二代协议组装录像类型 
		 */
		public void deserializeRecordTypeToBinary() {
			
//			 Bit0-动态检测录像；
//			 Bit1-报警录像；
//			 Bit2-定时录像
			nEnable = motionEnable ? nEnable | 0x00000001 : nEnable & 0xfffffffe;
			nEnable = alarmEnable  ? nEnable | 0x00000002 : nEnable & 0xfffffffd;
			nEnable = normalEnable ? nEnable | 0x00000004 : nEnable & 0xfffffffb;
		} 
	 
	@Override
	 public String toString() {
		 String time = String.format("%d %02d:%02d:%02d-%02d:%02d:%02d", 
				nEnable,nBeginHour,nBeginMinute,nBeginSecond,
				nEndHour,nEndMinute,nEndSecond);
		 return time;
	 }
	
	/**
	 * 获取开始时间的日期格式
	 * @return 开始时间日期格式
	 */
	 public String getStartDate() {
		 String date = String.format("%02d:%02d",nBeginHour,nBeginMinute);
		 return date;
	 }
	 
	/**
	 * 获取结束时间的日期格式
	 * @return 结束时间日期格式
	 */
	 public String getEndDate() {
		 String date = String.format("%02d:%02d",nEndHour,nEndMinute,nEndSecond);
		 return date;
	 }
	 
}
