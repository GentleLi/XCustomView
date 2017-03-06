package com.android.tao.xcustomview.bean;

import android.graphics.Rect;

/**
 * 树洞
 * Created by Jiantao on 2017/3/6.
 */

public class PlantHole {
    private Rect rect;
    private boolean isFull;
    private int pos;

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
