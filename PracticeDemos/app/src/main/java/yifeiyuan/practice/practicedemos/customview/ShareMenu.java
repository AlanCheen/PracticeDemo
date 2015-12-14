package yifeiyuan.practice.practicedemos.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import yifeiyuan.practice.practicedemos.R;

/**
 * Created by 程序亦非猿 on 15/10/27.
 */
public class ShareMenu extends RelativeLayout{


    public static final int WEIBO = 0;
    public static final int QZONE = 1;
    public static final int QQ = 2;
    public static final int WEIXIN = 3;
    public static final int WEIXIN_CIRCLE = 4;
    public static final int MESSAGE = 5;
    public static final int FACEBOOK = 6;
    public static final int TWITTER = 7;

//    @IntDef(value = {WEIBO,QZONE,QQ,WEIXIN,WEIXIN_CIRCLE,MESSAGE,FACEBOOK,TWITTER})
//    public @interface ShareType{}

    private Context mContext;
    private ActionListener mActionListener;
    private List<View> mViews;

    private int mY;
    public ShareMenu(Context context) {
        super(context);
    }

    public ShareMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShareMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ShareMenu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        mY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, mContext.getResources().getDisplayMetrics());
        mActionListener = new ActionListener();
        mViews = new ArrayList<>();

        addActionViews();
    }

    /*添加菜单view*/
    private void addActionViews() {

        setGravity(CENTER_HORIZONTAL);

        //固定大小
        Drawable wechat = getResources().getDrawable(R.mipmap.bg_share_weixin_n);
        int height = wechat.getIntrinsicHeight();
        int width = wechat.getIntrinsicWidth();

        LayoutParams lp = new LayoutParams(width, height);
        lp.addRule(ALIGN_PARENT_BOTTOM, TRUE);//居于底部

        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(lp);
        imageView.setTag(MESSAGE);
        imageView.setImageResource(R.drawable.bg_share_duanxin);
        imageView.setOnClickListener(mActionListener);
        addView(imageView);
        mViews.add(imageView);

        imageView = new ImageView(mContext);
        imageView.setLayoutParams(lp);
        imageView.setTag(WEIBO);
        imageView.setImageResource(R.drawable.bg_share_weibo);
        imageView.setOnClickListener(mActionListener);
        addView(imageView);
        mViews.add(imageView);

        imageView = new ImageView(mContext);
        imageView.setLayoutParams(lp);
        imageView.setTag(QZONE);
        imageView.setImageResource(R.drawable.bg_share_qzone);
        imageView.setOnClickListener(mActionListener);
        addView(imageView);
        mViews.add(imageView);

        imageView = new ImageView(mContext);
        imageView.setLayoutParams(lp);
        imageView.setTag(QQ);
        imageView.setImageResource(R.drawable.bg_share_qq);
        imageView.setOnClickListener(mActionListener);
        addView(imageView);
        mViews.add(imageView);

        imageView = new ImageView(mContext);
        imageView.setLayoutParams(lp);
        imageView.setTag(WEIXIN_CIRCLE);
        imageView.setImageResource(R.drawable.bg_share_pyq);
        imageView.setOnClickListener(mActionListener);
        addView(imageView);
        mViews.add(imageView);

        imageView = new ImageView(mContext);
        imageView.setLayoutParams(lp);
        imageView.setTag(WEIXIN);
        imageView.setImageResource(R.drawable.bg_share_weixin);
        imageView.setOnClickListener(mActionListener);
        addView(imageView);
        mViews.add(imageView);

//        imageView = new ImageView(mContext);
//        imageView.setLayoutParams(lp);
//        imageView.setTag(FACEBOOK);
//        imageView.setImageResource(R.drawable.selector_login_fb);
//        imageView.setOnClickListener(mActionListener);
//        addView(imageView);
//        mViews.add(imageView);
//
//        imageView = new ImageView(mContext);
//        imageView.setLayoutParams(lp);
//        imageView.setTag(TWITTER);
//        imageView.setImageResource(R.drawable.selector_login_twitter);
//        imageView.setOnClickListener(mActionListener);
//        addView(imageView);
//        mViews.add(imageView);

    }


    /**
     * 打开或者关闭菜单
     */
    public void toggleMenu(){

        if (isEnabled()){

            if (isMenuOpened){
                closeMenu();
                isMenuOpened = false;
            }else{
                openMenu();
                isMenuOpened = true;
            }

            if (null != mOpenEventListener) {
                mOpenEventListener.onMenuOpenCloseEvent(isMenuOpened);
            }
        }

    }

    /**
     *菜单是否打开
     */
    private boolean isMenuOpened = false;
    private ObjectAnimator[] mObjectAnimators;

    private void openMenu(){

        AnimatorSet set = new AnimatorSet();
        set.setDuration(200);
        int size = mViews.size();
        mObjectAnimators = new ObjectAnimator[size];
        for (int i = 0; i < size; i++) {
            View view = mViews.get(i);
            mObjectAnimators[i]= makeOpenAnimator(view, i);
        }

        set.playTogether(mObjectAnimators);
        set.addListener(mAnimatorListener);
        set.start();
    }

    private void closeMenu(){

        AnimatorSet set = new AnimatorSet();
        set.setDuration(200);
        int size = mViews.size();
        mObjectAnimators = new ObjectAnimator[size];
        for (int i = 0; i < size; i++) {
            View view = mViews.get(i);
            mObjectAnimators[i]= makeCloseAnimator(view);
        }

        set.playTogether(mObjectAnimators);
        set.addListener(mAnimatorListener);
        set.start();
    }

    private ObjectAnimator makeCloseAnimator(View view){
        ObjectAnimator oa = ObjectAnimator.ofFloat(view,View.TRANSLATION_Y,0);
        oa.setDuration(200);
        return oa;
    }

    private ObjectAnimator makeOpenAnimator(View view, int position){
        ObjectAnimator oa = ObjectAnimator.ofFloat(view,View.TRANSLATION_Y,-mY*(position+1));
        oa.setDuration(200);
        return oa;
    }

    private AnimatorListenerAdapter mAnimatorListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationStart(Animator animation) {
            setEnabled(false);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            setEnabled(true);
        }
    };

    private class ActionListener implements OnClickListener{

        @Override
        public void onClick(View v) {
            if (null != mOnActionClickListener) {
                mOnActionClickListener.onActionClickListener((Integer) v.getTag());
            }
            toggleMenu();
        }
    }

    private OnActionClickListener mOnActionClickListener;

    public void setOnActionClickListener(OnActionClickListener OnActionClickListener) {
        mOnActionClickListener = OnActionClickListener;
    }
    public interface OnActionClickListener{
        void onActionClickListener(int actionType);
    }

    private OnMenuOpenOrClosedListener mOpenEventListener;
    public void setOnMenuOpenOrClosedListener(OnMenuOpenOrClosedListener OnMenuOpenOrClosedListener) {
        mOpenEventListener = OnMenuOpenOrClosedListener;
    }
    public interface OnMenuOpenOrClosedListener{
        void onMenuOpenCloseEvent(boolean opened);
    }

    /**
     * @return true  if menu was opened
     */
    public boolean isMenuOpened(){
        return isMenuOpened;
    }

}
