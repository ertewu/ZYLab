package com.example.demo.cases;

import android.app.Activity;

public class CaseForDbObservers {

    private static CaseForDbObservers sCase;
    private Activity mAct;
    public static CaseForDbObservers getInstance(Activity act){
        if(sCase==null){
            sCase=new CaseForDbObservers();
        }
        sCase.init(act);
        return sCase;
    }

    private void init(Activity act){
        this.mAct=act;
    }

    public void work(){

    }
}
