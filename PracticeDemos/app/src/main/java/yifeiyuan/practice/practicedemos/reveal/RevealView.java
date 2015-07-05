package yifeiyuan.practice.practicedemos.reveal;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import yifeiyuan.practice.practicedemos.R;

/**
 * Created by yifeiyuan on 15/7/1.
 */
public class RevealView extends View {

    public static final String TAG = "RevealView";

    public RevealView(Context context) {
        super(context);
        init();
    }

    public RevealView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RevealView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.primary_dark));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    private Paint mPaint;
    private int radius;

    public void startReveal() {

        //计算半径 sqrt 开平方,pow 计算x的n次方
        // +1 为了避免强转丢失精度
        int maxRadius = (int) (Math.sqrt(Math.pow(getHeight(), 2)+ getWidth() * getWidth()) + 1)/2;
        ObjectAnimator revealAnimator = ObjectAnimator.ofInt(this, "radius", maxRadius).setDuration(300);
        revealAnimator.setInterpolator(new AccelerateInterpolator());
        revealAnimator.start();
    }

    public void startReveal2() {

        ObjectAnimator revealAnimator = ObjectAnimator.ofInt(this, "radius", 20, 50);
        revealAnimator.setRepeatMode(ValueAnimator.REVERSE);
        revealAnimator.setInterpolator(new LinearInterpolator());
        revealAnimator.setRepeatCount(ValueAnimator.INFINITE);
        revealAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);
    }

    public int getRadius() {
        Log.d(TAG, "getRadius ");

        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        Log.d(TAG, "setRadius "+radius);
        //调用invalidate 之后 onDraw会被调用
        invalidate();
    }

}
