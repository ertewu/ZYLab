package zystudio.demo.scrolls;

import zystudio.mylib.utils.LogUtil;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 用于实验scrollView的一些函数
 *
 */
public class MyScrollView extends ScrollView {

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX,
            boolean clampedY) {
        LogUtil.log("onOverscrolledOcured,ScrollY :" + scrollY);
        // LogUtil.printStackTrace(null, 3, 7);
        LogUtil.log("                                                                                  ");
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    /**
     * 这个方法在scrollView里起码是可以弹的...
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
            int scrollY, int scrollRangeX, int scrollRangeY,
            int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        maxOverScrollY = 300;
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
                scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY,
                isTouchEvent);
    }
}
