package com.topxgun.appbase.component.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.topxgun.appbase.R;

/**
 * Created by Jwding on 2017/8/11.
 */

public class ComfirmDialog extends Dialog{
    
    //布局文件
    int layoutId;
    //标题id
    int titleId;
    //内容id
    int messageId;
    //ok_btn_id
    int okBtnId;
    //cancel_btn_id
    int cancelBtnId;


    public ComfirmDialog(@NonNull Context context, int layoutId) {
        super(context,R.style.MyDialog_);
        this.layoutId=layoutId;
        setContentView(layoutId);
    }

    public ComfirmDialog(@NonNull Context context, int layoutId, int okBtnId, int cancelBtnId) {
        super(context,R.style.MyDialog_);
        this.layoutId=layoutId;
        setContentView(layoutId);
        this.okBtnId=okBtnId;
        this.cancelBtnId=cancelBtnId;
    }

    public ComfirmDialog(@NonNull Context context, int layoutId, int okBtnId, int cancelBtnId, Boolean cancelAble) {
        super(context,R.style.MyDialog_);
        this.layoutId=layoutId;
        setContentView(layoutId);
        this.okBtnId=okBtnId;
        this.cancelBtnId=cancelBtnId;
        setCancelable(cancelAble);
    }

    public ComfirmDialog(@NonNull Context context, int layoutId, int titleId, int messageId, int okBtnId, int cancelBtnId) {
        super(context,R.style.MyDialog_);
        this.layoutId=layoutId;
        setContentView(layoutId);
        this.titleId=titleId;
        this.messageId=messageId;
        this.okBtnId=okBtnId;
        this.cancelBtnId=cancelBtnId;
    }

    public ComfirmDialog setTitle(String title){
        if(titleId!=0&&findViewById(titleId)!=null&&findViewById(titleId) instanceof TextView){
            ((TextView) findViewById(titleId)).setText(title);
        }
        return this;
    }

    public ComfirmDialog setMessage(String message){
        if(messageId!=0&&findViewById(messageId)!=null&&findViewById(messageId) instanceof TextView){
            ((TextView) findViewById(messageId)).setText(message);
        }
        return this;
    }

    public ComfirmDialog setOkClickListener(View.OnClickListener onClickListener){
        if(okBtnId!=0&&findViewById(okBtnId)!=null){
            findViewById(okBtnId).setOnClickListener(onClickListener);
        }
        return this;
    }

    public ComfirmDialog setCancelClickListener(View.OnClickListener onClickListener){
        if(cancelBtnId!=0&&findViewById(cancelBtnId)!=null){
            findViewById(cancelBtnId).setOnClickListener(onClickListener);
        }
        return this;
    }

    public ComfirmDialog showDialog(){
        this.show();
        return this;
    }

    public View getViewById(int id){
        return findViewById(id);
    }

}
