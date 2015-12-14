package yifeiyuan.practice.practicedemos;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by 程序亦非猿 on 15/12/1.
 * aaaaa
 */
public class HeadView extends FrameLayout {

    public static final String TAG = HeadView.class.getSimpleName();

    public HeadView(Context context) {
        this(context, null);
    }

    public HeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

}