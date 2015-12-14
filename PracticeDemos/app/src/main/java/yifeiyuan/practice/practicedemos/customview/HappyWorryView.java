package yifeiyuan.practice.practicedemos.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Created by 程序亦非猿 on 15/11/18.
 */
public class HappyWorryView extends CheckBox {

    public static final String TAG = "HappyWorryView";

    public HappyWorryView(Context context) {
        this(context, null);
    }

    public HappyWorryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HappyWorryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //自己的宽高
    private int mWidth;
    private int mHeight;

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();
        mHeight = getHeight();
    }

}