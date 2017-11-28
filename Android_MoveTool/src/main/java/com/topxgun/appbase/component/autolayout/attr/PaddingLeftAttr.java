package com.topxgun.appbase.component.autolayout.attr;

import android.view.View;

import com.topxgun.appbase.component.autolayout.attr.*;
import com.topxgun.appbase.component.autolayout.attr.AutoAttr;

/**
 * Created by zhy on 15/12/5.
 */
public class PaddingLeftAttr extends com.topxgun.appbase.component.autolayout.attr.AutoAttr
{
    public PaddingLeftAttr(int pxVal, int baseWidth, int baseHeight)
    {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal()
    {
        return com.topxgun.appbase.component.autolayout.attr.Attrs.PADDING_LEFT;
    }

    @Override
    protected boolean defaultBaseWidth()
    {
        return true;
    }

    @Override
    protected void execute(View view, int val)
    {
        int l = val;
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();
        view.setPadding(l, t, r, b);

    }


    public static PaddingLeftAttr generate(int val, int baseFlag)
    {
        PaddingLeftAttr attr = null;
        switch (baseFlag)
        {
            case com.topxgun.appbase.component.autolayout.attr.AutoAttr.BASE_WIDTH:
                attr = new PaddingLeftAttr(val, com.topxgun.appbase.component.autolayout.attr.Attrs.PADDING_LEFT, 0);
                break;
            case com.topxgun.appbase.component.autolayout.attr.AutoAttr.BASE_HEIGHT:
                attr = new PaddingLeftAttr(val, 0, com.topxgun.appbase.component.autolayout.attr.Attrs.PADDING_LEFT);
                break;
            case AutoAttr.BASE_DEFAULT:
                attr = new PaddingLeftAttr(val, 0, 0);
                break;
        }
        return attr;
    }
}
