package com.android.tao.xcustomview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.tao.xcustomview.ui.ViewDragActivity;
import com.android.tao.xcustomview.utils.SizeUtils;
import com.android.tao.xcustomview.view.CircleProgress;
import com.android.tao.xcustomview.view.XRoundRectButton;
import com.android.tao.xcustomview.view.XRoundRectImageView;
import com.android.tao.xcustomview.view.XStrokeRoundRectButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.xbtn_drag_activity)
    XStrokeRoundRectButton mXBtnDrag;

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
        mXBtnDrag.setBgColor(Color.RED,Color.GRAY);
        mXBtnDrag.setBorder(2,Color.WHITE);
        mXBtnDrag.setRadius(30);
        mXBtnDrag.setClickEnable(true);

    }

    /**
     * 初始化监听
     */
    private void initListener() {
    }

    /** 跳转到 拖动页面 */
    @OnClick(R.id.xbtn_drag_activity)
    public void onClickXBtnDrag(View view){
        Intent intent=new Intent(MainActivity.this, ViewDragActivity.class);
        startActivity(intent);
    }

}
