package zystudio.demo.scrolls;

import zystudio.mylib.utils.LogUtil;
import zystudio.mylib.utils.ScreenUtil;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class QQNavigationLayout extends ViewGroup implements OnGestureListener {

    private Scroller mScroller;
    private Context mContext;
    private GestureDetector mGestureDetector;
    // 这个值在i9100上是12
    private int mTouchSlopY;
    private int mAllChildHeight;
    private int mMinFlingVelocity;
    private int mMaxFlingVelocity;

    public QQNavigationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mScroller = new Scroller(context);
        mGestureDetector = new GestureDetector(getContext(), this);
        final ViewConfiguration config = ViewConfiguration.get(context);
        mTouchSlopY = config.getScaledTouchSlop();
        mMinFlingVelocity = config.getScaledMinimumFlingVelocity();
        mMaxFlingVelocity = config.getScaledMaximumFlingVelocity();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams lp = child.getLayoutParams();
            final int childWidthMeasureSpec = getChildMeasureSpec(
                    widthMeasureSpec, 0, lp.width);
            // final int childHeightMeasureSpec = getChildMeasureSpec(
            // heightMeasureSpec, 0, lp.height);

            // 这样写heightMeasureSpec，就可以得到child自己想要的大小了，可以比parent更大..
            final int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }
        // setMeasuredDimen用系统的方法，super.onMeasure也就是viewgroup的onMeausre，只有setMeasuredDimen这一个方法调用
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.log("onInterrceptTouchEvent:"
                + ScreenUtil.getMotionActionStr(ev.getAction()));
        boolean isNeedInterrupt = mGestureDetector.onTouchEvent(ev);
        boolean result = false;
        if (isNeedInterrupt) {
            result = true;
        } else {
            result = super.onInterceptTouchEvent(ev);
        }
        LogUtil.log("onInterrceptTouchEvent result:" + result);

        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isHandled = mGestureDetector.onTouchEvent(event);
        if (isHandled) {
            return true;
        }
        boolean result = super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!mScroller.isFinished()) {
                // 这个是为了在按下时，finish
                // fling的动作，虽然注释上说abortAnimation与forceFinished是相反的，但在我这里好像都能起到一样的作用
                // mScroller.abortAnimation();
                mScroller.forceFinished(true);
            }
            result = true;
        }
        return result;
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
        // 到了最后，所有child的高加在一起就是childTop了
        mAllChildHeight = childTop;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
            float distanceY) {
        LogUtil.log("onScroll occured");
        // 没想到这个mTouchSlopY还挺大的，很多scroll都走不了
        // if (Math.abs(distanceY) > mTouchSlopY) {
        if (Math.abs(distanceY) > 0) {
            return scrollPiece((int) distanceY);
        }
        return false;
    }

    /**
     * @return 真正有滑动即消费了这个动作，返回true;否则返回false
     */
    private boolean scrollPiece(int distanceY) {
        int currentScrollY = getScrollY();
        final int MIN_SCROLLY = 0;
        final int MAX_SCROLLY = mAllChildHeight - getHeight();

        LogUtil.log("MIN&&MAX_SCROLLY&&CurrentScrollY&&distance:" + MIN_SCROLLY
                + "|" + MAX_SCROLLY + "|" + currentScrollY + "|" + distanceY);

        int distanceToScroll = distanceY;
        if (distanceToScroll > 0) {
            // 大于0是往上滑的
            if (currentScrollY + distanceY > MAX_SCROLLY) {
                distanceToScroll = MAX_SCROLLY - currentScrollY;
            }
        } else if (distanceToScroll < 0) {
            // 小于0是往下滑的
            if (currentScrollY + distanceY < MIN_SCROLLY) {
                distanceToScroll = MIN_SCROLLY - currentScrollY;
            }
        }
        // 没有任何弹性
        if (distanceToScroll != 0) {
            scrollBy(0, distanceToScroll);
            return true;
        }
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
            float velocityY) {
        LogUtil.log("onFling occured");
        if (Math.abs(velocityY) > mMinFlingVelocity) {
            mScroller.fling(getScrollX(), getScrollY(), 0, (int) -velocityY, 0,
                    0, 0, mAllChildHeight - getHeight());
            postInvalidateOnAnimation();
            return true;
        } else {
            // 这个else是什么情况的说..
        }
        return false;
    }

    public void scrollChildToTop(View childView) {
        // 只有是last的时候才有这个问题
        // if (childView.getHeight() < getHeight()) {
        // throw new IllegalStateException(
        // "Error the child short than its parent,How to scroll to TOP!");
        // }
        //
        int location[] = new int[2];
        childView.getLocationInWindow(location);
        LogUtil.log("scrollY is:" + location[1]);
        // 38是我i9100的通知栏的高度
        mScroller.startScroll(0, getScrollY(), 0, location[1] - 38,
                500);
        postInvalidateOnAnimation();
    }

    /********************************** 底下这些垃圾回调都没什么用.. *****************************/
    @Override
    public void onLongPress(MotionEvent e) {
        LogUtil.log("onLongPress occured");
    }

    @Override
    public boolean onDown(MotionEvent e) {
        LogUtil.log("onDown occured");
        return false;
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
}
