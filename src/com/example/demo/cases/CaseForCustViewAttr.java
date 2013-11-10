package com.example.demo.cases;

import android.app.Activity;

import com.example.demo.R;

public class CaseForCustViewAttr {
    private static CaseForCustViewAttr sCase;
    private Activity mActivity;

    public static CaseForCustViewAttr obtain(Activity activity) {
        if (sCase == null) {
            sCase = new CaseForCustViewAttr();
        }
        sCase.init(activity);
        return sCase;
    }

    private void init(Activity activity) {
        this.mActivity = activity;
    }

    public void work() {
        mActivity.setContentView(R.layout.case_custview_attr);
    }

}
