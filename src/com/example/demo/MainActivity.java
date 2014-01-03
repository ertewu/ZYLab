package com.example.demo;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	private AnimImageView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.caseforanimview);
		view = (AnimImageView) findViewById(R.id.anim_view);
		// CaseInvoke.invokeCase(this);
	}
}
