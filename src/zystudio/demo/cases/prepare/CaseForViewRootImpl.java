package zystudio.demo.cases.prepare;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;

import zystudio.mylib.utils.LogUtil;

/**
 * 主要是通过log查看一个View 的onMeasure,onLayout,onDraw是从哪里调用的，即起源于ViewRootImpl的哪里
 * 从log来看，确实是performTraversal中的顺序，onMeasure第一，onLayout 第二， onDraw 最后调用 <br>
 * 但是 这个例子中 onMeasure 与onLayout 不止走了一次，最后onDraw才走 <br>
 * 可以再好好分析一下这个树..
 *
 * 下边还有keyDown 以及touch 的分析、以及focus的流程，focus的order探究，也用类似<br>
 * 同时，ViewRootImpl中的draw函数，也很有看头的样子<br>
 *
 */
public class CaseForViewRootImpl {

    private static CaseForViewRootImpl sCase;
    private Activity mAct;

    public static CaseForViewRootImpl getInstance(Activity activity) {
        if (null == sCase) {
            sCase = new CaseForViewRootImpl(activity);
        }
        return sCase;
    }

    private CaseForViewRootImpl(Activity activity) {
        this.mAct = activity;
    }

    public void work() {
        View view = new LogView(mAct);
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        // view.setBackgroundResource(R.drawable.motor);
        mAct.setContentView(view);
    }

    public static class LogView extends View {

        public LogView(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            LogUtil.printStackTrace();
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right,
                int bottom) {
            LogUtil.printStackTrace();
            super.onLayout(changed, left, top, right, bottom);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            LogUtil.printStackTrace();
            super.onDraw(canvas);
        }

    }
}
