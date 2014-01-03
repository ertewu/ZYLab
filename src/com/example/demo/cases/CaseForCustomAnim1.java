package com.example.demo.cases;

import android.app.Activity;

import com.example.demo.AnimImageView;
import com.example.demo.R;

/**
 * 这是一个自定义属性动画的案例，还用了一个自定义功能的ImageView,可以在view上方加一个半透明的黑mask
 */
public class CaseForCustomAnim1 {

    private static CaseForCustomAnim1 sCase;
    private AnimImageView view;
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
        mAct.setContentView(R.layout.caseforanimview);
        view = (AnimImageView) mAct.findViewById(R.id.anim_view);
        view.displayAnim();
    }
}
