package yifeiyuan.practice.practicedemos.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    public static final String TAG = "MyService";
    public MyService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

//    @Override
//    public void onStart(Intent intent, int startId) {
//        super.onStart(intent, startId);
//        Log.d(TAG, "onStart() called with: " + "intent = [" + intent + "], startId = [" + startId + "]");
//    }

    // onStartCommand 里调用了onstart
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() called with: " + "intent = [" + intent + "], flags = [" + flags + "], startId = [" + startId + "]");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: ");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory: ");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG, "onTrimMemory: ");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        //返回true则会走onReBind
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind: ");
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.d(TAG, "onTaskRemoved: ");
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return new MyBinder();
    }

    public class MyBinder extends Binder{

        public void dosth(){
            Log.d(TAG, "dosth: ");
        }
    }
}
