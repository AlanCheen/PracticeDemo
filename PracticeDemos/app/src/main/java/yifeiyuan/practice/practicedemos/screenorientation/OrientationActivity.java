package yifeiyuan.practice.practicedemos.screenorientation;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseActivity;

public class OrientationActivity extends BaseActivity {

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

//        newConfig.getLayoutDirection();//RTL or LTR

        if (Configuration.ORIENTATION_LANDSCAPE == newConfig.orientation) {
            Log.d(TAG, "onConfigurationChanged :" + "landscape 横屏");
        } else if (Configuration.ORIENTATION_PORTRAIT == newConfig.orientation) {
            Log.d(TAG, "onConfigurationChanged :" + "portrait 竖屏");
        }
        super.onConfigurationChanged(newConfig);//必须调用super!! 否则会崩溃 android.util.SuperNotCalledException: Activity screenorientation.OrientationActivity did not call through to super.onConfigurationChanged()

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate ");

        //无视manifest的设置 强制设置屏幕方向
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 横屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);// 传感器

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
    }
}
