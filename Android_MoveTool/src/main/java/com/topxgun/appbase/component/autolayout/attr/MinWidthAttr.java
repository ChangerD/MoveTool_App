package com.topxgun.appbase.component.autolayout.attr;

import android.os.Build;
import android.view.View;

import com.topxgun.appbase.component.autolayout.attr.*;
import com.topxgun.appbase.component.autolayout.attr.AutoAttr;

import java.lang.reflect.Field;

/**
 * Created by zhy on 15/12/24.
 */
public class MinWidthAttr extends com.topxgun.appbase.component.autolayout.attr.AutoAttr
{
    public MinWidthAttr(int pxVal, int baseWidth, int baseHeight)
    {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal()
    {
        return com.topxgun.appbase.component.autolayout.attr.Attrs.MIN_WIDTH;
    }

    @Override
    protected boolean defaultBaseWidth()
    {
        return true;
    }

    @Override
    protected void execute(View view, int val)
    {
        try
        {
//            Method setMaxWidthMethod = view.getClass().getMethod("setMinWidth", int.class);
//            setMaxWidthMethod.invoke(view, val);
        } catch (Exception ignore)
        {
        }

        view.setMinimumWidth(val);
    }

    public static int getMinWidth(View view)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            return view.getMinimumWidth();
        try
        {
            Field minWidth = view.getClass().getField("mMinWidth");
            minWidth.setAccessible(true);
            return (int) minWidth.get(view);
        } catch (Exception ignore)
        {
        }
        return 0;
    }


    public static MinWidthAttr generate(int val, int baseFlag)
    {
        MinWidthAttr attr = null;
        switch (baseFlag)
        {
            case com.topxgun.appbase.component.autolayout.attr.AutoAttr.BASE_WIDTH:
                attr = new MinWidthAttr(val, com.topxgun.appbase.component.autolayout.attr.Attrs.MIN_WIDTH, 0);
                break;
            case com.topxgun.appbase.component.autolayout.attr.AutoAttr.BASE_HEIGHT:
                attr = new MinWidthAttr(val, 0, com.topxgun.appbase.component.autolayout.attr.Attrs.MIN_WIDTH);
                break;
            case AutoAttr.BASE_DEFAULT:
                attr = new MinWidthAttr(val, 0, 0);
                break;
        }
        return attr;
    }
}
