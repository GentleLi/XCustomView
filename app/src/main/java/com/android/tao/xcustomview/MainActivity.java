package com.android.tao.xcustomview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.tao.xcustomview.ui.BezierActivity;
import com.android.tao.xcustomview.ui.BezierWaveActivity;
import com.android.tao.xcustomview.ui.TestViewActivity;
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
    @BindView(R.id.xbtn_view_test)
    XStrokeRoundRectButton mXBtnViewTest;
    @BindView(R.id.xbtn_refresh_view)
    XStrokeRoundRectButton mXBtnRefresh;
    @BindView(R.id.xbtn_bezier_view)
    XStrokeRoundRectButton mXBtnBezier;
    @BindView(R.id.xbtn_bezier_wave_view)
    XStrokeRoundRectButton mXBtnBezierWave;

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

        mXBtnViewTest.setBgColor(Color.RED, Color.GRAY);
        mXBtnViewTest.setBorder(2, Color.WHITE);
        mXBtnViewTest.setRadius(100);
        mXBtnViewTest.setClickEnable(true);

        mXBtnRefresh.setBgColor(Color.RED, Color.GRAY);
        mXBtnRefresh.setBorder(2, Color.WHITE);
        mXBtnRefresh.setRadius(100);
        mXBtnRefresh.setClickEnable(true);

        mXBtnBezier.setBgColor(Color.RED, Color.GRAY);
        mXBtnBezier.setBorder(2, Color.WHITE);
        mXBtnBezier.setRadius(100);
        mXBtnBezier.setClickEnable(true);

        mXBtnBezierWave.setBgColor(Color.RED, Color.GRAY);
        mXBtnBezierWave.setBorder(2, Color.WHITE);
        mXBtnBezierWave.setRadius(100);
        mXBtnBezierWave.setClickEnable(true);


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

    /**
     * 跳转到 拖动页面
     */
    @OnClick(R.id.xbtn_view_test)
    public void onClickXBtnViewTest(View view) {
        Intent intent = new Intent(MainActivity.this, TestViewActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到 拖动页面
     */
    @OnClick(R.id.xbtn_refresh_view)
    public void onClickXBtnRefresh(View view) {
        Intent intent = new Intent(MainActivity.this, TestViewActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到 BezierActivity
     */
    @OnClick(R.id.xbtn_bezier_view)
    public void onClickXBtnBezier(View view) {
        Intent intent = new Intent(MainActivity.this, BezierActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到 BezierActivity
     */
    @OnClick(R.id.xbtn_bezier_wave_view)
    public void onClickXBtnBezierWave(View view) {
        Intent intent = new Intent(MainActivity.this, BezierWaveActivity.class);
        startActivity(intent);
    }


}
