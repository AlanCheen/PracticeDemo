package yifeiyuan.practice.practicedemos.customview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;

public class CustomViewActivity extends AppCompatActivity {

    @InjectView(R.id.cir)
    CircleImgView mCir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        ButterKnife.inject(this);

//        mCir.setRankBmp();
        Drawable drawable = getResources().getDrawable(R.mipmap.pic);
        mCir.setRankDrawle(drawable);

    }
}
