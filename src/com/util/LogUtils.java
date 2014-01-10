package com.util;

import android.util.Log;

public class LogUtils {

    public static void log(Object str) {
        Log.i("ertewu", "" + str);
    }

    /**
     * 这个footPrint是从项目中拿出来的，应该是最好用的
     */
    public static void footPrint(String appendMsg) {
        String msgToPrint = "--------------footprint start------------------\n";
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        int index = className.lastIndexOf(".");
        if (index > -1) {
            className = className.substring(index + 1);
        }
        msgToPrint = Thread.currentThread().getId() + " " + className + "."
                + Thread.currentThread().getStackTrace()[3].getMethodName();
        msgToPrint = msgToPrint + "\n";
        msgToPrint = msgToPrint + appendMsg + "\n";
        msgToPrint = msgToPrint + "--------------footprint end----------------";
        Log.i("ertewu", msgToPrint);
    }

    /**
     * 这个是意思我不懂为什么是Thread.currentThread.getStackTrace()[3],那0,1,2是做什么的？所以我试一下
     */
    public static void printStackTrace(String appendMsg) {
        StringBuilder builder = new StringBuilder();
        builder.append("--------------printStackTrace start----------------\n");
        StackTraceElement[] array = Thread.currentThread().getStackTrace();
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (i > 3) {
                    StackTraceElement item = array[i];
                    String methodName = item.getMethodName();
                    String className = item.getClassName();
                    // 因为className中有很多包名，看上去很不方便，所以我们只想让其显示最后一个点后边的东西，也就是类的名字
                    int classIndex = className.lastIndexOf(".");
                    className = className.substring(classIndex + 1);
                    String logStr = Thread.currentThread().getId() + " " + Thread.currentThread().getName() + " " + className
                            + "  " + methodName + "\n";
                    builder.append(logStr);
                }
            }
            builder.append(appendMsg + "\n");
            builder.append("--------------printStackTrace end----------------");
        }
    }

}
