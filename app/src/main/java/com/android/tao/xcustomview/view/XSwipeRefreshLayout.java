package com.android.tao.xcustomview.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * 添加上拉加载更多的SwipeRefreshLayout
 * Created by Jiantao on 2017/3/31.
 */

public class XSwipeRefreshLayout extends SwipeRefreshLayout {

    private static final String TAG=XSwipeRefreshLayout.class.getSimpleName();
    private ListView mListView;
    private RecyclerView mRecyclerView;


    public XSwipeRefreshLayout(Context context) {
        super(context);
    }

    public XSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mRecyclerView==null){
            int count=getChildCount();
            if (count>0){
                View view=getChildAt(0);
                if (view instanceof RecyclerView){
                    mRecyclerView= (RecyclerView) view;
                    setRecyclerViewOnScroll(mRecyclerView);
                }
            }
        }

    }

    private int mDownY;
    private int mUpY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action=ev.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_CANCEL:


                break;
            case MotionEvent.ACTION_UP:


                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    private void setRecyclerViewOnScroll(RecyclerView recyclerView) {
        final LinearLayoutManager mLinearLayoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
        final StaggeredGridLayoutManager mStaggeredGridLayoutManager= (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
        mStaggeredGridLayoutManager.getSpanCount();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisiblePos=mLinearLayoutManager.findLastVisibleItemPosition();
//                mStaggeredGridLayoutManager.findLastCompletelyVisibleItemPositions()
                Log.e(TAG,"lastVisiblePos:"+lastVisiblePos);



            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }




}
