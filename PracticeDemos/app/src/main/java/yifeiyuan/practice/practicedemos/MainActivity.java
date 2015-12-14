package yifeiyuan.practice.practicedemos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import yifeiyuan.practice.practicedemos.base.AboutMeActivity;
import yifeiyuan.practice.practicedemos.base.Practice;
import yifeiyuan.practice.practicedemos.camera.CameraActivity;
import yifeiyuan.practice.practicedemos.customview.CanvasActivity;
import yifeiyuan.practice.practicedemos.customview.ClipActivity;
import yifeiyuan.practice.practicedemos.customview.CustomViewAndGroupActivity;
import yifeiyuan.practice.practicedemos.customview.SwitchActivity;
import yifeiyuan.practice.practicedemos.fastblur.FastBlurActivity;
import yifeiyuan.practice.practicedemos.info.DeviceInfoActivty;
import yifeiyuan.practice.practicedemos.intent.IntentActivity;
import yifeiyuan.practice.practicedemos.itemtouchhelper.TouchHelperActivity;
import yifeiyuan.practice.practicedemos.jni.HelloJni;
import yifeiyuan.practice.practicedemos.materialsupport.MaterialActivity;
import yifeiyuan.practice.practicedemos.materialsupport.TextInputActivity;
import yifeiyuan.practice.practicedemos.periscope.BezierActivity;
import yifeiyuan.practice.practicedemos.recyclerview.HorizontalRvActivity;
import yifeiyuan.practice.practicedemos.reveal.GoToRevealActivity;
import yifeiyuan.practice.practicedemos.screenorientation.OrientationActivity;
import yifeiyuan.practice.practicedemos.service.ServiceActivity;
import yifeiyuan.practice.practicedemos.surfaceview.GlsurfaceActivity;
import yifeiyuan.practice.practicedemos.thirdparty.FacebookActivity;
import yifeiyuan.practice.practicedemos.thirdparty.TwitterActivity;
import yifeiyuan.practice.practicedemos.touch.TouchActivity;
import yifeiyuan.practice.practicedemos.ui.ScrollingActivity;
import yifeiyuan.practice.practicedemos.ui.SettingsActivity;
import yifeiyuan.practice.practicedemos.ui.UIOneActivity;
import yifeiyuan.practice.practicedemos.ui.ViewPagerActivity;
import yifeiyuan.practice.practicedemos.viewdrager.SwipeBackActivity;
import yifeiyuan.practice.practicedemos.viewdrager.ViewDragerActivity;
import yifeiyuan.practice.practicedemos.webview.WebViewActivity;
import yifeiyuan.practice.practicedemos.window.FloatingWindowActivity;
//// TODO: 15/10/30 http://stackoverflow.com/questions/26440879/how-do-i-use-drawerlayout-to-display-over-the-actionbar-toolbar-and-under-the-st
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @InjectView(R.id.nav_view)
    NavigationView mNavView;
    @InjectView(R.id.listview)
    ListView mListView;
    @InjectView(R.id.drawer)
    DrawerLayout mDrawer;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.fab)
    FloatingActionButton mFab;
    @InjectView(R.id.coordinator)
    CoordinatorLayout mCoordinator;
    private ArrayList<Practice> mPractices;
    private Context mContext;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        Log.d(TAG, "onCreate: ");

        mContext = this;
        initData();
        setupListView();
        setupToolbar();
        setupDrawer();
    }

    private void initData() {

        mPractices = new ArrayList<>();


        mPractices.add(new Practice("ActivityLaunchMode", new Intent(mContext, SwitchActivity.class)));

        mPractices.add(new Practice("FaceView", new Intent(mContext, SwitchActivity.class)));

        mPractices.add(new Practice("Camera5.1.1", new Intent(mContext, CameraActivity.class)));

        mPractices.add(new Practice("Facebook 分享", new Intent(mContext, FacebookActivity.class)));

        mPractices.add(new Practice("Touch事件分发", new Intent(mContext, TouchActivity.class)));

        mPractices.add(new Practice("ViewPager 效果 1", new Intent(mContext, ViewPagerActivity.class)));
        mPractices.add(new Practice("UI效果 one ", new Intent(mContext, UIOneActivity.class)));

        mPractices.add(new Practice("Material Setting ", new Intent(mContext, SettingsActivity.class)));

        mPractices.add(new Practice("Material Scrolling ", new Intent(mContext, ScrollingActivity.class)));

        mPractices.add(new Practice("Service学习 ", new Intent(mContext, ServiceActivity.class)));

        mPractices.add(new Practice("GLSurfaceView ", new Intent(mContext, GlsurfaceActivity.class)));

        mPractices.add(new Practice("悬浮窗.", new Intent(mContext, FloatingWindowActivity.class)));

        mPractices.add(new Practice("自定义ViewAndViewGroup", new Intent(mContext, CustomViewAndGroupActivity.class)));

        mPractices.add(new Practice("Twitter Login", new Intent(mContext, TwitterActivity.class)));
        mPractices.add(new Practice("Canvas裁剪", new Intent(mContext, ClipActivity.class)));
        mPractices.add(new Practice("Canvas基础绘画", new Intent(mContext, CanvasActivity.class)));

        mPractices.add(new Practice("Periscope点赞效果", new Intent(mContext, BezierActivity.class)));

        mPractices.add(new Practice("Fastblur", new Intent(mContext, FastBlurActivity.class)));
        mPractices.add(new Practice("RevealEffect", new Intent(mContext, GoToRevealActivity.class)));
//        mPractices.add(new Practice("Wave", new Intent(mContext, WaveActivity.class)));
        mPractices.add(new Practice("ViewDragerHelper之基础", new Intent(mContext, ViewDragerActivity.class)));
        mPractices.add(new Practice("ViewDragerHelper之SwipeBack", new Intent(mContext, SwipeBackActivity.class)));
        mPractices.add(new Practice("SwipeDismiss", new Intent(mContext, TouchHelperActivity.class)));
        mPractices.add(new Practice("横竖屏切换", new Intent(mContext, OrientationActivity.class)));
        mPractices.add(new Practice("WebView基础", new Intent(mContext, WebViewActivity.class)));
        mPractices.add(new Practice("Material Support", new Intent(mContext, MaterialActivity.class)));
        mPractices.add(new Practice("TextInput", new Intent(mContext, TextInputActivity.class)));
        mPractices.add(new Practice("设备信息", new Intent(mContext, DeviceInfoActivty.class)));
        mPractices.add(new Practice("Android基础之Intent", new Intent(mContext, IntentActivity.class)));
        mPractices.add(new Practice("IntentService", new Intent(mContext, ServiceActivity.class)));
        mPractices.add(new Practice("水平的RecyclerView", new Intent(mContext, HorizontalRvActivity.class)));

    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);//Notice  如果这个注释 那么就保存不了view的一些状态 比如滚动到了哪里,侧边栏是否打开
        Log.d(TAG, "onSaveInstanceState: ");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
    }

    private void setupListView(){
        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Practice practice = mPractices.get(position);
                startActivity(practice.intent);
            }
        });
    }


    private void setupDrawer(){

        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                //todo
                mDrawer.closeDrawer(GravityCompat.START);

                switch (menuItem.getItemId()) {
                    case R.id.github:
                        startActivity(WebViewActivity.start(MainActivity.this, Const.URL_GITHUB));
                        break;

                    case R.id.jianshu:
                        startActivity(WebViewActivity.start(MainActivity.this, Const.URL_JIANSHU));
                        break;

                    case R.id.sina:
                        startActivity(WebViewActivity.start(MainActivity.this, Const.URL_SINA));
                        break;

                    case R.id.menu_about:
                        startActivity(AboutMeActivity.start(MainActivity.this));
                        break;
                }
                return false;
            }
        });

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open_drawer, R.string.close_drawer){
            //可以重写一些方法
        };
        mDrawerToggle.syncState();
        mDrawer.setDrawerListener(mDrawerToggle);

//        mDrawer.setStatusBarBackgroundColor(getResources().getColor(R.color.white));

    }

    private void setupToolbar(){
        setSupportActionBar(mToolbar);
        //似乎没什么用 不设置也没有关系(或许跟ActionBarDrawerToggle 有关)
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);//左上角返回键 设置这个就行
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));

    }

    @OnClick({R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:

//                Log.d(TAG,Log.getStackTraceString(new Throwable()));//简单暴力 打出堆栈

                String jni = HelloJni.helloJni();
                Snackbar.make(mFab, jni, Snackbar.LENGTH_SHORT).setAction("TODO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "TODO", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
        }

//        throw new IllegalArgumentException("Hello crashcatcher");
    }


    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mPractices.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            if (null == convertView) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.mooc_item, null);
                viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Practice mooc = mPractices.get(position);
            viewHolder.title.setText(mooc.title);
            return convertView;
        }

         class ViewHolder {
            TextView title;
        }
    }


}
