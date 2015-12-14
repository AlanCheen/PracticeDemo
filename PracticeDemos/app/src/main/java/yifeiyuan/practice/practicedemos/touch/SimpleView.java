package yifeiyuan.practice.practicedemos.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 程序亦非猿 on 15/10/29.
 * aaaaa
 */
public class SimpleView extends View {

    public static final String TAG = SimpleView.class.getSimpleName();

    public SimpleView(Context context) {
        this(context, null);
    }

    public SimpleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.d(TouchActivity.TAG, "SimpleView dispatchTouchEvent() "+super.dispatchTouchEvent(ev)+"  called with: " + "ev = [" + TouchUtil.getAction(ev) + "]");
        Log.d(TouchActivity.TAG, "SimpleView dispatchTouchEvent() false  called with: " + "ev = [" + TouchUtil.getAction(ev) + "]");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TouchActivity.TAG, "SimpleView onTouchEvent() false  called with: " + "ev = [" + TouchUtil.getAction(event) + "]");
        return super.onTouchEvent(event);
    }

    private void init() {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

}