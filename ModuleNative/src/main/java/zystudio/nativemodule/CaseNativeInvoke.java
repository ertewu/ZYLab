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
        //PthreadDemo2 是个实验，有用但是会crash，要写运行后边的代码先把这个注释掉
//        showPthreadDemo2();

        Person person=new Person();
        getPersonInfoByIndex(person,2);
        LogUtil.log("Person toString is:"+person.toString());
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

    private native int getPersonInfoByIndex(Person person, int index);
}
