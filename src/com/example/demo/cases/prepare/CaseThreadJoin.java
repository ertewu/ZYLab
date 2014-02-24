package com.example.demo.cases.prepare;

import android.content.Context;
import android.util.Log;

/**
 * 我的例子有些迷惑，这个Demo构造比想像中复杂啊： http://blog.csdn.net/bzwm/article/details/3881392
 *
 * 迷惑就在于：子线程的start 也当前线程的 join 这两个有调用先后顺序问题么？</br>
 * join是在run中调用，还是在外部的调用的，这一点有关系么？
 */
public class CaseThreadJoin {
    private static CaseThreadJoin sCase;
    private Context mCtx;

    public static CaseThreadJoin getInstance(Context ctx) {
        if (null == sCase) {
            sCase = new CaseThreadJoin(ctx);
        }
        return sCase;
    }

    private CaseThreadJoin(Context ctx) {
        this.mCtx = ctx;
    }

    public void work() {
        BeginThread beginThread = new BeginThread();
        SubThread subThread = new SubThread();
        beginThread.setSubThread(subThread);
        beginThread.start();
    }

    private void logNum() {
        for (int i = 0; i < 5; i++) {
            Log.i("ertewu", Thread.currentThread().getName() + ":" + i);
            try {
                Thread.currentThread().sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class BeginThread extends Thread {

        private SubThread mSubThread;

        public BeginThread() {
            this.setName("BeginThread");
        }

        public void setSubThread(SubThread thread) {
            this.mSubThread = thread;
        }

        @Override
        public void run() {
             work();
        }

        private void work() {
            if (null != mSubThread) {
                mSubThread.start();
                logNum();
            }
        }

//        private void workJoin() {
//            try {
//                //无论这个join放在mSubThread前边还是后边，这个例子都是不正确的，这个使用都是有些问题的
//                this.join();
//                mSubThread.start();
//                logNum();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }

    private class SubThread extends Thread {
        public SubThread() {
            this.setName("SubsequentThread");
        }

        @Override
        public void run() {
            logNum();
        }
    }
}
