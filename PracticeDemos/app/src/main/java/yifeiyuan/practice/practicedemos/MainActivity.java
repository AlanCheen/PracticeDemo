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
import yifeiyuan.practice.practicedemos.base.Practice;
import yifeiyuan.practice.practicedemos.blur.BlurActivity;
import yifeiyuan.practice.practicedemos.screenorientation.OrientationActivity;
import yifeiyuan.practice.practicedemos.drager.ViewDragerActivity;
import yifeiyuan.practice.practicedemos.periscope.BezierActivity;
import yifeiyuan.practice.practicedemos.reveal.GoToRevealActivity;
import yifeiyuan.practice.practicedemos.wave.WaveActivity;

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

        mContext = this;
        initData();
        setUpListView();
        setUpToolbar();
        setUpDrawer();
    }


    private void initData() {

        mPractices = new ArrayList<>();
        mPractices.add(new Practice("Periscope点赞效果", new Intent(mContext, BezierActivity.class)));
        mPractices.add(new Practice("Blur", new Intent(mContext, BlurActivity.class)));
        mPractices.add(new Practice("RevealEffect", new Intent(mContext, GoToRevealActivity.class)));
        mPractices.add(new Practice("Wave", new Intent(mContext, WaveActivity.class)));
        mPractices.add(new Practice("ViewDragerHelper", new Intent(mContext, ViewDragerActivity.class)));
        mPractices.add(new Practice("横竖屏切换", new Intent(mContext, OrientationActivity.class)));

    }

    private void setUpListView(){
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


    private void setUpDrawer(){

        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //todo
                mDrawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open_drawer, R.string.close_drawer){
            //可以重写一些方法
        };
        mDrawerToggle.syncState();
        mDrawer.setDrawerListener(mDrawerToggle);
    }

    private void setUpToolbar(){
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

//                Log.d(TAG,Log.getStackTraceString(new Throwable()));//简单暴力

                Snackbar.make(mFab, "Snacke ssss......", Snackbar.LENGTH_SHORT).setAction("TODO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "TODO", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
        }
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
