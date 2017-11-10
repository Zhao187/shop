package com.zhao.shopapp.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

/**
 * 图片的工具类
 */

public class BtimapUtil {
    /**
     * 图片的缩放
     */
    public static Bitmap zoom(Bitmap source,float fw,float fh)
    {
        Matrix matrix=new Matrix();

        float scaleX = fw / source.getWidth();
        float scaleY=fh/source.getHeight();
        matrix.postScale(scaleX,scaleY);
        return Bitmap.createBitmap(source,0,0,source.getWidth(),source.getHeight(),matrix,true);
    }

    /**
     * 头像圆形裁剪器
     */
    public static Bitmap circleBitmap(Bitmap source)
    {
        Paint paint=new Paint();
        int width = source.getWidth();
        Bitmap target=Bitmap.createBitmap(width,width, Bitmap.Config.ARGB_8888);
        //根据target生成一张画布
        Canvas canvas=new Canvas(target);
        canvas.drawCircle(width/2,width/2,width/2,paint);
        //canvas.drawRoundRect(new RectF(0,0,width,width),20,20,paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(source,0,0,paint);
        return target;
    }

    /**
     * 绘制椭圆图像
     */
    public static Bitmap roundRectBitmap(Bitmap source,int radius)
    {
        Paint paint=new Paint();
        int width = source.getWidth();
        Bitmap target=Bitmap.createBitmap(width,width, Bitmap.Config.ARGB_8888);
        //根据target生成一张画布
        Canvas canvas=new Canvas(target);
        canvas.drawRoundRect(new RectF(0,0,width,width),radius,radius,paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(source,0,0,paint);
        return target;
    }
}
