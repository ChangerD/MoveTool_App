package com.jwding.appbase.component.recycleview;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

/**
 * anthor：JWDING
 * date:2018/1/8.
 */

public class MViewHolder extends ViewHolder{

    public int getViewType(){
        return 0;
    };

    public int getLayoutId(){
        return 0;
    }

    public MViewHolder(View itemView) {
        super(itemView);
    }

    public void bindViewHolder(Item item){

    }
}
