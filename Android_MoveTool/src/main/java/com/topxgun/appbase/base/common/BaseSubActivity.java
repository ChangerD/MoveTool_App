package com.topxgun.appbase.base.common;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.topxgun.appbase.R;
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

public abstract class BaseSubActivity extends BaseActivity {

    protected PartialCommunication partialCommunication;

    public PartialCommunication getPartialCommunication() {
        return partialCommunication;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        ButterKnife.bind(this);
        partialCommunication = new PartialCommunication();
        setSwitchAnim(R.anim.open_enter_slide_in_left, R.anim.open_exit_fade_back);
        initView();
        initData();
        initListener();
        onPrepared();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (useEventBus() != null && useEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

/*    *//**
     * 防止eventbus崩溃
     * @param emptyEvent
     *//*
    @Subscribe
    public void onEvent(Object emptyEvent){

    }*/


    @Override
    protected void onStop() {
        super.onStop();
        if (useEventBus() != null && useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void finish() {
        super.finish();
        setSwitchAnim(R.anim.close_enter_fade_forward, R.anim.close_exit_slide_out_right);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(partialCommunication!=null)
        partialCommunication.release();
        ButterKnife.unbind(this);
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
     * 视图数据监听都准备完成
     */
    protected void onPrepared(){

    }

    /**
     * 是否使用EventBus
     * 如果使用eventbus需要写onEvent方法，不然会报错crash的
     */
    public abstract Boolean useEventBus();

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
        return ConfigSPF.getInstance(this).getConfigSPF(spfName);
    }

    /**
     * 显示和隐藏等待框
     */
    public void showProgressDialog() {
        WaitDialog.show(this);
    }

    public void showProgressDialog(String messsage) {
        WaitDialog.show(this, messsage);
    }

    public void showProgressDialog(String message, Boolean cancelAble) {
        WaitDialog.show(this, message, cancelAble);
    }

    public void dismissProgressDialog() {
        WaitDialog.dismiss();
    }

    /**
     * 显示吐司
     *
     * @param message
     */
    public void showBottomToast(String message) {
        CustomToast.showBottomToast(this, message);
    }

    public void showTopToast(String message) {
        CustomToast.showTopToast(this, message);
    }

}
