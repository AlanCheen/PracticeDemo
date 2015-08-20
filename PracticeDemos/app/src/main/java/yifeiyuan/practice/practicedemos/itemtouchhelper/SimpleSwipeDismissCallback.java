package yifeiyuan.practice.practicedemos.itemtouchhelper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by alanchen on 15/8/20.
 *
 * //todo
 *
 */
public class SimpleSwipeDismissCallback extends ItemTouchHelper.SimpleCallback{


    public SimpleSwipeDismissCallback(){
        this(0,1);
    }

    public SimpleSwipeDismissCallback(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
