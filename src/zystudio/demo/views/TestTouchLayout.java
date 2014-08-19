package zystudio.demo.views;

import zystudio.mylib.utils.LogUtil;
import zystudio.mylib.utils.ScreenUtil;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class TestTouchLayout extends FrameLayout {

    public boolean mIsAllowCustomDispatch = false;
    public boolean mDispatchReturnValue = false;

    public boolean mAllowInterruptCustom = false;
    public boolean mOnInterruptReturnValue = false;

    public boolean mAllowOnTouchCustom = false;
    public boolean mOnTouchReturnValue = false;

    public TestTouchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            LogUtil.log("-----------------");
        }
        LogUtil.log("TestTouchLayout-dispatchTouchEvent:" + ScreenUtil.getMotionActionStr(ev.getAction()));
        boolean result = super.dispatchTouchEvent(ev);
        if (mIsAllowCustomDispatch) {
            result = mDispatchReturnValue;
        }
        LogUtil.log("TestTouchLayout-dispatchTouchEvent:" + ScreenUtil.getMotionActionStr(ev.getAction())
                + "|result is:" + result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.log("TestTouchLayout-onInterceptTouchEvent:" + ScreenUtil.getMotionActionStr(ev.getAction()));
        boolean result = super.onInterceptTouchEvent(ev);
        if (mAllowInterruptCustom) {
            result = mOnInterruptReturnValue;
        }
        LogUtil.log("TestTouchLayout-onInterruptTouchEvent:" + ScreenUtil.getMotionActionStr(ev.getAction())
                + "|result is:" + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        LogUtil.log("TestTouchLayout-onTouchEvent:" + ScreenUtil.getMotionActionStr(ev.getAction()));
        boolean result = super.onTouchEvent(ev);
        if (mAllowOnTouchCustom) {
            result = mOnTouchReturnValue;
        }
        LogUtil.log("TestTouchLayout-onTouchEvent:" + ScreenUtil.getMotionActionStr(ev.getAction()) + "|result:"
                + result);
        return result;
    }
}
