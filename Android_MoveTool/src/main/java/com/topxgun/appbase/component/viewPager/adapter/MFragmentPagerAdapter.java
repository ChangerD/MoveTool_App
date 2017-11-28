package com.topxgun.appbase.component.viewPager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

/**
 * Created by JWDING on 2017/4/18.
 * FragmentAdapter继承base---lib
 * 支持方向
 */

public class MFragmentPagerAdapter extends MFragmentPagerAdapterBase {

    ArrayList<Fragment> list;

    public MFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    public void setList(ArrayList<Fragment> list) {
        if(list!=this.list){
            this.list = list;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

}
