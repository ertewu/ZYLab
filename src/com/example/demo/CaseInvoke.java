package com.example.demo;

import android.app.Activity;

import com.example.demo.cases.CaseForRegex;
import com.example.demo.cases.custview.CaseDecorMeasureInfo;

public class CaseInvoke {
    public static void invokeCase(Activity activity) {

        CaseDecorMeasureInfo.instance(activity).work();
        CaseForRegex.obtain().work();
        // CaseDrawables.obtain(activity).work();
        // CaseJavaContainer.obtain().work();
        // CaseCanvas.obtain(activity).work();
        // CaseSpecialDrawable.obtain(activity).work();
        // CaseDownloadActivity.start(activity);
        // CaseGson.obtain(activity).work();
        // CaseBitmapOperate.obtain(activity).work();
        // CaseForLinkedHashMap.instance(activity).work();
        // CaseCreateTempFile.showDemo(activity);
        // CaseForViewRootImpl.getInstance(activity).work();
        // CaseShiftOperation.obtain(activity).work();
        // CaseForCustomAnim1.obtain(activity).work();
        // CaseThreadPriority.getInstance(activity).work();
        // CaseThreadJoin.getInstance(activity).work();
        // CaseForMath.obtain().work();

        // CaseForCustViewAttr.obtain(activity).work();

        // CaseForFinal.getInstance(activity).work();

        // CaseForTryFinally.obtain().work();
        // CaseForResourceUri.obtain().work(activity);

        // CaseForNullInvoke.obtain().work();

        // CaseForFinal.getInstance(activity).work();

        // CaseForAnim1.getInstance(activity).work();
        // CaseForAnnotation.getInstance(this).work();

        // CaseForDraw.getInstance(activity).work();

        // CaseForDraw.getInstance(activity).work();

        // CaseInterpolator.obtain(this).work();

        // CaseViewConfiguration.obtain(this).work();

        // CaseBitMask.obtain().work();

        // CaseForTouch.obtain(this).work4();

        // CaseExecutor.getInstance().work(0);

        // CaseObserver.getInstance().work();

        // (new Handler()).postDelayed(new Runnable(){
        // @Override
        // public void run() {
        // CaseDefaultBrowser.getInstance().work1(MainActivity.this);
        // }
        // }, 1000);

        // CaseDecorator.getInstance().work();

        // String str= CaseForJson.assembleMsgIdArrayStr("wangzhengyu");
        // Log.i("ertewu","str is:"+str);
    }
}
