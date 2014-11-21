package zystudio.cases.graphics.view;

import zystudio.demo.R;
import android.app.Activity;

public class CaseDecorMeasureInfo {
    private static CaseDecorMeasureInfo sCase;
    private Activity mAct;

    public static CaseDecorMeasureInfo instance(Activity act) {
        if (sCase == null) {
            sCase = new CaseDecorMeasureInfo();
            sCase.mAct = act;
        }
        return sCase;
    }

    public void work() {
        // mAct.setContentView(new TestMeausreInfoView(mAct));
        // 如果使用自定义view，就尽量不要使用layout这种形式看来
        mAct.setContentView(R.layout.case_decor_measure);
    }
}
