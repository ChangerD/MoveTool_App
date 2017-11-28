package com.topxgun.appbase.component.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.topxgun.appbase.R;

/**
 * Created by JWDING on 2017/7/5.
 */

public class ProgressBarHorWithNum extends Dialog{

    private static ProgressBarHorWithNum instance;
    private Context context;

    private int totalNum=-1;
    private int num=0;
    private BaseProgressBarWithNum agricHorizontalProgressBar;

    public ProgressBarHorWithNum(@NonNull Context context) {
        super(context, R.style.MyDialog_);
        this.context=context;
        setContentView(R.layout.view_progressbar);
        agricHorizontalProgressBar= (BaseProgressBarWithNum) findViewById(R.id.weight_pb_agric);
        agricHorizontalProgressBar.setMax(100);
        agricHorizontalProgressBar.setProgress(0);
        setCancelable(false);
    }

    public static ProgressBarHorWithNum getInstance(Context context){
        if(instance==null){
            instance=new ProgressBarHorWithNum(context);
        }
        return instance;
    }

    /**
     * 隐藏进度条
     */
    public static void dismissProgressBar(){
        if(instance!=null&&instance.context!=null&&!((Activity)(instance.context)).isFinishing()){
            instance.dismiss();
            instance=null;
        }
    }

    /**
     * 设置总任务数
     * @param totalNum
     */
    public ProgressBarHorWithNum setTotalNum(int totalNum){
        if(instance!=null){
            instance.totalNum=totalNum;
        }
        return instance;
    }

    /**
     * 通过任务数控制进度条位置
     * @param num
     */
    public static void progressByNum(int num){
        if(instance!=null&&instance.totalNum>0){
            int pb=(int)(num*1.0*100/instance.totalNum);
            for(int i=instance.agricHorizontalProgressBar.getProgress();i<=pb;i++){
                instance.agricHorizontalProgressBar.setProgress(i);
                if(i==100){
                    dismissProgressBar();
                }
            }
        }
    }

    /**
     * 通过任务数控制进度条位置
     */
    public static void progressByNumAuto(){
        if(instance!=null&&instance.totalNum>0){
            instance.num++;
            int pb=(int)(instance.num*1.0*100/instance.totalNum);
            for(int i=instance.agricHorizontalProgressBar.getProgress();i<=pb;i++){
                instance.agricHorizontalProgressBar.setProgress(i);
            }
        }
    }

    long clicktime=0;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(System.currentTimeMillis()-clicktime<=1000){
            dismissProgressBar();
        }
        clicktime= System.currentTimeMillis();
    }
}
