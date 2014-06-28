package zystudio.demo.cases;

import zystudio.demo.views.AnimImageView;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.widget.TextView;

import zystudio.demo.R;

/**
 * 这是一个自定义属性动画的案例，还用了一个自定义功能的ImageView,可以在view上方加一个半透明的黑mask <br>
 * 找一些网址： http://www.cnblogs.com/angeldevil/archive/2011/12/02/2271096.html
 * 我记得有更好的get与set方法的
 *
 * 看APIdemo中的CustomEvalutor 怎么做的，就该那么做
 */
public class CaseForCustomAnim1 {

    private static CaseForCustomAnim1 sCase;
    private Activity mAct;

    public static CaseForCustomAnim1 obtain(Activity activity) {
        if (sCase == null) {
            sCase = new CaseForCustomAnim1(activity);
        }
        return sCase;
    }

    private CaseForCustomAnim1(Activity activity) {
        this.mAct = activity;
    }

    public void work() {
        // work1();
        // work2();
        work3();
    }

    public void work1() {
        mAct.setContentView(R.layout.caseforanimview);
        AnimImageView view = (AnimImageView) mAct.findViewById(R.id.anim_view);
        view.displayAnim();
    }

    public void work2() {

        mAct.setContentView(R.layout.caseforanimview);
        TextView view = (TextView) mAct.findViewById(R.id.tv);

        final int RED = 0xffFF8080;
        final int BLUE = 0xff8080FF;

        ValueAnimator colorAnim = ObjectAnimator.ofInt(view, "backgroundColor",
                RED, BLUE);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }

    /**
     * 这个并不是为了试验AnimImageView的mask黑色的功能，<br>
     * 而是要试验ImageView的setColorFiler或Drawable的setColorFiler的功能
     */
    public void work3() {
        mAct.setContentView(R.layout.caseforanimview);
        AnimImageView view = (AnimImageView) mAct.findViewById(R.id.anim_view);

    }

}
