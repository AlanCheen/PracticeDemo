package yifeiyuan.practice.practicedemos.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseFragment;

/**
 */
public class HoriListFragment extends BaseFragment {

    @InjectView(R.id.rv_list)
    RecyclerView mRvList;

    private ListAdapter mAdapter;
    private List<String> mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_single_rv, container, false);
        ButterKnife.inject(this, mRootView);
        return mRootView;
    }

    protected void init(Bundle savedInstanceState) {

        mData = new ArrayList<>();
        mAdapter = new ListAdapter(getActivity(), mData, (view, position) -> {
            Snackbar.make(view, "点击了position:" + position, Snackbar.LENGTH_SHORT)
                    .setAction("删除该项目",v-> {
                        mData.remove(position);
                        mAdapter.notifyItemRemoved(position);
                    })
                    .show();
        });

        for (int i = 0; i < 20; i++) {
            mData.add("~~" + i);
        }

        mRvList.setItemAnimator(new DefaultItemAnimator());
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public static class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements View.OnClickListener {

        private Context mContext;
        private List<String> mDatas;

        public ListAdapter(Context context, List<String> data,Callback mCallback) {
            mContext = context;
            mDatas = data;
            this.mCallback = mCallback;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img, parent, false);
            view.setOnClickListener(this);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            position = holder.getAdapterPosition();
            if (position == 0) {
                holder.mTvSf.setText("司令");
            }else if (position == 1) {
                holder.mTvSf.setText("军长");
            }else if (position == 2) {
                holder.mTvSf.setText("旅长");
            }else{
                holder.mTvSf.setText("小兵");
            }

            holder.itemView.setOnClickListener(v-> {
                mCallback.onItemClick(v, holder.getAdapterPosition());
            });
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        @Override
        public void onClick(View v) {
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            @InjectView(R.id.tv_sf)
            public TextView mTvSf;
            public ViewHolder(View view) {
                super(view);
                ButterKnife.inject(this, view);
            }
        }
        private Callback mCallback ;
        public interface Callback {
            void onItemClick(View view, int position);
        }
    }
}
