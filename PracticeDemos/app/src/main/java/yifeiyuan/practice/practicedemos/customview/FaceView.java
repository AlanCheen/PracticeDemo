package yifeiyuan.practice.practicedemos.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 程序亦非猿 on 15/11/18.
 * aaaaa
 */
public class FaceView extends View {

    public static final String TAG = FaceView.class.getSimpleName();

    public static final String HEIGHT_RATE = "heightRate";

    private int mWidth;
    private int mHeight;
    private PointF mLeftEye;
    private PointF mRightEye;
    private Paint mPaint;

    private float heightRate = MIN_RATE;
    private static final float MAX_RATE = 0.7f;
    private static final float MIN_RATE = 0.3f;
    private Path mPath;
    private Paint mMouthPaint;

    public FaceView(Context context) {
        this(context, null);
    }

    public FaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mLeftEye = new PointF();
        mRightEye = new PointF();

        //画个圆角
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(10);
        drawable.setColor(Color.parseColor("#ff0000"));
        setBackground(drawable);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#000000"));
        mPaint.setStrokeWidth(4);

        mMouthPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMouthPaint.setColor(Color.parseColor("#000000"));
        mMouthPaint.setStrokeWidth(2);

        mPath = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(mLeftEye.x, mLeftEye.y, mPaint);
        canvas.drawPoint(mRightEye.x, mRightEye.y, mPaint);

        canvas.drawPath(mPath,mMouthPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();
        mHeight = getHeight();


        mPath.reset();

        mLeftEye.x = mWidth * 0.2f;
        mLeftEye.y = mHeight * heightRate;

        mRightEye.x = mWidth * 0.8f;
        mRightEye.y = mHeight * heightRate;

//        mPath.moveTo(mWidth * 0.3f,);
    }


    boolean isHappy;
    public void toogle() {
        if (isHappy) {
            sad();
        }else{
            happy();
        }
    }
    public void happy() {
        isHappy = true;
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, HEIGHT_RATE, MAX_RATE);
        animator.setDuration(200);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        animator.start();
    }

    public void sad() {
        isHappy = false;
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, HEIGHT_RATE, MIN_RATE);
        animator.setDuration(200);
        animator.start();
    }

    public float getHeightRate() {
        return heightRate;
    }

    public void setHeightRate(float heightRate) {
        this.heightRate = heightRate;
        mLeftEye.y = heightRate*mHeight;
        mRightEye.y = heightRate*mHeight;
        invalidate();
    }
}