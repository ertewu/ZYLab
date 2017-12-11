package zystudio.cases.androidmechanism;

import android.app.Activity;
import android.content.Context;

import zystudio.mylib.utils.LogUtil;

/**
 * 想看看到底那个Context 都是什么类..
 */
public class CaseContextKinds {
    private static CaseContextKinds sInstance;
    private Activity mActivity;

    private CaseContextKinds() {

    }

    public static CaseContextKinds obtain(Activity activity) {
        if (sInstance == null) {
            sInstance = new CaseContextKinds();
        }
        sInstance.mActivity=activity;
        return sInstance;
    }


    public void work() {
        Context appFromActivity= mActivity.getApplication();
        LogUtil.log("appFromActivity:"+appFromActivity.getClass().getName());

        Context appContextFromActivity= mActivity.getApplicationContext();
        LogUtil.log("appContextFromActivity:"+appContextFromActivity.getClass().getName());

        Context appContextFromApplication=mActivity.getApplication().getApplicationContext();
        LogUtil.log("appContextFromApplication:"+appContextFromApplication.getClass().getName());

        /**
         * 结论： 嗯，没错， 这三个都返回了 zystudio.demo.ZYDemoApp 。。。这可能只是接口的思路才会有各不同的函数了。它们返回是都一样的
         */

    }




}
