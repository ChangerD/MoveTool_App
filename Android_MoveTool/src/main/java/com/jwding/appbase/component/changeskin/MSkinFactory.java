package com.jwding.appbase.component.changeskin;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jwding on 2017/12/21.
 */

public class MSkinFactory implements LayoutInflater.Factory2 {


    private static final String TAG = "MSkinFactory";

    public static String[] systemViewPackageList = {"android.widget.", "android.view.", "android.webkit."};

    public long skinResId=0;

    List<SkinView> skinViews=new ArrayList<>();

    public MSkinFactory() {

    }

    CreateViewExtend createViewExtend;

    public interface CreateViewExtend {
        View onCreateView(View view, String s, Context context, AttributeSet attributeSet);
    }

    public MSkinFactory(CreateViewExtend createViewExtend) {
        this.createViewExtend = createViewExtend;
    }

    @Override
    public View onCreateView(View view, String s, Context context, AttributeSet attributeSet) {
        Log.i(TAG, s);
        //----------------------------创建View-------------------------------------------
        if (createViewExtend != null) {
            view = createViewExtend.onCreateView(view, s, context, attributeSet);
        }
        if (view == null) {
            //自定义控件
            if (s.contains(".")) {
                view =createView(context,s,attributeSet);
            }
            //系统控件
            else {
                for (String pkgN : systemViewPackageList) {
                    view = createView(context,pkgN+s, attributeSet);
                    if (view != null) {
                        break;
                    }
                }

            }
        }
        //----------------------------创建View-------------------------------------------
        //-----------------------收集需要换肤的控件--------------------------------------
        parseSkinView(context,attributeSet,view);
        //-----------------------收集需要换肤的控件--------------------------------------
        return view;
    }

    public void parseSkinView(Context context,AttributeSet attrs,View view){
        List<SkinItem> skinItems=new ArrayList<>();
        for(int i=0;i<attrs.getAttributeCount();i++){
            String attrName=attrs.getAttributeName(i);
            String attrValue=attrs.getAttributeValue(i);
            Log.d(TAG,"attrName:"+attrName+"----"+"attrValue:"+attrValue);
            if(attrName.equals("background")||attrName.equals("textColor")){
                if(attrValue.startsWith("@")){
                    int id=Integer.parseInt(attrValue.substring(1));
                    String entryName=context.getResources().getResourceEntryName(id);
                    String typeName=context.getResources().getResourceTypeName(id);
                    SkinItem skinItem=new SkinItem(attrName,id,entryName,typeName);
                    skinItems.add(skinItem);
                }
            }
        }
        if(!skinItems.isEmpty()){
            SkinView skinView=new SkinView(view,skinItems);
            skinViews.add(skinView);
            skinView.applySkin();
        }
    }

    class SkinView{
        View view;
        List<SkinItem> skinItems;

        public SkinView(View view, List<SkinItem> skinItems) {
            this.view = view;
            this.skinItems = skinItems;
        }

        public void applySkin(){
            for(SkinItem skinItem:skinItems){
                if(skinItem.getAttrName().equals("background")){
                    if("color".equals(skinItem.getAttrType())){
                        view.setBackgroundColor(SkinManager.getInstance().getColor(skinItem.refId));
                    }else if("drawable".equals(skinItem.getAttrType())){
                        view.setBackground(SkinManager.getInstance().getDrawable(skinItem.refId));
                    }
                }else if(skinItem.getAttrName().equals("textColor")){
                    if(view instanceof TextView){
                        ((TextView) view).setTextColor(SkinManager.getInstance().getColor(skinItem.refId));
                    }
                }
            }
        }
    }

    public void refreshSkinViews(){
        for(SkinView skinView:skinViews){
            if(skinView!=null){
                skinView.applySkin();
            }
        }
    }

    //Acty-Views-SkinItem
    class SkinItem{

        String attrName;
        int refId;
        String attrValue;
        String attrType;

        public SkinItem(String attrName, int refId, String attrValue, String attrType) {
            this.attrName = attrName;
            this.refId = refId;
            this.attrValue = attrValue;
            /**
             *  color
             *  drawable
             */
            this.attrType = attrType;
        }

        public String getAttrName() {
            return attrName;
        }

        public int getRefId() {
            return refId;
        }

        public String getAttrValue() {
            return attrValue;
        }

        public String getAttrType() {
            return attrType;
        }
    }

    @Override
    public View onCreateView(String s, Context context, AttributeSet attributeSet) {
        Log.i(TAG, s);
        return null;
    }

    public View createView(Context context, String name, AttributeSet attributeSet) {
        try {
            ClassLoader cl = context.getClassLoader();
            Class viewC = cl.loadClass(name);
            Constructor<? extends View> constructor = viewC.getConstructor(new Class[]{Context.class, AttributeSet.class});
            return constructor.newInstance(context, attributeSet);
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
        } catch (NoSuchMethodException e) {
            //e.printStackTrace();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

}
