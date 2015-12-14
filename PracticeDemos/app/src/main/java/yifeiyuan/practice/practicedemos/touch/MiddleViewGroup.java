package yifeiyuan.practice.practicedemos.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by 程序亦非猿 on 15/10/29.
 * aaaaa
 */
public class MiddleViewGroup extends FrameLayout {

    public static final String TAG = MiddleViewGroup.class.getSimpleName();

    public MiddleViewGroup(Context context) {
        this(context, null);
    }

    public MiddleViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MiddleViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.d(TouchActivity.TAG, "MiddleViewGroup dispatchTouchEvent() "+super.dispatchTouchEvent(ev)+"  called with: " + "ev = [" + TouchUtil.getAction(ev) + "]");
        Log.d(TouchActivity.TAG, "MiddleViewGroup dispatchTouchEvent() "+"false"+"  called with: " + "ev = [" + TouchUtil.getAction(ev) + "]");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        Log.d(TouchActivity.TAG, "MiddleViewGroup onInterceptTouchEvent() "+super.onInterceptTouchEvent(ev)+"  called with: " + "ev = [" + TouchUtil.getAction(ev) + "]");
        Log.d(TouchActivity.TAG, "MiddleViewGroup onInterceptTouchEvent() false called with: " + "ev = [" + TouchUtil.getAction(ev) + "]");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.d(TouchActivity.TAG, "MiddleViewGroup onTouchEvent() "+super.onTouchEvent(event)+"  called with: " + "ev = [" + TouchUtil.getAction(event) + "]");
        Log.d(TouchActivity.TAG, "MiddleViewGroup onTouchEvent() false  called with: " + "ev = [" + TouchUtil.getAction(event) + "]");
        return super.onTouchEvent(event);
    }

    private void init() {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

}