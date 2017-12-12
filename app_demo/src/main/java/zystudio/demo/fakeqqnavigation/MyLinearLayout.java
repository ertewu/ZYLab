package zystudio.demo.fakeqqnavigation;

import zystudio.mylib.utils.LogUtil;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class MyLinearLayout extends ViewGroup implements OnGestureListener {

    private Scroller mScroller;
    private Context mContext;
    private GestureDetector mGestureDetector;

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mScroller = new Scroller(context);
        mGestureDetector = new GestureDetector(getContext(), this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams lp = child.getLayoutParams();
            final int childWidthMeasureSpec = getChildMeasureSpec(
                    widthMeasureSpec, 0, lp.width);
            final int childHeightMeasureSpec = getChildMeasureSpec(
                    heightMeasureSpec, 0, lp.height);

            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }
        // setMeasuredDimen用系统的方法..
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = r - l;
        int childTop = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int childHeight = child.getMeasuredHeight();
            LogUtil.log(i + "_childHeight is:" + childHeight);
            child.layout(0, childTop, width, childTop + childHeight);
            childTop += childHeight;
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isHandled = mGestureDetector.onTouchEvent(event);
        return isHandled;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        LogUtil.log("onDown occured");
        // return false;
        // 一定要return true才会走onScroll与onFling
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        LogUtil.log("onShowPress occured");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        LogUtil.log("onSingleTapUp occured");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
            float distanceY) {
        LogUtil.log("onScroll occured");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        LogUtil.log("onLongPress occured");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
            float velocityY) {
        LogUtil.log("onFling occured");
        return false;
    }

}
