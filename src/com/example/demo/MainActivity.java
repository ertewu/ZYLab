package com.example.demo;

import android.app.Activity;
import android.os.Bundle;

import com.example.demo.cases.CaseInterpolator;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        CaseInterpolator.obtain(this).work();

//        CaseViewConfiguration.obtain(this).work();

//        CaseBitMask.obtain().work();

//        CaseForTouch.obtain(this).work4();

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
