package com.android.tao.xcustomview.impl;

import android.content.Context;
import android.util.Log;

import com.android.tao.xcustomview.storage.Storage;
import com.meituan.robust.Patch;
import com.meituan.robust.PatchManipulate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiantao on 2017/6/20.
 */

public class PatchManipulateImp extends PatchManipulate {
    public static final String TAG=PatchManipulateImp.class.getSimpleName();

    @Override
    protected List<Patch> fetchPatchList(Context context) {//此处获取补丁包
        Patch patch=new Patch();
        patch.setName("patch");
        patch.setLocalPath(Storage.HOT_FIX_DIR+"patch");
        patch.setPatchesInfoImplClassFullName("com.android.tao.xcustomview.PatchesInfoImpl");
        //请注意这里的设置
//        patch.setPatchesInfoImplClassFullName("com.meituan.robust.patch.PatchesInfoImpl");
        List<Patch> mPatchList=new ArrayList<>();
        mPatchList.add(patch);
        return mPatchList;
    }

    @Override
    protected boolean verifyPatch(Context context, Patch patch) {
        Log.e(TAG,"verifyPatch"+patch.toString());
        //do your verification, put the real patch to patch
        //放到app的私有目录
        patch.setTempPath(context.getCacheDir()+ File.separator+"robust"+File.separator + "patch");
        //in the sample we just copy the file
        try {
            copy(patch.getLocalPath(), patch.getTempPath());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("copy source patch to local patch error, no patch execute in path "+patch.getTempPath());
        }
        return true;
    }

    public void copy(String srcPath,String dstPath) throws IOException {
        File src=new File(srcPath);
        if(!src.exists()){
            throw new RuntimeException("source patch does not exist ");
        }
        File dst=new File(dstPath);
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();
        }
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }
    @Override
    protected boolean ensurePatchExist(Patch patch) {
        Log.e(TAG,"ensurePatchExist"+patch.toString());
        return true;
    }
}
