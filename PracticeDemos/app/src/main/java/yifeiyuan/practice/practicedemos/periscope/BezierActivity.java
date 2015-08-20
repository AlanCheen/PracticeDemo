package yifeiyuan.practice.practicedemos.periscope;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.ToolbarActivity;

public class BezierActivity extends ToolbarActivity {

    @InjectView(R.id.btn_start_anim)
    Button mBtnStartAnim;
    @InjectView(R.id.zan)
    FavorLayout mFavorLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periscope);
        mBtnStartAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFavorLayout.addFavor();
            }
        });
    }

}
