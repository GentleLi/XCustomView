package com.android.tao.xcustomview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Scroller;

/**
 * Created by administrato on 2017/3/1.
 */

public class XScrollerImageView extends ImageView {

    private Context mContext;
    private int mLastX;
    private int mLastY;

    public XScrollerImageView(Context context) {
        this(context, null);
    }

    public XScrollerImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XScrollerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
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
                int left = getLeft() + dx;
                int top = getTop() + dy;
                int right = getRight() + dx;
                int bottom = getBottom() + dy;
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
                layout(left, top, right, bottom);
                mLastX = (int) event.getRawX();
                mLastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    /**
     * init method
     */
    private void init() {

    }


}
