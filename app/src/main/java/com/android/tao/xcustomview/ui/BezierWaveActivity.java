package com.android.tao.xcustomview.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.android.tao.xcustomview.R;
import com.android.tao.xcustomview.view.MyBezierView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jiantao on 2017/4/1.
 */

public class BezierWaveActivity extends AppCompatActivity {


    @BindView(R.id.my_bezier_view)
    MyBezierView mBezierView;
    @BindView(R.id.btn_add_percent)
    Button mBtnAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_wave);
        ButterKnife.bind(this);

        mBezierView.startAnim();
    }


    @OnClick(R.id.btn_add_percent)
    public void OnClickAddPercent(){
        mBezierView.addWaveHeight(1);
    }


}
