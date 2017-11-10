package com.zhao.shopapp.util;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.zhao.shopapp.commont.ShopApplication;

/**
 * UI界面操作的常用一些工具类
 */

public class UIUtils {
    /**
     * 获取资源中的颜色
     */
    public static int getColor(int id)
    {
        return getContext().getResources().getColor(id);
    }

    /**
     * 获取StringArray中数据
     * @param arrId
     * @return
     */
    public static String[] getStringArr(int arrId) {
        return getContext().getResources().getStringArray(arrId);
    }
    /**
     * @param dp
     * @return
     */
    public static int dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

    ;

    public static int px2dp(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    /**
     * 获取布局中的view
     * @return resId
     */

    public static View getXMLView(int resId)
    {
        return View.inflate(getContext(), resId, null);
    }

    /**
     * 运行在主线程的方法,这样不用关系框架中回调是否在子线程中
     * @param runnable
     */
    public static void runOnMainThread(Runnable runnable)
    {
        if(isInMainThread())
        {
            runnable.run();
        }else
        {
            getHandler().post(runnable);
        }
    }


    /**
     * 判断当前线程是否是主线程
     * @return
     */
    public static boolean isInMainThread()
    {
        int tid=android.os.Process.myTid();

        if(tid==ShopApplication.mainThreadId)
        {
            return true;
        }
        return false;
    }

    public static Context getContext()
    {
        return ShopApplication.context;
    }

    public static Handler getHandler()
    {
        return ShopApplication.handler;
    }
}
