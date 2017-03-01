package com.android.tao.xcustomview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.tao.xcustomview.ui.ViewDragActivity;
import com.android.tao.xcustomview.ui.ViewPlantActivity;
import com.android.tao.xcustomview.view.XStrokeRoundRectButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.xbtn_drag_activity)
    XStrokeRoundRectButton mXBtnDrag;
    @BindView(R.id.xbtn_plant_activity)
    XStrokeRoundRectButton mXBtnPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initListener();

    }

    /**
     * 初始化View控件
     */
    private void initView() {
        setRoundRectButton();
    }

    private void setRoundRectButton() {
        mXBtnDrag.setBgColor(Color.RED, Color.GRAY);
        mXBtnDrag.setBorder(2, Color.WHITE);
        mXBtnDrag.setRadius(100);
        mXBtnDrag.setClickEnable(true);

        mXBtnPlant.setBgColor(Color.RED, Color.GRAY);
        mXBtnPlant.setBorder(2, Color.WHITE);
        mXBtnPlant.setRadius(100);
        mXBtnPlant.setClickEnable(true);

    }

    /**
     * 初始化监听
     */
    private void initListener() {
    }

    /**
     * 跳转到 拖动页面
     */
    @OnClick(R.id.xbtn_drag_activity)
    public void onClickXBtnDrag(View view) {
        Intent intent = new Intent(MainActivity.this, ViewDragActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到 拖动页面
     */
    @OnClick(R.id.xbtn_plant_activity)
    public void onClickXBtnPlant(View view) {
        Intent intent = new Intent(MainActivity.this, ViewPlantActivity.class);
        startActivity(intent);
    }


}
