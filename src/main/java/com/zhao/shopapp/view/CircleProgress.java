package com.zhao.shopapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.zhao.shopapp.R;

/**
 * 圆环进度条
 */

public class CircleProgress extends View {
    private Paint paint;
    private TextPaint mTextPaint;

    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float textSize;
    private float roundWidth;
    private int max = 100;

    private int progress = 50;

    public CircleProgress(Context context) {
        this(context,null);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint=new Paint();
        mTextPaint=new TextPaint();
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);
        //圆环的颜色
        roundColor = mTypedArray.getColor(R.styleable.RoundProgress_roundColor, Color.RED);
        //圆环进度的颜色
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgress_roundProgressColor, Color.GREEN);
        //中间进度百分比文字字符串的颜色
        textColor = mTypedArray.getColor(R.styleable.RoundProgress_textColor, Color.GREEN);
        //中间进度百分比的字符串的字体大小
        textSize = mTypedArray.getDimension(R.styleable.RoundProgress_textSize, 15);
        //圆环的宽度
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgress_roundWidth, 5);
        mTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //第一步 画一个圆环
        paint.setColor(roundColor);
        paint.setStrokeWidth(roundWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        int center=getWidth()/2;
        int radius= (int) (center-roundWidth/2);
        canvas.drawCircle(center,center,radius,paint);

        //向圆环中间写字
        float widthSize = mTextPaint.measureText(progress+"%");
        paint.setColor(textColor);
        paint.setStrokeWidth(0);
        paint.setTextSize(textSize);
        canvas.drawText(progress+"%",center-widthSize/2,center+textSize/2,paint);

        //绘制弧线
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);
        paint.setColor(roundProgressColor);
        paint.setStrokeWidth(roundWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval,0,360*progress/max, false,paint);
    }

    public void setProgress(int progress)
    {
        this.progress=progress;
        if(progress>100)
        {
            this.progress=100;
        }
        postInvalidate();
    }
}
