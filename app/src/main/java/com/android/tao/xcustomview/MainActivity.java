package com.android.tao.xcustomview;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.tao.xcustomview.impl.PatchManipulateImp;
import com.android.tao.xcustomview.storage.Storage;
import com.android.tao.xcustomview.ui.BezierActivity;
import com.android.tao.xcustomview.ui.BezierWaveActivity;
import com.android.tao.xcustomview.ui.RefreshActivity;
import com.android.tao.xcustomview.ui.TestViewActivity;
import com.android.tao.xcustomview.ui.ViewDragActivity;
import com.android.tao.xcustomview.ui.ViewPlantActivity;
import com.android.tao.xcustomview.utils.FileUtils;
import com.android.tao.xcustomview.view.XStrokeRoundRectButton;
import com.meituan.robust.Patch;
import com.meituan.robust.PatchExecutor;
import com.meituan.robust.RobustCallBack;
import com.meituan.robust.patch.annotaion.Modify;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String TAG=MainActivity.class.getSimpleName();

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
    @BindView(R.id.xbtn_hot_fix)
    XStrokeRoundRectButton mXBtnHotFix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        initView();
        initListener();
        initData();

    }

    private void initData() {//创建一个文件
        File file=new File(Storage.HOT_FIX_DIR+"test.txt");
        FileUtils.writeFileFromString(file,"gagaggaga",true);
    }

    /**
     * 初始化View控件
     */
    private void initView() {
        setRoundRectButton();
    }
    @Modify
    private void setRoundRectButton() {
        mXBtnDrag.setBgColor(Color.YELLOW, Color.GRAY);
        mXBtnDrag.setBorder(2, Color.WHITE);
        mXBtnDrag.setRadius(100);
        mXBtnDrag.setClickEnable(true);

        mXBtnPlant.setBgColor(Color.YELLOW, Color.GRAY);
        mXBtnPlant.setBorder(2, Color.WHITE);
        mXBtnPlant.setRadius(100);
        mXBtnPlant.setClickEnable(true);

        mXBtnViewTest.setBgColor(Color.YELLOW, Color.GRAY);
        mXBtnViewTest.setBorder(2, Color.WHITE);
        mXBtnViewTest.setRadius(100);
        mXBtnViewTest.setClickEnable(true);

        mXBtnRefresh.setBgColor(Color.YELLOW, Color.GRAY);
        mXBtnRefresh.setBorder(2, Color.WHITE);
        mXBtnRefresh.setRadius(100);
        mXBtnRefresh.setClickEnable(true);

        mXBtnBezier.setBgColor(Color.YELLOW, Color.GRAY);
        mXBtnBezier.setBorder(2, Color.WHITE);
        mXBtnBezier.setRadius(100);
        mXBtnBezier.setClickEnable(true);

        mXBtnBezierWave.setBgColor(Color.YELLOW, Color.GRAY);
        mXBtnBezierWave.setBorder(2, Color.WHITE);
        mXBtnBezierWave.setRadius(100);
        mXBtnBezierWave.setClickEnable(true);

        mXBtnHotFix.setBgColor(Color.YELLOW, Color.GRAY);
        mXBtnHotFix.setBorder(2, Color.WHITE);
        mXBtnHotFix.setRadius(100);
        mXBtnHotFix.setClickEnable(true);


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
        Intent intent = new Intent(MainActivity.this, RefreshActivity.class);
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

    /**
     * 跳转到 BezierActivity
     */
    @OnClick(R.id.xbtn_hot_fix)
    public void onClickXBtnHotFix(View view) {

        new PatchExecutor(getApplicationContext(), new PatchManipulateImp(), new RobustCallBack() {
            @Override
            public void onPatchListFetched(boolean result, boolean isNet, List<Patch> patches) {
                Log.e(TAG,"onPatchListFetched  patches:"+patches.toString());
            }

            @Override
            public void onPatchFetched(boolean result, boolean isNet, Patch patch) {
                Log.e(TAG,"onPatchFetched:"+patch.toString());
            }

            @Override
            public void onPatchApplied(boolean result, Patch patch) {
                Log.e(TAG,"onPatchApplied: "+result);
            }

            @Override
            public void logNotify(String log, String where) {
                Log.e(TAG,"logNotify: "+log);
            }

            @Override
            public void exceptionNotify(Throwable throwable, String where) {
                throwable.printStackTrace();
                Log.e(TAG,"exceptionNotify: "+where);
            }
        }).start();
    }


}
