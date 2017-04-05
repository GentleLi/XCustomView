package com.android.tao.xcustomview.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.tao.xcustomview.R;
import com.android.tao.xcustomview.view.XPlantLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 实现植物的移动效果
 * Created by Jiantao on 2017/3/01.
 */

public class ViewPlantActivity extends AppCompatActivity {

    @BindView(R.id.lay_plant_container)
    XPlantLayout mLayPlantContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mLayPlantContainer.addCustomView(50, 30,0);
        mLayPlantContainer.addCustomView(450, 50,1);
        mLayPlantContainer.addCustomView(80, 500,2);
        mLayPlantContainer.addCustomView(570, 1200,3);
        mLayPlantContainer.addPlantView(0);
        mLayPlantContainer.addPlantView(1);
    }



}
