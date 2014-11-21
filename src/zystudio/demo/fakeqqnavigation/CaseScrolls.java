package zystudio.demo.fakeqqnavigation;

import android.app.Activity;
import android.content.Intent;


public class CaseScrolls {

    private Activity mActivity;
    private static CaseScrolls sCase;


    public static CaseScrolls obtain(Activity activity) {
        if (sCase == null) {
            sCase = new CaseScrolls();
            sCase.mActivity = activity;
        }
        return sCase;
    }

    public void work() {
        mActivity.startActivity(new Intent(mActivity,
                CaseShowScrollActivity.class));
    }
}
