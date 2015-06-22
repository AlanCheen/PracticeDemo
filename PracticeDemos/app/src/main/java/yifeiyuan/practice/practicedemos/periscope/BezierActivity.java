package yifeiyuan.practice.practicedemos.periscope;

import android.view.View;
import android.widget.Button;

import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseActivity;

public class BezierActivity extends BaseActivity {

    @InjectView(R.id.btn_start_anim)
    Button mBtnStartAnim;
    @InjectView(R.id.zan)
    FavorLayout mFavorLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_periscope;
    }

    @Override
    protected void init() {

        mBtnStartAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFavorLayout.addFavor();
            }
        });
    }

}
