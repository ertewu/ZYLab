package com.example.demo.cases.bitmap;

/**
 * Animation部分的PropertyValuesHolder类的使用demo,这个类的源码很有含量，抽象到Class，Method
 * 以及有不少native函数
 */
public class CasePropertyValuesHolder {

    private static CasePropertyValuesHolder sCase;

    public static CasePropertyValuesHolder obtain() {
        if (sCase == null) {
            sCase = new CasePropertyValuesHolder();
        }
        return sCase;
    }

    public void work() {

    }
}
