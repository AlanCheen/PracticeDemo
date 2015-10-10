package yifeiyuan.practice.practicedemos.materialsupport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.AboutMeActivity;
import yifeiyuan.practice.practicedemos.base.BaseFragment;

/**
 */
public class ListFragment extends BaseFragment {

    @InjectView(R.id.rv_list)
    RecyclerView mRvList;

    @InjectView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private ListAdapter mAdapter;
    private List<String> mData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.inject(this, mRootView);
        return mRootView;
    }

    protected void init(Bundle savedInstanceState) {

        mData = new ArrayList<>();
        mAdapter = new ListAdapter(getActivity(),mData);

        for (int i = 0; i < 20; i++) {
            mData.add("~~"+i);
        }

        mRvList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRvList.setAdapter(mAdapter);
        mSwipeRefresh.setColorSchemeResources(R.color.primary, R.color.accent, R.color.primary_dark);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (null != mSwipeRefresh) {
                            mSwipeRefresh.setRefreshing(false);
                        }
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public static class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements View.OnClickListener{

//        private final TypedValue mTypedValue = new TypedValue();
//        private int mBackground;
        private Context mContext;
        private List<String>mDatas;
        public ListAdapter(Context context,List<String> data){
            mContext = context;
            mDatas= data;
            //android.graphics.drawable.StateListDrawable cannot be cast to android.support.v7.widget.RoundRectDrawableWithShadow
//            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
//            mBackground = mTypedValue.resourceId;

        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_list_item, parent, false);
//            view.setBackgroundResource(mBackground);
            view.setOnClickListener(this);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
//            return 20;
        }

        @Override
        public void onClick(View v) {
            mContext.startActivity(new Intent(mContext, AboutMeActivity.class));
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{

            public ViewHolder(View view){
                super(view);
            }
        }

    }
}
