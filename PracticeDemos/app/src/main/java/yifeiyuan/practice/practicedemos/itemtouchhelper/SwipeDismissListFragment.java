package yifeiyuan.practice.practicedemos.itemtouchhelper;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseFragment;

/**
 * todo swipedismiss
 */
public class SwipeDismissListFragment extends BaseFragment {

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
            mData.add("情侣~~"+i);
        }

        mRvList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRvList.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mData.remove(position);
                mAdapter.notifyItemRemoved(position);
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }

        }).attachToRecyclerView(mRvList);

        mSwipeRefresh.setEnabled(false);

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

    public static class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

        private Context mContext;
        private List<String>mDatas;
        public ListAdapter(Context context,List<String> data){
            mContext = context;
            mDatas= data;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
//            return 20;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{

            TextView text;
            public ViewHolder(View view){
                super(view);
                text = (TextView) view.findViewById(R.id.iv_item);
            }
        }

    }
}
