package com.topxgun.appbase.component.autolayout.attr;

import android.view.View;

import com.topxgun.appbase.component.autolayout.attr.*;
import com.topxgun.appbase.component.autolayout.attr.AutoAttr;

/**
 * Created by zhy on 15/12/5.
 */
public class PaddingTopAttr extends com.topxgun.appbase.component.autolayout.attr.AutoAttr
{
    public PaddingTopAttr(int pxVal, int baseWidth, int baseHeight)
    {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal()
    {
        return com.topxgun.appbase.component.autolayout.attr.Attrs.PADDING_TOP;
    }

    @Override
    protected boolean defaultBaseWidth()
    {
        return false;
    }

    @Override
    protected void execute(View view, int val)
    {
        int l = view.getPaddingLeft();
        int t = val;
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();
        view.setPadding(l, t, r, b);
    }

    public static PaddingTopAttr generate(int val, int baseFlag)
    {
        PaddingTopAttr attr = null;
        switch (baseFlag)
        {
            case com.topxgun.appbase.component.autolayout.attr.AutoAttr.BASE_WIDTH:
                attr = new PaddingTopAttr(val, com.topxgun.appbase.component.autolayout.attr.Attrs.PADDING_TOP, 0);
                break;
            case com.topxgun.appbase.component.autolayout.attr.AutoAttr.BASE_HEIGHT:
                attr = new PaddingTopAttr(val, 0, com.topxgun.appbase.component.autolayout.attr.Attrs.PADDING_TOP);
                break;
            case AutoAttr.BASE_DEFAULT:
                attr = new PaddingTopAttr(val, 0, 0);
                break;
        }
        return attr;
    }
}
