package yifeiyuan.practice.practicedemos.customview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseFragment;

public class CustomViewGroupFragment extends BaseFragment {

    public CustomViewGroupFragment() {
    }

    public static CustomViewGroupFragment newInstance() {
        CustomViewGroupFragment fragment = new CustomViewGroupFragment();
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
        View view = inflater.inflate(R.layout.fragment_custom_view_group, container, false);
        view.findViewById(R.id.tv1).setOnClickListener(v-> Toast.makeText(getActivity(),"TV1111",Toast.LENGTH_SHORT).show());
        view.findViewById(R.id.tv2).setOnClickListener(v-> Toast.makeText(getActivity(),"TV2222",Toast.LENGTH_SHORT).show());
        view.findViewById(R.id.tv3).setOnClickListener(v-> Toast.makeText(getActivity(),"TV3333",Toast.LENGTH_SHORT).show());
        return view;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
