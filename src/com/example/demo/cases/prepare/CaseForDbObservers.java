package com.example.demo.cases.prepare;

import android.app.Activity;

/**
 * 主要是ContentObserver与DataSetObserver
 * 两者的对比，两者经常混啊，与cursor结合用起来，不知道哪个是cursor中的内容变了自动通知，还是不自动通知之类的，反正就那样
 *
 */
public class CaseForDbObservers {

    private static CaseForDbObservers sCase;
    private Activity mAct;

    public static CaseForDbObservers getInstance(Activity act) {
        if (sCase == null) {
            sCase = new CaseForDbObservers();
        }
        sCase.init(act);
        return sCase;
    }

    private void init(Activity act) {
        this.mAct = act;
    }

    public void work() {

    }
}
