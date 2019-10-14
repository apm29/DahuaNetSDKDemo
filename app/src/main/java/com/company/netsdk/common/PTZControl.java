package com.company.netsdk.common;

import android.view.MotionEvent;

import com.company.NetSDK.INetSDK;
import com.company.NetSDK.SDK_EXTPTZ_ControlType;
import com.company.NetSDK.SDK_PTZ_ControlType;

/**
 * Created by 32940 on 2017/6/26.
 */
public class PTZControl {
    /**
     * \if ENGLISH_LANG
     * PTZ Up
     * @param param1 vertical velocity
     * @param param2 horizontal velocity
     * \else
     * 云台向上
     * @param param1 垂直速度
     * @param param2 水平速度
     * \endif
     */
    public boolean ptzControlUp(MotionEvent event, long loginHandle, int nChn, byte param1, byte param2) {
        boolean bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_UP_CONTROL, param1, param2, (byte) 0, false);
        if(bPtzControl) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_UP_CONTROL, (byte) 0, (byte) 0 , (byte) 0, true);
        }
        return bPtzControl;
    }

    /**
     * \if ENGLISH_LANG
     * PTZ Down
     * @param param1 vertical velocity
     * @param param2 horizontal velocity
     * \else
     * 云台向下
     * @param param1 垂直速度
     * @param param2 水平速度
     * \endif
     */
    public boolean ptzControlDown(MotionEvent event, long loginHandle, int nChn, byte param1, byte param2) {
        boolean bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_DOWN_CONTROL, param1, param2, (byte) 0, false);
        if(bPtzControl) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_DOWN_CONTROL, (byte) 0, (byte) 0, (byte) 0, true);
        }
        return bPtzControl;
    }

    /**
     * \if ENGLISH_LANG
     * PTZ Left
     * @param param1 vertical velocity
     * @param param2 horizontal velocity
     * \else
     * 云台向左
     * @param param1 垂直速度
     * @param param2 水平速度
     * \endif
     */
    public boolean ptzControlLeft(MotionEvent event, long loginHandle, int nChn, byte param1, byte param2) {
        boolean bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_LEFT_CONTROL, param1, param2 ,(byte) 0, false);
        if(bPtzControl) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_LEFT_CONTROL, (byte) 0, (byte) 0,(byte) 0, true);
        }
        return bPtzControl;
    }

    /**
     * \if ENGLISH_LANG
     * PTZ Right
     * @param param1 vertical velocity
     * @param param2 horizontal velocity
     * \else
     * 云台向右
     * @param param1 垂直速度
     * @param param2 水平速度
     * \endif
     */
    public boolean ptzControlRight(MotionEvent event, long loginHandle, int nChn, byte param1, byte param2) {
        boolean bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_RIGHT_CONTROL, param1, param2, (byte) 0, false);
        if(bPtzControl) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_RIGHT_CONTROL, (byte) 0, (byte) 0, (byte) 0, true);
        }
        return bPtzControl;
    }

    /**
     * \if ENGLISH_LANG
     * PTZ LeftUp
     * @param param1 vertical velocity
     * @param param2 horizontal velocity
     * \else
     * 云台向左上
     * @param param1 垂直速度
     * @param param2 水平速度
     * \endif
     */
    public boolean ptzControlLeftUp(MotionEvent event, long loginHandle, int nChn, byte param1, byte param2) {
        boolean bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_EXTPTZ_ControlType.SDK_EXTPTZ_LEFTTOP, param1, param2, (byte) 0, false);
        if(bPtzControl) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_EXTPTZ_ControlType.SDK_EXTPTZ_LEFTTOP, (byte) 0, (byte) 0, (byte) 0, true);
        }
        return bPtzControl;
    }

    /**
     * \if ENGLISH_LANG
     * PTZ LeftDown
     * @param param1 vertical velocity
     * @param param2 horizontal velocity
     * \else
     * 云台向左下
     * @param param1 垂直速度
     * @param param2 水平速度
     * \endif
     */
    public boolean ptzControlLeftDown(MotionEvent event, long loginHandle, int nChn, byte param1, byte param2) {
        boolean bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_EXTPTZ_ControlType.SDK_EXTPTZ_LEFTDOWN, param1, param2, (byte) 0, false);
        if(bPtzControl) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_EXTPTZ_ControlType.SDK_EXTPTZ_LEFTDOWN, (byte) 0, (byte) 0, (byte) 0, true);
        }
        return bPtzControl;
    }

    /**
     * \if ENGLISH_LANG
     * PTZ RightUp
     * @param param1 vertical velocity
     * @param param2 horizontal velocity
     * \else
     * 云台向右上
     * @param param1 垂直速度
     * @param param2 水平速度
     * \endif
     */
    public boolean ptzControlRightUp(MotionEvent event, long loginHandle, int nChn, byte param1, byte param2) {
        boolean bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_EXTPTZ_ControlType.SDK_EXTPTZ_RIGHTTOP, param1, param2, (byte) 0, false);
        if(bPtzControl) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_EXTPTZ_ControlType.SDK_EXTPTZ_RIGHTTOP, (byte) 0, (byte) 0, (byte) 0, true);
        }
        return bPtzControl;
    }

    /**
     * \if ENGLISH_LANG
     * PTZ RightDown
     * @param param1 vertical velocity
     * @param param2 horizontal velocity
     * \else
     * 云台向右下
     * @param param1 垂直速度
     * @param param2 水平速度
     * \endif
     */
    public boolean ptzControlRightDown(MotionEvent event, long loginHandle, int nChn, byte param1, byte param2) {
        boolean bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_EXTPTZ_ControlType.SDK_EXTPTZ_RIGHTDOWN, param1, param2, (byte)0, false);
        if(bPtzControl) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_EXTPTZ_ControlType.SDK_EXTPTZ_RIGHTDOWN, (byte) 0, (byte) 0,(byte) 0, true);
        }
        return bPtzControl;
    }

    /**
     * \if ENGLISH_LANG
     * Zoom+
     * @param param1 velocity
     * \else
     * 变倍+
     * @param param1 速度
     * \endif
     */
    public boolean ptzControlZoomAdd(MotionEvent event, long loginHandle, int nChn, byte param1) {
        boolean bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_ZOOM_ADD_CONTROL, (byte) 0, param1, (byte) 0, false);
        if(bPtzControl) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_ZOOM_ADD_CONTROL, (byte) 0, (byte) 0, (byte) 0, true);
        }
        return bPtzControl;
    }

    /**
     * \if ENGLISH_LANG
     * Zoom-
     * @param param1 velocity
     * \else
     * 变倍-
     * @param param1 速度
     * \endif
     */
    public boolean ptzControlZoomDec(MotionEvent event, long loginHandle, int nChn, byte param1) {
        boolean bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_ZOOM_DEC_CONTROL, (byte) 0, param1, (byte) 0, false);
        if(bPtzControl) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_ZOOM_DEC_CONTROL, (byte) 0, (byte) 0, (byte) 0, true);
        }
        return bPtzControl;
    }

    /**
     * \if ENGLISH_LANG
     * Focus+
     * @param param1 velocity
     * \else
     * 变焦+
     * @param param1 速度
     * \endif
     */
    public boolean ptzControlFocusAddStart(long loginHandle, int nChn, byte param1) {
        // SDK_PTZ_FOCUS_DEC_CONTROL 对应 +
        return INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_FOCUS_DEC_CONTROL, (byte) 0, param1, (byte) 0, false);
    }

    public boolean ptzControlFocusAddStop(long loginHandle, int nChn) {
        return INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_FOCUS_DEC_CONTROL, (byte) 0, (byte) 0, (byte) 0 , true);
    }

    /**
     * \if ENGLISH_LANG
     * Focus-
     * @param param1 velocity
     * \else
     * 变焦-
     * @param param1 速度
     * \endif
     */
    public boolean ptzControlFocusDecStart(long loginHandle, int nChn, byte param1) {
        // SDK_PTZ_FOCUS_ADD_CONTROL 对应 -
        return INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_FOCUS_ADD_CONTROL, (byte) 0, param1, (byte) 0, false);
    }

    public boolean ptzControlFocusDecStop(long loginHandle, int nChn) {
        return INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_FOCUS_ADD_CONTROL, (byte)0, (byte) 0, (byte) 0, true);
    }

    /**
     * \if ENGLISH_LANG
     * Aperture+
     * @param param1 velocity
     * \else
     * 光圈+
     * @param param1 速度
     * \endif
     */
    public boolean ptzControlApertureAddStart(long loginHandle, int nChn, byte param1) {
        return INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_APERTURE_ADD_CONTROL, (byte) 0, param1, (byte) 0, false);
    }

    public boolean ptzControlApertureAddStop(long loginHandle, int nChn) {
        return INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_APERTURE_ADD_CONTROL, (byte) 0, (byte) 0, (byte) 0, true);
    }

    /**
     * \if ENGLISH_LANG
     * Aperture-
     * @param param1 velocity
     * \else
     * 光圈-
     * @param param1 速度
     * \endif
     */
    public boolean ptzControlApertureDecStart(long loginHandle, int nChn, byte param1) {
        return INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_APERTURE_DEC_CONTROL, (byte) 0, param1, (byte) 0, false);
    }

    public boolean ptzControlApertureDecStop(long loginHandle, int nChn) {
        return INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_APERTURE_DEC_CONTROL, (byte) 0, (byte) 0, (byte) 0, true);
    }

    /**
     * \if ENGLISH_LANG
     * Preset Add
     * @param param1 Preset
     * \else
     * 预置点添加
     * @param param1 预置点
     * \endif
     */
    public boolean ptzControlPresetAdd(long loginHandle, int nChn, byte param1) {
        return INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_POINT_SET_CONTROL, (byte) 0, param1, (byte) 0, false);
    }

    /**
     * \if ENGLISH_LANG
     * Preset Goto
     * @param param1 Preset
     * \else
     * 预置点跳转
     * @param param1 预置点
     * \endif
     */
    public boolean ptzControlPresetGoto(long loginHandle, int nChn, byte param1) {
        return INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_POINT_MOVE_CONTROL, (byte) 0, param1,(byte) 0, false);
    }

    /**
     * \if ENGLISH_LANG
     * Preset Delete
     * @param param1 Preset
     * \else
     * 预置点删除
     * @param param1 预置点
     * \endif
     */
    public boolean ptzControlPresetDec(long loginHandle, int nChn, byte param1) {
        return INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_POINT_DEL_CONTROL, (byte) 0, param1, (byte) 0, false);
    }

    /**
     * \if ENGLISH_LANG
     * Preset Delete
     * @param param1 horizontal degree(0~3600)
     * @param param2 vertical coordinates(0~900)
     * @param param3 zoom(1~128)
     * \else
     *  三维精确定位
     * @param param1 水平角度(0~3600)
     * @param param2 垂直坐标(0~900)
     * @param param3 变倍(1~128)
     * \endif
     */
    public boolean ptzControlExactGoto(long loginHandle, int nChn, byte param1, byte param2, byte param3) {
        return INetSDK.SDKPTZControlEx(loginHandle, nChn, SDK_EXTPTZ_ControlType.SDK_EXTPTZ_EXACTGOTO,  param1, param2, param3, false);
    }

    /**
     * \if ENGLISH_LANG
     * lamp control
     * @param param1 1 open, 0 close
     * \else
     * 灯光雨刷控制
     * @param param1 1开启‚ 0关闭
     * @return
     */
    public boolean ptzControlLamp(long loginHandle, int nChn, byte param1) {
        return INetSDK.SDKPTZControl(loginHandle, nChn, SDK_PTZ_ControlType.SDK_PTZ_LAMP_CONTROL,  param1, (byte) 0, (byte) 0, false);
    }


    public boolean ptzControl(MotionEvent event, long loginHandle, int nChn, int control, byte param1,  byte param2) {
        // 运行
        boolean bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, control, param1, param2, (byte) 0, false);
        if(bPtzControl) {

            try {
                Thread.sleep(100);  // 加这个的目的的为了移动的多一点
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 停止，此时param1, param2 必须为0
            bPtzControl = INetSDK.SDKPTZControl(loginHandle, nChn, control, (byte) 0, (byte) 0, (byte) 0, true);
        }
        return bPtzControl;
    }
}
