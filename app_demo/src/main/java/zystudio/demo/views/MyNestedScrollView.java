package zystudio.demo.views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

import zystudio.mylib.utils.LogUtil;

public class MyNestedScrollView extends NestedScrollView {
    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        boolean result = super.onStartNestedScroll(child, target, nestedScrollAxes);
        LogUtil.log( "onStartNestedScroll|" + child.getClass().getSimpleName() + "|" + target.getClass().getSimpleName() + "|" + nestedScrollAxes + "|" + result);
        return result;
    }


    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        LogUtil.log("onNestedScroll|" + target.getClass().getSimpleName() + "|" + target.getClass().getSimpleName() + "|" + dxConsumed + "|"
            + dyConsumed + "|" + dxUnconsumed + "|" + dyUnconsumed);
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        LogUtil.log("onNestedPreScroll|" + target.getClass().getSimpleName() + "|" + dx + "|" + dy + consumed);
        super.onNestedPreScroll(target, dx, dy, consumed);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        boolean result = super.onNestedFling(target, velocityX, velocityY, consumed);
        LogUtil.log("onNestedFling|" + target.getClass().getSimpleName() + "|" + velocityX + "|" + velocityY + consumed + "|" + result);
        return result;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        boolean result = super.onNestedPreFling(target, velocityX, velocityY);
        LogUtil.log("onNestedPreFling|" + target.getClass().getSimpleName() + "|" + velocityX + "|" + velocityY + "|" + result);
        return result;
    }

}
