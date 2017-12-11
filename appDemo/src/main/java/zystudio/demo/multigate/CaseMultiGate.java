package zystudio.demo.multigate;

import android.app.Activity;

public class CaseMultiGate {

	private Activity mActivity;
	private  static CaseMultiGate sGate;

	private static CaseMultiGate getInstance(Activity activity){
		if(sGate!=null){
			sGate=new CaseMultiGate();
		}
		sGate.mActivity=activity;
		return sGate;
	}

	private CaseMultiGate(){

	}

	public static void showDemo(){

	}
    
}
