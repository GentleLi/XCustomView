package com.android.tao.xcustomview.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.android.tao.xcustomview.R;

/**
 * Created by jiantao on 2017/9/12.
 */

public class PathView extends View {

    private static final String TAG = PathView.class.getSimpleName();
    private Context mContext;
    private int mViewWidth;
    private int mViewHeight;
    private Paint mPaint;
    private Path mInnerCirclePath;
    private Path mOuterCirclePath;
    private Path mTriangleOnePath;
    private Path mTriangleTwoPath;
    private Path mDrawPath;
    private PathMeasure mPathMeasure;
    private DrawState mCurrState;
    private float distance;//当前执行动画的百分比取值 range：0~1
    private Handler mHandler;
    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener;
    private Animator.AnimatorListener mAnimatorListener;
    private ValueAnimator valueAnimator;


    public enum DrawState {
        CIRCLE, TRIANGLE, FINISHING
    }

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        initPaint();
        initPath();
        initHandler();
        initListener();
        initAnimator();
        mCurrState=DrawState.CIRCLE;
        valueAnimator.start();
    }

    private void initAnimator() {
        valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(3000);
        valueAnimator.addUpdateListener(mAnimatorUpdateListener);
        valueAnimator.addListener(mAnimatorListener);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth=w;
        mViewHeight=h;
        Log.d(TAG,"onSizeChanged:"+"  w="+w+"  h="+h);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG,"onDraw");
        canvas.drawColor(getResources().getColor(R.color.colorPrimary));
        canvas.save();
        canvas.translate(mViewWidth/2,mViewHeight/2);
        switch(mCurrState){
            case CIRCLE:
                mDrawPath.reset();
                mPathMeasure.setPath(mInnerCirclePath,false);
                mPathMeasure.getSegment(0,distance*mPathMeasure.getLength(),mDrawPath,true);
                canvas.drawPath(mDrawPath,mPaint);

                mPathMeasure.setPath(mOuterCirclePath,false);
                mDrawPath.reset();
                mPathMeasure.getSegment(0,distance*mPathMeasure.getLength(),mDrawPath,true);
                canvas.drawPath(mOuterCirclePath,mPaint);
                break;
            case TRIANGLE:
                canvas.drawPath(mInnerCirclePath,mPaint);
                canvas.drawPath(mOuterCirclePath,mPaint);
                mDrawPath.reset();
                mPathMeasure.setPath(mTriangleOnePath,false);
                float stopD=distance*mPathMeasure.getLength();
                float startD=stopD-(0.5f-Math.abs(0.5f-distance)*200);
                mPathMeasure.getSegment(startD,stopD,mDrawPath,true);
                canvas.drawPath(mDrawPath,mPaint);
                mDrawPath.reset();
                mPathMeasure.setPath(mTriangleTwoPath,false);
                mPathMeasure.getSegment(startD,stopD,mDrawPath,true);
                canvas.drawPath(mDrawPath,mPaint);
                break;
            case FINISHING:
                canvas.drawPath(mInnerCirclePath,mPaint);
                canvas.drawPath(mOuterCirclePath,mPaint);
                mDrawPath.reset();
                mPathMeasure.setPath(mTriangleOnePath,false);
                mPathMeasure.getSegment(0,distance*mPathMeasure.getLength(),mDrawPath,true);
                canvas.drawPath(mDrawPath,mPaint);
                mDrawPath.reset();
                mPathMeasure.setPath(mTriangleTwoPath,false);
                mPathMeasure.getSegment(0,distance*mPathMeasure.getLength(),mDrawPath,true);
                canvas.drawPath(mDrawPath,mPaint);
                break;
            default:
                Log.d(TAG,"state no match in method onDraw()");
                break;
        }
        canvas.restore();

    }

    private void initListener() {
        mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener(){
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                distance= (float) animation.getAnimatedValue();
                invalidate();
            }
        };

        mAnimatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG,"onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG,"onAnimationEnd");
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d(TAG,"onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d(TAG,"onAnimationRepeat");
            }
        };

    }



    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (mCurrState) {
                    case CIRCLE:
                        mCurrState = DrawState.TRIANGLE;
                        valueAnimator.start();
                        break;
                    case TRIANGLE:
                        mCurrState = DrawState.FINISHING;
                        valueAnimator.start();
                        break;
                    case FINISHING:
                        break;
                    default:
                        Log.d(TAG, "state no match");
                        break;
                }

            }
        };
    }

    private void initPath() {
        mInnerCirclePath = new Path();
        mOuterCirclePath = new Path();
        mTriangleOnePath = new Path();
        mTriangleTwoPath = new Path();
        mDrawPath = new Path();

        mPathMeasure = new PathMeasure();
        RectF innerRect = new RectF(-220, -220, 220, 220);
        RectF outerRect = new RectF(-280, -280, 280, 280);
        mInnerCirclePath.addArc(innerRect, 150, -359.9f);
        mOuterCirclePath.addArc(outerRect, 60, -359.9f);
        mPathMeasure.setPath(mInnerCirclePath, false);

        float[] pos = new float[2];
        mPathMeasure.getPosTan(0, pos, null);
        mTriangleOnePath.moveTo(pos[0], pos[1]);
        mPathMeasure.getPosTan((1.0f / 3) * mPathMeasure.getLength(), pos, null);
        Log.d(TAG, "pos[0]:" + pos[0] + "  pos[1]:" + pos[1]);

        mTriangleOnePath.lineTo(pos[0], pos[1]);
        mPathMeasure.getPosTan((2.0f / 3) * mPathMeasure.getLength(), pos, null);
        mTriangleOnePath.moveTo(pos[0], pos[1]);
        mTriangleOnePath.close();

        mPathMeasure.getPosTan((2.0f / 3) * mPathMeasure.getLength(), pos, null);
        Matrix matrix = new Matrix();
        matrix.postRotate(-180);
        mTriangleOnePath.transform(matrix, mTriangleTwoPath);


    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        mPaint.setShadowLayer(15, 0, 0, Color.WHITE);
    }


}
