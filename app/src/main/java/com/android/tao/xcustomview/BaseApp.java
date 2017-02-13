package com.android.tao.xcustomview;

import android.app.Application;

import com.android.tao.xcustomview.utils.Utils;

/**
 * Created by administrato on 2016/12/21.
 */

public class BaseApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
