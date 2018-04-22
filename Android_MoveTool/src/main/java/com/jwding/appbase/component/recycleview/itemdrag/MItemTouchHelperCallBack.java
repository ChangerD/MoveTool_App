package com.jwding.appbase.component.recycleview.itemdrag;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.jwding.appbase.component.recycleview.MRecycleViewAdapter;

import java.util.Collections;

/**
 * Created by Jwding on 2018/1/9.
 */

public class MItemTouchHelperCallBack extends ItemTouchHelper.Callback {

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = -1;
        int swipeFlags = -1;
        MRecycleViewAdapter.Item item;
        if (viewHolder != null && viewHolder instanceof MRecycleViewAdapter.MViewHolder) {
            item = ((MRecycleViewAdapter.MViewHolder) viewHolder).item;
            if (item != null) {
                dragFlags = item.getDragFlag();
                swipeFlags = item.getSwipeFlag();
            }
        }
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager || recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            if (dragFlags < 0)
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            if (swipeFlags < 0)
                swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            ;
        } else {
            if (dragFlags < 0)
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            if (swipeFlags < 0)
                swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            ;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }


    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
        int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
        MRecycleViewAdapter mRecycleViewAdapter = null;
        if (recyclerView.getAdapter() != null && recyclerView.getAdapter() instanceof MRecycleViewAdapter) {
            mRecycleViewAdapter = (MRecycleViewAdapter) recyclerView.getAdapter();
        } else {
            return false;
        }
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mRecycleViewAdapter.getItems(), i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mRecycleViewAdapter.getItems(), i, i - 1);
            }
        }
        mRecycleViewAdapter.notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if(viewHolder!=null&&viewHolder instanceof MRecycleViewAdapter.MViewHolder){
            ((MRecycleViewAdapter.MViewHolder) viewHolder).onSwiped();
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState ==ItemTouchHelper.ACTION_STATE_SWIPE){
            if(viewHolder!=null&&viewHolder instanceof MRecycleViewAdapter.MViewHolder){
                ((MRecycleViewAdapter.MViewHolder) viewHolder).onSwipe(dX,dY);
                Log.d("jwding",dX+"--"+dY);
            }
        }else {
            //拖拽状态下不做改变，需要调用父类的方法
            super.onChildDraw(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive);
        }
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        //拖拽结束标志
        if (actionState ==ItemTouchHelper.ACTION_STATE_DRAG) {
            //获取系统震动服务
            Vibrator vib = (Vibrator) viewHolder.itemView.getContext().getSystemService(Service.VIBRATOR_SERVICE);
            //震动70毫秒
            vib.vibrate(70);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
}
