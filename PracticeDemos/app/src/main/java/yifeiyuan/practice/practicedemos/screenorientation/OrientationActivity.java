package yifeiyuan.practice.practicedemos.screenorientation;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.view.Surface;

import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseActivity;

/**
 * 参考 : http://ticktick.blog.51cto.com/823160/1301209
 */
public class OrientationActivity extends BaseActivity {

    private MyOrientationEventListener myOrientationEventListener;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_config;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    //必须在manifests 里配置
    // android:configChanges="screenSize|orientation"
    // 才会有回调
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged() called with " + "newConfig = [" + newConfig + "]");
        //Notice 必须调用super!! 否则会崩溃 android.util.SuperNotCalledException: Activity screenorientation.OrientationActivity did not call through to super.onConfigurationChanged()
        super.onConfigurationChanged(newConfig);

//        newConfig.getLayoutDirection();//RTL or LTR

        if (Configuration.ORIENTATION_LANDSCAPE == newConfig.orientation) {
            Log.d(TAG, "onConfigurationChanged :" + "landscape 横屏");
        } else if (Configuration.ORIENTATION_PORTRAIT == newConfig.orientation) {
            Log.d(TAG, "onConfigurationChanged :" + "portrait 竖屏");
        }

        int rotation = getWindowManager().getDefaultDisplay().getRotation();

        switch (rotation) {
            case Surface.ROTATION_0:
                Log.d(TAG, "onConfigurationChanged rotation:" +"0");
                break;
            case Surface.ROTATION_90:
                Log.d(TAG, "onConfigurationChanged rotation:" +"90");
                break;
            case Surface.ROTATION_180:
                Log.d(TAG, "onConfigurationChanged rotation:" +"180");
                break;
            case Surface.ROTATION_270:
                Log.d(TAG, "onConfigurationChanged rotation:" +"270");
                break;
        }
        Log.d(TAG, "onConfigurationChanged rotation:" + rotation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate ");

        //无视manifest的设置 强制设置屏幕方向
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 横屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);// 传感器


        myOrientationEventListener = new MyOrientationEventListener(this);
        myOrientationEventListener.enable();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_config, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ori_land) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 横屏
        } else if (item.getItemId() == R.id.ori_port) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 竖屏
        } else {
            //这个不会回调onConfigurationChanged 而上面两个会
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);// 传感器
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy ");

        //Notice 必须disable 不然页面关闭后  它依然在工作 不断打印
        myOrientationEventListener.disable();
    }


    private class MyOrientationEventListener extends OrientationEventListener {

        public MyOrientationEventListener(Context context) {
            super(context);
        }

        @Override
        public void onOrientationChanged(int orientation) {
            Log.d(TAG, "onOrientationChanged() called with " + "orientation = [" + orientation + "]");

            //Notice  手机水平放置的时候是-1  OrientationEventListener.ORIENTATION_UNKNOWN
            //手机竖直是0 按顺时针方向 0-359

        }
    }

}
