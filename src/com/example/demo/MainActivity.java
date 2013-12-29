package com.example.demo;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		CaseInvoke.invokeCase(this);
		// setContentView(R.layout.activity_main);
//		int i=getCombineId(1,138);
//        Log.i("ertewu","i is:"+i );
	}

    private int getCombineId(int groupPos,int childPos){
        String resultStr= ""+groupPos+""+childPos;
        return Integer.parseInt(resultStr);
    }

}
