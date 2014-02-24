package com.example.demo.cases.bitmap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.demo.R;

public class CaseBitmapOperate {

    private static CaseBitmapOperate sCase;
    private Activity mActivity;

    public static CaseBitmapOperate obtain(Activity act) {
        if (sCase == null) {
            sCase = new CaseBitmapOperate();
            sCase.init(act);
        }
        return sCase;
    }

    private void init(Activity activity) {
        this.mActivity = activity;
    }

    public void work() {

    }

    private void testBitmapDensity() {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        Bitmap bit = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.motor);

    }

    private void generateBitmap() {

    }

    private void makeBitmap() {

    }
}
