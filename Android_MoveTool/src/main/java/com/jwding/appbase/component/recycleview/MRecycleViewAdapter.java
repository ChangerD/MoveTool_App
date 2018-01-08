package com.jwding.appbase.component.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by 25340 on 2018/1/8.
 */

public class MRecycleViewAdapter extends RecyclerView.Adapter {

    private List<Item> items;
    private List<Class> viewHolders;
    private Context context;

    public MRecycleViewAdapter(Context context, List<Item> items, List<Class> viewHolders) {
        this.items = items;
        this.context = context;
        this.viewHolders=viewHolders;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewHolders != null) {
            for (Class mViewHolderCls : viewHolders) {
                int mViewType=0;
                int mLayoutId=0;
                try {
                    mViewType=mViewHolderCls.getField("viewType").getInt(null);
                    mLayoutId=mViewHolderCls.getField("layoutId").getInt(null);
                }  catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                try {
                    if (mViewType == viewType) {
                        try {
                            if(mLayoutId!=0&&context!=null){
                                View view = LayoutInflater.from(context).inflate(mLayoutId,null,false);
                                if(view!=null){
                                    MViewHolder newViewHolder = (MViewHolder) mViewHolderCls.getDeclaredConstructor(new Class[]{View.class}).newInstance(view);
                                    return newViewHolder;
                                }
                            }
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
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
            return items.get(position).getViewType();
        }
        return 0;
    }
}
