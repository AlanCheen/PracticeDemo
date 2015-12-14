package yifeiyuan.practice.yutils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;

/**
 * Created by 程序亦非猿 on 15/10/30.
 */
public class BitmapUtils {


    /**
     * 给bitmap着色
     * @param sourceBitmap
     * @param color  rgb
     * @return
     */
    public static Bitmap changeBitmapColor(@NonNull Bitmap sourceBitmap, @IntegerRes int color) {

        Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0,
                sourceBitmap.getWidth() - 1, sourceBitmap.getHeight() - 1);
        Paint p = new Paint();
        ColorFilter filter = new LightingColorFilter(color, 1);
        p.setColorFilter(filter);

        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, p);
        return resultBitmap;
    }




}
