package com.zhao.shopapp.adapter.product.adapter1.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * baseAdapter的抽取
 */

public abstract class BaseListAdapter<T> extends BaseAdapter {
    private List<T> list;

    public BaseListAdapter(List<T> list)
    {
        this.list=list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T t = list.get(position);
        if(convertView==null)
        {
            convertView=getConvertView();
        }
        setData(convertView,t);
        return convertView;
    }

    public abstract View getConvertView();
    public abstract void setData(View converView,T t);
}
