package zystudio.demo.cases.prepare;

import android.util.Log;
import android.view.View.MeasureSpec;

public class CaseForMeasureSpec {


    public static void makeMeasureSpecDemo(){

    }


    /**
     * 我记得是要有与或的，不是这样用的
     */
    public static void showMeasureSpec() {
        StringBuilder logStr = new StringBuilder();
        logStr.append("MeasureSpec.EXACTLY:" + MeasureSpec.EXACTLY + "\n");
        logStr.append("MeasureSpec.UNSPECIFIED:" + MeasureSpec.UNSPECIFIED + "\n");
        logStr.append("MeasureSpec.AT_MOST:" + MeasureSpec.AT_MOST + "\n");
        logStr.append("-----------------------------");

        Log.i("ertewu", "MeasureSpec" + logStr.toString());
    }
}
