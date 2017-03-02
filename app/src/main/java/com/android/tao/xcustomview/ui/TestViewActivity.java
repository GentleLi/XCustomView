package com.android.tao.xcustomview.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.android.tao.xcustomview.R;
import com.android.tao.xcustomview.view.XPlantLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 实现植物的移动效果
 * Created by Jiantao on 2017/3/01.
 */

public class TestViewActivity extends AppCompatActivity {

    @BindView(R.id.btn_move)
    Button mBtnMove;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);
        ButterKnife.bind(this);
        initView();

    }

    /**
     * initial method
     */
    private void initView() {


    }

    @OnClick(R.id.btn_move)
    public void onClickMove() {

        mBtnMove.layout(100, 300, 500, 500);

    }


}
