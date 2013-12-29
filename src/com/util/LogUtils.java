package com.util;

import android.util.Log;

public class LogUtils {

    public static void log(Object str) {
        Log.i("ertewu", "" + str);
    }

    /**
     * 能打印出当前函数的invoke，继而看出问题来..
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

}
