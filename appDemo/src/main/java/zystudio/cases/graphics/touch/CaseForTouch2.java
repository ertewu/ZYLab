package zystudio.cases.graphics.touch;

import zystudio.demo.R;
import zystudio.demo.views.TestTouchLayout;
import zystudio.demo.views.TestTouchView;
import android.app.Activity;

/**
 * 现在已经不了解CaseForTouch1是作什么的了，但是CaseForTouch2是用来了解
 * dispatchTouchEvent,onInterruptTouchEvent,onTouchEvent 在父ViewGroup与子view之间的关系的
 * <p>
 * 我选择继承自FrameLayout与继承自ImageView的自定义view,
 * 原因是FrameLayout与ImageView均是最简单的继承了ViewGroup与View的类，完全没有修改过任何touch中间的函数
 * <p>
 * dispatchTouchEvent与onTouchEvent是View中的函数<br>
 * onInterruptTouchEvent是ViewGroup中的函数，默认值是false
 * <p>
 * 一些已经确认的东西是父ViewGroup的dispatchTouchEvent一定是最先调用的，以这个为基础作case<br>
 * 父view的onTouchEvent是在子View的onTouchEvent之后调用的，其受到子view的onTouchEvent影响<br>
 * 每一次的起始都以父view的down为起点<br>
 *
 * ======== 通过大量的实验，我发现这个touch的可能性非常多，若要列出来，是不可穷尽的。但是这个例子已经足够。
 * 要理解这个touch事件，只能在保证其它不变的情况下，让某一个return 之类的改变，才能看到这个return带来的效果。就是这样
 *
 *<p>
 * 以及对于 layout的onInterruptTouchEvent函数
 * 对于不同的 action 拦截， 对于子View 的反应是不一样的
 * 如果 layout  在 action_down时就拦截时 。那子View 会完整的收不到 所有事件，事件直接回流到 layout的 onTouchEvent 中 <br>
 * 而如果 layout 在 action_move 时作了拦截 ，那就是子View 实际上已经收到过 前续事件了。 比如 action_down 肯定是收到了，或者之前的action_move 什么的
 * 那么，这时子View的 dispatchTouchEvent 就会收到onTouchEvent 会收到一个 action_cancel 事件。
 * 此后，再也收不到啥消息了就..所有消息继续走了layout的onTouch事件
 *
 */
public class CaseForTouch2 {

    private static CaseForTouch2 sCase;
    private Activity mAct;

    public static CaseForTouch2 obtain(Activity act) {
        if (sCase == null) {
            sCase = new CaseForTouch2();
        }
        sCase.setActivity(act);
        return sCase;
    }

    private void setActivity(Activity act) {
        this.mAct = act;
    }

    TestTouchLayout mLayout;
    TestTouchView mView;

    public void work() {
        mAct.setContentView(R.layout.casefortouch2);
        mLayout = (TestTouchLayout) mAct.findViewById(R.id.casetouch2_layout);
        mView = (TestTouchView) mAct.findViewById(R.id.casetouch2_view);
        setParentLayout();
        setChildLayout();
    }

    private void setParentLayout() {
        // mLayout.mAllowOnTouchCustom = true;
        // mLayout.mOnTouchReturnValue = true;

        // mLayout.mIsAllowCustomDispatch = true;
        // mLayout.mDispatchReturnValue = true;

        // mLayout.mAllowInterruptCustom = true;
        // mLayout.mOnInterruptReturnValue = true;
    }

    private void setChildLayout() {
        // mView.mAllowCustomOnTouch = true;
        // mView.mOnTouchReturnValue = true;
    }
}
