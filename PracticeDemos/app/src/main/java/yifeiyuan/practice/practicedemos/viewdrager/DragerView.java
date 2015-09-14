package yifeiyuan.practice.practicedemos.viewdrager;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by alanchen on 15/7/14.
 *
 * onFinishInflate
 * onAttachedToWindow
 * onMeasure
 * onLayout
 * onMeasure
 * onLayout
 *
 *
 * 一般流程:             return
 * tryCaptureView        true
 * onViewCaptured
 * 一直move:clampViewPositionVertical/clampViewPositionHorizontal  left/top值
 * up:onViewReleased
 */
public class DragerView extends LinearLayout {

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


    private int originLeft;
    private int originTop;
    private void init() {

        mDragHelper = ViewDragHelper.create(this, 1f, new ViewDragHelper.Callback() {

            /**
             *
             * @param child  用户想要拖动的view
             * @param pointerId
             * @return true 代表可以拖动
             */
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                Log.d(TAG, "tryCaptureView() called with " + "child = [" + child + "], pointerId = [" + pointerId + "]");
                return child == mNormal || child == mAutoBack||child == mClickableView;
            }

            /**
             * 当某个view 被captured 后会被调用
             * @param capturedChild
             * @param activePointerId
             */
            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                Log.d(TAG, "onViewCaptured() called with " + "capturedChild = [" + capturedChild + "], activePointerId = [" + activePointerId + "]");
                super.onViewCaptured(capturedChild, activePointerId);
            }

            /**
             * 水平固定?
             * 限制子view
             *
             * @param child 移动的view
             * @param left  即将移动到的位置 以此确定x轴的位置
             * @param dx    差值 从5 ~8 那么dx就是3
             * @return
             */
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                Log.d(TAG, "clampViewPositionHorizontal() called with " + "left = [" + left + "], dx = [" + dx + "]");
                int leftbound = getPaddingLeft();
                int rightbound = mWidth - getPaddingRight() - child.getWidth();
//                return Math.max(left,0);//感觉像是整个坐标系 在 整个屏幕上
                return Math.min(Math.max(leftbound, left), rightbound);
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                Log.d(TAG, "clampViewPositionVertical() called with " + "top = [" + top + "], dy = [" + dy + "]");
                return top;
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                Log.d(TAG, "onViewPositionChanged() called with " + "changedView = [" + changedView + "], left = [" + left + "], top = [" + top + "], dx = [" + dx + "], dy = [" + dy + "]");
                super.onViewPositionChanged(changedView, left, top, dx, dy);
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                Log.d(TAG, "onViewReleased() called with " + "releasedChild = [" + releasedChild + "], xvel = [" + xvel + "], yvel = [" + yvel + "]");
                super.onViewReleased(releasedChild, xvel, yvel);
                if (releasedChild == mAutoBack) {
                    //Notice 其实 就是scroller.startScroll 所以记得要invalidate 还要重写 computeScroll
                    mDragHelper.settleCapturedViewAt(originLeft, originTop);
                    invalidate();
                }

            }

            /**
             * 拖动状态改变
             * @see ViewDragHelper#STATE_IDLE     0        停止
             * STATE_DRAGGING                     1        正在被拖动
             * STATE_SETTLING                     2        自动移动?
             * @param state
             */
            @Override
            public void onViewDragStateChanged(int state) {
                Log.d(TAG, "onViewDragStateChanged() called with " + "state = [" + state + "]");
                super.onViewDragStateChanged(state);
            }

            /**
             * 当被订阅的边界被touch 时 触发
             * @param edgeFlags
             * @param pointerId
             */
            @Override
            public void onEdgeTouched(int edgeFlags, int pointerId) {
                Log.d(TAG, "onEdgeTouched() called with " + "edgeFlags = [" + edgeFlags + "], pointerId = [" + pointerId + "]");
                super.onEdgeTouched(edgeFlags, pointerId);

                //Notice captureChildView 强制capture 无视try
                if (ViewDragHelper.EDGE_LEFT == edgeFlags) {
                    mDragHelper.captureChildView(mEdgeLeft, pointerId);
                } else if (ViewDragHelper.EDGE_RIGHT == edgeFlags) {
                    mDragHelper.captureChildView(mEdgeRight, pointerId);
                }
            }


            @Override
            public boolean onEdgeLock(int edgeFlags) {
                Log.d(TAG, "onEdgeLock() called with " + "edgeFlags = [" + edgeFlags + "]");
                return super.onEdgeLock(edgeFlags);
            }

            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                Log.d(TAG, "onEdgeDragStarted() called with " + "edgeFlags = [" + edgeFlags + "], pointerId = [" + pointerId + "]");
                super.onEdgeDragStarted(edgeFlags, pointerId);
            }

            @Override
            public int getOrderedChildIndex(int index) {
                Log.d(TAG, "getOrderedChildIndex() called with " + "index = [" + index + "]");
                return super.getOrderedChildIndex(index);
            }


            /**
             *  Notice  当想要拖动的子view 有点击事件的时候需要重写
             *  Notice  想在水平上能拖动 getViewHorizontalDragRange 需要return 一个>0的数
             *  Notice  同理 垂直:  getViewVerticalDragRange   也需要return 一个>0的数
             * @param child
             * @return
             */
            @Override
            public int getViewHorizontalDragRange(View child) {
                Log.d(TAG, "getViewHorizontalDragRange() called with " + "child = [" + child + "]");
//                return super.getViewHorizontalDragRange(child);
                return 1;
            }

            /**
             * @param child
             * @return
             */
            @Override
            public int getViewVerticalDragRange(View child) {
                Log.d(TAG, "getViewVerticalDragRange() called with " + "child = [" + child + "]");
//                return super.getViewVerticalDragRange(child);
                return 1;
            }
        });

        /**
         * 订阅边界触发
         * 当用户触摸边界 会触发 onEdgeTouched
         */
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT | ViewDragHelper.EDGE_RIGHT);

    }


    @Override
    public void computeScroll() {
        super.computeScroll();

        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent() called with " + "ev = [" + ev + "]");
      return mDragHelper.shouldInterceptTouchEvent(ev);
//        mDragHelper.shouldInterceptTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent() called with " + "event = [" + event + "]");
        mDragHelper.processTouchEvent(event);
//        return super.onTouchEvent(event);
        return true;
    }

    private View mNormal;
    private View mAutoBack;
    private View mEdgeLeft;
    private View mEdgeRight;
    private View mClickableView;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "onFinishInflate ");
        mNormal = getChildAt(0);
        mAutoBack = getChildAt(1);
        mEdgeLeft = getChildAt(2);
        mEdgeRight = getChildAt(3);
        mClickableView = getChildAt(4);
    }

    private int mHeight;
    private int mWidth;
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d(TAG, "onLayout ");
        mWidth = getWidth();
        mHeight = getHeight();
        originLeft = mAutoBack.getLeft();
        originTop = mAutoBack.getTop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure ");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow ");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow ");
    }
}