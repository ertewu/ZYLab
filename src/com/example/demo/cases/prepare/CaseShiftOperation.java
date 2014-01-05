package com.example.demo.cases.prepare;

import android.content.Context;
import android.util.Log;

/**
 * 移位操作Demo
 */
public class CaseShiftOperation {

    private static CaseShiftOperation sCase;

    public static CaseShiftOperation obtain(Context context) {
        if (sCase == null) {
            sCase = new CaseShiftOperation();
        }
        return sCase;
    }

    private CaseShiftOperation() {

    }

    public void work() {
        int color = 43;
        int leftshiftValue = color << 24;
        int multiplyValue = color * 0x01000000;

        // 这两个值是一样的
        Log.i("ertewu", "r28:" + leftshiftValue + "|" + multiplyValue);
    }

    /**
     * 在现实的例子中，有好多左移与右移的应用，比如： <br/>
     * MeasureSpec 类<br>
     * argbevaluator的类中的颜色的移位<br>
     * 还有我让改变颜色aphla那个24移位</br> 我想知道那些东西是怎么运作的
     */

    /**这个是找MeasureSpec的shift移位*/
    public void bitShiftMeasureDemo() {

    }

    public void bitShiftARGBValuatorDemo(){

    }
}
