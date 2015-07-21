package yifeiyuan.practice.practicedemos.drager;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by alanchen on 15/7/14.
 */
public class DragerView extends LinearLayout{

    public static final String TAG = DragerView.class.getSimpleName();

    private ViewDragHelper mDragHelper;

    public DragerView(Context context) {
        this(context, null);
    }

    public DragerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mDragHelper = ViewDragHelper.create(this,1f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                Log.d(TAG, "tryCaptureView() called with " + "child = [" + child + "], pointerId = [" + pointerId + "]");
                return true;
            }

            /**
             * 水平固定?
             * 限制子view
             * @param child  移动的view
             * @param left   即将移动到的位置
             * @param dx     差值
             * @return
             */
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                Log.d(TAG, "clampViewPositionHorizontal() called with " +"left = [" + left + "], dx = [" + dx + "]");
                int leftbound = getPaddingLeft();
                int rightbound = mWidth-getPaddingRight()-child.getWidth();
//                return Math.max(left,0);//感觉像是整个坐标系 在 整个屏幕上
                return Math.min(Math.max(leftbound,left),rightbound);
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                Log.d(TAG, "clampViewPositionVertical() called with " + "top = [" + top + "], dy = [" + dy + "]");
                return top;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
            }
        });

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
//        return super.onTouchEvent(event);
        return true;
    }

    private View mTv1;
    private View mTv2;
    private View mTv3;
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTv1 = getChildAt(0);
        mTv2 = getChildAt(1);
        mTv3 = getChildAt(2);

    }

    private int mHeight;
    private int mWidth;
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mWidth = getWidth();
        mHeight = getHeight();
    }
}