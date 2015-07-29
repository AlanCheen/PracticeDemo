package yifeiyuan.practice.practicedemos.materialsupport;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class GridFragment extends BaseFragment {

    @InjectView(R.id.rv_grid)
    RecyclerView mRvGrid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_grid, container, false);
        ButterKnife.inject(this, mRootView);
        return mRootView;
    }

    private GridAdapter mAdapter;
    private List<String> mData;

    @Override
    protected void init(Bundle savedInstanceState) {
        mData = new ArrayList<>();
        mAdapter = new GridAdapter(getActivity(),mData);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //Notice SpanSizeLookup可以控制每个 cell的span
                int temp = position%3;
//                return position%3==0?1:gridLayoutManager.getSpanCount();
                return temp+1;
            }
        });
        mRvGrid.setLayoutManager(gridLayoutManager);
        mRvGrid.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public static class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder>{

        private Context mContext;
        private List<String> mDatas;
        public GridAdapter(Context context,List<String> data){
            mContext = context;
            mDatas= data;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_grid_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
//            return mDatas.size();
            return 28;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{

            public ViewHolder(View view){
                super(view);
            }
        }

    }
}
