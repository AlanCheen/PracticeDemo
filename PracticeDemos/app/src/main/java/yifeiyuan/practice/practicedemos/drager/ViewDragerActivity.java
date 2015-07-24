package yifeiyuan.practice.practicedemos.drager;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.InjectView;
import butterknife.OnClick;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseActivity;

public class ViewDragerActivity extends BaseActivity {

    @InjectView(R.id.tv_one)
    TextView mTvOne;
    @InjectView(R.id.tv_two)
    TextView mTvTwo;
    @InjectView(R.id.tv_three)
    TextView mTvThree;
    @InjectView(R.id.drager)
    DragerView mDrager;
    @InjectView(R.id.tv_four)
    TextView mTvFour;
    @InjectView(R.id.tv_five)
    TextView mTvFive;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.appbar)
    AppBarLayout mAppbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_drager;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
    }

    @OnClick((R.id.tv_five))
    public void five() {
        Toast.makeText(mContext, "onclick", Toast.LENGTH_SHORT).show();
    }

}

