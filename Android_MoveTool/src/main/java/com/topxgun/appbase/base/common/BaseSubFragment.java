package com.topxgun.appbase.base.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.topxgun.appbase.component.partialcommunication.PartialCommunication;
import com.topxgun.appbase.component.dialog.WaitDialog;
import com.topxgun.appbase.component.toast.CustomToast;
import com.topxgun.appbase.dao.spf.ConfigSPF;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Jwding on 2017/8/7.
 * 二层基础封装，可以引用三方控件，引用自定义控件等，但是不涉及任何业务层代码
 */

public abstract class BaseSubFragment extends Fragment implements OnClickListener {

    protected String TAG = "BaseFragment";
    // rootView
    protected View rootView;
    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (useEventBus() != null && useEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
            mContext = getActivity();
            ButterKnife.bind(this, rootView);
            this.initView();
            this.initData();
            this.initListener();
            this.onPrepared();
        }
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (useEventBus() != null && useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    /*
                     *获取布局文件
                     */
    protected abstract int getLayoutId();

    /*
     *初始化view
     */
    protected abstract void initView();

    /*
     *初始化数据
     */
    protected abstract void initData();

    /*
     *初始化监听
     */
    protected abstract void initListener();

    /**
     * 是否使用EventBus
     * 如果使用eventbus需要写onEvent方法，不然会报错crash的
     */
    public abstract Boolean useEventBus();

    /**
     * 防止eventbus崩溃
     *
     * @param emptyEvent
     */
    public void onEvent(Object emptyEvent) {

    }

    /**
     * 视图创建完成后
     */
    protected void onPrepared() {

    }

    protected PartialCommunication getPartialCommunication() {
        if (getActivity() instanceof BaseSubActivity) {
            return ((BaseSubActivity) getActivity()).getPartialCommunication();
        }
        return null;
    }

    /**
     * 获取默认SPF
     */
    public SharedPreferences getDefaultSPF() {
        return getDefaultSPF();
    }

    /**
     * 获取指定SPF
     *
     * @param spfName
     */
    public SharedPreferences getSPF(String spfName) {
        return ConfigSPF.getInstance(mContext).getConfigSPF(spfName);
    }

    /**
     * 显示和隐藏等待框
     */
    public void showWaitDialog() {
        if(mContext!=null&&mContext instanceof BaseActivity){
            ((BaseActivity) mContext).mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    WaitDialog.show(mContext);
                }
            },10);
        }else{
            WaitDialog.show(mContext);
        }
    }

    public void hideWaitDialog() {
        if(mContext!=null&&mContext instanceof BaseActivity){
            ((BaseActivity) mContext).mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    WaitDialog.dismiss();
                }
            },300);
        }else{
            WaitDialog.dismiss();
        }
    }

    public void showWaitDialog(String messsage) {
        WaitDialog.show(mContext, messsage);
    }

    public void showWaitDialog(String message, Boolean cancelAble) {
        WaitDialog.show(mContext, message, cancelAble);
    }

    /**
     * 显示吐司
     *
     * @param message
     */
    public void showBottomToast(String message) {
        CustomToast.showBottomToast(mContext, message);
    }

    public void showTopToast(String message) {
        CustomToast.showTopToast(mContext, message);
    }

    @Override
    public void onClick(View v) {

    }

}
