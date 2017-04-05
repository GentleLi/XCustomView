package com.android.tao.xcustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jiantao on 2017/4/1.
 */

public class MaskView extends View {

    private static final String TAG = MaskView.class.getSimpleName();
    private int mWidth;
    private int mHeight;
    private int mRealWidth;
    private int mRealHeight;
    private float mRadius;
    private Paint mCirclePaint;
    private Paint mMaskPaint;
    private Path mCirclePath;
    private Path mMaskPath;


    public MaskView(Context context) {
        this(context, null);
    }

    public MaskView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaskView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(Color.TRANSPARENT);

        mMaskPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMaskPaint.setColor(Color.DKGRAY);

        mCirclePath = new Path();
        mMaskPath = new Path();

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        mRealWidth = mWidth - getPaddingLeft() - getPaddingRight();
        mRealHeight = mHeight - getPaddingTop() - getPaddingBottom();

        mRadius = Math.min(mRealWidth, mRealHeight) / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawColor(Color.TRANSPARENT);
        mMaskPath.addRect(-mWidth/2,-mHeight/2,mWidth,mHeight, Path.Direction.CCW);
        mCirclePath.addCircle(0,0,mRadius, Path.Direction.CCW);
        mMaskPath.op(mCirclePath, Path.Op.DIFFERENCE);
        canvas.drawPath(mMaskPath,mMaskPaint);


    }


}


