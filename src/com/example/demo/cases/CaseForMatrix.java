package com.example.demo.cases;

import android.content.Context;

public class CaseForMatrix {
    private static CaseForMatrix sCase;
    private Context mContext;

    public static CaseForMatrix obtain(Context context) {
        if (sCase == null) {
            sCase = new CaseForMatrix();
        }
        sCase.init(context);
        return sCase;
    }

    private CaseForMatrix() {

    }

    private void init(Context ctx) {
        mContext = ctx;
    }

    public void work(){
//        Test postScale函数
    }
}
