package yifeiyuan.practice.practicedemos.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * canvas基础
 *
 * 通常情况下(0,0)是我们手机的左上角
 * (0,0)
 * --------------------------> X
 * |
 * |
 * |
 * |
 * |
 * |
 * |
 * ⬇Y️
 */
public class CanvasActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CanvasView view = new CanvasView(CanvasActivity.this);
//        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
//        lp.leftMargin = 200;
//        lp.topMargin = 400;
//        view.setLayoutParams(lp);
        setContentView(view);
    }


    class CanvasView extends View {

        private RectF mArcRectF;
        private RectF mOvalRectF;

        public CanvasView(Context context) {
            super(context);
            init();
        }

        Paint mLinePaint;
        Paint mArcPaint;
        Paint mRectFPaint;
        Paint mCirclePaint;
        Paint mPointPaint;
        Paint mOvalPaint;

        private void init() {


            mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPointPaint.setColor(Color.parseColor("#000000"));
            mPointPaint.setStrokeWidth(15);

            mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mLinePaint.setColor(Color.parseColor("#0000ff"));
            mLinePaint.setStrokeWidth(5);// stroke宽度

            mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mArcPaint.setColor(Color.parseColor("#00ff00"));

            mRectFPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mRectFPaint.setColor(Color.parseColor("#eeff00"));


            mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mCirclePaint.setColor(Color.parseColor("#443322"));

            mOvalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mOvalPaint.setColor(Color.parseColor("#88ddff"));

            //RectF rectangle 矩形的意思,根据这四个点即可确定一个矩形
            mArcRectF = new RectF(0, 30, 267, 222);
            mOvalRectF = new RectF(0,0,300,200);
        }

        @Override
        protected void onDraw(Canvas canvas) {

            canvas.save();
            //X轴平移 10个像素
            canvas.translate(10, 0);
            //填充整个canvas 画颜色 Notice 前面的translate对 drawARGB没有作用!!
            canvas.drawARGB(111, 156, 11, 44);

            // 画一个点
//            canvas.drawPoint(10, 10, mPointPaint);

            //X轴平移 50个像素
            canvas.translate(50, 0);
            //画直线 Notice 前面的translate(10,0) 以及 translate(50,0) 都有影响!
            //Notice 也即 这里的起点虽然为(0,0),但是相对于最初始的情况是(60,0)!!
            canvas.drawLine(0, 0, 0, 300, mLinePaint);
            canvas.drawLine(0, 300, 300, 300, mLinePaint);
            //绘制一个矩形
            canvas.drawRect(mArcRectF, mRectFPaint);
            canvas.drawRect(1, 2, 3, 4, mRectFPaint);

            //根据一个矩形绘制一个弧形
            //矩阵的中心为圆心,最短边为直径(内切圆)
            /**
             * Notice  圆心 弧度示意图:
             *
             *            270°(-90°)
             *            |
             *            |
             *            |
             * 180°       |
             * ___________|____________ 0°
             *           /|\a
             *          /b| \
             *         /  |  \
             *        /   |   \
             *       /    90°(-270°)
             *
             *
             *  startAngle 表示初始位置是多少,从0 开始(a)
             *  sweepAngle 表示旋转了多少度(b)   Notice 超过360°,则为一整个圆.
             *  假设 a 为90 b 为22 那么最终效果是 扇形的一条变在 90°处,了一条边在112°处
             *
             *  useCenter true 是否使用圆心
             *
             *  http://ww3.sinaimg.cn/large/98900c07gw1ewtt438wdej20e10c8aa7.jpg
             */
            // 使用中心 则为弧形
            canvas.drawArc(mArcRectF, 90, 22, true, mArcPaint);
//            // 不使用中心
            canvas.drawArc(mArcRectF, 122, 33, false, mArcPaint);
//
//            //只描边 默认fill(填充)
            mArcPaint.setStyle(Paint.Style.STROKE);
            canvas.drawArc(mArcRectF, 166, 44, true, mArcPaint);
//
            canvas.translate(305, 0);
            //超过360°,则为一整个圆,即使useCenter为false
            mArcPaint.setStrokeWidth(20);
            mArcPaint.setStyle(Paint.Style.FILL_AND_STROKE);//什么鬼?貌似没什么效果
            canvas.drawArc(mArcRectF, 22, 393, false, mArcPaint);

            canvas.translate(330, 100);
            //画圆 指定圆心,半径 便可画一个圆
            mCirclePaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(50, 50, 50, mCirclePaint);
            mCirclePaint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(0, 0, 100, 100, mCirclePaint);

            canvas.restore();
            canvas.translate(0, 300);
            canvas.drawOval(mOvalRectF,mOvalPaint);

        }
    }
}
