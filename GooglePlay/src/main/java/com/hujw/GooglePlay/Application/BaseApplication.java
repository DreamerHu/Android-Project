package com.hujw.GooglePlay.Application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import java.util.logging.LogRecord;

/**
 * Created by Admin on 2015/8/25.
 */
public class BaseApplication extends Application {
    private static Handler handler;
    private static BaseApplication baseApplication;
    private static int  mainTid;
    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication=this;
        mainTid=android.os.Process.myTid();
        handler=new Handler();
    }
    public static Context getContext(){

        return baseApplication;
    }

    public static int getMainTid() {
        return mainTid;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }
}
