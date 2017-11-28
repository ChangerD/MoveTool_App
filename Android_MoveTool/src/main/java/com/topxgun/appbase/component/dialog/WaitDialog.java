package com.topxgun.appbase.component.dialog;

import android.app.ProgressDialog;
import android.content.Context;

public class WaitDialog {

    private static ProgressDialog progressDialog;

    public static void show(Context context){
        String lan=context.getResources().getConfiguration().locale.getLanguage();
        if(lan.endsWith("zh")){
            show(context,"请稍后");
        }else{
            show(context,"Please wait");
        }
    }

    public static void show(Context context,String message){
        show(context,message,true);
    }

    public static void show(Context context,String message,boolean cancelAble){
        if(progressDialog!=null){
            progressDialog.setMessage(message);
        }else{
            progressDialog= ProgressDialog.show(context,"",message);
        }
        progressDialog.show();
        progressDialog.setCancelable(cancelAble);
    }

    public static void dismiss(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

}
