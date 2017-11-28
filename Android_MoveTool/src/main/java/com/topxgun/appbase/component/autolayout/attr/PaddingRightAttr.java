package com.topxgun.appbase.component.autolayout.attr;

import android.view.View;

import com.topxgun.appbase.component.autolayout.attr.*;
import com.topxgun.appbase.component.autolayout.attr.AutoAttr;

/**
 * Created by zhy on 15/12/5.
 */
public class PaddingRightAttr extends com.topxgun.appbase.component.autolayout.attr.AutoAttr
{
    public PaddingRightAttr(int pxVal, int baseWidth, int baseHeight)
    {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal()
    {
        return com.topxgun.appbase.component.autolayout.attr.Attrs.PADDING_RIGHT;
    }

    @Override
    protected boolean defaultBaseWidth()
    {
        return true;
    }

    @Override
    protected void execute(View view, int val)
    {
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = val;
        int b = view.getPaddingBottom();
        view.setPadding(l, t, r, b);

    }


    public static PaddingRightAttr generate(int val, int baseFlag)
    {
        PaddingRightAttr attr = null;
        switch (baseFlag)
        {
            case com.topxgun.appbase.component.autolayout.attr.AutoAttr.BASE_WIDTH:
                attr = new PaddingRightAttr(val, com.topxgun.appbase.component.autolayout.attr.Attrs.PADDING_RIGHT, 0);
                break;
            case com.topxgun.appbase.component.autolayout.attr.AutoAttr.BASE_HEIGHT:
                attr = new PaddingRightAttr(val, 0, com.topxgun.appbase.component.autolayout.attr.Attrs.PADDING_RIGHT);
                break;
            case AutoAttr.BASE_DEFAULT:
                attr = new PaddingRightAttr(val, 0, 0);
                break;
        }
        return attr;
    }
}
