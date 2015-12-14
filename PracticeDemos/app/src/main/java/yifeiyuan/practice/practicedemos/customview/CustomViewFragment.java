package yifeiyuan.practice.practicedemos.customview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomViewFragment extends BaseFragment {

    public CustomViewFragment() {
        // Required empty public constructor
    }

    public static CustomViewFragment newInstance() {
        CustomViewFragment fragment = new CustomViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_view, container, false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
