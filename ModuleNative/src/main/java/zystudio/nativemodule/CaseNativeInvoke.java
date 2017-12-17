package zystudio.nativemodule;

import android.os.Handler;
import android.os.Looper;
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
        showPthreadDemo1();
        showPthreadDemo2();
    }

    private void showCaseStringFromJNI(){
        String word=stringFromJNI();
    }

    private void showPthreadDemo1(){
        nativeThread();
    }

    private void showPthreadDemo2(){
        init();
        run(new LocalRunnable() {
            @Override
            public void run() {
                LogUtil.log("LocalRunnable occured:"+Thread.currentThread().getName());
            }
        });
        recycle();
    }

    public native String stringFromJNI();

    public native int nativeThread();

    public native void init();

    public native void run(LocalRunnable runnable);

    public native void recycle();
}
