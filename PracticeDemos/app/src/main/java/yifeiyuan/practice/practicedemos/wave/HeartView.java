package yifeiyuan.practice.practicedemos.wave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by alanchen on 15/7/5.
 */
public class HeartView extends View{
    public HeartView(Context context) {
        super(context);
    }

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#ff0000"));
        mPaint.setStyle(Paint.Style.STROKE);

    }

    private Paint mPaint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(p, mPaint);
    }


    private int w;
    private int h;
    Path p;
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        w = getWidth();
        h = getHeight();
        p = new Path();
        p.moveTo(w / 2, 4 * h / 7);
        p.quadTo(2*w, 0, w / 2, h/1.5f);
        p.quadTo(-w, 0, w / 2, 4*h / 7);
    }
}
