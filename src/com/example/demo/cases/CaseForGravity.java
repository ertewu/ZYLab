package com.example.demo.cases;


public class CaseForGravity {
    private static CaseForGravity sCase;

    public static CaseForGravity obtain() {
        if (sCase == null) {
            sCase = new CaseForGravity();
        }
        return sCase;
    }

    public void work() {

    }
}
