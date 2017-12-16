package zystudio.nativemodule;

import android.util.Log;

import zystudio.mylib.utils.LogUtil;

/**
 * Created by zylab on 2017/11/12.
 */

public class CaseNativeInvoke {

    static {
        System.loadLibrary("native-lib");
    }


    public void work(){
        showCaseStringFromJNI();
    }

    private void showCaseStringFromJNI(){
        String word=stringFromJNI();
    }

    public native String stringFromJNI();
}
