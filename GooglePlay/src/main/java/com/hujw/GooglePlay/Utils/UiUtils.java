package com.hujw.GooglePlay.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.hujw.GooglePlay.Application.BaseApplication;

/**
 * Created by Admin on 2015/8/25.
 */
public class UiUtils {

    public static Context getContext(){
        return BaseApplication.getBaseApplication();
    }
    public static void runOnUiThread(Runnable runnable) {
        if(android.os.Process.myTid()== BaseApplication.getMainTid())
        {
            runnable.run();
        }
        else
        {
            BaseApplication.getHandler().post(runnable);
        }
    }

    public static View inflater(int id) {
        return View.inflate(getContext(),id,null);
    }

    public static Drawable getDrawalbe(int nothing) {
        return getContext().getResources().getDrawable(nothing);
    }
}
