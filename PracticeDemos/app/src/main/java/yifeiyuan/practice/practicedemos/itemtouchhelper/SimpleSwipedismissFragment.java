package yifeiyuan.practice.practicedemos.itemtouchhelper;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseFragment;

/**
 */
public class SimpleSwipedismissFragment extends BaseFragment {

    @InjectView(R.id.rv)
    RecyclerView mRvList;
    private ListAdapter mAdapter;
    private List<String> mData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.simple_rv, container, false);
        ButterKnife.inject(this, mRootView);
        return mRootView;
    }

    protected void init(Bundle savedInstanceState) {

        mData = new ArrayList<>();
        mAdapter = new ListAdapter(getActivity(),mData);

        for (int i = 0; i < 20; i++) {
            mData.add("滑动拆散情侣:"+i+" 号");
        }

        mRvList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRvList.setAdapter(mAdapter);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Log.d(TAG, "onSwiped() called with " + "viewHolder = [" + viewHolder + "], direction = [" + direction + "]");
                int position = viewHolder.getAdapterPosition();
                mData.remove(position);
                mAdapter.notifyItemRemoved(position);
                Toast.makeText(getActivity(), "拆散的position:"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Log.d(TAG, "onMove() called with " + "recyclerView = [" + recyclerView + "], viewHolder = [" + viewHolder + "], target = [" + target + "]");
                return false;
            }

        }).attachToRecyclerView(mRvList);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public static class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements View.OnClickListener{
        private Context mContext;
        private List<String>mDatas;
        public ListAdapter(Context context,List<String> data){
            mDatas= data;
            mContext = context;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText(mDatas.get(position));

            holder.text.setTag(position);
            holder.text.setOnClickListener(this);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        @Override
        public void onClick(View v) {
            TextView tv = (TextView) v;
            Toast.makeText(mContext, tv.getText()+";;"+tv.getTag(), Toast.LENGTH_SHORT).show();
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
