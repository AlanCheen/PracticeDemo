package yifeiyuan.practice.practicedemos.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 */
public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(savedInstanceState);
    }
    protected  abstract void init(Bundle savedInstanceState);
}
