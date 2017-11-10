package com.zhao.shopapp.adapter.product.adapter2.base;


import android.view.View;

import butterknife.ButterKnife;

/**
 * getView的所有操作全部交由BaseHolder去实现
 */

public abstract class BaseHolder<T>{
    private T mData;
    private View rootView;

    public BaseHolder()
    {
        rootView=initView();
        rootView.setTag(this);
        ButterKnife.bind(this,rootView);
    }

    public View getRootView() {
        return rootView;
    }

    public T getmData() {
        return mData;
    }

    public void setmData(T mData) {
        this.mData = mData;
        refreshView();
    }

    public abstract void refreshView();
    public abstract View initView();
}
