package yifeiyuan.practice.practicedemos.materialsupport;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseActivity;

public class MaterialActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.tablayout)
    TabLayout mTablayout;
    @InjectView(R.id.app_bar)
    AppBarLayout mAppBar;
    @InjectView(R.id.view_pager)
    ViewPager mViewPager;
    @InjectView(R.id.fab)
    FloatingActionButton mFab;
    @InjectView(R.id.root)
    CoordinatorLayout mRoot;

    private TabAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.material_support;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mRoot, "Hello Material design !~", Snackbar.LENGTH_SHORT).show();
            }
        });
        setupViewPager();
        setupTab();
    }

    private void setupViewPager() {

        mAdapter = new TabAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new ListFragment(), "List");
        mAdapter.addFragment(new GridFragment(), "Grid");
        mAdapter.addFragment(new StaggeredGridFragment(), "StaggeredGrid");
        mViewPager.setAdapter(mAdapter);
    }

    private void setupTab() {

        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected() called with " + "tab = [" + tab + "]");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabUnselected() called with " + "tab = [" + tab + "]");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabReselected() called with " + "tab = [" + tab + "]");
            }
        });

//        mTablayout.setTabMode(TabLayout.MODE_FIXED);//默认 平均分 Notice 但是字多了会多行显示 很坑...
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);//Notice 每个tab的宽度都是wrap_content,大小不一致 不会出现多行显示
        //Notice 如果与viewpager 合用,那么这里设置的text将无效
        mTablayout.addTab(mTablayout.newTab().setText("new tab 1").setIcon(R.mipmap.blue));
        mTablayout.addTab(mTablayout.newTab().setText("new tab 2"), true);//设置默认选中2
        mTablayout.addTab(mTablayout.newTab().setText("new tab 3"));

        mTablayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_material, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_home:
                return true;
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class TabAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        /**
         * 这个决定了 tablayout 的显示的标题
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
