package com.android.tao.xcustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.ValueAnimator;

import java.text.NumberFormat;

/**
 * Created by Jiantao on 2017/4/1.
 */

public class MyBezierView extends View {

    private static final String TAG = MyBezierView.class.getSimpleName();

    private int mViewWidth;
    private int mViewHeight;

    private int mWidth;
    private int mHeight;
    private float mRadius;
    private RectF mRectF;
    private Paint mWavePaint;
    private Paint mCirclePaint;
    private PointF mPointF;
    private Paint mTextPaint;
    private Path mWavePath;
    private Path mCirclePath;
    private int mItemWaveLength = 200;
    private int dx;
    private int mWaveHeight;

    public MyBezierView(Context context) {
        this(context, null);
    }

    public MyBezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mWavePath = new Path();
        mWavePaint = new Paint();
        mWavePaint.setColor(Color.GREEN);
        mWavePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setColor(Color.parseColor("#ff0099ff"));
        mTextPaint.setStrokeWidth(5);
        mTextPaint.setTextSize(80);

        mCirclePath = new Path();
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.BLACK);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(5);

        mPointF = new PointF(0, 0);

    }

    /**
     * 设置水波纹的高度
     *
     * @param percentHeight 高度的百分比
     */
    public void setWaveHeight(float percentHeight) {
        this.mWaveHeight = (int) (percentHeight * mHeight / 100);
        if (mWaveHeight > mHeight) {
            mWaveHeight = mHeight;
            invalidate();//可以显示进度满的情况
            mWaveHeight -= mHeight;
        }
        invalidate();
    }

    /**
     * 是一个百分比
     *
     * @param deltaPercentHeight 高度增量的百分比
     */
    public void addWaveHeight(float deltaPercentHeight) {
        if (deltaPercentHeight < 0 || deltaPercentHeight > 100) return;
        this.mWaveHeight += deltaPercentHeight * mHeight / 100;
        if (mWaveHeight > mHeight) {
            mWaveHeight -= mHeight;
        }
        invalidate();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;

        mWidth = mViewWidth - getPaddingLeft() - getPaddingRight();
        mHeight = mViewHeight - getPaddingTop() - getPaddingBottom();

        mRadius = Math.min(mWidth, mHeight) * 0.4f;
        mRectF = new RectF(-mRadius, -mRadius, mRadius, mRadius);
        mWaveHeight = 0;

        mPointF.set(mViewWidth / 2, mViewHeight / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCirclePath.addCircle(mViewWidth / 2, mViewHeight / 2, mRadius, Path.Direction.CCW);
//        canvas.drawPath(mCirclePath, mCirclePaint);
//        canvas.translate(mViewWidth/2,mViewHeight/2);
        mWavePath.reset();
        int halfWaveLen = mItemWaveLength / 2;
        mWavePath.moveTo(-mItemWaveLength + dx, mHeight - mWaveHeight);
        for (int i = -mItemWaveLength; i <= mWidth + mItemWaveLength; i += mItemWaveLength) {
            mWavePath.rQuadTo(halfWaveLen / 4, -mItemWaveLength / 3, halfWaveLen, 0);
            mWavePath.rQuadTo(halfWaveLen / 2, mItemWaveLength / 4, halfWaveLen, 0);
        }
        mWavePath.lineTo(mWidth, mHeight);
        mWavePath.lineTo(0, mHeight);
        mWavePath.close();
//        mWavePath.op(mCirclePath, Path.Op.INTERSECT);
        canvas.drawPath(mWavePath, mWavePaint);

        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMinimumFractionDigits(1);
        textCenter(new String[]{numberFormat.format(mWaveHeight * 1.0f / mHeight)}, mTextPaint, canvas, mPointF, Paint.Align.CENTER);
    }


    /**
     * 多行文本居中、居右、居左
     *
     * @param strings 文本字符串列表
     * @param paint   画笔
     * @param canvas  画布
     * @param point   点的坐标
     * @param align   居中、居右、居左
     */
    protected void textCenter(String[] strings, Paint paint, Canvas canvas, PointF point, Paint.Align align) {
        paint.setTextAlign(align);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        int length = strings.length;
        float total = (length - 1) * (-top + bottom) + (-fontMetrics.ascent + fontMetrics.descent);
        float offset = total / 2 - bottom;
        for (int i = 0; i < length; i++) {
            float yAxis = -(length - i - 1) * (-top + bottom) + offset;
            canvas.drawText(strings[i], point.x, point.y + yAxis, paint);
        }
    }


    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }


}
