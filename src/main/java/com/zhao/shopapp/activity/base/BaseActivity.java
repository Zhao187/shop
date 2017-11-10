package com.zhao.shopapp.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.zhao.shopapp.commont.AppManager;

import butterknife.ButterKnife;

/**
 * Activity的基类
 */

public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(getLayoutId());
        AppManager.getInstance().addActivity(this);
        ButterKnife.bind(this);
        initTitle();
        initData();
    }

    protected abstract int getLayoutId();
    protected abstract void initData();
    protected abstract void initTitle();

    /**
     * 关闭当前activity
     */
    public void closeCurrentActivity()
    {
        AppManager.getInstance().removeCurrent();
    }

    /**
     * Activity的跳转
     */
    public void startActivity(Class clazz,Bundle bundle)
    {
        Intent intent=new Intent(this,clazz);
        if (bundle != null) {
            intent.putExtra("param", bundle);
        }
        startActivity(intent);
    }
}
