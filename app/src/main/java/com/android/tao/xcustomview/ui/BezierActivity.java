package com.android.tao.xcustomview.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.tao.xcustomview.R;
import com.android.tao.xcustomview.view.BezierView;

import butterknife.BindView;

/**
 * Created by administrato on 2017/3/31.
 */

public class BezierActivity extends AppCompatActivity {


    @BindView(R.id.bezier_view)
    BezierView mBezierView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);



    }





}
