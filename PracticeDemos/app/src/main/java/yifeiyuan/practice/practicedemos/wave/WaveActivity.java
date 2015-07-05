package yifeiyuan.practice.practicedemos.wave;

import android.os.Bundle;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseActivity;

public class WaveActivity extends BaseActivity {


    @InjectView(R.id.btn_wave)
    Button mBtnWave;
    @InjectView(R.id.wave_view)
    WaveView mWaveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        ButterKnife.inject(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wave;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @OnClick(R.id.btn_wave)
    public void onClick(){
        mWaveView.startWave(10000);
    }
}

