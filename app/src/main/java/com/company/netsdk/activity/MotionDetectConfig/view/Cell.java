package com.company.netsdk.activity.MotionDetectConfig.view;

import android.graphics.RectF;

/**
 * Created by 32940 on 2018/8/23.
 */
public class Cell {
    public RectF rectF;
    public boolean  choose = false;

    public Cell(RectF rectF,boolean choose)
    {
        this.rectF = rectF;
        this.choose = choose;
    }
}
