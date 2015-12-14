package yifeiyuan.practice.practicedemos.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 程序亦非猿 on 15/11/19.
 * aaaaa
 */
public class PathView extends View {

    public static final String TAG = PathView.class.getSimpleName();
    private Paint mPaint;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0xff00ff);
        mPaint.setStrokeWidth(3);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPath(canvas);
    }

    private void drawPath(Canvas canvas) {

        int width = getWidth();
        int height = getHeight();

        Path path = new Path();

        path.moveTo(0, 0);
        path.lineTo(20, 20);
        path.lineTo(30, 30);
        path.rLineTo(50,50);
        path.quadTo(100,100,200,200);

        canvas.drawPath(path,mPaint);
    }

}