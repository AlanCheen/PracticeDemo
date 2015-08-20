package yifeiyuan.practice.practicedemos.itemtouchhelper;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

/**
 * Created by alanchen on 15/7/30.
 */
public class SwipeDismissAdapter extends ItemTouchHelper  {

    public static final String TAG = "SwipeDismissAdapter";
    /**
     * Creates an ItemTouchHelper that will work with the given Callback.
     * <p/>
     * You can attach ItemTouchHelper to a RecyclerView via
     * {@link #attachToRecyclerView(RecyclerView)}. Upon attaching, it will add an item decoration,
     * an onItemTouchListener and a Child attach / detach listener to the RecyclerView.
     *
     * @param callback The Callback which controls the behavior of this touch helper.
     */
    public SwipeDismissAdapter(Callback callback) {
        super(callback);
    }

    @Override
    public void attachToRecyclerView(RecyclerView recyclerView) {
        super.attachToRecyclerView(recyclerView);
        Log.d(TAG, "attachToRecyclerView ");
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        Log.d(TAG, "onDrawOver() called with " + "c = [" + c + "], parent = [" + parent + "], state = [" + state + "]");
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Log.d(TAG, "onDraw() called with " + "c = [" + c + "], parent = [" + parent + "], state = [" + state + "]");
    }

    @Override
    public void onChildViewAttachedToWindow(View view) {
        super.onChildViewAttachedToWindow(view);
        Log.d(TAG, "onChildViewAttachedToWindow ");
    }

    @Override
    public void onChildViewDetachedFromWindow(View view) {
        super.onChildViewDetachedFromWindow(view);
        Log.d(TAG, "onChildViewDetachedFromWindow() called with " + "view = [" + view + "]");
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        Log.d(TAG, "getItemOffsets() called with " + "outRect = [" + outRect + "], view = [" + view + "], parent = [" + parent + "], state = [" + state + "]");
    }

    @Override
    public void startDrag(RecyclerView.ViewHolder viewHolder) {
        super.startDrag(viewHolder);
        Log.d(TAG, "startDrag() called with " + "viewHolder = [" + viewHolder + "]");
    }

    @Override
    public void startSwipe(RecyclerView.ViewHolder viewHolder) {
        super.startSwipe(viewHolder);
        Log.d(TAG, "startSwipe() called with " + "viewHolder = [" + viewHolder + "]");
    }
}
