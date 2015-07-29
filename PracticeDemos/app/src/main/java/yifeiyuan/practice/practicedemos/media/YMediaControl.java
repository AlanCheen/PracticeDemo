package yifeiyuan.practice.practicedemos.media;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.MediaController;

/**
 * Created by alanchen on 15/7/27.
 */
public class YMediaControl extends MediaController {

    public YMediaControl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public YMediaControl(Context context, boolean useFastForward) {
        super(context, useFastForward);
    }

    public YMediaControl(Context context) {
        super(context);
    }

}
