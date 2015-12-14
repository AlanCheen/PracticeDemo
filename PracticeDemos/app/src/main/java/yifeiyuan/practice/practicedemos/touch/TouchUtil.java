package yifeiyuan.practice.practicedemos.touch;

import android.view.MotionEvent;

/**
 * Created by 程序亦非猿 on 15/10/29.
 */
public class TouchUtil {

    public static String getAction(MotionEvent ev) {
        int action = ev.getAction();
        String result = "action_unknow";
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                result = "  action_down";
                break;

            case MotionEvent.ACTION_UP:
                result = "  action_up";
                break;
            case MotionEvent.ACTION_CANCEL:
                result = "  action_cancel";
                break;
            case MotionEvent.ACTION_MOVE:
                result = "  action_move";
                break;
        }
        return result;
    }
}
