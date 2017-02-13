package com.android.tao.xcustomview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.tao.xcustomview.utils.SizeUtils;
import com.android.tao.xcustomview.view.XRoundRectButton;
import com.android.tao.xcustomview.view.XRoundRectImageView;
import com.android.tao.xcustomview.view.XStrokeRoundRectButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_one)
    public XRoundRectButton mBtnOne;
    @BindView(R.id.btn_two)
    public XStrokeRoundRectButton mBtnTwo;
    @BindView(R.id.xiv_btn_add)
    public XRoundRectImageView mBtnAdd;

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
        mBtnOne.setBgColor(getColor(R.color.colorAccent), getColor(R.color.colorPrimary));
        mBtnOne.setRadius(SizeUtils.dp2px(40));
        mBtnOne.setClickEnable(true);
        mBtnTwo.setBgColor(Color.TRANSPARENT, getColor(R.color.colorPrimary));
        mBtnTwo.setBorder(SizeUtils.dp2px(2.5f), Color.WHITE);
        mBtnTwo.setRadius(SizeUtils.dp2px(40));
        mBtnTwo.setClickEnable(true);
        mBtnAdd.setBgColor(Color.YELLOW,Color.GRAY);
        mBtnAdd.setRadius(SizeUtils.dp2px(25));
        mBtnAdd.setBorder(SizeUtils.dp2px(1),Color.TRANSPARENT);
        mBtnAdd.setClickEnable(true);
    }

    /**
     * 初始化监听
     */
    private void initListener() {

    }


    @OnClick(R.id.btn_one)
    public void onClickBtnOne(View view){

        switch (view.getId()){
            case R.id.btn_one:
                Toast.makeText(this, "你点我了", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_two:
                Toast.makeText(this, "你点我了", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
