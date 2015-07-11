package yifeiyuan.practice.practicedemos.reveal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseActivity;

public class GoToRevealActivity extends BaseActivity {

    @InjectView(R.id.textView1)
    TextView mTextView1;
    @InjectView(R.id.textView2)
    TextView mTextView2;
    @InjectView(R.id.textView3)
    TextView mTextView3;
    @InjectView(R.id.textView4)
    TextView mTextView4;
    @InjectView(R.id.textView5)
    TextView mTextView5;
    @InjectView(R.id.raval_parent)
    RelativeLayout mRavalParent;
    @InjectView(R.id.btn_reveal)
    Button mBtnReveal;
    @InjectView(R.id.v_reveal)
    RevealView mVReveal;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_go_to_reval;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mVReveal.setCallback(new RevealView.Callback() {
            @Override
            public void onRevealEnd() {
//                mVReveal.setVisibility(View.GONE);
//                todo 其他事情
            }
        });
    }

    @OnClick({R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5})
    public void goToReval(View view) {

        Log.d(TAG, "goToReval " + mRavalParent.getTop() + ";;" + mRavalParent.getBottom() + ";;" + mRavalParent.getHeight());

        Log.d(TAG, "goToReval " + view.getLeft() + ";" + view.getTop());

        int wh = getWindow().getDecorView().getHeight();
        Log.d(TAG, "goToReval wh" + wh);
        int[] location = new int[2];
        view.getLocationInWindow(location);
        Log.d(TAG, "goToReval loaction" + location[0] + ";;" + location[1] + ";;;left+" + view.getLeft());
        view.getLocationOnScreen(location);//Notice location[0] 为在屏幕上的x坐标值 location[1]为y轴 (这个屏幕是包括了状态栏的)
        Log.d(TAG, "goToReval loaction" + location[0] + ";;" + location[1]);
        Intent intent = new Intent(this, RevealActivity.class);
        intent.putExtra("location", location);
        startActivity(intent);
        overridePendingTransition(0, 0);//Notice 这个绝对不能省..不然没有效果
    }

    @OnClick(R.id.btn_reveal)
    public void doReveal(){
        mVReveal.startReveal();
    }

    @OnClick(R.id.btn_reveal2)
    public void doReveal2(){

        mVReveal.startLoading();
    }
}
