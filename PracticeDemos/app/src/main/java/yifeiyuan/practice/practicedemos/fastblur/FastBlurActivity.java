package yifeiyuan.practice.practicedemos.fastblur;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.ToolbarActivity;

public class FastBlurActivity extends ToolbarActivity {

    @InjectView(R.id.btn_start_blur)
    Button mBtnStartBlur;
    @InjectView(R.id.iv_blur)
    ImageView mIvBlur;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_blur);
        // FastBlur 用的时候 有些需要copy之后才行
        // Bitmap bitmap = sentBitmap.copy(Bitmap.Config.ARGB_8888, true);

        mBtnStartBlur.setOnClickListener(v -> {
            mIvBlur.buildDrawingCache();
            Bitmap bitmap = FastBlur.doBlur(mIvBlur.getDrawingCache(), 20, true);
            mIvBlur.setImageBitmap(bitmap);

        });
    }

    /**
     * 有些手机不支持....so  不能用
     *
     * @param sentBitmap
     * @param radius
     */
    private void RenderScriptblur(Bitmap sentBitmap, int radius) {

        long start = System.currentTimeMillis();

        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        final RenderScript rs = RenderScript.create(FastBlurActivity.this);
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

        Log.v(TAG, "fastblur time:" + (end - start));
    }


}
