package com.zhao.shopapp.commont;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * 一些全局的配置
 */

public class ShopApplication extends Application {

    public static Context context = null;

    public static Handler handler=null;

    public static int mainThreadId=0;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        handler=new Handler();
        mainThreadId=android.os.Process.myTid();

       // CrashHandler.getInstance().init(this);
    }
}
