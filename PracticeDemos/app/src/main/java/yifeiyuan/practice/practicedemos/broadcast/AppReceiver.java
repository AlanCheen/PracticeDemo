package yifeiyuan.practice.practicedemos.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by alanchen on 15/9/14.
 *
 * 监听别的应用的安装和卸载
 */
public class AppReceiver extends BroadcastReceiver{

    public static final String TAG = "AppReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(Intent.ACTION_PACKAGE_ADDED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
            Log.d(TAG, "ACTION_PACKAGE_ADDED:" + packageName);
        }
        if (action.equals(Intent.ACTION_PACKAGE_REMOVED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
            Log.d(TAG,"ACTION_PACKAGE_REMOVED:"+packageName);
        }
        if (action.equals(Intent.ACTION_PACKAGE_REPLACED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
            Log.d(TAG,"ACTION_PACKAGE_REPLACED:"+packageName);
        }
    }
}
