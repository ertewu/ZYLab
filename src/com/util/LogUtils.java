package com.util;

import android.util.Log;

public class LogUtils {

    public static void log(Object str) {
        Log.i("ertewu", "" + str);
    }

    /**
     * 能打印出当前函数的invoke，继而看出问题来.. 这个是我自己写的..
     *
     * @param tag
     */
    public static void footPrint(String tag) {
        String msgToPrint = Thread.currentThread().getId() + "|" + "."
                + Thread.currentThread().getStackTrace()[4].getMethodName()
                + "|"
                + Thread.currentThread().getStackTrace()[5].getMethodName()
                + "|"
                + Thread.currentThread().getStackTrace()[6].getMethodName();
        Log.i("ertewu", tag + ":" + msgToPrint);
    }

    /**
     * 这个footPrint是从项目中拿出来的，应该是最好用的
     */
    public static void footPrint2() {
        String className = Thread.currentThread().getStackTrace()[3]
                .getClassName();
        int index = className.lastIndexOf(".");
        if (index > -1) {
            className = className.substring(index + 1);
        }
        String msgToPrint = Thread.currentThread().getId() + " " + className
                + "."
                + Thread.currentThread().getStackTrace()[3].getMethodName();
        Log.i("ertewu", msgToPrint);
    }

    /**
     * 这个是意思我不懂为什么是Thread.currentThread.getStackTrace()[3],那0,1,2是做什么的？所以我试一下
     */
    public static void stackTraceDemo() {
        StackTraceElement[] array = Thread.currentThread().getStackTrace();
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                StackTraceElement item = array[i];
                String methodName = item.getMethodName();
                String className = item.getClassName();
                // 因为className中有很多包名，看上去很不方便，所以我们只想让其显示最后一个点后边的东西，也就是类的名字
                int classIndex = className.lastIndexOf(".");
                className = className.substring(classIndex + 1);
                String logStr = Thread.currentThread().getId() + " "
                        + Thread.currentThread().getName() + " " + className
                        + "  " + methodName;
                log(logStr);
            }
        }
    }

}
