package yifeiyuan.practice.practicedemos.periscope;

import android.os.Bundle;
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
    protected void init(Bundle savedInstanceState) {

        mBtnStartAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFavorLayout.addFavor();
            }
        });

//        ViewAnimationUtils.createCircularReveal(mFavorLayout,0,0, 5, 10);
    }

}
