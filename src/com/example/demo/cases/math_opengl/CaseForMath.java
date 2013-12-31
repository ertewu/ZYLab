package com.example.demo.cases.math_opengl;

import android.util.Log;

public class CaseForMath {

    private static CaseForMath sCase;

    public static CaseForMath obtain() {
        if (sCase == null) {
            sCase = new CaseForMath();
        }
        return sCase;
    }

    public void work() {
        computeFunc();
//        computeRadios();
    }

    private void computeFunc(){
        float tempAngle = 360 / 6;
        double toRadian=Math.toRadians(tempAngle);
        double result=Math.cos(toRadian);
        //cos(PI/3)也即cos(60')，确实就是0.5
        Log.i("ertewu", "result is:"+result);
    }

    private void computeRadios() {
        float tempAngle = 360 / 6;
        for (float angle = 0; angle < 360; angle += tempAngle) {
            Log.i("ertewu", "r33:" + angle + "|" + Math.toRadians(angle));
        }
        /**
         * 从结果来看就是把角度制转成弧度制,比如60.那Math.toRadians(60)是1.04719.. 其实就是PI/3
         */
    }
}
