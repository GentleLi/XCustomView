package com.android.tao.xcustomview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tao.xcustomview.base.BaseRecyclerAdapter;

/**
 * Created by Jiantao on 2017/3/31.
 */

public class MyRecyclerAdapter extends BaseRecyclerAdapter<String, MyRecyclerAdapter.StringViewHolder> {


    public MyRecyclerAdapter(LayoutInflater mInflater) {
        super(mInflater);
    }

    @Override
    public StringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(StringViewHolder holder, int position) {

        super.onBindViewHolder(holder, position);
    }


    public static class StringViewHolder extends RecyclerView.ViewHolder {

        public StringViewHolder(View itemView) {
            super(itemView);
        }
    }

}
