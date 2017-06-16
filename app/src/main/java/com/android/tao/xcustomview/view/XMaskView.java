package com.android.tao.xcustomview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.android.tao.xcustomview.R;

/**
 * Created by jiantao on 2017/4/5.
 */

public class XMaskView extends View {

    private Context mContext;
    private int mWidth;
    private int mHeight;
    private Bitmap mDstBitmap;
    private int mDstBmpWIdth;
    private int mDstBmpHeight;
    private Bitmap mMaskedBItmap;
    private Paint mPaint;


    public XMaskView(Context context) {
        this(context, null);
    }

    public XMaskView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XMaskView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {

        mDstBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.apple);

        mDstBmpWIdth = mDstBitmap.getWidth();
        mDstBmpHeight = mDstBitmap.getHeight();
        mMaskedBItmap = createRectImage(mDstBitmap, Math.min(mDstBmpWIdth * 3 / 5, mDstBmpHeight * 3 / 5));

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));


    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();
        mHeight = getHeight();


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawBitmap(mMaskedBItmap, 0, 0, mPaint);


    }


    /**
     * 根据原图和变长绘制圆形图片
     *
     * @param source
     * @param min
     * @return
     */
    private Bitmap createCircleImage(Bitmap source, int min) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        // 注意一定要用ARGB_8888，否则因为背景不透明导致遮罩失败
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        // 产生一个同样大小的画布
        Canvas canvas = new Canvas(target);
        // 首先绘制圆形
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        // 使用SRC_IN
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // 绘制图片
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }


    /**
     * 根据原图和变长绘制圆形图片
     *
     * @param source
     * @param min
     * @return
     */
    private Bitmap createRectImage(Bitmap source, int min) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        // 注意一定要用ARGB_8888，否则因为背景不透明导致遮罩失败
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        // 产生一个同样大小的画布
        Canvas canvas = new Canvas(target);
        // 首先绘制圆形
        canvas.drawRect(0,0, min,min, paint);
        // 使用SRC_IN
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // 绘制图片
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }





}
