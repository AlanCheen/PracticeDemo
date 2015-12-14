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
public class TopViewGroup extends FrameLayout {

    public static final String TAG = TopViewGroup.class.getSimpleName();

    public TopViewGroup(Context context) {
        this(context, null);
    }

    public TopViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.d(TouchActivity.TAG, "TopViewGroup dispatchTouchEvent() "+super.dispatchTouchEvent(ev)+"  called with: " + "ev = [" + TouchUtil.getAction(ev) + "]");
        Log.d(TouchActivity.TAG, "TopViewGroup dispatchTouchEvent() true  called with: " + "ev = [" + TouchUtil.getAction(ev) + "]");
//        return super.dispatchTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        Log.d(TouchActivity.TAG, "TopViewGroup onInterceptTouchEvent() "+super.onInterceptTouchEvent(ev)+"  called with: " + "ev = [" + TouchUtil.getAction(ev) + "]");
        Log.d(TouchActivity.TAG, "TopViewGroup onInterceptTouchEvent() false  called with: " + "ev = [" + TouchUtil.getAction(ev) + "]");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.d(TouchActivity.TAG, "TopViewGroup onTouchEvent() "+super.onTouchEvent(event)+"  called with: " + "ev = [" + TouchUtil.getAction(event) + "]");
        Log.d(TouchActivity.TAG, "TopViewGroup onTouchEvent() false  called with: " + "ev = [" + TouchUtil.getAction(event) + "]");
        return super.onTouchEvent(event);
    }

    private void init() {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

}