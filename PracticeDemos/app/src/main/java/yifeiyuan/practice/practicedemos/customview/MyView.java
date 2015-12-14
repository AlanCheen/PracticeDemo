package yifeiyuan.practice.practicedemos.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by alanchen on 15/10/15.
 */
public class MyView extends View {

    public static final String TAG = MyView.class.getSimpleName();

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

}