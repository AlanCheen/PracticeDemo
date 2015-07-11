package yifeiyuan.practice.practicedemos.blur;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseActivity;

public class BlurActivity extends BaseActivity {

    @InjectView(R.id.btn_start_blur)
    Button mBtnStartBlur;
    @InjectView(R.id.iv_blur)
    ImageView mIvBlur;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gauss_blur;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mBtnStartBlur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIvBlur.buildDrawingCache();
                fastBlur(mIvBlur.getDrawingCache(),20);
            }
        });
    }

    private void fastBlur(Bitmap sentBitmap, int radius){
        long start = System.currentTimeMillis();

        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        final RenderScript rs = RenderScript.create(BlurActivity.this);
        final Allocation input = Allocation.createFromBitmap(rs, sentBitmap, Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        final Allocation output = Allocation.createTyped(rs, input.getType());
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(radius /* e.g. 3.f */);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(bitmap);

        mIvBlur.setImageBitmap(bitmap);
        long end = System.currentTimeMillis();

        Log.v(TAG,"fastblur time:"+(end-start));
    }



}
