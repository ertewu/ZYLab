package com.example.demo.cases.bitmap;

import android.app.Activity;

public class CaseBitmapOperate {

    private static CaseBitmapOperate sCase;
    private Activity mActivity;

    public static CaseBitmapOperate obtain(Activity act) {
        if (sCase == null) {
            sCase = new CaseBitmapOperate();
        }
        return sCase;
    }

    private void init(Activity activity) {
        this.mActivity = activity;
    }

    public void work() {

    }
}
