package com.android.tao.xcustomview.impl;

import android.content.Context;
import android.util.Log;

import com.android.tao.xcustomview.storage.Storage;
import com.meituan.robust.Patch;
import com.meituan.robust.PatchManipulate;

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
        List<Patch> mPatchList=new ArrayList<>();
        mPatchList.add(patch);
        return mPatchList;
    }

    @Override
    protected boolean verifyPatch(Context context, Patch patch) {
        Log.e(TAG,"verifyPatch"+patch.toString());
        return true;
    }

    @Override
    protected boolean ensurePatchExist(Patch patch) {
        Log.e(TAG,"ensurePatchExist"+patch.toString());
        return true;
    }
}
