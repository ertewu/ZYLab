package com.example.demo.cases;

import java.util.Random;

import android.app.Activity;

import com.util.LogUtils;

/**
 * Java 编程思想第7章，7.8的例子
 * @author WZY
 *
 */
public class CaseForFinal {

    private static CaseForFinal sCase;
    private static Activity mAct;

    public static CaseForFinal getInstance(Activity act) {
        if (sCase == null) {
            sCase = new CaseForFinal();
            sCase.init(act);
        }
        return sCase;
    }

    private void init(Activity act) {
        this.mAct = act;
    }

    public void work() {
        // work1();
        // work2();
        // work3();
        work4();
    }

    private void work1() {
        FinalData fd1 = new FinalData("fd1");
        log(fd1.v2.i);
        fd1.v2.i++;
        // 从这里看到v2是可以改变的,final不是让其不能改变
        log(fd1.v2.i);
        fd1.v1 = new Value(9);
        for (int i = 0; i < fd1.a.length; i++) {
            fd1.a[i]++;
        }
        log(fd1);
        log("Creating new FinalData");
        FinalData fd2 = new FinalData("fd2");
        log(fd1);
        log(fd2);
    }

    private void work2() {
        new BlankFinal();
        new BlankFinal(47);
    }

    private void work3() {
        FinalArguments bf = new FinalArguments();
        bf.without(null);
        bf.with(null);
    }

    private void work4() {
        OverridingPrivate2 op2 = new OverridingPrivate2();
        op2.f();
        op2.g();
    }

    private static void log(Object obj){
        LogUtils.log(obj);
    }

    // p176
    public static class WithFinals {
        private final void f() {
            log("WithFinals.f()");
        }

        private void g() {
            log("WithFinals.g()");
        }
    }

    static class OverridingPrivate extends WithFinals {
        private final void f() {
            log("OverridingPrivate f()");
        }

        private void g() {
            log("OverridingPrivate.g()");
        }
    }

    static class OverridingPrivate2 extends OverridingPrivate {
        public final void f() {
            log("OverridingPrivate2.f()");
        }

        public void g() {
            log("OverridingPrivate2.g()");
        }
    }

    // p175
    static class Gizmo {
        public void spin() {
        }
    }

    public static class FinalArguments {
        void with(final Gizmo g) {
            // ! g=new Gizmo();
        }

        void without(Gizmo g) {
            g = new Gizmo();
            g.spin();
        }

        // void f(final int i){i++;}
        int g(final int i) {
            return i + 1;
        }
    }

    private static class Value {
        int i;

        public Value(int i) {
            this.i = i;
        }
    }

    private static class FinalData {
        private static Random rand = new Random(47);
        private String id;

        public FinalData(String id) {
            this.id = id;
        }

        // Can be compile-time constants;
        private final int valueOne = 9;
        private static final int VALUE_TWO = 99;
        // Typical public constant:
        public static final int VALUE_THREE = 39;
        // Cannot be compile-time constants:
        private final int i4 = rand.nextInt(20);
        static final int INT_5 = rand.nextInt(20);

        private Value v1 = new Value(11);
        private final Value v2 = new Value(22);
        private static final Value VAL_3 = new Value(33);

        // Arrays
        private final int[] a = { 1, 2, 3, 4, 5, 6 };

        @Override
        public String toString() {
            return id + ": " + "i4=" + i4 + ". INT_5=" + INT_5;
        }
    }

    private static class Poppet {
        private int i;

        Poppet(int ii) {
            i = ii;
        }
    }

    public class BlankFinal {
        private final int i = 0;
        private final int j;
        private final Poppet p;

        public BlankFinal() {
            j = 1;
            p = new Poppet(1);
        }

        public BlankFinal(int x) {
            j = x;
            p = new Poppet(x);
        }
    }
}
