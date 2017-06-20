package com.android.tao.xcustomview.storage;

import android.os.Environment;

import java.io.File;

/**
 * Created by Jiantao on 2017/6/12.
 */

public class Storage {

    private static String ROOT_DIR = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"custom-view"+File.separator;//根目录
    public static String DOWNLOAD_DIR = ROOT_DIR+ "download";//下载目录
    public static String FILE_DIR = ROOT_DIR + "file" + File.separator;//文件目录
    public static String CACHE_DIR = ROOT_DIR+ "cache" + File.separator;//缓存目录
    public static String HOT_FIX_DIR = ROOT_DIR+ "hotfix" + File.separator;//缓存目录


}
