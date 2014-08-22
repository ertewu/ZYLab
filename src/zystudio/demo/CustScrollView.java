package zystudio.demo;

import zystudio.mylib.utils.LogUtil;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class CustScrollView extends ViewGroup implements OnGestureListener {

    public static final int TOUCH_STATE_DONT_CARE = 0;
    public static final int TOUCH_STATE_HORIZONTAL_SCROLLING = 1;
    public static final int TOUCH_STATE_HORIZONTAL_FLING = 2;

    private static final int SNAP_VELOCITY_Y = 500;

    private int mTouchState = TOUCH_STATE_DONT_CARE;

    private Scroller mScroller;
    private Context mContext;
    private GestureDetector mGestureDetector;
    private int mTouchSlopY = 5;

    public CustScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mScroller = new Scroller(context);
        mGestureDetector = new GestureDetector(getContext(), this);
    }

    private void responseTouchScroll(int distanceY) {
        if (getScrollX() < 0 || getScrollX() > getHeight()) {
            return;
        }
        scrollBy(0, distanceY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams lp = child.getLayoutParams();
            child.measure(widthMeasureSpec, heightMeasureSpec);
        }
        // setMeasuredDimen用系统的方法..
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    // @Override
    // protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    // int count = getChildCount();
    // for (int i = 0; i < count; i++) {
    // View child = getChildAt(i);
    // LayoutParams params = child.getLayoutParams();
    // // wrap content是-2,match_parent是-1
    // if (params.height > 0) {
    // // height是自定义的
    // int childheightMeasureSpec = MeasureSpec.makeMeasureSpec(
    // params.height, MeasureSpec.EXACTLY);
    // // width是用parent的
    // child.measure(widthMeasureSpec, childheightMeasureSpec);
    // }
    // }
    // // setMeasuredDimen用系统的方法..
    // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    // }

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
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            mTouchState = TOUCH_STATE_DONT_CARE;
        }

        boolean isIntercept;
        boolean isNeedIntercept = mGestureDetector.onTouchEvent(ev);
        if (isNeedIntercept) {
            isIntercept = isNeedIntercept;
        } else {
            isIntercept = super.onInterceptTouchEvent(ev);
        }
        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP
                || action == MotionEvent.ACTION_CANCEL) {
            if (mTouchState == TOUCH_STATE_HORIZONTAL_SCROLLING) {
                mTouchState = TOUCH_STATE_DONT_CARE;
            }
        }

        boolean isHandled = mGestureDetector.onTouchEvent(event);
        if (isHandled) {
            return true;
        } else {
            if (action == MotionEvent.ACTION_DOWN && !super.onTouchEvent(event)) {
                return true;
            } else {
                return super.onTouchEvent(event);
            }
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
            float velocityY) {
        LogUtil.log("onFling occuredY:" + velocityY);
        if (velocityY > SNAP_VELOCITY_Y || velocityY < -SNAP_VELOCITY_Y) {
            mTouchState = TOUCH_STATE_HORIZONTAL_FLING;
            mScroller.fling(0, getScrollX(), 0, (int) velocityY, 0, 0, 0,
                    getHeight());
            return true;
        }
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
            float distanceY) {
        return false;
    }

    // @Override
    // public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
    // float distanceY) {
    // LogUtil.log("onScroll occured");
    // if (mTouchState == TOUCH_STATE_HORIZONTAL_SCROLLING) {
    // responseTouchScroll((int) distanceY);
    // } else {
    // if (Math.abs(distanceY) > mTouchSlopY) {
    // mTouchState = TOUCH_STATE_HORIZONTAL_SCROLLING;
    // responseTouchScroll((int) distanceY);
    // } else {
    // mTouchState = TOUCH_STATE_DONT_CARE;
    // return false;
    // }
    // }
    // return true;
    // }
}
