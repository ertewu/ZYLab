package zystudio.mylib.utils;

import android.text.TextUtils;
import android.util.Log;

public class LogUtil {
    public final static String TAG = "ZYStudio";

    public static void log(String str) {
        Log.i(TAG, str);
    }
    public static void log(String tag,String msg){
        Log.i(tag, msg);
    }

    /**
     * 打印调用printFuncInfo 的那个方法的信息:包括方法所在的类，方法名称,以及当前调用所在的线程Id
     *
     * @param appendMsg
     *            调用者自己额外填加的一些信息
     */
    public static void printFuncInfo(String appendMsg) {
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
        log(msgToPrint);
    }

    public static void printStackTrace() {
        printStackTrace(null);
    }

    /**
     * 打印printStackTrace的调用栈,包括每一级的调用类名，调用方法名，线程名称及Id
     *
     * @param appendMsg
     *            调用者自己额外添加的一些信息
     */
    public static void printStackTrace(String appendMsg) {
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append("PrintStackStart------------------------\n");
        StackTraceElement[] array = Thread.currentThread().getStackTrace();
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (i > 2) {
                    StackTraceElement item = array[i];
                    String methodName = item.getMethodName();
                    String className = item.getClassName();
                    // 因为className中有很多包名，看上去很不方便，所以我们只想让其显示最后一个点后边的东西，也就是类的名字
                    int classIndex = className.lastIndexOf(".");
                    className = className.substring(classIndex + 1);
                    String log = Thread.currentThread().getId() + " "
                            + Thread.currentThread().getName() + " " + className + "  "
                            + methodName + "\n";
                    logBuilder.append(log);
                }
            }
            if (!TextUtils.isEmpty(appendMsg)) {
                logBuilder.append(appendMsg + "\n");
            }
            logBuilder.append("PrintStackEnd------------------------\n");
            log(logBuilder.toString());
        }
    }

    public static void printInvokeFuncMsg(){
        StackTraceElement[] array = Thread.currentThread().getStackTrace();
        int maxRange=array.length-2;
        int minRange=array.length-4;
        printStackTrace("",minRange,maxRange);
    }

    public static void printStackTrace(String appendMsg, int minRange, int maxRange) {
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append("PrintStackStart------------------------\n");
        StackTraceElement[] array = Thread.currentThread().getStackTrace();
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if ((i >= minRange) && i <= maxRange) {
                    StackTraceElement item = array[i];
                    String methodName = item.getMethodName();
                    String className = item.getClassName();
                    // 因为className中有很多包名，看上去很不方便，所以我们只想让其显示最后一个点后边的东西，也就是类的名字
                    int classIndex = className.lastIndexOf(".");
                    className = className.substring(classIndex + 1);
                    String log = Thread.currentThread().getId() + " "
                            + Thread.currentThread().getName() + " " + className + "  "
                            + methodName + "\n";
                    logBuilder.append(log);
                }
            }
            if (!TextUtils.isEmpty(appendMsg)) {
                logBuilder.append(appendMsg + "\n");
            }
            logBuilder.append("PrintStackEnd------------------------\n");
            log(logBuilder.toString());
        }
    }

    public static void printTime(String perfix) {
        long time = System.currentTimeMillis() % 100000;
        log(perfix + " :" + time);
    }

    public static void logException(Exception e) {
        logException(null, e);
    }

    public static void logException(String prefix, Exception e) {
        prefix=TextUtils.isEmpty(prefix)?"":prefix+":";
        LogUtil.log(prefix + e.getClass().getSimpleName() + "|" + e.getMessage());
    }
}