package yifeiyuan.practice.practicedemos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;

import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.base.BaseActivity;

/**
 *
 *
 *  项目地址: https://github.com/flavioarfaria/KenBurnsView
 */
public class LaunchActivity extends BaseActivity {

    @InjectView(R.id.iv_welcome)
    KenBurnsView mIvWelcome;
    @InjectView(R.id.tv_pass)
    TextView mTvPass;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        mIvWelcome.setTransitionListener(new KenBurnsView.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                startActivity(new Intent(LaunchActivity.this,MainActivity.class));
                finish();
            }
        });

        mTvPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaunchActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIvWelcome.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIvWelcome.pause();
    }


}
