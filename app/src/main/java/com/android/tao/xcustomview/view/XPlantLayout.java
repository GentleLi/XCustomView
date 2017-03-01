package com.android.tao.xcustomview.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
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

    private Context mContext;
    private List<View> mViewList = new ArrayList<>();
    private View mCurrFocusView;
    private int mPlantHoleWidth = 230;
    private int mPlantHoleHeight = 230;
    private int mPlantWidth = 100;
    private int mPlantHeight = 100;
    //实例化Scroller对象，在自定义View中，mContext可以在自定义View的构造方法中获取
    private Scroller mScroller;
    private int mLastX;
    private int mLastY;
    Point mPointSource = new Point(0, 0);
    Point mPointDes = new Point(0, 0);
    private ArrayList<Rect> mPlantHoleRect = new ArrayList<>(4);
    private XScrollerImageView mPlantView;


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
        mScroller = new Scroller(mContext);
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
        mPlantView = new XScrollerImageView(mContext);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.width = mPlantWidth;
        params.height = mPlantHeight;
        params.leftMargin = left + (mPlantHoleWidth - params.width) / 2;
        params.topMargin = top + (mPlantHoleHeight - params.height) / 2;
        mPlantView.setLayoutParams(params);
//        mPlantView.setBackgroundColor(Color.GREEN);
        mPlantView.setImageResource(R.mipmap.diamond);
        addView(mPlantView);
        mPlantView.setOnTouchListener(new View.OnTouchListener() {//宠物拖动效果
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
//                    Log.e(TAG, "Touch:" + action);
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
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

    private void computeDes(View v) {
        int left = v.getLeft();
        int top = v.getTop();
        int right = v.getRight();
        int bottom = v.getBottom();
        for (int i = 0; i < mPlantHoleRect.size(); i++) {
            Rect rect = mPlantHoleRect.get(i);
            if (rect.contains(left, top) || rect.contains(left, bottom) || rect.contains(right, top) || rect.contains(right, bottom)) {
                mPointSource.x = left;
                mPointSource.y = top;
                mPointDes.x = rect.left;
                mPointDes.y = rect.top;
                startMoveAnim();
                break;
            }
        }
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
     */
    public void startMoveAnim() {
        ObjectAnimator obj = ObjectAnimator.ofFloat(mPlantView, "translationX", 0, (mPointDes.x + mPlantHoleWidth / 2) - (mPointSource.x + mPlantWidth / 2));
        ObjectAnimator obj2 = ObjectAnimator.ofFloat(mPlantView, "translationY", 0, (mPointDes.y + mPlantHoleHeight / 2) - (mPointSource.y + mPlantHeight / 2));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(obj, obj2);
        set.setDuration(300);
        set.start();
    }


    //在一个自定义View中实现该方法，方法名可以自定义
    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int dx = destX - scrollX;
        int dy = destY - scrollY;
        //前两个参数表示起始位置，第三第四个参数表示位移量，最后一个参数表示时间
        mScroller.startScroll(scrollX, scrollY, dx, dy, 1000);
        invalidate();
    }

    //自定义View中重写该方法
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }


}
