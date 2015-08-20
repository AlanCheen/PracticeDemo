package yifeiyuan.practice.practicedemos.info;

import android.content.Context;
import android.net.TrafficStats;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.ToolbarActivity;


/**
 *
 * 信号强度 参考 :
 * http://blog.csdn.net/zmwell/article/details/6826700
 * https://software.intel.com/zh-cn/blogs/2011/12/16/android-gsmcdma
 * http://yuemeiqing2008-163-com.iteye.com/blog/2077489
 */
public class DeviceInfoActivty extends ToolbarActivity {

    @InjectView(R.id.tv_device_info)
    TextView mTvDeviceInfo;
    @InjectView(R.id.tv_signal_info)
    TextView mTvSignalInfo;

    private long mTotalRxBytes = TrafficStats.getTotalRxBytes();

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            //不准?...

            long mrx = TrafficStats.getMobileRxBytes() / 1024; ////获取通过Mobile连接收到的字节总数，不包含WiFi
            long mtx = TrafficStats.getMobileTxBytes() / 1024; //Mobile发送的总字节数
            long trx = (long) ((TrafficStats.getTotalRxBytes() - mTotalRxBytes) * 1.00f / 1024);
            mTotalRxBytes = TrafficStats.getTotalRxBytes(); //获取总的接受字节数，包含Mobile和WiFi等
            long ttx = TrafficStats.getTotalTxBytes() / 1024; //总的发送字节数，包含Mobile和WiFi等
            long uidrx = TrafficStats.getUidRxBytes(getApplicationInfo().uid) / 1024;//获取某个网络UID的接受字节数，某一个进程的总接收量
            long uidtx = TrafficStats.getUidTxBytes(getApplicationInfo().uid) / 1024;//获取某个网络UID的发送字节数，某一个进程的总发送量
            StringBuilder sb = new StringBuilder();

            sb.append("mrx:" + mrx + "\n\r")
                    .append("mtx:" + mtx + "\n\r")
                    .append("trx:" + trx + "\n\r")
                    .append("ttx:" + ttx + "\n\r")
                    .append("uidrx:" + uidrx + "\n\r")
                    .append("uidtx:" + uidtx + "\n\r")
            ;
            mTvDeviceInfo.setText(sb.toString());
            mHandler.sendEmptyMessageDelayed(0, 1000);

            return true;
        }
    });
    private TelephonyManager mTelephonyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info_activty);

        mHandler.sendEmptyMessageDelayed(0, 0);

        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        //LISTEN_SIGNAL_STRENGTHS  Listen for changes to the network signal strengths (cellular).
        mTelephonyManager.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_SIGNAL_STRENGTHS | PhoneStateListener.LISTEN_SERVICE_STATE);

    }


    private class MyPhoneStateListener extends PhoneStateListener {
        public MyPhoneStateListener() {
            super();
        }


        /**
         * ServiceState.STATE_EMERGENCY_ONLY   仅限紧急呼叫
         * ServiceState.STATE_IN_SERVICE       信号正常
         * ServiceState.STATE_OUT_OF_SERVICE   不在服务区
         * ServiceState.STATE_POWER_OFF        断电
         */
        @Override
        public void onServiceStateChanged(ServiceState serviceState) {
            super.onServiceStateChanged(serviceState);
            Log.d(TAG, "onServiceStateChanged() called with " + "serviceState = [" + serviceState + "]");
        }

        @Override
        public void onSignalStrengthChanged(int asu) {
            super.onSignalStrengthChanged(asu);
            Log.d(TAG, "onSignalStrengthChanged() called with " + "asu = [" + asu + "]");
        }

        @Override
        public void onMessageWaitingIndicatorChanged(boolean mwi) {
            super.onMessageWaitingIndicatorChanged(mwi);
            Log.d(TAG, "onMessageWaitingIndicatorChanged() called with " + "mwi = [" + mwi + "]");
        }

        @Override
        public void onCallForwardingIndicatorChanged(boolean cfi) {
            super.onCallForwardingIndicatorChanged(cfi);
            Log.d(TAG, "onCallForwardingIndicatorChanged() called with " + "cfi = [" + cfi + "]");
        }

        @Override
        public void onCellLocationChanged(CellLocation location) {
            super.onCellLocationChanged(location);
            Log.d(TAG, "onCellLocationChanged() called with " + "location = [" + location + "]");
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            Log.d(TAG, "onCallStateChanged() called with " + "state = [" + state + "], incomingNumber = [" + incomingNumber + "]");
        }

        @Override
        public void onDataConnectionStateChanged(int state) {
            super.onDataConnectionStateChanged(state);
            Log.d(TAG, "onDataConnectionStateChanged() called with " + "state = [" + state + "]");
        }

        @Override
        public void onDataConnectionStateChanged(int state, int networkType) {
            super.onDataConnectionStateChanged(state, networkType);
            Log.d(TAG, "onDataConnectionStateChanged() called with " + "state = [" + state + "], networkType = [" + networkType + "]");
        }

        @Override
        public void onDataActivity(int direction) {
            super.onDataActivity(direction);
            Log.d(TAG, "onDataActivity() called with " + "direction = [" + direction + "]");
        }

        /**
         * signalStrength.isGsm()           是否GSM信号 2G or 3G
         * signalStrength.getCdmaDbm();     联通3G 信号强度
         * signalStrength.getCdmaEcio();    联通3G 载干比
         * signalStrength.getEvdoDbm();     电信3G 信号强度
         * signalStrength.getEvdoEcio();    电信3G 载干比
         * signalStrength.getEvdoSnr();     电信3G 信噪比
         * signalStrength.getGsmSignalStrength();  2G 信号强度
         * signalStrength.getGsmBitErrorRate();    2G 误码率
         * 载干比 ，它是指空中模拟电波中的信号与噪声的比值
         */
        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            Log.d(TAG, "onSignalStrengthsChanged() called with " + "signalStrength :"+signalStrength+";\n gsmSignalStrength = [" + signalStrength.getGsmSignalStrength() + "]");

//            if (mTelephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
//                mTvSignalInfo.setText("GSM Strength" + signalStrength.getGsmSignalStrength());
//            } else if (mTelephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
//                mTvSignalInfo.setText("CDMA Strength" + signalStrength.getCdmaDbm() + " dBm");
//            } else {
//                mTvSignalInfo.setText("Unknown PhoneType: " + mTelephonyManager.getPhoneType());
//            }

            mTvSignalInfo.setText("IsGsm : " + signalStrength.isGsm() +
                    "\nCDMA Dbm : " + signalStrength.getCdmaDbm() + " Dbm" +
                    "\nCDMA Ecio : " + signalStrength.getCdmaEcio() +  " dB*10" +
                    "\nEvdo Dbm : " + signalStrength.getEvdoDbm() + " Dbm" +
                    "\nEvdo Ecio : " + signalStrength.getEvdoEcio() + " dB*10" +
                    "\nGsm SignalStrength : " + signalStrength.getGsmSignalStrength() +
                    "\nGsm BitErrorRate : " + signalStrength.getGsmBitErrorRate());



        }

        @Override
        public void onCellInfoChanged(List<CellInfo> cellInfo) {
            super.onCellInfoChanged(cellInfo);
            Log.d(TAG, "onCellInfoChanged() called with " + "cellInfo = [" + cellInfo + "]");
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_device_info_activty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
