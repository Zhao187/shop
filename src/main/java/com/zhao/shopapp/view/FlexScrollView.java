package com.zhao.shopapp.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * 自定义伸缩回弹控件
 */

public class FlexScrollView extends ScrollView {

    //要操纵的布局
    private View innerView;
    //垂直移动的距离
    private float y;

    //记录正常的位置
    private Rect Normal=new Rect();

    private boolean isScrollAnimationFinsh=true;
    public FlexScrollView(Context context) {
        super(context,null);
    }

    public FlexScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlexScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 当View中所有的子控件均被映射成xml后触发
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount=getChildCount();
        if(childCount>0)
        {
            innerView=getChildAt(0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(innerView==null)
        {
            return super.onTouchEvent(ev);
        }
        else
        {
            commentTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    public void commentTouchEvent(MotionEvent ev)
    {
        if(isScrollAnimationFinsh)
        {
            switch (ev.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    y=ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    //随之y的移动距离增到,布局相应往下移动
                    float preY=y==0?ev.getY():y;
                    float nowY=ev.getY();

                    int dexY= (int) (preY-nowY);
                    y=nowY;
                    //操作view移动为手指移动距离的一半
                    if(isNeedMove())
                    {
                        if(Normal.isEmpty())
                        {
                            Normal.set(innerView.getLeft(),innerView.getTop(),innerView.getRight(),innerView.getBottom());
                        }

                        innerView.layout(innerView.getLeft(),innerView.getTop()-dexY,innerView.getRight(),innerView.getBottom()-dexY);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    //回弹效果
                    y=0;
                    //判断是否需要滚回原地
                    if(isNeedScrollNormal())
                    {
                        ScrollNormalAnimation();
                    }
                    break;
            }
        }
    }

    public void ScrollNormalAnimation()
    {
        TranslateAnimation translateAnimation=new TranslateAnimation(0,0,0,Normal.top-innerView.getTop());
        translateAnimation.setDuration(200);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isScrollAnimationFinsh=false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                innerView.clearAnimation();
                innerView.layout(Normal.left,Normal.top,Normal.right,Normal.bottom);
                Normal.setEmpty();
                isScrollAnimationFinsh=true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        innerView.startAnimation(translateAnimation);
    }

    /**
     * 判断是否需要回滚操作
     * @return
     */
    public boolean isNeedScrollNormal()
    {
        return !Normal.isEmpty();
    }

    /**
     * 是否需要移动
     */
    public boolean isNeedMove()
    {
        int offsexY=innerView.getMeasuredHeight()-getHeight();
        int scrollY=getScrollY();

        if(scrollY==0 || scrollY==offsexY)
        {
            return true;
        }
        return false;
    }
}
