package yifeiyuan.practice.practicedemos.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;

/**
 * http://www.trinea.cn/android/viewpager-multi-fragment-effect/
 *
 */
public class ViewPagerActivity extends AppCompatActivity {


    @InjectView(R.id.vp)
    ViewPager mVp;
    @InjectView(R.id.rl_root)
    RelativeLayout mRlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.inject(this);

        mVp.setOffscreenPageLimit(5);
        mVp.setPageMargin(100);

        mVp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                View view = new View(ViewPagerActivity.this
                );
                view.setBackgroundColor(getResources().getColor(R.color.primary_dark));
                view.setBackgroundColor(Color.rgb(position * 55, position * 22, position * 33));
                container.addView(view, position);
                return view;
//                return super.instantiateItem(container, position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });


        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                mRlRoot.invalidate();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
