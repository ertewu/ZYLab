package zystudio.mylib.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

public class ScreenUtil {

    //Get the child's index of its  parent
    public int getChildIndex(View child,ViewGroup parent) {
        for (int i = 0; i <parent. getChildCount(); i++) {
            View item = parent.getChildAt(i);
            if (child == item) {
                return i;
            }
        }
        return -1;
    }

    // private static Integer SDK;
    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }

    public static String getMeasureSpecInfo(int measureSpec) {
        final int mode = MeasureSpec.getMode(measureSpec);
        final int size = MeasureSpec.getSize(measureSpec);
        String modeStr = null;
        switch (mode) {
        case MeasureSpec.AT_MOST:
            modeStr = "AT_MOST";
            break;
        case MeasureSpec.EXACTLY:
            modeStr = "EXACTLY";
            break;
        case MeasureSpec.UNSPECIFIED:
            modeStr = "UNSPECIFIED";
            break;
        default:
            modeStr = "Error! not exists a mode value!";
        }
        return modeStr + "|size is:" + size;
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
