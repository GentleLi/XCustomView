package com.android.tao.xcustomview.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.android.tao.xcustomview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiantao on 2017/3/1.
 */

public class XPlantLayout extends RelativeLayout {

    private static final String TAG = XPlantLayout.class.getSimpleName();
    private Context mContext;
    private List<View> mViewList = new ArrayList<>();
    private View mCurrFocusView;
    private int mPlantHoleWidth = 230;
    private int mPlantHoleHeight = 230;
    private int mPlantWidth = 100;
    private int mPlantHeight = 100;
    Point mPointSource = new Point(0, 0);
    Point mPointDes = new Point(0, 0);
    private ArrayList<Rect> mPlantHoleRect = new ArrayList<>(4);
    private Rect mCurrDesRect = new Rect(0, 0, 0, 0);


    public XPlantLayout(Context context) {
        this(context, null);
    }

    public XPlantLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XPlantLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    /**
     * init method
     */
    private void init() {
    }

    /**
     * 添加自定义的View
     */
    public void addCustomView(int left, int top) {
        RelativeLayout.LayoutParams plantHoleLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        plantHoleLayoutParams.leftMargin = left;
        plantHoleLayoutParams.topMargin = top;
        /*mLayPlantParams.width = 300;
        mLayPlantParams.height = 300;*/
        RelativeLayout plantHole = new RelativeLayout(mContext);
        plantHole.setLayoutParams(plantHoleLayoutParams);
        View image = getPlantHoleView(mPlantHoleWidth, mPlantHoleHeight);
        plantHole.addView(image);
        addView(plantHole);
        Rect rect = new Rect(left, top, left + mPlantHoleWidth, top + mPlantHoleHeight);
        mPlantHoleRect.add(rect);///**记录每一株植物的位置*/
    }

    /**
     * 添加一株植物
     */
    public void addPlantView(int left, int top) {
        ImageView plant = new ImageView(mContext);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.width = mPlantWidth;
        params.height = mPlantHeight;
        params.leftMargin = left + (mPlantHoleWidth - params.width) / 2;
        params.topMargin = top + (mPlantHoleHeight - params.height) / 2;
        mCurrDesRect.set(left, top, left + mPlantHoleWidth, top + mPlantHoleHeight);
        plant.setLayoutParams(params);
        plant.setImageResource(R.mipmap.diamond);
        addView(plant);
        plant.setOnTouchListener(new View.OnTouchListener() {//宠物拖动效果
            private int mLastX;
            private int mLastY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getAction();
//                    Log.e(TAG, "Touch:" + action);
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        mCurrDesRect.set(v.getLeft() - (mPlantHoleWidth - mPlantWidth) / 2, v.getTop() - (mPlantHoleHeight - mPlantHeight) / 2, v.getRight() + (mPlantHoleWidth - mPlantWidth) / 2, v.getBottom() + (mPlantHoleHeight - mPlantHeight) / 2);//重新设置当前植物的初始位置，因为现在有两株植物，所以拖动那一株，初始位置就要设置为当前植物的位置
                        mLastX = (int) event.getRawX();
                        mLastY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = (int) event.getRawX() - mLastX;
                        int dy = (int) event.getRawY() - mLastY;
                        int left = v.getLeft() + dx;
                        int top = v.getTop() + dy;
                        int right = v.getRight() + dx;
                        int bottom = v.getBottom() + dy;
//                            if (left < 0) {
//                                left = 0;
//                                right = left + v.getWidth();
//                            }
//                            if (right > mScreenWidth) {
//                                right = mScreenWidth;
//                                left = right - v.getWidth();
//                            }
//                            if (top < 0) {
//                                top = 0;
//                                bottom = top + v.getHeight();
//                            }
//                            if (bottom > (mScreenHeight - bottom)) {
//                                bottom = (mScreenHeight - bottom);
//                                top = bottom - v.getHeight();
//                            }
                        v.layout(left, top, right, bottom);
                        mLastX = (int) event.getRawX();
                        mLastY = (int) event.getRawY();
                        Log.e(TAG, "mLastX==" + mLastX);
                        Log.e(TAG, "mLastX==" + mLastX);
                        break;
                    case MotionEvent.ACTION_UP:
                        computeDes(v);
//                        startMoveAnim();
                        break;
                }
                return true;
            }
        });


    }

    /**
     * 计算 植物移动的目标位置
     *
     * @param v
     */
    private void computeDes(View v) {
        int left = v.getLeft();
        int top = v.getTop();
        int right = v.getRight();
        int bottom = v.getBottom();
        Log.e(TAG, "left==" + left);
        Log.e(TAG, "top==" + top);
        mPointSource.x = left;
        mPointSource.y = top;
        for (int i = 0; i < mPlantHoleRect.size(); i++) {
            Rect rect = mPlantHoleRect.get(i);
            if (rect.contains(left, top) || rect.contains(left, bottom) || rect.contains(right, top) || rect.contains(right, bottom)) {
                mCurrDesRect.set(rect.left, rect.top, rect.right, rect.bottom);
                break;
            }
        }
//        Log.e(TAG, "植物回到原来的位置");
        mPointDes.x = mCurrDesRect.left;
        mPointDes.y = mCurrDesRect.top;
        startMoveAnim(v);
    }


    /**
     * 获取一株植物洞穴
     */
    private ImageView getPlantHoleView(int width, int height) {
        ImageView mPlant = new ImageView(mContext);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.width = width;
        params.height = height;
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        mPlant.setLayoutParams(params);
        mPlant.setImageResource(R.mipmap.btn_wy_bg);
        return mPlant;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.w(TAG, "getWidth()==" + getWidth() + "getHeight()==" + getHeight());
    }


    /**
     * 添加一个View集合
     */

    public void addViewList(List<View> viewList) {

    }

    public void setCurrFocusView(View currFocusView) {
        this.mCurrFocusView = currFocusView;
    }

    public View getCurrFocusView() {
        return mCurrFocusView;
    }

    /**
     * 设置当前要移动到的目标
     */
    public void setDestination() {
//        mPointDes.x = mPoints.get()
    }

    /**
     * 开始移动的动画
     *
     * @param v
     */

    public void startMoveAnim(View v) {
        v.layout(mCurrDesRect.left + mPlantHoleWidth / 2 - mPlantWidth / 2, mCurrDesRect.top + mPlantHoleHeight / 2 - mPlantHeight / 2, mCurrDesRect.left + mPlantHoleWidth / 2 + mPlantWidth / 2, mCurrDesRect.top + mPlantHoleHeight / 2 + mPlantHeight / 2);
        /*ObjectAnimator obj = ObjectAnimator.ofFloat(mPlantView, "translationX", (mPointDes.x + mPlantHoleWidth / 2) - (mPointSource.x + mPlantWidth / 2));
        ObjectAnimator obj2 = ObjectAnimator.ofFloat(mPlantView, "translationY", (mPointDes.y + mPlantHoleHeight / 2) - (mPointSource.y + mPlantHeight / 2));
        Log.e(TAG, "translationX ==" + ((mPointDes.x + mPlantHoleWidth / 2) - (mPointSource.x + mPlantWidth / 2)));
        Log.e(TAG, "translationY ==" + ((mPointDes.y + mPlantHoleHeight / 2) - (mPointSource.y + mPlantHeight / 2)));
        AnimatorSet set = new AnimatorSet();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                *//*mLastX = 0;
                mLastY = 0;*//*

//                mPlantView.layout(mCurrDesRect.left,mCurrDesRect.top,mCurrDesRect.right,mCurrDesRect.bottom);

            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        set.playTogether(obj, obj2);
        set.setDuration(300);
        set.start();*/
    }

}
