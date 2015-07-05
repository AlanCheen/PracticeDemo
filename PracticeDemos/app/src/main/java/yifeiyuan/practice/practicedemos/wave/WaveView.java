package yifeiyuan.practice.practicedemos.wave;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import yifeiyuan.practice.practicedemos.R;

/**
 * Created by alanchen on 15/7/2.
 */
public class WaveView extends View {


    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.primary));
        mPaint.setAntiAlias(true);

    }

    private int currentHeight = 0;

    public void setCurrentHeight(int currentHeight) {
        this.currentHeight = currentHeight;
        invalidate();
    }

    public void startWave(int duration) {
        setVisibility(VISIBLE);
        ObjectAnimator waveAnimator = ObjectAnimator.ofInt(this, "currentHeight", 0, height).setDuration(duration);
        waveAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        waveAnimator.start();
    }
    private void reset(){

    }
    private Paint mPaint;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        for (int i = 0; i < width; i++) {
            canvas.drawLine(i,getHeight(),i,height-getYY(i),mPaint);
        }
    }

    private double width;
    private int height;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    private float getYY(double x) {
        double a  =x/width;
        return (float) (currentHeight*(Math.sin(2*Math.PI*a)+1));
    }
}
