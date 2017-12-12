package zystudio.cases.graphics.view;

import zystudio.demo.R;
import android.app.Activity;

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
        showNormalCase();
//        showDefStyleUsage();
    }

    private void showNormalCase(){
        // xml中设不设style,defStyle这个参数都为0，且CustAttrView的目前的三种声明都没有defStyle
        mActivity.setContentView(R.layout.case_custview_attr);
    }

//    private void showDefStyleUsage(){
//        mActivity.setTheme(R.style.AppTheme);
//        mActivity.setContentView(R.layout.case_custview_attr);
//    }
}
