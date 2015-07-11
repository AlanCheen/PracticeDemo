package yifeiyuan.practice.practicedemos.reveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
        setVisibility(VISIBLE);
        //计算对角线 sqrt 开平方,pow 计算x的n次方
        int maxRadius = (int) (Math.sqrt(Math.pow(getHeight(), 2)+ Math.pow(getWidth(), 2)));
        ObjectAnimator revealAnimator = ObjectAnimator.ofInt(this, "radius", 0,maxRadius).setDuration(300);
        revealAnimator.setInterpolator(new AccelerateInterpolator());
        revealAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (null != callback) {
                    callback.onRevealEnd();
                }
            }
        });
        revealAnimator.start();
    }

    /**
     *  可以做成loading效果~~
     */
    public void startLoading() {

        ObjectAnimator revealAnimator = ObjectAnimator.ofInt(this, "radius", 20, 50);
        revealAnimator.setRepeatMode(ValueAnimator.REVERSE);
        revealAnimator.setInterpolator(new LinearInterpolator());
        revealAnimator.setRepeatCount(ValueAnimator.INFINITE);
        revealAnimator.start();
        //revealAnimator.cancel();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //原点是中心
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);
    }

    public int getRadius() {
        Log.d(TAG, "getRadius ");//当属性动画不设置初始值时,get方法才会被调用

        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        Log.d(TAG, "setRadius "+radius);
        //Notice 调用invalidate 之后 onDraw才会被调用
        invalidate();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
    private Callback callback;
    public interface Callback{
        void onRevealEnd();
    }

}
