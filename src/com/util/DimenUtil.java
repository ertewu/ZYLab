package com.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

public class DimenUtil {
	
    // private static Integer SDK;
    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }

    public static String getMotionActionStr(int i) {
        String action = "";
        switch (i) {
        case MotionEvent.ACTION_CANCEL:
            action += "MotionEvent.ACTION_CANCEL";
            break;
        case MotionEvent.ACTION_DOWN:
            action += "MotionEvent.ACTION_DOWN";
            break;
        case MotionEvent.ACTION_UP:
            action += "MotionEvent.ACTION_UP";
            break;
        case MotionEvent.ACTION_MOVE:
            action += "MotionEvent.ACTION_MOVE";
            break;
        }
        return action;
    }
}
