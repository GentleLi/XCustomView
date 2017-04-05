package com.android.tao.xcustomview.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.android.tao.xcustomview.R;
import com.android.tao.xcustomview.view.BezierView;
import com.android.tao.xcustomview.view.TBezierView;
import com.android.tao.xcustomview.view.XBezierView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jiantao on 2017/3/31.
 */

public class BezierActivity extends AppCompatActivity {


    @BindView(R.id.bezier_view)
    TBezierView mBezierView;
    @BindView(R.id.btn_add_percent)
    Button mBtnPercent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);
        ButterKnife.bind(this);




    }


    @OnClick(R.id.btn_add_percent)
    public void onClickPercent() {
        mBezierView.addPercent(0.01f);


    }


}
