package yifeiyuan.practice.yutils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by alanchen on 15/9/8.
 */
public class ViewUtils {

    public static float getElevation(Context context, float degree) {
        return context.getResources().getDisplayMetrics().density * degree;
    }

    public static float getElevation(Context context, int degree) {
        return context.getResources().getDisplayMetrics().density * degree;
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;

        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    public static int getToolbarHeight(Context context) {
        TypedValue typedValue = new TypedValue();

        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }

        return 0;
    }

}
