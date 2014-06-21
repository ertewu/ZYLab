package com.example.demo.cases;

/**
 * 为了实验activity与fragment的onSaveInstanceState与onRestoreInstanceState的,<br>
 * 见：http://gundumw100.iteye.com/blog/1115080 <br>
 * 主要原因是 这个"容易销毁"的时机确实不好整..我需要试一试之类的,而且使用场景是什么也不好想像
 */
public class CaseInstanceState {

    private static CaseInstanceState sCase;

    public static CaseInstanceState obtain() {
        if (sCase == null) {
            sCase = new CaseInstanceState();
        }
        return sCase;
    }

    public void work() {

    }

}
