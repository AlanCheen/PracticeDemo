package yifeiyuan.practice.practicedemos.reveal;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseActivity;

public class RevealActivity extends BaseActivity {

    @InjectView(R.id.bg_reval)
    RevealBackgroundView mBgReval;
    @InjectView(R.id.tv_reval)
    TextView mTvReval;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reval;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        final int[]location  = getIntent().getExtras().getIntArray("location");

        mBgReval.setFillPaintColor(getResources().getColor(R.color.primary));
        mBgReval.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                //必须remove掉 不然会重复调用
                mBgReval.getViewTreeObserver().removeOnPreDrawListener(this);
                mBgReval.startFromLocation(location);
                return false;
            }
        });
    }
}
