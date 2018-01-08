package com.jwding.appbase.util;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.widget.DatePicker;

/**
 * Created by Jwding on 2017/12/21.
 */

public class SystemWidgetUtil {

    public static void showDatePickerDialog(Activity activity, int themeResId, String title, final String[] results, int year, int mon, int day) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity
                , themeResId
                // 绑定监听器(How the parent is notified that the date is set.)
                , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                results[0] = "您选择了：" + year + "年" + monthOfYear + "月" + dayOfMonth + "日";
            }
        }, year, mon - 1, day);
        if (!TextUtils.isEmpty(title)) {
            datePickerDialog.setTitle(title);
        }
        datePickerDialog.show();
    }

}
