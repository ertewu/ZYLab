package com.example.demo;

import android.app.Activity;

import com.example.demo.cases.CaseForCustomAnim1;

public class CaseInvoke {
    public static void invokeCase(Activity activity) {

        // CaseShiftOperation.obtain(activity).work();
        CaseForCustomAnim1.obtain(activity).work();
        // CaseThreadPriority.getInstance(activity).work();
        // CaseForMath.obtain().work();

        // CaseForCustViewAttr.obtain(activity).work();

        // CaseForFinal.getInstance(activity).work();

        // CaseForTryFinally.obtain().work();
        // CaseForUri.obtain().work(activity);

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
