package yifeiyuan.practice.practicedemos;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadService extends IntentService {

    public static final String TAG = "DownloadService";

    public static final String EXTRA_PATH = "yfy.practice.filepath";

    public DownloadService() {
        super("DownloadService");
    }

    String mUrl;

    @Override
    protected void onHandleIntent(Intent intent) {
        mUrl = intent.getStringExtra(EXTRA_PATH);
        if (TextUtils.isEmpty(mUrl)) {
            stopSelf();
        } else {
            download();
        }
    }

    private void download() {

        try {
            //file:/storage/emulated/0/test.apk
            File file = new File(Environment.getExternalStorageDirectory(), "test.apk");
            Log.d(TAG, "download: file:" + file.getPath());
            URL url = new URL(mUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream in = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(in);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int size = -1;
            while ((size = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, size);
            }

            fos.flush();
            fos.close();
            bis.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        super.setIntentRedelivery(enabled);
        Log.d(TAG, "setIntentRedelivery() called with: " + "enabled = [" + enabled + "]");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart() called with: " + "intent = [" + intent + "], startId = [" + startId + "]");
    }

    // onStartCommand 里调用了onstart
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() called with: " + "flags = [" + flags + "], startId = [" + startId + "]");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called with: " + "");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() called with: " + "intent = [" + intent + "]");
        return super.onBind(intent);
    }
}
