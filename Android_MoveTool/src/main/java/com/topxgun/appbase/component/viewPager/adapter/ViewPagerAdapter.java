package com.topxgun.appbase.component.viewPager.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.topxgun.appbase.component.viewPager.PagerAdapter;
import com.topxgun.appbase.component.viewPager.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jwding on 2017/8/14.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private List<View> viewList=new ArrayList<>();

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    public void setViewList(List<View> viewList){
        this.viewList=viewList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }
}
