package yifeiyuan.practice.practicedemos.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.materialsupport.GridFragment;
import yifeiyuan.practice.practicedemos.materialsupport.ListFragment;
import yifeiyuan.practice.practicedemos.materialsupport.StaggeredGridFragment;

public class UIOneActivity extends AppCompatActivity {

    @InjectView(R.id.tablayout)
    TabLayout mTablayout;
    @InjectView(R.id.vp)
    ViewPager mVp;

    TabAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uione);
        ButterKnife.inject(this);

        mAdapter = new TabAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new ListFragment(), "List");
        mAdapter.addFragment(new GridFragment(), "Grid");
        mAdapter.addFragment(new StaggeredGridFragment(), "StaggeredGrid");

        mVp.setAdapter(mAdapter);

        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTablayout.setupWithViewPager(mVp);
        mTablayout.setTabsFromPagerAdapter(mAdapter);
        mTablayout.setTabMode(TabLayout.MODE_FIXED);

    }

    private class TabAdapter extends FragmentPagerAdapter{

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

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
