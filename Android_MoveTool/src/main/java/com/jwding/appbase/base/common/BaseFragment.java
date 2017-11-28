package com.jwding.appbase.base.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;

public abstract class BaseFragment extends Fragment implements OnClickListener {

    protected String TAG = "BaseFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
