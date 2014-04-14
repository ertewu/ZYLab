package com.example.demo.cases;

import com.example.demo.R;

import android.app.Activity;
import android.widget.FrameLayout;


public class CaseSpecialDrawable {

    private static CaseSpecialDrawable sCase;
    private Activity mAct;

    public static CaseSpecialDrawable obtain(Activity act) {
        if (sCase == null) {
            sCase = new CaseSpecialDrawable();
            sCase.mAct=act;
        }
        return sCase;
    }

    public void work(){
        workClipDrawable();
        workScaleDrawable();
    }
    
    private void workClipDrawable(){
        FrameLayout layout=new FrameLayout(mAct);
        mAct.setContentView(layout);
        //根本就显示不出来..
        layout.setBackgroundResource(R.drawable.scale_img);
    }
    
    private void workScaleDrawable(){
        
    }

}
