package com.hujw.GooglePlay.Utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by Administrator on 2015/8/23.
 */
public class ViewUtils {

    /**
     * 移除控件的父容器
     * @param view
     */
    public static void removeParentView(View view){

        ViewParent parent=view.getParent();

        if(parent instanceof ViewGroup){
            ViewGroup viewGroup= (ViewGroup) parent;
            viewGroup.removeView(view);
        }

    }

}
