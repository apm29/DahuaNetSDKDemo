package com.company.netsdk.audiopair;

import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Locale;

import com.example.dhcommonlib.audiopair.AudioPairEncodeData;
import com.example.dhcommonlib.audiopair.AudioPairSDK;


import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

@SuppressLint("NewApi")
public class AudioPair {

    final boolean initStat = true;

    final float volumeFloat = (float) 1;

    private final static String PROTOCOL_FLAG = "AW";

    private final static byte PROTOCOL_VER = 0x10;

    private final static byte PROTOCOL_TYPE = 0;

    private final static byte REMARK = 0;

    private AudioPairSDK mAudioPairSDK; // sdk对象

    private byte[] mIp; // 本机ip

    private short mUdpPort = 8666; // 绑定udp端口

    private byte mIpFamily = 0; // ip家族类型 ipv4、ipv6

    DatagramSocket mUdpSocket = null; // EDP套接字

    AudioTrack mAudioTrack = null;

    int mPlayBufSize;

    int current;

    static final int in_frequency = 44100;

    static final int frequency = 44100;

    static final int channelConfiguration = AudioFormat.CHANNEL_OUT_MONO;

    static final int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;

    // BassBoost bb = null;
    // EnvironmentalReverb er = null;
    // Equalizer eq = null;
    // PresetReverb pr = null;
    // Virtualizer vz = null;

    // LoudnessEnhancer le = null;

    public AudioPair() {
        mAudioPairSDK = null;
    }

    // 初始化条件
    public Boolean init(Context mContext) {
        if (mAudioPairSDK != null) {
            return true;
        }

        // 初始化配对
        mAudioPairSDK = new AudioPairSDK();
        if (mAudioPairSDK.native_audiopair_init() != 0) {
            Log.d("audioPair", "native_audiopair_init error");
            return false;
        }

        if (mAudioPairSDK.native_audiopair_setformat(in_frequency, frequency, 2, 300) != 0) {
            Log.d("audioPair", "native_audiopair_setformat error");
            return false;
        }

        // 初始化udp套接字
        mIp = getPhoneIp();

        if (mIp == null || mIp.length <= 0) {
            Log.d("audioPair", "local ip is null");
            return false;
        }
        Log.d("audioPair", "local ip is " + mIp.toString() + " len is " + String.valueOf(mIp.length));
        // 创建udp套接字，绑定端口
        /*
         * for (int i = 0 ; i < 5; i ++) { try { mUdpSocket = new DatagramSocket (mUdpPort);
         * mUdpSocket.setSoTimeout(45*1000); break; } catch (SocketException e) { // TODO
         * Auto-generated catch block //e.printStackTrace(); mUdpPort += 10; // 如果还是未套接字创建成功，则返回 if
         * (i >= 5) { return false; } } }
         */

        // 初始化音频播放参数
        mPlayBufSize = AudioTrack.getMinBufferSize(frequency, channelConfiguration, audioEncoding);

        mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, frequency, channelConfiguration, audioEncoding,
                mPlayBufSize, AudioTrack.MODE_STREAM);

        // 初始系统音量 80%
        // try {
        // mAudioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
        // int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        //
        // // mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int)(max * volumeFloat), 0);
        // } catch (Exception e) {
        // Log.e("lc Exception", "init audio error", e);
        // }
        //
        // 关闭系统模块
        //
        // try {
        // bb = new BassBoost(Integer.MAX_VALUE, 0);
        // bb.setEnabled(initStat);
        // } catch (Exception e) {
        // // TODO: handle exception
        // Log.d("AudioPair", "BassBoost create error");
        // }
        //
        // try {
        // er = new EnvironmentalReverb(Integer.MAX_VALUE, 0);
        // er.setEnabled(initStat);
        // } catch (Exception e) {
        // // TODO: handle exception
        // Log.d("AudioPair", "EnvironmentalReverb create error");
        // }
        //
        // try {
        // eq = new Equalizer(Integer.MAX_VALUE, 0);
        // eq.setEnabled(initStat);
        // } catch (Exception e) {
        // // TODO: handle exception
        // Log.d("AudioPair", "Equalizer create error");
        // }
        // //
        // try {
        // pr = new PresetReverb(Integer.MAX_VALUE, 0);
        // pr.setEnabled(initStat);
        // } catch (Exception e) {
        // // TODO: handle exception
        // Log.d("AudioPair", "PresetReverb create error");
        // }
        //
        // try {
        // // vz = new Virtualizer(Integer.MAX_VALUE, 0);
        // // vz.setEnabled(initStat);
        // } catch (Exception e) {
        // // TODO: handle exception
        // Log.d("AudioPair", "Virtualizer create error");
        // }
        //
        // try {
        // //le =new LoudnessEnhancer(0);
        // //le.setEnabled(initStat);
        // } catch (Exception e) {
        // // TODO: handle exception
        // Log.d("AudioPair", "Virtualizer create error");
        // }

        return true;
    }

    // 反初始化
    public Boolean uninit() {
        if (mAudioPairSDK == null) {
            return false;
        }
        // try {
        // mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int)(current * volumeFloat),
        // 0);
        // } catch (Exception e) {
        // Log.e("lc Exception", "uninit init audio error", e);
        // }
        // 开启音响控制
        // if (bb != null)
        // {
        // bb.setEnabled(true);
        // }
        // if (er != null)
        // {
        // er.setEnabled(true);
        // }
        // if (eq != null)
        // {
        // eq.setEnabled(true);
        // }
        // if (pr != null)
        // {
        // pr.setEnabled(true);
        // }
        // if (vz != null)
        // {
        // vz.setEnabled(true);
        // }
        // if (le != null)
        // {
        // le.setEnabled(true);
        // }

        // 关闭udp套接字
        /*
         * if (mUdpSocket != null) { mUdpSocket.close(); mUdpSocket = null; }
         */

        // 反初始化配对库
        if (mAudioPairSDK.native_audiopair_uninit() != 0) {
            Log.d("audioPair", "native_audiopair_uninit error");
        }
        mAudioPairSDK = null;

        if (mAudioTrack != null) {
            mAudioTrack.release();
            mAudioTrack = null;
        }

        return true;
    }

    // 接收到设备发回的数据信号
    // 异步线程调用
    /*
     * public Boolean hasReceiveSign(){ if (mAudioPairSDK == null || mUdpSocket == null ||
     * mAudioTrack == null) { return false; } byte data[] = new byte[1024]; DatagramPacket packag =
     * new DatagramPacket(data , data.length); try { Log.d("audioPair", "receive packag in");
     * mUdpSocket.receive(packag); Log.d("audioPair", "receive packag out"); } catch (IOException e)
     * { // TODO Auto-generated catch block e.printStackTrace(); } // 如果读取到数据，则返回成功 if
     * (packag.getData().length > 0) { Log.d("audioPair", "receive data " +
     * packag.getData().toString()); return true; } return false; }
     */
    // 播放配对声音数据
    public int playAudioData(String ssid, String password, String security, String deviceCode) {
        if (mAudioPairSDK == null || mAudioTrack == null) {
            Log.d("audioPair", "playAudioData object null");
            return 1;
        }

        int len = 0;
        byte[] data = new byte[300];

        // 协议标示
        byte[] tempData = PROTOCOL_FLAG.getBytes();

        // 协议版本
        data[len] = PROTOCOL_VER;
        len++;

        // 协议类型
        data[len] = (byte) getWifiEncryption(security);
        len++;

        // ssid
        tempData = ssid.getBytes();
        data[len] = (byte) tempData.length;
        len++;

        System.arraycopy(tempData, 0, data, len, tempData.length);
        len += tempData.length;

        // 密码
        tempData = password.getBytes();
        data[len] = (byte) tempData.length;
        len++;

        System.arraycopy(tempData, 0, data, len, tempData.length);
        len += tempData.length;

        // 设备序列号
        tempData = deviceCode.getBytes();
        data[len] = (byte) tempData.length;
        len++;

        System.arraycopy(tempData, 0, data, len, tempData.length);
        len += tempData.length;

        // 校验和
        /*
         * int sTemp = 0; for(int i = 0; i < len; i ++) { sTemp += data[i]; } data[len] =
         * (byte)sTemp;
         */
        // crc校验
        CRCHelper m_crc = new CRCHelper();
        int crc = (int) m_crc.crc32(data, len);
        data[len++] = (byte) ((crc >> 24) & 0xff);
        data[len++] = (byte) ((crc >> 16) & 0xff);
        data[len++] = (byte) ((crc >> 8) & 0xff);
        data[len++] = (byte) (crc & 0xff);

        // 拷贝数据
        byte[] newData = new byte[len];
        System.arraycopy(data, 0, newData, 0, len);
        data = null;

        AudioPairEncodeData encodeData = mAudioPairSDK.native_audiopair_encode(newData);
        if (encodeData == null) {
            Log.d("audioPair", "native_audiopair_encode  error");
            return 1;
        }
        if (encodeData.mEncodeData == null) {
            Log.d("audioPair", "native_audiopair_encode  mEncodeData null");
            return 1;
        }

        // 播放声音
        mAudioTrack.play();
        mAudioTrack.write(encodeData.mEncodeData, 0, encodeData.mEncodeData.length);
        mAudioTrack.stop();

        return 0;
    }

    /**
     * 获取手机ip地址
     * 
     * @return
     */
    private byte[] getPhoneIp() {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            if (en == null) {
                return null;
            }
            while (en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        // mIpFamily = 1;
                        // }
                        // else
                        // {
                        mIpFamily = 0;
                        // }

                        Log.d("audioPair", inetAddress.toString());

                        // 网络字节序存储
                        return inetAddress.getAddress();
                    }
                }
            }
        } catch (Exception e) {
            Log.d("audioPair", "getPhoneIp error");
        }

        return null;
    }

    // 网络字节序转换 应该放置到工具类中
    private static byte[] shortToBytes(short n) {
        byte[] b = new byte[2];
        b[1] = (byte) (n & 0xff);
        b[0] = (byte) ((n >> 8) & 0xff);
        return b;
    }

    private int getWifiEncryption(String capabilities) {
        int Encryption = 255;
        if (capabilities == null || capabilities.length() <= 0) {
            return Encryption;
        }
        String cap = capabilities.toUpperCase(Locale.US);
        // wep加密
        if (cap.indexOf("WPA2") != -1) {// wpa 加密 优先
            if (cap.indexOf("WPA2-PSK-TKIP") != -1) {
                Encryption = 6;
            } else if (cap.indexOf("WPA2-PSK-AES") != -1) {
                Encryption = 7;
            } else if (cap.indexOf("WPA2-TKIP") != -1) {
                Encryption = 10;
            } else if (cap.indexOf("WPA2-AES") != -1) {
                Encryption = 11;
            } else if (cap.indexOf("WPA2-PSK-CCMP") != -1) {
                Encryption = 12;
            }
        } else if (cap.indexOf("WPA") != -1) {// wpa 加密
            if (cap.indexOf("WPA-PSK-TKIP") != -1) {
                Encryption = 4;
            } else if (cap.indexOf("WPA-PSK-CCMP") != -1) {
                Encryption = 5;
            } else if (cap.indexOf("WPA-TKIP") != -1) {
                Encryption = 8;
            } else if (cap.indexOf("WPA-CCMP") != -1) {
                Encryption = 9;
            }
        } else if (cap.indexOf("WEP") != -1) {
            if (cap.indexOf("WEP_Open") != -1) {
                Encryption = 2;
            } else if (cap.indexOf("WEP_Shared") != -1) {
                Encryption = 3;
            }
        } else {
            Encryption = 255;
        }
        return Encryption;
    }
}
