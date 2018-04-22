package com.jwding.appbase.component.recycleview;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jwding on 2018/1/8.
 */

public class MRecycleViewAdapter extends RecyclerView.Adapter {

    private List<Item> items;
    private SparseArrayCompat<Class> viewTypeBindHolders = new SparseArrayCompat();
    private Context context;

    public MRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Class vhCls = viewTypeBindHolders.get(viewType);
        if (vhCls == null) {
            for (Item item : items) {
                viewTypeBindHolders.append(item.getLayoutId(), item.getViewHolder());
            }
        }
        vhCls = viewTypeBindHolders.get(viewType);
        if (vhCls == null) {
            return new MViewHolder(new View(context));
        }
        try {
            if (viewType != 0 && context != null) {
                View view = LayoutInflater.from(context).inflate(viewType, parent, false);
                if (view != null) {
                    MViewHolder newViewHolder = (MViewHolder) vhCls.getDeclaredConstructor(new Class[]{View.class}).newInstance(view);
                    newViewHolder.setAdapter(this);
                    return newViewHolder;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MViewHolder(new View(context));
        }
        return new MViewHolder(new View(context));
    }


    private boolean containsViewType(int[] viewTypes, int viewType) {
        if (viewTypes == null) {
            return false;
        }
        for (int vt : viewTypes) {
            if (vt == viewType) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MViewHolder) {
            if (items != null && position < items.size()) {
                ((MViewHolder) holder).bindViewHolder(items.get(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (items != null && position < items.size()) {
            return items.get(position).getLayoutId();
        }
        return 0;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public abstract static class Item {

        public abstract int getLayoutId();

        public abstract Class getViewHolder();

        //默认-1 开启
        public int getDragFlag() {
            return -1;
        }

        //默认0 关闭
        public int getSwipeFlag() {
            return 0;
        }

    }

    public static class MViewHolder extends RecyclerView.ViewHolder {

        protected View mItemView;

        protected MRecycleViewAdapter adapter;

        public Item item;

        public MViewHolder(View itemView) {
            super(itemView);
            this.mItemView = itemView;
        }

        public void bindViewHolder(Item item) {
            this.item = item;
        }

        public void onSwipe(float xd, float yd) {

        }

        public void onSwiped() {
        }

        public void onClick() {
        }

        public void onLongClick() {
        }

        public MViewHolder setAdapter(MRecycleViewAdapter adapter) {
            this.adapter = adapter;
            return this;
        }

    }

}
