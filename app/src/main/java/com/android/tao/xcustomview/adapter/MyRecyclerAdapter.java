package com.android.tao.xcustomview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.tao.xcustomview.R;
import com.android.tao.xcustomview.base.BaseRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jiantao on 2017/3/31.
 */

public class MyRecyclerAdapter extends BaseRecyclerAdapter<String, MyRecyclerAdapter.StringViewHolder> {

    public MyRecyclerAdapter(LayoutInflater mInflater) {
        super(mInflater);
    }

    @Override
    public StringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StringViewHolder(mInflater.inflate(R.layout.rv_item_one,null));
    }

    @Override
    public void onBindViewHolder(StringViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (null==datas)return;
        String str=datas.get(position);
        holder.mTxtItem.setText(str);
    }


    public static class StringViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item)
        TextView mTxtItem;
        public StringViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
