package com.android.tao.xcustomview.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.android.tao.xcustomview.R;
import com.android.tao.xcustomview.adapter.MyRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 实现植物的移动效果
 * Created by Jiantao on 2017/3/01.
 */

public class RefreshActivity extends AppCompatActivity {

    private static final int REFRESH_UI=100;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private List<String> mDataList = new ArrayList<>(10);
    private MyRecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();
    }

    private void initData() {
        for (int i = 0; i < 50; i++) {
            mDataList.add("This is item " + i);
        }
    }

    public void getMoreData(){
        int size=mDataList.size();
        for (int i = 0; i < 20; i++) {
            mDataList.add("This is item " + (size+i));
        }
    }

    /**
     * initial method
     */
    private void initView() {
        // 设置下拉进度的背景颜色，默认就是白色的
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerAdapter = new MyRecyclerAdapter(getLayoutInflater());
        mRecyclerAdapter.setData(mDataList);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }


    private void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMoreData();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });
    }

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case REFRESH_UI:
                    mRecyclerAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

}
