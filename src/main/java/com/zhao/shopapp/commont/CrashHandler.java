package com.zhao.shopapp.commont;


import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * 自定义异常消息捕获
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler crashHandler=null;

    private Context context;
    private CrashHandler(){}

    public static CrashHandler getInstance()
    {
        if(crashHandler==null)
        {
            crashHandler=new CrashHandler();
        }
        return crashHandler;
    }

    //暴露初始化CrashHandler的方法
    public void init(Context context){
        this.context=context;
        //将CrashHandler作为系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if(isHandler(e))
            try {
                handlerException(t,e);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
    }

    /**
     * 处理异常错误
     * @param t
     * @param e
     */
    private void handlerException(Thread t, Throwable e) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context,"抱歉系统出现未知错误,即将推出程序。。。",Toast.LENGTH_SHORT);
                Looper.loop();
            }
        }).start();

        collectionException(e);

        t.sleep(2000);
        AppManager.getInstance().removeAll();
        //关闭当前进程
        android.os.Process.killProcess(android.os.Process.myPid());
        //关闭虚拟机，释放所有内存
        System.exit(0);
    }

    /**
     * 收集崩溃的错误信息
     */
    private void collectionException(Throwable e)
    {
        //返回给后台的信息 设备信息,错误信息
        final String deviceInfo= Build.DEVICE+Build.VERSION.SDK_INT+Build.MODEL+Build.PRODUCT;
        final String errorInfo=e.getMessage();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("error",deviceInfo+"----"+errorInfo);
            }
        }).start();
    }

    /**
     * 判断异常是否需要在即处理
     */
    private boolean isHandler(Throwable ex)
    {
        if(ex==null)
        {
            return false;
        }
        return true;
    }
}
