package com.zhao.shopapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhao.shopapp.bean.Index;
import com.zhao.shopapp.commont.AppNetConfig;
import com.zhao.shopapp.util.UIUtils;

import java.util.List;

/**
 * 轮播图适配器
 */

public class ImagePageAdapter extends PagerAdapter {
    private List<Index.ImageArrBean> imageArr;
    public ImagePageAdapter(List<Index.ImageArrBean> imageArr)
    {
        this.imageArr=imageArr;
    }

    @Override
    public int getCount() {
        return imageArr.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(UIUtils.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(UIUtils.getContext()).load(AppNetConfig.BASEURL+imageArr.get(position).getIMAURL()).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
