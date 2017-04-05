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

import com.android.tao.xcustomview.utils.LogUtils;

import java.text.NumberFormat;

/**
 * w
 * test Bezier Curve
 * Created by Jiantao on 2017/4/1.
 */

public class XBezierView extends View {

    private static final String TAG = XBezierView.class.getSimpleName();

    private Paint mPaint, mPaint2;
    private Path mPath = new Path();
    protected int mViewWidth, mViewHeight;
    protected int mWidth, mHeight;
    private float r, rArc, x;
    private float mPercent = 0.0f;
    private RectF rectF;
    private PointF mPointF = new PointF(0, 0);

    public XBezierView(Context context) {
        this(context, null);
    }

    public XBezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(100);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.CYAN);
        mPaint2.setStrokeWidth(8);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    public void setPercent(float percent) {
        this.mPercent = percent;
        invalidate();
    }

    /**
     * 增加 百分比
     *
     * @param increment
     */
    public void addPercent(float increment) {
        this.mPercent += increment;
        if (mPercent > 1.0f) {
            mPercent -= 1.0f;
        }
        setPercent(mPercent);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LogUtils.i(TAG, "onSizeChanged");
        mViewWidth = w;
        mViewHeight = h;

        mWidth = mViewWidth - getPaddingLeft() - getPaddingRight();
        mHeight = mViewHeight - getPaddingTop() - getPaddingBottom();

        r = Math.min(mWidth, mHeight) * 0.4f;
        rectF = new RectF(-r, -r, r, r);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        LogUtils.i(TAG, "onDraw");
        canvas.translate(mViewWidth / 2, mViewHeight / 2);
        canvas.drawCircle(0, 0, r, mPaint);

        Path pathCircle = new Path();
        pathCircle.addCircle(0, 0, r, Path.Direction.CCW);

        rArc = r * (1 - 2 * mPercent);
        double angle = Math.acos((double) rArc / r);
        x = r * (float) Math.sin(angle);

      /*  Path rectPath = new Path();
        rectPath.moveTo(-x, rArc);
        rectPath.lineTo(-r, r);
        rectPath.lineTo(r, r);
        rectPath.lineTo(x, rArc);
        canvas.drawPath(rectPath, mPaint);*/

        mPath.addArc(rectF, 90 - (float) Math.toDegrees(angle), (float) Math.toDegrees(angle) * 2);
        mPath.moveTo(-x, rArc);
        mPath.rQuadTo(x / 2, -r / 8, x, 0);
        mPath.rQuadTo(x / 2, r / 8, x, 0);
        mPath.op(pathCircle, Path.Op.INTERSECT);
        canvas.drawPath(mPath, mPaint2);
        mPath.rewind();
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMinimumFractionDigits(1);
        textCenter(new String[]{numberFormat.format(mPercent)}, mPaint, canvas, mPointF, Paint.Align.CENTER);
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
}
