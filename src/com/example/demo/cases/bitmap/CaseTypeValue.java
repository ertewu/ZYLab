package com.example.demo.cases.bitmap;

/**
 * 这个类是用来实验TypedValue与TypedArray这两个类的，我来举一段代码：<br>
 * <code>
 * int resourceId;
 * Resources res =getContext().getResources();
 * TypedArray array=res.obtainTypedArray(resourceId);
 * final int count = array.length();
 * for(int index:count){
 *    TypedValue value = array.peekValue(index);
 * }
 * </code>
 * 
 */
public class CaseTypeValue {

    private static CaseTypeValue sCase;

    public static CaseTypeValue obtain() {
        if (sCase == null) {
            sCase = new CaseTypeValue();
        }
        return sCase;
    }

    public void work() {

    }
}
