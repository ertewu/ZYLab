package com.example.demo;

import android.app.Activity;
import android.os.Bundle;

import com.example.demo.cases.anims.CaseForAnim1;
import com.example.demo.cases.annotationdemo.CaseForAnnotation;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		CaseForAnim1.getInstance(this).work();
//		CaseForAnnotation.getInstance(this).work();

		// CaseForDraw.getInstance(this).work();

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
