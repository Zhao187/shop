package com.zhao.shopapp.adapter.product.adapter2.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * baseAdapter的抽取
 */

public abstract class BaseListAdapter1<T> extends BaseAdapter {
    private List<T> list;

    public BaseListAdapter1(List<T> list)
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
        BaseHolder holder=null;
        if(convertView==null)
        {
            holder=getHoler();
        }
        else
        {
            holder=(BaseHolder)convertView.getTag();
        }
        holder.setmData(list.get(position));
        return holder.getRootView();
    }

    public abstract BaseHolder getHoler();
}
