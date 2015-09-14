package yifeiyuan.practice.practicedemos.viewdrager;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by alanchen on 15/7/29.
 */
public class SwipeBackFrameLayout extends FrameLayout {

    public static final String TAG = SwipeBackFrameLayout.class.getSimpleName();
    private ViewDragHelper mDragHelper;

    public SwipeBackFrameLayout(Context context) {
        this(context, null);
    }

    public SwipeBackFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeBackFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int mLastdx;
    private void init() {
        mDragHelper = ViewDragHelper.create(this, 1f,new ViewDragHelper.Callback() {

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return false;
            }

            @Override
            public void onEdgeTouched(int edgeFlags, int pointerId) {
                super.onEdgeTouched(edgeFlags, pointerId);
                mDragHelper.captureChildView(mContentView, pointerId);
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return 1;
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                Log.d(TAG, "onViewPositionChanged() called with left = [" + left + "], top = [" + top + "], dx = [" + dx + "], dy = [" + dy + "]");
                //0.0 - 1.0
                //Notice 这边可以给个接口回调出去,就可以做各种炫酷的效果了
                float alpha = (float) (left*1.0/mDividerWidth);
                mDividerView.setAlpha(alpha);
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
//                Log.d(TAG, "clampViewPositionHorizontal() called with  dx = [" + dx + "]");
                mLastdx = dx;
                int newLeft = Math.min(mDividerWidth, Math.max(left,0));
                return newLeft;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {

                //>0代表用户想关闭
                if (mLastdx>0){
                    if (mDividerWidth != releasedChild.getLeft()) {
                        mDragHelper.settleCapturedViewAt(mDividerWidth, releasedChild.getTop());
                        invalidate();
                    } else {
                        if (mCallback != null) {
                            mCallback.onShouldFinish();
                        }
                    }
                }else{
                    if (mDividerWidth != 0) {
                        mDragHelper.settleCapturedViewAt(0, releasedChild.getTop());
                        invalidate();
                    }
                }
            }

            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
                if (mDragHelper.getViewDragState()==ViewDragHelper.STATE_IDLE&&mCallback != null&&mDividerWidth==mContentView.getLeft()&&mLastdx>0) {
                    mCallback.onShouldFinish();
                }
            }
        });

        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    private View mDividerView;
    private View mContentView;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDividerView = getChildAt(0);
        mDividerView.setAlpha(0f);
        mContentView = getChildAt(1);
    }

    private int mDividerWidth;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mDividerWidth = mDividerView.getWidth();
    }


    //Notice view 刚初始化的时候就会被调用一次
    @Override
    public void computeScroll() {
        super.computeScroll();
//        Log.d(TAG, "computeScroll() called with " + "");
        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    public void setCallback(Callback mCallback){
        this.mCallback = mCallback;
    }
    private Callback mCallback;
    public interface Callback{
        void onShouldFinish();
    }
}