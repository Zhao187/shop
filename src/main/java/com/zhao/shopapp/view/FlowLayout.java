package com.zhao.shopapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 流式布局
 */

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 求取布局的宽度
     * EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
     AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
     UNSPECIFIED：表示子布局想要多大就多大，很少使用
     * @param widthMeasureSpec 宽度的测量规格
     * @param heightMeasureSpec 高度的测量规格
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);//宽度测量模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);//宽度

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        int lineWidth=0;
        int lineHeight=0;

        int width=0;
        int height=0;

        //处理AT_MOST情况
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
            int childWidth = childView.getMeasuredWidth();
            int childHeight=childView.getMeasuredHeight();
            MarginLayoutParams mp= (MarginLayoutParams) childView.getLayoutParams();

            if(lineWidth+childWidth+mp.leftMargin+mp.rightMargin>width)
            {
                //进行换行
                width=Math.max(width,lineWidth);
                height+=lineHeight;

                //重置数据
                lineWidth=childWidth+mp.leftMargin+mp.rightMargin;
                lineHeight=childHeight;
            }else
            {
                lineWidth+=childWidth+mp.leftMargin+mp.rightMargin;
                lineHeight=Math.max(lineHeight,childHeight);
            }

            //最后一个也要计算一下
            if(i==childCount-1)
            {
                width=Math.max(width,lineWidth);
                height+=lineHeight;
            }
        }
        Log.e("it520",width+" "+height+" "+getChildCount());
        setMeasuredDimension(widthMode==MeasureSpec.EXACTLY?widthSize:width,heightMode==MeasureSpec.EXACTLY?heightSize:height);
    }


    //每一行所包含的所有子view
    private List<List<View>> allViews = new ArrayList<>();

    private List<Integer> allHeights = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int width = getWidth();
        int cCount = getChildCount();

        int lineWidth = 0;
        int lineHeight = 0;

        List<View> lineViews = new ArrayList<>();

        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            MarginLayoutParams mp = ((MarginLayoutParams) child.getLayoutParams());
            if (lineWidth + childWidth + mp.leftMargin + mp.rightMargin > width) {
                //换行
                allViews.add(lineViews);
                allHeights.add(lineHeight);
                //重置一下变量
                lineViews = new ArrayList<>();
                lineWidth = 0;
                lineHeight = childHeight + mp.topMargin + mp.bottomMargin;
            }
            //不换行
            lineWidth += childWidth + mp.leftMargin + mp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + mp.topMargin + mp.bottomMargin);
            lineViews.add(child);

            if (i == cCount - 1) {
                allViews.add(lineViews);
                allHeights.add(lineHeight);
            }
        }
        //通过计算每一行的每一个子view的left,top,right,bottom,摆放每一行的每一个子view的位置
        int left = 0;
        int top = 0;

        for (int i = 0; i < allViews.size(); i++) {
            int curLineHeight = allHeights.get(i);
            //当前行的所有子view
            List<View> views = allViews.get(i);
            for (View view : views) {
                int viewWidth = view.getMeasuredWidth();
                int viewHeight = view.getMeasuredHeight();
                MarginLayoutParams mp = ((MarginLayoutParams) view.getLayoutParams());
                int lc = left + mp.leftMargin;
                int tc = top + mp.topMargin;
                int rc = lc + viewWidth;
                int bc = tc + viewHeight;
                view.layout(lc, tc, rc, bc);
                left += viewWidth + mp.rightMargin + mp.leftMargin;
            }
            left = 0;
            top += curLineHeight;
        }
    }

    /**
     * WRAP_CONTENT的时候使用,获得子布局params
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        MarginLayoutParams marginLayoutParams=new MarginLayoutParams(getContext(),attrs);
        return marginLayoutParams;
    }
}
