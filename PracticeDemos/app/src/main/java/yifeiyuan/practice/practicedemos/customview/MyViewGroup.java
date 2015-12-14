package yifeiyuan.practice.practicedemos.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by alanchen on 15/10/15.
 */
public class MyViewGroup extends ViewGroup {

    public static final String TAG = MyViewGroup.class.getSimpleName();

    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //        int unspecified = MeasureSpec.UNSPECIFIED;//0
//        int atMost = MeasureSpec.AT_MOST;//-2147483648
//        int exactly = MeasureSpec.EXACTLY;//1073741824
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 0 0
//        Log.d(TAG, "onMeasure: height" + getMeasuredHeight() + ";width:" + getMeasuredWidth());
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Log.d(TAG, "onMeasure() called with: " + "widthMeasureSpec = [" + widthMeasureSpec + "], heightMeasureSpec = [" + heightMeasureSpec + "]");
//        logModeAndSize(widthMeasureSpec);
//        logModeAndSize(heightMeasureSpec);
//        Log.d(TAG, "onMeasure: height" + getMeasuredHeight() + ";width:" + getMeasuredWidth());

        int height = 0;//group的计算高度
        int width = 0;//宽度

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int childcount = getChildCount();
        for (int i = 0; i < childcount; i++) {
            View child = getChildAt(i);
            //gone 的就无视掉
            if (child.getVisibility() == GONE) {
                continue;
            }
            LayoutParams lp = child.getLayoutParams();
            int widthSpec = 0;
            int heightSpec = 0;

            //根据LayoutParams,给子View生成MeasureSpec规则
            if (lp.width == LayoutParams.WRAP_CONTENT) {
                widthSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.AT_MOST);
            } else if (lp.width == LayoutParams.MATCH_PARENT) {
                widthSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
            } else {
                //其实xml里不会出现这样的情况
                widthSpec = MeasureSpec.makeMeasureSpec(lp.width, MeasureSpec.EXACTLY);
            }

            if (lp.height == LayoutParams.WRAP_CONTENT) {
                heightSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.AT_MOST);
            } else if (lp.height == LayoutParams.MATCH_PARENT) {
                heightSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
            } else {
                heightSpec = MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.EXACTLY);
            }

            child.measure(widthSpec, heightSpec);
            //把所有的子View的高度加起来,就是高度
            height += child.getMeasuredHeight();
            // 拿子View中的最大宽度当自己的宽度,保证所有子View能够显示全
            width = Math.max(width, child.getMeasuredWidth());
            Log.d(TAG, "onMeasure: i:" + i + ",width:" + child.getMeasuredWidth() + ",height:" + child.getMeasuredHeight());
        }

        // 再根据父view给自己的spec,处理自己的宽高
        if (MeasureSpec.EXACTLY == widthMode) {
            width = widthSize;
        }else if (MeasureSpec.AT_MOST == widthMode) {
            width = Math.min(width, widthSize);
        }

        if (MeasureSpec.EXACTLY == heightMode) {
            height = heightSize;
        }else if (MeasureSpec.AT_MOST == heightMode) {
            height = Math.min(height, heightSize);
        }

        //一定要记得调用
        setMeasuredDimension(width, height);

        Log.d(TAG, "onMeasure: height" + getMeasuredHeight() + ";width:" + getMeasuredWidth());

    }


    private void logModeAndSize(int measureSpec) {
        switch (MeasureSpec.getMode(measureSpec)) {
            case MeasureSpec.UNSPECIFIED:
                Log.d(TAG, "UNSPECIFIED: " + MeasureSpec.getSize(measureSpec));
                break;
            case MeasureSpec.AT_MOST:
                Log.d(TAG, "AT_MOST: " + MeasureSpec.getSize(measureSpec));
                break;
            case MeasureSpec.EXACTLY:
                Log.d(TAG, "EXACTLY: " + MeasureSpec.getSize(measureSpec));
                break;
        }
    }

    private int measureHeight(int heightMeasureSpec) {

        int result = 100;
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result, size);
                break;
            case MeasureSpec.UNSPECIFIED:
                //看情况而定
                // 这个是从RecyclerView的onMeasure里看来的.
                //result = ViewCompat.getMinimumHeight(this);
                break;
        }
        return result;
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 100;// 实际上需要自己计算
            if (mode == MeasureSpec.AT_MOST) {
                //至多模式,别超过了
                result = Math.min(result, size);
            }
        }
        return result;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childcount = getChildCount();
        int height = 0;
        for (int i = 0; i < childcount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            child.layout(l,t+height,l+child.getMeasuredWidth(),t+height+child.getMeasuredHeight());
            height += child.getMeasuredHeight();
            Log.d(TAG, "onLayout: i:" + i + ",width:" + child.getMeasuredWidth() + ",height:" + child.getMeasuredHeight());
        }
    }

    private void init() {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        Log.d(TAG, "onFinishInflate() called with: " + "");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        Log.d(TAG, "onAttachedToWindow() called with: " + "");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        Log.d(TAG, "onDetachedFromWindow() called with: " + "");
    }
}