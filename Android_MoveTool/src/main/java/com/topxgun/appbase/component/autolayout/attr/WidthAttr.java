package com.topxgun.appbase.component.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

import com.topxgun.appbase.component.autolayout.attr.*;
import com.topxgun.appbase.component.autolayout.attr.AutoAttr;

/**
 * Created by zhy on 15/12/5.
 */
public class WidthAttr extends com.topxgun.appbase.component.autolayout.attr.AutoAttr
{
    public WidthAttr(int pxVal, int baseWidth, int baseHeight)
    {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal()
    {
        return com.topxgun.appbase.component.autolayout.attr.Attrs.WIDTH;
    }

    @Override
    protected boolean defaultBaseWidth()
    {
        return true;
    }

    @Override
    protected void execute(View view, int val)
    {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = val;
    }

    public static WidthAttr generate(int val, int baseFlag)
    {
        WidthAttr widthAttr = null;
        switch (baseFlag)
        {
            case com.topxgun.appbase.component.autolayout.attr.AutoAttr.BASE_WIDTH:
                widthAttr = new WidthAttr(val, com.topxgun.appbase.component.autolayout.attr.Attrs.WIDTH, 0);
                break;
            case com.topxgun.appbase.component.autolayout.attr.AutoAttr.BASE_HEIGHT:
                widthAttr = new WidthAttr(val, 0, com.topxgun.appbase.component.autolayout.attr.Attrs.WIDTH);
                break;
            case AutoAttr.BASE_DEFAULT:
                widthAttr = new WidthAttr(val, 0, 0);
                break;
        }
        return widthAttr;
    }

}
