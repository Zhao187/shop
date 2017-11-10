package com.zhao.shopapp.util;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * 资源图片处理工具类
 */

public class DrawableUtil {
    public static GradientDrawable getDrawable(int rgb,int connerradius)
    {
        GradientDrawable gradientDrawable=new GradientDrawable();
        gradientDrawable.setColor(rgb);
        gradientDrawable.setCornerRadius(connerradius);
        gradientDrawable.setGradientType(GradientDrawable.RECTANGLE);
        return gradientDrawable;
    }

    public static StateListDrawable getSelector(Drawable normalDrawable, Drawable pressDrawable) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        //给当前的颜色选择器添加选中图片指向状态，未选中图片指向状态
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed}, pressDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, normalDrawable);
        //设置默认状态
        stateListDrawable.addState(new int[]{}, normalDrawable);
        return stateListDrawable;
    }
}
