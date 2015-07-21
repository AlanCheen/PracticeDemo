package yifeiyuan.practice.practicedemos.drager;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.InjectView;
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_drager;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

}
