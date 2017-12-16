package zystudio.demo.views;

import zystudio.mylib.utils.LogUtil;
import zystudio.mylib.utils.ScreenUtil;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class TestTouchView extends ImageView {
    public boolean mAllowCustomDispatch = false;
    public boolean mDispatchReturnValue = false;

    public boolean mAllowCustomOnTouch = true;

    public boolean mOnTouchReturnValue = true;

    public TestTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.log("TestTouchView-dispatchTouchEvent:" + ScreenUtil.getMotionActionStr(ev.getAction()));
        boolean result = super.dispatchTouchEvent(ev);
        if (mAllowCustomDispatch) {
            result = mDispatchReturnValue;
        }
        LogUtil.log("TestTouchView-dispatchTouchEvent:" + ScreenUtil.getMotionActionStr(ev.getAction()) + "|result:"
                + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        LogUtil.log("TestTouchView-onTouchEvent:" + ScreenUtil.getMotionActionStr(ev.getAction()));
        boolean result = super.onTouchEvent(ev);
        if (mAllowCustomOnTouch) {
            result = mOnTouchReturnValue;
        }
        LogUtil.log("TestTouchView-onTouchEvent:" + ScreenUtil.getMotionActionStr(ev.getAction()) + "|result:" + result);
        return result;
    }
}
