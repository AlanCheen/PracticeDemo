package yifeiyuan.practice.yutils;

import android.content.Context;

/**
 * Created by alanchen on 15/8/12.
 */
public class PixelUtils {

    /**
     *  根据手机的分辨率从dp 的单位 转成为px(像素)
     */
    public static int dip2px(Context context, float dpValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     *  根据手机的分辨率从px(像素) 的单位 转成为dp
     */
    public static int px2dip(Context context, float pxValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(Context context,float pxValue)
    {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context,float spValue)
    {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue * scale + 0.5f);
    }
}
