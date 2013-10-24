package com.example.demo.cases;

import android.content.Context;
import android.util.Log;
/**
 * 移位操作Demo
 */
public class CaseShiftOperation {

	private static CaseShiftOperation sCase;

	public static CaseShiftOperation obtain(Context context){
		if(sCase==null){
			sCase=new CaseShiftOperation();
		}
		return sCase;
	}

	private CaseShiftOperation(){

	}

	public void work(){
		int color=43;
		int leftshiftValue=color << 24;
		int multiplyValue=color*0x01000000;

		//这两个值是一样的
		Log.i("ertewu", "r28:"+leftshiftValue+"|"+multiplyValue);
	}
}
