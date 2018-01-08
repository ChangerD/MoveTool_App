package com.jwding.appbase.component.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by 25340 on 2018/1/8.
 */

public class MRecycleViewAdapter extends RecyclerView.Adapter {

    private List<Item> items;
    private List<MViewHolder> viewHolders;
    private Context context;

    public MRecycleViewAdapter(Context context, List<Item> items, List<MViewHolder> viewHolders) {
        this.items = items;
        this.context = context;
        this.viewHolders=viewHolders;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewHolders != null) {
            for (MViewHolder mViewHolder : viewHolders) {
                if (mViewHolder.getViewType() == viewType) {
                    try {
                        if(mViewHolder.getLayoutId()!=0&&context!=null){
                            View view = LayoutInflater.from(context).inflate(mViewHolder.getLayoutId(),null,false);
                            if(view!=null){
                                MViewHolder newViewHolder = mViewHolder.getClass().getDeclaredConstructor(new Class[]{View.class}).newInstance(view);
                                return newViewHolder;
                            }
                        }
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
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
