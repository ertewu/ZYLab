package zystudio.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.logging.LogRecord;

/**
 * Created by wangzhengyu on 2017/11/20.
 */

public class MainHandler {

    private static Handler sInstance;
    public static  Handler obtain(){
        if(sInstance==null){
            sInstance=new Handler(Looper.myLooper());
        }
        return sInstance;
    }

}
